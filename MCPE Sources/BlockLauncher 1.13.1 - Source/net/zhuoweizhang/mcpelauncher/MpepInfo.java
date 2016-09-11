package net.zhuoweizhang.mcpelauncher;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.json.JSONException;
import org.json.JSONObject;

public class MpepInfo
{
  public String authorName;
  public List<String> modFiles;
  public String modName;
  public String modNote;
  public String modVersion;
  public String scrambleCode;
  
  public static MpepInfo fromJSON(JSONObject paramJSONObject)
    throws JSONException
  {
    MpepInfo localMpepInfo = new MpepInfo();
    paramJSONObject = paramJSONObject.getJSONObject("mod");
    localMpepInfo.modName = paramJSONObject.optString("mod_name");
    localMpepInfo.authorName = paramJSONObject.optString("author_name");
    localMpepInfo.modVersion = paramJSONObject.optString("mod_version");
    localMpepInfo.modNote = paramJSONObject.optString("mod_note");
    localMpepInfo.scrambleCode = paramJSONObject.optString("scramble_code");
    return localMpepInfo;
  }
  
  public static MpepInfo fromZip(ZipFile paramZipFile)
    throws IOException, JSONException
  {
    Object localObject = paramZipFile.getEntry("metadata/metadata.json");
    if (localObject == null) {
      return null;
    }
    paramZipFile = paramZipFile.getInputStream((ZipEntry)localObject);
    localObject = new byte[(int)((ZipEntry)localObject).getSize()];
    paramZipFile.read((byte[])localObject);
    paramZipFile.close();
    return fromJSON(new JSONObject(new String((byte[])localObject, Charset.forName("UTF-8"))));
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\net\zhuoweizhang\mcpelauncher\MpepInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */