package com.microsoft.xbox.xle.app.adapter;

import android.graphics.drawable.ColorDrawable;
import android.os.Build.VERSION;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import com.microsoft.xbox.service.model.sls.FeedbackType;
import com.microsoft.xbox.toolkit.XLEAssert;
import com.microsoft.xbox.toolkit.ui.CustomTypefaceTextView;
import com.microsoft.xbox.toolkit.ui.XLEButton;
import com.microsoft.xbox.xle.telemetry.helpers.UTCReportUser;
import com.microsoft.xbox.xle.viewmodel.AdapterBase;
import com.microsoft.xbox.xle.viewmodel.ReportUserScreenViewModel;
import com.microsoft.xboxtcui.R.id;
import com.microsoft.xboxtcui.R.layout;
import com.microsoft.xboxtcui.XboxTcuiSdk;

public class ReportUserScreenAdapter
  extends AdapterBase
{
  private XLEButton cancelButton;
  private EditText optionalText;
  private Spinner reasonSpinner;
  private ArrayAdapter<String> reasonSpinnerAdapter;
  private XLEButton submitButton;
  private CustomTypefaceTextView titleTextView;
  private ReportUserScreenViewModel viewModel;
  
  public ReportUserScreenAdapter(ReportUserScreenViewModel paramReportUserScreenViewModel)
  {
    super(paramReportUserScreenViewModel);
    this.viewModel = paramReportUserScreenViewModel;
    this.titleTextView = ((CustomTypefaceTextView)findViewById(R.id.report_user_title));
    this.reasonSpinner = ((Spinner)findViewById(R.id.report_user_reason));
    this.optionalText = ((EditText)findViewById(R.id.report_user_text));
    this.cancelButton = ((XLEButton)findViewById(R.id.report_user_cancel));
    this.submitButton = ((XLEButton)findViewById(R.id.report_user_submit));
    XLEAssert.assertNotNull(this.titleTextView);
    XLEAssert.assertNotNull(this.reasonSpinner);
    XLEAssert.assertNotNull(this.optionalText);
    XLEAssert.assertNotNull(this.cancelButton);
    XLEAssert.assertNotNull(this.submitButton);
  }
  
  public void onStart()
  {
    super.onStart();
    this.reasonSpinnerAdapter = new ArrayAdapter(XboxTcuiSdk.getActivity(), R.layout.report_spinner_item, this.viewModel.getReasonTitles());
    this.reasonSpinnerAdapter.setDropDownViewResource(R.layout.spinner_item_dropdown);
    this.reasonSpinner.setAdapter(this.reasonSpinnerAdapter);
    if (Build.VERSION.SDK_INT >= 16) {
      this.reasonSpinner.setPopupBackgroundDrawable(new ColorDrawable(this.viewModel.getPreferredColor()));
    }
    this.reasonSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
    {
      public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        ReportUserScreenAdapter.this.viewModel.setReason(paramAnonymousInt);
      }
      
      public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView) {}
    });
    this.cancelButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ReportUserScreenAdapter.this.viewModel.onBackButtonPressed();
      }
    });
    this.submitButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (ReportUserScreenAdapter.this.viewModel.getReason() == null) {}
        for (paramAnonymousView = "Unknown";; paramAnonymousView = ReportUserScreenAdapter.this.viewModel.getReason().toString())
        {
          UTCReportUser.trackReportDialogOK(paramAnonymousView);
          ReportUserScreenAdapter.this.viewModel.submitReport(ReportUserScreenAdapter.this.optionalText.getText().toString());
          return;
        }
      }
    });
  }
  
  protected void updateViewOverride()
  {
    if (this.titleTextView != null) {
      this.titleTextView.setText(this.viewModel.getTitle());
    }
    if (this.submitButton != null) {
      this.submitButton.setEnabled(this.viewModel.validReasonSelected());
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\xle\app\adapter\ReportUserScreenAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */