package org.mozilla.javascript;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.mozilla.javascript.ast.ScriptNode;
import org.mozilla.javascript.debug.DebugFrame;
import org.mozilla.javascript.debug.Debugger;

public final class Interpreter
  extends Icode
  implements Evaluator
{
  static final int EXCEPTION_HANDLER_SLOT = 2;
  static final int EXCEPTION_LOCAL_SLOT = 4;
  static final int EXCEPTION_SCOPE_SLOT = 5;
  static final int EXCEPTION_SLOT_SIZE = 6;
  static final int EXCEPTION_TRY_END_SLOT = 1;
  static final int EXCEPTION_TRY_START_SLOT = 0;
  static final int EXCEPTION_TYPE_SLOT = 3;
  InterpreterData itsData;
  
  private static void addInstructionCount(Context paramContext, CallFrame paramCallFrame, int paramInt)
  {
    paramContext.instructionCount += paramCallFrame.pc - paramCallFrame.pcPrevBranch + paramInt;
    if (paramContext.instructionCount > paramContext.instructionThreshold)
    {
      paramContext.observeInstructionCount(paramContext.instructionCount);
      paramContext.instructionCount = 0;
    }
  }
  
  private static int bytecodeSpan(int paramInt)
  {
    int i = 3;
    switch (paramInt)
    {
    default: 
      if (!validBytecode(paramInt)) {
        throw Kit.codeBug();
      }
      break;
    case -21: 
      i = 5;
    case -63: 
    case -62: 
    case -54: 
    case -46: 
    case -39: 
    case -27: 
    case -26: 
    case -23: 
    case -6: 
    case 5: 
    case 6: 
    case 7: 
    case 50: 
    case 73: 
      return i;
    case 57: 
      return 2;
    case -11: 
    case -10: 
    case -9: 
    case -8: 
    case -7: 
      return 2;
    case -28: 
      return 5;
    case -38: 
      return 2;
    case -40: 
      return 5;
    case -45: 
      return 2;
    case -47: 
      return 5;
    case -61: 
    case -49: 
    case -48: 
      return 2;
    }
    return 1;
  }
  
  public static NativeContinuation captureContinuation(Context paramContext)
  {
    if ((paramContext.lastInterpreterFrame == null) || (!(paramContext.lastInterpreterFrame instanceof CallFrame))) {
      throw new IllegalStateException("Interpreter frames not found");
    }
    return captureContinuation(paramContext, (CallFrame)paramContext.lastInterpreterFrame, true);
  }
  
  private static NativeContinuation captureContinuation(Context paramContext, CallFrame paramCallFrame, boolean paramBoolean)
  {
    NativeContinuation localNativeContinuation = new NativeContinuation();
    ScriptRuntime.setObjectProtoAndParent(localNativeContinuation, ScriptRuntime.getTopCallScope(paramContext));
    paramContext = paramCallFrame;
    Object localObject = paramCallFrame;
    if ((localObject != null) && (!((CallFrame)localObject).frozen))
    {
      ((CallFrame)localObject).frozen = true;
      int i = ((CallFrame)localObject).savedStackTop + 1;
      while (i != ((CallFrame)localObject).stack.length)
      {
        ((CallFrame)localObject).stack[i] = null;
        ((CallFrame)localObject).stackAttributes[i] = 0;
        i += 1;
      }
      if (((CallFrame)localObject).savedCallOp == 38) {
        ((CallFrame)localObject).stack[localObject.savedStackTop] = null;
      }
      for (;;)
      {
        CallFrame localCallFrame = ((CallFrame)localObject).parentFrame;
        paramContext = (Context)localObject;
        localObject = localCallFrame;
        break;
        if (((CallFrame)localObject).savedCallOp != 30) {
          Kit.codeBug();
        }
      }
    }
    if (paramBoolean)
    {
      while (paramContext.parentFrame != null) {
        paramContext = paramContext.parentFrame;
      }
      if (!paramContext.isContinuationsTopFrame) {
        throw new IllegalStateException("Cannot capture continuation from JavaScript code not called directly by executeScriptWithContinuations or callFunctionWithContinuations");
      }
    }
    localNativeContinuation.initImplementation(paramCallFrame);
    return localNativeContinuation;
  }
  
  private static CallFrame captureFrameForGenerator(CallFrame paramCallFrame)
  {
    paramCallFrame.frozen = true;
    CallFrame localCallFrame = paramCallFrame.cloneFrozen();
    paramCallFrame.frozen = false;
    localCallFrame.parentFrame = null;
    localCallFrame.frameIndex = 0;
    return localCallFrame;
  }
  
  private static void doAdd(Object[] paramArrayOfObject, double[] paramArrayOfDouble, int paramInt, Context paramContext)
  {
    Object localObject1 = paramArrayOfObject[(paramInt + 1)];
    Object localObject2 = paramArrayOfObject[paramInt];
    double d1;
    int i;
    if (localObject1 == UniqueTag.DOUBLE_MARK)
    {
      d1 = paramArrayOfDouble[(paramInt + 1)];
      if (localObject2 == UniqueTag.DOUBLE_MARK)
      {
        paramArrayOfDouble[paramInt] += d1;
        return;
      }
      i = 1;
      localObject1 = localObject2;
    }
    while ((localObject1 instanceof Scriptable))
    {
      paramArrayOfDouble = ScriptRuntime.wrapNumber(d1);
      Object localObject3 = localObject1;
      localObject2 = paramArrayOfDouble;
      if (i == 0)
      {
        localObject2 = localObject1;
        localObject3 = paramArrayOfDouble;
      }
      paramArrayOfObject[paramInt] = ScriptRuntime.add(localObject3, localObject2, paramContext);
      return;
      if (localObject2 == UniqueTag.DOUBLE_MARK)
      {
        d1 = paramArrayOfDouble[paramInt];
        i = 0;
      }
      else
      {
        if (((localObject2 instanceof Scriptable)) || ((localObject1 instanceof Scriptable)))
        {
          paramArrayOfObject[paramInt] = ScriptRuntime.add(localObject2, localObject1, paramContext);
          return;
        }
        if (((localObject2 instanceof CharSequence)) || ((localObject1 instanceof CharSequence)))
        {
          paramArrayOfObject[paramInt] = new ConsString(ScriptRuntime.toCharSequence(localObject2), ScriptRuntime.toCharSequence(localObject1));
          return;
        }
        if ((localObject2 instanceof Number))
        {
          d1 = ((Number)localObject2).doubleValue();
          if (!(localObject1 instanceof Number)) {
            break label242;
          }
        }
        label242:
        for (d2 = ((Number)localObject1).doubleValue();; d2 = ScriptRuntime.toNumber(localObject1))
        {
          paramArrayOfObject[paramInt] = UniqueTag.DOUBLE_MARK;
          paramArrayOfDouble[paramInt] = (d2 + d1);
          return;
          d1 = ScriptRuntime.toNumber(localObject2);
          break;
        }
      }
    }
    if ((localObject1 instanceof CharSequence))
    {
      paramArrayOfDouble = (CharSequence)localObject1;
      paramContext = ScriptRuntime.toCharSequence(Double.valueOf(d1));
      if (i != 0)
      {
        paramArrayOfObject[paramInt] = new ConsString(paramArrayOfDouble, paramContext);
        return;
      }
      paramArrayOfObject[paramInt] = new ConsString(paramContext, paramArrayOfDouble);
      return;
    }
    if ((localObject1 instanceof Number)) {}
    for (double d2 = ((Number)localObject1).doubleValue();; d2 = ScriptRuntime.toNumber(localObject1))
    {
      paramArrayOfObject[paramInt] = UniqueTag.DOUBLE_MARK;
      paramArrayOfDouble[paramInt] = (d2 + d1);
      return;
    }
  }
  
  private static int doArithmetic(CallFrame paramCallFrame, int paramInt1, Object[] paramArrayOfObject, double[] paramArrayOfDouble, int paramInt2)
  {
    double d2 = stack_double(paramCallFrame, paramInt2);
    paramInt2 -= 1;
    double d1 = stack_double(paramCallFrame, paramInt2);
    paramArrayOfObject[paramInt2] = UniqueTag.DOUBLE_MARK;
    switch (paramInt1)
    {
    }
    for (;;)
    {
      paramArrayOfDouble[paramInt2] = d1;
      return paramInt2;
      d1 -= d2;
      continue;
      d1 *= d2;
      continue;
      d1 /= d2;
      continue;
      d1 %= d2;
    }
  }
  
  private static int doBitOp(CallFrame paramCallFrame, int paramInt1, Object[] paramArrayOfObject, double[] paramArrayOfDouble, int paramInt2)
  {
    int i = stack_int32(paramCallFrame, paramInt2 - 1);
    int k = stack_int32(paramCallFrame, paramInt2);
    int j = paramInt2 - 1;
    paramArrayOfObject[j] = UniqueTag.DOUBLE_MARK;
    paramInt2 = i;
    switch (paramInt1)
    {
    default: 
      paramInt2 = i;
    }
    for (;;)
    {
      paramArrayOfDouble[j] = paramInt2;
      return j;
      paramInt2 = i & k;
      continue;
      paramInt2 = i | k;
      continue;
      paramInt2 = i ^ k;
      continue;
      paramInt2 = i << k;
      continue;
      paramInt2 = i >> k;
    }
  }
  
  private static int doCallSpecial(Context paramContext, CallFrame paramCallFrame, Object[] paramArrayOfObject, double[] paramArrayOfDouble, int paramInt1, byte[] paramArrayOfByte, int paramInt2)
  {
    int j = paramArrayOfByte[paramCallFrame.pc] & 0xFF;
    int i;
    int k;
    if (paramArrayOfByte[(paramCallFrame.pc + 1)] != 0)
    {
      i = 1;
      k = getIndex(paramArrayOfByte, paramCallFrame.pc + 2);
      if (i == 0) {
        break label126;
      }
      paramInt1 -= paramInt2;
      Object localObject = paramArrayOfObject[paramInt1];
      paramArrayOfByte = (byte[])localObject;
      if (localObject == UniqueTag.DOUBLE_MARK) {
        paramArrayOfByte = ScriptRuntime.wrapNumber(paramArrayOfDouble[paramInt1]);
      }
      paramArrayOfObject[paramInt1] = ScriptRuntime.newSpecial(paramContext, paramArrayOfByte, getArgsArray(paramArrayOfObject, paramArrayOfDouble, paramInt1 + 1, paramInt2), paramCallFrame.scope, j);
    }
    for (;;)
    {
      paramCallFrame.pc += 4;
      return paramInt1;
      i = 0;
      break;
      label126:
      paramInt1 -= paramInt2 + 1;
      paramArrayOfByte = (Scriptable)paramArrayOfObject[(paramInt1 + 1)];
      paramArrayOfObject[paramInt1] = ScriptRuntime.callSpecial(paramContext, (Callable)paramArrayOfObject[paramInt1], paramArrayOfByte, getArgsArray(paramArrayOfObject, paramArrayOfDouble, paramInt1 + 2, paramInt2), paramCallFrame.scope, paramCallFrame.thisObj, j, paramCallFrame.idata.itsSourceFile, k);
    }
  }
  
  private static int doCompare(CallFrame paramCallFrame, int paramInt1, Object[] paramArrayOfObject, double[] paramArrayOfDouble, int paramInt2)
  {
    boolean bool = true;
    paramInt2 -= 1;
    Object localObject1 = paramArrayOfObject[(paramInt2 + 1)];
    Object localObject2 = paramArrayOfObject[paramInt2];
    double d2;
    if (localObject1 == UniqueTag.DOUBLE_MARK) {
      d2 = paramArrayOfDouble[(paramInt2 + 1)];
    }
    for (double d1 = stack_double(paramCallFrame, paramInt2);; d1 = paramArrayOfDouble[paramInt2]) {
      switch (paramInt1)
      {
      default: 
        throw Kit.codeBug();
        if (localObject2 != UniqueTag.DOUBLE_MARK) {
          break label176;
        }
        d2 = ScriptRuntime.toNumber(localObject1);
      }
    }
    if (d1 >= d2) {}
    for (;;)
    {
      paramArrayOfObject[paramInt2] = ScriptRuntime.wrapBoolean(bool);
      return paramInt2;
      bool = false;
      continue;
      if (d1 > d2)
      {
        bool = false;
        continue;
        if (d1 <= d2)
        {
          bool = false;
          continue;
          if (d1 >= d2)
          {
            bool = false;
            continue;
            switch (paramInt1)
            {
            default: 
              throw Kit.codeBug();
            case 17: 
              bool = ScriptRuntime.cmp_LE(localObject1, localObject2);
              break;
            case 15: 
              bool = ScriptRuntime.cmp_LE(localObject2, localObject1);
              break;
            case 16: 
              bool = ScriptRuntime.cmp_LT(localObject1, localObject2);
              break;
            case 14: 
              label176:
              bool = ScriptRuntime.cmp_LT(localObject2, localObject1);
            }
          }
        }
      }
    }
  }
  
  private static int doDelName(Context paramContext, CallFrame paramCallFrame, int paramInt1, Object[] paramArrayOfObject, double[] paramArrayOfDouble, int paramInt2)
  {
    Object localObject2 = paramArrayOfObject[paramInt2];
    Object localObject1 = localObject2;
    if (localObject2 == UniqueTag.DOUBLE_MARK) {
      localObject1 = ScriptRuntime.wrapNumber(paramArrayOfDouble[paramInt2]);
    }
    paramInt2 -= 1;
    Object localObject3 = paramArrayOfObject[paramInt2];
    localObject2 = localObject3;
    if (localObject3 == UniqueTag.DOUBLE_MARK) {
      localObject2 = ScriptRuntime.wrapNumber(paramArrayOfDouble[paramInt2]);
    }
    paramCallFrame = paramCallFrame.scope;
    if (paramInt1 == 0) {}
    for (boolean bool = true;; bool = false)
    {
      paramArrayOfObject[paramInt2] = ScriptRuntime.delete(localObject2, localObject1, paramContext, paramCallFrame, bool);
      return paramInt2;
    }
  }
  
  private static int doElemIncDec(Context paramContext, CallFrame paramCallFrame, byte[] paramArrayOfByte, Object[] paramArrayOfObject, double[] paramArrayOfDouble, int paramInt)
  {
    Object localObject2 = paramArrayOfObject[paramInt];
    Object localObject1 = localObject2;
    if (localObject2 == UniqueTag.DOUBLE_MARK) {
      localObject1 = ScriptRuntime.wrapNumber(paramArrayOfDouble[paramInt]);
    }
    paramInt -= 1;
    Object localObject3 = paramArrayOfObject[paramInt];
    localObject2 = localObject3;
    if (localObject3 == UniqueTag.DOUBLE_MARK) {
      localObject2 = ScriptRuntime.wrapNumber(paramArrayOfDouble[paramInt]);
    }
    paramArrayOfObject[paramInt] = ScriptRuntime.elemIncrDecr(localObject2, localObject1, paramContext, paramCallFrame.scope, paramArrayOfByte[paramCallFrame.pc]);
    paramCallFrame.pc += 1;
    return paramInt;
  }
  
  private static boolean doEquals(Object[] paramArrayOfObject, double[] paramArrayOfDouble, int paramInt)
  {
    Object localObject = paramArrayOfObject[(paramInt + 1)];
    paramArrayOfObject = paramArrayOfObject[paramInt];
    if (localObject == UniqueTag.DOUBLE_MARK)
    {
      if (paramArrayOfObject == UniqueTag.DOUBLE_MARK) {
        return paramArrayOfDouble[paramInt] == paramArrayOfDouble[(paramInt + 1)];
      }
      return ScriptRuntime.eqNumber(paramArrayOfDouble[(paramInt + 1)], paramArrayOfObject);
    }
    if (paramArrayOfObject == UniqueTag.DOUBLE_MARK) {
      return ScriptRuntime.eqNumber(paramArrayOfDouble[paramInt], localObject);
    }
    return ScriptRuntime.eq(paramArrayOfObject, localObject);
  }
  
  private static int doGetElem(Context paramContext, CallFrame paramCallFrame, Object[] paramArrayOfObject, double[] paramArrayOfDouble, int paramInt)
  {
    paramInt -= 1;
    Object localObject2 = paramArrayOfObject[paramInt];
    Object localObject1 = localObject2;
    if (localObject2 == UniqueTag.DOUBLE_MARK) {
      localObject1 = ScriptRuntime.wrapNumber(paramArrayOfDouble[paramInt]);
    }
    localObject2 = paramArrayOfObject[(paramInt + 1)];
    if (localObject2 != UniqueTag.DOUBLE_MARK) {}
    for (paramContext = ScriptRuntime.getObjectElem(localObject1, localObject2, paramContext, paramCallFrame.scope);; paramContext = ScriptRuntime.getObjectIndex(localObject1, paramArrayOfDouble[(paramInt + 1)], paramContext, paramCallFrame.scope))
    {
      paramArrayOfObject[paramInt] = paramContext;
      return paramInt;
    }
  }
  
  private static int doGetVar(CallFrame paramCallFrame, Object[] paramArrayOfObject1, double[] paramArrayOfDouble1, int paramInt1, Object[] paramArrayOfObject2, double[] paramArrayOfDouble2, int paramInt2)
  {
    paramInt1 += 1;
    if (!paramCallFrame.useActivation)
    {
      paramArrayOfObject1[paramInt1] = paramArrayOfObject2[paramInt2];
      paramArrayOfDouble1[paramInt1] = paramArrayOfDouble2[paramInt2];
      return paramInt1;
    }
    paramArrayOfDouble1 = paramCallFrame.idata.argNames[paramInt2];
    paramArrayOfObject1[paramInt1] = paramCallFrame.scope.get(paramArrayOfDouble1, paramCallFrame.scope);
    return paramInt1;
  }
  
  private static int doInOrInstanceof(Context paramContext, int paramInt1, Object[] paramArrayOfObject, double[] paramArrayOfDouble, int paramInt2)
  {
    Object localObject2 = paramArrayOfObject[paramInt2];
    Object localObject1 = localObject2;
    if (localObject2 == UniqueTag.DOUBLE_MARK) {
      localObject1 = ScriptRuntime.wrapNumber(paramArrayOfDouble[paramInt2]);
    }
    paramInt2 -= 1;
    Object localObject3 = paramArrayOfObject[paramInt2];
    localObject2 = localObject3;
    if (localObject3 == UniqueTag.DOUBLE_MARK) {
      localObject2 = ScriptRuntime.wrapNumber(paramArrayOfDouble[paramInt2]);
    }
    if (paramInt1 == 52) {}
    for (boolean bool = ScriptRuntime.in(localObject2, localObject1, paramContext);; bool = ScriptRuntime.instanceOf(localObject2, localObject1, paramContext))
    {
      paramArrayOfObject[paramInt2] = ScriptRuntime.wrapBoolean(bool);
      return paramInt2;
    }
  }
  
  private static int doRefMember(Context paramContext, Object[] paramArrayOfObject, double[] paramArrayOfDouble, int paramInt1, int paramInt2)
  {
    Object localObject2 = paramArrayOfObject[paramInt1];
    Object localObject1 = localObject2;
    if (localObject2 == UniqueTag.DOUBLE_MARK) {
      localObject1 = ScriptRuntime.wrapNumber(paramArrayOfDouble[paramInt1]);
    }
    paramInt1 -= 1;
    Object localObject3 = paramArrayOfObject[paramInt1];
    localObject2 = localObject3;
    if (localObject3 == UniqueTag.DOUBLE_MARK) {
      localObject2 = ScriptRuntime.wrapNumber(paramArrayOfDouble[paramInt1]);
    }
    paramArrayOfObject[paramInt1] = ScriptRuntime.memberRef(localObject2, localObject1, paramContext, paramInt2);
    return paramInt1;
  }
  
  private static int doRefNsMember(Context paramContext, Object[] paramArrayOfObject, double[] paramArrayOfDouble, int paramInt1, int paramInt2)
  {
    Object localObject2 = paramArrayOfObject[paramInt1];
    Object localObject1 = localObject2;
    if (localObject2 == UniqueTag.DOUBLE_MARK) {
      localObject1 = ScriptRuntime.wrapNumber(paramArrayOfDouble[paramInt1]);
    }
    paramInt1 -= 1;
    Object localObject3 = paramArrayOfObject[paramInt1];
    localObject2 = localObject3;
    if (localObject3 == UniqueTag.DOUBLE_MARK) {
      localObject2 = ScriptRuntime.wrapNumber(paramArrayOfDouble[paramInt1]);
    }
    paramInt1 -= 1;
    Object localObject4 = paramArrayOfObject[paramInt1];
    localObject3 = localObject4;
    if (localObject4 == UniqueTag.DOUBLE_MARK) {
      localObject3 = ScriptRuntime.wrapNumber(paramArrayOfDouble[paramInt1]);
    }
    paramArrayOfObject[paramInt1] = ScriptRuntime.memberRef(localObject3, localObject2, localObject1, paramContext, paramInt2);
    return paramInt1;
  }
  
  private static int doRefNsName(Context paramContext, CallFrame paramCallFrame, Object[] paramArrayOfObject, double[] paramArrayOfDouble, int paramInt1, int paramInt2)
  {
    Object localObject2 = paramArrayOfObject[paramInt1];
    Object localObject1 = localObject2;
    if (localObject2 == UniqueTag.DOUBLE_MARK) {
      localObject1 = ScriptRuntime.wrapNumber(paramArrayOfDouble[paramInt1]);
    }
    paramInt1 -= 1;
    Object localObject3 = paramArrayOfObject[paramInt1];
    localObject2 = localObject3;
    if (localObject3 == UniqueTag.DOUBLE_MARK) {
      localObject2 = ScriptRuntime.wrapNumber(paramArrayOfDouble[paramInt1]);
    }
    paramArrayOfObject[paramInt1] = ScriptRuntime.nameRef(localObject2, localObject1, paramContext, paramCallFrame.scope, paramInt2);
    return paramInt1;
  }
  
  private static int doSetConstVar(CallFrame paramCallFrame, Object[] paramArrayOfObject1, double[] paramArrayOfDouble1, int paramInt1, Object[] paramArrayOfObject2, double[] paramArrayOfDouble2, int[] paramArrayOfInt, int paramInt2)
  {
    if (!paramCallFrame.useActivation)
    {
      if ((paramArrayOfInt[paramInt2] & 0x1) == 0) {
        throw Context.reportRuntimeError1("msg.var.redecl", paramCallFrame.idata.argNames[paramInt2]);
      }
      if ((paramArrayOfInt[paramInt2] & 0x8) != 0)
      {
        paramArrayOfObject2[paramInt2] = paramArrayOfObject1[paramInt1];
        paramArrayOfInt[paramInt2] &= 0xFFFFFFF7;
        paramArrayOfDouble2[paramInt2] = paramArrayOfDouble1[paramInt1];
      }
      return paramInt1;
    }
    paramArrayOfObject1 = paramArrayOfObject1[paramInt1];
    if (paramArrayOfObject1 == UniqueTag.DOUBLE_MARK) {
      paramArrayOfObject1 = ScriptRuntime.wrapNumber(paramArrayOfDouble1[paramInt1]);
    }
    for (;;)
    {
      paramArrayOfDouble1 = paramCallFrame.idata.argNames[paramInt2];
      if ((paramCallFrame.scope instanceof ConstProperties))
      {
        ((ConstProperties)paramCallFrame.scope).putConst(paramArrayOfDouble1, paramCallFrame.scope, paramArrayOfObject1);
        return paramInt1;
      }
      throw Kit.codeBug();
    }
  }
  
  private static int doSetElem(Context paramContext, CallFrame paramCallFrame, Object[] paramArrayOfObject, double[] paramArrayOfDouble, int paramInt)
  {
    paramInt -= 2;
    Object localObject2 = paramArrayOfObject[(paramInt + 2)];
    Object localObject1 = localObject2;
    if (localObject2 == UniqueTag.DOUBLE_MARK) {
      localObject1 = ScriptRuntime.wrapNumber(paramArrayOfDouble[(paramInt + 2)]);
    }
    Object localObject3 = paramArrayOfObject[paramInt];
    localObject2 = localObject3;
    if (localObject3 == UniqueTag.DOUBLE_MARK) {
      localObject2 = ScriptRuntime.wrapNumber(paramArrayOfDouble[paramInt]);
    }
    localObject3 = paramArrayOfObject[(paramInt + 1)];
    if (localObject3 != UniqueTag.DOUBLE_MARK) {}
    for (paramContext = ScriptRuntime.setObjectElem(localObject2, localObject3, localObject1, paramContext, paramCallFrame.scope);; paramContext = ScriptRuntime.setObjectIndex(localObject2, paramArrayOfDouble[(paramInt + 1)], localObject1, paramContext, paramCallFrame.scope))
    {
      paramArrayOfObject[paramInt] = paramContext;
      return paramInt;
    }
  }
  
  private static int doSetVar(CallFrame paramCallFrame, Object[] paramArrayOfObject1, double[] paramArrayOfDouble1, int paramInt1, Object[] paramArrayOfObject2, double[] paramArrayOfDouble2, int[] paramArrayOfInt, int paramInt2)
  {
    if (!paramCallFrame.useActivation)
    {
      if ((paramArrayOfInt[paramInt2] & 0x1) == 0)
      {
        paramArrayOfObject2[paramInt2] = paramArrayOfObject1[paramInt1];
        paramArrayOfDouble2[paramInt2] = paramArrayOfDouble1[paramInt1];
      }
      return paramInt1;
    }
    paramArrayOfObject2 = paramArrayOfObject1[paramInt1];
    paramArrayOfObject1 = paramArrayOfObject2;
    if (paramArrayOfObject2 == UniqueTag.DOUBLE_MARK) {
      paramArrayOfObject1 = ScriptRuntime.wrapNumber(paramArrayOfDouble1[paramInt1]);
    }
    paramArrayOfDouble1 = paramCallFrame.idata.argNames[paramInt2];
    paramCallFrame.scope.put(paramArrayOfDouble1, paramCallFrame.scope, paramArrayOfObject1);
    return paramInt1;
  }
  
  private static boolean doShallowEquals(Object[] paramArrayOfObject, double[] paramArrayOfDouble, int paramInt)
  {
    boolean bool = false;
    Object localObject = paramArrayOfObject[(paramInt + 1)];
    paramArrayOfObject = paramArrayOfObject[paramInt];
    UniqueTag localUniqueTag = UniqueTag.DOUBLE_MARK;
    double d2;
    double d1;
    if (localObject == localUniqueTag)
    {
      d2 = paramArrayOfDouble[(paramInt + 1)];
      if (paramArrayOfObject == localUniqueTag)
      {
        d1 = paramArrayOfDouble[paramInt];
        if (d1 != d2) {
          break label112;
        }
        bool = true;
      }
    }
    for (;;)
    {
      return bool;
      if ((paramArrayOfObject instanceof Number))
      {
        d1 = ((Number)paramArrayOfObject).doubleValue();
        break;
        if (paramArrayOfObject == localUniqueTag)
        {
          d1 = paramArrayOfDouble[paramInt];
          if (!(localObject instanceof Number)) {
            continue;
          }
          d2 = ((Number)localObject).doubleValue();
          break;
        }
        return ScriptRuntime.shallowEq(paramArrayOfObject, localObject);
        label112:
        bool = false;
      }
    }
  }
  
  private static int doVarIncDec(Context paramContext, CallFrame paramCallFrame, Object[] paramArrayOfObject1, double[] paramArrayOfDouble1, int paramInt1, Object[] paramArrayOfObject2, double[] paramArrayOfDouble2, int[] paramArrayOfInt, int paramInt2)
  {
    int i = paramInt1 + 1;
    paramInt1 = paramCallFrame.idata.itsICode[paramCallFrame.pc];
    double d1;
    double d2;
    if (!paramCallFrame.useActivation)
    {
      paramContext = paramArrayOfObject2[paramInt2];
      if (paramContext == UniqueTag.DOUBLE_MARK)
      {
        d1 = paramArrayOfDouble2[paramInt2];
        if ((paramInt1 & 0x1) != 0) {
          break label142;
        }
        d2 = 1.0D + d1;
        label60:
        if ((paramInt1 & 0x2) == 0) {
          break label151;
        }
        paramInt1 = 1;
        label70:
        if ((paramArrayOfInt[paramInt2] & 0x1) != 0) {
          break label164;
        }
        if (paramContext != UniqueTag.DOUBLE_MARK) {
          paramArrayOfObject2[paramInt2] = UniqueTag.DOUBLE_MARK;
        }
        paramArrayOfDouble2[paramInt2] = d2;
        paramArrayOfObject1[i] = UniqueTag.DOUBLE_MARK;
        if (paramInt1 == 0) {
          break label157;
        }
        label114:
        paramArrayOfDouble1[i] = d1;
      }
    }
    for (;;)
    {
      paramCallFrame.pc += 1;
      return i;
      d1 = ScriptRuntime.toNumber(paramContext);
      break;
      label142:
      d2 = d1 - 1.0D;
      break label60;
      label151:
      paramInt1 = 0;
      break label70;
      label157:
      d1 = d2;
      break label114;
      label164:
      if ((paramInt1 != 0) && (paramContext != UniqueTag.DOUBLE_MARK))
      {
        paramArrayOfObject1[i] = paramContext;
      }
      else
      {
        paramArrayOfObject1[i] = UniqueTag.DOUBLE_MARK;
        if (paramInt1 != 0) {}
        for (;;)
        {
          paramArrayOfDouble1[i] = d1;
          break;
          d1 = d2;
        }
        paramArrayOfDouble1 = paramCallFrame.idata.argNames[paramInt2];
        paramArrayOfObject1[i] = ScriptRuntime.nameIncrDecr(paramCallFrame.scope, paramArrayOfDouble1, paramContext, paramInt1);
      }
    }
  }
  
  static void dumpICode(InterpreterData paramInterpreterData) {}
  
  private static void enterFrame(Context paramContext, CallFrame paramCallFrame, Object[] paramArrayOfObject, boolean paramBoolean)
  {
    boolean bool = paramCallFrame.idata.itsNeedsActivation;
    int i;
    Object localObject2;
    Object localObject1;
    if (paramCallFrame.debuggerFrame != null)
    {
      i = 1;
      if ((bool) || (i != 0))
      {
        localObject2 = paramCallFrame.scope;
        if (localObject2 != null) {
          break label88;
        }
        Kit.codeBug();
        localObject1 = localObject2;
      }
    }
    for (;;)
    {
      if (i != 0) {
        paramCallFrame.debuggerFrame.onEnter(paramContext, (Scriptable)localObject1, paramCallFrame.thisObj, paramArrayOfObject);
      }
      if (bool) {
        ScriptRuntime.enterActivationFunction(paramContext, (Scriptable)localObject1);
      }
      return;
      i = 0;
      break;
      label88:
      localObject1 = localObject2;
      if (paramBoolean)
      {
        do
        {
          do
          {
            localObject1 = localObject2;
            if (!(localObject2 instanceof NativeWith)) {
              break;
            }
            localObject1 = ((Scriptable)localObject2).getParentScope();
            if (localObject1 == null) {
              break label149;
            }
            localObject2 = localObject1;
          } while (paramCallFrame.parentFrame == null);
          localObject2 = localObject1;
        } while (paramCallFrame.parentFrame.scope != localObject1);
        label149:
        Kit.codeBug();
      }
    }
  }
  
  private static void exitFrame(Context paramContext, CallFrame paramCallFrame, Object paramObject)
  {
    if (paramCallFrame.idata.itsNeedsActivation) {
      ScriptRuntime.exitActivationFunction(paramContext);
    }
    if (paramCallFrame.debuggerFrame != null) {
      for (;;)
      {
        ContinuationJump localContinuationJump;
        try
        {
          if ((paramObject instanceof Throwable))
          {
            paramCallFrame.debuggerFrame.onExit(paramContext, true, paramObject);
            return;
          }
          localContinuationJump = (ContinuationJump)paramObject;
          if (localContinuationJump == null)
          {
            paramObject = paramCallFrame.result;
            Object localObject = paramObject;
            if (paramObject == UniqueTag.DOUBLE_MARK)
            {
              if (localContinuationJump != null) {
                break label124;
              }
              d = paramCallFrame.resultDbl;
              localObject = ScriptRuntime.wrapNumber(d);
            }
            paramCallFrame.debuggerFrame.onExit(paramContext, false, localObject);
            return;
          }
        }
        catch (Throwable paramContext)
        {
          System.err.println("RHINO USAGE WARNING: onExit terminated with exception");
          paramContext.printStackTrace(System.err);
          return;
        }
        paramObject = localContinuationJump.result;
        continue;
        label124:
        double d = localContinuationJump.resultDbl;
      }
    }
  }
  
  private static Object freezeGenerator(Context paramContext, CallFrame paramCallFrame, int paramInt, GeneratorState paramGeneratorState)
  {
    if (paramGeneratorState.operation == 2) {
      throw ScriptRuntime.typeError0("msg.yield.closing");
    }
    paramCallFrame.frozen = true;
    paramCallFrame.result = paramCallFrame.stack[paramInt];
    paramCallFrame.resultDbl = paramCallFrame.sDbl[paramInt];
    paramCallFrame.savedStackTop = paramInt;
    paramCallFrame.pc -= 1;
    ScriptRuntime.exitActivationFunction(paramContext);
    if (paramCallFrame.result != UniqueTag.DOUBLE_MARK) {
      return paramCallFrame.result;
    }
    return ScriptRuntime.wrapNumber(paramCallFrame.resultDbl);
  }
  
  private static Object[] getArgsArray(Object[] paramArrayOfObject, double[] paramArrayOfDouble, int paramInt1, int paramInt2)
  {
    if (paramInt2 == 0) {
      return ScriptRuntime.emptyArgs;
    }
    Object[] arrayOfObject = new Object[paramInt2];
    int j = 0;
    int i = paramInt1;
    paramInt1 = j;
    while (paramInt1 != paramInt2)
    {
      Object localObject2 = paramArrayOfObject[i];
      Object localObject1 = localObject2;
      if (localObject2 == UniqueTag.DOUBLE_MARK) {
        localObject1 = ScriptRuntime.wrapNumber(paramArrayOfDouble[i]);
      }
      arrayOfObject[paramInt1] = localObject1;
      i += 1;
      paramInt1 += 1;
    }
    return arrayOfObject;
  }
  
  static String getEncodedSource(InterpreterData paramInterpreterData)
  {
    if (paramInterpreterData.encodedSource == null) {
      return null;
    }
    return paramInterpreterData.encodedSource.substring(paramInterpreterData.encodedSourceStart, paramInterpreterData.encodedSourceEnd);
  }
  
  private static int getExceptionHandler(CallFrame paramCallFrame, boolean paramBoolean)
  {
    int i = 0;
    int[] arrayOfInt = paramCallFrame.idata.itsExceptionTable;
    if (arrayOfInt == null) {
      return -1;
    }
    int i5 = paramCallFrame.pc - 1;
    int m = 0;
    int j = -1;
    int i1 = 0;
    if (i != arrayOfInt.length)
    {
      int i3 = arrayOfInt[(i + 0)];
      int i4 = arrayOfInt[(i + 1)];
      int k = i1;
      int n = m;
      int i2 = j;
      if (i3 <= i5)
      {
        if (i5 < i4) {
          break label112;
        }
        i2 = j;
        n = m;
        k = i1;
      }
      for (;;)
      {
        i += 6;
        i1 = k;
        m = n;
        j = i2;
        break;
        label112:
        if (paramBoolean)
        {
          k = i1;
          n = m;
          i2 = j;
          if (arrayOfInt[(i + 3)] != 1) {}
        }
        else
        {
          if (j >= 0)
          {
            k = i1;
            n = m;
            i2 = j;
            if (i1 < i4) {
              continue;
            }
            if (m > i3) {
              Kit.codeBug();
            }
            if (i1 == i4) {
              Kit.codeBug();
            }
          }
          k = i4;
          n = i3;
          i2 = i;
        }
      }
    }
    return j;
  }
  
  private static int getIndex(byte[] paramArrayOfByte, int paramInt)
  {
    return (paramArrayOfByte[paramInt] & 0xFF) << 8 | paramArrayOfByte[(paramInt + 1)] & 0xFF;
  }
  
  private static int getInt(byte[] paramArrayOfByte, int paramInt)
  {
    return paramArrayOfByte[paramInt] << 24 | (paramArrayOfByte[(paramInt + 1)] & 0xFF) << 16 | (paramArrayOfByte[(paramInt + 2)] & 0xFF) << 8 | paramArrayOfByte[(paramInt + 3)] & 0xFF;
  }
  
  static int[] getLineNumbers(InterpreterData paramInterpreterData)
  {
    UintMap localUintMap = new UintMap();
    paramInterpreterData = paramInterpreterData.itsICode;
    int j = paramInterpreterData.length;
    int i = 0;
    while (i != j)
    {
      int k = paramInterpreterData[i];
      int m = bytecodeSpan(k);
      if (k == -26)
      {
        if (m != 3) {
          Kit.codeBug();
        }
        localUintMap.put(getIndex(paramInterpreterData, i + 1), 0);
      }
      i += m;
    }
    return localUintMap.getKeys();
  }
  
  private static int getShort(byte[] paramArrayOfByte, int paramInt)
  {
    return paramArrayOfByte[paramInt] << 8 | paramArrayOfByte[(paramInt + 1)] & 0xFF;
  }
  
  private static void initFrame(Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject, double[] paramArrayOfDouble, int paramInt1, int paramInt2, InterpretedFunction paramInterpretedFunction, CallFrame paramCallFrame1, CallFrame paramCallFrame2)
  {
    InterpreterData localInterpreterData = paramInterpretedFunction.idata;
    boolean bool = localInterpreterData.itsNeedsActivation;
    DebugFrame localDebugFrame;
    if (paramContext.debugger != null)
    {
      localDebugFrame = paramContext.debugger.getFrame(paramContext, localInterpreterData);
      if (localDebugFrame != null) {
        bool = true;
      }
    }
    for (;;)
    {
      Object[] arrayOfObject1 = paramArrayOfObject;
      double[] arrayOfDouble = paramArrayOfDouble;
      int i = paramInt1;
      if (bool)
      {
        arrayOfObject1 = paramArrayOfObject;
        if (paramArrayOfDouble != null) {
          arrayOfObject1 = getArgsArray(paramArrayOfObject, paramArrayOfDouble, paramInt1, paramInt2);
        }
        i = 0;
        arrayOfDouble = null;
      }
      if (localInterpreterData.itsFunctionType != 0)
      {
        paramArrayOfObject = paramInterpretedFunction.getParentScope();
        paramScriptable1 = paramArrayOfObject;
        if (bool)
        {
          if (localInterpreterData.itsFunctionType != 4) {
            break label204;
          }
          paramScriptable1 = ScriptRuntime.createArrowFunctionActivation(paramInterpretedFunction, paramArrayOfObject, arrayOfObject1, localInterpreterData.isStrict);
        }
      }
      while (localInterpreterData.itsNestedFunctions != null)
      {
        if ((localInterpreterData.itsFunctionType != 0) && (!localInterpreterData.itsNeedsActivation)) {
          Kit.codeBug();
        }
        paramInt1 = 0;
        while (paramInt1 < localInterpreterData.itsNestedFunctions.length)
        {
          if (localInterpreterData.itsNestedFunctions[paramInt1].itsFunctionType == 1) {
            initFunction(paramContext, paramScriptable1, paramInterpretedFunction, paramInt1);
          }
          paramInt1 += 1;
        }
        label204:
        paramScriptable1 = ScriptRuntime.createFunctionActivation(paramInterpretedFunction, paramArrayOfObject, arrayOfObject1, localInterpreterData.isStrict);
        continue;
        ScriptRuntime.initScript(paramInterpretedFunction, paramScriptable2, paramContext, paramScriptable1, paramInterpretedFunction.idata.evalScriptFlag);
      }
      int k = localInterpreterData.itsMaxVars + localInterpreterData.itsMaxLocals - 1;
      int j = localInterpreterData.itsMaxFrameArray;
      if (j != localInterpreterData.itsMaxStack + k + 1) {
        Kit.codeBug();
      }
      Object[] arrayOfObject2;
      if ((paramCallFrame2.stack != null) && (j <= paramCallFrame2.stack.length))
      {
        paramInt1 = 1;
        arrayOfObject2 = paramCallFrame2.stack;
        paramArrayOfDouble = paramCallFrame2.stackAttributes;
      }
      for (paramArrayOfObject = paramCallFrame2.sDbl;; paramArrayOfObject = new double[j])
      {
        int m = localInterpreterData.getParamAndVarCount();
        j = 0;
        while (j < m)
        {
          if (localInterpreterData.getParamOrVarConst(j)) {
            paramArrayOfDouble[j] = 13;
          }
          j += 1;
        }
        paramInt1 = 0;
        arrayOfObject2 = new Object[j];
        paramArrayOfDouble = new int[j];
      }
      j = localInterpreterData.argCount;
      if (j > paramInt2) {}
      for (;;)
      {
        paramCallFrame2.parentFrame = paramCallFrame1;
        if (paramCallFrame1 == null) {}
        for (j = 0;; j = paramCallFrame1.frameIndex + 1)
        {
          paramCallFrame2.frameIndex = j;
          if (paramCallFrame2.frameIndex <= paramContext.getMaximumInterpreterStackDepth()) {
            break;
          }
          throw Context.reportRuntimeError("Exceeded maximum stack depth");
        }
        paramCallFrame2.frozen = false;
        paramCallFrame2.fnOrScript = paramInterpretedFunction;
        paramCallFrame2.idata = localInterpreterData;
        paramCallFrame2.stack = arrayOfObject2;
        paramCallFrame2.stackAttributes = paramArrayOfDouble;
        paramCallFrame2.sDbl = paramArrayOfObject;
        paramCallFrame2.varSource = paramCallFrame2;
        paramCallFrame2.localShift = localInterpreterData.itsMaxVars;
        paramCallFrame2.emptyStackTop = k;
        paramCallFrame2.debuggerFrame = localDebugFrame;
        paramCallFrame2.useActivation = bool;
        paramCallFrame2.thisObj = paramScriptable2;
        paramCallFrame2.result = Undefined.instance;
        paramCallFrame2.pc = 0;
        paramCallFrame2.pcPrevBranch = 0;
        paramCallFrame2.pcSourceLineStart = localInterpreterData.firstLinePC;
        paramCallFrame2.scope = paramScriptable1;
        paramCallFrame2.savedStackTop = k;
        paramCallFrame2.savedCallOp = 0;
        System.arraycopy(arrayOfObject1, i, arrayOfObject2, 0, paramInt2);
        j = paramInt2;
        if (arrayOfDouble != null)
        {
          System.arraycopy(arrayOfDouble, i, paramArrayOfObject, 0, paramInt2);
          j = paramInt2;
        }
        while (j != localInterpreterData.itsMaxVars)
        {
          arrayOfObject2[j] = Undefined.instance;
          j += 1;
        }
        if (paramInt1 != 0)
        {
          paramInt1 = k + 1;
          while (paramInt1 != arrayOfObject2.length)
          {
            arrayOfObject2[paramInt1] = null;
            paramInt1 += 1;
          }
        }
        enterFrame(paramContext, paramCallFrame2, arrayOfObject1, false);
        return;
        paramInt2 = j;
      }
      continue;
      localDebugFrame = null;
    }
  }
  
  private static CallFrame initFrameForApplyOrCall(Context paramContext, CallFrame paramCallFrame, int paramInt1, Object[] paramArrayOfObject, double[] paramArrayOfDouble, int paramInt2, int paramInt3, Scriptable paramScriptable, IdFunctionObject paramIdFunctionObject, InterpretedFunction paramInterpretedFunction)
  {
    Object localObject2;
    Object localObject1;
    if (paramInt1 != 0)
    {
      localObject2 = paramArrayOfObject[(paramInt2 + 2)];
      localObject1 = localObject2;
      if (localObject2 == UniqueTag.DOUBLE_MARK) {
        localObject1 = ScriptRuntime.wrapNumber(paramArrayOfDouble[(paramInt2 + 2)]);
      }
      localObject1 = ScriptRuntime.toObjectOrNull(paramContext, localObject1, paramCallFrame.scope);
      localObject2 = localObject1;
      if (localObject1 == null) {
        localObject2 = ScriptRuntime.getTopCallScope(paramContext);
      }
      if (paramInt3 != -55) {
        break label135;
      }
      exitFrame(paramContext, paramCallFrame, null);
      paramCallFrame = paramCallFrame.parentFrame;
      label81:
      localObject1 = new CallFrame(null);
      if (!BaseFunction.isApply(paramIdFunctionObject)) {
        break label164;
      }
      if (paramInt1 >= 2) {
        break label150;
      }
    }
    label135:
    label150:
    for (paramArrayOfObject = ScriptRuntime.emptyArgs;; paramArrayOfObject = ScriptRuntime.getApplyArguments(paramContext, paramArrayOfObject[(paramInt2 + 3)]))
    {
      initFrame(paramContext, paramScriptable, (Scriptable)localObject2, paramArrayOfObject, null, 0, paramArrayOfObject.length, paramInterpretedFunction, paramCallFrame, (CallFrame)localObject1);
      return (CallFrame)localObject1;
      localObject1 = null;
      break;
      paramCallFrame.savedStackTop = paramInt2;
      paramCallFrame.savedCallOp = paramInt3;
      break label81;
    }
    label164:
    paramInt3 = 1;
    while (paramInt3 < paramInt1)
    {
      paramArrayOfObject[(paramInt2 + 1 + paramInt3)] = paramArrayOfObject[(paramInt2 + 2 + paramInt3)];
      paramArrayOfDouble[(paramInt2 + 1 + paramInt3)] = paramArrayOfDouble[(paramInt2 + 2 + paramInt3)];
      paramInt3 += 1;
    }
    if (paramInt1 < 2) {
      paramInt1 = 0;
    }
    for (;;)
    {
      initFrame(paramContext, paramScriptable, (Scriptable)localObject2, paramArrayOfObject, paramArrayOfDouble, paramInt2 + 2, paramInt1, paramInterpretedFunction, paramCallFrame, (CallFrame)localObject1);
      return (CallFrame)localObject1;
      paramInt1 -= 1;
    }
  }
  
  private static CallFrame initFrameForNoSuchMethod(Context paramContext, CallFrame paramCallFrame, int paramInt1, Object[] paramArrayOfObject, double[] paramArrayOfDouble, int paramInt2, int paramInt3, Scriptable paramScriptable1, Scriptable paramScriptable2, ScriptRuntime.NoSuchMethodShim paramNoSuchMethodShim, InterpretedFunction paramInterpretedFunction)
  {
    Object[] arrayOfObject = new Object[paramInt1];
    int j = paramInt2 + 2;
    int i = 0;
    while (i < paramInt1)
    {
      Object localObject2 = paramArrayOfObject[j];
      localObject1 = localObject2;
      if (localObject2 == UniqueTag.DOUBLE_MARK) {
        localObject1 = ScriptRuntime.wrapNumber(paramArrayOfDouble[j]);
      }
      arrayOfObject[i] = localObject1;
      j += 1;
      i += 1;
    }
    paramArrayOfDouble = paramNoSuchMethodShim.methodName;
    paramNoSuchMethodShim = paramContext.newArray(paramScriptable2, arrayOfObject);
    Object localObject1 = new CallFrame(null);
    if (paramInt3 == -55)
    {
      paramArrayOfObject = paramCallFrame.parentFrame;
      exitFrame(paramContext, paramCallFrame, null);
    }
    for (;;)
    {
      initFrame(paramContext, paramScriptable2, paramScriptable1, new Object[] { paramArrayOfDouble, paramNoSuchMethodShim }, null, 0, 2, paramInterpretedFunction, paramArrayOfObject, (CallFrame)localObject1);
      if (paramInt3 != -55)
      {
        paramCallFrame.savedStackTop = paramInt2;
        paramCallFrame.savedCallOp = paramInt3;
      }
      return (CallFrame)localObject1;
      paramArrayOfObject = paramCallFrame;
    }
  }
  
  private static void initFunction(Context paramContext, Scriptable paramScriptable, InterpretedFunction paramInterpretedFunction, int paramInt)
  {
    InterpretedFunction localInterpretedFunction = InterpretedFunction.createFunction(paramContext, paramScriptable, paramInterpretedFunction, paramInt);
    ScriptRuntime.initFunction(paramContext, paramScriptable, localInterpretedFunction, localInterpretedFunction.idata.itsFunctionType, paramInterpretedFunction.idata.evalScriptFlag);
  }
  
  static Object interpret(InterpretedFunction paramInterpretedFunction, Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject)
  {
    if (!ScriptRuntime.hasTopCall(paramContext)) {
      Kit.codeBug();
    }
    if (paramContext.interpreterSecurityDomain != paramInterpretedFunction.securityDomain)
    {
      localObject = paramContext.interpreterSecurityDomain;
      paramContext.interpreterSecurityDomain = paramInterpretedFunction.securityDomain;
      try
      {
        paramInterpretedFunction = paramInterpretedFunction.securityController.callWithDomain(paramInterpretedFunction.securityDomain, paramContext, paramInterpretedFunction, paramScriptable1, paramScriptable2, paramArrayOfObject);
        return paramInterpretedFunction;
      }
      finally
      {
        paramContext.interpreterSecurityDomain = localObject;
      }
    }
    Object localObject = new CallFrame(null);
    initFrame(paramContext, paramScriptable1, paramScriptable2, paramArrayOfObject, null, 0, paramArrayOfObject.length, paramInterpretedFunction, null, (CallFrame)localObject);
    ((CallFrame)localObject).isContinuationsTopFrame = paramContext.isContinuationsTopCall;
    paramContext.isContinuationsTopCall = false;
    return interpretLoop(paramContext, (CallFrame)localObject, null);
  }
  
  /* Error */
  private static Object interpretLoop(Context paramContext, CallFrame paramCallFrame, Object paramObject)
  {
    // Byte code:
    //   0: getstatic 149	org/mozilla/javascript/UniqueTag:DOUBLE_MARK	Lorg/mozilla/javascript/UniqueTag;
    //   3: astore 23
    //   5: getstatic 571	org/mozilla/javascript/Undefined:instance	Ljava/lang/Object;
    //   8: astore 24
    //   10: aload_0
    //   11: getfield 56	org/mozilla/javascript/Context:instructionThreshold	I
    //   14: ifeq +1335 -> 1349
    //   17: iconst_1
    //   18: istore 13
    //   20: iconst_m1
    //   21: istore 10
    //   23: aload_0
    //   24: getfield 78	org/mozilla/javascript/Context:lastInterpreterFrame	Ljava/lang/Object;
    //   27: ifnull +32 -> 59
    //   30: aload_0
    //   31: getfield 661	org/mozilla/javascript/Context:previousInterpreterInvocations	Lorg/mozilla/javascript/ObjArray;
    //   34: ifnonnull +14 -> 48
    //   37: aload_0
    //   38: new 663	org/mozilla/javascript/ObjArray
    //   41: dup
    //   42: invokespecial 664	org/mozilla/javascript/ObjArray:<init>	()V
    //   45: putfield 661	org/mozilla/javascript/Context:previousInterpreterInvocations	Lorg/mozilla/javascript/ObjArray;
    //   48: aload_0
    //   49: getfield 661	org/mozilla/javascript/Context:previousInterpreterInvocations	Lorg/mozilla/javascript/ObjArray;
    //   52: aload_0
    //   53: getfield 78	org/mozilla/javascript/Context:lastInterpreterFrame	Ljava/lang/Object;
    //   56: invokevirtual 667	org/mozilla/javascript/ObjArray:push	(Ljava/lang/Object;)V
    //   59: aload_2
    //   60: ifnull +1306 -> 1366
    //   63: aload_2
    //   64: instanceof 16
    //   67: ifeq +1288 -> 1355
    //   70: aload_2
    //   71: checkcast 16	org/mozilla/javascript/Interpreter$GeneratorState
    //   74: astore 22
    //   76: aload_0
    //   77: aload_1
    //   78: getstatic 438	org/mozilla/javascript/ScriptRuntime:emptyArgs	[Ljava/lang/Object;
    //   81: iconst_1
    //   82: invokestatic 583	org/mozilla/javascript/Interpreter:enterFrame	(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Interpreter$CallFrame;[Ljava/lang/Object;Z)V
    //   85: aconst_null
    //   86: astore_2
    //   87: aconst_null
    //   88: astore 18
    //   90: dconst_0
    //   91: dstore_3
    //   92: aconst_null
    //   93: astore 15
    //   95: aload_2
    //   96: astore 17
    //   98: aload_1
    //   99: astore 16
    //   101: aload 18
    //   103: astore_2
    //   104: aload 15
    //   106: astore_1
    //   107: aload 17
    //   109: ifnull +1263 -> 1372
    //   112: aload 16
    //   114: astore 21
    //   116: aload_1
    //   117: astore 19
    //   119: aload 17
    //   121: astore 20
    //   123: aload_0
    //   124: aload 17
    //   126: aload 16
    //   128: iload 10
    //   130: iload 13
    //   132: invokestatic 671	org/mozilla/javascript/Interpreter:processThrowable	(Lorg/mozilla/javascript/Context;Ljava/lang/Object;Lorg/mozilla/javascript/Interpreter$CallFrame;IZ)Lorg/mozilla/javascript/Interpreter$CallFrame;
    //   135: astore 15
    //   137: aload 15
    //   139: getfield 674	org/mozilla/javascript/Interpreter$CallFrame:throwable	Ljava/lang/Object;
    //   142: astore 18
    //   144: aload 18
    //   146: astore 17
    //   148: aload 15
    //   150: aconst_null
    //   151: putfield 674	org/mozilla/javascript/Interpreter$CallFrame:throwable	Ljava/lang/Object;
    //   154: aload 15
    //   156: astore 21
    //   158: aload_1
    //   159: astore 19
    //   161: aload 18
    //   163: astore 20
    //   165: aload 15
    //   167: getfield 112	org/mozilla/javascript/Interpreter$CallFrame:stack	[Ljava/lang/Object;
    //   170: astore 26
    //   172: aload 15
    //   174: astore 21
    //   176: aload_1
    //   177: astore 19
    //   179: aload 18
    //   181: astore 20
    //   183: aload 15
    //   185: getfield 435	org/mozilla/javascript/Interpreter$CallFrame:sDbl	[D
    //   188: astore 25
    //   190: aload 15
    //   192: astore 21
    //   194: aload_1
    //   195: astore 19
    //   197: aload 18
    //   199: astore 20
    //   201: aload 15
    //   203: getfield 560	org/mozilla/javascript/Interpreter$CallFrame:varSource	Lorg/mozilla/javascript/Interpreter$CallFrame;
    //   206: getfield 112	org/mozilla/javascript/Interpreter$CallFrame:stack	[Ljava/lang/Object;
    //   209: astore 28
    //   211: aload 15
    //   213: astore 21
    //   215: aload_1
    //   216: astore 19
    //   218: aload 18
    //   220: astore 20
    //   222: aload 15
    //   224: getfield 560	org/mozilla/javascript/Interpreter$CallFrame:varSource	Lorg/mozilla/javascript/Interpreter$CallFrame;
    //   227: getfield 435	org/mozilla/javascript/Interpreter$CallFrame:sDbl	[D
    //   230: astore 29
    //   232: aload 15
    //   234: astore 21
    //   236: aload_1
    //   237: astore 19
    //   239: aload 18
    //   241: astore 20
    //   243: aload 15
    //   245: getfield 560	org/mozilla/javascript/Interpreter$CallFrame:varSource	Lorg/mozilla/javascript/Interpreter$CallFrame;
    //   248: getfield 116	org/mozilla/javascript/Interpreter$CallFrame:stackAttributes	[I
    //   251: astore 30
    //   253: aload 15
    //   255: astore 21
    //   257: aload_1
    //   258: astore 19
    //   260: aload 18
    //   262: astore 20
    //   264: aload 15
    //   266: getfield 223	org/mozilla/javascript/Interpreter$CallFrame:idata	Lorg/mozilla/javascript/InterpreterData;
    //   269: getfield 351	org/mozilla/javascript/InterpreterData:itsICode	[B
    //   272: astore 27
    //   274: aload 15
    //   276: astore 21
    //   278: aload_1
    //   279: astore 19
    //   281: aload 18
    //   283: astore 20
    //   285: aload 15
    //   287: getfield 223	org/mozilla/javascript/Interpreter$CallFrame:idata	Lorg/mozilla/javascript/InterpreterData;
    //   290: getfield 677	org/mozilla/javascript/InterpreterData:itsStringTable	[Ljava/lang/String;
    //   293: astore 31
    //   295: aload 15
    //   297: astore 21
    //   299: aload_1
    //   300: astore 19
    //   302: aload 18
    //   304: astore 20
    //   306: aload 15
    //   308: getfield 108	org/mozilla/javascript/Interpreter$CallFrame:savedStackTop	I
    //   311: istore 9
    //   313: aload 15
    //   315: astore 21
    //   317: aload_1
    //   318: astore 19
    //   320: aload 18
    //   322: astore 20
    //   324: aload_0
    //   325: aload 15
    //   327: putfield 78	org/mozilla/javascript/Context:lastInterpreterFrame	Ljava/lang/Object;
    //   330: aload 15
    //   332: getfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   335: istore 11
    //   337: aload 15
    //   339: iload 11
    //   341: iconst_1
    //   342: iadd
    //   343: putfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   346: aload 27
    //   348: iload 11
    //   350: baload
    //   351: istore 12
    //   353: iload 9
    //   355: istore 11
    //   357: iload 12
    //   359: tableswitch	default:+6695->7054, -64:+5764->6123, -63:+1432->1791, -62:+1095->1454, -61:+4340->4699, -60:+901->1260, -59:+2432->2791, -58:+5372->5731, -57:+5305->5664, -56:+2982->3341, -55:+3285->3644, -54:+5609->5968, -53:+5557->5916, -52:+6979->7338, -51:+6957->7316, -50:+7016->7375, -49:+4387->4746, -48:+4434->4793, -47:+6019->6378, -46:+5974->6333, -45:+5927->6286, -44:+7176->7535, -43:+7168->7527, -42:+7160->7519, -41:+7152->7511, -40:+5900->6259, -39:+5873->6232, -38:+5844->6203, -37:+7146->7505, -36:+7140->7499, -35:+7134->7493, -34:+7128->7487, -33:+7122->7481, -32:+7116->7475, -31:+5439->5798, -30:+5238->5597, -29:+5198->5557, -28:+4196->4555, -27:+4152->4511, -26:+5786->6145, -25:+1978->2337, -24:+1910->2269, -23:+1881->2240, -22:+2115->2474, -21:+3248->3607, -20:+5141->5500, -19:+5077->5436, -18:+3191->3550, -17:+3103->3462, -16:+3040->3399, -15:+3001->3360, -14:+4129->4488, -13:+5054->5413, -12:+5028->5387, -11:+2898->3257, -10:+2782->3141, -9:+2680->3039, -8:+4296->4655, -7:+4479->4838, -6:+1776->2135, -5:+2057->2416, -4:+6731->7090, -3:+6840->7199, -2:+6779->7138, -1:+6746->7105, 0:+2485->2844, 1:+901->1260, 2:+4576->4935, 3:+4629->4988, 4:+2092->2451, 5:+1811->2170, 6:+1737->2096, 7:+1698->2057, 8:+2347->2706, 9:+2154->2513, 10:+2154->2513, 11:+2154->2513, 12:+1604->1963, 13:+1604->1963, 14:+1569->1928, 15:+1569->1928, 16:+1569->1928, 17:+1569->1928, 18:+2154->2513, 19:+2154->2513, 20:+2172->2531, 21:+2260->2619, 22:+2279->2638, 23:+2279->2638, 24:+2279->2638, 25:+2279->2638, 26:+2297->2656, 27:+2125->2484, 28:+2225->2584, 29:+2225->2584, 30:+3829->4188, 31:+2485->2844, 32:+4088->4447, 33:+2552->2911, 34:+2504->2863, 35:+2600->2959, 36:+2748->3107, 37:+2765->3124, 38:+3285->3644, 39:+4272->4631, 40:+4240->4599, 41:+6942->7301, 42:+7001->7360, 43:+4504->4863, 44:+4542->4901, 45:+4559->4918, 46:+1651->2010, 47:+1651->2010, 48:+5160->5519, 49:+2323->2682, 50:+1483->1842, 51:+1550->1909, 52:+1587->1946, 53:+1587->1946, 54:+2943->3302, 55:+6670->7029, 56:+6673->7032, 57:+4645->5004, 58:+4752->5111, 59:+4752->5111, 60:+4752->5111, 61:+4752->5111, 62:+4827->5186, 63:+4827->5186, 64:+4523->4882, 65:+1152->1511, 66:+5439->5798, 67:+5439->5798, 68:+2801->3160, 69:+2821->3180, 70:+2878->3237, 71:+3285->3644, 72:+4878->5237, 73:+1243->1602, 74:+2347->2706, 75:+5666->6025, 76:+5708->6067, 77:+5736->6095, 78:+4926->5285, 79:+4943->5302, 80:+4960->5319, 81:+5009->5368, 82:+901->1260, 83:+901->1260, 84:+901->1260, 85:+901->1260, 86:+901->1260, 87:+901->1260, 88:+901->1260, 89:+901->1260, 90:+901->1260, 91:+901->1260, 92:+901->1260, 93:+901->1260, 94:+901->1260, 95:+901->1260, 96:+901->1260, 97:+901->1260, 98:+901->1260, 99:+901->1260, 100:+901->1260, 101:+901->1260, 102:+901->1260, 103:+901->1260, 104:+901->1260, 105:+901->1260, 106:+901->1260, 107:+901->1260, 108:+901->1260, 109:+901->1260, 110:+901->1260, 111:+901->1260, 112:+901->1260, 113:+901->1260, 114:+901->1260, 115:+901->1260, 116:+901->1260, 117:+901->1260, 118:+901->1260, 119:+901->1260, 120:+901->1260, 121:+901->1260, 122:+901->1260, 123:+901->1260, 124:+901->1260, 125:+901->1260, 126:+901->1260, 127:+901->1260, 128:+901->1260, 129:+901->1260, 130:+901->1260, 131:+901->1260, 132:+901->1260, 133:+901->1260, 134:+901->1260, 135:+901->1260, 136:+901->1260, 137:+901->1260, 138:+901->1260, 139:+901->1260, 140:+901->1260, 141:+901->1260, 142:+901->1260, 143:+901->1260, 144:+901->1260, 145:+901->1260, 146:+901->1260, 147:+901->1260, 148:+901->1260, 149:+901->1260, 150:+901->1260, 151:+901->1260, 152:+901->1260, 153:+901->1260, 154:+901->1260, 155:+901->1260, 156:+901->1260, 157:+6676->7035
    //   1260: aload 15
    //   1262: getfield 223	org/mozilla/javascript/Interpreter$CallFrame:idata	Lorg/mozilla/javascript/InterpreterData;
    //   1265: invokestatic 679	org/mozilla/javascript/Interpreter:dumpICode	(Lorg/mozilla/javascript/InterpreterData;)V
    //   1268: new 655	java/lang/RuntimeException
    //   1271: dup
    //   1272: new 681	java/lang/StringBuilder
    //   1275: dup
    //   1276: invokespecial 682	java/lang/StringBuilder:<init>	()V
    //   1279: ldc_w 684
    //   1282: invokevirtual 688	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1285: iload 12
    //   1287: invokevirtual 691	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1290: ldc_w 693
    //   1293: invokevirtual 688	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1296: aload 15
    //   1298: getfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   1301: iconst_1
    //   1302: isub
    //   1303: invokevirtual 691	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1306: invokevirtual 697	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1309: invokespecial 698	java/lang/RuntimeException:<init>	(Ljava/lang/String;)V
    //   1312: athrow
    //   1313: astore 19
    //   1315: aload 18
    //   1317: astore 17
    //   1319: aload 15
    //   1321: astore 16
    //   1323: aload_1
    //   1324: astore 15
    //   1326: aload 19
    //   1328: astore_1
    //   1329: aload 17
    //   1331: ifnull +5166 -> 6497
    //   1334: aload_1
    //   1335: getstatic 407	java/lang/System:err	Ljava/io/PrintStream;
    //   1338: invokevirtual 418	java/lang/Throwable:printStackTrace	(Ljava/io/PrintStream;)V
    //   1341: new 80	java/lang/IllegalStateException
    //   1344: dup
    //   1345: invokespecial 699	java/lang/IllegalStateException:<init>	()V
    //   1348: athrow
    //   1349: iconst_0
    //   1350: istore 13
    //   1352: goto -1332 -> 20
    //   1355: aload_2
    //   1356: instanceof 13
    //   1359: ifne +7 -> 1366
    //   1362: invokestatic 72	org/mozilla/javascript/Kit:codeBug	()Ljava/lang/RuntimeException;
    //   1365: pop
    //   1366: aconst_null
    //   1367: astore 22
    //   1369: goto -1282 -> 87
    //   1372: aload 16
    //   1374: astore 15
    //   1376: aload 17
    //   1378: astore 18
    //   1380: aload 22
    //   1382: ifnonnull -1228 -> 154
    //   1385: aload 16
    //   1387: astore 15
    //   1389: aload 17
    //   1391: astore 18
    //   1393: aload 16
    //   1395: astore 21
    //   1397: aload_1
    //   1398: astore 19
    //   1400: aload 17
    //   1402: astore 20
    //   1404: aload 16
    //   1406: getfield 105	org/mozilla/javascript/Interpreter$CallFrame:frozen	Z
    //   1409: ifeq -1255 -> 154
    //   1412: aload 16
    //   1414: astore 21
    //   1416: aload_1
    //   1417: astore 19
    //   1419: aload 17
    //   1421: astore 20
    //   1423: invokestatic 72	org/mozilla/javascript/Kit:codeBug	()Ljava/lang/RuntimeException;
    //   1426: pop
    //   1427: aload 16
    //   1429: astore 15
    //   1431: aload 17
    //   1433: astore 18
    //   1435: goto -1281 -> 154
    //   1438: astore_1
    //   1439: aload 19
    //   1441: astore 15
    //   1443: aload 21
    //   1445: astore 16
    //   1447: aload 20
    //   1449: astore 17
    //   1451: goto -122 -> 1329
    //   1454: aload 15
    //   1456: getfield 105	org/mozilla/javascript/Interpreter$CallFrame:frozen	Z
    //   1459: ifne +143 -> 1602
    //   1462: aload 15
    //   1464: aload 15
    //   1466: getfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   1469: iconst_1
    //   1470: isub
    //   1471: putfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   1474: aload 15
    //   1476: invokestatic 701	org/mozilla/javascript/Interpreter:captureFrameForGenerator	(Lorg/mozilla/javascript/Interpreter$CallFrame;)Lorg/mozilla/javascript/Interpreter$CallFrame;
    //   1479: astore 16
    //   1481: aload 16
    //   1483: iconst_1
    //   1484: putfield 105	org/mozilla/javascript/Interpreter$CallFrame:frozen	Z
    //   1487: aload 15
    //   1489: new 703	org/mozilla/javascript/NativeGenerator
    //   1492: dup
    //   1493: aload 15
    //   1495: getfield 211	org/mozilla/javascript/Interpreter$CallFrame:scope	Lorg/mozilla/javascript/Scriptable;
    //   1498: aload 16
    //   1500: getfield 557	org/mozilla/javascript/Interpreter$CallFrame:fnOrScript	Lorg/mozilla/javascript/InterpretedFunction;
    //   1503: aload 16
    //   1505: invokespecial 706	org/mozilla/javascript/NativeGenerator:<init>	(Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/NativeFunction;Ljava/lang/Object;)V
    //   1508: putfield 397	org/mozilla/javascript/Interpreter$CallFrame:result	Ljava/lang/Object;
    //   1511: aload_0
    //   1512: aload 15
    //   1514: aconst_null
    //   1515: invokestatic 591	org/mozilla/javascript/Interpreter:exitFrame	(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Interpreter$CallFrame;Ljava/lang/Object;)V
    //   1518: aload 15
    //   1520: getfield 397	org/mozilla/javascript/Interpreter$CallFrame:result	Ljava/lang/Object;
    //   1523: astore 16
    //   1525: aload 15
    //   1527: getfield 401	org/mozilla/javascript/Interpreter$CallFrame:resultDbl	D
    //   1530: dstore 5
    //   1532: aload 15
    //   1534: getfield 123	org/mozilla/javascript/Interpreter$CallFrame:parentFrame	Lorg/mozilla/javascript/Interpreter$CallFrame;
    //   1537: ifnull +4910 -> 6447
    //   1540: aload 15
    //   1542: getfield 123	org/mozilla/javascript/Interpreter$CallFrame:parentFrame	Lorg/mozilla/javascript/Interpreter$CallFrame;
    //   1545: astore 17
    //   1547: aload 17
    //   1549: astore_2
    //   1550: aload 17
    //   1552: astore 15
    //   1554: aload 17
    //   1556: getfield 105	org/mozilla/javascript/Interpreter$CallFrame:frozen	Z
    //   1559: ifeq +13 -> 1572
    //   1562: aload 17
    //   1564: astore 15
    //   1566: aload 17
    //   1568: invokevirtual 138	org/mozilla/javascript/Interpreter$CallFrame:cloneFrozen	()Lorg/mozilla/javascript/Interpreter$CallFrame;
    //   1571: astore_2
    //   1572: aload_2
    //   1573: astore 15
    //   1575: aload_2
    //   1576: aload 16
    //   1578: dload 5
    //   1580: invokestatic 710	org/mozilla/javascript/Interpreter:setCallResult	(Lorg/mozilla/javascript/Interpreter$CallFrame;Ljava/lang/Object;D)V
    //   1583: aconst_null
    //   1584: astore 15
    //   1586: dload 5
    //   1588: dstore_3
    //   1589: aload_2
    //   1590: astore 16
    //   1592: aload 18
    //   1594: astore 17
    //   1596: aload 15
    //   1598: astore_2
    //   1599: goto -1492 -> 107
    //   1602: aload 15
    //   1604: getfield 105	org/mozilla/javascript/Interpreter$CallFrame:frozen	Z
    //   1607: ifne +14 -> 1621
    //   1610: aload_0
    //   1611: aload 15
    //   1613: iload 9
    //   1615: aload 22
    //   1617: invokestatic 712	org/mozilla/javascript/Interpreter:freezeGenerator	(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Interpreter$CallFrame;ILorg/mozilla/javascript/Interpreter$GeneratorState;)Ljava/lang/Object;
    //   1620: areturn
    //   1621: aload 15
    //   1623: iload 9
    //   1625: aload 22
    //   1627: iload 12
    //   1629: invokestatic 716	org/mozilla/javascript/Interpreter:thawGenerator	(Lorg/mozilla/javascript/Interpreter$CallFrame;ILorg/mozilla/javascript/Interpreter$GeneratorState;I)Ljava/lang/Object;
    //   1632: astore 16
    //   1634: getstatic 719	org/mozilla/javascript/Scriptable:NOT_FOUND	Ljava/lang/Object;
    //   1637: astore 17
    //   1639: aload 16
    //   1641: aload 17
    //   1643: if_acmpeq -1313 -> 330
    //   1646: aload_1
    //   1647: astore 17
    //   1649: aload 16
    //   1651: ifnonnull +7 -> 1658
    //   1654: invokestatic 72	org/mozilla/javascript/Kit:codeBug	()Ljava/lang/RuntimeException;
    //   1657: pop
    //   1658: aload 22
    //   1660: ifnull +4851 -> 6511
    //   1663: aload 22
    //   1665: getfield 425	org/mozilla/javascript/Interpreter$GeneratorState:operation	I
    //   1668: iconst_2
    //   1669: if_icmpne +4842 -> 6511
    //   1672: aload 16
    //   1674: aload 22
    //   1676: getfield 722	org/mozilla/javascript/Interpreter$GeneratorState:value	Ljava/lang/Object;
    //   1679: if_acmpne +4832 -> 6511
    //   1682: iconst_1
    //   1683: istore 9
    //   1685: aconst_null
    //   1686: astore_1
    //   1687: iload 13
    //   1689: ifeq +5362 -> 7051
    //   1692: aload_0
    //   1693: aload 15
    //   1695: bipush 100
    //   1697: invokestatic 724	org/mozilla/javascript/Interpreter:addInstructionCount	(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Interpreter$CallFrame;I)V
    //   1700: aload 15
    //   1702: getfield 366	org/mozilla/javascript/Interpreter$CallFrame:debuggerFrame	Lorg/mozilla/javascript/debug/DebugFrame;
    //   1705: ifnull +5333 -> 7038
    //   1708: aload 16
    //   1710: instanceof 655
    //   1713: ifeq +5325 -> 7038
    //   1716: aload 16
    //   1718: checkcast 655	java/lang/RuntimeException
    //   1721: astore 18
    //   1723: aload 15
    //   1725: getfield 366	org/mozilla/javascript/Interpreter$CallFrame:debuggerFrame	Lorg/mozilla/javascript/debug/DebugFrame;
    //   1728: aload_0
    //   1729: aload 18
    //   1731: invokeinterface 728 3 0
    //   1736: aload 15
    //   1738: astore 18
    //   1740: aload_1
    //   1741: astore 19
    //   1743: aload 16
    //   1745: astore_1
    //   1746: iload 9
    //   1748: ifeq +4970 -> 6718
    //   1751: iload 9
    //   1753: iconst_2
    //   1754: if_icmpeq +4958 -> 6712
    //   1757: iconst_1
    //   1758: istore 14
    //   1760: aload 18
    //   1762: iload 14
    //   1764: invokestatic 730	org/mozilla/javascript/Interpreter:getExceptionHandler	(Lorg/mozilla/javascript/Interpreter$CallFrame;Z)I
    //   1767: istore 10
    //   1769: iload 10
    //   1771: iflt +4947 -> 6718
    //   1774: aload_1
    //   1775: astore 15
    //   1777: aload 18
    //   1779: astore 16
    //   1781: aload 17
    //   1783: astore_1
    //   1784: aload 15
    //   1786: astore 17
    //   1788: goto -1681 -> 107
    //   1791: aload 15
    //   1793: iconst_1
    //   1794: putfield 105	org/mozilla/javascript/Interpreter$CallFrame:frozen	Z
    //   1797: aload 27
    //   1799: aload 15
    //   1801: getfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   1804: invokestatic 203	org/mozilla/javascript/Interpreter:getIndex	([BI)I
    //   1807: istore 9
    //   1809: aload 22
    //   1811: new 732	org/mozilla/javascript/JavaScriptException
    //   1814: dup
    //   1815: aload 15
    //   1817: getfield 211	org/mozilla/javascript/Interpreter$CallFrame:scope	Lorg/mozilla/javascript/Scriptable;
    //   1820: invokestatic 738	org/mozilla/javascript/NativeIterator:getStopIterationObject	(Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;
    //   1823: aload 15
    //   1825: getfield 223	org/mozilla/javascript/Interpreter$CallFrame:idata	Lorg/mozilla/javascript/InterpreterData;
    //   1828: getfield 229	org/mozilla/javascript/InterpreterData:itsSourceFile	Ljava/lang/String;
    //   1831: iload 9
    //   1833: invokespecial 741	org/mozilla/javascript/JavaScriptException:<init>	(Ljava/lang/Object;Ljava/lang/String;I)V
    //   1836: putfield 745	org/mozilla/javascript/Interpreter$GeneratorState:returnedException	Ljava/lang/RuntimeException;
    //   1839: goto -328 -> 1511
    //   1842: aload 26
    //   1844: iload 9
    //   1846: aaload
    //   1847: astore 17
    //   1849: aload 17
    //   1851: astore 16
    //   1853: aload 17
    //   1855: aload 23
    //   1857: if_acmpne +13 -> 1870
    //   1860: aload 25
    //   1862: iload 9
    //   1864: daload
    //   1865: invokestatic 155	org/mozilla/javascript/ScriptRuntime:wrapNumber	(D)Ljava/lang/Number;
    //   1868: astore 16
    //   1870: aload 27
    //   1872: aload 15
    //   1874: getfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   1877: invokestatic 203	org/mozilla/javascript/Interpreter:getIndex	([BI)I
    //   1880: istore 9
    //   1882: new 732	org/mozilla/javascript/JavaScriptException
    //   1885: dup
    //   1886: aload 16
    //   1888: aload 15
    //   1890: getfield 223	org/mozilla/javascript/Interpreter$CallFrame:idata	Lorg/mozilla/javascript/InterpreterData;
    //   1893: getfield 229	org/mozilla/javascript/InterpreterData:itsSourceFile	Ljava/lang/String;
    //   1896: iload 9
    //   1898: invokespecial 741	org/mozilla/javascript/JavaScriptException:<init>	(Ljava/lang/Object;Ljava/lang/String;I)V
    //   1901: astore 16
    //   1903: aload_1
    //   1904: astore 17
    //   1906: goto -257 -> 1649
    //   1909: aload 26
    //   1911: aload 15
    //   1913: getfield 563	org/mozilla/javascript/Interpreter$CallFrame:localShift	I
    //   1916: iload 10
    //   1918: iadd
    //   1919: aaload
    //   1920: astore 16
    //   1922: aload_1
    //   1923: astore 17
    //   1925: goto -276 -> 1649
    //   1928: aload 15
    //   1930: iload 12
    //   1932: aload 26
    //   1934: aload 25
    //   1936: iload 9
    //   1938: invokestatic 747	org/mozilla/javascript/Interpreter:doCompare	(Lorg/mozilla/javascript/Interpreter$CallFrame;I[Ljava/lang/Object;[DI)I
    //   1941: istore 9
    //   1943: goto -1613 -> 330
    //   1946: aload_0
    //   1947: iload 12
    //   1949: aload 26
    //   1951: aload 25
    //   1953: iload 9
    //   1955: invokestatic 749	org/mozilla/javascript/Interpreter:doInOrInstanceof	(Lorg/mozilla/javascript/Context;I[Ljava/lang/Object;[DI)I
    //   1958: istore 9
    //   1960: goto -1630 -> 330
    //   1963: iload 9
    //   1965: iconst_1
    //   1966: isub
    //   1967: istore 11
    //   1969: aload 26
    //   1971: aload 25
    //   1973: iload 11
    //   1975: invokestatic 751	org/mozilla/javascript/Interpreter:doEquals	([Ljava/lang/Object;[DI)Z
    //   1978: istore 14
    //   1980: iload 12
    //   1982: bipush 13
    //   1984: if_icmpne +5075 -> 7059
    //   1987: iconst_1
    //   1988: istore 9
    //   1990: aload 26
    //   1992: iload 11
    //   1994: iload 9
    //   1996: iload 14
    //   1998: ixor
    //   1999: invokestatic 238	org/mozilla/javascript/ScriptRuntime:wrapBoolean	(Z)Ljava/lang/Boolean;
    //   2002: aastore
    //   2003: iload 11
    //   2005: istore 9
    //   2007: goto -1677 -> 330
    //   2010: iload 9
    //   2012: iconst_1
    //   2013: isub
    //   2014: istore 11
    //   2016: aload 26
    //   2018: aload 25
    //   2020: iload 11
    //   2022: invokestatic 753	org/mozilla/javascript/Interpreter:doShallowEquals	([Ljava/lang/Object;[DI)Z
    //   2025: istore 14
    //   2027: iload 12
    //   2029: bipush 47
    //   2031: if_icmpne +5034 -> 7065
    //   2034: iconst_1
    //   2035: istore 9
    //   2037: aload 26
    //   2039: iload 11
    //   2041: iload 9
    //   2043: iload 14
    //   2045: ixor
    //   2046: invokestatic 238	org/mozilla/javascript/ScriptRuntime:wrapBoolean	(Z)Ljava/lang/Boolean;
    //   2049: aastore
    //   2050: iload 11
    //   2052: istore 9
    //   2054: goto -1724 -> 330
    //   2057: iload 9
    //   2059: iconst_1
    //   2060: isub
    //   2061: istore 12
    //   2063: iload 12
    //   2065: istore 11
    //   2067: aload 15
    //   2069: iload 9
    //   2071: invokestatic 757	org/mozilla/javascript/Interpreter:stack_boolean	(Lorg/mozilla/javascript/Interpreter$CallFrame;I)Z
    //   2074: ifeq +4952 -> 7026
    //   2077: aload 15
    //   2079: aload 15
    //   2081: getfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   2084: iconst_2
    //   2085: iadd
    //   2086: putfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   2089: iload 12
    //   2091: istore 9
    //   2093: goto -1763 -> 330
    //   2096: iload 9
    //   2098: iconst_1
    //   2099: isub
    //   2100: istore 12
    //   2102: iload 12
    //   2104: istore 11
    //   2106: aload 15
    //   2108: iload 9
    //   2110: invokestatic 757	org/mozilla/javascript/Interpreter:stack_boolean	(Lorg/mozilla/javascript/Interpreter$CallFrame;I)Z
    //   2113: ifne +4913 -> 7026
    //   2116: aload 15
    //   2118: aload 15
    //   2120: getfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   2123: iconst_2
    //   2124: iadd
    //   2125: putfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   2128: iload 12
    //   2130: istore 9
    //   2132: goto -1802 -> 330
    //   2135: iload 9
    //   2137: iconst_1
    //   2138: isub
    //   2139: istore 11
    //   2141: aload 15
    //   2143: iload 9
    //   2145: invokestatic 757	org/mozilla/javascript/Interpreter:stack_boolean	(Lorg/mozilla/javascript/Interpreter$CallFrame;I)Z
    //   2148: ifne +4923 -> 7071
    //   2151: aload 15
    //   2153: aload 15
    //   2155: getfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   2158: iconst_2
    //   2159: iadd
    //   2160: putfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   2163: iload 11
    //   2165: istore 9
    //   2167: goto -1837 -> 330
    //   2170: iload 13
    //   2172: ifeq +10 -> 2182
    //   2175: aload_0
    //   2176: aload 15
    //   2178: iconst_2
    //   2179: invokestatic 724	org/mozilla/javascript/Interpreter:addInstructionCount	(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Interpreter$CallFrame;I)V
    //   2182: aload 27
    //   2184: aload 15
    //   2186: getfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   2189: invokestatic 759	org/mozilla/javascript/Interpreter:getShort	([BI)I
    //   2192: istore 9
    //   2194: iload 9
    //   2196: ifeq +4227 -> 6423
    //   2199: aload 15
    //   2201: iload 9
    //   2203: iconst_1
    //   2204: isub
    //   2205: aload 15
    //   2207: getfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   2210: iadd
    //   2211: putfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   2214: iload 11
    //   2216: istore 9
    //   2218: iload 13
    //   2220: ifeq -1890 -> 330
    //   2223: aload 15
    //   2225: aload 15
    //   2227: getfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   2230: putfield 53	org/mozilla/javascript/Interpreter$CallFrame:pcPrevBranch	I
    //   2233: iload 11
    //   2235: istore 9
    //   2237: goto -1907 -> 330
    //   2240: iload 9
    //   2242: iconst_1
    //   2243: iadd
    //   2244: istore 11
    //   2246: aload 26
    //   2248: iload 11
    //   2250: aload 23
    //   2252: aastore
    //   2253: aload 25
    //   2255: iload 11
    //   2257: aload 15
    //   2259: getfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   2262: iconst_2
    //   2263: iadd
    //   2264: i2d
    //   2265: dastore
    //   2266: goto -96 -> 2170
    //   2269: iload 9
    //   2271: aload 15
    //   2273: getfield 566	org/mozilla/javascript/Interpreter$CallFrame:emptyStackTop	I
    //   2276: iconst_1
    //   2277: iadd
    //   2278: if_icmpne +42 -> 2320
    //   2281: iload 10
    //   2283: aload 15
    //   2285: getfield 563	org/mozilla/javascript/Interpreter$CallFrame:localShift	I
    //   2288: iadd
    //   2289: istore 10
    //   2291: aload 26
    //   2293: iload 10
    //   2295: aload 26
    //   2297: iload 9
    //   2299: aaload
    //   2300: aastore
    //   2301: aload 25
    //   2303: iload 10
    //   2305: aload 25
    //   2307: iload 9
    //   2309: daload
    //   2310: dastore
    //   2311: iload 9
    //   2313: iconst_1
    //   2314: isub
    //   2315: istore 9
    //   2317: goto -1987 -> 330
    //   2320: iload 9
    //   2322: aload 15
    //   2324: getfield 566	org/mozilla/javascript/Interpreter$CallFrame:emptyStackTop	I
    //   2327: if_icmpeq -1997 -> 330
    //   2330: invokestatic 72	org/mozilla/javascript/Kit:codeBug	()Ljava/lang/RuntimeException;
    //   2333: pop
    //   2334: goto -2004 -> 330
    //   2337: iload 13
    //   2339: ifeq +10 -> 2349
    //   2342: aload_0
    //   2343: aload 15
    //   2345: iconst_0
    //   2346: invokestatic 724	org/mozilla/javascript/Interpreter:addInstructionCount	(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Interpreter$CallFrame;I)V
    //   2349: iload 10
    //   2351: aload 15
    //   2353: getfield 563	org/mozilla/javascript/Interpreter$CallFrame:localShift	I
    //   2356: iadd
    //   2357: istore 11
    //   2359: aload 26
    //   2361: iload 11
    //   2363: aaload
    //   2364: astore 16
    //   2366: aload 16
    //   2368: aload 23
    //   2370: if_acmpeq +9 -> 2379
    //   2373: aload_1
    //   2374: astore 17
    //   2376: goto -727 -> 1649
    //   2379: aload 15
    //   2381: aload 25
    //   2383: iload 11
    //   2385: daload
    //   2386: d2i
    //   2387: putfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   2390: iload 11
    //   2392: istore 10
    //   2394: iload 13
    //   2396: ifeq -2066 -> 330
    //   2399: aload 15
    //   2401: aload 15
    //   2403: getfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   2406: putfield 53	org/mozilla/javascript/Interpreter$CallFrame:pcPrevBranch	I
    //   2409: iload 11
    //   2411: istore 10
    //   2413: goto -2083 -> 330
    //   2416: aload 15
    //   2418: aload 26
    //   2420: iload 9
    //   2422: aaload
    //   2423: putfield 397	org/mozilla/javascript/Interpreter$CallFrame:result	Ljava/lang/Object;
    //   2426: aload 15
    //   2428: aload 25
    //   2430: iload 9
    //   2432: daload
    //   2433: putfield 401	org/mozilla/javascript/Interpreter$CallFrame:resultDbl	D
    //   2436: aload 26
    //   2438: iload 9
    //   2440: aconst_null
    //   2441: aastore
    //   2442: iload 9
    //   2444: iconst_1
    //   2445: isub
    //   2446: istore 9
    //   2448: goto -2118 -> 330
    //   2451: aload 15
    //   2453: aload 26
    //   2455: iload 9
    //   2457: aaload
    //   2458: putfield 397	org/mozilla/javascript/Interpreter$CallFrame:result	Ljava/lang/Object;
    //   2461: aload 15
    //   2463: aload 25
    //   2465: iload 9
    //   2467: daload
    //   2468: putfield 401	org/mozilla/javascript/Interpreter$CallFrame:resultDbl	D
    //   2471: goto -960 -> 1511
    //   2474: aload 15
    //   2476: aload 24
    //   2478: putfield 397	org/mozilla/javascript/Interpreter$CallFrame:result	Ljava/lang/Object;
    //   2481: goto -970 -> 1511
    //   2484: aload 15
    //   2486: iload 9
    //   2488: invokestatic 197	org/mozilla/javascript/Interpreter:stack_int32	(Lorg/mozilla/javascript/Interpreter$CallFrame;I)I
    //   2491: istore 11
    //   2493: aload 26
    //   2495: iload 9
    //   2497: aload 23
    //   2499: aastore
    //   2500: aload 25
    //   2502: iload 9
    //   2504: iload 11
    //   2506: iconst_m1
    //   2507: ixor
    //   2508: i2d
    //   2509: dastore
    //   2510: goto -2180 -> 330
    //   2513: aload 15
    //   2515: iload 12
    //   2517: aload 26
    //   2519: aload 25
    //   2521: iload 9
    //   2523: invokestatic 761	org/mozilla/javascript/Interpreter:doBitOp	(Lorg/mozilla/javascript/Interpreter$CallFrame;I[Ljava/lang/Object;[DI)I
    //   2526: istore 9
    //   2528: goto -2198 -> 330
    //   2531: aload 15
    //   2533: iload 9
    //   2535: iconst_1
    //   2536: isub
    //   2537: invokestatic 192	org/mozilla/javascript/Interpreter:stack_double	(Lorg/mozilla/javascript/Interpreter$CallFrame;I)D
    //   2540: dstore 5
    //   2542: aload 15
    //   2544: iload 9
    //   2546: invokestatic 197	org/mozilla/javascript/Interpreter:stack_int32	(Lorg/mozilla/javascript/Interpreter$CallFrame;I)I
    //   2549: istore 11
    //   2551: iload 9
    //   2553: iconst_1
    //   2554: isub
    //   2555: istore 9
    //   2557: aload 26
    //   2559: iload 9
    //   2561: aload 23
    //   2563: aastore
    //   2564: aload 25
    //   2566: iload 9
    //   2568: dload 5
    //   2570: invokestatic 765	org/mozilla/javascript/ScriptRuntime:toUint32	(D)J
    //   2573: iload 11
    //   2575: bipush 31
    //   2577: iand
    //   2578: lushr
    //   2579: l2d
    //   2580: dastore
    //   2581: goto -2251 -> 330
    //   2584: aload 15
    //   2586: iload 9
    //   2588: invokestatic 192	org/mozilla/javascript/Interpreter:stack_double	(Lorg/mozilla/javascript/Interpreter$CallFrame;I)D
    //   2591: dstore 7
    //   2593: aload 26
    //   2595: iload 9
    //   2597: aload 23
    //   2599: aastore
    //   2600: dload 7
    //   2602: dstore 5
    //   2604: iload 12
    //   2606: bipush 29
    //   2608: if_icmpne +4650 -> 7258
    //   2611: dload 7
    //   2613: dneg
    //   2614: dstore 5
    //   2616: goto +4642 -> 7258
    //   2619: iload 9
    //   2621: iconst_1
    //   2622: isub
    //   2623: istore 9
    //   2625: aload 26
    //   2627: aload 25
    //   2629: iload 9
    //   2631: aload_0
    //   2632: invokestatic 767	org/mozilla/javascript/Interpreter:doAdd	([Ljava/lang/Object;[DILorg/mozilla/javascript/Context;)V
    //   2635: goto -2305 -> 330
    //   2638: aload 15
    //   2640: iload 12
    //   2642: aload 26
    //   2644: aload 25
    //   2646: iload 9
    //   2648: invokestatic 769	org/mozilla/javascript/Interpreter:doArithmetic	(Lorg/mozilla/javascript/Interpreter$CallFrame;I[Ljava/lang/Object;[DI)I
    //   2651: istore 9
    //   2653: goto -2323 -> 330
    //   2656: aload 15
    //   2658: iload 9
    //   2660: invokestatic 757	org/mozilla/javascript/Interpreter:stack_boolean	(Lorg/mozilla/javascript/Interpreter$CallFrame;I)Z
    //   2663: ifne +4605 -> 7268
    //   2666: iconst_1
    //   2667: istore 14
    //   2669: aload 26
    //   2671: iload 9
    //   2673: iload 14
    //   2675: invokestatic 238	org/mozilla/javascript/ScriptRuntime:wrapBoolean	(Z)Ljava/lang/Boolean;
    //   2678: aastore
    //   2679: goto -2349 -> 330
    //   2682: iload 9
    //   2684: iconst_1
    //   2685: iadd
    //   2686: istore 9
    //   2688: aload 26
    //   2690: iload 9
    //   2692: aload_0
    //   2693: aload 15
    //   2695: getfield 211	org/mozilla/javascript/Interpreter$CallFrame:scope	Lorg/mozilla/javascript/Scriptable;
    //   2698: aload_1
    //   2699: invokestatic 773	org/mozilla/javascript/ScriptRuntime:bind	(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Ljava/lang/String;)Lorg/mozilla/javascript/Scriptable;
    //   2702: aastore
    //   2703: goto -2373 -> 330
    //   2706: aload 26
    //   2708: iload 9
    //   2710: aaload
    //   2711: astore 16
    //   2713: aload 16
    //   2715: aload 23
    //   2717: if_acmpne +4306 -> 7023
    //   2720: aload 25
    //   2722: iload 9
    //   2724: daload
    //   2725: invokestatic 155	org/mozilla/javascript/ScriptRuntime:wrapNumber	(D)Ljava/lang/Number;
    //   2728: astore 16
    //   2730: iload 9
    //   2732: iconst_1
    //   2733: isub
    //   2734: istore 9
    //   2736: aload 26
    //   2738: iload 9
    //   2740: aaload
    //   2741: checkcast 151	org/mozilla/javascript/Scriptable
    //   2744: astore 17
    //   2746: iload 12
    //   2748: bipush 8
    //   2750: if_icmpne +22 -> 2772
    //   2753: aload 17
    //   2755: aload 16
    //   2757: aload_0
    //   2758: aload 15
    //   2760: getfield 211	org/mozilla/javascript/Interpreter$CallFrame:scope	Lorg/mozilla/javascript/Scriptable;
    //   2763: aload_1
    //   2764: invokestatic 777	org/mozilla/javascript/ScriptRuntime:setName	(Lorg/mozilla/javascript/Scriptable;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Ljava/lang/String;)Ljava/lang/Object;
    //   2767: astore 16
    //   2769: goto +4505 -> 7274
    //   2772: aload 17
    //   2774: aload 16
    //   2776: aload_0
    //   2777: aload 15
    //   2779: getfield 211	org/mozilla/javascript/Interpreter$CallFrame:scope	Lorg/mozilla/javascript/Scriptable;
    //   2782: aload_1
    //   2783: invokestatic 780	org/mozilla/javascript/ScriptRuntime:strictSetName	(Lorg/mozilla/javascript/Scriptable;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Ljava/lang/String;)Ljava/lang/Object;
    //   2786: astore 16
    //   2788: goto +4486 -> 7274
    //   2791: aload 26
    //   2793: iload 9
    //   2795: aaload
    //   2796: astore 16
    //   2798: aload 16
    //   2800: aload 23
    //   2802: if_acmpne +4218 -> 7020
    //   2805: aload 25
    //   2807: iload 9
    //   2809: daload
    //   2810: invokestatic 155	org/mozilla/javascript/ScriptRuntime:wrapNumber	(D)Ljava/lang/Number;
    //   2813: astore 16
    //   2815: iload 9
    //   2817: iconst_1
    //   2818: isub
    //   2819: istore 9
    //   2821: aload 26
    //   2823: iload 9
    //   2825: aload 26
    //   2827: iload 9
    //   2829: aaload
    //   2830: checkcast 151	org/mozilla/javascript/Scriptable
    //   2833: aload 16
    //   2835: aload_0
    //   2836: aload_1
    //   2837: invokestatic 784	org/mozilla/javascript/ScriptRuntime:setConst	(Lorg/mozilla/javascript/Scriptable;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Ljava/lang/String;)Ljava/lang/Object;
    //   2840: aastore
    //   2841: goto -2511 -> 330
    //   2844: aload_0
    //   2845: aload 15
    //   2847: iload 12
    //   2849: aload 26
    //   2851: aload 25
    //   2853: iload 9
    //   2855: invokestatic 786	org/mozilla/javascript/Interpreter:doDelName	(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Interpreter$CallFrame;I[Ljava/lang/Object;[DI)I
    //   2858: istore 9
    //   2860: goto -2530 -> 330
    //   2863: aload 26
    //   2865: iload 9
    //   2867: aaload
    //   2868: astore 17
    //   2870: aload 17
    //   2872: astore 16
    //   2874: aload 17
    //   2876: aload 23
    //   2878: if_acmpne +13 -> 2891
    //   2881: aload 25
    //   2883: iload 9
    //   2885: daload
    //   2886: invokestatic 155	org/mozilla/javascript/ScriptRuntime:wrapNumber	(D)Ljava/lang/Number;
    //   2889: astore 16
    //   2891: aload 26
    //   2893: iload 9
    //   2895: aload 16
    //   2897: aload_1
    //   2898: aload_0
    //   2899: aload 15
    //   2901: getfield 211	org/mozilla/javascript/Interpreter$CallFrame:scope	Lorg/mozilla/javascript/Scriptable;
    //   2904: invokestatic 790	org/mozilla/javascript/ScriptRuntime:getObjectPropNoWarn	(Ljava/lang/Object;Ljava/lang/String;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;
    //   2907: aastore
    //   2908: goto -2578 -> 330
    //   2911: aload 26
    //   2913: iload 9
    //   2915: aaload
    //   2916: astore 17
    //   2918: aload 17
    //   2920: astore 16
    //   2922: aload 17
    //   2924: aload 23
    //   2926: if_acmpne +13 -> 2939
    //   2929: aload 25
    //   2931: iload 9
    //   2933: daload
    //   2934: invokestatic 155	org/mozilla/javascript/ScriptRuntime:wrapNumber	(D)Ljava/lang/Number;
    //   2937: astore 16
    //   2939: aload 26
    //   2941: iload 9
    //   2943: aload 16
    //   2945: aload_1
    //   2946: aload_0
    //   2947: aload 15
    //   2949: getfield 211	org/mozilla/javascript/Interpreter$CallFrame:scope	Lorg/mozilla/javascript/Scriptable;
    //   2952: invokestatic 793	org/mozilla/javascript/ScriptRuntime:getObjectProp	(Ljava/lang/Object;Ljava/lang/String;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;
    //   2955: aastore
    //   2956: goto -2626 -> 330
    //   2959: aload 26
    //   2961: iload 9
    //   2963: aaload
    //   2964: astore 16
    //   2966: aload 16
    //   2968: aload 23
    //   2970: if_acmpne +4047 -> 7017
    //   2973: aload 25
    //   2975: iload 9
    //   2977: daload
    //   2978: invokestatic 155	org/mozilla/javascript/ScriptRuntime:wrapNumber	(D)Ljava/lang/Number;
    //   2981: astore 16
    //   2983: iload 9
    //   2985: iconst_1
    //   2986: isub
    //   2987: istore 9
    //   2989: aload 26
    //   2991: iload 9
    //   2993: aaload
    //   2994: astore 19
    //   2996: aload 19
    //   2998: astore 17
    //   3000: aload 19
    //   3002: aload 23
    //   3004: if_acmpne +13 -> 3017
    //   3007: aload 25
    //   3009: iload 9
    //   3011: daload
    //   3012: invokestatic 155	org/mozilla/javascript/ScriptRuntime:wrapNumber	(D)Ljava/lang/Number;
    //   3015: astore 17
    //   3017: aload 26
    //   3019: iload 9
    //   3021: aload 17
    //   3023: aload_1
    //   3024: aload 16
    //   3026: aload_0
    //   3027: aload 15
    //   3029: getfield 211	org/mozilla/javascript/Interpreter$CallFrame:scope	Lorg/mozilla/javascript/Scriptable;
    //   3032: invokestatic 797	org/mozilla/javascript/ScriptRuntime:setObjectProp	(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;
    //   3035: aastore
    //   3036: goto -2706 -> 330
    //   3039: aload 26
    //   3041: iload 9
    //   3043: aaload
    //   3044: astore 17
    //   3046: aload 17
    //   3048: astore 16
    //   3050: aload 17
    //   3052: aload 23
    //   3054: if_acmpne +13 -> 3067
    //   3057: aload 25
    //   3059: iload 9
    //   3061: daload
    //   3062: invokestatic 155	org/mozilla/javascript/ScriptRuntime:wrapNumber	(D)Ljava/lang/Number;
    //   3065: astore 16
    //   3067: aload 26
    //   3069: iload 9
    //   3071: aload 16
    //   3073: aload_1
    //   3074: aload_0
    //   3075: aload 15
    //   3077: getfield 211	org/mozilla/javascript/Interpreter$CallFrame:scope	Lorg/mozilla/javascript/Scriptable;
    //   3080: aload 27
    //   3082: aload 15
    //   3084: getfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   3087: baload
    //   3088: invokestatic 801	org/mozilla/javascript/ScriptRuntime:propIncrDecr	(Ljava/lang/Object;Ljava/lang/String;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;I)Ljava/lang/Object;
    //   3091: aastore
    //   3092: aload 15
    //   3094: aload 15
    //   3096: getfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   3099: iconst_1
    //   3100: iadd
    //   3101: putfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   3104: goto -2774 -> 330
    //   3107: aload_0
    //   3108: aload 15
    //   3110: aload 26
    //   3112: aload 25
    //   3114: iload 9
    //   3116: invokestatic 803	org/mozilla/javascript/Interpreter:doGetElem	(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Interpreter$CallFrame;[Ljava/lang/Object;[DI)I
    //   3119: istore 9
    //   3121: goto -2791 -> 330
    //   3124: aload_0
    //   3125: aload 15
    //   3127: aload 26
    //   3129: aload 25
    //   3131: iload 9
    //   3133: invokestatic 805	org/mozilla/javascript/Interpreter:doSetElem	(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Interpreter$CallFrame;[Ljava/lang/Object;[DI)I
    //   3136: istore 9
    //   3138: goto -2808 -> 330
    //   3141: aload_0
    //   3142: aload 15
    //   3144: aload 27
    //   3146: aload 26
    //   3148: aload 25
    //   3150: iload 9
    //   3152: invokestatic 807	org/mozilla/javascript/Interpreter:doElemIncDec	(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Interpreter$CallFrame;[B[Ljava/lang/Object;[DI)I
    //   3155: istore 9
    //   3157: goto -2827 -> 330
    //   3160: aload 26
    //   3162: iload 9
    //   3164: aload 26
    //   3166: iload 9
    //   3168: aaload
    //   3169: checkcast 809	org/mozilla/javascript/Ref
    //   3172: aload_0
    //   3173: invokestatic 813	org/mozilla/javascript/ScriptRuntime:refGet	(Lorg/mozilla/javascript/Ref;Lorg/mozilla/javascript/Context;)Ljava/lang/Object;
    //   3176: aastore
    //   3177: goto -2847 -> 330
    //   3180: aload 26
    //   3182: iload 9
    //   3184: aaload
    //   3185: astore 16
    //   3187: aload 16
    //   3189: aload 23
    //   3191: if_acmpne +3823 -> 7014
    //   3194: aload 25
    //   3196: iload 9
    //   3198: daload
    //   3199: invokestatic 155	org/mozilla/javascript/ScriptRuntime:wrapNumber	(D)Ljava/lang/Number;
    //   3202: astore 16
    //   3204: iload 9
    //   3206: iconst_1
    //   3207: isub
    //   3208: istore 9
    //   3210: aload 26
    //   3212: iload 9
    //   3214: aload 26
    //   3216: iload 9
    //   3218: aaload
    //   3219: checkcast 809	org/mozilla/javascript/Ref
    //   3222: aload 16
    //   3224: aload_0
    //   3225: aload 15
    //   3227: getfield 211	org/mozilla/javascript/Interpreter$CallFrame:scope	Lorg/mozilla/javascript/Scriptable;
    //   3230: invokestatic 817	org/mozilla/javascript/ScriptRuntime:refSet	(Lorg/mozilla/javascript/Ref;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;
    //   3233: aastore
    //   3234: goto -2904 -> 330
    //   3237: aload 26
    //   3239: iload 9
    //   3241: aload 26
    //   3243: iload 9
    //   3245: aaload
    //   3246: checkcast 809	org/mozilla/javascript/Ref
    //   3249: aload_0
    //   3250: invokestatic 820	org/mozilla/javascript/ScriptRuntime:refDel	(Lorg/mozilla/javascript/Ref;Lorg/mozilla/javascript/Context;)Ljava/lang/Object;
    //   3253: aastore
    //   3254: goto -2924 -> 330
    //   3257: aload 26
    //   3259: iload 9
    //   3261: aload 26
    //   3263: iload 9
    //   3265: aaload
    //   3266: checkcast 809	org/mozilla/javascript/Ref
    //   3269: aload_0
    //   3270: aload 15
    //   3272: getfield 211	org/mozilla/javascript/Interpreter$CallFrame:scope	Lorg/mozilla/javascript/Scriptable;
    //   3275: aload 27
    //   3277: aload 15
    //   3279: getfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   3282: baload
    //   3283: invokestatic 824	org/mozilla/javascript/ScriptRuntime:refIncrDecr	(Lorg/mozilla/javascript/Ref;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;I)Ljava/lang/Object;
    //   3286: aastore
    //   3287: aload 15
    //   3289: aload 15
    //   3291: getfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   3294: iconst_1
    //   3295: iadd
    //   3296: putfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   3299: goto -2969 -> 330
    //   3302: iload 9
    //   3304: iconst_1
    //   3305: iadd
    //   3306: istore 9
    //   3308: iload 10
    //   3310: aload 15
    //   3312: getfield 563	org/mozilla/javascript/Interpreter$CallFrame:localShift	I
    //   3315: iadd
    //   3316: istore 10
    //   3318: aload 26
    //   3320: iload 9
    //   3322: aload 26
    //   3324: iload 10
    //   3326: aaload
    //   3327: aastore
    //   3328: aload 25
    //   3330: iload 9
    //   3332: aload 25
    //   3334: iload 10
    //   3336: daload
    //   3337: dastore
    //   3338: goto -3008 -> 330
    //   3341: iload 10
    //   3343: aload 15
    //   3345: getfield 563	org/mozilla/javascript/Interpreter$CallFrame:localShift	I
    //   3348: iadd
    //   3349: istore 10
    //   3351: aload 26
    //   3353: iload 10
    //   3355: aconst_null
    //   3356: aastore
    //   3357: goto -3027 -> 330
    //   3360: iload 9
    //   3362: iconst_1
    //   3363: iadd
    //   3364: istore 9
    //   3366: aload 26
    //   3368: iload 9
    //   3370: aload_1
    //   3371: aload_0
    //   3372: aload 15
    //   3374: getfield 211	org/mozilla/javascript/Interpreter$CallFrame:scope	Lorg/mozilla/javascript/Scriptable;
    //   3377: invokestatic 828	org/mozilla/javascript/ScriptRuntime:getNameFunctionAndThis	(Ljava/lang/String;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Callable;
    //   3380: aastore
    //   3381: iload 9
    //   3383: iconst_1
    //   3384: iadd
    //   3385: istore 9
    //   3387: aload 26
    //   3389: iload 9
    //   3391: aload_0
    //   3392: invokestatic 831	org/mozilla/javascript/ScriptRuntime:lastStoredScriptable	(Lorg/mozilla/javascript/Context;)Lorg/mozilla/javascript/Scriptable;
    //   3395: aastore
    //   3396: goto -3066 -> 330
    //   3399: aload 26
    //   3401: iload 9
    //   3403: aaload
    //   3404: astore 17
    //   3406: aload 17
    //   3408: astore 16
    //   3410: aload 17
    //   3412: aload 23
    //   3414: if_acmpne +13 -> 3427
    //   3417: aload 25
    //   3419: iload 9
    //   3421: daload
    //   3422: invokestatic 155	org/mozilla/javascript/ScriptRuntime:wrapNumber	(D)Ljava/lang/Number;
    //   3425: astore 16
    //   3427: aload 26
    //   3429: iload 9
    //   3431: aload 16
    //   3433: aload_1
    //   3434: aload_0
    //   3435: aload 15
    //   3437: getfield 211	org/mozilla/javascript/Interpreter$CallFrame:scope	Lorg/mozilla/javascript/Scriptable;
    //   3440: invokestatic 835	org/mozilla/javascript/ScriptRuntime:getPropFunctionAndThis	(Ljava/lang/Object;Ljava/lang/String;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Callable;
    //   3443: aastore
    //   3444: iload 9
    //   3446: iconst_1
    //   3447: iadd
    //   3448: istore 9
    //   3450: aload 26
    //   3452: iload 9
    //   3454: aload_0
    //   3455: invokestatic 831	org/mozilla/javascript/ScriptRuntime:lastStoredScriptable	(Lorg/mozilla/javascript/Context;)Lorg/mozilla/javascript/Scriptable;
    //   3458: aastore
    //   3459: goto -3129 -> 330
    //   3462: aload 26
    //   3464: iload 9
    //   3466: iconst_1
    //   3467: isub
    //   3468: aaload
    //   3469: astore 16
    //   3471: aload 16
    //   3473: aload 23
    //   3475: if_acmpne +3536 -> 7011
    //   3478: aload 25
    //   3480: iload 9
    //   3482: iconst_1
    //   3483: isub
    //   3484: daload
    //   3485: invokestatic 155	org/mozilla/javascript/ScriptRuntime:wrapNumber	(D)Ljava/lang/Number;
    //   3488: astore 16
    //   3490: aload 26
    //   3492: iload 9
    //   3494: aaload
    //   3495: astore 19
    //   3497: aload 19
    //   3499: astore 17
    //   3501: aload 19
    //   3503: aload 23
    //   3505: if_acmpne +13 -> 3518
    //   3508: aload 25
    //   3510: iload 9
    //   3512: daload
    //   3513: invokestatic 155	org/mozilla/javascript/ScriptRuntime:wrapNumber	(D)Ljava/lang/Number;
    //   3516: astore 17
    //   3518: aload 26
    //   3520: iload 9
    //   3522: iconst_1
    //   3523: isub
    //   3524: aload 16
    //   3526: aload 17
    //   3528: aload_0
    //   3529: aload 15
    //   3531: getfield 211	org/mozilla/javascript/Interpreter$CallFrame:scope	Lorg/mozilla/javascript/Scriptable;
    //   3534: invokestatic 839	org/mozilla/javascript/ScriptRuntime:getElemFunctionAndThis	(Ljava/lang/Object;Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Callable;
    //   3537: aastore
    //   3538: aload 26
    //   3540: iload 9
    //   3542: aload_0
    //   3543: invokestatic 831	org/mozilla/javascript/ScriptRuntime:lastStoredScriptable	(Lorg/mozilla/javascript/Context;)Lorg/mozilla/javascript/Scriptable;
    //   3546: aastore
    //   3547: goto -3217 -> 330
    //   3550: aload 26
    //   3552: iload 9
    //   3554: aaload
    //   3555: astore 17
    //   3557: aload 17
    //   3559: astore 16
    //   3561: aload 17
    //   3563: aload 23
    //   3565: if_acmpne +13 -> 3578
    //   3568: aload 25
    //   3570: iload 9
    //   3572: daload
    //   3573: invokestatic 155	org/mozilla/javascript/ScriptRuntime:wrapNumber	(D)Ljava/lang/Number;
    //   3576: astore 16
    //   3578: aload 26
    //   3580: iload 9
    //   3582: aload 16
    //   3584: aload_0
    //   3585: invokestatic 843	org/mozilla/javascript/ScriptRuntime:getValueFunctionAndThis	(Ljava/lang/Object;Lorg/mozilla/javascript/Context;)Lorg/mozilla/javascript/Callable;
    //   3588: aastore
    //   3589: iload 9
    //   3591: iconst_1
    //   3592: iadd
    //   3593: istore 9
    //   3595: aload 26
    //   3597: iload 9
    //   3599: aload_0
    //   3600: invokestatic 831	org/mozilla/javascript/ScriptRuntime:lastStoredScriptable	(Lorg/mozilla/javascript/Context;)Lorg/mozilla/javascript/Scriptable;
    //   3603: aastore
    //   3604: goto -3274 -> 330
    //   3607: iload 13
    //   3609: ifeq +14 -> 3623
    //   3612: aload_0
    //   3613: aload_0
    //   3614: getfield 47	org/mozilla/javascript/Context:instructionCount	I
    //   3617: bipush 100
    //   3619: iadd
    //   3620: putfield 47	org/mozilla/javascript/Context:instructionCount	I
    //   3623: aload_0
    //   3624: aload 15
    //   3626: aload 26
    //   3628: aload 25
    //   3630: iload 9
    //   3632: aload 27
    //   3634: iload 10
    //   3636: invokestatic 845	org/mozilla/javascript/Interpreter:doCallSpecial	(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Interpreter$CallFrame;[Ljava/lang/Object;[DI[BI)I
    //   3639: istore 9
    //   3641: goto -3311 -> 330
    //   3644: iload 13
    //   3646: ifeq +14 -> 3660
    //   3649: aload_0
    //   3650: aload_0
    //   3651: getfield 47	org/mozilla/javascript/Context:instructionCount	I
    //   3654: bipush 100
    //   3656: iadd
    //   3657: putfield 47	org/mozilla/javascript/Context:instructionCount	I
    //   3660: iload 9
    //   3662: iload 10
    //   3664: iconst_1
    //   3665: iadd
    //   3666: isub
    //   3667: istore 9
    //   3669: aload 26
    //   3671: iload 9
    //   3673: aaload
    //   3674: checkcast 217	org/mozilla/javascript/Callable
    //   3677: astore 17
    //   3679: aload 26
    //   3681: iload 9
    //   3683: iconst_1
    //   3684: iadd
    //   3685: aaload
    //   3686: checkcast 151	org/mozilla/javascript/Scriptable
    //   3689: astore 20
    //   3691: iload 12
    //   3693: bipush 71
    //   3695: if_icmpne +32 -> 3727
    //   3698: aload 26
    //   3700: iload 9
    //   3702: aload 17
    //   3704: aload 20
    //   3706: aload 26
    //   3708: aload 25
    //   3710: iload 9
    //   3712: iconst_2
    //   3713: iadd
    //   3714: iload 10
    //   3716: invokestatic 207	org/mozilla/javascript/Interpreter:getArgsArray	([Ljava/lang/Object;[DII)[Ljava/lang/Object;
    //   3719: aload_0
    //   3720: invokestatic 849	org/mozilla/javascript/ScriptRuntime:callRef	(Lorg/mozilla/javascript/Callable;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;Lorg/mozilla/javascript/Context;)Lorg/mozilla/javascript/Ref;
    //   3723: aastore
    //   3724: goto -3394 -> 330
    //   3727: aload 15
    //   3729: getfield 211	org/mozilla/javascript/Interpreter$CallFrame:scope	Lorg/mozilla/javascript/Scriptable;
    //   3732: astore 16
    //   3734: aload 15
    //   3736: getfield 281	org/mozilla/javascript/Interpreter$CallFrame:useActivation	Z
    //   3739: ifeq +13 -> 3752
    //   3742: aload 15
    //   3744: getfield 211	org/mozilla/javascript/Interpreter$CallFrame:scope	Lorg/mozilla/javascript/Scriptable;
    //   3747: invokestatic 855	org/mozilla/javascript/ScriptableObject:getTopLevelScope	(Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Scriptable;
    //   3750: astore 16
    //   3752: aload 17
    //   3754: instanceof 482
    //   3757: ifeq +105 -> 3862
    //   3760: aload 17
    //   3762: checkcast 482	org/mozilla/javascript/InterpretedFunction
    //   3765: astore 21
    //   3767: aload 15
    //   3769: getfield 557	org/mozilla/javascript/Interpreter$CallFrame:fnOrScript	Lorg/mozilla/javascript/InterpretedFunction;
    //   3772: getfield 636	org/mozilla/javascript/InterpretedFunction:securityDomain	Ljava/lang/Object;
    //   3775: aload 21
    //   3777: getfield 636	org/mozilla/javascript/InterpretedFunction:securityDomain	Ljava/lang/Object;
    //   3780: if_acmpne +82 -> 3862
    //   3783: new 10	org/mozilla/javascript/Interpreter$CallFrame
    //   3786: dup
    //   3787: aconst_null
    //   3788: invokespecial 594	org/mozilla/javascript/Interpreter$CallFrame:<init>	(Lorg/mozilla/javascript/Interpreter$1;)V
    //   3791: astore 19
    //   3793: iload 12
    //   3795: bipush -55
    //   3797: if_icmpne +3207 -> 7004
    //   3800: aload 15
    //   3802: getfield 123	org/mozilla/javascript/Interpreter$CallFrame:parentFrame	Lorg/mozilla/javascript/Interpreter$CallFrame;
    //   3805: astore 17
    //   3807: aload_0
    //   3808: aload 15
    //   3810: aconst_null
    //   3811: invokestatic 591	org/mozilla/javascript/Interpreter:exitFrame	(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Interpreter$CallFrame;Ljava/lang/Object;)V
    //   3814: aload_0
    //   3815: aload 16
    //   3817: aload 20
    //   3819: aload 26
    //   3821: aload 25
    //   3823: iload 9
    //   3825: iconst_2
    //   3826: iadd
    //   3827: iload 10
    //   3829: aload 21
    //   3831: aload 17
    //   3833: aload 19
    //   3835: invokestatic 602	org/mozilla/javascript/Interpreter:initFrame	(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;[DIILorg/mozilla/javascript/InterpretedFunction;Lorg/mozilla/javascript/Interpreter$CallFrame;Lorg/mozilla/javascript/Interpreter$CallFrame;)V
    //   3838: iload 12
    //   3840: bipush -55
    //   3842: if_icmpeq +3442 -> 7284
    //   3845: aload 15
    //   3847: iload 9
    //   3849: putfield 108	org/mozilla/javascript/Interpreter$CallFrame:savedStackTop	I
    //   3852: aload 15
    //   3854: iload 12
    //   3856: putfield 119	org/mozilla/javascript/Interpreter$CallFrame:savedCallOp	I
    //   3859: goto +3425 -> 7284
    //   3862: aload 17
    //   3864: instanceof 90
    //   3867: ifeq +61 -> 3928
    //   3870: new 13	org/mozilla/javascript/Interpreter$ContinuationJump
    //   3873: dup
    //   3874: aload 17
    //   3876: checkcast 90	org/mozilla/javascript/NativeContinuation
    //   3879: aload 15
    //   3881: invokespecial 858	org/mozilla/javascript/Interpreter$ContinuationJump:<init>	(Lorg/mozilla/javascript/NativeContinuation;Lorg/mozilla/javascript/Interpreter$CallFrame;)V
    //   3884: astore 16
    //   3886: iload 10
    //   3888: ifne +13 -> 3901
    //   3891: aload 16
    //   3893: aload 24
    //   3895: putfield 419	org/mozilla/javascript/Interpreter$ContinuationJump:result	Ljava/lang/Object;
    //   3898: goto +3397 -> 7295
    //   3901: aload 16
    //   3903: aload 26
    //   3905: iload 9
    //   3907: iconst_2
    //   3908: iadd
    //   3909: aaload
    //   3910: putfield 419	org/mozilla/javascript/Interpreter$ContinuationJump:result	Ljava/lang/Object;
    //   3913: aload 16
    //   3915: aload 25
    //   3917: iload 9
    //   3919: iconst_2
    //   3920: iadd
    //   3921: daload
    //   3922: putfield 420	org/mozilla/javascript/Interpreter$ContinuationJump:resultDbl	D
    //   3925: goto +3370 -> 7295
    //   3928: aload 17
    //   3930: instanceof 860
    //   3933: ifeq +116 -> 4049
    //   3936: aload 17
    //   3938: checkcast 860	org/mozilla/javascript/IdFunctionObject
    //   3941: astore 19
    //   3943: aload 19
    //   3945: invokestatic 863	org/mozilla/javascript/NativeContinuation:isContinuationConstructor	(Lorg/mozilla/javascript/IdFunctionObject;)Z
    //   3948: ifeq +24 -> 3972
    //   3951: aload 15
    //   3953: getfield 112	org/mozilla/javascript/Interpreter$CallFrame:stack	[Ljava/lang/Object;
    //   3956: iload 9
    //   3958: aload_0
    //   3959: aload 15
    //   3961: getfield 123	org/mozilla/javascript/Interpreter$CallFrame:parentFrame	Lorg/mozilla/javascript/Interpreter$CallFrame;
    //   3964: iconst_0
    //   3965: invokestatic 88	org/mozilla/javascript/Interpreter:captureContinuation	(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Interpreter$CallFrame;Z)Lorg/mozilla/javascript/NativeContinuation;
    //   3968: aastore
    //   3969: goto -3639 -> 330
    //   3972: aload 19
    //   3974: invokestatic 866	org/mozilla/javascript/BaseFunction:isApplyOrCall	(Lorg/mozilla/javascript/IdFunctionObject;)Z
    //   3977: ifeq +72 -> 4049
    //   3980: aload 20
    //   3982: invokestatic 870	org/mozilla/javascript/ScriptRuntime:getCallable	(Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Callable;
    //   3985: astore 21
    //   3987: aload 21
    //   3989: instanceof 482
    //   3992: ifeq +57 -> 4049
    //   3995: aload 21
    //   3997: checkcast 482	org/mozilla/javascript/InterpretedFunction
    //   4000: astore 21
    //   4002: aload 15
    //   4004: getfield 557	org/mozilla/javascript/Interpreter$CallFrame:fnOrScript	Lorg/mozilla/javascript/InterpretedFunction;
    //   4007: getfield 636	org/mozilla/javascript/InterpretedFunction:securityDomain	Ljava/lang/Object;
    //   4010: aload 21
    //   4012: getfield 636	org/mozilla/javascript/InterpretedFunction:securityDomain	Ljava/lang/Object;
    //   4015: if_acmpne +34 -> 4049
    //   4018: aload_0
    //   4019: aload 15
    //   4021: iload 10
    //   4023: aload 26
    //   4025: aload 25
    //   4027: iload 9
    //   4029: iload 12
    //   4031: aload 16
    //   4033: aload 19
    //   4035: aload 21
    //   4037: invokestatic 872	org/mozilla/javascript/Interpreter:initFrameForApplyOrCall	(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Interpreter$CallFrame;I[Ljava/lang/Object;[DIILorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/IdFunctionObject;Lorg/mozilla/javascript/InterpretedFunction;)Lorg/mozilla/javascript/Interpreter$CallFrame;
    //   4040: astore 16
    //   4042: aload 18
    //   4044: astore 17
    //   4046: goto -3939 -> 107
    //   4049: aload 17
    //   4051: instanceof 610
    //   4054: ifeq +81 -> 4135
    //   4057: aload 17
    //   4059: checkcast 610	org/mozilla/javascript/ScriptRuntime$NoSuchMethodShim
    //   4062: astore 19
    //   4064: aload 19
    //   4066: getfield 876	org/mozilla/javascript/ScriptRuntime$NoSuchMethodShim:noSuchMethodMethod	Lorg/mozilla/javascript/Callable;
    //   4069: astore 21
    //   4071: aload 21
    //   4073: instanceof 482
    //   4076: ifeq +59 -> 4135
    //   4079: aload 21
    //   4081: checkcast 482	org/mozilla/javascript/InterpretedFunction
    //   4084: astore 21
    //   4086: aload 15
    //   4088: getfield 557	org/mozilla/javascript/Interpreter$CallFrame:fnOrScript	Lorg/mozilla/javascript/InterpretedFunction;
    //   4091: getfield 636	org/mozilla/javascript/InterpretedFunction:securityDomain	Ljava/lang/Object;
    //   4094: aload 21
    //   4096: getfield 636	org/mozilla/javascript/InterpretedFunction:securityDomain	Ljava/lang/Object;
    //   4099: if_acmpne +36 -> 4135
    //   4102: aload_0
    //   4103: aload 15
    //   4105: iload 10
    //   4107: aload 26
    //   4109: aload 25
    //   4111: iload 9
    //   4113: iload 12
    //   4115: aload 20
    //   4117: aload 16
    //   4119: aload 19
    //   4121: aload 21
    //   4123: invokestatic 878	org/mozilla/javascript/Interpreter:initFrameForNoSuchMethod	(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Interpreter$CallFrame;I[Ljava/lang/Object;[DIILorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/ScriptRuntime$NoSuchMethodShim;Lorg/mozilla/javascript/InterpretedFunction;)Lorg/mozilla/javascript/Interpreter$CallFrame;
    //   4126: astore 16
    //   4128: aload 18
    //   4130: astore 17
    //   4132: goto -4025 -> 107
    //   4135: aload_0
    //   4136: aload 15
    //   4138: putfield 78	org/mozilla/javascript/Context:lastInterpreterFrame	Ljava/lang/Object;
    //   4141: aload 15
    //   4143: iload 12
    //   4145: putfield 119	org/mozilla/javascript/Interpreter$CallFrame:savedCallOp	I
    //   4148: aload 15
    //   4150: iload 9
    //   4152: putfield 108	org/mozilla/javascript/Interpreter$CallFrame:savedStackTop	I
    //   4155: aload 26
    //   4157: iload 9
    //   4159: aload 17
    //   4161: aload_0
    //   4162: aload 16
    //   4164: aload 20
    //   4166: aload 26
    //   4168: aload 25
    //   4170: iload 9
    //   4172: iconst_2
    //   4173: iadd
    //   4174: iload 10
    //   4176: invokestatic 207	org/mozilla/javascript/Interpreter:getArgsArray	([Ljava/lang/Object;[DII)[Ljava/lang/Object;
    //   4179: invokeinterface 882 5 0
    //   4184: aastore
    //   4185: goto -3855 -> 330
    //   4188: iload 13
    //   4190: ifeq +14 -> 4204
    //   4193: aload_0
    //   4194: aload_0
    //   4195: getfield 47	org/mozilla/javascript/Context:instructionCount	I
    //   4198: bipush 100
    //   4200: iadd
    //   4201: putfield 47	org/mozilla/javascript/Context:instructionCount	I
    //   4204: iload 9
    //   4206: iload 10
    //   4208: isub
    //   4209: istore 9
    //   4211: aload 26
    //   4213: iload 9
    //   4215: aaload
    //   4216: astore 17
    //   4218: aload 17
    //   4220: instanceof 482
    //   4223: ifeq +104 -> 4327
    //   4226: aload 17
    //   4228: checkcast 482	org/mozilla/javascript/InterpretedFunction
    //   4231: astore 19
    //   4233: aload 15
    //   4235: getfield 557	org/mozilla/javascript/Interpreter$CallFrame:fnOrScript	Lorg/mozilla/javascript/InterpretedFunction;
    //   4238: getfield 636	org/mozilla/javascript/InterpretedFunction:securityDomain	Ljava/lang/Object;
    //   4241: aload 19
    //   4243: getfield 636	org/mozilla/javascript/InterpretedFunction:securityDomain	Ljava/lang/Object;
    //   4246: if_acmpne +81 -> 4327
    //   4249: aload 19
    //   4251: aload_0
    //   4252: aload 15
    //   4254: getfield 211	org/mozilla/javascript/Interpreter$CallFrame:scope	Lorg/mozilla/javascript/Scriptable;
    //   4257: invokevirtual 886	org/mozilla/javascript/InterpretedFunction:createObject	(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Scriptable;
    //   4260: astore 17
    //   4262: new 10	org/mozilla/javascript/Interpreter$CallFrame
    //   4265: dup
    //   4266: aconst_null
    //   4267: invokespecial 594	org/mozilla/javascript/Interpreter$CallFrame:<init>	(Lorg/mozilla/javascript/Interpreter$1;)V
    //   4270: astore 16
    //   4272: aload_0
    //   4273: aload 15
    //   4275: getfield 211	org/mozilla/javascript/Interpreter$CallFrame:scope	Lorg/mozilla/javascript/Scriptable;
    //   4278: aload 17
    //   4280: aload 26
    //   4282: aload 25
    //   4284: iload 9
    //   4286: iconst_1
    //   4287: iadd
    //   4288: iload 10
    //   4290: aload 19
    //   4292: aload 15
    //   4294: aload 16
    //   4296: invokestatic 602	org/mozilla/javascript/Interpreter:initFrame	(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;[Ljava/lang/Object;[DIILorg/mozilla/javascript/InterpretedFunction;Lorg/mozilla/javascript/Interpreter$CallFrame;Lorg/mozilla/javascript/Interpreter$CallFrame;)V
    //   4299: aload 26
    //   4301: iload 9
    //   4303: aload 17
    //   4305: aastore
    //   4306: aload 15
    //   4308: iload 9
    //   4310: putfield 108	org/mozilla/javascript/Interpreter$CallFrame:savedStackTop	I
    //   4313: aload 15
    //   4315: iload 12
    //   4317: putfield 119	org/mozilla/javascript/Interpreter$CallFrame:savedCallOp	I
    //   4320: aload 18
    //   4322: astore 17
    //   4324: goto -4217 -> 107
    //   4327: aload 17
    //   4329: instanceof 888
    //   4332: ifne +30 -> 4362
    //   4335: aload 17
    //   4337: astore 16
    //   4339: aload 17
    //   4341: aload 23
    //   4343: if_acmpne +13 -> 4356
    //   4346: aload 25
    //   4348: iload 9
    //   4350: daload
    //   4351: invokestatic 155	org/mozilla/javascript/ScriptRuntime:wrapNumber	(D)Ljava/lang/Number;
    //   4354: astore 16
    //   4356: aload 16
    //   4358: invokestatic 892	org/mozilla/javascript/ScriptRuntime:notFunctionError	(Ljava/lang/Object;)Ljava/lang/RuntimeException;
    //   4361: athrow
    //   4362: aload 17
    //   4364: checkcast 888	org/mozilla/javascript/Function
    //   4367: astore 16
    //   4369: aload 16
    //   4371: instanceof 860
    //   4374: ifeq +35 -> 4409
    //   4377: aload 16
    //   4379: checkcast 860	org/mozilla/javascript/IdFunctionObject
    //   4382: invokestatic 863	org/mozilla/javascript/NativeContinuation:isContinuationConstructor	(Lorg/mozilla/javascript/IdFunctionObject;)Z
    //   4385: ifeq +24 -> 4409
    //   4388: aload 15
    //   4390: getfield 112	org/mozilla/javascript/Interpreter$CallFrame:stack	[Ljava/lang/Object;
    //   4393: iload 9
    //   4395: aload_0
    //   4396: aload 15
    //   4398: getfield 123	org/mozilla/javascript/Interpreter$CallFrame:parentFrame	Lorg/mozilla/javascript/Interpreter$CallFrame;
    //   4401: iconst_0
    //   4402: invokestatic 88	org/mozilla/javascript/Interpreter:captureContinuation	(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Interpreter$CallFrame;Z)Lorg/mozilla/javascript/NativeContinuation;
    //   4405: aastore
    //   4406: goto -4076 -> 330
    //   4409: aload 26
    //   4411: aload 25
    //   4413: iload 9
    //   4415: iconst_1
    //   4416: iadd
    //   4417: iload 10
    //   4419: invokestatic 207	org/mozilla/javascript/Interpreter:getArgsArray	([Ljava/lang/Object;[DII)[Ljava/lang/Object;
    //   4422: astore 17
    //   4424: aload 26
    //   4426: iload 9
    //   4428: aload 16
    //   4430: aload_0
    //   4431: aload 15
    //   4433: getfield 211	org/mozilla/javascript/Interpreter$CallFrame:scope	Lorg/mozilla/javascript/Scriptable;
    //   4436: aload 17
    //   4438: invokeinterface 896 4 0
    //   4443: aastore
    //   4444: goto -4114 -> 330
    //   4447: aload 26
    //   4449: iload 9
    //   4451: aaload
    //   4452: astore 17
    //   4454: aload 17
    //   4456: astore 16
    //   4458: aload 17
    //   4460: aload 23
    //   4462: if_acmpne +13 -> 4475
    //   4465: aload 25
    //   4467: iload 9
    //   4469: daload
    //   4470: invokestatic 155	org/mozilla/javascript/ScriptRuntime:wrapNumber	(D)Ljava/lang/Number;
    //   4473: astore 16
    //   4475: aload 26
    //   4477: iload 9
    //   4479: aload 16
    //   4481: invokestatic 900	org/mozilla/javascript/ScriptRuntime:typeof	(Ljava/lang/Object;)Ljava/lang/String;
    //   4484: aastore
    //   4485: goto -4155 -> 330
    //   4488: iload 9
    //   4490: iconst_1
    //   4491: iadd
    //   4492: istore 9
    //   4494: aload 26
    //   4496: iload 9
    //   4498: aload 15
    //   4500: getfield 211	org/mozilla/javascript/Interpreter$CallFrame:scope	Lorg/mozilla/javascript/Scriptable;
    //   4503: aload_1
    //   4504: invokestatic 904	org/mozilla/javascript/ScriptRuntime:typeofName	(Lorg/mozilla/javascript/Scriptable;Ljava/lang/String;)Ljava/lang/String;
    //   4507: aastore
    //   4508: goto -4178 -> 330
    //   4511: iload 9
    //   4513: iconst_1
    //   4514: iadd
    //   4515: istore 9
    //   4517: aload 26
    //   4519: iload 9
    //   4521: aload 23
    //   4523: aastore
    //   4524: aload 25
    //   4526: iload 9
    //   4528: aload 27
    //   4530: aload 15
    //   4532: getfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   4535: invokestatic 759	org/mozilla/javascript/Interpreter:getShort	([BI)I
    //   4538: i2d
    //   4539: dastore
    //   4540: aload 15
    //   4542: aload 15
    //   4544: getfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   4547: iconst_2
    //   4548: iadd
    //   4549: putfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   4552: goto -4222 -> 330
    //   4555: iload 9
    //   4557: iconst_1
    //   4558: iadd
    //   4559: istore 9
    //   4561: aload 26
    //   4563: iload 9
    //   4565: aload 23
    //   4567: aastore
    //   4568: aload 25
    //   4570: iload 9
    //   4572: aload 27
    //   4574: aload 15
    //   4576: getfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   4579: invokestatic 906	org/mozilla/javascript/Interpreter:getInt	([BI)I
    //   4582: i2d
    //   4583: dastore
    //   4584: aload 15
    //   4586: aload 15
    //   4588: getfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   4591: iconst_4
    //   4592: iadd
    //   4593: putfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   4596: goto -4266 -> 330
    //   4599: iload 9
    //   4601: iconst_1
    //   4602: iadd
    //   4603: istore 9
    //   4605: aload 26
    //   4607: iload 9
    //   4609: aload 23
    //   4611: aastore
    //   4612: aload 25
    //   4614: iload 9
    //   4616: aload 15
    //   4618: getfield 223	org/mozilla/javascript/Interpreter$CallFrame:idata	Lorg/mozilla/javascript/InterpreterData;
    //   4621: getfield 909	org/mozilla/javascript/InterpreterData:itsDoubleTable	[D
    //   4624: iload 10
    //   4626: daload
    //   4627: dastore
    //   4628: goto -4298 -> 330
    //   4631: iload 9
    //   4633: iconst_1
    //   4634: iadd
    //   4635: istore 9
    //   4637: aload 26
    //   4639: iload 9
    //   4641: aload_0
    //   4642: aload 15
    //   4644: getfield 211	org/mozilla/javascript/Interpreter$CallFrame:scope	Lorg/mozilla/javascript/Scriptable;
    //   4647: aload_1
    //   4648: invokestatic 913	org/mozilla/javascript/ScriptRuntime:name	(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Ljava/lang/String;)Ljava/lang/Object;
    //   4651: aastore
    //   4652: goto -4322 -> 330
    //   4655: iload 9
    //   4657: iconst_1
    //   4658: iadd
    //   4659: istore 9
    //   4661: aload 26
    //   4663: iload 9
    //   4665: aload 15
    //   4667: getfield 211	org/mozilla/javascript/Interpreter$CallFrame:scope	Lorg/mozilla/javascript/Scriptable;
    //   4670: aload_1
    //   4671: aload_0
    //   4672: aload 27
    //   4674: aload 15
    //   4676: getfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   4679: baload
    //   4680: invokestatic 355	org/mozilla/javascript/ScriptRuntime:nameIncrDecr	(Lorg/mozilla/javascript/Scriptable;Ljava/lang/String;Lorg/mozilla/javascript/Context;I)Ljava/lang/Object;
    //   4683: aastore
    //   4684: aload 15
    //   4686: aload 15
    //   4688: getfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   4691: iconst_1
    //   4692: iadd
    //   4693: putfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   4696: goto -4366 -> 330
    //   4699: aload 15
    //   4701: getfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   4704: istore 10
    //   4706: aload 15
    //   4708: iload 10
    //   4710: iconst_1
    //   4711: iadd
    //   4712: putfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   4715: aload 27
    //   4717: iload 10
    //   4719: baload
    //   4720: istore 10
    //   4722: aload 15
    //   4724: aload 26
    //   4726: aload 25
    //   4728: iload 9
    //   4730: aload 28
    //   4732: aload 29
    //   4734: aload 30
    //   4736: iload 10
    //   4738: invokestatic 915	org/mozilla/javascript/Interpreter:doSetConstVar	(Lorg/mozilla/javascript/Interpreter$CallFrame;[Ljava/lang/Object;[DI[Ljava/lang/Object;[D[II)I
    //   4741: istore 9
    //   4743: goto -4413 -> 330
    //   4746: aload 15
    //   4748: getfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   4751: istore 10
    //   4753: aload 15
    //   4755: iload 10
    //   4757: iconst_1
    //   4758: iadd
    //   4759: putfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   4762: aload 27
    //   4764: iload 10
    //   4766: baload
    //   4767: istore 10
    //   4769: aload 15
    //   4771: aload 26
    //   4773: aload 25
    //   4775: iload 9
    //   4777: aload 28
    //   4779: aload 29
    //   4781: aload 30
    //   4783: iload 10
    //   4785: invokestatic 917	org/mozilla/javascript/Interpreter:doSetVar	(Lorg/mozilla/javascript/Interpreter$CallFrame;[Ljava/lang/Object;[DI[Ljava/lang/Object;[D[II)I
    //   4788: istore 9
    //   4790: goto -4460 -> 330
    //   4793: aload 15
    //   4795: getfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   4798: istore 10
    //   4800: aload 15
    //   4802: iload 10
    //   4804: iconst_1
    //   4805: iadd
    //   4806: putfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   4809: aload 27
    //   4811: iload 10
    //   4813: baload
    //   4814: istore 10
    //   4816: aload 15
    //   4818: aload 26
    //   4820: aload 25
    //   4822: iload 9
    //   4824: aload 28
    //   4826: aload 29
    //   4828: iload 10
    //   4830: invokestatic 919	org/mozilla/javascript/Interpreter:doGetVar	(Lorg/mozilla/javascript/Interpreter$CallFrame;[Ljava/lang/Object;[DI[Ljava/lang/Object;[DI)I
    //   4833: istore 9
    //   4835: goto -4505 -> 330
    //   4838: aload_0
    //   4839: aload 15
    //   4841: aload 26
    //   4843: aload 25
    //   4845: iload 9
    //   4847: aload 28
    //   4849: aload 29
    //   4851: aload 30
    //   4853: iload 10
    //   4855: invokestatic 921	org/mozilla/javascript/Interpreter:doVarIncDec	(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Interpreter$CallFrame;[Ljava/lang/Object;[DI[Ljava/lang/Object;[D[II)I
    //   4858: istore 9
    //   4860: goto -4530 -> 330
    //   4863: iload 9
    //   4865: iconst_1
    //   4866: iadd
    //   4867: istore 9
    //   4869: aload 26
    //   4871: iload 9
    //   4873: aload 15
    //   4875: getfield 220	org/mozilla/javascript/Interpreter$CallFrame:thisObj	Lorg/mozilla/javascript/Scriptable;
    //   4878: aastore
    //   4879: goto -4549 -> 330
    //   4882: iload 9
    //   4884: iconst_1
    //   4885: iadd
    //   4886: istore 9
    //   4888: aload 26
    //   4890: iload 9
    //   4892: aload 15
    //   4894: getfield 557	org/mozilla/javascript/Interpreter$CallFrame:fnOrScript	Lorg/mozilla/javascript/InterpretedFunction;
    //   4897: aastore
    //   4898: goto -4568 -> 330
    //   4901: iload 9
    //   4903: iconst_1
    //   4904: iadd
    //   4905: istore 9
    //   4907: aload 26
    //   4909: iload 9
    //   4911: getstatic 927	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   4914: aastore
    //   4915: goto -4585 -> 330
    //   4918: iload 9
    //   4920: iconst_1
    //   4921: iadd
    //   4922: istore 9
    //   4924: aload 26
    //   4926: iload 9
    //   4928: getstatic 930	java/lang/Boolean:TRUE	Ljava/lang/Boolean;
    //   4931: aastore
    //   4932: goto -4602 -> 330
    //   4935: aload 26
    //   4937: iload 9
    //   4939: aaload
    //   4940: astore 17
    //   4942: aload 17
    //   4944: astore 16
    //   4946: aload 17
    //   4948: aload 23
    //   4950: if_acmpne +13 -> 4963
    //   4953: aload 25
    //   4955: iload 9
    //   4957: daload
    //   4958: invokestatic 155	org/mozilla/javascript/ScriptRuntime:wrapNumber	(D)Ljava/lang/Number;
    //   4961: astore 16
    //   4963: iload 9
    //   4965: iconst_1
    //   4966: isub
    //   4967: istore 9
    //   4969: aload 15
    //   4971: aload 16
    //   4973: aload_0
    //   4974: aload 15
    //   4976: getfield 211	org/mozilla/javascript/Interpreter$CallFrame:scope	Lorg/mozilla/javascript/Scriptable;
    //   4979: invokestatic 934	org/mozilla/javascript/ScriptRuntime:enterWith	(Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Scriptable;
    //   4982: putfield 211	org/mozilla/javascript/Interpreter$CallFrame:scope	Lorg/mozilla/javascript/Scriptable;
    //   4985: goto -4655 -> 330
    //   4988: aload 15
    //   4990: aload 15
    //   4992: getfield 211	org/mozilla/javascript/Interpreter$CallFrame:scope	Lorg/mozilla/javascript/Scriptable;
    //   4995: invokestatic 937	org/mozilla/javascript/ScriptRuntime:leaveWith	(Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Scriptable;
    //   4998: putfield 211	org/mozilla/javascript/Interpreter$CallFrame:scope	Lorg/mozilla/javascript/Scriptable;
    //   5001: goto -4671 -> 330
    //   5004: iload 9
    //   5006: iconst_1
    //   5007: isub
    //   5008: istore 11
    //   5010: iload 10
    //   5012: aload 15
    //   5014: getfield 563	org/mozilla/javascript/Interpreter$CallFrame:localShift	I
    //   5017: iadd
    //   5018: istore 10
    //   5020: aload 15
    //   5022: getfield 223	org/mozilla/javascript/Interpreter$CallFrame:idata	Lorg/mozilla/javascript/InterpreterData;
    //   5025: getfield 351	org/mozilla/javascript/InterpreterData:itsICode	[B
    //   5028: aload 15
    //   5030: getfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   5033: baload
    //   5034: ifeq +2357 -> 7391
    //   5037: iconst_1
    //   5038: istore 9
    //   5040: aload 26
    //   5042: iload 11
    //   5044: iconst_1
    //   5045: iadd
    //   5046: aaload
    //   5047: checkcast 386	java/lang/Throwable
    //   5050: astore 17
    //   5052: iload 9
    //   5054: ifne +44 -> 5098
    //   5057: aconst_null
    //   5058: astore 16
    //   5060: aload 26
    //   5062: iload 10
    //   5064: aload 17
    //   5066: aload 16
    //   5068: aload_1
    //   5069: aload_0
    //   5070: aload 15
    //   5072: getfield 211	org/mozilla/javascript/Interpreter$CallFrame:scope	Lorg/mozilla/javascript/Scriptable;
    //   5075: invokestatic 941	org/mozilla/javascript/ScriptRuntime:newCatchScope	(Ljava/lang/Throwable;Lorg/mozilla/javascript/Scriptable;Ljava/lang/String;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Scriptable;
    //   5078: aastore
    //   5079: aload 15
    //   5081: aload 15
    //   5083: getfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   5086: iconst_1
    //   5087: iadd
    //   5088: putfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   5091: iload 11
    //   5093: istore 9
    //   5095: goto -4765 -> 330
    //   5098: aload 26
    //   5100: iload 10
    //   5102: aaload
    //   5103: checkcast 151	org/mozilla/javascript/Scriptable
    //   5106: astore 16
    //   5108: goto -48 -> 5060
    //   5111: aload 26
    //   5113: iload 9
    //   5115: aaload
    //   5116: astore 16
    //   5118: aload 16
    //   5120: aload 23
    //   5122: if_acmpne +1879 -> 7001
    //   5125: aload 25
    //   5127: iload 9
    //   5129: daload
    //   5130: invokestatic 155	org/mozilla/javascript/ScriptRuntime:wrapNumber	(D)Ljava/lang/Number;
    //   5133: astore 16
    //   5135: iload 9
    //   5137: iconst_1
    //   5138: isub
    //   5139: istore 11
    //   5141: iload 10
    //   5143: aload 15
    //   5145: getfield 563	org/mozilla/javascript/Interpreter$CallFrame:localShift	I
    //   5148: iadd
    //   5149: istore 10
    //   5151: iload 12
    //   5153: bipush 58
    //   5155: if_icmpne +2242 -> 7397
    //   5158: iconst_0
    //   5159: istore 9
    //   5161: aload 26
    //   5163: iload 10
    //   5165: aload 16
    //   5167: aload_0
    //   5168: aload 15
    //   5170: getfield 211	org/mozilla/javascript/Interpreter$CallFrame:scope	Lorg/mozilla/javascript/Scriptable;
    //   5173: iload 9
    //   5175: invokestatic 945	org/mozilla/javascript/ScriptRuntime:enumInit	(Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;I)Ljava/lang/Object;
    //   5178: aastore
    //   5179: iload 11
    //   5181: istore 9
    //   5183: goto -4853 -> 330
    //   5186: iload 10
    //   5188: aload 15
    //   5190: getfield 563	org/mozilla/javascript/Interpreter$CallFrame:localShift	I
    //   5193: iadd
    //   5194: istore 10
    //   5196: aload 26
    //   5198: iload 10
    //   5200: aaload
    //   5201: astore 16
    //   5203: iload 9
    //   5205: iconst_1
    //   5206: iadd
    //   5207: istore 9
    //   5209: iload 12
    //   5211: bipush 62
    //   5213: if_icmpne +13 -> 5226
    //   5216: aload 16
    //   5218: invokestatic 949	org/mozilla/javascript/ScriptRuntime:enumNext	(Ljava/lang/Object;)Ljava/lang/Boolean;
    //   5221: astore 16
    //   5223: goto +2207 -> 7430
    //   5226: aload 16
    //   5228: aload_0
    //   5229: invokestatic 953	org/mozilla/javascript/ScriptRuntime:enumId	(Ljava/lang/Object;Lorg/mozilla/javascript/Context;)Ljava/lang/Object;
    //   5232: astore 16
    //   5234: goto +2196 -> 7430
    //   5237: aload 26
    //   5239: iload 9
    //   5241: aaload
    //   5242: astore 17
    //   5244: aload 17
    //   5246: astore 16
    //   5248: aload 17
    //   5250: aload 23
    //   5252: if_acmpne +13 -> 5265
    //   5255: aload 25
    //   5257: iload 9
    //   5259: daload
    //   5260: invokestatic 155	org/mozilla/javascript/ScriptRuntime:wrapNumber	(D)Ljava/lang/Number;
    //   5263: astore 16
    //   5265: aload 26
    //   5267: iload 9
    //   5269: aload 16
    //   5271: aload_1
    //   5272: aload_0
    //   5273: aload 15
    //   5275: getfield 211	org/mozilla/javascript/Interpreter$CallFrame:scope	Lorg/mozilla/javascript/Scriptable;
    //   5278: invokestatic 957	org/mozilla/javascript/ScriptRuntime:specialRef	(Ljava/lang/Object;Ljava/lang/String;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Ref;
    //   5281: aastore
    //   5282: goto -4952 -> 330
    //   5285: aload_0
    //   5286: aload 26
    //   5288: aload 25
    //   5290: iload 9
    //   5292: iload 10
    //   5294: invokestatic 959	org/mozilla/javascript/Interpreter:doRefMember	(Lorg/mozilla/javascript/Context;[Ljava/lang/Object;[DII)I
    //   5297: istore 9
    //   5299: goto -4969 -> 330
    //   5302: aload_0
    //   5303: aload 26
    //   5305: aload 25
    //   5307: iload 9
    //   5309: iload 10
    //   5311: invokestatic 961	org/mozilla/javascript/Interpreter:doRefNsMember	(Lorg/mozilla/javascript/Context;[Ljava/lang/Object;[DII)I
    //   5314: istore 9
    //   5316: goto -4986 -> 330
    //   5319: aload 26
    //   5321: iload 9
    //   5323: aaload
    //   5324: astore 17
    //   5326: aload 17
    //   5328: astore 16
    //   5330: aload 17
    //   5332: aload 23
    //   5334: if_acmpne +13 -> 5347
    //   5337: aload 25
    //   5339: iload 9
    //   5341: daload
    //   5342: invokestatic 155	org/mozilla/javascript/ScriptRuntime:wrapNumber	(D)Ljava/lang/Number;
    //   5345: astore 16
    //   5347: aload 26
    //   5349: iload 9
    //   5351: aload 16
    //   5353: aload_0
    //   5354: aload 15
    //   5356: getfield 211	org/mozilla/javascript/Interpreter$CallFrame:scope	Lorg/mozilla/javascript/Scriptable;
    //   5359: iload 10
    //   5361: invokestatic 964	org/mozilla/javascript/ScriptRuntime:nameRef	(Ljava/lang/Object;Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;I)Lorg/mozilla/javascript/Ref;
    //   5364: aastore
    //   5365: goto -5035 -> 330
    //   5368: aload_0
    //   5369: aload 15
    //   5371: aload 26
    //   5373: aload 25
    //   5375: iload 9
    //   5377: iload 10
    //   5379: invokestatic 966	org/mozilla/javascript/Interpreter:doRefNsName	(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Interpreter$CallFrame;[Ljava/lang/Object;[DII)I
    //   5382: istore 9
    //   5384: goto -5054 -> 330
    //   5387: iload 10
    //   5389: aload 15
    //   5391: getfield 563	org/mozilla/javascript/Interpreter$CallFrame:localShift	I
    //   5394: iadd
    //   5395: istore 10
    //   5397: aload 15
    //   5399: aload 26
    //   5401: iload 10
    //   5403: aaload
    //   5404: checkcast 151	org/mozilla/javascript/Scriptable
    //   5407: putfield 211	org/mozilla/javascript/Interpreter$CallFrame:scope	Lorg/mozilla/javascript/Scriptable;
    //   5410: goto -5080 -> 330
    //   5413: iload 10
    //   5415: aload 15
    //   5417: getfield 563	org/mozilla/javascript/Interpreter$CallFrame:localShift	I
    //   5420: iadd
    //   5421: istore 10
    //   5423: aload 26
    //   5425: iload 10
    //   5427: aload 15
    //   5429: getfield 211	org/mozilla/javascript/Interpreter$CallFrame:scope	Lorg/mozilla/javascript/Scriptable;
    //   5432: aastore
    //   5433: goto -5103 -> 330
    //   5436: aload_0
    //   5437: aload 15
    //   5439: getfield 211	org/mozilla/javascript/Interpreter$CallFrame:scope	Lorg/mozilla/javascript/Scriptable;
    //   5442: aload 15
    //   5444: getfield 557	org/mozilla/javascript/Interpreter$CallFrame:fnOrScript	Lorg/mozilla/javascript/InterpretedFunction;
    //   5447: iload 10
    //   5449: invokestatic 621	org/mozilla/javascript/InterpretedFunction:createFunction	(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/InterpretedFunction;I)Lorg/mozilla/javascript/InterpretedFunction;
    //   5452: astore 16
    //   5454: aload 16
    //   5456: getfield 483	org/mozilla/javascript/InterpretedFunction:idata	Lorg/mozilla/javascript/InterpreterData;
    //   5459: getfield 496	org/mozilla/javascript/InterpreterData:itsFunctionType	I
    //   5462: iconst_4
    //   5463: if_icmpne +1977 -> 7440
    //   5466: iload 9
    //   5468: iconst_1
    //   5469: iadd
    //   5470: istore 9
    //   5472: aload 26
    //   5474: iload 9
    //   5476: new 968	org/mozilla/javascript/ArrowFunction
    //   5479: dup
    //   5480: aload_0
    //   5481: aload 15
    //   5483: getfield 211	org/mozilla/javascript/Interpreter$CallFrame:scope	Lorg/mozilla/javascript/Scriptable;
    //   5486: aload 16
    //   5488: aload 15
    //   5490: getfield 220	org/mozilla/javascript/Interpreter$CallFrame:thisObj	Lorg/mozilla/javascript/Scriptable;
    //   5493: invokespecial 971	org/mozilla/javascript/ArrowFunction:<init>	(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Callable;Lorg/mozilla/javascript/Scriptable;)V
    //   5496: aastore
    //   5497: goto -5167 -> 330
    //   5500: aload_0
    //   5501: aload 15
    //   5503: getfield 211	org/mozilla/javascript/Interpreter$CallFrame:scope	Lorg/mozilla/javascript/Scriptable;
    //   5506: aload 15
    //   5508: getfield 557	org/mozilla/javascript/Interpreter$CallFrame:fnOrScript	Lorg/mozilla/javascript/InterpretedFunction;
    //   5511: iload 10
    //   5513: invokestatic 512	org/mozilla/javascript/Interpreter:initFunction	(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/InterpretedFunction;I)V
    //   5516: goto -5186 -> 330
    //   5519: aload 15
    //   5521: getfield 223	org/mozilla/javascript/Interpreter$CallFrame:idata	Lorg/mozilla/javascript/InterpreterData;
    //   5524: getfield 974	org/mozilla/javascript/InterpreterData:itsRegExpLiterals	[Ljava/lang/Object;
    //   5527: iload 10
    //   5529: aaload
    //   5530: astore 16
    //   5532: iload 9
    //   5534: iconst_1
    //   5535: iadd
    //   5536: istore 9
    //   5538: aload 26
    //   5540: iload 9
    //   5542: aload_0
    //   5543: aload 15
    //   5545: getfield 211	org/mozilla/javascript/Interpreter$CallFrame:scope	Lorg/mozilla/javascript/Scriptable;
    //   5548: aload 16
    //   5550: invokestatic 978	org/mozilla/javascript/ScriptRuntime:wrapRegExp	(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;Ljava/lang/Object;)Lorg/mozilla/javascript/Scriptable;
    //   5553: aastore
    //   5554: goto -5224 -> 330
    //   5557: iload 9
    //   5559: iconst_1
    //   5560: iadd
    //   5561: istore 9
    //   5563: aload 26
    //   5565: iload 9
    //   5567: iload 10
    //   5569: newarray <illegal type>
    //   5571: aastore
    //   5572: iload 9
    //   5574: iconst_1
    //   5575: iadd
    //   5576: istore 9
    //   5578: aload 26
    //   5580: iload 9
    //   5582: iload 10
    //   5584: anewarray 440	java/lang/Object
    //   5587: aastore
    //   5588: aload 25
    //   5590: iload 9
    //   5592: dconst_0
    //   5593: dastore
    //   5594: goto -5264 -> 330
    //   5597: aload 26
    //   5599: iload 9
    //   5601: aaload
    //   5602: astore 16
    //   5604: aload 16
    //   5606: aload 23
    //   5608: if_acmpne +1390 -> 6998
    //   5611: aload 25
    //   5613: iload 9
    //   5615: daload
    //   5616: invokestatic 155	org/mozilla/javascript/ScriptRuntime:wrapNumber	(D)Ljava/lang/Number;
    //   5619: astore 16
    //   5621: iload 9
    //   5623: iconst_1
    //   5624: isub
    //   5625: istore 9
    //   5627: aload 25
    //   5629: iload 9
    //   5631: daload
    //   5632: d2i
    //   5633: istore 11
    //   5635: aload 26
    //   5637: iload 9
    //   5639: aaload
    //   5640: checkcast 979	[Ljava/lang/Object;
    //   5643: checkcast 979	[Ljava/lang/Object;
    //   5646: iload 11
    //   5648: aload 16
    //   5650: aastore
    //   5651: aload 25
    //   5653: iload 9
    //   5655: iload 11
    //   5657: iconst_1
    //   5658: iadd
    //   5659: i2d
    //   5660: dastore
    //   5661: goto -5331 -> 330
    //   5664: aload 26
    //   5666: iload 9
    //   5668: aaload
    //   5669: astore 16
    //   5671: iload 9
    //   5673: iconst_1
    //   5674: isub
    //   5675: istore 9
    //   5677: aload 25
    //   5679: iload 9
    //   5681: daload
    //   5682: d2i
    //   5683: istore 11
    //   5685: aload 26
    //   5687: iload 9
    //   5689: aaload
    //   5690: checkcast 979	[Ljava/lang/Object;
    //   5693: checkcast 979	[Ljava/lang/Object;
    //   5696: iload 11
    //   5698: aload 16
    //   5700: aastore
    //   5701: aload 26
    //   5703: iload 9
    //   5705: iconst_1
    //   5706: isub
    //   5707: aaload
    //   5708: checkcast 980	[I
    //   5711: checkcast 980	[I
    //   5714: iload 11
    //   5716: iconst_m1
    //   5717: iastore
    //   5718: aload 25
    //   5720: iload 9
    //   5722: iload 11
    //   5724: iconst_1
    //   5725: iadd
    //   5726: i2d
    //   5727: dastore
    //   5728: goto -5398 -> 330
    //   5731: aload 26
    //   5733: iload 9
    //   5735: aaload
    //   5736: astore 16
    //   5738: iload 9
    //   5740: iconst_1
    //   5741: isub
    //   5742: istore 9
    //   5744: aload 25
    //   5746: iload 9
    //   5748: daload
    //   5749: d2i
    //   5750: istore 11
    //   5752: aload 26
    //   5754: iload 9
    //   5756: aaload
    //   5757: checkcast 979	[Ljava/lang/Object;
    //   5760: checkcast 979	[Ljava/lang/Object;
    //   5763: iload 11
    //   5765: aload 16
    //   5767: aastore
    //   5768: aload 26
    //   5770: iload 9
    //   5772: iconst_1
    //   5773: isub
    //   5774: aaload
    //   5775: checkcast 980	[I
    //   5778: checkcast 980	[I
    //   5781: iload 11
    //   5783: iconst_1
    //   5784: iastore
    //   5785: aload 25
    //   5787: iload 9
    //   5789: iload 11
    //   5791: iconst_1
    //   5792: iadd
    //   5793: i2d
    //   5794: dastore
    //   5795: goto -5465 -> 330
    //   5798: aload 26
    //   5800: iload 9
    //   5802: aaload
    //   5803: checkcast 979	[Ljava/lang/Object;
    //   5806: checkcast 979	[Ljava/lang/Object;
    //   5809: astore 17
    //   5811: iload 9
    //   5813: iconst_1
    //   5814: isub
    //   5815: istore 9
    //   5817: aload 26
    //   5819: iload 9
    //   5821: aaload
    //   5822: checkcast 980	[I
    //   5825: checkcast 980	[I
    //   5828: astore 16
    //   5830: iload 12
    //   5832: bipush 67
    //   5834: if_icmpne +38 -> 5872
    //   5837: aload 15
    //   5839: getfield 223	org/mozilla/javascript/Interpreter$CallFrame:idata	Lorg/mozilla/javascript/InterpreterData;
    //   5842: getfield 983	org/mozilla/javascript/InterpreterData:literalIds	[Ljava/lang/Object;
    //   5845: iload 10
    //   5847: aaload
    //   5848: checkcast 979	[Ljava/lang/Object;
    //   5851: checkcast 979	[Ljava/lang/Object;
    //   5854: aload 17
    //   5856: aload 16
    //   5858: aload_0
    //   5859: aload 15
    //   5861: getfield 211	org/mozilla/javascript/Interpreter$CallFrame:scope	Lorg/mozilla/javascript/Scriptable;
    //   5864: invokestatic 987	org/mozilla/javascript/ScriptRuntime:newObjectLiteral	([Ljava/lang/Object;[Ljava/lang/Object;[ILorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Scriptable;
    //   5867: astore 16
    //   5869: goto +1587 -> 7456
    //   5872: iload 12
    //   5874: bipush -31
    //   5876: if_icmpne +1116 -> 6992
    //   5879: aload 15
    //   5881: getfield 223	org/mozilla/javascript/Interpreter$CallFrame:idata	Lorg/mozilla/javascript/InterpreterData;
    //   5884: getfield 983	org/mozilla/javascript/InterpreterData:literalIds	[Ljava/lang/Object;
    //   5887: iload 10
    //   5889: aaload
    //   5890: checkcast 980	[I
    //   5893: checkcast 980	[I
    //   5896: astore 16
    //   5898: aload 17
    //   5900: aload 16
    //   5902: aload_0
    //   5903: aload 15
    //   5905: getfield 211	org/mozilla/javascript/Interpreter$CallFrame:scope	Lorg/mozilla/javascript/Scriptable;
    //   5908: invokestatic 991	org/mozilla/javascript/ScriptRuntime:newArrayLiteral	([Ljava/lang/Object;[ILorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Scriptable;
    //   5911: astore 16
    //   5913: goto +1543 -> 7456
    //   5916: aload 26
    //   5918: iload 9
    //   5920: aaload
    //   5921: astore 17
    //   5923: aload 17
    //   5925: astore 16
    //   5927: aload 17
    //   5929: aload 23
    //   5931: if_acmpne +13 -> 5944
    //   5934: aload 25
    //   5936: iload 9
    //   5938: daload
    //   5939: invokestatic 155	org/mozilla/javascript/ScriptRuntime:wrapNumber	(D)Ljava/lang/Number;
    //   5942: astore 16
    //   5944: iload 9
    //   5946: iconst_1
    //   5947: isub
    //   5948: istore 9
    //   5950: aload 15
    //   5952: aload 16
    //   5954: aload 15
    //   5956: getfield 211	org/mozilla/javascript/Interpreter$CallFrame:scope	Lorg/mozilla/javascript/Scriptable;
    //   5959: invokestatic 995	org/mozilla/javascript/ScriptRuntime:enterDotQuery	(Ljava/lang/Object;Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Scriptable;
    //   5962: putfield 211	org/mozilla/javascript/Interpreter$CallFrame:scope	Lorg/mozilla/javascript/Scriptable;
    //   5965: goto -5635 -> 330
    //   5968: aload 15
    //   5970: iload 9
    //   5972: invokestatic 757	org/mozilla/javascript/Interpreter:stack_boolean	(Lorg/mozilla/javascript/Interpreter$CallFrame;I)Z
    //   5975: aload 15
    //   5977: getfield 211	org/mozilla/javascript/Interpreter$CallFrame:scope	Lorg/mozilla/javascript/Scriptable;
    //   5980: invokestatic 999	org/mozilla/javascript/ScriptRuntime:updateDotQuery	(ZLorg/mozilla/javascript/Scriptable;)Ljava/lang/Object;
    //   5983: astore 16
    //   5985: aload 16
    //   5987: ifnull +1479 -> 7466
    //   5990: aload 26
    //   5992: iload 9
    //   5994: aload 16
    //   5996: aastore
    //   5997: aload 15
    //   5999: aload 15
    //   6001: getfield 211	org/mozilla/javascript/Interpreter$CallFrame:scope	Lorg/mozilla/javascript/Scriptable;
    //   6004: invokestatic 1002	org/mozilla/javascript/ScriptRuntime:leaveDotQuery	(Lorg/mozilla/javascript/Scriptable;)Lorg/mozilla/javascript/Scriptable;
    //   6007: putfield 211	org/mozilla/javascript/Interpreter$CallFrame:scope	Lorg/mozilla/javascript/Scriptable;
    //   6010: aload 15
    //   6012: aload 15
    //   6014: getfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   6017: iconst_2
    //   6018: iadd
    //   6019: putfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   6022: goto -5692 -> 330
    //   6025: aload 26
    //   6027: iload 9
    //   6029: aaload
    //   6030: astore 17
    //   6032: aload 17
    //   6034: astore 16
    //   6036: aload 17
    //   6038: aload 23
    //   6040: if_acmpne +13 -> 6053
    //   6043: aload 25
    //   6045: iload 9
    //   6047: daload
    //   6048: invokestatic 155	org/mozilla/javascript/ScriptRuntime:wrapNumber	(D)Ljava/lang/Number;
    //   6051: astore 16
    //   6053: aload 26
    //   6055: iload 9
    //   6057: aload 16
    //   6059: aload_0
    //   6060: invokestatic 1005	org/mozilla/javascript/ScriptRuntime:setDefaultNamespace	(Ljava/lang/Object;Lorg/mozilla/javascript/Context;)Ljava/lang/Object;
    //   6063: aastore
    //   6064: goto -5734 -> 330
    //   6067: aload 26
    //   6069: iload 9
    //   6071: aaload
    //   6072: astore 16
    //   6074: aload 16
    //   6076: aload 23
    //   6078: if_acmpeq -5748 -> 330
    //   6081: aload 26
    //   6083: iload 9
    //   6085: aload 16
    //   6087: aload_0
    //   6088: invokestatic 1009	org/mozilla/javascript/ScriptRuntime:escapeAttributeValue	(Ljava/lang/Object;Lorg/mozilla/javascript/Context;)Ljava/lang/String;
    //   6091: aastore
    //   6092: goto -5762 -> 330
    //   6095: aload 26
    //   6097: iload 9
    //   6099: aaload
    //   6100: astore 16
    //   6102: aload 16
    //   6104: aload 23
    //   6106: if_acmpeq -5776 -> 330
    //   6109: aload 26
    //   6111: iload 9
    //   6113: aload 16
    //   6115: aload_0
    //   6116: invokestatic 1012	org/mozilla/javascript/ScriptRuntime:escapeTextValue	(Ljava/lang/Object;Lorg/mozilla/javascript/Context;)Ljava/lang/String;
    //   6119: aastore
    //   6120: goto -5790 -> 330
    //   6123: aload 15
    //   6125: getfield 366	org/mozilla/javascript/Interpreter$CallFrame:debuggerFrame	Lorg/mozilla/javascript/debug/DebugFrame;
    //   6128: ifnull -5798 -> 330
    //   6131: aload 15
    //   6133: getfield 366	org/mozilla/javascript/Interpreter$CallFrame:debuggerFrame	Lorg/mozilla/javascript/debug/DebugFrame;
    //   6136: aload_0
    //   6137: invokeinterface 1015 2 0
    //   6142: goto -5812 -> 330
    //   6145: aload 15
    //   6147: aload 15
    //   6149: getfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   6152: putfield 577	org/mozilla/javascript/Interpreter$CallFrame:pcSourceLineStart	I
    //   6155: aload 15
    //   6157: getfield 366	org/mozilla/javascript/Interpreter$CallFrame:debuggerFrame	Lorg/mozilla/javascript/debug/DebugFrame;
    //   6160: ifnull +28 -> 6188
    //   6163: aload 27
    //   6165: aload 15
    //   6167: getfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   6170: invokestatic 203	org/mozilla/javascript/Interpreter:getIndex	([BI)I
    //   6173: istore 11
    //   6175: aload 15
    //   6177: getfield 366	org/mozilla/javascript/Interpreter$CallFrame:debuggerFrame	Lorg/mozilla/javascript/debug/DebugFrame;
    //   6180: aload_0
    //   6181: iload 11
    //   6183: invokeinterface 1019 3 0
    //   6188: aload 15
    //   6190: aload 15
    //   6192: getfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   6195: iconst_2
    //   6196: iadd
    //   6197: putfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   6200: goto -5870 -> 330
    //   6203: aload 27
    //   6205: aload 15
    //   6207: getfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   6210: baload
    //   6211: sipush 255
    //   6214: iand
    //   6215: istore 10
    //   6217: aload 15
    //   6219: aload 15
    //   6221: getfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   6224: iconst_1
    //   6225: iadd
    //   6226: putfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   6229: goto -5899 -> 330
    //   6232: aload 27
    //   6234: aload 15
    //   6236: getfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   6239: invokestatic 203	org/mozilla/javascript/Interpreter:getIndex	([BI)I
    //   6242: istore 10
    //   6244: aload 15
    //   6246: aload 15
    //   6248: getfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   6251: iconst_2
    //   6252: iadd
    //   6253: putfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   6256: goto -5926 -> 330
    //   6259: aload 27
    //   6261: aload 15
    //   6263: getfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   6266: invokestatic 906	org/mozilla/javascript/Interpreter:getInt	([BI)I
    //   6269: istore 10
    //   6271: aload 15
    //   6273: aload 15
    //   6275: getfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   6278: iconst_4
    //   6279: iadd
    //   6280: putfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   6283: goto -5953 -> 330
    //   6286: aload 31
    //   6288: aload 27
    //   6290: aload 15
    //   6292: getfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   6295: baload
    //   6296: sipush 255
    //   6299: iand
    //   6300: aaload
    //   6301: astore 16
    //   6303: aload 15
    //   6305: astore 21
    //   6307: aload 16
    //   6309: astore 19
    //   6311: aload 18
    //   6313: astore 20
    //   6315: aload 15
    //   6317: aload 15
    //   6319: getfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   6322: iconst_1
    //   6323: iadd
    //   6324: putfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   6327: aload 16
    //   6329: astore_1
    //   6330: goto -6000 -> 330
    //   6333: aload 31
    //   6335: aload 27
    //   6337: aload 15
    //   6339: getfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   6342: invokestatic 203	org/mozilla/javascript/Interpreter:getIndex	([BI)I
    //   6345: aaload
    //   6346: astore 16
    //   6348: aload 15
    //   6350: astore 21
    //   6352: aload 16
    //   6354: astore 19
    //   6356: aload 18
    //   6358: astore 20
    //   6360: aload 15
    //   6362: aload 15
    //   6364: getfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   6367: iconst_2
    //   6368: iadd
    //   6369: putfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   6372: aload 16
    //   6374: astore_1
    //   6375: goto -6045 -> 330
    //   6378: aload 31
    //   6380: aload 27
    //   6382: aload 15
    //   6384: getfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   6387: invokestatic 906	org/mozilla/javascript/Interpreter:getInt	([BI)I
    //   6390: aaload
    //   6391: astore 16
    //   6393: aload 15
    //   6395: astore 21
    //   6397: aload 16
    //   6399: astore 19
    //   6401: aload 18
    //   6403: astore 20
    //   6405: aload 15
    //   6407: aload 15
    //   6409: getfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   6412: iconst_4
    //   6413: iadd
    //   6414: putfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   6417: aload 16
    //   6419: astore_1
    //   6420: goto -6090 -> 330
    //   6423: aload 15
    //   6425: aload 15
    //   6427: getfield 223	org/mozilla/javascript/Interpreter$CallFrame:idata	Lorg/mozilla/javascript/InterpreterData;
    //   6430: getfield 1023	org/mozilla/javascript/InterpreterData:longJumps	Lorg/mozilla/javascript/UintMap;
    //   6433: aload 15
    //   6435: getfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   6438: invokevirtual 1026	org/mozilla/javascript/UintMap:getExistingInt	(I)I
    //   6441: putfield 50	org/mozilla/javascript/Interpreter$CallFrame:pc	I
    //   6444: goto -4230 -> 2214
    //   6447: aload 16
    //   6449: astore_2
    //   6450: aload 18
    //   6452: astore_1
    //   6453: aload_0
    //   6454: getfield 661	org/mozilla/javascript/Context:previousInterpreterInvocations	Lorg/mozilla/javascript/ObjArray;
    //   6457: ifnull +386 -> 6843
    //   6460: aload_0
    //   6461: getfield 661	org/mozilla/javascript/Context:previousInterpreterInvocations	Lorg/mozilla/javascript/ObjArray;
    //   6464: invokevirtual 1029	org/mozilla/javascript/ObjArray:size	()I
    //   6467: ifeq +376 -> 6843
    //   6470: aload_0
    //   6471: aload_0
    //   6472: getfield 661	org/mozilla/javascript/Context:previousInterpreterInvocations	Lorg/mozilla/javascript/ObjArray;
    //   6475: invokevirtual 1033	org/mozilla/javascript/ObjArray:pop	()Ljava/lang/Object;
    //   6478: putfield 78	org/mozilla/javascript/Context:lastInterpreterFrame	Ljava/lang/Object;
    //   6481: aload_1
    //   6482: ifnull +379 -> 6861
    //   6485: aload_1
    //   6486: instanceof 655
    //   6489: ifeq +367 -> 6856
    //   6492: aload_1
    //   6493: checkcast 655	java/lang/RuntimeException
    //   6496: athrow
    //   6497: aload 15
    //   6499: astore 17
    //   6501: aload 16
    //   6503: astore 15
    //   6505: aload_1
    //   6506: astore 16
    //   6508: goto -4859 -> 1649
    //   6511: aload 16
    //   6513: instanceof 732
    //   6516: ifeq +11 -> 6527
    //   6519: iconst_2
    //   6520: istore 9
    //   6522: aconst_null
    //   6523: astore_1
    //   6524: goto -4837 -> 1687
    //   6527: aload 16
    //   6529: instanceof 1035
    //   6532: ifeq +11 -> 6543
    //   6535: iconst_2
    //   6536: istore 9
    //   6538: aconst_null
    //   6539: astore_1
    //   6540: goto -4853 -> 1687
    //   6543: aload 16
    //   6545: instanceof 1037
    //   6548: ifeq +11 -> 6559
    //   6551: iconst_2
    //   6552: istore 9
    //   6554: aconst_null
    //   6555: astore_1
    //   6556: goto -4869 -> 1687
    //   6559: aload 16
    //   6561: instanceof 1039
    //   6564: ifeq +11 -> 6575
    //   6567: iconst_0
    //   6568: istore 9
    //   6570: aconst_null
    //   6571: astore_1
    //   6572: goto -4885 -> 1687
    //   6575: aload 16
    //   6577: instanceof 655
    //   6580: ifeq +26 -> 6606
    //   6583: aload_0
    //   6584: bipush 13
    //   6586: invokevirtual 1042	org/mozilla/javascript/Context:hasFeature	(I)Z
    //   6589: ifeq +11 -> 6600
    //   6592: iconst_2
    //   6593: istore 9
    //   6595: aconst_null
    //   6596: astore_1
    //   6597: goto -4910 -> 1687
    //   6600: iconst_1
    //   6601: istore 9
    //   6603: goto -8 -> 6595
    //   6606: aload 16
    //   6608: instanceof 657
    //   6611: ifeq +26 -> 6637
    //   6614: aload_0
    //   6615: bipush 13
    //   6617: invokevirtual 1042	org/mozilla/javascript/Context:hasFeature	(I)Z
    //   6620: ifeq +11 -> 6631
    //   6623: iconst_2
    //   6624: istore 9
    //   6626: aconst_null
    //   6627: astore_1
    //   6628: goto -4941 -> 1687
    //   6631: iconst_0
    //   6632: istore 9
    //   6634: goto -8 -> 6626
    //   6637: aload 16
    //   6639: instanceof 13
    //   6642: ifeq +15 -> 6657
    //   6645: iconst_1
    //   6646: istore 9
    //   6648: aload 16
    //   6650: checkcast 13	org/mozilla/javascript/Interpreter$ContinuationJump
    //   6653: astore_1
    //   6654: goto -4967 -> 1687
    //   6657: aload_0
    //   6658: bipush 13
    //   6660: invokevirtual 1042	org/mozilla/javascript/Context:hasFeature	(I)Z
    //   6663: ifeq +11 -> 6674
    //   6666: iconst_2
    //   6667: istore 9
    //   6669: aconst_null
    //   6670: astore_1
    //   6671: goto -4984 -> 1687
    //   6674: iconst_1
    //   6675: istore 9
    //   6677: goto -8 -> 6669
    //   6680: astore 16
    //   6682: iconst_1
    //   6683: istore 9
    //   6685: goto -4985 -> 1700
    //   6688: astore 16
    //   6690: iconst_0
    //   6691: istore 9
    //   6693: aconst_null
    //   6694: astore_1
    //   6695: goto -4995 -> 1700
    //   6698: astore_1
    //   6699: aconst_null
    //   6700: astore 19
    //   6702: iconst_0
    //   6703: istore 9
    //   6705: aload 15
    //   6707: astore 18
    //   6709: goto -4963 -> 1746
    //   6712: iconst_0
    //   6713: istore 14
    //   6715: goto -4955 -> 1760
    //   6718: aload_0
    //   6719: aload 18
    //   6721: aload_1
    //   6722: invokestatic 591	org/mozilla/javascript/Interpreter:exitFrame	(Lorg/mozilla/javascript/Context;Lorg/mozilla/javascript/Interpreter$CallFrame;Ljava/lang/Object;)V
    //   6725: aload 18
    //   6727: getfield 123	org/mozilla/javascript/Interpreter$CallFrame:parentFrame	Lorg/mozilla/javascript/Interpreter$CallFrame;
    //   6730: astore 15
    //   6732: aload 15
    //   6734: ifnonnull +48 -> 6782
    //   6737: aload 19
    //   6739: ifnull +247 -> 6986
    //   6742: aload 19
    //   6744: getfield 1045	org/mozilla/javascript/Interpreter$ContinuationJump:branchFrame	Lorg/mozilla/javascript/Interpreter$CallFrame;
    //   6747: ifnull +7 -> 6754
    //   6750: invokestatic 72	org/mozilla/javascript/Kit:codeBug	()Ljava/lang/RuntimeException;
    //   6753: pop
    //   6754: aload 19
    //   6756: getfield 1048	org/mozilla/javascript/Interpreter$ContinuationJump:capturedFrame	Lorg/mozilla/javascript/Interpreter$CallFrame;
    //   6759: ifnull +66 -> 6825
    //   6762: iconst_m1
    //   6763: istore 10
    //   6765: aload_1
    //   6766: astore 18
    //   6768: aload 15
    //   6770: astore 16
    //   6772: aload 17
    //   6774: astore_1
    //   6775: aload 18
    //   6777: astore 17
    //   6779: goto -6672 -> 107
    //   6782: aload 15
    //   6784: astore 18
    //   6786: aload 19
    //   6788: ifnull -5042 -> 1746
    //   6791: aload 15
    //   6793: astore 18
    //   6795: aload 19
    //   6797: getfield 1045	org/mozilla/javascript/Interpreter$ContinuationJump:branchFrame	Lorg/mozilla/javascript/Interpreter$CallFrame;
    //   6800: aload 15
    //   6802: if_acmpne -5056 -> 1746
    //   6805: iconst_m1
    //   6806: istore 10
    //   6808: aload_1
    //   6809: astore 18
    //   6811: aload 15
    //   6813: astore 16
    //   6815: aload 17
    //   6817: astore_1
    //   6818: aload 18
    //   6820: astore 17
    //   6822: goto -6715 -> 107
    //   6825: aload 19
    //   6827: getfield 419	org/mozilla/javascript/Interpreter$ContinuationJump:result	Ljava/lang/Object;
    //   6830: astore_2
    //   6831: aload 19
    //   6833: getfield 420	org/mozilla/javascript/Interpreter$ContinuationJump:resultDbl	D
    //   6836: dstore 5
    //   6838: aconst_null
    //   6839: astore_1
    //   6840: goto -387 -> 6453
    //   6843: aload_0
    //   6844: aconst_null
    //   6845: putfield 78	org/mozilla/javascript/Context:lastInterpreterFrame	Ljava/lang/Object;
    //   6848: aload_0
    //   6849: aconst_null
    //   6850: putfield 661	org/mozilla/javascript/Context:previousInterpreterInvocations	Lorg/mozilla/javascript/ObjArray;
    //   6853: goto -372 -> 6481
    //   6856: aload_1
    //   6857: checkcast 657	java/lang/Error
    //   6860: athrow
    //   6861: aload_2
    //   6862: aload 23
    //   6864: if_acmpne +193 -> 7057
    //   6867: dload 5
    //   6869: invokestatic 155	org/mozilla/javascript/ScriptRuntime:wrapNumber	(D)Ljava/lang/Number;
    //   6872: areturn
    //   6873: astore 19
    //   6875: aload 15
    //   6877: astore 17
    //   6879: aload 16
    //   6881: astore_2
    //   6882: aload 19
    //   6884: astore 16
    //   6886: aload_1
    //   6887: astore 15
    //   6889: aload 16
    //   6891: astore_1
    //   6892: dload 5
    //   6894: dstore_3
    //   6895: aload 17
    //   6897: astore 16
    //   6899: aload 18
    //   6901: astore 17
    //   6903: goto -5574 -> 1329
    //   6906: astore_2
    //   6907: aload 15
    //   6909: astore 17
    //   6911: aload_1
    //   6912: astore 15
    //   6914: aload_2
    //   6915: astore_1
    //   6916: aload 16
    //   6918: astore_2
    //   6919: dload 5
    //   6921: dstore_3
    //   6922: aload 17
    //   6924: astore 16
    //   6926: aload 18
    //   6928: astore 17
    //   6930: goto -5601 -> 1329
    //   6933: astore 19
    //   6935: aload_1
    //   6936: astore 16
    //   6938: aload 15
    //   6940: astore 18
    //   6942: aload 19
    //   6944: astore_1
    //   6945: aload 16
    //   6947: astore 15
    //   6949: aload 18
    //   6951: astore 16
    //   6953: goto -5624 -> 1329
    //   6956: astore 20
    //   6958: aload_1
    //   6959: astore 17
    //   6961: aload 15
    //   6963: astore 19
    //   6965: aload 16
    //   6967: astore_2
    //   6968: aload 20
    //   6970: astore_1
    //   6971: aload 17
    //   6973: astore 15
    //   6975: aload 19
    //   6977: astore 16
    //   6979: aload 18
    //   6981: astore 17
    //   6983: goto -5654 -> 1329
    //   6986: dload_3
    //   6987: dstore 5
    //   6989: goto -536 -> 6453
    //   6992: aconst_null
    //   6993: astore 16
    //   6995: goto -1097 -> 5898
    //   6998: goto -1377 -> 5621
    //   7001: goto -1866 -> 5135
    //   7004: aload 15
    //   7006: astore 17
    //   7008: goto -3194 -> 3814
    //   7011: goto -3521 -> 3490
    //   7014: goto -3810 -> 3204
    //   7017: goto -4034 -> 2983
    //   7020: goto -4205 -> 2815
    //   7023: goto -4293 -> 2730
    //   7026: goto -4856 -> 2170
    //   7029: goto -2213 -> 4816
    //   7032: goto -2263 -> 4769
    //   7035: goto -2313 -> 4722
    //   7038: aload_1
    //   7039: astore 19
    //   7041: aload 16
    //   7043: astore_1
    //   7044: aload 15
    //   7046: astore 18
    //   7048: goto -5302 -> 1746
    //   7051: goto -5351 -> 1700
    //   7054: goto -5794 -> 1260
    //   7057: aload_2
    //   7058: areturn
    //   7059: iconst_0
    //   7060: istore 9
    //   7062: goto -5072 -> 1990
    //   7065: iconst_0
    //   7066: istore 9
    //   7068: goto -5031 -> 2037
    //   7071: iload 11
    //   7073: iconst_1
    //   7074: isub
    //   7075: istore 9
    //   7077: aload 26
    //   7079: iload 11
    //   7081: aconst_null
    //   7082: aastore
    //   7083: iload 9
    //   7085: istore 11
    //   7087: goto -4917 -> 2170
    //   7090: aload 26
    //   7092: iload 9
    //   7094: aconst_null
    //   7095: aastore
    //   7096: iload 9
    //   7098: iconst_1
    //   7099: isub
    //   7100: istore 9
    //   7102: goto -6772 -> 330
    //   7105: aload 26
    //   7107: iload 9
    //   7109: iconst_1
    //   7110: iadd
    //   7111: aload 26
    //   7113: iload 9
    //   7115: aaload
    //   7116: aastore
    //   7117: aload 25
    //   7119: iload 9
    //   7121: iconst_1
    //   7122: iadd
    //   7123: aload 25
    //   7125: iload 9
    //   7127: daload
    //   7128: dastore
    //   7129: iload 9
    //   7131: iconst_1
    //   7132: iadd
    //   7133: istore 9
    //   7135: goto -6805 -> 330
    //   7138: aload 26
    //   7140: iload 9
    //   7142: iconst_1
    //   7143: iadd
    //   7144: aload 26
    //   7146: iload 9
    //   7148: iconst_1
    //   7149: isub
    //   7150: aaload
    //   7151: aastore
    //   7152: aload 25
    //   7154: iload 9
    //   7156: iconst_1
    //   7157: iadd
    //   7158: aload 25
    //   7160: iload 9
    //   7162: iconst_1
    //   7163: isub
    //   7164: daload
    //   7165: dastore
    //   7166: aload 26
    //   7168: iload 9
    //   7170: iconst_2
    //   7171: iadd
    //   7172: aload 26
    //   7174: iload 9
    //   7176: aaload
    //   7177: aastore
    //   7178: aload 25
    //   7180: iload 9
    //   7182: iconst_2
    //   7183: iadd
    //   7184: aload 25
    //   7186: iload 9
    //   7188: daload
    //   7189: dastore
    //   7190: iload 9
    //   7192: iconst_2
    //   7193: iadd
    //   7194: istore 9
    //   7196: goto -6866 -> 330
    //   7199: aload 26
    //   7201: iload 9
    //   7203: aaload
    //   7204: astore 16
    //   7206: aload 26
    //   7208: iload 9
    //   7210: aload 26
    //   7212: iload 9
    //   7214: iconst_1
    //   7215: isub
    //   7216: aaload
    //   7217: aastore
    //   7218: aload 26
    //   7220: iload 9
    //   7222: iconst_1
    //   7223: isub
    //   7224: aload 16
    //   7226: aastore
    //   7227: aload 25
    //   7229: iload 9
    //   7231: daload
    //   7232: dstore 5
    //   7234: aload 25
    //   7236: iload 9
    //   7238: aload 25
    //   7240: iload 9
    //   7242: iconst_1
    //   7243: isub
    //   7244: daload
    //   7245: dastore
    //   7246: aload 25
    //   7248: iload 9
    //   7250: iconst_1
    //   7251: isub
    //   7252: dload 5
    //   7254: dastore
    //   7255: goto -6925 -> 330
    //   7258: aload 25
    //   7260: iload 9
    //   7262: dload 5
    //   7264: dastore
    //   7265: goto -6935 -> 330
    //   7268: iconst_0
    //   7269: istore 14
    //   7271: goto -4602 -> 2669
    //   7274: aload 26
    //   7276: iload 9
    //   7278: aload 16
    //   7280: aastore
    //   7281: goto -6951 -> 330
    //   7284: aload 19
    //   7286: astore 16
    //   7288: aload 18
    //   7290: astore 17
    //   7292: goto -7185 -> 107
    //   7295: aload_1
    //   7296: astore 17
    //   7298: goto -5649 -> 1649
    //   7301: iload 9
    //   7303: iconst_1
    //   7304: iadd
    //   7305: istore 9
    //   7307: aload 26
    //   7309: iload 9
    //   7311: aload_1
    //   7312: aastore
    //   7313: goto -6983 -> 330
    //   7316: iload 9
    //   7318: iconst_1
    //   7319: iadd
    //   7320: istore 9
    //   7322: aload 26
    //   7324: iload 9
    //   7326: aload 23
    //   7328: aastore
    //   7329: aload 25
    //   7331: iload 9
    //   7333: dconst_0
    //   7334: dastore
    //   7335: goto -7005 -> 330
    //   7338: iload 9
    //   7340: iconst_1
    //   7341: iadd
    //   7342: istore 9
    //   7344: aload 26
    //   7346: iload 9
    //   7348: aload 23
    //   7350: aastore
    //   7351: aload 25
    //   7353: iload 9
    //   7355: dconst_1
    //   7356: dastore
    //   7357: goto -7027 -> 330
    //   7360: iload 9
    //   7362: iconst_1
    //   7363: iadd
    //   7364: istore 9
    //   7366: aload 26
    //   7368: iload 9
    //   7370: aconst_null
    //   7371: aastore
    //   7372: goto -7042 -> 330
    //   7375: iload 9
    //   7377: iconst_1
    //   7378: iadd
    //   7379: istore 9
    //   7381: aload 26
    //   7383: iload 9
    //   7385: aload 24
    //   7387: aastore
    //   7388: goto -7058 -> 330
    //   7391: iconst_0
    //   7392: istore 9
    //   7394: goto -2354 -> 5040
    //   7397: iload 12
    //   7399: bipush 59
    //   7401: if_icmpne +9 -> 7410
    //   7404: iconst_1
    //   7405: istore 9
    //   7407: goto -2246 -> 5161
    //   7410: iload 12
    //   7412: bipush 61
    //   7414: if_icmpne +10 -> 7424
    //   7417: bipush 6
    //   7419: istore 9
    //   7421: goto -2260 -> 5161
    //   7424: iconst_2
    //   7425: istore 9
    //   7427: goto -2266 -> 5161
    //   7430: aload 26
    //   7432: iload 9
    //   7434: aload 16
    //   7436: aastore
    //   7437: goto -7107 -> 330
    //   7440: iload 9
    //   7442: iconst_1
    //   7443: iadd
    //   7444: istore 9
    //   7446: aload 26
    //   7448: iload 9
    //   7450: aload 16
    //   7452: aastore
    //   7453: goto -7123 -> 330
    //   7456: aload 26
    //   7458: iload 9
    //   7460: aload 16
    //   7462: aastore
    //   7463: goto -7133 -> 330
    //   7466: iload 9
    //   7468: iconst_1
    //   7469: isub
    //   7470: istore 11
    //   7472: goto -5302 -> 2170
    //   7475: iconst_0
    //   7476: istore 10
    //   7478: goto -7148 -> 330
    //   7481: iconst_1
    //   7482: istore 10
    //   7484: goto -7154 -> 330
    //   7487: iconst_2
    //   7488: istore 10
    //   7490: goto -7160 -> 330
    //   7493: iconst_3
    //   7494: istore 10
    //   7496: goto -7166 -> 330
    //   7499: iconst_4
    //   7500: istore 10
    //   7502: goto -7172 -> 330
    //   7505: iconst_5
    //   7506: istore 10
    //   7508: goto -7178 -> 330
    //   7511: aload 31
    //   7513: iconst_0
    //   7514: aaload
    //   7515: astore_1
    //   7516: goto -7186 -> 330
    //   7519: aload 31
    //   7521: iconst_1
    //   7522: aaload
    //   7523: astore_1
    //   7524: goto -7194 -> 330
    //   7527: aload 31
    //   7529: iconst_2
    //   7530: aaload
    //   7531: astore_1
    //   7532: goto -7202 -> 330
    //   7535: aload 31
    //   7537: iconst_3
    //   7538: aaload
    //   7539: astore_1
    //   7540: goto -7210 -> 330
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	7543	0	paramContext	Context
    //   0	7543	1	paramCallFrame	CallFrame
    //   0	7543	2	paramObject	Object
    //   91	6896	3	d1	double
    //   1530	5733	5	d2	double
    //   2591	21	7	d3	double
    //   311	7159	9	i	int
    //   21	7486	10	j	int
    //   335	7136	11	k	int
    //   351	7064	12	m	int
    //   18	4171	13	bool1	boolean
    //   1758	241	14	n	int
    //   2025	21	14	i1	int
    //   2667	4603	14	bool2	boolean
    //   93	6952	15	localObject1	Object
    //   99	6550	16	localObject2	Object
    //   6680	1	16	localRuntimeException	RuntimeException
    //   6688	1	16	localError	Error
    //   6770	691	16	localObject3	Object
    //   96	7201	17	localObject4	Object
    //   88	7201	18	localObject5	Object
    //   117	202	19	localCallFrame	CallFrame
    //   1313	14	19	localThrowable1	Throwable
    //   1398	5434	19	localObject6	Object
    //   6873	10	19	localThrowable2	Throwable
    //   6933	10	19	localThrowable3	Throwable
    //   6963	322	19	localObject7	Object
    //   121	6283	20	localObject8	Object
    //   6956	13	20	localThrowable4	Throwable
    //   114	6282	21	localObject9	Object
    //   74	1736	22	localGeneratorState	GeneratorState
    //   3	7346	23	localUniqueTag	UniqueTag
    //   8	7378	24	localObject10	Object
    //   188	7164	25	arrayOfDouble1	double[]
    //   170	7287	26	arrayOfObject1	Object[]
    //   272	6109	27	arrayOfByte	byte[]
    //   209	4639	28	arrayOfObject2	Object[]
    //   230	4620	29	arrayOfDouble2	double[]
    //   251	4601	30	arrayOfInt	int[]
    //   293	7243	31	arrayOfString	String[]
    // Exception table:
    //   from	to	target	type
    //   330	346	1313	java/lang/Throwable
    //   1260	1313	1313	java/lang/Throwable
    //   1454	1511	1313	java/lang/Throwable
    //   1511	1525	1313	java/lang/Throwable
    //   1602	1621	1313	java/lang/Throwable
    //   1621	1639	1313	java/lang/Throwable
    //   1791	1839	1313	java/lang/Throwable
    //   1860	1870	1313	java/lang/Throwable
    //   1870	1903	1313	java/lang/Throwable
    //   1909	1922	1313	java/lang/Throwable
    //   1928	1943	1313	java/lang/Throwable
    //   1946	1960	1313	java/lang/Throwable
    //   1969	1980	1313	java/lang/Throwable
    //   1990	2003	1313	java/lang/Throwable
    //   2016	2027	1313	java/lang/Throwable
    //   2037	2050	1313	java/lang/Throwable
    //   2067	2089	1313	java/lang/Throwable
    //   2106	2128	1313	java/lang/Throwable
    //   2141	2163	1313	java/lang/Throwable
    //   2175	2182	1313	java/lang/Throwable
    //   2182	2194	1313	java/lang/Throwable
    //   2199	2214	1313	java/lang/Throwable
    //   2223	2233	1313	java/lang/Throwable
    //   2253	2266	1313	java/lang/Throwable
    //   2269	2291	1313	java/lang/Throwable
    //   2320	2334	1313	java/lang/Throwable
    //   2342	2349	1313	java/lang/Throwable
    //   2349	2359	1313	java/lang/Throwable
    //   2379	2390	1313	java/lang/Throwable
    //   2399	2409	1313	java/lang/Throwable
    //   2416	2436	1313	java/lang/Throwable
    //   2451	2471	1313	java/lang/Throwable
    //   2474	2481	1313	java/lang/Throwable
    //   2484	2493	1313	java/lang/Throwable
    //   2513	2528	1313	java/lang/Throwable
    //   2531	2551	1313	java/lang/Throwable
    //   2564	2581	1313	java/lang/Throwable
    //   2584	2593	1313	java/lang/Throwable
    //   2625	2635	1313	java/lang/Throwable
    //   2638	2653	1313	java/lang/Throwable
    //   2656	2666	1313	java/lang/Throwable
    //   2669	2679	1313	java/lang/Throwable
    //   2688	2703	1313	java/lang/Throwable
    //   2720	2730	1313	java/lang/Throwable
    //   2736	2746	1313	java/lang/Throwable
    //   2753	2769	1313	java/lang/Throwable
    //   2772	2788	1313	java/lang/Throwable
    //   2805	2815	1313	java/lang/Throwable
    //   2821	2841	1313	java/lang/Throwable
    //   2844	2860	1313	java/lang/Throwable
    //   2881	2891	1313	java/lang/Throwable
    //   2891	2908	1313	java/lang/Throwable
    //   2929	2939	1313	java/lang/Throwable
    //   2939	2956	1313	java/lang/Throwable
    //   2973	2983	1313	java/lang/Throwable
    //   3007	3017	1313	java/lang/Throwable
    //   3017	3036	1313	java/lang/Throwable
    //   3057	3067	1313	java/lang/Throwable
    //   3067	3104	1313	java/lang/Throwable
    //   3107	3121	1313	java/lang/Throwable
    //   3124	3138	1313	java/lang/Throwable
    //   3141	3157	1313	java/lang/Throwable
    //   3160	3177	1313	java/lang/Throwable
    //   3194	3204	1313	java/lang/Throwable
    //   3210	3234	1313	java/lang/Throwable
    //   3237	3254	1313	java/lang/Throwable
    //   3257	3299	1313	java/lang/Throwable
    //   3308	3318	1313	java/lang/Throwable
    //   3341	3351	1313	java/lang/Throwable
    //   3366	3381	1313	java/lang/Throwable
    //   3387	3396	1313	java/lang/Throwable
    //   3417	3427	1313	java/lang/Throwable
    //   3427	3444	1313	java/lang/Throwable
    //   3450	3459	1313	java/lang/Throwable
    //   3478	3490	1313	java/lang/Throwable
    //   3508	3518	1313	java/lang/Throwable
    //   3518	3547	1313	java/lang/Throwable
    //   3568	3578	1313	java/lang/Throwable
    //   3578	3589	1313	java/lang/Throwable
    //   3595	3604	1313	java/lang/Throwable
    //   3612	3623	1313	java/lang/Throwable
    //   3623	3641	1313	java/lang/Throwable
    //   3649	3660	1313	java/lang/Throwable
    //   3669	3691	1313	java/lang/Throwable
    //   3698	3724	1313	java/lang/Throwable
    //   3727	3734	1313	java/lang/Throwable
    //   3734	3752	1313	java/lang/Throwable
    //   3752	3793	1313	java/lang/Throwable
    //   3800	3814	1313	java/lang/Throwable
    //   3814	3838	1313	java/lang/Throwable
    //   3845	3859	1313	java/lang/Throwable
    //   3862	3886	1313	java/lang/Throwable
    //   3891	3898	1313	java/lang/Throwable
    //   3901	3925	1313	java/lang/Throwable
    //   3928	3969	1313	java/lang/Throwable
    //   3972	4042	1313	java/lang/Throwable
    //   4049	4128	1313	java/lang/Throwable
    //   4135	4185	1313	java/lang/Throwable
    //   4193	4204	1313	java/lang/Throwable
    //   4218	4299	1313	java/lang/Throwable
    //   4306	4320	1313	java/lang/Throwable
    //   4327	4335	1313	java/lang/Throwable
    //   4346	4356	1313	java/lang/Throwable
    //   4356	4362	1313	java/lang/Throwable
    //   4362	4406	1313	java/lang/Throwable
    //   4409	4444	1313	java/lang/Throwable
    //   4465	4475	1313	java/lang/Throwable
    //   4475	4485	1313	java/lang/Throwable
    //   4494	4508	1313	java/lang/Throwable
    //   4524	4552	1313	java/lang/Throwable
    //   4568	4596	1313	java/lang/Throwable
    //   4612	4628	1313	java/lang/Throwable
    //   4637	4652	1313	java/lang/Throwable
    //   4661	4696	1313	java/lang/Throwable
    //   4699	4715	1313	java/lang/Throwable
    //   4722	4743	1313	java/lang/Throwable
    //   4746	4762	1313	java/lang/Throwable
    //   4769	4790	1313	java/lang/Throwable
    //   4793	4809	1313	java/lang/Throwable
    //   4816	4835	1313	java/lang/Throwable
    //   4838	4860	1313	java/lang/Throwable
    //   4869	4879	1313	java/lang/Throwable
    //   4888	4898	1313	java/lang/Throwable
    //   4907	4915	1313	java/lang/Throwable
    //   4924	4932	1313	java/lang/Throwable
    //   4953	4963	1313	java/lang/Throwable
    //   4969	4985	1313	java/lang/Throwable
    //   4988	5001	1313	java/lang/Throwable
    //   5010	5037	1313	java/lang/Throwable
    //   5040	5052	1313	java/lang/Throwable
    //   5060	5091	1313	java/lang/Throwable
    //   5098	5108	1313	java/lang/Throwable
    //   5125	5135	1313	java/lang/Throwable
    //   5141	5151	1313	java/lang/Throwable
    //   5161	5179	1313	java/lang/Throwable
    //   5186	5196	1313	java/lang/Throwable
    //   5216	5223	1313	java/lang/Throwable
    //   5226	5234	1313	java/lang/Throwable
    //   5255	5265	1313	java/lang/Throwable
    //   5265	5282	1313	java/lang/Throwable
    //   5285	5299	1313	java/lang/Throwable
    //   5302	5316	1313	java/lang/Throwable
    //   5337	5347	1313	java/lang/Throwable
    //   5347	5365	1313	java/lang/Throwable
    //   5368	5384	1313	java/lang/Throwable
    //   5387	5410	1313	java/lang/Throwable
    //   5413	5433	1313	java/lang/Throwable
    //   5436	5466	1313	java/lang/Throwable
    //   5472	5497	1313	java/lang/Throwable
    //   5500	5516	1313	java/lang/Throwable
    //   5519	5532	1313	java/lang/Throwable
    //   5538	5554	1313	java/lang/Throwable
    //   5563	5572	1313	java/lang/Throwable
    //   5578	5588	1313	java/lang/Throwable
    //   5611	5621	1313	java/lang/Throwable
    //   5635	5651	1313	java/lang/Throwable
    //   5685	5718	1313	java/lang/Throwable
    //   5752	5785	1313	java/lang/Throwable
    //   5798	5811	1313	java/lang/Throwable
    //   5817	5830	1313	java/lang/Throwable
    //   5837	5869	1313	java/lang/Throwable
    //   5879	5898	1313	java/lang/Throwable
    //   5898	5913	1313	java/lang/Throwable
    //   5934	5944	1313	java/lang/Throwable
    //   5950	5965	1313	java/lang/Throwable
    //   5968	5985	1313	java/lang/Throwable
    //   5997	6022	1313	java/lang/Throwable
    //   6043	6053	1313	java/lang/Throwable
    //   6053	6064	1313	java/lang/Throwable
    //   6081	6092	1313	java/lang/Throwable
    //   6109	6120	1313	java/lang/Throwable
    //   6123	6142	1313	java/lang/Throwable
    //   6145	6188	1313	java/lang/Throwable
    //   6188	6200	1313	java/lang/Throwable
    //   6203	6229	1313	java/lang/Throwable
    //   6232	6256	1313	java/lang/Throwable
    //   6259	6283	1313	java/lang/Throwable
    //   6286	6303	1313	java/lang/Throwable
    //   6333	6348	1313	java/lang/Throwable
    //   6378	6393	1313	java/lang/Throwable
    //   6423	6444	1313	java/lang/Throwable
    //   123	137	1438	java/lang/Throwable
    //   165	172	1438	java/lang/Throwable
    //   183	190	1438	java/lang/Throwable
    //   201	211	1438	java/lang/Throwable
    //   222	232	1438	java/lang/Throwable
    //   243	253	1438	java/lang/Throwable
    //   264	274	1438	java/lang/Throwable
    //   285	295	1438	java/lang/Throwable
    //   306	313	1438	java/lang/Throwable
    //   324	330	1438	java/lang/Throwable
    //   1404	1412	1438	java/lang/Throwable
    //   1423	1427	1438	java/lang/Throwable
    //   6315	6327	1438	java/lang/Throwable
    //   6360	6372	1438	java/lang/Throwable
    //   6405	6417	1438	java/lang/Throwable
    //   1692	1700	6680	java/lang/RuntimeException
    //   1692	1700	6688	java/lang/Error
    //   1723	1736	6698	java/lang/Throwable
    //   1532	1547	6873	java/lang/Throwable
    //   1554	1562	6906	java/lang/Throwable
    //   1566	1572	6906	java/lang/Throwable
    //   1575	1583	6906	java/lang/Throwable
    //   137	144	6933	java/lang/Throwable
    //   148	154	6933	java/lang/Throwable
    //   1525	1532	6956	java/lang/Throwable
  }
  
  private static boolean isFrameEnterExitRequired(CallFrame paramCallFrame)
  {
    return (paramCallFrame.debuggerFrame != null) || (paramCallFrame.idata.itsNeedsActivation);
  }
  
  private static CallFrame processThrowable(Context paramContext, Object paramObject, CallFrame paramCallFrame, int paramInt, boolean paramBoolean)
  {
    int i;
    int j;
    int k;
    if (paramInt >= 0)
    {
      paramContext = paramCallFrame;
      if (paramCallFrame.frozen) {
        paramContext = paramCallFrame.cloneFrozen();
      }
      paramCallFrame = paramContext.idata.itsExceptionTable;
      paramContext.pc = paramCallFrame[(paramInt + 2)];
      if (paramBoolean) {
        paramContext.pcPrevBranch = paramContext.pc;
      }
      paramContext.savedStackTop = paramContext.emptyStackTop;
      i = paramContext.localShift;
      j = paramCallFrame[(paramInt + 5)];
      k = paramContext.localShift;
      paramInt = paramCallFrame[(paramInt + 4)];
      paramContext.scope = ((Scriptable)paramContext.stack[(i + j)]);
      paramContext.stack[(k + paramInt)] = paramObject;
    }
    for (;;)
    {
      paramContext.throwable = null;
      return paramContext;
      ContinuationJump localContinuationJump = (ContinuationJump)paramObject;
      if (localContinuationJump.branchFrame != paramCallFrame) {
        Kit.codeBug();
      }
      if (localContinuationJump.capturedFrame == null) {
        Kit.codeBug();
      }
      paramInt = localContinuationJump.capturedFrame.frameIndex + 1;
      i = paramInt;
      if (localContinuationJump.branchFrame != null) {
        i = paramInt - localContinuationJump.branchFrame.frameIndex;
      }
      CallFrame localCallFrame = localContinuationJump.capturedFrame;
      j = 0;
      paramInt = 0;
      paramObject = null;
      for (;;)
      {
        k = paramInt;
        if (j == i) {
          break;
        }
        if (!localCallFrame.frozen) {
          Kit.codeBug();
        }
        paramCallFrame = (CallFrame)paramObject;
        k = paramInt;
        if (isFrameEnterExitRequired(localCallFrame))
        {
          paramCallFrame = (CallFrame)paramObject;
          if (paramObject == null) {
            paramCallFrame = new CallFrame[i - j];
          }
          paramCallFrame[paramInt] = localCallFrame;
          k = paramInt + 1;
        }
        localCallFrame = localCallFrame.parentFrame;
        j += 1;
        paramObject = paramCallFrame;
        paramInt = k;
      }
      while (k != 0)
      {
        k -= 1;
        enterFrame(paramContext, paramObject[k], ScriptRuntime.emptyArgs, true);
      }
      paramContext = localContinuationJump.capturedFrame.cloneFrozen();
      setCallResult(paramContext, localContinuationJump.result, localContinuationJump.resultDbl);
    }
  }
  
  public static Object restartContinuation(NativeContinuation paramNativeContinuation, Context paramContext, Scriptable paramScriptable, Object[] paramArrayOfObject)
  {
    if (!ScriptRuntime.hasTopCall(paramContext))
    {
      paramArrayOfObject = ScriptRuntime.doTopCall(paramNativeContinuation, paramContext, paramScriptable, null, paramArrayOfObject, paramContext.isTopLevelStrict);
      return paramArrayOfObject;
    }
    if (paramArrayOfObject.length == 0) {}
    for (paramScriptable = Undefined.instance;; paramScriptable = paramArrayOfObject[0])
    {
      paramArrayOfObject = paramScriptable;
      if ((CallFrame)paramNativeContinuation.getImplementation() == null) {
        break;
      }
      paramNativeContinuation = new ContinuationJump(paramNativeContinuation, null);
      paramNativeContinuation.result = paramScriptable;
      return interpretLoop(paramContext, null, paramNativeContinuation);
    }
  }
  
  public static Object resumeGenerator(Context paramContext, Scriptable paramScriptable, int paramInt, Object paramObject1, Object paramObject2)
  {
    paramObject1 = (CallFrame)paramObject1;
    paramScriptable = new GeneratorState(paramInt, paramObject2);
    if (paramInt == 2) {}
    do
    {
      try
      {
        paramContext = interpretLoop(paramContext, (CallFrame)paramObject1, paramScriptable);
        return paramContext;
      }
      catch (RuntimeException paramContext)
      {
        if (paramContext != paramObject2) {
          throw paramContext;
        }
        return Undefined.instance;
      }
      paramContext = interpretLoop(paramContext, (CallFrame)paramObject1, paramScriptable);
    } while (paramScriptable.returnedException == null);
    throw paramScriptable.returnedException;
  }
  
  private static void setCallResult(CallFrame paramCallFrame, Object paramObject, double paramDouble)
  {
    if (paramCallFrame.savedCallOp == 38)
    {
      paramCallFrame.stack[paramCallFrame.savedStackTop] = paramObject;
      paramCallFrame.sDbl[paramCallFrame.savedStackTop] = paramDouble;
    }
    for (;;)
    {
      paramCallFrame.savedCallOp = 0;
      return;
      if (paramCallFrame.savedCallOp == 30)
      {
        if ((paramObject instanceof Scriptable)) {
          paramCallFrame.stack[paramCallFrame.savedStackTop] = paramObject;
        }
      }
      else {
        Kit.codeBug();
      }
    }
  }
  
  private static boolean stack_boolean(CallFrame paramCallFrame, int paramInt)
  {
    Object localObject = paramCallFrame.stack[paramInt];
    if (localObject == Boolean.TRUE) {}
    double d;
    do
    {
      return true;
      if (localObject == Boolean.FALSE) {
        return false;
      }
      if (localObject == UniqueTag.DOUBLE_MARK)
      {
        d = paramCallFrame.sDbl[paramInt];
        if ((d == d) && (d != 0.0D)) {}
        for (boolean bool = true;; bool = false) {
          return bool;
        }
      }
      if ((localObject == null) || (localObject == Undefined.instance)) {
        return false;
      }
      if (!(localObject instanceof Number)) {
        break;
      }
      d = ((Number)localObject).doubleValue();
    } while ((d == d) && (d != 0.0D));
    return false;
    if ((localObject instanceof Boolean)) {
      return ((Boolean)localObject).booleanValue();
    }
    return ScriptRuntime.toBoolean(localObject);
  }
  
  private static double stack_double(CallFrame paramCallFrame, int paramInt)
  {
    Object localObject = paramCallFrame.stack[paramInt];
    if (localObject != UniqueTag.DOUBLE_MARK) {
      return ScriptRuntime.toNumber(localObject);
    }
    return paramCallFrame.sDbl[paramInt];
  }
  
  private static int stack_int32(CallFrame paramCallFrame, int paramInt)
  {
    Object localObject = paramCallFrame.stack[paramInt];
    if (localObject == UniqueTag.DOUBLE_MARK) {
      return ScriptRuntime.toInt32(paramCallFrame.sDbl[paramInt]);
    }
    return ScriptRuntime.toInt32(localObject);
  }
  
  private static Object thawGenerator(CallFrame paramCallFrame, int paramInt1, GeneratorState paramGeneratorState, int paramInt2)
  {
    paramCallFrame.frozen = false;
    int i = getIndex(paramCallFrame.idata.itsICode, paramCallFrame.pc);
    paramCallFrame.pc += 2;
    if (paramGeneratorState.operation == 1) {
      return new JavaScriptException(paramGeneratorState.value, paramCallFrame.idata.itsSourceFile, i);
    }
    if (paramGeneratorState.operation == 2) {
      return paramGeneratorState.value;
    }
    if (paramGeneratorState.operation != 0) {
      throw Kit.codeBug();
    }
    if (paramInt2 == 73) {
      paramCallFrame.stack[paramInt1] = paramGeneratorState.value;
    }
    return Scriptable.NOT_FOUND;
  }
  
  public void captureStackInfo(RhinoException paramRhinoException)
  {
    int j = 0;
    Object localObject = Context.getCurrentContext();
    if ((localObject == null) || (((Context)localObject).lastInterpreterFrame == null))
    {
      paramRhinoException.interpreterStackInfo = null;
      paramRhinoException.interpreterLineData = null;
      return;
    }
    CallFrame[] arrayOfCallFrame;
    if ((((Context)localObject).previousInterpreterInvocations == null) || (((Context)localObject).previousInterpreterInvocations.size() == 0)) {
      arrayOfCallFrame = new CallFrame[1];
    }
    int i;
    for (;;)
    {
      arrayOfCallFrame[(arrayOfCallFrame.length - 1)] = ((CallFrame)((Context)localObject).lastInterpreterFrame);
      int k = 0;
      i = j;
      j = k;
      while (j != arrayOfCallFrame.length)
      {
        i += arrayOfCallFrame[j].frameIndex + 1;
        j += 1;
      }
      k = ((Context)localObject).previousInterpreterInvocations.size();
      i = k;
      if (((Context)localObject).previousInterpreterInvocations.peek() == ((Context)localObject).lastInterpreterFrame) {
        i = k - 1;
      }
      arrayOfCallFrame = new CallFrame[i + 1];
      ((Context)localObject).previousInterpreterInvocations.toArray(arrayOfCallFrame);
    }
    int[] arrayOfInt = new int[i];
    j = arrayOfCallFrame.length;
    while (j != 0)
    {
      j -= 1;
      for (localObject = arrayOfCallFrame[j]; localObject != null; localObject = ((CallFrame)localObject).parentFrame)
      {
        i -= 1;
        arrayOfInt[i] = ((CallFrame)localObject).pcSourceLineStart;
      }
    }
    if (i != 0) {
      Kit.codeBug();
    }
    paramRhinoException.interpreterStackInfo = arrayOfCallFrame;
    paramRhinoException.interpreterLineData = arrayOfInt;
  }
  
  public Object compile(CompilerEnvirons paramCompilerEnvirons, ScriptNode paramScriptNode, String paramString, boolean paramBoolean)
  {
    this.itsData = new CodeGenerator().compile(paramCompilerEnvirons, paramScriptNode, paramString, paramBoolean);
    return this.itsData;
  }
  
  public Function createFunctionObject(Context paramContext, Scriptable paramScriptable, Object paramObject1, Object paramObject2)
  {
    if (paramObject1 != this.itsData) {
      Kit.codeBug();
    }
    return InterpretedFunction.createFunction(paramContext, paramScriptable, this.itsData, paramObject2);
  }
  
  public Script createScriptObject(Object paramObject1, Object paramObject2)
  {
    if (paramObject1 != this.itsData) {
      Kit.codeBug();
    }
    return InterpretedFunction.createScript(this.itsData, paramObject2);
  }
  
  public String getPatchedStack(RhinoException paramRhinoException, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder(paramString.length() + 1000);
    String str = SecurityUtilities.getSystemProperty("line.separator");
    CallFrame[] arrayOfCallFrame = (CallFrame[])paramRhinoException.interpreterStackInfo;
    int[] arrayOfInt = paramRhinoException.interpreterLineData;
    int i = arrayOfCallFrame.length;
    int k = arrayOfInt.length;
    int j = 0;
    for (;;)
    {
      int m;
      if (i != 0)
      {
        m = i - 1;
        i = paramString.indexOf("org.mozilla.javascript.Interpreter.interpretLoop", j);
        if (i >= 0) {}
      }
      else
      {
        localStringBuilder.append(paramString.substring(j));
        return localStringBuilder.toString();
      }
      i += "org.mozilla.javascript.Interpreter.interpretLoop".length();
      for (;;)
      {
        if (i != paramString.length())
        {
          int n = paramString.charAt(i);
          if ((n != 10) && (n != 13)) {}
        }
        else
        {
          localStringBuilder.append(paramString.substring(j, i));
          for (paramRhinoException = arrayOfCallFrame[m]; paramRhinoException != null; paramRhinoException = paramRhinoException.parentFrame)
          {
            if (k == 0) {
              Kit.codeBug();
            }
            k -= 1;
            InterpreterData localInterpreterData = paramRhinoException.idata;
            localStringBuilder.append(str);
            localStringBuilder.append("\tat script");
            if ((localInterpreterData.itsName != null) && (localInterpreterData.itsName.length() != 0))
            {
              localStringBuilder.append('.');
              localStringBuilder.append(localInterpreterData.itsName);
            }
            localStringBuilder.append('(');
            localStringBuilder.append(localInterpreterData.itsSourceFile);
            j = arrayOfInt[k];
            if (j >= 0)
            {
              localStringBuilder.append(':');
              localStringBuilder.append(getIndex(localInterpreterData.itsICode, j));
            }
            localStringBuilder.append(')');
          }
        }
        i += 1;
      }
      j = i;
      i = m;
    }
  }
  
  public List<String> getScriptStack(RhinoException paramRhinoException)
  {
    paramRhinoException = getScriptStackElements(paramRhinoException);
    ArrayList localArrayList = new ArrayList(paramRhinoException.length);
    String str = SecurityUtilities.getSystemProperty("line.separator");
    int k = paramRhinoException.length;
    int i = 0;
    while (i < k)
    {
      Object localObject = paramRhinoException[i];
      StringBuilder localStringBuilder = new StringBuilder();
      int m = localObject.length;
      int j = 0;
      while (j < m)
      {
        localObject[j].renderJavaStyle(localStringBuilder);
        localStringBuilder.append(str);
        j += 1;
      }
      localArrayList.add(localStringBuilder.toString());
      i += 1;
    }
    return localArrayList;
  }
  
  public ScriptStackElement[][] getScriptStackElements(RhinoException paramRhinoException)
  {
    if (paramRhinoException.interpreterStackInfo == null) {
      return (ScriptStackElement[][])null;
    }
    ArrayList localArrayList1 = new ArrayList();
    CallFrame[] arrayOfCallFrame = (CallFrame[])paramRhinoException.interpreterStackInfo;
    int[] arrayOfInt = paramRhinoException.interpreterLineData;
    int j = arrayOfCallFrame.length;
    int i = arrayOfInt.length;
    ArrayList localArrayList2;
    label69:
    int k;
    String str;
    if (j != 0)
    {
      j -= 1;
      paramRhinoException = arrayOfCallFrame[j];
      localArrayList2 = new ArrayList();
      if (paramRhinoException != null)
      {
        if (i == 0) {
          Kit.codeBug();
        }
        k = i - 1;
        localObject = paramRhinoException.idata;
        str = ((InterpreterData)localObject).itsSourceFile;
        i = -1;
        int m = arrayOfInt[k];
        if (m >= 0) {
          i = getIndex(((InterpreterData)localObject).itsICode, m);
        }
        if ((((InterpreterData)localObject).itsName == null) || (((InterpreterData)localObject).itsName.length() == 0)) {
          break label230;
        }
      }
    }
    label230:
    for (Object localObject = ((InterpreterData)localObject).itsName;; localObject = null)
    {
      paramRhinoException = paramRhinoException.parentFrame;
      localArrayList2.add(new ScriptStackElement(str, (String)localObject, i));
      i = k;
      break label69;
      localArrayList1.add(localArrayList2.toArray(new ScriptStackElement[localArrayList2.size()]));
      break;
      return (ScriptStackElement[][])localArrayList1.toArray(new ScriptStackElement[localArrayList1.size()][]);
    }
  }
  
  public String getSourcePositionFromStack(Context paramContext, int[] paramArrayOfInt)
  {
    paramContext = (CallFrame)paramContext.lastInterpreterFrame;
    InterpreterData localInterpreterData = paramContext.idata;
    if (paramContext.pcSourceLineStart >= 0) {
      paramArrayOfInt[0] = getIndex(localInterpreterData.itsICode, paramContext.pcSourceLineStart);
    }
    for (;;)
    {
      return localInterpreterData.itsSourceFile;
      paramArrayOfInt[0] = 0;
    }
  }
  
  public void setEvalScriptFlag(Script paramScript)
  {
    ((InterpretedFunction)paramScript).idata.evalScriptFlag = true;
  }
  
  private static class CallFrame
    implements Cloneable, Serializable
  {
    static final long serialVersionUID = -2843792508994958978L;
    DebugFrame debuggerFrame;
    int emptyStackTop;
    InterpretedFunction fnOrScript;
    int frameIndex;
    boolean frozen;
    InterpreterData idata;
    boolean isContinuationsTopFrame;
    int localShift;
    CallFrame parentFrame;
    int pc;
    int pcPrevBranch;
    int pcSourceLineStart;
    Object result;
    double resultDbl;
    double[] sDbl;
    int savedCallOp;
    int savedStackTop;
    Scriptable scope;
    Object[] stack;
    int[] stackAttributes;
    Scriptable thisObj;
    Object throwable;
    boolean useActivation;
    CallFrame varSource;
    
    CallFrame cloneFrozen()
    {
      if (!this.frozen) {
        Kit.codeBug();
      }
      try
      {
        CallFrame localCallFrame = (CallFrame)clone();
        localCallFrame.stack = ((Object[])this.stack.clone());
        localCallFrame.stackAttributes = ((int[])this.stackAttributes.clone());
        localCallFrame.sDbl = ((double[])this.sDbl.clone());
        localCallFrame.frozen = false;
        return localCallFrame;
      }
      catch (CloneNotSupportedException localCloneNotSupportedException)
      {
        throw new IllegalStateException();
      }
    }
  }
  
  private static final class ContinuationJump
    implements Serializable
  {
    static final long serialVersionUID = 7687739156004308247L;
    Interpreter.CallFrame branchFrame;
    Interpreter.CallFrame capturedFrame;
    Object result;
    double resultDbl;
    
    ContinuationJump(NativeContinuation paramNativeContinuation, Interpreter.CallFrame paramCallFrame)
    {
      this.capturedFrame = ((Interpreter.CallFrame)paramNativeContinuation.getImplementation());
      if ((this.capturedFrame == null) || (paramCallFrame == null)) {
        this.branchFrame = null;
      }
      do
      {
        return;
        paramNativeContinuation = this.capturedFrame;
        int j = paramNativeContinuation.frameIndex - paramCallFrame.frameIndex;
        Object localObject1 = paramNativeContinuation;
        Object localObject2 = paramCallFrame;
        if (j != 0)
        {
          int i = j;
          localObject1 = paramNativeContinuation;
          paramNativeContinuation = paramCallFrame;
          if (j < 0)
          {
            paramNativeContinuation = this.capturedFrame;
            i = -j;
            localObject1 = paramCallFrame;
          }
          do
          {
            paramCallFrame = ((Interpreter.CallFrame)localObject1).parentFrame;
            j = i - 1;
            i = j;
            localObject1 = paramCallFrame;
          } while (j != 0);
          localObject1 = paramCallFrame;
          localObject2 = paramNativeContinuation;
          if (paramCallFrame.frameIndex != paramNativeContinuation.frameIndex)
          {
            Kit.codeBug();
            localObject2 = paramNativeContinuation;
            localObject1 = paramCallFrame;
          }
        }
        while ((localObject1 != localObject2) && (localObject1 != null))
        {
          localObject1 = ((Interpreter.CallFrame)localObject1).parentFrame;
          localObject2 = ((Interpreter.CallFrame)localObject2).parentFrame;
        }
        this.branchFrame = ((Interpreter.CallFrame)localObject1);
      } while ((this.branchFrame == null) || (this.branchFrame.frozen));
      Kit.codeBug();
    }
  }
  
  static class GeneratorState
  {
    int operation;
    RuntimeException returnedException;
    Object value;
    
    GeneratorState(int paramInt, Object paramObject)
    {
      this.operation = paramInt;
      this.value = paramObject;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\Interpreter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */