package org.mozilla.javascript.ast;

import java.io.Serializable;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.Node;
import org.mozilla.javascript.Token;

public abstract class AstNode
  extends Node
  implements Comparable<AstNode>
{
  private static Map<Integer, String> operatorNames = new HashMap();
  protected int length = 1;
  protected AstNode parent;
  protected int position = -1;
  
  static
  {
    operatorNames.put(Integer.valueOf(52), "in");
    operatorNames.put(Integer.valueOf(32), "typeof");
    operatorNames.put(Integer.valueOf(53), "instanceof");
    operatorNames.put(Integer.valueOf(31), "delete");
    operatorNames.put(Integer.valueOf(90), ",");
    operatorNames.put(Integer.valueOf(104), ":");
    operatorNames.put(Integer.valueOf(105), "||");
    operatorNames.put(Integer.valueOf(106), "&&");
    operatorNames.put(Integer.valueOf(107), "++");
    operatorNames.put(Integer.valueOf(108), "--");
    operatorNames.put(Integer.valueOf(9), "|");
    operatorNames.put(Integer.valueOf(10), "^");
    operatorNames.put(Integer.valueOf(11), "&");
    operatorNames.put(Integer.valueOf(12), "==");
    operatorNames.put(Integer.valueOf(13), "!=");
    operatorNames.put(Integer.valueOf(14), "<");
    operatorNames.put(Integer.valueOf(16), ">");
    operatorNames.put(Integer.valueOf(15), "<=");
    operatorNames.put(Integer.valueOf(17), ">=");
    operatorNames.put(Integer.valueOf(18), "<<");
    operatorNames.put(Integer.valueOf(19), ">>");
    operatorNames.put(Integer.valueOf(20), ">>>");
    operatorNames.put(Integer.valueOf(21), "+");
    operatorNames.put(Integer.valueOf(22), "-");
    operatorNames.put(Integer.valueOf(23), "*");
    operatorNames.put(Integer.valueOf(24), "/");
    operatorNames.put(Integer.valueOf(25), "%");
    operatorNames.put(Integer.valueOf(26), "!");
    operatorNames.put(Integer.valueOf(27), "~");
    operatorNames.put(Integer.valueOf(28), "+");
    operatorNames.put(Integer.valueOf(29), "-");
    operatorNames.put(Integer.valueOf(46), "===");
    operatorNames.put(Integer.valueOf(47), "!==");
    operatorNames.put(Integer.valueOf(91), "=");
    operatorNames.put(Integer.valueOf(92), "|=");
    operatorNames.put(Integer.valueOf(94), "&=");
    operatorNames.put(Integer.valueOf(95), "<<=");
    operatorNames.put(Integer.valueOf(96), ">>=");
    operatorNames.put(Integer.valueOf(97), ">>>=");
    operatorNames.put(Integer.valueOf(98), "+=");
    operatorNames.put(Integer.valueOf(99), "-=");
    operatorNames.put(Integer.valueOf(100), "*=");
    operatorNames.put(Integer.valueOf(101), "/=");
    operatorNames.put(Integer.valueOf(102), "%=");
    operatorNames.put(Integer.valueOf(93), "^=");
    operatorNames.put(Integer.valueOf(127), "void");
  }
  
  public AstNode()
  {
    super(-1);
  }
  
  public AstNode(int paramInt)
  {
    this();
  }
  
  public AstNode(int paramInt1, int paramInt2)
  {
    this();
  }
  
  public static RuntimeException codeBug()
    throws RuntimeException
  {
    throw Kit.codeBug();
  }
  
  public static String operatorToString(int paramInt)
  {
    String str = (String)operatorNames.get(Integer.valueOf(paramInt));
    if (str == null) {
      throw new IllegalArgumentException("Invalid operator: " + paramInt);
    }
    return str;
  }
  
  public void addChild(AstNode paramAstNode)
  {
    assertNotNull(paramAstNode);
    setLength(paramAstNode.getPosition() + paramAstNode.getLength() - getPosition());
    addChildToBack(paramAstNode);
    paramAstNode.setParent(this);
  }
  
  protected void assertNotNull(Object paramObject)
  {
    if (paramObject == null) {
      throw new IllegalArgumentException("arg cannot be null");
    }
  }
  
  public int compareTo(AstNode paramAstNode)
  {
    int j = -1;
    int i;
    if (equals(paramAstNode)) {
      i = 0;
    }
    int k;
    int m;
    do
    {
      do
      {
        return i;
        k = getAbsolutePosition();
        m = paramAstNode.getAbsolutePosition();
        i = j;
      } while (k < m);
      if (m < k) {
        return 1;
      }
      k = getLength();
      m = paramAstNode.getLength();
      i = j;
    } while (k < m);
    if (m < k) {
      return 1;
    }
    return hashCode() - paramAstNode.hashCode();
  }
  
  public String debugPrint()
  {
    DebugPrintVisitor localDebugPrintVisitor = new DebugPrintVisitor(new StringBuilder(1000));
    visit(localDebugPrintVisitor);
    return localDebugPrintVisitor.toString();
  }
  
  public int depth()
  {
    if (this.parent == null) {
      return 0;
    }
    return this.parent.depth() + 1;
  }
  
  public int getAbsolutePosition()
  {
    int i = this.position;
    for (AstNode localAstNode = this.parent; localAstNode != null; localAstNode = localAstNode.getParent()) {
      i += localAstNode.getPosition();
    }
    return i;
  }
  
  public AstRoot getAstRoot()
  {
    for (AstNode localAstNode = this; (localAstNode != null) && (!(localAstNode instanceof AstRoot)); localAstNode = localAstNode.getParent()) {}
    return (AstRoot)localAstNode;
  }
  
  public FunctionNode getEnclosingFunction()
  {
    for (AstNode localAstNode = getParent(); (localAstNode != null) && (!(localAstNode instanceof FunctionNode)); localAstNode = localAstNode.getParent()) {}
    return (FunctionNode)localAstNode;
  }
  
  public Scope getEnclosingScope()
  {
    for (AstNode localAstNode = getParent(); (localAstNode != null) && (!(localAstNode instanceof Scope)); localAstNode = localAstNode.getParent()) {}
    return (Scope)localAstNode;
  }
  
  public int getLength()
  {
    return this.length;
  }
  
  public int getLineno()
  {
    int i = -1;
    if (this.lineno != -1) {
      i = this.lineno;
    }
    while (this.parent == null) {
      return i;
    }
    return this.parent.getLineno();
  }
  
  public AstNode getParent()
  {
    return this.parent;
  }
  
  public int getPosition()
  {
    return this.position;
  }
  
  public boolean hasSideEffects()
  {
    switch (getType())
    {
    case 0: 
    case 1: 
    case 9: 
    case 10: 
    case 11: 
    case 12: 
    case 13: 
    case 14: 
    case 15: 
    case 16: 
    case 17: 
    case 18: 
    case 19: 
    case 20: 
    case 21: 
    case 22: 
    case 23: 
    case 24: 
    case 25: 
    case 26: 
    case 27: 
    case 28: 
    case 29: 
    case 32: 
    case 33: 
    case 34: 
    case 36: 
    case 39: 
    case 40: 
    case 41: 
    case 42: 
    case 43: 
    case 44: 
    case 45: 
    case 46: 
    case 47: 
    case 48: 
    case 49: 
    case 52: 
    case 53: 
    case 54: 
    case 55: 
    case 58: 
    case 59: 
    case 60: 
    case 61: 
    case 62: 
    case 63: 
    case 64: 
    case 66: 
    case 67: 
    case 68: 
    case 72: 
    case 74: 
    case 75: 
    case 76: 
    case 77: 
    case 78: 
    case 79: 
    case 80: 
    case 81: 
    case 84: 
    case 85: 
    case 86: 
    case 87: 
    case 88: 
    case 89: 
    case 90: 
    case 103: 
    case 104: 
    case 105: 
    case 106: 
    case 109: 
    case 116: 
    case 117: 
    case 127: 
    case 128: 
    case 129: 
    case 134: 
    case 137: 
    case 138: 
    case 139: 
    case 144: 
    case 145: 
    case 146: 
    case 147: 
    case 148: 
    case 149: 
    case 150: 
    case 151: 
    case 152: 
    case 153: 
    case 156: 
    case 157: 
    case 158: 
    default: 
      return false;
    }
    return true;
  }
  
  public String makeIndent(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    while (i < paramInt)
    {
      localStringBuilder.append("  ");
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  protected <T extends AstNode> void printList(List<T> paramList, StringBuilder paramStringBuilder)
  {
    int j = paramList.size();
    paramList = paramList.iterator();
    int i = 0;
    if (paramList.hasNext())
    {
      AstNode localAstNode = (AstNode)paramList.next();
      paramStringBuilder.append(localAstNode.toSource(0));
      if (i < j - 1) {
        paramStringBuilder.append(", ");
      }
      for (;;)
      {
        i += 1;
        break;
        if ((localAstNode instanceof EmptyExpression)) {
          paramStringBuilder.append(",");
        }
      }
    }
  }
  
  public void setBounds(int paramInt1, int paramInt2)
  {
    setPosition(paramInt1);
    setLength(paramInt2 - paramInt1);
  }
  
  public void setLength(int paramInt)
  {
    this.length = paramInt;
  }
  
  public void setParent(AstNode paramAstNode)
  {
    if (paramAstNode == this.parent) {}
    do
    {
      return;
      if (this.parent != null) {
        setRelative(-this.parent.getPosition());
      }
      this.parent = paramAstNode;
    } while (paramAstNode == null);
    setRelative(paramAstNode.getPosition());
  }
  
  public void setPosition(int paramInt)
  {
    this.position = paramInt;
  }
  
  public void setRelative(int paramInt)
  {
    this.position -= paramInt;
  }
  
  public String shortName()
  {
    String str = getClass().getName();
    return str.substring(str.lastIndexOf(".") + 1);
  }
  
  public String toSource()
  {
    return toSource(0);
  }
  
  public abstract String toSource(int paramInt);
  
  public abstract void visit(NodeVisitor paramNodeVisitor);
  
  protected static class DebugPrintVisitor
    implements NodeVisitor
  {
    private static final int DEBUG_INDENT = 2;
    private StringBuilder buffer;
    
    public DebugPrintVisitor(StringBuilder paramStringBuilder)
    {
      this.buffer = paramStringBuilder;
    }
    
    private String makeIndent(int paramInt)
    {
      StringBuilder localStringBuilder = new StringBuilder(paramInt * 2);
      int i = 0;
      while (i < paramInt * 2)
      {
        localStringBuilder.append(" ");
        i += 1;
      }
      return localStringBuilder.toString();
    }
    
    public String toString()
    {
      return this.buffer.toString();
    }
    
    public boolean visit(AstNode paramAstNode)
    {
      int i = paramAstNode.getType();
      String str = Token.typeToName(i);
      this.buffer.append(paramAstNode.getAbsolutePosition()).append("\t");
      this.buffer.append(makeIndent(paramAstNode.depth()));
      this.buffer.append(str).append(" ");
      this.buffer.append(paramAstNode.getPosition()).append(" ");
      this.buffer.append(paramAstNode.getLength());
      if (i == 39) {
        this.buffer.append(" ").append(((Name)paramAstNode).getIdentifier());
      }
      this.buffer.append("\n");
      return true;
    }
  }
  
  public static class PositionComparator
    implements Comparator<AstNode>, Serializable
  {
    private static final long serialVersionUID = 1L;
    
    public int compare(AstNode paramAstNode1, AstNode paramAstNode2)
    {
      return paramAstNode1.position - paramAstNode2.position;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\ast\AstNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */