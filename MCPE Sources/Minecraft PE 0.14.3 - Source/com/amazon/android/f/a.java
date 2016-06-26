package com.amazon.android.f;

import android.app.Activity;
import android.content.Intent;
import com.amazon.android.framework.util.KiwiLogger;

final class a
{
  final int a;
  Activity b;
  private final Intent c;
  
  public a(Intent paramIntent, int paramInt)
  {
    this.c = paramIntent;
    this.a = paramInt;
  }
  
  public final void a(Activity paramActivity)
  {
    c.a().trace("Calling startActivityForResult from: " + paramActivity);
    paramActivity.startActivityForResult(this.c, this.a);
    this.b = paramActivity;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\f\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */