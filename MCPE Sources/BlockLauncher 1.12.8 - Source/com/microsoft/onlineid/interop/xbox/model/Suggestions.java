package com.microsoft.onlineid.interop.xbox.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;

public class Suggestions
{
  public static class Request
  {
    public int Algorithm;
    public int Count;
    public String Locale;
    public String Seed;
  }
  
  public static class Response
    implements Parcelable
  {
    public static final Parcelable.Creator<Response> CREATOR = new Parcelable.Creator()
    {
      public Suggestions.Response createFromParcel(Parcel paramAnonymousParcel)
      {
        return new Suggestions.Response(paramAnonymousParcel);
      }
      
      public Suggestions.Response[] newArray(int paramAnonymousInt)
      {
        return new Suggestions.Response[paramAnonymousInt];
      }
    };
    public ArrayList<String> Gamertags;
    
    protected Response(Parcel paramParcel)
    {
      this.Gamertags = paramParcel.createStringArrayList();
    }
    
    public int describeContents()
    {
      return 0;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      paramParcel.writeStringList(this.Gamertags);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\interop\xbox\model\Suggestions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */