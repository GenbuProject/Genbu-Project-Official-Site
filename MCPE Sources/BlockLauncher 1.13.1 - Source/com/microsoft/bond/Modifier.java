package com.microsoft.bond;

public enum Modifier
{
  Optional(0),  Required(1),  RequiredOptional(2),  __INVALID_ENUM_VALUE(3);
  
  private final int value;
  
  private Modifier(int paramInt)
  {
    this.value = paramInt;
  }
  
  public static Modifier fromValue(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return __INVALID_ENUM_VALUE;
    case 0: 
      return Optional;
    case 1: 
      return Required;
    }
    return RequiredOptional;
  }
  
  public int getValue()
  {
    return this.value;
  }
}


/* Location:              C:\Users\Genbu Hase\ドキュメント\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\bond\Modifier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */