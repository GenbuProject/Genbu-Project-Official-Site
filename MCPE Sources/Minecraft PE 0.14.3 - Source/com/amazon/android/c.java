package com.amazon.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.amazon.android.framework.util.KiwiLogger;
import com.amazon.android.o.g;

final class c
  extends BroadcastReceiver
{
  c(Kiwi paramKiwi) {}
  
  public final void onReceive(Context paramContext, Intent paramIntent)
  {
    if (Kiwi.a(this.a).b("TEST_MODE")) {
      return;
    }
    if (KiwiLogger.TRACE_ON) {
      Kiwi.a().trace("Enable test mode broadcast received!");
    }
    KiwiLogger.enableTest();
    Kiwi.a().test("Enabling test mode!");
    Kiwi.a().test("drm enabled: " + Kiwi.b(this.a));
    Kiwi.a(this.a).a("TEST_MODE", Boolean.valueOf(true));
    Kiwi.c(this.a).a(new a());
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */