package com.facebook.share.model;

import android.os.Parcel;
import com.facebook.share.ShareBuilder;

public abstract interface ShareModelBuilder<P extends ShareModel, E extends ShareModelBuilder>
  extends ShareBuilder<P, E>
{
  public abstract E readFrom(Parcel paramParcel);
  
  public abstract E readFrom(P paramP);
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\facebook\share\model\ShareModelBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */