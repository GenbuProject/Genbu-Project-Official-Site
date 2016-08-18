package com.microsoft.onlineid.userdata;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract.Profile;
import android.text.TextUtils;
import com.microsoft.onlineid.analytics.ClientAnalytics;
import com.microsoft.onlineid.analytics.IClientAnalytics;
import com.microsoft.onlineid.internal.Assertion;
import com.microsoft.onlineid.internal.log.Logger;

public class MeContactReader
  implements IPhoneNumberReader
{
  private final Context _applicationContext;
  
  public MeContactReader(Context paramContext)
  {
    this._applicationContext = paramContext;
  }
  
  protected Cursor createCursorForProfile(String[] paramArrayOfString, String paramString)
  {
    try
    {
      paramArrayOfString = this._applicationContext.getContentResolver().query(Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI, "data"), paramArrayOfString, "mimetype = ?", new String[] { paramString }, null);
      return paramArrayOfString;
    }
    catch (Exception paramArrayOfString)
    {
      Logger.warning("Failed to retrieve user profile from device", paramArrayOfString);
    }
    return null;
  }
  
  public FullName getFullName()
  {
    boolean bool = true;
    Object localObject = createCursorForProfile(new String[] { "data2", "data3" }, "vnd.android.cursor.item/name");
    FullName localFullName;
    label77:
    IClientAnalytics localIClientAnalytics;
    if ((localObject != null) && (((Cursor)localObject).moveToFirst()))
    {
      localFullName = new FullName(((Cursor)localObject).getString(((Cursor)localObject).getColumnIndex("data2")), ((Cursor)localObject).getString(((Cursor)localObject).getColumnIndex("data3")));
      if (localFullName == null) {
        break label171;
      }
      Assertion.check(bool);
      if (localObject != null) {
        ((Cursor)localObject).close();
      }
      localIClientAnalytics = ClientAnalytics.get();
      if (!TextUtils.isEmpty(localFullName.getFirstName())) {
        break label176;
      }
      localObject = "Does not exist in Me Contact";
      label109:
      localIClientAnalytics.logEvent("User data", "First name", (String)localObject);
      localIClientAnalytics = ClientAnalytics.get();
      if (!TextUtils.isEmpty(localFullName.getLastName())) {
        break label182;
      }
    }
    label171:
    label176:
    label182:
    for (localObject = "Does not exist in Me Contact";; localObject = "Exists in Me Contact")
    {
      localIClientAnalytics.logEvent("User data", "Last name", (String)localObject);
      return localFullName;
      localFullName = new FullName("", "");
      break;
      bool = false;
      break label77;
      localObject = "Exists in Me Contact";
      break label109;
    }
  }
  
  public String getPhoneNumber()
  {
    Cursor localCursor = createCursorForProfile(new String[] { "data2", "data1" }, "vnd.android.cursor.item/phone_v2");
    Object localObject2 = null;
    IClientAnalytics localIClientAnalytics = null;
    Object localObject1 = null;
    String str = null;
    if (localCursor != null)
    {
      localObject2 = localIClientAnalytics;
      localObject1 = str;
      while (localCursor.moveToNext())
      {
        int i = localCursor.getInt(localCursor.getColumnIndex("data2"));
        if (i == 2)
        {
          if (TextUtils.isEmpty((CharSequence)localObject2)) {
            localObject2 = localCursor.getString(localCursor.getColumnIndex("data1"));
          }
        }
        else if ((i == 1) && (TextUtils.isEmpty((CharSequence)localObject1))) {
          localObject1 = localCursor.getString(localCursor.getColumnIndex("data1"));
        }
      }
      localCursor.close();
    }
    localIClientAnalytics = ClientAnalytics.get();
    if (TextUtils.isEmpty((CharSequence)localObject2))
    {
      str = "Does not exist in Me Contact";
      localIClientAnalytics.logEvent("User data", "Mobile phone number", str);
      localIClientAnalytics = ClientAnalytics.get();
      if (!TextUtils.isEmpty((CharSequence)localObject1)) {
        break label217;
      }
    }
    label217:
    for (str = "Does not exist in Me Contact";; str = "Exists in Me Contact")
    {
      localIClientAnalytics.logEvent("User data", "Home phone number", str);
      if (!TextUtils.isEmpty((CharSequence)localObject2)) {
        return localObject2;
      }
      return (String)localObject1;
      str = "Exists in Me Contact";
      break;
    }
    return (String)localObject2;
  }
  
  public class FullName
  {
    private final String _firstName;
    private final String _lastName;
    
    public FullName(String paramString1, String paramString2)
    {
      this._firstName = paramString1;
      this._lastName = paramString2;
    }
    
    public String getFirstName()
    {
      return this._firstName;
    }
    
    public String getLastName()
    {
      return this._lastName;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\onlineid\userdata\MeContactReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */