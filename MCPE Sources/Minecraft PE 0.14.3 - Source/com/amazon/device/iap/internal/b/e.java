package com.amazon.device.iap.internal.b;

import android.content.Context;
import android.os.Handler;
import com.amazon.device.iap.PurchasingListener;
import com.amazon.device.iap.internal.util.b;
import com.amazon.device.iap.model.ProductDataResponse;
import com.amazon.device.iap.model.PurchaseResponse;
import com.amazon.device.iap.model.PurchaseUpdatesResponse;
import com.amazon.device.iap.model.RequestId;
import com.amazon.device.iap.model.UserData;
import com.amazon.device.iap.model.UserDataResponse;

public class e
{
  private static final String a = e.class.getSimpleName();
  private final RequestId b;
  private final h c;
  private i d;
  
  public e(RequestId paramRequestId)
  {
    this.b = paramRequestId;
    this.c = new h();
    this.d = null;
  }
  
  public void a() {}
  
  protected void a(i parami)
  {
    this.d = parami;
  }
  
  protected void a(Object paramObject)
  {
    a(paramObject, null);
  }
  
  protected void a(final Object paramObject, final i parami)
  {
    com.amazon.device.iap.internal.util.d.a(paramObject, "response");
    Context localContext = com.amazon.device.iap.internal.d.d().b();
    final PurchasingListener localPurchasingListener = com.amazon.device.iap.internal.d.d().a();
    if ((localContext == null) || (localPurchasingListener == null))
    {
      com.amazon.device.iap.internal.util.e.a(a, "PurchasingListener is not set. Dropping response: " + paramObject);
      return;
    }
    paramObject = new Runnable()
    {
      public void run()
      {
        e.this.d().a("notifyListenerResult", Boolean.FALSE);
        try
        {
          if ((paramObject instanceof ProductDataResponse)) {
            localPurchasingListener.onProductDataResponse((ProductDataResponse)paramObject);
          }
          for (;;)
          {
            e.this.d().a("notifyListenerResult", Boolean.TRUE);
            if (parami != null)
            {
              parami.a(true);
              parami.a_();
            }
            return;
            if (!(paramObject instanceof UserDataResponse)) {
              break;
            }
            localPurchasingListener.onUserDataResponse((UserDataResponse)paramObject);
          }
        }
        catch (Throwable localThrowable)
        {
          for (;;)
          {
            com.amazon.device.iap.internal.util.e.b(e.f(), "Error in sendResponse: " + localThrowable);
            continue;
            if ((paramObject instanceof PurchaseUpdatesResponse))
            {
              PurchaseUpdatesResponse localPurchaseUpdatesResponse = (PurchaseUpdatesResponse)paramObject;
              localPurchasingListener.onPurchaseUpdatesResponse(localPurchaseUpdatesResponse);
              Object localObject = e.this.d().a("newCursor");
              if ((localObject != null) && ((localObject instanceof String))) {
                b.a(localPurchaseUpdatesResponse.getUserData().getUserId(), localObject.toString());
              }
            }
            else if ((paramObject instanceof PurchaseResponse))
            {
              localPurchasingListener.onPurchaseResponse((PurchaseResponse)paramObject);
            }
            else
            {
              com.amazon.device.iap.internal.util.e.b(e.f(), "Unknown response type:" + paramObject.getClass().getName());
            }
          }
        }
      }
    };
    new Handler(localContext.getMainLooper()).post((Runnable)paramObject);
  }
  
  public void b() {}
  
  public RequestId c()
  {
    return this.b;
  }
  
  public h d()
  {
    return this.c;
  }
  
  public void e()
  {
    if (this.d != null)
    {
      this.d.a_();
      return;
    }
    a();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\device\iap\internal\b\e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */