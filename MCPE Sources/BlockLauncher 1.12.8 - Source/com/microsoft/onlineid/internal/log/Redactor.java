package com.microsoft.onlineid.internal.log;

import java.util.Locale;

public class Redactor
{
  protected static final String RedactedStringEmptyReplacement = "";
  protected static final String RedactedStringNullReplacement = "(null)";
  protected static final String RedactedStringReplacement = "*(%d)*";
  protected static final String RedactedStringStarReplacement = "***";
  
  private static String doRedact(String paramString, RedactionType paramRedactionType)
  {
    if (paramString == null) {
      return "(null)";
    }
    if (paramString.isEmpty() == true) {
      return "";
    }
    switch (paramRedactionType)
    {
    default: 
      return "***";
    case ???: 
      return String.format(Locale.getDefault(), "*(%d)*", new Object[] { Integer.valueOf(paramString.length()) });
    case ???: 
      return "***";
    }
    return String.format(Locale.getDefault(), "*(%d)*", new Object[] { Integer.valueOf(paramString.length()) });
  }
  
  public static String redactEmail(String paramString)
  {
    return doRedact(paramString, RedactionType.Email);
  }
  
  public static String redactPassword(String paramString)
  {
    return doRedact(paramString, RedactionType.Password);
  }
  
  public static String redactString(String paramString)
  {
    return doRedact(paramString, RedactionType.String);
  }
  
  public static boolean shouldRedact()
  {
    return Logger.shouldRedact();
  }
  
  private static enum RedactionType
  {
    Email,  Password,  String;
    
    private RedactionType() {}
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\internal\log\Redactor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */