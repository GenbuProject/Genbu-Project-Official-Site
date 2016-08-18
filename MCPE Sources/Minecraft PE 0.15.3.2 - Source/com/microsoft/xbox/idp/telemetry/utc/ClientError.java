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

public class ClientError
  extends Data<CommonData>
{
  private String callStack;
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
  
  public final String getCallStack()
  {
    return this.callStack;
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
    case 40: 
      return this.callStack;
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
      paramObject = (ClientError)paramObject;
    } while ((!memberwiseCompareQuick((ClientError)paramObject)) || (!memberwiseCompareDeep((ClientError)paramObject)));
    return true;
  }
  
  protected boolean memberwiseCompareDeep(ClientError paramClientError)
  {
    int i;
    if ((1 != 0) && (super.memberwiseCompareDeep(paramClientError)))
    {
      i = 1;
      if (i == 0) {
        break label98;
      }
      if (this.errorName != null) {
        break label84;
      }
      label25:
      i = 1;
      label27:
      if (i == 0) {
        break label117;
      }
      if (this.errorText != null) {
        break label103;
      }
      label38:
      i = 1;
      label40:
      if (i == 0) {
        break label136;
      }
      if (this.errorCode != null) {
        break label122;
      }
      label51:
      i = 1;
      label53:
      if (i == 0) {
        break label155;
      }
      if (this.callStack != null) {
        break label141;
      }
      label64:
      i = 1;
      label66:
      if (i == 0) {
        break label174;
      }
      if (this.pageName != null) {
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
    while (this.pageName.equals(paramClientError.pageName))
    {
      return true;
      i = 0;
      break;
      if (this.errorName.equals(paramClientError.errorName)) {
        break label25;
      }
      i = 0;
      break label27;
      if (this.errorText.equals(paramClientError.errorText)) {
        break label38;
      }
      i = 0;
      break label40;
      if (this.errorCode.equals(paramClientError.errorCode)) {
        break label51;
      }
      i = 0;
      break label53;
      if (this.callStack.equals(paramClientError.callStack)) {
        break label64;
      }
      i = 0;
      break label66;
    }
    label174:
    return false;
  }
  
  protected boolean memberwiseCompareQuick(ClientError paramClientError)
  {
    int i;
    label27:
    int j;
    if ((1 != 0) && (super.memberwiseCompareQuick(paramClientError)))
    {
      i = 1;
      if (i == 0) {
        break label239;
      }
      if (this.errorName != null) {
        break label229;
      }
      i = 1;
      if (paramClientError.errorName != null) {
        break label234;
      }
      j = 1;
      label36:
      if (i != j) {
        break label239;
      }
      i = 1;
      label43:
      if (i == 0) {
        break label261;
      }
      if (this.errorName != null) {
        break label244;
      }
      label54:
      i = 1;
      label56:
      if (i == 0) {
        break label276;
      }
      if (this.errorText != null) {
        break label266;
      }
      i = 1;
      label69:
      if (paramClientError.errorText != null) {
        break label271;
      }
      j = 1;
      label78:
      if (i != j) {
        break label276;
      }
      i = 1;
      label85:
      if (i == 0) {
        break label298;
      }
      if (this.errorText != null) {
        break label281;
      }
      label96:
      i = 1;
      label98:
      if (i == 0) {
        break label313;
      }
      if (this.errorCode != null) {
        break label303;
      }
      i = 1;
      label111:
      if (paramClientError.errorCode != null) {
        break label308;
      }
      j = 1;
      label120:
      if (i != j) {
        break label313;
      }
      i = 1;
      label127:
      if (i == 0) {
        break label335;
      }
      if (this.errorCode != null) {
        break label318;
      }
      label138:
      i = 1;
      label140:
      if (i == 0) {
        break label350;
      }
      if (this.callStack != null) {
        break label340;
      }
      i = 1;
      label153:
      if (paramClientError.callStack != null) {
        break label345;
      }
      j = 1;
      label162:
      if (i != j) {
        break label350;
      }
      i = 1;
      label169:
      if (i == 0) {
        break label372;
      }
      if (this.callStack != null) {
        break label355;
      }
      label180:
      i = 1;
      label182:
      if (i == 0) {
        break label387;
      }
      if (this.pageName != null) {
        break label377;
      }
      i = 1;
      label195:
      if (paramClientError.pageName != null) {
        break label382;
      }
      j = 1;
      label204:
      if (i != j) {
        break label387;
      }
      i = 1;
      label211:
      if (i == 0) {
        break label409;
      }
      if (this.pageName != null) {
        break label392;
      }
    }
    label229:
    label234:
    label239:
    label244:
    label261:
    label266:
    label271:
    label276:
    label281:
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
    label372:
    label377:
    label382:
    label387:
    label392:
    while (this.pageName.length() == paramClientError.pageName.length())
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
      if (this.errorName.length() == paramClientError.errorName.length()) {
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
      if (this.errorText.length() == paramClientError.errorText.length()) {
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
      if (this.errorCode.length() == paramClientError.errorCode.length()) {
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
      if (this.callStack.length() == paramClientError.callStack.length()) {
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
    }
    label409:
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
        this.callStack = ReadHelper.readWString(paramProtocolReader, localFieldTag.type);
        break;
      case 50: 
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
      this.callStack = paramProtocolReader.readWString();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.pageName = paramProtocolReader.readWString();
    }
    paramProtocolReader.readStructEnd();
  }
  
  public void reset()
  {
    reset("ClientError", "com.microsoft.xbox.idp.telemetry.utc.ClientError");
  }
  
  protected void reset(String paramString1, String paramString2)
  {
    super.reset(paramString1, paramString2);
    this.errorName = "";
    this.errorText = "";
    this.errorCode = "";
    this.callStack = "";
    this.pageName = "";
  }
  
  public final void setCallStack(String paramString)
  {
    this.callStack = paramString;
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
    case 40: 
      this.callStack = ((String)paramObject);
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
        break label243;
      }
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_WSTRING, 30, Schema.errorCode_metadata);
      paramProtocolWriter.writeWString(this.errorCode);
      paramProtocolWriter.writeFieldEnd();
      label134:
      if ((bool) && (this.callStack == Schema.callStack_metadata.getDefault_value().getWstring_value())) {
        break label258;
      }
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_WSTRING, 40, Schema.callStack_metadata);
      paramProtocolWriter.writeWString(this.callStack);
      paramProtocolWriter.writeFieldEnd();
      label178:
      if ((bool) && (this.pageName == Schema.pageName_metadata.getDefault_value().getWstring_value())) {
        break label273;
      }
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_WSTRING, 50, Schema.pageName_metadata);
      paramProtocolWriter.writeWString(this.pageName);
      paramProtocolWriter.writeFieldEnd();
    }
    for (;;)
    {
      paramProtocolWriter.writeStructEnd(paramBoolean);
      return;
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_WSTRING, 20, Schema.errorText_metadata);
      break;
      label243:
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_WSTRING, 30, Schema.errorCode_metadata);
      break label134;
      label258:
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_WSTRING, 40, Schema.callStack_metadata);
      break label178;
      label273:
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_WSTRING, 50, Schema.pageName_metadata);
    }
  }
  
  public static class Schema
  {
    private static final Metadata callStack_metadata;
    private static final Metadata errorCode_metadata;
    private static final Metadata errorName_metadata;
    private static final Metadata errorText_metadata;
    public static final Metadata metadata = new Metadata();
    private static final Metadata pageName_metadata;
    public static final SchemaDef schemaDef;
    
    static
    {
      metadata.setName("ClientError");
      metadata.setQualified_name("com.microsoft.xbox.idp.telemetry.utc.ClientError");
      metadata.getAttributes().put("Description", "OnlineId Client Error event");
      errorName_metadata = new Metadata();
      errorName_metadata.setName("errorName");
      errorName_metadata.setModifier(Modifier.Required);
      errorName_metadata.getAttributes().put("Description", "the name of the error-  Can be a specific name (such as UserCanceled) or Exception name (if exception handling)");
      errorText_metadata = new Metadata();
      errorText_metadata.setName("errorText");
      errorText_metadata.getAttributes().put("Description", "The text of the error message or exception, if applicable");
      errorCode_metadata = new Metadata();
      errorCode_metadata.setName("errorCode");
      errorCode_metadata.getAttributes().put("Description", "The code we get back in the exception, if applicable.");
      callStack_metadata = new Metadata();
      callStack_metadata.setName("callStack");
      callStack_metadata.getAttributes().put("Description", "Call stack if we have it.");
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
      paramSchemaDef.setMetadata(callStack_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_WSTRING);
      localStructDef.getFields().add(paramSchemaDef);
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)50);
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


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\idp\telemetry\utc\ClientError.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */