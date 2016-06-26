package com.amazon.android;

import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.amazon.android.framework.context.ContextManager;
import com.amazon.android.framework.prompt.PromptManager;
import com.amazon.android.framework.prompt.PromptManagerImpl;
import com.amazon.android.framework.resource.Resource;
import com.amazon.android.framework.task.Task;
import com.amazon.android.framework.task.TaskManager;
import com.amazon.android.framework.task.command.AbstractCommandTask;
import com.amazon.android.framework.task.pipeline.TaskPipelineId;
import com.amazon.android.framework.util.KiwiLogger;

public final class Kiwi
  implements com.amazon.android.framework.resource.b
{
  protected static final String ACTIVITY_NAME = "ActivityName";
  protected static final String EVENT_NAME = "EventName";
  private static Kiwi INSTANCE;
  private static final KiwiLogger LOGGER = new KiwiLogger("Kiwi");
  protected static final String TIMESTAMP = "Timestamp";
  @Resource
  private Application application;
  @Resource
  private com.amazon.android.m.c authKeyLoader;
  @Resource
  private ContextManager contextManager;
  @Resource
  private com.amazon.android.n.a dataStore;
  private final boolean drmEnabled;
  @Resource
  private com.amazon.android.o.g eventManager;
  @Resource
  private PromptManager promptManager;
  @Resource
  private com.amazon.android.f.b resultManager;
  @Resource
  private TaskManager taskManager;
  
  private Kiwi(Application paramApplication, boolean paramBoolean)
  {
    long l1 = System.currentTimeMillis();
    this.drmEnabled = paramBoolean;
    if (KiwiLogger.TRACE_ON)
    {
      LOGGER.trace("Starting initialization process for application: " + paramApplication.getPackageName());
      LOGGER.trace("DRM enabled: " + paramBoolean);
    }
    instantiateTheWorld(paramApplication);
    registerTestModeReceiver(paramApplication);
    if (KiwiLogger.TRACE_ON)
    {
      long l2 = System.currentTimeMillis();
      LOGGER.trace("Kiwi.Constructor Time: " + (l2 - l1));
    }
  }
  
  @Deprecated
  public static void addCommandToCommandTaskPipeline(AbstractCommandTask paramAbstractCommandTask)
  {
    if (KiwiLogger.TRACE_ON) {
      LOGGER.trace("WARNING: Use of deprecated method detected.");
    }
    if (isInitialized())
    {
      unsafeAddCommandToCommandTaskPipeline(paramAbstractCommandTask);
      return;
    }
    LOGGER.error("Kiwi subsystem is not fully initialized.  Cannot process task.");
  }
  
  public static void addCommandToCommandTaskPipeline(AbstractCommandTask paramAbstractCommandTask, Context paramContext)
  {
    if (!isInitialized())
    {
      if ((paramContext != null) && (paramContext.getApplicationContext() != null)) {
        INSTANCE = new Kiwi((Application)paramContext.getApplicationContext(), false);
      }
    }
    else
    {
      unsafeAddCommandToCommandTaskPipeline(paramAbstractCommandTask);
      return;
    }
    LOGGER.error("Kiwi subsystem cannot be initialized because of null context. Unable to enqueue task.");
  }
  
  private void enqueueAppLaunchWorkflowTask()
  {
    if (KiwiLogger.TRACE_ON) {
      LOGGER.trace("Enqueuing launch workflow");
    }
    Task localTask = getLaunchWorkflow();
    this.taskManager.enqueue(TaskPipelineId.COMMAND, localTask);
  }
  
  private Task getLaunchWorkflow()
  {
    if (this.drmEnabled) {
      return new b();
    }
    return new h();
  }
  
  public static PromptManager getPromptManager()
  {
    return INSTANCE.promptManager;
  }
  
  private static void ignoreEvent(String paramString, Context paramContext)
  {
    if (KiwiLogger.TRACE_ON) {
      LOGGER.trace(paramString + " called on context: " + paramContext + " when " + "Kiwi is dead, ignoring...");
    }
  }
  
  private void instantiateTheWorld(Application paramApplication)
  {
    com.amazon.android.framework.resource.c localc = new com.amazon.android.framework.resource.c();
    localc.a(paramApplication);
    localc.a(new com.amazon.android.framework.task.a());
    localc.a(new com.amazon.android.n.a());
    localc.a(new com.amazon.android.f.c());
    localc.a(new com.amazon.android.framework.context.d());
    localc.a(new PromptManagerImpl());
    localc.a(new com.amazon.android.o.b());
    localc.a(new com.amazon.android.c.a());
    localc.a(new com.amazon.android.q.c());
    localc.a(new com.amazon.android.framework.task.command.b());
    localc.a(new com.amazon.android.m.c());
    localc.a(new com.amazon.android.framework.task.command.e());
    localc.a(new com.amazon.android.g.a());
    localc.a();
    localc.b(this);
  }
  
  private static boolean isInitialized()
  {
    return INSTANCE != null;
  }
  
  public static boolean isSignedByKiwi(String paramString1, String paramString2)
  {
    if (isInitialized()) {
      try
      {
        boolean bool = com.amazon.android.m.a.a(paramString1, paramString2, INSTANCE.authKeyLoader.a());
        return bool;
      }
      catch (com.amazon.android.h.a paramString1)
      {
        LOGGER.trace("Unable to validate signature: " + paramString1.getMessage());
      }
    }
    for (;;)
    {
      return false;
      LOGGER.trace("Kiwi was not yet initialized - cannot do the IAP call");
    }
  }
  
  public static boolean onActivityResult(Activity paramActivity, int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (preProcess("onActivityResult", paramActivity))
    {
      paramActivity = new com.amazon.android.f.f(paramActivity, paramInt1, paramInt2, paramIntent);
      return INSTANCE.resultManager.a(paramActivity);
    }
    return false;
  }
  
  public static void onCreate(Activity paramActivity, boolean paramBoolean)
  {
    long l1 = System.currentTimeMillis();
    if (!isInitialized()) {
      INSTANCE = new Kiwi(paramActivity.getApplication(), paramBoolean);
    }
    if (preProcess("onCreate", paramActivity)) {
      INSTANCE.contextManager.onCreate(paramActivity);
    }
    if (KiwiLogger.TRACE_ON)
    {
      long l2 = System.currentTimeMillis();
      LOGGER.trace("Kiwi.ActivityOnCreate Time: " + (l2 - l1));
    }
  }
  
  public static void onCreate(Service paramService, boolean paramBoolean)
  {
    long l1 = System.currentTimeMillis();
    if (preProcess("onCreate", paramService)) {
      INSTANCE.contextManager.onCreate(paramService);
    }
    if (KiwiLogger.TRACE_ON)
    {
      long l2 = System.currentTimeMillis();
      LOGGER.trace("Kiwi.ServiceOnCreate Time: " + (l2 - l1));
    }
  }
  
  public static Dialog onCreateDialog(Activity paramActivity, int paramInt)
  {
    long l1 = System.currentTimeMillis();
    if (preProcess("onCreateDialog", paramActivity)) {
      return INSTANCE.promptManager.onCreateDialog(paramActivity, paramInt);
    }
    if (KiwiLogger.TRACE_ON)
    {
      long l2 = System.currentTimeMillis();
      LOGGER.trace("Kiwi.ActivityOnCreateDialog Time: " + (l2 - l1));
    }
    return null;
  }
  
  public static void onDestroy(Activity paramActivity)
  {
    long l1 = System.currentTimeMillis();
    if (preProcess("onDestroy", paramActivity)) {}
    try
    {
      INSTANCE.contextManager.onDestroy(paramActivity);
      if (KiwiLogger.TRACE_ON)
      {
        long l2 = System.currentTimeMillis();
        LOGGER.trace("Kiwi.ActivityOnDestroy Time: " + (l2 - l1));
      }
      return;
    }
    catch (Exception paramActivity)
    {
      for (;;)
      {
        LOGGER.error("Kiwi.ActivityOnDestroy Error: ", paramActivity);
      }
    }
  }
  
  public static void onDestroy(Service paramService)
  {
    long l1 = System.currentTimeMillis();
    if (preProcess("onDestroy", paramService)) {
      INSTANCE.contextManager.onDestroy(paramService);
    }
    if (KiwiLogger.TRACE_ON)
    {
      long l2 = System.currentTimeMillis();
      LOGGER.trace("Kiwi.ServiceOnDestroy Time: " + (l2 - l1));
    }
  }
  
  public static void onPause(Activity paramActivity)
  {
    long l1 = System.currentTimeMillis();
    if (preProcess("onPause", paramActivity)) {
      INSTANCE.contextManager.onPause(paramActivity);
    }
    if (KiwiLogger.TRACE_ON)
    {
      long l2 = System.currentTimeMillis();
      LOGGER.trace("Kiwi.ActivityOnPause Time: " + (l2 - l1));
    }
  }
  
  public static void onResume(Activity paramActivity)
  {
    long l1 = System.currentTimeMillis();
    if (preProcess("onResume", paramActivity)) {
      INSTANCE.contextManager.onResume(paramActivity);
    }
    if (KiwiLogger.TRACE_ON)
    {
      long l2 = System.currentTimeMillis();
      LOGGER.trace("Kiwi.ActivityOnResume Time: " + (l2 - l1));
    }
  }
  
  public static void onStart(Activity paramActivity)
  {
    long l1 = System.currentTimeMillis();
    if (preProcess("onStart", paramActivity)) {
      INSTANCE.contextManager.onStart(paramActivity);
    }
    if (KiwiLogger.TRACE_ON)
    {
      long l2 = System.currentTimeMillis();
      LOGGER.trace("Kiwi.ActivityOnStart Time: " + (l2 - l1));
    }
  }
  
  public static void onStop(Activity paramActivity)
  {
    long l1 = System.currentTimeMillis();
    if (preProcess("onStop", paramActivity)) {
      INSTANCE.contextManager.onStop(paramActivity);
    }
    if (KiwiLogger.TRACE_ON)
    {
      long l2 = System.currentTimeMillis();
      LOGGER.trace("Kiwi.ActivityOnStop Time: " + (l2 - l1));
    }
  }
  
  public static void onWindowFocusChanged(Activity paramActivity, boolean paramBoolean)
  {
    if (preProcess("onWindowFocusChanged", paramActivity)) {
      INSTANCE.promptManager.onWindowFocusChanged(paramActivity, paramBoolean);
    }
  }
  
  private static boolean preProcess(String paramString, Context paramContext)
  {
    
    if (KiwiLogger.TRACE_ON) {
      LOGGER.trace(paramString + ": " + paramContext);
    }
    if (isInitialized()) {
      return true;
    }
    ignoreEvent(paramString, paramContext);
    return false;
  }
  
  private void registerActivityLifeCyclePauseListener()
  {
    d locald = new d(this);
    this.eventManager.a(locald);
  }
  
  private void registerActivityLifeCycleResumeListener()
  {
    f localf = new f(this);
    this.eventManager.a(localf);
  }
  
  private void registerApplicationCreationListener()
  {
    g localg = new g(this);
    this.eventManager.a(localg);
  }
  
  private void registerApplicationDestructionListener()
  {
    e locale = new e(this);
    this.eventManager.a(locale);
  }
  
  private void registerTestModeReceiver(Application paramApplication)
  {
    paramApplication.registerReceiver(new c(this), new IntentFilter(paramApplication.getPackageName() + ".enable.test.mode"));
  }
  
  private static void unsafeAddCommandToCommandTaskPipeline(AbstractCommandTask paramAbstractCommandTask)
  {
    INSTANCE.taskManager.enqueue(TaskPipelineId.COMMAND, paramAbstractCommandTask);
  }
  
  public final void onResourcesPopulated()
  {
    registerApplicationCreationListener();
    registerApplicationDestructionListener();
    registerActivityLifeCyclePauseListener();
    registerActivityLifeCycleResumeListener();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\Kiwi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */