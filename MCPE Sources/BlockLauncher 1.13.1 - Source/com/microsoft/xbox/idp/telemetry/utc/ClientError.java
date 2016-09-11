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
    if (super.memberwiseCompareDeep(paramClientError))
    {
      i = 1;
      if (i == 0) {
        break label94;
      }
      if (this.errorName != null) {
        break label80;
      }
      label21:
      i = 1;
      label23:
      if (i == 0) {
        break label113;
      }
      if (this.errorText != null) {
        break label99;
      }
      label34:
      i = 1;
      label36:
      if (i == 0) {
        break label132;
      }
      if (this.errorCode != null) {
        break label118;
      }
      label47:
      i = 1;
      label49:
      if (i == 0) {
        break label151;
      }
      if (this.callStack != null) {
        break label137;
      }
      label60:
      i = 1;
      label62:
      if (i == 0) {
        break label170;
      }
      if (this.pageName != null) {
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
    while (this.pageName.equals(paramClientError.pageName))
    {
      return true;
      i = 0;
      break;
      if (this.errorName.equals(paramClientError.errorName)) {
        break label21;
      }
      i = 0;
      break label23;
      if (this.errorText.equals(paramClientError.errorText)) {
        break label34;
      }
      i = 0;
      break label36;
      if (this.errorCode.equals(paramClientError.errorCode)) {
        break label47;
      }
      i = 0;
      break label49;
      if (this.callStack.equals(paramClientError.callStack)) {
        break label60;
      }
      i = 0;
      break label62;
    }
    label170:
    return false;
  }
  
  protected boolean memberwiseCompareQuick(ClientError paramClientError)
  {
    int i;
    label23:
    int j;
    if (super.memberwiseCompareQuick(paramClientError))
    {
      i = 1;
      if (i == 0) {
        break label235;
      }
      if (this.errorName != null) {
        break label225;
      }
      i = 1;
      if (paramClientError.errorName != null) {
        break label230;
      }
      j = 1;
      label32:
      if (i != j) {
        break label235;
      }
      i = 1;
      label39:
      if (i == 0) {
        break label257;
      }
      if (this.errorName != null) {
        break label240;
      }
      label50:
      i = 1;
      label52:
      if (i == 0) {
        break label272;
      }
      if (this.errorText != null) {
        break label262;
      }
      i = 1;
      label65:
      if (paramClientError.errorText != null) {
        break label267;
      }
      j = 1;
      label74:
      if (i != j) {
        break label272;
      }
      i = 1;
      label81:
      if (i == 0) {
        break label294;
      }
      if (this.errorText != null) {
        break label277;
      }
      label92:
      i = 1;
      label94:
      if (i == 0) {
        break label309;
      }
      if (this.errorCode != null) {
        break label299;
      }
      i = 1;
      label107:
      if (paramClientError.errorCode != null) {
        break label304;
      }
      j = 1;
      label116:
      if (i != j) {
        break label309;
      }
      i = 1;
      label123:
      if (i == 0) {
        break label331;
      }
      if (this.errorCode != null) {
        break label314;
      }
      label134:
      i = 1;
      label136:
      if (i == 0) {
        break label346;
      }
      if (this.callStack != null) {
        break label336;
      }
      i = 1;
      label149:
      if (paramClientError.callStack != null) {
        break label341;
      }
      j = 1;
      label158:
      if (i != j) {
        break label346;
      }
      i = 1;
      label165:
      if (i == 0) {
        break label368;
      }
      if (this.callStack != null) {
        break label351;
      }
      label176:
      i = 1;
      label178:
      if (i == 0) {
        break label383;
      }
      if (this.pageName != null) {
        break label373;
      }
      i = 1;
      label191:
      if (paramClientError.pageName != null) {
        break label378;
      }
      j = 1;
      label200:
      if (i != j) {
        break label383;
      }
      i = 1;
      label207:
      if (i == 0) {
        break label405;
      }
      if (this.pageName != null) {
        break label388;
      }
    }
    label225:
    label230:
    label235:
    label240:
    label257:
    label262:
    label267:
    label272:
    label277:
    label294:
    label299:
    label304:
    label309:
    label314:
    label331:
    label336:
    label341:
    label346:
    label351:
    label368:
    label373:
    label378:
    label383:
    label388:
    while (this.pageName.length() == paramClientError.pageName.length())
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
      if (this.errorName.length() == paramClientError.errorName.length()) {
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
      if (this.errorText.length() == paramClientError.errorText.length()) {
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
      if (this.errorCode.length() == paramClientError.errorCode.length()) {
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
      if (this.callStack.length() == paramClientError.callStack.length()) {
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
    }
    label405:
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
      this.errorName = ReadHelper.readWString(paramProtocolReader, localFieldTag.type);
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


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\idp\telemetry\utc\ClientError.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */