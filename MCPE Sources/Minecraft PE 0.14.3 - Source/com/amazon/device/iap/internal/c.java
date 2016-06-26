package com.amazon.device.iap.internal;

import android.content.Context;
import android.content.Intent;
import com.amazon.device.iap.model.FulfillmentResult;
import com.amazon.device.iap.model.RequestId;
import java.util.Set;

public abstract interface c
{
  public abstract void a(Context paramContext, Intent paramIntent);
  
  public abstract void a(RequestId paramRequestId);
  
  public abstract void a(RequestId paramRequestId, String paramString);
  
  public abstract void a(RequestId paramRequestId, String paramString, FulfillmentResult paramFulfillmentResult);
  
  public abstract void a(RequestId paramRequestId, Set<String> paramSet);
  
  public abstract void a(RequestId paramRequestId, boolean paramBoolean);
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\device\iap\internal\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */