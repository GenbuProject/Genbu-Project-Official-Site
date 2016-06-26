package com.microsoft.onlineid.sts.response;

import android.util.Xml;
import com.microsoft.onlineid.analytics.ClientAnalytics;
import com.microsoft.onlineid.analytics.IClientAnalytics;
import com.microsoft.onlineid.internal.Strings;
import com.microsoft.onlineid.internal.log.Logger;
import com.microsoft.onlineid.internal.log.RedactableResponse;
import com.microsoft.onlineid.sts.StsError;
import com.microsoft.onlineid.sts.StsErrorCode;
import com.microsoft.onlineid.sts.exception.StsParseException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Locale;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public abstract class AbstractStsResponse
{
  public abstract StsError getError();
  
  public void parse(InputStream paramInputStream)
    throws IOException, StsParseException
  {
    try
    {
      XmlPullParser localXmlPullParser = Xml.newPullParser();
      paramInputStream = Strings.fromStream(paramInputStream, Strings.Utf8Charset);
      Logger.info(new RedactableResponse(String.format(Locale.US, "%s: %s", new Object[] { getClass().getSimpleName(), paramInputStream })));
      localXmlPullParser.setInput(new StringReader(paramInputStream));
      parse(localXmlPullParser);
      if (getError() != null)
      {
        paramInputStream = getError();
        ClientAnalytics.get().logEvent("Server errors", paramInputStream.getCode().name(), getClass().getSimpleName() + ": " + paramInputStream.getOriginalErrorMessage());
      }
      return;
    }
    catch (XmlPullParserException paramInputStream)
    {
      throw new StsParseException("XML response could not be properly parsed.", paramInputStream, new Object[0]);
    }
  }
  
  protected abstract void parse(XmlPullParser paramXmlPullParser)
    throws IOException, StsParseException;
  
  public boolean succeeded()
  {
    return getError() == null;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\sts\response\AbstractStsResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */