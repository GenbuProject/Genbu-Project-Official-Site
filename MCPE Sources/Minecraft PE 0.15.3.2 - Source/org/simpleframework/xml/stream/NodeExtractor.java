package org.simpleframework.xml.stream;

import java.util.LinkedList;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

class NodeExtractor
  extends LinkedList<Node>
{
  public NodeExtractor(Document paramDocument)
  {
    extract(paramDocument);
  }
  
  private void extract(Document paramDocument)
  {
    paramDocument = paramDocument.getDocumentElement();
    if (paramDocument != null)
    {
      offer(paramDocument);
      extract(paramDocument);
    }
  }
  
  private void extract(Node paramNode)
  {
    paramNode = paramNode.getChildNodes();
    int j = paramNode.getLength();
    int i = 0;
    while (i < j)
    {
      Node localNode = paramNode.item(i);
      if (localNode.getNodeType() != 8)
      {
        offer(localNode);
        extract(localNode);
      }
      i += 1;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\�h�L�������g\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\stream\NodeExtractor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */