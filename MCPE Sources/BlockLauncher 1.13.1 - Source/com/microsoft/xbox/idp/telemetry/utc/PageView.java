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
import com.microsoft.bond.internal.Marshaler;
import com.microsoft.bond.internal.ReadHelper;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class PageView
  extends Data<CommonData>
{
  private String fromPage;
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
  
  public Object getField(FieldDef paramFieldDef)
  {
    switch (paramFieldDef.getId())
    {
    default: 
      return null;
    case 10: 
      return this.pageName;
    }
    return this.fromPage;
  }
  
  public final String getFromPage()
  {
    return this.fromPage;
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
      paramObject = (PageView)paramObject;
    } while ((!memberwiseCompareQuick((PageView)paramObject)) || (!memberwiseCompareDeep((PageView)paramObject)));
    return true;
  }
  
  protected boolean memberwiseCompareDeep(PageView paramPageView)
  {
    int i;
    if (super.memberwiseCompareDeep(paramPageView))
    {
      i = 1;
      if (i == 0) {
        break label55;
      }
      if (this.pageName != null) {
        break label41;
      }
      label21:
      i = 1;
      label23:
      if (i == 0) {
        break label74;
      }
      if (this.fromPage != null) {
        break label60;
      }
    }
    label41:
    label55:
    label60:
    while (this.fromPage.equals(paramPageView.fromPage))
    {
      return true;
      i = 0;
      break;
      if (this.pageName.equals(paramPageView.pageName)) {
        break label21;
      }
      i = 0;
      break label23;
    }
    label74:
    return false;
  }
  
  protected boolean memberwiseCompareQuick(PageView paramPageView)
  {
    int i;
    label23:
    int j;
    if (super.memberwiseCompareQuick(paramPageView))
    {
      i = 1;
      if (i == 0) {
        break label109;
      }
      if (this.pageName != null) {
        break label99;
      }
      i = 1;
      if (paramPageView.pageName != null) {
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
      if (this.pageName != null) {
        break label114;
      }
      label50:
      i = 1;
      label52:
      if (i == 0) {
        break label146;
      }
      if (this.fromPage != null) {
        break label136;
      }
      i = 1;
      label65:
      if (paramPageView.fromPage != null) {
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
      if (this.fromPage != null) {
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
    while (this.fromPage.length() == paramPageView.fromPage.length())
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
      if (this.pageName.length() == paramPageView.pageName.length()) {
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
      this.pageName = ReadHelper.readWString(paramProtocolReader, localFieldTag.type);
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
        this.fromPage = ReadHelper.readWString(paramProtocolReader, localFieldTag.type);
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
      this.pageName = paramProtocolReader.readWString();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.fromPage = paramProtocolReader.readWString();
    }
    paramProtocolReader.readStructEnd();
  }
  
  public void reset()
  {
    reset("PageView", "com.microsoft.xbox.idp.telemetry.utc.PageView");
  }
  
  protected void reset(String paramString1, String paramString2)
  {
    super.reset(paramString1, paramString2);
    this.pageName = "";
    this.fromPage = "";
  }
  
  public void setField(FieldDef paramFieldDef, Object paramObject)
  {
    switch (paramFieldDef.getId())
    {
    default: 
      return;
    case 10: 
      this.pageName = ((String)paramObject);
      return;
    }
    this.fromPage = ((String)paramObject);
  }
  
  public final void setFromPage(String paramString)
  {
    this.fromPage = paramString;
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
    paramProtocolWriter.hasCapability(ProtocolCapability.CAN_OMIT_FIELDS);
    paramProtocolWriter.writeStructBegin(Schema.metadata, paramBoolean);
    super.writeNested(paramProtocolWriter, true);
    paramProtocolWriter.writeFieldBegin(BondDataType.BT_WSTRING, 10, Schema.pageName_metadata);
    paramProtocolWriter.writeWString(this.pageName);
    paramProtocolWriter.writeFieldEnd();
    paramProtocolWriter.writeFieldBegin(BondDataType.BT_WSTRING, 20, Schema.fromPage_metadata);
    paramProtocolWriter.writeWString(this.fromPage);
    paramProtocolWriter.writeFieldEnd();
    paramProtocolWriter.writeStructEnd(paramBoolean);
  }
  
  public static class Schema
  {
    private static final Metadata fromPage_metadata;
    public static final Metadata metadata = new Metadata();
    private static final Metadata pageName_metadata;
    public static final SchemaDef schemaDef;
    
    static
    {
      metadata.setName("PageView");
      metadata.setQualified_name("com.microsoft.xbox.idp.telemetry.utc.PageView");
      metadata.getAttributes().put("Description", "OnlineId PageView event");
      pageName_metadata = new Metadata();
      pageName_metadata.setName("pageName");
      pageName_metadata.setModifier(Modifier.Required);
      pageName_metadata.getAttributes().put("Description", "The name of the currently viewed page");
      fromPage_metadata = new Metadata();
      fromPage_metadata.setName("fromPage");
      fromPage_metadata.setModifier(Modifier.Required);
      fromPage_metadata.getAttributes().put("Description", "The name of the previously viewed page (aka Referer Page Uri)");
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
      paramSchemaDef.setMetadata(pageName_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_WSTRING);
      localStructDef.getFields().add(paramSchemaDef);
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)20);
      paramSchemaDef.setMetadata(fromPage_metadata);
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


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\idp\telemetry\utc\PageView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */