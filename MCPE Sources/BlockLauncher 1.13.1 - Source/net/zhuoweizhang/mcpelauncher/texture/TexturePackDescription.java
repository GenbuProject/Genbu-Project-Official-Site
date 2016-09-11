package net.zhuoweizhang.mcpelauncher.texture;

import android.graphics.Bitmap;
import org.json.JSONException;
import org.json.JSONObject;

public final class TexturePackDescription
{
  public String description = "";
  public Bitmap img = null;
  public String path;
  public String type;
  
  public TexturePackDescription(String paramString1, String paramString2)
  {
    this.type = paramString1;
    this.path = paramString2;
  }
  
  public static TexturePackDescription fromJson(JSONObject paramJSONObject)
    throws JSONException
  {
    return new TexturePackDescription(paramJSONObject.getString("t"), paramJSONObject.getString("p"));
  }
  
  public JSONObject toJson()
    throws JSONException
  {
    return new JSONObject().put("t", this.type).put("p", this.path);
  }
  
  public String toString()
  {
    return this.type + ":" + this.path;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\net\zhuoweizhang\mcpelauncher\texture\TexturePackDescription.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */