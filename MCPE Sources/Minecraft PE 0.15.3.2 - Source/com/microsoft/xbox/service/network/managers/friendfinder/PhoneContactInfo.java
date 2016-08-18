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
    Object localObject = { "93", "AF", "" };
    String[] arrayOfString1 = { "355", "AL", "" };
    String[] arrayOfString2 = { "213", "DZ", "" };
    String[] arrayOfString3 = { "376", "AD", "" };
    String[] arrayOfString4 = { "54", "AR", "" };
    String[] arrayOfString5 = { "374", "AM", "" };
    String[] arrayOfString6 = { "61", "AU", "" };
    String[] arrayOfString7 = { "43", "AT", "" };
    String[] arrayOfString8 = { "994", "AZ", "" };
    String[] arrayOfString9 = { "973", "BH", "" };
    String[] arrayOfString10 = { "375", "BY", "" };
    String[] arrayOfString11 = { "32", "BE", "" };
    String[] arrayOfString12 = { "975", "BT", "" };
    String[] arrayOfString13 = { "387", "BA", "" };
    String[] arrayOfString14 = { "267", "BW", "" };
    String[] arrayOfString15 = { "673", "BN", "" };
    String[] arrayOfString16 = { "226", "BF", "" };
    String[] arrayOfString17 = { "855", "KH", "" };
    String[] arrayOfString18 = { "238", "CV", "" };
    String[] arrayOfString19 = { "235", "TD", "" };
    String[] arrayOfString20 = { "56", "CL", "" };
    String[] arrayOfString21 = { "86", "CN", "" };
    String[] arrayOfString22 = { "61", "CC", "" };
    String[] arrayOfString23 = { "57", "CO", "" };
    String[] arrayOfString24 = { "243", "CD", "" };
    String[] arrayOfString25 = { "506", "CR", "" };
    String[] arrayOfString26 = { "53", "CU", "" };
    String[] arrayOfString27 = { "357", "CY", "" };
    String[] arrayOfString28 = { "420", "CZ", "" };
    String[] arrayOfString29 = { "45", "DK", "" };
    String[] arrayOfString30 = { "253", "DJ", "" };
    String[] arrayOfString31 = { "670", "TL", "" };
    String[] arrayOfString32 = { "593", "EC", "" };
    String[] arrayOfString33 = { "20", "EG", "" };
    String[] arrayOfString34 = { "503", "SV", "" };
    String[] arrayOfString35 = { "291", "ER", "" };
    String[] arrayOfString36 = { "372", "EE", "" };
    String[] arrayOfString37 = { "298", "FO", "" };
    String[] arrayOfString38 = { "679", "FJ", "" };
    String[] arrayOfString39 = { "358", "FI", "" };
    String[] arrayOfString40 = { "995", "GE", "" };
    String[] arrayOfString41 = { "233", "GH", "" };
    String[] arrayOfString42 = { "30", "GR", "" };
    String[] arrayOfString43 = { "299", "GL", "" };
    String[] arrayOfString44 = { "502", "GT", "" };
    String[] arrayOfString45 = { "224", "GN", "" };
    String[] arrayOfString46 = { "592", "GY", "" };
    String[] arrayOfString47 = { "852", "HK", "" };
    String[] arrayOfString48 = { "36", "HU", "" };
    String[] arrayOfString49 = { "91", "IN", "" };
    String[] arrayOfString50 = { "62", "ID", "" };
    String[] arrayOfString51 = { "972", "IL", "" };
    String[] arrayOfString52 = { "39", "IT", "" };
    String[] arrayOfString53 = { "81", "JP", "" };
    String[] arrayOfString54 = { "7", "KZ", "" };
    String[] arrayOfString55 = { "254", "KE", "" };
    String[] arrayOfString56 = { "686", "KI", "" };
    String[] arrayOfString57 = { "961", "LB", "" };
    String[] arrayOfString58 = { "231", "LR", "" };
    String[] arrayOfString59 = { "370", "LT", "" };
    String[] arrayOfString60 = { "352", "LU", "" };
    String[] arrayOfString61 = { "853", "MO", "" };
    String[] arrayOfString62 = { "389", "MK", "" };
    String[] arrayOfString63 = { "60", "MY", "" };
    String[] arrayOfString64 = { "960", "MV", "" };
    String[] arrayOfString65 = { "223", "ML", "" };
    String[] arrayOfString66 = { "356", "MT", "" };
    String[] arrayOfString67 = { "692", "MH", "" };
    String[] arrayOfString68 = { "222", "MR", "" };
    String[] arrayOfString69 = { "230", "MU", "" };
    String[] arrayOfString70 = { "262", "YT", "" };
    String[] arrayOfString71 = { "52", "MX", "" };
    String[] arrayOfString72 = { "691", "FM", "" };
    String[] arrayOfString73 = { "373", "MD", "" };
    String[] arrayOfString74 = { "382", "ME", "" };
    String[] arrayOfString75 = { "674", "NR", "" };
    String[] arrayOfString76 = { "64", "NZ", "" };
    String[] arrayOfString77 = { "505", "NI", "" };
    String[] arrayOfString78 = { "234", "NG", "" };
    String[] arrayOfString79 = { "683", "NU", "" };
    String[] arrayOfString80 = { "47", "NO", "" };
    String[] arrayOfString81 = { "92", "PK", "" };
    String[] arrayOfString82 = { "680", "PW", "" };
    String[] arrayOfString83 = { "675", "PG", "" };
    String[] arrayOfString84 = { "51", "PE", "" };
    String[] arrayOfString85 = { "63", "PH", "" };
    String[] arrayOfString86 = { "48", "PL", "" };
    String[] arrayOfString87 = { "351", "PT", "" };
    String[] arrayOfString88 = { "974", "QA", "" };
    String[] arrayOfString89 = { "40", "RO", "" };
    String[] arrayOfString90 = { "7", "RU", "" };
    String[] arrayOfString91 = { "250", "RW", "" };
    String[] arrayOfString92 = { "590", "BL", "" };
    String[] arrayOfString93 = { "378", "SM", "" };
    String[] arrayOfString94 = { "966", "SA", "" };
    String[] arrayOfString95 = { "221", "SN", "" };
    String[] arrayOfString96 = { "381", "RS", "" };
    String[] arrayOfString97 = { "248", "SC", "" };
    String[] arrayOfString98 = { "421", "SK", "" };
    String[] arrayOfString99 = { "677", "SB", "" };
    String[] arrayOfString100 = { "27", "ZA", "" };
    String[] arrayOfString101 = { "82", "KR", "" };
    String[] arrayOfString102 = { "34", "ES", "" };
    String[] arrayOfString103 = { "290", "SH", "" };
    String[] arrayOfString104 = { "963", "SY", "" };
    String[] arrayOfString105 = { "992", "TJ", "" };
    String[] arrayOfString106 = { "228", "TG", "" };
    String[] arrayOfString107 = { "216", "TN", "" };
    String[] arrayOfString108 = { "688", "TV", "" };
    String[] arrayOfString109 = { "971", "AE", "" };
    String[] arrayOfString110 = { "256", "UG", "" };
    String[] arrayOfString111 = { "380", "UA", "" };
    String[] arrayOfString112 = { "998", "UZ", "" };
    String[] arrayOfString113 = { "678", "VU", "" };
    String[] arrayOfString114 = { "39", "VA", "" };
    String[] arrayOfString115 = { "58", "VE", "" };
    String[] arrayOfString116 = { "681", "WF", "" };
    String[] arrayOfString117 = { "967", "YE", "" };
    String[] arrayOfString118 = { "260", "ZM", "" };
    String[] arrayOfString119 = { "263", "ZW", "" };
    this.countryCodes = new String[][] { localObject, arrayOfString1, arrayOfString2, arrayOfString3, { "244", "AO", "" }, { "672", "AQ", "" }, arrayOfString4, arrayOfString5, { "297", "AW", "" }, arrayOfString6, arrayOfString7, arrayOfString8, arrayOfString9, { "880", "BD", "" }, arrayOfString10, arrayOfString11, { "501", "BZ", "" }, { "229", "BJ", "" }, arrayOfString12, { "591", "BO", "" }, arrayOfString13, arrayOfString14, { "55", "BR", "" }, arrayOfString15, { "359", "BG", "" }, arrayOfString16, { "95", "MM", "" }, { "257", "BI", "" }, arrayOfString17, { "237", "CM", "" }, { "1", "CA", "" }, arrayOfString18, { "236", "CF", "" }, arrayOfString19, arrayOfString20, arrayOfString21, { "61", "CX", "" }, arrayOfString22, arrayOfString23, { "269", "KM", "" }, { "242", "CG", "" }, arrayOfString24, { "682", "CK", "" }, arrayOfString25, { "385", "HR", "" }, arrayOfString26, arrayOfString27, arrayOfString28, arrayOfString29, arrayOfString30, arrayOfString31, arrayOfString32, arrayOfString33, arrayOfString34, { "240", "GQ", "" }, arrayOfString35, arrayOfString36, { "251", "ET", "" }, { "500", "FK", "" }, arrayOfString37, arrayOfString38, arrayOfString39, { "33", "FR", "" }, { "689", "PF", "" }, { "241", "GA", "" }, { "220", "GM", "" }, arrayOfString40, { "49", "DE", "" }, arrayOfString41, { "350", "GI", "" }, arrayOfString42, arrayOfString43, arrayOfString44, arrayOfString45, { "245", "GW", "" }, arrayOfString46, { "509", "HT", "" }, { "504", "HN", "" }, arrayOfString47, arrayOfString48, arrayOfString49, arrayOfString50, { "98", "IR", "" }, { "964", "IQ", "" }, { "353", "IE", "" }, { "44", "IM", "" }, arrayOfString51, arrayOfString52, { "225", "CI", "" }, arrayOfString53, { "962", "JO", "" }, arrayOfString54, arrayOfString55, arrayOfString56, { "965", "KW", "" }, { "996", "KG", "" }, { "856", "LA", "" }, { "371", "LV", "" }, arrayOfString57, { "266", "LS", "" }, arrayOfString58, { "218", "LY", "" }, { "423", "LI", "" }, arrayOfString59, arrayOfString60, arrayOfString61, arrayOfString62, { "261", "MG", "" }, { "265", "MW", "" }, arrayOfString63, arrayOfString64, arrayOfString65, arrayOfString66, arrayOfString67, arrayOfString68, arrayOfString69, arrayOfString70, arrayOfString71, arrayOfString72, arrayOfString73, { "377", "MC", "" }, { "976", "MN", "" }, arrayOfString74, { "212", "MA", "" }, { "258", "MZ", "" }, { "264", "NA", "" }, arrayOfString75, { "977", "NP", "" }, { "31", "NL", "" }, { "599", "AN", "" }, { "687", "NC", "" }, arrayOfString76, arrayOfString77, { "227", "NE", "" }, arrayOfString78, arrayOfString79, { "850", "KP", "" }, arrayOfString80, { "968", "OM", "" }, arrayOfString81, arrayOfString82, { "507", "PA", "" }, arrayOfString83, { "595", "PY", "" }, arrayOfString84, arrayOfString85, { "870", "PN", "" }, arrayOfString86, arrayOfString87, { "1", "PR", "" }, arrayOfString88, arrayOfString89, arrayOfString90, arrayOfString91, arrayOfString92, { "685", "WS", "" }, arrayOfString93, { "239", "ST", "" }, arrayOfString94, arrayOfString95, arrayOfString96, arrayOfString97, { "232", "SL", "" }, { "65", "SG", "" }, arrayOfString98, { "386", "SI", "" }, arrayOfString99, { "252", "SO", "" }, arrayOfString100, arrayOfString101, arrayOfString102, { "94", "LK", "" }, arrayOfString103, { "508", "PM", "" }, { "249", "SD", "" }, { "597", "SR", "" }, { "268", "SZ", "" }, { "46", "SE", "" }, { "41", "CH", "" }, arrayOfString104, { "886", "TW", "" }, arrayOfString105, { "255", "TZ", "" }, { "66", "TH", "" }, arrayOfString106, { "690", "TK", "" }, { "676", "TO", "" }, arrayOfString107, { "90", "TR", "" }, { "993", "TM", "" }, arrayOfString108, arrayOfString109, arrayOfString110, { "44", "GB", "" }, arrayOfString111, { "598", "UY", "" }, { "1", "US", "" }, arrayOfString112, arrayOfString113, arrayOfString114, arrayOfString115, { "84", "VN", "" }, arrayOfString116, arrayOfString117, arrayOfString118, arrayOfString119 };
    int i = 0;
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
    catch (UnsupportedEncodingException paramString)
    {
      return null;
    }
    catch (NoSuchAlgorithmException paramString)
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
        Object localObject1 = null;
        String str3 = getCountryCode();
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


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\service\network\managers\friendfinder\PhoneContactInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */