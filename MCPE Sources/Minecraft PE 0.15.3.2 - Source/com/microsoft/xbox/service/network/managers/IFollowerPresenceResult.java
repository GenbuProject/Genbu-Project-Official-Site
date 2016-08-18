package com.microsoft.xbox.service.network.managers;

import com.microsoft.xbox.service.model.serialization.UTCDateConverterGson.UTCDateConverterJSONDeserializer;
import com.microsoft.xbox.toolkit.GsonUtil;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;

public abstract interface IFollowerPresenceResult
{
  public static class ActivityRecord
  {
    public IFollowerPresenceResult.BroadcastRecord broadcast;
    public String richPresence;
  }
  
  public static class BroadcastRecord
  {
    public String id;
    public String provider;
    public String session;
    public int viewers;
  }
  
  public static class DeviceRecord
  {
    public ArrayList<IFollowerPresenceResult.TitleRecord> titles;
    public String type;
    
    public boolean isXbox360()
    {
      return "Xbox360".equalsIgnoreCase(this.type);
    }
    
    public boolean isXboxOne()
    {
      return "XboxOne".equalsIgnoreCase(this.type);
    }
  }
  
  public static class FollowersPresenceResult
  {
    public ArrayList<IFollowerPresenceResult.UserPresence> userPresence;
    
    public static FollowersPresenceResult deserialize(InputStream paramInputStream)
    {
      Object localObject = null;
      IFollowerPresenceResult.UserPresence[] arrayOfUserPresence = (IFollowerPresenceResult.UserPresence[])GsonUtil.deserializeJson(paramInputStream, IFollowerPresenceResult.UserPresence[].class, Date.class, new UTCDateConverterGson.UTCDateConverterJSONDeserializer());
      paramInputStream = (InputStream)localObject;
      if (arrayOfUserPresence != null)
      {
        paramInputStream = new FollowersPresenceResult();
        paramInputStream.userPresence = new ArrayList(Arrays.asList(arrayOfUserPresence));
      }
      return paramInputStream;
    }
  }
  
  public static class LastSeenRecord
  {
    public String deviceType;
    public String titleName;
  }
  
  public static class TitleRecord
  {
    public IFollowerPresenceResult.ActivityRecord activity;
    public long id;
    public Date lastModified;
    public String name;
    public String placement;
    
    public boolean isDash()
    {
      return this.id == 4294838225L;
    }
    
    public boolean isRunningInFullOrFill()
    {
      return ("Full".equalsIgnoreCase(this.placement)) || ("Fill".equalsIgnoreCase(this.placement));
    }
  }
  
  public static class UserPresence
  {
    private IFollowerPresenceResult.BroadcastRecord broadcastRecord;
    private boolean broadcastRecordSet;
    public ArrayList<IFollowerPresenceResult.DeviceRecord> devices;
    public IFollowerPresenceResult.LastSeenRecord lastSeen;
    public String state;
    public String xuid;
    
    public IFollowerPresenceResult.BroadcastRecord getBroadcastRecord(long paramLong)
    {
      if (!this.broadcastRecordSet)
      {
        if ("Online".equalsIgnoreCase(this.state))
        {
          IFollowerPresenceResult.TitleRecord localTitleRecord;
          do
          {
            Iterator localIterator = this.devices.iterator();
            Object localObject;
            while (!((Iterator)localObject).hasNext())
            {
              do
              {
                if (!localIterator.hasNext()) {
                  break;
                }
                localObject = (IFollowerPresenceResult.DeviceRecord)localIterator.next();
              } while (!((IFollowerPresenceResult.DeviceRecord)localObject).isXboxOne());
              localObject = ((IFollowerPresenceResult.DeviceRecord)localObject).titles.iterator();
            }
            localTitleRecord = (IFollowerPresenceResult.TitleRecord)((Iterator)localObject).next();
          } while ((localTitleRecord.id != paramLong) || (!localTitleRecord.isRunningInFullOrFill()) || (localTitleRecord.activity == null) || (localTitleRecord.activity.broadcast == null));
          this.broadcastRecord = localTitleRecord.activity.broadcast;
        }
        this.broadcastRecordSet = true;
      }
      return this.broadcastRecord;
    }
    
    public int getBroadcastingViewerCount(long paramLong)
    {
      IFollowerPresenceResult.BroadcastRecord localBroadcastRecord = getBroadcastRecord(paramLong);
      if (localBroadcastRecord == null) {
        return 0;
      }
      return localBroadcastRecord.viewers;
    }
    
    public Date getXboxOneNowPlayingDate()
    {
      Object localObject = null;
      Date localDate = null;
      if ("Online".equalsIgnoreCase(this.state))
      {
        Iterator localIterator = this.devices.iterator();
        for (;;)
        {
          localObject = localDate;
          if (!localIterator.hasNext()) {
            return localObject;
          }
          localObject = (IFollowerPresenceResult.DeviceRecord)localIterator.next();
          if (((IFollowerPresenceResult.DeviceRecord)localObject).isXboxOne())
          {
            localObject = ((IFollowerPresenceResult.DeviceRecord)localObject).titles.iterator();
            if (((Iterator)localObject).hasNext())
            {
              IFollowerPresenceResult.TitleRecord localTitleRecord = (IFollowerPresenceResult.TitleRecord)((Iterator)localObject).next();
              if (!localTitleRecord.isRunningInFullOrFill()) {
                break;
              }
              localDate = localTitleRecord.lastModified;
            }
          }
        }
      }
      return (Date)localObject;
    }
    
    public long getXboxOneNowPlayingTitleId()
    {
      long l1 = -1L;
      long l2 = l1;
      if ("Online".equalsIgnoreCase(this.state))
      {
        Iterator localIterator = this.devices.iterator();
        for (;;)
        {
          l2 = l1;
          if (!localIterator.hasNext()) {
            return l2;
          }
          Object localObject = (IFollowerPresenceResult.DeviceRecord)localIterator.next();
          if (((IFollowerPresenceResult.DeviceRecord)localObject).isXboxOne())
          {
            localObject = ((IFollowerPresenceResult.DeviceRecord)localObject).titles.iterator();
            if (((Iterator)localObject).hasNext())
            {
              IFollowerPresenceResult.TitleRecord localTitleRecord = (IFollowerPresenceResult.TitleRecord)((Iterator)localObject).next();
              if (!localTitleRecord.isRunningInFullOrFill()) {
                break;
              }
              l1 = localTitleRecord.id;
            }
          }
        }
      }
      return l2;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\service\network\managers\IFollowerPresenceResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */