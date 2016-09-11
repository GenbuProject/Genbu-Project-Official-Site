package com.google.android.gms.appdatasearch;

import android.accounts.Account;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class DocumentContents
  implements SafeParcelable
{
  public static final zzb CREATOR = new zzb();
  public final Account account;
  final int mVersionCode;
  final DocumentSection[] zzTC;
  public final String zzTD;
  public final boolean zzTE;
  
  DocumentContents(int paramInt, DocumentSection[] paramArrayOfDocumentSection, String paramString, boolean paramBoolean, Account paramAccount)
  {
    this.mVersionCode = paramInt;
    this.zzTC = paramArrayOfDocumentSection;
    this.zzTD = paramString;
    this.zzTE = paramBoolean;
    this.account = paramAccount;
  }
  
  DocumentContents(String paramString, boolean paramBoolean, Account paramAccount, DocumentSection... paramVarArgs)
  {
    this(1, paramVarArgs, paramString, paramBoolean, paramAccount);
    paramString = new BitSet(zzh.zzmg());
    int i = 0;
    while (i < paramVarArgs.length)
    {
      int j = paramVarArgs[i].zzTQ;
      if (j != -1)
      {
        if (paramString.get(j)) {
          throw new IllegalArgumentException("Duplicate global search section type " + zzh.zzao(j));
        }
        paramString.set(j);
      }
      i += 1;
    }
  }
  
  public int describeContents()
  {
    zzb localzzb = CREATOR;
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzb localzzb = CREATOR;
    zzb.zza(this, paramParcel, paramInt);
  }
  
  public static class zza
  {
    private List<DocumentSection> zzTF;
    private String zzTG;
    private boolean zzTH;
    private Account zzTI;
    
    public zza zzL(boolean paramBoolean)
    {
      this.zzTH = paramBoolean;
      return this;
    }
    
    public zza zza(DocumentSection paramDocumentSection)
    {
      if (this.zzTF == null) {
        this.zzTF = new ArrayList();
      }
      this.zzTF.add(paramDocumentSection);
      return this;
    }
    
    public zza zzb(Account paramAccount)
    {
      this.zzTI = paramAccount;
      return this;
    }
    
    public zza zzbz(String paramString)
    {
      this.zzTG = paramString;
      return this;
    }
    
    public DocumentContents zzme()
    {
      String str = this.zzTG;
      boolean bool = this.zzTH;
      Account localAccount = this.zzTI;
      if (this.zzTF != null) {}
      for (DocumentSection[] arrayOfDocumentSection = (DocumentSection[])this.zzTF.toArray(new DocumentSection[this.zzTF.size()]);; arrayOfDocumentSection = null) {
        return new DocumentContents(str, bool, localAccount, arrayOfDocumentSection);
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\appdatasearch\DocumentContents.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */