package org.mozilla.javascript.xml;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.IdScriptableObject;
import org.mozilla.javascript.NativeWith;
import org.mozilla.javascript.Ref;
import org.mozilla.javascript.Scriptable;

public abstract class XMLObject
  extends IdScriptableObject
{
  static final long serialVersionUID = 8455156490438576500L;
  
  public XMLObject() {}
  
  public XMLObject(Scriptable paramScriptable1, Scriptable paramScriptable2)
  {
    super(paramScriptable1, paramScriptable2);
  }
  
  public Object addValues(Context paramContext, boolean paramBoolean, Object paramObject)
  {
    return Scriptable.NOT_FOUND;
  }
  
  public abstract boolean delete(Context paramContext, Object paramObject);
  
  public abstract NativeWith enterDotQuery(Scriptable paramScriptable);
  
  public abstract NativeWith enterWith(Scriptable paramScriptable);
  
  public abstract Object get(Context paramContext, Object paramObject);
  
  public abstract Scriptable getExtraMethodSource(Context paramContext);
  
  public abstract Object getFunctionProperty(Context paramContext, int paramInt);
  
  public abstract Object getFunctionProperty(Context paramContext, String paramString);
  
  public String getTypeOf()
  {
    if (avoidObjectDetection()) {
      return "undefined";
    }
    return "xml";
  }
  
  public abstract boolean has(Context paramContext, Object paramObject);
  
  public abstract Ref memberRef(Context paramContext, Object paramObject, int paramInt);
  
  public abstract Ref memberRef(Context paramContext, Object paramObject1, Object paramObject2, int paramInt);
  
  public abstract void put(Context paramContext, Object paramObject1, Object paramObject2);
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\xml\XMLObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */