package com.microsoft.onlineid.userdata;

import android.content.Context;

public class MeContactReader
  implements IPhoneNumberReader
{
  public MeContactReader(Context paramContext) {}
  
  public FullName getFullName()
  {
    return new FullName(null, null);
  }
  
  public String getPhoneNumber()
  {
    return null;
  }
  
  public class FullName
  {
    public String _firstName;
    public String _lastName;
    
    public FullName(String paramString1, String paramString2)
    {
      this._firstName = paramString1;
      this._lastName = paramString2;
    }
    
    public String getFirstName()
    {
      return this._firstName;
    }
    
    public String getLastName()
    {
      return this._lastName;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\onlineid\userdata\MeContactReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */