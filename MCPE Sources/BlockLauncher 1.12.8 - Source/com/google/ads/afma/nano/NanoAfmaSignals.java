package com.google.ads.afma.nano;

import com.google.android.gms.internal.zzsm;
import com.google.android.gms.internal.zzsn;
import com.google.android.gms.internal.zzss;
import com.google.android.gms.internal.zzst;
import com.google.android.gms.internal.zzsu;
import com.google.android.gms.internal.zzsx;
import java.io.IOException;

public abstract interface NanoAfmaSignals
{
  public static final class AFMASignals
    extends zzsu
  {
    private static volatile AFMASignals[] zzaL;
    public Long actSignal;
    public Long acxSignal;
    public Long acySignal;
    public Long aczSignal;
    public NanoAfmaSignals.AdAttestationSignal adAttestationSignal;
    public String afmaVersion;
    public Long attSignal;
    public Long atvSignal;
    public Long btlSignal;
    public Long btsSignal;
    public String cerSignal;
    public Boolean didOptOut;
    public String didSignal;
    public String didSignalAndroidAdId;
    public Integer didSignalType;
    public Long evtTime;
    public String intSignal;
    public Long jbkSignal;
    public Long netSignal;
    public Long ornSignal;
    public String osVersion;
    public TouchInfo[] previousTouches;
    public Long psnSignal;
    public NanoAfmaSignals.RawAdAttestationSignal rawAdAttestationSignal;
    public Long reqType;
    public String stkSignal;
    public Long swzSignal;
    public Long tccSignal;
    public Long tcdSignal;
    public Long tcdnSignal;
    public Long tcmSignal;
    public Long tcpSignal;
    public Long tctSignal;
    public Long tcuSignal;
    public Long tcxSignal;
    public Long tcySignal;
    public Long uhSignal;
    public Long uptSignal;
    public Long usgSignal;
    public Long utzSignal;
    public Long uwSignal;
    public Long vcdSignal;
    public Long visSignal;
    public String vnmSignal;
    
    public AFMASignals()
    {
      clear();
    }
    
    public static AFMASignals[] emptyArray()
    {
      if (zzaL == null) {}
      synchronized (zzss.zzbut)
      {
        if (zzaL == null) {
          zzaL = new AFMASignals[0];
        }
        return zzaL;
      }
    }
    
    public static AFMASignals parseFrom(zzsm paramzzsm)
      throws IOException
    {
      return new AFMASignals().mergeFrom(paramzzsm);
    }
    
    public static AFMASignals parseFrom(byte[] paramArrayOfByte)
      throws zzst
    {
      return (AFMASignals)zzsu.mergeFrom(new AFMASignals(), paramArrayOfByte);
    }
    
    public AFMASignals clear()
    {
      this.osVersion = null;
      this.afmaVersion = null;
      this.atvSignal = null;
      this.attSignal = null;
      this.btsSignal = null;
      this.btlSignal = null;
      this.acxSignal = null;
      this.acySignal = null;
      this.aczSignal = null;
      this.actSignal = null;
      this.netSignal = null;
      this.ornSignal = null;
      this.stkSignal = null;
      this.tcxSignal = null;
      this.tcySignal = null;
      this.tctSignal = null;
      this.uptSignal = null;
      this.visSignal = null;
      this.swzSignal = null;
      this.psnSignal = null;
      this.jbkSignal = null;
      this.usgSignal = null;
      this.intSignal = null;
      this.cerSignal = null;
      this.uwSignal = null;
      this.uhSignal = null;
      this.utzSignal = null;
      this.vnmSignal = null;
      this.vcdSignal = null;
      this.tcpSignal = null;
      this.tcdSignal = null;
      this.adAttestationSignal = null;
      this.tcuSignal = null;
      this.tcmSignal = null;
      this.tcdnSignal = null;
      this.tccSignal = null;
      this.previousTouches = TouchInfo.emptyArray();
      this.reqType = null;
      this.didSignal = null;
      this.didSignalType = null;
      this.didOptOut = null;
      this.didSignalAndroidAdId = null;
      this.evtTime = null;
      this.rawAdAttestationSignal = null;
      this.zzbuu = -1;
      return this;
    }
    
    public AFMASignals mergeFrom(zzsm paramzzsm)
      throws IOException
    {
      for (;;)
      {
        int i = paramzzsm.zzIX();
        switch (i)
        {
        default: 
          if (zzsx.zzb(paramzzsm, i)) {}
          break;
        case 0: 
          return this;
        case 10: 
          this.osVersion = paramzzsm.readString();
          break;
        case 18: 
          this.afmaVersion = paramzzsm.readString();
          break;
        case 24: 
          this.atvSignal = Long.valueOf(paramzzsm.zzJa());
          break;
        case 32: 
          this.attSignal = Long.valueOf(paramzzsm.zzJa());
          break;
        case 40: 
          this.btsSignal = Long.valueOf(paramzzsm.zzJa());
          break;
        case 48: 
          this.btlSignal = Long.valueOf(paramzzsm.zzJa());
          break;
        case 56: 
          this.acxSignal = Long.valueOf(paramzzsm.zzJa());
          break;
        case 64: 
          this.acySignal = Long.valueOf(paramzzsm.zzJa());
          break;
        case 72: 
          this.aczSignal = Long.valueOf(paramzzsm.zzJa());
          break;
        case 80: 
          this.actSignal = Long.valueOf(paramzzsm.zzJa());
          break;
        case 88: 
          this.netSignal = Long.valueOf(paramzzsm.zzJa());
          break;
        case 96: 
          this.ornSignal = Long.valueOf(paramzzsm.zzJa());
          break;
        case 106: 
          this.stkSignal = paramzzsm.readString();
          break;
        case 112: 
          this.tcxSignal = Long.valueOf(paramzzsm.zzJa());
          break;
        case 120: 
          this.tcySignal = Long.valueOf(paramzzsm.zzJa());
          break;
        case 128: 
          this.tctSignal = Long.valueOf(paramzzsm.zzJa());
          break;
        case 136: 
          this.uptSignal = Long.valueOf(paramzzsm.zzJa());
          break;
        case 144: 
          this.visSignal = Long.valueOf(paramzzsm.zzJa());
          break;
        case 152: 
          this.swzSignal = Long.valueOf(paramzzsm.zzJa());
          break;
        case 160: 
          this.psnSignal = Long.valueOf(paramzzsm.zzJa());
          break;
        case 168: 
          this.reqType = Long.valueOf(paramzzsm.zzJa());
          break;
        case 176: 
          this.jbkSignal = Long.valueOf(paramzzsm.zzJa());
          break;
        case 184: 
          this.usgSignal = Long.valueOf(paramzzsm.zzJa());
          break;
        case 194: 
          this.didSignal = paramzzsm.readString();
          break;
        case 200: 
          this.evtTime = Long.valueOf(paramzzsm.zzJa());
          break;
        case 208: 
          i = paramzzsm.zzJb();
          switch (i)
          {
          default: 
            break;
          case 0: 
          case 1: 
          case 2: 
          case 3: 
          case 4: 
          case 5: 
          case 6: 
            this.didSignalType = Integer.valueOf(i);
          }
          break;
        case 218: 
          this.intSignal = paramzzsm.readString();
          break;
        case 224: 
          this.didOptOut = Boolean.valueOf(paramzzsm.zzJc());
          break;
        case 234: 
          this.cerSignal = paramzzsm.readString();
          break;
        case 242: 
          this.didSignalAndroidAdId = paramzzsm.readString();
          break;
        case 248: 
          this.uwSignal = Long.valueOf(paramzzsm.zzJa());
          break;
        case 256: 
          this.uhSignal = Long.valueOf(paramzzsm.zzJa());
          break;
        case 264: 
          this.utzSignal = Long.valueOf(paramzzsm.zzJa());
          break;
        case 274: 
          this.vnmSignal = paramzzsm.readString();
          break;
        case 280: 
          this.vcdSignal = Long.valueOf(paramzzsm.zzJa());
          break;
        case 288: 
          this.tcpSignal = Long.valueOf(paramzzsm.zzJa());
          break;
        case 296: 
          this.tcdSignal = Long.valueOf(paramzzsm.zzJa());
          break;
        case 306: 
          if (this.adAttestationSignal == null) {
            this.adAttestationSignal = new NanoAfmaSignals.AdAttestationSignal();
          }
          paramzzsm.zza(this.adAttestationSignal);
          break;
        case 312: 
          this.tcuSignal = Long.valueOf(paramzzsm.zzJa());
          break;
        case 320: 
          this.tcmSignal = Long.valueOf(paramzzsm.zzJa());
          break;
        case 328: 
          this.tcdnSignal = Long.valueOf(paramzzsm.zzJa());
          break;
        case 336: 
          this.tccSignal = Long.valueOf(paramzzsm.zzJa());
          break;
        case 346: 
          int j = zzsx.zzc(paramzzsm, 346);
          if (this.previousTouches == null) {}
          TouchInfo[] arrayOfTouchInfo;
          for (i = 0;; i = this.previousTouches.length)
          {
            arrayOfTouchInfo = new TouchInfo[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.previousTouches, 0, arrayOfTouchInfo, 0, i);
              j = i;
            }
            while (j < arrayOfTouchInfo.length - 1)
            {
              arrayOfTouchInfo[j] = new TouchInfo();
              paramzzsm.zza(arrayOfTouchInfo[j]);
              paramzzsm.zzIX();
              j += 1;
            }
          }
          arrayOfTouchInfo[j] = new TouchInfo();
          paramzzsm.zza(arrayOfTouchInfo[j]);
          this.previousTouches = arrayOfTouchInfo;
          break;
        case 1610: 
          if (this.rawAdAttestationSignal == null) {
            this.rawAdAttestationSignal = new NanoAfmaSignals.RawAdAttestationSignal();
          }
          paramzzsm.zza(this.rawAdAttestationSignal);
        }
      }
    }
    
    public void writeTo(zzsn paramzzsn)
      throws IOException
    {
      if (this.osVersion != null) {
        paramzzsn.zzn(1, this.osVersion);
      }
      if (this.afmaVersion != null) {
        paramzzsn.zzn(2, this.afmaVersion);
      }
      if (this.atvSignal != null) {
        paramzzsn.zzb(3, this.atvSignal.longValue());
      }
      if (this.attSignal != null) {
        paramzzsn.zzb(4, this.attSignal.longValue());
      }
      if (this.btsSignal != null) {
        paramzzsn.zzb(5, this.btsSignal.longValue());
      }
      if (this.btlSignal != null) {
        paramzzsn.zzb(6, this.btlSignal.longValue());
      }
      if (this.acxSignal != null) {
        paramzzsn.zzb(7, this.acxSignal.longValue());
      }
      if (this.acySignal != null) {
        paramzzsn.zzb(8, this.acySignal.longValue());
      }
      if (this.aczSignal != null) {
        paramzzsn.zzb(9, this.aczSignal.longValue());
      }
      if (this.actSignal != null) {
        paramzzsn.zzb(10, this.actSignal.longValue());
      }
      if (this.netSignal != null) {
        paramzzsn.zzb(11, this.netSignal.longValue());
      }
      if (this.ornSignal != null) {
        paramzzsn.zzb(12, this.ornSignal.longValue());
      }
      if (this.stkSignal != null) {
        paramzzsn.zzn(13, this.stkSignal);
      }
      if (this.tcxSignal != null) {
        paramzzsn.zzb(14, this.tcxSignal.longValue());
      }
      if (this.tcySignal != null) {
        paramzzsn.zzb(15, this.tcySignal.longValue());
      }
      if (this.tctSignal != null) {
        paramzzsn.zzb(16, this.tctSignal.longValue());
      }
      if (this.uptSignal != null) {
        paramzzsn.zzb(17, this.uptSignal.longValue());
      }
      if (this.visSignal != null) {
        paramzzsn.zzb(18, this.visSignal.longValue());
      }
      if (this.swzSignal != null) {
        paramzzsn.zzb(19, this.swzSignal.longValue());
      }
      if (this.psnSignal != null) {
        paramzzsn.zzb(20, this.psnSignal.longValue());
      }
      if (this.reqType != null) {
        paramzzsn.zzb(21, this.reqType.longValue());
      }
      if (this.jbkSignal != null) {
        paramzzsn.zzb(22, this.jbkSignal.longValue());
      }
      if (this.usgSignal != null) {
        paramzzsn.zzb(23, this.usgSignal.longValue());
      }
      if (this.didSignal != null) {
        paramzzsn.zzn(24, this.didSignal);
      }
      if (this.evtTime != null) {
        paramzzsn.zzb(25, this.evtTime.longValue());
      }
      if (this.didSignalType != null) {
        paramzzsn.zzA(26, this.didSignalType.intValue());
      }
      if (this.intSignal != null) {
        paramzzsn.zzn(27, this.intSignal);
      }
      if (this.didOptOut != null) {
        paramzzsn.zze(28, this.didOptOut.booleanValue());
      }
      if (this.cerSignal != null) {
        paramzzsn.zzn(29, this.cerSignal);
      }
      if (this.didSignalAndroidAdId != null) {
        paramzzsn.zzn(30, this.didSignalAndroidAdId);
      }
      if (this.uwSignal != null) {
        paramzzsn.zzb(31, this.uwSignal.longValue());
      }
      if (this.uhSignal != null) {
        paramzzsn.zzb(32, this.uhSignal.longValue());
      }
      if (this.utzSignal != null) {
        paramzzsn.zzb(33, this.utzSignal.longValue());
      }
      if (this.vnmSignal != null) {
        paramzzsn.zzn(34, this.vnmSignal);
      }
      if (this.vcdSignal != null) {
        paramzzsn.zzb(35, this.vcdSignal.longValue());
      }
      if (this.tcpSignal != null) {
        paramzzsn.zzb(36, this.tcpSignal.longValue());
      }
      if (this.tcdSignal != null) {
        paramzzsn.zzb(37, this.tcdSignal.longValue());
      }
      if (this.adAttestationSignal != null) {
        paramzzsn.zza(38, this.adAttestationSignal);
      }
      if (this.tcuSignal != null) {
        paramzzsn.zzb(39, this.tcuSignal.longValue());
      }
      if (this.tcmSignal != null) {
        paramzzsn.zzb(40, this.tcmSignal.longValue());
      }
      if (this.tcdnSignal != null) {
        paramzzsn.zzb(41, this.tcdnSignal.longValue());
      }
      if (this.tccSignal != null) {
        paramzzsn.zzb(42, this.tccSignal.longValue());
      }
      if ((this.previousTouches != null) && (this.previousTouches.length > 0))
      {
        int i = 0;
        while (i < this.previousTouches.length)
        {
          TouchInfo localTouchInfo = this.previousTouches[i];
          if (localTouchInfo != null) {
            paramzzsn.zza(43, localTouchInfo);
          }
          i += 1;
        }
      }
      if (this.rawAdAttestationSignal != null) {
        paramzzsn.zza(201, this.rawAdAttestationSignal);
      }
      super.writeTo(paramzzsn);
    }
    
    protected int zzz()
    {
      int j = super.zzz();
      int i = j;
      if (this.osVersion != null) {
        i = j + zzsn.zzo(1, this.osVersion);
      }
      j = i;
      if (this.afmaVersion != null) {
        j = i + zzsn.zzo(2, this.afmaVersion);
      }
      i = j;
      if (this.atvSignal != null) {
        i = j + zzsn.zzd(3, this.atvSignal.longValue());
      }
      j = i;
      if (this.attSignal != null) {
        j = i + zzsn.zzd(4, this.attSignal.longValue());
      }
      i = j;
      if (this.btsSignal != null) {
        i = j + zzsn.zzd(5, this.btsSignal.longValue());
      }
      j = i;
      if (this.btlSignal != null) {
        j = i + zzsn.zzd(6, this.btlSignal.longValue());
      }
      i = j;
      if (this.acxSignal != null) {
        i = j + zzsn.zzd(7, this.acxSignal.longValue());
      }
      j = i;
      if (this.acySignal != null) {
        j = i + zzsn.zzd(8, this.acySignal.longValue());
      }
      i = j;
      if (this.aczSignal != null) {
        i = j + zzsn.zzd(9, this.aczSignal.longValue());
      }
      j = i;
      if (this.actSignal != null) {
        j = i + zzsn.zzd(10, this.actSignal.longValue());
      }
      i = j;
      if (this.netSignal != null) {
        i = j + zzsn.zzd(11, this.netSignal.longValue());
      }
      j = i;
      if (this.ornSignal != null) {
        j = i + zzsn.zzd(12, this.ornSignal.longValue());
      }
      i = j;
      if (this.stkSignal != null) {
        i = j + zzsn.zzo(13, this.stkSignal);
      }
      j = i;
      if (this.tcxSignal != null) {
        j = i + zzsn.zzd(14, this.tcxSignal.longValue());
      }
      i = j;
      if (this.tcySignal != null) {
        i = j + zzsn.zzd(15, this.tcySignal.longValue());
      }
      j = i;
      if (this.tctSignal != null) {
        j = i + zzsn.zzd(16, this.tctSignal.longValue());
      }
      i = j;
      if (this.uptSignal != null) {
        i = j + zzsn.zzd(17, this.uptSignal.longValue());
      }
      j = i;
      if (this.visSignal != null) {
        j = i + zzsn.zzd(18, this.visSignal.longValue());
      }
      i = j;
      if (this.swzSignal != null) {
        i = j + zzsn.zzd(19, this.swzSignal.longValue());
      }
      j = i;
      if (this.psnSignal != null) {
        j = i + zzsn.zzd(20, this.psnSignal.longValue());
      }
      i = j;
      if (this.reqType != null) {
        i = j + zzsn.zzd(21, this.reqType.longValue());
      }
      j = i;
      if (this.jbkSignal != null) {
        j = i + zzsn.zzd(22, this.jbkSignal.longValue());
      }
      i = j;
      if (this.usgSignal != null) {
        i = j + zzsn.zzd(23, this.usgSignal.longValue());
      }
      j = i;
      if (this.didSignal != null) {
        j = i + zzsn.zzo(24, this.didSignal);
      }
      i = j;
      if (this.evtTime != null) {
        i = j + zzsn.zzd(25, this.evtTime.longValue());
      }
      j = i;
      if (this.didSignalType != null) {
        j = i + zzsn.zzC(26, this.didSignalType.intValue());
      }
      i = j;
      if (this.intSignal != null) {
        i = j + zzsn.zzo(27, this.intSignal);
      }
      j = i;
      if (this.didOptOut != null) {
        j = i + zzsn.zzf(28, this.didOptOut.booleanValue());
      }
      i = j;
      if (this.cerSignal != null) {
        i = j + zzsn.zzo(29, this.cerSignal);
      }
      j = i;
      if (this.didSignalAndroidAdId != null) {
        j = i + zzsn.zzo(30, this.didSignalAndroidAdId);
      }
      i = j;
      if (this.uwSignal != null) {
        i = j + zzsn.zzd(31, this.uwSignal.longValue());
      }
      j = i;
      if (this.uhSignal != null) {
        j = i + zzsn.zzd(32, this.uhSignal.longValue());
      }
      i = j;
      if (this.utzSignal != null) {
        i = j + zzsn.zzd(33, this.utzSignal.longValue());
      }
      j = i;
      if (this.vnmSignal != null) {
        j = i + zzsn.zzo(34, this.vnmSignal);
      }
      i = j;
      if (this.vcdSignal != null) {
        i = j + zzsn.zzd(35, this.vcdSignal.longValue());
      }
      j = i;
      if (this.tcpSignal != null) {
        j = i + zzsn.zzd(36, this.tcpSignal.longValue());
      }
      i = j;
      if (this.tcdSignal != null) {
        i = j + zzsn.zzd(37, this.tcdSignal.longValue());
      }
      j = i;
      if (this.adAttestationSignal != null) {
        j = i + zzsn.zzc(38, this.adAttestationSignal);
      }
      i = j;
      if (this.tcuSignal != null) {
        i = j + zzsn.zzd(39, this.tcuSignal.longValue());
      }
      j = i;
      if (this.tcmSignal != null) {
        j = i + zzsn.zzd(40, this.tcmSignal.longValue());
      }
      int k = j;
      if (this.tcdnSignal != null) {
        k = j + zzsn.zzd(41, this.tcdnSignal.longValue());
      }
      i = k;
      if (this.tccSignal != null) {
        i = k + zzsn.zzd(42, this.tccSignal.longValue());
      }
      j = i;
      if (this.previousTouches != null)
      {
        j = i;
        if (this.previousTouches.length > 0)
        {
          j = 0;
          while (j < this.previousTouches.length)
          {
            TouchInfo localTouchInfo = this.previousTouches[j];
            k = i;
            if (localTouchInfo != null) {
              k = i + zzsn.zzc(43, localTouchInfo);
            }
            j += 1;
            i = k;
          }
          j = i;
        }
      }
      i = j;
      if (this.rawAdAttestationSignal != null) {
        i = j + zzsn.zzc(201, this.rawAdAttestationSignal);
      }
      return i;
    }
    
    public static abstract interface DeviceIdType
    {
      public static final int DEVICE_IDENTIFIER_ADVERTISER_ID = 3;
      public static final int DEVICE_IDENTIFIER_ADVERTISER_ID_UNHASHED = 4;
      public static final int DEVICE_IDENTIFIER_ANDROID_AD_ID = 5;
      public static final int DEVICE_IDENTIFIER_APP_SPECIFIC_ID = 1;
      public static final int DEVICE_IDENTIFIER_GFIBER_ADVERTISING_ID = 6;
      public static final int DEVICE_IDENTIFIER_GLOBAL_ID = 2;
      public static final int DEVICE_IDENTIFIER_NO_ID = 0;
    }
    
    public static final class TouchInfo
      extends zzsu
    {
      private static volatile TouchInfo[] zzaM;
      public Long tcxSignal;
      public Long tcySignal;
      
      public TouchInfo()
      {
        clear();
      }
      
      public static TouchInfo[] emptyArray()
      {
        if (zzaM == null) {}
        synchronized (zzss.zzbut)
        {
          if (zzaM == null) {
            zzaM = new TouchInfo[0];
          }
          return zzaM;
        }
      }
      
      public static TouchInfo parseFrom(zzsm paramzzsm)
        throws IOException
      {
        return new TouchInfo().mergeFrom(paramzzsm);
      }
      
      public static TouchInfo parseFrom(byte[] paramArrayOfByte)
        throws zzst
      {
        return (TouchInfo)zzsu.mergeFrom(new TouchInfo(), paramArrayOfByte);
      }
      
      public TouchInfo clear()
      {
        this.tcxSignal = null;
        this.tcySignal = null;
        this.zzbuu = -1;
        return this;
      }
      
      public TouchInfo mergeFrom(zzsm paramzzsm)
        throws IOException
      {
        for (;;)
        {
          int i = paramzzsm.zzIX();
          switch (i)
          {
          default: 
            if (zzsx.zzb(paramzzsm, i)) {}
            break;
          case 0: 
            return this;
          case 8: 
            this.tcxSignal = Long.valueOf(paramzzsm.zzJa());
            break;
          case 16: 
            this.tcySignal = Long.valueOf(paramzzsm.zzJa());
          }
        }
      }
      
      public void writeTo(zzsn paramzzsn)
        throws IOException
      {
        if (this.tcxSignal != null) {
          paramzzsn.zzb(1, this.tcxSignal.longValue());
        }
        if (this.tcySignal != null) {
          paramzzsn.zzb(2, this.tcySignal.longValue());
        }
        super.writeTo(paramzzsn);
      }
      
      protected int zzz()
      {
        int j = super.zzz();
        int i = j;
        if (this.tcxSignal != null) {
          i = j + zzsn.zzd(1, this.tcxSignal.longValue());
        }
        j = i;
        if (this.tcySignal != null) {
          j = i + zzsn.zzd(2, this.tcySignal.longValue());
        }
        return j;
      }
    }
  }
  
  public static final class AdAttestationSignal
    extends zzsu
  {
    private static volatile AdAttestationSignal[] zzaN;
    public Integer reasons;
    public Integer responseType;
    public Boolean suspicious;
    public Long timestampMs;
    
    public AdAttestationSignal()
    {
      clear();
    }
    
    public static AdAttestationSignal[] emptyArray()
    {
      if (zzaN == null) {}
      synchronized (zzss.zzbut)
      {
        if (zzaN == null) {
          zzaN = new AdAttestationSignal[0];
        }
        return zzaN;
      }
    }
    
    public static AdAttestationSignal parseFrom(zzsm paramzzsm)
      throws IOException
    {
      return new AdAttestationSignal().mergeFrom(paramzzsm);
    }
    
    public static AdAttestationSignal parseFrom(byte[] paramArrayOfByte)
      throws zzst
    {
      return (AdAttestationSignal)zzsu.mergeFrom(new AdAttestationSignal(), paramArrayOfByte);
    }
    
    public AdAttestationSignal clear()
    {
      this.timestampMs = null;
      this.responseType = null;
      this.suspicious = null;
      this.reasons = null;
      this.zzbuu = -1;
      return this;
    }
    
    public AdAttestationSignal mergeFrom(zzsm paramzzsm)
      throws IOException
    {
      for (;;)
      {
        int i = paramzzsm.zzIX();
        switch (i)
        {
        default: 
          if (zzsx.zzb(paramzzsm, i)) {}
          break;
        case 0: 
          return this;
        case 8: 
          this.timestampMs = Long.valueOf(paramzzsm.zzJa());
          break;
        case 16: 
          i = paramzzsm.zzJb();
          switch (i)
          {
          default: 
            break;
          case 0: 
          case 1: 
          case 2: 
          case 3: 
            this.responseType = Integer.valueOf(i);
          }
          break;
        case 24: 
          this.suspicious = Boolean.valueOf(paramzzsm.zzJc());
          break;
        case 32: 
          i = paramzzsm.zzJb();
          switch (i)
          {
          default: 
            break;
          case 1: 
          case 2: 
          case 3: 
          case 4: 
          case 5: 
          case 6: 
          case 7: 
          case 8: 
          case 9: 
          case 10: 
          case 11: 
          case 12: 
          case 13: 
          case 14: 
          case 15: 
          case 16: 
          case 17: 
          case 18: 
          case 19: 
          case 20: 
          case 21: 
          case 22: 
          case 23: 
          case 24: 
          case 25: 
          case 26: 
          case 27: 
          case 28: 
          case 29: 
          case 30: 
          case 31: 
            this.reasons = Integer.valueOf(i);
          }
          break;
        }
      }
    }
    
    public void writeTo(zzsn paramzzsn)
      throws IOException
    {
      if (this.timestampMs != null) {
        paramzzsn.zzb(1, this.timestampMs.longValue());
      }
      if (this.responseType != null) {
        paramzzsn.zzA(2, this.responseType.intValue());
      }
      if (this.suspicious != null) {
        paramzzsn.zze(3, this.suspicious.booleanValue());
      }
      if (this.reasons != null) {
        paramzzsn.zzA(4, this.reasons.intValue());
      }
      super.writeTo(paramzzsn);
    }
    
    protected int zzz()
    {
      int j = super.zzz();
      int i = j;
      if (this.timestampMs != null) {
        i = j + zzsn.zzd(1, this.timestampMs.longValue());
      }
      j = i;
      if (this.responseType != null) {
        j = i + zzsn.zzC(2, this.responseType.intValue());
      }
      i = j;
      if (this.suspicious != null) {
        i = j + zzsn.zzf(3, this.suspicious.booleanValue());
      }
      j = i;
      if (this.reasons != null) {
        j = i + zzsn.zzC(4, this.reasons.intValue());
      }
      return j;
    }
  }
  
  public static final class AdSignalsContainer
    extends zzsu
  {
    private static volatile AdSignalsContainer[] zzaO;
    public byte[] encryptedDidSignal;
    public byte[] encryptedSpamSignals;
    
    public AdSignalsContainer()
    {
      clear();
    }
    
    public static AdSignalsContainer[] emptyArray()
    {
      if (zzaO == null) {}
      synchronized (zzss.zzbut)
      {
        if (zzaO == null) {
          zzaO = new AdSignalsContainer[0];
        }
        return zzaO;
      }
    }
    
    public static AdSignalsContainer parseFrom(zzsm paramzzsm)
      throws IOException
    {
      return new AdSignalsContainer().mergeFrom(paramzzsm);
    }
    
    public static AdSignalsContainer parseFrom(byte[] paramArrayOfByte)
      throws zzst
    {
      return (AdSignalsContainer)zzsu.mergeFrom(new AdSignalsContainer(), paramArrayOfByte);
    }
    
    public AdSignalsContainer clear()
    {
      this.encryptedSpamSignals = null;
      this.encryptedDidSignal = null;
      this.zzbuu = -1;
      return this;
    }
    
    public AdSignalsContainer mergeFrom(zzsm paramzzsm)
      throws IOException
    {
      for (;;)
      {
        int i = paramzzsm.zzIX();
        switch (i)
        {
        default: 
          if (zzsx.zzb(paramzzsm, i)) {}
          break;
        case 0: 
          return this;
        case 10: 
          this.encryptedSpamSignals = paramzzsm.readBytes();
          break;
        case 18: 
          this.encryptedDidSignal = paramzzsm.readBytes();
        }
      }
    }
    
    public void writeTo(zzsn paramzzsn)
      throws IOException
    {
      if (this.encryptedSpamSignals != null) {
        paramzzsn.zza(1, this.encryptedSpamSignals);
      }
      if (this.encryptedDidSignal != null) {
        paramzzsn.zza(2, this.encryptedDidSignal);
      }
      super.writeTo(paramzzsn);
    }
    
    protected int zzz()
    {
      int j = super.zzz();
      int i = j;
      if (this.encryptedSpamSignals != null) {
        i = j + zzsn.zzb(1, this.encryptedSpamSignals);
      }
      j = i;
      if (this.encryptedDidSignal != null) {
        j = i + zzsn.zzb(2, this.encryptedDidSignal);
      }
      return j;
    }
  }
  
  public static abstract interface DroidGuardResponseType
  {
    public static final int RESPONSE_TYPE_FALLBACK = 1;
    public static final int RESPONSE_TYPE_NORMAL = 0;
    public static final int RESPONSE_TYPE_OTHER = 3;
    public static final int RESPONSE_TYPE_PLAIN_TEXT = 2;
  }
  
  public static abstract interface DroidGuardSuspiciousReason
  {
    public static final int REASON_ANDROID_ID_MISMATCH = 26;
    public static final int REASON_BAD_SIGNAL_EVALUATION_ORDER = 24;
    public static final int REASON_BLACKLISTED_IMEI_HASH = 29;
    public static final int REASON_BLACKLISTED_IMSI_HASH = 30;
    public static final int REASON_BLACKLISTED_MAC_ADDRESS_HASH = 28;
    public static final int REASON_CONTENT_BINDING_FAILED = 7;
    public static final int REASON_CONTEXT_VERIFICATION_FAILED = 27;
    public static final int REASON_CORRUPTED_RESPONSE = 3;
    public static final int REASON_CTS_PROFILE_LOOKUP_FAILURE = 10;
    public static final int REASON_CTS_PROFILE_MISMATCH = 11;
    public static final int REASON_DEVICE_SIDE_ERROR = 8;
    public static final int REASON_DEVICE_SIDE_PARSE_ERROR = 14;
    public static final int REASON_DROIDGUASSO_VALIDATION_FAILED = 16;
    public static final int REASON_EMPTY_RESPONSE = 1;
    public static final int REASON_EMULATOR = 15;
    public static final int REASON_GMS_CORE_SIGNATURE_MISMATCH = 22;
    public static final int REASON_GOLDFISH = 12;
    public static final int REASON_INVALID_GMS_CORE_VERSION = 25;
    public static final int REASON_INVALID_TAG = 2;
    public static final int REASON_MISSING_ARM_CPU_CACHE = 9;
    public static final int REASON_MISSING_PROGRESS_REPORT = 6;
    public static final int REASON_MISSING_REPLAY_PROTECTION = 17;
    public static final int REASON_MISSING_RUNTIME_API = 20;
    public static final int REASON_REPLAY_PROTECTION = 4;
    public static final int REASON_ROOTED = 21;
    public static final int REASON_VIRTUALBOX = 13;
    public static final int REASON_VM_BINDING_FAILED = 18;
    public static final int REASON_VM_EXCEPTION = 5;
    public static final int REASON_WIDEVINE_LEVEL_MISMATCH = 23;
    public static final int REASON_WIDEVINE_VALIDATION_FAILED = 19;
    public static final int REASON_XPOSED_INSTALLED = 31;
  }
  
  public static final class RawAdAttestationSignal
    extends zzsu
  {
    private static volatile RawAdAttestationSignal[] zzaP;
    public byte[] encryptedAdAttestationStatemement;
    public String keyIdentifier;
    public Long timestampMs;
    
    public RawAdAttestationSignal()
    {
      clear();
    }
    
    public static RawAdAttestationSignal[] emptyArray()
    {
      if (zzaP == null) {}
      synchronized (zzss.zzbut)
      {
        if (zzaP == null) {
          zzaP = new RawAdAttestationSignal[0];
        }
        return zzaP;
      }
    }
    
    public static RawAdAttestationSignal parseFrom(zzsm paramzzsm)
      throws IOException
    {
      return new RawAdAttestationSignal().mergeFrom(paramzzsm);
    }
    
    public static RawAdAttestationSignal parseFrom(byte[] paramArrayOfByte)
      throws zzst
    {
      return (RawAdAttestationSignal)zzsu.mergeFrom(new RawAdAttestationSignal(), paramArrayOfByte);
    }
    
    public RawAdAttestationSignal clear()
    {
      this.timestampMs = null;
      this.keyIdentifier = null;
      this.encryptedAdAttestationStatemement = null;
      this.zzbuu = -1;
      return this;
    }
    
    public RawAdAttestationSignal mergeFrom(zzsm paramzzsm)
      throws IOException
    {
      for (;;)
      {
        int i = paramzzsm.zzIX();
        switch (i)
        {
        default: 
          if (zzsx.zzb(paramzzsm, i)) {}
          break;
        case 0: 
          return this;
        case 8: 
          this.timestampMs = Long.valueOf(paramzzsm.zzJa());
          break;
        case 26: 
          this.keyIdentifier = paramzzsm.readString();
          break;
        case 34: 
          this.encryptedAdAttestationStatemement = paramzzsm.readBytes();
        }
      }
    }
    
    public void writeTo(zzsn paramzzsn)
      throws IOException
    {
      if (this.timestampMs != null) {
        paramzzsn.zzb(1, this.timestampMs.longValue());
      }
      if (this.keyIdentifier != null) {
        paramzzsn.zzn(3, this.keyIdentifier);
      }
      if (this.encryptedAdAttestationStatemement != null) {
        paramzzsn.zza(4, this.encryptedAdAttestationStatemement);
      }
      super.writeTo(paramzzsn);
    }
    
    protected int zzz()
    {
      int j = super.zzz();
      int i = j;
      if (this.timestampMs != null) {
        i = j + zzsn.zzd(1, this.timestampMs.longValue());
      }
      j = i;
      if (this.keyIdentifier != null) {
        j = i + zzsn.zzo(3, this.keyIdentifier);
      }
      i = j;
      if (this.encryptedAdAttestationStatemement != null) {
        i = j + zzsn.zzb(4, this.encryptedAdAttestationStatemement);
      }
      return i;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\ads\afma\nano\NanoAfmaSignals.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */