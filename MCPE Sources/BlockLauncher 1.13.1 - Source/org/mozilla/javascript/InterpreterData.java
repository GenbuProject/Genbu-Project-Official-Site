package org.mozilla.javascript;

import java.io.Serializable;
import org.mozilla.javascript.debug.DebuggableScript;

final class InterpreterData
  implements Serializable, DebuggableScript
{
  static final int INITIAL_MAX_ICODE_LENGTH = 1024;
  static final int INITIAL_NUMBERTABLE_SIZE = 64;
  static final int INITIAL_STRINGTABLE_SIZE = 64;
  static final long serialVersionUID = 5067677351589230234L;
  int argCount;
  boolean[] argIsConst;
  String[] argNames;
  String encodedSource;
  int encodedSourceEnd;
  int encodedSourceStart;
  boolean evalScriptFlag;
  int firstLinePC = -1;
  boolean isStrict;
  double[] itsDoubleTable;
  int[] itsExceptionTable;
  int itsFunctionType;
  byte[] itsICode;
  int itsMaxCalleeArgs;
  int itsMaxFrameArray;
  int itsMaxLocals;
  int itsMaxStack;
  int itsMaxVars;
  String itsName;
  boolean itsNeedsActivation;
  InterpreterData[] itsNestedFunctions;
  Object[] itsRegExpLiterals;
  String itsSourceFile;
  String[] itsStringTable;
  int languageVersion;
  Object[] literalIds;
  UintMap longJumps;
  InterpreterData parentData;
  boolean topLevel;
  
  InterpreterData(int paramInt, String paramString1, String paramString2, boolean paramBoolean)
  {
    this.languageVersion = paramInt;
    this.itsSourceFile = paramString1;
    this.encodedSource = paramString2;
    this.isStrict = paramBoolean;
    init();
  }
  
  InterpreterData(InterpreterData paramInterpreterData)
  {
    this.parentData = paramInterpreterData;
    this.languageVersion = paramInterpreterData.languageVersion;
    this.itsSourceFile = paramInterpreterData.itsSourceFile;
    this.encodedSource = paramInterpreterData.encodedSource;
    this.isStrict = paramInterpreterData.isStrict;
    init();
  }
  
  private void init()
  {
    this.itsICode = new byte['?'];
    this.itsStringTable = new String[64];
  }
  
  public DebuggableScript getFunction(int paramInt)
  {
    return this.itsNestedFunctions[paramInt];
  }
  
  public int getFunctionCount()
  {
    if (this.itsNestedFunctions == null) {
      return 0;
    }
    return this.itsNestedFunctions.length;
  }
  
  public String getFunctionName()
  {
    return this.itsName;
  }
  
  public int[] getLineNumbers()
  {
    return Interpreter.getLineNumbers(this);
  }
  
  public int getParamAndVarCount()
  {
    return this.argNames.length;
  }
  
  public int getParamCount()
  {
    return this.argCount;
  }
  
  public boolean getParamOrVarConst(int paramInt)
  {
    return this.argIsConst[paramInt];
  }
  
  public String getParamOrVarName(int paramInt)
  {
    return this.argNames[paramInt];
  }
  
  public DebuggableScript getParent()
  {
    return this.parentData;
  }
  
  public String getSourceName()
  {
    return this.itsSourceFile;
  }
  
  public boolean isFunction()
  {
    return this.itsFunctionType != 0;
  }
  
  public boolean isGeneratedScript()
  {
    return ScriptRuntime.isGeneratedScript(this.itsSourceFile);
  }
  
  public boolean isTopLevel()
  {
    return this.topLevel;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\InterpreterData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */