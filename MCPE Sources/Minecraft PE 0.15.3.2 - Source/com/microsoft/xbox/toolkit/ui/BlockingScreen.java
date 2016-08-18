package com.microsoft.xbox.toolkit.ui;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;
import com.microsoft.xbox.toolkit.XLERValueHelper;

public class BlockingScreen
  extends Dialog
{
  public BlockingScreen(Context paramContext)
  {
    super(paramContext, XLERValueHelper.getStyleRValue("blocking_dialog_style"));
    requestWindowFeature(1);
  }
  
  public void setMessage(CharSequence paramCharSequence)
  {
    ((TextView)findViewById(XLERValueHelper.getIdRValue("blocking_dialog_status_text"))).setText(paramCharSequence);
  }
  
  public void show(Context paramContext, CharSequence paramCharSequence)
  {
    setCancelable(false);
    setOnCancelListener(null);
    setContentView(XLERValueHelper.getLayoutRValue("blocking_dialog"));
    setMessage(paramCharSequence);
    show();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\toolkit\ui\BlockingScreen.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */