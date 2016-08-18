package com.microsoft.xbox.service.model.serialization;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class UTCDateConverterGson
{
  private static final int NO_MS_STRING_LENGTH = 19;
  private static SimpleDateFormat defaultFormatMs;
  private static SimpleDateFormat defaultFormatNoMs = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
  private static SimpleDateFormat shortDateAlternateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.ENGLISH);
  private static SimpleDateFormat shortDateFormat;
  
  static
  {
    defaultFormatMs = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.ENGLISH);
    shortDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.ENGLISH);
  }
  
  /* Error */
  public static Date convert(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: ldc 2
    //   5: monitorenter
    //   6: aload_0
    //   7: invokestatic 71	com/microsoft/xbox/toolkit/JavaUtil:isNullOrEmpty	(Ljava/lang/String;)Z
    //   10: istore_2
    //   11: iload_2
    //   12: ifeq +11 -> 23
    //   15: aload 5
    //   17: astore_0
    //   18: ldc 2
    //   20: monitorexit
    //   21: aload_0
    //   22: areturn
    //   23: aload_0
    //   24: astore 4
    //   26: aload_0
    //   27: ldc 73
    //   29: invokevirtual 78	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   32: ifeq +13 -> 45
    //   35: aload_0
    //   36: ldc 73
    //   38: ldc 80
    //   40: invokevirtual 84	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   43: astore 4
    //   45: aconst_null
    //   46: astore 6
    //   48: aload 4
    //   50: ldc 86
    //   52: invokevirtual 78	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   55: ifeq +64 -> 119
    //   58: aload 4
    //   60: ldc 86
    //   62: ldc 80
    //   64: invokevirtual 84	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   67: astore_0
    //   68: aload 6
    //   70: astore_3
    //   71: aload_0
    //   72: invokevirtual 90	java/lang/String:length	()I
    //   75: bipush 19
    //   77: if_icmpne +103 -> 180
    //   80: iconst_1
    //   81: istore_1
    //   82: aload_3
    //   83: astore 4
    //   85: aload_3
    //   86: ifnonnull +10 -> 96
    //   89: ldc 92
    //   91: invokestatic 98	java/util/TimeZone:getTimeZone	(Ljava/lang/String;)Ljava/util/TimeZone;
    //   94: astore 4
    //   96: iload_1
    //   97: ifeq +88 -> 185
    //   100: getstatic 42	com/microsoft/xbox/service/model/serialization/UTCDateConverterGson:defaultFormatNoMs	Ljava/text/SimpleDateFormat;
    //   103: aload 4
    //   105: invokevirtual 102	java/text/SimpleDateFormat:setTimeZone	(Ljava/util/TimeZone;)V
    //   108: getstatic 42	com/microsoft/xbox/service/model/serialization/UTCDateConverterGson:defaultFormatNoMs	Ljava/text/SimpleDateFormat;
    //   111: aload_0
    //   112: invokevirtual 105	java/text/SimpleDateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
    //   115: astore_0
    //   116: goto -98 -> 18
    //   119: aload 4
    //   121: ldc 107
    //   123: invokevirtual 78	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   126: ifeq +22 -> 148
    //   129: aload 4
    //   131: ldc 107
    //   133: ldc 80
    //   135: invokevirtual 84	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   138: astore_0
    //   139: ldc 109
    //   141: invokestatic 98	java/util/TimeZone:getTimeZone	(Ljava/lang/String;)Ljava/util/TimeZone;
    //   144: astore_3
    //   145: goto -74 -> 71
    //   148: aload 6
    //   150: astore_3
    //   151: aload 4
    //   153: astore_0
    //   154: aload 4
    //   156: ldc 111
    //   158: invokevirtual 115	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   161: ifeq -90 -> 71
    //   164: aload 4
    //   166: ldc 117
    //   168: ldc 119
    //   170: invokevirtual 123	java/lang/String:replaceAll	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   173: astore_0
    //   174: aload 6
    //   176: astore_3
    //   177: goto -106 -> 71
    //   180: iconst_0
    //   181: istore_1
    //   182: goto -100 -> 82
    //   185: getstatic 46	com/microsoft/xbox/service/model/serialization/UTCDateConverterGson:defaultFormatMs	Ljava/text/SimpleDateFormat;
    //   188: aload 4
    //   190: invokevirtual 102	java/text/SimpleDateFormat:setTimeZone	(Ljava/util/TimeZone;)V
    //   193: getstatic 46	com/microsoft/xbox/service/model/serialization/UTCDateConverterGson:defaultFormatMs	Ljava/text/SimpleDateFormat;
    //   196: aload_0
    //   197: invokevirtual 105	java/text/SimpleDateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
    //   200: astore_0
    //   201: goto -183 -> 18
    //   204: astore_0
    //   205: aload 5
    //   207: astore_0
    //   208: goto -190 -> 18
    //   211: astore_0
    //   212: ldc 2
    //   214: monitorexit
    //   215: aload_0
    //   216: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	217	0	paramString	String
    //   81	101	1	i	int
    //   10	2	2	bool	boolean
    //   70	107	3	localObject1	Object
    //   24	165	4	localObject2	Object
    //   1	205	5	localObject3	Object
    //   46	129	6	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   100	116	204	java/text/ParseException
    //   185	201	204	java/text/ParseException
    //   6	11	211	finally
    //   26	45	211	finally
    //   48	68	211	finally
    //   71	80	211	finally
    //   89	96	211	finally
    //   100	116	211	finally
    //   119	145	211	finally
    //   154	174	211	finally
    //   185	201	211	finally
  }
  
  public static class UTCDateConverterJSONDeserializer
    implements JsonDeserializer<Date>
  {
    public Date deserialize(JsonElement paramJsonElement, Type paramType, JsonDeserializationContext paramJsonDeserializationContext)
    {
      return UTCDateConverterGson.convert(paramJsonElement.getAsJsonPrimitive().getAsString());
    }
  }
  
  public static class UTCDateConverterShortDateAlternateFormatJSONDeserializer
    implements JsonDeserializer<Date>
  {
    public Date deserialize(JsonElement paramJsonElement, Type paramType, JsonDeserializationContext paramJsonDeserializationContext)
    {
      paramJsonDeserializationContext = paramJsonElement.getAsJsonPrimitive().getAsString();
      paramJsonElement = null;
      TimeZone localTimeZone = TimeZone.getTimeZone("GMT");
      UTCDateConverterGson.shortDateFormat.setTimeZone(localTimeZone);
      try
      {
        paramType = UTCDateConverterGson.shortDateFormat.parse(paramJsonDeserializationContext);
        paramJsonElement = paramType;
      }
      catch (ParseException paramType)
      {
        for (;;) {}
      }
      paramType = paramJsonElement;
      if (paramJsonElement != null)
      {
        paramType = paramJsonElement;
        if (paramJsonElement.getYear() + 1900 < 2000) {
          UTCDateConverterGson.shortDateAlternateFormat.setTimeZone(localTimeZone);
        }
      }
      try
      {
        paramType = UTCDateConverterGson.shortDateAlternateFormat.parse(paramJsonDeserializationContext);
        return paramType;
      }
      catch (ParseException paramType)
      {
        return paramJsonElement;
      }
    }
  }
  
  public static class UTCDateConverterShortDateFormatJSONDeserializer
    implements JsonDeserializer<Date>
  {
    public Date deserialize(JsonElement paramJsonElement, Type paramType, JsonDeserializationContext paramJsonDeserializationContext)
    {
      paramJsonElement = paramJsonElement.getAsJsonPrimitive().getAsString();
      paramType = TimeZone.getTimeZone("GMT");
      UTCDateConverterGson.shortDateFormat.setTimeZone(paramType);
      try
      {
        paramJsonElement = UTCDateConverterGson.shortDateFormat.parse(paramJsonElement);
        return paramJsonElement;
      }
      catch (ParseException paramJsonElement) {}
      return null;
    }
  }
  
  public static class UTCRoundtripDateConverterJSONDeserializer
    implements JsonDeserializer<Date>
  {
    public Date deserialize(JsonElement paramJsonElement, Type paramType, JsonDeserializationContext paramJsonDeserializationContext)
    {
      paramType = paramJsonElement.getAsJsonPrimitive().getAsString();
      paramJsonElement = paramType;
      if (paramType.endsWith("Z")) {
        paramJsonElement = paramType.replace("Z", "");
      }
      paramType = null;
      if (0 == 0) {
        paramType = TimeZone.getTimeZone("GMT");
      }
      UTCDateConverterGson.defaultFormatNoMs.setTimeZone(paramType);
      try
      {
        paramJsonElement = UTCDateConverterGson.defaultFormatNoMs.parse(paramJsonElement);
        return paramJsonElement;
      }
      catch (ParseException paramJsonElement) {}
      return null;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\service\model\serialization\UTCDateConverterGson.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */