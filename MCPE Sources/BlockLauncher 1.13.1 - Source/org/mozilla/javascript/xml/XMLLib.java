package org.mozilla.javascript.xml;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Ref;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

public abstract class XMLLib
{
  private static final Object XML_LIB_KEY = new Object();
  
  public static XMLLib extractFromScope(Scriptable paramScriptable)
  {
    paramScriptable = extractFromScopeOrNull(paramScriptable);
    if (paramScriptable != null) {
      return paramScriptable;
    }
    throw Context.reportRuntimeError(ScriptRuntime.getMessage0("msg.XML.not.available"));
  }
  
  public static XMLLib extractFromScopeOrNull(Scriptable paramScriptable)
  {
    paramScriptable = ScriptRuntime.getLibraryScopeOrNull(paramScriptable);
    if (paramScriptable == null) {
      return null;
    }
    ScriptableObject.getProperty(paramScriptable, "XML");
    return (XMLLib)paramScriptable.getAssociatedValue(XML_LIB_KEY);
  }
  
  protected final XMLLib bindToScope(Scriptable paramScriptable)
  {
    paramScriptable = ScriptRuntime.getLibraryScopeOrNull(paramScriptable);
    if (paramScriptable == null) {
      throw new IllegalStateException();
    }
    return (XMLLib)paramScriptable.associateValue(XML_LIB_KEY, this);
  }
  
  public abstract String escapeAttributeValue(Object paramObject);
  
  public abstract String escapeTextValue(Object paramObject);
  
  public int getPrettyIndent()
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean isIgnoreComments()
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean isIgnoreProcessingInstructions()
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean isIgnoreWhitespace()
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean isPrettyPrinting()
  {
    throw new UnsupportedOperationException();
  }
  
  public abstract boolean isXMLName(Context paramContext, Object paramObject);
  
  public abstract Ref nameRef(Context paramContext, Object paramObject1, Object paramObject2, Scriptable paramScriptable, int paramInt);
  
  public abstract Ref nameRef(Context paramContext, Object paramObject, Scriptable paramScriptable, int paramInt);
  
  public void setIgnoreComments(boolean paramBoolean)
  {
    throw new UnsupportedOperationException();
  }
  
  public void setIgnoreProcessingInstructions(boolean paramBoolean)
  {
    throw new UnsupportedOperationException();
  }
  
  public void setIgnoreWhitespace(boolean paramBoolean)
  {
    throw new UnsupportedOperationException();
  }
  
  public void setPrettyIndent(int paramInt)
  {
    throw new UnsupportedOperationException();
  }
  
  public void setPrettyPrinting(boolean paramBoolean)
  {
    throw new UnsupportedOperationException();
  }
  
  public abstract Object toDefaultXmlNamespace(Context paramContext, Object paramObject);
  
  public static abstract class Factory
  {
    public static Factory create(String paramString)
    {
      new Factory()
      {
        public String getImplementationClassName()
        {
          return this.val$className;
        }
      };
    }
    
    public abstract String getImplementationClassName();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\xml\XMLLib.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */