package com.microsoft.onlineid.internal.storage;

import android.content.Context;
import com.microsoft.onlineid.ISecurityScope;
import com.microsoft.onlineid.Ticket;
import com.microsoft.onlineid.internal.Objects;
import com.microsoft.onlineid.internal.Strings;
import com.microsoft.onlineid.sts.ClockSkewManager;
import java.util.Date;

public class TicketStorage
{
  private final ClockSkewManager _clockSkewManager;
  private TypedStorage _typedStorage;
  
  public TicketStorage(Context paramContext)
  {
    this._clockSkewManager = new ClockSkewManager(paramContext);
    this._typedStorage = new TypedStorage(paramContext);
  }
  
  static void checkCommonParameters(String paramString1, String paramString2)
  {
    Strings.verifyArgumentNotNullOrEmpty(paramString1, "accountId");
    Strings.verifyArgumentNotNullOrEmpty(paramString2, "appId");
  }
  
  public Ticket getTicket(String paramString1, String paramString2, ISecurityScope paramISecurityScope)
  {
    checkCommonParameters(paramString1, paramString2);
    Objects.verifyArgumentNotNull(paramISecurityScope, "scope");
    Ticket localTicket2 = this._typedStorage.getTicket(paramString1, paramString2, paramISecurityScope);
    Ticket localTicket1 = localTicket2;
    if (localTicket2 != null)
    {
      localTicket1 = localTicket2;
      if (!isTicketValid(localTicket2.getExpiry()))
      {
        this._typedStorage.removeTicket(paramString1, paramString2, paramISecurityScope);
        localTicket1 = null;
      }
    }
    return localTicket1;
  }
  
  boolean isTicketValid(Date paramDate)
  {
    return this._clockSkewManager.getCurrentServerTime().compareTo(paramDate) < 0;
  }
  
  public void removeTickets(String paramString)
  {
    Strings.verifyArgumentNotNullOrEmpty(paramString, "accountId");
    this._typedStorage.removeTickets(paramString);
  }
  
  void setTypedStorage(TypedStorage paramTypedStorage)
  {
    Objects.verifyArgumentNotNull(paramTypedStorage, "typedStorage");
    this._typedStorage = paramTypedStorage;
  }
  
  public void storeTicket(String paramString1, String paramString2, Ticket paramTicket)
  {
    checkCommonParameters(paramString1, paramString2);
    Objects.verifyArgumentNotNull(paramTicket, "ticket");
    if (isTicketValid(paramTicket.getExpiry())) {
      this._typedStorage.storeTicket(paramString1, paramString2, paramTicket);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\internal\storage\TicketStorage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */