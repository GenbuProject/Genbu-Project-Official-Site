package com.microsoft.onlineid;

import android.os.Bundle;
import com.microsoft.onlineid.internal.Objects;

public class SignInOptions
{
  private final Bundle _values;
  
  public SignInOptions()
  {
    this._values = new Bundle();
  }
  
  public SignInOptions(Bundle paramBundle)
  {
    Objects.verifyArgumentNotNull(paramBundle, "bundle");
    this._values = paramBundle;
  }
  
  public Bundle asBundle()
  {
    return this._values;
  }
  
  public String getPrefillUsername()
  {
    return this._values.getString("com.microsoft.onlineid.prefill_username");
  }
  
  public SignInOptions setPrefillUsername(String paramString)
  {
    this._values.putString("com.microsoft.onlineid.prefill_username", paramString);
    return this;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\SignInOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */