package com.microsoft.xbox.authenticate;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class DelegateRPSTicketResult
  implements Parcelable
{
  public static final Parcelable.Creator<DelegateRPSTicketResult> CREATOR = new Parcelable.Creator()
  {
    public DelegateRPSTicketResult createFromParcel(Parcel paramAnonymousParcel)
    {
      return new DelegateRPSTicketResult(paramAnonymousParcel, null);
    }
    
    public DelegateRPSTicketResult[] newArray(int paramAnonymousInt)
    {
      return new DelegateRPSTicketResult[paramAnonymousInt];
    }
  };
  public static int RESULT_NOCID;
  public static int RESULT_SUCCESS = 0;
  public static int RESULT_UNEXPECTED;
  private int errorCode;
  private PendingIntent pendingIntent;
  private String ticket;
  
  static
  {
    RESULT_NOCID = 1;
    RESULT_UNEXPECTED = 2;
  }
  
  private DelegateRPSTicketResult(Parcel paramParcel)
  {
    readFromParcel(paramParcel);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public int getErrorCode()
  {
    return this.errorCode;
  }
  
  public PendingIntent getPendingIntent()
  {
    return this.pendingIntent;
  }
  
  public String getTicket()
  {
    return this.ticket;
  }
  
  public void readFromParcel(Parcel paramParcel)
  {
    this.errorCode = paramParcel.readInt();
    this.ticket = paramParcel.readString();
    this.pendingIntent = PendingIntent.readPendingIntentOrNullFromParcel(paramParcel);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeInt(this.errorCode);
    paramParcel.writeString(this.ticket);
    PendingIntent.writePendingIntentOrNullToParcel(this.pendingIntent, paramParcel);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\authenticate\DelegateRPSTicketResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */