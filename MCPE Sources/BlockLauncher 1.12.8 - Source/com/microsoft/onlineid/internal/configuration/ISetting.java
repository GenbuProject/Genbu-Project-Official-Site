package com.microsoft.onlineid.internal.configuration;

public abstract interface ISetting<T>
{
  public abstract T getDefaultValue();
  
  public abstract String getSettingName();
}


/* Location:              C:\Users\Genbu Hase\ドキュメント\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\internal\configuration\ISetting.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */