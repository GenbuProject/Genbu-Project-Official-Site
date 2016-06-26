package com.amazon.device.iap.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amazon.device.iap.internal.util.d;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

public final class RequestId
  implements Parcelable
{
  public static final Parcelable.Creator<RequestId> CREATOR = new Parcelable.Creator()
  {
    public RequestId createFromParcel(Parcel paramAnonymousParcel)
    {
      return new RequestId(paramAnonymousParcel, null);
    }
    
    public RequestId[] newArray(int paramAnonymousInt)
    {
      return new RequestId[paramAnonymousInt];
    }
  };
  private static final String ENCODED_ID = "encodedId";
  private final String encodedId;
  
  public RequestId()
  {
    this.encodedId = UUID.randomUUID().toString();
  }
  
  private RequestId(Parcel paramParcel)
  {
    this.encodedId = paramParcel.readString();
  }
  
  private RequestId(String paramString)
  {
    d.a(paramString, "encodedId");
    this.encodedId = paramString;
  }
  
  public static RequestId fromString(String paramString)
  {
    return new RequestId(paramString);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if ((paramObject == null) || (getClass() != paramObject.getClass())) {
      return false;
    }
    return this.encodedId.equals(((RequestId)paramObject).encodedId);
  }
  
  public int hashCode()
  {
    if (this.encodedId == null) {}
    for (int i = 0;; i = this.encodedId.hashCode()) {
      return i + 31;
    }
  }
  
  public JSONObject toJSON()
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("encodedId", this.encodedId);
      return localJSONObject;
    }
    catch (JSONException localJSONException) {}
    return localJSONObject;
  }
  
  public String toString()
  {
    return this.encodedId;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.encodedId);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\device\iap\model\RequestId.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */