package com.microsoft.xbox.service.network.managers;

public class AddFollowingUserResponseContainer
{
  public static class AddFollowingUserResponse
  {
    public int code;
    public String description;
    private boolean success;
    
    public boolean getAddFollowingRequestStatus()
    {
      return this.success;
    }
    
    public void setAddFollowingRequestStatus(boolean paramBoolean)
    {
      this.success = paramBoolean;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\service\network\managers\AddFollowingUserResponseContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */