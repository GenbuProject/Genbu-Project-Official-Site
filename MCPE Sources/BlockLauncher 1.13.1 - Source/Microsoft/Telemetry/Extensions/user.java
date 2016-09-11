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
    if (super.memberwiseCompareDeep(paramuser))
    {
      i = 1;
      if (i == 0) {
        break label68;
      }
      if (this.id != null) {
        break label54;
      }
      label21:
      i = 1;
      label23:
      if (i == 0) {
        break label87;
      }
      if (this.localId != null) {
        break label73;
      }
      label34:
      i = 1;
      label36:
      if (i == 0) {
        break label106;
      }
      if (this.authId != null) {
        break label92;
      }
    }
    label54:
    label68:
    label73:
    label87:
    label92:
    while (this.authId.equals(paramuser.authId))
    {
      return true;
      i = 0;
      break;
      if (this.id.equals(paramuser.id)) {
        break label21;
      }
      i = 0;
      break label23;
      if (this.localId.equals(paramuser.localId)) {
        break label34;
      }
      i = 0;
      break label36;
    }
    label106:
    return false;
  }
  
  protected boolean memberwiseCompareQuick(user paramuser)
  {
    int i;
    label23:
    int j;
    if (super.memberwiseCompareQuick(paramuser))
    {
      i = 1;
      if (i == 0) {
        break label151;
      }
      if (this.id != null) {
        break label141;
      }
      i = 1;
      if (paramuser.id != null) {
        break label146;
      }
      j = 1;
      label32:
      if (i != j) {
        break label151;
      }
      i = 1;
      label39:
      if (i == 0) {
        break label173;
      }
      if (this.id != null) {
        break label156;
      }
      label50:
      i = 1;
      label52:
      if (i == 0) {
        break label188;
      }
      if (this.localId != null) {
        break label178;
      }
      i = 1;
      label65:
      if (paramuser.localId != null) {
        break label183;
      }
      j = 1;
      label74:
      if (i != j) {
        break label188;
      }
      i = 1;
      label81:
      if (i == 0) {
        break label210;
      }
      if (this.localId != null) {
        break label193;
      }
      label92:
      i = 1;
      label94:
      if (i == 0) {
        break label225;
      }
      if (this.authId != null) {
        break label215;
      }
      i = 1;
      label107:
      if (paramuser.authId != null) {
        break label220;
      }
      j = 1;
      label116:
      if (i != j) {
        break label225;
      }
      i = 1;
      label123:
      if (i == 0) {
        break label247;
      }
      if (this.authId != null) {
        break label230;
      }
    }
    label141:
    label146:
    label151:
    label156:
    label173:
    label178:
    label183:
    label188:
    label193:
    label210:
    label215:
    label220:
    label225:
    label230:
    while (this.authId.length() == paramuser.authId.length())
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
      if (this.id.length() == paramuser.id.length()) {
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
      if (this.localId.length() == paramuser.localId.length()) {
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
    }
    label247:
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


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\Microsoft\Telemetry\Extensions\user.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */