package net.zhuoweizhang.mcpelauncher.ui;

import android.content.Context;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import de.ankri.views.Switch;
import java.io.PrintStream;
import net.zhuoweizhang.mcpelauncher.R.id;
import net.zhuoweizhang.mcpelauncher.R.layout;

public class SwitchPreference
  extends Preference
  implements CompoundButton.OnCheckedChangeListener
{
  public Switch content = null;
  protected OnCheckedChangeListener listener = null;
  
  public SwitchPreference(Context paramContext)
  {
    super(paramContext);
    setWidgetLayoutResource(R.layout.switch_preference);
  }
  
  public SwitchPreference(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    setWidgetLayoutResource(R.layout.switch_preference);
  }
  
  public SwitchPreference(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    setWidgetLayoutResource(R.layout.switch_preference);
  }
  
  protected void onBindView(View paramView)
  {
    super.onBindView(paramView);
    this.content = ((Switch)paramView.findViewById(R.id.switch_widget));
    if (this.content != null)
    {
      this.content.setChecked(getPersistedBoolean(false));
      this.content.setOnCheckedChangeListener(this);
      return;
    }
    System.err.println("SwitchPreference Switch is null");
  }
  
  public void onCheckedChanged(CompoundButton paramCompoundButton, boolean paramBoolean)
  {
    persistBoolean(paramBoolean);
    if (this.listener != null) {
      this.listener.onCheckedChange(this.content);
    }
  }
  
  public void setListener(OnCheckedChangeListener paramOnCheckedChangeListener)
  {
    this.listener = paramOnCheckedChangeListener;
  }
  
  public static abstract interface OnCheckedChangeListener
  {
    public abstract void onCheckedChange(Switch paramSwitch);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\net\zhuoweizhang\mcpelauncher\ui\SwitchPreference.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */