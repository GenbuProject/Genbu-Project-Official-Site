package android.support.v4.media;

import android.os.Bundle;
import java.util.List;

public class MediaBrowserCompatUtils
{
  public static List<MediaBrowserCompat.MediaItem> applyOptions(List<MediaBrowserCompat.MediaItem> paramList, Bundle paramBundle)
  {
    int i = paramBundle.getInt("android.media.browse.extra.PAGE", -1);
    int m = paramBundle.getInt("android.media.browse.extra.PAGE_SIZE", -1);
    if ((i == -1) && (m == -1)) {
      return paramList;
    }
    int k = m * (i - 1);
    int j = k + m;
    if ((i < 1) || (m < 1) || (k >= paramList.size())) {
      return null;
    }
    i = j;
    if (j > paramList.size()) {
      i = paramList.size();
    }
    return paramList.subList(k, i);
  }
  
  public static boolean areSameOptions(Bundle paramBundle1, Bundle paramBundle2)
  {
    if (paramBundle1 == paramBundle2) {}
    do
    {
      do
      {
        do
        {
          return true;
          if (paramBundle1 != null) {
            break;
          }
        } while ((paramBundle2.getInt("android.media.browse.extra.PAGE", -1) == -1) && (paramBundle2.getInt("android.media.browse.extra.PAGE_SIZE", -1) == -1));
        return false;
        if (paramBundle2 != null) {
          break;
        }
      } while ((paramBundle1.getInt("android.media.browse.extra.PAGE", -1) == -1) && (paramBundle1.getInt("android.media.browse.extra.PAGE_SIZE", -1) == -1));
      return false;
    } while ((paramBundle1.getInt("android.media.browse.extra.PAGE", -1) == paramBundle2.getInt("android.media.browse.extra.PAGE", -1)) && (paramBundle1.getInt("android.media.browse.extra.PAGE_SIZE", -1) == paramBundle2.getInt("android.media.browse.extra.PAGE_SIZE", -1)));
    return false;
  }
  
  public static boolean hasDuplicatedItems(Bundle paramBundle1, Bundle paramBundle2)
  {
    int k;
    int i;
    label13:
    int m;
    label20:
    int j;
    if (paramBundle1 == null)
    {
      k = -1;
      if (paramBundle2 != null) {
        break label86;
      }
      i = -1;
      if (paramBundle1 != null) {
        break label97;
      }
      m = -1;
      if (paramBundle2 != null) {
        break label109;
      }
      j = -1;
      label26:
      if ((k != -1) && (m != -1)) {
        break label120;
      }
      m = 0;
      k = Integer.MAX_VALUE;
      label45:
      if ((i != -1) && (j != -1)) {
        break label145;
      }
      j = 0;
      i = Integer.MAX_VALUE;
      label60:
      if ((m > j) || (j > k)) {
        break label165;
      }
    }
    label86:
    label97:
    label109:
    label120:
    label145:
    label165:
    while ((m <= i) && (i <= k))
    {
      return true;
      k = paramBundle1.getInt("android.media.browse.extra.PAGE", -1);
      break;
      i = paramBundle2.getInt("android.media.browse.extra.PAGE", -1);
      break label13;
      m = paramBundle1.getInt("android.media.browse.extra.PAGE_SIZE", -1);
      break label20;
      j = paramBundle2.getInt("android.media.browse.extra.PAGE_SIZE", -1);
      break label26;
      int n = m * (k - 1);
      k = n + m - 1;
      m = n;
      break label45;
      n = j * (i - 1);
      i = n + j - 1;
      j = n;
      break label60;
    }
    return false;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\android\support\v4\media\MediaBrowserCompatUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */