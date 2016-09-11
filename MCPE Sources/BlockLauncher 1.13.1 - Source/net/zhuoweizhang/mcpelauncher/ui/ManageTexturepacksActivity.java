package net.zhuoweizhang.mcpelauncher.ui;

import android.annotation.TargetApi;
import android.app.AlertDialog.Builder;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.os.AsyncTask;
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
import android.widget.Toast;
import com.ipaulpro.afilechooser.FileChooserActivity;
import com.ipaulpro.afilechooser.utils.FileUtils;
import eu.chainfire.libsuperuser.Shell.SU;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.zhuoweizhang.mcpelauncher.R.id;
import net.zhuoweizhang.mcpelauncher.R.layout;
import net.zhuoweizhang.mcpelauncher.R.menu;
import net.zhuoweizhang.mcpelauncher.R.string;
import net.zhuoweizhang.mcpelauncher.ScriptManager;
import net.zhuoweizhang.mcpelauncher.Utils;

public class ManageTexturepacksActivity
  extends ListActivity
{
  public static final File REQUEST_DEMO = new File("/demo/textures");
  public static final File REQUEST_DISABLE = new File("/just/disable/textures");
  public static final File REQUEST_ENABLE = new File("/just/enable/textures");
  protected TexturesAdapter adapter;
  CompoundButton.OnCheckedChangeListener ls = new CompoundButton.OnCheckedChangeListener()
  {
    public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
    {
      if (paramAnonymousBoolean) {
        ManageTexturepacksActivity.this.setTexturepack(ManageTexturepacksActivity.REQUEST_ENABLE);
      }
      for (;;)
      {
        ManageTexturepacksActivity.this.loadHistory();
        return;
        ManageTexturepacksActivity.this.setTexturepack(ManageTexturepacksActivity.REQUEST_DISABLE);
      }
    }
  };
  protected CompoundButton master = null;
  
  public static void setTexturepack(File paramFile, ManageTexturepacksActivity paramManageTexturepacksActivity)
  {
    SharedPreferences.Editor localEditor1 = Utils.getPrefs(0).edit();
    SharedPreferences.Editor localEditor2 = Utils.getPrefs(1).edit();
    if (paramFile.getAbsolutePath().equalsIgnoreCase(REQUEST_DISABLE.getAbsolutePath()))
    {
      localEditor1.putBoolean("zz_texture_pack_enable", false);
      localEditor1.putBoolean("zz_texture_pack_demo", false);
    }
    for (;;)
    {
      localEditor1.apply();
      localEditor2.apply();
      if (paramManageTexturepacksActivity != null)
      {
        paramManageTexturepacksActivity.refreshABToggle();
        paramManageTexturepacksActivity.setResult(-1);
      }
      return;
      if (paramFile.getAbsolutePath().equalsIgnoreCase(REQUEST_ENABLE.getAbsolutePath()))
      {
        localEditor1.putBoolean("zz_texture_pack_enable", true);
        if (Utils.getPrefs(1).getString("texturePack", "no_pack").equals("no_pack")) {
          localEditor1.putBoolean("zz_texture_pack_demo", true);
        }
      }
      else if (paramFile.getAbsolutePath().equalsIgnoreCase(REQUEST_DEMO.getAbsolutePath()))
      {
        localEditor1.putBoolean("zz_texture_pack_enable", true);
        localEditor1.putBoolean("zz_texture_pack_demo", true);
        localEditor2.putString("texturePack", null);
      }
      else
      {
        localEditor1.putBoolean("zz_texture_pack_enable", true);
        localEditor2.putString("texturePack", paramFile.getAbsolutePath());
        localEditor1.putBoolean("zz_texture_pack_demo", false);
      }
    }
  }
  
  protected boolean canAccessMCPE()
  {
    try
    {
      ApplicationInfo localApplicationInfo = getPackageManager().getApplicationInfo("com.mojang.minecraftpe", 0);
      boolean bool = localApplicationInfo.sourceDir.equalsIgnoreCase(localApplicationInfo.publicSourceDir);
      return bool;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      throw new RuntimeException(localNameNotFoundException);
    }
  }
  
  protected boolean isEnabled()
  {
    return Utils.getPrefs(0).getBoolean("zz_texture_pack_enable", false);
  }
  
  public void loadHistory()
  {
    this.adapter.clear();
    if (isEnabled())
    {
      if (canAccessMCPE()) {
        this.adapter.add(REQUEST_DEMO);
      }
      String[] arrayOfString = Utils.getPrefs(0).getString("textures_history", "").split(";");
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
    if ((paramInt1 == 5) && (paramInt2 == -1))
    {
      paramIntent = FileUtils.getFile(paramIntent.getData());
      this.adapter.add(paramIntent);
      this.adapter.notifyDataSetChanged();
      setTexturepack(paramIntent);
      finish();
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.manage_textures);
    this.adapter = new TexturesAdapter(this);
    setListAdapter(this.adapter);
    getListView().setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        paramAnonymousAdapterView = (File)ManageTexturepacksActivity.this.adapter.getItem(paramAnonymousInt);
        ManageTexturepacksActivity.this.setTexturepack(paramAnonymousAdapterView);
        ManageTexturepacksActivity.this.finish();
      }
    });
    ((Button)findViewById(R.id.manage_textures_select)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = FileUtils.createGetContentIntent();
        paramAnonymousView.setType("application/zip");
        paramAnonymousView.setClass(ManageTexturepacksActivity.this, FileChooserActivity.class);
        ManageTexturepacksActivity.this.startActivityForResult(paramAnonymousView, 5);
      }
    });
    ((Button)findViewById(R.id.manage_textures_extract)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        new ManageTexturepacksActivity.ExtractTextureTask(ManageTexturepacksActivity.this, null).execute(new Void[0]);
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
      if (this.master == null) {
        break label87;
      }
      this.master.setOnCheckedChangeListener(this.ls);
      refreshABToggle();
    }
    for (;;)
    {
      paramMenu.add(getResources().getString(R.string.textures_clear_script_texture_overrides));
      return true;
      label87:
      System.err.println("WTF?");
    }
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getTitle().equals(getResources().getString(R.string.textures_clear_script_texture_overrides)))
    {
      ScriptManager.clearTextureOverrides();
      return true;
    }
    return super.onOptionsItemSelected(paramMenuItem);
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
    if ((Build.VERSION.SDK_INT >= 11) && (this.master != null))
    {
      this.master.setOnCheckedChangeListener(null);
      this.master.setChecked(isEnabled());
      this.master.setOnCheckedChangeListener(this.ls);
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
      Object localObject2 = Utils.getPrefs(0).edit();
      ((SharedPreferences.Editor)localObject2).putString("textures_history", (String)localObject1);
      ((SharedPreferences.Editor)localObject2).apply();
    }
  }
  
  protected void setTexturepack(File paramFile)
  {
    setTexturepack(paramFile, this);
  }
  
  private class ExtractTextureTask
    extends AsyncTask<Void, Void, Void>
  {
    private ProgressDialog dialog;
    private boolean hasSu = true;
    private String mcpeApkLoc;
    private File outFile;
    
    private ExtractTextureTask() {}
    
    protected Void doInBackground(Void... paramVarArgs)
    {
      try
      {
        this.mcpeApkLoc = ManageTexturepacksActivity.this.getPackageManager().getApplicationInfo("com.mojang.minecraftpe", 0).sourceDir;
        this.outFile = new File(ManageTexturepacksActivity.this.getExternalFilesDir(null), "minecraft.apk");
        if (Shell.SU.run("cat \"" + this.mcpeApkLoc + "\" >\"" + this.outFile.getAbsolutePath() + "\"") == null) {
          this.hasSu = false;
        }
        ScriptManager.clearTextureOverrides();
        return null;
      }
      catch (PackageManager.NameNotFoundException paramVarArgs)
      {
        for (;;) {}
      }
    }
    
    protected void onPostExecute(Void paramVoid)
    {
      this.dialog.dismiss();
      if (this.outFile.exists())
      {
        ManageTexturepacksActivity.this.adapter.add(this.outFile);
        ManageTexturepacksActivity.this.adapter.notifyDataSetChanged();
        ManageTexturepacksActivity.this.saveHistory();
        ManageTexturepacksActivity.this.setTexturepack(this.outFile);
        Toast.makeText(ManageTexturepacksActivity.this, R.string.extract_textures_success, 0).show();
        return;
      }
      paramVoid = new AlertDialog.Builder(ManageTexturepacksActivity.this);
      if (this.hasSu) {}
      for (int i = R.string.extract_textures_error;; i = R.string.extract_textures_no_root)
      {
        paramVoid.setMessage(i).setPositiveButton(17039370, null).show();
        return;
      }
    }
    
    protected void onPreExecute()
    {
      this.dialog = new ProgressDialog(ManageTexturepacksActivity.this);
      this.dialog.setMessage(ManageTexturepacksActivity.this.getResources().getString(R.string.extracting_textures));
      this.dialog.setIndeterminate(true);
      this.dialog.setCancelable(false);
      this.dialog.show();
    }
  }
  
  protected class TexturesAdapter
    extends ArrayAdapter<File>
  {
    private LayoutInflater inflater;
    
    public TexturesAdapter(Context paramContext)
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
      paramView = (TextView)localView.findViewById(16908308);
      paramViewGroup = (File)getItem(paramInt);
      if (paramViewGroup.getAbsolutePath().equalsIgnoreCase(ManageTexturepacksActivity.REQUEST_DEMO.getAbsolutePath()))
      {
        paramView.setText(R.string.textures_demo);
        return localView;
      }
      paramView.setText(paramViewGroup.getName());
      return localView;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\net\zhuoweizhang\mcpelauncher\ui\ManageTexturepacksActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */