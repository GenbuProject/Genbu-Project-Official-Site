package net.zhuoweizhang.mcpelauncher.api;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.ipaulpro.afilechooser.utils.FileUtils;
import java.io.File;
import net.zhuoweizhang.mcpelauncher.R.id;
import net.zhuoweizhang.mcpelauncher.R.layout;
import net.zhuoweizhang.mcpelauncher.Utils;

public abstract class ImportActivity
  extends Activity
  implements View.OnClickListener
{
  public Button cancelButton;
  public TextView installConfirmText;
  public File mFile = null;
  public Button okButton;
  public TextView patchNameText;
  
  public void onClick(View paramView)
  {
    if (paramView.equals(this.cancelButton)) {
      finish();
    }
    while (!paramView.equals(this.okButton)) {
      return;
    }
    this.okButton.setEnabled(false);
    this.cancelButton.setEnabled(false);
    startImport();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    Utils.setLanguageOverride();
    super.onCreate(paramBundle);
    setContentView(R.layout.import_confirm);
    this.okButton = ((Button)findViewById(R.id.ok_button));
    this.cancelButton = ((Button)findViewById(R.id.cancel_button));
    this.okButton.setOnClickListener(this);
    this.cancelButton.setOnClickListener(this);
    this.patchNameText = ((TextView)findViewById(R.id.app_name));
    this.installConfirmText = ((TextView)findViewById(R.id.install_confirm_question));
    if (getIntent() == null)
    {
      finish();
      return;
    }
    this.mFile = FileUtils.getFile(getIntent().getData());
    if ((this.mFile == null) || (!this.mFile.canRead()))
    {
      finish();
      return;
    }
    this.patchNameText.setText(this.mFile.getName());
    setResult(0);
  }
  
  protected abstract void startImport();
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\net\zhuoweizhang\mcpelauncher\api\ImportActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */