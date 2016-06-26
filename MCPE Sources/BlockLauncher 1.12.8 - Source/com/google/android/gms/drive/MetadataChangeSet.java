package com.google.android.gms.drive;

import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.drive.metadata.CustomPropertyKey;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.internal.AppVisibleCustomProperties;
import com.google.android.gms.drive.metadata.internal.AppVisibleCustomProperties.zza;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.internal.zznm;
import com.google.android.gms.internal.zzno;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

public final class MetadataChangeSet
{
  public static final int CUSTOM_PROPERTY_SIZE_LIMIT_BYTES = 124;
  public static final int INDEXABLE_TEXT_SIZE_LIMIT_BYTES = 131072;
  public static final int MAX_PRIVATE_PROPERTIES_PER_RESOURCE_PER_APP = 30;
  public static final int MAX_PUBLIC_PROPERTIES_PER_RESOURCE = 30;
  public static final int MAX_TOTAL_PROPERTIES_PER_RESOURCE = 100;
  public static final MetadataChangeSet zzapd = new MetadataChangeSet(MetadataBundle.zztE());
  private final MetadataBundle zzape;
  
  public MetadataChangeSet(MetadataBundle paramMetadataBundle)
  {
    this.zzape = paramMetadataBundle.zztF();
  }
  
  public Map<CustomPropertyKey, String> getCustomPropertyChangeMap()
  {
    AppVisibleCustomProperties localAppVisibleCustomProperties = (AppVisibleCustomProperties)this.zzape.zza(zznm.zzasW);
    if (localAppVisibleCustomProperties == null) {
      return Collections.emptyMap();
    }
    return localAppVisibleCustomProperties.zztz();
  }
  
  public String getDescription()
  {
    return (String)this.zzape.zza(zznm.zzasX);
  }
  
  public String getIndexableText()
  {
    return (String)this.zzape.zza(zznm.zzatd);
  }
  
  public Date getLastViewedByMeDate()
  {
    return (Date)this.zzape.zza(zzno.zzatN);
  }
  
  public String getMimeType()
  {
    return (String)this.zzape.zza(zznm.zzatr);
  }
  
  public String getTitle()
  {
    return (String)this.zzape.zza(zznm.zzatA);
  }
  
  public Boolean isPinned()
  {
    return (Boolean)this.zzape.zza(zznm.zzatj);
  }
  
  public Boolean isStarred()
  {
    return (Boolean)this.zzape.zza(zznm.zzaty);
  }
  
  public Boolean isViewed()
  {
    return (Boolean)this.zzape.zza(zznm.zzatq);
  }
  
  public <T> MetadataChangeSet zza(MetadataField<T> paramMetadataField, T paramT)
  {
    MetadataChangeSet localMetadataChangeSet = zzsM();
    localMetadataChangeSet.zzsL().zzc(paramMetadataField, paramT);
    return localMetadataChangeSet;
  }
  
  public MetadataBundle zzsL()
  {
    return this.zzape;
  }
  
  public MetadataChangeSet zzsM()
  {
    return new MetadataChangeSet(zzsL());
  }
  
  public static class Builder
  {
    private final MetadataBundle zzape = MetadataBundle.zztE();
    private AppVisibleCustomProperties.zza zzapf;
    
    private int zzda(String paramString)
    {
      if (paramString == null) {
        return 0;
      }
      return paramString.getBytes().length;
    }
    
    private String zzj(String paramString, int paramInt1, int paramInt2)
    {
      return String.format("%s must be no more than %d bytes, but is %d bytes.", new Object[] { paramString, Integer.valueOf(paramInt1), Integer.valueOf(paramInt2) });
    }
    
    private void zzk(String paramString, int paramInt1, int paramInt2)
    {
      if (paramInt2 <= paramInt1) {}
      for (boolean bool = true;; bool = false)
      {
        zzx.zzb(bool, zzj(paramString, paramInt1, paramInt2));
        return;
      }
    }
    
    private AppVisibleCustomProperties.zza zzsN()
    {
      if (this.zzapf == null) {
        this.zzapf = new AppVisibleCustomProperties.zza();
      }
      return this.zzapf;
    }
    
    public MetadataChangeSet build()
    {
      if (this.zzapf != null) {
        this.zzape.zzc(zznm.zzasW, this.zzapf.zztA());
      }
      return new MetadataChangeSet(this.zzape);
    }
    
    public Builder deleteCustomProperty(CustomPropertyKey paramCustomPropertyKey)
    {
      zzx.zzb(paramCustomPropertyKey, "key");
      zzsN().zza(paramCustomPropertyKey, null);
      return this;
    }
    
    public Builder setCustomProperty(CustomPropertyKey paramCustomPropertyKey, String paramString)
    {
      zzx.zzb(paramCustomPropertyKey, "key");
      zzx.zzb(paramString, "value");
      zzk("The total size of key string and value string of a custom property", 124, zzda(paramCustomPropertyKey.getKey()) + zzda(paramString));
      zzsN().zza(paramCustomPropertyKey, paramString);
      return this;
    }
    
    public Builder setDescription(String paramString)
    {
      this.zzape.zzc(zznm.zzasX, paramString);
      return this;
    }
    
    public Builder setIndexableText(String paramString)
    {
      zzk("Indexable text size", 131072, zzda(paramString));
      this.zzape.zzc(zznm.zzatd, paramString);
      return this;
    }
    
    public Builder setLastViewedByMeDate(Date paramDate)
    {
      this.zzape.zzc(zzno.zzatN, paramDate);
      return this;
    }
    
    public Builder setMimeType(String paramString)
    {
      this.zzape.zzc(zznm.zzatr, paramString);
      return this;
    }
    
    public Builder setPinned(boolean paramBoolean)
    {
      this.zzape.zzc(zznm.zzatj, Boolean.valueOf(paramBoolean));
      return this;
    }
    
    public Builder setStarred(boolean paramBoolean)
    {
      this.zzape.zzc(zznm.zzaty, Boolean.valueOf(paramBoolean));
      return this;
    }
    
    public Builder setTitle(String paramString)
    {
      this.zzape.zzc(zznm.zzatA, paramString);
      return this;
    }
    
    public Builder setViewed(boolean paramBoolean)
    {
      this.zzape.zzc(zznm.zzatq, Boolean.valueOf(paramBoolean));
      return this;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\MetadataChangeSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */