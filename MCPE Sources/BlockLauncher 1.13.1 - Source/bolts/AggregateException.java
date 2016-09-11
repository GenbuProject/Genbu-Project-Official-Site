package bolts;

import java.util.List;

public class AggregateException
  extends Exception
{
  private static final long serialVersionUID = 1L;
  private List<Exception> errors;
  
  public AggregateException(List<Exception> paramList)
  {
    super("There were multiple errors.");
    this.errors = paramList;
  }
  
  public List<Exception> getErrors()
  {
    return this.errors;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\bolts\AggregateException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */