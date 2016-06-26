package com.google.android.gms.common.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public abstract class zze
{
  public static final zze zzakF = zza("\t\n\013\f\r ?????　???").zza(zza('?', '?'));
  public static final zze zzakG = zza("\t\n\013\f\r ?????　").zza(zza('?', '?')).zza(zza('?', '?'));
  public static final zze zzakH = zza('\000', '');
  public static final zze zzakI;
  public static final zze zzakJ = zza('\t', '\r').zza(zza('\034', ' ')).zza(zzc('?')).zza(zzc('?')).zza(zza('?', '?')).zza(zza('?', '?')).zza(zza('?', '?')).zza(zzc('?')).zza(zzc('　'));
  public static final zze zzakK = new zze()
  {
    public boolean zzd(char paramAnonymousChar)
    {
      return Character.isDigit(paramAnonymousChar);
    }
  };
  public static final zze zzakL = new zze()
  {
    public boolean zzd(char paramAnonymousChar)
    {
      return Character.isLetter(paramAnonymousChar);
    }
  };
  public static final zze zzakM = new zze()
  {
    public boolean zzd(char paramAnonymousChar)
    {
      return Character.isLetterOrDigit(paramAnonymousChar);
    }
  };
  public static final zze zzakN = new zze()
  {
    public boolean zzd(char paramAnonymousChar)
    {
      return Character.isUpperCase(paramAnonymousChar);
    }
  };
  public static final zze zzakO = new zze()
  {
    public boolean zzd(char paramAnonymousChar)
    {
      return Character.isLowerCase(paramAnonymousChar);
    }
  };
  public static final zze zzakP = zza('\000', '\037').zza(zza('', '?'));
  public static final zze zzakQ = zza('\000', ' ').zza(zza('', '?')).zza(zzc('?')).zza(zza('?', '?')).zza(zza("??????")).zza(zza('?', '?')).zza(zza('?', '?')).zza(zza('?', '?')).zza(zza('?', '?')).zza(zzc('　')).zza(zza(55296, 63743)).zza(zza("????"));
  public static final zze zzakR = zza('\000', '?').zza(zzc('?')).zza(zza('?', '?')).zza(zzc('?')).zza(zzc('?')).zza(zza('?', '?')).zza(zza('?', '?')).zza(zza('?', '?')).zza(zza('?', '?')).zza(zza('?', '?')).zza(zza(64336, 65023)).zza(zza(65136, 65279)).zza(zza(65377, 65500));
  public static final zze zzakS = new zze()
  {
    public zze zza(zze paramAnonymouszze)
    {
      zzx.zzz(paramAnonymouszze);
      return this;
    }
    
    public boolean zzb(CharSequence paramAnonymousCharSequence)
    {
      zzx.zzz(paramAnonymousCharSequence);
      return true;
    }
    
    public boolean zzd(char paramAnonymousChar)
    {
      return true;
    }
  };
  public static final zze zzakT = new zze()
  {
    public zze zza(zze paramAnonymouszze)
    {
      return (zze)zzx.zzz(paramAnonymouszze);
    }
    
    public boolean zzb(CharSequence paramAnonymousCharSequence)
    {
      return paramAnonymousCharSequence.length() == 0;
    }
    
    public boolean zzd(char paramAnonymousChar)
    {
      return false;
    }
  };
  
  static
  {
    zze localzze = zza('0', '9');
    char[] arrayOfChar = "?????????????????????????????０".toCharArray();
    int j = arrayOfChar.length;
    int i = 0;
    while (i < j)
    {
      char c = arrayOfChar[i];
      localzze = localzze.zza(zza(c, (char)(c + '\t')));
      i += 1;
    }
    zzakI = localzze;
  }
  
  public static zze zza(char paramChar1, final char paramChar2)
  {
    if (paramChar2 >= paramChar1) {}
    for (boolean bool = true;; bool = false)
    {
      zzx.zzac(bool);
      new zze()
      {
        public boolean zzd(char paramAnonymousChar)
        {
          return (this.zzakX <= paramAnonymousChar) && (paramAnonymousChar <= paramChar2);
        }
      };
    }
  }
  
  public static zze zza(CharSequence paramCharSequence)
  {
    switch (paramCharSequence.length())
    {
    default: 
      paramCharSequence = paramCharSequence.toString().toCharArray();
      Arrays.sort(paramCharSequence);
      new zze()
      {
        public boolean zzd(char paramAnonymousChar)
        {
          return Arrays.binarySearch(this.zzakW, paramAnonymousChar) >= 0;
        }
      };
    case 0: 
      return zzakT;
    case 1: 
      return zzc(paramCharSequence.charAt(0));
    }
    new zze()
    {
      public boolean zzd(char paramAnonymousChar)
      {
        return (paramAnonymousChar == this.zzakU) || (paramAnonymousChar == this.zzakV);
      }
    };
  }
  
  public static zze zzc(char paramChar)
  {
    new zze()
    {
      public zze zza(zze paramAnonymouszze)
      {
        if (paramAnonymouszze.zzd(this.zzakZ)) {
          return paramAnonymouszze;
        }
        return super.zza(paramAnonymouszze);
      }
      
      public boolean zzd(char paramAnonymousChar)
      {
        return paramAnonymousChar == this.zzakZ;
      }
    };
  }
  
  public zze zza(zze paramzze)
  {
    return new zza(Arrays.asList(new zze[] { this, (zze)zzx.zzz(paramzze) }));
  }
  
  public boolean zzb(CharSequence paramCharSequence)
  {
    int i = paramCharSequence.length() - 1;
    while (i >= 0)
    {
      if (!zzd(paramCharSequence.charAt(i))) {
        return false;
      }
      i -= 1;
    }
    return true;
  }
  
  public abstract boolean zzd(char paramChar);
  
  private static class zza
    extends zze
  {
    List<zze> zzala;
    
    zza(List<zze> paramList)
    {
      this.zzala = paramList;
    }
    
    public zze zza(zze paramzze)
    {
      ArrayList localArrayList = new ArrayList(this.zzala);
      localArrayList.add(zzx.zzz(paramzze));
      return new zza(localArrayList);
    }
    
    public boolean zzd(char paramChar)
    {
      Iterator localIterator = this.zzala.iterator();
      while (localIterator.hasNext()) {
        if (((zze)localIterator.next()).zzd(paramChar)) {
          return true;
        }
      }
      return false;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ドキュメント\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\common\internal\zze.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */