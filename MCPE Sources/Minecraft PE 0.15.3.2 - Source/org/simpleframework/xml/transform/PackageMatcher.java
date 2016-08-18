package org.simpleframework.xml.transform;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Currency;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

class PackageMatcher
  implements Matcher
{
  private Transform matchEnum(Class paramClass)
  {
    Class localClass = paramClass.getSuperclass();
    if (localClass != null)
    {
      if (localClass.isEnum()) {
        return new EnumTransform(paramClass);
      }
      if (paramClass.isEnum()) {
        return new EnumTransform(paramClass);
      }
    }
    return null;
  }
  
  private Transform matchFile(Class paramClass)
    throws Exception
  {
    if (paramClass == File.class) {
      return new FileTransform();
    }
    return null;
  }
  
  private Transform matchLanguage(Class paramClass)
    throws Exception
  {
    if (paramClass == Boolean.class) {
      return new BooleanTransform();
    }
    if (paramClass == Integer.class) {
      return new IntegerTransform();
    }
    if (paramClass == Long.class) {
      return new LongTransform();
    }
    if (paramClass == Double.class) {
      return new DoubleTransform();
    }
    if (paramClass == Float.class) {
      return new FloatTransform();
    }
    if (paramClass == Short.class) {
      return new ShortTransform();
    }
    if (paramClass == Byte.class) {
      return new ByteTransform();
    }
    if (paramClass == Character.class) {
      return new CharacterTransform();
    }
    if (paramClass == String.class) {
      return new StringTransform();
    }
    if (paramClass == Class.class) {
      return new ClassTransform();
    }
    return null;
  }
  
  private Transform matchMath(Class paramClass)
    throws Exception
  {
    if (paramClass == BigDecimal.class) {
      return new BigDecimalTransform();
    }
    if (paramClass == BigInteger.class) {
      return new BigIntegerTransform();
    }
    return null;
  }
  
  private Transform matchSQL(Class paramClass)
    throws Exception
  {
    if (paramClass == Time.class) {
      return new DateTransform(paramClass);
    }
    if (paramClass == java.sql.Date.class) {
      return new DateTransform(paramClass);
    }
    if (paramClass == Timestamp.class) {
      return new DateTransform(paramClass);
    }
    return null;
  }
  
  private Transform matchURL(Class paramClass)
    throws Exception
  {
    if (paramClass == URL.class) {
      return new URLTransform();
    }
    return null;
  }
  
  private Transform matchUtility(Class paramClass)
    throws Exception
  {
    if (paramClass == java.util.Date.class) {
      return new DateTransform(paramClass);
    }
    if (paramClass == Locale.class) {
      return new LocaleTransform();
    }
    if (paramClass == Currency.class) {
      return new CurrencyTransform();
    }
    if (paramClass == GregorianCalendar.class) {
      return new GregorianCalendarTransform();
    }
    if (paramClass == TimeZone.class) {
      return new TimeZoneTransform();
    }
    if (paramClass == AtomicInteger.class) {
      return new AtomicIntegerTransform();
    }
    if (paramClass == AtomicLong.class) {
      return new AtomicLongTransform();
    }
    return null;
  }
  
  public Transform match(Class paramClass)
    throws Exception
  {
    String str = paramClass.getName();
    if (str.startsWith("java.lang")) {
      return matchLanguage(paramClass);
    }
    if (str.startsWith("java.util")) {
      return matchUtility(paramClass);
    }
    if (str.startsWith("java.net")) {
      return matchURL(paramClass);
    }
    if (str.startsWith("java.io")) {
      return matchFile(paramClass);
    }
    if (str.startsWith("java.sql")) {
      return matchSQL(paramClass);
    }
    if (str.startsWith("java.math")) {
      return matchMath(paramClass);
    }
    return matchEnum(paramClass);
  }
}


/* Location:              C:\Users\Genbu Hase\�h�L�������g\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\transform\PackageMatcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */