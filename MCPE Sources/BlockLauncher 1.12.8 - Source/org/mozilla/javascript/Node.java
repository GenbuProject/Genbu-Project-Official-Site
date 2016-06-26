package org.mozilla.javascript;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.mozilla.javascript.ast.Comment;
import org.mozilla.javascript.ast.Jump;
import org.mozilla.javascript.ast.Name;
import org.mozilla.javascript.ast.NumberLiteral;
import org.mozilla.javascript.ast.Scope;
import org.mozilla.javascript.ast.ScriptNode;

public class Node
  implements Iterable<Node>
{
  public static final int ARROW_FUNCTION_PROP = 27;
  public static final int ATTRIBUTE_FLAG = 2;
  public static final int BOTH = 0;
  public static final int CASEARRAY_PROP = 5;
  public static final int CATCH_SCOPE_PROP = 14;
  public static final int CONTROL_BLOCK_PROP = 18;
  public static final int DECR_FLAG = 1;
  public static final int DESCENDANTS_FLAG = 4;
  public static final int DESTRUCTURING_ARRAY_LENGTH = 21;
  public static final int DESTRUCTURING_NAMES = 22;
  public static final int DESTRUCTURING_PARAMS = 23;
  public static final int DESTRUCTURING_SHORTHAND = 26;
  public static final int DIRECTCALL_PROP = 9;
  public static final int END_DROPS_OFF = 1;
  public static final int END_RETURNS = 2;
  public static final int END_RETURNS_VALUE = 4;
  public static final int END_UNREACHED = 0;
  public static final int END_YIELDS = 8;
  public static final int EXPRESSION_CLOSURE_PROP = 25;
  public static final int FUNCTION_PROP = 1;
  public static final int GENERATOR_END_PROP = 20;
  public static final int INCRDECR_PROP = 13;
  public static final int ISNUMBER_PROP = 8;
  public static final int JSDOC_PROP = 24;
  public static final int LABEL_ID_PROP = 15;
  public static final int LAST_PROP = 27;
  public static final int LEFT = 1;
  public static final int LOCAL_BLOCK_PROP = 3;
  public static final int LOCAL_PROP = 2;
  public static final int MEMBER_TYPE_PROP = 16;
  public static final int NAME_PROP = 17;
  public static final int NON_SPECIALCALL = 0;
  private static final Node NOT_SET = new Node(-1);
  public static final int OBJECT_IDS_PROP = 12;
  public static final int PARENTHESIZED_PROP = 19;
  public static final int POST_FLAG = 2;
  public static final int PROPERTY_FLAG = 1;
  public static final int REGEXP_PROP = 4;
  public static final int RIGHT = 2;
  public static final int SKIP_INDEXES_PROP = 11;
  public static final int SPECIALCALL_EVAL = 1;
  public static final int SPECIALCALL_PROP = 10;
  public static final int SPECIALCALL_WITH = 2;
  public static final int TARGETBLOCK_PROP = 6;
  public static final int VARIABLE_PROP = 7;
  protected Node first;
  protected Node last;
  protected int lineno = -1;
  protected Node next;
  protected PropListItem propListHead;
  protected int type = -1;
  
  public Node(int paramInt)
  {
    this.type = paramInt;
  }
  
  public Node(int paramInt1, int paramInt2)
  {
    this.type = paramInt1;
    this.lineno = paramInt2;
  }
  
  public Node(int paramInt, Node paramNode)
  {
    this.type = paramInt;
    this.last = paramNode;
    this.first = paramNode;
    paramNode.next = null;
  }
  
  public Node(int paramInt1, Node paramNode, int paramInt2)
  {
    this(paramInt1, paramNode);
    this.lineno = paramInt2;
  }
  
  public Node(int paramInt, Node paramNode1, Node paramNode2)
  {
    this.type = paramInt;
    this.first = paramNode1;
    this.last = paramNode2;
    paramNode1.next = paramNode2;
    paramNode2.next = null;
  }
  
  public Node(int paramInt1, Node paramNode1, Node paramNode2, int paramInt2)
  {
    this(paramInt1, paramNode1, paramNode2);
    this.lineno = paramInt2;
  }
  
  public Node(int paramInt, Node paramNode1, Node paramNode2, Node paramNode3)
  {
    this.type = paramInt;
    this.first = paramNode1;
    this.last = paramNode3;
    paramNode1.next = paramNode2;
    paramNode2.next = paramNode3;
    paramNode3.next = null;
  }
  
  public Node(int paramInt1, Node paramNode1, Node paramNode2, Node paramNode3, int paramInt2)
  {
    this(paramInt1, paramNode1, paramNode2, paramNode3);
    this.lineno = paramInt2;
  }
  
  private static void appendPrintId(Node paramNode, ObjToIntMap paramObjToIntMap, StringBuilder paramStringBuilder) {}
  
  private int endCheck()
  {
    switch (this.type)
    {
    }
    do
    {
      do
      {
        do
        {
          return 1;
          return endCheckBreak();
        } while (this.first == null);
        return this.first.endCheck();
        return 8;
        return 0;
        if (this.first != null) {
          return 4;
        }
        return 2;
      } while (this.next == null);
      return this.next.endCheck();
      return endCheckLoop();
    } while (this.first == null);
    switch (this.first.type)
    {
    default: 
      return endCheckBlock();
    case 131: 
      return this.first.endCheckLabel();
    case 7: 
      return this.first.endCheckIf();
    case 115: 
      return this.first.endCheckSwitch();
    }
    return this.first.endCheckTry();
  }
  
  private int endCheckBlock()
  {
    int i = 1;
    for (Node localNode = this.first; ((i & 0x1) != 0) && (localNode != null); localNode = localNode.next) {
      i = i & 0xFFFFFFFE | localNode.endCheck();
    }
    return i;
  }
  
  private int endCheckBreak()
  {
    ((Jump)this).getJumpStatement().putIntProp(18, 1);
    return 0;
  }
  
  private int endCheckIf()
  {
    Node localNode1 = this.next;
    Node localNode2 = ((Jump)this).target;
    int i = localNode1.endCheck();
    if (localNode2 != null) {
      return i | localNode2.endCheck();
    }
    return i | 0x1;
  }
  
  private int endCheckLabel()
  {
    return this.next.endCheck() | getIntProp(18, 0);
  }
  
  private int endCheckLoop()
  {
    for (Node localNode = this.first; localNode.next != this.last; localNode = localNode.next) {}
    if (localNode.type != 6) {
      return 1;
    }
    int j = ((Jump)localNode).target.next.endCheck();
    int i = j;
    if (localNode.first.type == 45) {
      i = j & 0xFFFFFFFE;
    }
    return i | getIntProp(18, 0);
  }
  
  private int endCheckSwitch()
  {
    return 0;
  }
  
  private int endCheckTry()
  {
    return 0;
  }
  
  private PropListItem ensureProperty(int paramInt)
  {
    PropListItem localPropListItem2 = lookupProperty(paramInt);
    PropListItem localPropListItem1 = localPropListItem2;
    if (localPropListItem2 == null)
    {
      localPropListItem1 = new PropListItem(null);
      localPropListItem1.type = paramInt;
      localPropListItem1.next = this.propListHead;
      this.propListHead = localPropListItem1;
    }
    return localPropListItem1;
  }
  
  private static void generatePrintIds(Node paramNode, ObjToIntMap paramObjToIntMap) {}
  
  private PropListItem lookupProperty(int paramInt)
  {
    for (PropListItem localPropListItem = this.propListHead; (localPropListItem != null) && (paramInt != localPropListItem.type); localPropListItem = localPropListItem.next) {}
    return localPropListItem;
  }
  
  public static Node newNumber(double paramDouble)
  {
    NumberLiteral localNumberLiteral = new NumberLiteral();
    localNumberLiteral.setNumber(paramDouble);
    return localNumberLiteral;
  }
  
  public static Node newString(int paramInt, String paramString)
  {
    Name localName = new Name();
    localName.setIdentifier(paramString);
    localName.setType(paramInt);
    return localName;
  }
  
  public static Node newString(String paramString)
  {
    return newString(41, paramString);
  }
  
  public static Node newTarget()
  {
    return new Node(132);
  }
  
  private static final String propToString(int paramInt)
  {
    return null;
  }
  
  private void resetTargets_r()
  {
    if ((this.type == 132) || (this.type == 73)) {
      labelId(-1);
    }
    for (Node localNode = this.first; localNode != null; localNode = localNode.next) {
      localNode.resetTargets_r();
    }
  }
  
  private void toString(ObjToIntMap paramObjToIntMap, StringBuilder paramStringBuilder) {}
  
  private static void toStringTreeHelper(ScriptNode paramScriptNode, Node paramNode, ObjToIntMap paramObjToIntMap, int paramInt, StringBuilder paramStringBuilder) {}
  
  public void addChildAfter(Node paramNode1, Node paramNode2)
  {
    if (paramNode1.next != null) {
      throw new RuntimeException("newChild had siblings in addChildAfter");
    }
    paramNode1.next = paramNode2.next;
    paramNode2.next = paramNode1;
    if (this.last == paramNode2) {
      this.last = paramNode1;
    }
  }
  
  public void addChildBefore(Node paramNode1, Node paramNode2)
  {
    if (paramNode1.next != null) {
      throw new RuntimeException("newChild had siblings in addChildBefore");
    }
    if (this.first == paramNode2)
    {
      paramNode1.next = this.first;
      this.first = paramNode1;
      return;
    }
    addChildAfter(paramNode1, getChildBefore(paramNode2));
  }
  
  public void addChildToBack(Node paramNode)
  {
    paramNode.next = null;
    if (this.last == null)
    {
      this.last = paramNode;
      this.first = paramNode;
      return;
    }
    this.last.next = paramNode;
    this.last = paramNode;
  }
  
  public void addChildToFront(Node paramNode)
  {
    paramNode.next = this.first;
    this.first = paramNode;
    if (this.last == null) {
      this.last = paramNode;
    }
  }
  
  public void addChildrenToBack(Node paramNode)
  {
    if (this.last != null) {
      this.last.next = paramNode;
    }
    this.last = paramNode.getLastSibling();
    if (this.first == null) {
      this.first = paramNode;
    }
  }
  
  public void addChildrenToFront(Node paramNode)
  {
    Node localNode = paramNode.getLastSibling();
    localNode.next = this.first;
    this.first = paramNode;
    if (this.last == null) {
      this.last = localNode;
    }
  }
  
  public Node getChildBefore(Node paramNode)
  {
    Object localObject2;
    if (paramNode == this.first)
    {
      localObject2 = null;
      return (Node)localObject2;
    }
    Object localObject1 = this.first;
    do
    {
      localObject2 = localObject1;
      if (((Node)localObject1).next == paramNode) {
        break;
      }
      localObject2 = ((Node)localObject1).next;
      localObject1 = localObject2;
    } while (localObject2 != null);
    throw new RuntimeException("node is not a child");
  }
  
  public final double getDouble()
  {
    return ((NumberLiteral)this).getNumber();
  }
  
  public int getExistingIntProp(int paramInt)
  {
    PropListItem localPropListItem = lookupProperty(paramInt);
    if (localPropListItem == null) {
      Kit.codeBug();
    }
    return localPropListItem.intValue;
  }
  
  public Node getFirstChild()
  {
    return this.first;
  }
  
  public int getIntProp(int paramInt1, int paramInt2)
  {
    PropListItem localPropListItem = lookupProperty(paramInt1);
    if (localPropListItem == null) {
      return paramInt2;
    }
    return localPropListItem.intValue;
  }
  
  public String getJsDoc()
  {
    Comment localComment = getJsDocNode();
    if (localComment != null) {
      return localComment.getValue();
    }
    return null;
  }
  
  public Comment getJsDocNode()
  {
    return (Comment)getProp(24);
  }
  
  public Node getLastChild()
  {
    return this.last;
  }
  
  public Node getLastSibling()
  {
    for (Node localNode = this; localNode.next != null; localNode = localNode.next) {}
    return localNode;
  }
  
  public int getLineno()
  {
    return this.lineno;
  }
  
  public Node getNext()
  {
    return this.next;
  }
  
  public Object getProp(int paramInt)
  {
    PropListItem localPropListItem = lookupProperty(paramInt);
    if (localPropListItem == null) {
      return null;
    }
    return localPropListItem.objectValue;
  }
  
  public Scope getScope()
  {
    return ((Name)this).getScope();
  }
  
  public final String getString()
  {
    return ((Name)this).getIdentifier();
  }
  
  public int getType()
  {
    return this.type;
  }
  
  public boolean hasChildren()
  {
    return this.first != null;
  }
  
  public boolean hasConsistentReturnUsage()
  {
    int i = endCheck();
    return ((i & 0x4) == 0) || ((i & 0xB) == 0);
  }
  
  public boolean hasSideEffects()
  {
    boolean bool2 = false;
    boolean bool3 = true;
    boolean bool1 = bool3;
    switch (this.type)
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
    case 104: 
    case 109: 
    case 110: 
    case 111: 
    case 112: 
    case 116: 
    case 117: 
    case 127: 
    case 128: 
    case 129: 
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
      bool1 = false;
    case -1: 
    case 2: 
    case 3: 
    case 4: 
    case 5: 
    case 6: 
    case 7: 
    case 8: 
    case 30: 
    case 31: 
    case 35: 
    case 37: 
    case 38: 
    case 50: 
    case 51: 
    case 56: 
    case 57: 
    case 65: 
    case 69: 
    case 70: 
    case 71: 
    case 73: 
    case 82: 
    case 83: 
    case 91: 
    case 92: 
    case 93: 
    case 94: 
    case 95: 
    case 96: 
    case 97: 
    case 98: 
    case 99: 
    case 100: 
    case 101: 
    case 102: 
    case 107: 
    case 108: 
    case 113: 
    case 114: 
    case 115: 
    case 118: 
    case 119: 
    case 120: 
    case 121: 
    case 122: 
    case 123: 
    case 124: 
    case 125: 
    case 126: 
    case 130: 
    case 131: 
    case 132: 
    case 133: 
    case 135: 
    case 136: 
    case 140: 
    case 141: 
    case 142: 
    case 143: 
    case 154: 
    case 155: 
    case 159: 
    case 160: 
    case 90: 
    case 134: 
    case 103: 
      do
      {
        do
        {
          return bool1;
          bool1 = bool3;
        } while (this.last == null);
        return this.last.hasSideEffects();
        if ((this.first == null) || (this.first.next == null) || (this.first.next.next == null)) {
          Kit.codeBug();
        }
        if (!this.first.next.hasSideEffects()) {
          break;
        }
        bool1 = bool3;
      } while (this.first.next.next.hasSideEffects());
      return false;
    }
    if ((this.first == null) || (this.last == null)) {
      Kit.codeBug();
    }
    if (!this.first.hasSideEffects())
    {
      bool1 = bool2;
      if (!this.last.hasSideEffects()) {}
    }
    else
    {
      bool1 = true;
    }
    return bool1;
  }
  
  public Iterator<Node> iterator()
  {
    return new NodeIterator();
  }
  
  public final int labelId()
  {
    if ((this.type != 132) && (this.type != 73)) {
      Kit.codeBug();
    }
    return getIntProp(15, -1);
  }
  
  public void labelId(int paramInt)
  {
    if ((this.type != 132) && (this.type != 73)) {
      Kit.codeBug();
    }
    putIntProp(15, paramInt);
  }
  
  public void putIntProp(int paramInt1, int paramInt2)
  {
    ensureProperty(paramInt1).intValue = paramInt2;
  }
  
  public void putProp(int paramInt, Object paramObject)
  {
    if (paramObject == null)
    {
      removeProp(paramInt);
      return;
    }
    ensureProperty(paramInt).objectValue = paramObject;
  }
  
  public void removeChild(Node paramNode)
  {
    Node localNode = getChildBefore(paramNode);
    if (localNode == null) {
      this.first = this.first.next;
    }
    for (;;)
    {
      if (paramNode == this.last) {
        this.last = localNode;
      }
      paramNode.next = null;
      return;
      localNode.next = paramNode.next;
    }
  }
  
  public void removeChildren()
  {
    this.last = null;
    this.first = null;
  }
  
  public void removeProp(int paramInt)
  {
    Object localObject1 = this.propListHead;
    Object localObject2;
    if (localObject1 != null) {
      localObject2 = null;
    }
    for (;;)
    {
      PropListItem localPropListItem;
      if (((PropListItem)localObject1).type != paramInt)
      {
        localPropListItem = ((PropListItem)localObject1).next;
        if (localPropListItem != null) {}
      }
      else
      {
        if (localObject2 == null)
        {
          this.propListHead = ((PropListItem)localObject1).next;
          return;
        }
        ((PropListItem)localObject2).next = ((PropListItem)localObject1).next;
        return;
      }
      localObject2 = localObject1;
      localObject1 = localPropListItem;
    }
  }
  
  public void replaceChild(Node paramNode1, Node paramNode2)
  {
    paramNode2.next = paramNode1.next;
    if (paramNode1 == this.first) {
      this.first = paramNode2;
    }
    for (;;)
    {
      if (paramNode1 == this.last) {
        this.last = paramNode2;
      }
      paramNode1.next = null;
      return;
      getChildBefore(paramNode1).next = paramNode2;
    }
  }
  
  public void replaceChildAfter(Node paramNode1, Node paramNode2)
  {
    Node localNode = paramNode1.next;
    paramNode2.next = localNode.next;
    paramNode1.next = paramNode2;
    if (localNode == this.last) {
      this.last = paramNode2;
    }
    localNode.next = null;
  }
  
  public void resetTargets()
  {
    if (this.type == 126)
    {
      resetTargets_r();
      return;
    }
    Kit.codeBug();
  }
  
  public final void setDouble(double paramDouble)
  {
    ((NumberLiteral)this).setNumber(paramDouble);
  }
  
  public void setJsDocNode(Comment paramComment)
  {
    putProp(24, paramComment);
  }
  
  public void setLineno(int paramInt)
  {
    this.lineno = paramInt;
  }
  
  public void setScope(Scope paramScope)
  {
    if (paramScope == null) {
      Kit.codeBug();
    }
    if (!(this instanceof Name)) {
      throw Kit.codeBug();
    }
    ((Name)this).setScope(paramScope);
  }
  
  public final void setString(String paramString)
  {
    if (paramString == null) {
      Kit.codeBug();
    }
    ((Name)this).setIdentifier(paramString);
  }
  
  public Node setType(int paramInt)
  {
    this.type = paramInt;
    return this;
  }
  
  public String toString()
  {
    return String.valueOf(this.type);
  }
  
  public String toStringTree(ScriptNode paramScriptNode)
  {
    return null;
  }
  
  public class NodeIterator
    implements Iterator<Node>
  {
    private Node cursor = Node.this.first;
    private Node prev = Node.NOT_SET;
    private Node prev2;
    private boolean removed = false;
    
    public NodeIterator() {}
    
    public boolean hasNext()
    {
      return this.cursor != null;
    }
    
    public Node next()
    {
      if (this.cursor == null) {
        throw new NoSuchElementException();
      }
      this.removed = false;
      this.prev2 = this.prev;
      this.prev = this.cursor;
      this.cursor = this.cursor.next;
      return this.prev;
    }
    
    public void remove()
    {
      if (this.prev == Node.NOT_SET) {
        throw new IllegalStateException("next() has not been called");
      }
      if (this.removed) {
        throw new IllegalStateException("remove() already called for current element");
      }
      if (this.prev == Node.this.first)
      {
        Node.this.first = this.prev.next;
        return;
      }
      if (this.prev == Node.this.last)
      {
        this.prev2.next = null;
        Node.this.last = this.prev2;
        return;
      }
      this.prev2.next = this.cursor;
    }
  }
  
  private static class PropListItem
  {
    int intValue;
    PropListItem next;
    Object objectValue;
    int type;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\Node.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */