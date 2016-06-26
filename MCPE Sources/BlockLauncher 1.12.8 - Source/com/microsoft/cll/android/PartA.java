package com.microsoft.cll.android;

import com.microsoft.telemetry.Base;
import com.microsoft.telemetry.Data;
import com.microsoft.telemetry.Domain;
import com.microsoft.telemetry.Extension;
import com.microsoft.telemetry.extensions.android;
import com.microsoft.telemetry.extensions.app;
import com.microsoft.telemetry.extensions.device;
import com.microsoft.telemetry.extensions.os;
import com.microsoft.telemetry.extensions.user;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class PartA
{
  private final String TAG = "PartA";
  protected final app appExt;
  protected String appId;
  protected String appVer;
  private CorrelationVector correlationVector;
  private final String csVer = "2.1";
  protected final device deviceExt;
  private long epoch;
  private long flags;
  private final char[] hexArray = "0123456789ABCDEF".toCharArray();
  private String iKey;
  protected final ILogger logger;
  protected final os osExt;
  protected String osName;
  protected String osVer;
  private Random random;
  private final String salt = "oRq=MAHHHC~6CCe|JfEqRZ+gc0ESI||g2Jlb^PYjc5UYN2P 27z_+21xxd2n";
  protected final AtomicLong seqCounter;
  private EventSerializer serializer;
  protected String uniqueId;
  private boolean useLegacyCS = false;
  protected final user userExt;
  
  public PartA(ILogger paramILogger, String paramString, CorrelationVector paramCorrelationVector)
  {
    this.logger = paramILogger;
    this.iKey = paramString;
    this.correlationVector = paramCorrelationVector;
    this.seqCounter = new AtomicLong(0L);
    this.serializer = new EventSerializer(paramILogger);
    this.userExt = new user();
    this.deviceExt = new device();
    this.osExt = new os();
    this.appExt = new app();
    this.random = new Random();
    this.epoch = this.random.nextLong();
  }
  
  private String bytesToHex(byte[] paramArrayOfByte)
  {
    char[] arrayOfChar = new char[paramArrayOfByte.length * 2];
    int i = 0;
    while (i < paramArrayOfByte.length)
    {
      int j = paramArrayOfByte[i] & 0xFF;
      arrayOfChar[(i * 2)] = this.hexArray[(j >>> 4)];
      arrayOfChar[(i * 2 + 1)] = this.hexArray[(j & 0xF)];
      i += 1;
    }
    return new String(arrayOfChar);
  }
  
  private LinkedHashMap<String, Extension> createExtensions(List<String> paramList)
  {
    LinkedHashMap localLinkedHashMap = new LinkedHashMap();
    localLinkedHashMap.put("user", this.userExt);
    localLinkedHashMap.put("os", this.osExt);
    localLinkedHashMap.put("device", this.deviceExt);
    android localandroid = new android();
    localandroid.setLibVer("3.0.2");
    if ((paramList != null) && (paramList.size() > 0)) {
      localandroid.setTickets(paramList);
    }
    localLinkedHashMap.put("android", localandroid);
    if ((this.appExt.getExpId() != null) || (this.appExt.getUserId() != null)) {
      localLinkedHashMap.put("app", this.appExt);
    }
    return localLinkedHashMap;
  }
  
  private String getDateTime()
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
    localSimpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    return localSimpleDateFormat.format(new Date()).toString();
  }
  
  private int getSensitivityLevel(EventSensitivity... paramVarArgs)
  {
    int i = EventSensitivity.None.getCode();
    int m = paramVarArgs.length;
    int j = 0;
    for (;;)
    {
      int k = i;
      EventSensitivity localEventSensitivity;
      if (j < m)
      {
        localEventSensitivity = paramVarArgs[j];
        if (localEventSensitivity == EventSensitivity.Drop) {
          k = EventSensitivity.Drop.getCode();
        }
      }
      else
      {
        return k;
      }
      if (localEventSensitivity == EventSensitivity.Hash) {
        i = EventSensitivity.Hash.getCode();
      }
      j += 1;
    }
  }
  
  private SerializedEvent populateSerializedEvent(String paramString1, Cll.EventPersistence paramEventPersistence, Cll.EventLatency paramEventLatency, double paramDouble, String paramString2)
  {
    paramString2 = new SerializedEvent();
    paramString2.setSerializedData(paramString1);
    paramString2.setSampleRate(paramDouble);
    paramString2.setDeviceId(this.deviceExt.getLocalId());
    paramString2.setPersistence(paramEventPersistence);
    paramString2.setLatency(paramEventLatency);
    return paramString2;
  }
  
  private void scrubPII(com.microsoft.telemetry.Envelope paramEnvelope, EventSensitivity... paramVarArgs)
  {
    if (paramVarArgs == null) {}
    int i;
    do
    {
      do
      {
        return;
        i = getSensitivityLevel(paramVarArgs);
      } while (i == EventSensitivity.None.getCode());
      paramVarArgs = (user)paramEnvelope.getExt().get("user");
      Object localObject = new user();
      ((user)localObject).setLocalId(paramVarArgs.getLocalId());
      ((user)localObject).setAuthId(paramVarArgs.getAuthId());
      ((user)localObject).setId(paramVarArgs.getId());
      ((user)localObject).setVer(paramVarArgs.getVer());
      paramEnvelope.getExt().put("user", localObject);
      paramVarArgs = (device)paramEnvelope.getExt().get("device");
      localObject = new device();
      ((device)localObject).setLocalId(paramVarArgs.getLocalId());
      ((device)localObject).setVer(paramVarArgs.getVer());
      ((device)localObject).setId(paramVarArgs.getId());
      ((device)localObject).setAuthId(paramVarArgs.getAuthId());
      ((device)localObject).setAuthSecId(paramVarArgs.getAuthSecId());
      ((device)localObject).setDeviceClass(paramVarArgs.getDeviceClass());
      paramEnvelope.getExt().put("device", localObject);
      if (paramEnvelope.getExt().containsKey("app"))
      {
        paramVarArgs = (app)paramEnvelope.getExt().get("app");
        localObject = new app();
        ((app)localObject).setExpId(paramVarArgs.getExpId());
        ((app)localObject).setUserId(paramVarArgs.getUserId());
        paramEnvelope.getExt().put("app", localObject);
      }
      if (i == EventSensitivity.Drop.getCode())
      {
        ((user)paramEnvelope.getExt().get("user")).setLocalId(null);
        ((device)paramEnvelope.getExt().get("device")).setLocalId("r:" + String.valueOf(this.random.nextLong()));
        if (paramEnvelope.getExt().containsKey("app")) {
          ((app)paramEnvelope.getExt().get("app")).setUserId(null);
        }
        if (this.correlationVector.isInitialized) {
          paramEnvelope.setCV(null);
        }
        paramEnvelope.setEpoch(null);
        paramEnvelope.setSeqNum(0L);
        return;
      }
    } while (i != EventSensitivity.Hash.getCode());
    ((user)paramEnvelope.getExt().get("user")).setLocalId("d:" + HashStringSha256(((user)paramEnvelope.getExt().get("user")).getLocalId()));
    ((device)paramEnvelope.getExt().get("device")).setLocalId("d:" + HashStringSha256(((device)paramEnvelope.getExt().get("device")).getLocalId()));
    if (paramEnvelope.getExt().containsKey("app")) {
      ((app)paramEnvelope.getExt().get("app")).setUserId("d:" + HashStringSha256(((app)paramEnvelope.getExt().get("app")).getUserId()));
    }
    if (this.correlationVector.isInitialized) {
      paramEnvelope.setCV(HashStringSha256(paramEnvelope.getCV()));
    }
    paramEnvelope.setEpoch(HashStringSha256(paramEnvelope.getEpoch()));
  }
  
  private void setBaseType(Base paramBase)
  {
    try
    {
      paramBase.setBaseType(((Data)paramBase).getBaseData().QualifiedName);
      return;
    }
    catch (ClassCastException paramBase)
    {
      this.logger.error("PartA", "This event doesn't extend data");
    }
  }
  
  private long setFlags(Cll.EventPersistence paramEventPersistence, Cll.EventLatency paramEventLatency, Base paramBase, EventSensitivity... paramVarArgs)
  {
    this.flags = 0L;
    paramBase = (String)paramBase.Attributes.get("Sensitivity");
    if ((paramBase != null) && (String.valueOf(paramBase).compareToIgnoreCase("UserSensitive") == 0)) {
      this.flags |= EventSensitivity.Mark.getCode();
    }
    if (paramVarArgs != null)
    {
      int j = paramVarArgs.length;
      int i = 0;
      while (i < j)
      {
        paramBase = paramVarArgs[i];
        long l = this.flags;
        this.flags = (paramBase.getCode() | l);
        i += 1;
      }
    }
    this.flags |= paramEventLatency.getCode();
    this.flags |= paramEventPersistence.getCode();
    return this.flags;
  }
  
  private long setSeq(EventSensitivity... paramVarArgs)
  {
    if (paramVarArgs != null)
    {
      int j = paramVarArgs.length;
      int i = 0;
      while (i < j)
      {
        if (paramVarArgs[i] == EventSensitivity.Drop) {
          return 0L;
        }
        i += 1;
      }
    }
    return this.seqCounter.incrementAndGet();
  }
  
  protected String HashStringSha256(String paramString)
  {
    if (paramString == null) {
      return "";
    }
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("SHA-256");
      localMessageDigest.reset();
      localMessageDigest.update(paramString.getBytes());
      localMessageDigest.update("oRq=MAHHHC~6CCe|JfEqRZ+gc0ESI||g2Jlb^PYjc5UYN2P 27z_+21xxd2n".getBytes());
      paramString = bytesToHex(localMessageDigest.digest());
      return paramString;
    }
    catch (NoSuchAlgorithmException paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  protected abstract void PopulateConstantValues();
  
  String getAppUserId()
  {
    return this.appExt.getUserId();
  }
  
  public SerializedEvent populate(Base paramBase, List<String> paramList, Map<String, String> paramMap, EventSensitivity... paramVarArgs)
  {
    int i = Integer.parseInt(SettingsStore.getSetting(paramBase, SettingsStore.Settings.SAMPLERATE).toString());
    Cll.EventPersistence localEventPersistence = Cll.EventPersistence.valueOf(SettingsStore.getSetting(paramBase, SettingsStore.Settings.PERSISTENCE).toString().toUpperCase());
    Cll.EventLatency localEventLatency = Cll.EventLatency.valueOf(SettingsStore.getSetting(paramBase, SettingsStore.Settings.LATENCY).toString().toUpperCase());
    if (this.useLegacyCS)
    {
      paramBase = populateLegacyEnvelope(paramBase, this.correlationVector.GetValue(), i, localEventPersistence, localEventLatency, paramMap, paramVarArgs);
      return populateSerializedEvent(this.serializer.serialize(paramBase), localEventPersistence, localEventLatency, paramBase.getSampleRate(), paramBase.getDeviceId());
    }
    paramBase = populateEnvelope(paramBase, this.correlationVector.GetValue(), i, localEventPersistence, localEventLatency, paramList, paramVarArgs);
    return populateSerializedEvent(this.serializer.serialize(paramBase), localEventPersistence, localEventLatency, paramBase.getPopSample(), this.deviceExt.getLocalId());
  }
  
  public com.microsoft.telemetry.Envelope populateEnvelope(Base paramBase, String paramString, int paramInt, Cll.EventPersistence paramEventPersistence, Cll.EventLatency paramEventLatency, List<String> paramList, EventSensitivity... paramVarArgs)
  {
    com.microsoft.telemetry.Envelope localEnvelope = new com.microsoft.telemetry.Envelope();
    setBaseType(paramBase);
    localEnvelope.setVer("2.1");
    localEnvelope.setTime(getDateTime());
    localEnvelope.setName(paramBase.QualifiedName);
    localEnvelope.setPopSample(paramInt);
    localEnvelope.setEpoch(String.valueOf(this.epoch));
    localEnvelope.setSeqNum(setSeq(paramVarArgs));
    localEnvelope.setOs(this.osName);
    localEnvelope.setOsVer(this.osVer);
    localEnvelope.setData(paramBase);
    localEnvelope.setAppId(this.appId);
    localEnvelope.setAppVer(this.appVer);
    if (this.correlationVector.isInitialized) {
      localEnvelope.setCV(paramString);
    }
    localEnvelope.setFlags(setFlags(paramEventPersistence, paramEventLatency, paramBase, paramVarArgs));
    localEnvelope.setIKey(this.iKey);
    localEnvelope.setExt(createExtensions(paramList));
    scrubPII(localEnvelope, paramVarArgs);
    return localEnvelope;
  }
  
  public com.microsoft.telemetry.cs2.Envelope populateLegacyEnvelope(Base paramBase, String paramString, int paramInt, Cll.EventPersistence paramEventPersistence, Cll.EventLatency paramEventLatency, Map<String, String> paramMap, EventSensitivity... paramVarArgs)
  {
    Object localObject = paramMap;
    if (paramMap == null) {
      localObject = new HashMap();
    }
    if (this.correlationVector.isInitialized) {
      ((Map)localObject).put("cV", paramString);
    }
    paramString = new com.microsoft.telemetry.cs2.Envelope();
    paramString.setVer(1);
    paramString.setTime(getDateTime());
    paramString.setName(paramBase.QualifiedName);
    paramString.setSampleRate(paramInt);
    paramString.setSeq(String.valueOf(this.epoch) + ":" + String.valueOf(setSeq(paramVarArgs)));
    paramString.setOs(this.osName);
    paramString.setOsVer(this.osVer);
    paramString.setData(paramBase);
    paramString.setAppId(this.appId);
    paramString.setAppVer(this.appVer);
    paramString.setTags((Map)localObject);
    paramString.setFlags(setFlags(paramEventPersistence, paramEventLatency, paramBase, paramVarArgs));
    paramString.setIKey(this.iKey);
    paramString.setUserId(this.userExt.getLocalId());
    paramString.setDeviceId(this.deviceExt.getLocalId());
    return paramString;
  }
  
  protected abstract void setAppInfo();
  
  void setAppUserId(String paramString)
  {
    if (paramString == null)
    {
      this.appExt.setUserId(null);
      return;
    }
    if (!Pattern.compile("^((c:)|(i:)|(w:)).*").matcher(paramString).find())
    {
      this.appExt.setUserId(null);
      this.logger.warn("PartA", "The userId supplied does not match the required format which requires the appId to start with 'c:', 'i:', or 'w:'.");
      return;
    }
    this.appExt.setUserId(paramString);
  }
  
  protected abstract void setDeviceInfo();
  
  protected void setExpId(String paramString)
  {
    this.appExt.setExpId(paramString);
  }
  
  protected abstract void setOs();
  
  protected abstract void setUserId();
  
  void useLegacyCS(boolean paramBoolean)
  {
    this.useLegacyCS = paramBoolean;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\cll\android\PartA.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */