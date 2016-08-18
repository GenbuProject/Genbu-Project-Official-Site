package Microsoft.Android.LoggingLibrary;

import Ms.Telemetry.CllHeartBeat;
import com.microsoft.telemetry.Data;
import com.microsoft.telemetry.IJsonSerializable;
import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashMap;

public class Snapshot
  extends Data<CllHeartBeat>
  implements IJsonSerializable
{
  public Snapshot()
  {
    InitializeFields();
    SetupAttributes();
  }
  
  protected void InitializeFields()
  {
    this.QualifiedName = "Microsoft.Android.LoggingLibrary.Snapshot";
  }
  
  public void SetupAttributes()
  {
    this.Attributes.put("Description", "Android's Client Telemetry Snapshot");
  }
  
  protected String serializeContent(Writer paramWriter)
    throws IOException
  {
    return super.serializeContent(paramWriter);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\Microsoft\Android\LoggingLibrary\Snapshot.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */