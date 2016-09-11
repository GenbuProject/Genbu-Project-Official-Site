package com.microsoft.cll.android;

import com.microsoft.telemetry.IJsonSerializable;
import java.util.List;

public abstract interface IStorage
{
  public abstract void add(Tuple<String, List<String>> paramTuple)
    throws Exception;
  
  public abstract void add(IJsonSerializable paramIJsonSerializable)
    throws Exception;
  
  public abstract boolean canAdd(Tuple<String, List<String>> paramTuple);
  
  public abstract boolean canAdd(IJsonSerializable paramIJsonSerializable);
  
  public abstract void close();
  
  public abstract void discard();
  
  public abstract List<Tuple<String, List<String>>> drain();
  
  public abstract long size();
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\cll\android\IStorage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */