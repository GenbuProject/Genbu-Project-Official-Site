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

public class cloud
  extends Extension
{
  private String deploymentUnit;
  private String environment;
  private String location;
  private String name;
  private String role;
  private String roleInstance;
  private String roleVer;
  
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
  
  public final String getDeploymentUnit()
  {
    return this.deploymentUnit;
  }
  
  public final String getEnvironment()
  {
    return this.environment;
  }
  
  public Object getField(FieldDef paramFieldDef)
  {
    switch (paramFieldDef.getId())
    {
    default: 
      return null;
    case 10: 
      return this.name;
    case 20: 
      return this.role;
    case 30: 
      return this.roleInstance;
    case 40: 
      return this.location;
    case 50: 
      return this.roleVer;
    case 60: 
      return this.environment;
    }
    return this.deploymentUnit;
  }
  
  public final String getLocation()
  {
    return this.location;
  }
  
  public final String getName()
  {
    return this.name;
  }
  
  public final String getRole()
  {
    return this.role;
  }
  
  public final String getRoleInstance()
  {
    return this.roleInstance;
  }
  
  public final String getRoleVer()
  {
    return this.roleVer;
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
      paramObject = (cloud)paramObject;
    } while ((!memberwiseCompareQuick((cloud)paramObject)) || (!memberwiseCompareDeep((cloud)paramObject)));
    return true;
  }
  
  protected boolean memberwiseCompareDeep(cloud paramcloud)
  {
    int i;
    if ((1 != 0) && (super.memberwiseCompareDeep(paramcloud)))
    {
      i = 1;
      if (i == 0) {
        break label124;
      }
      if (this.name != null) {
        break label110;
      }
      label25:
      i = 1;
      label27:
      if (i == 0) {
        break label143;
      }
      if (this.role != null) {
        break label129;
      }
      label38:
      i = 1;
      label40:
      if (i == 0) {
        break label162;
      }
      if (this.roleInstance != null) {
        break label148;
      }
      label51:
      i = 1;
      label53:
      if (i == 0) {
        break label181;
      }
      if (this.location != null) {
        break label167;
      }
      label64:
      i = 1;
      label66:
      if (i == 0) {
        break label200;
      }
      if (this.roleVer != null) {
        break label186;
      }
      label77:
      i = 1;
      label79:
      if (i == 0) {
        break label219;
      }
      if (this.environment != null) {
        break label205;
      }
      label90:
      i = 1;
      label92:
      if (i == 0) {
        break label238;
      }
      if (this.deploymentUnit != null) {
        break label224;
      }
    }
    label110:
    label124:
    label129:
    label143:
    label148:
    label162:
    label167:
    label181:
    label186:
    label200:
    label205:
    label219:
    label224:
    while (this.deploymentUnit.equals(paramcloud.deploymentUnit))
    {
      return true;
      i = 0;
      break;
      if (this.name.equals(paramcloud.name)) {
        break label25;
      }
      i = 0;
      break label27;
      if (this.role.equals(paramcloud.role)) {
        break label38;
      }
      i = 0;
      break label40;
      if (this.roleInstance.equals(paramcloud.roleInstance)) {
        break label51;
      }
      i = 0;
      break label53;
      if (this.location.equals(paramcloud.location)) {
        break label64;
      }
      i = 0;
      break label66;
      if (this.roleVer.equals(paramcloud.roleVer)) {
        break label77;
      }
      i = 0;
      break label79;
      if (this.environment.equals(paramcloud.environment)) {
        break label90;
      }
      i = 0;
      break label92;
    }
    label238:
    return false;
  }
  
  protected boolean memberwiseCompareQuick(cloud paramcloud)
  {
    int i;
    label27:
    int j;
    if ((1 != 0) && (super.memberwiseCompareQuick(paramcloud)))
    {
      i = 1;
      if (i == 0) {
        break label323;
      }
      if (this.name != null) {
        break label313;
      }
      i = 1;
      if (paramcloud.name != null) {
        break label318;
      }
      j = 1;
      label36:
      if (i != j) {
        break label323;
      }
      i = 1;
      label43:
      if (i == 0) {
        break label345;
      }
      if (this.name != null) {
        break label328;
      }
      label54:
      i = 1;
      label56:
      if (i == 0) {
        break label360;
      }
      if (this.role != null) {
        break label350;
      }
      i = 1;
      label69:
      if (paramcloud.role != null) {
        break label355;
      }
      j = 1;
      label78:
      if (i != j) {
        break label360;
      }
      i = 1;
      label85:
      if (i == 0) {
        break label382;
      }
      if (this.role != null) {
        break label365;
      }
      label96:
      i = 1;
      label98:
      if (i == 0) {
        break label397;
      }
      if (this.roleInstance != null) {
        break label387;
      }
      i = 1;
      label111:
      if (paramcloud.roleInstance != null) {
        break label392;
      }
      j = 1;
      label120:
      if (i != j) {
        break label397;
      }
      i = 1;
      label127:
      if (i == 0) {
        break label419;
      }
      if (this.roleInstance != null) {
        break label402;
      }
      label138:
      i = 1;
      label140:
      if (i == 0) {
        break label434;
      }
      if (this.location != null) {
        break label424;
      }
      i = 1;
      label153:
      if (paramcloud.location != null) {
        break label429;
      }
      j = 1;
      label162:
      if (i != j) {
        break label434;
      }
      i = 1;
      label169:
      if (i == 0) {
        break label456;
      }
      if (this.location != null) {
        break label439;
      }
      label180:
      i = 1;
      label182:
      if (i == 0) {
        break label471;
      }
      if (this.roleVer != null) {
        break label461;
      }
      i = 1;
      label195:
      if (paramcloud.roleVer != null) {
        break label466;
      }
      j = 1;
      label204:
      if (i != j) {
        break label471;
      }
      i = 1;
      label211:
      if (i == 0) {
        break label493;
      }
      if (this.roleVer != null) {
        break label476;
      }
      label222:
      i = 1;
      label224:
      if (i == 0) {
        break label508;
      }
      if (this.environment != null) {
        break label498;
      }
      i = 1;
      label237:
      if (paramcloud.environment != null) {
        break label503;
      }
      j = 1;
      label246:
      if (i != j) {
        break label508;
      }
      i = 1;
      label253:
      if (i == 0) {
        break label530;
      }
      if (this.environment != null) {
        break label513;
      }
      label264:
      i = 1;
      label266:
      if (i == 0) {
        break label545;
      }
      if (this.deploymentUnit != null) {
        break label535;
      }
      i = 1;
      label279:
      if (paramcloud.deploymentUnit != null) {
        break label540;
      }
      j = 1;
      label288:
      if (i != j) {
        break label545;
      }
      i = 1;
      label295:
      if (i == 0) {
        break label567;
      }
      if (this.deploymentUnit != null) {
        break label550;
      }
    }
    label313:
    label318:
    label323:
    label328:
    label345:
    label350:
    label355:
    label360:
    label365:
    label382:
    label387:
    label392:
    label397:
    label402:
    label419:
    label424:
    label429:
    label434:
    label439:
    label456:
    label461:
    label466:
    label471:
    label476:
    label493:
    label498:
    label503:
    label508:
    label513:
    label530:
    label535:
    label540:
    label545:
    label550:
    while (this.deploymentUnit.length() == paramcloud.deploymentUnit.length())
    {
      return true;
      i = 0;
      break;
      i = 0;
      break label27;
      j = 0;
      break label36;
      i = 0;
      break label43;
      if (this.name.length() == paramcloud.name.length()) {
        break label54;
      }
      i = 0;
      break label56;
      i = 0;
      break label69;
      j = 0;
      break label78;
      i = 0;
      break label85;
      if (this.role.length() == paramcloud.role.length()) {
        break label96;
      }
      i = 0;
      break label98;
      i = 0;
      break label111;
      j = 0;
      break label120;
      i = 0;
      break label127;
      if (this.roleInstance.length() == paramcloud.roleInstance.length()) {
        break label138;
      }
      i = 0;
      break label140;
      i = 0;
      break label153;
      j = 0;
      break label162;
      i = 0;
      break label169;
      if (this.location.length() == paramcloud.location.length()) {
        break label180;
      }
      i = 0;
      break label182;
      i = 0;
      break label195;
      j = 0;
      break label204;
      i = 0;
      break label211;
      if (this.roleVer.length() == paramcloud.roleVer.length()) {
        break label222;
      }
      i = 0;
      break label224;
      i = 0;
      break label237;
      j = 0;
      break label246;
      i = 0;
      break label253;
      if (this.environment.length() == paramcloud.environment.length()) {
        break label264;
      }
      i = 0;
      break label266;
      i = 0;
      break label279;
      j = 0;
      break label288;
      i = 0;
      break label295;
    }
    label567:
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
    boolean bool = true;
    paramProtocolReader.readStructBegin(paramBoolean);
    ProtocolReader.FieldTag localFieldTag;
    if (!super.readTagged(paramProtocolReader, true))
    {
      return false;
      this.name = ReadHelper.readString(paramProtocolReader, localFieldTag.type);
    }
    for (;;)
    {
      paramProtocolReader.readFieldEnd();
      localFieldTag = paramProtocolReader.readFieldBegin();
      if ((localFieldTag.type == BondDataType.BT_STOP) || (localFieldTag.type == BondDataType.BT_STOP_BASE))
      {
        if (localFieldTag.type == BondDataType.BT_STOP_BASE) {}
        for (paramBoolean = bool;; paramBoolean = false)
        {
          paramProtocolReader.readStructEnd();
          return paramBoolean;
        }
      }
      switch (localFieldTag.id)
      {
      case 10: 
      default: 
        paramProtocolReader.skip(localFieldTag.type);
        break;
      case 20: 
        this.role = ReadHelper.readString(paramProtocolReader, localFieldTag.type);
        break;
      case 30: 
        this.roleInstance = ReadHelper.readString(paramProtocolReader, localFieldTag.type);
        break;
      case 40: 
        this.location = ReadHelper.readString(paramProtocolReader, localFieldTag.type);
        break;
      case 50: 
        this.roleVer = ReadHelper.readString(paramProtocolReader, localFieldTag.type);
        break;
      case 60: 
        this.environment = ReadHelper.readString(paramProtocolReader, localFieldTag.type);
        break;
      case 70: 
        this.deploymentUnit = ReadHelper.readString(paramProtocolReader, localFieldTag.type);
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
      this.name = paramProtocolReader.readString();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.role = paramProtocolReader.readString();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.roleInstance = paramProtocolReader.readString();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.location = paramProtocolReader.readString();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.roleVer = paramProtocolReader.readString();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.environment = paramProtocolReader.readString();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.deploymentUnit = paramProtocolReader.readString();
    }
    paramProtocolReader.readStructEnd();
  }
  
  public void reset()
  {
    reset("cloud", "Microsoft.Telemetry.Extensions.cloud");
  }
  
  protected void reset(String paramString1, String paramString2)
  {
    super.reset(paramString1, paramString2);
    this.name = "";
    this.role = "";
    this.roleInstance = "";
    this.location = "";
    this.roleVer = "";
    this.environment = "";
    this.deploymentUnit = "";
  }
  
  public final void setDeploymentUnit(String paramString)
  {
    this.deploymentUnit = paramString;
  }
  
  public final void setEnvironment(String paramString)
  {
    this.environment = paramString;
  }
  
  public void setField(FieldDef paramFieldDef, Object paramObject)
  {
    switch (paramFieldDef.getId())
    {
    default: 
      return;
    case 10: 
      this.name = ((String)paramObject);
      return;
    case 20: 
      this.role = ((String)paramObject);
      return;
    case 30: 
      this.roleInstance = ((String)paramObject);
      return;
    case 40: 
      this.location = ((String)paramObject);
      return;
    case 50: 
      this.roleVer = ((String)paramObject);
      return;
    case 60: 
      this.environment = ((String)paramObject);
      return;
    }
    this.deploymentUnit = ((String)paramObject);
  }
  
  public final void setLocation(String paramString)
  {
    this.location = paramString;
  }
  
  public final void setName(String paramString)
  {
    this.name = paramString;
  }
  
  public final void setRole(String paramString)
  {
    this.role = paramString;
  }
  
  public final void setRoleInstance(String paramString)
  {
    this.roleInstance = paramString;
  }
  
  public final void setRoleVer(String paramString)
  {
    this.roleVer = paramString;
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
    paramProtocolWriter.writeFieldBegin(BondDataType.BT_STRING, 10, Schema.name_metadata);
    paramProtocolWriter.writeString(this.name);
    paramProtocolWriter.writeFieldEnd();
    paramProtocolWriter.writeFieldBegin(BondDataType.BT_STRING, 20, Schema.role_metadata);
    paramProtocolWriter.writeString(this.role);
    paramProtocolWriter.writeFieldEnd();
    paramProtocolWriter.writeFieldBegin(BondDataType.BT_STRING, 30, Schema.roleInstance_metadata);
    paramProtocolWriter.writeString(this.roleInstance);
    paramProtocolWriter.writeFieldEnd();
    paramProtocolWriter.writeFieldBegin(BondDataType.BT_STRING, 40, Schema.location_metadata);
    paramProtocolWriter.writeString(this.location);
    paramProtocolWriter.writeFieldEnd();
    if ((!bool) || (this.roleVer != Schema.roleVer_metadata.getDefault_value().getString_value()))
    {
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_STRING, 50, Schema.roleVer_metadata);
      paramProtocolWriter.writeString(this.roleVer);
      paramProtocolWriter.writeFieldEnd();
      if ((bool) && (this.environment == Schema.environment_metadata.getDefault_value().getString_value())) {
        break label271;
      }
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_STRING, 60, Schema.environment_metadata);
      paramProtocolWriter.writeString(this.environment);
      paramProtocolWriter.writeFieldEnd();
      label206:
      if ((bool) && (this.deploymentUnit == Schema.deploymentUnit_metadata.getDefault_value().getString_value())) {
        break label286;
      }
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_STRING, 70, Schema.deploymentUnit_metadata);
      paramProtocolWriter.writeString(this.deploymentUnit);
      paramProtocolWriter.writeFieldEnd();
    }
    for (;;)
    {
      paramProtocolWriter.writeStructEnd(paramBoolean);
      return;
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_STRING, 50, Schema.roleVer_metadata);
      break;
      label271:
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_STRING, 60, Schema.environment_metadata);
      break label206;
      label286:
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_STRING, 70, Schema.deploymentUnit_metadata);
    }
  }
  
  public static class Schema
  {
    private static final Metadata deploymentUnit_metadata;
    private static final Metadata environment_metadata;
    private static final Metadata location_metadata;
    public static final Metadata metadata = new Metadata();
    private static final Metadata name_metadata;
    private static final Metadata roleInstance_metadata;
    private static final Metadata roleVer_metadata;
    private static final Metadata role_metadata;
    public static final SchemaDef schemaDef;
    
    static
    {
      metadata.setName("cloud");
      metadata.setQualified_name("Microsoft.Telemetry.Extensions.cloud");
      metadata.getAttributes().put("Description", "Describes the service related fields populated by the cloud service.");
      name_metadata = new Metadata();
      name_metadata.setName("name");
      name_metadata.setModifier(Modifier.Required);
      name_metadata.getAttributes().put("Description", "Name of the service.");
      role_metadata = new Metadata();
      role_metadata.setName("role");
      role_metadata.setModifier(Modifier.Required);
      role_metadata.getAttributes().put("Description", "Service role.");
      roleInstance_metadata = new Metadata();
      roleInstance_metadata.setName("roleInstance");
      roleInstance_metadata.setModifier(Modifier.Required);
      roleInstance_metadata.getAttributes().put("Description", "Instance id of the deployed role instance generating the event.");
      location_metadata = new Metadata();
      location_metadata.setName("location");
      location_metadata.setModifier(Modifier.Required);
      location_metadata.getAttributes().put("Description", "Deployed location of the role instance (canonical name of datacenter, e.g. 'East US')");
      roleVer_metadata = new Metadata();
      roleVer_metadata.setName("roleVer");
      roleVer_metadata.getAttributes().put("Description", "Build version of the role. Recommended formats are either semantic version, or NT style: <MajorVersion>.<MinorVersion>.<Optional MileStone?>, <BuildNumber>.<Architecture>.<Branch>.<yyMMdd-hhmm>, e.g. 130.0.4590.3525.amd64fre.rd_fabric_n.140618-1229.");
      environment_metadata = new Metadata();
      environment_metadata.setName("environment");
      environment_metadata.getAttributes().put("Description", "Service deployment environment or topology (e.g. Prod, PPE, ChinaProd).");
      deploymentUnit_metadata = new Metadata();
      deploymentUnit_metadata.setName("deploymentUnit");
      deploymentUnit_metadata.getAttributes().put("Description", "Service deployment or scale unit (for partitioned services).");
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
      paramSchemaDef.setMetadata(name_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_STRING);
      localStructDef.getFields().add(paramSchemaDef);
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)20);
      paramSchemaDef.setMetadata(role_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_STRING);
      localStructDef.getFields().add(paramSchemaDef);
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)30);
      paramSchemaDef.setMetadata(roleInstance_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_STRING);
      localStructDef.getFields().add(paramSchemaDef);
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)40);
      paramSchemaDef.setMetadata(location_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_STRING);
      localStructDef.getFields().add(paramSchemaDef);
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)50);
      paramSchemaDef.setMetadata(roleVer_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_STRING);
      localStructDef.getFields().add(paramSchemaDef);
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)60);
      paramSchemaDef.setMetadata(environment_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_STRING);
      localStructDef.getFields().add(paramSchemaDef);
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)70);
      paramSchemaDef.setMetadata(deploymentUnit_metadata);
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


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\MCPE.jar!\Microsoft\Telemetry\Extensions\cloud.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */