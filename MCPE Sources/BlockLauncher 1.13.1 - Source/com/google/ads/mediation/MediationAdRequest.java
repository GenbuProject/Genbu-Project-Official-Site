package com.google.ads.mediation;

import android.location.Location;
import com.google.ads.AdRequest.Gender;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

@Deprecated
public class MediationAdRequest
{
  private final Date zzbf;
  private final AdRequest.Gender zzbg;
  private final Set<String> zzbh;
  private final boolean zzbi;
  private final Location zzbj;
  
  public MediationAdRequest(Date paramDate, AdRequest.Gender paramGender, Set<String> paramSet, boolean paramBoolean, Location paramLocation)
  {
    this.zzbf = paramDate;
    this.zzbg = paramGender;
    this.zzbh = paramSet;
    this.zzbi = paramBoolean;
    this.zzbj = paramLocation;
  }
  
  public Integer getAgeInYears()
  {
    if (this.zzbf != null)
    {
      Calendar localCalendar1 = Calendar.getInstance();
      Calendar localCalendar2 = Calendar.getInstance();
      localCalendar1.setTime(this.zzbf);
      Integer localInteger2 = Integer.valueOf(localCalendar2.get(1) - localCalendar1.get(1));
      Integer localInteger1;
      if (localCalendar2.get(2) >= localCalendar1.get(2))
      {
        localInteger1 = localInteger2;
        if (localCalendar2.get(2) == localCalendar1.get(2))
        {
          localInteger1 = localInteger2;
          if (localCalendar2.get(5) >= localCalendar1.get(5)) {}
        }
      }
      else
      {
        localInteger1 = Integer.valueOf(localInteger2.intValue() - 1);
      }
      return localInteger1;
    }
    return null;
  }
  
  public Date getBirthday()
  {
    return this.zzbf;
  }
  
  public AdRequest.Gender getGender()
  {
    return this.zzbg;
  }
  
  public Set<String> getKeywords()
  {
    return this.zzbh;
  }
  
  public Location getLocation()
  {
    return this.zzbj;
  }
  
  public boolean isTesting()
  {
    return this.zzbi;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\ads\mediation\MediationAdRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */