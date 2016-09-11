package com.microsoft.cll.android;

import java.util.Random;

public class CorrelationVector
{
  private final String base64CharSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
  private String baseVector;
  private int currentVector;
  private final int id0Length = 16;
  boolean isInitialized = false;
  
  private boolean CanExtend()
  {
    return (int)Math.floor(1.0D + Math.log10(this.currentVector)) + (this.baseVector.length() + 1) + 1 + 1 <= SettingsStore.getCllSettingsAsInt(SettingsStore.Settings.MAXCORRELATIONVECTORLENGTH);
  }
  
  private boolean CanIncrement(int paramInt)
  {
    if (paramInt - 1 == Integer.MAX_VALUE) {}
    while ((int)Math.floor(1.0D + Math.log10(paramInt)) + this.baseVector.length() + 1 > SettingsStore.getCllSettingsAsInt(SettingsStore.Settings.MAXCORRELATIONVECTORLENGTH)) {
      return false;
    }
    return true;
  }
  
  private String SeedCorrelationVector()
  {
    String str = "";
    Random localRandom = new Random();
    int i = 0;
    while (i < 16)
    {
      str = str + "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(localRandom.nextInt("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".length()));
      i += 1;
    }
    return str;
  }
  
  public String Extend()
  {
    try
    {
      if (!this.isInitialized) {
        Init();
      }
      if (CanExtend())
      {
        this.baseVector = GetValue();
        this.currentVector = 1;
      }
      String str = GetValue();
      return str;
    }
    finally {}
  }
  
  public String GetValue()
  {
    if (!this.isInitialized) {
      return null;
    }
    return this.baseVector + "." + this.currentVector;
  }
  
  public String Increment()
  {
    try
    {
      if (!this.isInitialized) {
        Init();
      }
      int i = this.currentVector + 1;
      if (CanIncrement(i)) {
        this.currentVector = i;
      }
      String str = GetValue();
      return str;
    }
    finally {}
  }
  
  public void Init()
  {
    this.baseVector = SeedCorrelationVector();
    this.currentVector = 1;
    this.isInitialized = true;
  }
  
  boolean IsValid(String paramString)
  {
    if (paramString.length() > SettingsStore.getCllSettingsAsInt(SettingsStore.Settings.MAXCORRELATIONVECTORLENGTH)) {}
    while (!paramString.matches("^[ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/]{16}(.[0-9]+)+$")) {
      return false;
    }
    return true;
  }
  
  public void SetValue(String paramString)
  {
    try
    {
      if (IsValid(paramString))
      {
        int i = paramString.lastIndexOf(".");
        this.baseVector = paramString.substring(0, i);
        this.currentVector = Integer.parseInt(paramString.substring(i + 1));
        this.isInitialized = true;
        return;
      }
      throw new IllegalArgumentException("Cannot set invalid correlation vector value");
    }
    finally {}
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\cll\android\CorrelationVector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */