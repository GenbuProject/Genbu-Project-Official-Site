package org.simpleframework.xml.core;

import org.simpleframework.xml.DefaultType;
import org.simpleframework.xml.util.Cache;
import org.simpleframework.xml.util.ConcurrentCache;

class DetailExtractor
{
  private final Cache<Detail> details = new ConcurrentCache();
  private final Cache<ContactList> fields = new ConcurrentCache();
  private final Cache<ContactList> methods = new ConcurrentCache();
  private final DefaultType override;
  private final Support support;
  
  public DetailExtractor(Support paramSupport)
  {
    this(paramSupport, null);
  }
  
  public DetailExtractor(Support paramSupport, DefaultType paramDefaultType)
  {
    this.override = paramDefaultType;
    this.support = paramSupport;
  }
  
  private ContactList getFields(Class paramClass, Detail paramDetail)
    throws Exception
  {
    FieldScanner localFieldScanner = new FieldScanner(paramDetail, this.support);
    if (paramDetail != null) {
      this.fields.cache(paramClass, localFieldScanner);
    }
    return localFieldScanner;
  }
  
  private ContactList getMethods(Class paramClass, Detail paramDetail)
    throws Exception
  {
    MethodScanner localMethodScanner = new MethodScanner(paramDetail, this.support);
    if (paramDetail != null) {
      this.methods.cache(paramClass, localMethodScanner);
    }
    return localMethodScanner;
  }
  
  public Detail getDetail(Class paramClass)
  {
    Detail localDetail = (Detail)this.details.fetch(paramClass);
    Object localObject = localDetail;
    if (localDetail == null)
    {
      localObject = new DetailScanner(paramClass, this.override);
      this.details.cache(paramClass, localObject);
    }
    return (Detail)localObject;
  }
  
  public ContactList getFields(Class paramClass)
    throws Exception
  {
    ContactList localContactList2 = (ContactList)this.fields.fetch(paramClass);
    ContactList localContactList1 = localContactList2;
    if (localContactList2 == null)
    {
      Detail localDetail = getDetail(paramClass);
      localContactList1 = localContactList2;
      if (localDetail != null) {
        localContactList1 = getFields(paramClass, localDetail);
      }
    }
    return localContactList1;
  }
  
  public ContactList getMethods(Class paramClass)
    throws Exception
  {
    ContactList localContactList2 = (ContactList)this.methods.fetch(paramClass);
    ContactList localContactList1 = localContactList2;
    if (localContactList2 == null)
    {
      Detail localDetail = getDetail(paramClass);
      localContactList1 = localContactList2;
      if (localDetail != null) {
        localContactList1 = getMethods(paramClass, localDetail);
      }
    }
    return localContactList1;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\DetailExtractor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */