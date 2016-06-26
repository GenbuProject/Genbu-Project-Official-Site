package com.microsoft.onlineid.internal.configuration;

public class Setting<T>
  implements ISetting<T>
{
  private final T _defaultValue;
  private final String _settingName;
  
  public Setting(String paramString, T paramT)
  {
    this._settingName = paramString;
    this._defaultValue = paramT;
  }
  
  public T getDefaultValue()
  {
    return (T)this._defaultValue;
  }
  
  public String getSettingName()
  {
    return this._settingName;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\internal\configuration\Setting.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */