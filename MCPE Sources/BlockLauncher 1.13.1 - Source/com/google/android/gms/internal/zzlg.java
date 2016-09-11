package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Intent;
import com.google.android.gms.auth.api.signin.internal.IdpTokenType;
import com.google.android.gms.auth.api.signin.zzd;
import com.google.android.gms.common.internal.zzx;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class zzlg
  implements zzlf
{
  protected final Activity mActivity;
  private String zzWP;
  private String zzXT;
  protected final Set<String> zzXf;
  private zzlf.zza zzYe;
  
  protected zzlg(Activity paramActivity, List<String> paramList1, List<String> paramList2)
  {
    this.mActivity = ((Activity)zzx.zzz(paramActivity));
    paramActivity = new HashSet((Collection)zzx.zzz(paramList1));
    paramActivity.addAll((Collection)zzx.zzz(paramList2));
    this.zzXf = Collections.unmodifiableSet(paramActivity);
  }
  
  protected Intent zza(IdpTokenType paramIdpTokenType, String paramString1, String paramString2)
  {
    zzx.zzz(paramIdpTokenType);
    zzx.zzcM(paramString1);
    Intent localIntent = new Intent("com.google.android.gms.auth.VERIFY_ASSERTION");
    localIntent.putExtra("idpTokenType", paramIdpTokenType);
    localIntent.putExtra("idpToken", paramString1);
    localIntent.putExtra("pendingToken", paramString2);
    paramIdpTokenType = zzmU();
    if (paramIdpTokenType != null) {
      localIntent.putExtra("idProvider", paramIdpTokenType.zzmT());
    }
    return localIntent;
  }
  
  protected void zzb(zzlf.zza paramzza)
  {
    this.zzYe = ((zzlf.zza)zzx.zzz(paramzza));
  }
  
  protected void zzb(String paramString1, String paramString2, zzlf.zza paramzza)
  {
    zzbW(paramString1);
    zzbX(paramString2);
    zzb(paramzza);
  }
  
  protected void zzbW(String paramString)
  {
    this.zzWP = paramString;
  }
  
  protected void zzbX(String paramString)
  {
    this.zzXT = paramString;
  }
  
  protected Set<String> zzns()
  {
    return this.zzXf;
  }
  
  protected zzlf.zza zznt()
  {
    return this.zzYe;
  }
  
  protected String zznu()
  {
    return this.zzXT;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzlg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */