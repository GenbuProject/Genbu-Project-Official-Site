package org.mozilla.javascript;

public class ContinuationPending
  extends RuntimeException
{
  private static final long serialVersionUID = 4956008116771118856L;
  private Object applicationState;
  private NativeContinuation continuationState;
  
  ContinuationPending(NativeContinuation paramNativeContinuation)
  {
    this.continuationState = paramNativeContinuation;
  }
  
  public Object getApplicationState()
  {
    return this.applicationState;
  }
  
  public Object getContinuation()
  {
    return this.continuationState;
  }
  
  NativeContinuation getContinuationState()
  {
    return this.continuationState;
  }
  
  public void setApplicationState(Object paramObject)
  {
    this.applicationState = paramObject;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\ContinuationPending.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */