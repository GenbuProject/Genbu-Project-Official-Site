package org.mozilla.javascript;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.mozilla.javascript.ast.AstRoot;
import org.mozilla.javascript.ast.ScriptNode;
import org.mozilla.javascript.debug.DebuggableScript;
import org.mozilla.javascript.debug.Debugger;
import org.mozilla.javascript.xml.XMLLib;
import org.mozilla.javascript.xml.XMLLib.Factory;

public class Context
{
  public static final int FEATURE_DYNAMIC_SCOPE = 7;
  public static final int FEATURE_E4X = 6;
  public static final int FEATURE_ENHANCED_JAVA_ACCESS = 13;
  public static final int FEATURE_ENUMERATE_IDS_FIRST = 16;
  public static final int FEATURE_LOCATION_INFORMATION_IN_ERROR = 10;
  public static final int FEATURE_MEMBER_EXPR_AS_FUNCTION_NAME = 2;
  public static final int FEATURE_NON_ECMA_GET_YEAR = 1;
  public static final int FEATURE_OLD_UNDEF_NULL_THIS = 15;
  public static final int FEATURE_PARENT_PROTO_PROPERTIES = 5;
  @Deprecated
  public static final int FEATURE_PARENT_PROTO_PROPRTIES = 5;
  public static final int FEATURE_RESERVED_KEYWORD_AS_IDENTIFIER = 3;
  public static final int FEATURE_STRICT_EVAL = 9;
  public static final int FEATURE_STRICT_MODE = 11;
  public static final int FEATURE_STRICT_VARS = 8;
  public static final int FEATURE_TO_STRING_AS_SOURCE = 4;
  public static final int FEATURE_V8_EXTENSIONS = 14;
  public static final int FEATURE_WARNING_AS_ERROR = 12;
  public static final int VERSION_1_0 = 100;
  public static final int VERSION_1_1 = 110;
  public static final int VERSION_1_2 = 120;
  public static final int VERSION_1_3 = 130;
  public static final int VERSION_1_4 = 140;
  public static final int VERSION_1_5 = 150;
  public static final int VERSION_1_6 = 160;
  public static final int VERSION_1_7 = 170;
  public static final int VERSION_1_8 = 180;
  public static final int VERSION_DEFAULT = 0;
  public static final int VERSION_ES6 = 200;
  public static final int VERSION_UNKNOWN = -1;
  private static Class<?> codegenClass = Kit.classOrNull("org.mozilla.javascript.optimizer.Codegen");
  public static final Object[] emptyArgs = ScriptRuntime.emptyArgs;
  public static final String errorReporterProperty = "error reporter";
  private static String implementationVersion;
  private static Class<?> interpreterClass = Kit.classOrNull("org.mozilla.javascript.Interpreter");
  public static final String languageVersionProperty = "language version";
  Set<String> activationNames;
  private ClassLoader applicationClassLoader;
  XMLLib cachedXMLLib;
  private ClassShutter classShutter;
  NativeCall currentActivationCall;
  Debugger debugger;
  private Object debuggerData;
  private int enterCount;
  private ErrorReporter errorReporter;
  private final ContextFactory factory;
  public boolean generateObserverCount = false;
  private boolean generatingDebug;
  private boolean generatingDebugChanged;
  private boolean generatingSource = true;
  private boolean hasClassShutter;
  int instructionCount;
  int instructionThreshold;
  Object interpreterSecurityDomain;
  boolean isContinuationsTopCall;
  boolean isTopLevelStrict;
  ObjToIntMap iterating;
  Object lastInterpreterFrame;
  private Locale locale;
  private int maximumInterpreterStackDepth;
  private int optimizationLevel;
  ObjArray previousInterpreterInvocations;
  private Object propertyListeners;
  RegExpProxy regExpProxy;
  int scratchIndex;
  Scriptable scratchScriptable;
  long scratchUint32;
  private Object sealKey;
  private boolean sealed;
  private SecurityController securityController;
  private Map<Object, Object> threadLocalMap;
  Scriptable topCallScope;
  BaseFunction typeErrorThrower;
  boolean useDynamicScope;
  int version;
  private WrapFactory wrapFactory;
  
  @Deprecated
  public Context()
  {
    this(ContextFactory.getGlobal());
  }
  
  protected Context(ContextFactory paramContextFactory)
  {
    if (paramContextFactory == null) {
      throw new IllegalArgumentException("factory == null");
    }
    this.factory = paramContextFactory;
    this.version = 0;
    if (codegenClass != null) {}
    for (;;)
    {
      this.optimizationLevel = i;
      this.maximumInterpreterStackDepth = Integer.MAX_VALUE;
      return;
      i = -1;
    }
  }
  
  @Deprecated
  public static void addContextListener(ContextListener paramContextListener)
  {
    if ("org.mozilla.javascript.tools.debugger.Main".equals(paramContextListener.getClass().getName()))
    {
      Object localObject = paramContextListener.getClass();
      Class localClass = Kit.classOrNull("org.mozilla.javascript.ContextFactory");
      ContextFactory localContextFactory = ContextFactory.getGlobal();
      try
      {
        ((Class)localObject).getMethod("attachTo", new Class[] { localClass }).invoke(paramContextListener, new Object[] { localContextFactory });
        return;
      }
      catch (Exception paramContextListener)
      {
        localObject = new RuntimeException();
        Kit.initCause((RuntimeException)localObject, paramContextListener);
        throw ((Throwable)localObject);
      }
    }
    ContextFactory.getGlobal().addListener(paramContextListener);
  }
  
  @Deprecated
  public static Object call(ContextAction paramContextAction)
  {
    return call(ContextFactory.getGlobal(), paramContextAction);
  }
  
  public static Object call(ContextFactory paramContextFactory, Callable paramCallable, final Scriptable paramScriptable1, final Scriptable paramScriptable2, final Object[] paramArrayOfObject)
  {
    ContextFactory localContextFactory = paramContextFactory;
    if (paramContextFactory == null) {
      localContextFactory = ContextFactory.getGlobal();
    }
    call(localContextFactory, new ContextAction()
    {
      public Object run(Context paramAnonymousContext)
      {
        return this.val$callable.call(paramAnonymousContext, paramScriptable1, paramScriptable2, paramArrayOfObject);
      }
    });
  }
  
  static Object call(ContextFactory paramContextFactory, ContextAction paramContextAction)
  {
    paramContextFactory = enter(null, paramContextFactory);
    try
    {
      paramContextFactory = paramContextAction.run(paramContextFactory);
      return paramContextFactory;
    }
    finally
    {
      exit();
    }
  }
  
  public static void checkLanguageVersion(int paramInt)
  {
    if (isValidLanguageVersion(paramInt)) {
      return;
    }
    throw new IllegalArgumentException("Bad language version: " + paramInt);
  }
  
  public static void checkOptimizationLevel(int paramInt)
  {
    if (isValidOptimizationLevel(paramInt)) {
      return;
    }
    throw new IllegalArgumentException("Optimization level outside [-1..9]: " + paramInt);
  }
  
  private Object compileImpl(Scriptable paramScriptable, Reader paramReader, String paramString1, String paramString2, int paramInt, Object paramObject, boolean paramBoolean, Evaluator paramEvaluator, ErrorReporter paramErrorReporter)
    throws IOException
  {
    boolean bool3 = false;
    String str = paramString2;
    if (paramString2 == null) {
      str = "unnamed script";
    }
    if ((paramObject != null) && (getSecurityController() == null)) {
      throw new IllegalArgumentException("securityDomain should be null if setSecurityController() was never called");
    }
    boolean bool1;
    boolean bool2;
    label54:
    CompilerEnvirons localCompilerEnvirons;
    ErrorReporter localErrorReporter;
    if (paramReader == null)
    {
      bool1 = true;
      if (paramString1 != null) {
        break label261;
      }
      bool2 = true;
      if (!(bool2 ^ bool1)) {
        Kit.codeBug();
      }
      bool1 = bool3;
      if (paramScriptable == null) {
        bool1 = true;
      }
      if (!(bool1 ^ paramBoolean)) {
        Kit.codeBug();
      }
      localCompilerEnvirons = new CompilerEnvirons();
      localCompilerEnvirons.initFromContext(this);
      localErrorReporter = paramErrorReporter;
      if (paramErrorReporter == null) {
        localErrorReporter = localCompilerEnvirons.getErrorReporter();
      }
      paramErrorReporter = paramReader;
      paramString2 = paramString1;
      if (this.debugger != null)
      {
        paramErrorReporter = paramReader;
        paramString2 = paramString1;
        if (paramReader != null)
        {
          paramString2 = Kit.readReader(paramReader);
          paramErrorReporter = null;
        }
      }
      paramReader = new Parser(localCompilerEnvirons, localErrorReporter);
      if (paramBoolean) {
        paramReader.calledByCompileFunction = true;
      }
      if (isStrictMode()) {
        paramReader.setDefaultUseStrictDirective(true);
      }
      if (paramString2 == null) {
        break label267;
      }
    }
    label261:
    label267:
    for (paramReader = paramReader.parse(paramString2, str, paramInt);; paramReader = paramReader.parse(paramErrorReporter, str, paramInt))
    {
      if ((!paramBoolean) || ((paramReader.getFirstChild() != null) && (paramReader.getFirstChild().getType() == 110))) {
        break label281;
      }
      throw new IllegalArgumentException("compileFunction only accepts source with single JS function: " + paramString2);
      bool1 = false;
      break;
      bool2 = false;
      break label54;
    }
    label281:
    paramString1 = new IRFactory(localCompilerEnvirons, localErrorReporter).transformTree(paramReader);
    paramReader = paramEvaluator;
    if (paramEvaluator == null) {
      paramReader = createCompiler();
    }
    paramString1 = paramReader.compile(localCompilerEnvirons, paramString1, paramString1.getEncodedSource(), paramBoolean);
    if (this.debugger != null)
    {
      if (paramString2 == null) {
        Kit.codeBug();
      }
      if ((paramString1 instanceof DebuggableScript)) {
        notifyDebugger_r(this, (DebuggableScript)paramString1, paramString2);
      }
    }
    else
    {
      if (!paramBoolean) {
        break label387;
      }
      return paramReader.createFunctionObject(this, paramScriptable, paramString1, paramObject);
    }
    throw new RuntimeException("NOT SUPPORTED");
    label387:
    return paramReader.createScriptObject(paramString1, paramObject);
  }
  
  private Evaluator createCompiler()
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (this.optimizationLevel >= 0)
    {
      localObject1 = localObject2;
      if (codegenClass != null) {
        localObject1 = (Evaluator)Kit.newInstanceOrNull(codegenClass);
      }
    }
    localObject2 = localObject1;
    if (localObject1 == null) {
      localObject2 = createInterpreter();
    }
    return (Evaluator)localObject2;
  }
  
  static Evaluator createInterpreter()
  {
    return (Evaluator)Kit.newInstanceOrNull(interpreterClass);
  }
  
  public static Context enter()
  {
    return enter(null);
  }
  
  @Deprecated
  public static Context enter(Context paramContext)
  {
    return enter(paramContext, ContextFactory.getGlobal());
  }
  
  static final Context enter(Context paramContext, ContextFactory paramContextFactory)
  {
    Object localObject = VMBridge.instance.getThreadContextHelper();
    Context localContext = VMBridge.instance.getContext(localObject);
    if (localContext != null)
    {
      paramContext = localContext;
      paramContext.enterCount += 1;
      return paramContext;
    }
    if (paramContext == null)
    {
      paramContext = paramContextFactory.makeContext();
      if (paramContext.enterCount != 0) {
        throw new IllegalStateException("factory.makeContext() returned Context instance already associated with some thread");
      }
      paramContextFactory.onContextCreated(paramContext);
      localContext = paramContext;
      if (paramContextFactory.isSealed())
      {
        localContext = paramContext;
        if (!paramContext.isSealed())
        {
          paramContext.seal(null);
          localContext = paramContext;
        }
      }
    }
    do
    {
      VMBridge.instance.setContext(localObject, localContext);
      paramContext = localContext;
      break;
      localContext = paramContext;
    } while (paramContext.enterCount == 0);
    throw new IllegalStateException("can not use Context instance already associated with some thread");
  }
  
  public static void exit()
  {
    Object localObject = VMBridge.instance.getThreadContextHelper();
    Context localContext = VMBridge.instance.getContext(localObject);
    if (localContext == null) {
      throw new IllegalStateException("Calling Context.exit without previous Context.enter");
    }
    if (localContext.enterCount < 1) {
      Kit.codeBug();
    }
    int i = localContext.enterCount - 1;
    localContext.enterCount = i;
    if (i == 0)
    {
      VMBridge.instance.setContext(localObject, null);
      localContext.factory.onContextReleased(localContext);
    }
  }
  
  private void firePropertyChangeImpl(Object paramObject1, String paramString, Object paramObject2, Object paramObject3)
  {
    int i = 0;
    for (;;)
    {
      Object localObject = Kit.getListener(paramObject1, i);
      if (localObject == null) {
        return;
      }
      if ((localObject instanceof PropertyChangeListener)) {
        ((PropertyChangeListener)localObject).propertyChange(new PropertyChangeEvent(this, paramString, paramObject2, paramObject3));
      }
      i += 1;
    }
  }
  
  static Context getContext()
  {
    Context localContext = getCurrentContext();
    if (localContext == null) {
      throw new RuntimeException("No Context associated with current Thread");
    }
    return localContext;
  }
  
  public static Context getCurrentContext()
  {
    Object localObject = VMBridge.instance.getThreadContextHelper();
    return VMBridge.instance.getContext(localObject);
  }
  
  public static DebuggableScript getDebuggableView(Script paramScript)
  {
    if ((paramScript instanceof NativeFunction)) {
      return ((NativeFunction)paramScript).getDebuggableView();
    }
    return null;
  }
  
  static String getSourcePositionFromStack(int[] paramArrayOfInt)
  {
    Object localObject = getCurrentContext();
    if (localObject == null) {}
    for (;;)
    {
      return null;
      Evaluator localEvaluator;
      if (((Context)localObject).lastInterpreterFrame != null)
      {
        localEvaluator = createInterpreter();
        if (localEvaluator != null) {
          return localEvaluator.getSourcePositionFromStack((Context)localObject, paramArrayOfInt);
        }
      }
      localObject = new Throwable().getStackTrace();
      int j = localObject.length;
      int i = 0;
      while (i < j)
      {
        localEvaluator = localObject[i];
        String str = localEvaluator.getFileName();
        if ((str != null) && (!str.endsWith(".java")))
        {
          int k = localEvaluator.getLineNumber();
          if (k >= 0)
          {
            paramArrayOfInt[0] = k;
            return str;
          }
        }
        i += 1;
      }
    }
  }
  
  public static Object getUndefinedValue()
  {
    return Undefined.instance;
  }
  
  public static boolean isValidLanguageVersion(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return false;
    }
    return true;
  }
  
  public static boolean isValidOptimizationLevel(int paramInt)
  {
    return (-1 <= paramInt) && (paramInt <= 9);
  }
  
  public static Object javaToJS(Object paramObject, Scriptable paramScriptable)
  {
    if (((paramObject instanceof String)) || ((paramObject instanceof Number)) || ((paramObject instanceof Boolean)) || ((paramObject instanceof Scriptable))) {
      return paramObject;
    }
    if ((paramObject instanceof Character)) {
      return String.valueOf(((Character)paramObject).charValue());
    }
    Context localContext = getContext();
    return localContext.getWrapFactory().wrap(localContext, paramScriptable, paramObject, null);
  }
  
  public static Object jsToJava(Object paramObject, Class<?> paramClass)
    throws EvaluatorException
  {
    return NativeJavaObject.coerceTypeImpl(paramClass, paramObject);
  }
  
  private static void notifyDebugger_r(Context paramContext, DebuggableScript paramDebuggableScript, String paramString)
  {
    paramContext.debugger.handleCompilationDone(paramContext, paramDebuggableScript, paramString);
    int i = 0;
    while (i != paramDebuggableScript.getFunctionCount())
    {
      notifyDebugger_r(paramContext, paramDebuggableScript.getFunction(i), paramString);
      i += 1;
    }
  }
  
  static void onSealedMutation()
  {
    throw new IllegalStateException();
  }
  
  @Deprecated
  public static void removeContextListener(ContextListener paramContextListener)
  {
    ContextFactory.getGlobal().addListener(paramContextListener);
  }
  
  public static void reportError(String paramString)
  {
    int[] arrayOfInt = new int[1];
    arrayOfInt[0] = 0;
    reportError(paramString, getSourcePositionFromStack(arrayOfInt), arrayOfInt[0], null, 0);
  }
  
  public static void reportError(String paramString1, String paramString2, int paramInt1, String paramString3, int paramInt2)
  {
    Context localContext = getCurrentContext();
    if (localContext != null)
    {
      localContext.getErrorReporter().error(paramString1, paramString2, paramInt1, paramString3, paramInt2);
      return;
    }
    throw new EvaluatorException(paramString1, paramString2, paramInt1, paramString3, paramInt2);
  }
  
  public static EvaluatorException reportRuntimeError(String paramString)
  {
    int[] arrayOfInt = new int[1];
    arrayOfInt[0] = 0;
    return reportRuntimeError(paramString, getSourcePositionFromStack(arrayOfInt), arrayOfInt[0], null, 0);
  }
  
  public static EvaluatorException reportRuntimeError(String paramString1, String paramString2, int paramInt1, String paramString3, int paramInt2)
  {
    Context localContext = getCurrentContext();
    if (localContext != null) {
      return localContext.getErrorReporter().runtimeError(paramString1, paramString2, paramInt1, paramString3, paramInt2);
    }
    throw new EvaluatorException(paramString1, paramString2, paramInt1, paramString3, paramInt2);
  }
  
  static EvaluatorException reportRuntimeError0(String paramString)
  {
    return reportRuntimeError(ScriptRuntime.getMessage0(paramString));
  }
  
  static EvaluatorException reportRuntimeError1(String paramString, Object paramObject)
  {
    return reportRuntimeError(ScriptRuntime.getMessage1(paramString, paramObject));
  }
  
  static EvaluatorException reportRuntimeError2(String paramString, Object paramObject1, Object paramObject2)
  {
    return reportRuntimeError(ScriptRuntime.getMessage2(paramString, paramObject1, paramObject2));
  }
  
  static EvaluatorException reportRuntimeError3(String paramString, Object paramObject1, Object paramObject2, Object paramObject3)
  {
    return reportRuntimeError(ScriptRuntime.getMessage3(paramString, paramObject1, paramObject2, paramObject3));
  }
  
  static EvaluatorException reportRuntimeError4(String paramString, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4)
  {
    return reportRuntimeError(ScriptRuntime.getMessage4(paramString, paramObject1, paramObject2, paramObject3, paramObject4));
  }
  
  public static void reportWarning(String paramString)
  {
    int[] arrayOfInt = new int[1];
    arrayOfInt[0] = 0;
    reportWarning(paramString, getSourcePositionFromStack(arrayOfInt), arrayOfInt[0], null, 0);
  }
  
  public static void reportWarning(String paramString1, String paramString2, int paramInt1, String paramString3, int paramInt2)
  {
    Context localContext = getContext();
    if (localContext.hasFeature(12))
    {
      reportError(paramString1, paramString2, paramInt1, paramString3, paramInt2);
      return;
    }
    localContext.getErrorReporter().warning(paramString1, paramString2, paramInt1, paramString3, paramInt2);
  }
  
  public static void reportWarning(String paramString, Throwable paramThrowable)
  {
    int[] arrayOfInt = new int[1];
    arrayOfInt[0] = 0;
    String str = getSourcePositionFromStack(arrayOfInt);
    StringWriter localStringWriter = new StringWriter();
    PrintWriter localPrintWriter = new PrintWriter(localStringWriter);
    localPrintWriter.println(paramString);
    paramThrowable.printStackTrace(localPrintWriter);
    localPrintWriter.flush();
    reportWarning(localStringWriter.toString(), str, arrayOfInt[0], null, 0);
  }
  
  @Deprecated
  public static void setCachingEnabled(boolean paramBoolean) {}
  
  public static RuntimeException throwAsScriptRuntimeEx(Throwable paramThrowable)
  {
    while ((paramThrowable instanceof InvocationTargetException)) {
      paramThrowable = ((InvocationTargetException)paramThrowable).getTargetException();
    }
    if ((paramThrowable instanceof Error))
    {
      Context localContext = getContext();
      if ((localContext == null) || (!localContext.hasFeature(13))) {
        throw ((Error)paramThrowable);
      }
    }
    if ((paramThrowable instanceof RhinoException)) {
      throw ((RhinoException)paramThrowable);
    }
    throw new WrappedException(paramThrowable);
  }
  
  public static boolean toBoolean(Object paramObject)
  {
    return ScriptRuntime.toBoolean(paramObject);
  }
  
  public static double toNumber(Object paramObject)
  {
    return ScriptRuntime.toNumber(paramObject);
  }
  
  public static Scriptable toObject(Object paramObject, Scriptable paramScriptable)
  {
    return ScriptRuntime.toObject(paramScriptable, paramObject);
  }
  
  @Deprecated
  public static Scriptable toObject(Object paramObject, Scriptable paramScriptable, Class<?> paramClass)
  {
    return ScriptRuntime.toObject(paramScriptable, paramObject);
  }
  
  public static String toString(Object paramObject)
  {
    return ScriptRuntime.toString(paramObject);
  }
  
  @Deprecated
  public static Object toType(Object paramObject, Class<?> paramClass)
    throws IllegalArgumentException
  {
    try
    {
      paramObject = jsToJava(paramObject, paramClass);
      return paramObject;
    }
    catch (EvaluatorException paramObject)
    {
      paramClass = new IllegalArgumentException(((EvaluatorException)paramObject).getMessage());
      Kit.initCause(paramClass, (Throwable)paramObject);
      throw paramClass;
    }
  }
  
  public void addActivationName(String paramString)
  {
    if (this.sealed) {
      onSealedMutation();
    }
    if (this.activationNames == null) {
      this.activationNames = new HashSet();
    }
    this.activationNames.add(paramString);
  }
  
  public final void addPropertyChangeListener(PropertyChangeListener paramPropertyChangeListener)
  {
    if (this.sealed) {
      onSealedMutation();
    }
    this.propertyListeners = Kit.addListener(this.propertyListeners, paramPropertyChangeListener);
  }
  
  public Object callFunctionWithContinuations(Callable paramCallable, Scriptable paramScriptable, Object[] paramArrayOfObject)
    throws ContinuationPending
  {
    if (!(paramCallable instanceof InterpretedFunction)) {
      throw new IllegalArgumentException("Function argument was not created by interpreted mode ");
    }
    if (ScriptRuntime.hasTopCall(this)) {
      throw new IllegalStateException("Cannot have any pending top calls when executing a script with continuations");
    }
    this.isContinuationsTopCall = true;
    return ScriptRuntime.doTopCall(paramCallable, this, paramScriptable, paramScriptable, paramArrayOfObject, this.isTopLevelStrict);
  }
  
  public ContinuationPending captureContinuation()
  {
    return new ContinuationPending(Interpreter.captureContinuation(this));
  }
  
  public final Function compileFunction(Scriptable paramScriptable, String paramString1, String paramString2, int paramInt, Object paramObject)
  {
    return compileFunction(paramScriptable, paramString1, null, null, paramString2, paramInt, paramObject);
  }
  
  final Function compileFunction(Scriptable paramScriptable, String paramString1, Evaluator paramEvaluator, ErrorReporter paramErrorReporter, String paramString2, int paramInt, Object paramObject)
  {
    try
    {
      paramScriptable = (Function)compileImpl(paramScriptable, null, paramString1, paramString2, paramInt, paramObject, true, paramEvaluator, paramErrorReporter);
      return paramScriptable;
    }
    catch (IOException paramScriptable)
    {
      throw new RuntimeException();
    }
  }
  
  public final Script compileReader(Reader paramReader, String paramString, int paramInt, Object paramObject)
    throws IOException
  {
    if (paramInt < 0) {
      paramInt = 0;
    }
    for (;;)
    {
      return (Script)compileImpl(null, paramReader, null, paramString, paramInt, paramObject, false, null, null);
    }
  }
  
  @Deprecated
  public final Script compileReader(Scriptable paramScriptable, Reader paramReader, String paramString, int paramInt, Object paramObject)
    throws IOException
  {
    return compileReader(paramReader, paramString, paramInt, paramObject);
  }
  
  public final Script compileString(String paramString1, String paramString2, int paramInt, Object paramObject)
  {
    if (paramInt < 0) {
      paramInt = 0;
    }
    for (;;)
    {
      return compileString(paramString1, null, null, paramString2, paramInt, paramObject);
    }
  }
  
  final Script compileString(String paramString1, Evaluator paramEvaluator, ErrorReporter paramErrorReporter, String paramString2, int paramInt, Object paramObject)
  {
    try
    {
      paramString1 = (Script)compileImpl(null, null, paramString1, paramString2, paramInt, paramObject, false, paramEvaluator, paramErrorReporter);
      return paramString1;
    }
    catch (IOException paramString1)
    {
      throw new RuntimeException();
    }
  }
  
  public GeneratedClassLoader createClassLoader(ClassLoader paramClassLoader)
  {
    return getFactory().createClassLoader(paramClassLoader);
  }
  
  public final String decompileFunction(Function paramFunction, int paramInt)
  {
    if ((paramFunction instanceof BaseFunction)) {
      return ((BaseFunction)paramFunction).decompile(paramInt, 0);
    }
    return "function " + paramFunction.getClassName() + "() {\n\t[native code]\n}\n";
  }
  
  public final String decompileFunctionBody(Function paramFunction, int paramInt)
  {
    if ((paramFunction instanceof BaseFunction)) {
      return ((BaseFunction)paramFunction).decompile(paramInt, 1);
    }
    return "[native code]\n";
  }
  
  public final String decompileScript(Script paramScript, int paramInt)
  {
    return ((NativeFunction)paramScript).decompile(paramInt, 0);
  }
  
  public final Object evaluateReader(Scriptable paramScriptable, Reader paramReader, String paramString, int paramInt, Object paramObject)
    throws IOException
  {
    paramReader = compileReader(paramScriptable, paramReader, paramString, paramInt, paramObject);
    if (paramReader != null) {
      return paramReader.exec(this, paramScriptable);
    }
    return null;
  }
  
  public final Object evaluateString(Scriptable paramScriptable, String paramString1, String paramString2, int paramInt, Object paramObject)
  {
    paramString1 = compileString(paramString1, paramString2, paramInt, paramObject);
    if (paramString1 != null) {
      return paramString1.exec(this, paramScriptable);
    }
    return null;
  }
  
  public Object executeScriptWithContinuations(Script paramScript, Scriptable paramScriptable)
    throws ContinuationPending
  {
    if ((!(paramScript instanceof InterpretedFunction)) || (!((InterpretedFunction)paramScript).isScript())) {
      throw new IllegalArgumentException("Script argument was not a script or was not created by interpreted mode ");
    }
    return callFunctionWithContinuations((InterpretedFunction)paramScript, paramScriptable, ScriptRuntime.emptyArgs);
  }
  
  final void firePropertyChange(String paramString, Object paramObject1, Object paramObject2)
  {
    Object localObject = this.propertyListeners;
    if (localObject != null) {
      firePropertyChangeImpl(localObject, paramString, paramObject1, paramObject2);
    }
  }
  
  public final ClassLoader getApplicationClassLoader()
  {
    if (this.applicationClassLoader == null)
    {
      ContextFactory localContextFactory = getFactory();
      ClassLoader localClassLoader = localContextFactory.getApplicationClassLoader();
      localObject = localClassLoader;
      if (localClassLoader == null)
      {
        localObject = VMBridge.instance.getCurrentThreadClassLoader();
        if ((localObject != null) && (Kit.testIfCanLoadRhinoClasses((ClassLoader)localObject))) {
          return (ClassLoader)localObject;
        }
        localObject = localContextFactory.getClass();
        if (localObject == ScriptRuntime.ContextFactoryClass) {
          break label70;
        }
      }
    }
    label70:
    for (Object localObject = ((Class)localObject).getClassLoader();; localObject = getClass().getClassLoader())
    {
      this.applicationClassLoader = ((ClassLoader)localObject);
      return this.applicationClassLoader;
    }
  }
  
  final ClassShutter getClassShutter()
  {
    try
    {
      ClassShutter localClassShutter = this.classShutter;
      return localClassShutter;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /* Error */
  public final ClassShutterSetter getClassShutterSetter()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 864	org/mozilla/javascript/Context:hasClassShutter	Z
    //   6: istore_1
    //   7: iload_1
    //   8: ifeq +9 -> 17
    //   11: aconst_null
    //   12: astore_2
    //   13: aload_0
    //   14: monitorexit
    //   15: aload_2
    //   16: areturn
    //   17: aload_0
    //   18: iconst_1
    //   19: putfield 864	org/mozilla/javascript/Context:hasClassShutter	Z
    //   22: new 8	org/mozilla/javascript/Context$2
    //   25: dup
    //   26: aload_0
    //   27: invokespecial 866	org/mozilla/javascript/Context$2:<init>	(Lorg/mozilla/javascript/Context;)V
    //   30: astore_2
    //   31: goto -18 -> 13
    //   34: astore_2
    //   35: aload_0
    //   36: monitorexit
    //   37: aload_2
    //   38: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	39	0	this	Context
    //   6	2	1	bool	boolean
    //   12	19	2	localObject1	Object
    //   34	4	2	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   2	7	34	finally
    //   17	31	34	finally
  }
  
  public final Debugger getDebugger()
  {
    return this.debugger;
  }
  
  public final Object getDebuggerContextData()
  {
    return this.debuggerData;
  }
  
  public XMLLib.Factory getE4xImplementationFactory()
  {
    return getFactory().getE4xImplementationFactory();
  }
  
  public final Object[] getElements(Scriptable paramScriptable)
  {
    return ScriptRuntime.getArrayElements(paramScriptable);
  }
  
  public final ErrorReporter getErrorReporter()
  {
    if (this.errorReporter == null) {
      return DefaultErrorReporter.instance;
    }
    return this.errorReporter;
  }
  
  public final ContextFactory getFactory()
  {
    return this.factory;
  }
  
  /* Error */
  public final String getImplementationVersion()
  {
    // Byte code:
    //   0: getstatic 890	org/mozilla/javascript/Context:implementationVersion	Ljava/lang/String;
    //   3: ifnonnull +184 -> 187
    //   6: ldc 2
    //   8: invokevirtual 858	java/lang/Class:getClassLoader	()Ljava/lang/ClassLoader;
    //   11: ldc_w 892
    //   14: invokevirtual 898	java/lang/ClassLoader:getResources	(Ljava/lang/String;)Ljava/util/Enumeration;
    //   17: astore_2
    //   18: aload_2
    //   19: invokeinterface 903 1 0
    //   24: ifeq +163 -> 187
    //   27: aload_2
    //   28: invokeinterface 906 1 0
    //   33: checkcast 908	java/net/URL
    //   36: astore_1
    //   37: aload_1
    //   38: invokevirtual 912	java/net/URL:openStream	()Ljava/io/InputStream;
    //   41: astore_1
    //   42: new 914	java/util/jar/Manifest
    //   45: dup
    //   46: aload_1
    //   47: invokespecial 917	java/util/jar/Manifest:<init>	(Ljava/io/InputStream;)V
    //   50: invokevirtual 921	java/util/jar/Manifest:getMainAttributes	()Ljava/util/jar/Attributes;
    //   53: astore_3
    //   54: ldc_w 923
    //   57: aload_3
    //   58: ldc_w 925
    //   61: invokevirtual 930	java/util/jar/Attributes:getValue	(Ljava/lang/String;)Ljava/lang/String;
    //   64: invokevirtual 229	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   67: ifeq +74 -> 141
    //   70: new 284	java/lang/StringBuilder
    //   73: dup
    //   74: invokespecial 285	java/lang/StringBuilder:<init>	()V
    //   77: ldc_w 932
    //   80: invokevirtual 291	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   83: aload_3
    //   84: ldc_w 934
    //   87: invokevirtual 930	java/util/jar/Attributes:getValue	(Ljava/lang/String;)Ljava/lang/String;
    //   90: invokevirtual 291	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   93: ldc_w 936
    //   96: invokevirtual 291	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   99: aload_3
    //   100: ldc_w 938
    //   103: invokevirtual 930	java/util/jar/Attributes:getValue	(Ljava/lang/String;)Ljava/lang/String;
    //   106: ldc_w 940
    //   109: ldc_w 936
    //   112: invokevirtual 944	java/lang/String:replaceAll	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   115: invokevirtual 291	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   118: invokevirtual 297	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   121: putstatic 890	org/mozilla/javascript/Context:implementationVersion	Ljava/lang/String;
    //   124: getstatic 890	org/mozilla/javascript/Context:implementationVersion	Ljava/lang/String;
    //   127: astore_3
    //   128: aload_1
    //   129: ifnull +7 -> 136
    //   132: aload_1
    //   133: invokevirtual 949	java/io/InputStream:close	()V
    //   136: aload_3
    //   137: areturn
    //   138: astore_1
    //   139: aconst_null
    //   140: areturn
    //   141: aload_1
    //   142: ifnull -124 -> 18
    //   145: aload_1
    //   146: invokevirtual 949	java/io/InputStream:close	()V
    //   149: goto -131 -> 18
    //   152: astore_1
    //   153: goto -135 -> 18
    //   156: astore_1
    //   157: aconst_null
    //   158: astore_1
    //   159: aload_1
    //   160: ifnull -142 -> 18
    //   163: aload_1
    //   164: invokevirtual 949	java/io/InputStream:close	()V
    //   167: goto -149 -> 18
    //   170: astore_1
    //   171: goto -153 -> 18
    //   174: astore_2
    //   175: aconst_null
    //   176: astore_1
    //   177: aload_1
    //   178: ifnull +7 -> 185
    //   181: aload_1
    //   182: invokevirtual 949	java/io/InputStream:close	()V
    //   185: aload_2
    //   186: athrow
    //   187: getstatic 890	org/mozilla/javascript/Context:implementationVersion	Ljava/lang/String;
    //   190: areturn
    //   191: astore_1
    //   192: aload_3
    //   193: areturn
    //   194: astore_1
    //   195: goto -10 -> 185
    //   198: astore_2
    //   199: goto -22 -> 177
    //   202: astore_3
    //   203: goto -44 -> 159
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	206	0	this	Context
    //   36	97	1	localObject1	Object
    //   138	8	1	localIOException1	IOException
    //   152	1	1	localIOException2	IOException
    //   156	1	1	localIOException3	IOException
    //   158	6	1	localObject2	Object
    //   170	1	1	localIOException4	IOException
    //   176	6	1	localObject3	Object
    //   191	1	1	localIOException5	IOException
    //   194	1	1	localIOException6	IOException
    //   17	11	2	localEnumeration	java.util.Enumeration
    //   174	12	2	localObject4	Object
    //   198	1	2	localObject5	Object
    //   53	140	3	localObject6	Object
    //   202	1	3	localIOException7	IOException
    // Exception table:
    //   from	to	target	type
    //   6	18	138	java/io/IOException
    //   145	149	152	java/io/IOException
    //   37	42	156	java/io/IOException
    //   163	167	170	java/io/IOException
    //   37	42	174	finally
    //   132	136	191	java/io/IOException
    //   181	185	194	java/io/IOException
    //   42	128	198	finally
    //   42	128	202	java/io/IOException
  }
  
  public final int getInstructionObserverThreshold()
  {
    return this.instructionThreshold;
  }
  
  public final int getLanguageVersion()
  {
    return this.version;
  }
  
  public final Locale getLocale()
  {
    if (this.locale == null) {
      this.locale = Locale.getDefault();
    }
    return this.locale;
  }
  
  public final int getMaximumInterpreterStackDepth()
  {
    return this.maximumInterpreterStackDepth;
  }
  
  public final int getOptimizationLevel()
  {
    return this.optimizationLevel;
  }
  
  RegExpProxy getRegExpProxy()
  {
    if (this.regExpProxy == null)
    {
      Class localClass = Kit.classOrNull("org.mozilla.javascript.regexp.RegExpImpl");
      if (localClass != null) {
        this.regExpProxy = ((RegExpProxy)Kit.newInstanceOrNull(localClass));
      }
    }
    return this.regExpProxy;
  }
  
  SecurityController getSecurityController()
  {
    SecurityController localSecurityController = SecurityController.global();
    if (localSecurityController != null) {
      return localSecurityController;
    }
    return this.securityController;
  }
  
  public final Object getThreadLocal(Object paramObject)
  {
    if (this.threadLocalMap == null) {
      return null;
    }
    return this.threadLocalMap.get(paramObject);
  }
  
  public final WrapFactory getWrapFactory()
  {
    if (this.wrapFactory == null) {
      this.wrapFactory = new WrapFactory();
    }
    return this.wrapFactory;
  }
  
  public boolean hasFeature(int paramInt)
  {
    return getFactory().hasFeature(this, paramInt);
  }
  
  public final Scriptable initSafeStandardObjects(ScriptableObject paramScriptableObject)
  {
    return initSafeStandardObjects(paramScriptableObject, false);
  }
  
  public final ScriptableObject initSafeStandardObjects()
  {
    return initSafeStandardObjects(null, false);
  }
  
  public ScriptableObject initSafeStandardObjects(ScriptableObject paramScriptableObject, boolean paramBoolean)
  {
    return ScriptRuntime.initSafeStandardObjects(this, paramScriptableObject, paramBoolean);
  }
  
  public final Scriptable initStandardObjects(ScriptableObject paramScriptableObject)
  {
    return initStandardObjects(paramScriptableObject, false);
  }
  
  public final ScriptableObject initStandardObjects()
  {
    return initStandardObjects(null, false);
  }
  
  public ScriptableObject initStandardObjects(ScriptableObject paramScriptableObject, boolean paramBoolean)
  {
    return ScriptRuntime.initStandardObjects(this, paramScriptableObject, paramBoolean);
  }
  
  public final boolean isActivationNeeded(String paramString)
  {
    return (this.activationNames != null) && (this.activationNames.contains(paramString));
  }
  
  public final boolean isGeneratingDebug()
  {
    return this.generatingDebug;
  }
  
  public final boolean isGeneratingDebugChanged()
  {
    return this.generatingDebugChanged;
  }
  
  public final boolean isGeneratingSource()
  {
    return this.generatingSource;
  }
  
  public final boolean isSealed()
  {
    return this.sealed;
  }
  
  public final boolean isStrictMode()
  {
    return (this.isTopLevelStrict) || ((this.currentActivationCall != null) && (this.currentActivationCall.isStrict));
  }
  
  final boolean isVersionECMA1()
  {
    return (this.version == 0) || (this.version >= 130);
  }
  
  public Scriptable newArray(Scriptable paramScriptable, int paramInt)
  {
    NativeArray localNativeArray = new NativeArray(paramInt);
    ScriptRuntime.setBuiltinProtoAndParent(localNativeArray, paramScriptable, TopLevel.Builtins.Array);
    return localNativeArray;
  }
  
  public Scriptable newArray(Scriptable paramScriptable, Object[] paramArrayOfObject)
  {
    if (paramArrayOfObject.getClass().getComponentType() != ScriptRuntime.ObjectClass) {
      throw new IllegalArgumentException();
    }
    paramArrayOfObject = new NativeArray(paramArrayOfObject);
    ScriptRuntime.setBuiltinProtoAndParent(paramArrayOfObject, paramScriptable, TopLevel.Builtins.Array);
    return paramArrayOfObject;
  }
  
  public Scriptable newObject(Scriptable paramScriptable)
  {
    NativeObject localNativeObject = new NativeObject();
    ScriptRuntime.setBuiltinProtoAndParent(localNativeObject, paramScriptable, TopLevel.Builtins.Object);
    return localNativeObject;
  }
  
  public Scriptable newObject(Scriptable paramScriptable, String paramString)
  {
    return newObject(paramScriptable, paramString, ScriptRuntime.emptyArgs);
  }
  
  public Scriptable newObject(Scriptable paramScriptable, String paramString, Object[] paramArrayOfObject)
  {
    return ScriptRuntime.newObject(this, paramScriptable, paramString, paramArrayOfObject);
  }
  
  protected void observeInstructionCount(int paramInt)
  {
    getFactory().observeInstructionCount(this, paramInt);
  }
  
  public final void putThreadLocal(Object paramObject1, Object paramObject2)
  {
    try
    {
      if (this.sealed) {
        onSealedMutation();
      }
      if (this.threadLocalMap == null) {
        this.threadLocalMap = new HashMap();
      }
      this.threadLocalMap.put(paramObject1, paramObject2);
      return;
    }
    finally {}
  }
  
  public void removeActivationName(String paramString)
  {
    if (this.sealed) {
      onSealedMutation();
    }
    if (this.activationNames != null) {
      this.activationNames.remove(paramString);
    }
  }
  
  public final void removePropertyChangeListener(PropertyChangeListener paramPropertyChangeListener)
  {
    if (this.sealed) {
      onSealedMutation();
    }
    this.propertyListeners = Kit.removeListener(this.propertyListeners, paramPropertyChangeListener);
  }
  
  public final void removeThreadLocal(Object paramObject)
  {
    if (this.sealed) {
      onSealedMutation();
    }
    if (this.threadLocalMap == null) {
      return;
    }
    this.threadLocalMap.remove(paramObject);
  }
  
  public Object resumeContinuation(Object paramObject1, Scriptable paramScriptable, Object paramObject2)
    throws ContinuationPending
  {
    return Interpreter.restartContinuation((NativeContinuation)paramObject1, this, paramScriptable, new Object[] { paramObject2 });
  }
  
  public final void seal(Object paramObject)
  {
    if (this.sealed) {
      onSealedMutation();
    }
    this.sealed = true;
    this.sealKey = paramObject;
  }
  
  public final void setApplicationClassLoader(ClassLoader paramClassLoader)
  {
    if (this.sealed) {
      onSealedMutation();
    }
    if (paramClassLoader == null)
    {
      this.applicationClassLoader = null;
      return;
    }
    if (!Kit.testIfCanLoadRhinoClasses(paramClassLoader)) {
      throw new IllegalArgumentException("Loader can not resolve Rhino classes");
    }
    this.applicationClassLoader = paramClassLoader;
  }
  
  public final void setClassShutter(ClassShutter paramClassShutter)
  {
    try
    {
      if (this.sealed) {
        onSealedMutation();
      }
      if (paramClassShutter == null) {
        throw new IllegalArgumentException();
      }
    }
    finally {}
    if (this.hasClassShutter) {
      throw new SecurityException("Cannot overwrite existing ClassShutter object");
    }
    this.classShutter = paramClassShutter;
    this.hasClassShutter = true;
  }
  
  public final void setDebugger(Debugger paramDebugger, Object paramObject)
  {
    if (this.sealed) {
      onSealedMutation();
    }
    this.debugger = paramDebugger;
    this.debuggerData = paramObject;
  }
  
  public final ErrorReporter setErrorReporter(ErrorReporter paramErrorReporter)
  {
    if (this.sealed) {
      onSealedMutation();
    }
    if (paramErrorReporter == null) {
      throw new IllegalArgumentException();
    }
    ErrorReporter localErrorReporter = getErrorReporter();
    if (paramErrorReporter == localErrorReporter) {
      return localErrorReporter;
    }
    Object localObject = this.propertyListeners;
    if (localObject != null) {
      firePropertyChangeImpl(localObject, "error reporter", localErrorReporter, paramErrorReporter);
    }
    this.errorReporter = paramErrorReporter;
    return localErrorReporter;
  }
  
  public void setGenerateObserverCount(boolean paramBoolean)
  {
    this.generateObserverCount = paramBoolean;
  }
  
  public final void setGeneratingDebug(boolean paramBoolean)
  {
    if (this.sealed) {
      onSealedMutation();
    }
    this.generatingDebugChanged = true;
    if ((paramBoolean) && (getOptimizationLevel() > 0)) {
      setOptimizationLevel(0);
    }
    this.generatingDebug = paramBoolean;
  }
  
  public final void setGeneratingSource(boolean paramBoolean)
  {
    if (this.sealed) {
      onSealedMutation();
    }
    this.generatingSource = paramBoolean;
  }
  
  public final void setInstructionObserverThreshold(int paramInt)
  {
    if (this.sealed) {
      onSealedMutation();
    }
    if (paramInt < 0) {
      throw new IllegalArgumentException();
    }
    this.instructionThreshold = paramInt;
    if (paramInt > 0) {}
    for (boolean bool = true;; bool = false)
    {
      setGenerateObserverCount(bool);
      return;
    }
  }
  
  public void setLanguageVersion(int paramInt)
  {
    if (this.sealed) {
      onSealedMutation();
    }
    checkLanguageVersion(paramInt);
    Object localObject = this.propertyListeners;
    if ((localObject != null) && (paramInt != this.version)) {
      firePropertyChangeImpl(localObject, "language version", Integer.valueOf(this.version), Integer.valueOf(paramInt));
    }
    this.version = paramInt;
  }
  
  public final Locale setLocale(Locale paramLocale)
  {
    if (this.sealed) {
      onSealedMutation();
    }
    Locale localLocale = this.locale;
    this.locale = paramLocale;
    return localLocale;
  }
  
  public final void setMaximumInterpreterStackDepth(int paramInt)
  {
    if (this.sealed) {
      onSealedMutation();
    }
    if (this.optimizationLevel != -1) {
      throw new IllegalStateException("Cannot set maximumInterpreterStackDepth when optimizationLevel != -1");
    }
    if (paramInt < 1) {
      throw new IllegalArgumentException("Cannot set maximumInterpreterStackDepth to less than 1");
    }
    this.maximumInterpreterStackDepth = paramInt;
  }
  
  public final void setOptimizationLevel(int paramInt)
  {
    int j = -1;
    if (this.sealed) {
      onSealedMutation();
    }
    int i = paramInt;
    if (paramInt == -2) {
      i = -1;
    }
    checkOptimizationLevel(i);
    if (codegenClass == null) {}
    for (paramInt = j;; paramInt = i)
    {
      this.optimizationLevel = paramInt;
      return;
    }
  }
  
  public final void setSecurityController(SecurityController paramSecurityController)
  {
    if (this.sealed) {
      onSealedMutation();
    }
    if (paramSecurityController == null) {
      throw new IllegalArgumentException();
    }
    if (this.securityController != null) {
      throw new SecurityException("Can not overwrite existing SecurityController object");
    }
    if (SecurityController.hasGlobal()) {
      throw new SecurityException("Can not overwrite existing global SecurityController object");
    }
    this.securityController = paramSecurityController;
  }
  
  public final void setWrapFactory(WrapFactory paramWrapFactory)
  {
    if (this.sealed) {
      onSealedMutation();
    }
    if (paramWrapFactory == null) {
      throw new IllegalArgumentException();
    }
    this.wrapFactory = paramWrapFactory;
  }
  
  public final boolean stringIsCompilableUnit(String paramString)
  {
    Object localObject = new CompilerEnvirons();
    ((CompilerEnvirons)localObject).initFromContext(this);
    ((CompilerEnvirons)localObject).setGeneratingSource(false);
    localObject = new Parser((CompilerEnvirons)localObject, DefaultErrorReporter.instance);
    try
    {
      ((Parser)localObject).parse(paramString, null, 1);
      i = 0;
    }
    catch (EvaluatorException paramString)
    {
      for (;;)
      {
        int i = 1;
      }
    }
    return (i == 0) || (!((Parser)localObject).eof());
  }
  
  public final void unseal(Object paramObject)
  {
    if (paramObject == null) {
      throw new IllegalArgumentException();
    }
    if (this.sealKey != paramObject) {
      throw new IllegalArgumentException();
    }
    if (!this.sealed) {
      throw new IllegalStateException();
    }
    this.sealed = false;
    this.sealKey = null;
  }
  
  public static abstract interface ClassShutterSetter
  {
    public abstract ClassShutter getClassShutter();
    
    public abstract void setClassShutter(ClassShutter paramClassShutter);
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\Context.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */