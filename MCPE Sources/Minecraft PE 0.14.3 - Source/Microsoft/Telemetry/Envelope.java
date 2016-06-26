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
  implements BondMirror, BondSerializable
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
    label13:
    label24:
    label26:
    label37:
    label39:
    label50:
    label52:
    label63:
    label65:
    label76:
    label78:
    label89:
    label91:
    label102:
    label104:
    label115:
    label117:
    label128:
    int j;
    label130:
    Object localObject1;
    Object localObject2;
    if (1 != 0) {
      if (this.ver == null)
      {
        i = 1;
        if (i == 0) {
          break label531;
        }
        if (this.name != null) {
          break label517;
        }
        i = 1;
        if (i == 0) {
          break label550;
        }
        if (this.time != null) {
          break label536;
        }
        i = 1;
        if (i == 0) {
          break label569;
        }
        if (this.epoch != null) {
          break label555;
        }
        i = 1;
        if (i == 0) {
          break label588;
        }
        if (this.iKey != null) {
          break label574;
        }
        i = 1;
        if (i == 0) {
          break label607;
        }
        if (this.os != null) {
          break label593;
        }
        i = 1;
        if (i == 0) {
          break label626;
        }
        if (this.osVer != null) {
          break label612;
        }
        i = 1;
        if (i == 0) {
          break label645;
        }
        if (this.appId != null) {
          break label631;
        }
        i = 1;
        if (i == 0) {
          break label664;
        }
        if (this.appVer != null) {
          break label650;
        }
        i = 1;
        if (i == 0) {
          break label683;
        }
        if (this.cV != null) {
          break label669;
        }
        j = 1;
        i = j;
        Iterator localIterator;
        Map.Entry localEntry;
        if (j != 0)
        {
          i = j;
          if (this.tags != null)
          {
            i = j;
            if (this.tags.size() != 0)
            {
              localIterator = this.tags.entrySet().iterator();
              label249:
              label266:
              label273:
              label280:
              label289:
              label291:
              label300:
              label302:
              do
              {
                i = j;
                if (!localIterator.hasNext()) {
                  break;
                }
                localEntry = (Map.Entry)localIterator.next();
                localObject1 = (String)localEntry.getValue();
                localObject2 = (String)paramEnvelope.tags.get(localEntry.getKey());
                if ((j == 0) || (!paramEnvelope.tags.containsKey(localEntry.getKey()))) {
                  break label688;
                }
                j = 1;
                i = j;
                if (j != 0)
                {
                  if (j == 0) {
                    break label703;
                  }
                  if (localObject1 != null) {
                    break label693;
                  }
                  i = 1;
                  if (localObject2 != null) {
                    break label698;
                  }
                  j = 1;
                  if (i != j) {
                    break label703;
                  }
                  i = 1;
                  if (i == 0) {
                    break label721;
                  }
                  if (localObject1 != null) {
                    break label708;
                  }
                  i = 1;
                  if (i == 0) {
                    break label736;
                  }
                  if (localObject1 != null) {
                    break label726;
                  }
                  i = 1;
                }
                j = i;
              } while (i != 0);
            }
          }
        }
        int k = i;
        if (i != 0)
        {
          k = i;
          if (this.ext != null)
          {
            k = i;
            if (this.ext.size() != 0)
            {
              localIterator = this.ext.entrySet().iterator();
              j = i;
              label433:
              label450:
              label457:
              label464:
              label473:
              label475:
              do
              {
                k = j;
                if (!localIterator.hasNext()) {
                  break;
                }
                localEntry = (Map.Entry)localIterator.next();
                localObject1 = (Bonded)localEntry.getValue();
                localObject2 = (Bonded)paramEnvelope.ext.get(localEntry.getKey());
                if ((j == 0) || (!paramEnvelope.ext.containsKey(localEntry.getKey()))) {
                  break label741;
                }
                j = 1;
                i = j;
                if (j != 0)
                {
                  if (j == 0) {
                    break label756;
                  }
                  if (localObject1 != null) {
                    break label746;
                  }
                  i = 1;
                  if (localObject2 != null) {
                    break label751;
                  }
                  j = 1;
                  if (i != j) {
                    break label756;
                  }
                  i = 1;
                  if (i == 0) {
                    break label771;
                  }
                  if (localObject1 != null) {
                    break label761;
                  }
                  i = 1;
                }
                j = i;
              } while (i != 0);
              k = i;
            }
          }
        }
        if (k == 0) {
          break label790;
        }
        if (this.data != null) {
          break label776;
        }
      }
    }
    label517:
    label531:
    label536:
    label550:
    label555:
    label569:
    label574:
    label588:
    label593:
    label607:
    label612:
    label626:
    label631:
    label645:
    label650:
    label664:
    label669:
    label683:
    label688:
    label693:
    label698:
    label703:
    label708:
    label721:
    label726:
    label736:
    label741:
    label746:
    label751:
    label756:
    label761:
    label771:
    label776:
    while (this.data.memberwiseCompare(paramEnvelope.data))
    {
      return true;
      if (this.ver.equals(paramEnvelope.ver)) {
        break;
      }
      i = 0;
      break label13;
      if (this.name.equals(paramEnvelope.name)) {
        break label24;
      }
      i = 0;
      break label26;
      if (this.time.equals(paramEnvelope.time)) {
        break label37;
      }
      i = 0;
      break label39;
      if (this.epoch.equals(paramEnvelope.epoch)) {
        break label50;
      }
      i = 0;
      break label52;
      if (this.iKey.equals(paramEnvelope.iKey)) {
        break label63;
      }
      i = 0;
      break label65;
      if (this.os.equals(paramEnvelope.os)) {
        break label76;
      }
      i = 0;
      break label78;
      if (this.osVer.equals(paramEnvelope.osVer)) {
        break label89;
      }
      i = 0;
      break label91;
      if (this.appId.equals(paramEnvelope.appId)) {
        break label102;
      }
      i = 0;
      break label104;
      if (this.appVer.equals(paramEnvelope.appVer)) {
        break label115;
      }
      i = 0;
      break label117;
      if (this.cV.equals(paramEnvelope.cV)) {
        break label128;
      }
      j = 0;
      break label130;
      j = 0;
      break label249;
      i = 0;
      break label266;
      j = 0;
      break label273;
      i = 0;
      break label280;
      if (((String)localObject1).length() == ((String)localObject2).length()) {
        break label289;
      }
      i = 0;
      break label291;
      if (((String)localObject1).equals(localObject2)) {
        break label300;
      }
      i = 0;
      break label302;
      j = 0;
      break label433;
      i = 0;
      break label450;
      j = 0;
      break label457;
      i = 0;
      break label464;
      if (((Bonded)localObject1).memberwiseCompare(localObject2)) {
        break label473;
      }
      i = 0;
      break label475;
    }
    label790:
    return false;
  }
  
  protected boolean memberwiseCompareQuick(Envelope paramEnvelope)
  {
    int i;
    int j;
    if (1 != 0) {
      if (this.ver == null)
      {
        i = 1;
        if (paramEnvelope.ver != null) {
          break label571;
        }
        j = 1;
        label22:
        if (i != j) {
          break label576;
        }
        i = 1;
        label29:
        if (i == 0) {
          break label598;
        }
        if (this.ver != null) {
          break label581;
        }
        label40:
        i = 1;
        label42:
        if (i == 0) {
          break label613;
        }
        if (this.name != null) {
          break label603;
        }
        i = 1;
        label55:
        if (paramEnvelope.name != null) {
          break label608;
        }
        j = 1;
        label64:
        if (i != j) {
          break label613;
        }
        i = 1;
        label71:
        if (i == 0) {
          break label635;
        }
        if (this.name != null) {
          break label618;
        }
        label82:
        i = 1;
        label84:
        if (i == 0) {
          break label650;
        }
        if (this.time != null) {
          break label640;
        }
        i = 1;
        label97:
        if (paramEnvelope.time != null) {
          break label645;
        }
        j = 1;
        label106:
        if (i != j) {
          break label650;
        }
        i = 1;
        label113:
        if (i == 0) {
          break label672;
        }
        if (this.time != null) {
          break label655;
        }
        label124:
        i = 1;
        label126:
        if (i == 0) {
          break label689;
        }
        if (!Double.isNaN(this.popSample)) {
          break label677;
        }
        if (!Double.isNaN(paramEnvelope.popSample)) {
          break label689;
        }
        label150:
        i = 1;
        label152:
        if (i == 0) {
          break label704;
        }
        if (this.epoch != null) {
          break label694;
        }
        i = 1;
        label165:
        if (paramEnvelope.epoch != null) {
          break label699;
        }
        j = 1;
        label174:
        if (i != j) {
          break label704;
        }
        i = 1;
        label181:
        if (i == 0) {
          break label726;
        }
        if (this.epoch != null) {
          break label709;
        }
        label192:
        i = 1;
        label194:
        if ((i == 0) || (this.seqNum != paramEnvelope.seqNum)) {
          break label731;
        }
        i = 1;
        label212:
        if (i == 0) {
          break label746;
        }
        if (this.iKey != null) {
          break label736;
        }
        i = 1;
        label225:
        if (paramEnvelope.iKey != null) {
          break label741;
        }
        j = 1;
        label234:
        if (i != j) {
          break label746;
        }
        i = 1;
        label241:
        if (i == 0) {
          break label768;
        }
        if (this.iKey != null) {
          break label751;
        }
        label252:
        i = 1;
        label254:
        if ((i == 0) || (this.flags != paramEnvelope.flags)) {
          break label773;
        }
        i = 1;
        label272:
        if (i == 0) {
          break label788;
        }
        if (this.os != null) {
          break label778;
        }
        i = 1;
        label285:
        if (paramEnvelope.os != null) {
          break label783;
        }
        j = 1;
        label294:
        if (i != j) {
          break label788;
        }
        i = 1;
        label301:
        if (i == 0) {
          break label810;
        }
        if (this.os != null) {
          break label793;
        }
        label312:
        i = 1;
        label314:
        if (i == 0) {
          break label825;
        }
        if (this.osVer != null) {
          break label815;
        }
        i = 1;
        label327:
        if (paramEnvelope.osVer != null) {
          break label820;
        }
        j = 1;
        label336:
        if (i != j) {
          break label825;
        }
        i = 1;
        label343:
        if (i == 0) {
          break label847;
        }
        if (this.osVer != null) {
          break label830;
        }
        label354:
        i = 1;
        label356:
        if (i == 0) {
          break label862;
        }
        if (this.appId != null) {
          break label852;
        }
        i = 1;
        label369:
        if (paramEnvelope.appId != null) {
          break label857;
        }
        j = 1;
        label378:
        if (i != j) {
          break label862;
        }
        i = 1;
        label385:
        if (i == 0) {
          break label884;
        }
        if (this.appId != null) {
          break label867;
        }
        label396:
        i = 1;
        label398:
        if (i == 0) {
          break label899;
        }
        if (this.appVer != null) {
          break label889;
        }
        i = 1;
        label411:
        if (paramEnvelope.appVer != null) {
          break label894;
        }
        j = 1;
        label420:
        if (i != j) {
          break label899;
        }
        i = 1;
        label427:
        if (i == 0) {
          break label921;
        }
        if (this.appVer != null) {
          break label904;
        }
        label438:
        i = 1;
        label440:
        if (i == 0) {
          break label936;
        }
        if (this.cV != null) {
          break label926;
        }
        i = 1;
        label453:
        if (paramEnvelope.cV != null) {
          break label931;
        }
        j = 1;
        label462:
        if (i != j) {
          break label936;
        }
        i = 1;
        label469:
        if (i == 0) {
          break label958;
        }
        if (this.cV != null) {
          break label941;
        }
        label480:
        i = 1;
        label482:
        if (i == 0) {
          break label973;
        }
        if (this.tags != null) {
          break label963;
        }
        i = 1;
        label495:
        if (paramEnvelope.tags != null) {
          break label968;
        }
        j = 1;
        label504:
        if (i != j) {
          break label973;
        }
        i = 1;
        label511:
        if (i == 0) {
          break label995;
        }
        if (this.tags != null) {
          break label978;
        }
        label522:
        i = 1;
        label524:
        if (i == 0) {
          break label1010;
        }
        if (this.ext != null) {
          break label1000;
        }
        i = 1;
        label537:
        if (paramEnvelope.ext != null) {
          break label1005;
        }
        j = 1;
        label546:
        if (i != j) {
          break label1010;
        }
        i = 1;
        label553:
        if (i == 0) {
          break label1032;
        }
        if (this.ext != null) {
          break label1015;
        }
      }
    }
    label571:
    label576:
    label581:
    label598:
    label603:
    label608:
    label613:
    label618:
    label635:
    label640:
    label645:
    label650:
    label655:
    label672:
    label677:
    label689:
    label694:
    label699:
    label704:
    label709:
    label726:
    label731:
    label736:
    label741:
    label746:
    label751:
    label768:
    label773:
    label778:
    label783:
    label788:
    label793:
    label810:
    label815:
    label820:
    label825:
    label830:
    label847:
    label852:
    label857:
    label862:
    label867:
    label884:
    label889:
    label894:
    label899:
    label904:
    label921:
    label926:
    label931:
    label936:
    label941:
    label958:
    label963:
    label968:
    label973:
    label978:
    label995:
    label1000:
    label1005:
    label1010:
    label1015:
    while (this.ext.size() == paramEnvelope.ext.size())
    {
      return true;
      i = 0;
      break;
      j = 0;
      break label22;
      i = 0;
      break label29;
      if (this.ver.length() == paramEnvelope.ver.length()) {
        break label40;
      }
      i = 0;
      break label42;
      i = 0;
      break label55;
      j = 0;
      break label64;
      i = 0;
      break label71;
      if (this.name.length() == paramEnvelope.name.length()) {
        break label82;
      }
      i = 0;
      break label84;
      i = 0;
      break label97;
      j = 0;
      break label106;
      i = 0;
      break label113;
      if (this.time.length() == paramEnvelope.time.length()) {
        break label124;
      }
      i = 0;
      break label126;
      if (this.popSample == paramEnvelope.popSample) {
        break label150;
      }
      i = 0;
      break label152;
      i = 0;
      break label165;
      j = 0;
      break label174;
      i = 0;
      break label181;
      if (this.epoch.length() == paramEnvelope.epoch.length()) {
        break label192;
      }
      i = 0;
      break label194;
      i = 0;
      break label212;
      i = 0;
      break label225;
      j = 0;
      break label234;
      i = 0;
      break label241;
      if (this.iKey.length() == paramEnvelope.iKey.length()) {
        break label252;
      }
      i = 0;
      break label254;
      i = 0;
      break label272;
      i = 0;
      break label285;
      j = 0;
      break label294;
      i = 0;
      break label301;
      if (this.os.length() == paramEnvelope.os.length()) {
        break label312;
      }
      i = 0;
      break label314;
      i = 0;
      break label327;
      j = 0;
      break label336;
      i = 0;
      break label343;
      if (this.osVer.length() == paramEnvelope.osVer.length()) {
        break label354;
      }
      i = 0;
      break label356;
      i = 0;
      break label369;
      j = 0;
      break label378;
      i = 0;
      break label385;
      if (this.appId.length() == paramEnvelope.appId.length()) {
        break label396;
      }
      i = 0;
      break label398;
      i = 0;
      break label411;
      j = 0;
      break label420;
      i = 0;
      break label427;
      if (this.appVer.length() == paramEnvelope.appVer.length()) {
        break label438;
      }
      i = 0;
      break label440;
      i = 0;
      break label453;
      j = 0;
      break label462;
      i = 0;
      break label469;
      if (this.cV.length() == paramEnvelope.cV.length()) {
        break label480;
      }
      i = 0;
      break label482;
      i = 0;
      break label495;
      j = 0;
      break label504;
      i = 0;
      break label511;
      if (this.tags.size() == paramEnvelope.tags.size()) {
        break label522;
      }
      i = 0;
      break label524;
      i = 0;
      break label537;
      j = 0;
      break label546;
      i = 0;
      break label553;
    }
    label1032:
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


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\MCPE.jar!\Microsoft\Telemetry\Envelope.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */