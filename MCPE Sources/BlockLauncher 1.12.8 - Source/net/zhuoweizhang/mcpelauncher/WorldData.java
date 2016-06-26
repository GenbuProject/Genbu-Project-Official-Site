package net.zhuoweizhang.mcpelauncher;

import java.io.File;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

public class WorldData
{
  boolean dirty = false;
  JSONObject mData;
  File mDir;
  File mFile;
  
  public WorldData(File paramFile)
    throws IOException
  {
    this.mDir = paramFile;
    this.mFile = new File(paramFile, "blocklauncher_data.json");
    load();
  }
  
  /* Error */
  public void clearEntityData(long paramLong)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 42	net/zhuoweizhang/mcpelauncher/WorldData:mData	Lorg/json/JSONObject;
    //   4: ldc 44
    //   6: invokevirtual 50	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   9: lload_1
    //   10: invokestatic 56	java/lang/Long:toString	(J)Ljava/lang/String;
    //   13: invokevirtual 60	org/json/JSONObject:remove	(Ljava/lang/String;)Ljava/lang/Object;
    //   16: ifnull +20 -> 36
    //   19: iconst_1
    //   20: istore_3
    //   21: aload_0
    //   22: iload_3
    //   23: putfield 20	net/zhuoweizhang/mcpelauncher/WorldData:dirty	Z
    //   26: aload_0
    //   27: getfield 20	net/zhuoweizhang/mcpelauncher/WorldData:dirty	Z
    //   30: istore_3
    //   31: iload_3
    //   32: ifeq +3 -> 35
    //   35: return
    //   36: iconst_0
    //   37: istore_3
    //   38: goto -17 -> 21
    //   41: astore 4
    //   43: new 62	java/lang/RuntimeException
    //   46: dup
    //   47: aload 4
    //   49: invokespecial 65	java/lang/RuntimeException:<init>	(Ljava/lang/Throwable;)V
    //   52: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	53	0	this	WorldData
    //   0	53	1	paramLong	long
    //   20	18	3	bool	boolean
    //   41	7	4	localJSONException	JSONException
    // Exception table:
    //   from	to	target	type
    //   0	19	41	org/json/JSONException
    //   21	31	41	org/json/JSONException
  }
  
  public String getEntityData(long paramLong, String paramString)
  {
    try
    {
      JSONObject localJSONObject = this.mData.getJSONObject("entities").optJSONObject(Long.toString(paramLong));
      if (localJSONObject == null) {
        return null;
      }
      paramString = localJSONObject.optString(paramString);
      return paramString;
    }
    catch (JSONException paramString)
    {
      throw new RuntimeException(paramString);
    }
  }
  
  /* Error */
  protected void load()
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 31	net/zhuoweizhang/mcpelauncher/WorldData:mFile	Ljava/io/File;
    //   4: invokevirtual 78	java/io/File:exists	()Z
    //   7: ifeq +15 -> 22
    //   10: aload_0
    //   11: getfield 31	net/zhuoweizhang/mcpelauncher/WorldData:mFile	Ljava/io/File;
    //   14: invokevirtual 82	java/io/File:length	()J
    //   17: lconst_0
    //   18: lcmp
    //   19: ifne +8 -> 27
    //   22: aload_0
    //   23: invokevirtual 85	net/zhuoweizhang/mcpelauncher/WorldData:loadDefaults	()V
    //   26: return
    //   27: aload_0
    //   28: getfield 31	net/zhuoweizhang/mcpelauncher/WorldData:mFile	Ljava/io/File;
    //   31: invokevirtual 82	java/io/File:length	()J
    //   34: l2i
    //   35: newarray <illegal type>
    //   37: astore_3
    //   38: aconst_null
    //   39: astore_2
    //   40: new 87	java/io/FileInputStream
    //   43: dup
    //   44: aload_0
    //   45: getfield 31	net/zhuoweizhang/mcpelauncher/WorldData:mFile	Ljava/io/File;
    //   48: invokespecial 89	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   51: astore_1
    //   52: aload_1
    //   53: aload_3
    //   54: invokevirtual 93	java/io/FileInputStream:read	([B)I
    //   57: pop
    //   58: aload_1
    //   59: ifnull +7 -> 66
    //   62: aload_1
    //   63: invokevirtual 96	java/io/FileInputStream:close	()V
    //   66: iconst_1
    //   67: ifeq +62 -> 129
    //   70: aload_0
    //   71: new 46	org/json/JSONObject
    //   74: dup
    //   75: new 98	java/lang/String
    //   78: dup
    //   79: aload_3
    //   80: ldc 100
    //   82: invokespecial 103	java/lang/String:<init>	([BLjava/lang/String;)V
    //   85: invokespecial 106	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   88: putfield 42	net/zhuoweizhang/mcpelauncher/WorldData:mData	Lorg/json/JSONObject;
    //   91: return
    //   92: astore_1
    //   93: aload_1
    //   94: invokevirtual 109	org/json/JSONException:printStackTrace	()V
    //   97: aload_0
    //   98: invokevirtual 85	net/zhuoweizhang/mcpelauncher/WorldData:loadDefaults	()V
    //   101: return
    //   102: astore_1
    //   103: aload_1
    //   104: invokevirtual 110	java/io/IOException:printStackTrace	()V
    //   107: goto -41 -> 66
    //   110: astore_1
    //   111: aload_2
    //   112: ifnull +7 -> 119
    //   115: aload_2
    //   116: invokevirtual 96	java/io/FileInputStream:close	()V
    //   119: aload_1
    //   120: athrow
    //   121: astore_2
    //   122: aload_2
    //   123: invokevirtual 110	java/io/IOException:printStackTrace	()V
    //   126: goto -7 -> 119
    //   129: aload_0
    //   130: invokevirtual 85	net/zhuoweizhang/mcpelauncher/WorldData:loadDefaults	()V
    //   133: return
    //   134: astore_3
    //   135: aload_1
    //   136: astore_2
    //   137: aload_3
    //   138: astore_1
    //   139: goto -28 -> 111
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	142	0	this	WorldData
    //   51	12	1	localFileInputStream	java.io.FileInputStream
    //   92	2	1	localJSONException	JSONException
    //   102	2	1	localIOException1	IOException
    //   110	26	1	localObject1	Object
    //   138	1	1	localObject2	Object
    //   39	77	2	localObject3	Object
    //   121	2	2	localIOException2	IOException
    //   136	1	2	localObject4	Object
    //   37	43	3	arrayOfByte	byte[]
    //   134	4	3	localObject5	Object
    // Exception table:
    //   from	to	target	type
    //   70	91	92	org/json/JSONException
    //   62	66	102	java/io/IOException
    //   40	52	110	finally
    //   115	119	121	java/io/IOException
    //   52	58	134	finally
  }
  
  protected void loadDefaults()
  {
    try
    {
      this.mData = new JSONObject();
      this.mData.put("entities", new JSONObject());
      return;
    }
    catch (JSONException localJSONException)
    {
      throw new RuntimeException(localJSONException);
    }
  }
  
  /* Error */
  public void save()
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 20	net/zhuoweizhang/mcpelauncher/WorldData:dirty	Z
    //   4: ifne +4 -> 8
    //   7: return
    //   8: aconst_null
    //   9: astore_2
    //   10: new 118	java/io/FileOutputStream
    //   13: dup
    //   14: aload_0
    //   15: getfield 31	net/zhuoweizhang/mcpelauncher/WorldData:mFile	Ljava/io/File;
    //   18: invokespecial 119	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   21: astore_3
    //   22: aload_3
    //   23: aload_0
    //   24: getfield 42	net/zhuoweizhang/mcpelauncher/WorldData:mData	Lorg/json/JSONObject;
    //   27: invokevirtual 122	org/json/JSONObject:toString	()Ljava/lang/String;
    //   30: ldc 100
    //   32: invokevirtual 126	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   35: invokevirtual 130	java/io/FileOutputStream:write	([B)V
    //   38: aload_0
    //   39: iconst_0
    //   40: putfield 20	net/zhuoweizhang/mcpelauncher/WorldData:dirty	Z
    //   43: aload_3
    //   44: ifnull -37 -> 7
    //   47: aload_3
    //   48: invokevirtual 131	java/io/FileOutputStream:close	()V
    //   51: return
    //   52: astore_1
    //   53: aload_1
    //   54: invokevirtual 110	java/io/IOException:printStackTrace	()V
    //   57: return
    //   58: astore_1
    //   59: aload_2
    //   60: ifnull +7 -> 67
    //   63: aload_2
    //   64: invokevirtual 131	java/io/FileOutputStream:close	()V
    //   67: aload_1
    //   68: athrow
    //   69: astore_2
    //   70: aload_2
    //   71: invokevirtual 110	java/io/IOException:printStackTrace	()V
    //   74: goto -7 -> 67
    //   77: astore_1
    //   78: aload_3
    //   79: astore_2
    //   80: goto -21 -> 59
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	83	0	this	WorldData
    //   52	2	1	localIOException1	IOException
    //   58	10	1	localObject1	Object
    //   77	1	1	localObject2	Object
    //   9	55	2	localObject3	Object
    //   69	2	2	localIOException2	IOException
    //   79	1	2	localObject4	Object
    //   21	58	3	localFileOutputStream	java.io.FileOutputStream
    // Exception table:
    //   from	to	target	type
    //   47	51	52	java/io/IOException
    //   10	22	58	finally
    //   63	67	69	java/io/IOException
    //   22	43	77	finally
  }
  
  public void setEntityData(long paramLong, String paramString1, String paramString2)
  {
    if (paramString1.indexOf(".") == -1) {
      throw new RuntimeException("Entity data keys must be in format of author.modname.keyname; for example, coolmcpemodder.sz.favoritecolor");
    }
    try
    {
      JSONObject localJSONObject3 = this.mData.getJSONObject("entities");
      JSONObject localJSONObject2 = this.mData.optJSONObject(Long.toString(paramLong));
      JSONObject localJSONObject1 = localJSONObject2;
      if (localJSONObject2 == null)
      {
        localJSONObject1 = new JSONObject();
        localJSONObject3.put(Long.toString(paramLong), localJSONObject1);
      }
      localJSONObject1.put(paramString1, paramString2);
      this.dirty = true;
      return;
    }
    catch (JSONException paramString1)
    {
      throw new RuntimeException(paramString1);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\net\zhuoweizhang\mcpelauncher\WorldData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */