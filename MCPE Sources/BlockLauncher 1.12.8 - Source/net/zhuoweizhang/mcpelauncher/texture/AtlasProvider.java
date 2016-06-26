package net.zhuoweizhang.mcpelauncher.texture;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import com.mojang.minecraftpe.MainActivity;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import net.zhuoweizhang.mcpelauncher.TexturePack;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AtlasProvider
  implements TexturePack
{
  private Canvas atlasCanvas;
  public Bitmap atlasImg;
  public String atlasName;
  public boolean hasChanges = false;
  private boolean hdWorkaroundActive = false;
  public String importDir;
  public ImageLoader loader;
  public String metaName;
  public AtlasMeta metaObj;
  private int mipLevels;
  private String mipPrefix;
  private final boolean needsHDWorkaround;
  private Paint tempPaint = new Paint();
  private Rect tempRect = new Rect();
  private Rect tempRect2 = new Rect();
  public int xscale;
  
  public AtlasProvider(String paramString1, String paramString2, String paramString3, ImageLoader paramImageLoader, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    this.metaName = paramString1;
    this.atlasName = paramString2;
    this.importDir = paramString3;
    this.loader = paramImageLoader;
    this.xscale = paramInt1;
    this.mipLevels = paramInt2;
    this.mipPrefix = getMipMapPrefix(paramString2);
    this.needsHDWorkaround = paramBoolean;
  }
  
  private boolean addAllToMeta(MainActivity paramMainActivity)
    throws Exception
  {
    List localList = TextureUtils.getAllFilesFilter(paramMainActivity.textureOverrides, this.importDir);
    if (localList.size() == 0) {
      return false;
    }
    Object[] arrayOfObject = new Object[2];
    int i = localList.size() - 1;
    if (i >= 0)
    {
      Object localObject = (String)localList.get(i);
      if (!((String)localObject).toLowerCase().endsWith(".png")) {}
      for (;;)
      {
        i -= 1;
        break;
        parseNameParts((String)localObject, arrayOfObject);
        if (arrayOfObject[0] != null)
        {
          JSONArray localJSONArray = this.metaObj.getOrAddIcon((String)arrayOfObject[0], ((Integer)arrayOfObject[1]).intValue());
          localObject = readBitmap(paramMainActivity, (String)localObject);
          if (localObject != null) {
            placeIntoAtlas((Bitmap)localObject, localJSONArray, this.metaObj);
          }
        }
      }
    }
    return true;
  }
  
  private void calcXScale(MainActivity paramMainActivity, AtlasMeta paramAtlasMeta)
    throws IOException
  {
    paramMainActivity = TextureUtils.getAllFilesFilter(paramMainActivity.textureOverrides, this.importDir);
    int k = paramAtlasMeta.width / paramAtlasMeta.tileWidth * (paramAtlasMeta.height / paramAtlasMeta.tileWidth);
    int j = k - paramAtlasMeta.originalUVCount;
    int m = paramMainActivity.size();
    this.xscale = 1;
    for (int i = j; (i < m) && (this.xscale < 64); i = j + (this.xscale - 1) * k) {
      this.xscale *= 2;
    }
  }
  
  private Bitmap getMipMap(int paramInt)
  {
    int i = this.atlasImg.getWidth();
    int j = this.atlasImg.getHeight();
    return Bitmap.createScaledBitmap(this.atlasImg, i >> paramInt + 1, j >> paramInt + 1, true);
  }
  
  private String getMipMapPrefix(String paramString)
  {
    int i = paramString.lastIndexOf(".");
    return paramString.substring(0, i) + "_mip";
  }
  
  private void loadAtlas(MainActivity paramMainActivity)
    throws Exception
  {
    boolean bool2 = false;
    InputStream localInputStream = paramMainActivity.getInputStreamForAsset(this.metaName);
    Object localObject2 = new byte['?'];
    Object localObject1 = new ByteArrayOutputStream();
    for (;;)
    {
      int i = localInputStream.read((byte[])localObject2);
      if (i == -1) {
        break;
      }
      ((ByteArrayOutputStream)localObject1).write((byte[])localObject2, 0, i);
    }
    localInputStream.close();
    localInputStream = paramMainActivity.getInputStreamForAsset(this.atlasName);
    localObject2 = this.loader.load(localInputStream);
    localObject1 = new JSONArray(new String(((ByteArrayOutputStream)localObject1).toByteArray(), "UTF-8"));
    calcXScale(paramMainActivity, new AtlasMeta((JSONArray)localObject1, this.needsHDWorkaround, ((Bitmap)localObject2).getWidth()));
    scaleMeta((JSONArray)localObject1);
    this.metaObj = new AtlasMeta((JSONArray)localObject1, this.needsHDWorkaround, ((Bitmap)localObject2).getWidth() * this.xscale);
    this.atlasImg = scaleAtlas((Bitmap)localObject2);
    this.atlasCanvas = new Canvas(this.atlasImg);
    localInputStream.close();
    boolean bool1 = bool2;
    if (this.needsHDWorkaround)
    {
      bool1 = bool2;
      if (this.metaObj.width != this.atlasImg.getWidth()) {
        bool1 = true;
      }
    }
    this.hdWorkaroundActive = bool1;
  }
  
  private void parseNameParts(String paramString, Object[] paramArrayOfObject)
  {
    paramArrayOfObject[0] = null;
    String str = paramString.substring(paramString.lastIndexOf("/") + 1, paramString.lastIndexOf("."));
    int i = str.lastIndexOf("_");
    if (i < 0) {
      return;
    }
    paramString = str.substring(0, i);
    str = str.substring(i + 1);
    try
    {
      paramArrayOfObject[1] = Integer.valueOf(Integer.parseInt(str));
      paramArrayOfObject[0] = paramString;
      return;
    }
    catch (NumberFormatException paramString) {}
  }
  
  private void placeIntoAtlas(Bitmap paramBitmap, JSONArray paramJSONArray, AtlasMeta paramAtlasMeta)
    throws JSONException
  {
    Rect localRect1 = this.tempRect2;
    this.tempRect2.top = 0;
    localRect1.left = 0;
    localRect1 = this.tempRect2;
    Rect localRect2 = this.tempRect2;
    int i = paramBitmap.getWidth();
    localRect2.bottom = i;
    localRect1.right = i;
    i = this.atlasImg.getWidth();
    int j = this.atlasImg.getHeight();
    this.tempRect.left = ((int)(paramJSONArray.getDouble(0) * i / paramAtlasMeta.width + 0.5D));
    this.tempRect.right = ((int)(paramJSONArray.getDouble(2) * i / paramAtlasMeta.width + 0.5D));
    this.tempRect.top = ((int)(paramJSONArray.getDouble(1) * j / paramAtlasMeta.height + 0.5D));
    this.tempRect.bottom = ((int)(paramJSONArray.getDouble(3) * j / paramAtlasMeta.height + 0.5D));
    this.atlasCanvas.drawBitmap(paramBitmap, this.tempRect2, this.tempRect, this.tempPaint);
    if (this.hdWorkaroundActive)
    {
      this.tempRect.left = ((int)(paramJSONArray.getDouble(0) + 0.5D));
      this.tempRect.right = ((int)(paramJSONArray.getDouble(2) + 0.5D));
      this.tempRect.top = ((int)(paramJSONArray.getDouble(1) + 0.5D));
      this.tempRect.bottom = ((int)(paramJSONArray.getDouble(3) + 0.5D));
      this.atlasCanvas.drawBitmap(paramBitmap, this.tempRect2, this.tempRect, this.tempPaint);
    }
  }
  
  private Bitmap readBitmap(MainActivity paramMainActivity, String paramString)
    throws IOException
  {
    paramMainActivity = paramMainActivity.getInputStreamForAsset(paramString);
    if (paramMainActivity == null) {
      return null;
    }
    paramString = BitmapFactory.decodeStream(paramMainActivity);
    paramMainActivity.close();
    return paramString;
  }
  
  private Bitmap scaleAtlas(Bitmap paramBitmap)
  {
    int i = paramBitmap.getWidth();
    int j = paramBitmap.getHeight();
    Bitmap localBitmap = Bitmap.createBitmap(this.xscale * i, j, Bitmap.Config.ARGB_8888);
    int[] arrayOfInt = new int[i * j];
    paramBitmap.getPixels(arrayOfInt, 0, i, 0, 0, i, j);
    localBitmap.setPixels(arrayOfInt, 0, i, 0, 0, i, j);
    return localBitmap;
  }
  
  private void scaleMeta(JSONArray paramJSONArray)
    throws JSONException
  {
    if (this.xscale == 1) {}
    for (;;)
    {
      return;
      int k = paramJSONArray.length();
      int i = 0;
      while (i < k)
      {
        JSONArray localJSONArray1 = paramJSONArray.getJSONObject(i).getJSONArray("uvs");
        int m = localJSONArray1.length();
        int j = 0;
        while (j < m)
        {
          JSONArray localJSONArray2 = localJSONArray1.getJSONArray(j);
          localJSONArray2.put(4, localJSONArray2.getDouble(4) * this.xscale);
          j += 1;
        }
        i += 1;
      }
    }
  }
  
  public void close()
    throws IOException
  {
    this.atlasImg = null;
    this.metaObj = null;
    this.atlasCanvas = null;
    this.hasChanges = false;
  }
  
  public void dumpAtlas()
    throws IOException
  {
    FileOutputStream localFileOutputStream = new FileOutputStream(new File("/sdcard", "bl_atlas_dump_" + new File(this.atlasName).getName() + ".png"));
    this.atlasImg.compress(Bitmap.CompressFormat.PNG, 100, localFileOutputStream);
    localFileOutputStream.close();
    localFileOutputStream = new FileOutputStream(new File("/sdcard", "bl_atlas_dump_" + new File(this.atlasName).getName() + "mip0.png"));
    getMipMap(0).compress(Bitmap.CompressFormat.PNG, 100, localFileOutputStream);
    localFileOutputStream.close();
    localFileOutputStream = new FileOutputStream(new File("/sdcard", "bl_atlas_dump_" + new File(this.metaName).getName()));
    localFileOutputStream.write(this.metaObj.data.toString().getBytes("UTF-8"));
    localFileOutputStream.close();
  }
  
  public InputStream getInputStream(String paramString)
    throws IOException
  {
    if (!this.hasChanges) {}
    for (;;)
    {
      return null;
      if (paramString.equals(this.metaName)) {
        return new ByteArrayInputStream(this.metaObj.data.toString().getBytes("UTF-8"));
      }
      if (paramString.equals(this.atlasName))
      {
        paramString = new ByteArrayOutputStream();
        this.loader.save(this.atlasImg, paramString);
        return new ByteArrayInputStream(paramString.toByteArray());
      }
      if ((this.mipLevels > 0) && (paramString.startsWith(this.mipPrefix))) {
        try
        {
          int i = Integer.parseInt(paramString.substring(paramString.lastIndexOf("_mip") + 4, paramString.lastIndexOf(".")));
          if ((i >= 0) && (i < this.mipLevels))
          {
            paramString = new ByteArrayOutputStream();
            this.loader.save(getMipMap(i), paramString);
            paramString = new ByteArrayInputStream(paramString.toByteArray());
            return paramString;
          }
        }
        catch (NumberFormatException paramString)
        {
          paramString.printStackTrace();
        }
      }
    }
    return null;
  }
  
  public long getSize(String paramString)
  {
    return 0L;
  }
  
  public void initAtlas(MainActivity paramMainActivity)
    throws Exception
  {
    this.hasChanges = false;
    this.tempPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
    loadAtlas(paramMainActivity);
    this.hasChanges = addAllToMeta(paramMainActivity);
    this.atlasCanvas = null;
  }
  
  public List<String> listFiles()
    throws IOException
  {
    return new ArrayList();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\net\zhuoweizhang\mcpelauncher\texture\AtlasProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */