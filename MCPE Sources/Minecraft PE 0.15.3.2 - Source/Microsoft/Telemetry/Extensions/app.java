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

public class app
  extends Extension
{
  private String expId;
  
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
    }
    return this.expId;
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
      paramObject = (app)paramObject;
    } while ((!memberwiseCompareQuick((app)paramObject)) || (!memberwiseCompareDeep((app)paramObject)));
    return true;
  }
  
  protected boolean memberwiseCompareDeep(app paramapp)
  {
    int i;
    if ((1 != 0) && (super.memberwiseCompareDeep(paramapp)))
    {
      i = 1;
      if (i == 0) {
        break label46;
      }
      if (this.expId != null) {
        break label32;
      }
    }
    label32:
    while (this.expId.equals(paramapp.expId))
    {
      return true;
      i = 0;
      break;
    }
    label46:
    return false;
  }
  
  protected boolean memberwiseCompareQuick(app paramapp)
  {
    int i;
    label27:
    int j;
    if ((1 != 0) && (super.memberwiseCompareQuick(paramapp)))
    {
      i = 1;
      if (i == 0) {
        break label71;
      }
      if (this.expId != null) {
        break label61;
      }
      i = 1;
      if (paramapp.expId != null) {
        break label66;
      }
      j = 1;
      label36:
      if (i != j) {
        break label71;
      }
      i = 1;
      label43:
      if (i == 0) {
        break label93;
      }
      if (this.expId != null) {
        break label76;
      }
    }
    label61:
    label66:
    label71:
    label76:
    while (this.expId.length() == paramapp.expId.length())
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
    }
    label93:
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
      this.expId = ReadHelper.readString(paramProtocolReader, localFieldTag.type);
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
      }
      paramProtocolReader.skip(localFieldTag.type);
    }
  }
  
  protected void readUntagged(ProtocolReader paramProtocolReader, boolean paramBoolean)
    throws IOException
  {
    boolean bool = paramProtocolReader.hasCapability(ProtocolCapability.CAN_OMIT_FIELDS);
    paramProtocolReader.readStructBegin(paramBoolean);
    super.readUntagged(paramProtocolReader, true);
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.expId = paramProtocolReader.readString();
    }
    paramProtocolReader.readStructEnd();
  }
  
  public void reset()
  {
    reset("app", "Microsoft.Telemetry.Extensions.app");
  }
  
  protected void reset(String paramString1, String paramString2)
  {
    super.reset(paramString1, paramString2);
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
    }
    this.expId = ((String)paramObject);
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
    if ((!bool) || (this.expId != Schema.expId_metadata.getDefault_value().getString_value()))
    {
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_STRING, 10, Schema.expId_metadata);
      paramProtocolWriter.writeString(this.expId);
      paramProtocolWriter.writeFieldEnd();
    }
    for (;;)
    {
      paramProtocolWriter.writeStructEnd(paramBoolean);
      return;
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_STRING, 10, Schema.expId_metadata);
    }
  }
  
  public static class Schema
  {
    private static final Metadata expId_metadata;
    public static final Metadata metadata = new Metadata();
    public static final SchemaDef schemaDef;
    
    static
    {
      metadata.setName("app");
      metadata.setQualified_name("Microsoft.Telemetry.Extensions.app");
      metadata.getAttributes().put("Description", "Describes the properties of the running application. This extension could be populated by a client app or a web app.");
      expId_metadata = new Metadata();
      expId_metadata.setName("expId");
      expId_metadata.getAttributes().put("Description", "Comma delimited list of experiment ids for experiments installed on the Application. Format is <NamespaceIdentifier>:<ExperimentId> for example, m:12345.");
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


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\Microsoft\Telemetry\Extensions\app.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */