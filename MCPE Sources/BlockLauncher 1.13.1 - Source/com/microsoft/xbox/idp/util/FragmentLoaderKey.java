package com.microsoft.xbox.idp.util;

import android.app.Fragment;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class FragmentLoaderKey
  implements Parcelable
{
  public static final Parcelable.Creator<FragmentLoaderKey> CREATOR;
  private final String className;
  private final int loaderId;
  
  static
  {
    if (!FragmentLoaderKey.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      CREATOR = new Parcelable.Creator()
      {
        public FragmentLoaderKey createFromParcel(Parcel paramAnonymousParcel)
        {
          return new FragmentLoaderKey(paramAnonymousParcel);
        }
        
        public FragmentLoaderKey[] newArray(int paramAnonymousInt)
        {
          return new FragmentLoaderKey[paramAnonymousInt];
        }
      };
      return;
    }
  }
  
  protected FragmentLoaderKey(Parcel paramParcel)
  {
    this.className = paramParcel.readString();
    this.loaderId = paramParcel.readInt();
  }
  
  public FragmentLoaderKey(Class<? extends Fragment> paramClass, int paramInt)
  {
    assert (paramClass != null);
    this.className = paramClass.getName();
    this.loaderId = paramInt;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    boolean bool1;
    if (this == paramObject) {
      bool1 = true;
    }
    do
    {
      do
      {
        do
        {
          return bool1;
          bool1 = bool2;
        } while (paramObject == null);
        bool1 = bool2;
      } while (getClass() != paramObject.getClass());
      paramObject = (FragmentLoaderKey)paramObject;
      bool1 = bool2;
    } while (this.loaderId != ((FragmentLoaderKey)paramObject).loaderId);
    return this.className.equals(((FragmentLoaderKey)paramObject).className);
  }
  
  public int hashCode()
  {
    return this.className.hashCode() * 31 + this.loaderId;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.className);
    paramParcel.writeInt(this.loaderId);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\idp\util\FragmentLoaderKey.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */