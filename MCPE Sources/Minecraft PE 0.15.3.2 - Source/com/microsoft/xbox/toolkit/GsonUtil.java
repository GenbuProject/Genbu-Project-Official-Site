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
    //   1: astore_3
    //   2: aconst_null
    //   3: astore 7
    //   5: aconst_null
    //   6: astore 5
    //   8: aconst_null
    //   9: astore 6
    //   11: aconst_null
    //   12: astore 4
    //   14: new 52	java/io/InputStreamReader
    //   17: dup
    //   18: aload_1
    //   19: invokespecial 55	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   22: astore_1
    //   23: new 57	java/io/BufferedReader
    //   26: dup
    //   27: aload_1
    //   28: invokespecial 60	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   31: astore_3
    //   32: aload_0
    //   33: aload_3
    //   34: aload_2
    //   35: invokevirtual 66	com/google/gson/Gson:fromJson	(Ljava/io/Reader;Ljava/lang/Class;)Ljava/lang/Object;
    //   38: astore_0
    //   39: aload_3
    //   40: ifnull +7 -> 47
    //   43: aload_3
    //   44: invokevirtual 67	java/io/BufferedReader:close	()V
    //   47: aload_1
    //   48: ifnull +125 -> 173
    //   51: aload_1
    //   52: invokevirtual 68	java/io/InputStreamReader:close	()V
    //   55: aload_0
    //   56: astore_1
    //   57: aload_1
    //   58: areturn
    //   59: astore_1
    //   60: aload_0
    //   61: areturn
    //   62: astore_0
    //   63: aload 7
    //   65: astore_0
    //   66: aload 6
    //   68: astore_1
    //   69: aload_1
    //   70: ifnull +7 -> 77
    //   73: aload_1
    //   74: invokevirtual 67	java/io/BufferedReader:close	()V
    //   77: aload 4
    //   79: astore_1
    //   80: aload_0
    //   81: ifnull -24 -> 57
    //   84: aload_0
    //   85: invokevirtual 68	java/io/InputStreamReader:close	()V
    //   88: aconst_null
    //   89: areturn
    //   90: astore_0
    //   91: aconst_null
    //   92: areturn
    //   93: astore_1
    //   94: aload_3
    //   95: astore_0
    //   96: aload 5
    //   98: astore_2
    //   99: aload_2
    //   100: ifnull +7 -> 107
    //   103: aload_2
    //   104: invokevirtual 67	java/io/BufferedReader:close	()V
    //   107: aload_0
    //   108: ifnull +7 -> 115
    //   111: aload_0
    //   112: invokevirtual 68	java/io/InputStreamReader:close	()V
    //   115: aload_1
    //   116: athrow
    //   117: astore_2
    //   118: goto -71 -> 47
    //   121: astore_1
    //   122: goto -45 -> 77
    //   125: astore_2
    //   126: goto -19 -> 107
    //   129: astore_0
    //   130: goto -15 -> 115
    //   133: astore_3
    //   134: aload_1
    //   135: astore_0
    //   136: aload 5
    //   138: astore_2
    //   139: aload_3
    //   140: astore_1
    //   141: goto -42 -> 99
    //   144: astore 4
    //   146: aload_3
    //   147: astore_2
    //   148: aload_1
    //   149: astore_0
    //   150: aload 4
    //   152: astore_1
    //   153: goto -54 -> 99
    //   156: astore_0
    //   157: aload_1
    //   158: astore_0
    //   159: aload 6
    //   161: astore_1
    //   162: goto -93 -> 69
    //   165: astore_0
    //   166: aload_1
    //   167: astore_0
    //   168: aload_3
    //   169: astore_1
    //   170: goto -101 -> 69
    //   173: aload_0
    //   174: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	175	0	paramGson	Gson
    //   0	175	1	paramInputStream	InputStream
    //   0	175	2	paramClass	Class<T>
    //   1	94	3	localBufferedReader	java.io.BufferedReader
    //   133	36	3	localObject1	Object
    //   12	66	4	localObject2	Object
    //   144	7	4	localObject3	Object
    //   6	131	5	localObject4	Object
    //   9	151	6	localObject5	Object
    //   3	61	7	localObject6	Object
    // Exception table:
    //   from	to	target	type
    //   51	55	59	java/lang/Exception
    //   14	23	62	java/lang/Exception
    //   84	88	90	java/lang/Exception
    //   14	23	93	finally
    //   43	47	117	java/lang/Exception
    //   73	77	121	java/lang/Exception
    //   103	107	125	java/lang/Exception
    //   111	115	129	java/lang/Exception
    //   23	32	133	finally
    //   32	39	144	finally
    //   23	32	156	java/lang/Exception
    //   32	39	165	java/lang/Exception
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


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\toolkit\GsonUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */