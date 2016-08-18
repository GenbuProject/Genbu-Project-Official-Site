package com.microsoft.xbox.idp.telemetry.utc;

import Microsoft.Telemetry.Data;
import Microsoft.Telemetry.Data.Schema;
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

public class ServiceError
  extends Data<CommonData>
{
  private String errorCode;
  private String errorName;
  private String errorText;
  private String pageName;
  
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
  
  public final String getErrorCode()
  {
    return this.errorCode;
  }
  
  public final String getErrorName()
  {
    return this.errorName;
  }
  
  public final String getErrorText()
  {
    return this.errorText;
  }
  
  public Object getField(FieldDef paramFieldDef)
  {
    switch (paramFieldDef.getId())
    {
    default: 
      return null;
    case 10: 
      return this.errorName;
    case 20: 
      return this.errorText;
    case 30: 
      return this.errorCode;
    }
    return this.pageName;
  }
  
  public final String getPageName()
  {
    return this.pageName;
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
      paramObject = (ServiceError)paramObject;
    } while ((!memberwiseCompareQuick((ServiceError)paramObject)) || (!memberwiseCompareDeep((ServiceError)paramObject)));
    return true;
  }
  
  protected boolean memberwiseCompareDeep(ServiceError paramServiceError)
  {
    int i;
    if ((1 != 0) && (super.memberwiseCompareDeep(paramServiceError)))
    {
      i = 1;
      if (i == 0) {
        break label85;
      }
      if (this.errorName != null) {
        break label71;
      }
      label25:
      i = 1;
      label27:
      if (i == 0) {
        break label104;
      }
      if (this.errorText != null) {
        break label90;
      }
      label38:
      i = 1;
      label40:
      if (i == 0) {
        break label123;
      }
      if (this.errorCode != null) {
        break label109;
      }
      label51:
      i = 1;
      label53:
      if (i == 0) {
        break label142;
      }
      if (this.pageName != null) {
        break label128;
      }
    }
    label71:
    label85:
    label90:
    label104:
    label109:
    label123:
    label128:
    while (this.pageName.equals(paramServiceError.pageName))
    {
      return true;
      i = 0;
      break;
      if (this.errorName.equals(paramServiceError.errorName)) {
        break label25;
      }
      i = 0;
      break label27;
      if (this.errorText.equals(paramServiceError.errorText)) {
        break label38;
      }
      i = 0;
      break label40;
      if (this.errorCode.equals(paramServiceError.errorCode)) {
        break label51;
      }
      i = 0;
      break label53;
    }
    label142:
    return false;
  }
  
  protected boolean memberwiseCompareQuick(ServiceError paramServiceError)
  {
    int i;
    label27:
    int j;
    if ((1 != 0) && (super.memberwiseCompareQuick(paramServiceError)))
    {
      i = 1;
      if (i == 0) {
        break label197;
      }
      if (this.errorName != null) {
        break label187;
      }
      i = 1;
      if (paramServiceError.errorName != null) {
        break label192;
      }
      j = 1;
      label36:
      if (i != j) {
        break label197;
      }
      i = 1;
      label43:
      if (i == 0) {
        break label219;
      }
      if (this.errorName != null) {
        break label202;
      }
      label54:
      i = 1;
      label56:
      if (i == 0) {
        break label234;
      }
      if (this.errorText != null) {
        break label224;
      }
      i = 1;
      label69:
      if (paramServiceError.errorText != null) {
        break label229;
      }
      j = 1;
      label78:
      if (i != j) {
        break label234;
      }
      i = 1;
      label85:
      if (i == 0) {
        break label256;
      }
      if (this.errorText != null) {
        break label239;
      }
      label96:
      i = 1;
      label98:
      if (i == 0) {
        break label271;
      }
      if (this.errorCode != null) {
        break label261;
      }
      i = 1;
      label111:
      if (paramServiceError.errorCode != null) {
        break label266;
      }
      j = 1;
      label120:
      if (i != j) {
        break label271;
      }
      i = 1;
      label127:
      if (i == 0) {
        break label293;
      }
      if (this.errorCode != null) {
        break label276;
      }
      label138:
      i = 1;
      label140:
      if (i == 0) {
        break label308;
      }
      if (this.pageName != null) {
        break label298;
      }
      i = 1;
      label153:
      if (paramServiceError.pageName != null) {
        break label303;
      }
      j = 1;
      label162:
      if (i != j) {
        break label308;
      }
      i = 1;
      label169:
      if (i == 0) {
        break label330;
      }
      if (this.pageName != null) {
        break label313;
      }
    }
    label187:
    label192:
    label197:
    label202:
    label219:
    label224:
    label229:
    label234:
    label239:
    label256:
    label261:
    label266:
    label271:
    label276:
    label293:
    label298:
    label303:
    label308:
    label313:
    while (this.pageName.length() == paramServiceError.pageName.length())
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
      if (this.errorName.length() == paramServiceError.errorName.length()) {
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
      if (this.errorText.length() == paramServiceError.errorText.length()) {
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
      if (this.errorCode.length() == paramServiceError.errorCode.length()) {
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
    }
    label330:
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
      this.errorName = ReadHelper.readWString(paramProtocolReader, localFieldTag.type);
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
        this.errorText = ReadHelper.readWString(paramProtocolReader, localFieldTag.type);
        break;
      case 30: 
        this.errorCode = ReadHelper.readWString(paramProtocolReader, localFieldTag.type);
        break;
      case 40: 
        this.pageName = ReadHelper.readWString(paramProtocolReader, localFieldTag.type);
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
      this.errorName = paramProtocolReader.readWString();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.errorText = paramProtocolReader.readWString();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.errorCode = paramProtocolReader.readWString();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.pageName = paramProtocolReader.readWString();
    }
    paramProtocolReader.readStructEnd();
  }
  
  public void reset()
  {
    reset("ServiceError", "com.microsoft.xbox.idp.telemetry.utc.ServiceError");
  }
  
  protected void reset(String paramString1, String paramString2)
  {
    super.reset(paramString1, paramString2);
    this.errorName = "";
    this.errorText = "";
    this.errorCode = "";
    this.pageName = "";
  }
  
  public final void setErrorCode(String paramString)
  {
    this.errorCode = paramString;
  }
  
  public final void setErrorName(String paramString)
  {
    this.errorName = paramString;
  }
  
  public final void setErrorText(String paramString)
  {
    this.errorText = paramString;
  }
  
  public void setField(FieldDef paramFieldDef, Object paramObject)
  {
    switch (paramFieldDef.getId())
    {
    default: 
      return;
    case 10: 
      this.errorName = ((String)paramObject);
      return;
    case 20: 
      this.errorText = ((String)paramObject);
      return;
    case 30: 
      this.errorCode = ((String)paramObject);
      return;
    }
    this.pageName = ((String)paramObject);
  }
  
  public final void setPageName(String paramString)
  {
    this.pageName = paramString;
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
    paramProtocolWriter.writeFieldBegin(BondDataType.BT_WSTRING, 10, Schema.errorName_metadata);
    paramProtocolWriter.writeWString(this.errorName);
    paramProtocolWriter.writeFieldEnd();
    if ((!bool) || (this.errorText != Schema.errorText_metadata.getDefault_value().getWstring_value()))
    {
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_WSTRING, 20, Schema.errorText_metadata);
      paramProtocolWriter.writeWString(this.errorText);
      paramProtocolWriter.writeFieldEnd();
      if ((bool) && (this.errorCode == Schema.errorCode_metadata.getDefault_value().getWstring_value())) {
        break label199;
      }
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_WSTRING, 30, Schema.errorCode_metadata);
      paramProtocolWriter.writeWString(this.errorCode);
      paramProtocolWriter.writeFieldEnd();
      label134:
      if ((bool) && (this.pageName == Schema.pageName_metadata.getDefault_value().getWstring_value())) {
        break label214;
      }
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_WSTRING, 40, Schema.pageName_metadata);
      paramProtocolWriter.writeWString(this.pageName);
      paramProtocolWriter.writeFieldEnd();
    }
    for (;;)
    {
      paramProtocolWriter.writeStructEnd(paramBoolean);
      return;
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_WSTRING, 20, Schema.errorText_metadata);
      break;
      label199:
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_WSTRING, 30, Schema.errorCode_metadata);
      break label134;
      label214:
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_WSTRING, 40, Schema.pageName_metadata);
    }
  }
  
  public static class Schema
  {
    private static final Metadata errorCode_metadata;
    private static final Metadata errorName_metadata;
    private static final Metadata errorText_metadata;
    public static final Metadata metadata = new Metadata();
    private static final Metadata pageName_metadata;
    public static final SchemaDef schemaDef;
    
    static
    {
      metadata.setName("ServiceError");
      metadata.setQualified_name("com.microsoft.xbox.idp.telemetry.utc.ServiceError");
      metadata.getAttributes().put("Description", "OnlineId Service Error event");
      errorName_metadata = new Metadata();
      errorName_metadata.setName("errorName");
      errorName_metadata.setModifier(Modifier.Required);
      errorName_metadata.getAttributes().put("Description", "the name of the error - Can be a specific name (such as Profile Load Error)");
      errorText_metadata = new Metadata();
      errorText_metadata.setName("errorText");
      errorText_metadata.getAttributes().put("Description", "The text of the http error, if applicable");
      errorCode_metadata = new Metadata();
      errorCode_metadata.setName("errorCode");
      errorCode_metadata.getAttributes().put("Description", "The code we get back in http error, if applicable.");
      pageName_metadata = new Metadata();
      pageName_metadata.setName("pageName");
      pageName_metadata.getAttributes().put("Description", "Most recent page shown");
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
      localStructDef.setBase_def(Data.Schema.getTypeDef(paramSchemaDef));
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)10);
      paramSchemaDef.setMetadata(errorName_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_WSTRING);
      localStructDef.getFields().add(paramSchemaDef);
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)20);
      paramSchemaDef.setMetadata(errorText_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_WSTRING);
      localStructDef.getFields().add(paramSchemaDef);
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)30);
      paramSchemaDef.setMetadata(errorCode_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_WSTRING);
      localStructDef.getFields().add(paramSchemaDef);
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)40);
      paramSchemaDef.setMetadata(pageName_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_WSTRING);
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


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\idp\telemetry\utc\ServiceError.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */