package com.amazon.android.f;

import android.app.Activity;
import android.content.Intent;

public final class f
{
  final int a;
  public final int b;
  private final Activity c;
  private final Intent d;
  
  public f(Activity paramActivity, int paramInt1, int paramInt2, Intent paramIntent)
  {
    this.c = paramActivity;
    this.a = paramInt1;
    this.b = paramInt2;
    this.d = paramIntent;
  }
  
  public final String toString()
  {
    return "ActivtyResult: [ requestCode: " + this.a + ", resultCode: " + this.b + ", activity: " + this.c + ", intent: " + this.d + "]";
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\f\f.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */