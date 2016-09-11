package com.google.android.gms.appinvite;

import android.accounts.Account;
import android.content.Intent;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzx;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public final class AppInviteInvitation
{
  private static final String[] zzUK = { "jpg", "jpeg", "png" };
  
  public static String[] getInvitationIds(int paramInt, @NonNull Intent paramIntent)
  {
    if (paramInt == -1) {
      return paramIntent.getStringArrayExtra("com.google.android.gms.appinvite.RESULT_INVITATION_IDS");
    }
    return null;
  }
  
  private static Bundle zzJ(Map<String, String> paramMap)
  {
    Bundle localBundle = new Bundle();
    Iterator localIterator = paramMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      localBundle.putString(str, (String)paramMap.get(str));
    }
    return localBundle;
  }
  
  private static boolean zzbE(String paramString)
  {
    boolean bool2 = false;
    int i = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (i < zzUK.length)
      {
        if (zzUK[i].equals(paramString)) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i += 1;
    }
  }
  
  public static final class IntentBuilder
  {
    public static final int MAX_CALL_TO_ACTION_TEXT_LENGTH = 20;
    public static final int MAX_EMAIL_HTML_CONTENT = 512000;
    public static final int MAX_MESSAGE_LENGTH = 100;
    public static final int MIN_CALL_TO_ACTION_TEXT_LENGTH = 2;
    private final Intent mIntent;
    private String zzUL;
    private String zzUM;
    
    public IntentBuilder(@NonNull CharSequence paramCharSequence)
    {
      zzx.zzz(paramCharSequence);
      this.mIntent = new Intent("com.google.android.gms.appinvite.ACTION_APP_INVITE");
      this.mIntent.putExtra("com.google.android.gms.appinvite.TITLE", paramCharSequence);
      this.mIntent.setPackage("com.google.android.gms");
    }
    
    public Intent build()
    {
      if (!TextUtils.isEmpty(this.zzUL))
      {
        zzx.zzh(this.zzUM, "Email html content must be set when email subject is set.");
        if (this.mIntent.getData() == null)
        {
          bool = true;
          zzx.zzb(bool, "Custom image must not be set when email html content is set.");
          zzx.zzb(TextUtils.isEmpty(this.mIntent.getCharSequenceExtra("com.google.android.gms.appinvite.BUTTON_TEXT")), "Call to action text must not be set when email html content is set.");
          this.mIntent.putExtra("com.google.android.gms.appinvite.EMAIL_SUBJECT", this.zzUL);
          this.mIntent.putExtra("com.google.android.gms.appinvite.EMAIL_CONTENT", this.zzUM);
        }
      }
      while (TextUtils.isEmpty(this.zzUM)) {
        for (;;)
        {
          return this.mIntent;
          boolean bool = false;
        }
      }
      throw new IllegalArgumentException("Email subject must be set when email html content is set.");
    }
    
    public IntentBuilder setAccount(Account paramAccount)
    {
      if ((paramAccount != null) && ("com.google".equals(paramAccount.type)))
      {
        this.mIntent.putExtra("com.google.android.gms.appinvite.ACCOUNT_NAME", paramAccount);
        return this;
      }
      this.mIntent.removeExtra("com.google.android.gms.appinvite.ACCOUNT_NAME");
      return this;
    }
    
    public IntentBuilder setAdditionalReferralParameters(Map<String, String> paramMap)
    {
      if (paramMap != null)
      {
        this.mIntent.putExtra("com.google.android.gms.appinvite.REFERRAL_PARAMETERS_URI", AppInviteInvitation.zzK(paramMap));
        return this;
      }
      this.mIntent.removeExtra("com.google.android.gms.appinvite.REFERRAL_PARAMETERS_URI");
      return this;
    }
    
    public IntentBuilder setAndroidMinimumVersionCode(int paramInt)
    {
      this.mIntent.putExtra("com.google.android.gms.appinvite.appMinimumVersionCode", paramInt);
      return this;
    }
    
    public IntentBuilder setCallToActionText(CharSequence paramCharSequence)
    {
      if ((paramCharSequence == null) || (paramCharSequence.length() < 2) || (paramCharSequence.length() > 20)) {
        throw new IllegalArgumentException(String.format("Text must be between %d and %d chars in length.", new Object[] { Integer.valueOf(2), Integer.valueOf(20) }));
      }
      this.mIntent.putExtra("com.google.android.gms.appinvite.BUTTON_TEXT", paramCharSequence);
      return this;
    }
    
    public IntentBuilder setCustomImage(Uri paramUri)
    {
      boolean bool2 = false;
      zzx.zzz(paramUri);
      zzx.zzb(paramUri.isAbsolute(), "Image uri is not an absolute uri. Did you forget to add a scheme to the Uri?");
      String str2 = paramUri.getScheme().toLowerCase();
      int i;
      boolean bool1;
      label84:
      String str1;
      if ((str2.equals("android.resource")) || (str2.equals("content")) || (str2.equals("file")))
      {
        i = 1;
        if ((i == 0) && (!str2.equals("http")) && (!str2.equals("https"))) {
          break label221;
        }
        bool1 = true;
        zzx.zzb(bool1, "Image uri must be a content URI with scheme \"android.resource\", \"content\" or \"file\", or a network url with scheme \"http\" or \"https\".");
        if (i == 0)
        {
          str1 = paramUri.toString();
          str1 = str1.substring(str1.lastIndexOf("/") + 1, str1.length());
          if (str1 != null) {
            break label226;
          }
          str1 = null;
        }
      }
      for (;;)
      {
        if (!TextUtils.isEmpty(str1))
        {
          bool1 = bool2;
          if (!AppInviteInvitation.zzbF(str1)) {}
        }
        else
        {
          bool1 = true;
        }
        zzx.zzb(bool1, str1 + " images are not supported. Only jpg, jpeg, or png images are" + " supported.");
        paramUri = paramUri.buildUpon().scheme(str2).build();
        this.mIntent.setData(paramUri);
        if (i != 0) {
          this.mIntent.addFlags(1);
        }
        return this;
        i = 0;
        break;
        label221:
        bool1 = false;
        break label84;
        label226:
        if (str1.lastIndexOf(".") == -1) {
          str1 = null;
        } else {
          str1 = str1.substring(str1.lastIndexOf(".") + 1, str1.length()).toLowerCase();
        }
      }
    }
    
    public IntentBuilder setDeepLink(Uri paramUri)
    {
      if (paramUri != null)
      {
        this.mIntent.putExtra("com.google.android.gms.appinvite.DEEP_LINK_URL", paramUri);
        return this;
      }
      this.mIntent.removeExtra("com.google.android.gms.appinvite.DEEP_LINK_URL");
      return this;
    }
    
    public IntentBuilder setEmailHtmlContent(String paramString)
    {
      if ((paramString != null) && (paramString.getBytes().length > 512000)) {
        throw new IllegalArgumentException(String.format("Email html content must be %d bytes or less.", new Object[] { Integer.valueOf(512000) }));
      }
      this.zzUM = paramString;
      return this;
    }
    
    public IntentBuilder setEmailSubject(String paramString)
    {
      this.zzUL = paramString;
      return this;
    }
    
    public IntentBuilder setGoogleAnalyticsTrackingId(String paramString)
    {
      this.mIntent.putExtra("com.google.android.gms.appinvite.GOOGLE_ANALYTICS_TRACKING_ID", paramString);
      return this;
    }
    
    public IntentBuilder setMessage(CharSequence paramCharSequence)
    {
      if ((paramCharSequence != null) && (paramCharSequence.length() > 100)) {
        throw new IllegalArgumentException(String.format("Message must be %d chars or less.", new Object[] { Integer.valueOf(100) }));
      }
      this.mIntent.putExtra("com.google.android.gms.appinvite.MESSAGE", paramCharSequence);
      return this;
    }
    
    public IntentBuilder setOtherPlatformsTargetApplication(int paramInt, String paramString)
      throws IllegalArgumentException
    {
      switch (paramInt)
      {
      default: 
        throw new IllegalArgumentException("targetPlatform must be either PROJECT_PLATFORM_IOS or PROJECT_PLATFORM_ANDROID.");
      case 1: 
        this.mIntent.putExtra("com.google.android.gms.appinvite.iosTargetApplication", paramString);
        return this;
      }
      this.mIntent.putExtra("com.google.android.gms.appinvite.androidTargetApplication", paramString);
      return this;
    }
    
    @Retention(RetentionPolicy.SOURCE)
    public static @interface PlatformMode
    {
      public static final int PROJECT_PLATFORM_ANDROID = 2;
      public static final int PROJECT_PLATFORM_IOS = 1;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\appinvite\AppInviteInvitation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */