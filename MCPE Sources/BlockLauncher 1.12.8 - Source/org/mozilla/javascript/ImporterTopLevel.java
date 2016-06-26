package org.mozilla.javascript;

public class ImporterTopLevel
  extends TopLevel
{
  private static final Object IMPORTER_TAG = "Importer";
  private static final int Id_constructor = 1;
  private static final int Id_importClass = 2;
  private static final int Id_importPackage = 3;
  private static final int MAX_PROTOTYPE_ID = 3;
  static final long serialVersionUID = -9095380847465315412L;
  private ObjArray importedPackages = new ObjArray();
  private boolean topScopeFlag;
  
  public ImporterTopLevel() {}
  
  public ImporterTopLevel(Context paramContext)
  {
    this(paramContext, false);
  }
  
  public ImporterTopLevel(Context paramContext, boolean paramBoolean)
  {
    initStandardObjects(paramContext, paramBoolean);
  }
  
  private Object getPackageProperty(String paramString, Scriptable paramScriptable)
  {
    Object localObject1 = NOT_FOUND;
    Object localObject3;
    synchronized (this.importedPackages)
    {
      Object[] arrayOfObject = this.importedPackages.toArray();
      int i = 0;
      if (i >= arrayOfObject.length) {
        break label116;
      }
      localObject3 = ((NativeJavaPackage)arrayOfObject[i]).getPkgProperty(paramString, paramScriptable, false);
      ??? = localObject1;
      if (localObject3 != null)
      {
        ??? = localObject1;
        if (!(localObject3 instanceof NativeJavaPackage))
        {
          if (localObject1 != NOT_FOUND) {
            break label100;
          }
          ??? = localObject3;
        }
      }
      i += 1;
      localObject1 = ???;
    }
    label100:
    throw Context.reportRuntimeError2("msg.ambig.import", localObject1.toString(), localObject3.toString());
    label116:
    return localObject1;
  }
  
  private void importClass(NativeJavaClass paramNativeJavaClass)
  {
    String str = paramNativeJavaClass.getClassObject().getName();
    str = str.substring(str.lastIndexOf('.') + 1);
    Object localObject = get(str, this);
    if ((localObject != NOT_FOUND) && (localObject != paramNativeJavaClass)) {
      throw Context.reportRuntimeError1("msg.prop.defined", str);
    }
    put(str, this, paramNativeJavaClass);
  }
  
  private void importPackage(NativeJavaPackage paramNativeJavaPackage)
  {
    if (paramNativeJavaPackage == null) {
      return;
    }
    ObjArray localObjArray = this.importedPackages;
    int i = 0;
    for (;;)
    {
      try
      {
        if (i == this.importedPackages.size()) {
          break;
        }
        if (paramNativeJavaPackage.equals(this.importedPackages.get(i))) {
          return;
        }
      }
      finally {}
      i += 1;
    }
    this.importedPackages.add(paramNativeJavaPackage);
  }
  
  public static void init(Context paramContext, Scriptable paramScriptable, boolean paramBoolean)
  {
    new ImporterTopLevel().exportAsJSClass(3, paramScriptable, paramBoolean);
  }
  
  private Object js_construct(Scriptable paramScriptable, Object[] paramArrayOfObject)
  {
    ImporterTopLevel localImporterTopLevel = new ImporterTopLevel();
    int i = 0;
    if (i != paramArrayOfObject.length)
    {
      Object localObject = paramArrayOfObject[i];
      if ((localObject instanceof NativeJavaClass)) {
        localImporterTopLevel.importClass((NativeJavaClass)localObject);
      }
      for (;;)
      {
        i += 1;
        break;
        if (!(localObject instanceof NativeJavaPackage)) {
          break label68;
        }
        localImporterTopLevel.importPackage((NativeJavaPackage)localObject);
      }
      label68:
      throw Context.reportRuntimeError1("msg.not.class.not.pkg", Context.toString(localObject));
    }
    localImporterTopLevel.setParentScope(paramScriptable);
    localImporterTopLevel.setPrototype(this);
    return localImporterTopLevel;
  }
  
  private Object js_importClass(Object[] paramArrayOfObject)
  {
    int i = 0;
    while (i != paramArrayOfObject.length)
    {
      Object localObject = paramArrayOfObject[i];
      if (!(localObject instanceof NativeJavaClass)) {
        throw Context.reportRuntimeError1("msg.not.class", Context.toString(localObject));
      }
      importClass((NativeJavaClass)localObject);
      i += 1;
    }
    return Undefined.instance;
  }
  
  private Object js_importPackage(Object[] paramArrayOfObject)
  {
    int i = 0;
    while (i != paramArrayOfObject.length)
    {
      Object localObject = paramArrayOfObject[i];
      if (!(localObject instanceof NativeJavaPackage)) {
        throw Context.reportRuntimeError1("msg.not.pkg", Context.toString(localObject));
      }
      importPackage((NativeJavaPackage)localObject);
      i += 1;
    }
    return Undefined.instance;
  }
  
  private ImporterTopLevel realThis(Scriptable paramScriptable, IdFunctionObject paramIdFunctionObject)
  {
    if (this.topScopeFlag) {
      return this;
    }
    if (!(paramScriptable instanceof ImporterTopLevel)) {
      throw incompatibleCallError(paramIdFunctionObject);
    }
    return (ImporterTopLevel)paramScriptable;
  }
  
  public Object execIdCall(IdFunctionObject paramIdFunctionObject, Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject)
  {
    if (!paramIdFunctionObject.hasTag(IMPORTER_TAG)) {
      return super.execIdCall(paramIdFunctionObject, paramContext, paramScriptable1, paramScriptable2, paramArrayOfObject);
    }
    int i = paramIdFunctionObject.methodId();
    switch (i)
    {
    default: 
      throw new IllegalArgumentException(String.valueOf(i));
    case 1: 
      return js_construct(paramScriptable1, paramArrayOfObject);
    case 2: 
      return realThis(paramScriptable2, paramIdFunctionObject).js_importClass(paramArrayOfObject);
    }
    return realThis(paramScriptable2, paramIdFunctionObject).js_importPackage(paramArrayOfObject);
  }
  
  protected int findPrototypeId(String paramString)
  {
    int i = paramString.length();
    String str;
    if (i == 11)
    {
      i = paramString.charAt(0);
      if (i == 99)
      {
        i = 1;
        str = "constructor";
      }
    }
    for (;;)
    {
      if ((str != null) && (str != paramString) && (!str.equals(paramString)))
      {
        return 0;
        if (i == 105)
        {
          i = 2;
          str = "importClass";
          continue;
          if (i == 13)
          {
            i = 3;
            str = "importPackage";
          }
        }
      }
      else
      {
        return i;
      }
      str = null;
      i = 0;
    }
  }
  
  public Object get(String paramString, Scriptable paramScriptable)
  {
    Object localObject = super.get(paramString, paramScriptable);
    if (localObject != NOT_FOUND) {
      return localObject;
    }
    return getPackageProperty(paramString, paramScriptable);
  }
  
  public String getClassName()
  {
    if (this.topScopeFlag) {
      return "global";
    }
    return "JavaImporter";
  }
  
  public boolean has(String paramString, Scriptable paramScriptable)
  {
    return (super.has(paramString, paramScriptable)) || (getPackageProperty(paramString, paramScriptable) != NOT_FOUND);
  }
  
  @Deprecated
  public void importPackage(Context paramContext, Scriptable paramScriptable, Object[] paramArrayOfObject, Function paramFunction)
  {
    js_importPackage(paramArrayOfObject);
  }
  
  protected void initPrototypeId(int paramInt)
  {
    int i = 1;
    String str;
    switch (paramInt)
    {
    default: 
      throw new IllegalArgumentException(String.valueOf(paramInt));
    case 1: 
      i = 0;
      str = "constructor";
    }
    for (;;)
    {
      initPrototypeMethod(IMPORTER_TAG, paramInt, str, i);
      return;
      str = "importClass";
      continue;
      str = "importPackage";
    }
  }
  
  public void initStandardObjects(Context paramContext, boolean paramBoolean)
  {
    paramContext.initStandardObjects(this, paramBoolean);
    this.topScopeFlag = true;
    paramContext = exportAsJSClass(3, this, false);
    if (paramBoolean) {
      paramContext.sealObject();
    }
    delete("constructor");
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\ImporterTopLevel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */