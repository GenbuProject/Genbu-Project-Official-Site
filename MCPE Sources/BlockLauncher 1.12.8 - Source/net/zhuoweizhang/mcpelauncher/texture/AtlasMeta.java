package net.zhuoweizhang.mcpelauncher.texture;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AtlasMeta
{
  public JSONArray data;
  public int height;
  private Map<String, JSONObject> nameMap = new HashMap();
  public boolean needsHDWorkaround;
  public boolean[] occupied;
  public int originalUVCount = 0;
  public int realWidth;
  public int tileWidth;
  public int width;
  
  public AtlasMeta(JSONArray paramJSONArray, boolean paramBoolean, int paramInt)
  {
    this.data = paramJSONArray;
    this.needsHDWorkaround = paramBoolean;
    this.realWidth = paramInt;
    try
    {
      calculateStuff();
      return;
    }
    catch (JSONException paramJSONArray)
    {
      throw new RuntimeException(paramJSONArray);
    }
  }
  
  private void calculateOccupied()
    throws JSONException
  {
    int i = 0;
    if (i < this.data.length())
    {
      Object localObject2 = this.data.getJSONObject(i);
      if (!((JSONObject)localObject2).has("name")) {}
      for (;;)
      {
        i += 1;
        break;
        Object localObject1 = ((JSONObject)localObject2).getString("name");
        this.nameMap.put(localObject1, localObject2);
        localObject2 = ((JSONObject)localObject2).getJSONArray("uvs");
        int j = 0;
        int k;
        while (j < ((JSONArray)localObject2).length())
        {
          JSONArray localJSONArray = ((JSONArray)localObject2).getJSONArray(j);
          k = uvToIndex(localJSONArray.getDouble(0), localJSONArray.getDouble(1));
          this.occupied[k] = true;
          this.originalUVCount += 1;
          j += 1;
        }
        if ((((String)localObject1).equals("flowing_water")) || (((String)localObject1).equals("flowing_lava")))
        {
          localObject1 = ((JSONArray)localObject2).getJSONArray(0);
          j = uvToIndex(((JSONArray)localObject1).getDouble(0), ((JSONArray)localObject1).getDouble(1));
          k = this.width / this.tileWidth;
          this.occupied[(j + 1)] = true;
          this.occupied[(j + k)] = true;
          this.occupied[(j + k + 1)] = true;
          this.originalUVCount += 3;
        }
      }
    }
  }
  
  private void calculateStuff()
    throws JSONException
  {
    JSONArray localJSONArray = this.data.getJSONObject(0).getJSONArray("uvs").getJSONArray(0);
    this.width = localJSONArray.getInt(4);
    this.height = localJSONArray.getInt(5);
    this.tileWidth = ((int)(localJSONArray.getDouble(2) - localJSONArray.getDouble(0) + 0.5D));
    if (!isPowerOfTwo(this.tileWidth)) {
      throw new RuntimeException("Non power of two value in icon width: " + this.tileWidth);
    }
    this.occupied = new boolean[this.width / this.tileWidth * (this.height / this.tileWidth)];
    calculateOccupied();
    if ((this.needsHDWorkaround) && (this.realWidth != this.width)) {
      markWorkaroundOccupied();
    }
  }
  
  private JSONArray indexToUv(int paramInt)
  {
    int i = paramInt % (this.width / this.tileWidth);
    paramInt /= this.width / this.tileWidth;
    double d1 = this.tileWidth * i;
    double d2 = (i + 1) * this.tileWidth;
    double d3 = this.tileWidth * paramInt;
    double d4 = (paramInt + 1) * this.tileWidth;
    try
    {
      JSONArray localJSONArray = new JSONArray().put(d1).put(d3).put(d2).put(d4).put(this.width).put(this.height);
      return localJSONArray;
    }
    catch (JSONException localJSONException)
    {
      throw new RuntimeException(localJSONException);
    }
  }
  
  private static boolean isPowerOfTwo(int paramInt)
  {
    return (paramInt - 1 & paramInt) == 0;
  }
  
  private void markWorkaroundOccupied()
  {
    System.out.println("width: " + this.width + " realWidth: " + this.realWidth);
    int k = this.width / this.tileWidth;
    int i = this.realWidth / k;
    int m = this.width / i;
    int n = this.height / i;
    i = 0;
    while (i < n)
    {
      int j = 0;
      while (j < m)
      {
        this.occupied[(i * k + j)] = true;
        j += 1;
      }
      i += 1;
    }
  }
  
  private int uvToIndex(double paramDouble1, double paramDouble2)
  {
    int i = (int)(paramDouble1 / this.tileWidth + 0.5D);
    int j = (int)(paramDouble2 / this.tileWidth + 0.5D);
    return this.width / this.tileWidth * j + i;
  }
  
  public JSONArray getOrAddIcon(String paramString, int paramInt)
    throws JSONException
  {
    JSONObject localJSONObject = (JSONObject)this.nameMap.get(paramString);
    Object localObject = localJSONObject;
    if (localJSONObject == null)
    {
      localObject = new JSONObject().put("name", paramString).put("uvs", new JSONArray());
      this.nameMap.put(paramString, localObject);
      this.data.put(localObject);
    }
    localObject = ((JSONObject)localObject).getJSONArray("uvs");
    if (!((JSONArray)localObject).isNull(paramInt)) {
      return ((JSONArray)localObject).getJSONArray(paramInt);
    }
    int i = 0;
    for (;;)
    {
      if ((i >= this.occupied.length) || (this.occupied[i] == 0))
      {
        if (i < this.occupied.length) {
          break;
        }
        throw new RuntimeException("No more space in texture atlas; can't add " + paramString + "_" + paramInt + " :(");
      }
      i += 1;
    }
    paramString = indexToUv(i);
    ((JSONArray)localObject).put(paramInt, paramString);
    this.occupied[i] = true;
    return paramString;
  }
  
  public boolean hasIcon(String paramString, int paramInt)
  {
    try
    {
      paramString = (JSONObject)this.nameMap.get(paramString);
      if (paramString == null) {
        return false;
      }
      int i = paramString.getJSONArray("uvs").length();
      if (paramInt < i) {
        return true;
      }
    }
    catch (JSONException paramString) {}
    return false;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\net\zhuoweizhang\mcpelauncher\texture\AtlasMeta.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */