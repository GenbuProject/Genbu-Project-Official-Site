package com.microsoft.xbox.idp.ui;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;
import com.microsoft.xbox.idp.R.id;
import com.microsoft.xbox.idp.compat.BaseActivity;
import com.microsoft.xbox.idp.compat.BaseFragment;

public final class UiUtil
{
  private static final String TAG = UiUtil.class.getSimpleName();
  
  public static boolean canScroll(ScrollView paramScrollView)
  {
    boolean bool2 = false;
    View localView = paramScrollView.getChildAt(0);
    boolean bool1 = bool2;
    if (localView != null)
    {
      ViewGroup.MarginLayoutParams localMarginLayoutParams = (ViewGroup.MarginLayoutParams)localView.getLayoutParams();
      int i = localMarginLayoutParams.topMargin;
      int j = localView.getHeight();
      int k = localMarginLayoutParams.bottomMargin;
      bool1 = bool2;
      if (paramScrollView.getHeight() < i + j + k) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public static void ensureClickableSpanOnUnderlineSpan(TextView paramTextView, int paramInt, ClickableSpan paramClickableSpan)
  {
    SpannableStringBuilder localSpannableStringBuilder = new SpannableStringBuilder(Html.fromHtml(paramTextView.getResources().getString(paramInt)));
    Object localObject = (UnderlineSpan[])localSpannableStringBuilder.getSpans(0, localSpannableStringBuilder.length(), UnderlineSpan.class);
    if ((localObject != null) && (localObject.length > 0))
    {
      localObject = localObject[0];
      localSpannableStringBuilder.setSpan(paramClickableSpan, localSpannableStringBuilder.getSpanStart(localObject), localSpannableStringBuilder.getSpanEnd(localObject), 33);
      paramTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }
    paramTextView.setText(localSpannableStringBuilder);
  }
  
  public static boolean ensureErrorButtonsFragment(BaseActivity paramBaseActivity, ErrorActivity.ErrorScreen paramErrorScreen)
  {
    if (!paramBaseActivity.hasFragment(R.id.xbid_error_buttons))
    {
      Bundle localBundle = new Bundle();
      localBundle.putInt("ARG_LEFT_ERROR_BUTTON_STRING_ID", paramErrorScreen.leftButtonTextId);
      return ensureFragment(ErrorButtonsFragment.class, paramBaseActivity, R.id.xbid_error_buttons, localBundle);
    }
    return false;
  }
  
  public static boolean ensureErrorFragment(BaseActivity paramBaseActivity, ErrorActivity.ErrorScreen paramErrorScreen)
  {
    if (!paramBaseActivity.hasFragment(R.id.xbid_body_fragment)) {
      return ensureFragment(paramErrorScreen.errorFragmentClass, paramBaseActivity, R.id.xbid_body_fragment, paramBaseActivity.getIntent().getExtras());
    }
    return false;
  }
  
  private static boolean ensureFragment(Class<? extends BaseFragment> paramClass, BaseActivity paramBaseActivity, int paramInt, Bundle paramBundle)
  {
    if (!paramBaseActivity.hasFragment(paramInt)) {}
    try
    {
      paramClass = (BaseFragment)paramClass.newInstance();
      paramClass.setArguments(paramBundle);
      paramBaseActivity.addFragment(paramInt, paramClass);
      return true;
    }
    catch (InstantiationException paramClass)
    {
      Log.e(TAG, paramClass.getMessage());
      return false;
    }
    catch (IllegalAccessException paramClass)
    {
      for (;;)
      {
        Log.e(TAG, paramClass.getMessage());
      }
    }
  }
  
  public static boolean ensureGamerTagCreationFragment(BaseActivity paramBaseActivity, int paramInt, Bundle paramBundle)
  {
    return ensureFragment(SignUpFragment.class, paramBaseActivity, paramInt, paramBundle);
  }
  
  public static boolean ensureHeaderFragment(BaseActivity paramBaseActivity, int paramInt, Bundle paramBundle)
  {
    return ensureFragment(HeaderFragment.class, paramBaseActivity, paramInt, paramBundle);
  }
  
  public static boolean ensureWelcomeFragment(BaseActivity paramBaseActivity, int paramInt, boolean paramBoolean, Bundle paramBundle)
  {
    if (paramBoolean) {
      return ensureFragment(IntroducingFragment.class, paramBaseActivity, paramInt, paramBundle);
    }
    return ensureFragment(WelcomeFragment.class, paramBaseActivity, paramInt, paramBundle);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\idp\ui\UiUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */