package org.mozilla.javascript.ast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.mozilla.javascript.EvaluatorException;

public class ErrorCollector
  implements IdeErrorReporter
{
  private List<ParseProblem> errors = new ArrayList();
  
  public void error(String paramString1, String paramString2, int paramInt1, int paramInt2)
  {
    this.errors.add(new ParseProblem(ParseProblem.Type.Error, paramString1, paramString2, paramInt1, paramInt2));
  }
  
  public void error(String paramString1, String paramString2, int paramInt1, String paramString3, int paramInt2)
  {
    throw new UnsupportedOperationException();
  }
  
  public List<ParseProblem> getErrors()
  {
    return this.errors;
  }
  
  public EvaluatorException runtimeError(String paramString1, String paramString2, int paramInt1, String paramString3, int paramInt2)
  {
    throw new UnsupportedOperationException();
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(this.errors.size() * 100);
    Iterator localIterator = this.errors.iterator();
    while (localIterator.hasNext()) {
      localStringBuilder.append(((ParseProblem)localIterator.next()).toString()).append("\n");
    }
    return localStringBuilder.toString();
  }
  
  public void warning(String paramString1, String paramString2, int paramInt1, int paramInt2)
  {
    this.errors.add(new ParseProblem(ParseProblem.Type.Warning, paramString1, paramString2, paramInt1, paramInt2));
  }
  
  public void warning(String paramString1, String paramString2, int paramInt1, String paramString3, int paramInt2)
  {
    throw new UnsupportedOperationException();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\ast\ErrorCollector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */