package com.microsoft.onlineid.userdata;

import android.content.Context;
import android.text.TextUtils;
import com.microsoft.onlineid.internal.Assertion;

public class SignUpData
{
  private final AccountManagerReader _accountManagerReader;
  private final MeContactReader.FullName _fullName;
  private final MeContactReader _meContactReader;
  private final TelephonyManagerReader _telephonyManagerReader;
  
  public SignUpData(Context paramContext)
  {
    this(new TelephonyManagerReader(paramContext), new AccountManagerReader(paramContext), new MeContactReader(paramContext));
  }
  
  SignUpData(TelephonyManagerReader paramTelephonyManagerReader, AccountManagerReader paramAccountManagerReader, MeContactReader paramMeContactReader)
  {
    this._telephonyManagerReader = paramTelephonyManagerReader;
    this._accountManagerReader = paramAccountManagerReader;
    this._meContactReader = paramMeContactReader;
    this._fullName = this._meContactReader.getFullName();
    if (this._fullName != null) {}
    for (boolean bool = true;; bool = false)
    {
      Assertion.check(bool);
      return;
    }
  }
  
  public String getCountryCode()
  {
    return this._telephonyManagerReader.getIsoCountryCode();
  }
  
  public String getDeviceEmail()
  {
    return this._accountManagerReader.getDeviceEmail();
  }
  
  public String getFirstName()
  {
    return this._fullName.getFirstName();
  }
  
  public String getLastName()
  {
    return this._fullName.getLastName();
  }
  
  public String getPhone()
  {
    String str2 = this._telephonyManagerReader.getPhoneNumber();
    String str1 = str2;
    if (TextUtils.isEmpty(str2)) {
      str1 = this._meContactReader.getPhoneNumber();
    }
    return str1;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\userdata\SignUpData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */