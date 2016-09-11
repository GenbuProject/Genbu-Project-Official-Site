package Microsoft.Telemetry.Extensions;

import Microsoft.Telemetry.Extension;
import Microsoft.Telemetry.Extension.Schema;
import com.microsoft.bond.BondDataType;
import com.microsoft.bond.BondMirror;
import com.microsoft.bond.BondSerializable;
import com.microsoft.bond.FieldDef;
import com.microsoft.bond.Metadata;
import com.microsoft.bond.ProtocolCapability;
import com.microsoft.bond.ProtocolReader;
import com.microsoft.bond.ProtocolReader.FieldTag;
import com.microsoft.bond.ProtocolWriter;
import com.microsoft.bond.SchemaDef;
import com.microsoft.bond.StructDef;
import com.microsoft.bond.TypeDef;
import com.microsoft.bond.Variant;
import com.microsoft.bond.internal.Marshaler;
import com.microsoft.bond.internal.ReadHelper;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class device
  extends Extension
{
  private String authId;
  private String authSecId;
  private String deviceClass;
  private String id;
  private String localId;
  
  public static SchemaDef getRuntimeSchema()
  {
    return Schema.schemaDef;
  }
  
  public BondSerializable clone()
  {
    return null;
  }
  
  public BondMirror createInstance(StructDef paramStructDef)
  {
    return null;
  }
  
  public final String getAuthId()
  {
    return this.authId;
  }
  
  public final String getAuthSecId()
  {
    return this.authSecId;
  }
  
  public final String getDeviceClass()
  {
    return this.deviceClass;
  }
  
  public Object getField(FieldDef paramFieldDef)
  {
    switch (paramFieldDef.getId())
    {
    default: 
      return null;
    case 10: 
      return this.id;
    case 20: 
      return this.localId;
    case 30: 
      return this.authId;
    case 40: 
      return this.authSecId;
    }
    return this.deviceClass;
  }
  
  public final String getId()
  {
    return this.id;
  }
  
  public final String getLocalId()
  {
    return this.localId;
  }
  
  public SchemaDef getSchema()
  {
    return getRuntimeSchema();
  }
  
  public void marshal(ProtocolWriter paramProtocolWriter)
    throws IOException
  {
    Marshaler.marshal(this, paramProtocolWriter);
  }
  
  public boolean memberwiseCompare(Object paramObject)
  {
    if (paramObject == null) {}
    do
    {
      return false;
      paramObject = (device)paramObject;
    } while ((!memberwiseCompareQuick((device)paramObject)) || (!memberwiseCompareDeep((device)paramObject)));
    return true;
  }
  
  protected boolean memberwiseCompareDeep(device paramdevice)
  {
    int i;
    if (super.memberwiseCompareDeep(paramdevice))
    {
      i = 1;
      if (i == 0) {
        break label94;
      }
      if (this.id != null) {
        break label80;
      }
      label21:
      i = 1;
      label23:
      if (i == 0) {
        break label113;
      }
      if (this.localId != null) {
        break label99;
      }
      label34:
      i = 1;
      label36:
      if (i == 0) {
        break label132;
      }
      if (this.authId != null) {
        break label118;
      }
      label47:
      i = 1;
      label49:
      if (i == 0) {
        break label151;
      }
      if (this.authSecId != null) {
        break label137;
      }
      label60:
      i = 1;
      label62:
      if (i == 0) {
        break label170;
      }
      if (this.deviceClass != null) {
        break label156;
      }
    }
    label80:
    label94:
    label99:
    label113:
    label118:
    label132:
    label137:
    label151:
    label156:
    while (this.deviceClass.equals(paramdevice.deviceClass))
    {
      return true;
      i = 0;
      break;
      if (this.id.equals(paramdevice.id)) {
        break label21;
      }
      i = 0;
      break label23;
      if (this.localId.equals(paramdevice.localId)) {
        break label34;
      }
      i = 0;
      break label36;
      if (this.authId.equals(paramdevice.authId)) {
        break label47;
      }
      i = 0;
      break label49;
      if (this.authSecId.equals(paramdevice.authSecId)) {
        break label60;
      }
      i = 0;
      break label62;
    }
    label170:
    return false;
  }
  
  protected boolean memberwiseCompareQuick(device paramdevice)
  {
    int i;
    label23:
    int j;
    if (super.memberwiseCompareQuick(paramdevice))
    {
      i = 1;
      if (i == 0) {
        break label235;
      }
      if (this.id != null) {
        break label225;
      }
      i = 1;
      if (paramdevice.id != null) {
        break label230;
      }
      j = 1;
      label32:
      if (i != j) {
        break label235;
      }
      i = 1;
      label39:
      if (i == 0) {
        break label257;
      }
      if (this.id != null) {
        break label240;
      }
      label50:
      i = 1;
      label52:
      if (i == 0) {
        break label272;
      }
      if (this.localId != null) {
        break label262;
      }
      i = 1;
      label65:
      if (paramdevice.localId != null) {
        break label267;
      }
      j = 1;
      label74:
      if (i != j) {
        break label272;
      }
      i = 1;
      label81:
      if (i == 0) {
        break label294;
      }
      if (this.localId != null) {
        break label277;
      }
      label92:
      i = 1;
      label94:
      if (i == 0) {
        break label309;
      }
      if (this.authId != null) {
        break label299;
      }
      i = 1;
      label107:
      if (paramdevice.authId != null) {
        break label304;
      }
      j = 1;
      label116:
      if (i != j) {
        break label309;
      }
      i = 1;
      label123:
      if (i == 0) {
        break label331;
      }
      if (this.authId != null) {
        break label314;
      }
      label134:
      i = 1;
      label136:
      if (i == 0) {
        break label346;
      }
      if (this.authSecId != null) {
        break label336;
      }
      i = 1;
      label149:
      if (paramdevice.authSecId != null) {
        break label341;
      }
      j = 1;
      label158:
      if (i != j) {
        break label346;
      }
      i = 1;
      label165:
      if (i == 0) {
        break label368;
      }
      if (this.authSecId != null) {
        break label351;
      }
      label176:
      i = 1;
      label178:
      if (i == 0) {
        break label383;
      }
      if (this.deviceClass != null) {
        break label373;
      }
      i = 1;
      label191:
      if (paramdevice.deviceClass != null) {
        break label378;
      }
      j = 1;
      label200:
      if (i != j) {
        break label383;
      }
      i = 1;
      label207:
      if (i == 0) {
        break label405;
      }
      if (this.deviceClass != null) {
        break label388;
      }
    }
    label225:
    label230:
    label235:
    label240:
    label257:
    label262:
    label267:
    label272:
    label277:
    label294:
    label299:
    label304:
    label309:
    label314:
    label331:
    label336:
    label341:
    label346:
    label351:
    label368:
    label373:
    label378:
    label383:
    label388:
    while (this.deviceClass.length() == paramdevice.deviceClass.length())
    {
      return true;
      i = 0;
      break;
      i = 0;
      break label23;
      j = 0;
      break label32;
      i = 0;
      break label39;
      if (this.id.length() == paramdevice.id.length()) {
        break label50;
      }
      i = 0;
      break label52;
      i = 0;
      break label65;
      j = 0;
      break label74;
      i = 0;
      break label81;
      if (this.localId.length() == paramdevice.localId.length()) {
        break label92;
      }
      i = 0;
      break label94;
      i = 0;
      break label107;
      j = 0;
      break label116;
      i = 0;
      break label123;
      if (this.authId.length() == paramdevice.authId.length()) {
        break label134;
      }
      i = 0;
      break label136;
      i = 0;
      break label149;
      j = 0;
      break label158;
      i = 0;
      break label165;
      if (this.authSecId.length() == paramdevice.authSecId.length()) {
        break label176;
      }
      i = 0;
      break label178;
      i = 0;
      break label191;
      j = 0;
      break label200;
      i = 0;
      break label207;
    }
    label405:
    return false;
  }
  
  public void read(ProtocolReader paramProtocolReader)
    throws IOException
  {
    paramProtocolReader.readBegin();
    readNested(paramProtocolReader);
    paramProtocolReader.readEnd();
  }
  
  public void read(ProtocolReader paramProtocolReader, BondSerializable paramBondSerializable)
    throws IOException
  {}
  
  public void readNested(ProtocolReader paramProtocolReader)
    throws IOException
  {
    if (!paramProtocolReader.hasCapability(ProtocolCapability.TAGGED)) {
      readUntagged(paramProtocolReader, false);
    }
    while (!readTagged(paramProtocolReader, false)) {
      return;
    }
    ReadHelper.skipPartialStruct(paramProtocolReader);
  }
  
  protected boolean readTagged(ProtocolReader paramProtocolReader, boolean paramBoolean)
    throws IOException
  {
    boolean bool = false;
    paramProtocolReader.readStructBegin(paramBoolean);
    ProtocolReader.FieldTag localFieldTag;
    if (!super.readTagged(paramProtocolReader, true))
    {
      return false;
      this.id = ReadHelper.readString(paramProtocolReader, localFieldTag.type);
    }
    for (;;)
    {
      paramProtocolReader.readFieldEnd();
      localFieldTag = paramProtocolReader.readFieldBegin();
      if ((localFieldTag.type == BondDataType.BT_STOP) || (localFieldTag.type == BondDataType.BT_STOP_BASE))
      {
        paramBoolean = bool;
        if (localFieldTag.type == BondDataType.BT_STOP_BASE) {
          paramBoolean = true;
        }
        paramProtocolReader.readStructEnd();
        return paramBoolean;
      }
      switch (localFieldTag.id)
      {
      case 10: 
      default: 
        paramProtocolReader.skip(localFieldTag.type);
        break;
      case 20: 
        this.localId = ReadHelper.readString(paramProtocolReader, localFieldTag.type);
        break;
      case 30: 
        this.authId = ReadHelper.readString(paramProtocolReader, localFieldTag.type);
        break;
      case 40: 
        this.authSecId = ReadHelper.readString(paramProtocolReader, localFieldTag.type);
        break;
      case 50: 
        this.deviceClass = ReadHelper.readString(paramProtocolReader, localFieldTag.type);
      }
    }
  }
  
  protected void readUntagged(ProtocolReader paramProtocolReader, boolean paramBoolean)
    throws IOException
  {
    boolean bool = paramProtocolReader.hasCapability(ProtocolCapability.CAN_OMIT_FIELDS);
    paramProtocolReader.readStructBegin(paramBoolean);
    super.readUntagged(paramProtocolReader, true);
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.id = paramProtocolReader.readString();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.localId = paramProtocolReader.readString();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.authId = paramProtocolReader.readString();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.authSecId = paramProtocolReader.readString();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.deviceClass = paramProtocolReader.readString();
    }
    paramProtocolReader.readStructEnd();
  }
  
  public void reset()
  {
    reset("device", "Microsoft.Telemetry.Extensions.device");
  }
  
  protected void reset(String paramString1, String paramString2)
  {
    super.reset(paramString1, paramString2);
    this.id = "";
    this.localId = "";
    this.authId = "";
    this.authSecId = "";
    this.deviceClass = "";
  }
  
  public final void setAuthId(String paramString)
  {
    this.authId = paramString;
  }
  
  public final void setAuthSecId(String paramString)
  {
    this.authSecId = paramString;
  }
  
  public final void setDeviceClass(String paramString)
  {
    this.deviceClass = paramString;
  }
  
  public void setField(FieldDef paramFieldDef, Object paramObject)
  {
    switch (paramFieldDef.getId())
    {
    default: 
      return;
    case 10: 
      this.id = ((String)paramObject);
      return;
    case 20: 
      this.localId = ((String)paramObject);
      return;
    case 30: 
      this.authId = ((String)paramObject);
      return;
    case 40: 
      this.authSecId = ((String)paramObject);
      return;
    }
    this.deviceClass = ((String)paramObject);
  }
  
  public final void setId(String paramString)
  {
    this.id = paramString;
  }
  
  public final void setLocalId(String paramString)
  {
    this.localId = paramString;
  }
  
  public void unmarshal(InputStream paramInputStream)
    throws IOException
  {
    Marshaler.unmarshal(paramInputStream, this);
  }
  
  public void unmarshal(InputStream paramInputStream, BondSerializable paramBondSerializable)
    throws IOException
  {
    Marshaler.unmarshal(paramInputStream, (SchemaDef)paramBondSerializable, this);
  }
  
  public void write(ProtocolWriter paramProtocolWriter)
    throws IOException
  {
    paramProtocolWriter.writeBegin();
    ProtocolWriter localProtocolWriter = paramProtocolWriter.getFirstPassWriter();
    if (localProtocolWriter != null)
    {
      writeNested(localProtocolWriter, false);
      writeNested(paramProtocolWriter, false);
    }
    for (;;)
    {
      paramProtocolWriter.writeEnd();
      return;
      writeNested(paramProtocolWriter, false);
    }
  }
  
  public void writeNested(ProtocolWriter paramProtocolWriter, boolean paramBoolean)
    throws IOException
  {
    boolean bool = paramProtocolWriter.hasCapability(ProtocolCapability.CAN_OMIT_FIELDS);
    paramProtocolWriter.writeStructBegin(Schema.metadata, paramBoolean);
    super.writeNested(paramProtocolWriter, true);
    if ((!bool) || (this.id != Schema.id_metadata.getDefault_value().getString_value()))
    {
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_STRING, 10, Schema.id_metadata);
      paramProtocolWriter.writeString(this.id);
      paramProtocolWriter.writeFieldEnd();
      if ((bool) && (this.localId == Schema.localId_metadata.getDefault_value().getString_value())) {
        break label263;
      }
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_STRING, 20, Schema.localId_metadata);
      paramProtocolWriter.writeString(this.localId);
      paramProtocolWriter.writeFieldEnd();
      label110:
      if ((bool) && (this.authId == Schema.authId_metadata.getDefault_value().getString_value())) {
        break label278;
      }
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_STRING, 30, Schema.authId_metadata);
      paramProtocolWriter.writeString(this.authId);
      paramProtocolWriter.writeFieldEnd();
      label154:
      if ((bool) && (this.authSecId == Schema.authSecId_metadata.getDefault_value().getString_value())) {
        break label293;
      }
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_STRING, 40, Schema.authSecId_metadata);
      paramProtocolWriter.writeString(this.authSecId);
      paramProtocolWriter.writeFieldEnd();
      label198:
      if ((bool) && (this.deviceClass == Schema.deviceClass_metadata.getDefault_value().getString_value())) {
        break label308;
      }
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_STRING, 50, Schema.deviceClass_metadata);
      paramProtocolWriter.writeString(this.deviceClass);
      paramProtocolWriter.writeFieldEnd();
    }
    for (;;)
    {
      paramProtocolWriter.writeStructEnd(paramBoolean);
      return;
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_STRING, 10, Schema.id_metadata);
      break;
      label263:
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_STRING, 20, Schema.localId_metadata);
      break label110;
      label278:
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_STRING, 30, Schema.authId_metadata);
      break label154;
      label293:
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_STRING, 40, Schema.authSecId_metadata);
      break label198;
      label308:
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_STRING, 50, Schema.deviceClass_metadata);
    }
  }
  
  public static class Schema
  {
    private static final Metadata authId_metadata;
    private static final Metadata authSecId_metadata;
    private static final Metadata deviceClass_metadata;
    private static final Metadata id_metadata;
    private static final Metadata localId_metadata;
    public static final Metadata metadata = new Metadata();
    public static final SchemaDef schemaDef;
    
    static
    {
      metadata.setName("device");
      metadata.setQualified_name("Microsoft.Telemetry.Extensions.device");
      metadata.getAttributes().put("Description", "Describes the device related fields. See https://osgwiki.com/wiki/CommonSchema/device_id");
      id_metadata = new Metadata();
      id_metadata.setName("id");
      id_metadata.getAttributes().put("Description", "Unique device Id. Clients aren't expected to set this; instead the service will decide the best ID to use here. Clients may set this if they believe they have the best device ID already. Format is <NamespaceIdentifier>:<Id> for example, x:12345678.");
      localId_metadata = new Metadata();
      localId_metadata.setName("localId");
      localId_metadata.getAttributes().put("Description", "Local device identifier according to the client. Format is <NamespaceIdentifier>:<Id> for example, x:12345678.");
      authId_metadata = new Metadata();
      authId_metadata.setName("authId");
      authId_metadata.getAttributes().put("Description", "This is the ID of the device associated with this event, deduced from a token such as an MSA ticket or Xbox xtoken. For MSA tickets this is expected to be the MSA Global ID.");
      authId_metadata.getAttributes().put("Name", "DeviceAuthID");
      authSecId_metadata = new Metadata();
      authSecId_metadata.setName("authSecId");
      authSecId_metadata.getAttributes().put("Description", "This is the secondary ID of the device associated with this event, deduced from a token such as an MSA ticket or Xbox xtoken. For MSA tickets this is expected to be the MSA Hardware ID.");
      authSecId_metadata.getAttributes().put("Name", "DeviceAuthSecondID");
      deviceClass_metadata = new Metadata();
      deviceClass_metadata.setName("deviceClass");
      deviceClass_metadata.getAttributes().put("Description", "Platform of the device. Not to be confused with the Windows concept of device class which is different; Windows calls this Platform. See the Windows function RtlConvertPlatformInfoToString.  Legitimate values are: Windows.Universal, Windows.Windows8x, Windows.WindowsPhone8x, Windows.Desktop, Windows.Mobile, Windows.Xbox, Windows.PPI, Windows.IOT, Windows.IoTHeadless, Windows.Server, Windows.Analog, Windows.XBoxSRA, Windows.XBoxERA.");
      schemaDef = new SchemaDef();
      schemaDef.setRoot(getTypeDef(schemaDef));
    }
    
    private static short getStructDef(SchemaDef paramSchemaDef)
    {
      short s;
      for (int i = 0; i < paramSchemaDef.getStructs().size(); s = (short)(i + 1)) {
        if (((StructDef)paramSchemaDef.getStructs().get(i)).getMetadata() == metadata) {
          return i;
        }
      }
      StructDef localStructDef = new StructDef();
      paramSchemaDef.getStructs().add(localStructDef);
      localStructDef.setMetadata(metadata);
      localStructDef.setBase_def(Extension.Schema.getTypeDef(paramSchemaDef));
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)10);
      paramSchemaDef.setMetadata(id_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_STRING);
      localStructDef.getFields().add(paramSchemaDef);
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)20);
      paramSchemaDef.setMetadata(localId_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_STRING);
      localStructDef.getFields().add(paramSchemaDef);
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)30);
      paramSchemaDef.setMetadata(authId_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_STRING);
      localStructDef.getFields().add(paramSchemaDef);
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)40);
      paramSchemaDef.setMetadata(authSecId_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_STRING);
      localStructDef.getFields().add(paramSchemaDef);
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)50);
      paramSchemaDef.setMetadata(deviceClass_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_STRING);
      localStructDef.getFields().add(paramSchemaDef);
      return s;
    }
    
    public static TypeDef getTypeDef(SchemaDef paramSchemaDef)
    {
      TypeDef localTypeDef = new TypeDef();
      localTypeDef.setId(BondDataType.BT_STRUCT);
      localTypeDef.setStruct_def(getStructDef(paramSchemaDef));
      return localTypeDef;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\Microsoft\Telemetry\Extensions\device.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */