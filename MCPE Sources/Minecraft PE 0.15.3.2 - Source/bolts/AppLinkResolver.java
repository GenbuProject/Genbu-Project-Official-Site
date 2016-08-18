package bolts;

import android.net.Uri;

public abstract interface AppLinkResolver
{
  public abstract Task<AppLink> getAppLinkFromUrlInBackground(Uri paramUri);
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\bolts\AppLinkResolver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */