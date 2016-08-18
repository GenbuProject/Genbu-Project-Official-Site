package org.simpleframework.xml.core;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class ConstructorScanner
{
  private Signature primary;
  private ParameterMap registry = new ParameterMap();
  private List<Signature> signatures = new ArrayList();
  private Support support;
  
  public ConstructorScanner(Detail paramDetail, Support paramSupport)
    throws Exception
  {
    this.support = paramSupport;
    scan(paramDetail);
  }
  
  private void scan(Constructor paramConstructor)
    throws Exception
  {
    paramConstructor = new SignatureScanner(paramConstructor, this.registry, this.support);
    if (paramConstructor.isValid())
    {
      paramConstructor = paramConstructor.getSignatures().iterator();
      while (paramConstructor.hasNext())
      {
        Signature localSignature = (Signature)paramConstructor.next();
        if (localSignature.size() == 0) {
          this.primary = localSignature;
        }
        this.signatures.add(localSignature);
      }
    }
  }
  
  private void scan(Detail paramDetail)
    throws Exception
  {
    Constructor[] arrayOfConstructor = paramDetail.getConstructors();
    if (!paramDetail.isInstantiable()) {
      throw new ConstructorException("Can not construct inner %s", new Object[] { paramDetail });
    }
    int j = arrayOfConstructor.length;
    int i = 0;
    while (i < j)
    {
      Constructor localConstructor = arrayOfConstructor[i];
      if (!paramDetail.isPrimitive()) {
        scan(localConstructor);
      }
      i += 1;
    }
  }
  
  public ParameterMap getParameters()
  {
    return this.registry;
  }
  
  public Signature getSignature()
  {
    return this.primary;
  }
  
  public List<Signature> getSignatures()
  {
    return new ArrayList(this.signatures);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\ConstructorScanner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */