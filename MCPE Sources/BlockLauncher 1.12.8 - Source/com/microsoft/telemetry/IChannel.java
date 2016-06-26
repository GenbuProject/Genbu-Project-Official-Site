package com.microsoft.telemetry;

import java.util.Map;

public abstract interface IChannel
{
  public abstract void log(Base paramBase, Map<String, String> paramMap);
  
  public abstract void synchronize();
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\telemetry\IChannel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */