package com.amazon.device.iap.internal.b;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import com.amazon.android.framework.context.ContextManager;
import com.amazon.android.framework.prompt.PromptContent;
import com.amazon.android.framework.prompt.SimplePrompt;
import com.amazon.android.framework.resource.Resource;
import com.amazon.device.iap.internal.util.e;

public class b
  extends SimplePrompt
{
  private static final String a = b.class.getSimpleName();
  @Resource
  private ContextManager b;
  private final PromptContent c;
  
  public b(PromptContent paramPromptContent)
  {
    super(paramPromptContent);
    this.c = paramPromptContent;
  }
  
  protected void doAction()
  {
    e.a(a, "doAction");
    if (("Amazon Appstore required".equalsIgnoreCase(this.c.getTitle())) || ("Amazon Appstore Update Required".equalsIgnoreCase(this.c.getTitle()))) {}
    try
    {
      Activity localActivity2 = this.b.getVisible();
      Activity localActivity1 = localActivity2;
      if (localActivity2 == null) {
        localActivity1 = this.b.getRoot();
      }
      localActivity1.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://www.amazon.com/gp/mas/get-appstore/android/ref=mas_mx_mba_iap_dl")));
      return;
    }
    catch (Exception localException)
    {
      e.b(a, "Exception in PurchaseItemCommandTask.OnSuccess: " + localException);
    }
  }
  
  protected long getExpirationDurationInSeconds()
  {
    return 31536000L;
  }
  
  public String toString()
  {
    return a;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\device\iap\internal\b\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */