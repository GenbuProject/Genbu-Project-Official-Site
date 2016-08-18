package com.microsoft.onlineid.sts.request;

import com.microsoft.onlineid.analytics.ClientAnalytics;
import com.microsoft.onlineid.analytics.IClientAnalytics;
import com.microsoft.onlineid.internal.Assertion;
import com.microsoft.onlineid.internal.transport.TransportFactory;
import com.microsoft.onlineid.sts.ClockSkewManager;
import com.microsoft.onlineid.sts.ServerConfig.Endpoint;
import com.microsoft.onlineid.sts.response.AbstractStsResponse;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

public abstract class AbstractStsRequest<ResponseType extends AbstractStsResponse>
{
  public static final String AppIdentifier = "MSAAndroidApp";
  public static final String DeviceType = "Android";
  public static final String StsBinaryVersion = "11";
  private ClockSkewManager _clockSkewManager;
  private URL _destination;
  private int _msaAppVersionCode;
  private TransportFactory _transportFactory;
  
  private void updateClockSkew(long paramLong)
  {
    if (paramLong != 0L)
    {
      getClockSkewManager().onTimestampReceived(paramLong);
      ClientAnalytics.get().logClockSkew(getClockSkewManager().getSkewMilliseconds());
    }
  }
  
  public abstract Document buildRequest();
  
  protected final Document createBlankDocument(String paramString1, String paramString2)
  {
    DocumentBuilderFactory localDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
    localDocumentBuilderFactory.setNamespaceAware(true);
    try
    {
      paramString1 = localDocumentBuilderFactory.newDocumentBuilder().getDOMImplementation().createDocument(paramString1, paramString2, null);
      return paramString1;
    }
    catch (ParserConfigurationException paramString1)
    {
      Assertion.check(false);
      throw new RuntimeException("Invalid parser configuration.", paramString1);
    }
  }
  
  protected String getAnalyticsRequestType()
  {
    return "(none)";
  }
  
  protected ClockSkewManager getClockSkewManager()
  {
    return this._clockSkewManager;
  }
  
  public URL getDestination()
  {
    return this._destination;
  }
  
  public abstract ServerConfig.Endpoint getEndpoint();
  
  public int getMsaAppVersionCode()
  {
    return this._msaAppVersionCode;
  }
  
  protected abstract ResponseType instantiateResponse();
  
  /* Error */
  public ResponseType send()
    throws com.microsoft.onlineid.exception.NetworkException, com.microsoft.onlineid.sts.exception.InvalidResponseException
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 130	com/microsoft/onlineid/sts/request/AbstractStsRequest:instantiateResponse	()Lcom/microsoft/onlineid/sts/response/AbstractStsResponse;
    //   4: astore_3
    //   5: aload_0
    //   6: getfield 132	com/microsoft/onlineid/sts/request/AbstractStsRequest:_transportFactory	Lcom/microsoft/onlineid/internal/transport/TransportFactory;
    //   9: invokevirtual 138	com/microsoft/onlineid/internal/transport/TransportFactory:createTransport	()Lcom/microsoft/onlineid/internal/transport/Transport;
    //   12: astore_1
    //   13: aload_1
    //   14: aload_0
    //   15: invokevirtual 140	com/microsoft/onlineid/sts/request/AbstractStsRequest:getDestination	()Ljava/net/URL;
    //   18: invokevirtual 146	com/microsoft/onlineid/internal/transport/Transport:openPostRequest	(Ljava/net/URL;)V
    //   21: aload_1
    //   22: invokevirtual 150	com/microsoft/onlineid/internal/transport/Transport:getRequestStream	()Ljava/io/OutputStream;
    //   25: astore 4
    //   27: invokestatic 45	com/microsoft/onlineid/analytics/ClientAnalytics:get	()Lcom/microsoft/onlineid/analytics/IClientAnalytics;
    //   30: ldc -104
    //   32: aload_0
    //   33: invokevirtual 156	java/lang/Object:getClass	()Ljava/lang/Class;
    //   36: invokevirtual 161	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   39: aload_0
    //   40: invokevirtual 163	com/microsoft/onlineid/sts/request/AbstractStsRequest:getAnalyticsRequestType	()Ljava/lang/String;
    //   43: invokeinterface 167 4 0
    //   48: astore_2
    //   49: aload_2
    //   50: invokeinterface 173 1 0
    //   55: pop
    //   56: invokestatic 178	javax/xml/transform/TransformerFactory:newInstance	()Ljavax/xml/transform/TransformerFactory;
    //   59: invokevirtual 182	javax/xml/transform/TransformerFactory:newTransformer	()Ljavax/xml/transform/Transformer;
    //   62: astore 5
    //   64: invokestatic 188	com/microsoft/onlineid/internal/configuration/Settings:isDebugBuild	()Z
    //   67: ifeq +140 -> 207
    //   70: new 190	java/io/CharArrayWriter
    //   73: dup
    //   74: invokespecial 191	java/io/CharArrayWriter:<init>	()V
    //   77: astore 6
    //   79: aload 5
    //   81: new 193	javax/xml/transform/dom/DOMSource
    //   84: dup
    //   85: aload_0
    //   86: invokevirtual 195	com/microsoft/onlineid/sts/request/AbstractStsRequest:buildRequest	()Lorg/w3c/dom/Document;
    //   89: invokespecial 198	javax/xml/transform/dom/DOMSource:<init>	(Lorg/w3c/dom/Node;)V
    //   92: new 200	javax/xml/transform/stream/StreamResult
    //   95: dup
    //   96: aload 6
    //   98: invokespecial 203	javax/xml/transform/stream/StreamResult:<init>	(Ljava/io/Writer;)V
    //   101: invokevirtual 209	javax/xml/transform/Transformer:transform	(Ljavax/xml/transform/Source;Ljavax/xml/transform/Result;)V
    //   104: aload 6
    //   106: invokevirtual 212	java/io/CharArrayWriter:toString	()Ljava/lang/String;
    //   109: astore 5
    //   111: new 214	com/microsoft/onlineid/internal/log/RedactableXml
    //   114: dup
    //   115: getstatic 220	java/util/Locale:US	Ljava/util/Locale;
    //   118: ldc -34
    //   120: iconst_2
    //   121: anewarray 5	java/lang/Object
    //   124: dup
    //   125: iconst_0
    //   126: aload_0
    //   127: invokevirtual 156	java/lang/Object:getClass	()Ljava/lang/Class;
    //   130: invokevirtual 161	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   133: aastore
    //   134: dup
    //   135: iconst_1
    //   136: aload 5
    //   138: aastore
    //   139: invokestatic 228	java/lang/String:format	(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   142: iconst_0
    //   143: anewarray 224	java/lang/String
    //   146: invokespecial 231	com/microsoft/onlineid/internal/log/RedactableXml:<init>	(Ljava/lang/String;[Ljava/lang/String;)V
    //   149: invokestatic 237	com/microsoft/onlineid/internal/log/Logger:info	(Lcom/microsoft/onlineid/internal/log/IRedactable;)V
    //   152: aload 4
    //   154: aload 5
    //   156: getstatic 243	com/microsoft/onlineid/internal/Strings:Utf8Charset	Ljava/nio/charset/Charset;
    //   159: invokevirtual 247	java/lang/String:getBytes	(Ljava/nio/charset/Charset;)[B
    //   162: invokevirtual 253	java/io/OutputStream:write	([B)V
    //   165: aload 4
    //   167: invokevirtual 256	java/io/OutputStream:close	()V
    //   170: aload_1
    //   171: invokevirtual 260	com/microsoft/onlineid/internal/transport/Transport:getResponseStream	()Ljava/io/InputStream;
    //   174: astore 4
    //   176: aload_0
    //   177: aload_1
    //   178: invokevirtual 263	com/microsoft/onlineid/internal/transport/Transport:getResponseDate	()J
    //   181: invokespecial 265	com/microsoft/onlineid/sts/request/AbstractStsRequest:updateClockSkew	(J)V
    //   184: aload_3
    //   185: aload 4
    //   187: invokevirtual 271	com/microsoft/onlineid/sts/response/AbstractStsResponse:parse	(Ljava/io/InputStream;)V
    //   190: aload_2
    //   191: invokeinterface 274 1 0
    //   196: aload 4
    //   198: invokevirtual 277	java/io/InputStream:close	()V
    //   201: aload_1
    //   202: invokevirtual 280	com/microsoft/onlineid/internal/transport/Transport:closeConnection	()V
    //   205: aload_3
    //   206: areturn
    //   207: aload 5
    //   209: new 193	javax/xml/transform/dom/DOMSource
    //   212: dup
    //   213: aload_0
    //   214: invokevirtual 195	com/microsoft/onlineid/sts/request/AbstractStsRequest:buildRequest	()Lorg/w3c/dom/Document;
    //   217: invokespecial 198	javax/xml/transform/dom/DOMSource:<init>	(Lorg/w3c/dom/Node;)V
    //   220: new 200	javax/xml/transform/stream/StreamResult
    //   223: dup
    //   224: aload 4
    //   226: invokespecial 283	javax/xml/transform/stream/StreamResult:<init>	(Ljava/io/OutputStream;)V
    //   229: invokevirtual 209	javax/xml/transform/Transformer:transform	(Ljavax/xml/transform/Source;Ljavax/xml/transform/Result;)V
    //   232: goto -67 -> 165
    //   235: astore_2
    //   236: ldc_w 285
    //   239: aload_2
    //   240: invokestatic 288	com/microsoft/onlineid/internal/log/Logger:error	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   243: new 94	java/lang/RuntimeException
    //   246: dup
    //   247: ldc_w 285
    //   250: aload_2
    //   251: invokespecial 99	java/lang/RuntimeException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   254: athrow
    //   255: astore_2
    //   256: aload_1
    //   257: invokevirtual 280	com/microsoft/onlineid/internal/transport/Transport:closeConnection	()V
    //   260: aload_2
    //   261: athrow
    //   262: astore_3
    //   263: ldc_w 290
    //   266: aload_3
    //   267: invokestatic 288	com/microsoft/onlineid/internal/log/Logger:error	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   270: new 120	com/microsoft/onlineid/exception/NetworkException
    //   273: dup
    //   274: ldc_w 290
    //   277: aload_3
    //   278: invokespecial 291	com/microsoft/onlineid/exception/NetworkException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   281: athrow
    //   282: astore_3
    //   283: aload_2
    //   284: invokeinterface 274 1 0
    //   289: aload 4
    //   291: invokevirtual 277	java/io/InputStream:close	()V
    //   294: aload_3
    //   295: athrow
    //   296: astore_2
    //   297: ldc_w 293
    //   300: aload_2
    //   301: invokestatic 288	com/microsoft/onlineid/internal/log/Logger:error	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   304: new 94	java/lang/RuntimeException
    //   307: dup
    //   308: ldc_w 293
    //   311: aload_2
    //   312: invokespecial 99	java/lang/RuntimeException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   315: athrow
    //   316: astore_2
    //   317: ldc_w 295
    //   320: aload_2
    //   321: invokestatic 288	com/microsoft/onlineid/internal/log/Logger:error	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   324: new 120	com/microsoft/onlineid/exception/NetworkException
    //   327: dup
    //   328: ldc_w 295
    //   331: aload_2
    //   332: invokespecial 291	com/microsoft/onlineid/exception/NetworkException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   335: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	336	0	this	AbstractStsRequest
    //   12	245	1	localTransport	com.microsoft.onlineid.internal.transport.Transport
    //   48	143	2	localITimedAnalyticsEvent	com.microsoft.onlineid.analytics.ITimedAnalyticsEvent
    //   235	16	2	localTransformerConfigurationException	javax.xml.transform.TransformerConfigurationException
    //   255	29	2	localObject1	Object
    //   296	16	2	localTransformerException	javax.xml.transform.TransformerException
    //   316	16	2	localIOException1	java.io.IOException
    //   4	202	3	localAbstractStsResponse	AbstractStsResponse
    //   262	16	3	localIOException2	java.io.IOException
    //   282	13	3	localObject2	Object
    //   25	265	4	localObject3	Object
    //   62	146	5	localObject4	Object
    //   77	28	6	localCharArrayWriter	java.io.CharArrayWriter
    // Exception table:
    //   from	to	target	type
    //   56	165	235	javax/xml/transform/TransformerConfigurationException
    //   165	184	235	javax/xml/transform/TransformerConfigurationException
    //   190	201	235	javax/xml/transform/TransformerConfigurationException
    //   207	232	235	javax/xml/transform/TransformerConfigurationException
    //   283	296	235	javax/xml/transform/TransformerConfigurationException
    //   56	165	255	finally
    //   165	184	255	finally
    //   190	201	255	finally
    //   207	232	255	finally
    //   236	255	255	finally
    //   283	296	255	finally
    //   297	316	255	finally
    //   317	336	255	finally
    //   184	190	262	java/io/IOException
    //   184	190	282	finally
    //   263	282	282	finally
    //   56	165	296	javax/xml/transform/TransformerException
    //   165	184	296	javax/xml/transform/TransformerException
    //   190	201	296	javax/xml/transform/TransformerException
    //   207	232	296	javax/xml/transform/TransformerException
    //   283	296	296	javax/xml/transform/TransformerException
    //   56	165	316	java/io/IOException
    //   165	184	316	java/io/IOException
    //   190	201	316	java/io/IOException
    //   207	232	316	java/io/IOException
    //   283	296	316	java/io/IOException
  }
  
  public void setClockSkewManager(ClockSkewManager paramClockSkewManager)
  {
    this._clockSkewManager = paramClockSkewManager;
  }
  
  public void setDestination(URL paramURL)
  {
    this._destination = paramURL;
  }
  
  public void setMsaAppVersionCode(int paramInt)
  {
    this._msaAppVersionCode = paramInt;
  }
  
  void setTransportFactory(TransportFactory paramTransportFactory)
  {
    this._transportFactory = paramTransportFactory;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\onlineid\sts\request\AbstractStsRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */