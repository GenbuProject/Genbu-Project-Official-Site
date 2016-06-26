package com.microsoft.onlineid.internal.log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RedactableXml
  implements IRedactable
{
  private static final Pattern GenericNodePattern = Pattern.compile("<([^<> ]+)([^<>]*)>([^<>]+)</\\1[^>]*>");
  private final String[] _tagsToKeep;
  private final String _unredactedXml;
  
  public RedactableXml(String paramString, String... paramVarArgs)
  {
    this._unredactedXml = paramString;
    this._tagsToKeep = paramVarArgs;
  }
  
  private boolean isInApprovedList(String paramString)
  {
    boolean bool2 = false;
    String[] arrayOfString = this._tagsToKeep;
    int j = arrayOfString.length;
    int i = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (i < j)
      {
        if (arrayOfString[i].equalsIgnoreCase(paramString)) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i += 1;
    }
  }
  
  public String getRedactedString()
  {
    Matcher localMatcher = GenericNodePattern.matcher(this._unredactedXml);
    StringBuffer localStringBuffer = new StringBuffer();
    while (localMatcher.find()) {
      if (!isInApprovedList(localMatcher.group(1))) {
        localMatcher.appendReplacement(localStringBuffer, "<$1$2>" + Redactor.redactString(localMatcher.group(3)) + "</$1>");
      }
    }
    localMatcher.appendTail(localStringBuffer);
    return localStringBuffer.toString();
  }
  
  public String getUnredactedString()
  {
    return this._unredactedXml;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\internal\log\RedactableXml.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */