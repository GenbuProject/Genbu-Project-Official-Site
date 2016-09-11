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

public class os
  extends Extension
{
  private String expId;
  private String locale;
  
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
  
  public final String getExpId()
  {
    return this.expId;
  }
  
  public Object getField(FieldDef paramFieldDef)
  {
    switch (paramFieldDef.getId())
    {
    default: 
      return null;
    case 10: 
      return this.locale;
    }
    return this.expId;
  }
  
  public final String getLocale()
  {
    return this.locale;
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
      paramObject = (os)paramObject;
    } while ((!memberwiseCompareQuick((os)paramObject)) || (!memberwiseCompareDeep((os)paramObject)));
    return true;
  }
  
  protected boolean memberwiseCompareDeep(os paramos)
  {
    int i;
    if (super.memberwiseCompareDeep(paramos))
    {
      i = 1;
      if (i == 0) {
        break label55;
      }
      if (this.locale != null) {
        break label41;
      }
      label21:
      i = 1;
      label23:
      if (i == 0) {
        break label74;
      }
      if (this.expId != null) {
        break label60;
      }
    }
    label41:
    label55:
    label60:
    while (this.expId.equals(paramos.expId))
    {
      return true;
      i = 0;
      break;
      if (this.locale.equals(paramos.locale)) {
        break label21;
      }
      i = 0;
      break label23;
    }
    label74:
    return false;
  }
  
  protected boolean memberwiseCompareQuick(os paramos)
  {
    int i;
    label23:
    int j;
    if (super.memberwiseCompareQuick(paramos))
    {
      i = 1;
      if (i == 0) {
        break label109;
      }
      if (this.locale != null) {
        break label99;
      }
      i = 1;
      if (paramos.locale != null) {
        break label104;
      }
      j = 1;
      label32:
      if (i != j) {
        break label109;
      }
      i = 1;
      label39:
      if (i == 0) {
        break label131;
      }
      if (this.locale != null) {
        break label114;
      }
      label50:
      i = 1;
      label52:
      if (i == 0) {
        break label146;
      }
      if (this.expId != null) {
        break label136;
      }
      i = 1;
      label65:
      if (paramos.expId != null) {
        break label141;
      }
      j = 1;
      label74:
      if (i != j) {
        break label146;
      }
      i = 1;
      label81:
      if (i == 0) {
        break label168;
      }
      if (this.expId != null) {
        break label151;
      }
    }
    label99:
    label104:
    label109:
    label114:
    label131:
    label136:
    label141:
    label146:
    label151:
    while (this.expId.length() == paramos.expId.length())
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
      if (this.locale.length() == paramos.locale.length()) {
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
    }
    label168:
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
      this.locale = ReadHelper.readString(paramProtocolReader, localFieldTag.type);
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
        this.expId = ReadHelper.readString(paramProtocolReader, localFieldTag.type);
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
      this.locale = paramProtocolReader.readString();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.expId = paramProtocolReader.readString();
    }
    paramProtocolReader.readStructEnd();
  }
  
  public void reset()
  {
    reset("os", "Microsoft.Telemetry.Extensions.os");
  }
  
  protected void reset(String paramString1, String paramString2)
  {
    super.reset(paramString1, paramString2);
    this.locale = "";
    this.expId = "";
  }
  
  public final void setExpId(String paramString)
  {
    this.expId = paramString;
  }
  
  public void setField(FieldDef paramFieldDef, Object paramObject)
  {
    switch (paramFieldDef.getId())
    {
    default: 
      return;
    case 10: 
      this.locale = ((String)paramObject);
      return;
    }
    this.expId = ((String)paramObject);
  }
  
  public final void setLocale(String paramString)
  {
    this.locale = paramString;
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
    if ((!bool) || (this.locale != Schema.locale_metadata.getDefault_value().getString_value()))
    {
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_STRING, 10, Schema.locale_metadata);
      paramProtocolWriter.writeString(this.locale);
      paramProtocolWriter.writeFieldEnd();
      if ((bool) && (this.expId == Schema.expId_metadata.getDefault_value().getString_value())) {
        break label131;
      }
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_STRING, 20, Schema.expId_metadata);
      paramProtocolWriter.writeString(this.expId);
      paramProtocolWriter.writeFieldEnd();
    }
    for (;;)
    {
      paramProtocolWriter.writeStructEnd(paramBoolean);
      return;
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_STRING, 10, Schema.locale_metadata);
      break;
      label131:
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_STRING, 20, Schema.expId_metadata);
    }
  }
  
  public static class Schema
  {
    private static final Metadata expId_metadata;
    private static final Metadata locale_metadata;
    public static final Metadata metadata = new Metadata();
    public static final SchemaDef schemaDef;
    
    static
    {
      metadata.setName("os");
      metadata.setQualified_name("Microsoft.Telemetry.Extensions.os");
      metadata.getAttributes().put("Description", "Describes the OS properties that would be populated by the client.");
      locale_metadata = new Metadata();
      locale_metadata.setName("locale");
      locale_metadata.getAttributes().put("Description", "OS locale, set by the user, in the Windows locale format. Example, en-US for US English. Refer RFC 4646 for the format.");
      expId_metadata = new Metadata();
      expId_metadata.setName("expId");
      expId_metadata.getAttributes().put("Description", "Comma delimited list of experiment ids for experiments installed on the OS. Format is <NamespaceIdentifier>:<ExperimentId> for example, m:12345.");
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
      paramSchemaDef.setMetadata(locale_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_STRING);
      localStructDef.getFields().add(paramSchemaDef);
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)20);
      paramSchemaDef.setMetadata(expId_metadata);
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


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\Microsoft\Telemetry\Extensions\os.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */