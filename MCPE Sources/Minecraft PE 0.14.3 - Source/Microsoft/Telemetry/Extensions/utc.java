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
    if ((1 != 0) && (super.memberwiseCompareDeep(paramutc)))
    {
      i = 1;
      if (i == 0) {
        break label98;
      }
      if (this.stId != null) {
        break label84;
      }
      label25:
      i = 1;
      label27:
      if (i == 0) {
        break label117;
      }
      if (this.aId != null) {
        break label103;
      }
      label38:
      i = 1;
      label40:
      if (i == 0) {
        break label136;
      }
      if (this.raId != null) {
        break label122;
      }
      label51:
      i = 1;
      label53:
      if (i == 0) {
        break label155;
      }
      if (this.op != null) {
        break label141;
      }
      label64:
      i = 1;
      label66:
      if (i == 0) {
        break label174;
      }
      if (this.sqmId != null) {
        break label160;
      }
    }
    label84:
    label98:
    label103:
    label117:
    label122:
    label136:
    label141:
    label155:
    label160:
    while (this.sqmId.equals(paramutc.sqmId))
    {
      return true;
      i = 0;
      break;
      if (this.stId.equals(paramutc.stId)) {
        break label25;
      }
      i = 0;
      break label27;
      if (this.aId.equals(paramutc.aId)) {
        break label38;
      }
      i = 0;
      break label40;
      if (this.raId.equals(paramutc.raId)) {
        break label51;
      }
      i = 0;
      break label53;
      if (this.op.equals(paramutc.op)) {
        break label64;
      }
      i = 0;
      break label66;
    }
    label174:
    return false;
  }
  
  protected boolean memberwiseCompareQuick(utc paramutc)
  {
    int i;
    label27:
    int j;
    if ((1 != 0) && (super.memberwiseCompareQuick(paramutc)))
    {
      i = 1;
      if (i == 0) {
        break label275;
      }
      if (this.stId != null) {
        break label265;
      }
      i = 1;
      if (paramutc.stId != null) {
        break label270;
      }
      j = 1;
      label36:
      if (i != j) {
        break label275;
      }
      i = 1;
      label43:
      if (i == 0) {
        break label297;
      }
      if (this.stId != null) {
        break label280;
      }
      label54:
      i = 1;
      label56:
      if (i == 0) {
        break label312;
      }
      if (this.aId != null) {
        break label302;
      }
      i = 1;
      label69:
      if (paramutc.aId != null) {
        break label307;
      }
      j = 1;
      label78:
      if (i != j) {
        break label312;
      }
      i = 1;
      label85:
      if (i == 0) {
        break label334;
      }
      if (this.aId != null) {
        break label317;
      }
      label96:
      i = 1;
      label98:
      if (i == 0) {
        break label349;
      }
      if (this.raId != null) {
        break label339;
      }
      i = 1;
      label111:
      if (paramutc.raId != null) {
        break label344;
      }
      j = 1;
      label120:
      if (i != j) {
        break label349;
      }
      i = 1;
      label127:
      if (i == 0) {
        break label371;
      }
      if (this.raId != null) {
        break label354;
      }
      label138:
      i = 1;
      label140:
      if (i == 0) {
        break label386;
      }
      if (this.op != null) {
        break label376;
      }
      i = 1;
      label153:
      if (paramutc.op != null) {
        break label381;
      }
      j = 1;
      label162:
      if (i != j) {
        break label386;
      }
      i = 1;
      label169:
      if (i == 0) {
        break label408;
      }
      if (this.op != null) {
        break label391;
      }
      label180:
      i = 1;
      label182:
      if ((i == 0) || (this.cat != paramutc.cat)) {
        break label413;
      }
      i = 1;
      label200:
      if ((i == 0) || (this.flags != paramutc.flags)) {
        break label418;
      }
      i = 1;
      label218:
      if (i == 0) {
        break label433;
      }
      if (this.sqmId != null) {
        break label423;
      }
      i = 1;
      label231:
      if (paramutc.sqmId != null) {
        break label428;
      }
      j = 1;
      label240:
      if (i != j) {
        break label433;
      }
      i = 1;
      label247:
      if (i == 0) {
        break label455;
      }
      if (this.sqmId != null) {
        break label438;
      }
    }
    label265:
    label270:
    label275:
    label280:
    label297:
    label302:
    label307:
    label312:
    label317:
    label334:
    label339:
    label344:
    label349:
    label354:
    label371:
    label376:
    label381:
    label386:
    label391:
    label408:
    label413:
    label418:
    label423:
    label428:
    label433:
    label438:
    while (this.sqmId.length() == paramutc.sqmId.length())
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
      if (this.stId.length() == paramutc.stId.length()) {
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
      if (this.aId.length() == paramutc.aId.length()) {
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
      if (this.raId.length() == paramutc.raId.length()) {
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
      if (this.op.length() == paramutc.op.length()) {
        break label180;
      }
      i = 0;
      break label182;
      i = 0;
      break label200;
      i = 0;
      break label218;
      i = 0;
      break label231;
      j = 0;
      break label240;
      i = 0;
      break label247;
    }
    label455:
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
      this.stId = ReadHelper.readString(paramProtocolReader, localFieldTag.type);
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


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\MCPE.jar!\Microsoft\Telemetry\Extensions\utc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */