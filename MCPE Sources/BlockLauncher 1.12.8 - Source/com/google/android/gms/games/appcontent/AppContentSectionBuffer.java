package com.google.android.gms.games.appcontent;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzf;
import java.util.ArrayList;

public final class AppContentSectionBuffer
  extends zzf<AppContentSection>
{
  private final ArrayList<DataHolder> zzaDN;
  
  public void release()
  {
    super.release();
    int j = this.zzaDN.size();
    int i = 1;
    while (i < j)
    {
      DataHolder localDataHolder = (DataHolder)this.zzaDN.get(i);
      if (localDataHolder != null) {
        localDataHolder.close();
      }
      i += 1;
    }
  }
  
  protected AppContentSection zzo(int paramInt1, int paramInt2)
  {
    return new AppContentSectionRef(this.zzaDN, paramInt1, paramInt2);
  }
  
  protected String zzqg()
  {
    return "section_id";
  }
  
  protected String zzqi()
  {
    return "card_id";
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\games\appcontent\AppContentSectionBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */