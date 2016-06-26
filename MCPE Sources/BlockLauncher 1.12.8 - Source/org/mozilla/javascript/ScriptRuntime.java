package org.mozilla.javascript;

import java.io.PrintStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.mozilla.javascript.v8dtoa.DoubleConversion;
import org.mozilla.javascript.v8dtoa.FastDtoa;
import org.mozilla.javascript.xml.XMLLib;
import org.mozilla.javascript.xml.XMLLib.Factory;
import org.mozilla.javascript.xml.XMLObject;

public class ScriptRuntime
{
  public static final Class<?> BooleanClass = Kit.classOrNull("java.lang.Boolean");
  public static final Class<?> ByteClass = Kit.classOrNull("java.lang.Byte");
  public static final Class<?> CharacterClass = Kit.classOrNull("java.lang.Character");
  public static final Class<?> ClassClass = Kit.classOrNull("java.lang.Class");
  public static final Class<?> ContextClass;
  public static final Class<?> ContextFactoryClass;
  private static final String DEFAULT_NS_TAG = "__default_namespace__";
  public static final Class<?> DateClass;
  public static final Class<?> DoubleClass = Kit.classOrNull("java.lang.Double");
  public static final int ENUMERATE_ARRAY = 2;
  public static final int ENUMERATE_ARRAY_NO_ITERATOR = 5;
  public static final int ENUMERATE_KEYS = 0;
  public static final int ENUMERATE_KEYS_NO_ITERATOR = 3;
  public static final int ENUMERATE_VALUES = 1;
  public static final int ENUMERATE_VALUES_IN_ORDER = 6;
  public static final int ENUMERATE_VALUES_NO_ITERATOR = 4;
  public static final Class<?> FloatClass = Kit.classOrNull("java.lang.Float");
  public static final Class<?> FunctionClass;
  public static final Class<?> IntegerClass = Kit.classOrNull("java.lang.Integer");
  private static final Object LIBRARY_SCOPE_KEY;
  public static final Class<?> LongClass = Kit.classOrNull("java.lang.Long");
  public static final double NaN;
  public static final Double NaNobj;
  public static final Class<?> NumberClass = Kit.classOrNull("java.lang.Number");
  public static final Class<?> ObjectClass = Kit.classOrNull("java.lang.Object");
  public static Locale ROOT_LOCALE;
  public static final Class<Scriptable> ScriptableClass;
  public static final Class<?> ScriptableObjectClass;
  public static final Class<?> ShortClass = Kit.classOrNull("java.lang.Short");
  public static final Class<?> StringClass = Kit.classOrNull("java.lang.String");
  public static final Object[] emptyArgs = new Object[0];
  public static final String[] emptyStrings = new String[0];
  public static MessageProvider messageProvider;
  public static final double negativeZero;
  
  static
  {
    DateClass = Kit.classOrNull("java.util.Date");
    ContextClass = Kit.classOrNull("org.mozilla.javascript.Context");
    ContextFactoryClass = Kit.classOrNull("org.mozilla.javascript.ContextFactory");
    FunctionClass = Kit.classOrNull("org.mozilla.javascript.Function");
    ScriptableObjectClass = Kit.classOrNull("org.mozilla.javascript.ScriptableObject");
    ScriptableClass = Scriptable.class;
    ROOT_LOCALE = new Locale("");
    LIBRARY_SCOPE_KEY = "LIBRARY_SCOPE";
    NaN = Double.longBitsToDouble(9221120237041090560L);
    negativeZero = Double.longBitsToDouble(Long.MIN_VALUE);
    NaNobj = new Double(NaN);
    messageProvider = new DefaultMessageProvider(null);
  }
  
  public static CharSequence add(CharSequence paramCharSequence, Object paramObject)
  {
    return new ConsString(paramCharSequence, toCharSequence(paramObject));
  }
  
  public static CharSequence add(Object paramObject, CharSequence paramCharSequence)
  {
    return new ConsString(toCharSequence(paramObject), paramCharSequence);
  }
  
  public static Object add(Object paramObject1, Object paramObject2, Context paramContext)
  {
    Object localObject1;
    if (((paramObject1 instanceof Number)) && ((paramObject2 instanceof Number))) {
      localObject1 = wrapNumber(((Number)paramObject1).doubleValue() + ((Number)paramObject2).doubleValue());
    }
    do
    {
      Object localObject2;
      do
      {
        return localObject1;
        if (!(paramObject1 instanceof XMLObject)) {
          break;
        }
        localObject2 = ((XMLObject)paramObject1).addValues(paramContext, true, paramObject2);
        localObject1 = localObject2;
      } while (localObject2 != Scriptable.NOT_FOUND);
      if (!(paramObject2 instanceof XMLObject)) {
        break;
      }
      paramContext = ((XMLObject)paramObject2).addValues(paramContext, false, paramObject1);
      localObject1 = paramContext;
    } while (paramContext != Scriptable.NOT_FOUND);
    if ((paramObject1 instanceof Scriptable)) {
      paramObject1 = ((Scriptable)paramObject1).getDefaultValue(null);
    }
    for (;;)
    {
      if ((paramObject2 instanceof Scriptable)) {
        paramObject2 = ((Scriptable)paramObject2).getDefaultValue(null);
      }
      for (;;)
      {
        if ((!(paramObject1 instanceof CharSequence)) && (!(paramObject2 instanceof CharSequence)))
        {
          if (((paramObject1 instanceof Number)) && ((paramObject2 instanceof Number)))
          {
            d = ((Number)paramObject1).doubleValue();
            return wrapNumber(((Number)paramObject2).doubleValue() + d);
          }
          double d = toNumber(paramObject1);
          return wrapNumber(toNumber(paramObject2) + d);
        }
        return new ConsString(toCharSequence(paramObject1), toCharSequence(paramObject2));
      }
    }
  }
  
  public static void addInstructionCount(Context paramContext, int paramInt)
  {
    paramContext.instructionCount += paramInt;
    if (paramContext.instructionCount > paramContext.instructionThreshold)
    {
      paramContext.observeInstructionCount(paramContext.instructionCount);
      paramContext.instructionCount = 0;
    }
  }
  
  public static Object applyOrCall(boolean paramBoolean, Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject)
  {
    int i = paramArrayOfObject.length;
    Callable localCallable = getCallable(paramScriptable2);
    paramScriptable2 = null;
    if (i != 0)
    {
      if (paramContext.hasFeature(15)) {
        paramScriptable2 = toObjectOrNull(paramContext, paramArrayOfObject[0], paramScriptable1);
      }
    }
    else {
      if ((paramScriptable2 != null) || (!paramContext.hasFeature(15))) {
        break label161;
      }
    }
    label161:
    for (Scriptable localScriptable = getTopCallScope(paramContext);; localScriptable = paramScriptable2)
    {
      if (paramBoolean) {
        if (i <= 1) {
          paramScriptable2 = emptyArgs;
        }
      }
      for (;;)
      {
        return localCallable.call(paramContext, paramScriptable1, localScriptable, paramScriptable2);
        if (paramArrayOfObject[0] == Undefined.instance)
        {
          paramScriptable2 = Undefined.SCRIPTABLE_UNDEFINED;
          break;
        }
        paramScriptable2 = toObjectOrNull(paramContext, paramArrayOfObject[0], paramScriptable1);
        break;
        paramScriptable2 = getApplyArguments(paramContext, paramArrayOfObject[1]);
        continue;
        if (i <= 1)
        {
          paramScriptable2 = emptyArgs;
        }
        else
        {
          paramScriptable2 = new Object[i - 1];
          System.arraycopy(paramArrayOfObject, 1, paramScriptable2, 0, i - 1);
        }
      }
    }
  }
  
  public static Scriptable bind(Context paramContext, Scriptable paramScriptable, String paramString)
  {
    Object localObject1 = null;
    Object localObject2 = null;
    Scriptable localScriptable = paramScriptable.getParentScope();
    Object localObject3;
    if (localScriptable != null)
    {
      localObject1 = localScriptable;
      localScriptable = paramScriptable;
      paramScriptable = (Scriptable)localObject1;
      localObject1 = localObject2;
      localObject2 = paramScriptable;
      localObject3 = localScriptable;
      if ((localScriptable instanceof NativeWith))
      {
        localScriptable = localScriptable.getPrototype();
        if ((localScriptable instanceof XMLObject))
        {
          localObject2 = (XMLObject)localScriptable;
          if (((XMLObject)localObject2).has(paramContext, paramString)) {
            label78:
            return (Scriptable)localObject2;
          }
          if (localObject1 != null) {
            break label146;
          }
          localObject1 = localObject2;
          label88:
          localObject2 = paramScriptable.getParentScope();
          if (localObject2 != null) {
            break label149;
          }
        }
      }
    }
    for (;;)
    {
      localObject2 = paramScriptable;
      if (paramContext.useDynamicScope) {
        localObject2 = checkDynamicScope(paramContext.topCallScope, paramScriptable);
      }
      if (ScriptableObject.hasProperty((Scriptable)localObject2, paramString))
      {
        return (Scriptable)localObject2;
        localObject2 = localScriptable;
        if (ScriptableObject.hasProperty(localScriptable, paramString)) {
          break label78;
        }
        label146:
        break label88;
        label149:
        localScriptable = paramScriptable;
        paramScriptable = (Scriptable)localObject2;
        break;
        do
        {
          localObject3 = localObject2;
          localObject2 = paramScriptable;
          if (ScriptableObject.hasProperty((Scriptable)localObject3, paramString)) {
            return (Scriptable)localObject3;
          }
          paramScriptable = ((Scriptable)localObject2).getParentScope();
        } while (paramScriptable != null);
        paramScriptable = (Scriptable)localObject2;
        continue;
      }
      return (Scriptable)localObject1;
    }
  }
  
  @Deprecated
  public static Object call(Context paramContext, Object paramObject1, Object paramObject2, Object[] paramArrayOfObject, Scriptable paramScriptable)
  {
    if (!(paramObject1 instanceof Function)) {
      throw notFunctionError(toString(paramObject1));
    }
    paramObject1 = (Function)paramObject1;
    paramObject2 = toObjectOrNull(paramContext, paramObject2, paramScriptable);
    if (paramObject2 == null) {
      throw undefCallError(paramObject2, "function");
    }
    return ((Function)paramObject1).call(paramContext, paramScriptable, (Scriptable)paramObject2, paramArrayOfObject);
  }
  
  public static Ref callRef(Callable paramCallable, Scriptable paramScriptable, Object[] paramArrayOfObject, Context paramContext)
  {
    if ((paramCallable instanceof RefCallable))
    {
      paramCallable = (RefCallable)paramCallable;
      paramScriptable = paramCallable.refCall(paramContext, paramScriptable, paramArrayOfObject);
      if (paramScriptable == null) {
        throw new IllegalStateException(paramCallable.getClass().getName() + ".refCall() returned null");
      }
    }
    else
    {
      throw constructError("ReferenceError", getMessage1("msg.no.ref.from.function", toString(paramCallable)));
    }
    return paramScriptable;
  }
  
  public static Object callSpecial(Context paramContext, Callable paramCallable, Scriptable paramScriptable1, Object[] paramArrayOfObject, Scriptable paramScriptable2, Scriptable paramScriptable3, int paramInt1, String paramString, int paramInt2)
  {
    if (paramInt1 == 1)
    {
      if ((paramScriptable1.getParentScope() == null) && (NativeGlobal.isEvalFunction(paramCallable))) {
        return evalSpecial(paramContext, paramScriptable2, paramScriptable3, paramArrayOfObject, paramString, paramInt2);
      }
    }
    else if (paramInt1 == 2)
    {
      if (NativeWith.isWithFunction(paramCallable)) {
        throw Context.reportRuntimeError1("msg.only.from.new", "With");
      }
    }
    else {
      throw Kit.codeBug();
    }
    return paramCallable.call(paramContext, paramScriptable2, paramScriptable1, paramArrayOfObject);
  }
  
  static void checkDeprecated(Context paramContext, String paramString)
  {
    int i = paramContext.getLanguageVersion();
    if ((i >= 140) || (i == 0))
    {
      paramContext = getMessage1("msg.deprec.ctor", paramString);
      if (i == 0) {
        Context.reportWarning(paramContext);
      }
    }
    else
    {
      return;
    }
    throw Context.reportRuntimeError(paramContext);
  }
  
  static Scriptable checkDynamicScope(Scriptable paramScriptable1, Scriptable paramScriptable2)
  {
    if (paramScriptable1 == paramScriptable2) {
      return paramScriptable1;
    }
    Object localObject = paramScriptable1;
    Scriptable localScriptable;
    do
    {
      localScriptable = ((Scriptable)localObject).getPrototype();
      if (localScriptable == paramScriptable2) {
        break;
      }
      localObject = localScriptable;
    } while (localScriptable != null);
    return paramScriptable2;
  }
  
  public static RegExpProxy checkRegExpProxy(Context paramContext)
  {
    paramContext = getRegExpProxy(paramContext);
    if (paramContext == null) {
      throw Context.reportRuntimeError0("msg.no.regexp");
    }
    return paramContext;
  }
  
  public static boolean cmp_LE(Object paramObject1, Object paramObject2)
  {
    double d2;
    if (((paramObject1 instanceof Number)) && ((paramObject2 instanceof Number))) {
      d2 = ((Number)paramObject1).doubleValue();
    }
    for (double d1 = ((Number)paramObject2).doubleValue(); d2 <= d1; d1 = toNumber(paramObject1))
    {
      Object localObject;
      do
      {
        return true;
        localObject = paramObject1;
        if ((paramObject1 instanceof Scriptable)) {
          localObject = ((Scriptable)paramObject1).getDefaultValue(NumberClass);
        }
        paramObject1 = paramObject2;
        if ((paramObject2 instanceof Scriptable)) {
          paramObject1 = ((Scriptable)paramObject2).getDefaultValue(NumberClass);
        }
        if ((!(localObject instanceof CharSequence)) || (!(paramObject1 instanceof CharSequence))) {
          break;
        }
      } while (localObject.toString().compareTo(paramObject1.toString()) <= 0);
      return false;
      d2 = toNumber(localObject);
    }
    return false;
  }
  
  public static boolean cmp_LT(Object paramObject1, Object paramObject2)
  {
    double d2;
    if (((paramObject1 instanceof Number)) && ((paramObject2 instanceof Number))) {
      d2 = ((Number)paramObject1).doubleValue();
    }
    for (double d1 = ((Number)paramObject2).doubleValue(); d2 < d1; d1 = toNumber(paramObject1))
    {
      Object localObject;
      do
      {
        return true;
        localObject = paramObject1;
        if ((paramObject1 instanceof Scriptable)) {
          localObject = ((Scriptable)paramObject1).getDefaultValue(NumberClass);
        }
        paramObject1 = paramObject2;
        if ((paramObject2 instanceof Scriptable)) {
          paramObject1 = ((Scriptable)paramObject2).getDefaultValue(NumberClass);
        }
        if ((!(localObject instanceof CharSequence)) || (!(paramObject1 instanceof CharSequence))) {
          break;
        }
      } while (localObject.toString().compareTo(paramObject1.toString()) < 0);
      return false;
      d2 = toNumber(localObject);
    }
    return false;
  }
  
  public static EcmaError constructError(String paramString1, String paramString2)
  {
    int[] arrayOfInt = new int[1];
    return constructError(paramString1, paramString2, Context.getSourcePositionFromStack(arrayOfInt), arrayOfInt[0], null, 0);
  }
  
  public static EcmaError constructError(String paramString1, String paramString2, int paramInt)
  {
    int[] arrayOfInt = new int[1];
    String str = Context.getSourcePositionFromStack(arrayOfInt);
    if (arrayOfInt[0] != 0) {
      arrayOfInt[0] += paramInt;
    }
    return constructError(paramString1, paramString2, str, arrayOfInt[0], null, 0);
  }
  
  public static EcmaError constructError(String paramString1, String paramString2, String paramString3, int paramInt1, String paramString4, int paramInt2)
  {
    return new EcmaError(paramString1, paramString2, paramString3, paramInt1, paramString4, paramInt2);
  }
  
  public static Scriptable createArrowFunctionActivation(NativeFunction paramNativeFunction, Scriptable paramScriptable, Object[] paramArrayOfObject, boolean paramBoolean)
  {
    return new NativeCall(paramNativeFunction, paramScriptable, paramArrayOfObject, true, paramBoolean);
  }
  
  @Deprecated
  public static Scriptable createFunctionActivation(NativeFunction paramNativeFunction, Scriptable paramScriptable, Object[] paramArrayOfObject)
  {
    return createFunctionActivation(paramNativeFunction, paramScriptable, paramArrayOfObject, false);
  }
  
  public static Scriptable createFunctionActivation(NativeFunction paramNativeFunction, Scriptable paramScriptable, Object[] paramArrayOfObject, boolean paramBoolean)
  {
    return new NativeCall(paramNativeFunction, paramScriptable, paramArrayOfObject, false, paramBoolean);
  }
  
  private static XMLLib currentXMLLib(Context paramContext)
  {
    if (paramContext.topCallScope == null) {
      throw new IllegalStateException();
    }
    XMLLib localXMLLib2 = paramContext.cachedXMLLib;
    XMLLib localXMLLib1 = localXMLLib2;
    if (localXMLLib2 == null)
    {
      localXMLLib1 = XMLLib.extractFromScope(paramContext.topCallScope);
      if (localXMLLib1 == null) {
        throw new IllegalStateException();
      }
      paramContext.cachedXMLLib = localXMLLib1;
    }
    return localXMLLib1;
  }
  
  static String defaultObjectToSource(Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject)
  {
    int i;
    boolean bool;
    StringBuilder localStringBuilder;
    if (paramContext.iterating == null)
    {
      paramContext.iterating = new ObjToIntMap(31);
      i = 1;
      bool = false;
      localStringBuilder = new StringBuilder(128);
      if (i != 0) {
        localStringBuilder.append("(");
      }
      localStringBuilder.append('{');
      if (bool) {}
    }
    else
    {
      for (;;)
      {
        int j;
        Object localObject;
        try
        {
          paramContext.iterating.intern(paramScriptable2);
          Object[] arrayOfObject = paramScriptable2.getIds();
          j = 0;
          if (j >= arrayOfObject.length) {
            break label312;
          }
          paramArrayOfObject = arrayOfObject[j];
          if ((paramArrayOfObject instanceof Integer))
          {
            int k = ((Integer)paramArrayOfObject).intValue();
            paramArrayOfObject = paramScriptable2.get(k, paramScriptable2);
            localObject = Scriptable.NOT_FOUND;
            if (paramArrayOfObject == localObject)
            {
              j += 1;
              continue;
              bool = paramContext.iterating.has(paramScriptable2);
              i = 0;
              break;
            }
            if (j > 0) {
              localStringBuilder.append(", ");
            }
            localStringBuilder.append(k);
            localStringBuilder.append(':');
            localStringBuilder.append(uneval(paramContext, paramScriptable1, paramArrayOfObject));
            continue;
          }
          localObject = (String)paramArrayOfObject;
        }
        finally
        {
          if (i != 0) {
            paramContext.iterating = null;
          }
        }
        paramArrayOfObject = paramScriptable2.get((String)localObject, paramScriptable2);
        if (paramArrayOfObject != Scriptable.NOT_FOUND)
        {
          if (j > 0) {
            localStringBuilder.append(", ");
          }
          if (isValidIdentifierName((String)localObject, paramContext, paramContext.isStrictMode()))
          {
            localStringBuilder.append((String)localObject);
          }
          else
          {
            localStringBuilder.append('\'');
            localStringBuilder.append(escapeString((String)localObject, '\''));
            localStringBuilder.append('\'');
          }
        }
      }
    }
    label312:
    if (i != 0) {
      paramContext.iterating = null;
    }
    localStringBuilder.append('}');
    if (i != 0) {
      localStringBuilder.append(')');
    }
    return localStringBuilder.toString();
  }
  
  static String defaultObjectToString(Scriptable paramScriptable)
  {
    if (paramScriptable == null) {
      return "[object Null]";
    }
    if (Undefined.isUndefined(paramScriptable)) {
      return "[object Undefined]";
    }
    return "[object " + paramScriptable.getClassName() + ']';
  }
  
  @Deprecated
  public static Object delete(Object paramObject1, Object paramObject2, Context paramContext)
  {
    return delete(paramObject1, paramObject2, paramContext, false);
  }
  
  public static Object delete(Object paramObject1, Object paramObject2, Context paramContext, Scriptable paramScriptable, boolean paramBoolean)
  {
    paramScriptable = toObjectOrNull(paramContext, paramObject1, paramScriptable);
    if (paramScriptable == null)
    {
      if (paramBoolean) {
        return Boolean.TRUE;
      }
      throw undefDeleteError(paramObject1, paramObject2);
    }
    return wrapBoolean(deleteObjectElem(paramScriptable, paramObject2, paramContext));
  }
  
  @Deprecated
  public static Object delete(Object paramObject1, Object paramObject2, Context paramContext, boolean paramBoolean)
  {
    return delete(paramObject1, paramObject2, paramContext, getTopCallScope(paramContext), paramBoolean);
  }
  
  public static boolean deleteObjectElem(Scriptable paramScriptable, Object paramObject, Context paramContext)
  {
    paramObject = toStringIdOrIndex(paramContext, paramObject);
    if (paramObject == null)
    {
      int i = lastIndexResult(paramContext);
      paramScriptable.delete(i);
      if (paramScriptable.has(i, paramScriptable)) {}
    }
    do
    {
      return true;
      return false;
      paramScriptable.delete((String)paramObject);
    } while (!paramScriptable.has((String)paramObject, paramScriptable));
    return false;
  }
  
  private static Object doScriptableIncrDecr(Scriptable paramScriptable1, String paramString, Scriptable paramScriptable2, Object paramObject, int paramInt)
  {
    int i;
    double d;
    if ((paramInt & 0x2) != 0)
    {
      i = 1;
      if (!(paramObject instanceof Number)) {
        break label69;
      }
      d = ((Number)paramObject).doubleValue();
    }
    label26:
    label69:
    label98:
    label101:
    for (;;)
    {
      if ((paramInt & 0x1) == 0) {}
      Number localNumber;
      for (d += 1.0D;; d -= 1.0D)
      {
        localNumber = wrapNumber(d);
        paramScriptable1.put(paramString, paramScriptable2, localNumber);
        if (i == 0) {
          break label98;
        }
        return paramObject;
        i = 0;
        break;
        d = toNumber(paramObject);
        if (i == 0) {
          break label101;
        }
        paramObject = wrapNumber(d);
        break label26;
      }
      return localNumber;
    }
  }
  
  public static Object doTopCall(Callable paramCallable, Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject)
  {
    return doTopCall(paramCallable, paramContext, paramScriptable1, paramScriptable2, paramArrayOfObject, paramContext.isTopLevelStrict);
  }
  
  public static Object doTopCall(Callable paramCallable, Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject, boolean paramBoolean)
  {
    if (paramScriptable1 == null) {
      throw new IllegalArgumentException();
    }
    if (paramContext.topCallScope != null) {
      throw new IllegalStateException();
    }
    paramContext.topCallScope = ScriptableObject.getTopLevelScope(paramScriptable1);
    paramContext.useDynamicScope = paramContext.hasFeature(7);
    boolean bool = paramContext.isTopLevelStrict;
    paramContext.isTopLevelStrict = paramBoolean;
    ContextFactory localContextFactory = paramContext.getFactory();
    try
    {
      paramCallable = localContextFactory.doTopCall(paramCallable, paramContext, paramScriptable1, paramScriptable2, paramArrayOfObject);
      paramContext.topCallScope = null;
      paramContext.cachedXMLLib = null;
      paramContext.isTopLevelStrict = bool;
      if (paramContext.currentActivationCall != null) {
        throw new IllegalStateException();
      }
    }
    finally
    {
      paramContext.topCallScope = null;
      paramContext.cachedXMLLib = null;
      paramContext.isTopLevelStrict = bool;
      if (paramContext.currentActivationCall != null) {
        throw new IllegalStateException();
      }
    }
    return paramCallable;
  }
  
  @Deprecated
  public static Object elemIncrDecr(Object paramObject1, Object paramObject2, Context paramContext, int paramInt)
  {
    return elemIncrDecr(paramObject1, paramObject2, paramContext, getTopCallScope(paramContext), paramInt);
  }
  
  public static Object elemIncrDecr(Object paramObject1, Object paramObject2, Context paramContext, Scriptable paramScriptable, int paramInt)
  {
    Object localObject = getObjectElem(paramObject1, paramObject2, paramContext, paramScriptable);
    int i;
    double d;
    if ((paramInt & 0x2) != 0)
    {
      i = 1;
      if (!(localObject instanceof Number)) {
        break label86;
      }
      d = ((Number)localObject).doubleValue();
    }
    label37:
    label86:
    label117:
    for (;;)
    {
      if ((paramInt & 0x1) == 0) {}
      for (d += 1.0D;; d -= 1.0D)
      {
        Number localNumber = wrapNumber(d);
        setObjectElem(paramObject1, paramObject2, localNumber, paramContext, paramScriptable);
        paramObject1 = localNumber;
        if (i != 0) {
          paramObject1 = localObject;
        }
        return paramObject1;
        i = 0;
        break;
        d = toNumber(localObject);
        if (i == 0) {
          break label117;
        }
        localObject = wrapNumber(d);
        break label37;
      }
    }
  }
  
  public static void enterActivationFunction(Context paramContext, Scriptable paramScriptable)
  {
    if (paramContext.topCallScope == null) {
      throw new IllegalStateException();
    }
    paramScriptable = (NativeCall)paramScriptable;
    paramScriptable.parentActivationCall = paramContext.currentActivationCall;
    paramContext.currentActivationCall = paramScriptable;
    paramScriptable.defineAttributesForArguments();
  }
  
  public static Scriptable enterDotQuery(Object paramObject, Scriptable paramScriptable)
  {
    if (!(paramObject instanceof XMLObject)) {
      throw notXmlError(paramObject);
    }
    return ((XMLObject)paramObject).enterDotQuery(paramScriptable);
  }
  
  public static Scriptable enterWith(Object paramObject, Context paramContext, Scriptable paramScriptable)
  {
    paramContext = toObjectOrNull(paramContext, paramObject, paramScriptable);
    if (paramContext == null) {
      throw typeError1("msg.undef.with", toString(paramObject));
    }
    if ((paramContext instanceof XMLObject)) {
      return ((XMLObject)paramContext).enterWith(paramScriptable);
    }
    return new NativeWith(paramScriptable, paramContext);
  }
  
  private static void enumChangeObject(IdEnumeration paramIdEnumeration)
  {
    Object[] arrayOfObject1 = null;
    for (;;)
    {
      if (paramIdEnumeration.obj != null)
      {
        arrayOfObject1 = paramIdEnumeration.obj.getIds();
        if (arrayOfObject1.length == 0) {}
      }
      else
      {
        if ((paramIdEnumeration.obj == null) || (paramIdEnumeration.ids == null)) {
          break;
        }
        Object[] arrayOfObject2 = paramIdEnumeration.ids;
        int j = arrayOfObject2.length;
        if (paramIdEnumeration.used == null) {
          paramIdEnumeration.used = new ObjToIntMap(j);
        }
        int i = 0;
        while (i != j)
        {
          paramIdEnumeration.used.intern(arrayOfObject2[i]);
          i += 1;
        }
      }
      paramIdEnumeration.obj = paramIdEnumeration.obj.getPrototype();
    }
    paramIdEnumeration.ids = arrayOfObject1;
    paramIdEnumeration.index = 0;
  }
  
  public static Object enumId(Object paramObject, Context paramContext)
  {
    IdEnumeration localIdEnumeration = (IdEnumeration)paramObject;
    if (localIdEnumeration.iterator != null) {
      return localIdEnumeration.currentId;
    }
    switch (localIdEnumeration.enumType)
    {
    default: 
      throw Kit.codeBug();
    case 0: 
    case 3: 
      return localIdEnumeration.currentId;
    case 1: 
    case 4: 
      return enumValue(paramObject, paramContext);
    }
    Object localObject = localIdEnumeration.currentId;
    paramObject = enumValue(paramObject, paramContext);
    return paramContext.newArray(ScriptableObject.getTopLevelScope(localIdEnumeration.obj), new Object[] { localObject, paramObject });
  }
  
  @Deprecated
  public static Object enumInit(Object paramObject, Context paramContext, int paramInt)
  {
    return enumInit(paramObject, paramContext, getTopCallScope(paramContext), paramInt);
  }
  
  public static Object enumInit(Object paramObject, Context paramContext, Scriptable paramScriptable, int paramInt)
  {
    IdEnumeration localIdEnumeration = new IdEnumeration(null);
    localIdEnumeration.obj = toObjectOrNull(paramContext, paramObject, paramScriptable);
    if (paramInt == 6)
    {
      localIdEnumeration.enumType = paramInt;
      localIdEnumeration.iterator = null;
      return enumInitInOrder(paramContext, localIdEnumeration);
    }
    if (localIdEnumeration.obj == null) {
      return localIdEnumeration;
    }
    localIdEnumeration.enumType = paramInt;
    localIdEnumeration.iterator = null;
    if ((paramInt != 3) && (paramInt != 4) && (paramInt != 5))
    {
      paramObject = localIdEnumeration.obj.getParentScope();
      paramScriptable = localIdEnumeration.obj;
      if (paramInt != 0) {
        break label137;
      }
    }
    label137:
    for (boolean bool = true;; bool = false)
    {
      localIdEnumeration.iterator = toIterator(paramContext, (Scriptable)paramObject, paramScriptable, bool);
      if (localIdEnumeration.iterator == null) {
        enumChangeObject(localIdEnumeration);
      }
      return localIdEnumeration;
    }
  }
  
  @Deprecated
  public static Object enumInit(Object paramObject, Context paramContext, boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (int i = 1;; i = 0) {
      return enumInit(paramObject, paramContext, i);
    }
  }
  
  private static Object enumInitInOrder(Context paramContext, IdEnumeration paramIdEnumeration)
  {
    if ((paramIdEnumeration.obj == null) || (!ScriptableObject.hasProperty(paramIdEnumeration.obj, "@@iterator"))) {
      throw typeError1("msg.not.iterable", toString(paramIdEnumeration.obj));
    }
    Object localObject = ScriptableObject.getProperty(paramIdEnumeration.obj, "@@iterator");
    if (!(localObject instanceof Callable)) {
      throw typeError1("msg.not.iterable", toString(paramIdEnumeration.obj));
    }
    paramContext = ((Callable)localObject).call(paramContext, paramIdEnumeration.obj.getParentScope(), paramIdEnumeration.obj, new Object[0]);
    if (!(paramContext instanceof Scriptable)) {
      throw typeError1("msg.not.iterable", toString(paramIdEnumeration.obj));
    }
    paramIdEnumeration.iterator = ((Scriptable)paramContext);
    return paramIdEnumeration;
  }
  
  public static Boolean enumNext(Object paramObject)
  {
    IdEnumeration localIdEnumeration = (IdEnumeration)paramObject;
    Context localContext;
    if (localIdEnumeration.iterator != null)
    {
      if (localIdEnumeration.enumType == 6) {
        return enumNextInOrder(localIdEnumeration);
      }
      paramObject = ScriptableObject.getProperty(localIdEnumeration.iterator, "next");
      if (!(paramObject instanceof Callable)) {
        return Boolean.FALSE;
      }
      paramObject = (Callable)paramObject;
      localContext = Context.getContext();
    }
    label136:
    int i;
    do
    {
      do
      {
        do
        {
          try
          {
            localIdEnumeration.currentId = ((Callable)paramObject).call(localContext, localIdEnumeration.iterator.getParentScope(), localIdEnumeration.iterator, emptyArgs);
            paramObject = Boolean.TRUE;
            return (Boolean)paramObject;
          }
          catch (JavaScriptException paramObject)
          {
            if ((((JavaScriptException)paramObject).getValue() instanceof NativeIterator.StopIteration)) {
              return Boolean.FALSE;
            }
            throw ((Throwable)paramObject);
          }
          break label136;
          while (localIdEnumeration.index == localIdEnumeration.ids.length)
          {
            localIdEnumeration.obj = localIdEnumeration.obj.getPrototype();
            enumChangeObject(localIdEnumeration);
            if (localIdEnumeration.obj == null) {
              return Boolean.FALSE;
            }
          }
          paramObject = localIdEnumeration.ids;
          i = localIdEnumeration.index;
          localIdEnumeration.index = (i + 1);
          paramObject = paramObject[i];
        } while ((localIdEnumeration.used != null) && (localIdEnumeration.used.has(paramObject)));
        if (!(paramObject instanceof String)) {
          break;
        }
        paramObject = (String)paramObject;
      } while (!localIdEnumeration.obj.has((String)paramObject, localIdEnumeration.obj));
      localIdEnumeration.currentId = paramObject;
      return Boolean.TRUE;
      i = ((Number)paramObject).intValue();
    } while (!localIdEnumeration.obj.has(i, localIdEnumeration.obj));
    if (localIdEnumeration.enumNumbers) {}
    for (paramObject = Integer.valueOf(i);; paramObject = String.valueOf(i))
    {
      localIdEnumeration.currentId = paramObject;
      break;
    }
  }
  
  private static Boolean enumNextInOrder(IdEnumeration paramIdEnumeration)
  {
    Object localObject1 = ScriptableObject.getProperty(paramIdEnumeration.iterator, "next");
    if (!(localObject1 instanceof Callable)) {
      throw notFunctionError(paramIdEnumeration.iterator, "next");
    }
    localObject1 = (Callable)localObject1;
    Object localObject2 = Context.getContext();
    Scriptable localScriptable = paramIdEnumeration.iterator.getParentScope();
    localObject1 = toObject((Context)localObject2, localScriptable, ((Callable)localObject1).call((Context)localObject2, localScriptable, paramIdEnumeration.iterator, emptyArgs));
    localObject2 = ScriptableObject.getProperty((Scriptable)localObject1, "done");
    if ((localObject2 != ScriptableObject.NOT_FOUND) && (toBoolean(localObject2))) {
      return Boolean.FALSE;
    }
    paramIdEnumeration.currentId = ScriptableObject.getProperty((Scriptable)localObject1, "value");
    return Boolean.TRUE;
  }
  
  public static Object enumValue(Object paramObject, Context paramContext)
  {
    paramObject = (IdEnumeration)paramObject;
    String str = toStringIdOrIndex(paramContext, ((IdEnumeration)paramObject).currentId);
    if (str == null)
    {
      int i = lastIndexResult(paramContext);
      return ((IdEnumeration)paramObject).obj.get(i, ((IdEnumeration)paramObject).obj);
    }
    return ((IdEnumeration)paramObject).obj.get(str, ((IdEnumeration)paramObject).obj);
  }
  
  public static boolean eq(Object paramObject1, Object paramObject2)
  {
    double d = 1.0D;
    boolean bool1 = true;
    boolean bool2 = false;
    if ((paramObject1 == null) || (paramObject1 == Undefined.instance)) {
      if ((paramObject2 == null) || (paramObject2 == Undefined.instance)) {
        bool1 = true;
      }
    }
    do
    {
      Object localObject;
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                do
                {
                  do
                  {
                    return bool1;
                    bool1 = bool2;
                  } while (!(paramObject2 instanceof ScriptableObject));
                  paramObject1 = ((ScriptableObject)paramObject2).equivalentValues(paramObject1);
                  bool1 = bool2;
                } while (paramObject1 == Scriptable.NOT_FOUND);
                return ((Boolean)paramObject1).booleanValue();
                if ((paramObject1 instanceof Number)) {
                  return eqNumber(((Number)paramObject1).doubleValue(), paramObject2);
                }
                if (paramObject1 == paramObject2) {
                  return true;
                }
                if ((paramObject1 instanceof CharSequence)) {
                  return eqString((CharSequence)paramObject1, paramObject2);
                }
                if ((paramObject1 instanceof Boolean))
                {
                  bool1 = ((Boolean)paramObject1).booleanValue();
                  if ((paramObject2 instanceof Boolean))
                  {
                    if (bool1 == ((Boolean)paramObject2).booleanValue()) {}
                    for (bool1 = true;; bool1 = false) {
                      return bool1;
                    }
                  }
                  if ((paramObject2 instanceof ScriptableObject))
                  {
                    paramObject1 = ((ScriptableObject)paramObject2).equivalentValues(paramObject1);
                    if (paramObject1 != Scriptable.NOT_FOUND) {
                      return ((Boolean)paramObject1).booleanValue();
                    }
                  }
                  if (bool1) {}
                  for (d = 1.0D;; d = 0.0D) {
                    return eqNumber(d, paramObject2);
                  }
                }
                if (!(paramObject1 instanceof Scriptable)) {
                  break label479;
                }
                if (!(paramObject2 instanceof Scriptable)) {
                  break;
                }
                if ((paramObject1 instanceof ScriptableObject))
                {
                  localObject = ((ScriptableObject)paramObject1).equivalentValues(paramObject2);
                  if (localObject != Scriptable.NOT_FOUND) {
                    return ((Boolean)localObject).booleanValue();
                  }
                }
                if ((paramObject2 instanceof ScriptableObject))
                {
                  localObject = ((ScriptableObject)paramObject2).equivalentValues(paramObject1);
                  if (localObject != Scriptable.NOT_FOUND) {
                    return ((Boolean)localObject).booleanValue();
                  }
                }
                bool1 = bool2;
              } while (!(paramObject1 instanceof Wrapper));
              bool1 = bool2;
            } while (!(paramObject2 instanceof Wrapper));
            paramObject1 = ((Wrapper)paramObject1).unwrap();
            paramObject2 = ((Wrapper)paramObject2).unwrap();
            if (paramObject1 == paramObject2) {
              break;
            }
            bool1 = bool2;
          } while (!isPrimitive(paramObject1));
          bool1 = bool2;
        } while (!isPrimitive(paramObject2));
        bool1 = bool2;
      } while (!eq(paramObject1, paramObject2));
      return true;
      if ((paramObject2 instanceof Boolean))
      {
        if ((paramObject1 instanceof ScriptableObject))
        {
          localObject = ((ScriptableObject)paramObject1).equivalentValues(paramObject2);
          if (localObject != Scriptable.NOT_FOUND) {
            return ((Boolean)localObject).booleanValue();
          }
        }
        if (((Boolean)paramObject2).booleanValue()) {}
        for (;;)
        {
          return eqNumber(d, paramObject1);
          d = 0.0D;
        }
      }
      if ((paramObject2 instanceof Number)) {
        return eqNumber(((Number)paramObject2).doubleValue(), paramObject1);
      }
      bool1 = bool2;
    } while (!(paramObject2 instanceof CharSequence));
    return eqString((CharSequence)paramObject2, paramObject1);
    label479:
    warnAboutNonJSObject(paramObject1);
    if (paramObject1 == paramObject2) {}
    for (;;)
    {
      return bool1;
      bool1 = false;
    }
  }
  
  static boolean eqNumber(double paramDouble, Object paramObject)
  {
    boolean bool = true;
    for (;;)
    {
      if ((paramObject == null) || (paramObject == Undefined.instance)) {
        bool = false;
      }
      for (;;)
      {
        return bool;
        if ((paramObject instanceof Number))
        {
          if (paramDouble == ((Number)paramObject).doubleValue()) {}
          for (bool = true;; bool = false) {
            return bool;
          }
        }
        if ((paramObject instanceof CharSequence))
        {
          if (paramDouble != toNumber(paramObject)) {
            return false;
          }
        }
        else
        {
          if (!(paramObject instanceof Boolean)) {
            break;
          }
          if (((Boolean)paramObject).booleanValue()) {}
          for (double d = 1.0D; paramDouble != d; d = 0.0D) {
            return false;
          }
        }
      }
      if (!(paramObject instanceof Scriptable)) {
        break;
      }
      if ((paramObject instanceof ScriptableObject))
      {
        Object localObject = wrapNumber(paramDouble);
        localObject = ((ScriptableObject)paramObject).equivalentValues(localObject);
        if (localObject != Scriptable.NOT_FOUND) {
          return ((Boolean)localObject).booleanValue();
        }
      }
      paramObject = toPrimitive(paramObject);
    }
    warnAboutNonJSObject(paramObject);
    return false;
  }
  
  private static boolean eqString(CharSequence paramCharSequence, Object paramObject)
  {
    boolean bool = true;
    for (;;)
    {
      if ((paramObject == null) || (paramObject == Undefined.instance)) {
        bool = false;
      }
      for (;;)
      {
        return bool;
        if ((paramObject instanceof CharSequence))
        {
          paramObject = (CharSequence)paramObject;
          if ((paramCharSequence.length() == ((CharSequence)paramObject).length()) && (paramCharSequence.toString().equals(((CharSequence)paramObject).toString()))) {}
          for (bool = true;; bool = false) {
            return bool;
          }
        }
        if ((paramObject instanceof Number))
        {
          if (toNumber(paramCharSequence.toString()) != ((Number)paramObject).doubleValue()) {
            return false;
          }
        }
        else
        {
          if (!(paramObject instanceof Boolean)) {
            break;
          }
          double d2 = toNumber(paramCharSequence.toString());
          if (((Boolean)paramObject).booleanValue()) {}
          for (double d1 = 1.0D; d2 != d1; d1 = 0.0D) {
            return false;
          }
        }
      }
      if (!(paramObject instanceof Scriptable)) {
        break;
      }
      if ((paramObject instanceof ScriptableObject))
      {
        Object localObject = ((ScriptableObject)paramObject).equivalentValues(paramCharSequence.toString());
        if (localObject != Scriptable.NOT_FOUND) {
          return ((Boolean)localObject).booleanValue();
        }
      }
      paramObject = toPrimitive(paramObject);
    }
    warnAboutNonJSObject(paramObject);
    return false;
  }
  
  private static RuntimeException errorWithClassName(String paramString, Object paramObject)
  {
    return Context.reportRuntimeError1(paramString, paramObject.getClass().getName());
  }
  
  public static String escapeAttributeValue(Object paramObject, Context paramContext)
  {
    return currentXMLLib(paramContext).escapeAttributeValue(paramObject);
  }
  
  public static String escapeString(String paramString)
  {
    return escapeString(paramString, '"');
  }
  
  public static String escapeString(String paramString, char paramChar)
  {
    if ((paramChar != '"') && (paramChar != '\'')) {
      Kit.codeBug();
    }
    int k = paramString.length();
    int i = 0;
    Object localObject2 = null;
    char c2;
    Object localObject1;
    if (i != k)
    {
      c2 = paramString.charAt(i);
      if ((' ' <= c2) && (c2 <= '~') && (c2 != paramChar) && (c2 != '\\'))
      {
        localObject1 = localObject2;
        if (localObject2 != null)
        {
          ((StringBuilder)localObject2).append((char)c2);
          localObject1 = localObject2;
        }
      }
    }
    label279:
    label328:
    label385:
    label415:
    for (;;)
    {
      i += 1;
      localObject2 = localObject1;
      break;
      if (localObject2 == null)
      {
        localObject1 = new StringBuilder(k + 3);
        ((StringBuilder)localObject1).append(paramString);
        ((StringBuilder)localObject1).setLength(i);
      }
      for (;;)
      {
        char c1 = '?';
        switch (c2)
        {
        }
        for (;;)
        {
          if (c1 < 0) {
            break label279;
          }
          ((StringBuilder)localObject1).append('\\');
          ((StringBuilder)localObject1).append((char)c1);
          break;
          c1 = 'b';
          continue;
          c1 = 'f';
          continue;
          c1 = 'n';
          continue;
          c1 = 'r';
          continue;
          c1 = 't';
          continue;
          c1 = 'v';
          continue;
          c1 = ' ';
          continue;
          c1 = '\\';
        }
        if (c2 == paramChar)
        {
          ((StringBuilder)localObject1).append('\\');
          ((StringBuilder)localObject1).append(paramChar);
          break;
        }
        int j;
        if (c2 < '?')
        {
          ((StringBuilder)localObject1).append("\\x");
          c1 = '\002';
          c1 = (c1 - '\001') * 4;
          if (c1 < 0) {
            break label415;
          }
          j = c2 >> c1 & 0xF;
          if (j >= 10) {
            break label385;
          }
          j += 48;
        }
        for (;;)
        {
          ((StringBuilder)localObject1).append((char)j);
          c1 -= '\004';
          break label328;
          ((StringBuilder)localObject1).append("\\u");
          c1 = '\004';
          break;
          j += 87;
        }
        if (localObject2 == null) {
          return paramString;
        }
        return ((StringBuilder)localObject2).toString();
        localObject1 = localObject2;
      }
    }
  }
  
  public static String escapeTextValue(Object paramObject, Context paramContext)
  {
    return currentXMLLib(paramContext).escapeTextValue(paramObject);
  }
  
  public static Object evalSpecial(Context paramContext, Scriptable paramScriptable, Object paramObject, Object[] paramArrayOfObject, String paramString, int paramInt)
  {
    if (paramArrayOfObject.length < 1) {
      return Undefined.instance;
    }
    Object localObject = paramArrayOfObject[0];
    if (!(localObject instanceof CharSequence))
    {
      if ((paramContext.hasFeature(11)) || (paramContext.hasFeature(9))) {
        throw Context.reportRuntimeError0("msg.eval.nonstring.strict");
      }
      Context.reportWarning(getMessage0("msg.eval.nonstring"));
      return localObject;
    }
    paramArrayOfObject = paramString;
    int i = paramInt;
    if (paramString == null)
    {
      paramString = new int[1];
      paramArrayOfObject = Context.getSourcePositionFromStack(paramString);
      if (paramArrayOfObject == null) {
        break label135;
      }
    }
    String str;
    ErrorReporter localErrorReporter;
    for (i = paramString[0];; i = paramInt)
    {
      str = makeUrlForGeneratedScript(true, paramArrayOfObject, i);
      localErrorReporter = DefaultErrorReporter.forEval(paramContext.getErrorReporter());
      paramString = Context.createInterpreter();
      if (paramString != null) {
        break;
      }
      throw new JavaScriptException("Interpreter not present", paramArrayOfObject, i);
      label135:
      paramArrayOfObject = "";
    }
    paramArrayOfObject = paramContext.compileString(localObject.toString(), paramString, localErrorReporter, str, 1, null);
    paramString.setEvalScriptFlag(paramArrayOfObject);
    return ((Callable)paramArrayOfObject).call(paramContext, paramScriptable, (Scriptable)paramObject, emptyArgs);
  }
  
  public static void exitActivationFunction(Context paramContext)
  {
    NativeCall localNativeCall = paramContext.currentActivationCall;
    paramContext.currentActivationCall = localNativeCall.parentActivationCall;
    localNativeCall.parentActivationCall = null;
  }
  
  static NativeCall findFunctionActivation(Context paramContext, Function paramFunction)
  {
    for (paramContext = paramContext.currentActivationCall; paramContext != null; paramContext = paramContext.parentActivationCall) {
      if (paramContext.function == paramFunction) {
        return paramContext;
      }
    }
    return null;
  }
  
  static Object[] getApplyArguments(Context paramContext, Object paramObject)
  {
    if ((paramObject == null) || (paramObject == Undefined.instance)) {
      return emptyArgs;
    }
    if (((paramObject instanceof NativeArray)) || ((paramObject instanceof Arguments))) {
      return paramContext.getElements((Scriptable)paramObject);
    }
    throw typeError0("msg.arg.isnt.array");
  }
  
  public static Object[] getArrayElements(Scriptable paramScriptable)
  {
    long l = NativeArray.getLengthProperty(Context.getContext(), paramScriptable);
    if (l > 2147483647L) {
      throw new IllegalArgumentException();
    }
    int j = (int)l;
    if (j == 0) {
      return emptyArgs;
    }
    Object[] arrayOfObject = new Object[j];
    int i = 0;
    while (i < j)
    {
      Object localObject2 = ScriptableObject.getProperty(paramScriptable, i);
      Object localObject1 = localObject2;
      if (localObject2 == Scriptable.NOT_FOUND) {
        localObject1 = Undefined.instance;
      }
      arrayOfObject[i] = localObject1;
      i += 1;
    }
    return arrayOfObject;
  }
  
  static Callable getCallable(Scriptable paramScriptable)
  {
    if ((paramScriptable instanceof Callable)) {
      return (Callable)paramScriptable;
    }
    Object localObject = paramScriptable.getDefaultValue(FunctionClass);
    if (!(localObject instanceof Callable)) {
      throw notFunctionError(localObject, paramScriptable);
    }
    return (Callable)localObject;
  }
  
  @Deprecated
  public static Callable getElemFunctionAndThis(Object paramObject1, Object paramObject2, Context paramContext)
  {
    return getElemFunctionAndThis(paramObject1, paramObject2, paramContext, getTopCallScope(paramContext));
  }
  
  public static Callable getElemFunctionAndThis(Object paramObject1, Object paramObject2, Context paramContext, Scriptable paramScriptable)
  {
    String str = toStringIdOrIndex(paramContext, paramObject2);
    if (str != null) {
      return getPropFunctionAndThis(paramObject1, str, paramContext, paramScriptable);
    }
    int i = lastIndexResult(paramContext);
    paramScriptable = toObjectOrNull(paramContext, paramObject1, paramScriptable);
    if (paramScriptable == null) {
      throw undefCallError(paramObject1, String.valueOf(i));
    }
    paramObject1 = ScriptableObject.getProperty(paramScriptable, i);
    if (!(paramObject1 instanceof Callable)) {
      throw notFunctionError(paramObject1, paramObject2);
    }
    storeScriptable(paramContext, paramScriptable);
    return (Callable)paramObject1;
  }
  
  static Function getExistingCtor(Context paramContext, Scriptable paramScriptable, String paramString)
  {
    paramContext = ScriptableObject.getProperty(paramScriptable, paramString);
    if ((paramContext instanceof Function)) {
      return (Function)paramContext;
    }
    if (paramContext == Scriptable.NOT_FOUND) {
      throw Context.reportRuntimeError1("msg.ctor.not.found", paramString);
    }
    throw Context.reportRuntimeError1("msg.not.ctor", paramString);
  }
  
  public static ScriptableObject getGlobal(Context paramContext)
  {
    Object localObject = Kit.classOrNull("org.mozilla.javascript.tools.shell.Global");
    if (localObject != null) {
      try
      {
        localObject = (ScriptableObject)((Class)localObject).getConstructor(new Class[] { ContextClass }).newInstance(new Object[] { paramContext });
        return (ScriptableObject)localObject;
      }
      catch (RuntimeException paramContext)
      {
        throw paramContext;
      }
      catch (Exception localException) {}
    }
    return new ImporterTopLevel(paramContext);
  }
  
  static Object getIndexObject(double paramDouble)
  {
    int i = (int)paramDouble;
    if (i == paramDouble) {
      return Integer.valueOf(i);
    }
    return toString(paramDouble);
  }
  
  static Object getIndexObject(String paramString)
  {
    long l = indexFromString(paramString);
    if (l >= 0L) {
      paramString = Integer.valueOf((int)l);
    }
    return paramString;
  }
  
  public static ScriptableObject getLibraryScopeOrNull(Scriptable paramScriptable)
  {
    return (ScriptableObject)ScriptableObject.getTopScopeValue(paramScriptable, LIBRARY_SCOPE_KEY);
  }
  
  public static String getMessage(String paramString, Object[] paramArrayOfObject)
  {
    return messageProvider.getMessage(paramString, paramArrayOfObject);
  }
  
  public static String getMessage0(String paramString)
  {
    return getMessage(paramString, null);
  }
  
  public static String getMessage1(String paramString, Object paramObject)
  {
    return getMessage(paramString, new Object[] { paramObject });
  }
  
  public static String getMessage2(String paramString, Object paramObject1, Object paramObject2)
  {
    return getMessage(paramString, new Object[] { paramObject1, paramObject2 });
  }
  
  public static String getMessage3(String paramString, Object paramObject1, Object paramObject2, Object paramObject3)
  {
    return getMessage(paramString, new Object[] { paramObject1, paramObject2, paramObject3 });
  }
  
  public static String getMessage4(String paramString, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4)
  {
    return getMessage(paramString, new Object[] { paramObject1, paramObject2, paramObject3, paramObject4 });
  }
  
  public static Callable getNameFunctionAndThis(String paramString, Context paramContext, Scriptable paramScriptable)
  {
    Object localObject = paramScriptable.getParentScope();
    if (localObject == null)
    {
      localObject = topScopeName(paramContext, paramScriptable, paramString);
      if (!(localObject instanceof Callable))
      {
        if (localObject == Scriptable.NOT_FOUND) {
          throw notFoundError(paramScriptable, paramString);
        }
        throw notFunctionError(localObject, paramString);
      }
      storeScriptable(paramContext, paramScriptable);
      return (Callable)localObject;
    }
    return (Callable)nameOrFunction(paramContext, paramScriptable, (Scriptable)localObject, paramString, true);
  }
  
  @Deprecated
  public static Object getObjectElem(Object paramObject1, Object paramObject2, Context paramContext)
  {
    return getObjectElem(paramObject1, paramObject2, paramContext, getTopCallScope(paramContext));
  }
  
  public static Object getObjectElem(Object paramObject1, Object paramObject2, Context paramContext, Scriptable paramScriptable)
  {
    paramScriptable = toObjectOrNull(paramContext, paramObject1, paramScriptable);
    if (paramScriptable == null) {
      throw undefReadError(paramObject1, paramObject2);
    }
    return getObjectElem(paramScriptable, paramObject2, paramContext);
  }
  
  public static Object getObjectElem(Scriptable paramScriptable, Object paramObject, Context paramContext)
  {
    if ((paramScriptable instanceof XMLObject)) {
      paramScriptable = ((XMLObject)paramScriptable).get(paramContext, paramObject);
    }
    for (;;)
    {
      paramObject = paramScriptable;
      if (paramScriptable == Scriptable.NOT_FOUND) {
        paramObject = Undefined.instance;
      }
      return paramObject;
      paramObject = toStringIdOrIndex(paramContext, paramObject);
      if (paramObject == null) {
        paramScriptable = ScriptableObject.getProperty(paramScriptable, lastIndexResult(paramContext));
      } else {
        paramScriptable = ScriptableObject.getProperty(paramScriptable, (String)paramObject);
      }
    }
  }
  
  @Deprecated
  public static Object getObjectIndex(Object paramObject, double paramDouble, Context paramContext)
  {
    return getObjectIndex(paramObject, paramDouble, paramContext, getTopCallScope(paramContext));
  }
  
  public static Object getObjectIndex(Object paramObject, double paramDouble, Context paramContext, Scriptable paramScriptable)
  {
    paramScriptable = toObjectOrNull(paramContext, paramObject, paramScriptable);
    if (paramScriptable == null) {
      throw undefReadError(paramObject, toString(paramDouble));
    }
    int i = (int)paramDouble;
    if (i == paramDouble) {
      return getObjectIndex(paramScriptable, i, paramContext);
    }
    return getObjectProp(paramScriptable, toString(paramDouble), paramContext);
  }
  
  public static Object getObjectIndex(Scriptable paramScriptable, int paramInt, Context paramContext)
  {
    paramContext = ScriptableObject.getProperty(paramScriptable, paramInt);
    paramScriptable = paramContext;
    if (paramContext == Scriptable.NOT_FOUND) {
      paramScriptable = Undefined.instance;
    }
    return paramScriptable;
  }
  
  @Deprecated
  public static Object getObjectProp(Object paramObject, String paramString, Context paramContext)
  {
    return getObjectProp(paramObject, paramString, paramContext, getTopCallScope(paramContext));
  }
  
  public static Object getObjectProp(Object paramObject, String paramString, Context paramContext, Scriptable paramScriptable)
  {
    paramScriptable = toObjectOrNull(paramContext, paramObject, paramScriptable);
    if (paramScriptable == null) {
      throw undefReadError(paramObject, paramString);
    }
    return getObjectProp(paramScriptable, paramString, paramContext);
  }
  
  public static Object getObjectProp(Scriptable paramScriptable, String paramString, Context paramContext)
  {
    Object localObject = ScriptableObject.getProperty(paramScriptable, paramString);
    paramScriptable = (Scriptable)localObject;
    if (localObject == Scriptable.NOT_FOUND)
    {
      if (paramContext.hasFeature(11)) {
        Context.reportWarning(getMessage1("msg.ref.undefined.prop", paramString));
      }
      paramScriptable = Undefined.instance;
    }
    return paramScriptable;
  }
  
  @Deprecated
  public static Object getObjectPropNoWarn(Object paramObject, String paramString, Context paramContext)
  {
    return getObjectPropNoWarn(paramObject, paramString, paramContext, getTopCallScope(paramContext));
  }
  
  public static Object getObjectPropNoWarn(Object paramObject, String paramString, Context paramContext, Scriptable paramScriptable)
  {
    paramContext = toObjectOrNull(paramContext, paramObject, paramScriptable);
    if (paramContext == null) {
      throw undefReadError(paramObject, paramString);
    }
    paramString = ScriptableObject.getProperty(paramContext, paramString);
    paramObject = paramString;
    if (paramString == Scriptable.NOT_FOUND) {
      paramObject = Undefined.instance;
    }
    return paramObject;
  }
  
  @Deprecated
  public static Callable getPropFunctionAndThis(Object paramObject, String paramString, Context paramContext)
  {
    return getPropFunctionAndThis(paramObject, paramString, paramContext, getTopCallScope(paramContext));
  }
  
  public static Callable getPropFunctionAndThis(Object paramObject, String paramString, Context paramContext, Scriptable paramScriptable)
  {
    return getPropFunctionAndThisHelper(paramObject, paramString, paramContext, toObjectOrNull(paramContext, paramObject, paramScriptable));
  }
  
  private static Callable getPropFunctionAndThisHelper(Object paramObject, String paramString, Context paramContext, Scriptable paramScriptable)
  {
    if (paramScriptable == null) {
      throw undefCallError(paramObject, paramString);
    }
    paramObject = ScriptableObject.getProperty(paramScriptable, paramString);
    if (!(paramObject instanceof Callable))
    {
      Object localObject = ScriptableObject.getProperty(paramScriptable, "__noSuchMethod__");
      if ((localObject instanceof Callable)) {
        paramObject = new NoSuchMethodShim((Callable)localObject, paramString);
      }
    }
    for (;;)
    {
      if (!(paramObject instanceof Callable)) {
        throw notFunctionError(paramScriptable, paramObject, paramString);
      }
      storeScriptable(paramContext, paramScriptable);
      return (Callable)paramObject;
    }
  }
  
  public static RegExpProxy getRegExpProxy(Context paramContext)
  {
    return paramContext.getRegExpProxy();
  }
  
  public static Scriptable getTopCallScope(Context paramContext)
  {
    paramContext = paramContext.topCallScope;
    if (paramContext == null) {
      throw new IllegalStateException();
    }
    return paramContext;
  }
  
  public static Object getTopLevelProp(Scriptable paramScriptable, String paramString)
  {
    return ScriptableObject.getProperty(ScriptableObject.getTopLevelScope(paramScriptable), paramString);
  }
  
  static String[] getTopPackageNames()
  {
    if ("Dalvik".equals(System.getProperty("java.vm.name"))) {
      return new String[] { "java", "javax", "org", "com", "edu", "net", "android" };
    }
    return new String[] { "java", "javax", "org", "com", "edu", "net" };
  }
  
  public static Callable getValueFunctionAndThis(Object paramObject, Context paramContext)
  {
    if (!(paramObject instanceof Callable)) {
      throw notFunctionError(paramObject);
    }
    Callable localCallable = (Callable)paramObject;
    Object localObject = null;
    if ((localCallable instanceof Scriptable)) {
      localObject = ((Scriptable)localCallable).getParentScope();
    }
    paramObject = localObject;
    if (localObject == null)
    {
      if (paramContext.topCallScope == null) {
        throw new IllegalStateException();
      }
      paramObject = paramContext.topCallScope;
    }
    localObject = paramObject;
    if (((Scriptable)paramObject).getParentScope() != null)
    {
      if (!(paramObject instanceof NativeWith)) {
        break label89;
      }
      localObject = paramObject;
    }
    for (;;)
    {
      storeScriptable(paramContext, (Scriptable)localObject);
      return localCallable;
      label89:
      localObject = paramObject;
      if ((paramObject instanceof NativeCall)) {
        localObject = ScriptableObject.getTopLevelScope((Scriptable)paramObject);
      }
    }
  }
  
  public static boolean hasObjectElem(Scriptable paramScriptable, Object paramObject, Context paramContext)
  {
    paramObject = toStringIdOrIndex(paramContext, paramObject);
    if (paramObject == null) {
      return ScriptableObject.hasProperty(paramScriptable, lastIndexResult(paramContext));
    }
    return ScriptableObject.hasProperty(paramScriptable, (String)paramObject);
  }
  
  public static boolean hasTopCall(Context paramContext)
  {
    return paramContext.topCallScope != null;
  }
  
  public static boolean in(Object paramObject1, Object paramObject2, Context paramContext)
  {
    if (!(paramObject2 instanceof Scriptable)) {
      throw typeError0("msg.in.not.object");
    }
    return hasObjectElem((Scriptable)paramObject2, paramObject1, paramContext);
  }
  
  public static long indexFromString(String paramString)
  {
    int i2 = 0;
    int i4 = 0;
    int i5 = paramString.length();
    int j;
    int i1;
    if (i5 > 0)
    {
      j = paramString.charAt(0);
      if ((j != 45) || (i5 <= 1)) {
        break label278;
      }
      j = paramString.charAt(1);
      if (j == 48) {
        return -1L;
      }
      i1 = 1;
    }
    for (int i = 1;; i = 0)
    {
      int m = j - 48;
      if ((m >= 0) && (m <= 9))
      {
        if (i1 != 0) {
          j = 11;
        }
        while (i5 <= j)
        {
          int n = -m;
          j = i + 1;
          int k = n;
          i = m;
          int i3 = j;
          if (n != 0)
          {
            i = m;
            m = i4;
            for (;;)
            {
              i2 = m;
              k = n;
              i3 = j;
              if (j == i5) {
                break;
              }
              i4 = paramString.charAt(j) - '0';
              i2 = m;
              k = n;
              i = i4;
              i3 = j;
              if (i4 < 0) {
                break;
              }
              i2 = m;
              k = n;
              i = i4;
              i3 = j;
              if (i4 > 9) {
                break;
              }
              j += 1;
              i = n * 10 - i4;
              m = n;
              n = i;
              i = i4;
            }
            j = 10;
          }
          else if (i3 == i5)
          {
            if (i2 <= -214748364)
            {
              if (i2 != -214748364) {
                break;
              }
              if (i1 != 0)
              {
                j = 8;
                if (i > j) {
                  break;
                }
              }
            }
            else
            {
              if (i1 == 0) {
                break label268;
              }
            }
            for (;;)
            {
              return k & 0xFFFFFFFF;
              j = 7;
              break;
              label268:
              k = -k;
            }
          }
        }
      }
      return -1L;
      label278:
      i1 = 0;
    }
  }
  
  public static void initFunction(Context paramContext, Scriptable paramScriptable, NativeFunction paramNativeFunction, int paramInt, boolean paramBoolean)
  {
    if (paramInt == 1)
    {
      paramContext = paramNativeFunction.getFunctionName();
      if ((paramContext != null) && (paramContext.length() != 0))
      {
        if (paramBoolean) {
          break label34;
        }
        ScriptableObject.defineProperty(paramScriptable, paramContext, paramNativeFunction, 4);
      }
    }
    label34:
    do
    {
      return;
      paramScriptable.put(paramContext, paramScriptable, paramNativeFunction);
      return;
      if (paramInt != 3) {
        break;
      }
      paramContext = paramNativeFunction.getFunctionName();
    } while ((paramContext == null) || (paramContext.length() == 0));
    while ((paramScriptable instanceof NativeWith)) {
      paramScriptable = paramScriptable.getParentScope();
    }
    paramScriptable.put(paramContext, paramScriptable, paramNativeFunction);
    return;
    throw Kit.codeBug();
  }
  
  public static ScriptableObject initSafeStandardObjects(Context paramContext, ScriptableObject paramScriptableObject, boolean paramBoolean)
  {
    if (paramScriptableObject == null) {
      paramScriptableObject = new NativeObject();
    }
    for (;;)
    {
      paramScriptableObject.associateValue(LIBRARY_SCOPE_KEY, paramScriptableObject);
      new ClassCache().associate(paramScriptableObject);
      BaseFunction.init(paramScriptableObject, paramBoolean);
      NativeObject.init(paramScriptableObject, paramBoolean);
      Object localObject = ScriptableObject.getObjectPrototype(paramScriptableObject);
      ScriptableObject.getClassPrototype(paramScriptableObject, "Function").setPrototype((Scriptable)localObject);
      if (paramScriptableObject.getPrototype() == null) {
        paramScriptableObject.setPrototype((Scriptable)localObject);
      }
      NativeError.init(paramScriptableObject, paramBoolean);
      NativeGlobal.init(paramContext, paramScriptableObject, paramBoolean);
      NativeArray.init(paramScriptableObject, paramBoolean);
      if (paramContext.getOptimizationLevel() > 0) {
        NativeArray.setMaximumInitialCapacity(200000);
      }
      NativeString.init(paramScriptableObject, paramBoolean);
      NativeBoolean.init(paramScriptableObject, paramBoolean);
      NativeNumber.init(paramScriptableObject, paramBoolean);
      NativeDate.init(paramScriptableObject, paramBoolean);
      NativeMath.init(paramScriptableObject, paramBoolean);
      NativeJSON.init(paramScriptableObject, paramBoolean);
      NativeWith.init(paramScriptableObject, paramBoolean);
      NativeCall.init(paramScriptableObject, paramBoolean);
      NativeScript.init(paramScriptableObject, paramBoolean);
      NativeIterator.init(paramScriptableObject, paramBoolean);
      NativeArrayIterator.init(paramScriptableObject, paramBoolean);
      NativeStringIterator.init(paramScriptableObject, paramBoolean);
      if ((paramContext.hasFeature(6)) && (paramContext.getE4xImplementationFactory() != null)) {}
      for (int i = 1;; i = 0)
      {
        new LazilyLoadedCtor(paramScriptableObject, "RegExp", "org.mozilla.javascript.regexp.NativeRegExp", paramBoolean, true);
        new LazilyLoadedCtor(paramScriptableObject, "Continuation", "org.mozilla.javascript.NativeContinuation", paramBoolean, true);
        if (i != 0)
        {
          localObject = paramContext.getE4xImplementationFactory().getImplementationClassName();
          new LazilyLoadedCtor(paramScriptableObject, "XML", (String)localObject, paramBoolean, true);
          new LazilyLoadedCtor(paramScriptableObject, "XMLList", (String)localObject, paramBoolean, true);
          new LazilyLoadedCtor(paramScriptableObject, "Namespace", (String)localObject, paramBoolean, true);
          new LazilyLoadedCtor(paramScriptableObject, "QName", (String)localObject, paramBoolean, true);
        }
        if (((paramContext.getLanguageVersion() >= 180) && (paramContext.hasFeature(14))) || (paramContext.getLanguageVersion() >= 200))
        {
          new LazilyLoadedCtor(paramScriptableObject, "ArrayBuffer", "org.mozilla.javascript.typedarrays.NativeArrayBuffer", paramBoolean, true);
          new LazilyLoadedCtor(paramScriptableObject, "Int8Array", "org.mozilla.javascript.typedarrays.NativeInt8Array", paramBoolean, true);
          new LazilyLoadedCtor(paramScriptableObject, "Uint8Array", "org.mozilla.javascript.typedarrays.NativeUint8Array", paramBoolean, true);
          new LazilyLoadedCtor(paramScriptableObject, "Uint8ClampedArray", "org.mozilla.javascript.typedarrays.NativeUint8ClampedArray", paramBoolean, true);
          new LazilyLoadedCtor(paramScriptableObject, "Int16Array", "org.mozilla.javascript.typedarrays.NativeInt16Array", paramBoolean, true);
          new LazilyLoadedCtor(paramScriptableObject, "Uint16Array", "org.mozilla.javascript.typedarrays.NativeUint16Array", paramBoolean, true);
          new LazilyLoadedCtor(paramScriptableObject, "Int32Array", "org.mozilla.javascript.typedarrays.NativeInt32Array", paramBoolean, true);
          new LazilyLoadedCtor(paramScriptableObject, "Uint32Array", "org.mozilla.javascript.typedarrays.NativeUint32Array", paramBoolean, true);
          new LazilyLoadedCtor(paramScriptableObject, "Float32Array", "org.mozilla.javascript.typedarrays.NativeFloat32Array", paramBoolean, true);
          new LazilyLoadedCtor(paramScriptableObject, "Float64Array", "org.mozilla.javascript.typedarrays.NativeFloat64Array", paramBoolean, true);
          new LazilyLoadedCtor(paramScriptableObject, "DataView", "org.mozilla.javascript.typedarrays.NativeDataView", paramBoolean, true);
        }
        if (paramContext.getLanguageVersion() >= 200) {
          new LazilyLoadedCtor(paramScriptableObject, "Symbol", NativeSymbol.class.getName(), paramBoolean, true);
        }
        if ((paramScriptableObject instanceof TopLevel)) {
          ((TopLevel)paramScriptableObject).cacheBuiltins();
        }
        return paramScriptableObject;
      }
    }
  }
  
  public static void initScript(NativeFunction paramNativeFunction, Scriptable paramScriptable1, Context paramContext, Scriptable paramScriptable2, boolean paramBoolean)
  {
    if (paramContext.topCallScope == null) {
      throw new IllegalStateException();
    }
    int j = paramNativeFunction.getParamAndVarCount();
    if (j != 0)
    {
      int i;
      for (paramScriptable1 = paramScriptable2;; paramScriptable1 = paramScriptable1.getParentScope())
      {
        i = j;
        if (!(paramScriptable1 instanceof NativeWith)) {
          break;
        }
      }
      j = i - 1;
      if (i != 0)
      {
        paramContext = paramNativeFunction.getParamOrVarName(j);
        boolean bool = paramNativeFunction.getParamOrVarConst(j);
        if (!ScriptableObject.hasProperty(paramScriptable2, paramContext)) {
          if (bool) {
            ScriptableObject.defineConstProperty(paramScriptable1, paramContext);
          }
        }
        for (;;)
        {
          i = j;
          break;
          if (!paramBoolean)
          {
            ScriptableObject.defineProperty(paramScriptable1, paramContext, Undefined.instance, 4);
          }
          else
          {
            paramScriptable1.put(paramContext, paramScriptable1, Undefined.instance);
            continue;
            ScriptableObject.redefineProperty(paramScriptable2, paramContext, bool);
          }
        }
      }
    }
  }
  
  public static ScriptableObject initStandardObjects(Context paramContext, ScriptableObject paramScriptableObject, boolean paramBoolean)
  {
    paramContext = initSafeStandardObjects(paramContext, paramScriptableObject, paramBoolean);
    new LazilyLoadedCtor(paramContext, "Packages", "org.mozilla.javascript.NativeJavaTopPackage", paramBoolean, true);
    new LazilyLoadedCtor(paramContext, "getClass", "org.mozilla.javascript.NativeJavaTopPackage", paramBoolean, true);
    new LazilyLoadedCtor(paramContext, "JavaAdapter", "org.mozilla.javascript.JavaAdapter", paramBoolean, true);
    new LazilyLoadedCtor(paramContext, "JavaImporter", "org.mozilla.javascript.ImporterTopLevel", paramBoolean, true);
    paramScriptableObject = getTopPackageNames();
    int j = paramScriptableObject.length;
    int i = 0;
    while (i < j)
    {
      new LazilyLoadedCtor(paramContext, paramScriptableObject[i], "org.mozilla.javascript.NativeJavaTopPackage", paramBoolean, true);
      i += 1;
    }
    return paramContext;
  }
  
  public static boolean instanceOf(Object paramObject1, Object paramObject2, Context paramContext)
  {
    if (!(paramObject2 instanceof Scriptable)) {
      throw typeError0("msg.instanceof.not.object");
    }
    if (!(paramObject1 instanceof Scriptable)) {
      return false;
    }
    return ((Scriptable)paramObject2).hasInstance((Scriptable)paramObject1);
  }
  
  public static boolean isArrayObject(Object paramObject)
  {
    return ((paramObject instanceof NativeArray)) || ((paramObject instanceof Arguments));
  }
  
  static boolean isGeneratedScript(String paramString)
  {
    return (paramString.indexOf("(eval)") >= 0) || (paramString.indexOf("(Function)") >= 0);
  }
  
  public static boolean isJSLineTerminator(int paramInt)
  {
    if ((0xDFD0 & paramInt) != 0) {}
    while ((paramInt != 10) && (paramInt != 13) && (paramInt != 8232) && (paramInt != 8233)) {
      return false;
    }
    return true;
  }
  
  public static boolean isJSWhitespaceOrLineTerminator(int paramInt)
  {
    return (isStrWhiteSpaceChar(paramInt)) || (isJSLineTerminator(paramInt));
  }
  
  public static boolean isPrimitive(Object paramObject)
  {
    return (paramObject == null) || (paramObject == Undefined.instance) || ((paramObject instanceof Number)) || ((paramObject instanceof String)) || ((paramObject instanceof Boolean));
  }
  
  public static boolean isRhinoRuntimeType(Class<?> paramClass)
  {
    boolean bool = false;
    if (paramClass.isPrimitive()) {
      return paramClass != Character.TYPE;
    }
    if ((paramClass == StringClass) || (paramClass == BooleanClass) || (NumberClass.isAssignableFrom(paramClass)) || (ScriptableClass.isAssignableFrom(paramClass))) {
      bool = true;
    }
    return bool;
  }
  
  static boolean isSpecialProperty(String paramString)
  {
    return (paramString.equals("__proto__")) || (paramString.equals("__parent__"));
  }
  
  static boolean isStrWhiteSpaceChar(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      if (Character.getType(paramInt) != 12) {
        break;
      }
    case 9: 
    case 10: 
    case 11: 
    case 12: 
    case 13: 
    case 32: 
    case 160: 
    case 8232: 
    case 8233: 
    case 65279: 
      return true;
    }
    return false;
  }
  
  static boolean isValidIdentifierName(String paramString, Context paramContext, boolean paramBoolean)
  {
    boolean bool = true;
    int j = paramString.length();
    if (j == 0) {}
    while (!Character.isJavaIdentifierStart(paramString.charAt(0))) {
      return false;
    }
    int i = 1;
    for (;;)
    {
      if (i == j) {
        break label53;
      }
      if (!Character.isJavaIdentifierPart(paramString.charAt(i))) {
        break;
      }
      i += 1;
    }
    label53:
    if (!TokenStream.isKeyword(paramString, paramContext.getLanguageVersion(), paramBoolean)) {}
    for (paramBoolean = bool;; paramBoolean = false) {
      return paramBoolean;
    }
  }
  
  private static boolean isVisible(Context paramContext, Object paramObject)
  {
    paramContext = paramContext.getClassShutter();
    return (paramContext == null) || (paramContext.visibleToScripts(paramObject.getClass().getName()));
  }
  
  public static boolean jsDelegatesTo(Scriptable paramScriptable1, Scriptable paramScriptable2)
  {
    for (paramScriptable1 = paramScriptable1.getPrototype(); paramScriptable1 != null; paramScriptable1 = paramScriptable1.getPrototype()) {
      if (paramScriptable1.equals(paramScriptable2)) {
        return true;
      }
    }
    return false;
  }
  
  static int lastIndexResult(Context paramContext)
  {
    return paramContext.scratchIndex;
  }
  
  public static Scriptable lastStoredScriptable(Context paramContext)
  {
    Scriptable localScriptable = paramContext.scratchScriptable;
    paramContext.scratchScriptable = null;
    return localScriptable;
  }
  
  public static long lastUint32Result(Context paramContext)
  {
    long l = paramContext.scratchUint32;
    if (l >>> 32 != 0L) {
      throw new IllegalStateException();
    }
    return l;
  }
  
  public static Scriptable leaveDotQuery(Scriptable paramScriptable)
  {
    return ((NativeWith)paramScriptable).getParentScope();
  }
  
  public static Scriptable leaveWith(Scriptable paramScriptable)
  {
    return ((NativeWith)paramScriptable).getParentScope();
  }
  
  static String makeUrlForGeneratedScript(boolean paramBoolean, String paramString, int paramInt)
  {
    if (paramBoolean) {
      return paramString + '#' + paramInt + "(eval)";
    }
    return paramString + '#' + paramInt + "(Function)";
  }
  
  public static Ref memberRef(Object paramObject1, Object paramObject2, Object paramObject3, Context paramContext, int paramInt)
  {
    if (!(paramObject1 instanceof XMLObject)) {
      throw notXmlError(paramObject1);
    }
    return ((XMLObject)paramObject1).memberRef(paramContext, paramObject2, paramObject3, paramInt);
  }
  
  public static Ref memberRef(Object paramObject1, Object paramObject2, Context paramContext, int paramInt)
  {
    if (!(paramObject1 instanceof XMLObject)) {
      throw notXmlError(paramObject1);
    }
    return ((XMLObject)paramObject1).memberRef(paramContext, paramObject2, paramInt);
  }
  
  public static Object name(Context paramContext, Scriptable paramScriptable, String paramString)
  {
    Object localObject = paramScriptable.getParentScope();
    if (localObject == null)
    {
      localObject = topScopeName(paramContext, paramScriptable, paramString);
      paramContext = (Context)localObject;
      if (localObject == Scriptable.NOT_FOUND) {
        throw notFoundError(paramScriptable, paramString);
      }
    }
    else
    {
      paramContext = nameOrFunction(paramContext, paramScriptable, (Scriptable)localObject, paramString, false);
    }
    return paramContext;
  }
  
  @Deprecated
  public static Object nameIncrDecr(Scriptable paramScriptable, String paramString, int paramInt)
  {
    return nameIncrDecr(paramScriptable, paramString, Context.getContext(), paramInt);
  }
  
  public static Object nameIncrDecr(Scriptable paramScriptable, String paramString, Context paramContext, int paramInt)
  {
    Object localObject1 = paramScriptable;
    paramScriptable = (Scriptable)localObject1;
    if (paramContext.useDynamicScope)
    {
      paramScriptable = (Scriptable)localObject1;
      if (((Scriptable)localObject1).getParentScope() == null) {
        paramScriptable = checkDynamicScope(paramContext.topCallScope, (Scriptable)localObject1);
      }
    }
    localObject1 = paramScriptable;
    label39:
    if (((localObject1 instanceof NativeWith)) && ((((Scriptable)localObject1).getPrototype() instanceof XMLObject))) {}
    for (;;)
    {
      paramScriptable = paramScriptable.getParentScope();
      localObject1 = paramScriptable;
      if (paramScriptable != null) {
        break;
      }
      throw notFoundError(paramScriptable, paramString);
      Object localObject2 = ((Scriptable)localObject1).get(paramString, paramScriptable);
      if (localObject2 != Scriptable.NOT_FOUND) {
        return doScriptableIncrDecr((Scriptable)localObject1, paramString, paramScriptable, localObject2, paramInt);
      }
      localObject2 = ((Scriptable)localObject1).getPrototype();
      localObject1 = localObject2;
      if (localObject2 != null) {
        break label39;
      }
    }
  }
  
  private static Object nameOrFunction(Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, String paramString, boolean paramBoolean)
  {
    Object localObject1 = null;
    Object localObject2 = paramScriptable1;
    for (;;)
    {
      if ((localObject2 instanceof NativeWith))
      {
        localObject2 = ((Scriptable)localObject2).getPrototype();
        if ((localObject2 instanceof XMLObject))
        {
          localObject2 = (XMLObject)localObject2;
          if (((XMLObject)localObject2).has(paramString, (Scriptable)localObject2))
          {
            paramScriptable2 = ((XMLObject)localObject2).get(paramString, (Scriptable)localObject2);
            paramScriptable1 = (Scriptable)localObject2;
            if (!paramBoolean) {
              break label255;
            }
            if ((paramScriptable2 instanceof Callable)) {
              break label250;
            }
            throw notFunctionError(paramScriptable2, paramString);
          }
          if (localObject1 != null) {
            break label263;
          }
          localObject1 = localObject2;
        }
      }
      Object localObject3;
      label250:
      label255:
      label257:
      label263:
      for (;;)
      {
        localObject3 = paramScriptable2.getParentScope();
        if (localObject3 != null) {
          break label266;
        }
        localObject2 = topScopeName(paramContext, paramScriptable2, paramString);
        paramScriptable1 = (Scriptable)localObject2;
        if (localObject2 == Scriptable.NOT_FOUND)
        {
          if ((localObject1 == null) || (paramBoolean))
          {
            throw notFoundError(paramScriptable2, paramString);
            localObject3 = ScriptableObject.getProperty((Scriptable)localObject2, paramString);
            if (localObject3 == Scriptable.NOT_FOUND) {
              break label263;
            }
            paramScriptable2 = (Scriptable)localObject3;
            paramScriptable1 = (Scriptable)localObject2;
            break;
            if ((localObject2 instanceof NativeCall))
            {
              localObject2 = ((Scriptable)localObject2).get(paramString, (Scriptable)localObject2);
              if (localObject2 == Scriptable.NOT_FOUND) {
                continue;
              }
              if (!paramBoolean) {
                break label257;
              }
              paramScriptable1 = ScriptableObject.getTopLevelScope(paramScriptable2);
              paramScriptable2 = (Scriptable)localObject2;
              break;
            }
            localObject3 = ScriptableObject.getProperty((Scriptable)localObject2, paramString);
            if (localObject3 == Scriptable.NOT_FOUND) {
              continue;
            }
            paramScriptable2 = (Scriptable)localObject3;
            paramScriptable1 = (Scriptable)localObject2;
            break;
          }
          paramScriptable1 = ((XMLObject)localObject1).get(paramString, (Scriptable)localObject1);
        }
        localObject1 = paramScriptable1;
        paramScriptable1 = paramScriptable2;
        paramScriptable2 = (Scriptable)localObject1;
        break;
        storeScriptable(paramContext, paramScriptable1);
        return paramScriptable2;
        paramScriptable2 = (Scriptable)localObject2;
        break;
      }
      label266:
      localObject2 = paramScriptable2;
      paramScriptable2 = (Scriptable)localObject3;
    }
  }
  
  public static Ref nameRef(Object paramObject1, Object paramObject2, Context paramContext, Scriptable paramScriptable, int paramInt)
  {
    return currentXMLLib(paramContext).nameRef(paramContext, paramObject1, paramObject2, paramScriptable, paramInt);
  }
  
  public static Ref nameRef(Object paramObject, Context paramContext, Scriptable paramScriptable, int paramInt)
  {
    return currentXMLLib(paramContext).nameRef(paramContext, paramObject, paramScriptable, paramInt);
  }
  
  public static Scriptable newArrayLiteral(Object[] paramArrayOfObject, int[] paramArrayOfInt, Context paramContext, Scriptable paramScriptable)
  {
    int n = 0;
    int m = 0;
    int j = paramArrayOfObject.length;
    if (paramArrayOfInt != null) {}
    for (int i = paramArrayOfInt.length;; i = 0)
    {
      int i1 = j + i;
      if ((i1 > 1) && (i * 2 < i1)) {
        if (i != 0) {}
      }
      for (;;)
      {
        return paramContext.newArray(paramScriptable, paramArrayOfObject);
        Object[] arrayOfObject = new Object[i1];
        j = 0;
        int k = 0;
        if (j != i1)
        {
          if ((k != i) && (paramArrayOfInt[k] == j))
          {
            arrayOfObject[j] = Scriptable.NOT_FOUND;
            k += 1;
          }
          for (;;)
          {
            j += 1;
            break;
            arrayOfObject[j] = paramArrayOfObject[m];
            m += 1;
          }
          paramContext = paramContext.newArray(paramScriptable, i1);
          j = 0;
          k = 0;
          m = n;
          if (j != i1)
          {
            if ((k != i) && (paramArrayOfInt[k] == j)) {
              k += 1;
            }
            for (;;)
            {
              j += 1;
              break;
              paramContext.put(j, paramContext, paramArrayOfObject[m]);
              m += 1;
            }
          }
          return paramContext;
        }
        paramArrayOfObject = arrayOfObject;
      }
    }
  }
  
  public static Scriptable newBuiltinObject(Context paramContext, Scriptable paramScriptable, TopLevel.Builtins paramBuiltins, Object[] paramArrayOfObject)
  {
    Scriptable localScriptable = ScriptableObject.getTopLevelScope(paramScriptable);
    paramBuiltins = TopLevel.getBuiltinCtor(paramContext, localScriptable, paramBuiltins);
    paramScriptable = paramArrayOfObject;
    if (paramArrayOfObject == null) {
      paramScriptable = emptyArgs;
    }
    return paramBuiltins.construct(paramContext, localScriptable, paramScriptable);
  }
  
  public static Scriptable newCatchScope(Throwable paramThrowable, Scriptable paramScriptable1, String paramString, Context paramContext, Scriptable paramScriptable2)
  {
    int i;
    if ((paramThrowable instanceof JavaScriptException))
    {
      paramScriptable1 = ((JavaScriptException)paramThrowable).getValue();
      i = 0;
    }
    for (;;)
    {
      Object localObject1 = new NativeObject();
      ((NativeObject)localObject1).defineProperty(paramString, paramScriptable1, 4);
      if (isVisible(paramContext, paramThrowable)) {
        ((NativeObject)localObject1).defineProperty("__exception__", Context.javaToJS(paramThrowable, paramScriptable2), 6);
      }
      if (i != 0) {
        ((NativeObject)localObject1).associateValue(paramThrowable, paramScriptable1);
      }
      return (Scriptable)localObject1;
      if (paramScriptable1 != null)
      {
        paramScriptable1 = ((NativeObject)paramScriptable1).getAssociatedValue(paramThrowable);
        if (paramScriptable1 == null)
        {
          Kit.codeBug();
          i = 1;
        }
      }
      else
      {
        Object localObject3;
        Object localObject2;
        label134:
        String str1;
        if ((paramThrowable instanceof EcmaError))
        {
          localObject3 = (EcmaError)paramThrowable;
          paramScriptable1 = TopLevel.NativeErrors.valueOf(((EcmaError)localObject3).getName());
          localObject1 = ((EcmaError)localObject3).getErrorMessage();
          localObject2 = null;
          String str2 = ((RhinoException)localObject3).sourceName();
          str1 = str2;
          if (str2 == null) {
            str1 = "";
          }
          i = ((RhinoException)localObject3).lineNumber();
          if (i <= 0) {
            break label422;
          }
        }
        label422:
        for (localObject1 = new Object[] { localObject1, str1, Integer.valueOf(i) };; localObject1 = new Object[] { localObject1, str1 })
        {
          paramScriptable1 = newNativeError(paramContext, paramScriptable2, paramScriptable1, (Object[])localObject1);
          if ((paramScriptable1 instanceof NativeError)) {
            ((NativeError)paramScriptable1).setStackProvider((RhinoException)localObject3);
          }
          if ((localObject2 != null) && (isVisible(paramContext, localObject2))) {
            ScriptableObject.defineProperty(paramScriptable1, "javaException", paramContext.getWrapFactory().wrap(paramContext, paramScriptable2, localObject2, null), 7);
          }
          if (isVisible(paramContext, localObject3)) {
            ScriptableObject.defineProperty(paramScriptable1, "rhinoException", paramContext.getWrapFactory().wrap(paramContext, paramScriptable2, localObject3, null), 7);
          }
          i = 1;
          break;
          if ((paramThrowable instanceof WrappedException))
          {
            localObject3 = (WrappedException)paramThrowable;
            localObject2 = ((WrappedException)localObject3).getWrappedException();
            paramScriptable1 = TopLevel.NativeErrors.JavaException;
            localObject1 = localObject2.getClass().getName() + ": " + ((Throwable)localObject2).getMessage();
            break label134;
          }
          if ((paramThrowable instanceof EvaluatorException))
          {
            localObject3 = (EvaluatorException)paramThrowable;
            paramScriptable1 = TopLevel.NativeErrors.InternalError;
            localObject1 = ((EvaluatorException)localObject3).getMessage();
            localObject2 = null;
            break label134;
          }
          if (paramContext.hasFeature(13))
          {
            localObject3 = new WrappedException(paramThrowable);
            paramScriptable1 = TopLevel.NativeErrors.JavaException;
            localObject1 = paramThrowable.toString();
            localObject2 = null;
            break label134;
          }
          throw Kit.codeBug();
        }
      }
      i = 1;
    }
  }
  
  static Scriptable newNativeError(Context paramContext, Scriptable paramScriptable, TopLevel.NativeErrors paramNativeErrors, Object[] paramArrayOfObject)
  {
    Scriptable localScriptable = ScriptableObject.getTopLevelScope(paramScriptable);
    paramNativeErrors = TopLevel.getNativeErrorCtor(paramContext, localScriptable, paramNativeErrors);
    paramScriptable = paramArrayOfObject;
    if (paramArrayOfObject == null) {
      paramScriptable = emptyArgs;
    }
    return paramNativeErrors.construct(paramContext, localScriptable, paramScriptable);
  }
  
  public static Scriptable newObject(Object paramObject, Context paramContext, Scriptable paramScriptable, Object[] paramArrayOfObject)
  {
    if (!(paramObject instanceof Function)) {
      throw notFunctionError(paramObject);
    }
    return ((Function)paramObject).construct(paramContext, paramScriptable, paramArrayOfObject);
  }
  
  public static Scriptable newObject(Context paramContext, Scriptable paramScriptable, String paramString, Object[] paramArrayOfObject)
  {
    Scriptable localScriptable = ScriptableObject.getTopLevelScope(paramScriptable);
    paramString = getExistingCtor(paramContext, localScriptable, paramString);
    paramScriptable = paramArrayOfObject;
    if (paramArrayOfObject == null) {
      paramScriptable = emptyArgs;
    }
    return paramString.construct(paramContext, localScriptable, paramScriptable);
  }
  
  @Deprecated
  public static Scriptable newObjectLiteral(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, Context paramContext, Scriptable paramScriptable)
  {
    return newObjectLiteral(paramArrayOfObject1, paramArrayOfObject2, null, paramContext, paramScriptable);
  }
  
  public static Scriptable newObjectLiteral(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, int[] paramArrayOfInt, Context paramContext, Scriptable paramScriptable)
  {
    Scriptable localScriptable = paramContext.newObject(paramScriptable);
    int k = paramArrayOfObject1.length;
    int i = 0;
    if (i != k)
    {
      Object localObject1 = paramArrayOfObject1[i];
      int j;
      label35:
      Object localObject2;
      if (paramArrayOfInt == null)
      {
        j = 0;
        localObject2 = paramArrayOfObject2[i];
        if (!(localObject1 instanceof String)) {
          break label171;
        }
        if (j != 0) {
          break label124;
        }
        if (!isSpecialProperty((String)localObject1)) {
          break label105;
        }
        specialRef(localScriptable, (String)localObject1, paramContext, paramScriptable).set(paramContext, paramScriptable, localObject2);
      }
      for (;;)
      {
        i += 1;
        break;
        j = paramArrayOfInt[i];
        break label35;
        label105:
        localScriptable.put((String)localObject1, localScriptable, localObject2);
        continue;
        label124:
        ScriptableObject localScriptableObject = (ScriptableObject)localScriptable;
        localObject2 = (Callable)localObject2;
        if (j == 1) {}
        for (boolean bool = true;; bool = false)
        {
          localScriptableObject.setGetterOrSetter((String)localObject1, 0, (Callable)localObject2, bool);
          break;
        }
        label171:
        localScriptable.put(((Integer)localObject1).intValue(), localScriptable, localObject2);
      }
    }
    return localScriptable;
  }
  
  public static Object newSpecial(Context paramContext, Object paramObject, Object[] paramArrayOfObject, Scriptable paramScriptable, int paramInt)
  {
    if (paramInt == 1)
    {
      if (NativeGlobal.isEvalFunction(paramObject)) {
        throw typeError1("msg.not.ctor", "eval");
      }
    }
    else if (paramInt == 2)
    {
      if (NativeWith.isWithFunction(paramObject)) {
        return NativeWith.newWithSpecial(paramContext, paramScriptable, paramArrayOfObject);
      }
    }
    else {
      throw Kit.codeBug();
    }
    return newObject(paramObject, paramContext, paramScriptable, paramArrayOfObject);
  }
  
  public static RuntimeException notFoundError(Scriptable paramScriptable, String paramString)
  {
    throw constructError("ReferenceError", getMessage1("msg.is.not.defined", paramString));
  }
  
  public static RuntimeException notFunctionError(Object paramObject)
  {
    return notFunctionError(paramObject, paramObject);
  }
  
  public static RuntimeException notFunctionError(Object paramObject1, Object paramObject2)
  {
    if (paramObject2 == null) {}
    for (paramObject2 = "null"; paramObject1 == Scriptable.NOT_FOUND; paramObject2 = paramObject2.toString()) {
      return typeError1("msg.function.not.found", paramObject2);
    }
    return typeError2("msg.isnt.function", paramObject2, typeof(paramObject1));
  }
  
  public static RuntimeException notFunctionError(Object paramObject1, Object paramObject2, String paramString)
  {
    String str2 = toString(paramObject1);
    String str1 = str2;
    if ((paramObject1 instanceof NativeFunction))
    {
      int i = str2.indexOf('{', str2.indexOf(41));
      str1 = str2;
      if (i > -1) {
        str1 = str2.substring(0, i + 1) + "...}";
      }
    }
    if (paramObject2 == Scriptable.NOT_FOUND) {
      return typeError2("msg.function.not.found.in", paramString, str1);
    }
    return typeError3("msg.isnt.function.in", paramString, str1, typeof(paramObject2));
  }
  
  private static RuntimeException notXmlError(Object paramObject)
  {
    throw typeError1("msg.isnt.xml.object", toString(paramObject));
  }
  
  public static String numberToString(double paramDouble, int paramInt)
  {
    if ((paramInt < 2) || (paramInt > 36)) {
      throw Context.reportRuntimeError1("msg.bad.radix", Integer.toString(paramInt));
    }
    if (paramDouble != paramDouble) {
      localObject = "NaN";
    }
    String str;
    do
    {
      return (String)localObject;
      if (paramDouble == Double.POSITIVE_INFINITY) {
        return "Infinity";
      }
      if (paramDouble == Double.NEGATIVE_INFINITY) {
        return "-Infinity";
      }
      if (paramDouble == 0.0D) {
        return "0";
      }
      if (paramInt != 10) {
        return DToA.JS_dtobasestr(paramInt, paramDouble);
      }
      str = FastDtoa.numberToString(paramDouble);
      localObject = str;
    } while (str != null);
    Object localObject = new StringBuilder();
    DToA.JS_dtostr((StringBuilder)localObject, 0, 0, paramDouble);
    return ((StringBuilder)localObject).toString();
  }
  
  public static Object[] padArguments(Object[] paramArrayOfObject, int paramInt)
  {
    if (paramInt < paramArrayOfObject.length) {
      return paramArrayOfObject;
    }
    Object[] arrayOfObject = new Object[paramInt];
    int i = 0;
    int j;
    for (;;)
    {
      j = i;
      if (i >= paramArrayOfObject.length) {
        break;
      }
      arrayOfObject[i] = paramArrayOfObject[i];
      i += 1;
    }
    while (j < paramInt)
    {
      arrayOfObject[j] = Undefined.instance;
      j += 1;
    }
    return arrayOfObject;
  }
  
  @Deprecated
  public static Object propIncrDecr(Object paramObject, String paramString, Context paramContext, int paramInt)
  {
    return propIncrDecr(paramObject, paramString, paramContext, getTopCallScope(paramContext), paramInt);
  }
  
  public static Object propIncrDecr(Object paramObject, String paramString, Context paramContext, Scriptable paramScriptable, int paramInt)
  {
    paramContext = toObjectOrNull(paramContext, paramObject, paramScriptable);
    if (paramContext == null) {
      throw undefReadError(paramObject, paramString);
    }
    paramObject = paramContext;
    do
    {
      paramScriptable = ((Scriptable)paramObject).get(paramString, paramContext);
      if (paramScriptable != Scriptable.NOT_FOUND) {
        return doScriptableIncrDecr((Scriptable)paramObject, paramString, paramContext, paramScriptable, paramInt);
      }
      paramScriptable = ((Scriptable)paramObject).getPrototype();
      paramObject = paramScriptable;
    } while (paramScriptable != null);
    paramContext.put(paramString, paramContext, NaNobj);
    return NaNobj;
  }
  
  public static EcmaError rangeError(String paramString)
  {
    return constructError("RangeError", paramString);
  }
  
  public static Object refDel(Ref paramRef, Context paramContext)
  {
    return wrapBoolean(paramRef.delete(paramContext));
  }
  
  public static Object refGet(Ref paramRef, Context paramContext)
  {
    return paramRef.get(paramContext);
  }
  
  @Deprecated
  public static Object refIncrDecr(Ref paramRef, Context paramContext, int paramInt)
  {
    return refIncrDecr(paramRef, paramContext, getTopCallScope(paramContext), paramInt);
  }
  
  public static Object refIncrDecr(Ref paramRef, Context paramContext, Scriptable paramScriptable, int paramInt)
  {
    Object localObject = paramRef.get(paramContext);
    int i;
    double d;
    if ((paramInt & 0x2) != 0)
    {
      i = 1;
      if (!(localObject instanceof Number)) {
        break label81;
      }
      d = ((Number)localObject).doubleValue();
    }
    label34:
    label81:
    label112:
    for (;;)
    {
      if ((paramInt & 0x1) == 0) {}
      for (d += 1.0D;; d -= 1.0D)
      {
        Number localNumber = wrapNumber(d);
        paramRef.set(paramContext, paramScriptable, localNumber);
        paramRef = localNumber;
        if (i != 0) {
          paramRef = (Ref)localObject;
        }
        return paramRef;
        i = 0;
        break;
        d = toNumber(localObject);
        if (i == 0) {
          break label112;
        }
        localObject = wrapNumber(d);
        break label34;
      }
    }
  }
  
  @Deprecated
  public static Object refSet(Ref paramRef, Object paramObject, Context paramContext)
  {
    return refSet(paramRef, paramObject, paramContext, getTopCallScope(paramContext));
  }
  
  public static Object refSet(Ref paramRef, Object paramObject, Context paramContext, Scriptable paramScriptable)
  {
    return paramRef.set(paramContext, paramScriptable, paramObject);
  }
  
  public static Object searchDefaultNamespace(Context paramContext)
  {
    Object localObject2 = paramContext.currentActivationCall;
    Object localObject1 = localObject2;
    if (localObject2 == null) {}
    for (localObject1 = getTopCallScope(paramContext);; localObject1 = localObject2)
    {
      localObject2 = ((Scriptable)localObject1).getParentScope();
      if (localObject2 == null)
      {
        localObject1 = ScriptableObject.getProperty((Scriptable)localObject1, "__default_namespace__");
        paramContext = (Context)localObject1;
        if (localObject1 == Scriptable.NOT_FOUND) {
          paramContext = null;
        }
      }
      do
      {
        return paramContext;
        localObject1 = ((Scriptable)localObject1).get("__default_namespace__", (Scriptable)localObject1);
        paramContext = (Context)localObject1;
      } while (localObject1 != Scriptable.NOT_FOUND);
    }
  }
  
  public static void setBuiltinProtoAndParent(ScriptableObject paramScriptableObject, Scriptable paramScriptable, TopLevel.Builtins paramBuiltins)
  {
    paramScriptable = ScriptableObject.getTopLevelScope(paramScriptable);
    paramScriptableObject.setParentScope(paramScriptable);
    paramScriptableObject.setPrototype(TopLevel.getBuiltinPrototype(paramScriptable, paramBuiltins));
  }
  
  public static Object setConst(Scriptable paramScriptable, Object paramObject, Context paramContext, String paramString)
  {
    if ((paramScriptable instanceof XMLObject))
    {
      paramScriptable.put(paramString, paramScriptable, paramObject);
      return paramObject;
    }
    ScriptableObject.putConstProperty(paramScriptable, paramString, paramObject);
    return paramObject;
  }
  
  public static Object setDefaultNamespace(Object paramObject, Context paramContext)
  {
    NativeCall localNativeCall = paramContext.currentActivationCall;
    Object localObject = localNativeCall;
    if (localNativeCall == null) {
      localObject = getTopCallScope(paramContext);
    }
    paramObject = currentXMLLib(paramContext).toDefaultXmlNamespace(paramContext, paramObject);
    if (!((Scriptable)localObject).has("__default_namespace__", (Scriptable)localObject)) {
      ScriptableObject.defineProperty((Scriptable)localObject, "__default_namespace__", paramObject, 6);
    }
    for (;;)
    {
      return Undefined.instance;
      ((Scriptable)localObject).put("__default_namespace__", (Scriptable)localObject, paramObject);
    }
  }
  
  public static void setEnumNumbers(Object paramObject, boolean paramBoolean)
  {
    ((IdEnumeration)paramObject).enumNumbers = paramBoolean;
  }
  
  public static void setFunctionProtoAndParent(BaseFunction paramBaseFunction, Scriptable paramScriptable)
  {
    paramBaseFunction.setParentScope(paramScriptable);
    paramBaseFunction.setPrototype(ScriptableObject.getFunctionPrototype(paramScriptable));
  }
  
  public static Object setName(Scriptable paramScriptable1, Object paramObject, Context paramContext, Scriptable paramScriptable2, String paramString)
  {
    if (paramScriptable1 != null)
    {
      ScriptableObject.putProperty(paramScriptable1, paramString, paramObject);
      return paramObject;
    }
    if ((paramContext.hasFeature(11)) || (paramContext.hasFeature(8))) {
      Context.reportWarning(getMessage1("msg.assn.create.strict", paramString));
    }
    paramScriptable2 = ScriptableObject.getTopLevelScope(paramScriptable2);
    paramScriptable1 = paramScriptable2;
    if (paramContext.useDynamicScope) {
      paramScriptable1 = checkDynamicScope(paramContext.topCallScope, paramScriptable2);
    }
    paramScriptable1.put(paramString, paramScriptable1, paramObject);
    return paramObject;
  }
  
  @Deprecated
  public static Object setObjectElem(Object paramObject1, Object paramObject2, Object paramObject3, Context paramContext)
  {
    return setObjectElem(paramObject1, paramObject2, paramObject3, paramContext, getTopCallScope(paramContext));
  }
  
  public static Object setObjectElem(Object paramObject1, Object paramObject2, Object paramObject3, Context paramContext, Scriptable paramScriptable)
  {
    paramScriptable = toObjectOrNull(paramContext, paramObject1, paramScriptable);
    if (paramScriptable == null) {
      throw undefWriteError(paramObject1, paramObject2, paramObject3);
    }
    return setObjectElem(paramScriptable, paramObject2, paramObject3, paramContext);
  }
  
  public static Object setObjectElem(Scriptable paramScriptable, Object paramObject1, Object paramObject2, Context paramContext)
  {
    if ((paramScriptable instanceof XMLObject))
    {
      ((XMLObject)paramScriptable).put(paramContext, paramObject1, paramObject2);
      return paramObject2;
    }
    paramObject1 = toStringIdOrIndex(paramContext, paramObject1);
    if (paramObject1 == null)
    {
      ScriptableObject.putProperty(paramScriptable, lastIndexResult(paramContext), paramObject2);
      return paramObject2;
    }
    ScriptableObject.putProperty(paramScriptable, (String)paramObject1, paramObject2);
    return paramObject2;
  }
  
  @Deprecated
  public static Object setObjectIndex(Object paramObject1, double paramDouble, Object paramObject2, Context paramContext)
  {
    return setObjectIndex(paramObject1, paramDouble, paramObject2, paramContext, getTopCallScope(paramContext));
  }
  
  public static Object setObjectIndex(Object paramObject1, double paramDouble, Object paramObject2, Context paramContext, Scriptable paramScriptable)
  {
    paramScriptable = toObjectOrNull(paramContext, paramObject1, paramScriptable);
    if (paramScriptable == null) {
      throw undefWriteError(paramObject1, String.valueOf(paramDouble), paramObject2);
    }
    int i = (int)paramDouble;
    if (i == paramDouble) {
      return setObjectIndex(paramScriptable, i, paramObject2, paramContext);
    }
    return setObjectProp(paramScriptable, toString(paramDouble), paramObject2, paramContext);
  }
  
  public static Object setObjectIndex(Scriptable paramScriptable, int paramInt, Object paramObject, Context paramContext)
  {
    ScriptableObject.putProperty(paramScriptable, paramInt, paramObject);
    return paramObject;
  }
  
  @Deprecated
  public static Object setObjectProp(Object paramObject1, String paramString, Object paramObject2, Context paramContext)
  {
    return setObjectProp(paramObject1, paramString, paramObject2, paramContext, getTopCallScope(paramContext));
  }
  
  public static Object setObjectProp(Object paramObject1, String paramString, Object paramObject2, Context paramContext, Scriptable paramScriptable)
  {
    paramScriptable = toObjectOrNull(paramContext, paramObject1, paramScriptable);
    if (paramScriptable == null) {
      throw undefWriteError(paramObject1, paramString, paramObject2);
    }
    return setObjectProp(paramScriptable, paramString, paramObject2, paramContext);
  }
  
  public static Object setObjectProp(Scriptable paramScriptable, String paramString, Object paramObject, Context paramContext)
  {
    ScriptableObject.putProperty(paramScriptable, paramString, paramObject);
    return paramObject;
  }
  
  public static void setObjectProtoAndParent(ScriptableObject paramScriptableObject, Scriptable paramScriptable)
  {
    paramScriptable = ScriptableObject.getTopLevelScope(paramScriptable);
    paramScriptableObject.setParentScope(paramScriptable);
    paramScriptableObject.setPrototype(ScriptableObject.getClassPrototype(paramScriptable, paramScriptableObject.getClassName()));
  }
  
  public static void setRegExpProxy(Context paramContext, RegExpProxy paramRegExpProxy)
  {
    if (paramRegExpProxy == null) {
      throw new IllegalArgumentException();
    }
    paramContext.regExpProxy = paramRegExpProxy;
  }
  
  public static boolean shallowEq(Object paramObject1, Object paramObject2)
  {
    if (paramObject1 == paramObject2) {
      if ((paramObject1 instanceof Number)) {}
    }
    do
    {
      do
      {
        do
        {
          do
          {
            double d;
            do
            {
              return true;
              d = ((Number)paramObject1).doubleValue();
            } while (d == d);
            return false;
            if ((paramObject1 != null) && (paramObject1 != Undefined.instance) && (paramObject1 != Undefined.SCRIPTABLE_UNDEFINED)) {
              break;
            }
          } while (((paramObject1 == Undefined.instance) && (paramObject2 == Undefined.SCRIPTABLE_UNDEFINED)) || ((paramObject1 == Undefined.SCRIPTABLE_UNDEFINED) && (paramObject2 == Undefined.instance)));
          return false;
          if (!(paramObject1 instanceof Number)) {
            break;
          }
          if (!(paramObject2 instanceof Number)) {
            break label213;
          }
        } while (((Number)paramObject1).doubleValue() == ((Number)paramObject2).doubleValue());
        return false;
        if ((paramObject1 instanceof CharSequence))
        {
          if (!(paramObject2 instanceof CharSequence)) {
            break label213;
          }
          return paramObject1.toString().equals(paramObject2.toString());
        }
        if ((paramObject1 instanceof Boolean))
        {
          if (!(paramObject2 instanceof Boolean)) {
            break label213;
          }
          return paramObject1.equals(paramObject2);
        }
        if (!(paramObject1 instanceof Scriptable)) {
          break;
        }
        if ((!(paramObject1 instanceof Wrapper)) || (!(paramObject2 instanceof Wrapper))) {
          break label213;
        }
      } while (((Wrapper)paramObject1).unwrap() == ((Wrapper)paramObject2).unwrap());
      return false;
      warnAboutNonJSObject(paramObject1);
    } while (paramObject1 == paramObject2);
    return false;
    label213:
    return false;
  }
  
  @Deprecated
  public static Ref specialRef(Object paramObject, String paramString, Context paramContext)
  {
    return specialRef(paramObject, paramString, paramContext, getTopCallScope(paramContext));
  }
  
  public static Ref specialRef(Object paramObject, String paramString, Context paramContext, Scriptable paramScriptable)
  {
    return SpecialRef.createSpecial(paramContext, paramScriptable, paramObject, paramString);
  }
  
  private static void storeIndexResult(Context paramContext, int paramInt)
  {
    paramContext.scratchIndex = paramInt;
  }
  
  private static void storeScriptable(Context paramContext, Scriptable paramScriptable)
  {
    if (paramContext.scratchScriptable != null) {
      throw new IllegalStateException();
    }
    paramContext.scratchScriptable = paramScriptable;
  }
  
  public static void storeUint32Result(Context paramContext, long paramLong)
  {
    if (paramLong >>> 32 != 0L) {
      throw new IllegalArgumentException();
    }
    paramContext.scratchUint32 = paramLong;
  }
  
  public static Object strictSetName(Scriptable paramScriptable1, Object paramObject, Context paramContext, Scriptable paramScriptable2, String paramString)
  {
    if (paramScriptable1 != null)
    {
      ScriptableObject.putProperty(paramScriptable1, paramString, paramObject);
      return paramObject;
    }
    throw constructError("ReferenceError", "Assignment to undefined \"" + paramString + "\" in strict mode");
  }
  
  static double stringToNumber(String paramString, int paramInt1, int paramInt2)
  {
    int m = 97;
    int k = 65;
    int n = paramString.length();
    if (paramInt2 < 10) {}
    for (int j = (char)(paramInt2 + 48 - 1);; j = 57)
    {
      if (paramInt2 > 10)
      {
        m = (char)(paramInt2 + 97 - 10);
        k = (char)(paramInt2 + 65 - 10);
      }
      double d1 = 0.0D;
      int i3 = paramInt1;
      if (i3 < n)
      {
        i = paramString.charAt(i3);
        if ((48 <= i) && (i <= j)) {
          i -= 48;
        }
        for (;;)
        {
          d2 = paramInt2;
          d1 = i + d2 * d1;
          i3 += 1;
          break;
          if ((97 <= i) && (i < m))
          {
            i = i - 97 + 10;
          }
          else
          {
            if ((65 > i) || (i >= k)) {
              break label172;
            }
            i = i - 65 + 10;
          }
        }
      }
      label172:
      double d3;
      if (paramInt1 == i3) {
        d3 = NaN;
      }
      do
      {
        do
        {
          return d3;
          d3 = d1;
        } while (d1 < 9.007199254740992E15D);
        if (paramInt2 == 10) {
          try
          {
            d1 = Double.parseDouble(paramString.substring(paramInt1, i3));
            return d1;
          }
          catch (NumberFormatException paramString)
          {
            return NaN;
          }
        }
        if ((paramInt2 == 2) || (paramInt2 == 4) || (paramInt2 == 8) || (paramInt2 == 16)) {
          break;
        }
        d3 = d1;
      } while (paramInt2 != 32);
      m = 1;
      int i1 = 0;
      k = 0;
      j = 53;
      double d2 = 0.0D;
      int i = 0;
      int i2 = 0;
      n = paramInt1;
      paramInt1 = i2;
      if (m == 1)
      {
        if (n == i3) {
          d3 = d1;
        }
        switch (k)
        {
        case 1: 
        case 2: 
        default: 
          return d1;
        case 0: 
          return 0.0D;
          m = paramString.charAt(n);
          if ((48 <= m) && (m <= 57))
          {
            m -= 48;
            label365:
            n += 1;
            i2 = paramInt2;
            i1 = m;
          }
          break;
        }
      }
      for (;;)
      {
        int i4 = i2 >> 1;
        if ((i1 & i4) != 0) {
          m = 1;
        }
        switch (k)
        {
        default: 
          d3 = d1;
          i2 = j;
          d1 = d2;
          j = i2;
          d2 = d3;
        case 0: 
        case 1: 
        case 2: 
          for (;;)
          {
            d3 = d2;
            m = i4;
            d2 = d1;
            d1 = d3;
            break;
            if ((97 <= m) && (m <= 122))
            {
              m -= 87;
              break label365;
            }
            m -= 55;
            break label365;
            m = 0;
            break label395;
            i2 = j;
            d3 = d1;
            if (m == 0) {
              break label439;
            }
            d3 = 1.0D;
            d1 = d2;
            j -= 1;
            k = 1;
            d2 = d3;
            continue;
            d3 = d1 * 2.0D;
            d1 = d3;
            if (m != 0) {
              d1 = d3 + 1.0D;
            }
            j -= 1;
            i2 = j;
            d3 = d1;
            if (j != 0) {
              break label439;
            }
            k = 2;
            i = m;
            d3 = d1;
            d1 = d2;
            d2 = d3;
            continue;
            d3 = 2.0D;
            k = 3;
            d2 = d1;
            paramInt1 = m;
            d1 = d3;
          }
        case 3: 
          label395:
          label439:
          if (m != 0) {
            k = 4;
          }
          break;
        }
        for (;;)
        {
          d3 = d2 * 2.0D;
          d2 = d1;
          d1 = d3;
          break;
          d3 = d1;
          if ((paramInt1 & i) != 0) {
            d3 = d1 + 1.0D;
          }
          return d3 * d2;
          d3 = d1;
          if (paramInt1 != 0) {
            d3 = d1 + 1.0D;
          }
          return d3 * d2;
        }
        i2 = m;
      }
    }
  }
  
  public static long testUint32String(String paramString)
  {
    long l2 = 0L;
    long l3 = -1L;
    int j = paramString.length();
    long l1 = l3;
    int i;
    if (1 <= j)
    {
      l1 = l3;
      if (j <= 10)
      {
        i = paramString.charAt(0) - '0';
        if (i != 0) {
          break label65;
        }
        if (j != 1) {
          break label57;
        }
        l1 = l2;
      }
    }
    label57:
    label65:
    label144:
    do
    {
      do
      {
        do
        {
          for (;;)
          {
            return l1;
            l1 = -1L;
          }
          l1 = l3;
        } while (1 > i);
        l1 = l3;
      } while (i > 9);
      l2 = i;
      i = 1;
      for (;;)
      {
        if (i == j) {
          break label144;
        }
        int k = paramString.charAt(i) - '0';
        l1 = l3;
        if (k < 0) {
          break;
        }
        l1 = l3;
        if (k > 9) {
          break;
        }
        l1 = k;
        i += 1;
        l2 = l1 + l2 * 10L;
      }
      l1 = l3;
    } while (l2 >>> 32 != 0L);
    return l2;
  }
  
  public static JavaScriptException throwCustomError(Context paramContext, Scriptable paramScriptable, String paramString1, String paramString2)
  {
    int[] arrayOfInt = new int[1];
    arrayOfInt[0] = 0;
    String str = Context.getSourcePositionFromStack(arrayOfInt);
    return new JavaScriptException(paramContext.newObject(paramScriptable, paramString1, new Object[] { paramString2, str, Integer.valueOf(arrayOfInt[0]) }), str, arrayOfInt[0]);
  }
  
  public static JavaScriptException throwError(Context paramContext, Scriptable paramScriptable, String paramString)
  {
    int[] arrayOfInt = new int[1];
    arrayOfInt[0] = 0;
    String str = Context.getSourcePositionFromStack(arrayOfInt);
    return new JavaScriptException(newBuiltinObject(paramContext, paramScriptable, TopLevel.Builtins.Error, new Object[] { paramString, str, Integer.valueOf(arrayOfInt[0]) }), str, arrayOfInt[0]);
  }
  
  public static boolean toBoolean(Object paramObject)
  {
    boolean bool2 = true;
    label93:
    Object localObject;
    do
    {
      boolean bool1;
      if ((paramObject instanceof Boolean)) {
        bool1 = ((Boolean)paramObject).booleanValue();
      }
      do
      {
        double d;
        do
        {
          return bool1;
          if ((paramObject == null) || (paramObject == Undefined.instance)) {
            return false;
          }
          if ((paramObject instanceof CharSequence))
          {
            if (((CharSequence)paramObject).length() != 0) {}
            for (bool1 = true;; bool1 = false) {
              return bool1;
            }
          }
          if (!(paramObject instanceof Number)) {
            break label93;
          }
          d = ((Number)paramObject).doubleValue();
          if (d != d) {
            break;
          }
          bool1 = bool2;
        } while (d != 0.0D);
        return false;
        if (!(paramObject instanceof Scriptable)) {
          break;
        }
        if (((paramObject instanceof ScriptableObject)) && (((ScriptableObject)paramObject).avoidObjectDetection())) {
          return false;
        }
        bool1 = bool2;
      } while (Context.getContext().isVersionECMA1());
      localObject = ((Scriptable)paramObject).getDefaultValue(BooleanClass);
      paramObject = localObject;
    } while (!(localObject instanceof Scriptable));
    throw errorWithClassName("msg.primitive.expected", localObject);
    warnAboutNonJSObject(paramObject);
    return true;
  }
  
  public static CharSequence toCharSequence(Object paramObject)
  {
    if ((paramObject instanceof NativeString)) {
      return ((NativeString)paramObject).toCharSequence();
    }
    if ((paramObject instanceof CharSequence)) {
      return (CharSequence)paramObject;
    }
    return toString(paramObject);
  }
  
  public static int toInt32(double paramDouble)
  {
    return DoubleConversion.doubleToInt32(paramDouble);
  }
  
  public static int toInt32(Object paramObject)
  {
    if ((paramObject instanceof Integer)) {
      return ((Integer)paramObject).intValue();
    }
    return toInt32(toNumber(paramObject));
  }
  
  public static int toInt32(Object[] paramArrayOfObject, int paramInt)
  {
    if (paramInt < paramArrayOfObject.length) {
      return toInt32(paramArrayOfObject[paramInt]);
    }
    return 0;
  }
  
  public static double toInteger(double paramDouble)
  {
    double d;
    if (paramDouble != paramDouble) {
      d = 0.0D;
    }
    do
    {
      do
      {
        do
        {
          return d;
          d = paramDouble;
        } while (paramDouble == 0.0D);
        d = paramDouble;
      } while (paramDouble == Double.POSITIVE_INFINITY);
      d = paramDouble;
    } while (paramDouble == Double.NEGATIVE_INFINITY);
    if (paramDouble > 0.0D) {
      return Math.floor(paramDouble);
    }
    return Math.ceil(paramDouble);
  }
  
  public static double toInteger(Object paramObject)
  {
    return toInteger(toNumber(paramObject));
  }
  
  public static double toInteger(Object[] paramArrayOfObject, int paramInt)
  {
    if (paramInt < paramArrayOfObject.length) {
      return toInteger(paramArrayOfObject[paramInt]);
    }
    return 0.0D;
  }
  
  public static Scriptable toIterator(Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, boolean paramBoolean)
  {
    if (ScriptableObject.hasProperty(paramScriptable2, "__iterator__"))
    {
      Object localObject = ScriptableObject.getProperty(paramScriptable2, "__iterator__");
      if (!(localObject instanceof Callable)) {
        throw typeError0("msg.invalid.iterator");
      }
      Callable localCallable = (Callable)localObject;
      if (paramBoolean) {}
      for (localObject = Boolean.TRUE;; localObject = Boolean.FALSE)
      {
        paramContext = localCallable.call(paramContext, paramScriptable1, paramScriptable2, new Object[] { localObject });
        if ((paramContext instanceof Scriptable)) {
          break;
        }
        throw typeError0("msg.iterator.primitive");
      }
      return (Scriptable)paramContext;
    }
    return null;
  }
  
  public static double toNumber(Object paramObject)
  {
    double d2 = 0.0D;
    Object localObject;
    do
    {
      double d1;
      if ((paramObject instanceof Number)) {
        d1 = ((Number)paramObject).doubleValue();
      }
      do
      {
        return d1;
        d1 = d2;
      } while (paramObject == null);
      if (paramObject == Undefined.instance) {
        return NaN;
      }
      if ((paramObject instanceof String)) {
        return toNumber((String)paramObject);
      }
      if ((paramObject instanceof CharSequence)) {
        return toNumber(paramObject.toString());
      }
      if ((paramObject instanceof Boolean))
      {
        if (((Boolean)paramObject).booleanValue()) {}
        for (d1 = 1.0D;; d1 = 0.0D) {
          return d1;
        }
      }
      if (!(paramObject instanceof Scriptable)) {
        break;
      }
      localObject = ((Scriptable)paramObject).getDefaultValue(NumberClass);
      paramObject = localObject;
    } while (!(localObject instanceof Scriptable));
    throw errorWithClassName("msg.primitive.expected", localObject);
    warnAboutNonJSObject(paramObject);
    return NaN;
  }
  
  public static double toNumber(String paramString)
  {
    int j = paramString.length();
    int i = 0;
    double d1;
    if (i == j) {
      d1 = 0.0D;
    }
    int m;
    int k;
    double d2;
    do
    {
      return d1;
      m = paramString.charAt(i);
      if (!isStrWhiteSpaceChar(m))
      {
        if (m == 48)
        {
          if (i + 2 >= j) {
            break label179;
          }
          k = paramString.charAt(i + 1);
          if ((k != 120) && (k != 88)) {
            break label179;
          }
          return stringToNumber(paramString, i + 2, 16);
        }
      }
      else
      {
        i += 1;
        break;
      }
      if (((m != 43) && (m != 45)) || (i + 3 >= j) || (paramString.charAt(i + 1) != '0')) {
        break label179;
      }
      k = paramString.charAt(i + 2);
      if ((k != 120) && (k != 88)) {
        break label179;
      }
      d2 = stringToNumber(paramString, i + 3, 16);
      d1 = d2;
    } while (m != 45);
    return -d2;
    label179:
    j -= 1;
    for (;;)
    {
      k = paramString.charAt(j);
      if (!isStrWhiteSpaceChar(k)) {
        break;
      }
      j -= 1;
    }
    if (k == 121)
    {
      if (m != 43)
      {
        k = i;
        if (m != 45) {}
      }
      else
      {
        k = i + 1;
      }
      if ((k + 7 == j) && (paramString.regionMatches(k, "Infinity", 0, 8)))
      {
        if (m == 45) {
          return Double.NEGATIVE_INFINITY;
        }
        return Double.POSITIVE_INFINITY;
      }
      return NaN;
    }
    paramString = paramString.substring(i, j + 1);
    i = paramString.length() - 1;
    while (i >= 0)
    {
      j = paramString.charAt(i);
      if (((48 <= j) && (j <= 57)) || (j == 46) || (j == 101) || (j == 69) || (j == 43) || (j == 45)) {
        i -= 1;
      } else {
        return NaN;
      }
    }
    try
    {
      d1 = Double.parseDouble(paramString);
      return d1;
    }
    catch (NumberFormatException paramString) {}
    return NaN;
  }
  
  public static double toNumber(Object[] paramArrayOfObject, int paramInt)
  {
    if (paramInt < paramArrayOfObject.length) {
      return toNumber(paramArrayOfObject[paramInt]);
    }
    return NaN;
  }
  
  public static Scriptable toObject(Context paramContext, Scriptable paramScriptable, Object paramObject)
  {
    if ((paramObject instanceof Scriptable)) {
      return (Scriptable)paramObject;
    }
    if ((paramObject instanceof CharSequence))
    {
      paramContext = new NativeString((CharSequence)paramObject);
      setBuiltinProtoAndParent(paramContext, paramScriptable, TopLevel.Builtins.String);
      return paramContext;
    }
    if ((paramObject instanceof Number))
    {
      paramContext = new NativeNumber(((Number)paramObject).doubleValue());
      setBuiltinProtoAndParent(paramContext, paramScriptable, TopLevel.Builtins.Number);
      return paramContext;
    }
    if ((paramObject instanceof Boolean))
    {
      paramContext = new NativeBoolean(((Boolean)paramObject).booleanValue());
      setBuiltinProtoAndParent(paramContext, paramScriptable, TopLevel.Builtins.Boolean);
      return paramContext;
    }
    if (paramObject == null) {
      throw typeError0("msg.null.to.object");
    }
    if (paramObject == Undefined.instance) {
      throw typeError0("msg.undef.to.object");
    }
    paramContext = paramContext.getWrapFactory().wrap(paramContext, paramScriptable, paramObject, null);
    if ((paramContext instanceof Scriptable)) {
      return (Scriptable)paramContext;
    }
    throw errorWithClassName("msg.invalid.type", paramObject);
  }
  
  @Deprecated
  public static Scriptable toObject(Context paramContext, Scriptable paramScriptable, Object paramObject, Class<?> paramClass)
  {
    return toObject(paramContext, paramScriptable, paramObject);
  }
  
  public static Scriptable toObject(Scriptable paramScriptable, Object paramObject)
  {
    if ((paramObject instanceof Scriptable)) {
      return (Scriptable)paramObject;
    }
    return toObject(Context.getContext(), paramScriptable, paramObject);
  }
  
  @Deprecated
  public static Scriptable toObject(Scriptable paramScriptable, Object paramObject, Class<?> paramClass)
  {
    if ((paramObject instanceof Scriptable)) {
      return (Scriptable)paramObject;
    }
    return toObject(Context.getContext(), paramScriptable, paramObject);
  }
  
  @Deprecated
  public static Scriptable toObjectOrNull(Context paramContext, Object paramObject)
  {
    if ((paramObject instanceof Scriptable)) {
      return (Scriptable)paramObject;
    }
    if ((paramObject != null) && (paramObject != Undefined.instance)) {
      return toObject(paramContext, getTopCallScope(paramContext), paramObject);
    }
    return null;
  }
  
  public static Scriptable toObjectOrNull(Context paramContext, Object paramObject, Scriptable paramScriptable)
  {
    if ((paramObject instanceof Scriptable)) {
      return (Scriptable)paramObject;
    }
    if ((paramObject != null) && (paramObject != Undefined.instance)) {
      return toObject(paramContext, paramScriptable, paramObject);
    }
    return null;
  }
  
  public static Object toPrimitive(Object paramObject)
  {
    return toPrimitive(paramObject, null);
  }
  
  public static Object toPrimitive(Object paramObject, Class<?> paramClass)
  {
    if (!(paramObject instanceof Scriptable)) {}
    do
    {
      return paramObject;
      paramClass = ((Scriptable)paramObject).getDefaultValue(paramClass);
      paramObject = paramClass;
    } while (!(paramClass instanceof Scriptable));
    throw typeError0("msg.bad.default.value");
  }
  
  public static String toString(double paramDouble)
  {
    return numberToString(paramDouble, 10);
  }
  
  public static String toString(Object paramObject)
  {
    Object localObject;
    do
    {
      if (paramObject == null) {
        return "null";
      }
      if ((paramObject == Undefined.instance) || (paramObject == Undefined.SCRIPTABLE_UNDEFINED)) {
        return "undefined";
      }
      if ((paramObject instanceof String)) {
        return (String)paramObject;
      }
      if ((paramObject instanceof CharSequence)) {
        return paramObject.toString();
      }
      if ((paramObject instanceof Number)) {
        return numberToString(((Number)paramObject).doubleValue(), 10);
      }
      if (!(paramObject instanceof Scriptable)) {
        break;
      }
      localObject = ((Scriptable)paramObject).getDefaultValue(StringClass);
      paramObject = localObject;
    } while (!(localObject instanceof Scriptable));
    throw errorWithClassName("msg.primitive.expected", localObject);
    return paramObject.toString();
  }
  
  public static String toString(Object[] paramArrayOfObject, int paramInt)
  {
    if (paramInt < paramArrayOfObject.length) {
      return toString(paramArrayOfObject[paramInt]);
    }
    return "undefined";
  }
  
  static String toStringIdOrIndex(Context paramContext, Object paramObject)
  {
    if ((paramObject instanceof Number))
    {
      double d = ((Number)paramObject).doubleValue();
      int i = (int)d;
      if (i == d)
      {
        storeIndexResult(paramContext, i);
        paramObject = null;
        return (String)paramObject;
      }
      return toString(paramObject);
    }
    if ((paramObject instanceof String)) {}
    for (paramObject = (String)paramObject;; paramObject = toString(paramObject))
    {
      long l = indexFromString((String)paramObject);
      if (l < 0L) {
        break;
      }
      storeIndexResult(paramContext, (int)l);
      return null;
    }
  }
  
  public static char toUint16(Object paramObject)
  {
    return (char)DoubleConversion.doubleToInt32(toNumber(paramObject));
  }
  
  public static long toUint32(double paramDouble)
  {
    return DoubleConversion.doubleToInt32(paramDouble) & 0xFFFFFFFF;
  }
  
  public static long toUint32(Object paramObject)
  {
    return toUint32(toNumber(paramObject));
  }
  
  private static Object topScopeName(Context paramContext, Scriptable paramScriptable, String paramString)
  {
    Scriptable localScriptable = paramScriptable;
    if (paramContext.useDynamicScope) {
      localScriptable = checkDynamicScope(paramContext.topCallScope, paramScriptable);
    }
    return ScriptableObject.getProperty(localScriptable, paramString);
  }
  
  public static EcmaError typeError(String paramString)
  {
    return constructError("TypeError", paramString);
  }
  
  public static EcmaError typeError0(String paramString)
  {
    return typeError(getMessage0(paramString));
  }
  
  public static EcmaError typeError1(String paramString, Object paramObject)
  {
    return typeError(getMessage1(paramString, paramObject));
  }
  
  public static EcmaError typeError2(String paramString, Object paramObject1, Object paramObject2)
  {
    return typeError(getMessage2(paramString, paramObject1, paramObject2));
  }
  
  public static EcmaError typeError3(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    return typeError(getMessage3(paramString1, paramString2, paramString3, paramString4));
  }
  
  @Deprecated
  public static BaseFunction typeErrorThrower()
  {
    return typeErrorThrower(Context.getCurrentContext());
  }
  
  public static BaseFunction typeErrorThrower(Context paramContext)
  {
    if (paramContext.typeErrorThrower == null)
    {
      BaseFunction local1 = new BaseFunction()
      {
        static final long serialVersionUID = -5891740962154902286L;
        
        public Object call(Context paramAnonymousContext, Scriptable paramAnonymousScriptable1, Scriptable paramAnonymousScriptable2, Object[] paramAnonymousArrayOfObject)
        {
          throw ScriptRuntime.typeError0("msg.op.not.allowed");
        }
        
        public int getLength()
        {
          return 0;
        }
      };
      setFunctionProtoAndParent(local1, paramContext.topCallScope);
      local1.preventExtensions();
      paramContext.typeErrorThrower = local1;
    }
    return paramContext.typeErrorThrower;
  }
  
  public static String typeof(Object paramObject)
  {
    if (paramObject == null) {
      return "object";
    }
    if (paramObject == Undefined.instance) {
      return "undefined";
    }
    if ((paramObject instanceof ScriptableObject)) {
      return ((ScriptableObject)paramObject).getTypeOf();
    }
    if ((paramObject instanceof Scriptable))
    {
      if ((paramObject instanceof Callable)) {
        return "function";
      }
      return "object";
    }
    if ((paramObject instanceof CharSequence)) {
      return "string";
    }
    if ((paramObject instanceof Number)) {
      return "number";
    }
    if ((paramObject instanceof Boolean)) {
      return "boolean";
    }
    throw errorWithClassName("msg.invalid.type", paramObject);
  }
  
  public static String typeofName(Scriptable paramScriptable, String paramString)
  {
    Context localContext = Context.getContext();
    paramScriptable = bind(localContext, paramScriptable, paramString);
    if (paramScriptable == null) {
      return "undefined";
    }
    return typeof(getObjectProp(paramScriptable, paramString, localContext));
  }
  
  public static RuntimeException undefCallError(Object paramObject1, Object paramObject2)
  {
    return typeError2("msg.undef.method.call", toString(paramObject1), toString(paramObject2));
  }
  
  private static RuntimeException undefDeleteError(Object paramObject1, Object paramObject2)
  {
    throw typeError2("msg.undef.prop.delete", toString(paramObject1), toString(paramObject2));
  }
  
  public static RuntimeException undefReadError(Object paramObject1, Object paramObject2)
  {
    return typeError2("msg.undef.prop.read", toString(paramObject1), toString(paramObject2));
  }
  
  public static RuntimeException undefWriteError(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    return typeError3("msg.undef.prop.write", toString(paramObject1), toString(paramObject2), toString(paramObject3));
  }
  
  static String uneval(Context paramContext, Scriptable paramScriptable, Object paramObject)
  {
    if (paramObject == null) {
      return "null";
    }
    if (paramObject == Undefined.instance) {
      return "undefined";
    }
    if ((paramObject instanceof CharSequence))
    {
      paramContext = escapeString(paramObject.toString());
      paramScriptable = new StringBuilder(paramContext.length() + 2);
      paramScriptable.append('"');
      paramScriptable.append(paramContext);
      paramScriptable.append('"');
      return paramScriptable.toString();
    }
    if ((paramObject instanceof Number))
    {
      double d = ((Number)paramObject).doubleValue();
      if ((d == 0.0D) && (1.0D / d < 0.0D)) {
        return "-0";
      }
      return toString(d);
    }
    if ((paramObject instanceof Boolean)) {
      return toString(paramObject);
    }
    if ((paramObject instanceof Scriptable))
    {
      Scriptable localScriptable = (Scriptable)paramObject;
      if (ScriptableObject.hasProperty(localScriptable, "toSource"))
      {
        Object localObject = ScriptableObject.getProperty(localScriptable, "toSource");
        if ((localObject instanceof Function)) {
          return toString(((Function)localObject).call(paramContext, paramScriptable, localScriptable, emptyArgs));
        }
      }
      return toString(paramObject);
    }
    warnAboutNonJSObject(paramObject);
    return paramObject.toString();
  }
  
  public static Object updateDotQuery(boolean paramBoolean, Scriptable paramScriptable)
  {
    return ((NativeWith)paramScriptable).updateDotQuery(paramBoolean);
  }
  
  private static void warnAboutNonJSObject(Object paramObject)
  {
    paramObject = "RHINO USAGE WARNING: Missed Context.javaToJS() conversion:\nRhino runtime detected object " + paramObject + " of class " + paramObject.getClass().getName() + " where it expected String, Number, Boolean or Scriptable instance. Please check your code for missing Context.javaToJS() call.";
    Context.reportWarning((String)paramObject);
    System.err.println((String)paramObject);
  }
  
  public static Boolean wrapBoolean(boolean paramBoolean)
  {
    if (paramBoolean) {
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
  }
  
  public static Scriptable wrapException(Throwable paramThrowable, Scriptable paramScriptable, Context paramContext)
  {
    Object localObject2;
    String str1;
    String str2;
    int i;
    if ((paramThrowable instanceof EcmaError))
    {
      localObject2 = (EcmaError)paramThrowable;
      str1 = ((EcmaError)localObject2).getName();
      localObject1 = ((EcmaError)localObject2).getErrorMessage();
      paramThrowable = null;
      String str3 = ((RhinoException)localObject2).sourceName();
      str2 = str3;
      if (str3 == null) {
        str2 = "";
      }
      i = ((RhinoException)localObject2).lineNumber();
      if (i <= 0) {
        break label319;
      }
    }
    label319:
    for (Object localObject1 = { localObject1, str2, Integer.valueOf(i) };; localObject1 = new Object[] { localObject1, str2 })
    {
      localObject1 = paramContext.newObject(paramScriptable, str1, (Object[])localObject1);
      ScriptableObject.putProperty((Scriptable)localObject1, "name", str1);
      if ((localObject1 instanceof NativeError)) {
        ((NativeError)localObject1).setStackProvider((RhinoException)localObject2);
      }
      if ((paramThrowable != null) && (isVisible(paramContext, paramThrowable))) {
        ScriptableObject.defineProperty((Scriptable)localObject1, "javaException", paramContext.getWrapFactory().wrap(paramContext, paramScriptable, paramThrowable, null), 7);
      }
      if (isVisible(paramContext, localObject2)) {
        ScriptableObject.defineProperty((Scriptable)localObject1, "rhinoException", paramContext.getWrapFactory().wrap(paramContext, paramScriptable, localObject2, null), 7);
      }
      return (Scriptable)localObject1;
      if ((paramThrowable instanceof WrappedException))
      {
        localObject2 = (WrappedException)paramThrowable;
        paramThrowable = ((WrappedException)localObject2).getWrappedException();
        localObject1 = paramThrowable.getClass().getName() + ": " + paramThrowable.getMessage();
        str1 = "JavaException";
        break;
      }
      if ((paramThrowable instanceof EvaluatorException))
      {
        localObject2 = (EvaluatorException)paramThrowable;
        localObject1 = ((EvaluatorException)localObject2).getMessage();
        paramThrowable = null;
        str1 = "InternalError";
        break;
      }
      if (paramContext.hasFeature(13))
      {
        localObject2 = new WrappedException(paramThrowable);
        localObject1 = paramThrowable.toString();
        str1 = "JavaException";
        paramThrowable = null;
        break;
      }
      throw Kit.codeBug();
    }
  }
  
  public static Integer wrapInt(int paramInt)
  {
    return Integer.valueOf(paramInt);
  }
  
  public static Number wrapNumber(double paramDouble)
  {
    if (paramDouble != paramDouble) {
      return NaNobj;
    }
    return new Double(paramDouble);
  }
  
  public static Scriptable wrapRegExp(Context paramContext, Scriptable paramScriptable, Object paramObject)
  {
    return paramContext.getRegExpProxy().wrapRegExp(paramContext, paramScriptable, paramObject);
  }
  
  private static class DefaultMessageProvider
    implements ScriptRuntime.MessageProvider
  {
    public String getMessage(String paramString, Object[] paramArrayOfObject)
    {
      Object localObject = Context.getCurrentContext();
      if (localObject != null) {}
      for (localObject = ((Context)localObject).getLocale();; localObject = Locale.getDefault())
      {
        localObject = ResourceBundle.getBundle("org.mozilla.javascript.resources.Messages", (Locale)localObject);
        try
        {
          localObject = ((ResourceBundle)localObject).getString(paramString);
          return new MessageFormat((String)localObject).format(paramArrayOfObject);
        }
        catch (MissingResourceException paramArrayOfObject)
        {
          throw new RuntimeException("no message resource found for message property " + paramString);
        }
      }
    }
  }
  
  private static class IdEnumeration
    implements Serializable
  {
    private static final long serialVersionUID = 1L;
    Object currentId;
    boolean enumNumbers;
    int enumType;
    Object[] ids;
    int index;
    Scriptable iterator;
    Scriptable obj;
    ObjToIntMap used;
  }
  
  public static abstract interface MessageProvider
  {
    public abstract String getMessage(String paramString, Object[] paramArrayOfObject);
  }
  
  static class NoSuchMethodShim
    implements Callable
  {
    String methodName;
    Callable noSuchMethodMethod;
    
    NoSuchMethodShim(Callable paramCallable, String paramString)
    {
      this.noSuchMethodMethod = paramCallable;
      this.methodName = paramString;
    }
    
    public Object call(Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject)
    {
      String str = this.methodName;
      paramArrayOfObject = ScriptRuntime.newArrayLiteral(paramArrayOfObject, null, paramContext, paramScriptable1);
      return this.noSuchMethodMethod.call(paramContext, paramScriptable1, paramScriptable2, new Object[] { str, paramArrayOfObject });
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\ScriptRuntime.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */