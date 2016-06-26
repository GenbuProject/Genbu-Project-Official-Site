package com.microsoft.onlineid.internal.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.microsoft.onlineid.internal.Objects;
import com.microsoft.onlineid.internal.Strings;
import com.microsoft.onlineid.internal.log.Logger;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;

public class Storage
{
  static final String DefaultStorageName = "com.microsoft.onlineid";
  private final SharedPreferences _preferences;
  
  public Storage(Context paramContext)
  {
    Objects.verifyArgumentNotNull(paramContext, "applicationContext");
    this._preferences = paramContext.getSharedPreferences("com.microsoft.onlineid", 0);
  }
  
  public Storage(Context paramContext, String paramString)
  {
    Objects.verifyArgumentNotNull(paramContext, "applicationContext");
    Strings.verifyArgumentNotNullOrEmpty(paramString, "name");
    this._preferences = paramContext.getSharedPreferences(paramString, 0);
  }
  
  public Editor edit()
  {
    return new Editor(this._preferences.edit());
  }
  
  public boolean readBoolean(String paramString, boolean paramBoolean)
  {
    return this._preferences.getBoolean(paramString, paramBoolean);
  }
  
  public long readLong(String paramString, long paramLong)
  {
    return this._preferences.getLong(paramString, paramLong);
  }
  
  public <T> T readObject(String paramString, ISerializer<T> paramISerializer)
  {
    Object localObject = null;
    try
    {
      String str = readString(paramString, null);
      if (str != null) {
        localObject = paramISerializer.deserialize(str);
      }
      paramISerializer = (ISerializer<T>)localObject;
      i = 1;
    }
    catch (ClassCastException paramISerializer)
    {
      for (;;)
      {
        Logger.warning("Object in storage was not of expected type.", paramISerializer);
        paramISerializer = null;
        i = 0;
      }
    }
    catch (IOException paramISerializer)
    {
      for (;;)
      {
        Logger.warning("Object in storage was corrupt.", paramISerializer);
        paramISerializer = null;
        int i = 0;
      }
    }
    if (i == 0) {
      edit().remove(paramString).apply();
    }
    return paramISerializer;
  }
  
  public String readString(String paramString)
  {
    return readString(paramString, null);
  }
  
  public String readString(String paramString1, String paramString2)
  {
    return this._preferences.getString(paramString1, paramString2);
  }
  
  public Set<String> readStringSet(String paramString)
  {
    return this._preferences.getStringSet(paramString, Collections.emptySet());
  }
  
  public static class Editor
  {
    private final SharedPreferences.Editor _editor;
    
    public Editor(SharedPreferences.Editor paramEditor)
    {
      this._editor = paramEditor;
    }
    
    public void apply()
    {
      this._editor.apply();
    }
    
    public Editor clear()
    {
      this._editor.clear();
      return this;
    }
    
    public boolean commit()
    {
      return this._editor.commit();
    }
    
    public Editor remove(String paramString)
    {
      this._editor.remove(paramString);
      return this;
    }
    
    public Editor writeBoolean(String paramString, boolean paramBoolean)
    {
      this._editor.putBoolean(paramString, paramBoolean);
      return this;
    }
    
    public Editor writeLong(String paramString, long paramLong)
    {
      this._editor.putLong(paramString, paramLong);
      return this;
    }
    
    public <T> Editor writeObject(String paramString, T paramT, ISerializer<T> paramISerializer)
      throws StorageException
    {
      if (paramT != null) {}
      try
      {
        this._editor.putString(paramString, paramISerializer.serialize(paramT));
        return this;
      }
      catch (IOException paramString)
      {
        throw new StorageException(paramString);
      }
    }
    
    public Editor writeString(String paramString1, String paramString2)
    {
      this._editor.putString(paramString1, paramString2);
      return this;
    }
    
    public Editor writeStringSet(String paramString, Set<String> paramSet)
    {
      this._editor.putStringSet(paramString, paramSet);
      return this;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\internal\storage\Storage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */