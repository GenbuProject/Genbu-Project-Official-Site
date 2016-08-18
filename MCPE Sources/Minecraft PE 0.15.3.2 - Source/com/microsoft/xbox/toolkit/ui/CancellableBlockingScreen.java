package com.microsoft.xbox.toolkit.ui;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;
import com.microsoft.xbox.toolkit.XLERValueHelper;

public class CancellableBlockingScreen
  extends Dialog
{
  private XLEButton cancelButton;
  private View container;
  private TextView statusText;
  
  public CancellableBlockingScreen(Context paramContext)
  {
    super(paramContext, XLERValueHelper.getStyleRValue("cancellable_dialog_style"));
    setCancelable(false);
    setOnCancelListener(null);
    requestWindowFeature(1);
    setContentView(XLERValueHelper.getLayoutRValue("cancellable_blocking_dialog"));
    this.container = findViewById(XLERValueHelper.getIdRValue("blocking_dialog_container"));
    this.cancelButton = ((XLEButton)findViewById(XLERValueHelper.getIdRValue("blocking_dialog_cancel")));
    this.statusText = ((TextView)findViewById(XLERValueHelper.getIdRValue("blocking_dialog_status_text")));
  }
  
  public void setCancelButtonAction(View.OnClickListener paramOnClickListener)
  {
    if (paramOnClickListener != null) {
      this.cancelButton.setOnClickListener(null);
    }
    this.cancelButton.setOnClickListener(paramOnClickListener);
  }
  
  public void setMessage(CharSequence paramCharSequence)
  {
    this.statusText.setText(paramCharSequence);
  }
  
  public void show(Context paramContext, CharSequence paramCharSequence)
  {
    boolean bool = isShowing();
    setMessage(paramCharSequence);
    show();
    if (!bool)
    {
      paramContext = new AlphaAnimation(0.0F, 1.0F);
      paramContext.setFillAfter(true);
      paramContext.setStartOffset(1000L);
      paramContext.setDuration(1000L);
      this.container.startAnimation(paramContext);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\toolkit\ui\CancellableBlockingScreen.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */