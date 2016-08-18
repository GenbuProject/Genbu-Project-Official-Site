package org.simpleframework.xml.core;

class Caller
{
  private final Function commit;
  private final Function complete;
  private final Context context;
  private final Function persist;
  private final Function replace;
  private final Function resolve;
  private final Function validate;
  
  public Caller(Scanner paramScanner, Context paramContext)
  {
    this.validate = paramScanner.getValidate();
    this.complete = paramScanner.getComplete();
    this.replace = paramScanner.getReplace();
    this.resolve = paramScanner.getResolve();
    this.persist = paramScanner.getPersist();
    this.commit = paramScanner.getCommit();
    this.context = paramContext;
  }
  
  public void commit(Object paramObject)
    throws Exception
  {
    if (this.commit != null) {
      this.commit.call(this.context, paramObject);
    }
  }
  
  public void complete(Object paramObject)
    throws Exception
  {
    if (this.complete != null) {
      this.complete.call(this.context, paramObject);
    }
  }
  
  public void persist(Object paramObject)
    throws Exception
  {
    if (this.persist != null) {
      this.persist.call(this.context, paramObject);
    }
  }
  
  public Object replace(Object paramObject)
    throws Exception
  {
    Object localObject = paramObject;
    if (this.replace != null) {
      localObject = this.replace.call(this.context, paramObject);
    }
    return localObject;
  }
  
  public Object resolve(Object paramObject)
    throws Exception
  {
    Object localObject = paramObject;
    if (this.resolve != null) {
      localObject = this.resolve.call(this.context, paramObject);
    }
    return localObject;
  }
  
  public void validate(Object paramObject)
    throws Exception
  {
    if (this.validate != null) {
      this.validate.call(this.context, paramObject);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\Caller.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */