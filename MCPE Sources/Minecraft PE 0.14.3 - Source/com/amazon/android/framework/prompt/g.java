package com.amazon.android.framework.prompt;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final class g
  implements DialogInterface.OnClickListener
{
  g(SimplePrompt paramSimplePrompt) {}
  
  public final void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    if (this.a.dismiss()) {
      this.a.doAction();
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\framework\prompt\g.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */