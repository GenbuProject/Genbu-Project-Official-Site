package org.mozilla.javascript;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

final class NativeDate
  extends IdScriptableObject
{
  private static final int ConstructorId_UTC = -1;
  private static final int ConstructorId_now = -3;
  private static final int ConstructorId_parse = -2;
  private static final Object DATE_TAG;
  private static final double HalfTimeDomain = 8.64E15D;
  private static final double HoursPerDay = 24.0D;
  private static final int Id_constructor = 1;
  private static final int Id_getDate = 17;
  private static final int Id_getDay = 19;
  private static final int Id_getFullYear = 13;
  private static final int Id_getHours = 21;
  private static final int Id_getMilliseconds = 27;
  private static final int Id_getMinutes = 23;
  private static final int Id_getMonth = 15;
  private static final int Id_getSeconds = 25;
  private static final int Id_getTime = 11;
  private static final int Id_getTimezoneOffset = 29;
  private static final int Id_getUTCDate = 18;
  private static final int Id_getUTCDay = 20;
  private static final int Id_getUTCFullYear = 14;
  private static final int Id_getUTCHours = 22;
  private static final int Id_getUTCMilliseconds = 28;
  private static final int Id_getUTCMinutes = 24;
  private static final int Id_getUTCMonth = 16;
  private static final int Id_getUTCSeconds = 26;
  private static final int Id_getYear = 12;
  private static final int Id_setDate = 39;
  private static final int Id_setFullYear = 43;
  private static final int Id_setHours = 37;
  private static final int Id_setMilliseconds = 31;
  private static final int Id_setMinutes = 35;
  private static final int Id_setMonth = 41;
  private static final int Id_setSeconds = 33;
  private static final int Id_setTime = 30;
  private static final int Id_setUTCDate = 40;
  private static final int Id_setUTCFullYear = 44;
  private static final int Id_setUTCHours = 38;
  private static final int Id_setUTCMilliseconds = 32;
  private static final int Id_setUTCMinutes = 36;
  private static final int Id_setUTCMonth = 42;
  private static final int Id_setUTCSeconds = 34;
  private static final int Id_setYear = 45;
  private static final int Id_toDateString = 4;
  private static final int Id_toGMTString = 8;
  private static final int Id_toISOString = 46;
  private static final int Id_toJSON = 47;
  private static final int Id_toLocaleDateString = 7;
  private static final int Id_toLocaleString = 5;
  private static final int Id_toLocaleTimeString = 6;
  private static final int Id_toSource = 9;
  private static final int Id_toString = 2;
  private static final int Id_toTimeString = 3;
  private static final int Id_toUTCString = 8;
  private static final int Id_valueOf = 10;
  private static double LocalTZA = 0.0D;
  private static final int MAXARGS = 7;
  private static final int MAX_PROTOTYPE_ID = 47;
  private static final double MinutesPerDay = 1440.0D;
  private static final double MinutesPerHour = 60.0D;
  private static final double SecondsPerDay = 86400.0D;
  private static final double SecondsPerHour = 3600.0D;
  private static final double SecondsPerMinute = 60.0D;
  private static final String js_NaN_date_str = "Invalid Date";
  private static DateFormat localeDateFormatter;
  private static DateFormat localeDateTimeFormatter;
  private static DateFormat localeTimeFormatter;
  private static final double msPerDay = 8.64E7D;
  private static final double msPerHour = 3600000.0D;
  private static final double msPerMinute = 60000.0D;
  private static final double msPerSecond = 1000.0D;
  static final long serialVersionUID = -8307438915861678966L;
  private static TimeZone thisTimeZone;
  private static DateFormat timeZoneFormatter;
  private double date;
  
  static
  {
    if (!NativeDate.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      DATE_TAG = "Date";
      return;
    }
  }
  
  private NativeDate()
  {
    if (thisTimeZone == null)
    {
      thisTimeZone = TimeZone.getDefault();
      LocalTZA = thisTimeZone.getRawOffset();
    }
  }
  
  private static int DateFromTime(double paramDouble)
  {
    int j = 30;
    int i = 31;
    int n = YearFromTime(paramDouble);
    int m = (int)(Day(paramDouble) - DayFromYear(n)) - 59;
    if (m < 0)
    {
      if (m < -28) {
        return m + 31 + 28 + 1;
      }
      return m + 28 + 1;
    }
    int k = m;
    if (IsLeapYear(n))
    {
      if (m == 0) {
        return 29;
      }
      k = m - 1;
    }
    switch (k / 30)
    {
    default: 
      throw Kit.codeBug();
    case 0: 
      return k + 1;
    case 1: 
      j = 31;
    case 2: 
    case 3: 
    case 4: 
    case 5: 
    case 6: 
    case 7: 
    case 8: 
    case 9: 
      for (;;)
      {
        k -= i;
        i = k;
        if (k < 0) {
          i = k + j;
        }
        return i + 1;
        i = 61;
        continue;
        j = 31;
        i = 92;
        continue;
        i = 122;
        continue;
        j = 31;
        i = 153;
        continue;
        j = 31;
        i = 184;
        continue;
        i = 214;
        continue;
        j = 31;
        i = 245;
        continue;
        i = 275;
      }
    }
    return k - 275 + 1;
  }
  
  private static double Day(double paramDouble)
  {
    return Math.floor(paramDouble / 8.64E7D);
  }
  
  private static double DayFromMonth(int paramInt1, int paramInt2)
  {
    int i = paramInt1 * 30;
    if (paramInt1 >= 7) {
      i += paramInt1 / 2 - 1;
    }
    for (;;)
    {
      int j = i;
      if (paramInt1 >= 2)
      {
        j = i;
        if (IsLeapYear(paramInt2)) {
          j = i + 1;
        }
      }
      return j;
      if (paramInt1 >= 2) {
        i += (paramInt1 - 1) / 2 - 1;
      } else {
        i += paramInt1;
      }
    }
  }
  
  private static double DayFromYear(double paramDouble)
  {
    return 365.0D * (paramDouble - 1970.0D) + Math.floor((paramDouble - 1969.0D) / 4.0D) - Math.floor((paramDouble - 1901.0D) / 100.0D) + Math.floor((paramDouble - 1601.0D) / 400.0D);
  }
  
  private static double DaylightSavingTA(double paramDouble)
  {
    double d = paramDouble;
    if (paramDouble < 0.0D) {
      d = MakeDate(MakeDay(EquivalentYear(YearFromTime(paramDouble)), MonthFromTime(paramDouble), DateFromTime(paramDouble)), TimeWithinDay(paramDouble));
    }
    Date localDate = new Date(d);
    if (thisTimeZone.inDaylightTime(localDate)) {
      return 3600000.0D;
    }
    return 0.0D;
  }
  
  private static int DaysInMonth(int paramInt1, int paramInt2)
  {
    if (paramInt2 == 2)
    {
      if (IsLeapYear(paramInt1)) {
        return 29;
      }
      return 28;
    }
    if (paramInt2 >= 8) {
      return 31 - (paramInt2 & 0x1);
    }
    return (paramInt2 & 0x1) + 30;
  }
  
  private static double DaysInYear(double paramDouble)
  {
    if ((Double.isInfinite(paramDouble)) || (Double.isNaN(paramDouble))) {
      return ScriptRuntime.NaN;
    }
    if (IsLeapYear((int)paramDouble)) {
      return 366.0D;
    }
    return 365.0D;
  }
  
  private static int EquivalentYear(int paramInt)
  {
    int j = ((int)DayFromYear(paramInt) + 4) % 7;
    int i = j;
    if (j < 0) {
      i = j + 7;
    }
    if (IsLeapYear(paramInt)) {
      switch (i)
      {
      }
    }
    for (;;)
    {
      throw Kit.codeBug();
      return 1984;
      return 1996;
      return 1980;
      return 1992;
      return 1976;
      return 1988;
      return 1972;
      switch (i)
      {
      }
    }
    return 1978;
    return 1973;
    return 1985;
    return 1986;
    return 1981;
    return 1971;
    return 1977;
  }
  
  private static int HourFromTime(double paramDouble)
  {
    double d = Math.floor(paramDouble / 3600000.0D) % 24.0D;
    paramDouble = d;
    if (d < 0.0D) {
      paramDouble = d + 24.0D;
    }
    return (int)paramDouble;
  }
  
  private static boolean IsLeapYear(int paramInt)
  {
    return (paramInt % 4 == 0) && ((paramInt % 100 != 0) || (paramInt % 400 == 0));
  }
  
  private static double LocalTime(double paramDouble)
  {
    return LocalTZA + paramDouble + DaylightSavingTA(paramDouble);
  }
  
  private static double MakeDate(double paramDouble1, double paramDouble2)
  {
    return 8.64E7D * paramDouble1 + paramDouble2;
  }
  
  private static double MakeDay(double paramDouble1, double paramDouble2, double paramDouble3)
  {
    double d = paramDouble1 + Math.floor(paramDouble2 / 12.0D);
    paramDouble2 %= 12.0D;
    paramDouble1 = paramDouble2;
    if (paramDouble2 < 0.0D) {
      paramDouble1 = paramDouble2 + 12.0D;
    }
    paramDouble2 = Math.floor(TimeFromYear(d) / 8.64E7D);
    return DayFromMonth((int)paramDouble1, (int)d) + paramDouble2 + paramDouble3 - 1.0D;
  }
  
  private static double MakeTime(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
  {
    return ((paramDouble1 * 60.0D + paramDouble2) * 60.0D + paramDouble3) * 1000.0D + paramDouble4;
  }
  
  private static int MinFromTime(double paramDouble)
  {
    double d = Math.floor(paramDouble / 60000.0D) % 60.0D;
    paramDouble = d;
    if (d < 0.0D) {
      paramDouble = d + 60.0D;
    }
    return (int)paramDouble;
  }
  
  private static int MonthFromTime(double paramDouble)
  {
    int i = 1;
    int k = YearFromTime(paramDouble);
    int j = (int)(Day(paramDouble) - DayFromYear(k)) - 59;
    if (j < 0) {
      if (j < -28) {
        i = 0;
      }
    }
    do
    {
      return i;
      if (!IsLeapYear(k)) {
        break;
      }
    } while (j == 0);
    j -= 1;
    for (;;)
    {
      k = j / 30;
      switch (k)
      {
      default: 
        throw Kit.codeBug();
      case 0: 
        return 2;
      case 1: 
        i = 31;
      }
      while (j >= i)
      {
        return k + 2;
        i = 61;
        continue;
        i = 92;
        continue;
        i = 122;
        continue;
        i = 153;
        continue;
        i = 184;
        continue;
        i = 214;
        continue;
        i = 245;
        continue;
        i = 275;
        continue;
        return 11;
      }
      return k + 1;
    }
  }
  
  private static int SecFromTime(double paramDouble)
  {
    double d = Math.floor(paramDouble / 1000.0D) % 60.0D;
    paramDouble = d;
    if (d < 0.0D) {
      paramDouble = d + 60.0D;
    }
    return (int)paramDouble;
  }
  
  private static double TimeClip(double paramDouble)
  {
    if ((paramDouble != paramDouble) || (paramDouble == Double.POSITIVE_INFINITY) || (paramDouble == Double.NEGATIVE_INFINITY) || (Math.abs(paramDouble) > 8.64E15D)) {
      return ScriptRuntime.NaN;
    }
    if (paramDouble > 0.0D) {
      return Math.floor(paramDouble + 0.0D);
    }
    return Math.ceil(paramDouble + 0.0D);
  }
  
  private static double TimeFromYear(double paramDouble)
  {
    return DayFromYear(paramDouble) * 8.64E7D;
  }
  
  private static double TimeWithinDay(double paramDouble)
  {
    double d = paramDouble % 8.64E7D;
    paramDouble = d;
    if (d < 0.0D) {
      paramDouble = d + 8.64E7D;
    }
    return paramDouble;
  }
  
  private static int WeekDay(double paramDouble)
  {
    double d = (Day(paramDouble) + 4.0D) % 7.0D;
    paramDouble = d;
    if (d < 0.0D) {
      paramDouble = d + 7.0D;
    }
    return (int)paramDouble;
  }
  
  private static int YearFromTime(double paramDouble)
  {
    if ((Double.isInfinite(paramDouble)) || (Double.isNaN(paramDouble))) {
      return 0;
    }
    double d2 = Math.floor(paramDouble / 3.1556952E10D) + 1970.0D;
    double d3 = TimeFromYear(d2);
    double d1;
    if (d3 > paramDouble) {
      d1 = d2 - 1.0D;
    }
    for (;;)
    {
      return (int)d1;
      d1 = d2;
      if (d3 + 8.64E7D * DaysInYear(d2) <= paramDouble) {
        d1 = d2 + 1.0D;
      }
    }
  }
  
  private static void append0PaddedUint(StringBuilder paramStringBuilder, int paramInt1, int paramInt2)
  {
    int j = 1000000000;
    if (paramInt1 < 0) {
      Kit.codeBug();
    }
    int i = paramInt2 - 1;
    if (paramInt1 >= 10) {
      if (paramInt1 < 1000000000)
      {
        paramInt2 = 1;
        j = paramInt2 * 10;
        if (paramInt1 >= j) {}
      }
    }
    for (;;)
    {
      j = paramInt2;
      int k = paramInt1;
      if (i > 0)
      {
        paramStringBuilder.append('0');
        i -= 1;
        continue;
        i -= 1;
        paramInt2 = j;
        break;
        i -= 9;
        paramInt2 = j;
        continue;
      }
      while (j != 1)
      {
        paramStringBuilder.append((char)(k / j + 48));
        k %= j;
        j /= 10;
      }
      paramStringBuilder.append((char)(k + 48));
      return;
      paramInt2 = 1;
    }
  }
  
  private static void appendMonthName(StringBuilder paramStringBuilder, int paramInt)
  {
    int i = 0;
    while (i != 3)
    {
      paramStringBuilder.append("JanFebMarAprMayJunJulAugSepOctNovDec".charAt(paramInt * 3 + i));
      i += 1;
    }
  }
  
  private static void appendWeekDayName(StringBuilder paramStringBuilder, int paramInt)
  {
    int i = 0;
    while (i != 3)
    {
      paramStringBuilder.append("SunMonTueWedThuFriSat".charAt(paramInt * 3 + i));
      i += 1;
    }
  }
  
  private static String date_format(double paramDouble, int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder(60);
    double d2 = LocalTime(paramDouble);
    if (paramInt != 3)
    {
      appendWeekDayName(localStringBuilder, WeekDay(d2));
      localStringBuilder.append(' ');
      appendMonthName(localStringBuilder, MonthFromTime(d2));
      localStringBuilder.append(' ');
      append0PaddedUint(localStringBuilder, DateFromTime(d2), 2);
      localStringBuilder.append(' ');
      int j = YearFromTime(d2);
      int i = j;
      if (j < 0)
      {
        localStringBuilder.append('-');
        i = -j;
      }
      append0PaddedUint(localStringBuilder, i, 4);
      if (paramInt != 4) {
        localStringBuilder.append(' ');
      }
    }
    if (paramInt != 4)
    {
      append0PaddedUint(localStringBuilder, HourFromTime(d2), 2);
      localStringBuilder.append(':');
      append0PaddedUint(localStringBuilder, MinFromTime(d2), 2);
      localStringBuilder.append(':');
      append0PaddedUint(localStringBuilder, SecFromTime(d2), 2);
      paramInt = (int)Math.floor((LocalTZA + DaylightSavingTA(paramDouble)) / 60000.0D);
      paramInt = paramInt % 60 + paramInt / 60 * 100;
      if (paramInt <= 0) {
        break label347;
      }
      localStringBuilder.append(" GMT+");
    }
    for (;;)
    {
      append0PaddedUint(localStringBuilder, paramInt, 4);
      if (timeZoneFormatter == null) {
        timeZoneFormatter = new SimpleDateFormat("zzz");
      }
      double d1 = paramDouble;
      if (paramDouble < 0.0D) {
        d1 = MakeDate(MakeDay(EquivalentYear(YearFromTime(d2)), MonthFromTime(paramDouble), DateFromTime(paramDouble)), TimeWithinDay(paramDouble));
      }
      localStringBuilder.append(" (");
      Date localDate = new Date(d1);
      synchronized (timeZoneFormatter)
      {
        localStringBuilder.append(timeZoneFormatter.format(localDate));
        localStringBuilder.append(')');
        return localStringBuilder.toString();
        label347:
        localStringBuilder.append(" GMT-");
        paramInt = -paramInt;
      }
    }
  }
  
  private static double date_msecFromArgs(Object[] paramArrayOfObject)
  {
    double[] arrayOfDouble = new double[7];
    int i = 0;
    if (i < 7)
    {
      if (i < paramArrayOfObject.length)
      {
        double d = ScriptRuntime.toNumber(paramArrayOfObject[i]);
        if ((d != d) || (Double.isInfinite(d))) {
          return ScriptRuntime.NaN;
        }
        arrayOfDouble[i] = ScriptRuntime.toInteger(paramArrayOfObject[i]);
      }
      for (;;)
      {
        i += 1;
        break;
        if (i == 2) {
          arrayOfDouble[i] = 1.0D;
        } else {
          arrayOfDouble[i] = 0.0D;
        }
      }
    }
    if ((arrayOfDouble[0] >= 0.0D) && (arrayOfDouble[0] <= 99.0D)) {
      arrayOfDouble[0] += 1900.0D;
    }
    return date_msecFromDate(arrayOfDouble[0], arrayOfDouble[1], arrayOfDouble[2], arrayOfDouble[3], arrayOfDouble[4], arrayOfDouble[5], arrayOfDouble[6]);
  }
  
  private static double date_msecFromDate(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6, double paramDouble7)
  {
    return MakeDate(MakeDay(paramDouble1, paramDouble2, paramDouble3), MakeTime(paramDouble4, paramDouble5, paramDouble6, paramDouble7));
  }
  
  private static double date_parseString(String paramString)
  {
    double d1 = parseISOString(paramString);
    if (d1 == d1) {
      return d1;
    }
    int m = -1;
    int j = -1;
    int i2 = -1;
    int i = -1;
    int i1 = -1;
    int k = -1;
    int i3 = 0;
    double d2 = -1.0D;
    int n = 0;
    int i9 = paramString.length();
    int i4 = 0;
    label1169:
    label1449:
    label1842:
    for (;;)
    {
      if (i3 < i9)
      {
        int i6 = paramString.charAt(i3);
        int i5 = i3 + 1;
        int i7;
        if ((i6 <= 32) || (i6 == 44) || (i6 == 45))
        {
          i3 = i5;
          if (i5 < i9)
          {
            i7 = paramString.charAt(i5);
            i3 = i5;
            if (i6 == 45)
            {
              i3 = i5;
              if (48 <= i7)
              {
                i3 = i5;
                if (i7 <= 57)
                {
                  i4 = i6;
                  i3 = i5;
                }
              }
            }
          }
        }
        else if (i6 == 40)
        {
          i3 = i5;
          i5 = 1;
          do
          {
            do
            {
              for (;;)
              {
                if (i3 >= i9) {
                  break label1842;
                }
                i7 = paramString.charAt(i3);
                i6 = i3 + 1;
                if (i7 != 40) {
                  break;
                }
                i5 += 1;
                i3 = i6;
              }
              i3 = i6;
            } while (i7 != 41);
            i7 = i5 - 1;
            i5 = i7;
            i3 = i6;
          } while (i7 > 0);
          i3 = i6;
        }
        else
        {
          int i8;
          if ((48 <= i6) && (i6 <= 57))
          {
            i3 = i6 - 48;
            for (;;)
            {
              i7 = i6;
              if (i5 >= i9) {
                break;
              }
              i6 = paramString.charAt(i5);
              i7 = i6;
              if (48 > i6) {
                break;
              }
              i7 = i6;
              if (i6 > 57) {
                break;
              }
              i3 = i3 * 10 + i6 - 48;
              i5 += 1;
            }
            if ((i4 == 43) || (i4 == 45))
            {
              if (i3 < 24) {}
              for (n = i3 * 60;; n = i3 / 100 * 60 + i3 % 100)
              {
                i3 = n;
                if (i4 == 43) {
                  i3 = -n;
                }
                if ((d2 == 0.0D) || (d2 == -1.0D)) {
                  break;
                }
                return ScriptRuntime.NaN;
              }
              d1 = i3;
              i6 = 1;
              i3 = i1;
              n = i2;
              i4 = k;
              i2 = m;
              i1 = j;
              m = i;
              k = i3;
              j = i4;
              i = i6;
            }
            for (;;)
            {
              i8 = 0;
              i4 = n;
              i6 = i1;
              i7 = i2;
              i1 = k;
              i3 = i5;
              n = i;
              k = j;
              i = m;
              i2 = i4;
              j = i6;
              m = i7;
              d2 = d1;
              i4 = i8;
              break;
              if ((i3 >= 70) || ((i4 == 47) && (j >= 0) && (i2 >= 0) && (m < 0)))
              {
                if (m >= 0) {
                  return ScriptRuntime.NaN;
                }
                if ((i7 <= 32) || (i7 == 44) || (i7 == 47) || (i5 >= i9))
                {
                  m = i3;
                  if (i3 < 100) {
                    m = i3 + 1900;
                  }
                  d1 = d2;
                  i4 = i1;
                  i6 = i;
                  i1 = j;
                  i3 = m;
                  i = n;
                  n = i2;
                  j = k;
                  k = i4;
                  m = i6;
                  i2 = i3;
                }
                else
                {
                  return ScriptRuntime.NaN;
                }
              }
              else if (i7 == 58)
              {
                if (i < 0)
                {
                  d1 = d2;
                  i = n;
                  i4 = j;
                  i6 = m;
                  n = i2;
                  j = k;
                  k = i1;
                  m = i3;
                  i1 = i4;
                  i2 = i6;
                }
                else if (i1 < 0)
                {
                  d1 = d2;
                  i1 = i3;
                  i4 = i;
                  i3 = m;
                  i6 = j;
                  i = n;
                  n = i2;
                  j = k;
                  k = i1;
                  m = i4;
                  i1 = i6;
                  i2 = i3;
                }
                else
                {
                  return ScriptRuntime.NaN;
                }
              }
              else if (i7 == 47)
              {
                if (j < 0)
                {
                  d1 = d2;
                  j = k;
                  k = i1;
                  i1 = i;
                  i4 = i3 - 1;
                  i3 = m;
                  i = n;
                  n = i2;
                  m = i1;
                  i1 = i4;
                  i2 = i3;
                }
                else if (i2 < 0)
                {
                  d1 = d2;
                  i4 = i1;
                  i6 = i;
                  i1 = j;
                  i2 = m;
                  i = n;
                  j = k;
                  k = i4;
                  m = i6;
                  n = i3;
                }
                else
                {
                  return ScriptRuntime.NaN;
                }
              }
              else
              {
                if ((i5 < i9) && (i7 != 44) && (i7 > 32) && (i7 != 45)) {
                  return ScriptRuntime.NaN;
                }
                if ((n != 0) && (i3 < 60))
                {
                  if (d2 < 0.0D)
                  {
                    d1 = d2 - i3;
                    i3 = i;
                    i = n;
                    i4 = m;
                    i6 = j;
                    n = i2;
                    j = k;
                    k = i1;
                    m = i3;
                    i1 = i6;
                    i2 = i4;
                  }
                  else
                  {
                    d1 = i3 + d2;
                    i3 = i;
                    i = n;
                    i4 = m;
                    i6 = j;
                    n = i2;
                    j = k;
                    k = i1;
                    m = i3;
                    i1 = i6;
                    i2 = i4;
                  }
                }
                else if ((i >= 0) && (i1 < 0))
                {
                  d1 = d2;
                  i1 = i3;
                  i4 = i;
                  i3 = m;
                  i6 = j;
                  i = n;
                  n = i2;
                  j = k;
                  k = i1;
                  m = i4;
                  i1 = i6;
                  i2 = i3;
                }
                else if ((i1 >= 0) && (k < 0))
                {
                  d1 = d2;
                  k = i1;
                  i4 = i;
                  i1 = j;
                  i = n;
                  i6 = m;
                  n = i2;
                  j = i3;
                  m = i4;
                  i2 = i6;
                }
                else
                {
                  if (i2 >= 0) {
                    break label1169;
                  }
                  d1 = d2;
                  i4 = i1;
                  i6 = i;
                  i1 = j;
                  i2 = m;
                  i = n;
                  j = k;
                  k = i4;
                  m = i6;
                  n = i3;
                }
              }
            }
            return ScriptRuntime.NaN;
          }
          if ((i6 == 47) || (i6 == 58) || (i6 == 43) || (i6 == 45))
          {
            i4 = i6;
            i3 = i5;
          }
          else
          {
            i7 = i5 - 1;
            i3 = i5;
            for (;;)
            {
              if (i3 < i9)
              {
                i5 = paramString.charAt(i3);
                if (((65 <= i5) && (i5 <= 90)) || ((97 <= i5) && (i5 <= 122))) {}
              }
              else
              {
                i8 = i3 - i7;
                if (i8 >= 2) {
                  break;
                }
                return ScriptRuntime.NaN;
              }
              i3 += 1;
            }
            i6 = 0;
            i5 = 0;
            for (;;)
            {
              int i10 = "am;pm;monday;tuesday;wednesday;thursday;friday;saturday;sunday;january;february;march;april;may;june;july;august;september;october;november;december;gmt;ut;utc;est;edt;cst;cdt;mst;mdt;pst;pdt;".indexOf(';', i6);
              if (i10 < 0) {
                return ScriptRuntime.NaN;
              }
              if ("am;pm;monday;tuesday;wednesday;thursday;friday;saturday;sunday;january;february;march;april;may;june;july;august;september;october;november;december;gmt;ut;utc;est;edt;cst;cdt;mst;mdt;pst;pdt;".regionMatches(true, i6, paramString, i7, i8))
              {
                if (i5 >= 2) {
                  break label1449;
                }
                if ((i <= 12) && (i >= 0)) {
                  break;
                }
                return ScriptRuntime.NaN;
              }
              i6 = i10 + 1;
              i5 += 1;
            }
            if (i5 == 0)
            {
              i5 = i;
              i6 = j;
              d1 = d2;
              if (i == 12)
              {
                i5 = 0;
                d1 = d2;
                i6 = j;
              }
            }
            for (;;)
            {
              i = i5;
              j = i6;
              d2 = d1;
              break;
              i5 = i;
              i6 = j;
              d1 = d2;
              if (i != 12)
              {
                i5 = i + 12;
                i6 = j;
                d1 = d2;
                continue;
                i7 = i5 - 2;
                i5 = i;
                i6 = j;
                d1 = d2;
                if (i7 >= 7)
                {
                  i6 = i7 - 7;
                  if (i6 < 12)
                  {
                    if (j < 0)
                    {
                      i5 = i;
                      d1 = d2;
                    }
                    else
                    {
                      return ScriptRuntime.NaN;
                    }
                  }
                  else {
                    switch (i6 - 12)
                    {
                    default: 
                      Kit.codeBug();
                      i5 = i;
                      i6 = j;
                      d1 = d2;
                      break;
                    case 0: 
                      d1 = 0.0D;
                      i5 = i;
                      i6 = j;
                      break;
                    case 1: 
                      d1 = 0.0D;
                      i5 = i;
                      i6 = j;
                      break;
                    case 2: 
                      d1 = 0.0D;
                      i5 = i;
                      i6 = j;
                      break;
                    case 3: 
                      d1 = 300.0D;
                      i5 = i;
                      i6 = j;
                      break;
                    case 4: 
                      d1 = 240.0D;
                      i5 = i;
                      i6 = j;
                      break;
                    case 5: 
                      d1 = 360.0D;
                      i5 = i;
                      i6 = j;
                      break;
                    case 6: 
                      d1 = 300.0D;
                      i5 = i;
                      i6 = j;
                      break;
                    case 7: 
                      d1 = 420.0D;
                      i5 = i;
                      i6 = j;
                      break;
                    case 8: 
                      d1 = 360.0D;
                      i5 = i;
                      i6 = j;
                      break;
                    case 9: 
                      d1 = 480.0D;
                      i5 = i;
                      i6 = j;
                      break;
                    case 10: 
                      d1 = 420.0D;
                      i5 = i;
                      i6 = j;
                    }
                  }
                }
              }
            }
          }
        }
      }
      else
      {
        if ((m < 0) || (j < 0) || (i2 < 0)) {
          return ScriptRuntime.NaN;
        }
        if (k < 0) {
          k = 0;
        }
        for (;;)
        {
          n = i1;
          if (i1 < 0) {
            n = 0;
          }
          i1 = i;
          if (i < 0) {
            i1 = 0;
          }
          d1 = date_msecFromDate(m, j, i2, i1, n, k, 0.0D);
          if (d2 == -1.0D) {
            return internalUTC(d1);
          }
          return d1 + 60000.0D * d2;
        }
      }
    }
  }
  
  static void init(Scriptable paramScriptable, boolean paramBoolean)
  {
    NativeDate localNativeDate = new NativeDate();
    localNativeDate.date = ScriptRuntime.NaN;
    localNativeDate.exportAsJSClass(47, paramScriptable, paramBoolean);
  }
  
  private static double internalUTC(double paramDouble)
  {
    return paramDouble - LocalTZA - DaylightSavingTA(paramDouble - LocalTZA);
  }
  
  private static Object jsConstructor(Object[] paramArrayOfObject)
  {
    NativeDate localNativeDate = new NativeDate();
    if (paramArrayOfObject.length == 0)
    {
      localNativeDate.date = now();
      return localNativeDate;
    }
    if (paramArrayOfObject.length == 1)
    {
      Object localObject = paramArrayOfObject[0];
      paramArrayOfObject = (Object[])localObject;
      if ((localObject instanceof Scriptable)) {
        paramArrayOfObject = ((Scriptable)localObject).getDefaultValue(null);
      }
      if ((paramArrayOfObject instanceof CharSequence)) {}
      for (d1 = date_parseString(paramArrayOfObject.toString());; d1 = ScriptRuntime.toNumber(paramArrayOfObject))
      {
        localNativeDate.date = TimeClip(d1);
        return localNativeDate;
      }
    }
    double d2 = date_msecFromArgs(paramArrayOfObject);
    double d1 = d2;
    if (!Double.isNaN(d2))
    {
      d1 = d2;
      if (!Double.isInfinite(d2)) {
        d1 = TimeClip(internalUTC(d2));
      }
    }
    localNativeDate.date = d1;
    return localNativeDate;
  }
  
  private static double jsStaticFunction_UTC(Object[] paramArrayOfObject)
  {
    return TimeClip(date_msecFromArgs(paramArrayOfObject));
  }
  
  private static String js_toISOString(double paramDouble)
  {
    StringBuilder localStringBuilder = new StringBuilder(27);
    int i = YearFromTime(paramDouble);
    if (i < 0)
    {
      localStringBuilder.append('-');
      append0PaddedUint(localStringBuilder, -i, 6);
    }
    for (;;)
    {
      localStringBuilder.append('-');
      append0PaddedUint(localStringBuilder, MonthFromTime(paramDouble) + 1, 2);
      localStringBuilder.append('-');
      append0PaddedUint(localStringBuilder, DateFromTime(paramDouble), 2);
      localStringBuilder.append('T');
      append0PaddedUint(localStringBuilder, HourFromTime(paramDouble), 2);
      localStringBuilder.append(':');
      append0PaddedUint(localStringBuilder, MinFromTime(paramDouble), 2);
      localStringBuilder.append(':');
      append0PaddedUint(localStringBuilder, SecFromTime(paramDouble), 2);
      localStringBuilder.append('.');
      append0PaddedUint(localStringBuilder, msFromTime(paramDouble), 3);
      localStringBuilder.append('Z');
      return localStringBuilder.toString();
      if (i > 9999) {
        append0PaddedUint(localStringBuilder, i, 6);
      } else {
        append0PaddedUint(localStringBuilder, i, 4);
      }
    }
  }
  
  private static String js_toUTCString(double paramDouble)
  {
    StringBuilder localStringBuilder = new StringBuilder(60);
    appendWeekDayName(localStringBuilder, WeekDay(paramDouble));
    localStringBuilder.append(", ");
    append0PaddedUint(localStringBuilder, DateFromTime(paramDouble), 2);
    localStringBuilder.append(' ');
    appendMonthName(localStringBuilder, MonthFromTime(paramDouble));
    localStringBuilder.append(' ');
    int j = YearFromTime(paramDouble);
    int i = j;
    if (j < 0)
    {
      localStringBuilder.append('-');
      i = -j;
    }
    append0PaddedUint(localStringBuilder, i, 4);
    localStringBuilder.append(' ');
    append0PaddedUint(localStringBuilder, HourFromTime(paramDouble), 2);
    localStringBuilder.append(':');
    append0PaddedUint(localStringBuilder, MinFromTime(paramDouble), 2);
    localStringBuilder.append(':');
    append0PaddedUint(localStringBuilder, SecFromTime(paramDouble), 2);
    localStringBuilder.append(" GMT");
    return localStringBuilder.toString();
  }
  
  private static double makeDate(double paramDouble, Object[] paramArrayOfObject, int paramInt)
  {
    if (paramArrayOfObject.length == 0) {
      return ScriptRuntime.NaN;
    }
    int j = 1;
    int k = 1;
    int i = 1;
    switch (paramInt)
    {
    default: 
      throw Kit.codeBug();
    case 40: 
      i = 0;
    case 39: 
      paramInt = 1;
      if (paramArrayOfObject.length >= paramInt) {
        break;
      }
    }
    for (k = paramArrayOfObject.length;; k = paramInt)
    {
      if (($assertionsDisabled) || ((1 <= k) && (k <= 3))) {
        break label131;
      }
      throw new AssertionError();
      j = 0;
      i = j;
      paramInt = 2;
      break;
      k = 0;
      i = k;
      paramInt = 3;
      break;
    }
    label131:
    double[] arrayOfDouble = new double[3];
    int m = 0;
    j = 0;
    double d1;
    if (j < k)
    {
      d1 = ScriptRuntime.toNumber(paramArrayOfObject[j]);
      if ((d1 != d1) || (Double.isInfinite(d1))) {
        m = 1;
      }
      for (;;)
      {
        j += 1;
        break;
        arrayOfDouble[j] = ScriptRuntime.toInteger(d1);
      }
    }
    if (m != 0) {
      return ScriptRuntime.NaN;
    }
    label244:
    double d2;
    if (paramDouble != paramDouble)
    {
      if (paramInt < 3) {
        return ScriptRuntime.NaN;
      }
      d1 = 0.0D;
      if ((paramInt < 3) || (k >= 0)) {
        break label346;
      }
      j = 1;
      paramDouble = arrayOfDouble[0];
      if ((paramInt < 2) || (j >= k)) {
        break label359;
      }
      m = j + 1;
      d2 = arrayOfDouble[j];
      j = m;
      label273:
      if ((paramInt < 1) || (j >= k)) {
        break label370;
      }
    }
    label346:
    label359:
    label370:
    for (double d3 = arrayOfDouble[j];; d3 = DateFromTime(d1))
    {
      d1 = MakeDate(MakeDay(paramDouble, d2, d3), TimeWithinDay(d1));
      paramDouble = d1;
      if (i != 0) {
        paramDouble = internalUTC(d1);
      }
      return TimeClip(paramDouble);
      d1 = paramDouble;
      if (i == 0) {
        break;
      }
      d1 = LocalTime(paramDouble);
      break;
      paramDouble = YearFromTime(d1);
      j = 0;
      break label244;
      d2 = MonthFromTime(d1);
      break label273;
    }
  }
  
  private static double makeTime(double paramDouble, Object[] paramArrayOfObject, int paramInt)
  {
    if (paramArrayOfObject.length == 0) {
      return ScriptRuntime.NaN;
    }
    int j = 1;
    int k = 1;
    int m = 1;
    int i = 1;
    switch (paramInt)
    {
    default: 
      throw Kit.codeBug();
    case 32: 
      i = 0;
    case 31: 
      paramInt = 1;
      if (paramArrayOfObject.length >= paramInt) {
        break;
      }
    }
    for (k = paramArrayOfObject.length;; k = paramInt)
    {
      if (($assertionsDisabled) || (k <= 4)) {
        break label149;
      }
      throw new AssertionError();
      j = 0;
      i = j;
      paramInt = 2;
      break;
      k = 0;
      i = k;
      paramInt = 3;
      break;
      m = 0;
      i = m;
      paramInt = 4;
      break;
    }
    label149:
    double[] arrayOfDouble = new double[4];
    m = 0;
    j = 0;
    if (j < k)
    {
      d1 = ScriptRuntime.toNumber(paramArrayOfObject[j]);
      if ((d1 != d1) || (Double.isInfinite(d1))) {
        m = 1;
      }
      for (;;)
      {
        j += 1;
        break;
        arrayOfDouble[j] = ScriptRuntime.toInteger(d1);
      }
    }
    if ((m != 0) || (paramDouble != paramDouble)) {
      return ScriptRuntime.NaN;
    }
    double d1 = paramDouble;
    if (i != 0) {
      d1 = LocalTime(paramDouble);
    }
    double d2;
    label293:
    double d3;
    if ((paramInt >= 4) && (k < 0))
    {
      j = 1;
      paramDouble = arrayOfDouble[0];
      if ((paramInt < 3) || (j >= k)) {
        break label395;
      }
      m = j + 1;
      d2 = arrayOfDouble[j];
      j = m;
      if ((paramInt < 2) || (j >= k)) {
        break label406;
      }
      m = j + 1;
      d3 = arrayOfDouble[j];
      j = m;
      label322:
      if ((paramInt < 1) || (j >= k)) {
        break label417;
      }
    }
    label395:
    label406:
    label417:
    for (double d4 = arrayOfDouble[j];; d4 = msFromTime(d1))
    {
      paramDouble = MakeTime(paramDouble, d2, d3, d4);
      d1 = MakeDate(Day(d1), paramDouble);
      paramDouble = d1;
      if (i != 0) {
        paramDouble = internalUTC(d1);
      }
      return TimeClip(paramDouble);
      paramDouble = HourFromTime(d1);
      j = 0;
      break;
      d2 = MinFromTime(d1);
      break label293;
      d3 = SecFromTime(d1);
      break label322;
    }
  }
  
  private static int msFromTime(double paramDouble)
  {
    double d = paramDouble % 1000.0D;
    paramDouble = d;
    if (d < 0.0D) {
      paramDouble = d + 1000.0D;
    }
    return (int)paramDouble;
  }
  
  private static double now()
  {
    return System.currentTimeMillis();
  }
  
  private static double parseISOString(String paramString)
  {
    int[] arrayOfInt = new int[9];
    int[] tmp8_6 = arrayOfInt;
    tmp8_6[0] = '?';
    int[] tmp14_8 = tmp8_6;
    tmp14_8[1] = 1;
    int[] tmp18_14 = tmp14_8;
    tmp18_14[2] = 1;
    int[] tmp22_18 = tmp18_14;
    tmp22_18[3] = 0;
    int[] tmp26_22 = tmp22_18;
    tmp26_22[4] = 0;
    int[] tmp30_26 = tmp26_22;
    tmp30_26[5] = 0;
    int[] tmp34_30 = tmp30_26;
    tmp34_30[6] = 0;
    int[] tmp39_34 = tmp34_30;
    tmp39_34[7] = -1;
    int[] tmp44_39 = tmp39_34;
    tmp44_39[8] = -1;
    tmp44_39;
    int i2 = 4;
    int i1 = 1;
    int j = 0;
    int i4 = paramString.length();
    int i;
    int k;
    int n;
    if (i4 != 0)
    {
      i = paramString.charAt(0);
      if ((i == 43) || (i == 45))
      {
        j = 1;
        i2 = 6;
        if (i == 45)
        {
          i = -1;
          k = 0;
          n = i;
          i = k;
        }
      }
    }
    for (;;)
    {
      label119:
      k = i;
      int m = j;
      if (i != -1)
      {
        if (i != 0) {
          break label207;
        }
        k = i2;
        label142:
        m = j + k;
        if (m <= i4) {
          break label226;
        }
        k = -1;
        m = j;
      }
      double d2;
      label180:
      label207:
      label226:
      int i3;
      for (;;)
      {
        if ((k == -1) || (m != i4))
        {
          d2 = ScriptRuntime.NaN;
          return d2;
          i = 1;
          break;
          if (i != 84) {
            break label980;
          }
          j = 1;
          n = 1;
          i = 3;
          break label119;
          if (i == 6)
          {
            k = 3;
            break label142;
          }
          k = 2;
          break label142;
          k = 0;
          for (;;)
          {
            if (j >= m) {
              break label290;
            }
            i3 = paramString.charAt(j);
            if ((i3 < 48) || (i3 > 57))
            {
              k = -1;
              m = j;
              break;
            }
            k = k * 10 + (i3 - 48);
            j += 1;
          }
          label290:
          tmp14_8[i] = k;
          if (j == i4)
          {
            switch (i)
            {
            }
            for (;;)
            {
              k = i;
              m = j;
              break;
              i = -1;
            }
          }
          i3 = j + 1;
          k = paramString.charAt(j);
          if (k == 90)
          {
            tmp14_8[7] = 0;
            tmp14_8[8] = 0;
            k = i;
            m = i3;
            switch (i)
            {
            }
            k = -1;
            m = i3;
          }
          else
          {
            switch (i)
            {
            default: 
              if (i != 7) {
                break label966;
              }
              if (k == 45)
              {
                j = -1;
                i1 = j;
                j = i3;
              }
              break;
            case 0: 
            case 1: 
              if (k == 45) {
                i += 1;
              }
              for (;;)
              {
                break;
                if (k == 84) {
                  i = 3;
                } else {
                  i = -1;
                }
              }
            case 2: 
              if (k == 84) {}
              for (i = 3;; i = -1) {
                break;
              }
            case 3: 
              if (k == 58) {}
              for (i = 4;; i = -1) {
                break;
              }
            case 7: 
              label484:
              label501:
              if (k == 58) {
                break label973;
              }
            }
          }
        }
      }
      label964:
      label966:
      label973:
      for (i = i3 - 1;; i = i3)
      {
        j = 8;
        i3 = i;
        i = j;
        break label484;
        if (k == 58) {
          i = 5;
        }
        for (;;)
        {
          break;
          if ((k == 43) || (k == 45)) {
            i = 7;
          } else {
            i = -1;
          }
        }
        if (k == 46) {
          i = 6;
        }
        for (;;)
        {
          break;
          if ((k == 43) || (k == 45)) {
            i = 7;
          } else {
            i = -1;
          }
        }
        if ((k == 43) || (k == 45)) {}
        for (i = 7;; i = -1) {
          break;
        }
        i = -1;
        break label484;
        j = 1;
        break label501;
        i = tmp14_8[0];
        j = tmp14_8[1];
        k = tmp14_8[2];
        m = tmp14_8[3];
        i2 = tmp14_8[4];
        i3 = tmp14_8[5];
        i4 = tmp14_8[6];
        int i5 = tmp14_8[7];
        int i6 = tmp14_8[8];
        if ((i > 275943) || (j < 1) || (j > 12) || (k < 1) || (k > DaysInMonth(i, j)) || (m > 24) || ((m == 24) && ((i2 > 0) || (i3 > 0) || (i4 > 0))) || (i2 > 59) || (i3 > 59) || (i5 > 23) || (i6 > 59)) {
          break;
        }
        double d1 = date_msecFromDate(i * n, j - 1, k, m, i2, i3, i4);
        if (i5 == -1) {}
        for (;;)
        {
          if (d1 < -8.64E15D) {
            break label964;
          }
          d2 = d1;
          if (d1 <= 8.64E15D) {
            break label180;
          }
          break;
          d1 -= (i5 * 60 + i6) * 60000.0D * i1;
        }
        break;
        j = i1;
        break label501;
      }
      label980:
      n = 1;
      i = 0;
    }
  }
  
  private static String toLocale_helper(double paramDouble, int paramInt)
  {
    switch (paramInt)
    {
    default: 
      throw new AssertionError();
    case 5: 
      if (localeDateTimeFormatter == null) {
        localeDateTimeFormatter = DateFormat.getDateTimeInstance(1, 1);
      }
      break;
    }
    synchronized (localeDateTimeFormatter)
    {
      for (;;)
      {
        String str = ???.format(new Date(paramDouble));
        return str;
        if (localeTimeFormatter == null) {
          localeTimeFormatter = DateFormat.getTimeInstance(1);
        }
        ??? = localeTimeFormatter;
      }
      if (localeDateFormatter == null) {
        localeDateFormatter = DateFormat.getDateInstance(1);
      }
      ??? = localeDateFormatter;
    }
  }
  
  public Object execIdCall(IdFunctionObject paramIdFunctionObject, Context paramContext, Scriptable paramScriptable1, Scriptable paramScriptable2, Object[] paramArrayOfObject)
  {
    if (!paramIdFunctionObject.hasTag(DATE_TAG)) {
      paramIdFunctionObject = super.execIdCall(paramIdFunctionObject, paramContext, paramScriptable1, paramScriptable2, paramArrayOfObject);
    }
    int i;
    do
    {
      return paramIdFunctionObject;
      i = paramIdFunctionObject.methodId();
      switch (i)
      {
      default: 
        if ((paramScriptable2 instanceof NativeDate)) {
          break;
        }
        throw incompatibleCallError(paramIdFunctionObject);
      case -3: 
        return ScriptRuntime.wrapNumber(now());
      case -2: 
        return ScriptRuntime.wrapNumber(date_parseString(ScriptRuntime.toString(paramArrayOfObject, 0)));
      case -1: 
        return ScriptRuntime.wrapNumber(jsStaticFunction_UTC(paramArrayOfObject));
      case 1: 
        if (paramScriptable2 != null) {
          return date_format(now(), 2);
        }
        return jsConstructor(paramArrayOfObject);
      case 47: 
        paramIdFunctionObject = ScriptRuntime.toObject(paramContext, paramScriptable1, paramScriptable2);
        paramScriptable2 = ScriptRuntime.toPrimitive(paramIdFunctionObject, ScriptRuntime.NumberClass);
        if ((paramScriptable2 instanceof Number))
        {
          d1 = ((Number)paramScriptable2).doubleValue();
          if ((d1 != d1) || (Double.isInfinite(d1))) {
            return null;
          }
        }
        paramScriptable2 = ScriptableObject.getProperty(paramIdFunctionObject, "toISOString");
        if (paramScriptable2 == NOT_FOUND) {
          throw ScriptRuntime.typeError2("msg.function.not.found.in", "toISOString", ScriptRuntime.toString(paramIdFunctionObject));
        }
        if (!(paramScriptable2 instanceof Callable)) {
          throw ScriptRuntime.typeError3("msg.isnt.function.in", "toISOString", ScriptRuntime.toString(paramIdFunctionObject), ScriptRuntime.toString(paramScriptable2));
        }
        paramContext = ((Callable)paramScriptable2).call(paramContext, paramScriptable1, paramIdFunctionObject, ScriptRuntime.emptyArgs);
        paramIdFunctionObject = paramContext;
      }
    } while (ScriptRuntime.isPrimitive(paramContext));
    throw ScriptRuntime.typeError1("msg.toisostring.must.return.primitive", ScriptRuntime.toString(paramContext));
    paramIdFunctionObject = (NativeDate)paramScriptable2;
    double d1 = paramIdFunctionObject.date;
    double d2;
    switch (i)
    {
    default: 
      throw new IllegalArgumentException(String.valueOf(i));
    case 2: 
    case 3: 
    case 4: 
      if (d1 == d1) {
        return date_format(d1, i);
      }
      return "Invalid Date";
    case 5: 
    case 6: 
    case 7: 
      if (d1 == d1) {
        return toLocale_helper(d1, i);
      }
      return "Invalid Date";
    case 8: 
      if (d1 == d1) {
        return js_toUTCString(d1);
      }
      return "Invalid Date";
    case 9: 
      return "(new Date(" + ScriptRuntime.toString(d1) + "))";
    case 10: 
    case 11: 
      return ScriptRuntime.wrapNumber(d1);
    case 12: 
    case 13: 
    case 14: 
      d2 = d1;
      if (d1 == d1)
      {
        d2 = d1;
        if (i != 14) {
          d2 = LocalTime(d1);
        }
        d1 = YearFromTime(d2);
        d2 = d1;
        if (i == 12)
        {
          if (!paramContext.hasFeature(1)) {
            break label702;
          }
          d2 = d1;
          if (1900.0D <= d1)
          {
            d2 = d1;
            if (d1 >= 2000.0D) {}
          }
        }
      }
      for (d2 = d1 - 1900.0D;; d2 = d1 - 1900.0D) {
        return ScriptRuntime.wrapNumber(d2);
      }
    case 15: 
    case 16: 
      d2 = d1;
      if (d1 == d1)
      {
        d2 = d1;
        if (i == 15) {
          d2 = LocalTime(d1);
        }
        d2 = MonthFromTime(d2);
      }
      return ScriptRuntime.wrapNumber(d2);
    case 17: 
    case 18: 
      d2 = d1;
      if (d1 == d1)
      {
        d2 = d1;
        if (i == 17) {
          d2 = LocalTime(d1);
        }
        d2 = DateFromTime(d2);
      }
      return ScriptRuntime.wrapNumber(d2);
    case 19: 
    case 20: 
      d2 = d1;
      if (d1 == d1)
      {
        d2 = d1;
        if (i == 19) {
          d2 = LocalTime(d1);
        }
        d2 = WeekDay(d2);
      }
      return ScriptRuntime.wrapNumber(d2);
    case 21: 
    case 22: 
      d2 = d1;
      if (d1 == d1)
      {
        d2 = d1;
        if (i == 21) {
          d2 = LocalTime(d1);
        }
        d2 = HourFromTime(d2);
      }
      return ScriptRuntime.wrapNumber(d2);
    case 23: 
    case 24: 
      d2 = d1;
      if (d1 == d1)
      {
        d2 = d1;
        if (i == 23) {
          d2 = LocalTime(d1);
        }
        d2 = MinFromTime(d2);
      }
      return ScriptRuntime.wrapNumber(d2);
    case 25: 
    case 26: 
      d2 = d1;
      if (d1 == d1)
      {
        d2 = d1;
        if (i == 25) {
          d2 = LocalTime(d1);
        }
        d2 = SecFromTime(d2);
      }
      return ScriptRuntime.wrapNumber(d2);
    case 27: 
    case 28: 
      d2 = d1;
      if (d1 == d1)
      {
        d2 = d1;
        if (i == 27) {
          d2 = LocalTime(d1);
        }
        d2 = msFromTime(d2);
      }
      return ScriptRuntime.wrapNumber(d2);
    case 29: 
      d2 = d1;
      if (d1 == d1) {
        d2 = (d1 - LocalTime(d1)) / 60000.0D;
      }
      return ScriptRuntime.wrapNumber(d2);
    case 30: 
      d1 = TimeClip(ScriptRuntime.toNumber(paramArrayOfObject, 0));
      paramIdFunctionObject.date = d1;
      return ScriptRuntime.wrapNumber(d1);
    case 31: 
    case 32: 
    case 33: 
    case 34: 
    case 35: 
    case 36: 
    case 37: 
    case 38: 
      d1 = makeTime(d1, paramArrayOfObject, i);
      paramIdFunctionObject.date = d1;
      return ScriptRuntime.wrapNumber(d1);
    case 39: 
    case 40: 
    case 41: 
    case 42: 
    case 43: 
    case 44: 
      d1 = makeDate(d1, paramArrayOfObject, i);
      paramIdFunctionObject.date = d1;
      return ScriptRuntime.wrapNumber(d1);
    case 45: 
      label702:
      d2 = ScriptRuntime.toNumber(paramArrayOfObject, 0);
      if ((d2 != d2) || (Double.isInfinite(d2)))
      {
        d1 = ScriptRuntime.NaN;
        paramIdFunctionObject.date = d1;
        return ScriptRuntime.wrapNumber(d1);
      }
      if (d1 != d1)
      {
        d1 = 0.0D;
        label1174:
        if ((d2 < 0.0D) || (d2 > 99.0D)) {
          break label1271;
        }
        d2 += 1900.0D;
      }
      break;
    }
    label1271:
    for (;;)
    {
      d1 = TimeClip(internalUTC(MakeDate(MakeDay(d2, MonthFromTime(d1), DateFromTime(d1)), TimeWithinDay(d1))));
      break;
      d1 = LocalTime(d1);
      break label1174;
      if (d1 == d1) {
        return js_toISOString(d1);
      }
      throw ScriptRuntime.constructError("RangeError", ScriptRuntime.getMessage0("msg.invalid.date"));
    }
  }
  
  protected void fillConstructorProperties(IdFunctionObject paramIdFunctionObject)
  {
    addIdFunctionProperty(paramIdFunctionObject, DATE_TAG, -3, "now", 0);
    addIdFunctionProperty(paramIdFunctionObject, DATE_TAG, -2, "parse", 1);
    addIdFunctionProperty(paramIdFunctionObject, DATE_TAG, -1, "UTC", 7);
    super.fillConstructorProperties(paramIdFunctionObject);
  }
  
  protected int findPrototypeId(String paramString)
  {
    String str;
    int i;
    switch (paramString.length())
    {
    case 16: 
    default: 
      str = null;
      i = 0;
    }
    for (;;)
    {
      if ((str == null) || (str == paramString) || (str.equals(paramString))) {
        return i;
      }
      return 0;
      i = paramString.charAt(0);
      if (i == 103)
      {
        i = 19;
        str = "getDay";
      }
      else
      {
        if (i != 116) {
          break;
        }
        i = 47;
        str = "toJSON";
        continue;
        switch (paramString.charAt(3))
        {
        default: 
          str = null;
          i = 0;
          break;
        case 'D': 
          i = paramString.charAt(0);
          if (i == 103)
          {
            i = 17;
            str = "getDate";
          }
          else
          {
            if (i != 115) {
              break;
            }
            i = 39;
            str = "setDate";
          }
          break;
        case 'T': 
          i = paramString.charAt(0);
          if (i == 103)
          {
            i = 11;
            str = "getTime";
          }
          else
          {
            if (i != 115) {
              break;
            }
            i = 30;
            str = "setTime";
          }
          break;
        case 'Y': 
          i = paramString.charAt(0);
          if (i == 103)
          {
            i = 12;
            str = "getYear";
          }
          else
          {
            if (i != 115) {
              break;
            }
            i = 45;
            str = "setYear";
          }
          break;
        case 'u': 
          i = 10;
          str = "valueOf";
          continue;
          switch (paramString.charAt(3))
          {
          default: 
            str = null;
            i = 0;
            break;
          case 'H': 
            i = paramString.charAt(0);
            if (i == 103)
            {
              i = 21;
              str = "getHours";
            }
            else
            {
              if (i != 115) {
                break;
              }
              i = 37;
              str = "setHours";
            }
            break;
          case 'M': 
            i = paramString.charAt(0);
            if (i == 103)
            {
              i = 15;
              str = "getMonth";
            }
            else
            {
              if (i != 115) {
                break;
              }
              i = 41;
              str = "setMonth";
            }
            break;
          case 'o': 
            i = 9;
            str = "toSource";
            break;
          case 't': 
            i = 2;
            str = "toString";
            continue;
            i = 20;
            str = "getUTCDay";
            continue;
            i = paramString.charAt(3);
            if (i == 77)
            {
              i = paramString.charAt(0);
              if (i == 103)
              {
                i = 23;
                str = "getMinutes";
                continue;
              }
              if (i != 115) {
                break;
              }
              i = 35;
              str = "setMinutes";
              continue;
            }
            if (i == 83)
            {
              i = paramString.charAt(0);
              if (i == 103)
              {
                i = 25;
                str = "getSeconds";
                continue;
              }
              if (i != 115) {
                break;
              }
              i = 33;
              str = "setSeconds";
              continue;
            }
            if (i != 85) {
              break;
            }
            i = paramString.charAt(0);
            if (i == 103)
            {
              i = 18;
              str = "getUTCDate";
            }
            else
            {
              if (i != 115) {
                break;
              }
              i = 40;
              str = "setUTCDate";
              continue;
              switch (paramString.charAt(3))
              {
              default: 
                str = null;
                i = 0;
                break;
              case 'F': 
                i = paramString.charAt(0);
                if (i == 103)
                {
                  i = 13;
                  str = "getFullYear";
                }
                else
                {
                  if (i != 115) {
                    break;
                  }
                  i = 43;
                  str = "setFullYear";
                }
                break;
              case 'M': 
                i = 8;
                str = "toGMTString";
                break;
              case 'S': 
                i = 46;
                str = "toISOString";
                break;
              case 'T': 
                i = 8;
                str = "toUTCString";
                break;
              case 'U': 
                i = paramString.charAt(0);
                if (i == 103)
                {
                  i = paramString.charAt(9);
                  if (i == 114)
                  {
                    i = 22;
                    str = "getUTCHours";
                    continue;
                  }
                  if (i != 116) {
                    break;
                  }
                  i = 16;
                  str = "getUTCMonth";
                  continue;
                }
                if (i != 115) {
                  break;
                }
                i = paramString.charAt(9);
                if (i == 114)
                {
                  i = 38;
                  str = "setUTCHours";
                }
                else
                {
                  if (i != 116) {
                    break;
                  }
                  i = 42;
                  str = "setUTCMonth";
                }
                break;
              case 's': 
                i = 1;
                str = "constructor";
                continue;
                i = paramString.charAt(2);
                if (i == 68)
                {
                  i = 4;
                  str = "toDateString";
                }
                else
                {
                  if (i != 84) {
                    break;
                  }
                  i = 3;
                  str = "toTimeString";
                  continue;
                  i = paramString.charAt(0);
                  if (i == 103)
                  {
                    i = paramString.charAt(6);
                    if (i == 77)
                    {
                      i = 24;
                      str = "getUTCMinutes";
                      continue;
                    }
                    if (i != 83) {
                      break;
                    }
                    i = 26;
                    str = "getUTCSeconds";
                    continue;
                  }
                  if (i != 115) {
                    break;
                  }
                  i = paramString.charAt(6);
                  if (i == 77)
                  {
                    i = 36;
                    str = "setUTCMinutes";
                  }
                  else
                  {
                    if (i != 83) {
                      break;
                    }
                    i = 34;
                    str = "setUTCSeconds";
                    continue;
                    i = paramString.charAt(0);
                    if (i == 103)
                    {
                      i = 14;
                      str = "getUTCFullYear";
                    }
                    else if (i == 115)
                    {
                      i = 44;
                      str = "setUTCFullYear";
                    }
                    else
                    {
                      if (i != 116) {
                        break;
                      }
                      i = 5;
                      str = "toLocaleString";
                      continue;
                      i = paramString.charAt(0);
                      if (i == 103)
                      {
                        i = 27;
                        str = "getMilliseconds";
                      }
                      else
                      {
                        if (i != 115) {
                          break;
                        }
                        i = 31;
                        str = "setMilliseconds";
                        continue;
                        i = 29;
                        str = "getTimezoneOffset";
                        continue;
                        i = paramString.charAt(0);
                        if (i == 103)
                        {
                          i = 28;
                          str = "getUTCMilliseconds";
                        }
                        else if (i == 115)
                        {
                          i = 32;
                          str = "setUTCMilliseconds";
                        }
                        else
                        {
                          if (i != 116) {
                            break;
                          }
                          i = paramString.charAt(8);
                          if (i == 68)
                          {
                            i = 7;
                            str = "toLocaleDateString";
                          }
                          else
                          {
                            if (i != 84) {
                              break;
                            }
                            i = 6;
                            str = "toLocaleTimeString";
                          }
                        }
                      }
                    }
                  }
                }
                break;
              }
            }
            break;
          }
          break;
        }
      }
    }
    return i;
  }
  
  public String getClassName()
  {
    return "Date";
  }
  
  public Object getDefaultValue(Class<?> paramClass)
  {
    Object localObject = paramClass;
    if (paramClass == null) {
      localObject = ScriptRuntime.StringClass;
    }
    return super.getDefaultValue((Class)localObject);
  }
  
  double getJSTimeValue()
  {
    return this.date;
  }
  
  protected void initPrototypeId(int paramInt)
  {
    int i = 0;
    String str;
    switch (paramInt)
    {
    default: 
      throw new IllegalArgumentException(String.valueOf(paramInt));
    case 1: 
      i = 7;
      str = "constructor";
    }
    for (;;)
    {
      initPrototypeMethod(DATE_TAG, paramInt, str, i);
      return;
      str = "toString";
      continue;
      str = "toTimeString";
      continue;
      str = "toDateString";
      continue;
      str = "toLocaleString";
      continue;
      str = "toLocaleTimeString";
      continue;
      str = "toLocaleDateString";
      continue;
      str = "toUTCString";
      continue;
      str = "toSource";
      continue;
      str = "valueOf";
      continue;
      str = "getTime";
      continue;
      str = "getYear";
      continue;
      str = "getFullYear";
      continue;
      str = "getUTCFullYear";
      continue;
      str = "getMonth";
      continue;
      str = "getUTCMonth";
      continue;
      str = "getDate";
      continue;
      str = "getUTCDate";
      continue;
      str = "getDay";
      continue;
      str = "getUTCDay";
      continue;
      str = "getHours";
      continue;
      str = "getUTCHours";
      continue;
      str = "getMinutes";
      continue;
      str = "getUTCMinutes";
      continue;
      str = "getSeconds";
      continue;
      str = "getUTCSeconds";
      continue;
      str = "getMilliseconds";
      continue;
      str = "getUTCMilliseconds";
      continue;
      str = "getTimezoneOffset";
      continue;
      str = "setTime";
      i = 1;
      continue;
      str = "setMilliseconds";
      i = 1;
      continue;
      str = "setUTCMilliseconds";
      i = 1;
      continue;
      str = "setSeconds";
      i = 2;
      continue;
      str = "setUTCSeconds";
      i = 2;
      continue;
      str = "setMinutes";
      i = 3;
      continue;
      str = "setUTCMinutes";
      i = 3;
      continue;
      str = "setHours";
      i = 4;
      continue;
      str = "setUTCHours";
      i = 4;
      continue;
      str = "setDate";
      i = 1;
      continue;
      str = "setUTCDate";
      i = 1;
      continue;
      str = "setMonth";
      i = 2;
      continue;
      str = "setUTCMonth";
      i = 2;
      continue;
      str = "setFullYear";
      i = 3;
      continue;
      str = "setUTCFullYear";
      i = 3;
      continue;
      str = "setYear";
      i = 1;
      continue;
      str = "toISOString";
      continue;
      str = "toJSON";
      i = 1;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\NativeDate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */