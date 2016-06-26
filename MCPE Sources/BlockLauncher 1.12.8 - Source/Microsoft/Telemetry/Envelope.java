package Microsoft.Telemetry;

import com.microsoft.bond.BondDataType;
import com.microsoft.bond.BondMirror;
import com.microsoft.bond.BondSerializable;
import com.microsoft.bond.Bonded;
import com.microsoft.bond.FieldDef;
import com.microsoft.bond.Metadata;
import com.microsoft.bond.Modifier;
import com.microsoft.bond.ProtocolCapability;
import com.microsoft.bond.ProtocolReader;
import com.microsoft.bond.ProtocolReader.FieldTag;
import com.microsoft.bond.ProtocolReader.MapTag;
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
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class Envelope
  implements BondSerializable, BondMirror
{
  private String appId;
  private String appVer;
  private String cV;
  private Bonded<Base> data;
  private String epoch;
  private HashMap<String, Bonded<Extension>> ext;
  private long flags;
  private String iKey;
  private String name;
  private String os;
  private String osVer;
  private double popSample;
  private long seqNum;
  private HashMap<String, String> tags;
  private String time;
  private String ver;
  
  public Envelope()
  {
    reset();
  }
  
  public static SchemaDef getRuntimeSchema()
  {
    return Schema.schemaDef;
  }
  
  private void readFieldImpl_ext(ProtocolReader paramProtocolReader, BondDataType paramBondDataType)
    throws IOException
  {
    ReadHelper.validateType(paramBondDataType, BondDataType.BT_MAP);
    paramBondDataType = paramProtocolReader.readMapContainerBegin();
    ReadHelper.validateType(paramBondDataType.valueType, BondDataType.BT_STRUCT);
    int i = 0;
    while (i < paramBondDataType.size)
    {
      Bonded localBonded = new Bonded();
      String str = ReadHelper.readString(paramProtocolReader, paramBondDataType.keyType);
      localBonded.readNested(paramProtocolReader);
      this.ext.put(str, localBonded);
      i += 1;
    }
    paramProtocolReader.readContainerEnd();
  }
  
  private void readFieldImpl_tags(ProtocolReader paramProtocolReader, BondDataType paramBondDataType)
    throws IOException
  {
    ReadHelper.validateType(paramBondDataType, BondDataType.BT_MAP);
    paramBondDataType = paramProtocolReader.readMapContainerBegin();
    int i = 0;
    while (i < paramBondDataType.size)
    {
      String str1 = ReadHelper.readString(paramProtocolReader, paramBondDataType.keyType);
      String str2 = ReadHelper.readString(paramProtocolReader, paramBondDataType.valueType);
      this.tags.put(str1, str2);
      i += 1;
    }
    paramProtocolReader.readContainerEnd();
  }
  
  public BondSerializable clone()
  {
    return null;
  }
  
  public BondMirror createInstance(StructDef paramStructDef)
  {
    if (Extension.Schema.metadata == paramStructDef.getMetadata()) {
      return new Extension();
    }
    if (Base.Schema.metadata == paramStructDef.getMetadata()) {
      return new Base();
    }
    return null;
  }
  
  public final String getAppId()
  {
    return this.appId;
  }
  
  public final String getAppVer()
  {
    return this.appVer;
  }
  
  public final String getCV()
  {
    return this.cV;
  }
  
  public final Bonded<Base> getData()
  {
    return this.data;
  }
  
  public final String getEpoch()
  {
    return this.epoch;
  }
  
  public final HashMap<String, Bonded<Extension>> getExt()
  {
    return this.ext;
  }
  
  public Object getField(FieldDef paramFieldDef)
  {
    switch (paramFieldDef.getId())
    {
    default: 
      return null;
    case 10: 
      return this.ver;
    case 20: 
      return this.name;
    case 30: 
      return this.time;
    case 40: 
      return Double.valueOf(this.popSample);
    case 50: 
      return this.epoch;
    case 60: 
      return Long.valueOf(this.seqNum);
    case 70: 
      return this.iKey;
    case 80: 
      return Long.valueOf(this.flags);
    case 90: 
      return this.os;
    case 100: 
      return this.osVer;
    case 110: 
      return this.appId;
    case 120: 
      return this.appVer;
    case 130: 
      return this.cV;
    case 500: 
      return this.tags;
    case 510: 
      return this.ext;
    }
    return this.data;
  }
  
  public final long getFlags()
  {
    return this.flags;
  }
  
  public final String getIKey()
  {
    return this.iKey;
  }
  
  public final String getName()
  {
    return this.name;
  }
  
  public final String getOs()
  {
    return this.os;
  }
  
  public final String getOsVer()
  {
    return this.osVer;
  }
  
  public final double getPopSample()
  {
    return this.popSample;
  }
  
  public SchemaDef getSchema()
  {
    return getRuntimeSchema();
  }
  
  public final long getSeqNum()
  {
    return this.seqNum;
  }
  
  public final HashMap<String, String> getTags()
  {
    return this.tags;
  }
  
  public final String getTime()
  {
    return this.time;
  }
  
  public final String getVer()
  {
    return this.ver;
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
      paramObject = (Envelope)paramObject;
    } while ((!memberwiseCompareQuick((Envelope)paramObject)) || (!memberwiseCompareDeep((Envelope)paramObject)));
    return true;
  }
  
  protected boolean memberwiseCompareDeep(Envelope paramEnvelope)
  {
    int i;
    label9:
    label20:
    label22:
    label33:
    label35:
    label46:
    label48:
    label59:
    label61:
    label72:
    label74:
    label85:
    label87:
    label98:
    label100:
    label111:
    label113:
    label124:
    int j;
    label126:
    Iterator localIterator;
    label169:
    Map.Entry localEntry;
    Object localObject1;
    Object localObject2;
    if (this.ver == null)
    {
      i = 1;
      if (i == 0) {
        break label513;
      }
      if (this.name != null) {
        break label499;
      }
      i = 1;
      if (i == 0) {
        break label532;
      }
      if (this.time != null) {
        break label518;
      }
      i = 1;
      if (i == 0) {
        break label551;
      }
      if (this.epoch != null) {
        break label537;
      }
      i = 1;
      if (i == 0) {
        break label570;
      }
      if (this.iKey != null) {
        break label556;
      }
      i = 1;
      if (i == 0) {
        break label589;
      }
      if (this.os != null) {
        break label575;
      }
      i = 1;
      if (i == 0) {
        break label608;
      }
      if (this.osVer != null) {
        break label594;
      }
      i = 1;
      if (i == 0) {
        break label627;
      }
      if (this.appId != null) {
        break label613;
      }
      i = 1;
      if (i == 0) {
        break label646;
      }
      if (this.appVer != null) {
        break label632;
      }
      i = 1;
      if (i == 0) {
        break label665;
      }
      if (this.cV != null) {
        break label651;
      }
      j = 1;
      i = j;
      if (j != 0)
      {
        i = j;
        if (this.tags != null)
        {
          i = j;
          if (this.tags.size() != 0)
          {
            localIterator = this.tags.entrySet().iterator();
            i = j;
            if (!localIterator.hasNext()) {
              break label785;
            }
            localEntry = (Map.Entry)localIterator.next();
            localObject1 = (String)localEntry.getValue();
            localObject2 = (String)paramEnvelope.tags.get(localEntry.getKey());
            if ((i == 0) || (!paramEnvelope.tags.containsKey(localEntry.getKey()))) {
              break label670;
            }
            j = 1;
            label245:
            i = j;
            if (j != 0)
            {
              if (j == 0) {
                break label685;
              }
              if (localObject1 != null) {
                break label675;
              }
              i = 1;
              label262:
              if (localObject2 != null) {
                break label680;
              }
              j = 1;
              label269:
              if (i != j) {
                break label685;
              }
              i = 1;
              label276:
              if (i == 0) {
                break label703;
              }
              if (localObject1 != null) {
                break label690;
              }
              label285:
              i = 1;
              label287:
              if (i == 0) {
                break label718;
              }
              if (localObject1 != null) {
                break label708;
              }
              label296:
              i = 1;
            }
            label298:
            if (i != 0) {
              break label782;
            }
          }
        }
      }
    }
    label343:
    label419:
    label436:
    label443:
    label450:
    label459:
    label461:
    label499:
    label513:
    label518:
    label532:
    label537:
    label551:
    label556:
    label570:
    label575:
    label589:
    label594:
    label608:
    label613:
    label627:
    label632:
    label646:
    label651:
    label665:
    label670:
    label675:
    label680:
    label685:
    label690:
    label703:
    label708:
    label718:
    label723:
    label728:
    label733:
    label738:
    label743:
    label753:
    label774:
    label777:
    label782:
    label785:
    for (;;)
    {
      j = i;
      if (i != 0)
      {
        j = i;
        if (this.ext != null)
        {
          j = i;
          if (this.ext.size() != 0)
          {
            localIterator = this.ext.entrySet().iterator();
            if (!localIterator.hasNext()) {
              break label777;
            }
            localEntry = (Map.Entry)localIterator.next();
            localObject1 = (Bonded)localEntry.getValue();
            localObject2 = (Bonded)paramEnvelope.ext.get(localEntry.getKey());
            if ((i == 0) || (!paramEnvelope.ext.containsKey(localEntry.getKey()))) {
              break label723;
            }
            j = 1;
            i = j;
            if (j != 0)
            {
              if (j == 0) {
                break label738;
              }
              if (localObject1 != null) {
                break label728;
              }
              i = 1;
              if (localObject2 != null) {
                break label733;
              }
              j = 1;
              if (i != j) {
                break label738;
              }
              i = 1;
              if (i == 0) {
                break label753;
              }
              if (localObject1 != null) {
                break label743;
              }
              i = 1;
            }
            if (i != 0) {
              break label774;
            }
          }
        }
      }
      for (j = i;; j = i)
      {
        if (j != 0)
        {
          if (this.data == null) {}
          while (this.data.memberwiseCompare(paramEnvelope.data))
          {
            return true;
            if (this.ver.equals(paramEnvelope.ver)) {
              break;
            }
            i = 0;
            break label9;
            if (this.name.equals(paramEnvelope.name)) {
              break label20;
            }
            i = 0;
            break label22;
            if (this.time.equals(paramEnvelope.time)) {
              break label33;
            }
            i = 0;
            break label35;
            if (this.epoch.equals(paramEnvelope.epoch)) {
              break label46;
            }
            i = 0;
            break label48;
            if (this.iKey.equals(paramEnvelope.iKey)) {
              break label59;
            }
            i = 0;
            break label61;
            if (this.os.equals(paramEnvelope.os)) {
              break label72;
            }
            i = 0;
            break label74;
            if (this.osVer.equals(paramEnvelope.osVer)) {
              break label85;
            }
            i = 0;
            break label87;
            if (this.appId.equals(paramEnvelope.appId)) {
              break label98;
            }
            i = 0;
            break label100;
            if (this.appVer.equals(paramEnvelope.appVer)) {
              break label111;
            }
            i = 0;
            break label113;
            if (this.cV.equals(paramEnvelope.cV)) {
              break label124;
            }
            j = 0;
            break label126;
            j = 0;
            break label245;
            i = 0;
            break label262;
            j = 0;
            break label269;
            i = 0;
            break label276;
            if (((String)localObject1).length() == ((String)localObject2).length()) {
              break label285;
            }
            i = 0;
            break label287;
            if (((String)localObject1).equals(localObject2)) {
              break label296;
            }
            i = 0;
            break label298;
            j = 0;
            break label419;
            i = 0;
            break label436;
            j = 0;
            break label443;
            i = 0;
            break label450;
            if (((Bonded)localObject1).memberwiseCompare(localObject2)) {
              break label459;
            }
            i = 0;
            break label461;
          }
        }
        return false;
        break label343;
      }
      break label169;
    }
  }
  
  protected boolean memberwiseCompareQuick(Envelope paramEnvelope)
  {
    int i;
    int j;
    if (this.ver == null)
    {
      i = 1;
      if (paramEnvelope.ver != null) {
        break label567;
      }
      j = 1;
      label18:
      if (i != j) {
        break label572;
      }
      i = 1;
      label25:
      if (i == 0) {
        break label594;
      }
      if (this.ver != null) {
        break label577;
      }
      label36:
      i = 1;
      label38:
      if (i == 0) {
        break label609;
      }
      if (this.name != null) {
        break label599;
      }
      i = 1;
      label51:
      if (paramEnvelope.name != null) {
        break label604;
      }
      j = 1;
      label60:
      if (i != j) {
        break label609;
      }
      i = 1;
      label67:
      if (i == 0) {
        break label631;
      }
      if (this.name != null) {
        break label614;
      }
      label78:
      i = 1;
      label80:
      if (i == 0) {
        break label646;
      }
      if (this.time != null) {
        break label636;
      }
      i = 1;
      label93:
      if (paramEnvelope.time != null) {
        break label641;
      }
      j = 1;
      label102:
      if (i != j) {
        break label646;
      }
      i = 1;
      label109:
      if (i == 0) {
        break label668;
      }
      if (this.time != null) {
        break label651;
      }
      label120:
      i = 1;
      label122:
      if (i == 0) {
        break label685;
      }
      if (!Double.isNaN(this.popSample)) {
        break label673;
      }
      if (!Double.isNaN(paramEnvelope.popSample)) {
        break label685;
      }
      label146:
      i = 1;
      label148:
      if (i == 0) {
        break label700;
      }
      if (this.epoch != null) {
        break label690;
      }
      i = 1;
      label161:
      if (paramEnvelope.epoch != null) {
        break label695;
      }
      j = 1;
      label170:
      if (i != j) {
        break label700;
      }
      i = 1;
      label177:
      if (i == 0) {
        break label722;
      }
      if (this.epoch != null) {
        break label705;
      }
      label188:
      i = 1;
      label190:
      if ((i == 0) || (this.seqNum != paramEnvelope.seqNum)) {
        break label727;
      }
      i = 1;
      label208:
      if (i == 0) {
        break label742;
      }
      if (this.iKey != null) {
        break label732;
      }
      i = 1;
      label221:
      if (paramEnvelope.iKey != null) {
        break label737;
      }
      j = 1;
      label230:
      if (i != j) {
        break label742;
      }
      i = 1;
      label237:
      if (i == 0) {
        break label764;
      }
      if (this.iKey != null) {
        break label747;
      }
      label248:
      i = 1;
      label250:
      if ((i == 0) || (this.flags != paramEnvelope.flags)) {
        break label769;
      }
      i = 1;
      label268:
      if (i == 0) {
        break label784;
      }
      if (this.os != null) {
        break label774;
      }
      i = 1;
      label281:
      if (paramEnvelope.os != null) {
        break label779;
      }
      j = 1;
      label290:
      if (i != j) {
        break label784;
      }
      i = 1;
      label297:
      if (i == 0) {
        break label806;
      }
      if (this.os != null) {
        break label789;
      }
      label308:
      i = 1;
      label310:
      if (i == 0) {
        break label821;
      }
      if (this.osVer != null) {
        break label811;
      }
      i = 1;
      label323:
      if (paramEnvelope.osVer != null) {
        break label816;
      }
      j = 1;
      label332:
      if (i != j) {
        break label821;
      }
      i = 1;
      label339:
      if (i == 0) {
        break label843;
      }
      if (this.osVer != null) {
        break label826;
      }
      label350:
      i = 1;
      label352:
      if (i == 0) {
        break label858;
      }
      if (this.appId != null) {
        break label848;
      }
      i = 1;
      label365:
      if (paramEnvelope.appId != null) {
        break label853;
      }
      j = 1;
      label374:
      if (i != j) {
        break label858;
      }
      i = 1;
      label381:
      if (i == 0) {
        break label880;
      }
      if (this.appId != null) {
        break label863;
      }
      label392:
      i = 1;
      label394:
      if (i == 0) {
        break label895;
      }
      if (this.appVer != null) {
        break label885;
      }
      i = 1;
      label407:
      if (paramEnvelope.appVer != null) {
        break label890;
      }
      j = 1;
      label416:
      if (i != j) {
        break label895;
      }
      i = 1;
      label423:
      if (i == 0) {
        break label917;
      }
      if (this.appVer != null) {
        break label900;
      }
      label434:
      i = 1;
      label436:
      if (i == 0) {
        break label932;
      }
      if (this.cV != null) {
        break label922;
      }
      i = 1;
      label449:
      if (paramEnvelope.cV != null) {
        break label927;
      }
      j = 1;
      label458:
      if (i != j) {
        break label932;
      }
      i = 1;
      label465:
      if (i == 0) {
        break label954;
      }
      if (this.cV != null) {
        break label937;
      }
      label476:
      i = 1;
      label478:
      if (i == 0) {
        break label969;
      }
      if (this.tags != null) {
        break label959;
      }
      i = 1;
      label491:
      if (paramEnvelope.tags != null) {
        break label964;
      }
      j = 1;
      label500:
      if (i != j) {
        break label969;
      }
      i = 1;
      label507:
      if (i == 0) {
        break label991;
      }
      if (this.tags != null) {
        break label974;
      }
      label518:
      i = 1;
      label520:
      if (i == 0) {
        break label1006;
      }
      if (this.ext != null) {
        break label996;
      }
      i = 1;
      label533:
      if (paramEnvelope.ext != null) {
        break label1001;
      }
      j = 1;
      label542:
      if (i != j) {
        break label1006;
      }
      i = 1;
      label549:
      if (i == 0) {
        break label1028;
      }
      if (this.ext != null) {
        break label1011;
      }
    }
    label567:
    label572:
    label577:
    label594:
    label599:
    label604:
    label609:
    label614:
    label631:
    label636:
    label641:
    label646:
    label651:
    label668:
    label673:
    label685:
    label690:
    label695:
    label700:
    label705:
    label722:
    label727:
    label732:
    label737:
    label742:
    label747:
    label764:
    label769:
    label774:
    label779:
    label784:
    label789:
    label806:
    label811:
    label816:
    label821:
    label826:
    label843:
    label848:
    label853:
    label858:
    label863:
    label880:
    label885:
    label890:
    label895:
    label900:
    label917:
    label922:
    label927:
    label932:
    label937:
    label954:
    label959:
    label964:
    label969:
    label974:
    label991:
    label996:
    label1001:
    label1006:
    label1011:
    while (this.ext.size() == paramEnvelope.ext.size())
    {
      return true;
      i = 0;
      break;
      j = 0;
      break label18;
      i = 0;
      break label25;
      if (this.ver.length() == paramEnvelope.ver.length()) {
        break label36;
      }
      i = 0;
      break label38;
      i = 0;
      break label51;
      j = 0;
      break label60;
      i = 0;
      break label67;
      if (this.name.length() == paramEnvelope.name.length()) {
        break label78;
      }
      i = 0;
      break label80;
      i = 0;
      break label93;
      j = 0;
      break label102;
      i = 0;
      break label109;
      if (this.time.length() == paramEnvelope.time.length()) {
        break label120;
      }
      i = 0;
      break label122;
      if (this.popSample == paramEnvelope.popSample) {
        break label146;
      }
      i = 0;
      break label148;
      i = 0;
      break label161;
      j = 0;
      break label170;
      i = 0;
      break label177;
      if (this.epoch.length() == paramEnvelope.epoch.length()) {
        break label188;
      }
      i = 0;
      break label190;
      i = 0;
      break label208;
      i = 0;
      break label221;
      j = 0;
      break label230;
      i = 0;
      break label237;
      if (this.iKey.length() == paramEnvelope.iKey.length()) {
        break label248;
      }
      i = 0;
      break label250;
      i = 0;
      break label268;
      i = 0;
      break label281;
      j = 0;
      break label290;
      i = 0;
      break label297;
      if (this.os.length() == paramEnvelope.os.length()) {
        break label308;
      }
      i = 0;
      break label310;
      i = 0;
      break label323;
      j = 0;
      break label332;
      i = 0;
      break label339;
      if (this.osVer.length() == paramEnvelope.osVer.length()) {
        break label350;
      }
      i = 0;
      break label352;
      i = 0;
      break label365;
      j = 0;
      break label374;
      i = 0;
      break label381;
      if (this.appId.length() == paramEnvelope.appId.length()) {
        break label392;
      }
      i = 0;
      break label394;
      i = 0;
      break label407;
      j = 0;
      break label416;
      i = 0;
      break label423;
      if (this.appVer.length() == paramEnvelope.appVer.length()) {
        break label434;
      }
      i = 0;
      break label436;
      i = 0;
      break label449;
      j = 0;
      break label458;
      i = 0;
      break label465;
      if (this.cV.length() == paramEnvelope.cV.length()) {
        break label476;
      }
      i = 0;
      break label478;
      i = 0;
      break label491;
      j = 0;
      break label500;
      i = 0;
      break label507;
      if (this.tags.size() == paramEnvelope.tags.size()) {
        break label518;
      }
      i = 0;
      break label520;
      i = 0;
      break label533;
      j = 0;
      break label542;
      i = 0;
      break label549;
    }
    label1028:
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
    paramProtocolReader.readStructBegin(paramBoolean);
    ProtocolReader.FieldTag localFieldTag = paramProtocolReader.readFieldBegin();
    if ((localFieldTag.type == BondDataType.BT_STOP) || (localFieldTag.type == BondDataType.BT_STOP_BASE))
    {
      if (localFieldTag.type == BondDataType.BT_STOP_BASE) {}
      for (paramBoolean = true;; paramBoolean = false)
      {
        paramProtocolReader.readStructEnd();
        return paramBoolean;
      }
    }
    switch (localFieldTag.id)
    {
    default: 
      paramProtocolReader.skip(localFieldTag.type);
    }
    for (;;)
    {
      paramProtocolReader.readFieldEnd();
      break;
      this.ver = ReadHelper.readString(paramProtocolReader, localFieldTag.type);
      continue;
      this.name = ReadHelper.readString(paramProtocolReader, localFieldTag.type);
      continue;
      this.time = ReadHelper.readString(paramProtocolReader, localFieldTag.type);
      continue;
      this.popSample = ReadHelper.readDouble(paramProtocolReader, localFieldTag.type);
      continue;
      this.epoch = ReadHelper.readString(paramProtocolReader, localFieldTag.type);
      continue;
      this.seqNum = ReadHelper.readInt64(paramProtocolReader, localFieldTag.type);
      continue;
      this.iKey = ReadHelper.readString(paramProtocolReader, localFieldTag.type);
      continue;
      this.flags = ReadHelper.readInt64(paramProtocolReader, localFieldTag.type);
      continue;
      this.os = ReadHelper.readString(paramProtocolReader, localFieldTag.type);
      continue;
      this.osVer = ReadHelper.readString(paramProtocolReader, localFieldTag.type);
      continue;
      this.appId = ReadHelper.readString(paramProtocolReader, localFieldTag.type);
      continue;
      this.appVer = ReadHelper.readString(paramProtocolReader, localFieldTag.type);
      continue;
      this.cV = ReadHelper.readString(paramProtocolReader, localFieldTag.type);
      continue;
      readFieldImpl_tags(paramProtocolReader, localFieldTag.type);
      continue;
      readFieldImpl_ext(paramProtocolReader, localFieldTag.type);
      continue;
      ReadHelper.validateType(localFieldTag.type, BondDataType.BT_STRUCT);
      this.data.readNested(paramProtocolReader);
    }
  }
  
  protected void readUntagged(ProtocolReader paramProtocolReader, boolean paramBoolean)
    throws IOException
  {
    boolean bool = paramProtocolReader.hasCapability(ProtocolCapability.CAN_OMIT_FIELDS);
    paramProtocolReader.readStructBegin(paramBoolean);
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.ver = paramProtocolReader.readString();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.name = paramProtocolReader.readString();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.time = paramProtocolReader.readString();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.popSample = paramProtocolReader.readDouble();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.epoch = paramProtocolReader.readString();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.seqNum = paramProtocolReader.readInt64();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.iKey = paramProtocolReader.readString();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.flags = paramProtocolReader.readInt64();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.os = paramProtocolReader.readString();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.osVer = paramProtocolReader.readString();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.appId = paramProtocolReader.readString();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.appVer = paramProtocolReader.readString();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.cV = paramProtocolReader.readString();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      readFieldImpl_tags(paramProtocolReader, BondDataType.BT_MAP);
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      readFieldImpl_ext(paramProtocolReader, BondDataType.BT_MAP);
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.data.read(paramProtocolReader);
    }
    paramProtocolReader.readStructEnd();
  }
  
  public void reset()
  {
    reset("Envelope", "Microsoft.Telemetry.Envelope");
  }
  
  protected void reset(String paramString1, String paramString2)
  {
    this.ver = "";
    this.name = "";
    this.time = "";
    this.popSample = 100.0D;
    this.epoch = "";
    this.seqNum = 0L;
    this.iKey = "";
    this.flags = 0L;
    this.os = "";
    this.osVer = "";
    this.appId = "";
    this.appVer = "";
    this.cV = "";
    if (this.tags == null)
    {
      this.tags = new HashMap();
      if (this.ext != null) {
        break label145;
      }
      this.ext = new HashMap();
    }
    for (;;)
    {
      this.data = new Bonded();
      return;
      this.tags.clear();
      break;
      label145:
      this.ext.clear();
    }
  }
  
  public final void setAppId(String paramString)
  {
    this.appId = paramString;
  }
  
  public final void setAppVer(String paramString)
  {
    this.appVer = paramString;
  }
  
  public final void setCV(String paramString)
  {
    this.cV = paramString;
  }
  
  public final void setData(Bonded<Base> paramBonded)
  {
    this.data = paramBonded;
  }
  
  public final void setEpoch(String paramString)
  {
    this.epoch = paramString;
  }
  
  public final void setExt(HashMap<String, Bonded<Extension>> paramHashMap)
  {
    this.ext = paramHashMap;
  }
  
  public void setField(FieldDef paramFieldDef, Object paramObject)
  {
    switch (paramFieldDef.getId())
    {
    default: 
      return;
    case 10: 
      this.ver = ((String)paramObject);
      return;
    case 20: 
      this.name = ((String)paramObject);
      return;
    case 30: 
      this.time = ((String)paramObject);
      return;
    case 40: 
      this.popSample = ((Double)paramObject).doubleValue();
      return;
    case 50: 
      this.epoch = ((String)paramObject);
      return;
    case 60: 
      this.seqNum = ((Long)paramObject).longValue();
      return;
    case 70: 
      this.iKey = ((String)paramObject);
      return;
    case 80: 
      this.flags = ((Long)paramObject).longValue();
      return;
    case 90: 
      this.os = ((String)paramObject);
      return;
    case 100: 
      this.osVer = ((String)paramObject);
      return;
    case 110: 
      this.appId = ((String)paramObject);
      return;
    case 120: 
      this.appVer = ((String)paramObject);
      return;
    case 130: 
      this.cV = ((String)paramObject);
      return;
    case 500: 
      this.tags = ((HashMap)paramObject);
      return;
    case 510: 
      this.ext = ((HashMap)paramObject);
      return;
    }
    this.data = ((Bonded)paramObject);
  }
  
  public final void setFlags(long paramLong)
  {
    this.flags = paramLong;
  }
  
  public final void setIKey(String paramString)
  {
    this.iKey = paramString;
  }
  
  public final void setName(String paramString)
  {
    this.name = paramString;
  }
  
  public final void setOs(String paramString)
  {
    this.os = paramString;
  }
  
  public final void setOsVer(String paramString)
  {
    this.osVer = paramString;
  }
  
  public final void setPopSample(double paramDouble)
  {
    this.popSample = paramDouble;
  }
  
  public final void setSeqNum(long paramLong)
  {
    this.seqNum = paramLong;
  }
  
  public final void setTags(HashMap<String, String> paramHashMap)
  {
    this.tags = paramHashMap;
  }
  
  public final void setTime(String paramString)
  {
    this.time = paramString;
  }
  
  public final void setVer(String paramString)
  {
    this.ver = paramString;
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
    paramProtocolWriter.writeFieldBegin(BondDataType.BT_STRING, 10, Schema.ver_metadata);
    paramProtocolWriter.writeString(this.ver);
    paramProtocolWriter.writeFieldEnd();
    paramProtocolWriter.writeFieldBegin(BondDataType.BT_STRING, 20, Schema.name_metadata);
    paramProtocolWriter.writeString(this.name);
    paramProtocolWriter.writeFieldEnd();
    paramProtocolWriter.writeFieldBegin(BondDataType.BT_STRING, 30, Schema.time_metadata);
    paramProtocolWriter.writeString(this.time);
    paramProtocolWriter.writeFieldEnd();
    if ((!bool) || (this.popSample != Schema.popSample_metadata.getDefault_value().getDouble_value()))
    {
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_DOUBLE, 40, Schema.popSample_metadata);
      paramProtocolWriter.writeDouble(this.popSample);
      paramProtocolWriter.writeFieldEnd();
      if ((bool) && (this.epoch == Schema.epoch_metadata.getDefault_value().getString_value())) {
        break label672;
      }
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_STRING, 50, Schema.epoch_metadata);
      paramProtocolWriter.writeString(this.epoch);
      paramProtocolWriter.writeFieldEnd();
      label180:
      if ((bool) && (this.seqNum == Schema.seqNum_metadata.getDefault_value().getInt_value())) {
        break label687;
      }
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_INT64, 60, Schema.seqNum_metadata);
      paramProtocolWriter.writeInt64(this.seqNum);
      paramProtocolWriter.writeFieldEnd();
      label226:
      if ((bool) && (this.iKey == Schema.iKey_metadata.getDefault_value().getString_value())) {
        break label702;
      }
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_STRING, 70, Schema.iKey_metadata);
      paramProtocolWriter.writeString(this.iKey);
      paramProtocolWriter.writeFieldEnd();
      label271:
      if ((bool) && (this.flags == Schema.flags_metadata.getDefault_value().getInt_value())) {
        break label717;
      }
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_INT64, 80, Schema.flags_metadata);
      paramProtocolWriter.writeInt64(this.flags);
      paramProtocolWriter.writeFieldEnd();
      label317:
      if ((bool) && (this.os == Schema.os_metadata.getDefault_value().getString_value())) {
        break label732;
      }
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_STRING, 90, Schema.os_metadata);
      paramProtocolWriter.writeString(this.os);
      paramProtocolWriter.writeFieldEnd();
      label362:
      if ((bool) && (this.osVer == Schema.osVer_metadata.getDefault_value().getString_value())) {
        break label747;
      }
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_STRING, 100, Schema.osVer_metadata);
      paramProtocolWriter.writeString(this.osVer);
      paramProtocolWriter.writeFieldEnd();
      label407:
      if ((bool) && (this.appId == Schema.appId_metadata.getDefault_value().getString_value())) {
        break label762;
      }
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_STRING, 110, Schema.appId_metadata);
      paramProtocolWriter.writeString(this.appId);
      paramProtocolWriter.writeFieldEnd();
      label452:
      if ((bool) && (this.appVer == Schema.appVer_metadata.getDefault_value().getString_value())) {
        break label777;
      }
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_STRING, 120, Schema.appVer_metadata);
      paramProtocolWriter.writeString(this.appVer);
      paramProtocolWriter.writeFieldEnd();
      label497:
      if ((bool) && (this.cV == Schema.cV_metadata.getDefault_value().getString_value())) {
        break label792;
      }
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_STRING, 130, Schema.cV_metadata);
      paramProtocolWriter.writeString(this.cV);
      paramProtocolWriter.writeFieldEnd();
    }
    int i;
    Iterator localIterator;
    Map.Entry localEntry;
    for (;;)
    {
      i = this.tags.size();
      if ((bool) && (i == 0)) {
        break label931;
      }
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_MAP, 500, Schema.tags_metadata);
      paramProtocolWriter.writeContainerBegin(this.tags.size(), BondDataType.BT_STRING, BondDataType.BT_STRING);
      localIterator = this.tags.entrySet().iterator();
      while (localIterator.hasNext())
      {
        localEntry = (Map.Entry)localIterator.next();
        paramProtocolWriter.writeString((String)localEntry.getKey());
        paramProtocolWriter.writeString((String)localEntry.getValue());
      }
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_DOUBLE, 40, Schema.popSample_metadata);
      break;
      label672:
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_STRING, 50, Schema.epoch_metadata);
      break label180;
      label687:
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_INT64, 60, Schema.seqNum_metadata);
      break label226;
      label702:
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_STRING, 70, Schema.iKey_metadata);
      break label271;
      label717:
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_INT64, 80, Schema.flags_metadata);
      break label317;
      label732:
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_STRING, 90, Schema.os_metadata);
      break label362;
      label747:
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_STRING, 100, Schema.osVer_metadata);
      break label407;
      label762:
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_STRING, 110, Schema.appId_metadata);
      break label452;
      label777:
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_STRING, 120, Schema.appVer_metadata);
      break label497;
      label792:
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_STRING, 130, Schema.cV_metadata);
    }
    paramProtocolWriter.writeContainerEnd();
    paramProtocolWriter.writeFieldEnd();
    for (;;)
    {
      i = this.ext.size();
      if ((bool) && (i == 0)) {
        break label987;
      }
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_MAP, 510, Schema.ext_metadata);
      paramProtocolWriter.writeContainerBegin(this.ext.size(), BondDataType.BT_STRING, BondDataType.BT_STRUCT);
      localIterator = this.ext.entrySet().iterator();
      while (localIterator.hasNext())
      {
        localEntry = (Map.Entry)localIterator.next();
        paramProtocolWriter.writeString((String)localEntry.getKey());
        ((Bonded)localEntry.getValue()).writeNested(paramProtocolWriter, false);
      }
      label931:
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_MAP, 500, Schema.tags_metadata);
    }
    paramProtocolWriter.writeContainerEnd();
    paramProtocolWriter.writeFieldEnd();
    for (;;)
    {
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_STRUCT, 999, Schema.data_metadata);
      this.data.writeNested(paramProtocolWriter, false);
      paramProtocolWriter.writeFieldEnd();
      paramProtocolWriter.writeStructEnd(paramBoolean);
      return;
      label987:
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_MAP, 510, Schema.ext_metadata);
    }
  }
  
  public static class Schema
  {
    private static final Metadata appId_metadata;
    private static final Metadata appVer_metadata;
    private static final Metadata cV_metadata;
    private static final Metadata data_metadata;
    private static final Metadata epoch_metadata;
    private static final Metadata ext_metadata;
    private static final Metadata flags_metadata;
    private static final Metadata iKey_metadata;
    public static final Metadata metadata = new Metadata();
    private static final Metadata name_metadata;
    private static final Metadata osVer_metadata;
    private static final Metadata os_metadata;
    private static final Metadata popSample_metadata;
    public static final SchemaDef schemaDef;
    private static final Metadata seqNum_metadata;
    private static final Metadata tags_metadata;
    private static final Metadata time_metadata;
    private static final Metadata ver_metadata;
    
    static
    {
      metadata.setName("Envelope");
      metadata.setQualified_name("Microsoft.Telemetry.Envelope");
      metadata.getAttributes().put("Description", "System variables for a telemetry item (Part A)");
      ver_metadata = new Metadata();
      ver_metadata.setName("ver");
      ver_metadata.setModifier(Modifier.Required);
      ver_metadata.getAttributes().put("Name", "SchemaVersion");
      name_metadata = new Metadata();
      name_metadata.setName("name");
      name_metadata.setModifier(Modifier.Required);
      name_metadata.getAttributes().put("Name", "DataTypeName");
      time_metadata = new Metadata();
      time_metadata.setName("time");
      time_metadata.setModifier(Modifier.Required);
      time_metadata.getAttributes().put("Name", "DateTime");
      popSample_metadata = new Metadata();
      popSample_metadata.setName("popSample");
      popSample_metadata.getAttributes().put("Name", "SamplingRate");
      popSample_metadata.getDefault_value().setDouble_value(100.0D);
      epoch_metadata = new Metadata();
      epoch_metadata.setName("epoch");
      epoch_metadata.getAttributes().put("Name", "Epoch");
      seqNum_metadata = new Metadata();
      seqNum_metadata.setName("seqNum");
      seqNum_metadata.getAttributes().put("Name", "SequenceNumber");
      seqNum_metadata.getDefault_value().setInt_value(0L);
      iKey_metadata = new Metadata();
      iKey_metadata.setName("iKey");
      iKey_metadata.getAttributes().put("Name", "InstrumentationKey");
      flags_metadata = new Metadata();
      flags_metadata.setName("flags");
      flags_metadata.getAttributes().put("Name", "TelemetryProperties");
      flags_metadata.getDefault_value().setInt_value(0L);
      os_metadata = new Metadata();
      os_metadata.setName("os");
      os_metadata.getAttributes().put("Name", "OsPlatform");
      osVer_metadata = new Metadata();
      osVer_metadata.setName("osVer");
      osVer_metadata.getAttributes().put("Name", "OsVersion");
      appId_metadata = new Metadata();
      appId_metadata.setName("appId");
      appId_metadata.getAttributes().put("Name", "ApplicationId");
      appVer_metadata = new Metadata();
      appVer_metadata.setName("appVer");
      appVer_metadata.getAttributes().put("Name", "ApplicationVersion");
      cV_metadata = new Metadata();
      cV_metadata.setName("cV");
      cV_metadata.getAttributes().put("Name", "CorrelationVector");
      tags_metadata = new Metadata();
      tags_metadata.setName("tags");
      tags_metadata.getAttributes().put("Name", "Tags");
      ext_metadata = new Metadata();
      ext_metadata.setName("ext");
      ext_metadata.getAttributes().put("Name", "Extensions");
      data_metadata = new Metadata();
      data_metadata.setName("data");
      data_metadata.getAttributes().put("Name", "TelemetryData");
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
      FieldDef localFieldDef = new FieldDef();
      localFieldDef.setId((short)10);
      localFieldDef.setMetadata(ver_metadata);
      localFieldDef.getType().setId(BondDataType.BT_STRING);
      localStructDef.getFields().add(localFieldDef);
      localFieldDef = new FieldDef();
      localFieldDef.setId((short)20);
      localFieldDef.setMetadata(name_metadata);
      localFieldDef.getType().setId(BondDataType.BT_STRING);
      localStructDef.getFields().add(localFieldDef);
      localFieldDef = new FieldDef();
      localFieldDef.setId((short)30);
      localFieldDef.setMetadata(time_metadata);
      localFieldDef.getType().setId(BondDataType.BT_STRING);
      localStructDef.getFields().add(localFieldDef);
      localFieldDef = new FieldDef();
      localFieldDef.setId((short)40);
      localFieldDef.setMetadata(popSample_metadata);
      localFieldDef.getType().setId(BondDataType.BT_DOUBLE);
      localStructDef.getFields().add(localFieldDef);
      localFieldDef = new FieldDef();
      localFieldDef.setId((short)50);
      localFieldDef.setMetadata(epoch_metadata);
      localFieldDef.getType().setId(BondDataType.BT_STRING);
      localStructDef.getFields().add(localFieldDef);
      localFieldDef = new FieldDef();
      localFieldDef.setId((short)60);
      localFieldDef.setMetadata(seqNum_metadata);
      localFieldDef.getType().setId(BondDataType.BT_INT64);
      localStructDef.getFields().add(localFieldDef);
      localFieldDef = new FieldDef();
      localFieldDef.setId((short)70);
      localFieldDef.setMetadata(iKey_metadata);
      localFieldDef.getType().setId(BondDataType.BT_STRING);
      localStructDef.getFields().add(localFieldDef);
      localFieldDef = new FieldDef();
      localFieldDef.setId((short)80);
      localFieldDef.setMetadata(flags_metadata);
      localFieldDef.getType().setId(BondDataType.BT_INT64);
      localStructDef.getFields().add(localFieldDef);
      localFieldDef = new FieldDef();
      localFieldDef.setId((short)90);
      localFieldDef.setMetadata(os_metadata);
      localFieldDef.getType().setId(BondDataType.BT_STRING);
      localStructDef.getFields().add(localFieldDef);
      localFieldDef = new FieldDef();
      localFieldDef.setId((short)100);
      localFieldDef.setMetadata(osVer_metadata);
      localFieldDef.getType().setId(BondDataType.BT_STRING);
      localStructDef.getFields().add(localFieldDef);
      localFieldDef = new FieldDef();
      localFieldDef.setId((short)110);
      localFieldDef.setMetadata(appId_metadata);
      localFieldDef.getType().setId(BondDataType.BT_STRING);
      localStructDef.getFields().add(localFieldDef);
      localFieldDef = new FieldDef();
      localFieldDef.setId((short)120);
      localFieldDef.setMetadata(appVer_metadata);
      localFieldDef.getType().setId(BondDataType.BT_STRING);
      localStructDef.getFields().add(localFieldDef);
      localFieldDef = new FieldDef();
      localFieldDef.setId((short)130);
      localFieldDef.setMetadata(cV_metadata);
      localFieldDef.getType().setId(BondDataType.BT_STRING);
      localStructDef.getFields().add(localFieldDef);
      localFieldDef = new FieldDef();
      localFieldDef.setId((short)500);
      localFieldDef.setMetadata(tags_metadata);
      localFieldDef.getType().setId(BondDataType.BT_MAP);
      localFieldDef.getType().setKey(new TypeDef());
      localFieldDef.getType().setElement(new TypeDef());
      localFieldDef.getType().getKey().setId(BondDataType.BT_STRING);
      localFieldDef.getType().getElement().setId(BondDataType.BT_STRING);
      localStructDef.getFields().add(localFieldDef);
      localFieldDef = new FieldDef();
      localFieldDef.setId((short)510);
      localFieldDef.setMetadata(ext_metadata);
      localFieldDef.getType().setId(BondDataType.BT_MAP);
      localFieldDef.getType().setKey(new TypeDef());
      localFieldDef.getType().setElement(new TypeDef());
      localFieldDef.getType().getKey().setId(BondDataType.BT_STRING);
      localFieldDef.getType().setElement(Extension.Schema.getTypeDef(paramSchemaDef));
      localStructDef.getFields().add(localFieldDef);
      localFieldDef = new FieldDef();
      localFieldDef.setId((short)999);
      localFieldDef.setMetadata(data_metadata);
      localFieldDef.setType(Base.Schema.getTypeDef(paramSchemaDef));
      localStructDef.getFields().add(localFieldDef);
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


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\Microsoft\Telemetry\Envelope.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */