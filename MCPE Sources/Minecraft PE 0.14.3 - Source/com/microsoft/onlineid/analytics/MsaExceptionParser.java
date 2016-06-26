package com.microsoft.onlineid.analytics;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.analytics.StandardExceptionParser;
import com.microsoft.onlineid.sts.StsError;
import com.microsoft.onlineid.sts.exception.StsException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MsaExceptionParser
  extends StandardExceptionParser
{
  public MsaExceptionParser(Context paramContext, Collection<String> paramCollection)
  {
    super(paramContext, paramCollection);
  }
  
  private String getStackLocationDescription(StackTraceElement paramStackTraceElement)
  {
    Object localObject = paramStackTraceElement.getClassName();
    int i = ((String)localObject).lastIndexOf('.');
    if (i >= 0) {
      localObject = ((String)localObject).substring(i + 1);
    }
    for (;;)
    {
      String str = (String)localObject + ":" + paramStackTraceElement.getMethodName();
      localObject = str;
      if (paramStackTraceElement.getLineNumber() > 0) {
        localObject = str + ":" + paramStackTraceElement.getLineNumber();
      }
      return "(@" + (String)localObject + ")";
    }
  }
  
  protected String getDescription(Throwable paramThrowable, StackTraceElement paramStackTraceElement, String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(paramThrowable.getClass().getSimpleName());
    if ((paramThrowable instanceof StsException))
    {
      paramThrowable = ((StsException)paramThrowable).getError();
      if (paramThrowable != null) {
        localArrayList.add("[" + paramThrowable.getOriginalErrorMessage() + "]");
      }
    }
    localArrayList.add(getStackLocationDescription(paramStackTraceElement));
    localArrayList.add("{" + paramString + "}");
    return TextUtils.join(" ", localArrayList);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\analytics\MsaExceptionParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */