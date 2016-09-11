package com.microsoft.xbox.service.model;

import com.microsoft.xbox.toolkit.JavaUtil;

public enum UserStatus
{
  Offline,  Online;
  
  private UserStatus() {}
  
  public static UserStatus getStatusFromString(String paramString)
  {
    if (JavaUtil.stringsEqualCaseInsensitive(paramString, Online.toString())) {
      return Online;
    }
    return Offline;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\service\model\UserStatus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */