package net.zhuoweizhang.mcpelauncher.ui;

import android.annotation.TargetApi;
import android.app.AlertDialog.Builder;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.TextView;
import com.ipaulpro.afilechooser.FileChooserActivity;
import com.ipaulpro.afilechooser.utils.FileUtils;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.zhuoweizhang.mcpelauncher.R.id;
import net.zhuoweizhang.mcpelauncher.R.layout;
import net.zhuoweizhang.mcpelauncher.R.menu;
import net.zhuoweizhang.mcpelauncher.R.string;
import net.zhuoweizhang.mcpelauncher.Utils;

public class ManageSkinsActivity
  extends ListActivity
{
  public static final File REQUEST_DISABLE = new File("/just/disable/skins");
  public static final File REQUEST_ENABLE = new File("/just/enable/skins");
  protected SkinsAdapter adapter;
  protected CompoundButton master = null;
  
  public static void setSkin(File paramFile, ManageSkinsActivity paramManageSkinsActivity)
  {
    SharedPreferences.Editor localEditor1 = Utils.getPrefs(0).edit();
    SharedPreferences.Editor localEditor2 = Utils.getPrefs(1).edit();
    if (paramFile.getAbsolutePath().equalsIgnoreCase(REQUEST_DISABLE.getAbsolutePath())) {
      localEditor1.putBoolean("zz_skin_enable", false);
    }
    for (;;)
    {
      localEditor1.apply();
      localEditor2.apply();
      if (paramManageSkinsActivity != null)
      {
        paramManageSkinsActivity.refreshABToggle();
        paramManageSkinsActivity.setResult(-1);
      }
      return;
      if (paramFile.getAbsolutePath().equalsIgnoreCase(REQUEST_ENABLE.getAbsolutePath()))
      {
        localEditor1.putBoolean("zz_skin_enable", true);
      }
      else
      {
        localEditor1.putBoolean("zz_skin_enable", true);
        localEditor2.putString("player_skin", paramFile.getAbsolutePath());
      }
    }
  }
  
  protected boolean isEnabled()
  {
    return Utils.getPrefs(0).getBoolean("zz_skin_enable", false);
  }
  
  public void loadHistory()
  {
    this.adapter.clear();
    if (isEnabled())
    {
      String[] arrayOfString = Utils.getPrefs(1).getString("skins_history", "").split(";");
      int j = arrayOfString.length;
      int i = 0;
      if (i < j)
      {
        File localFile = new File(arrayOfString[i]);
        if ((!localFile.exists()) || (!localFile.canRead())) {}
        for (;;)
        {
          i += 1;
          break;
          this.adapter.add(localFile);
        }
      }
      this.adapter.notifyDataSetChanged();
    }
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if ((paramInt1 == 7) && (paramInt2 == -1))
    {
      paramIntent = FileUtils.getFile(paramIntent.getData());
      this.adapter.add(paramIntent);
      this.adapter.notifyDataSetChanged();
      setSkin(paramIntent);
      finish();
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.manage_skins);
    this.adapter = new SkinsAdapter(this);
    setListAdapter(this.adapter);
    getListView().setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        paramAnonymousAdapterView = (File)ManageSkinsActivity.this.adapter.getItem(paramAnonymousInt);
        ManageSkinsActivity.this.setSkin(paramAnonymousAdapterView);
        ManageSkinsActivity.this.finish();
      }
    });
    ((Button)findViewById(R.id.manage_skins_select)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = FileUtils.createGetContentIntent();
        paramAnonymousView.setType("image/png");
        paramAnonymousView.setClass(ManageSkinsActivity.this, FileChooserActivity.class);
        ManageSkinsActivity.this.startActivityForResult(paramAnonymousView, 7);
      }
    });
    ((Button)findViewById(R.id.manage_skins_players)).setOnClickListener(new View.OnClickListener()
    {
      protected int getCurrentMode()
      {
        String str = Utils.getPrefs(0).getString("zz_skin_download_source", "none");
        if (str.equals("none")) {}
        while (!str.equals("mojang_pc")) {
          return 0;
        }
        return 1;
      }
      
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = new AlertDialog.Builder(ManageSkinsActivity.this);
        paramAnonymousView.setTitle(R.string.pref_zz_skin_download_source);
        String str1 = ManageSkinsActivity.this.getString(R.string.skin_download_do_not_download);
        String str2 = ManageSkinsActivity.this.getString(R.string.skin_download_download_pc);
        int i = getCurrentMode();
        DialogInterface.OnClickListener local1 = new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            paramAnonymous2DialogInterface = Utils.getPrefs(0).edit();
            switch (paramAnonymous2Int)
            {
            default: 
              paramAnonymous2DialogInterface.putString("zz_skin_download_source", "none");
            }
            for (;;)
            {
              paramAnonymous2DialogInterface.apply();
              return;
              paramAnonymous2DialogInterface.putString("zz_skin_download_source", "mojang_pc");
            }
          }
        };
        paramAnonymousView.setSingleChoiceItems(new String[] { str1, str2 }, i, local1);
        paramAnonymousView.show();
      }
    });
    setResult(0);
    loadHistory();
  }
  
  @TargetApi(14)
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    if (Build.VERSION.SDK_INT >= 11)
    {
      getMenuInflater().inflate(R.menu.ab_master, paramMenu);
      this.master = ((CompoundButton)paramMenu.findItem(R.id.ab_switch_container).getActionView().findViewById(R.id.ab_switch));
      if (this.master != null)
      {
        this.master.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
          public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
          {
            if (paramAnonymousBoolean) {
              ManageSkinsActivity.this.setSkin(ManageSkinsActivity.REQUEST_ENABLE);
            }
            for (;;)
            {
              ManageSkinsActivity.this.loadHistory();
              return;
              ManageSkinsActivity.this.setSkin(ManageSkinsActivity.REQUEST_DISABLE);
            }
          }
        });
        refreshABToggle();
      }
      for (;;)
      {
        return true;
        System.err.println("WTF?");
      }
    }
    return false;
  }
  
  protected void onPause()
  {
    super.onPause();
    saveHistory();
    refreshABToggle();
  }
  
  protected void onResume()
  {
    super.onResume();
    refreshABToggle();
  }
  
  protected void refreshABToggle()
  {
    if ((Build.VERSION.SDK_INT >= 11) && (this.master != null)) {
      this.master.setChecked(isEnabled());
    }
  }
  
  public void saveHistory()
  {
    if (isEnabled())
    {
      Object localObject1 = new ArrayList();
      int i = 0;
      while (i < this.adapter.getCount())
      {
        localObject2 = (File)this.adapter.getItem(i);
        if ((((File)localObject2).exists()) && (((File)localObject2).canRead())) {
          ((List)localObject1).add(((File)localObject2).getAbsolutePath());
        }
        i += 1;
      }
      localObject1 = Utils.join((Collection)localObject1, ";");
      Object localObject2 = Utils.getPrefs(1).edit();
      ((SharedPreferences.Editor)localObject2).putString("skins_history", (String)localObject1);
      ((SharedPreferences.Editor)localObject2).apply();
    }
  }
  
  protected void setSkin(File paramFile)
  {
    setSkin(paramFile, this);
  }
  
  protected class SkinsAdapter
    extends ArrayAdapter<File>
  {
    private LayoutInflater inflater;
    
    public SkinsAdapter(Context paramContext)
    {
      super(17367043, new ArrayList());
      this.inflater = LayoutInflater.from(paramContext);
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      View localView = paramView;
      if (paramView == null) {
        localView = this.inflater.inflate(17367043, paramViewGroup, false);
      }
      ((TextView)localView.findViewById(16908308)).setText(((File)getItem(paramInt)).getName());
      return localView;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\net\zhuoweizhang\mcpelauncher\ui\ManageSkinsActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */