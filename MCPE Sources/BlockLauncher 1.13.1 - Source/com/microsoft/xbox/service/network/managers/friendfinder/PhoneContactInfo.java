package com.microsoft.xbox.service.network.managers.friendfinder;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import com.microsoft.xbox.toolkit.JavaUtil;
import com.microsoft.xbox.toolkit.XLEAssert;
import com.microsoft.xbox.xle.app.XLEUtil;
import com.microsoft.xboxtcui.XboxTcuiSdk;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Set;

public class PhoneContactInfo
{
  public static final int MinimumPhoneLength = 7;
  private static PhoneContactInfo instance = new PhoneContactInfo();
  private ArrayList<Contact> contacts;
  private final String[][] countryCodes;
  private boolean isXboxContactsUpdated;
  private String phoneNumberFromSim;
  private String profilePhoneNumber;
  private String region;
  private String userEnteredNumber;
  
  private PhoneContactInfo()
  {
    Object localObject = { "244", "AO", "" };
    String[] arrayOfString1 = { "672", "AQ", "" };
    String[] arrayOfString2 = { "374", "AM", "" };
    String[] arrayOfString3 = { "267", "BW", "" };
    String[] arrayOfString4 = { "257", "BI", "" };
    String[] arrayOfString5 = { "56", "CL", "" };
    String[] arrayOfString6 = { "357", "CY", "" };
    String[] arrayOfString7 = { "45", "DK", "" };
    String[] arrayOfString8 = { "20", "EG", "" };
    String[] arrayOfString9 = { "240", "GQ", "" };
    String[] arrayOfString10 = { "291", "ER", "" };
    String[] arrayOfString11 = { "372", "EE", "" };
    String[] arrayOfString12 = { "298", "FO", "" };
    String[] arrayOfString13 = { "679", "FJ", "" };
    String[] arrayOfString14 = { "358", "FI", "" };
    String[] arrayOfString15 = { "33", "FR", "" };
    String[] arrayOfString16 = { "504", "HN", "" };
    String[] arrayOfString17 = { "91", "IN", "" };
    String[] arrayOfString18 = { "39", "IT", "" };
    String[] arrayOfString19 = { "7", "KZ", "" };
    String[] arrayOfString20 = { "254", "KE", "" };
    String[] arrayOfString21 = { "686", "KI", "" };
    String[] arrayOfString22 = { "965", "KW", "" };
    String[] arrayOfString23 = { "352", "LU", "" };
    String[] arrayOfString24 = { "853", "MO", "" };
    String[] arrayOfString25 = { "356", "MT", "" };
    String[] arrayOfString26 = { "230", "MU", "" };
    String[] arrayOfString27 = { "976", "MN", "" };
    String[] arrayOfString28 = { "382", "ME", "" };
    String[] arrayOfString29 = { "977", "NP", "" };
    String[] arrayOfString30 = { "599", "AN", "" };
    String[] arrayOfString31 = { "687", "NC", "" };
    String[] arrayOfString32 = { "505", "NI", "" };
    String[] arrayOfString33 = { "683", "NU", "" };
    String[] arrayOfString34 = { "92", "PK", "" };
    String[] arrayOfString35 = { "507", "PA", "" };
    String[] arrayOfString36 = { "7", "RU", "" };
    String[] arrayOfString37 = { "248", "SC", "" };
    String[] arrayOfString38 = { "421", "SK", "" };
    String[] arrayOfString39 = { "252", "SO", "" };
    String[] arrayOfString40 = { "27", "ZA", "" };
    String[] arrayOfString41 = { "290", "SH", "" };
    String[] arrayOfString42 = { "268", "SZ", "" };
    String[] arrayOfString43 = { "46", "SE", "" };
    String[] arrayOfString44 = { "41", "CH", "" };
    String[] arrayOfString45 = { "255", "TZ", "" };
    String[] arrayOfString46 = { "228", "TG", "" };
    String[] arrayOfString47 = { "44", "GB", "" };
    String[] arrayOfString48 = { "598", "UY", "" };
    String[] arrayOfString49 = { "58", "VE", "" };
    this.countryCodes = new String[][] { { "93", "AF", "" }, { "355", "AL", "" }, { "213", "DZ", "" }, { "376", "AD", "" }, localObject, arrayOfString1, { "54", "AR", "" }, arrayOfString2, { "297", "AW", "" }, { "61", "AU", "" }, { "43", "AT", "" }, { "994", "AZ", "" }, { "973", "BH", "" }, { "880", "BD", "" }, { "375", "BY", "" }, { "32", "BE", "" }, { "501", "BZ", "" }, { "229", "BJ", "" }, { "975", "BT", "" }, { "591", "BO", "" }, { "387", "BA", "" }, arrayOfString3, { "55", "BR", "" }, { "673", "BN", "" }, { "359", "BG", "" }, { "226", "BF", "" }, { "95", "MM", "" }, arrayOfString4, { "855", "KH", "" }, { "237", "CM", "" }, { "1", "CA", "" }, { "238", "CV", "" }, { "236", "CF", "" }, { "235", "TD", "" }, arrayOfString5, { "86", "CN", "" }, { "61", "CX", "" }, { "61", "CC", "" }, { "57", "CO", "" }, { "269", "KM", "" }, { "242", "CG", "" }, { "243", "CD", "" }, { "682", "CK", "" }, { "506", "CR", "" }, { "385", "HR", "" }, { "53", "CU", "" }, arrayOfString6, { "420", "CZ", "" }, arrayOfString7, { "253", "DJ", "" }, { "670", "TL", "" }, { "593", "EC", "" }, arrayOfString8, { "503", "SV", "" }, arrayOfString9, arrayOfString10, arrayOfString11, { "251", "ET", "" }, { "500", "FK", "" }, arrayOfString12, arrayOfString13, arrayOfString14, arrayOfString15, { "689", "PF", "" }, { "241", "GA", "" }, { "220", "GM", "" }, { "995", "GE", "" }, { "49", "DE", "" }, { "233", "GH", "" }, { "350", "GI", "" }, { "30", "GR", "" }, { "299", "GL", "" }, { "502", "GT", "" }, { "224", "GN", "" }, { "245", "GW", "" }, { "592", "GY", "" }, { "509", "HT", "" }, arrayOfString16, { "852", "HK", "" }, { "36", "HU", "" }, arrayOfString17, { "62", "ID", "" }, { "98", "IR", "" }, { "964", "IQ", "" }, { "353", "IE", "" }, { "44", "IM", "" }, { "972", "IL", "" }, arrayOfString18, { "225", "CI", "" }, { "81", "JP", "" }, { "962", "JO", "" }, arrayOfString19, arrayOfString20, arrayOfString21, arrayOfString22, { "996", "KG", "" }, { "856", "LA", "" }, { "371", "LV", "" }, { "961", "LB", "" }, { "266", "LS", "" }, { "231", "LR", "" }, { "218", "LY", "" }, { "423", "LI", "" }, { "370", "LT", "" }, arrayOfString23, arrayOfString24, { "389", "MK", "" }, { "261", "MG", "" }, { "265", "MW", "" }, { "60", "MY", "" }, { "960", "MV", "" }, { "223", "ML", "" }, arrayOfString25, { "692", "MH", "" }, { "222", "MR", "" }, arrayOfString26, { "262", "YT", "" }, { "52", "MX", "" }, { "691", "FM", "" }, { "373", "MD", "" }, { "377", "MC", "" }, arrayOfString27, arrayOfString28, { "212", "MA", "" }, { "258", "MZ", "" }, { "264", "NA", "" }, { "674", "NR", "" }, arrayOfString29, { "31", "NL", "" }, arrayOfString30, arrayOfString31, { "64", "NZ", "" }, arrayOfString32, { "227", "NE", "" }, { "234", "NG", "" }, arrayOfString33, { "850", "KP", "" }, { "47", "NO", "" }, { "968", "OM", "" }, arrayOfString34, { "680", "PW", "" }, arrayOfString35, { "675", "PG", "" }, { "595", "PY", "" }, { "51", "PE", "" }, { "63", "PH", "" }, { "870", "PN", "" }, { "48", "PL", "" }, { "351", "PT", "" }, { "1", "PR", "" }, { "974", "QA", "" }, { "40", "RO", "" }, arrayOfString36, { "250", "RW", "" }, { "590", "BL", "" }, { "685", "WS", "" }, { "378", "SM", "" }, { "239", "ST", "" }, { "966", "SA", "" }, { "221", "SN", "" }, { "381", "RS", "" }, arrayOfString37, { "232", "SL", "" }, { "65", "SG", "" }, arrayOfString38, { "386", "SI", "" }, { "677", "SB", "" }, arrayOfString39, arrayOfString40, { "82", "KR", "" }, { "34", "ES", "" }, { "94", "LK", "" }, arrayOfString41, { "508", "PM", "" }, { "249", "SD", "" }, { "597", "SR", "" }, arrayOfString42, arrayOfString43, arrayOfString44, { "963", "SY", "" }, { "886", "TW", "" }, { "992", "TJ", "" }, arrayOfString45, { "66", "TH", "" }, arrayOfString46, { "690", "TK", "" }, { "676", "TO", "" }, { "216", "TN", "" }, { "90", "TR", "" }, { "993", "TM", "" }, { "688", "TV", "" }, { "971", "AE", "" }, { "256", "UG", "" }, arrayOfString47, { "380", "UA", "" }, arrayOfString48, { "1", "US", "" }, { "998", "UZ", "" }, { "678", "VU", "" }, { "39", "VA", "" }, arrayOfString49, { "84", "VN", "" }, { "681", "WF", "" }, { "967", "YE", "" }, { "260", "ZM", "" }, { "263", "ZW", "" } };
    while (i < this.countryCodes.length)
    {
      localObject = new Locale("", this.countryCodes[i][1]).getDisplayCountry();
      XLEAssert.assertFalse("Failed to get country name : " + this.countryCodes[i][1], JavaUtil.isNullOrEmpty((String)localObject));
      this.countryCodes[i][2] = localObject;
      i += 1;
    }
  }
  
  public static PhoneContactInfo getInstance()
  {
    return instance;
  }
  
  public static String normalizePhoneNumber(String paramString)
  {
    if (paramString == null) {}
    StringBuffer localStringBuffer;
    do
    {
      do
      {
        do
        {
          return null;
        } while (paramString.length() < 7);
        paramString = paramString.toLowerCase();
      } while ((paramString.indexOf("ext") >= 0) || (paramString.indexOf("x") >= 0));
      localStringBuffer = new StringBuffer(paramString.length());
      int i = 0;
      while (i < paramString.length())
      {
        char c = paramString.charAt(i);
        if (Character.isDigit(c)) {
          localStringBuffer.append(c);
        }
        i += 1;
      }
    } while (localStringBuffer.length() < 7);
    return localStringBuffer.toString();
  }
  
  public static String sha2Encryption(String paramString)
  {
    if (JavaUtil.isNullOrEmpty(paramString)) {
      return paramString;
    }
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("SHA-256");
      localMessageDigest.reset();
      paramString = localMessageDigest.digest(paramString.getBytes("UTF-8"));
      paramString = Base64.encodeToString(paramString, 0, paramString.length, 10);
      return paramString;
    }
    catch (NoSuchAlgorithmException paramString)
    {
      return null;
    }
    catch (UnsupportedEncodingException paramString)
    {
      for (;;) {}
    }
  }
  
  public ArrayList<Contact> getContacts()
  {
    if (this.contacts == null) {}
    try
    {
      ContentResolver localContentResolver = XboxTcuiSdk.getContentResolver();
      Cursor localCursor1 = localContentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
      if (localCursor1 == null) {
        return null;
      }
      for (;;)
      {
        if ((localObject1 != null) && (!XLEUtil.isNullOrEmpty(((Contact)localObject1).phoneNumbers)))
        {
          if (this.contacts == null) {
            this.contacts = new ArrayList();
          }
          this.contacts.add(localObject1);
        }
        localCursor2.close();
        String str1;
        String str2;
        do
        {
          if (!localCursor1.moveToNext()) {
            break;
          }
          str1 = localCursor1.getString(localCursor1.getColumnIndex("_id"));
          str2 = localCursor1.getString(localCursor1.getColumnIndex("display_name"));
        } while (Integer.parseInt(localCursor1.getString(localCursor1.getColumnIndex("has_phone_number"))) <= 0);
        Cursor localCursor2 = localContentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, "contact_id = ?", new String[] { str1 }, null);
        String str3 = getCountryCode();
        Object localObject1 = null;
        while (localCursor2.moveToNext())
        {
          Object localObject2 = localCursor2.getString(localCursor2.getColumnIndex("data1"));
          if (!JavaUtil.isNullOrEmpty((String)localObject2))
          {
            String str4 = normalizePhoneNumber((String)localObject2);
            if (!JavaUtil.isNullOrEmpty(str4))
            {
              localObject2 = localObject1;
              if (localObject1 == null) {
                localObject2 = new Contact(str1, str2);
              }
              ((Contact)localObject2).addPhoneNumber(str4);
              localObject1 = localObject2;
              if (!JavaUtil.isNullOrEmpty(str3))
              {
                localObject1 = localObject2;
                if (!str4.startsWith(str3))
                {
                  ((Contact)localObject2).addPhoneNumber(str3 + str4);
                  localObject1 = localObject2;
                }
              }
            }
          }
        }
      }
      localCursor1.close();
      return this.contacts;
    }
    catch (SecurityException localSecurityException) {}
    return null;
  }
  
  public String getContryCodeFromRegion(String paramString)
  {
    int i = 0;
    while (i < this.countryCodes.length)
    {
      if (TextUtils.equals(paramString, this.countryCodes[i][1])) {
        return this.countryCodes[i][0];
      }
      i += 1;
    }
    return null;
  }
  
  public String getCountryCode()
  {
    return getContryCodeFromRegion(getRegion());
  }
  
  public String getCountryNameFromRegion(String paramString)
  {
    int i = 0;
    while (i < this.countryCodes.length)
    {
      if (TextUtils.equals(paramString, this.countryCodes[i][1])) {
        return this.countryCodes[i][2];
      }
      i += 1;
    }
    return null;
  }
  
  public ArrayList<String> getCountryNames()
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < this.countryCodes.length)
    {
      localArrayList.add(this.countryCodes[i][2]);
      i += 1;
    }
    Collections.sort(localArrayList);
    return localArrayList;
  }
  
  public String getPhoneNumberFromSim()
  {
    if (this.phoneNumberFromSim == null) {}
    try
    {
      String str1 = ((TelephonyManager)XboxTcuiSdk.getSystemService("phone")).getLine1Number();
      String str2 = getRegion();
      if ((!JavaUtil.isNullOrEmpty(str1)) && (!JavaUtil.isNullOrEmpty(str2)))
      {
        String str3 = getCountryCode();
        if (str1.startsWith(str3))
        {
          this.region = str2;
          this.phoneNumberFromSim = str1.substring(str3.length());
        }
      }
      return this.phoneNumberFromSim;
    }
    catch (SecurityException localSecurityException)
    {
      for (;;)
      {
        this.phoneNumberFromSim = "";
      }
    }
  }
  
  public String getProfileNumber()
  {
    return this.profilePhoneNumber;
  }
  
  public String getRegion()
  {
    if (this.region == null) {
      this.region = ((TelephonyManager)XboxTcuiSdk.getSystemService("phone")).getSimCountryIso().toUpperCase();
    }
    if (JavaUtil.isNullOrEmpty(this.region)) {
      this.region = Locale.getDefault().getCountry();
    }
    return this.region;
  }
  
  public String getRegionFromCountryName(String paramString)
  {
    int i = 0;
    while (i < this.countryCodes.length)
    {
      if (TextUtils.equals(paramString, this.countryCodes[i][2])) {
        return this.countryCodes[i][1];
      }
      i += 1;
    }
    return null;
  }
  
  public String getRegionWithCode()
  {
    String str1 = getInstance().getRegion();
    String str2 = getInstance().getCountryCode();
    if ((JavaUtil.isNullOrEmpty(str1)) || (JavaUtil.isNullOrEmpty(str2))) {
      return null;
    }
    return str1 + "-" + str2;
  }
  
  public String getUserEnteredNumber()
  {
    return this.userEnteredNumber;
  }
  
  public boolean isXboxContactsUpdated()
  {
    return this.isXboxContactsUpdated;
  }
  
  public void setProfileNumber(String paramString)
  {
    this.profilePhoneNumber = paramString;
  }
  
  public void setUserEnteredNumber(String paramString)
  {
    this.userEnteredNumber = paramString;
  }
  
  public void updateXboxContacts(Set<String> paramSet)
  {
    this.isXboxContactsUpdated = true;
    if (!XLEUtil.isNullOrEmpty(paramSet))
    {
      Enumeration localEnumeration = Collections.enumeration(this.contacts);
      while ((localEnumeration.hasMoreElements()) && (!paramSet.isEmpty()))
      {
        Contact localContact = (Contact)localEnumeration.nextElement();
        if (paramSet.contains(localContact.id))
        {
          paramSet.remove(localContact.id);
          localContact.isOnXbox = true;
        }
      }
    }
  }
  
  public class Contact
  {
    public String displayName;
    public String id;
    public boolean isOnXbox;
    public boolean isSelected;
    public ArrayList<String> phoneNumbers;
    
    public Contact(String paramString1, String paramString2)
    {
      this.id = paramString1;
      this.displayName = paramString2;
    }
    
    public void addPhoneNumber(String paramString)
    {
      if (this.phoneNumbers == null) {
        this.phoneNumbers = new ArrayList();
      }
      this.phoneNumbers.add(paramString);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\service\network\managers\friendfinder\PhoneContactInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */