package net.zhuoweizhang.mcpelauncher.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import net.zhuoweizhang.mcpelauncher.R.dimen;
import net.zhuoweizhang.mcpelauncher.R.id;
import net.zhuoweizhang.mcpelauncher.R.layout;

public class HoverCar
  extends PopupWindow
{
  public ImageButton mainButton;
  private Context theContext;
  
  public HoverCar(Context paramContext, boolean paramBoolean)
  {
    super(paramContext);
    this.theContext = paramContext;
    setContentView(((LayoutInflater)paramContext.getSystemService("layout_inflater")).inflate(R.layout.hovercar, null));
    this.mainButton = ((ImageButton)getContentView().findViewById(R.id.hovercar_main_button));
    float f1 = paramContext.getResources().getDimension(R.dimen.hovercar_width);
    float f2 = paramContext.getResources().getDimension(R.dimen.hovercar_height);
    setWidth((int)f1);
    setHeight((int)f2);
    if (paramBoolean) {}
    for (paramContext = new ColorDrawable(-2130771968);; paramContext = null)
    {
      setBackgroundDrawable(paramContext);
      return;
    }
  }
  
  public void setVisible(boolean paramBoolean)
  {
    ImageButton localImageButton = this.mainButton;
    if (paramBoolean) {}
    for (int i = 255;; i = 0)
    {
      localImageButton.setAlpha(i);
      update();
      return;
    }
  }
  
  public void show(View paramView)
  {
    showAtLocation(paramView, 49, (int)(this.theContext.getResources().getDimension(R.dimen.hovercar_width) * 1.5D), 0);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\net\zhuoweizhang\mcpelauncher\ui\HoverCar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */