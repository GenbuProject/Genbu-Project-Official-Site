package com.microsoft.onlineid.interop.xbox.jobs;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.util.HashSet;
import java.util.Set;

public class Settings
{
  private static final String SETTINGS_FILE = "com.microsoft.onlineid.xbox";
  private static final String STORAGE_ID_KEY = "msa.id";
  private static final String STORAGE_SIGNED_OUT_IDS_KEY = "msa.signed.out.ids";
  private final SharedPreferences preferences;
  
  public Settings(Context paramContext)
  {
    this.preferences = paramContext.getSharedPreferences("com.microsoft.onlineid.xbox", 0);
  }
  
  public void addKnownSignedOutAccount(String paramString)
  {
    Set localSet = this.preferences.getStringSet("msa.signed.out.ids", new HashSet());
    localSet.add(paramString);
    this.preferences.edit().putStringSet("msa.signed.out.ids", localSet).apply();
  }
  
  public void clearSavedId()
  {
    this.preferences.edit().remove("msa.id").apply();
  }
  
  public String getSavedId()
  {
    return this.preferences.getString("msa.id", null);
  }
  
  public boolean hasSavedId()
  {
    return getSavedId() != null;
  }
  
  public boolean isKnownSignedOutAccount(String paramString)
  {
    return this.preferences.getStringSet("msa.signed.out.ids", new HashSet()).contains(paramString);
  }
  
  public void removeKnownSignedOutAccount(String paramString)
  {
    Set localSet = this.preferences.getStringSet("msa.signed.out.ids", new HashSet());
    if (localSet.contains(paramString))
    {
      localSet.remove(paramString);
      this.preferences.edit().putStringSet("msa.signed.out.ids", localSet).apply();
    }
  }
  
  public void setSavedId(String paramString)
  {
    this.preferences.edit().putString("msa.id", paramString).apply();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\interop\xbox\jobs\Settings.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */