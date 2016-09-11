package com.microsoft.xbox.idp.model.serialization;

import android.text.TextUtils;
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
  
  public static Date convert(String paramString)
  {
    Object localObject2 = null;
    label218:
    for (;;)
    {
      Object localObject1;
      try
      {
        boolean bool = TextUtils.isEmpty(paramString);
        if (bool)
        {
          paramString = (String)localObject2;
          return paramString;
        }
        if (!paramString.endsWith("Z")) {
          break label218;
        }
        paramString = paramString.replace("Z", "");
        int i;
        if (paramString.endsWith("+00:00"))
        {
          str = paramString.replace("+00:00", "");
          paramString = null;
          if (str.length() != 19) {
            continue;
          }
          i = 1;
          localObject1 = paramString;
          if (paramString == null) {
            localObject1 = TimeZone.getTimeZone("GMT");
          }
          if (i == 0) {
            continue;
          }
        }
        try
        {
          defaultFormatNoMs.setTimeZone((TimeZone)localObject1);
          paramString = defaultFormatNoMs.parse(str);
        }
        catch (ParseException paramString)
        {
          Log.e(TAG, paramString.toString());
          paramString = (String)localObject2;
        }
        if (paramString.endsWith("+01:00"))
        {
          str = paramString.replace("+01:00", "");
          paramString = TimeZone.getTimeZone("GMT+01:00");
          continue;
        }
        if (paramString.contains("."))
        {
          str = paramString.replaceAll("([.][0-9]{3})[0-9]*$", "$1");
          paramString = null;
          continue;
          i = 0;
          continue;
          defaultFormatMs.setTimeZone((TimeZone)localObject1);
          paramString = defaultFormatMs.parse(str);
          continue;
          continue;
        }
        localObject1 = null;
      }
      finally {}
      String str = paramString;
      paramString = (String)localObject1;
    }
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
      paramJsonElement = paramJsonElement.getAsJsonPrimitive().getAsString();
      if (paramJsonElement.endsWith("Z")) {
        paramJsonElement = paramJsonElement.replace("Z", "");
      }
      for (;;)
      {
        paramType = TimeZone.getTimeZone("GMT");
        UTCDateConverter.defaultFormatNoMs.setTimeZone(paramType);
        try
        {
          paramType = UTCDateConverter.defaultFormatNoMs.parse(paramJsonElement);
          return paramType;
        }
        catch (ParseException paramType)
        {
          Log.d(UTCDateConverter.TAG, "failed to parse date " + paramJsonElement);
          return null;
        }
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\idp\model\serialization\UTCDateConverter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */