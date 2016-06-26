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

public class xbox
  extends Extension
{
  private String deviceType;
  private String eventSequence;
  private String expiryTimestamp;
  private String isDevelopmentAccount;
  private String isTestAccount;
  private String issueTimestamp;
  private String sandboxId;
  private String signedInUsers;
  private String sti;
  private String titleId;
  private String xblDeviceId;
  
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
  
  public final String getDeviceType()
  {
    return this.deviceType;
  }
  
  public final String getEventSequence()
  {
    return this.eventSequence;
  }
  
  public final String getExpiryTimestamp()
  {
    return this.expiryTimestamp;
  }
  
  public Object getField(FieldDef paramFieldDef)
  {
    switch (paramFieldDef.getId())
    {
    default: 
      return null;
    case 10: 
      return this.sti;
    case 20: 
      return this.eventSequence;
    case 30: 
      return this.issueTimestamp;
    case 40: 
      return this.expiryTimestamp;
    case 50: 
      return this.sandboxId;
    case 60: 
      return this.deviceType;
    case 70: 
      return this.xblDeviceId;
    case 80: 
      return this.signedInUsers;
    case 90: 
      return this.isDevelopmentAccount;
    case 100: 
      return this.isTestAccount;
    }
    return this.titleId;
  }
  
  public final String getIsDevelopmentAccount()
  {
    return this.isDevelopmentAccount;
  }
  
  public final String getIsTestAccount()
  {
    return this.isTestAccount;
  }
  
  public final String getIssueTimestamp()
  {
    return this.issueTimestamp;
  }
  
  public final String getSandboxId()
  {
    return this.sandboxId;
  }
  
  public SchemaDef getSchema()
  {
    return getRuntimeSchema();
  }
  
  public final String getSignedInUsers()
  {
    return this.signedInUsers;
  }
  
  public final String getSti()
  {
    return this.sti;
  }
  
  public final String getTitleId()
  {
    return this.titleId;
  }
  
  public final String getXblDeviceId()
  {
    return this.xblDeviceId;
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
      paramObject = (xbox)paramObject;
    } while ((!memberwiseCompareQuick((xbox)paramObject)) || (!memberwiseCompareDeep((xbox)paramObject)));
    return true;
  }
  
  protected boolean memberwiseCompareDeep(xbox paramxbox)
  {
    int i;
    if ((1 != 0) && (super.memberwiseCompareDeep(paramxbox)))
    {
      i = 1;
      if (i == 0) {
        break label176;
      }
      if (this.sti != null) {
        break label162;
      }
      label25:
      i = 1;
      label27:
      if (i == 0) {
        break label195;
      }
      if (this.eventSequence != null) {
        break label181;
      }
      label38:
      i = 1;
      label40:
      if (i == 0) {
        break label214;
      }
      if (this.issueTimestamp != null) {
        break label200;
      }
      label51:
      i = 1;
      label53:
      if (i == 0) {
        break label233;
      }
      if (this.expiryTimestamp != null) {
        break label219;
      }
      label64:
      i = 1;
      label66:
      if (i == 0) {
        break label252;
      }
      if (this.sandboxId != null) {
        break label238;
      }
      label77:
      i = 1;
      label79:
      if (i == 0) {
        break label271;
      }
      if (this.deviceType != null) {
        break label257;
      }
      label90:
      i = 1;
      label92:
      if (i == 0) {
        break label290;
      }
      if (this.xblDeviceId != null) {
        break label276;
      }
      label103:
      i = 1;
      label105:
      if (i == 0) {
        break label309;
      }
      if (this.signedInUsers != null) {
        break label295;
      }
      label116:
      i = 1;
      label118:
      if (i == 0) {
        break label328;
      }
      if (this.isDevelopmentAccount != null) {
        break label314;
      }
      label129:
      i = 1;
      label131:
      if (i == 0) {
        break label347;
      }
      if (this.isTestAccount != null) {
        break label333;
      }
      label142:
      i = 1;
      label144:
      if (i == 0) {
        break label366;
      }
      if (this.titleId != null) {
        break label352;
      }
    }
    label162:
    label176:
    label181:
    label195:
    label200:
    label214:
    label219:
    label233:
    label238:
    label252:
    label257:
    label271:
    label276:
    label290:
    label295:
    label309:
    label314:
    label328:
    label333:
    label347:
    label352:
    while (this.titleId.equals(paramxbox.titleId))
    {
      return true;
      i = 0;
      break;
      if (this.sti.equals(paramxbox.sti)) {
        break label25;
      }
      i = 0;
      break label27;
      if (this.eventSequence.equals(paramxbox.eventSequence)) {
        break label38;
      }
      i = 0;
      break label40;
      if (this.issueTimestamp.equals(paramxbox.issueTimestamp)) {
        break label51;
      }
      i = 0;
      break label53;
      if (this.expiryTimestamp.equals(paramxbox.expiryTimestamp)) {
        break label64;
      }
      i = 0;
      break label66;
      if (this.sandboxId.equals(paramxbox.sandboxId)) {
        break label77;
      }
      i = 0;
      break label79;
      if (this.deviceType.equals(paramxbox.deviceType)) {
        break label90;
      }
      i = 0;
      break label92;
      if (this.xblDeviceId.equals(paramxbox.xblDeviceId)) {
        break label103;
      }
      i = 0;
      break label105;
      if (this.signedInUsers.equals(paramxbox.signedInUsers)) {
        break label116;
      }
      i = 0;
      break label118;
      if (this.isDevelopmentAccount.equals(paramxbox.isDevelopmentAccount)) {
        break label129;
      }
      i = 0;
      break label131;
      if (this.isTestAccount.equals(paramxbox.isTestAccount)) {
        break label142;
      }
      i = 0;
      break label144;
    }
    label366:
    return false;
  }
  
  protected boolean memberwiseCompareQuick(xbox paramxbox)
  {
    int i;
    label27:
    int j;
    if ((1 != 0) && (super.memberwiseCompareQuick(paramxbox)))
    {
      i = 1;
      if (i == 0) {
        break label491;
      }
      if (this.sti != null) {
        break label481;
      }
      i = 1;
      if (paramxbox.sti != null) {
        break label486;
      }
      j = 1;
      label36:
      if (i != j) {
        break label491;
      }
      i = 1;
      label43:
      if (i == 0) {
        break label513;
      }
      if (this.sti != null) {
        break label496;
      }
      label54:
      i = 1;
      label56:
      if (i == 0) {
        break label528;
      }
      if (this.eventSequence != null) {
        break label518;
      }
      i = 1;
      label69:
      if (paramxbox.eventSequence != null) {
        break label523;
      }
      j = 1;
      label78:
      if (i != j) {
        break label528;
      }
      i = 1;
      label85:
      if (i == 0) {
        break label550;
      }
      if (this.eventSequence != null) {
        break label533;
      }
      label96:
      i = 1;
      label98:
      if (i == 0) {
        break label565;
      }
      if (this.issueTimestamp != null) {
        break label555;
      }
      i = 1;
      label111:
      if (paramxbox.issueTimestamp != null) {
        break label560;
      }
      j = 1;
      label120:
      if (i != j) {
        break label565;
      }
      i = 1;
      label127:
      if (i == 0) {
        break label587;
      }
      if (this.issueTimestamp != null) {
        break label570;
      }
      label138:
      i = 1;
      label140:
      if (i == 0) {
        break label602;
      }
      if (this.expiryTimestamp != null) {
        break label592;
      }
      i = 1;
      label153:
      if (paramxbox.expiryTimestamp != null) {
        break label597;
      }
      j = 1;
      label162:
      if (i != j) {
        break label602;
      }
      i = 1;
      label169:
      if (i == 0) {
        break label624;
      }
      if (this.expiryTimestamp != null) {
        break label607;
      }
      label180:
      i = 1;
      label182:
      if (i == 0) {
        break label639;
      }
      if (this.sandboxId != null) {
        break label629;
      }
      i = 1;
      label195:
      if (paramxbox.sandboxId != null) {
        break label634;
      }
      j = 1;
      label204:
      if (i != j) {
        break label639;
      }
      i = 1;
      label211:
      if (i == 0) {
        break label661;
      }
      if (this.sandboxId != null) {
        break label644;
      }
      label222:
      i = 1;
      label224:
      if (i == 0) {
        break label676;
      }
      if (this.deviceType != null) {
        break label666;
      }
      i = 1;
      label237:
      if (paramxbox.deviceType != null) {
        break label671;
      }
      j = 1;
      label246:
      if (i != j) {
        break label676;
      }
      i = 1;
      label253:
      if (i == 0) {
        break label698;
      }
      if (this.deviceType != null) {
        break label681;
      }
      label264:
      i = 1;
      label266:
      if (i == 0) {
        break label713;
      }
      if (this.xblDeviceId != null) {
        break label703;
      }
      i = 1;
      label279:
      if (paramxbox.xblDeviceId != null) {
        break label708;
      }
      j = 1;
      label288:
      if (i != j) {
        break label713;
      }
      i = 1;
      label295:
      if (i == 0) {
        break label735;
      }
      if (this.xblDeviceId != null) {
        break label718;
      }
      label306:
      i = 1;
      label308:
      if (i == 0) {
        break label750;
      }
      if (this.signedInUsers != null) {
        break label740;
      }
      i = 1;
      label321:
      if (paramxbox.signedInUsers != null) {
        break label745;
      }
      j = 1;
      label330:
      if (i != j) {
        break label750;
      }
      i = 1;
      label337:
      if (i == 0) {
        break label772;
      }
      if (this.signedInUsers != null) {
        break label755;
      }
      label348:
      i = 1;
      label350:
      if (i == 0) {
        break label787;
      }
      if (this.isDevelopmentAccount != null) {
        break label777;
      }
      i = 1;
      label363:
      if (paramxbox.isDevelopmentAccount != null) {
        break label782;
      }
      j = 1;
      label372:
      if (i != j) {
        break label787;
      }
      i = 1;
      label379:
      if (i == 0) {
        break label809;
      }
      if (this.isDevelopmentAccount != null) {
        break label792;
      }
      label390:
      i = 1;
      label392:
      if (i == 0) {
        break label824;
      }
      if (this.isTestAccount != null) {
        break label814;
      }
      i = 1;
      label405:
      if (paramxbox.isTestAccount != null) {
        break label819;
      }
      j = 1;
      label414:
      if (i != j) {
        break label824;
      }
      i = 1;
      label421:
      if (i == 0) {
        break label846;
      }
      if (this.isTestAccount != null) {
        break label829;
      }
      label432:
      i = 1;
      label434:
      if (i == 0) {
        break label861;
      }
      if (this.titleId != null) {
        break label851;
      }
      i = 1;
      label447:
      if (paramxbox.titleId != null) {
        break label856;
      }
      j = 1;
      label456:
      if (i != j) {
        break label861;
      }
      i = 1;
      label463:
      if (i == 0) {
        break label883;
      }
      if (this.titleId != null) {
        break label866;
      }
    }
    label481:
    label486:
    label491:
    label496:
    label513:
    label518:
    label523:
    label528:
    label533:
    label550:
    label555:
    label560:
    label565:
    label570:
    label587:
    label592:
    label597:
    label602:
    label607:
    label624:
    label629:
    label634:
    label639:
    label644:
    label661:
    label666:
    label671:
    label676:
    label681:
    label698:
    label703:
    label708:
    label713:
    label718:
    label735:
    label740:
    label745:
    label750:
    label755:
    label772:
    label777:
    label782:
    label787:
    label792:
    label809:
    label814:
    label819:
    label824:
    label829:
    label846:
    label851:
    label856:
    label861:
    label866:
    while (this.titleId.length() == paramxbox.titleId.length())
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
      if (this.sti.length() == paramxbox.sti.length()) {
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
      if (this.eventSequence.length() == paramxbox.eventSequence.length()) {
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
      if (this.issueTimestamp.length() == paramxbox.issueTimestamp.length()) {
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
      if (this.expiryTimestamp.length() == paramxbox.expiryTimestamp.length()) {
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
      if (this.sandboxId.length() == paramxbox.sandboxId.length()) {
        break label222;
      }
      i = 0;
      break label224;
      i = 0;
      break label237;
      j = 0;
      break label246;
      i = 0;
      break label253;
      if (this.deviceType.length() == paramxbox.deviceType.length()) {
        break label264;
      }
      i = 0;
      break label266;
      i = 0;
      break label279;
      j = 0;
      break label288;
      i = 0;
      break label295;
      if (this.xblDeviceId.length() == paramxbox.xblDeviceId.length()) {
        break label306;
      }
      i = 0;
      break label308;
      i = 0;
      break label321;
      j = 0;
      break label330;
      i = 0;
      break label337;
      if (this.signedInUsers.length() == paramxbox.signedInUsers.length()) {
        break label348;
      }
      i = 0;
      break label350;
      i = 0;
      break label363;
      j = 0;
      break label372;
      i = 0;
      break label379;
      if (this.isDevelopmentAccount.length() == paramxbox.isDevelopmentAccount.length()) {
        break label390;
      }
      i = 0;
      break label392;
      i = 0;
      break label405;
      j = 0;
      break label414;
      i = 0;
      break label421;
      if (this.isTestAccount.length() == paramxbox.isTestAccount.length()) {
        break label432;
      }
      i = 0;
      break label434;
      i = 0;
      break label447;
      j = 0;
      break label456;
      i = 0;
      break label463;
    }
    label883:
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
      this.sti = ReadHelper.readString(paramProtocolReader, localFieldTag.type);
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
        this.eventSequence = ReadHelper.readString(paramProtocolReader, localFieldTag.type);
        break;
      case 30: 
        this.issueTimestamp = ReadHelper.readString(paramProtocolReader, localFieldTag.type);
        break;
      case 40: 
        this.expiryTimestamp = ReadHelper.readString(paramProtocolReader, localFieldTag.type);
        break;
      case 50: 
        this.sandboxId = ReadHelper.readString(paramProtocolReader, localFieldTag.type);
        break;
      case 60: 
        this.deviceType = ReadHelper.readString(paramProtocolReader, localFieldTag.type);
        break;
      case 70: 
        this.xblDeviceId = ReadHelper.readString(paramProtocolReader, localFieldTag.type);
        break;
      case 80: 
        this.signedInUsers = ReadHelper.readString(paramProtocolReader, localFieldTag.type);
        break;
      case 90: 
        this.isDevelopmentAccount = ReadHelper.readString(paramProtocolReader, localFieldTag.type);
        break;
      case 100: 
        this.isTestAccount = ReadHelper.readString(paramProtocolReader, localFieldTag.type);
        break;
      case 110: 
        this.titleId = ReadHelper.readString(paramProtocolReader, localFieldTag.type);
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
      this.sti = paramProtocolReader.readString();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.eventSequence = paramProtocolReader.readString();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.issueTimestamp = paramProtocolReader.readString();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.expiryTimestamp = paramProtocolReader.readString();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.sandboxId = paramProtocolReader.readString();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.deviceType = paramProtocolReader.readString();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.xblDeviceId = paramProtocolReader.readString();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.signedInUsers = paramProtocolReader.readString();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.isDevelopmentAccount = paramProtocolReader.readString();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.isTestAccount = paramProtocolReader.readString();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.titleId = paramProtocolReader.readString();
    }
    paramProtocolReader.readStructEnd();
  }
  
  public void reset()
  {
    reset("xbox", "Microsoft.Telemetry.Extensions.xbox");
  }
  
  protected void reset(String paramString1, String paramString2)
  {
    super.reset(paramString1, paramString2);
    this.sti = "";
    this.eventSequence = "";
    this.issueTimestamp = "";
    this.expiryTimestamp = "";
    this.sandboxId = "";
    this.deviceType = "";
    this.xblDeviceId = "";
    this.signedInUsers = "";
    this.isDevelopmentAccount = "";
    this.isTestAccount = "";
    this.titleId = "";
  }
  
  public final void setDeviceType(String paramString)
  {
    this.deviceType = paramString;
  }
  
  public final void setEventSequence(String paramString)
  {
    this.eventSequence = paramString;
  }
  
  public final void setExpiryTimestamp(String paramString)
  {
    this.expiryTimestamp = paramString;
  }
  
  public void setField(FieldDef paramFieldDef, Object paramObject)
  {
    switch (paramFieldDef.getId())
    {
    default: 
      return;
    case 10: 
      this.sti = ((String)paramObject);
      return;
    case 20: 
      this.eventSequence = ((String)paramObject);
      return;
    case 30: 
      this.issueTimestamp = ((String)paramObject);
      return;
    case 40: 
      this.expiryTimestamp = ((String)paramObject);
      return;
    case 50: 
      this.sandboxId = ((String)paramObject);
      return;
    case 60: 
      this.deviceType = ((String)paramObject);
      return;
    case 70: 
      this.xblDeviceId = ((String)paramObject);
      return;
    case 80: 
      this.signedInUsers = ((String)paramObject);
      return;
    case 90: 
      this.isDevelopmentAccount = ((String)paramObject);
      return;
    case 100: 
      this.isTestAccount = ((String)paramObject);
      return;
    }
    this.titleId = ((String)paramObject);
  }
  
  public final void setIsDevelopmentAccount(String paramString)
  {
    this.isDevelopmentAccount = paramString;
  }
  
  public final void setIsTestAccount(String paramString)
  {
    this.isTestAccount = paramString;
  }
  
  public final void setIssueTimestamp(String paramString)
  {
    this.issueTimestamp = paramString;
  }
  
  public final void setSandboxId(String paramString)
  {
    this.sandboxId = paramString;
  }
  
  public final void setSignedInUsers(String paramString)
  {
    this.signedInUsers = paramString;
  }
  
  public final void setSti(String paramString)
  {
    this.sti = paramString;
  }
  
  public final void setTitleId(String paramString)
  {
    this.titleId = paramString;
  }
  
  public final void setXblDeviceId(String paramString)
  {
    this.xblDeviceId = paramString;
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
    if ((!bool) || (this.sti != Schema.sti_metadata.getDefault_value().getString_value()))
    {
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_STRING, 10, Schema.sti_metadata);
      paramProtocolWriter.writeString(this.sti);
      paramProtocolWriter.writeFieldEnd();
      if ((bool) && (this.eventSequence == Schema.eventSequence_metadata.getDefault_value().getString_value())) {
        break label527;
      }
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_STRING, 20, Schema.eventSequence_metadata);
      paramProtocolWriter.writeString(this.eventSequence);
      paramProtocolWriter.writeFieldEnd();
      label110:
      if ((bool) && (this.issueTimestamp == Schema.issueTimestamp_metadata.getDefault_value().getString_value())) {
        break label542;
      }
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_STRING, 30, Schema.issueTimestamp_metadata);
      paramProtocolWriter.writeString(this.issueTimestamp);
      paramProtocolWriter.writeFieldEnd();
      label154:
      if ((bool) && (this.expiryTimestamp == Schema.expiryTimestamp_metadata.getDefault_value().getString_value())) {
        break label557;
      }
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_STRING, 40, Schema.expiryTimestamp_metadata);
      paramProtocolWriter.writeString(this.expiryTimestamp);
      paramProtocolWriter.writeFieldEnd();
      label198:
      if ((bool) && (this.sandboxId == Schema.sandboxId_metadata.getDefault_value().getString_value())) {
        break label572;
      }
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_STRING, 50, Schema.sandboxId_metadata);
      paramProtocolWriter.writeString(this.sandboxId);
      paramProtocolWriter.writeFieldEnd();
      label242:
      if ((bool) && (this.deviceType == Schema.deviceType_metadata.getDefault_value().getString_value())) {
        break label587;
      }
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_STRING, 60, Schema.deviceType_metadata);
      paramProtocolWriter.writeString(this.deviceType);
      paramProtocolWriter.writeFieldEnd();
      label286:
      if ((bool) && (this.xblDeviceId == Schema.xblDeviceId_metadata.getDefault_value().getString_value())) {
        break label602;
      }
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_STRING, 70, Schema.xblDeviceId_metadata);
      paramProtocolWriter.writeString(this.xblDeviceId);
      paramProtocolWriter.writeFieldEnd();
      label330:
      if ((bool) && (this.signedInUsers == Schema.signedInUsers_metadata.getDefault_value().getString_value())) {
        break label617;
      }
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_STRING, 80, Schema.signedInUsers_metadata);
      paramProtocolWriter.writeString(this.signedInUsers);
      paramProtocolWriter.writeFieldEnd();
      label374:
      if ((bool) && (this.isDevelopmentAccount == Schema.isDevelopmentAccount_metadata.getDefault_value().getString_value())) {
        break label632;
      }
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_STRING, 90, Schema.isDevelopmentAccount_metadata);
      paramProtocolWriter.writeString(this.isDevelopmentAccount);
      paramProtocolWriter.writeFieldEnd();
      label418:
      if ((bool) && (this.isTestAccount == Schema.isTestAccount_metadata.getDefault_value().getString_value())) {
        break label647;
      }
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_STRING, 100, Schema.isTestAccount_metadata);
      paramProtocolWriter.writeString(this.isTestAccount);
      paramProtocolWriter.writeFieldEnd();
      label462:
      if ((bool) && (this.titleId == Schema.titleId_metadata.getDefault_value().getString_value())) {
        break label662;
      }
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_STRING, 110, Schema.titleId_metadata);
      paramProtocolWriter.writeString(this.titleId);
      paramProtocolWriter.writeFieldEnd();
    }
    for (;;)
    {
      paramProtocolWriter.writeStructEnd(paramBoolean);
      return;
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_STRING, 10, Schema.sti_metadata);
      break;
      label527:
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_STRING, 20, Schema.eventSequence_metadata);
      break label110;
      label542:
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_STRING, 30, Schema.issueTimestamp_metadata);
      break label154;
      label557:
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_STRING, 40, Schema.expiryTimestamp_metadata);
      break label198;
      label572:
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_STRING, 50, Schema.sandboxId_metadata);
      break label242;
      label587:
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_STRING, 60, Schema.deviceType_metadata);
      break label286;
      label602:
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_STRING, 70, Schema.xblDeviceId_metadata);
      break label330;
      label617:
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_STRING, 80, Schema.signedInUsers_metadata);
      break label374;
      label632:
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_STRING, 90, Schema.isDevelopmentAccount_metadata);
      break label418;
      label647:
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_STRING, 100, Schema.isTestAccount_metadata);
      break label462;
      label662:
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_STRING, 110, Schema.titleId_metadata);
    }
  }
  
  public static class Schema
  {
    private static final Metadata deviceType_metadata;
    private static final Metadata eventSequence_metadata;
    private static final Metadata expiryTimestamp_metadata;
    private static final Metadata isDevelopmentAccount_metadata;
    private static final Metadata isTestAccount_metadata;
    private static final Metadata issueTimestamp_metadata;
    public static final Metadata metadata = new Metadata();
    private static final Metadata sandboxId_metadata;
    public static final SchemaDef schemaDef;
    private static final Metadata signedInUsers_metadata;
    private static final Metadata sti_metadata;
    private static final Metadata titleId_metadata;
    private static final Metadata xblDeviceId_metadata;
    
    static
    {
      metadata.setName("xbox");
      metadata.setQualified_name("Microsoft.Telemetry.Extensions.xbox");
      metadata.getAttributes().put("Description", "Describes the XBox related fields and might be populated by the console.");
      sti_metadata = new Metadata();
      sti_metadata.setName("sti");
      sti_metadata.getAttributes().put("Description", "XBox supporting token index.");
      eventSequence_metadata = new Metadata();
      eventSequence_metadata.setName("eventSequence");
      eventSequence_metadata.getAttributes().put("Description", "XBox event sequence.");
      issueTimestamp_metadata = new Metadata();
      issueTimestamp_metadata.setName("issueTimestamp");
      issueTimestamp_metadata.getAttributes().put("Description", "Xbox token issue timestamp.");
      expiryTimestamp_metadata = new Metadata();
      expiryTimestamp_metadata.setName("expiryTimestamp");
      expiryTimestamp_metadata.getAttributes().put("Description", "XBox token expiry timestamp.");
      sandboxId_metadata = new Metadata();
      sandboxId_metadata.setName("sandboxId");
      sandboxId_metadata.getAttributes().put("Description", "Xbox sandboxId.");
      deviceType_metadata = new Metadata();
      deviceType_metadata.setName("deviceType");
      deviceType_metadata.getAttributes().put("Description", "XBox device type.");
      xblDeviceId_metadata = new Metadata();
      xblDeviceId_metadata.setName("xblDeviceId");
      xblDeviceId_metadata.getAttributes().put("Description", "Xbox live deviceId.");
      signedInUsers_metadata = new Metadata();
      signedInUsers_metadata.setName("signedInUsers");
      signedInUsers_metadata.getAttributes().put("Description", "XBox signed in Xuids.");
      isDevelopmentAccount_metadata = new Metadata();
      isDevelopmentAccount_metadata.setName("isDevelopmentAccount");
      isDevelopmentAccount_metadata.getAttributes().put("Description", "XBox is development account.");
      isTestAccount_metadata = new Metadata();
      isTestAccount_metadata.setName("isTestAccount");
      isTestAccount_metadata.getAttributes().put("Description", "XBox is test account.");
      titleId_metadata = new Metadata();
      titleId_metadata.setName("titleId");
      titleId_metadata.getAttributes().put("Description", "XBox titleId.");
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
      paramSchemaDef.setMetadata(sti_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_STRING);
      localStructDef.getFields().add(paramSchemaDef);
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)20);
      paramSchemaDef.setMetadata(eventSequence_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_STRING);
      localStructDef.getFields().add(paramSchemaDef);
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)30);
      paramSchemaDef.setMetadata(issueTimestamp_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_STRING);
      localStructDef.getFields().add(paramSchemaDef);
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)40);
      paramSchemaDef.setMetadata(expiryTimestamp_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_STRING);
      localStructDef.getFields().add(paramSchemaDef);
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)50);
      paramSchemaDef.setMetadata(sandboxId_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_STRING);
      localStructDef.getFields().add(paramSchemaDef);
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)60);
      paramSchemaDef.setMetadata(deviceType_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_STRING);
      localStructDef.getFields().add(paramSchemaDef);
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)70);
      paramSchemaDef.setMetadata(xblDeviceId_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_STRING);
      localStructDef.getFields().add(paramSchemaDef);
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)80);
      paramSchemaDef.setMetadata(signedInUsers_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_STRING);
      localStructDef.getFields().add(paramSchemaDef);
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)90);
      paramSchemaDef.setMetadata(isDevelopmentAccount_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_STRING);
      localStructDef.getFields().add(paramSchemaDef);
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)100);
      paramSchemaDef.setMetadata(isTestAccount_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_STRING);
      localStructDef.getFields().add(paramSchemaDef);
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)110);
      paramSchemaDef.setMetadata(titleId_metadata);
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


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\MCPE.jar!\Microsoft\Telemetry\Extensions\xbox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */