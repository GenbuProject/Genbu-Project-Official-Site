package com.facebook.internal;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookDialog;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import java.util.Iterator;
import java.util.List;

public abstract class FacebookDialogBase<CONTENT, RESULT>
  implements FacebookDialog<CONTENT, RESULT>
{
  protected static final Object BASE_AUTOMATIC_MODE = new Object();
  private static final String TAG = "FacebookDialog";
  private final Activity activity;
  private final FragmentWrapper fragmentWrapper;
  private List<FacebookDialogBase<CONTENT, RESULT>.ModeHandler> modeHandlers;
  private int requestCode;
  
  protected FacebookDialogBase(Activity paramActivity, int paramInt)
  {
    Validate.notNull(paramActivity, "activity");
    this.activity = paramActivity;
    this.fragmentWrapper = null;
    this.requestCode = paramInt;
  }
  
  protected FacebookDialogBase(FragmentWrapper paramFragmentWrapper, int paramInt)
  {
    Validate.notNull(paramFragmentWrapper, "fragmentWrapper");
    this.fragmentWrapper = paramFragmentWrapper;
    this.activity = null;
    this.requestCode = paramInt;
    if (paramFragmentWrapper.getActivity() == null) {
      throw new IllegalArgumentException("Cannot use a fragment that is not attached to an activity");
    }
  }
  
  private List<FacebookDialogBase<CONTENT, RESULT>.ModeHandler> cachedModeHandlers()
  {
    if (this.modeHandlers == null) {
      this.modeHandlers = getOrderedModeHandlers();
    }
    return this.modeHandlers;
  }
  
  private AppCall createAppCallForMode(CONTENT paramCONTENT, Object paramObject)
  {
    int i;
    if (paramObject == BASE_AUTOMATIC_MODE) {
      i = 1;
    }
    for (;;)
    {
      Object localObject2 = null;
      Iterator localIterator = cachedModeHandlers().iterator();
      Object localObject1;
      do
      {
        localObject1 = localObject2;
        if (!localIterator.hasNext()) {
          break;
        }
        localObject1 = (ModeHandler)localIterator.next();
      } while (((i == 0) && (!Utility.areObjectsEqual(((ModeHandler)localObject1).getMode(), paramObject))) || (!((ModeHandler)localObject1).canShow(paramCONTENT, true)));
      try
      {
        localObject1 = ((ModeHandler)localObject1).createAppCall(paramCONTENT);
        paramCONTENT = (CONTENT)localObject1;
        if (localObject1 == null)
        {
          paramCONTENT = createBaseAppCall();
          DialogPresenter.setupAppCallForCannotShowError(paramCONTENT);
        }
        return paramCONTENT;
        i = 0;
      }
      catch (FacebookException paramCONTENT)
      {
        for (;;)
        {
          localObject1 = createBaseAppCall();
          DialogPresenter.setupAppCallForValidationError((AppCall)localObject1, paramCONTENT);
        }
      }
    }
  }
  
  public boolean canShow(CONTENT paramCONTENT)
  {
    return canShowImpl(paramCONTENT, BASE_AUTOMATIC_MODE);
  }
  
  protected boolean canShowImpl(CONTENT paramCONTENT, Object paramObject)
  {
    if (paramObject == BASE_AUTOMATIC_MODE) {}
    for (int i = 1;; i = 0)
    {
      Iterator localIterator = cachedModeHandlers().iterator();
      ModeHandler localModeHandler;
      do
      {
        if (!localIterator.hasNext()) {
          break;
        }
        localModeHandler = (ModeHandler)localIterator.next();
      } while (((i == 0) && (!Utility.areObjectsEqual(localModeHandler.getMode(), paramObject))) || (!localModeHandler.canShow(paramCONTENT, false)));
      return true;
    }
    return false;
  }
  
  protected abstract AppCall createBaseAppCall();
  
  protected Activity getActivityContext()
  {
    if (this.activity != null) {
      return this.activity;
    }
    if (this.fragmentWrapper != null) {
      return this.fragmentWrapper.getActivity();
    }
    return null;
  }
  
  protected abstract List<FacebookDialogBase<CONTENT, RESULT>.ModeHandler> getOrderedModeHandlers();
  
  public int getRequestCode()
  {
    return this.requestCode;
  }
  
  public final void registerCallback(CallbackManager paramCallbackManager, FacebookCallback<RESULT> paramFacebookCallback)
  {
    if (!(paramCallbackManager instanceof CallbackManagerImpl)) {
      throw new FacebookException("Unexpected CallbackManager, please use the provided Factory.");
    }
    registerCallbackImpl((CallbackManagerImpl)paramCallbackManager, paramFacebookCallback);
  }
  
  public final void registerCallback(CallbackManager paramCallbackManager, FacebookCallback<RESULT> paramFacebookCallback, int paramInt)
  {
    setRequestCode(paramInt);
    registerCallback(paramCallbackManager, paramFacebookCallback);
  }
  
  protected abstract void registerCallbackImpl(CallbackManagerImpl paramCallbackManagerImpl, FacebookCallback<RESULT> paramFacebookCallback);
  
  protected void setRequestCode(int paramInt)
  {
    if (FacebookSdk.isFacebookRequestCode(paramInt)) {
      throw new IllegalArgumentException("Request code " + paramInt + " cannot be within the range reserved by the Facebook SDK.");
    }
    this.requestCode = paramInt;
  }
  
  public void show(CONTENT paramCONTENT)
  {
    showImpl(paramCONTENT, BASE_AUTOMATIC_MODE);
  }
  
  protected void showImpl(CONTENT paramCONTENT, Object paramObject)
  {
    paramCONTENT = createAppCallForMode(paramCONTENT, paramObject);
    if (paramCONTENT != null) {
      if (this.fragmentWrapper != null) {
        DialogPresenter.present(paramCONTENT, this.fragmentWrapper);
      }
    }
    do
    {
      return;
      DialogPresenter.present(paramCONTENT, this.activity);
      return;
      Log.e("FacebookDialog", "No code path should ever result in a null appCall");
    } while (!FacebookSdk.isDebugEnabled());
    throw new IllegalStateException("No code path should ever result in a null appCall");
  }
  
  protected void startActivityForResult(Intent paramIntent, int paramInt)
  {
    Object localObject = null;
    if (this.activity != null)
    {
      this.activity.startActivityForResult(paramIntent, paramInt);
      paramIntent = (Intent)localObject;
    }
    for (;;)
    {
      if (paramIntent != null) {
        Logger.log(LoggingBehavior.DEVELOPER_ERRORS, 6, getClass().getName(), paramIntent);
      }
      return;
      if (this.fragmentWrapper != null)
      {
        if (this.fragmentWrapper.getNativeFragment() != null)
        {
          this.fragmentWrapper.getNativeFragment().startActivityForResult(paramIntent, paramInt);
          paramIntent = (Intent)localObject;
        }
        else if (this.fragmentWrapper.getSupportFragment() != null)
        {
          this.fragmentWrapper.getSupportFragment().startActivityForResult(paramIntent, paramInt);
          paramIntent = (Intent)localObject;
        }
        else
        {
          paramIntent = "Failed to find Activity or Fragment to startActivityForResult ";
        }
      }
      else {
        paramIntent = "Failed to find Activity or Fragment to startActivityForResult ";
      }
    }
  }
  
  protected abstract class ModeHandler
  {
    protected ModeHandler() {}
    
    public abstract boolean canShow(CONTENT paramCONTENT, boolean paramBoolean);
    
    public abstract AppCall createAppCall(CONTENT paramCONTENT);
    
    public Object getMode()
    {
      return FacebookDialogBase.BASE_AUTOMATIC_MODE;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\facebook\internal\FacebookDialogBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */