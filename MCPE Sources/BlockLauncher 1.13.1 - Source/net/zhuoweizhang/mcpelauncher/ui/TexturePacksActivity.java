package net.zhuoweizhang.mcpelauncher.ui;

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
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.ipaulpro.afilechooser.FileChooserActivity;
import com.ipaulpro.afilechooser.utils.FileUtils;
import eu.chainfire.libsuperuser.Shell.SU;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.zhuoweizhang.mcpelauncher.R.id;
import net.zhuoweizhang.mcpelauncher.R.layout;
import net.zhuoweizhang.mcpelauncher.R.menu;
import net.zhuoweizhang.mcpelauncher.R.string;
import net.zhuoweizhang.mcpelauncher.ScriptManager;
import net.zhuoweizhang.mcpelauncher.Utils;
import net.zhuoweizhang.mcpelauncher.texture.TexturePackDescription;
import net.zhuoweizhang.mcpelauncher.texture.TexturePackLoader;

public class TexturePacksActivity
  extends ListActivity
  implements View.OnClickListener
{
  private static final int REQUEST_ADD_TEXTURE = 522;
  private ArrayAdapter<TexturePackDescription> adapter;
  private ImageButton addButton;
  private List<TexturePackDescription> list;
  
  private void addTexturePack(int paramInt, File paramFile)
  {
    addTexturePack(paramInt, new TexturePackDescription("zip", paramFile.getAbsolutePath()));
  }
  
  private void addTexturePack(int paramInt, TexturePackDescription paramTexturePackDescription)
  {
    Utils.getPrefs(0).edit().putBoolean("zz_texture_pack_enable", true).apply();
    Iterator localIterator = this.list.iterator();
    while (localIterator.hasNext()) {
      if (((TexturePackDescription)localIterator.next()).path.equals(paramTexturePackDescription.path)) {
        return;
      }
    }
    if (paramTexturePackDescription.img == null) {}
    try
    {
      TexturePackLoader.loadIconForDescription(paramTexturePackDescription);
      this.list.add(paramInt, paramTexturePackDescription);
      updateContents();
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
    }
  }
  
  private void saveState()
  {
    try
    {
      TexturePackLoader.saveDescriptions(this, this.list);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private void updateContents()
  {
    this.adapter.notifyDataSetChanged();
    saveState();
    setResult(-1);
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if ((paramInt1 == 522) && (paramInt2 == -1)) {
      addTexturePack(0, FileUtils.getFile(paramIntent.getData()));
    }
  }
  
  public void onClick(View paramView)
  {
    if (paramView == this.addButton)
    {
      paramView = FileUtils.createGetContentIntent();
      paramView.setType("application/zip");
      paramView.putExtra("net.zhuoweizhang.afilechooser.extra.MIME_TYPES", new String[] { "application/zip", "application/x-appx", "application/vnd.android.package-archive" });
      paramView.setClass(this, FileChooserActivity.class);
      startActivityForResult(paramView, 522);
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    try
    {
      this.list = TexturePackLoader.loadDescriptionsWithIcons(this);
      if (new File("/sdcard/bl_clearTextures").exists()) {
        this.list = new ArrayList();
      }
      setContentView(R.layout.manage_textures);
      this.adapter = new TexturesAdapter(this);
      setListAdapter(this.adapter);
      this.addButton = ((ImageButton)findViewById(R.id.manage_textures_select));
      this.addButton.setOnClickListener(this);
      return;
    }
    catch (Exception paramBundle)
    {
      for (;;)
      {
        paramBundle.printStackTrace();
        this.list = new ArrayList();
      }
    }
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(R.menu.manage_textures_menu, paramMenu);
    return true;
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() == R.id.manage_textures_extract)
    {
      new ExtractTextureTask(null).execute(new Void[0]);
      return true;
    }
    if (paramMenuItem.getItemId() == R.id.manage_textures_clear_script_texture_overrides)
    {
      ScriptManager.clearTextureOverrides();
      Toast.makeText(this, R.string.textures_clear_script_texture_overrides, 0).show();
      return true;
    }
    return super.onOptionsItemSelected(paramMenuItem);
  }
  
  public void onTextureDownClick(View paramView)
  {
    int i = ((Integer)((View)paramView.getParent()).getTag()).intValue();
    paramView = (TexturePackDescription)this.list.remove(i);
    this.list.add(i + 1, paramView);
    updateContents();
  }
  
  public void onTextureRemoveClick(View paramView)
  {
    int i = ((Integer)((View)paramView.getParent()).getTag()).intValue();
    paramView = (TexturePackDescription)this.list.remove(i);
    updateContents();
  }
  
  public void onTextureUpClick(View paramView)
  {
    int i = ((Integer)((View)paramView.getParent()).getTag()).intValue();
    paramView = (TexturePackDescription)this.list.remove(i);
    this.list.add(i - 1, paramView);
    updateContents();
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
        this.mcpeApkLoc = TexturePacksActivity.this.getPackageManager().getApplicationInfo("com.mojang.minecraftpe", 0).sourceDir;
        this.outFile = new File(TexturePacksActivity.this.getExternalFilesDir(null), "minecraft.apk");
        this.outFile.delete();
        paramVarArgs = this.outFile.getAbsolutePath().replace(Environment.getExternalStorageDirectory().getAbsolutePath(), "/sdcard");
        System.out.println(paramVarArgs);
        if (Shell.SU.run("cat \"" + this.mcpeApkLoc + "\" >\"" + paramVarArgs + "\"") == null) {
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
        TexturePacksActivity.this.addTexturePack(TexturePacksActivity.this.list.size(), this.outFile);
        Toast.makeText(TexturePacksActivity.this, R.string.extract_textures_success, 0).show();
        return;
      }
      paramVoid = new AlertDialog.Builder(TexturePacksActivity.this);
      if (this.hasSu) {}
      for (int i = R.string.extract_textures_error;; i = R.string.extract_textures_no_root)
      {
        paramVoid.setMessage(i).setPositiveButton(17039370, null).show();
        return;
      }
    }
    
    protected void onPreExecute()
    {
      this.dialog = new ProgressDialog(TexturePacksActivity.this);
      this.dialog.setMessage(TexturePacksActivity.this.getResources().getString(R.string.extracting_textures));
      this.dialog.setIndeterminate(true);
      this.dialog.setCancelable(false);
      this.dialog.show();
    }
  }
  
  private class TexturesAdapter
    extends ArrayAdapter<TexturePackDescription>
  {
    private LayoutInflater inflater;
    
    public TexturesAdapter(Context paramContext)
    {
      super(R.layout.texture_pack_entry, R.id.texture_entry_name, TexturePacksActivity.this.list);
      this.inflater = LayoutInflater.from(paramContext);
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      boolean bool2 = true;
      View localView = paramView;
      if (paramView == null) {
        localView = this.inflater.inflate(R.layout.texture_pack_entry, paramViewGroup, false);
      }
      paramView = (TexturePackDescription)getItem(paramInt);
      localView.findViewById(R.id.texture_entry_container).setTag(Integer.valueOf(paramInt));
      ((TextView)localView.findViewById(R.id.texture_entry_name)).setText(TexturePackLoader.describeTexturePack(TexturePacksActivity.this, paramView));
      paramViewGroup = (TextView)localView.findViewById(R.id.texture_entry_desc);
      paramViewGroup.setText(paramView.description);
      int i;
      boolean bool1;
      if (paramView.description.length() == 0)
      {
        i = 8;
        paramViewGroup.setVisibility(i);
        paramViewGroup = localView.findViewById(R.id.texture_entry_up);
        if (paramInt == 0) {
          break label206;
        }
        bool1 = true;
        label126:
        paramViewGroup.setEnabled(bool1);
        paramViewGroup = localView.findViewById(R.id.texture_entry_down);
        if (paramInt == getCount() - 1) {
          break label212;
        }
        bool1 = bool2;
        label155:
        paramViewGroup.setEnabled(bool1);
        paramViewGroup = (ImageView)localView.findViewById(R.id.texture_entry_img);
        if (paramView.img == null) {
          break label218;
        }
      }
      label206:
      label212:
      label218:
      for (paramView = new BitmapDrawable(paramView.img);; paramView = null)
      {
        paramViewGroup.setImageDrawable(paramView);
        return localView;
        i = 0;
        break;
        bool1 = false;
        break label126;
        bool1 = false;
        break label155;
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\net\zhuoweizhang\mcpelauncher\ui\TexturePacksActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */