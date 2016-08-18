package com.microsoft.onlineid.internal.log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RedactableLog
  implements IRedactable
{
  private static final Pattern RedactedPattern = Pattern.compile("*(%d)*".replace("*", "\\*").replace("(", "\\(").replace(")", "\\)").replace("%d", "\\d+"));
  private final Pattern[] _patternsToRedact;
  private final String _unredactedString;
  
  public RedactableLog(String paramString, Pattern... paramVarArgs)
  {
    this._unredactedString = paramString;
    this._patternsToRedact = paramVarArgs;
  }
  
  public String getRedactedString()
  {
    String str1 = this._unredactedString;
    Pattern[] arrayOfPattern = this._patternsToRedact;
    int k = arrayOfPattern.length;
    int i = 0;
    while (i < k)
    {
      Matcher localMatcher = arrayOfPattern[i].matcher(str1);
      if (localMatcher.find())
      {
        if (localMatcher.groupCount() == 0) {}
        for (int j = 0;; j = 1)
        {
          String str2 = localMatcher.group(j);
          if (RedactedPattern.matcher(str2).matches()) {
            break;
          }
          str1 = str1.replace(str2, Redactor.redactString(str2));
          break;
        }
      }
      i += 1;
    }
    return str1;
  }
  
  public String getUnredactedString()
  {
    return this._unredactedString;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\onlineid\internal\log\RedactableLog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */