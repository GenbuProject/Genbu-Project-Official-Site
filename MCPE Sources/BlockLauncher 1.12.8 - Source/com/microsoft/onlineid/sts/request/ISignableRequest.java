package com.microsoft.onlineid.sts.request;

import com.microsoft.onlineid.sts.XmlSigner;
import org.w3c.dom.Element;

public abstract interface ISignableRequest
{
  public abstract Element getParentOfSignatureNode();
  
  public abstract byte[] getSigningSessionKey();
  
  public abstract XmlSigner getXmlSigner();
  
  public abstract void setXmlSigner(XmlSigner paramXmlSigner);
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\sts\request\ISignableRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */