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
    if (super.memberwiseCompareDeep(paramcloud))
    {
      i = 1;
      if (i == 0) {
        break label120;
      }
      if (this.name != null) {
        break label106;
      }
      label21:
      i = 1;
      label23:
      if (i == 0) {
        break label139;
      }
      if (this.role != null) {
        break label125;
      }
      label34:
      i = 1;
      label36:
      if (i == 0) {
        break label158;
      }
      if (this.roleInstance != null) {
        break label144;
      }
      label47:
      i = 1;
      label49:
      if (i == 0) {
        break label177;
      }
      if (this.location != null) {
        break label163;
      }
      label60:
      i = 1;
      label62:
      if (i == 0) {
        break label196;
      }
      if (this.roleVer != null) {
        break label182;
      }
      label73:
      i = 1;
      label75:
      if (i == 0) {
        break label215;
      }
      if (this.environment != null) {
        break label201;
      }
      label86:
      i = 1;
      label88:
      if (i == 0) {
        break label234;
      }
      if (this.deploymentUnit != null) {
        break label220;
      }
    }
    label106:
    label120:
    label125:
    label139:
    label144:
    label158:
    label163:
    label177:
    label182:
    label196:
    label201:
    label215:
    label220:
    while (this.deploymentUnit.equals(paramcloud.deploymentUnit))
    {
      return true;
      i = 0;
      break;
      if (this.name.equals(paramcloud.name)) {
        break label21;
      }
      i = 0;
      break label23;
      if (this.role.equals(paramcloud.role)) {
        break label34;
      }
      i = 0;
      break label36;
      if (this.roleInstance.equals(paramcloud.roleInstance)) {
        break label47;
      }
      i = 0;
      break label49;
      if (this.location.equals(paramcloud.location)) {
        break label60;
      }
      i = 0;
      break label62;
      if (this.roleVer.equals(paramcloud.roleVer)) {
        break label73;
      }
      i = 0;
      break label75;
      if (this.environment.equals(paramcloud.environment)) {
        break label86;
      }
      i = 0;
      break label88;
    }
    label234:
    return false;
  }
  
  protected boolean memberwiseCompareQuick(cloud paramcloud)
  {
    int i;
    label23:
    int j;
    if (super.memberwiseCompareQuick(paramcloud))
    {
      i = 1;
      if (i == 0) {
        break label319;
      }
      if (this.name != null) {
        break label309;
      }
      i = 1;
      if (paramcloud.name != null) {
        break label314;
      }
      j = 1;
      label32:
      if (i != j) {
        break label319;
      }
      i = 1;
      label39:
      if (i == 0) {
        break label341;
      }
      if (this.name != null) {
        break label324;
      }
      label50:
      i = 1;
      label52:
      if (i == 0) {
        break label356;
      }
      if (this.role != null) {
        break label346;
      }
      i = 1;
      label65:
      if (paramcloud.role != null) {
        break label351;
      }
      j = 1;
      label74:
      if (i != j) {
        break label356;
      }
      i = 1;
      label81:
      if (i == 0) {
        break label378;
      }
      if (this.role != null) {
        break label361;
      }
      label92:
      i = 1;
      label94:
      if (i == 0) {
        break label393;
      }
      if (this.roleInstance != null) {
        break label383;
      }
      i = 1;
      label107:
      if (paramcloud.roleInstance != null) {
        break label388;
      }
      j = 1;
      label116:
      if (i != j) {
        break label393;
      }
      i = 1;
      label123:
      if (i == 0) {
        break label415;
      }
      if (this.roleInstance != null) {
        break label398;
      }
      label134:
      i = 1;
      label136:
      if (i == 0) {
        break label430;
      }
      if (this.location != null) {
        break label420;
      }
      i = 1;
      label149:
      if (paramcloud.location != null) {
        break label425;
      }
      j = 1;
      label158:
      if (i != j) {
        break label430;
      }
      i = 1;
      label165:
      if (i == 0) {
        break label452;
      }
      if (this.location != null) {
        break label435;
      }
      label176:
      i = 1;
      label178:
      if (i == 0) {
        break label467;
      }
      if (this.roleVer != null) {
        break label457;
      }
      i = 1;
      label191:
      if (paramcloud.roleVer != null) {
        break label462;
      }
      j = 1;
      label200:
      if (i != j) {
        break label467;
      }
      i = 1;
      label207:
      if (i == 0) {
        break label489;
      }
      if (this.roleVer != null) {
        break label472;
      }
      label218:
      i = 1;
      label220:
      if (i == 0) {
        break label504;
      }
      if (this.environment != null) {
        break label494;
      }
      i = 1;
      label233:
      if (paramcloud.environment != null) {
        break label499;
      }
      j = 1;
      label242:
      if (i != j) {
        break label504;
      }
      i = 1;
      label249:
      if (i == 0) {
        break label526;
      }
      if (this.environment != null) {
        break label509;
      }
      label260:
      i = 1;
      label262:
      if (i == 0) {
        break label541;
      }
      if (this.deploymentUnit != null) {
        break label531;
      }
      i = 1;
      label275:
      if (paramcloud.deploymentUnit != null) {
        break label536;
      }
      j = 1;
      label284:
      if (i != j) {
        break label541;
      }
      i = 1;
      label291:
      if (i == 0) {
        break label563;
      }
      if (this.deploymentUnit != null) {
        break label546;
      }
    }
    label309:
    label314:
    label319:
    label324:
    label341:
    label346:
    label351:
    label356:
    label361:
    label378:
    label383:
    label388:
    label393:
    label398:
    label415:
    label420:
    label425:
    label430:
    label435:
    label452:
    label457:
    label462:
    label467:
    label472:
    label489:
    label494:
    label499:
    label504:
    label509:
    label526:
    label531:
    label536:
    label541:
    label546:
    while (this.deploymentUnit.length() == paramcloud.deploymentUnit.length())
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
      if (this.name.length() == paramcloud.name.length()) {
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
      if (this.role.length() == paramcloud.role.length()) {
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
      if (this.roleInstance.length() == paramcloud.roleInstance.length()) {
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
      if (this.location.length() == paramcloud.location.length()) {
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
      if (this.roleVer.length() == paramcloud.roleVer.length()) {
        break label218;
      }
      i = 0;
      break label220;
      i = 0;
      break label233;
      j = 0;
      break label242;
      i = 0;
      break label249;
      if (this.environment.length() == paramcloud.environment.length()) {
        break label260;
      }
      i = 0;
      break label262;
      i = 0;
      break label275;
      j = 0;
      break label284;
      i = 0;
      break label291;
    }
    label563:
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
      this.name = ReadHelper.readString(paramProtocolReader, localFieldTag.type);
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


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\Microsoft\Telemetry\Extensions\cloud.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */