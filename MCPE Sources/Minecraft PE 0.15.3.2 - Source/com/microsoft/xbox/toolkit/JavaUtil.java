package com.microsoft.xbox.toolkit;

import android.graphics.Rect;
import android.text.format.DateUtils;
import android.view.View;
import com.microsoft.xboxtcui.XboxTcuiSdk;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;

public class JavaUtil
{
  private static final String HEX_PREFIX = "0x";
  private static final NumberFormat INTEGER_FORMATTER = NumberFormat.getIntegerInstance(Locale.getDefault());
  private static final Date MIN_DATE = new Date(100, 1, 1);
  private static final NumberFormat PERCENT_FORMATTER = NumberFormat.getPercentInstance(Locale.getDefault());
  
  public static <T> boolean DeepCompareArrayList(ArrayList<T> paramArrayList1, ArrayList<T> paramArrayList2)
  {
    if (paramArrayList1 == paramArrayList2) {}
    for (;;)
    {
      return true;
      if (paramArrayList1 == null)
      {
        if (paramArrayList2 != null) {
          return false;
        }
      }
      else
      {
        if (paramArrayList2 == null) {
          return false;
        }
        if (paramArrayList1.size() != paramArrayList2.size()) {
          return false;
        }
        int i = 0;
        while (i < paramArrayList1.size())
        {
          if (!paramArrayList1.get(i).equals(paramArrayList2.get(i))) {
            return false;
          }
          i += 1;
        }
      }
    }
  }
  
  public static String EnsureEncode(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0)) {
      return paramString;
    }
    try
    {
      String str = URLEncoder.encode(URLDecoder.decode(paramString, "UTF-8"), "UTF-8");
      return str;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException) {}
    return paramString;
  }
  
  public static Date JSONDateToJavaDate(String paramString)
  {
    if (isNullOrEmpty(paramString)) {
      return null;
    }
    XLEAssert.assertTrue(paramString.substring(0, 6).equals("/Date("));
    int i = paramString.length();
    if (paramString.substring(paramString.length() - 7, paramString.length()).equals("+0000)/")) {
      i = paramString.length() - 7;
    }
    for (;;)
    {
      return new Date(Long.parseLong(paramString.substring(6, i)));
      if (paramString.substring(paramString.length() - 2, paramString.length()).equals(")/")) {
        i = paramString.length() - 2;
      } else {
        XLEAssert.assertTrue(false);
      }
    }
  }
  
  public static String JavaDateToJSONDate(Date paramDate)
  {
    GregorianCalendar localGregorianCalendar = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
    localGregorianCalendar.setTime(paramDate);
    return String.format("/Date(%d)/", new Object[] { Long.valueOf(localGregorianCalendar.getTimeInMillis()) });
  }
  
  public static int[] concatIntArrays(int[]... paramVarArgs)
  {
    Object localObject;
    if (paramVarArgs == null)
    {
      localObject = null;
      return (int[])localObject;
    }
    int j = 0;
    int k = paramVarArgs.length;
    int i = 0;
    while (i < k)
    {
      j += paramVarArgs[i].length;
      i += 1;
    }
    int[] arrayOfInt = new int[j];
    j = 0;
    k = paramVarArgs.length;
    i = 0;
    for (;;)
    {
      localObject = arrayOfInt;
      if (i >= k) {
        break;
      }
      localObject = paramVarArgs[i];
      System.arraycopy(localObject, 0, arrayOfInt, j, localObject.length);
      j += localObject.length;
      i += 1;
    }
  }
  
  public static String concatenateStringsWithDelimiter(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    return concatenateStringsWithDelimiter(paramString1, paramString2, paramString3, paramString4, true);
  }
  
  public static String concatenateStringsWithDelimiter(String paramString1, String paramString2, String paramString3, String paramString4, boolean paramBoolean)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if (paramBoolean) {}
    for (Object localObject = " ";; localObject = "")
    {
      paramString4 = (String)localObject + paramString4 + " ";
      localObject = new StringBuilder();
      if (!isNullOrEmpty(paramString1)) {
        ((StringBuilder)localObject).append(paramString1);
      }
      if (!isNullOrEmpty(paramString2))
      {
        if (((StringBuilder)localObject).length() > 0) {
          ((StringBuilder)localObject).append(paramString4);
        }
        ((StringBuilder)localObject).append(paramString2);
      }
      if (!isNullOrEmpty(paramString3))
      {
        if (((StringBuilder)localObject).length() > 0) {
          ((StringBuilder)localObject).append(paramString4);
        }
        ((StringBuilder)localObject).append(paramString3);
      }
      return ((StringBuilder)localObject).toString();
    }
  }
  
  public static String concatenateStringsWithDelimiter(String paramString, boolean paramBoolean, String... paramVarArgs)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if (paramBoolean) {}
    for (Object localObject = " ";; localObject = "")
    {
      paramString = (String)localObject + paramString + " ";
      localObject = new StringBuilder();
      if (paramVarArgs.length != 0) {
        break;
      }
      return "";
    }
    int i = 0;
    while (i < paramVarArgs.length)
    {
      if (!isNullOrEmpty(paramVarArgs[i]))
      {
        if (((StringBuilder)localObject).length() > 0) {
          ((StringBuilder)localObject).append(paramString);
        }
        ((StringBuilder)localObject).append(paramVarArgs[i]);
      }
      i += 1;
    }
    return ((StringBuilder)localObject).toString();
  }
  
  public static String concatenateUrlWithLinkAndParam(String paramString1, String paramString2, String paramString3)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    if (!isNullOrEmpty(paramString1)) {
      localStringBuffer.append(paramString1);
    }
    if (!isNullOrEmpty(paramString2))
    {
      if (localStringBuffer.length() > 0) {
        localStringBuffer.append(paramString3);
      }
      localStringBuffer.append(paramString2);
    }
    return localStringBuffer.toString();
  }
  
  public static boolean containsFlag(int paramInt1, int paramInt2)
  {
    return (paramInt1 & paramInt2) == paramInt2;
  }
  
  public static Date convertToUTC(Date paramDate)
  {
    Object localObject = null;
    if (paramDate != null)
    {
      localObject = TimeZone.getDefault();
      Calendar localCalendar = Calendar.getInstance();
      localCalendar.setTime(paramDate);
      localCalendar.add(14, -((TimeZone)localObject).getOffset(paramDate.getTime()));
      localObject = localCalendar.getTime();
    }
    return (Date)localObject;
  }
  
  public static String formatInteger(int paramInt)
  {
    return INTEGER_FORMATTER.format(paramInt);
  }
  
  public static String formatPercent(float paramFloat)
  {
    String str = paramFloat + " is not between 0 and 1";
    if ((paramFloat >= 0.0F) && (paramFloat <= 1.0F)) {}
    for (boolean bool = true;; bool = false)
    {
      XLEAssert.assertTrue(str, bool);
      return PERCENT_FORMATTER.format(paramFloat);
    }
  }
  
  public static String getCurrentStackTraceAsString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    StackTraceElement[] arrayOfStackTraceElement = Thread.currentThread().getStackTrace();
    if (arrayOfStackTraceElement != null)
    {
      int j = arrayOfStackTraceElement.length;
      int i = 0;
      while (i < j)
      {
        StackTraceElement localStackTraceElement = arrayOfStackTraceElement[i];
        localStringBuilder.append("\n\n \t " + localStackTraceElement.toString());
        i += 1;
      }
    }
    return localStringBuilder.toString();
  }
  
  public static String getLocalizedDateString(Date paramDate)
  {
    try
    {
      paramDate = DateUtils.formatDateTime(XboxTcuiSdk.getApplicationContext(), paramDate.getTime(), 131088);
      return paramDate;
    }
    catch (Exception paramDate) {}
    return null;
  }
  
  public static String getShortClassName(Class paramClass)
  {
    paramClass = paramClass.getName().split("\\.");
    return paramClass[(paramClass.length - 1)];
  }
  
  public static String getTimeStringMMSS(long paramLong)
  {
    return DateUtils.formatElapsedTime(paramLong);
  }
  
  public static boolean isNullOrEmpty(String paramString)
  {
    return (paramString == null) || (paramString.length() == 0);
  }
  
  private static <T> boolean isPositionInRange(ArrayList<T> paramArrayList, int paramInt)
  {
    return (paramInt >= 0) && (paramInt < paramArrayList.size());
  }
  
  public static boolean isTouchPointInsideView(float paramFloat1, float paramFloat2, View paramView)
  {
    int[] arrayOfInt = new int[2];
    paramView.getLocationOnScreen(arrayOfInt);
    return new Rect(arrayOfInt[0], arrayOfInt[1], arrayOfInt[0] + paramView.getWidth(), arrayOfInt[1] + paramView.getHeight()).contains((int)paramFloat1, (int)paramFloat2);
  }
  
  public static <T> List<T> listIteratorToList(ListIterator<T> paramListIterator)
  {
    ArrayList localArrayList = new ArrayList();
    while ((paramListIterator != null) && (paramListIterator.hasNext())) {
      localArrayList.add(paramListIterator.next());
    }
    return localArrayList;
  }
  
  public static <T> boolean move(ArrayList<T> paramArrayList, int paramInt1, int paramInt2)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramArrayList != null)
    {
      bool1 = bool2;
      if (isPositionInRange(paramArrayList, paramInt1))
      {
        bool1 = bool2;
        if (isPositionInRange(paramArrayList, paramInt2))
        {
          Object localObject = paramArrayList.get(paramInt1);
          if (paramInt1 < paramInt2) {
            while (paramInt1 < paramInt2)
            {
              paramArrayList.set(paramInt1, paramArrayList.get(paramInt1 + 1));
              paramInt1 += 1;
            }
          }
          while (paramInt1 > paramInt2)
          {
            paramArrayList.set(paramInt1, paramArrayList.get(paramInt1 - 1));
            paramInt1 -= 1;
          }
          paramArrayList.set(paramInt2, localObject);
          bool1 = true;
        }
      }
    }
    return bool1;
  }
  
  private static boolean parseBoolean(String paramString)
  {
    try
    {
      boolean bool = Boolean.parseBoolean(paramString);
      return bool;
    }
    catch (Exception paramString) {}
    return false;
  }
  
  public static long parseHexLong(String paramString)
  {
    if (paramString == null) {
      return 0L;
    }
    if (paramString.startsWith("0x")) {
      return parseHexLongExpectHex(paramString);
    }
    try
    {
      long l = Long.parseLong(paramString, 16);
      return l;
    }
    catch (Exception paramString) {}
    return 0L;
  }
  
  private static long parseHexLongExpectHex(String paramString)
  {
    XLEAssert.assertTrue(paramString.startsWith("0x"));
    paramString = paramString.substring("0x".length());
    try
    {
      long l = Long.parseLong(paramString, 16);
      return l;
    }
    catch (Exception paramString) {}
    return 0L;
  }
  
  public static int parseInteger(String paramString)
  {
    try
    {
      int i = Integer.parseInt(paramString, 10);
      return i;
    }
    catch (Exception paramString) {}
    return 0;
  }
  
  public static String pluralize(int paramInt, String paramString1, String paramString2, String paramString3)
  {
    switch (paramInt)
    {
    default: 
      paramString1 = String.format(paramString3, new Object[] { Integer.valueOf(paramInt) });
    case 0: 
      return paramString1;
    }
    return paramString2;
  }
  
  public static int randInRange(Random paramRandom, int paramInt1, int paramInt2)
  {
    if (paramInt2 >= paramInt1) {}
    for (boolean bool = true;; bool = false)
    {
      XLEAssert.assertTrue(bool);
      return paramRandom.nextInt(paramInt2 - paramInt1) + paramInt1;
    }
  }
  
  public static boolean setFieldValue(Object paramObject1, String paramString, Object paramObject2)
  {
    try
    {
      paramString = paramObject1.getClass().getDeclaredField(paramString);
      paramString.setAccessible(true);
      paramString.set(paramObject1, paramObject2);
      return true;
    }
    catch (NoSuchFieldException paramObject1)
    {
      return false;
    }
    catch (IllegalAccessException paramObject1) {}
    return false;
  }
  
  public static void sleepDebug(long paramLong) {}
  
  public static String stringToLower(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    return paramString.toLowerCase();
  }
  
  public static String stringToUpper(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    return paramString.toUpperCase();
  }
  
  public static boolean stringsEqual(String paramString1, String paramString2)
  {
    if ((paramString1 == null) && (paramString2 == null)) {}
    while (paramString1 == paramString2) {
      return true;
    }
    return stringsEqualNonNull(paramString1, paramString2);
  }
  
  public static boolean stringsEqualCaseInsensitive(String paramString1, String paramString2)
  {
    boolean bool = true;
    if (paramString1 == paramString2) {
      return true;
    }
    if ((paramString1 == null) || (paramString2 == null)) {
      return false;
    }
    if ((paramString1 != null) && (paramString2 != null)) {}
    for (;;)
    {
      XLEAssert.assertTrue(bool);
      return paramString1.equalsIgnoreCase(paramString2);
      bool = false;
    }
  }
  
  public static boolean stringsEqualNonNull(String paramString1, String paramString2)
  {
    boolean bool2 = false;
    if ((paramString1 == null) || (paramString2 == null)) {
      return false;
    }
    boolean bool1 = bool2;
    if (paramString1 != null)
    {
      bool1 = bool2;
      if (paramString2 != null) {
        bool1 = true;
      }
    }
    XLEAssert.assertTrue(bool1);
    return paramString1.equals(paramString2);
  }
  
  public static boolean stringsEqualNonNullCaseInsensitive(String paramString1, String paramString2)
  {
    boolean bool2 = true;
    boolean bool1 = true;
    if ((paramString1 == null) || (paramString2 == null)) {
      bool1 = false;
    }
    while (paramString1 == paramString2) {
      return bool1;
    }
    if ((paramString1 != null) && (paramString2 != null)) {}
    for (bool1 = bool2;; bool1 = false)
    {
      XLEAssert.assertTrue(bool1);
      return paramString1.equalsIgnoreCase(paramString2);
    }
  }
  
  public static <T> ArrayList<T> sublistShuffle(ArrayList<T> paramArrayList, int paramInt)
  {
    boolean bool2 = true;
    Random localRandom = new Random();
    ArrayList localArrayList = new ArrayList(paramInt);
    if ((paramArrayList == null) || (paramArrayList.size() == 0)) {
      return localArrayList;
    }
    int i;
    if (paramArrayList.size() >= paramInt)
    {
      i = 0;
      while (i < paramInt)
      {
        int j = randInRange(localRandom, i, paramArrayList.size());
        Object localObject = paramArrayList.get(i);
        paramArrayList.set(i, paramArrayList.get(j));
        paramArrayList.set(j, localObject);
        localArrayList.add(paramArrayList.get(i));
        i += 1;
      }
    }
    if ((paramArrayList.size() > 0) && (paramArrayList.size() < paramInt)) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      XLEAssert.assertTrue(bool1);
      i = 0;
      while (i < paramInt)
      {
        localArrayList.add(paramArrayList.get(localRandom.nextInt(paramArrayList.size())));
        i += 1;
      }
    }
    if (localArrayList.size() == paramInt) {}
    for (bool1 = bool2;; bool1 = false)
    {
      XLEAssert.assertTrue(bool1);
      return localArrayList;
    }
  }
  
  public static String surroundInQuotes(String paramString)
  {
    return "\"" + paramString + "\"";
  }
  
  public static boolean tryParseBoolean(String paramString, boolean paramBoolean)
  {
    try
    {
      boolean bool = Boolean.parseBoolean(paramString);
      return bool;
    }
    catch (Exception paramString) {}
    return paramBoolean;
  }
  
  public static double tryParseDouble(String paramString, double paramDouble)
  {
    try
    {
      double d = Double.parseDouble(paramString);
      return d;
    }
    catch (Exception paramString) {}
    return paramDouble;
  }
  
  public static int tryParseInteger(String paramString, int paramInt)
  {
    try
    {
      int i = Integer.parseInt(paramString);
      return i;
    }
    catch (Exception paramString) {}
    return paramInt;
  }
  
  public static long tryParseLong(String paramString, long paramLong)
  {
    try
    {
      long l = Long.parseLong(paramString);
      return l;
    }
    catch (Exception paramString) {}
    return paramLong;
  }
  
  public static String urlDecode(String paramString)
  {
    try
    {
      paramString = URLDecoder.decode(paramString, "UTF-8");
      return paramString;
    }
    catch (UnsupportedEncodingException paramString) {}
    return null;
  }
  
  public static String urlEncode(String paramString)
  {
    try
    {
      paramString = URLEncoder.encode(paramString, "UTF-8");
      return paramString;
    }
    catch (UnsupportedEncodingException paramString) {}
    return null;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\toolkit\JavaUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */