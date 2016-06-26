package com.microsoft.onlineid.sts.request;

import java.io.IOException;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class Requests
{
  public static Element appendElement(Node paramNode, String paramString)
  {
    paramString = paramNode.getOwnerDocument().createElement(paramString);
    paramNode.appendChild(paramString);
    return paramString;
  }
  
  public static Element appendElement(Node paramNode, String paramString1, String paramString2)
  {
    paramNode = appendElement(paramNode, paramString1);
    paramNode.setTextContent(paramString2);
    return paramNode;
  }
  
  public static Element xmlStringToElement(String paramString)
    throws SAXException
  {
    try
    {
      paramString = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(paramString))).getDocumentElement();
      return paramString;
    }
    catch (ParserConfigurationException paramString)
    {
      throw new RuntimeException(paramString);
    }
    catch (IOException paramString)
    {
      throw new RuntimeException(paramString);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\sts\request\Requests.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */