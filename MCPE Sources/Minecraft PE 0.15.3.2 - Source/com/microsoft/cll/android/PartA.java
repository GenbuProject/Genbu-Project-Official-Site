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
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
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
  private final String TAG = "AndroidCll-PartA";
  protected final app appExt;
  protected String appId;
  protected String appVer;
  private CorrelationVector correlationVector;
  private final String csVer = "2.1";
  protected final device deviceExt;
  private long epoch;
  private long flags;
  private final char[] hexArray = "0123456789ABCDEF".toCharArray();
  protected final String iKey;
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
    localandroid.setLibVer("3.2.2");
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
  
  private long getFlags(EventEnums.Latency paramLatency, EventEnums.Persistence paramPersistence, EnumSet<EventEnums.Sensitivity> paramEnumSet)
  {
    long l1 = 0L;
    long l2 = l1;
    if (paramEnumSet != null)
    {
      paramEnumSet = paramEnumSet.iterator();
      for (;;)
      {
        l2 = l1;
        if (!paramEnumSet.hasNext()) {
          break;
        }
        EventEnums.Sensitivity localSensitivity = (EventEnums.Sensitivity)paramEnumSet.next();
        if (localSensitivity != EventEnums.Sensitivity.SensitivityUnspecified) {
          l1 |= localSensitivity.id;
        }
      }
    }
    return l2 | paramLatency.id | paramPersistence.id;
  }
  
  private EventEnums.Sensitivity getHighestSensitivityLevel(EnumSet<EventEnums.Sensitivity> paramEnumSet)
  {
    EventEnums.Sensitivity localSensitivity = EventEnums.Sensitivity.SensitivityNone;
    if (paramEnumSet.contains(EventEnums.Sensitivity.SensitivityDrop)) {
      localSensitivity = EventEnums.Sensitivity.SensitivityDrop;
    }
    while (!paramEnumSet.contains(EventEnums.Sensitivity.SensitivityHash)) {
      return localSensitivity;
    }
    return EventEnums.Sensitivity.SensitivityHash;
  }
  
  private long getSeqNum(EnumSet<EventEnums.Sensitivity> paramEnumSet)
  {
    if (paramEnumSet.contains(EventEnums.Sensitivity.SensitivityDrop)) {
      return 0L;
    }
    return this.seqCounter.incrementAndGet();
  }
  
  private SerializedEvent populateSerializedEvent(String paramString1, EventEnums.Latency paramLatency, EventEnums.Persistence paramPersistence, double paramDouble, String paramString2)
  {
    paramString2 = new SerializedEvent();
    paramString2.setSerializedData(paramString1);
    paramString2.setSampleRate(paramDouble);
    paramString2.setDeviceId(this.deviceExt.getLocalId());
    paramString2.setPersistence(paramPersistence);
    paramString2.setLatency(paramLatency);
    return paramString2;
  }
  
  private void scrubPII(com.microsoft.telemetry.Envelope paramEnvelope, EnumSet<EventEnums.Sensitivity> paramEnumSet)
  {
    if (paramEnumSet == null) {}
    do
    {
      do
      {
        return;
        paramEnumSet = getHighestSensitivityLevel(paramEnumSet);
      } while (paramEnumSet == EventEnums.Sensitivity.SensitivityNone);
      Object localObject1 = (user)paramEnvelope.getExt().get("user");
      Object localObject2 = new user();
      ((user)localObject2).setLocalId(((user)localObject1).getLocalId());
      ((user)localObject2).setAuthId(((user)localObject1).getAuthId());
      ((user)localObject2).setId(((user)localObject1).getId());
      ((user)localObject2).setVer(((user)localObject1).getVer());
      paramEnvelope.getExt().put("user", localObject2);
      localObject1 = (device)paramEnvelope.getExt().get("device");
      localObject2 = new device();
      ((device)localObject2).setLocalId(((device)localObject1).getLocalId());
      ((device)localObject2).setVer(((device)localObject1).getVer());
      ((device)localObject2).setId(((device)localObject1).getId());
      ((device)localObject2).setAuthId(((device)localObject1).getAuthId());
      ((device)localObject2).setAuthSecId(((device)localObject1).getAuthSecId());
      ((device)localObject2).setDeviceClass(((device)localObject1).getDeviceClass());
      paramEnvelope.getExt().put("device", localObject2);
      if (paramEnvelope.getExt().containsKey("app"))
      {
        localObject1 = (app)paramEnvelope.getExt().get("app");
        localObject2 = new app();
        ((app)localObject2).setExpId(((app)localObject1).getExpId());
        ((app)localObject2).setUserId(((app)localObject1).getUserId());
        paramEnvelope.getExt().put("app", localObject2);
      }
      if (paramEnumSet == EventEnums.Sensitivity.SensitivityDrop)
      {
        ((user)paramEnvelope.getExt().get("user")).setLocalId(null);
        ((device)paramEnvelope.getExt().get("device")).setLocalId("r:" + String.valueOf(Math.abs(this.random.nextInt())));
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
    } while (paramEnumSet != EventEnums.Sensitivity.SensitivityHash);
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
      this.logger.error("AndroidCll-PartA", "This event doesn't extend data");
    }
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
  
  public SerializedEvent populate(Base paramBase, EventEnums.Latency paramLatency, EventEnums.Persistence paramPersistence, EnumSet<EventEnums.Sensitivity> paramEnumSet, double paramDouble, List<String> paramList)
  {
    paramLatency = SettingsStore.getLatencyForEvent(paramBase, paramLatency);
    paramPersistence = SettingsStore.getPersistenceForEvent(paramBase, paramPersistence);
    paramEnumSet = SettingsStore.getSensitivityForEvent(paramBase, paramEnumSet);
    paramDouble = SettingsStore.getSampleRateForEvent(paramBase, paramDouble);
    if (this.useLegacyCS)
    {
      paramBase = populateLegacyEnvelope(paramBase, this.correlationVector.GetValue(), paramLatency, paramPersistence, paramEnumSet, paramDouble, paramList);
      return populateSerializedEvent(this.serializer.serialize(paramBase), paramLatency, paramPersistence, paramDouble, paramBase.getDeviceId());
    }
    paramBase = populateEnvelope(paramBase, this.correlationVector.GetValue(), paramLatency, paramPersistence, paramEnumSet, paramDouble, paramList);
    return populateSerializedEvent(this.serializer.serialize(paramBase), paramLatency, paramPersistence, paramDouble, this.deviceExt.getLocalId());
  }
  
  public com.microsoft.telemetry.Envelope populateEnvelope(Base paramBase, String paramString, EventEnums.Latency paramLatency, EventEnums.Persistence paramPersistence, EnumSet<EventEnums.Sensitivity> paramEnumSet, double paramDouble, List<String> paramList)
  {
    com.microsoft.telemetry.Envelope localEnvelope = new com.microsoft.telemetry.Envelope();
    setBaseType(paramBase);
    localEnvelope.setVer("2.1");
    localEnvelope.setTime(getDateTime());
    localEnvelope.setName(paramBase.QualifiedName);
    localEnvelope.setPopSample(paramDouble);
    localEnvelope.setEpoch(String.valueOf(this.epoch));
    localEnvelope.setSeqNum(getSeqNum(paramEnumSet));
    localEnvelope.setOs(this.osName);
    localEnvelope.setOsVer(this.osVer);
    localEnvelope.setData(paramBase);
    localEnvelope.setAppId(this.appId);
    localEnvelope.setAppVer(this.appVer);
    if (this.correlationVector.isInitialized) {
      localEnvelope.setCV(paramString);
    }
    localEnvelope.setFlags(getFlags(paramLatency, paramPersistence, paramEnumSet));
    localEnvelope.setIKey(this.iKey);
    localEnvelope.setExt(createExtensions(paramList));
    scrubPII(localEnvelope, paramEnumSet);
    return localEnvelope;
  }
  
  public com.microsoft.telemetry.cs2.Envelope populateLegacyEnvelope(Base paramBase, String paramString, EventEnums.Latency paramLatency, EventEnums.Persistence paramPersistence, EnumSet<EventEnums.Sensitivity> paramEnumSet, double paramDouble, List<String> paramList)
  {
    paramList = new HashMap();
    if (this.correlationVector.isInitialized) {
      paramList.put("cV", paramString);
    }
    paramString = new com.microsoft.telemetry.cs2.Envelope();
    paramString.setVer(1);
    paramString.setTime(getDateTime());
    paramString.setName(paramBase.QualifiedName);
    paramString.setSampleRate(paramDouble);
    paramString.setSeq(String.valueOf(this.epoch) + ":" + String.valueOf(getSeqNum(paramEnumSet)));
    paramString.setOs(this.osName);
    paramString.setOsVer(this.osVer);
    paramString.setData(paramBase);
    paramString.setAppId(this.appId);
    paramString.setAppVer(this.appVer);
    paramString.setTags(paramList);
    paramString.setFlags(getFlags(paramLatency, paramPersistence, paramEnumSet));
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
      this.logger.warn("AndroidCll-PartA", "The userId supplied does not match the required format which requires the appId to start with 'c:', 'i:', or 'w:'.");
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


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\cll\android\PartA.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */