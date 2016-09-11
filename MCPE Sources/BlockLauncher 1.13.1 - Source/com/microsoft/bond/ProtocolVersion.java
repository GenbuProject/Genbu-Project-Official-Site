package com.microsoft.bond;

public enum ProtocolVersion
{
  ONE(1),  TWO(2);
  
  private short value;
  
  private ProtocolVersion(int paramInt)
  {
    this.value = ((short)paramInt);
  }
  
  public static ProtocolVersion fromValue(short paramShort)
  {
    switch (paramShort)
    {
    default: 
      return null;
    case 1: 
      return ONE;
    }
    return TWO;
  }
  
  public short getValue()
  {
    return this.value;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\bond\ProtocolVersion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */