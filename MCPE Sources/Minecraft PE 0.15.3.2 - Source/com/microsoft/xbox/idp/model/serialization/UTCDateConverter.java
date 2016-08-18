package com.microsoft.xbox.idp.model.serialization;

import android.util.Log;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class UTCDateConverter
{
  private static final int NO_MS_STRING_LENGTH = 19;
  private static final String TAG = UTCDateConverter.class.getSimpleName();
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
    //   7: invokestatic 82	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
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
    //   27: ldc 84
    //   29: invokevirtual 90	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   32: ifeq +13 -> 45
    //   35: aload_0
    //   36: ldc 84
    //   38: ldc 92
    //   40: invokevirtual 96	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   43: astore 4
    //   45: aconst_null
    //   46: astore 6
    //   48: aload 4
    //   50: ldc 98
    //   52: invokevirtual 90	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   55: ifeq +64 -> 119
    //   58: aload 4
    //   60: ldc 98
    //   62: ldc 92
    //   64: invokevirtual 96	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   67: astore_0
    //   68: aload 6
    //   70: astore_3
    //   71: aload_0
    //   72: invokevirtual 102	java/lang/String:length	()I
    //   75: bipush 19
    //   77: if_icmpne +103 -> 180
    //   80: iconst_1
    //   81: istore_1
    //   82: aload_3
    //   83: astore 4
    //   85: aload_3
    //   86: ifnonnull +10 -> 96
    //   89: ldc 104
    //   91: invokestatic 110	java/util/TimeZone:getTimeZone	(Ljava/lang/String;)Ljava/util/TimeZone;
    //   94: astore 4
    //   96: iload_1
    //   97: ifeq +88 -> 185
    //   100: getstatic 52	com/microsoft/xbox/idp/model/serialization/UTCDateConverter:defaultFormatNoMs	Ljava/text/SimpleDateFormat;
    //   103: aload 4
    //   105: invokevirtual 114	java/text/SimpleDateFormat:setTimeZone	(Ljava/util/TimeZone;)V
    //   108: getstatic 52	com/microsoft/xbox/idp/model/serialization/UTCDateConverter:defaultFormatNoMs	Ljava/text/SimpleDateFormat;
    //   111: aload_0
    //   112: invokevirtual 117	java/text/SimpleDateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
    //   115: astore_0
    //   116: goto -98 -> 18
    //   119: aload 4
    //   121: ldc 119
    //   123: invokevirtual 90	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   126: ifeq +22 -> 148
    //   129: aload 4
    //   131: ldc 119
    //   133: ldc 92
    //   135: invokevirtual 96	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   138: astore_0
    //   139: ldc 121
    //   141: invokestatic 110	java/util/TimeZone:getTimeZone	(Ljava/lang/String;)Ljava/util/TimeZone;
    //   144: astore_3
    //   145: goto -74 -> 71
    //   148: aload 6
    //   150: astore_3
    //   151: aload 4
    //   153: astore_0
    //   154: aload 4
    //   156: ldc 123
    //   158: invokevirtual 126	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   161: ifeq -90 -> 71
    //   164: aload 4
    //   166: ldc -128
    //   168: ldc -126
    //   170: invokevirtual 134	java/lang/String:replaceAll	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   173: astore_0
    //   174: aload 6
    //   176: astore_3
    //   177: goto -106 -> 71
    //   180: iconst_0
    //   181: istore_1
    //   182: goto -100 -> 82
    //   185: getstatic 56	com/microsoft/xbox/idp/model/serialization/UTCDateConverter:defaultFormatMs	Ljava/text/SimpleDateFormat;
    //   188: aload 4
    //   190: invokevirtual 114	java/text/SimpleDateFormat:setTimeZone	(Ljava/util/TimeZone;)V
    //   193: getstatic 56	com/microsoft/xbox/idp/model/serialization/UTCDateConverter:defaultFormatMs	Ljava/text/SimpleDateFormat;
    //   196: aload_0
    //   197: invokevirtual 117	java/text/SimpleDateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
    //   200: astore_0
    //   201: goto -183 -> 18
    //   204: astore_0
    //   205: getstatic 36	com/microsoft/xbox/idp/model/serialization/UTCDateConverter:TAG	Ljava/lang/String;
    //   208: aload_0
    //   209: invokevirtual 137	java/text/ParseException:toString	()Ljava/lang/String;
    //   212: invokestatic 143	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   215: pop
    //   216: aload 5
    //   218: astore_0
    //   219: goto -201 -> 18
    //   222: astore_0
    //   223: ldc 2
    //   225: monitorexit
    //   226: aload_0
    //   227: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	228	0	paramString	String
    //   81	101	1	i	int
    //   10	2	2	bool	boolean
    //   70	107	3	localObject1	Object
    //   24	165	4	localObject2	Object
    //   1	216	5	localObject3	Object
    //   46	129	6	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   100	116	204	java/text/ParseException
    //   185	201	204	java/text/ParseException
    //   6	11	222	finally
    //   26	45	222	finally
    //   48	68	222	finally
    //   71	80	222	finally
    //   89	96	222	finally
    //   100	116	222	finally
    //   119	145	222	finally
    //   154	174	222	finally
    //   185	201	222	finally
    //   205	216	222	finally
  }
  
  public static class UTCDateConverterJSONDeserializer
    implements JsonDeserializer<Date>, JsonSerializer<Date>
  {
    public Date deserialize(JsonElement paramJsonElement, Type paramType, JsonDeserializationContext paramJsonDeserializationContext)
    {
      return UTCDateConverter.convert(paramJsonElement.getAsJsonPrimitive().getAsString());
    }
    
    public JsonElement serialize(Date paramDate, Type paramType, JsonSerializationContext paramJsonSerializationContext)
    {
      return new JsonPrimitive(UTCDateConverter.defaultFormatNoMs.format(paramDate));
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
      UTCDateConverter.shortDateFormat.setTimeZone(localTimeZone);
      try
      {
        paramType = UTCDateConverter.shortDateFormat.parse(paramJsonDeserializationContext);
        paramJsonElement = paramType;
      }
      catch (ParseException paramType)
      {
        for (;;)
        {
          try
          {
            paramType = UTCDateConverter.shortDateAlternateFormat.parse(paramJsonDeserializationContext);
            return paramType;
          }
          catch (ParseException paramType)
          {
            Log.d(UTCDateConverter.TAG, "failed to parse alternate short date " + paramJsonDeserializationContext);
          }
          paramType = paramType;
          Log.d(UTCDateConverter.TAG, "failed to parse short date " + paramJsonDeserializationContext);
        }
      }
      paramType = paramJsonElement;
      if (paramJsonElement != null)
      {
        paramType = paramJsonElement;
        if (paramJsonElement.getYear() + 1900 < 2000) {
          UTCDateConverter.shortDateAlternateFormat.setTimeZone(localTimeZone);
        }
      }
      return paramJsonElement;
    }
  }
  
  public static class UTCDateConverterShortDateFormatJSONDeserializer
    implements JsonDeserializer<Date>
  {
    public Date deserialize(JsonElement paramJsonElement, Type paramType, JsonDeserializationContext paramJsonDeserializationContext)
    {
      paramJsonElement = paramJsonElement.getAsJsonPrimitive().getAsString();
      paramType = TimeZone.getTimeZone("GMT");
      UTCDateConverter.shortDateFormat.setTimeZone(paramType);
      try
      {
        paramType = UTCDateConverter.shortDateFormat.parse(paramJsonElement);
        return paramType;
      }
      catch (ParseException paramType)
      {
        Log.d(UTCDateConverter.TAG, "failed to parse date " + paramJsonElement);
      }
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
      UTCDateConverter.defaultFormatNoMs.setTimeZone(paramType);
      try
      {
        paramType = UTCDateConverter.defaultFormatNoMs.parse(paramJsonElement);
        return paramType;
      }
      catch (ParseException paramType)
      {
        Log.d(UTCDateConverter.TAG, "failed to parse date " + paramJsonElement);
      }
      return null;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\idp\model\serialization\UTCDateConverter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */