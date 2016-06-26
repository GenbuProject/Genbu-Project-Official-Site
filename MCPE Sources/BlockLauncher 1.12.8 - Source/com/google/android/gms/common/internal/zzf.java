package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.view.View;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.internal.zzro;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public final class zzf
{
  private final Account zzTI;
  private final String zzUW;
  private final Set<Scope> zzagh;
  private final int zzagj;
  private final View zzagk;
  private final String zzagl;
  private final Set<Scope> zzalb;
  private final Map<Api<?>, zza> zzalc;
  private final zzro zzald;
  private Integer zzale;
  
  public zzf(Account paramAccount, Set<Scope> paramSet, Map<Api<?>, zza> paramMap, int paramInt, View paramView, String paramString1, String paramString2, zzro paramzzro)
  {
    this.zzTI = paramAccount;
    if (paramSet == null) {}
    for (paramAccount = Collections.EMPTY_SET;; paramAccount = Collections.unmodifiableSet(paramSet))
    {
      this.zzagh = paramAccount;
      paramAccount = paramMap;
      if (paramMap == null) {
        paramAccount = Collections.EMPTY_MAP;
      }
      this.zzalc = paramAccount;
      this.zzagk = paramView;
      this.zzagj = paramInt;
      this.zzUW = paramString1;
      this.zzagl = paramString2;
      this.zzald = paramzzro;
      paramAccount = new HashSet(this.zzagh);
      paramSet = this.zzalc.values().iterator();
      while (paramSet.hasNext()) {
        paramAccount.addAll(((zza)paramSet.next()).zzXf);
      }
    }
    this.zzalb = Collections.unmodifiableSet(paramAccount);
  }
  
  public static zzf zzat(Context paramContext)
  {
    return new GoogleApiClient.Builder(paramContext).zzoY();
  }
  
  public Account getAccount()
  {
    return this.zzTI;
  }
  
  @Deprecated
  public String getAccountName()
  {
    if (this.zzTI != null) {
      return this.zzTI.name;
    }
    return null;
  }
  
  public void zza(Integer paramInteger)
  {
    this.zzale = paramInteger;
  }
  
  public Set<Scope> zzb(Api<?> paramApi)
  {
    paramApi = (zza)this.zzalc.get(paramApi);
    if ((paramApi == null) || (paramApi.zzXf.isEmpty())) {
      return this.zzagh;
    }
    HashSet localHashSet = new HashSet(this.zzagh);
    localHashSet.addAll(paramApi.zzXf);
    return localHashSet;
  }
  
  public Account zzqq()
  {
    if (this.zzTI != null) {
      return this.zzTI;
    }
    return new Account("<<default account>>", "com.google");
  }
  
  public int zzqr()
  {
    return this.zzagj;
  }
  
  public Set<Scope> zzqs()
  {
    return this.zzagh;
  }
  
  public Set<Scope> zzqt()
  {
    return this.zzalb;
  }
  
  public Map<Api<?>, zza> zzqu()
  {
    return this.zzalc;
  }
  
  public String zzqv()
  {
    return this.zzUW;
  }
  
  public String zzqw()
  {
    return this.zzagl;
  }
  
  public View zzqx()
  {
    return this.zzagk;
  }
  
  public zzro zzqy()
  {
    return this.zzald;
  }
  
  public Integer zzqz()
  {
    return this.zzale;
  }
  
  public static final class zza
  {
    public final Set<Scope> zzXf;
    public final boolean zzalf;
    
    public zza(Set<Scope> paramSet, boolean paramBoolean)
    {
      zzx.zzz(paramSet);
      this.zzXf = Collections.unmodifiableSet(paramSet);
      this.zzalf = paramBoolean;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\common\internal\zzf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */