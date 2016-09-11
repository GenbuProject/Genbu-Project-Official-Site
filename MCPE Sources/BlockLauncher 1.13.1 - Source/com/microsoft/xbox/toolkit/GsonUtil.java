package com.microsoft.xbox.toolkit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class GsonUtil
{
  /* Error */
  public static String buildJsonBody(JsonBodyBuilder paramJsonBodyBuilder)
    throws IOException
  {
    // Byte code:
    //   0: new 18	java/io/StringWriter
    //   3: dup
    //   4: invokespecial 19	java/io/StringWriter:<init>	()V
    //   7: astore_1
    //   8: new 21	com/google/gson/stream/JsonWriter
    //   11: dup
    //   12: aload_1
    //   13: invokespecial 24	com/google/gson/stream/JsonWriter:<init>	(Ljava/io/Writer;)V
    //   16: astore_2
    //   17: aload_0
    //   18: aload_2
    //   19: invokeinterface 28 2 0
    //   24: aload_1
    //   25: invokevirtual 32	java/io/StringWriter:toString	()Ljava/lang/String;
    //   28: astore_0
    //   29: aload_2
    //   30: invokevirtual 35	com/google/gson/stream/JsonWriter:close	()V
    //   33: aload_1
    //   34: invokevirtual 36	java/io/StringWriter:close	()V
    //   37: aload_0
    //   38: areturn
    //   39: astore_0
    //   40: aload_2
    //   41: invokevirtual 35	com/google/gson/stream/JsonWriter:close	()V
    //   44: aload_0
    //   45: athrow
    //   46: astore_0
    //   47: aload_1
    //   48: invokevirtual 36	java/io/StringWriter:close	()V
    //   51: aload_0
    //   52: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	53	0	paramJsonBodyBuilder	JsonBodyBuilder
    //   7	41	1	localStringWriter	java.io.StringWriter
    //   16	25	2	localJsonWriter	JsonWriter
    // Exception table:
    //   from	to	target	type
    //   17	29	39	finally
    //   8	17	46	finally
    //   29	33	46	finally
    //   40	46	46	finally
  }
  
  public static GsonBuilder createMinimumGsonBuilder()
  {
    return new GsonBuilder().excludeFieldsWithModifiers(new int[] { 128 });
  }
  
  /* Error */
  public static <T> T deserializeJson(Gson paramGson, InputStream paramInputStream, Class<T> paramClass)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aconst_null
    //   4: astore 4
    //   6: new 52	java/io/InputStreamReader
    //   9: dup
    //   10: aload_1
    //   11: invokespecial 55	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   14: astore_1
    //   15: new 57	java/io/BufferedReader
    //   18: dup
    //   19: aload_1
    //   20: invokespecial 60	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   23: astore_3
    //   24: aload_0
    //   25: aload_3
    //   26: aload_2
    //   27: invokevirtual 66	com/google/gson/Gson:fromJson	(Ljava/io/Reader;Ljava/lang/Class;)Ljava/lang/Object;
    //   30: astore_0
    //   31: aload_3
    //   32: ifnull +7 -> 39
    //   35: aload_3
    //   36: invokevirtual 67	java/io/BufferedReader:close	()V
    //   39: aload_0
    //   40: astore_2
    //   41: aload_1
    //   42: ifnull +9 -> 51
    //   45: aload_1
    //   46: invokevirtual 68	java/io/InputStreamReader:close	()V
    //   49: aload_0
    //   50: astore_2
    //   51: aload_2
    //   52: areturn
    //   53: astore_0
    //   54: aconst_null
    //   55: astore_0
    //   56: aconst_null
    //   57: astore_1
    //   58: aload_0
    //   59: ifnull +7 -> 66
    //   62: aload_0
    //   63: invokevirtual 67	java/io/BufferedReader:close	()V
    //   66: aload 4
    //   68: astore_2
    //   69: aload_1
    //   70: ifnull -19 -> 51
    //   73: aload_1
    //   74: invokevirtual 68	java/io/InputStreamReader:close	()V
    //   77: aconst_null
    //   78: areturn
    //   79: astore_0
    //   80: aconst_null
    //   81: areturn
    //   82: astore_0
    //   83: aconst_null
    //   84: astore_1
    //   85: aload 5
    //   87: astore_2
    //   88: aload_2
    //   89: ifnull +7 -> 96
    //   92: aload_2
    //   93: invokevirtual 67	java/io/BufferedReader:close	()V
    //   96: aload_1
    //   97: ifnull +7 -> 104
    //   100: aload_1
    //   101: invokevirtual 68	java/io/InputStreamReader:close	()V
    //   104: aload_0
    //   105: athrow
    //   106: astore_0
    //   107: aload_3
    //   108: astore_2
    //   109: goto -21 -> 88
    //   112: astore_0
    //   113: aconst_null
    //   114: astore_0
    //   115: goto -57 -> 58
    //   118: astore_0
    //   119: aload_3
    //   120: astore_0
    //   121: goto -63 -> 58
    //   124: astore_2
    //   125: goto -86 -> 39
    //   128: astore_1
    //   129: aload_0
    //   130: areturn
    //   131: astore_0
    //   132: goto -66 -> 66
    //   135: astore_2
    //   136: goto -40 -> 96
    //   139: astore_1
    //   140: goto -36 -> 104
    //   143: astore_0
    //   144: aload 5
    //   146: astore_2
    //   147: goto -59 -> 88
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	150	0	paramGson	Gson
    //   0	150	1	paramInputStream	InputStream
    //   0	150	2	paramClass	Class<T>
    //   23	97	3	localBufferedReader	java.io.BufferedReader
    //   4	63	4	localObject1	Object
    //   1	144	5	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   6	15	53	java/lang/Exception
    //   73	77	79	java/lang/Exception
    //   6	15	82	finally
    //   24	31	106	finally
    //   15	24	112	java/lang/Exception
    //   24	31	118	java/lang/Exception
    //   35	39	124	java/lang/Exception
    //   45	49	128	java/lang/Exception
    //   62	66	131	java/lang/Exception
    //   92	96	135	java/lang/Exception
    //   100	104	139	java/lang/Exception
    //   15	24	143	finally
  }
  
  public static <T> T deserializeJson(Gson paramGson, String paramString, Class<T> paramClass)
  {
    try
    {
      paramGson = paramGson.fromJson(paramString, paramClass);
      return paramGson;
    }
    catch (Exception paramGson) {}
    return null;
  }
  
  public static <T> T deserializeJson(InputStream paramInputStream, Class<T> paramClass)
  {
    return (T)deserializeJson(createMinimumGsonBuilder().create(), paramInputStream, paramClass);
  }
  
  public static <T> T deserializeJson(InputStream paramInputStream, Class<T> paramClass, Type paramType, Object paramObject)
  {
    return (T)deserializeJson(createMinimumGsonBuilder().registerTypeAdapter(paramType, paramObject).create(), paramInputStream, paramClass);
  }
  
  public static <T> T deserializeJson(InputStream paramInputStream, Class<T> paramClass, Map<Type, Object> paramMap)
  {
    GsonBuilder localGsonBuilder = createMinimumGsonBuilder();
    paramMap = paramMap.entrySet().iterator();
    while (paramMap.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)paramMap.next();
      localGsonBuilder.registerTypeAdapter((Type)localEntry.getKey(), localEntry.getValue());
    }
    return (T)deserializeJson(localGsonBuilder.create(), paramInputStream, paramClass);
  }
  
  public static <T> T deserializeJson(String paramString, Class<T> paramClass)
  {
    return (T)deserializeJson(createMinimumGsonBuilder().create(), paramString, paramClass);
  }
  
  public static <T> T deserializeJson(String paramString, Class<T> paramClass, Type paramType, Object paramObject)
  {
    return (T)deserializeJson(createMinimumGsonBuilder().registerTypeAdapter(paramType, paramObject).create(), paramString, paramClass);
  }
  
  public static String toJsonString(Object paramObject)
  {
    return new Gson().toJson(paramObject);
  }
  
  public static abstract interface JsonBodyBuilder
  {
    public abstract void buildBody(JsonWriter paramJsonWriter)
      throws IOException;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\toolkit\GsonUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */