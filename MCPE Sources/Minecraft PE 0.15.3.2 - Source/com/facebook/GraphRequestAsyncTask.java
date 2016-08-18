package com.facebook;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;
import java.net.HttpURLConnection;
import java.util.Collection;
import java.util.List;

public class GraphRequestAsyncTask
  extends AsyncTask<Void, Void, List<GraphResponse>>
{
  private static final String TAG = GraphRequestAsyncTask.class.getCanonicalName();
  private final HttpURLConnection connection;
  private Exception exception;
  private final GraphRequestBatch requests;
  
  public GraphRequestAsyncTask(GraphRequestBatch paramGraphRequestBatch)
  {
    this(null, paramGraphRequestBatch);
  }
  
  public GraphRequestAsyncTask(HttpURLConnection paramHttpURLConnection, GraphRequestBatch paramGraphRequestBatch)
  {
    this.requests = paramGraphRequestBatch;
    this.connection = paramHttpURLConnection;
  }
  
  public GraphRequestAsyncTask(HttpURLConnection paramHttpURLConnection, Collection<GraphRequest> paramCollection)
  {
    this(paramHttpURLConnection, new GraphRequestBatch(paramCollection));
  }
  
  public GraphRequestAsyncTask(HttpURLConnection paramHttpURLConnection, GraphRequest... paramVarArgs)
  {
    this(paramHttpURLConnection, new GraphRequestBatch(paramVarArgs));
  }
  
  public GraphRequestAsyncTask(Collection<GraphRequest> paramCollection)
  {
    this(null, new GraphRequestBatch(paramCollection));
  }
  
  public GraphRequestAsyncTask(GraphRequest... paramVarArgs)
  {
    this(null, new GraphRequestBatch(paramVarArgs));
  }
  
  protected List<GraphResponse> doInBackground(Void... paramVarArgs)
  {
    try
    {
      if (this.connection == null) {
        return this.requests.executeAndWait();
      }
      paramVarArgs = GraphRequest.executeConnectionAndWait(this.connection, this.requests);
      return paramVarArgs;
    }
    catch (Exception paramVarArgs)
    {
      this.exception = paramVarArgs;
    }
    return null;
  }
  
  protected final Exception getException()
  {
    return this.exception;
  }
  
  protected final GraphRequestBatch getRequests()
  {
    return this.requests;
  }
  
  protected void onPostExecute(List<GraphResponse> paramList)
  {
    super.onPostExecute(paramList);
    if (this.exception != null) {
      Log.d(TAG, String.format("onPostExecute: exception encountered during request: %s", new Object[] { this.exception.getMessage() }));
    }
  }
  
  protected void onPreExecute()
  {
    super.onPreExecute();
    if (FacebookSdk.isDebugEnabled()) {
      Log.d(TAG, String.format("execute async task: %s", new Object[] { this }));
    }
    if (this.requests.getCallbackHandler() == null) {
      if (!(Thread.currentThread() instanceof HandlerThread)) {
        break label66;
      }
    }
    label66:
    for (Handler localHandler = new Handler();; localHandler = new Handler(Looper.getMainLooper()))
    {
      this.requests.setCallbackHandler(localHandler);
      return;
    }
  }
  
  public String toString()
  {
    return "{RequestAsyncTask: " + " connection: " + this.connection + ", requests: " + this.requests + "}";
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\facebook\GraphRequestAsyncTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */