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

public class utc
  extends Extension
{
  private String aId;
  private long cat;
  private long flags;
  private String op;
  private String raId;
  private String sqmId;
  private String stId;
  
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
  
  public final String getAId()
  {
    return this.aId;
  }
  
  public final long getCat()
  {
    return this.cat;
  }
  
  public Object getField(FieldDef paramFieldDef)
  {
    switch (paramFieldDef.getId())
    {
    default: 
      return null;
    case 10: 
      return this.stId;
    case 20: 
      return this.aId;
    case 30: 
      return this.raId;
    case 40: 
      return this.op;
    case 50: 
      return Long.valueOf(this.cat);
    case 60: 
      return Long.valueOf(this.flags);
    }
    return this.sqmId;
  }
  
  public final long getFlags()
  {
    return this.flags;
  }
  
  public final String getOp()
  {
    return this.op;
  }
  
  public final String getRaId()
  {
    return this.raId;
  }
  
  public SchemaDef getSchema()
  {
    return getRuntimeSchema();
  }
  
  public final String getSqmId()
  {
    return this.sqmId;
  }
  
  public final String getStId()
  {
    return this.stId;
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
      paramObject = (utc)paramObject;
    } while ((!memberwiseCompareQuick((utc)paramObject)) || (!memberwiseCompareDeep((utc)paramObject)));
    return true;
  }
  
  protected boolean memberwiseCompareDeep(utc paramutc)
  {
    int i;
    if (super.memberwiseCompareDeep(paramutc))
    {
      i = 1;
      if (i == 0) {
        break label94;
      }
      if (this.stId != null) {
        break label80;
      }
      label21:
      i = 1;
      label23:
      if (i == 0) {
        break label113;
      }
      if (this.aId != null) {
        break label99;
      }
      label34:
      i = 1;
      label36:
      if (i == 0) {
        break label132;
      }
      if (this.raId != null) {
        break label118;
      }
      label47:
      i = 1;
      label49:
      if (i == 0) {
        break label151;
      }
      if (this.op != null) {
        break label137;
      }
      label60:
      i = 1;
      label62:
      if (i == 0) {
        break label170;
      }
      if (this.sqmId != null) {
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
    while (this.sqmId.equals(paramutc.sqmId))
    {
      return true;
      i = 0;
      break;
      if (this.stId.equals(paramutc.stId)) {
        break label21;
      }
      i = 0;
      break label23;
      if (this.aId.equals(paramutc.aId)) {
        break label34;
      }
      i = 0;
      break label36;
      if (this.raId.equals(paramutc.raId)) {
        break label47;
      }
      i = 0;
      break label49;
      if (this.op.equals(paramutc.op)) {
        break label60;
      }
      i = 0;
      break label62;
    }
    label170:
    return false;
  }
  
  protected boolean memberwiseCompareQuick(utc paramutc)
  {
    int i;
    label23:
    int j;
    if (super.memberwiseCompareQuick(paramutc))
    {
      i = 1;
      if (i == 0) {
        break label271;
      }
      if (this.stId != null) {
        break label261;
      }
      i = 1;
      if (paramutc.stId != null) {
        break label266;
      }
      j = 1;
      label32:
      if (i != j) {
        break label271;
      }
      i = 1;
      label39:
      if (i == 0) {
        break label293;
      }
      if (this.stId != null) {
        break label276;
      }
      label50:
      i = 1;
      label52:
      if (i == 0) {
        break label308;
      }
      if (this.aId != null) {
        break label298;
      }
      i = 1;
      label65:
      if (paramutc.aId != null) {
        break label303;
      }
      j = 1;
      label74:
      if (i != j) {
        break label308;
      }
      i = 1;
      label81:
      if (i == 0) {
        break label330;
      }
      if (this.aId != null) {
        break label313;
      }
      label92:
      i = 1;
      label94:
      if (i == 0) {
        break label345;
      }
      if (this.raId != null) {
        break label335;
      }
      i = 1;
      label107:
      if (paramutc.raId != null) {
        break label340;
      }
      j = 1;
      label116:
      if (i != j) {
        break label345;
      }
      i = 1;
      label123:
      if (i == 0) {
        break label367;
      }
      if (this.raId != null) {
        break label350;
      }
      label134:
      i = 1;
      label136:
      if (i == 0) {
        break label382;
      }
      if (this.op != null) {
        break label372;
      }
      i = 1;
      label149:
      if (paramutc.op != null) {
        break label377;
      }
      j = 1;
      label158:
      if (i != j) {
        break label382;
      }
      i = 1;
      label165:
      if (i == 0) {
        break label404;
      }
      if (this.op != null) {
        break label387;
      }
      label176:
      i = 1;
      label178:
      if ((i == 0) || (this.cat != paramutc.cat)) {
        break label409;
      }
      i = 1;
      label196:
      if ((i == 0) || (this.flags != paramutc.flags)) {
        break label414;
      }
      i = 1;
      label214:
      if (i == 0) {
        break label429;
      }
      if (this.sqmId != null) {
        break label419;
      }
      i = 1;
      label227:
      if (paramutc.sqmId != null) {
        break label424;
      }
      j = 1;
      label236:
      if (i != j) {
        break label429;
      }
      i = 1;
      label243:
      if (i == 0) {
        break label451;
      }
      if (this.sqmId != null) {
        break label434;
      }
    }
    label261:
    label266:
    label271:
    label276:
    label293:
    label298:
    label303:
    label308:
    label313:
    label330:
    label335:
    label340:
    label345:
    label350:
    label367:
    label372:
    label377:
    label382:
    label387:
    label404:
    label409:
    label414:
    label419:
    label424:
    label429:
    label434:
    while (this.sqmId.length() == paramutc.sqmId.length())
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
      if (this.stId.length() == paramutc.stId.length()) {
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
      if (this.aId.length() == paramutc.aId.length()) {
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
      if (this.raId.length() == paramutc.raId.length()) {
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
      if (this.op.length() == paramutc.op.length()) {
        break label176;
      }
      i = 0;
      break label178;
      i = 0;
      break label196;
      i = 0;
      break label214;
      i = 0;
      break label227;
      j = 0;
      break label236;
      i = 0;
      break label243;
    }
    label451:
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
      this.stId = ReadHelper.readString(paramProtocolReader, localFieldTag.type);
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
        this.aId = ReadHelper.readString(paramProtocolReader, localFieldTag.type);
        break;
      case 30: 
        this.raId = ReadHelper.readString(paramProtocolReader, localFieldTag.type);
        break;
      case 40: 
        this.op = ReadHelper.readString(paramProtocolReader, localFieldTag.type);
        break;
      case 50: 
        this.cat = ReadHelper.readInt64(paramProtocolReader, localFieldTag.type);
        break;
      case 60: 
        this.flags = ReadHelper.readInt64(paramProtocolReader, localFieldTag.type);
        break;
      case 70: 
        this.sqmId = ReadHelper.readString(paramProtocolReader, localFieldTag.type);
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
      this.stId = paramProtocolReader.readString();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.aId = paramProtocolReader.readString();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.raId = paramProtocolReader.readString();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.op = paramProtocolReader.readString();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.cat = paramProtocolReader.readInt64();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.flags = paramProtocolReader.readInt64();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.sqmId = paramProtocolReader.readString();
    }
    paramProtocolReader.readStructEnd();
  }
  
  public void reset()
  {
    reset("utc", "Microsoft.Telemetry.Extensions.utc");
  }
  
  protected void reset(String paramString1, String paramString2)
  {
    super.reset(paramString1, paramString2);
    this.stId = "";
    this.aId = "";
    this.raId = "";
    this.op = "";
    this.cat = 0L;
    this.flags = 0L;
    this.sqmId = "";
  }
  
  public final void setAId(String paramString)
  {
    this.aId = paramString;
  }
  
  public final void setCat(long paramLong)
  {
    this.cat = paramLong;
  }
  
  public void setField(FieldDef paramFieldDef, Object paramObject)
  {
    switch (paramFieldDef.getId())
    {
    default: 
      return;
    case 10: 
      this.stId = ((String)paramObject);
      return;
    case 20: 
      this.aId = ((String)paramObject);
      return;
    case 30: 
      this.raId = ((String)paramObject);
      return;
    case 40: 
      this.op = ((String)paramObject);
      return;
    case 50: 
      this.cat = ((Long)paramObject).longValue();
      return;
    case 60: 
      this.flags = ((Long)paramObject).longValue();
      return;
    }
    this.sqmId = ((String)paramObject);
  }
  
  public final void setFlags(long paramLong)
  {
    this.flags = paramLong;
  }
  
  public final void setOp(String paramString)
  {
    this.op = paramString;
  }
  
  public final void setRaId(String paramString)
  {
    this.raId = paramString;
  }
  
  public final void setSqmId(String paramString)
  {
    this.sqmId = paramString;
  }
  
  public final void setStId(String paramString)
  {
    this.stId = paramString;
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
    if ((!bool) || (this.stId != Schema.stId_metadata.getDefault_value().getString_value()))
    {
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_STRING, 10, Schema.stId_metadata);
      paramProtocolWriter.writeString(this.stId);
      paramProtocolWriter.writeFieldEnd();
      if ((bool) && (this.aId == Schema.aId_metadata.getDefault_value().getString_value())) {
        break label353;
      }
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_STRING, 20, Schema.aId_metadata);
      paramProtocolWriter.writeString(this.aId);
      paramProtocolWriter.writeFieldEnd();
      label110:
      if ((bool) && (this.raId == Schema.raId_metadata.getDefault_value().getString_value())) {
        break label368;
      }
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_STRING, 30, Schema.raId_metadata);
      paramProtocolWriter.writeString(this.raId);
      paramProtocolWriter.writeFieldEnd();
      label154:
      if ((bool) && (this.op == Schema.op_metadata.getDefault_value().getString_value())) {
        break label383;
      }
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_STRING, 40, Schema.op_metadata);
      paramProtocolWriter.writeString(this.op);
      paramProtocolWriter.writeFieldEnd();
      label198:
      if ((bool) && (this.cat == Schema.cat_metadata.getDefault_value().getInt_value())) {
        break label398;
      }
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_INT64, 50, Schema.cat_metadata);
      paramProtocolWriter.writeInt64(this.cat);
      paramProtocolWriter.writeFieldEnd();
      label243:
      if ((bool) && (this.flags == Schema.flags_metadata.getDefault_value().getInt_value())) {
        break label413;
      }
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_INT64, 60, Schema.flags_metadata);
      paramProtocolWriter.writeInt64(this.flags);
      paramProtocolWriter.writeFieldEnd();
      label288:
      if ((bool) && (this.sqmId == Schema.sqmId_metadata.getDefault_value().getString_value())) {
        break label428;
      }
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_STRING, 70, Schema.sqmId_metadata);
      paramProtocolWriter.writeString(this.sqmId);
      paramProtocolWriter.writeFieldEnd();
    }
    for (;;)
    {
      paramProtocolWriter.writeStructEnd(paramBoolean);
      return;
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_STRING, 10, Schema.stId_metadata);
      break;
      label353:
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_STRING, 20, Schema.aId_metadata);
      break label110;
      label368:
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_STRING, 30, Schema.raId_metadata);
      break label154;
      label383:
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_STRING, 40, Schema.op_metadata);
      break label198;
      label398:
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_INT64, 50, Schema.cat_metadata);
      break label243;
      label413:
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_INT64, 60, Schema.flags_metadata);
      break label288;
      label428:
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_STRING, 70, Schema.sqmId_metadata);
    }
  }
  
  public static class Schema
  {
    private static final Metadata aId_metadata;
    private static final Metadata cat_metadata;
    private static final Metadata flags_metadata;
    public static final Metadata metadata = new Metadata();
    private static final Metadata op_metadata;
    private static final Metadata raId_metadata;
    public static final SchemaDef schemaDef;
    private static final Metadata sqmId_metadata;
    private static final Metadata stId_metadata;
    
    static
    {
      metadata.setName("utc");
      metadata.setQualified_name("Microsoft.Telemetry.Extensions.utc");
      metadata.getAttributes().put("Description", "Describes the properties that might be populated by a logging library on Windows.");
      stId_metadata = new Metadata();
      stId_metadata.setName("stId");
      stId_metadata.getAttributes().put("Description", "Used for UTC scenarios.");
      aId_metadata = new Metadata();
      aId_metadata.setName("aId");
      aId_metadata.getAttributes().put("Description", "Activity Id in ETW (event tracing for windows).");
      raId_metadata = new Metadata();
      raId_metadata.setName("raId");
      raId_metadata.getAttributes().put("Description", "Related Activity Id in ETW.");
      op_metadata = new Metadata();
      op_metadata.setName("op");
      op_metadata.getAttributes().put("Description", "Op Code in ETW.");
      cat_metadata = new Metadata();
      cat_metadata.setName("cat");
      cat_metadata.getAttributes().put("Description", "Categories.");
      cat_metadata.getDefault_value().setInt_value(0L);
      flags_metadata = new Metadata();
      flags_metadata.setName("flags");
      flags_metadata.getAttributes().put("Description", "This captures the characteristics of the traffic. Examples: isTest, isInternal.");
      flags_metadata.getDefault_value().setInt_value(0L);
      sqmId_metadata = new Metadata();
      sqmId_metadata.setName("sqmId");
      sqmId_metadata.getAttributes().put("Description", "The Windows SQM device ID.");
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
      paramSchemaDef.setMetadata(stId_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_STRING);
      localStructDef.getFields().add(paramSchemaDef);
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)20);
      paramSchemaDef.setMetadata(aId_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_STRING);
      localStructDef.getFields().add(paramSchemaDef);
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)30);
      paramSchemaDef.setMetadata(raId_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_STRING);
      localStructDef.getFields().add(paramSchemaDef);
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)40);
      paramSchemaDef.setMetadata(op_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_STRING);
      localStructDef.getFields().add(paramSchemaDef);
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)50);
      paramSchemaDef.setMetadata(cat_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_INT64);
      localStructDef.getFields().add(paramSchemaDef);
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)60);
      paramSchemaDef.setMetadata(flags_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_INT64);
      localStructDef.getFields().add(paramSchemaDef);
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)70);
      paramSchemaDef.setMetadata(sqmId_metadata);
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


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\Microsoft\Telemetry\Extensions\utc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */