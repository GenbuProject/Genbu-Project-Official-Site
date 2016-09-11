package org.mozilla.javascript;

public class NativeJavaTopPackage
  extends NativeJavaPackage
  implements Function, IdFunctionCall
{
  private static final Object FTAG = "JavaTopPackage";
  private static final int Id_getClass = 1;
  private static final String[][] commonPackages;
  static final long serialVersionUID = -1455787259477709999L;
  
  static
  {
    String[] arrayOfString1 = { "java", "lang", "reflect" };
    String[] arrayOfString2 = { "java", "math" };
    String[] arrayOfString3 = { "java", "net" };
    String[] arrayOfString4 = { "java", "applet" };
    String[] arrayOfString5 = { "javax", "swing" };
    commonPackages = new String[][] { arrayOfString1, { "java", "io" }, arrayOfString2, arrayOfString3, { "java", "util", "zip" }, { "java", "text", "resources" }, arrayOfString4, arrayOfString5 };
  }
  
  NativeJavaTopPackage(ClassLoader paramClassLoader)
  {
    super(true, "", paramClassLoader);
  }
  
  public static void init(Context paramContext, Scriptable paramScriptable, boolean paramBoolean)
  {
    int k = 0;
    NativeJavaTopPackage localNativeJavaTopPackage = new NativeJavaTopPackage(paramContext.getApplicationClassLoader());
    localNativeJavaTopPackage.setPrototype(getObjectPrototype(paramScriptable));
    localNativeJavaTopPackage.setParentScope(paramScriptable);
    int i = 0;
    while (i != commonPackages.length)
    {
      int j = 0;
      paramContext = localNativeJavaTopPackage;
      while (j != commonPackages[i].length)
      {
        paramContext = paramContext.forcePackage(commonPackages[i][j], paramScriptable);
        j += 1;
      }
      i += 1;
    }
    paramContext = new IdFunctionObject(localNativeJavaTopPackage, FTAG, 1, "getClass", 1, paramScriptable);
    String[] arrayOfString = ScriptRuntime.getTopPackageNames();
    NativeJavaPackage[] arrayOfNativeJavaPackage = new NativeJavaPackage[arrayOfString.length];
    i = 0;
    while (i < arrayOfString.length)
    {
      arrayOfNativeJavaPackage[i] = ((NativeJavaPackage)localNativeJavaTopPackage.get(arrayOfString[i], localNativeJavaTopPackage));
      i += 1;
    }
    paramScriptable = (ScriptableObject)paramScriptable;
    if (paramBoolean) {
      paramContext.sealObject();
    }
    paramContext.exportAsScopeProperty();
    paramScriptable.defineProperty("Packages", localNativeJavaTopPackage, 2);
    i = k;
    while (i < arrayOfString.length)
    {
      paramScriptable.defineProperty(arrayOfString[i], arrayOfNativeJavaPackage[i], 2);
      i += 1;
    }
  }
  
  private Scriptable js_getClass(Context paramContext, Scriptable paramScriptable, Object[] paramArrayOfObject)
  {
    int i;
    if ((paramArrayOfObject.length > 0) && ((paramArrayOfObject[0] instanceof Wrapper)))
    {
      paramArrayOfObject = ((Wrapper)paramArrayOfObject[0]).unwrap().getClass().getName();
      i = 0;
      paramContext = this;
    }
    for (;;)
    {
      int j = paramArrayOfObject.indexOf('.', i);
      if (j == -1) {}
      for (paramScriptable = paramArrayOfObject.substring(i);; paramScriptable = paramArrayOfObject.substring(i, j))
      {
        paramContext = paramContext.get(paramScriptable, paramContext);
        if ((paramContext instanceof Scriptable)) {
          break;
        }
        throw Context.reportRuntimeError0("msg.not.java.obj");
      }
      paramContext = (Scriptable)paramContext;
      if (j == -1) {
        return paramContext;
      }
      i = j + 1;
    }
  }
  
  public Object call(Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject)
  {
    return construct(paramContext, paramScriptable1, paramArrayOfObject);
  }
  
  public Scriptable construct(Context paramContext, Scriptable paramScriptable, Object[] paramArrayOfObject)
  {
    if (paramArrayOfObject.length != 0)
    {
      paramArrayOfObject = paramArrayOfObject[0];
      paramContext = paramArrayOfObject;
      if ((paramArrayOfObject instanceof Wrapper)) {
        paramContext = ((Wrapper)paramArrayOfObject).unwrap();
      }
      if (!(paramContext instanceof ClassLoader)) {}
    }
    for (paramContext = (ClassLoader)paramContext;; paramContext = null)
    {
      if (paramContext == null)
      {
        Context.reportRuntimeError0("msg.not.classloader");
        return null;
      }
      paramContext = new NativeJavaPackage(true, "", paramContext);
      ScriptRuntime.setObjectProtoAndParent(paramContext, paramScriptable);
      return paramContext;
    }
  }
  
  public Object execIdCall(IdFunctionObject paramIdFunctionObject, Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject)
  {
    if ((paramIdFunctionObject.hasTag(FTAG)) && (paramIdFunctionObject.methodId() == 1)) {
      return js_getClass(paramContext, paramScriptable1, paramArrayOfObject);
    }
    throw paramIdFunctionObject.unknown();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\NativeJavaTopPackage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */