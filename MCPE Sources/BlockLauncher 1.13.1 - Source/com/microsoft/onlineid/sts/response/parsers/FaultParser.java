package com.microsoft.onlineid.sts.response.parsers;

import com.microsoft.onlineid.sts.IntegerCodeServerError;
import com.microsoft.onlineid.sts.StsError;
import com.microsoft.onlineid.sts.exception.StsParseException;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class FaultParser
  extends BasePullParser
{
  private StsError _error;
  
  public FaultParser(XmlPullParser paramXmlPullParser)
  {
    super(paramXmlPullParser, "http://www.w3.org/2003/05/soap-envelope", "Fault");
  }
  
  public StsError getError()
  {
    verifyParseCalled();
    return this._error;
  }
  
  protected void onParse()
    throws XmlPullParserException, IOException, StsParseException
  {
    Object localObject2 = null;
    nextStartTag("S:Detail");
    NodeScope localNodeScope1 = getLocation();
    localNodeScope1.nextStartTag("psf:error");
    NodeScope localNodeScope2 = getLocation();
    Object localObject1 = null;
    Integer localInteger = null;
    while (localNodeScope2.nextStartTagNoThrow())
    {
      Object localObject3 = getPrefixedTagName();
      if (((String)localObject3).equals("psf:value"))
      {
        localInteger = Integer.valueOf(TextParsers.parseIntHex(nextRequiredText()));
      }
      else
      {
        if (((String)localObject3).equals("psf:internalerror"))
        {
          NodeScope localNodeScope3 = getLocation();
          Object localObject4 = localObject1;
          localObject3 = localObject2;
          for (;;)
          {
            localObject2 = localObject3;
            localObject1 = localObject4;
            if (!localNodeScope3.nextStartTagNoThrow()) {
              break;
            }
            localObject1 = getPrefixedTagName();
            if (((String)localObject1).equals("psf:code")) {
              localObject4 = Integer.valueOf(TextParsers.parseIntHex(nextRequiredText()));
            } else if (((String)localObject1).equals("psf:text")) {
              localObject3 = this._parser.nextText();
            } else {
              skipElement();
            }
          }
        }
        skipElement();
      }
    }
    localNodeScope1.finish();
    if (localInteger == null) {
      throw new StsParseException("psf:value node does not exist.", new Object[0]);
    }
    if (localObject1 == null) {
      throw new StsParseException("psf:code node does not exist.", new Object[0]);
    }
    this._error = new StsError(new IntegerCodeServerError(localInteger.intValue(), ((Integer)localObject1).intValue(), (String)localObject2));
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\onlineid\sts\response\parsers\FaultParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */