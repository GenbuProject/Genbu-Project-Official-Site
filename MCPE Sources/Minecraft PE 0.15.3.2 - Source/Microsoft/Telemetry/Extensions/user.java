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

public class user
  extends Extension
{
  private String authId;
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
    }
    return this.authId;
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
      paramObject = (user)paramObject;
    } while ((!memberwiseCompareQuick((user)paramObject)) || (!memberwiseCompareDeep((user)paramObject)));
    return true;
  }
  
  protected boolean memberwiseCompareDeep(user paramuser)
  {
    int i;
    if ((1 != 0) && (super.memberwiseCompareDeep(paramuser)))
    {
      i = 1;
      if (i == 0) {
        break label72;
      }
      if (this.id != null) {
        break label58;
      }
      label25:
      i = 1;
      label27:
      if (i == 0) {
        break label91;
      }
      if (this.localId != null) {
        break label77;
      }
      label38:
      i = 1;
      label40:
      if (i == 0) {
        break label110;
      }
      if (this.authId != null) {
        break label96;
      }
    }
    label58:
    label72:
    label77:
    label91:
    label96:
    while (this.authId.equals(paramuser.authId))
    {
      return true;
      i = 0;
      break;
      if (this.id.equals(paramuser.id)) {
        break label25;
      }
      i = 0;
      break label27;
      if (this.localId.equals(paramuser.localId)) {
        break label38;
      }
      i = 0;
      break label40;
    }
    label110:
    return false;
  }
  
  protected boolean memberwiseCompareQuick(user paramuser)
  {
    int i;
    label27:
    int j;
    if ((1 != 0) && (super.memberwiseCompareQuick(paramuser)))
    {
      i = 1;
      if (i == 0) {
        break label155;
      }
      if (this.id != null) {
        break label145;
      }
      i = 1;
      if (paramuser.id != null) {
        break label150;
      }
      j = 1;
      label36:
      if (i != j) {
        break label155;
      }
      i = 1;
      label43:
      if (i == 0) {
        break label177;
      }
      if (this.id != null) {
        break label160;
      }
      label54:
      i = 1;
      label56:
      if (i == 0) {
        break label192;
      }
      if (this.localId != null) {
        break label182;
      }
      i = 1;
      label69:
      if (paramuser.localId != null) {
        break label187;
      }
      j = 1;
      label78:
      if (i != j) {
        break label192;
      }
      i = 1;
      label85:
      if (i == 0) {
        break label214;
      }
      if (this.localId != null) {
        break label197;
      }
      label96:
      i = 1;
      label98:
      if (i == 0) {
        break label229;
      }
      if (this.authId != null) {
        break label219;
      }
      i = 1;
      label111:
      if (paramuser.authId != null) {
        break label224;
      }
      j = 1;
      label120:
      if (i != j) {
        break label229;
      }
      i = 1;
      label127:
      if (i == 0) {
        break label251;
      }
      if (this.authId != null) {
        break label234;
      }
    }
    label145:
    label150:
    label155:
    label160:
    label177:
    label182:
    label187:
    label192:
    label197:
    label214:
    label219:
    label224:
    label229:
    label234:
    while (this.authId.length() == paramuser.authId.length())
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
      if (this.id.length() == paramuser.id.length()) {
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
      if (this.localId.length() == paramuser.localId.length()) {
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
    }
    label251:
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
      this.id = ReadHelper.readString(paramProtocolReader, localFieldTag.type);
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
        this.localId = ReadHelper.readString(paramProtocolReader, localFieldTag.type);
        break;
      case 30: 
        this.authId = ReadHelper.readString(paramProtocolReader, localFieldTag.type);
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
    paramProtocolReader.readStructEnd();
  }
  
  public void reset()
  {
    reset("user", "Microsoft.Telemetry.Extensions.user");
  }
  
  protected void reset(String paramString1, String paramString2)
  {
    super.reset(paramString1, paramString2);
    this.id = "";
    this.localId = "";
    this.authId = "";
  }
  
  public final void setAuthId(String paramString)
  {
    this.authId = paramString;
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
    }
    this.authId = ((String)paramObject);
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
        break label175;
      }
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_STRING, 20, Schema.localId_metadata);
      paramProtocolWriter.writeString(this.localId);
      paramProtocolWriter.writeFieldEnd();
      label110:
      if ((bool) && (this.authId == Schema.authId_metadata.getDefault_value().getString_value())) {
        break label190;
      }
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_STRING, 30, Schema.authId_metadata);
      paramProtocolWriter.writeString(this.authId);
      paramProtocolWriter.writeFieldEnd();
    }
    for (;;)
    {
      paramProtocolWriter.writeStructEnd(paramBoolean);
      return;
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_STRING, 10, Schema.id_metadata);
      break;
      label175:
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_STRING, 20, Schema.localId_metadata);
      break label110;
      label190:
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_STRING, 30, Schema.authId_metadata);
    }
  }
  
  public static class Schema
  {
    private static final Metadata authId_metadata;
    private static final Metadata id_metadata;
    private static final Metadata localId_metadata;
    public static final Metadata metadata = new Metadata();
    public static final SchemaDef schemaDef;
    
    static
    {
      metadata.setName("user");
      metadata.setQualified_name("Microsoft.Telemetry.Extensions.user");
      metadata.getAttributes().put("Description", "Describes the User related fields. See https://osgwiki.com/wiki/CommonSchema/user_id");
      id_metadata = new Metadata();
      id_metadata.setName("id");
      id_metadata.getAttributes().put("Description", "Unique user Id. Clients aren't expected to set this; instead the service will decide the best ID to use here. Clients may set this if they believe they have the best user ID already. Format is <NamespaceIdentifier>:<Id> for example, x:12345678.");
      localId_metadata = new Metadata();
      localId_metadata.setName("localId");
      localId_metadata.getAttributes().put("Description", "Local user identifier according to the client. Format is <NamespaceIdentifier>:<Id> for example, x:12345678.");
      authId_metadata = new Metadata();
      authId_metadata.setName("authId");
      authId_metadata.getAttributes().put("Description", "This is the ID of the user associated with this event, deduced from a token such as an MSA ticket or Xbox xtoken.");
      authId_metadata.getAttributes().put("Name", "UserAuthId");
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


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\Microsoft\Telemetry\Extensions\user.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */