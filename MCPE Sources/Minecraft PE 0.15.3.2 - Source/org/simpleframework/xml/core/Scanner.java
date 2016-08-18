package org.simpleframework.xml.core;

import java.util.List;
import org.simpleframework.xml.Order;
import org.simpleframework.xml.Version;

abstract interface Scanner
  extends Policy
{
  public abstract Caller getCaller(Context paramContext);
  
  public abstract Function getCommit();
  
  public abstract Function getComplete();
  
  public abstract Decorator getDecorator();
  
  public abstract Instantiator getInstantiator();
  
  public abstract String getName();
  
  public abstract Order getOrder();
  
  public abstract ParameterMap getParameters();
  
  public abstract Function getPersist();
  
  public abstract Function getReplace();
  
  public abstract Function getResolve();
  
  public abstract Version getRevision();
  
  public abstract Section getSection();
  
  public abstract Signature getSignature();
  
  public abstract List<Signature> getSignatures();
  
  public abstract Label getText();
  
  public abstract Class getType();
  
  public abstract Function getValidate();
  
  public abstract Label getVersion();
  
  public abstract boolean isEmpty();
  
  public abstract boolean isPrimitive();
  
  public abstract boolean isStrict();
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\Scanner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */