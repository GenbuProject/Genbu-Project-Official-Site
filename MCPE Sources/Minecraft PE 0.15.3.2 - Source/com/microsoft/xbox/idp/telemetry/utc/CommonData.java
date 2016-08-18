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
    if ((1 != 0) && (super.memberwiseCompareDeep(paramCommonData)))
    {
      i = 1;
      if (i == 0) {
        break label189;
      }
      if (this.eventVersion != null) {
        break label175;
      }
      label25:
      i = 1;
      label27:
      if (i == 0) {
        break label208;
      }
      if (this.deviceModel != null) {
        break label194;
      }
      label38:
      i = 1;
      label40:
      if (i == 0) {
        break label227;
      }
      if (this.xsapiVersion != null) {
        break label213;
      }
      label51:
      i = 1;
      label53:
      if (i == 0) {
        break label246;
      }
      if (this.appName != null) {
        break label232;
      }
      label64:
      i = 1;
      label66:
      if (i == 0) {
        break label265;
      }
      if (this.clientLanguage != null) {
        break label251;
      }
      label77:
      i = 1;
      label79:
      if (i == 0) {
        break label284;
      }
      if (this.sandboxId != null) {
        break label270;
      }
      label90:
      i = 1;
      label92:
      if (i == 0) {
        break label303;
      }
      if (this.appSessionId != null) {
        break label289;
      }
      label103:
      i = 1;
      label105:
      if (i == 0) {
        break label322;
      }
      if (this.userId != null) {
        break label308;
      }
      label116:
      i = 1;
      label118:
      if (i == 0) {
        break label341;
      }
      if (this.additionalInfo != null) {
        break label327;
      }
      label129:
      i = 1;
      label131:
      if (i == 0) {
        break label360;
      }
      if (this.accessibilityInfo != null) {
        break label346;
      }
      label142:
      i = 1;
      label144:
      if (i == 0) {
        break label379;
      }
      if (this.titleDeviceId != null) {
        break label365;
      }
      label155:
      i = 1;
      label157:
      if (i == 0) {
        break label398;
      }
      if (this.titleSessionId != null) {
        break label384;
      }
    }
    label175:
    label189:
    label194:
    label208:
    label213:
    label227:
    label232:
    label246:
    label251:
    label265:
    label270:
    label284:
    label289:
    label303:
    label308:
    label322:
    label327:
    label341:
    label346:
    label360:
    label365:
    label379:
    label384:
    while (this.titleSessionId.equals(paramCommonData.titleSessionId))
    {
      return true;
      i = 0;
      break;
      if (this.eventVersion.equals(paramCommonData.eventVersion)) {
        break label25;
      }
      i = 0;
      break label27;
      if (this.deviceModel.equals(paramCommonData.deviceModel)) {
        break label38;
      }
      i = 0;
      break label40;
      if (this.xsapiVersion.equals(paramCommonData.xsapiVersion)) {
        break label51;
      }
      i = 0;
      break label53;
      if (this.appName.equals(paramCommonData.appName)) {
        break label64;
      }
      i = 0;
      break label66;
      if (this.clientLanguage.equals(paramCommonData.clientLanguage)) {
        break label77;
      }
      i = 0;
      break label79;
      if (this.sandboxId.equals(paramCommonData.sandboxId)) {
        break label90;
      }
      i = 0;
      break label92;
      if (this.appSessionId.equals(paramCommonData.appSessionId)) {
        break label103;
      }
      i = 0;
      break label105;
      if (this.userId.equals(paramCommonData.userId)) {
        break label116;
      }
      i = 0;
      break label118;
      if (this.additionalInfo.equals(paramCommonData.additionalInfo)) {
        break label129;
      }
      i = 0;
      break label131;
      if (this.accessibilityInfo.equals(paramCommonData.accessibilityInfo)) {
        break label142;
      }
      i = 0;
      break label144;
      if (this.titleDeviceId.equals(paramCommonData.titleDeviceId)) {
        break label155;
      }
      i = 0;
      break label157;
    }
    label398:
    return false;
  }
  
  protected boolean memberwiseCompareQuick(CommonData paramCommonData)
  {
    int i;
    label27:
    int j;
    if ((1 != 0) && (super.memberwiseCompareQuick(paramCommonData)))
    {
      i = 1;
      if (i == 0) {
        break label550;
      }
      if (this.eventVersion != null) {
        break label540;
      }
      i = 1;
      if (paramCommonData.eventVersion != null) {
        break label545;
      }
      j = 1;
      label36:
      if (i != j) {
        break label550;
      }
      i = 1;
      label43:
      if (i == 0) {
        break label572;
      }
      if (this.eventVersion != null) {
        break label555;
      }
      label54:
      i = 1;
      label56:
      if (i == 0) {
        break label587;
      }
      if (this.deviceModel != null) {
        break label577;
      }
      i = 1;
      label69:
      if (paramCommonData.deviceModel != null) {
        break label582;
      }
      j = 1;
      label78:
      if (i != j) {
        break label587;
      }
      i = 1;
      label85:
      if (i == 0) {
        break label609;
      }
      if (this.deviceModel != null) {
        break label592;
      }
      label96:
      i = 1;
      label98:
      if (i == 0) {
        break label624;
      }
      if (this.xsapiVersion != null) {
        break label614;
      }
      i = 1;
      label111:
      if (paramCommonData.xsapiVersion != null) {
        break label619;
      }
      j = 1;
      label120:
      if (i != j) {
        break label624;
      }
      i = 1;
      label127:
      if (i == 0) {
        break label646;
      }
      if (this.xsapiVersion != null) {
        break label629;
      }
      label138:
      i = 1;
      label140:
      if (i == 0) {
        break label661;
      }
      if (this.appName != null) {
        break label651;
      }
      i = 1;
      label153:
      if (paramCommonData.appName != null) {
        break label656;
      }
      j = 1;
      label162:
      if (i != j) {
        break label661;
      }
      i = 1;
      label169:
      if (i == 0) {
        break label683;
      }
      if (this.appName != null) {
        break label666;
      }
      label180:
      i = 1;
      label182:
      if (i == 0) {
        break label698;
      }
      if (this.clientLanguage != null) {
        break label688;
      }
      i = 1;
      label195:
      if (paramCommonData.clientLanguage != null) {
        break label693;
      }
      j = 1;
      label204:
      if (i != j) {
        break label698;
      }
      i = 1;
      label211:
      if (i == 0) {
        break label720;
      }
      if (this.clientLanguage != null) {
        break label703;
      }
      label222:
      i = 1;
      label224:
      if ((i == 0) || (this.network != paramCommonData.network)) {
        break label725;
      }
      i = 1;
      label241:
      if (i == 0) {
        break label740;
      }
      if (this.sandboxId != null) {
        break label730;
      }
      i = 1;
      label254:
      if (paramCommonData.sandboxId != null) {
        break label735;
      }
      j = 1;
      label263:
      if (i != j) {
        break label740;
      }
      i = 1;
      label270:
      if (i == 0) {
        break label762;
      }
      if (this.sandboxId != null) {
        break label745;
      }
      label281:
      i = 1;
      label283:
      if (i == 0) {
        break label777;
      }
      if (this.appSessionId != null) {
        break label767;
      }
      i = 1;
      label296:
      if (paramCommonData.appSessionId != null) {
        break label772;
      }
      j = 1;
      label305:
      if (i != j) {
        break label777;
      }
      i = 1;
      label312:
      if (i == 0) {
        break label799;
      }
      if (this.appSessionId != null) {
        break label782;
      }
      label323:
      i = 1;
      label325:
      if (i == 0) {
        break label814;
      }
      if (this.userId != null) {
        break label804;
      }
      i = 1;
      label338:
      if (paramCommonData.userId != null) {
        break label809;
      }
      j = 1;
      label347:
      if (i != j) {
        break label814;
      }
      i = 1;
      label354:
      if (i == 0) {
        break label836;
      }
      if (this.userId != null) {
        break label819;
      }
      label365:
      i = 1;
      label367:
      if (i == 0) {
        break label851;
      }
      if (this.additionalInfo != null) {
        break label841;
      }
      i = 1;
      label380:
      if (paramCommonData.additionalInfo != null) {
        break label846;
      }
      j = 1;
      label389:
      if (i != j) {
        break label851;
      }
      i = 1;
      label396:
      if (i == 0) {
        break label873;
      }
      if (this.additionalInfo != null) {
        break label856;
      }
      label407:
      i = 1;
      label409:
      if (i == 0) {
        break label888;
      }
      if (this.accessibilityInfo != null) {
        break label878;
      }
      i = 1;
      label422:
      if (paramCommonData.accessibilityInfo != null) {
        break label883;
      }
      j = 1;
      label431:
      if (i != j) {
        break label888;
      }
      i = 1;
      label438:
      if (i == 0) {
        break label910;
      }
      if (this.accessibilityInfo != null) {
        break label893;
      }
      label449:
      i = 1;
      label451:
      if (i == 0) {
        break label925;
      }
      if (this.titleDeviceId != null) {
        break label915;
      }
      i = 1;
      label464:
      if (paramCommonData.titleDeviceId != null) {
        break label920;
      }
      j = 1;
      label473:
      if (i != j) {
        break label925;
      }
      i = 1;
      label480:
      if (i == 0) {
        break label947;
      }
      if (this.titleDeviceId != null) {
        break label930;
      }
      label491:
      i = 1;
      label493:
      if (i == 0) {
        break label962;
      }
      if (this.titleSessionId != null) {
        break label952;
      }
      i = 1;
      label506:
      if (paramCommonData.titleSessionId != null) {
        break label957;
      }
      j = 1;
      label515:
      if (i != j) {
        break label962;
      }
      i = 1;
      label522:
      if (i == 0) {
        break label984;
      }
      if (this.titleSessionId != null) {
        break label967;
      }
    }
    label540:
    label545:
    label550:
    label555:
    label572:
    label577:
    label582:
    label587:
    label592:
    label609:
    label614:
    label619:
    label624:
    label629:
    label646:
    label651:
    label656:
    label661:
    label666:
    label683:
    label688:
    label693:
    label698:
    label703:
    label720:
    label725:
    label730:
    label735:
    label740:
    label745:
    label762:
    label767:
    label772:
    label777:
    label782:
    label799:
    label804:
    label809:
    label814:
    label819:
    label836:
    label841:
    label846:
    label851:
    label856:
    label873:
    label878:
    label883:
    label888:
    label893:
    label910:
    label915:
    label920:
    label925:
    label930:
    label947:
    label952:
    label957:
    label962:
    label967:
    while (this.titleSessionId.length() == paramCommonData.titleSessionId.length())
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
      if (this.eventVersion.length() == paramCommonData.eventVersion.length()) {
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
      if (this.deviceModel.length() == paramCommonData.deviceModel.length()) {
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
      if (this.xsapiVersion.length() == paramCommonData.xsapiVersion.length()) {
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
      if (this.appName.length() == paramCommonData.appName.length()) {
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
      if (this.clientLanguage.length() == paramCommonData.clientLanguage.length()) {
        break label222;
      }
      i = 0;
      break label224;
      i = 0;
      break label241;
      i = 0;
      break label254;
      j = 0;
      break label263;
      i = 0;
      break label270;
      if (this.sandboxId.length() == paramCommonData.sandboxId.length()) {
        break label281;
      }
      i = 0;
      break label283;
      i = 0;
      break label296;
      j = 0;
      break label305;
      i = 0;
      break label312;
      if (this.appSessionId.length() == paramCommonData.appSessionId.length()) {
        break label323;
      }
      i = 0;
      break label325;
      i = 0;
      break label338;
      j = 0;
      break label347;
      i = 0;
      break label354;
      if (this.userId.length() == paramCommonData.userId.length()) {
        break label365;
      }
      i = 0;
      break label367;
      i = 0;
      break label380;
      j = 0;
      break label389;
      i = 0;
      break label396;
      if (this.additionalInfo.length() == paramCommonData.additionalInfo.length()) {
        break label407;
      }
      i = 0;
      break label409;
      i = 0;
      break label422;
      j = 0;
      break label431;
      i = 0;
      break label438;
      if (this.accessibilityInfo.length() == paramCommonData.accessibilityInfo.length()) {
        break label449;
      }
      i = 0;
      break label451;
      i = 0;
      break label464;
      j = 0;
      break label473;
      i = 0;
      break label480;
      if (this.titleDeviceId.length() == paramCommonData.titleDeviceId.length()) {
        break label491;
      }
      i = 0;
      break label493;
      i = 0;
      break label506;
      j = 0;
      break label515;
      i = 0;
      break label522;
    }
    label984:
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
      this.eventVersion = ReadHelper.readWString(paramProtocolReader, localFieldTag.type);
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


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\idp\telemetry\utc\CommonData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */