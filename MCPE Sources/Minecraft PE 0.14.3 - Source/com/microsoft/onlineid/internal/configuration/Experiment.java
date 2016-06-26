package com.microsoft.onlineid.internal.configuration;

import android.text.TextUtils;

public enum Experiment
{
  QRCodeExperiment("MSAClient_Experiment1");
  
  private final String _experimentName;
  
  private Experiment(String paramString)
  {
    this._experimentName = paramString;
  }
  
  public static String getExperimentList()
  {
    return TextUtils.join(",", values());
  }
  
  public String getName()
  {
    return this._experimentName;
  }
  
  public String toString()
  {
    return this._experimentName;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\internal\configuration\Experiment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */