package com.microsoft.xbox.toolkit.ui;

import com.microsoft.xbox.service.model.friendfinder.FriendFinderType;
import java.util.HashMap;

public class ActivityParameters
  extends HashMap<String, Object>
{
  private static final String FF_DONE = "FriendFinderDone";
  private static final String FF_INFO_TYPE = "InfoType";
  private static final String FORCE_RELOAD = "ForceReload";
  private static final String FROM_SCREEN = "FromScreen";
  private static final String ME_XUID = "MeXuid";
  private static final String ORIGINATING_PAGE = "OriginatingPage";
  private static final String PRIVILEGES = "Privileges";
  private static final String SELECTED_PROFILE = "SelectedProfile";
  
  public boolean getFriendFinderDone()
  {
    Boolean localBoolean = (Boolean)get("FriendFinderDone");
    return (localBoolean != null) && (localBoolean.booleanValue());
  }
  
  public FriendFinderType getFriendFinderType()
  {
    FriendFinderType localFriendFinderType2 = (FriendFinderType)get("InfoType");
    FriendFinderType localFriendFinderType1 = localFriendFinderType2;
    if (localFriendFinderType2 == null) {
      localFriendFinderType1 = FriendFinderType.UNKNOWN;
    }
    return localFriendFinderType1;
  }
  
  public String getMeXuid()
  {
    return (String)get("MeXuid");
  }
  
  public String getPrivileges()
  {
    return (String)get("Privileges");
  }
  
  public String getSelectedProfile()
  {
    return (String)get("SelectedProfile");
  }
  
  public boolean isForceReload()
  {
    if (containsKey("ForceReload")) {
      return ((Boolean)get("ForceReload")).booleanValue();
    }
    return false;
  }
  
  public void putFriendFinderDone(boolean paramBoolean)
  {
    put("FriendFinderDone", Boolean.valueOf(paramBoolean));
  }
  
  public void putFriendFinderType(FriendFinderType paramFriendFinderType)
  {
    put("InfoType", paramFriendFinderType);
  }
  
  public void putFromScreen(ScreenLayout paramScreenLayout)
  {
    put("FromScreen", paramScreenLayout);
  }
  
  public void putMeXuid(String paramString)
  {
    put("MeXuid", paramString);
  }
  
  public void putPrivileges(String paramString)
  {
    put("Privileges", paramString);
  }
  
  public void putSelectedProfile(String paramString)
  {
    put("SelectedProfile", paramString);
  }
  
  public void putSourcePage(String paramString)
  {
    put("OriginatingPage", paramString);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\toolkit\ui\ActivityParameters.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */