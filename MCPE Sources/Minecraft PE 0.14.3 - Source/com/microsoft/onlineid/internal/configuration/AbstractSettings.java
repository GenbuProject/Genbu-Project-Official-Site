package com.microsoft.onlineid.internal.configuration;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.util.Set;

public abstract class AbstractSettings
{
  protected final SharedPreferences _preferences;
  
  protected AbstractSettings(Context paramContext, String paramString)
  {
    this._preferences = paramContext.getSharedPreferences(paramString, 0);
  }
  
  protected abstract Editor edit();
  
  protected boolean getBoolean(ISetting<? extends Boolean> paramISetting)
  {
    return this._preferences.getBoolean(paramISetting.getSettingName(), ((Boolean)paramISetting.getDefaultValue()).booleanValue());
  }
  
  protected int getInt(ISetting<? extends Integer> paramISetting)
  {
    return this._preferences.getInt(paramISetting.getSettingName(), ((Integer)paramISetting.getDefaultValue()).intValue());
  }
  
  protected String getString(ISetting<? extends String> paramISetting)
  {
    return this._preferences.getString(paramISetting.getSettingName(), (String)paramISetting.getDefaultValue());
  }
  
  protected Set<String> getStringSet(ISetting<? extends Set<String>> paramISetting)
  {
    return this._preferences.getStringSet(paramISetting.getSettingName(), (Set)paramISetting.getDefaultValue());
  }
  
  public static class Editor
  {
    protected final SharedPreferences.Editor _editor;
    
    protected Editor(SharedPreferences.Editor paramEditor)
    {
      this._editor = paramEditor;
    }
    
    protected Editor clear()
    {
      this._editor.clear();
      return this;
    }
    
    public boolean commit()
    {
      return this._editor.commit();
    }
    
    protected Editor setBoolean(ISetting<? extends Boolean> paramISetting, boolean paramBoolean)
    {
      this._editor.putBoolean(paramISetting.getSettingName(), paramBoolean);
      return this;
    }
    
    protected Editor setInt(ISetting<? extends Integer> paramISetting, int paramInt)
    {
      this._editor.putInt(paramISetting.getSettingName(), paramInt);
      return this;
    }
    
    protected Editor setString(ISetting<? extends String> paramISetting, String paramString)
    {
      this._editor.putString(paramISetting.getSettingName(), paramString);
      return this;
    }
    
    protected Editor setStringSet(ISetting<? extends Set<String>> paramISetting, Set<String> paramSet)
    {
      this._editor.putStringSet(paramISetting.getSettingName(), paramSet);
      return this;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\internal\configuration\AbstractSettings.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */