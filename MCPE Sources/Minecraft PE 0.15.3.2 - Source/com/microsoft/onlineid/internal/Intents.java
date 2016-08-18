package com.microsoft.onlineid.internal;

import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import com.microsoft.onlineid.RequestOptions;
import java.util.Iterator;
import java.util.List;

public class Intents
{
  public static class DataBuilder
  {
    private final Uri.Builder _builder = new Uri.Builder();
    
    public DataBuilder()
    {
      this._builder.scheme("extras");
    }
    
    public DataBuilder add(RequestOptions paramRequestOptions)
    {
      this._builder.appendPath("options");
      if (paramRequestOptions == null)
      {
        this._builder.appendPath("null");
        return this;
      }
      paramRequestOptions = paramRequestOptions.asBundle();
      if (paramRequestOptions == null)
      {
        this._builder.appendPath("empty");
        return this;
      }
      return add(paramRequestOptions.toString());
    }
    
    public DataBuilder add(String paramString)
    {
      this._builder.appendPath("str").appendPath(paramString);
      return this;
    }
    
    public DataBuilder add(List<String> paramList)
    {
      this._builder.appendPath("list");
      if (paramList != null)
      {
        paramList = paramList.iterator();
        while (paramList.hasNext())
        {
          String str = (String)paramList.next();
          this._builder.appendPath(str);
        }
      }
      this._builder.appendPath("null");
      return this;
    }
    
    public Uri build()
    {
      return this._builder.build();
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\onlineid\internal\Intents.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */