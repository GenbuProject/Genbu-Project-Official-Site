package com.microsoft.xbox.idp.telemetry.utc;

import Microsoft.Telemetry.Base;
import Microsoft.Telemetry.Base.Schema;
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

public class CommonData
  extends Base
{
  private String accessibilityInfo;
  private String additionalInfo;
  private String appName;
  private String appSessionId;
  private String clientLanguage;
  private String deviceModel;
  private String eventVersion;
  private int network;
  private String sandboxId;
  private String titleDeviceId;
  private String titleSessionId;
  private String userId;
  private String xsapiVersion;
  
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
  
  public final String getAccessibilityInfo()
  {
    return this.accessibilityInfo;
  }
  
  public final String getAdditionalInfo()
  {
    return this.additionalInfo;
  }
  
  public final String getAppName()
  {
    return this.appName;
  }
  
  public final String getAppSessionId()
  {
    return this.appSessionId;
  }
  
  public final String getClientLanguage()
  {
    return this.clientLanguage;
  }
  
  public final String getDeviceModel()
  {
    return this.deviceModel;
  }
  
  public final String getEventVersion()
  {
    return this.eventVersion;
  }
  
  public Object getField(FieldDef paramFieldDef)
  {
    switch (paramFieldDef.getId())
    {
    default: 
      return null;
    case 10: 
      return this.eventVersion;
    case 20: 
      return this.deviceModel;
    case 30: 
      return this.xsapiVersion;
    case 40: 
      return this.appName;
    case 50: 
      return this.clientLanguage;
    case 60: 
      return Integer.valueOf(this.network);
    case 70: 
      return this.sandboxId;
    case 80: 
      return this.appSessionId;
    case 90: 
      return this.userId;
    case 100: 
      return this.additionalInfo;
    case 110: 
      return this.accessibilityInfo;
    case 120: 
      return this.titleDeviceId;
    }
    return this.titleSessionId;
  }
  
  public final int getNetwork()
  {
    return this.network;
  }
  
  public final String getSandboxId()
  {
    return this.sandboxId;
  }
  
  public SchemaDef getSchema()
  {
    return getRuntimeSchema();
  }
  
  public final String getTitleDeviceId()
  {
    return this.titleDeviceId;
  }
  
  public final String getTitleSessionId()
  {
    return this.titleSessionId;
  }
  
  public final String getUserId()
  {
    return this.userId;
  }
  
  public final String getXsapiVersion()
  {
    return this.xsapiVersion;
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
      paramObject = (CommonData)paramObject;
    } while ((!memberwiseCompareQuick((CommonData)paramObject)) || (!memberwiseCompareDeep((CommonData)paramObject)));
    return true;
  }
  
  protected boolean memberwiseCompareDeep(CommonData paramCommonData)
  {
    int i;
    if (super.memberwiseCompareDeep(paramCommonData))
    {
      i = 1;
      if (i == 0) {
        break label185;
      }
      if (this.eventVersion != null) {
        break label171;
      }
      label21:
      i = 1;
      label23:
      if (i == 0) {
        break label204;
      }
      if (this.deviceModel != null) {
        break label190;
      }
      label34:
      i = 1;
      label36:
      if (i == 0) {
        break label223;
      }
      if (this.xsapiVersion != null) {
        break label209;
      }
      label47:
      i = 1;
      label49:
      if (i == 0) {
        break label242;
      }
      if (this.appName != null) {
        break label228;
      }
      label60:
      i = 1;
      label62:
      if (i == 0) {
        break label261;
      }
      if (this.clientLanguage != null) {
        break label247;
      }
      label73:
      i = 1;
      label75:
      if (i == 0) {
        break label280;
      }
      if (this.sandboxId != null) {
        break label266;
      }
      label86:
      i = 1;
      label88:
      if (i == 0) {
        break label299;
      }
      if (this.appSessionId != null) {
        break label285;
      }
      label99:
      i = 1;
      label101:
      if (i == 0) {
        break label318;
      }
      if (this.userId != null) {
        break label304;
      }
      label112:
      i = 1;
      label114:
      if (i == 0) {
        break label337;
      }
      if (this.additionalInfo != null) {
        break label323;
      }
      label125:
      i = 1;
      label127:
      if (i == 0) {
        break label356;
      }
      if (this.accessibilityInfo != null) {
        break label342;
      }
      label138:
      i = 1;
      label140:
      if (i == 0) {
        break label375;
      }
      if (this.titleDeviceId != null) {
        break label361;
      }
      label151:
      i = 1;
      label153:
      if (i == 0) {
        break label394;
      }
      if (this.titleSessionId != null) {
        break label380;
      }
    }
    label171:
    label185:
    label190:
    label204:
    label209:
    label223:
    label228:
    label242:
    label247:
    label261:
    label266:
    label280:
    label285:
    label299:
    label304:
    label318:
    label323:
    label337:
    label342:
    label356:
    label361:
    label375:
    label380:
    while (this.titleSessionId.equals(paramCommonData.titleSessionId))
    {
      return true;
      i = 0;
      break;
      if (this.eventVersion.equals(paramCommonData.eventVersion)) {
        break label21;
      }
      i = 0;
      break label23;
      if (this.deviceModel.equals(paramCommonData.deviceModel)) {
        break label34;
      }
      i = 0;
      break label36;
      if (this.xsapiVersion.equals(paramCommonData.xsapiVersion)) {
        break label47;
      }
      i = 0;
      break label49;
      if (this.appName.equals(paramCommonData.appName)) {
        break label60;
      }
      i = 0;
      break label62;
      if (this.clientLanguage.equals(paramCommonData.clientLanguage)) {
        break label73;
      }
      i = 0;
      break label75;
      if (this.sandboxId.equals(paramCommonData.sandboxId)) {
        break label86;
      }
      i = 0;
      break label88;
      if (this.appSessionId.equals(paramCommonData.appSessionId)) {
        break label99;
      }
      i = 0;
      break label101;
      if (this.userId.equals(paramCommonData.userId)) {
        break label112;
      }
      i = 0;
      break label114;
      if (this.additionalInfo.equals(paramCommonData.additionalInfo)) {
        break label125;
      }
      i = 0;
      break label127;
      if (this.accessibilityInfo.equals(paramCommonData.accessibilityInfo)) {
        break label138;
      }
      i = 0;
      break label140;
      if (this.titleDeviceId.equals(paramCommonData.titleDeviceId)) {
        break label151;
      }
      i = 0;
      break label153;
    }
    label394:
    return false;
  }
  
  protected boolean memberwiseCompareQuick(CommonData paramCommonData)
  {
    int i;
    label23:
    int j;
    if (super.memberwiseCompareQuick(paramCommonData))
    {
      i = 1;
      if (i == 0) {
        break label546;
      }
      if (this.eventVersion != null) {
        break label536;
      }
      i = 1;
      if (paramCommonData.eventVersion != null) {
        break label541;
      }
      j = 1;
      label32:
      if (i != j) {
        break label546;
      }
      i = 1;
      label39:
      if (i == 0) {
        break label568;
      }
      if (this.eventVersion != null) {
        break label551;
      }
      label50:
      i = 1;
      label52:
      if (i == 0) {
        break label583;
      }
      if (this.deviceModel != null) {
        break label573;
      }
      i = 1;
      label65:
      if (paramCommonData.deviceModel != null) {
        break label578;
      }
      j = 1;
      label74:
      if (i != j) {
        break label583;
      }
      i = 1;
      label81:
      if (i == 0) {
        break label605;
      }
      if (this.deviceModel != null) {
        break label588;
      }
      label92:
      i = 1;
      label94:
      if (i == 0) {
        break label620;
      }
      if (this.xsapiVersion != null) {
        break label610;
      }
      i = 1;
      label107:
      if (paramCommonData.xsapiVersion != null) {
        break label615;
      }
      j = 1;
      label116:
      if (i != j) {
        break label620;
      }
      i = 1;
      label123:
      if (i == 0) {
        break label642;
      }
      if (this.xsapiVersion != null) {
        break label625;
      }
      label134:
      i = 1;
      label136:
      if (i == 0) {
        break label657;
      }
      if (this.appName != null) {
        break label647;
      }
      i = 1;
      label149:
      if (paramCommonData.appName != null) {
        break label652;
      }
      j = 1;
      label158:
      if (i != j) {
        break label657;
      }
      i = 1;
      label165:
      if (i == 0) {
        break label679;
      }
      if (this.appName != null) {
        break label662;
      }
      label176:
      i = 1;
      label178:
      if (i == 0) {
        break label694;
      }
      if (this.clientLanguage != null) {
        break label684;
      }
      i = 1;
      label191:
      if (paramCommonData.clientLanguage != null) {
        break label689;
      }
      j = 1;
      label200:
      if (i != j) {
        break label694;
      }
      i = 1;
      label207:
      if (i == 0) {
        break label716;
      }
      if (this.clientLanguage != null) {
        break label699;
      }
      label218:
      i = 1;
      label220:
      if ((i == 0) || (this.network != paramCommonData.network)) {
        break label721;
      }
      i = 1;
      label237:
      if (i == 0) {
        break label736;
      }
      if (this.sandboxId != null) {
        break label726;
      }
      i = 1;
      label250:
      if (paramCommonData.sandboxId != null) {
        break label731;
      }
      j = 1;
      label259:
      if (i != j) {
        break label736;
      }
      i = 1;
      label266:
      if (i == 0) {
        break label758;
      }
      if (this.sandboxId != null) {
        break label741;
      }
      label277:
      i = 1;
      label279:
      if (i == 0) {
        break label773;
      }
      if (this.appSessionId != null) {
        break label763;
      }
      i = 1;
      label292:
      if (paramCommonData.appSessionId != null) {
        break label768;
      }
      j = 1;
      label301:
      if (i != j) {
        break label773;
      }
      i = 1;
      label308:
      if (i == 0) {
        break label795;
      }
      if (this.appSessionId != null) {
        break label778;
      }
      label319:
      i = 1;
      label321:
      if (i == 0) {
        break label810;
      }
      if (this.userId != null) {
        break label800;
      }
      i = 1;
      label334:
      if (paramCommonData.userId != null) {
        break label805;
      }
      j = 1;
      label343:
      if (i != j) {
        break label810;
      }
      i = 1;
      label350:
      if (i == 0) {
        break label832;
      }
      if (this.userId != null) {
        break label815;
      }
      label361:
      i = 1;
      label363:
      if (i == 0) {
        break label847;
      }
      if (this.additionalInfo != null) {
        break label837;
      }
      i = 1;
      label376:
      if (paramCommonData.additionalInfo != null) {
        break label842;
      }
      j = 1;
      label385:
      if (i != j) {
        break label847;
      }
      i = 1;
      label392:
      if (i == 0) {
        break label869;
      }
      if (this.additionalInfo != null) {
        break label852;
      }
      label403:
      i = 1;
      label405:
      if (i == 0) {
        break label884;
      }
      if (this.accessibilityInfo != null) {
        break label874;
      }
      i = 1;
      label418:
      if (paramCommonData.accessibilityInfo != null) {
        break label879;
      }
      j = 1;
      label427:
      if (i != j) {
        break label884;
      }
      i = 1;
      label434:
      if (i == 0) {
        break label906;
      }
      if (this.accessibilityInfo != null) {
        break label889;
      }
      label445:
      i = 1;
      label447:
      if (i == 0) {
        break label921;
      }
      if (this.titleDeviceId != null) {
        break label911;
      }
      i = 1;
      label460:
      if (paramCommonData.titleDeviceId != null) {
        break label916;
      }
      j = 1;
      label469:
      if (i != j) {
        break label921;
      }
      i = 1;
      label476:
      if (i == 0) {
        break label943;
      }
      if (this.titleDeviceId != null) {
        break label926;
      }
      label487:
      i = 1;
      label489:
      if (i == 0) {
        break label958;
      }
      if (this.titleSessionId != null) {
        break label948;
      }
      i = 1;
      label502:
      if (paramCommonData.titleSessionId != null) {
        break label953;
      }
      j = 1;
      label511:
      if (i != j) {
        break label958;
      }
      i = 1;
      label518:
      if (i == 0) {
        break label980;
      }
      if (this.titleSessionId != null) {
        break label963;
      }
    }
    label536:
    label541:
    label546:
    label551:
    label568:
    label573:
    label578:
    label583:
    label588:
    label605:
    label610:
    label615:
    label620:
    label625:
    label642:
    label647:
    label652:
    label657:
    label662:
    label679:
    label684:
    label689:
    label694:
    label699:
    label716:
    label721:
    label726:
    label731:
    label736:
    label741:
    label758:
    label763:
    label768:
    label773:
    label778:
    label795:
    label800:
    label805:
    label810:
    label815:
    label832:
    label837:
    label842:
    label847:
    label852:
    label869:
    label874:
    label879:
    label884:
    label889:
    label906:
    label911:
    label916:
    label921:
    label926:
    label943:
    label948:
    label953:
    label958:
    label963:
    while (this.titleSessionId.length() == paramCommonData.titleSessionId.length())
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
      if (this.eventVersion.length() == paramCommonData.eventVersion.length()) {
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
      if (this.deviceModel.length() == paramCommonData.deviceModel.length()) {
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
      if (this.xsapiVersion.length() == paramCommonData.xsapiVersion.length()) {
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
      if (this.appName.length() == paramCommonData.appName.length()) {
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
      if (this.clientLanguage.length() == paramCommonData.clientLanguage.length()) {
        break label218;
      }
      i = 0;
      break label220;
      i = 0;
      break label237;
      i = 0;
      break label250;
      j = 0;
      break label259;
      i = 0;
      break label266;
      if (this.sandboxId.length() == paramCommonData.sandboxId.length()) {
        break label277;
      }
      i = 0;
      break label279;
      i = 0;
      break label292;
      j = 0;
      break label301;
      i = 0;
      break label308;
      if (this.appSessionId.length() == paramCommonData.appSessionId.length()) {
        break label319;
      }
      i = 0;
      break label321;
      i = 0;
      break label334;
      j = 0;
      break label343;
      i = 0;
      break label350;
      if (this.userId.length() == paramCommonData.userId.length()) {
        break label361;
      }
      i = 0;
      break label363;
      i = 0;
      break label376;
      j = 0;
      break label385;
      i = 0;
      break label392;
      if (this.additionalInfo.length() == paramCommonData.additionalInfo.length()) {
        break label403;
      }
      i = 0;
      break label405;
      i = 0;
      break label418;
      j = 0;
      break label427;
      i = 0;
      break label434;
      if (this.accessibilityInfo.length() == paramCommonData.accessibilityInfo.length()) {
        break label445;
      }
      i = 0;
      break label447;
      i = 0;
      break label460;
      j = 0;
      break label469;
      i = 0;
      break label476;
      if (this.titleDeviceId.length() == paramCommonData.titleDeviceId.length()) {
        break label487;
      }
      i = 0;
      break label489;
      i = 0;
      break label502;
      j = 0;
      break label511;
      i = 0;
      break label518;
    }
    label980:
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
      this.eventVersion = ReadHelper.readWString(paramProtocolReader, localFieldTag.type);
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
        this.deviceModel = ReadHelper.readWString(paramProtocolReader, localFieldTag.type);
        break;
      case 30: 
        this.xsapiVersion = ReadHelper.readWString(paramProtocolReader, localFieldTag.type);
        break;
      case 40: 
        this.appName = ReadHelper.readWString(paramProtocolReader, localFieldTag.type);
        break;
      case 50: 
        this.clientLanguage = ReadHelper.readWString(paramProtocolReader, localFieldTag.type);
        break;
      case 60: 
        this.network = ReadHelper.readUInt32(paramProtocolReader, localFieldTag.type);
        break;
      case 70: 
        this.sandboxId = ReadHelper.readWString(paramProtocolReader, localFieldTag.type);
        break;
      case 80: 
        this.appSessionId = ReadHelper.readWString(paramProtocolReader, localFieldTag.type);
        break;
      case 90: 
        this.userId = ReadHelper.readWString(paramProtocolReader, localFieldTag.type);
        break;
      case 100: 
        this.additionalInfo = ReadHelper.readWString(paramProtocolReader, localFieldTag.type);
        break;
      case 110: 
        this.accessibilityInfo = ReadHelper.readWString(paramProtocolReader, localFieldTag.type);
        break;
      case 120: 
        this.titleDeviceId = ReadHelper.readWString(paramProtocolReader, localFieldTag.type);
        break;
      case 130: 
        this.titleSessionId = ReadHelper.readWString(paramProtocolReader, localFieldTag.type);
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
      this.eventVersion = paramProtocolReader.readWString();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.deviceModel = paramProtocolReader.readWString();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.xsapiVersion = paramProtocolReader.readWString();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.appName = paramProtocolReader.readWString();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.clientLanguage = paramProtocolReader.readWString();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.network = paramProtocolReader.readUInt32();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.sandboxId = paramProtocolReader.readWString();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.appSessionId = paramProtocolReader.readWString();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.userId = paramProtocolReader.readWString();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.additionalInfo = paramProtocolReader.readWString();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.accessibilityInfo = paramProtocolReader.readWString();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.titleDeviceId = paramProtocolReader.readWString();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.titleSessionId = paramProtocolReader.readWString();
    }
    paramProtocolReader.readStructEnd();
  }
  
  public void reset()
  {
    reset("CommonData", "com.microsoft.xbox.idp.telemetry.utc.CommonData");
  }
  
  protected void reset(String paramString1, String paramString2)
  {
    super.reset(paramString1, paramString2);
    this.eventVersion = "";
    this.deviceModel = "";
    this.xsapiVersion = "";
    this.appName = "";
    this.clientLanguage = "";
    this.network = 0;
    this.sandboxId = "";
    this.appSessionId = "";
    this.userId = "";
    this.additionalInfo = "";
    this.accessibilityInfo = "";
    this.titleDeviceId = "";
    this.titleSessionId = "";
  }
  
  public final void setAccessibilityInfo(String paramString)
  {
    this.accessibilityInfo = paramString;
  }
  
  public final void setAdditionalInfo(String paramString)
  {
    this.additionalInfo = paramString;
  }
  
  public final void setAppName(String paramString)
  {
    this.appName = paramString;
  }
  
  public final void setAppSessionId(String paramString)
  {
    this.appSessionId = paramString;
  }
  
  public final void setClientLanguage(String paramString)
  {
    this.clientLanguage = paramString;
  }
  
  public final void setDeviceModel(String paramString)
  {
    this.deviceModel = paramString;
  }
  
  public final void setEventVersion(String paramString)
  {
    this.eventVersion = paramString;
  }
  
  public void setField(FieldDef paramFieldDef, Object paramObject)
  {
    switch (paramFieldDef.getId())
    {
    default: 
      return;
    case 10: 
      this.eventVersion = ((String)paramObject);
      return;
    case 20: 
      this.deviceModel = ((String)paramObject);
      return;
    case 30: 
      this.xsapiVersion = ((String)paramObject);
      return;
    case 40: 
      this.appName = ((String)paramObject);
      return;
    case 50: 
      this.clientLanguage = ((String)paramObject);
      return;
    case 60: 
      this.network = ((Integer)paramObject).intValue();
      return;
    case 70: 
      this.sandboxId = ((String)paramObject);
      return;
    case 80: 
      this.appSessionId = ((String)paramObject);
      return;
    case 90: 
      this.userId = ((String)paramObject);
      return;
    case 100: 
      this.additionalInfo = ((String)paramObject);
      return;
    case 110: 
      this.accessibilityInfo = ((String)paramObject);
      return;
    case 120: 
      this.titleDeviceId = ((String)paramObject);
      return;
    }
    this.titleSessionId = ((String)paramObject);
  }
  
  public final void setNetwork(int paramInt)
  {
    this.network = paramInt;
  }
  
  public final void setSandboxId(String paramString)
  {
    this.sandboxId = paramString;
  }
  
  public final void setTitleDeviceId(String paramString)
  {
    this.titleDeviceId = paramString;
  }
  
  public final void setTitleSessionId(String paramString)
  {
    this.titleSessionId = paramString;
  }
  
  public final void setUserId(String paramString)
  {
    this.userId = paramString;
  }
  
  public final void setXsapiVersion(String paramString)
  {
    this.xsapiVersion = paramString;
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
    paramProtocolWriter.writeFieldBegin(BondDataType.BT_WSTRING, 10, Schema.eventVersion_metadata);
    paramProtocolWriter.writeWString(this.eventVersion);
    paramProtocolWriter.writeFieldEnd();
    paramProtocolWriter.writeFieldBegin(BondDataType.BT_WSTRING, 20, Schema.deviceModel_metadata);
    paramProtocolWriter.writeWString(this.deviceModel);
    paramProtocolWriter.writeFieldEnd();
    paramProtocolWriter.writeFieldBegin(BondDataType.BT_WSTRING, 30, Schema.xsapiVersion_metadata);
    paramProtocolWriter.writeWString(this.xsapiVersion);
    paramProtocolWriter.writeFieldEnd();
    paramProtocolWriter.writeFieldBegin(BondDataType.BT_WSTRING, 40, Schema.appName_metadata);
    paramProtocolWriter.writeWString(this.appName);
    paramProtocolWriter.writeFieldEnd();
    paramProtocolWriter.writeFieldBegin(BondDataType.BT_WSTRING, 50, Schema.clientLanguage_metadata);
    paramProtocolWriter.writeWString(this.clientLanguage);
    paramProtocolWriter.writeFieldEnd();
    paramProtocolWriter.writeFieldBegin(BondDataType.BT_UINT32, 60, Schema.network_metadata);
    paramProtocolWriter.writeUInt32(this.network);
    paramProtocolWriter.writeFieldEnd();
    paramProtocolWriter.writeFieldBegin(BondDataType.BT_WSTRING, 70, Schema.sandboxId_metadata);
    paramProtocolWriter.writeWString(this.sandboxId);
    paramProtocolWriter.writeFieldEnd();
    paramProtocolWriter.writeFieldBegin(BondDataType.BT_WSTRING, 80, Schema.appSessionId_metadata);
    paramProtocolWriter.writeWString(this.appSessionId);
    paramProtocolWriter.writeFieldEnd();
    paramProtocolWriter.writeFieldBegin(BondDataType.BT_WSTRING, 90, Schema.userId_metadata);
    paramProtocolWriter.writeWString(this.userId);
    paramProtocolWriter.writeFieldEnd();
    paramProtocolWriter.writeFieldBegin(BondDataType.BT_WSTRING, 100, Schema.additionalInfo_metadata);
    paramProtocolWriter.writeWString(this.additionalInfo);
    paramProtocolWriter.writeFieldEnd();
    paramProtocolWriter.writeFieldBegin(BondDataType.BT_WSTRING, 110, Schema.accessibilityInfo_metadata);
    paramProtocolWriter.writeWString(this.accessibilityInfo);
    paramProtocolWriter.writeFieldEnd();
    paramProtocolWriter.writeFieldBegin(BondDataType.BT_WSTRING, 120, Schema.titleDeviceId_metadata);
    paramProtocolWriter.writeWString(this.titleDeviceId);
    paramProtocolWriter.writeFieldEnd();
    paramProtocolWriter.writeFieldBegin(BondDataType.BT_WSTRING, 130, Schema.titleSessionId_metadata);
    paramProtocolWriter.writeWString(this.titleSessionId);
    paramProtocolWriter.writeFieldEnd();
    paramProtocolWriter.writeStructEnd(paramBoolean);
  }
  
  public static class Schema
  {
    private static final Metadata accessibilityInfo_metadata;
    private static final Metadata additionalInfo_metadata;
    private static final Metadata appName_metadata;
    private static final Metadata appSessionId_metadata;
    private static final Metadata clientLanguage_metadata;
    private static final Metadata deviceModel_metadata;
    private static final Metadata eventVersion_metadata;
    public static final Metadata metadata = new Metadata();
    private static final Metadata network_metadata;
    private static final Metadata sandboxId_metadata;
    public static final SchemaDef schemaDef;
    private static final Metadata titleDeviceId_metadata;
    private static final Metadata titleSessionId_metadata;
    private static final Metadata userId_metadata;
    private static final Metadata xsapiVersion_metadata;
    
    static
    {
      metadata.setName("CommonData");
      metadata.setQualified_name("com.microsoft.xbox.idp.telemetry.utc.CommonData");
      metadata.getAttributes().put("Description", "OnlineId base event with required fields");
      eventVersion_metadata = new Metadata();
      eventVersion_metadata.setName("eventVersion");
      eventVersion_metadata.setModifier(Modifier.Required);
      eventVersion_metadata.getAttributes().put("Description", "The event's version in the form of A.B.C where each subfield is the version for Part A, B, or C respectively.  This helps the backend cookers and processers adjust to different versions of the event schema");
      deviceModel_metadata = new Metadata();
      deviceModel_metadata.setName("deviceModel");
      deviceModel_metadata.setModifier(Modifier.Required);
      deviceModel_metadata.getAttributes().put("Description", "The specific model of the device.  On Android this is from the constant: android.os.Build.MODEL.  NOTE: For completeness, one should prepend android.os.Build.MANUFACTURER to this value if the MFG name is not part of the model name.");
      xsapiVersion_metadata = new Metadata();
      xsapiVersion_metadata.setName("xsapiVersion");
      xsapiVersion_metadata.setModifier(Modifier.Required);
      xsapiVersion_metadata.getAttributes().put("Description", "The xsapi version.  Should get this from the xsapi build properties");
      appName_metadata = new Metadata();
      appName_metadata.setName("appName");
      appName_metadata.setModifier(Modifier.Required);
      appName_metadata.getAttributes().put("Description", "The application name");
      clientLanguage_metadata = new Metadata();
      clientLanguage_metadata.setName("clientLanguage");
      clientLanguage_metadata.setModifier(Modifier.Required);
      clientLanguage_metadata.getAttributes().put("Description", "The system language-region (for example, en-US = english in USA).");
      network_metadata = new Metadata();
      network_metadata.setName("network");
      network_metadata.setModifier(Modifier.Required);
      network_metadata.getAttributes().put("Description", "The network connection being used (0 = unknown | 1 = wifi | 2 = cellular | 3 = wired)");
      network_metadata.getDefault_value().setUint_value(0L);
      sandboxId_metadata = new Metadata();
      sandboxId_metadata.setName("sandboxId");
      sandboxId_metadata.setModifier(Modifier.Required);
      sandboxId_metadata.getAttributes().put("Description", "The xsapi sandbox for service calls");
      appSessionId_metadata = new Metadata();
      appSessionId_metadata.setName("appSessionId");
      appSessionId_metadata.setModifier(Modifier.Required);
      appSessionId_metadata.getAttributes().put("Description", "The sessionId for the app; gets set on first use of telemetry -- useful for binding events together into scenarios and analyzing flow");
      userId_metadata = new Metadata();
      userId_metadata.setName("userId");
      userId_metadata.setModifier(Modifier.Required);
      userId_metadata.getAttributes().put("Description", "The User Id");
      additionalInfo_metadata = new Metadata();
      additionalInfo_metadata.setName("additionalInfo");
      additionalInfo_metadata.setModifier(Modifier.Required);
      additionalInfo_metadata.getAttributes().put("Description", "The json key-value collection of data that gives greater meaning to the event");
      accessibilityInfo_metadata = new Metadata();
      accessibilityInfo_metadata.setName("accessibilityInfo");
      accessibilityInfo_metadata.setModifier(Modifier.Required);
      accessibilityInfo_metadata.getAttributes().put("Description", "The json key-value collection of accessibility settings -- information within will differ by platform");
      titleDeviceId_metadata = new Metadata();
      titleDeviceId_metadata.setName("titleDeviceId");
      titleDeviceId_metadata.setModifier(Modifier.Required);
      titleDeviceId_metadata.getAttributes().put("Description", "The device guid from the title hosting xsapi idp or tcui");
      titleSessionId_metadata = new Metadata();
      titleSessionId_metadata.setName("titleSessionId");
      titleSessionId_metadata.setModifier(Modifier.Required);
      titleSessionId_metadata.getAttributes().put("Description", "The session guid from the title hosting xsapi idp or tcui");
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
      localStructDef.setBase_def(Base.Schema.getTypeDef(paramSchemaDef));
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)10);
      paramSchemaDef.setMetadata(eventVersion_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_WSTRING);
      localStructDef.getFields().add(paramSchemaDef);
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)20);
      paramSchemaDef.setMetadata(deviceModel_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_WSTRING);
      localStructDef.getFields().add(paramSchemaDef);
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)30);
      paramSchemaDef.setMetadata(xsapiVersion_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_WSTRING);
      localStructDef.getFields().add(paramSchemaDef);
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)40);
      paramSchemaDef.setMetadata(appName_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_WSTRING);
      localStructDef.getFields().add(paramSchemaDef);
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)50);
      paramSchemaDef.setMetadata(clientLanguage_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_WSTRING);
      localStructDef.getFields().add(paramSchemaDef);
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)60);
      paramSchemaDef.setMetadata(network_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_UINT32);
      localStructDef.getFields().add(paramSchemaDef);
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)70);
      paramSchemaDef.setMetadata(sandboxId_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_WSTRING);
      localStructDef.getFields().add(paramSchemaDef);
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)80);
      paramSchemaDef.setMetadata(appSessionId_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_WSTRING);
      localStructDef.getFields().add(paramSchemaDef);
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)90);
      paramSchemaDef.setMetadata(userId_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_WSTRING);
      localStructDef.getFields().add(paramSchemaDef);
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)100);
      paramSchemaDef.setMetadata(additionalInfo_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_WSTRING);
      localStructDef.getFields().add(paramSchemaDef);
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)110);
      paramSchemaDef.setMetadata(accessibilityInfo_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_WSTRING);
      localStructDef.getFields().add(paramSchemaDef);
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)120);
      paramSchemaDef.setMetadata(titleDeviceId_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_WSTRING);
      localStructDef.getFields().add(paramSchemaDef);
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)130);
      paramSchemaDef.setMetadata(titleSessionId_metadata);
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


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\idp\telemetry\utc\CommonData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */