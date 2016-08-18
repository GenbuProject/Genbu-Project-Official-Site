package com.microsoft.onlineid.internal.ui;

public abstract interface IWebPropertyProvider
{
  public abstract String getProperty(PropertyBag.Key paramKey);
  
  public abstract boolean handlesProperty(PropertyBag.Key paramKey);
  
  public abstract void setProperty(PropertyBag.Key paramKey, String paramString);
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\onlineid\internal\ui\IWebPropertyProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */