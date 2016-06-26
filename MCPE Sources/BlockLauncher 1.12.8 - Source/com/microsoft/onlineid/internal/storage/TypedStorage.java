package com.microsoft.onlineid.internal.storage;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.microsoft.onlineid.ISecurityScope;
import com.microsoft.onlineid.Ticket;
import com.microsoft.onlineid.analytics.ClientAnalytics;
import com.microsoft.onlineid.analytics.IClientAnalytics;
import com.microsoft.onlineid.internal.Assertion;
import com.microsoft.onlineid.internal.Objects;
import com.microsoft.onlineid.internal.Strings;
import com.microsoft.onlineid.internal.log.Logger;
import com.microsoft.onlineid.internal.sso.BundleMarshaller;
import com.microsoft.onlineid.internal.sso.BundleMarshallerException;
import com.microsoft.onlineid.sts.AuthenticatorUserAccount;
import com.microsoft.onlineid.sts.DeviceIdentity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class TypedStorage
{
  protected static final String AccountKeyToken = "Account";
  protected static final String AccountsCollectionKey = "Accounts";
  protected static final String ClockSkewKey = "ClockSkew";
  protected static final Object CollectionLock = new Object();
  protected static final String ConfigLastDownloadedTimeKey = "ConfigLastDownloadedTime";
  protected static final String DeviceBasedFlightsKey = "DeviceBasedFlights";
  protected static final String DeviceFlightOverrideKey = "DeviceFlightOverride";
  protected static final String DeviceIdentityKey = "Device";
  protected static final String FormatSeparator = "|";
  protected static final String LastBackupPushedTimeKey = "LastBackupPushedTime";
  protected static final String LastBackupReceivedTimeKey = "LastBackupReceivedTime";
  protected static final String SdkVersionKey = "SdkVersion";
  protected static final String TicketCollectionKeyToken = "Tickets";
  protected static final String TicketKeyToken = "Ticket";
  protected final Storage _storage;
  
  public TypedStorage(Context paramContext)
  {
    Objects.verifyArgumentNotNull(paramContext, "applicationContext");
    this._storage = new Storage(paramContext);
  }
  
  protected TypedStorage(Storage paramStorage)
  {
    this._storage = paramStorage;
  }
  
  protected static String constructAccountKey(String paramString)
  {
    return constructKey(new Object[] { "Account", paramString.toLowerCase(Locale.US) });
  }
  
  protected static String constructKey(Object... paramVarArgs)
  {
    return TextUtils.join("|", paramVarArgs);
  }
  
  protected static String constructTicketCollectionKey(String paramString)
  {
    return constructKey(new Object[] { "Tickets", paramString.toLowerCase(Locale.US) });
  }
  
  protected static String constructTicketCollectionKeyFromAccountKey(String paramString)
  {
    return paramString.replace("Account", "Tickets");
  }
  
  protected static String constructTicketKey(String paramString1, String paramString2, ISecurityScope paramISecurityScope)
  {
    Objects.verifyArgumentNotNull(paramISecurityScope.getTarget(), "Ticket target");
    Objects.verifyArgumentNotNull(paramISecurityScope.getPolicy(), "Ticket policy");
    return constructKey(new Object[] { "Ticket", paramString1.toLowerCase(Locale.US), paramString2.toLowerCase(Locale.US), paramISecurityScope.getTarget().toLowerCase(Locale.US), paramISecurityScope.getPolicy().toLowerCase(Locale.US) });
  }
  
  private boolean hasTickets(String paramString)
  {
    return hasCollection(constructTicketCollectionKey(paramString));
  }
  
  private <T> void replaceCollection(String paramString, Map<String, String> paramMap, Storage.Editor paramEditor)
  {
    Iterator localIterator = this._storage.readStringSet(paramString).iterator();
    while (localIterator.hasNext()) {
      paramEditor.remove((String)localIterator.next());
    }
    if (paramMap != null)
    {
      localIterator = paramMap.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        paramEditor.writeString((String)localEntry.getKey(), (String)localEntry.getValue());
      }
      paramEditor.writeStringSet(paramString, paramMap.keySet());
      return;
    }
    paramEditor.remove(paramString);
  }
  
  public boolean clearSynchronous()
  {
    synchronized (CollectionLock)
    {
      boolean bool = this._storage.edit().clear().commit();
      return bool;
    }
  }
  
  public void deleteDeviceIdentity()
  {
    this._storage.edit().remove("Device").apply();
  }
  
  protected ISerializer<AuthenticatorUserAccount> getAccountSerializer()
  {
    return new ObjectStreamSerializer();
  }
  
  protected ISerializer<DeviceIdentity> getDeviceIdentitySerializer()
  {
    return new ObjectStreamSerializer();
  }
  
  Ticket getTicket(String paramString1, String paramString2, ISecurityScope paramISecurityScope)
  {
    return (Ticket)readFromCollection(constructTicketCollectionKey(paramString1), constructTicketKey(paramString1, paramString2, paramISecurityScope), getTicketSerializer());
  }
  
  protected ISerializer<Ticket> getTicketSerializer()
  {
    return new ObjectStreamSerializer();
  }
  
  public boolean hasAccounts()
  {
    return hasCollection("Accounts");
  }
  
  protected boolean hasCollection(String paramString)
  {
    for (;;)
    {
      synchronized (CollectionLock)
      {
        if (!this._storage.readStringSet(paramString).isEmpty())
        {
          bool = true;
          return bool;
        }
      }
      boolean bool = false;
    }
  }
  
  public AuthenticatorUserAccount readAccount(String paramString)
  {
    return (AuthenticatorUserAccount)readFromCollection("Accounts", constructAccountKey(paramString), getAccountSerializer());
  }
  
  public Set<AuthenticatorUserAccount> readAllAccounts()
  {
    return readCollection("Accounts", getAccountSerializer());
  }
  
  public long readClockSkew()
  {
    return this._storage.readLong("ClockSkew", 0L);
  }
  
  protected <T> Set<T> readCollection(String paramString, ISerializer<T> paramISerializer)
  {
    HashMap localHashMap = new HashMap();
    HashSet localHashSet;
    Object localObject3;
    for (;;)
    {
      String str1;
      synchronized (CollectionLock)
      {
        localObject1 = this._storage.readStringSet(paramString);
        localHashSet = new HashSet((Collection)localObject1);
        localObject3 = ((Set)localObject1).iterator();
        if (!((Iterator)localObject3).hasNext()) {
          break;
        }
        str1 = (String)((Iterator)localObject3).next();
        String str2 = this._storage.readString(str1);
        if (str2 != null) {
          localHashMap.put(str1, str2);
        }
      }
      Assertion.check(false, "Stored collection value was null.");
      localHashSet.remove(str1);
    }
    int i;
    if (localHashSet.size() != ((Set)localObject1).size())
    {
      Logger.error("Key set was out of sync for collection: " + paramString);
      i = paramString.indexOf("|");
      localObject3 = ClientAnalytics.get();
      if (i <= 0) {
        break label240;
      }
    }
    for (Object localObject1 = paramString.substring(0, i);; localObject1 = paramString)
    {
      ((IClientAnalytics)localObject3).logEvent("Storage", "Collection consistency error", (String)localObject1);
      this._storage.edit().writeStringSet(paramString, localHashSet).apply();
      paramString = Collections.emptySet();
      try
      {
        paramISerializer = paramISerializer.deserializeAll(localHashMap);
        return paramISerializer;
      }
      catch (IOException paramISerializer)
      {
        label240:
        Logger.error("Unable to deserialize indexed collection.", paramISerializer);
      }
    }
    return paramString;
  }
  
  public long readConfigLastDownloadedTime()
  {
    return this._storage.readLong("ConfigLastDownloadedTime", 0L);
  }
  
  public Set<Integer> readDeviceBasedFlights()
  {
    Object localObject = this._storage.readStringSet("DeviceBasedFlights");
    HashSet localHashSet = new HashSet(((Set)localObject).size());
    localObject = ((Set)localObject).iterator();
    while (((Iterator)localObject).hasNext()) {
      localHashSet.add(Integer.valueOf(Integer.parseInt((String)((Iterator)localObject).next())));
    }
    return localHashSet;
  }
  
  public boolean readDeviceFlightOverrideEnabled()
  {
    return this._storage.readBoolean("DeviceFlightOverride", false);
  }
  
  public DeviceIdentity readDeviceIdentity()
  {
    return (DeviceIdentity)this._storage.readObject("Device", getDeviceIdentitySerializer());
  }
  
  /* Error */
  protected <T> T readFromCollection(String paramString1, String paramString2, ISerializer<T> paramISerializer)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 71	com/microsoft/onlineid/internal/storage/TypedStorage:_storage	Lcom/microsoft/onlineid/internal/storage/Storage;
    //   4: aload_2
    //   5: invokevirtual 253	com/microsoft/onlineid/internal/storage/Storage:readString	(Ljava/lang/String;)Ljava/lang/String;
    //   8: astore 5
    //   10: aconst_null
    //   11: astore 4
    //   13: aload 5
    //   15: ifnull +35 -> 50
    //   18: aload_3
    //   19: aload 5
    //   21: invokeinterface 368 2 0
    //   26: astore_3
    //   27: aload_3
    //   28: astore 4
    //   30: aload_3
    //   31: ifnonnull +19 -> 50
    //   34: aload_0
    //   35: aload_1
    //   36: iconst_1
    //   37: anewarray 82	java/lang/String
    //   40: dup
    //   41: iconst_0
    //   42: aload_2
    //   43: aastore
    //   44: invokevirtual 372	com/microsoft/onlineid/internal/storage/TypedStorage:removeFromCollection	(Ljava/lang/String;[Ljava/lang/String;)V
    //   47: aload_3
    //   48: astore 4
    //   50: aload 4
    //   52: areturn
    //   53: astore_3
    //   54: getstatic 80	java/util/Locale:US	Ljava/util/Locale;
    //   57: ldc_w 374
    //   60: iconst_1
    //   61: anewarray 4	java/lang/Object
    //   64: dup
    //   65: iconst_0
    //   66: aload_2
    //   67: aastore
    //   68: invokestatic 378	java/lang/String:format	(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   71: invokestatic 381	com/microsoft/onlineid/internal/log/Logger:warning	(Ljava/lang/String;)V
    //   74: aload_0
    //   75: aload_1
    //   76: iconst_1
    //   77: anewarray 82	java/lang/String
    //   80: dup
    //   81: iconst_0
    //   82: aload_2
    //   83: aastore
    //   84: invokevirtual 372	com/microsoft/onlineid/internal/storage/TypedStorage:removeFromCollection	(Ljava/lang/String;[Ljava/lang/String;)V
    //   87: aconst_null
    //   88: areturn
    //   89: astore_3
    //   90: aload_0
    //   91: aload_1
    //   92: iconst_1
    //   93: anewarray 82	java/lang/String
    //   96: dup
    //   97: iconst_0
    //   98: aload_2
    //   99: aastore
    //   100: invokevirtual 372	com/microsoft/onlineid/internal/storage/TypedStorage:removeFromCollection	(Ljava/lang/String;[Ljava/lang/String;)V
    //   103: aload_3
    //   104: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	105	0	this	TypedStorage
    //   0	105	1	paramString1	String
    //   0	105	2	paramString2	String
    //   0	105	3	paramISerializer	ISerializer<T>
    //   11	40	4	localISerializer	ISerializer<T>
    //   8	12	5	str	String
    // Exception table:
    //   from	to	target	type
    //   18	27	53	java/io/IOException
    //   18	27	89	finally
    //   54	74	89	finally
  }
  
  public long readLastBackupPushedTime()
  {
    return this._storage.readLong("LastBackupPushedTime", 0L);
  }
  
  public long readLastBackupReceivedTime()
  {
    return this._storage.readLong("LastBackupReceivedTime", 0L);
  }
  
  public String readSdkVersion()
  {
    return this._storage.readString("SdkVersion");
  }
  
  public void removeAccount(String paramString)
  {
    removeFromCollection("Accounts", new String[] { constructAccountKey(paramString) });
    removeTickets(paramString);
  }
  
  protected void removeCollection(String paramString)
  {
    synchronized (CollectionLock)
    {
      Storage.Editor localEditor = this._storage.edit();
      replaceCollection(paramString, null, localEditor);
      localEditor.apply();
      return;
    }
  }
  
  protected void removeFromCollection(String paramString, Collection<String> paramCollection)
  {
    if (!paramCollection.isEmpty())
    {
      Storage.Editor localEditor = this._storage.edit();
      ??? = paramCollection.iterator();
      while (((Iterator)???).hasNext()) {
        localEditor.remove((String)((Iterator)???).next());
      }
      synchronized (CollectionLock)
      {
        HashSet localHashSet = new HashSet(this._storage.readStringSet(paramString));
        localHashSet.removeAll(paramCollection);
        if (localHashSet.isEmpty())
        {
          localEditor.remove(paramString);
          localEditor.apply();
          return;
        }
        localEditor.writeStringSet(paramString, localHashSet);
      }
    }
  }
  
  protected void removeFromCollection(String paramString, String... paramVarArgs)
  {
    removeFromCollection(paramString, Arrays.asList(paramVarArgs));
  }
  
  void removeTicket(String paramString1, String paramString2, ISecurityScope paramISecurityScope)
  {
    removeFromCollection(constructTicketCollectionKey(paramString1), new String[] { constructTicketKey(paramString1, paramString2, paramISecurityScope) });
  }
  
  void removeTickets(String paramString)
  {
    removeCollection(constructTicketCollectionKey(paramString));
  }
  
  /* Error */
  protected <T> void replaceCollection(String paramString, Map<String, T> arg2, ISerializer<T> paramISerializer)
  {
    // Byte code:
    //   0: aload_3
    //   1: aload_2
    //   2: invokeinterface 419 2 0
    //   7: astore_3
    //   8: getstatic 55	com/microsoft/onlineid/internal/storage/TypedStorage:CollectionLock	Ljava/lang/Object;
    //   11: astore_2
    //   12: aload_2
    //   13: monitorenter
    //   14: aload_0
    //   15: getfield 71	com/microsoft/onlineid/internal/storage/TypedStorage:_storage	Lcom/microsoft/onlineid/internal/storage/Storage;
    //   18: invokevirtual 184	com/microsoft/onlineid/internal/storage/Storage:edit	()Lcom/microsoft/onlineid/internal/storage/Storage$Editor;
    //   21: astore 4
    //   23: aload_0
    //   24: aload_1
    //   25: aload_3
    //   26: aload 4
    //   28: invokespecial 392	com/microsoft/onlineid/internal/storage/TypedStorage:replaceCollection	(Ljava/lang/String;Ljava/util/Map;Lcom/microsoft/onlineid/internal/storage/Storage$Editor;)V
    //   31: aload 4
    //   33: invokevirtual 194	com/microsoft/onlineid/internal/storage/Storage$Editor:apply	()V
    //   36: aload_2
    //   37: monitorexit
    //   38: return
    //   39: astore_1
    //   40: new 421	com/microsoft/onlineid/internal/storage/StorageException
    //   43: dup
    //   44: aload_1
    //   45: invokespecial 424	com/microsoft/onlineid/internal/storage/StorageException:<init>	(Ljava/lang/Throwable;)V
    //   48: athrow
    //   49: astore_1
    //   50: aload_2
    //   51: monitorexit
    //   52: aload_1
    //   53: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	54	0	this	TypedStorage
    //   0	54	1	paramString	String
    //   0	54	3	paramISerializer	ISerializer<T>
    //   21	11	4	localEditor	Storage.Editor
    // Exception table:
    //   from	to	target	type
    //   0	8	39	java/io/IOException
    //   14	38	49	finally
    //   50	52	49	finally
  }
  
  public Bundle retrieveBackup()
  {
    Bundle localBundle = new Bundle();
    DeviceIdentity localDeviceIdentity = readDeviceIdentity();
    if (localDeviceIdentity != null) {
      localBundle.putBundle("com.microsoft.onlineid.backup_device", BundleMarshaller.deviceAccountToBundle(localDeviceIdentity));
    }
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = readAllAccounts().iterator();
    while (localIterator.hasNext())
    {
      AuthenticatorUserAccount localAuthenticatorUserAccount = (AuthenticatorUserAccount)localIterator.next();
      localArrayList.add(BundleMarshaller.userAccountToBundle(localAuthenticatorUserAccount));
      if (localDeviceIdentity == null)
      {
        if (!hasTickets(localAuthenticatorUserAccount.getPuid())) {}
        for (boolean bool = true;; bool = false)
        {
          Assertion.check(bool);
          break;
        }
      }
    }
    if (!localArrayList.isEmpty()) {
      localBundle.putParcelableArrayList("com.microsoft.onlineid.backup_users", localArrayList);
    }
    return localBundle;
  }
  
  public void storeBackup(Bundle paramBundle)
    throws BundleMarshallerException
  {
    Object localObject1 = paramBundle.getBundle("com.microsoft.onlineid.backup_device");
    if (localObject1 != null) {}
    for (localObject1 = BundleMarshaller.deviceAccountFromBundle((Bundle)localObject1);; localObject1 = null)
    {
      Object localObject3;
      try
      {
        localObject1 = getDeviceIdentitySerializer().serialize(localObject1);
        localObject3 = paramBundle.getParcelableArrayList("com.microsoft.onlineid.backup_users");
        paramBundle = new HashMap();
        ??? = getAccountSerializer();
        if (localObject3 != null)
        {
          localObject3 = ((List)localObject3).iterator();
          while (((Iterator)localObject3).hasNext())
          {
            Object localObject4 = (Bundle)((Iterator)localObject3).next();
            try
            {
              localObject4 = BundleMarshaller.userAccountFromBundle((Bundle)localObject4);
            }
            catch (BundleMarshallerException localBundleMarshallerException)
            {
              try
              {
                String str = ((ISerializer)???).serialize(localObject4);
                paramBundle.put(constructAccountKey(((AuthenticatorUserAccount)localObject4).getPuid()), str);
              }
              catch (IOException localIOException)
              {
                throw new StorageException(localIOException);
              }
              localBundleMarshallerException = localBundleMarshallerException;
              Logger.error("Encountered an error while trying to unbundle accounts.", localBundleMarshallerException);
              ClientAnalytics.get().logException(localBundleMarshallerException);
            }
          }
        }
        synchronized (CollectionLock)
        {
          localObject3 = this._storage.edit();
          if (localObject1 != null) {
            ((Storage.Editor)localObject3).writeString("Device", (String)localObject1);
          }
          localObject1 = this._storage.readStringSet("Accounts").iterator();
          if (((Iterator)localObject1).hasNext()) {
            replaceCollection(constructTicketCollectionKeyFromAccountKey((String)((Iterator)localObject1).next()), null, (Storage.Editor)localObject3);
          }
        }
      }
      catch (IOException paramBundle)
      {
        throw new StorageException(paramBundle);
      }
      replaceCollection("Accounts", paramBundle, (Storage.Editor)localObject3);
      ((Storage.Editor)localObject3).writeLong("LastBackupReceivedTime", System.currentTimeMillis());
      ((Storage.Editor)localObject3).apply();
      return;
    }
  }
  
  void storeTicket(String paramString1, String paramString2, Ticket paramTicket)
  {
    writeToCollection(constructTicketCollectionKey(paramString1), constructTicketKey(paramString1, paramString2, paramTicket.getScope()), paramTicket, getTicketSerializer());
  }
  
  public void writeAccount(AuthenticatorUserAccount paramAuthenticatorUserAccount)
  {
    Strings.verifyArgumentNotNullOrEmpty(paramAuthenticatorUserAccount.getPuid(), "account.PUID");
    writeToCollection("Accounts", constructAccountKey(paramAuthenticatorUserAccount.getPuid()), paramAuthenticatorUserAccount, getAccountSerializer());
  }
  
  public void writeClockSkew(long paramLong)
  {
    this._storage.edit().writeLong("ClockSkew", paramLong).apply();
  }
  
  public void writeConfigLastDownloadedTime()
  {
    this._storage.edit().writeLong("ConfigLastDownloadedTime", System.currentTimeMillis()).apply();
  }
  
  public void writeDeviceBasedFlights(Set<Integer> paramSet)
  {
    HashSet localHashSet = new HashSet(paramSet.size());
    paramSet = paramSet.iterator();
    while (paramSet.hasNext()) {
      localHashSet.add(((Integer)paramSet.next()).toString());
    }
    this._storage.edit().writeStringSet("DeviceBasedFlights", localHashSet).apply();
  }
  
  public void writeDeviceFlightOverrideEnabled(boolean paramBoolean)
  {
    this._storage.edit().writeBoolean("DeviceFlightOverride", paramBoolean).apply();
  }
  
  public void writeDeviceIdentity(DeviceIdentity paramDeviceIdentity)
  {
    this._storage.edit().writeObject("Device", paramDeviceIdentity, getDeviceIdentitySerializer()).apply();
  }
  
  public void writeLastBackupPushedTime()
  {
    this._storage.edit().writeLong("LastBackupPushedTime", System.currentTimeMillis()).apply();
  }
  
  public void writeLastBackupReceivedTime()
  {
    this._storage.edit().writeLong("LastBackupReceivedTime", System.currentTimeMillis()).apply();
  }
  
  public void writeSdkVersion(String paramString)
  {
    this._storage.edit().writeString("SdkVersion", paramString).apply();
  }
  
  /* Error */
  protected <T> void writeToCollection(String paramString1, String paramString2, T arg3, ISerializer<T> paramISerializer)
  {
    // Byte code:
    //   0: aload_3
    //   1: ifnull +103 -> 104
    //   4: iconst_1
    //   5: istore 5
    //   7: iload 5
    //   9: ldc_w 557
    //   12: invokestatic 265	com/microsoft/onlineid/internal/Assertion:check	(ZLjava/lang/Object;)V
    //   15: aload 4
    //   17: aload_3
    //   18: invokeinterface 485 2 0
    //   23: astore 4
    //   25: getstatic 55	com/microsoft/onlineid/internal/storage/TypedStorage:CollectionLock	Ljava/lang/Object;
    //   28: astore_3
    //   29: aload_3
    //   30: monitorenter
    //   31: aload_0
    //   32: getfield 71	com/microsoft/onlineid/internal/storage/TypedStorage:_storage	Lcom/microsoft/onlineid/internal/storage/Storage;
    //   35: aload_1
    //   36: invokevirtual 130	com/microsoft/onlineid/internal/storage/Storage:readStringSet	(Ljava/lang/String;)Ljava/util/Set;
    //   39: astore 7
    //   41: aload_0
    //   42: getfield 71	com/microsoft/onlineid/internal/storage/TypedStorage:_storage	Lcom/microsoft/onlineid/internal/storage/Storage;
    //   45: invokevirtual 184	com/microsoft/onlineid/internal/storage/Storage:edit	()Lcom/microsoft/onlineid/internal/storage/Storage$Editor;
    //   48: astore 6
    //   50: aload 7
    //   52: aload_2
    //   53: invokeinterface 560 2 0
    //   58: ifne +32 -> 90
    //   61: new 247	java/util/HashSet
    //   64: dup
    //   65: aload 7
    //   67: invokespecial 250	java/util/HashSet:<init>	(Ljava/util/Collection;)V
    //   70: astore 7
    //   72: aload 7
    //   74: aload_2
    //   75: invokeinterface 561 2 0
    //   80: pop
    //   81: aload 6
    //   83: aload_1
    //   84: aload 7
    //   86: invokevirtual 177	com/microsoft/onlineid/internal/storage/Storage$Editor:writeStringSet	(Ljava/lang/String;Ljava/util/Set;)Lcom/microsoft/onlineid/internal/storage/Storage$Editor;
    //   89: pop
    //   90: aload 6
    //   92: aload_2
    //   93: aload 4
    //   95: invokevirtual 170	com/microsoft/onlineid/internal/storage/Storage$Editor:writeString	(Ljava/lang/String;Ljava/lang/String;)Lcom/microsoft/onlineid/internal/storage/Storage$Editor;
    //   98: invokevirtual 194	com/microsoft/onlineid/internal/storage/Storage$Editor:apply	()V
    //   101: aload_3
    //   102: monitorexit
    //   103: return
    //   104: iconst_0
    //   105: istore 5
    //   107: goto -100 -> 7
    //   110: astore_1
    //   111: new 421	com/microsoft/onlineid/internal/storage/StorageException
    //   114: dup
    //   115: aload_1
    //   116: invokespecial 424	com/microsoft/onlineid/internal/storage/StorageException:<init>	(Ljava/lang/Throwable;)V
    //   119: athrow
    //   120: astore_1
    //   121: aload_3
    //   122: monitorexit
    //   123: aload_1
    //   124: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	125	0	this	TypedStorage
    //   0	125	1	paramString1	String
    //   0	125	2	paramString2	String
    //   0	125	4	paramISerializer	ISerializer<T>
    //   5	101	5	bool	boolean
    //   48	43	6	localEditor	Storage.Editor
    //   39	46	7	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   15	25	110	java/io/IOException
    //   31	90	120	finally
    //   90	103	120	finally
    //   121	123	120	finally
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\internal\storage\TypedStorage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */