package com.google.android.gms.nearby.messages;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.nearby.messages.devices.NearbyDeviceFilter;
import com.google.android.gms.nearby.messages.internal.MessageType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MessageFilter
  implements SafeParcelable
{
  public static final Parcelable.Creator<MessageFilter> CREATOR = new zzb();
  public static final MessageFilter INCLUDE_ALL_MY_TYPES = new Builder().includeAllMyTypes().build();
  final int mVersionCode;
  private final List<MessageType> zzbbB;
  private final List<NearbyDeviceFilter> zzbbC;
  private final boolean zzbbD;
  
  MessageFilter(int paramInt, List<MessageType> paramList, List<NearbyDeviceFilter> paramList1, boolean paramBoolean)
  {
    this.mVersionCode = paramInt;
    this.zzbbB = Collections.unmodifiableList((List)zzx.zzz(paramList));
    this.zzbbD = paramBoolean;
    paramList = paramList1;
    if (paramList1 == null) {
      paramList = Collections.emptyList();
    }
    this.zzbbC = Collections.unmodifiableList(paramList);
  }
  
  private MessageFilter(List<MessageType> paramList, List<NearbyDeviceFilter> paramList1, boolean paramBoolean)
  {
    this(1, paramList, paramList1, paramBoolean);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof MessageFilter)) {
        return false;
      }
      paramObject = (MessageFilter)paramObject;
    } while ((this.zzbbD == ((MessageFilter)paramObject).zzbbD) && (zzw.equal(this.zzbbB, ((MessageFilter)paramObject).zzbbB)) && (zzw.equal(this.zzbbC, ((MessageFilter)paramObject).zzbbC)));
    return false;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { this.zzbbB, this.zzbbC, Boolean.valueOf(this.zzbbD) });
  }
  
  public String toString()
  {
    return "MessageFilter{includeAllMyTypes=" + this.zzbbD + ", messageTypes=" + this.zzbbB + "}";
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzb.zza(this, paramParcel, paramInt);
  }
  
  List<MessageType> zzEo()
  {
    return this.zzbbB;
  }
  
  boolean zzEp()
  {
    return this.zzbbD;
  }
  
  List<NearbyDeviceFilter> zzEq()
  {
    return this.zzbbC;
  }
  
  public static final class Builder
  {
    private final List<NearbyDeviceFilter> zzbbC = new ArrayList();
    private boolean zzbbD;
    private final List<MessageType> zzbbE = new ArrayList();
    
    private Builder zzS(String paramString1, String paramString2)
    {
      this.zzbbE.add(new MessageType(paramString1, paramString2));
      return this;
    }
    
    public MessageFilter build()
    {
      if ((this.zzbbD) || (!this.zzbbE.isEmpty())) {}
      for (boolean bool = true;; bool = false)
      {
        zzx.zza(bool, "At least one of the include methods must be called.");
        return new MessageFilter(this.zzbbE, this.zzbbC, this.zzbbD, null);
      }
    }
    
    public Builder includeAllMyTypes()
    {
      this.zzbbD = true;
      return this;
    }
    
    public Builder includeFilter(MessageFilter paramMessageFilter)
    {
      this.zzbbE.addAll(paramMessageFilter.zzEo());
      this.zzbbC.addAll(paramMessageFilter.zzEq());
      this.zzbbD |= paramMessageFilter.zzEp();
      return this;
    }
    
    public Builder includeNamespacedType(String paramString1, String paramString2)
    {
      if ((paramString1 != null) && (!paramString1.isEmpty()) && (!paramString1.contains("*")))
      {
        bool = true;
        zzx.zzb(bool, "namespace(%s) cannot be null, empty or contain (*).", new Object[] { paramString1 });
        if ((paramString2 == null) || (paramString2.contains("*"))) {
          break label77;
        }
      }
      label77:
      for (boolean bool = true;; bool = false)
      {
        zzx.zzb(bool, "type(%s) cannot be null or contain (*).", new Object[] { paramString2 });
        return zzS(paramString1, paramString2);
        bool = false;
        break;
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\nearby\messages\MessageFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */