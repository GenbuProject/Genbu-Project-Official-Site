package com.microsoft.telemetry;

import java.io.IOException;
import java.io.Writer;

public abstract interface IJsonSerializable
{
  public abstract void serialize(Writer paramWriter)
    throws IOException;
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\telemetry\IJsonSerializable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */