package com.microsoft.onlineid;

import android.os.Bundle;
import com.microsoft.onlineid.internal.Objects;

public abstract class RequestOptions<B extends RequestOptions<B>>
{
  protected final Bundle _values;
  
  protected RequestOptions()
  {
    this(new Bundle());
  }
  
  protected RequestOptions(Bundle paramBundle)
  {
    Objects.verifyArgumentNotNull(paramBundle, "bundle");
    this._values = paramBundle;
  }
  
  public Bundle asBundle()
  {
    return this._values;
  }
  
  public String getFlightConfiguration()
  {
    return this._values.getString("com.microsoft.onlineid.client_flights");
  }
  
  public String getPrefillUsername()
  {
    return this._values.getString("com.microsoft.onlineid.prefill_username");
  }
  
  public String getUnauthenticatedSessionId()
  {
    return this._values.getString("com.microsoft.onlineid.unauth_session_id");
  }
  
  public boolean getWasPrecachingEnabled()
  {
    return this._values.getBoolean("com.microsoft.onlineid.web_telemetry_precaching_enabled", false);
  }
  
  public B setFlightConfiguration(String paramString)
  {
    this._values.putString("com.microsoft.onlineid.client_flights", paramString);
    return this;
  }
  
  public B setPrefillUsername(String paramString)
  {
    this._values.putString("com.microsoft.onlineid.prefill_username", paramString);
    return this;
  }
  
  public B setUnauthenticatedSessionId(String paramString)
  {
    this._values.putString("com.microsoft.onlineid.unauth_session_id", paramString);
    return this;
  }
  
  public B setWasPrecachingEnabled(boolean paramBoolean)
  {
    this._values.putBoolean("com.microsoft.onlineid.web_telemetry_precaching_enabled", paramBoolean);
    return this;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\onlineid\RequestOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */