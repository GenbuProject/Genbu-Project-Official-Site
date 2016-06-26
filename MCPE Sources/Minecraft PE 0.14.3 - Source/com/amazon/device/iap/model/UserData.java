package com.amazon.device.iap.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amazon.device.iap.internal.model.UserDataBuilder;
import org.json.JSONException;
import org.json.JSONObject;

public final class UserData
  implements Parcelable
{
  public static final Parcelable.Creator<UserData> CREATOR = new Parcelable.Creator()
  {
    public UserData createFromParcel(Parcel paramAnonymousParcel)
    {
      return new UserData(paramAnonymousParcel, null);
    }
    
    public UserData[] newArray(int paramAnonymousInt)
    {
      return new UserData[paramAnonymousInt];
    }
  };
  private static final String MARKETPLACE = "marketplace";
  private static final String USER_ID = "userId";
  private final String marketplace;
  private final String userId;
  
  private UserData(Parcel paramParcel)
  {
    this.userId = paramParcel.readString();
    this.marketplace = paramParcel.readString();
  }
  
  public UserData(UserDataBuilder paramUserDataBuilder)
  {
    this.userId = paramUserDataBuilder.getUserId();
    this.marketplace = paramUserDataBuilder.getMarketplace();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public String getMarketplace()
  {
    return this.marketplace;
  }
  
  public String getUserId()
  {
    return this.userId;
  }
  
  public JSONObject toJSON()
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("userId", this.userId);
      localJSONObject.put("marketplace", this.marketplace);
      return localJSONObject;
    }
    catch (JSONException localJSONException) {}
    return localJSONObject;
  }
  
  public String toString()
  {
    try
    {
      String str = toJSON().toString(4);
      return str;
    }
    catch (JSONException localJSONException) {}
    return null;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeStringArray(new String[] { this.userId, this.marketplace });
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\device\iap\model\UserData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */