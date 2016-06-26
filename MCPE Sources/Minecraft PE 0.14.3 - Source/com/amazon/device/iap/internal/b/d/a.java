package com.amazon.device.iap.internal.b.d;

import com.amazon.device.iap.internal.b.e;
import com.amazon.device.iap.internal.b.h;
import com.amazon.device.iap.internal.b.i;
import com.amazon.device.iap.internal.model.PurchaseUpdatesResponseBuilder;
import com.amazon.device.iap.model.PurchaseUpdatesResponse;
import com.amazon.device.iap.model.PurchaseUpdatesResponse.RequestStatus;
import com.amazon.device.iap.model.Receipt;
import com.amazon.device.iap.model.RequestId;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public final class a
  extends e
{
  public a(RequestId paramRequestId, boolean paramBoolean)
  {
    super(paramRequestId);
    paramRequestId = new com.amazon.device.iap.internal.b.e.c(this);
    paramRequestId.a(new c(this, paramBoolean));
    com.amazon.device.iap.internal.b.e.d locald = new com.amazon.device.iap.internal.b.e.d(this);
    locald.a(new d(this));
    paramRequestId.b(locald);
    a(paramRequestId);
  }
  
  public void a()
  {
    Iterator localIterator = null;
    PurchaseUpdatesResponse localPurchaseUpdatesResponse = (PurchaseUpdatesResponse)d().a();
    Object localObject = localIterator;
    if (localPurchaseUpdatesResponse.getReceipts() != null)
    {
      localObject = localIterator;
      if (localPurchaseUpdatesResponse.getReceipts().size() > 0)
      {
        localObject = new HashSet();
        localIterator = localPurchaseUpdatesResponse.getReceipts().iterator();
        while (localIterator.hasNext())
        {
          Receipt localReceipt = (Receipt)localIterator.next();
          if (!com.amazon.device.iap.internal.util.d.a(localReceipt.getReceiptId())) {
            ((Set)localObject).add(localReceipt.getReceiptId());
          }
        }
        localObject = new com.amazon.device.iap.internal.b.g.a(this, (Set)localObject, com.amazon.device.iap.internal.model.a.a.toString());
      }
    }
    a(localPurchaseUpdatesResponse, (i)localObject);
  }
  
  public void b()
  {
    Object localObject = d().a();
    if ((localObject == null) || (!(localObject instanceof PurchaseUpdatesResponse))) {}
    for (localObject = new PurchaseUpdatesResponseBuilder().setRequestId(c()).setRequestStatus(PurchaseUpdatesResponse.RequestStatus.FAILED).build();; localObject = (PurchaseUpdatesResponse)localObject)
    {
      a(localObject);
      return;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\device\iap\internal\b\d\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */