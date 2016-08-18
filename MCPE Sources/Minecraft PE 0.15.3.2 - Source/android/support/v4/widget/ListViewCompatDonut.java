package android.support.v4.widget;

import android.view.View;
import android.widget.ListView;

class ListViewCompatDonut
{
  static void scrollListBy(ListView paramListView, int paramInt)
  {
    int i = paramListView.getFirstVisiblePosition();
    if (i == -1) {}
    View localView;
    do
    {
      return;
      localView = paramListView.getChildAt(0);
    } while (localView == null);
    paramListView.setSelectionFromTop(i, localView.getTop() - paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\android\support\v4\widget\ListViewCompatDonut.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */