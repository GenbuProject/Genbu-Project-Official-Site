package net.zhuoweizhang.mcpelauncher;

import java.net.HttpURLConnection;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class TrustModifier
{
  private static final TrustingHostnameVerifier TRUSTING_HOSTNAME_VERIFIER = new TrustingHostnameVerifier(null);
  private static SSLSocketFactory factory;
  
  static SSLSocketFactory prepFactory(HttpsURLConnection paramHttpsURLConnection)
    throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException
  {
    try
    {
      if (factory == null)
      {
        paramHttpsURLConnection = SSLContext.getInstance("TLS");
        paramHttpsURLConnection.init(null, new TrustManager[] { new AlwaysTrustManager(null) }, null);
        factory = paramHttpsURLConnection.getSocketFactory();
      }
      paramHttpsURLConnection = factory;
      return paramHttpsURLConnection;
    }
    finally {}
  }
  
  public static void relaxHostChecking(HttpURLConnection paramHttpURLConnection)
    throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException
  {
    if ((paramHttpURLConnection instanceof HttpsURLConnection))
    {
      paramHttpURLConnection = (HttpsURLConnection)paramHttpURLConnection;
      paramHttpURLConnection.setSSLSocketFactory(prepFactory(paramHttpURLConnection));
      paramHttpURLConnection.setHostnameVerifier(TRUSTING_HOSTNAME_VERIFIER);
    }
  }
  
  private static class AlwaysTrustManager
    implements X509TrustManager
  {
    public void checkClientTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString)
      throws CertificateException
    {}
    
    public void checkServerTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString)
      throws CertificateException
    {}
    
    public X509Certificate[] getAcceptedIssuers()
    {
      return null;
    }
  }
  
  private static final class TrustingHostnameVerifier
    implements HostnameVerifier
  {
    public boolean verify(String paramString, SSLSession paramSSLSession)
    {
      return true;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\net\zhuoweizhang\mcpelauncher\TrustModifier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */