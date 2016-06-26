package org.mozilla.javascript;

import org.mozilla.javascript.ast.FunctionNode;
import org.mozilla.javascript.ast.Jump;
import org.mozilla.javascript.ast.ScriptNode;

class CodeGenerator
  extends Icode
{
  private static final int ECF_TAIL = 1;
  private static final int MIN_FIXUP_TABLE_SIZE = 40;
  private static final int MIN_LABEL_TABLE_SIZE = 32;
  private CompilerEnvirons compilerEnv;
  private int doubleTableTop;
  private int exceptionTableTop;
  private long[] fixupTable;
  private int fixupTableTop;
  private int iCodeTop;
  private InterpreterData itsData;
  private boolean itsInFunctionFlag;
  private boolean itsInTryFlag;
  private int[] labelTable;
  private int labelTableTop;
  private int lineNumber;
  private ObjArray literalIds = new ObjArray();
  private int localTop;
  private ScriptNode scriptOrFn;
  private int stackDepth;
  private ObjToIntMap strings = new ObjToIntMap(20);
  
  private void addBackwardGoto(int paramInt1, int paramInt2)
  {
    int i = this.iCodeTop;
    if (i <= paramInt2) {
      throw Kit.codeBug();
    }
    addGotoOp(paramInt1);
    resolveGoto(i, paramInt2);
  }
  
  private void addExceptionHandler(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, int paramInt4, int paramInt5)
  {
    int i = 0;
    int j = this.exceptionTableTop;
    int[] arrayOfInt2 = this.itsData.itsExceptionTable;
    int[] arrayOfInt1;
    if (arrayOfInt2 == null)
    {
      if (j != 0) {
        Kit.codeBug();
      }
      arrayOfInt1 = new int[12];
      this.itsData.itsExceptionTable = arrayOfInt1;
    }
    for (;;)
    {
      arrayOfInt1[(j + 0)] = paramInt1;
      arrayOfInt1[(j + 1)] = paramInt2;
      arrayOfInt1[(j + 2)] = paramInt3;
      paramInt1 = i;
      if (paramBoolean) {
        paramInt1 = 1;
      }
      arrayOfInt1[(j + 3)] = paramInt1;
      arrayOfInt1[(j + 4)] = paramInt4;
      arrayOfInt1[(j + 5)] = paramInt5;
      this.exceptionTableTop = (j + 6);
      return;
      arrayOfInt1 = arrayOfInt2;
      if (arrayOfInt2.length == j)
      {
        arrayOfInt1 = new int[arrayOfInt2.length * 2];
        System.arraycopy(this.itsData.itsExceptionTable, 0, arrayOfInt1, 0, j);
        this.itsData.itsExceptionTable = arrayOfInt1;
      }
    }
  }
  
  private void addGoto(Node paramNode, int paramInt)
  {
    int i = getTargetLabel(paramNode);
    if (i >= this.labelTableTop) {
      Kit.codeBug();
    }
    int j = this.labelTable[i];
    if (j != -1)
    {
      addBackwardGoto(paramInt, j);
      return;
    }
    j = this.iCodeTop;
    addGotoOp(paramInt);
    paramInt = this.fixupTableTop;
    if ((this.fixupTable == null) || (paramInt == this.fixupTable.length)) {
      if (this.fixupTable != null) {
        break label116;
      }
    }
    for (this.fixupTable = new long[40];; this.fixupTable = paramNode)
    {
      this.fixupTableTop = (paramInt + 1);
      paramNode = this.fixupTable;
      long l = i;
      paramNode[paramInt] = (j | l << 32);
      return;
      label116:
      paramNode = new long[this.fixupTable.length * 2];
      System.arraycopy(this.fixupTable, 0, paramNode, 0, paramInt);
    }
  }
  
  private void addGotoOp(int paramInt)
  {
    byte[] arrayOfByte2 = this.itsData.itsICode;
    int i = this.iCodeTop;
    byte[] arrayOfByte1 = arrayOfByte2;
    if (i + 3 > arrayOfByte2.length) {
      arrayOfByte1 = increaseICodeCapacity(3);
    }
    arrayOfByte1[i] = ((byte)paramInt);
    this.iCodeTop = (i + 1 + 2);
  }
  
  private void addIcode(int paramInt)
  {
    if (!Icode.validIcode(paramInt)) {
      throw Kit.codeBug();
    }
    addUint8(paramInt & 0xFF);
  }
  
  private void addIndexOp(int paramInt1, int paramInt2)
  {
    addIndexPrefix(paramInt2);
    if (Icode.validIcode(paramInt1))
    {
      addIcode(paramInt1);
      return;
    }
    addToken(paramInt1);
  }
  
  private void addIndexPrefix(int paramInt)
  {
    if (paramInt < 0) {
      Kit.codeBug();
    }
    if (paramInt < 6)
    {
      addIcode(-32 - paramInt);
      return;
    }
    if (paramInt <= 255)
    {
      addIcode(-38);
      addUint8(paramInt);
      return;
    }
    if (paramInt <= 65535)
    {
      addIcode(-39);
      addUint16(paramInt);
      return;
    }
    addIcode(-40);
    addInt(paramInt);
  }
  
  private void addInt(int paramInt)
  {
    byte[] arrayOfByte2 = this.itsData.itsICode;
    int i = this.iCodeTop;
    byte[] arrayOfByte1 = arrayOfByte2;
    if (i + 4 > arrayOfByte2.length) {
      arrayOfByte1 = increaseICodeCapacity(4);
    }
    arrayOfByte1[i] = ((byte)(paramInt >>> 24));
    arrayOfByte1[(i + 1)] = ((byte)(paramInt >>> 16));
    arrayOfByte1[(i + 2)] = ((byte)(paramInt >>> 8));
    arrayOfByte1[(i + 3)] = ((byte)paramInt);
    this.iCodeTop = (i + 4);
  }
  
  private void addStringOp(int paramInt, String paramString)
  {
    addStringPrefix(paramString);
    if (Icode.validIcode(paramInt))
    {
      addIcode(paramInt);
      return;
    }
    addToken(paramInt);
  }
  
  private void addStringPrefix(String paramString)
  {
    int j = this.strings.get(paramString, -1);
    int i = j;
    if (j == -1)
    {
      i = this.strings.size();
      this.strings.put(paramString, i);
    }
    if (i < 4)
    {
      addIcode(-41 - i);
      return;
    }
    if (i <= 255)
    {
      addIcode(-45);
      addUint8(i);
      return;
    }
    if (i <= 65535)
    {
      addIcode(-46);
      addUint16(i);
      return;
    }
    addIcode(-47);
    addInt(i);
  }
  
  private void addToken(int paramInt)
  {
    if (!Icode.validTokenCode(paramInt)) {
      throw Kit.codeBug();
    }
    addUint8(paramInt);
  }
  
  private void addUint16(int paramInt)
  {
    if ((0xFFFF0000 & paramInt) != 0) {
      throw Kit.codeBug();
    }
    byte[] arrayOfByte2 = this.itsData.itsICode;
    int i = this.iCodeTop;
    byte[] arrayOfByte1 = arrayOfByte2;
    if (i + 2 > arrayOfByte2.length) {
      arrayOfByte1 = increaseICodeCapacity(2);
    }
    arrayOfByte1[i] = ((byte)(paramInt >>> 8));
    arrayOfByte1[(i + 1)] = ((byte)paramInt);
    this.iCodeTop = (i + 2);
  }
  
  private void addUint8(int paramInt)
  {
    if ((paramInt & 0xFF00) != 0) {
      throw Kit.codeBug();
    }
    byte[] arrayOfByte2 = this.itsData.itsICode;
    int i = this.iCodeTop;
    byte[] arrayOfByte1 = arrayOfByte2;
    if (i == arrayOfByte2.length) {
      arrayOfByte1 = increaseICodeCapacity(1);
    }
    arrayOfByte1[i] = ((byte)paramInt);
    this.iCodeTop = (i + 1);
  }
  
  private void addVarOp(int paramInt1, int paramInt2)
  {
    switch (paramInt1)
    {
    default: 
      throw Kit.codeBug();
    case 157: 
      if (paramInt2 < 128)
      {
        addIcode(-61);
        addUint8(paramInt2);
        return;
      }
      addIndexOp(-60, paramInt2);
      return;
    case 55: 
    case 56: 
      if (paramInt2 < 128)
      {
        if (paramInt1 == 55) {}
        for (paramInt1 = -48;; paramInt1 = -49)
        {
          addIcode(paramInt1);
          addUint8(paramInt2);
          return;
        }
      }
      break;
    }
    addIndexOp(paramInt1, paramInt2);
  }
  
  private int allocLocal()
  {
    int i = this.localTop;
    this.localTop += 1;
    if (this.localTop > this.itsData.itsMaxLocals) {
      this.itsData.itsMaxLocals = this.localTop;
    }
    return i;
  }
  
  private RuntimeException badTree(Node paramNode)
  {
    throw new RuntimeException(paramNode.toString());
  }
  
  private void fixLabelGotos()
  {
    int i = 0;
    while (i < this.fixupTableTop)
    {
      long l = this.fixupTable[i];
      int k = (int)(l >> 32);
      int j = (int)l;
      k = this.labelTable[k];
      if (k == -1) {
        throw Kit.codeBug();
      }
      resolveGoto(j, k);
      i += 1;
    }
    this.fixupTableTop = 0;
  }
  
  private void generateCallFunAndThis(Node paramNode)
  {
    int i = paramNode.getType();
    switch (i)
    {
    default: 
      visitExpression(paramNode, 0);
      addIcode(-18);
      stackChange(1);
      return;
    case 39: 
      addStringOp(-15, paramNode.getString());
      stackChange(2);
      return;
    }
    paramNode = paramNode.getFirstChild();
    visitExpression(paramNode, 0);
    paramNode = paramNode.getNext();
    if (i == 33)
    {
      addStringOp(-16, paramNode.getString());
      stackChange(1);
      return;
    }
    visitExpression(paramNode, 0);
    addIcode(-17);
  }
  
  private void generateFunctionICode()
  {
    this.itsInFunctionFlag = true;
    FunctionNode localFunctionNode = (FunctionNode)this.scriptOrFn;
    this.itsData.itsFunctionType = localFunctionNode.getFunctionType();
    this.itsData.itsNeedsActivation = localFunctionNode.requiresActivation();
    if (localFunctionNode.getFunctionName() != null) {
      this.itsData.itsName = localFunctionNode.getName();
    }
    if (localFunctionNode.isGenerator())
    {
      addIcode(-62);
      addUint16(localFunctionNode.getBaseLineno() & 0xFFFF);
    }
    if (localFunctionNode.isInStrictMode()) {
      this.itsData.isStrict = true;
    }
    generateICodeFromTree(localFunctionNode.getLastChild());
  }
  
  private void generateICodeFromTree(Node paramNode)
  {
    generateNestedFunctions();
    generateRegExpLiterals();
    visitStatement(paramNode, 0);
    fixLabelGotos();
    if (this.itsData.itsFunctionType == 0) {
      addToken(65);
    }
    if (this.itsData.itsICode.length != this.iCodeTop)
    {
      paramNode = new byte[this.iCodeTop];
      System.arraycopy(this.itsData.itsICode, 0, paramNode, 0, this.iCodeTop);
      this.itsData.itsICode = paramNode;
    }
    if (this.strings.size() == 0)
    {
      this.itsData.itsStringTable = null;
      if (this.doubleTableTop != 0) {
        break label389;
      }
      this.itsData.itsDoubleTable = null;
    }
    for (;;)
    {
      if ((this.exceptionTableTop != 0) && (this.itsData.itsExceptionTable.length != this.exceptionTableTop))
      {
        paramNode = new int[this.exceptionTableTop];
        System.arraycopy(this.itsData.itsExceptionTable, 0, paramNode, 0, this.exceptionTableTop);
        this.itsData.itsExceptionTable = paramNode;
      }
      this.itsData.itsMaxVars = this.scriptOrFn.getParamAndVarCount();
      this.itsData.itsMaxFrameArray = (this.itsData.itsMaxVars + this.itsData.itsMaxLocals + this.itsData.itsMaxStack);
      this.itsData.argNames = this.scriptOrFn.getParamAndVarNames();
      this.itsData.argIsConst = this.scriptOrFn.getParamAndVarConst();
      this.itsData.argCount = this.scriptOrFn.getParamCount();
      this.itsData.encodedSourceStart = this.scriptOrFn.getEncodedSourceStart();
      this.itsData.encodedSourceEnd = this.scriptOrFn.getEncodedSourceEnd();
      if (this.literalIds.size() != 0) {
        this.itsData.literalIds = this.literalIds.toArray();
      }
      return;
      this.itsData.itsStringTable = new String[this.strings.size()];
      paramNode = this.strings.newIterator();
      paramNode.start();
      while (!paramNode.done())
      {
        String str = (String)paramNode.getKey();
        int i = paramNode.getValue();
        if (this.itsData.itsStringTable[i] != null) {
          Kit.codeBug();
        }
        this.itsData.itsStringTable[i] = str;
        paramNode.next();
      }
      break;
      label389:
      if (this.itsData.itsDoubleTable.length != this.doubleTableTop)
      {
        paramNode = new double[this.doubleTableTop];
        System.arraycopy(this.itsData.itsDoubleTable, 0, paramNode, 0, this.doubleTableTop);
        this.itsData.itsDoubleTable = paramNode;
      }
    }
  }
  
  private void generateNestedFunctions()
  {
    int j = this.scriptOrFn.getFunctionCount();
    if (j == 0) {
      return;
    }
    InterpreterData[] arrayOfInterpreterData = new InterpreterData[j];
    int i = 0;
    while (i != j)
    {
      FunctionNode localFunctionNode = this.scriptOrFn.getFunctionNode(i);
      CodeGenerator localCodeGenerator = new CodeGenerator();
      localCodeGenerator.compilerEnv = this.compilerEnv;
      localCodeGenerator.scriptOrFn = localFunctionNode;
      localCodeGenerator.itsData = new InterpreterData(this.itsData);
      localCodeGenerator.generateFunctionICode();
      arrayOfInterpreterData[i] = localCodeGenerator.itsData;
      i += 1;
    }
    this.itsData.itsNestedFunctions = arrayOfInterpreterData;
  }
  
  private void generateRegExpLiterals()
  {
    int j = this.scriptOrFn.getRegexpCount();
    if (j == 0) {
      return;
    }
    Context localContext = Context.getContext();
    RegExpProxy localRegExpProxy = ScriptRuntime.checkRegExpProxy(localContext);
    Object[] arrayOfObject = new Object[j];
    int i = 0;
    while (i != j)
    {
      arrayOfObject[i] = localRegExpProxy.compileRegExp(localContext, this.scriptOrFn.getRegexpString(i), this.scriptOrFn.getRegexpFlags(i));
      i += 1;
    }
    this.itsData.itsRegExpLiterals = arrayOfObject;
  }
  
  private int getDoubleIndex(double paramDouble)
  {
    int i = this.doubleTableTop;
    if (i == 0) {
      this.itsData.itsDoubleTable = new double[64];
    }
    for (;;)
    {
      this.itsData.itsDoubleTable[i] = paramDouble;
      this.doubleTableTop = (i + 1);
      return i;
      if (this.itsData.itsDoubleTable.length == i)
      {
        double[] arrayOfDouble = new double[i * 2];
        System.arraycopy(this.itsData.itsDoubleTable, 0, arrayOfDouble, 0, i);
        this.itsData.itsDoubleTable = arrayOfDouble;
      }
    }
  }
  
  private int getLocalBlockRef(Node paramNode)
  {
    return ((Node)paramNode.getProp(3)).getExistingIntProp(2);
  }
  
  private int getTargetLabel(Node paramNode)
  {
    int i = paramNode.labelId();
    if (i != -1) {
      return i;
    }
    i = this.labelTableTop;
    if ((this.labelTable == null) || (i == this.labelTable.length)) {
      if (this.labelTable != null) {
        break label69;
      }
    }
    label69:
    int[] arrayOfInt;
    for (this.labelTable = new int[32];; this.labelTable = arrayOfInt)
    {
      this.labelTableTop = (i + 1);
      this.labelTable[i] = -1;
      paramNode.labelId(i);
      return i;
      arrayOfInt = new int[this.labelTable.length * 2];
      System.arraycopy(this.labelTable, 0, arrayOfInt, 0, i);
    }
  }
  
  private byte[] increaseICodeCapacity(int paramInt)
  {
    int i = this.itsData.itsICode.length;
    int k = this.iCodeTop;
    if (k + paramInt <= i) {
      throw Kit.codeBug();
    }
    int j = i * 2;
    i = j;
    if (k + paramInt > j) {
      i = k + paramInt;
    }
    byte[] arrayOfByte = new byte[i];
    System.arraycopy(this.itsData.itsICode, 0, arrayOfByte, 0, k);
    this.itsData.itsICode = arrayOfByte;
    return arrayOfByte;
  }
  
  private void markTargetLabel(Node paramNode)
  {
    int i = getTargetLabel(paramNode);
    if (this.labelTable[i] != -1) {
      Kit.codeBug();
    }
    this.labelTable[i] = this.iCodeTop;
  }
  
  private void releaseLocal(int paramInt)
  {
    this.localTop -= 1;
    if (paramInt != this.localTop) {
      Kit.codeBug();
    }
  }
  
  private void resolveForwardGoto(int paramInt)
  {
    if (this.iCodeTop < paramInt + 3) {
      throw Kit.codeBug();
    }
    resolveGoto(paramInt, this.iCodeTop);
  }
  
  private void resolveGoto(int paramInt1, int paramInt2)
  {
    int i = paramInt2 - paramInt1;
    if ((i >= 0) && (i <= 2)) {
      throw Kit.codeBug();
    }
    int j = paramInt1 + 1;
    paramInt1 = i;
    if (i != (short)i)
    {
      if (this.itsData.longJumps == null) {
        this.itsData.longJumps = new UintMap();
      }
      this.itsData.longJumps.put(j, paramInt2);
      paramInt1 = 0;
    }
    byte[] arrayOfByte = this.itsData.itsICode;
    arrayOfByte[j] = ((byte)(paramInt1 >> 8));
    arrayOfByte[(j + 1)] = ((byte)paramInt1);
  }
  
  private void stackChange(int paramInt)
  {
    if (paramInt <= 0)
    {
      this.stackDepth += paramInt;
      return;
    }
    paramInt = this.stackDepth + paramInt;
    if (paramInt > this.itsData.itsMaxStack) {
      this.itsData.itsMaxStack = paramInt;
    }
    this.stackDepth = paramInt;
  }
  
  private void updateLineNumber(Node paramNode)
  {
    int i = paramNode.getLineno();
    if ((i != this.lineNumber) && (i >= 0))
    {
      if (this.itsData.firstLinePC < 0) {
        this.itsData.firstLinePC = i;
      }
      this.lineNumber = i;
      addIcode(-26);
      addUint16(i & 0xFFFF);
    }
  }
  
  private void visitArrayComprehension(Node paramNode1, Node paramNode2, Node paramNode3)
  {
    visitStatement(paramNode2, this.stackDepth);
    visitExpression(paramNode3, 0);
  }
  
  private void visitExpression(Node paramNode, int paramInt)
  {
    int j = 1;
    int k = 0;
    int i = paramNode.getType();
    Node localNode1 = paramNode.getFirstChild();
    int m = this.stackDepth;
    switch (i)
    {
    case 50: 
    case 51: 
    case 57: 
    case 58: 
    case 59: 
    case 60: 
    case 61: 
    case 65: 
    case 82: 
    case 83: 
    case 84: 
    case 85: 
    case 86: 
    case 87: 
    case 88: 
    case 89: 
    case 91: 
    case 92: 
    case 93: 
    case 94: 
    case 95: 
    case 96: 
    case 97: 
    case 98: 
    case 99: 
    case 100: 
    case 101: 
    case 102: 
    case 104: 
    case 109: 
    case 111: 
    case 112: 
    case 113: 
    case 114: 
    case 115: 
    case 116: 
    case 117: 
    case 118: 
    case 119: 
    case 120: 
    case 121: 
    case 122: 
    case 123: 
    case 124: 
    case 125: 
    case 126: 
    case 128: 
    case 129: 
    case 130: 
    case 131: 
    case 132: 
    case 133: 
    case 134: 
    case 135: 
    case 136: 
    case 137: 
    case 142: 
    case 144: 
    case 145: 
    case 146: 
    case 148: 
    case 149: 
    case 150: 
    case 151: 
    case 152: 
    case 153: 
    case 154: 
    case 155: 
    case 159: 
    default: 
      throw badTree(paramNode);
    case 110: 
      paramInt = paramNode.getExistingIntProp(1);
      paramNode = this.scriptOrFn.getFunctionNode(paramInt);
      if ((paramNode.getFunctionType() != 2) && (paramNode.getFunctionType() != 4)) {
        throw Kit.codeBug();
      }
      addIndexOp(-19, paramInt);
      stackChange(1);
    case 54: 
    case 90: 
    case 139: 
    case 30: 
    case 38: 
    case 71: 
      for (;;)
      {
        if (m + 1 != this.stackDepth) {
          Kit.codeBug();
        }
        return;
        addIndexOp(54, getLocalBlockRef(paramNode));
        stackChange(1);
        continue;
        paramNode = paramNode.getLastChild();
        while (localNode1 != paramNode)
        {
          visitExpression(localNode1, 0);
          addIcode(-4);
          stackChange(-1);
          localNode1 = localNode1.getNext();
        }
        visitExpression(localNode1, paramInt & 0x1);
        continue;
        stackChange(1);
        continue;
        if (i == 30) {
          visitExpression(localNode1, 0);
        }
        for (;;)
        {
          j = 0;
          for (;;)
          {
            localNode1 = localNode1.getNext();
            if (localNode1 == null) {
              break;
            }
            visitExpression(localNode1, 0);
            j += 1;
          }
          generateCallFunAndThis(localNode1);
        }
        int n = paramNode.getIntProp(10, 0);
        if ((i == 71) || (n == 0)) {
          break;
        }
        addIndexOp(-21, j);
        addUint8(n);
        paramInt = k;
        if (i == 30) {
          paramInt = 1;
        }
        addUint8(paramInt);
        addUint16(this.lineNumber & 0xFFFF);
        label916:
        if (i != 30) {
          break label1000;
        }
        stackChange(-j);
        label930:
        if (j > this.itsData.itsMaxCalleeArgs) {
          this.itsData.itsMaxCalleeArgs = j;
        }
      }
      if ((i != 38) || ((paramInt & 0x1) == 0) || (this.compilerEnv.isGenerateDebugInfo()) || (this.itsInTryFlag)) {
        break;
      }
    }
    for (paramInt = -55;; paramInt = i)
    {
      addIndexOp(paramInt, j);
      i = paramInt;
      break label916;
      label1000:
      stackChange(-1 - j);
      break label930;
      visitExpression(localNode1, 0);
      addIcode(-1);
      stackChange(1);
      j = this.iCodeTop;
      if (i == 106) {}
      for (i = 7;; i = 6)
      {
        addGotoOp(i);
        stackChange(-1);
        addIcode(-4);
        stackChange(-1);
        visitExpression(localNode1.getNext(), paramInt & 0x1);
        resolveForwardGoto(j);
        break;
      }
      paramNode = localNode1.getNext();
      Node localNode2 = paramNode.getNext();
      visitExpression(localNode1, 0);
      i = this.iCodeTop;
      addGotoOp(7);
      stackChange(-1);
      visitExpression(paramNode, paramInt & 0x1);
      j = this.iCodeTop;
      addGotoOp(5);
      resolveForwardGoto(i);
      this.stackDepth = m;
      visitExpression(localNode2, paramInt & 0x1);
      resolveForwardGoto(j);
      break;
      visitExpression(localNode1, 0);
      addStringOp(i, localNode1.getNext().getString());
      break;
      if (localNode1.getType() == 49)
      {
        paramInt = j;
        label1217:
        visitExpression(localNode1, 0);
        visitExpression(localNode1.getNext(), 0);
        if (paramInt == 0) {
          break label1256;
        }
        addIcode(0);
      }
      for (;;)
      {
        stackChange(-1);
        break;
        paramInt = 0;
        break label1217;
        label1256:
        addToken(31);
      }
      visitExpression(localNode1, 0);
      visitExpression(localNode1.getNext(), 0);
      addToken(i);
      stackChange(-1);
      break;
      visitExpression(localNode1, 0);
      if (i == 127)
      {
        addIcode(-4);
        addIcode(-50);
        break;
      }
      addToken(i);
      break;
      visitExpression(localNode1, 0);
      addToken(i);
      break;
      visitExpression(localNode1, 0);
      localNode1 = localNode1.getNext();
      paramNode = localNode1.getString();
      localNode1 = localNode1.getNext();
      if (i == 140)
      {
        addIcode(-1);
        stackChange(1);
        addStringOp(33, paramNode);
        stackChange(-1);
      }
      visitExpression(localNode1, 0);
      addStringOp(35, paramNode);
      stackChange(-1);
      break;
      visitExpression(localNode1, 0);
      paramNode = localNode1.getNext();
      visitExpression(paramNode, 0);
      paramNode = paramNode.getNext();
      if (i == 141)
      {
        addIcode(-2);
        stackChange(2);
        addToken(36);
        stackChange(-1);
        stackChange(-1);
      }
      visitExpression(paramNode, 0);
      addToken(37);
      stackChange(-2);
      break;
      visitExpression(localNode1, 0);
      paramNode = localNode1.getNext();
      if (i == 143)
      {
        addIcode(-1);
        stackChange(1);
        addToken(68);
        stackChange(-1);
      }
      visitExpression(paramNode, 0);
      addToken(69);
      stackChange(-1);
      break;
      paramNode = localNode1.getString();
      visitExpression(localNode1, 0);
      visitExpression(localNode1.getNext(), 0);
      addStringOp(i, paramNode);
      stackChange(-1);
      break;
      paramNode = localNode1.getString();
      visitExpression(localNode1, 0);
      visitExpression(localNode1.getNext(), 0);
      addStringOp(-59, paramNode);
      stackChange(-1);
      break;
      if ((this.itsInFunctionFlag) && (!this.itsData.itsNeedsActivation)) {}
      for (paramInt = this.scriptOrFn.getIndexForNameNode(paramNode);; paramInt = -1)
      {
        if (paramInt == -1)
        {
          addStringOp(-14, paramNode.getString());
          stackChange(1);
          break;
        }
        addVarOp(55, paramInt);
        stackChange(1);
        addToken(32);
        break;
        addStringOp(i, paramNode.getString());
        stackChange(1);
        break;
        visitIncDec(paramNode, localNode1);
        break;
        double d = paramNode.getDouble();
        paramInt = (int)d;
        if (paramInt == d) {
          if (paramInt == 0)
          {
            addIcode(-51);
            if (1.0D / d < 0.0D) {
              addToken(29);
            }
          }
        }
        for (;;)
        {
          stackChange(1);
          break;
          if (paramInt == 1)
          {
            addIcode(-52);
          }
          else if ((short)paramInt == paramInt)
          {
            addIcode(-27);
            addUint16(0xFFFF & paramInt);
          }
          else
          {
            addIcode(-28);
            addInt(paramInt);
            continue;
            addIndexOp(40, getDoubleIndex(d));
          }
        }
        if (this.itsData.itsNeedsActivation) {
          Kit.codeBug();
        }
        addVarOp(55, this.scriptOrFn.getIndexForNameNode(paramNode));
        stackChange(1);
        break;
        if (this.itsData.itsNeedsActivation) {
          Kit.codeBug();
        }
        paramInt = this.scriptOrFn.getIndexForNameNode(localNode1);
        visitExpression(localNode1.getNext(), 0);
        addVarOp(56, paramInt);
        break;
        if (this.itsData.itsNeedsActivation) {
          Kit.codeBug();
        }
        paramInt = this.scriptOrFn.getIndexForNameNode(localNode1);
        visitExpression(localNode1.getNext(), 0);
        addVarOp(157, paramInt);
        break;
        addToken(i);
        stackChange(1);
        break;
        addIndexOp(i, getLocalBlockRef(paramNode));
        stackChange(1);
        break;
        addIndexOp(48, paramNode.getExistingIntProp(4));
        stackChange(1);
        break;
        visitLiteral(paramNode, localNode1);
        break;
        visitArrayComprehension(paramNode, localNode1, localNode1.getNext());
        break;
        visitExpression(localNode1, 0);
        addStringOp(i, (String)paramNode.getProp(17));
        break;
        k = paramNode.getIntProp(16, 0);
        paramNode = localNode1;
        paramInt = 0;
        do
        {
          visitExpression(paramNode, 0);
          j = paramInt + 1;
          localNode1 = paramNode.getNext();
          paramInt = j;
          paramNode = localNode1;
        } while (localNode1 != null);
        addIndexOp(i, k);
        stackChange(1 - j);
        break;
        updateLineNumber(paramNode);
        visitExpression(localNode1, 0);
        addIcode(-53);
        stackChange(-1);
        paramInt = this.iCodeTop;
        visitExpression(localNode1.getNext(), 0);
        addBackwardGoto(-54, paramInt);
        break;
        visitExpression(localNode1, 0);
        addToken(i);
        break;
        if (localNode1 != null) {
          visitExpression(localNode1, 0);
        }
        for (;;)
        {
          addToken(73);
          addUint16(paramNode.getLineno() & 0xFFFF);
          break;
          addIcode(-50);
          stackChange(1);
        }
        paramNode = paramNode.getFirstChild();
        localNode1 = paramNode.getNext();
        visitExpression(paramNode.getFirstChild(), 0);
        addToken(2);
        stackChange(-1);
        visitExpression(localNode1.getFirstChild(), 0);
        addToken(3);
        break;
      }
    }
  }
  
  private void visitIncDec(Node paramNode1, Node paramNode2)
  {
    int i = paramNode1.getExistingIntProp(13);
    switch (paramNode2.getType())
    {
    default: 
      throw badTree(paramNode1);
    case 55: 
      if (this.itsData.itsNeedsActivation) {
        Kit.codeBug();
      }
      addVarOp(-7, this.scriptOrFn.getIndexForNameNode(paramNode2));
      addUint8(i);
      stackChange(1);
      return;
    case 39: 
      addStringOp(-8, paramNode2.getString());
      addUint8(i);
      stackChange(1);
      return;
    case 33: 
      paramNode1 = paramNode2.getFirstChild();
      visitExpression(paramNode1, 0);
      addStringOp(-9, paramNode1.getNext().getString());
      addUint8(i);
      return;
    case 36: 
      paramNode1 = paramNode2.getFirstChild();
      visitExpression(paramNode1, 0);
      visitExpression(paramNode1.getNext(), 0);
      addIcode(-10);
      addUint8(i);
      stackChange(-1);
      return;
    }
    visitExpression(paramNode2.getFirstChild(), 0);
    addIcode(-11);
    addUint8(i);
  }
  
  private void visitLiteral(Node paramNode1, Node paramNode2)
  {
    int k = paramNode1.getType();
    Object localObject2 = null;
    int i;
    Object localObject1;
    int j;
    if (k == 66)
    {
      Node localNode = paramNode2;
      i = 0;
      for (;;)
      {
        localObject1 = localObject2;
        j = i;
        if (localNode == null) {
          break;
        }
        localNode = localNode.getNext();
        i += 1;
      }
    }
    if (k == 67)
    {
      localObject1 = (Object[])paramNode1.getProp(12);
      j = localObject1.length;
      addIndexOp(-29, j);
      stackChange(2);
      if (paramNode2 == null) {
        break label201;
      }
      i = paramNode2.getType();
      if (i != 152) {
        break label136;
      }
      visitExpression(paramNode2.getFirstChild(), 0);
      addIcode(-57);
    }
    for (;;)
    {
      stackChange(-1);
      paramNode2 = paramNode2.getNext();
      break;
      throw badTree(paramNode1);
      label136:
      if (i == 153)
      {
        visitExpression(paramNode2.getFirstChild(), 0);
        addIcode(-58);
      }
      else if (i == 164)
      {
        visitExpression(paramNode2.getFirstChild(), 0);
        addIcode(-30);
      }
      else
      {
        visitExpression(paramNode2, 0);
        addIcode(-30);
      }
    }
    label201:
    if (k == 66)
    {
      paramNode1 = (int[])paramNode1.getProp(11);
      if (paramNode1 == null) {
        addToken(66);
      }
    }
    for (;;)
    {
      stackChange(-1);
      return;
      i = this.literalIds.size();
      this.literalIds.add(paramNode1);
      addIndexOp(-31, i);
      continue;
      i = this.literalIds.size();
      this.literalIds.add(localObject1);
      addIndexOp(67, i);
    }
  }
  
  private void visitStatement(Node paramNode, int paramInt)
  {
    int j = -5;
    int i = 0;
    int k = paramNode.getType();
    Node localNode1 = paramNode.getFirstChild();
    Node localNode2 = localNode1;
    switch (k)
    {
    default: 
      throw badTree(paramNode);
    case 110: 
      i = paramNode.getExistingIntProp(1);
      j = this.scriptOrFn.getFunctionNode(i).getFunctionType();
      if (j == 3)
      {
        addIndexOp(-20, i);
        if (!this.itsInFunctionFlag)
        {
          addIndexOp(-19, i);
          stackChange(1);
          addIcode(-5);
          stackChange(-1);
        }
      }
      break;
    }
    for (;;)
    {
      if (this.stackDepth == paramInt) {
        return;
      }
      throw Kit.codeBug();
      if (j == 1) {
        break;
      }
      throw Kit.codeBug();
      updateLineNumber(paramNode);
      for (localNode2 = localNode1; localNode2 != null; localNode2 = localNode2.getNext()) {
        visitStatement(localNode2, paramInt);
      }
      visitExpression(localNode1, 0);
      addToken(2);
      stackChange(-1);
      continue;
      addToken(3);
      continue;
      i = allocLocal();
      paramNode.putIntProp(2, i);
      updateLineNumber(paramNode);
      while (localNode1 != null)
      {
        visitStatement(localNode1, paramInt);
        localNode1 = localNode1.getNext();
      }
      addIndexOp(-56, i);
      releaseLocal(i);
      continue;
      addIcode(-64);
      continue;
      updateLineNumber(paramNode);
      visitExpression(localNode1, 0);
      for (paramNode = (Jump)localNode1.getNext(); paramNode != null; paramNode = (Jump)paramNode.getNext())
      {
        if (paramNode.getType() != 116) {
          throw badTree(paramNode);
        }
        localNode1 = paramNode.getFirstChild();
        addIcode(-1);
        stackChange(1);
        visitExpression(localNode1, 0);
        addToken(46);
        stackChange(-1);
        addGoto(paramNode.target, -6);
        stackChange(-1);
      }
      addIcode(-4);
      stackChange(-1);
      continue;
      markTargetLabel(paramNode);
      continue;
      paramNode = ((Jump)paramNode).target;
      visitExpression(localNode1, 0);
      addGoto(paramNode, k);
      stackChange(-1);
      continue;
      addGoto(((Jump)paramNode).target, k);
      continue;
      addGoto(((Jump)paramNode).target, -23);
      continue;
      stackChange(1);
      i = getLocalBlockRef(paramNode);
      addIndexOp(-24, i);
      stackChange(-1);
      while (localNode1 != null)
      {
        visitStatement(localNode1, paramInt);
        localNode1 = localNode1.getNext();
      }
      addIndexOp(-25, i);
      continue;
      updateLineNumber(paramNode);
      visitExpression(localNode1, 0);
      i = j;
      if (k == 134) {
        i = -4;
      }
      addIcode(i);
      stackChange(-1);
      continue;
      paramNode = (Jump)paramNode;
      i = getLocalBlockRef(paramNode);
      j = allocLocal();
      addIndexOp(-13, j);
      k = this.iCodeTop;
      boolean bool = this.itsInTryFlag;
      this.itsInTryFlag = true;
      while (localNode1 != null)
      {
        visitStatement(localNode1, paramInt);
        localNode1 = localNode1.getNext();
      }
      this.itsInTryFlag = bool;
      localNode1 = paramNode.target;
      int m;
      if (localNode1 != null)
      {
        m = this.labelTable[getTargetLabel(localNode1)];
        addExceptionHandler(k, m, m, false, i, j);
      }
      paramNode = paramNode.getFinally();
      if (paramNode != null)
      {
        m = this.labelTable[getTargetLabel(paramNode)];
        addExceptionHandler(k, m, m, true, i, j);
      }
      addIndexOp(-56, j);
      releaseLocal(j);
      continue;
      k = getLocalBlockRef(paramNode);
      j = paramNode.getExistingIntProp(14);
      paramNode = localNode1.getString();
      visitExpression(localNode1.getNext(), 0);
      addStringPrefix(paramNode);
      addIndexPrefix(k);
      addToken(57);
      if (j != 0) {
        i = 1;
      }
      addUint8(i);
      stackChange(-1);
      continue;
      updateLineNumber(paramNode);
      visitExpression(localNode1, 0);
      addToken(50);
      addUint16(this.lineNumber & 0xFFFF);
      stackChange(-1);
      continue;
      updateLineNumber(paramNode);
      addIndexOp(51, getLocalBlockRef(paramNode));
      continue;
      updateLineNumber(paramNode);
      if (paramNode.getIntProp(20, 0) != 0)
      {
        addIcode(-63);
        addUint16(this.lineNumber & 0xFFFF);
      }
      else if (localNode1 != null)
      {
        visitExpression(localNode1, 1);
        addToken(4);
        stackChange(-1);
      }
      else
      {
        addIcode(-22);
        continue;
        updateLineNumber(paramNode);
        addToken(65);
        continue;
        visitExpression(localNode1, 0);
        addIndexOp(k, getLocalBlockRef(paramNode));
        stackChange(-1);
      }
    }
  }
  
  public InterpreterData compile(CompilerEnvirons paramCompilerEnvirons, ScriptNode paramScriptNode, String paramString, boolean paramBoolean)
  {
    this.compilerEnv = paramCompilerEnvirons;
    new NodeTransformer().transform(paramScriptNode);
    if (paramBoolean)
    {
      this.scriptOrFn = paramScriptNode.getFunctionNode(0);
      this.itsData = new InterpreterData(paramCompilerEnvirons.getLanguageVersion(), this.scriptOrFn.getSourceName(), paramString, this.scriptOrFn.isInStrictMode());
      this.itsData.topLevel = true;
      if (!paramBoolean) {
        break label90;
      }
      generateFunctionICode();
    }
    for (;;)
    {
      return this.itsData;
      this.scriptOrFn = paramScriptNode;
      break;
      label90:
      generateICodeFromTree(this.scriptOrFn);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\CodeGenerator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */