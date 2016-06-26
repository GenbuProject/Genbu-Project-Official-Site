package net.zhuoweizhang.mcpelauncher.ui;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class ContentListAdapter
  extends ArrayAdapter<ContentListItem>
{
  private Resources mResources;
  
  public ContentListAdapter(Context paramContext, int paramInt, List<ContentListItem> paramList)
  {
    super(paramContext, paramInt, paramList);
    this.mResources = paramContext.getResources();
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    paramView = (TextView)super.getView(paramInt, paramView, paramViewGroup);
    paramViewGroup = (ContentListItem)getItem(paramInt);
    paramView.setText(paramViewGroup.toString(this.mResources));
    if (paramViewGroup.enabled) {}
    for (paramInt = 1;; paramInt = 0)
    {
      paramView.setTypeface(null, paramInt);
      return paramView;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\net\zhuoweizhang\mcpelauncher\ui\ContentListAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */