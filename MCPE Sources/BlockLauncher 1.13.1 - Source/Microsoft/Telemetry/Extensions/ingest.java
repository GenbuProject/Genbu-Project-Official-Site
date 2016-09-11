package Microsoft.Telemetry.Extensions;

import Microsoft.Telemetry.Extension;
import Microsoft.Telemetry.Extension.Schema;
import com.microsoft.bond.BondDataType;
import com.microsoft.bond.BondMirror;
import com.microsoft.bond.BondSerializable;
import com.microsoft.bond.FieldDef;
import com.microsoft.bond.Metadata;
import com.microsoft.bond.Modifier;
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

public class ingest
  extends Extension
{
  private long auth;
  private String clientIp;
  private long quality;
  private String time;
  private String uploadTime;
  private String userAgent;
  
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
  
  public final long getAuth()
  {
    return this.auth;
  }
  
  public final String getClientIp()
  {
    return this.clientIp;
  }
  
  public Object getField(FieldDef paramFieldDef)
  {
    switch (paramFieldDef.getId())
    {
    default: 
      return null;
    case 10: 
      return this.time;
    case 20: 
      return this.clientIp;
    case 30: 
      return Long.valueOf(this.auth);
    case 40: 
      return Long.valueOf(this.quality);
    case 50: 
      return this.uploadTime;
    }
    return this.userAgent;
  }
  
  public final long getQuality()
  {
    return this.quality;
  }
  
  public SchemaDef getSchema()
  {
    return getRuntimeSchema();
  }
  
  public final String getTime()
  {
    return this.time;
  }
  
  public final String getUploadTime()
  {
    return this.uploadTime;
  }
  
  public final String getUserAgent()
  {
    return this.userAgent;
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
      paramObject = (ingest)paramObject;
    } while ((!memberwiseCompareQuick((ingest)paramObject)) || (!memberwiseCompareDeep((ingest)paramObject)));
    return true;
  }
  
  protected boolean memberwiseCompareDeep(ingest paramingest)
  {
    int i;
    if (super.memberwiseCompareDeep(paramingest))
    {
      i = 1;
      if (i == 0) {
        break label81;
      }
      if (this.time != null) {
        break label67;
      }
      label21:
      i = 1;
      label23:
      if (i == 0) {
        break label100;
      }
      if (this.clientIp != null) {
        break label86;
      }
      label34:
      i = 1;
      label36:
      if (i == 0) {
        break label119;
      }
      if (this.uploadTime != null) {
        break label105;
      }
      label47:
      i = 1;
      label49:
      if (i == 0) {
        break label138;
      }
      if (this.userAgent != null) {
        break label124;
      }
    }
    label67:
    label81:
    label86:
    label100:
    label105:
    label119:
    label124:
    while (this.userAgent.equals(paramingest.userAgent))
    {
      return true;
      i = 0;
      break;
      if (this.time.equals(paramingest.time)) {
        break label21;
      }
      i = 0;
      break label23;
      if (this.clientIp.equals(paramingest.clientIp)) {
        break label34;
      }
      i = 0;
      break label36;
      if (this.uploadTime.equals(paramingest.uploadTime)) {
        break label47;
      }
      i = 0;
      break label49;
    }
    label138:
    return false;
  }
  
  protected boolean memberwiseCompareQuick(ingest paramingest)
  {
    int i;
    label23:
    int j;
    if (super.memberwiseCompareQuick(paramingest))
    {
      i = 1;
      if (i == 0) {
        break label229;
      }
      if (this.time != null) {
        break label219;
      }
      i = 1;
      if (paramingest.time != null) {
        break label224;
      }
      j = 1;
      label32:
      if (i != j) {
        break label229;
      }
      i = 1;
      label39:
      if (i == 0) {
        break label251;
      }
      if (this.time != null) {
        break label234;
      }
      label50:
      i = 1;
      label52:
      if (i == 0) {
        break label266;
      }
      if (this.clientIp != null) {
        break label256;
      }
      i = 1;
      label65:
      if (paramingest.clientIp != null) {
        break label261;
      }
      j = 1;
      label74:
      if (i != j) {
        break label266;
      }
      i = 1;
      label81:
      if (i == 0) {
        break label288;
      }
      if (this.clientIp != null) {
        break label271;
      }
      label92:
      i = 1;
      label94:
      if ((i == 0) || (this.auth != paramingest.auth)) {
        break label293;
      }
      i = 1;
      label112:
      if ((i == 0) || (this.quality != paramingest.quality)) {
        break label298;
      }
      i = 1;
      label130:
      if (i == 0) {
        break label313;
      }
      if (this.uploadTime != null) {
        break label303;
      }
      i = 1;
      label143:
      if (paramingest.uploadTime != null) {
        break label308;
      }
      j = 1;
      label152:
      if (i != j) {
        break label313;
      }
      i = 1;
      label159:
      if (i == 0) {
        break label335;
      }
      if (this.uploadTime != null) {
        break label318;
      }
      label170:
      i = 1;
      label172:
      if (i == 0) {
        break label350;
      }
      if (this.userAgent != null) {
        break label340;
      }
      i = 1;
      label185:
      if (paramingest.userAgent != null) {
        break label345;
      }
      j = 1;
      label194:
      if (i != j) {
        break label350;
      }
      i = 1;
      label201:
      if (i == 0) {
        break label372;
      }
      if (this.userAgent != null) {
        break label355;
      }
    }
    label219:
    label224:
    label229:
    label234:
    label251:
    label256:
    label261:
    label266:
    label271:
    label288:
    label293:
    label298:
    label303:
    label308:
    label313:
    label318:
    label335:
    label340:
    label345:
    label350:
    label355:
    while (this.userAgent.length() == paramingest.userAgent.length())
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
      if (this.time.length() == paramingest.time.length()) {
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
      if (this.clientIp.length() == paramingest.clientIp.length()) {
        break label92;
      }
      i = 0;
      break label94;
      i = 0;
      break label112;
      i = 0;
      break label130;
      i = 0;
      break label143;
      j = 0;
      break label152;
      i = 0;
      break label159;
      if (this.uploadTime.length() == paramingest.uploadTime.length()) {
        break label170;
      }
      i = 0;
      break label172;
      i = 0;
      break label185;
      j = 0;
      break label194;
      i = 0;
      break label201;
    }
    label372:
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
      this.time = ReadHelper.readString(paramProtocolReader, localFieldTag.type);
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
        this.clientIp = ReadHelper.readString(paramProtocolReader, localFieldTag.type);
        break;
      case 30: 
        this.auth = ReadHelper.readInt64(paramProtocolReader, localFieldTag.type);
        break;
      case 40: 
        this.quality = ReadHelper.readInt64(paramProtocolReader, localFieldTag.type);
        break;
      case 50: 
        this.uploadTime = ReadHelper.readString(paramProtocolReader, localFieldTag.type);
        break;
      case 60: 
        this.userAgent = ReadHelper.readString(paramProtocolReader, localFieldTag.type);
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
      this.time = paramProtocolReader.readString();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.clientIp = paramProtocolReader.readString();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.auth = paramProtocolReader.readInt64();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.quality = paramProtocolReader.readInt64();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.uploadTime = paramProtocolReader.readString();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.userAgent = paramProtocolReader.readString();
    }
    paramProtocolReader.readStructEnd();
  }
  
  public void reset()
  {
    reset("ingest", "Microsoft.Telemetry.Extensions.ingest");
  }
  
  protected void reset(String paramString1, String paramString2)
  {
    super.reset(paramString1, paramString2);
    this.time = "";
    this.clientIp = "";
    this.auth = 0L;
    this.quality = 0L;
    this.uploadTime = "";
    this.userAgent = "";
  }
  
  public final void setAuth(long paramLong)
  {
    this.auth = paramLong;
  }
  
  public final void setClientIp(String paramString)
  {
    this.clientIp = paramString;
  }
  
  public void setField(FieldDef paramFieldDef, Object paramObject)
  {
    switch (paramFieldDef.getId())
    {
    default: 
      return;
    case 10: 
      this.time = ((String)paramObject);
      return;
    case 20: 
      this.clientIp = ((String)paramObject);
      return;
    case 30: 
      this.auth = ((Long)paramObject).longValue();
      return;
    case 40: 
      this.quality = ((Long)paramObject).longValue();
      return;
    case 50: 
      this.uploadTime = ((String)paramObject);
      return;
    }
    this.userAgent = ((String)paramObject);
  }
  
  public final void setQuality(long paramLong)
  {
    this.quality = paramLong;
  }
  
  public final void setTime(String paramString)
  {
    this.time = paramString;
  }
  
  public final void setUploadTime(String paramString)
  {
    this.uploadTime = paramString;
  }
  
  public final void setUserAgent(String paramString)
  {
    this.userAgent = paramString;
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
    paramProtocolWriter.writeFieldBegin(BondDataType.BT_STRING, 10, Schema.time_metadata);
    paramProtocolWriter.writeString(this.time);
    paramProtocolWriter.writeFieldEnd();
    paramProtocolWriter.writeFieldBegin(BondDataType.BT_STRING, 20, Schema.clientIp_metadata);
    paramProtocolWriter.writeString(this.clientIp);
    paramProtocolWriter.writeFieldEnd();
    if ((!bool) || (this.auth != Schema.auth_metadata.getDefault_value().getInt_value()))
    {
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_INT64, 30, Schema.auth_metadata);
      paramProtocolWriter.writeInt64(this.auth);
      paramProtocolWriter.writeFieldEnd();
      if ((bool) && (this.quality == Schema.quality_metadata.getDefault_value().getInt_value())) {
        break label269;
      }
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_INT64, 40, Schema.quality_metadata);
      paramProtocolWriter.writeInt64(this.quality);
      paramProtocolWriter.writeFieldEnd();
      label160:
      if ((bool) && (this.uploadTime == Schema.uploadTime_metadata.getDefault_value().getString_value())) {
        break label284;
      }
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_STRING, 50, Schema.uploadTime_metadata);
      paramProtocolWriter.writeString(this.uploadTime);
      paramProtocolWriter.writeFieldEnd();
      label204:
      if ((bool) && (this.userAgent == Schema.userAgent_metadata.getDefault_value().getString_value())) {
        break label299;
      }
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_STRING, 60, Schema.userAgent_metadata);
      paramProtocolWriter.writeString(this.userAgent);
      paramProtocolWriter.writeFieldEnd();
    }
    for (;;)
    {
      paramProtocolWriter.writeStructEnd(paramBoolean);
      return;
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_INT64, 30, Schema.auth_metadata);
      break;
      label269:
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_INT64, 40, Schema.quality_metadata);
      break label160;
      label284:
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_STRING, 50, Schema.uploadTime_metadata);
      break label204;
      label299:
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_STRING, 60, Schema.userAgent_metadata);
    }
  }
  
  public static class Schema
  {
    private static final Metadata auth_metadata;
    private static final Metadata clientIp_metadata;
    public static final Metadata metadata = new Metadata();
    private static final Metadata quality_metadata;
    public static final SchemaDef schemaDef;
    private static final Metadata time_metadata;
    private static final Metadata uploadTime_metadata;
    private static final Metadata userAgent_metadata;
    
    static
    {
      metadata.setName("ingest");
      metadata.setQualified_name("Microsoft.Telemetry.Extensions.ingest");
      metadata.getAttributes().put("Description", "Describes the fields added dynamically by the service. Clients should NOT use this section since it is adding dynamically by the service.");
      time_metadata = new Metadata();
      time_metadata.setName("time");
      time_metadata.setModifier(Modifier.Required);
      time_metadata.getAttributes().put("Name", "IngestDateTime");
      clientIp_metadata = new Metadata();
      clientIp_metadata.setName("clientIp");
      clientIp_metadata.setModifier(Modifier.Required);
      clientIp_metadata.getAttributes().put("Name", "ClientIp");
      auth_metadata = new Metadata();
      auth_metadata.setName("auth");
      auth_metadata.getAttributes().put("Name", "DataAuthorization");
      auth_metadata.getDefault_value().setInt_value(0L);
      quality_metadata = new Metadata();
      quality_metadata.setName("quality");
      quality_metadata.getAttributes().put("Name", "DataQuality");
      quality_metadata.getDefault_value().setInt_value(0L);
      uploadTime_metadata = new Metadata();
      uploadTime_metadata.setName("uploadTime");
      uploadTime_metadata.getAttributes().put("Name", "UploadDateTime");
      userAgent_metadata = new Metadata();
      userAgent_metadata.setName("userAgent");
      userAgent_metadata.getAttributes().put("Name", "UserAgent");
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
      paramSchemaDef.setMetadata(time_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_STRING);
      localStructDef.getFields().add(paramSchemaDef);
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)20);
      paramSchemaDef.setMetadata(clientIp_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_STRING);
      localStructDef.getFields().add(paramSchemaDef);
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)30);
      paramSchemaDef.setMetadata(auth_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_INT64);
      localStructDef.getFields().add(paramSchemaDef);
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)40);
      paramSchemaDef.setMetadata(quality_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_INT64);
      localStructDef.getFields().add(paramSchemaDef);
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)50);
      paramSchemaDef.setMetadata(uploadTime_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_STRING);
      localStructDef.getFields().add(paramSchemaDef);
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)60);
      paramSchemaDef.setMetadata(userAgent_metadata);
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


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\Microsoft\Telemetry\Extensions\ingest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */