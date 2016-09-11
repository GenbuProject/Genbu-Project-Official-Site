package org.mozilla.javascript;

public class WrappedException
  extends EvaluatorException
{
  static final long serialVersionUID = -1551979216966520648L;
  private Throwable exception;
  
  public WrappedException(Throwable paramThrowable)
  {
    super("Wrapped " + paramThrowable.toString());
    this.exception = paramThrowable;
    Kit.initCause(this, paramThrowable);
    paramThrowable = new int[1];
    paramThrowable[0] = 0;
    String str = Context.getSourcePositionFromStack(paramThrowable);
    int i = paramThrowable[0];
    if (str != null) {
      initSourceName(str);
    }
    if (i != 0) {
      initLineNumber(i);
    }
  }
  
  public Throwable getWrappedException()
  {
    return this.exception;
  }
  
  @Deprecated
  public Object unwrap()
  {
    return getWrappedException();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\WrappedException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */