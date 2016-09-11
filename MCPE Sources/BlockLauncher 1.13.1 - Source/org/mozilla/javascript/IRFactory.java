package org.mozilla.javascript;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.mozilla.javascript.ast.ArrayComprehension;
import org.mozilla.javascript.ast.ArrayComprehensionLoop;
import org.mozilla.javascript.ast.ArrayLiteral;
import org.mozilla.javascript.ast.Assignment;
import org.mozilla.javascript.ast.AstNode;
import org.mozilla.javascript.ast.AstRoot;
import org.mozilla.javascript.ast.Block;
import org.mozilla.javascript.ast.BreakStatement;
import org.mozilla.javascript.ast.CatchClause;
import org.mozilla.javascript.ast.ConditionalExpression;
import org.mozilla.javascript.ast.ContinueStatement;
import org.mozilla.javascript.ast.DestructuringForm;
import org.mozilla.javascript.ast.DoLoop;
import org.mozilla.javascript.ast.ElementGet;
import org.mozilla.javascript.ast.EmptyExpression;
import org.mozilla.javascript.ast.ExpressionStatement;
import org.mozilla.javascript.ast.ForInLoop;
import org.mozilla.javascript.ast.ForLoop;
import org.mozilla.javascript.ast.FunctionCall;
import org.mozilla.javascript.ast.FunctionNode;
import org.mozilla.javascript.ast.GeneratorExpression;
import org.mozilla.javascript.ast.GeneratorExpressionLoop;
import org.mozilla.javascript.ast.IfStatement;
import org.mozilla.javascript.ast.InfixExpression;
import org.mozilla.javascript.ast.Jump;
import org.mozilla.javascript.ast.Label;
import org.mozilla.javascript.ast.LabeledStatement;
import org.mozilla.javascript.ast.LetNode;
import org.mozilla.javascript.ast.Loop;
import org.mozilla.javascript.ast.Name;
import org.mozilla.javascript.ast.NewExpression;
import org.mozilla.javascript.ast.NumberLiteral;
import org.mozilla.javascript.ast.ObjectLiteral;
import org.mozilla.javascript.ast.ObjectProperty;
import org.mozilla.javascript.ast.ParenthesizedExpression;
import org.mozilla.javascript.ast.PropertyGet;
import org.mozilla.javascript.ast.RegExpLiteral;
import org.mozilla.javascript.ast.ReturnStatement;
import org.mozilla.javascript.ast.Scope;
import org.mozilla.javascript.ast.ScriptNode;
import org.mozilla.javascript.ast.StringLiteral;
import org.mozilla.javascript.ast.SwitchCase;
import org.mozilla.javascript.ast.SwitchStatement;
import org.mozilla.javascript.ast.Symbol;
import org.mozilla.javascript.ast.ThrowStatement;
import org.mozilla.javascript.ast.TryStatement;
import org.mozilla.javascript.ast.UnaryExpression;
import org.mozilla.javascript.ast.VariableDeclaration;
import org.mozilla.javascript.ast.VariableInitializer;
import org.mozilla.javascript.ast.WhileLoop;
import org.mozilla.javascript.ast.WithStatement;
import org.mozilla.javascript.ast.XmlDotQuery;
import org.mozilla.javascript.ast.XmlElemRef;
import org.mozilla.javascript.ast.XmlExpression;
import org.mozilla.javascript.ast.XmlFragment;
import org.mozilla.javascript.ast.XmlLiteral;
import org.mozilla.javascript.ast.XmlMemberGet;
import org.mozilla.javascript.ast.XmlPropRef;
import org.mozilla.javascript.ast.XmlRef;
import org.mozilla.javascript.ast.XmlString;
import org.mozilla.javascript.ast.Yield;

public final class IRFactory
  extends Parser
{
  private static final int ALWAYS_FALSE_BOOLEAN = -1;
  private static final int ALWAYS_TRUE_BOOLEAN = 1;
  private static final int LOOP_DO_WHILE = 0;
  private static final int LOOP_FOR = 2;
  private static final int LOOP_WHILE = 1;
  private Decompiler decompiler = new Decompiler();
  
  public IRFactory() {}
  
  public IRFactory(CompilerEnvirons paramCompilerEnvirons)
  {
    this(paramCompilerEnvirons, paramCompilerEnvirons.getErrorReporter());
  }
  
  public IRFactory(CompilerEnvirons paramCompilerEnvirons, ErrorReporter paramErrorReporter)
  {
    super(paramCompilerEnvirons, paramErrorReporter);
  }
  
  private void addSwitchCase(Node paramNode1, Node paramNode2, Node paramNode3)
  {
    if (paramNode1.getType() != 130) {
      throw Kit.codeBug();
    }
    Jump localJump = (Jump)paramNode1.getFirstChild();
    if (localJump.getType() != 115) {
      throw Kit.codeBug();
    }
    Node localNode = Node.newTarget();
    if (paramNode2 != null)
    {
      paramNode2 = new Jump(116, paramNode2);
      paramNode2.target = localNode;
      localJump.addChildToBack(paramNode2);
    }
    for (;;)
    {
      paramNode1.addChildToBack(localNode);
      paramNode1.addChildToBack(paramNode3);
      return;
      localJump.setDefault(localNode);
    }
  }
  
  private Node arrayCompTransformHelper(ArrayComprehension paramArrayComprehension, String paramString)
  {
    this.decompiler.addToken(84);
    j = paramArrayComprehension.getLineno();
    localNode = transform(paramArrayComprehension.getResult());
    List localList = paramArrayComprehension.getLoops();
    int k = localList.size();
    Node[] arrayOfNode1 = new Node[k];
    Node[] arrayOfNode2 = new Node[k];
    i = 0;
    if (i < k)
    {
      localObject2 = (ArrayComprehensionLoop)localList.get(i);
      this.decompiler.addName(" ");
      this.decompiler.addToken(120);
      if (((ArrayComprehensionLoop)localObject2).isForEach()) {
        this.decompiler.addName("each ");
      }
      this.decompiler.addToken(88);
      Object localObject3 = ((ArrayComprehensionLoop)localObject2).getIterator();
      if (((AstNode)localObject3).getType() == 39)
      {
        localObject1 = ((AstNode)localObject3).getString();
        this.decompiler.addName((String)localObject1);
        label152:
        localObject3 = createName((String)localObject1);
        defineSymbol(154, (String)localObject1, false);
        arrayOfNode1[i] = localObject3;
        if (!((ArrayComprehensionLoop)localObject2).isForOf()) {
          break label273;
        }
        this.decompiler.addName("of ");
      }
      for (;;)
      {
        arrayOfNode2[i] = transform(((ArrayComprehensionLoop)localObject2).getIteratedObject());
        this.decompiler.addToken(89);
        i += 1;
        break;
        decompile((AstNode)localObject3);
        localObject1 = this.currentScriptOrFn.getNextTempName();
        defineSymbol(88, (String)localObject1, false);
        localNode = createBinary(90, createAssignment(91, (Node)localObject3, createName((String)localObject1)), localNode);
        break label152;
        label273:
        this.decompiler.addToken(52);
      }
    }
    localObject2 = createCallOrNew(38, createPropertyGet(createName(paramString), null, "push", 0));
    Object localObject1 = new Node(134, (Node)localObject2, j);
    paramString = (String)localObject1;
    if (paramArrayComprehension.getFilter() != null)
    {
      this.decompiler.addName(" ");
      this.decompiler.addToken(113);
      this.decompiler.addToken(88);
      paramString = createIf(transform(paramArrayComprehension.getFilter()), (Node)localObject1, null, j);
      this.decompiler.addToken(89);
    }
    j = k - 1;
    paramArrayComprehension = paramString;
    i = 0;
    for (;;)
    {
      if (j >= 0) {}
      try
      {
        paramString = (ArrayComprehensionLoop)localList.get(j);
        localObject1 = createLoopNode(null, paramString.getLineno());
        pushScope((Scope)localObject1);
        i += 1;
      }
      finally
      {
        j = 0;
        while (j < i)
        {
          popScope();
          j += 1;
        }
      }
      try
      {
        paramArrayComprehension = createForIn(154, (Node)localObject1, arrayOfNode1[j], arrayOfNode2[j], paramArrayComprehension, paramString.isForEach(), paramString.isForOf());
        j -= 1;
      }
      finally
      {
        break label496;
      }
    }
    j = 0;
    while (j < i)
    {
      popScope();
      j += 1;
    }
  }
  
  private void closeSwitch(Node paramNode)
  {
    if (paramNode.getType() != 130) {
      throw Kit.codeBug();
    }
    Jump localJump = (Jump)paramNode.getFirstChild();
    if (localJump.getType() != 115) {
      throw Kit.codeBug();
    }
    Node localNode2 = Node.newTarget();
    localJump.target = localNode2;
    Node localNode3 = localJump.getDefault();
    Node localNode1 = localNode3;
    if (localNode3 == null) {
      localNode1 = localNode2;
    }
    paramNode.addChildAfter(makeJump(5, localNode1), localJump);
    paramNode.addChildToBack(localNode2);
  }
  
  private Node createAssignment(int paramInt, Node paramNode1, Node paramNode2)
  {
    Node localNode = makeReference(paramNode1);
    if (localNode == null)
    {
      if ((paramNode1.getType() == 66) || (paramNode1.getType() == 67))
      {
        if (paramInt != 91)
        {
          reportError("msg.bad.destruct.op");
          return paramNode2;
        }
        return createDestructuringAssignment(-1, paramNode1, paramNode2);
      }
      reportError("msg.bad.assign.left");
      return paramNode2;
    }
    switch (paramInt)
    {
    default: 
      throw Kit.codeBug();
    case 91: 
      return simpleAssignment(localNode, paramNode2);
    case 92: 
      paramInt = 9;
    }
    for (;;)
    {
      i = localNode.getType();
      switch (i)
      {
      default: 
        throw Kit.codeBug();
        paramInt = 10;
        continue;
        paramInt = 11;
        continue;
        paramInt = 18;
        continue;
        paramInt = 19;
        continue;
        paramInt = 20;
        continue;
        paramInt = 21;
        continue;
        paramInt = 22;
        continue;
        paramInt = 23;
        continue;
        paramInt = 24;
        continue;
        paramInt = 25;
      }
    }
    paramNode1 = new Node(paramInt, localNode, paramNode2);
    return new Node(8, Node.newString(49, localNode.getString()), paramNode1);
    paramNode1 = localNode.getFirstChild();
    localNode = localNode.getLastChild();
    if (i == 33) {}
    for (int i = 140;; i = 141) {
      return new Node(i, paramNode1, localNode, new Node(paramInt, new Node(139), paramNode2));
    }
    paramNode1 = localNode.getFirstChild();
    checkMutableReference(paramNode1);
    return new Node(143, paramNode1, new Node(paramInt, new Node(139), paramNode2));
  }
  
  private Node createBinary(int paramInt, Node paramNode1, Node paramNode2)
  {
    switch (paramInt)
    {
    }
    int i;
    do
    {
      Node localNode = new Node(paramInt, paramNode1, paramNode2);
      do
      {
        do
        {
          return localNode;
          if (paramNode1.type == 41)
          {
            if (paramNode2.type == 41) {}
            for (paramNode2 = paramNode2.getString();; paramNode2 = ScriptRuntime.numberToString(paramNode2.getDouble(), 10))
            {
              paramNode1.setString(paramNode1.getString().concat(paramNode2));
              return paramNode1;
              if (paramNode2.type != 40) {
                break;
              }
            }
          }
          if (paramNode1.type != 40) {
            break;
          }
          if (paramNode2.type == 40)
          {
            paramNode1.setDouble(paramNode1.getDouble() + paramNode2.getDouble());
            return paramNode1;
          }
          if (paramNode2.type != 41) {
            break;
          }
          paramNode2.setString(ScriptRuntime.numberToString(paramNode1.getDouble(), 10).concat(paramNode2.getString()));
          return paramNode2;
          if (paramNode1.type == 40)
          {
            d = paramNode1.getDouble();
            if (paramNode2.type == 40)
            {
              paramNode1.setDouble(d - paramNode2.getDouble());
              return paramNode1;
            }
            if (d != 0.0D) {
              break;
            }
            return new Node(29, paramNode2);
          }
          if ((paramNode2.type != 40) || (paramNode2.getDouble() != 0.0D)) {
            break;
          }
          return new Node(28, paramNode1);
          if (paramNode1.type == 40)
          {
            d = paramNode1.getDouble();
            if (paramNode2.type == 40)
            {
              paramNode1.setDouble(d * paramNode2.getDouble());
              return paramNode1;
            }
            if (d != 1.0D) {
              break;
            }
            return new Node(28, paramNode2);
          }
          if ((paramNode2.type != 40) || (paramNode2.getDouble() != 1.0D)) {
            break;
          }
          return new Node(28, paramNode1);
          if (paramNode2.type != 40) {
            break;
          }
          double d = paramNode2.getDouble();
          if (paramNode1.type == 40)
          {
            paramNode1.setDouble(paramNode1.getDouble() / d);
            return paramNode1;
          }
          if (d != 1.0D) {
            break;
          }
          return new Node(28, paramNode1);
          i = isAlwaysDefinedBoolean(paramNode1);
          localNode = paramNode1;
        } while (i == -1);
        if (i != 1) {
          break;
        }
        return paramNode2;
        i = isAlwaysDefinedBoolean(paramNode1);
        localNode = paramNode1;
      } while (i == 1);
    } while (i != -1);
    return paramNode2;
  }
  
  private Node createCallOrNew(int paramInt, Node paramNode)
  {
    int i = 1;
    String str;
    if (paramNode.getType() == 39)
    {
      str = paramNode.getString();
      if (!str.equals("eval")) {}
    }
    for (;;)
    {
      paramNode = new Node(paramInt, paramNode);
      if (i != 0)
      {
        setRequiresActivation();
        paramNode.putIntProp(10, i);
      }
      return paramNode;
      if (str.equals("With"))
      {
        i = 2;
        continue;
        if ((paramNode.getType() == 33) && (paramNode.getLastChild().getString().equals("eval"))) {}
      }
      else
      {
        i = 0;
      }
    }
  }
  
  private Node createCatch(String paramString, Node paramNode1, Node paramNode2, int paramInt)
  {
    if (paramNode1 == null) {
      paramNode1 = new Node(129);
    }
    for (;;)
    {
      return new Node(125, createName(paramString), paramNode1, paramNode2, paramInt);
    }
  }
  
  private Node createCondExpr(Node paramNode1, Node paramNode2, Node paramNode3)
  {
    int i = isAlwaysDefinedBoolean(paramNode1);
    if (i == 1) {
      return paramNode2;
    }
    if (i == -1) {
      return paramNode3;
    }
    return new Node(103, paramNode1, paramNode2, paramNode3);
  }
  
  private Node createElementGet(Node paramNode1, String paramString, Node paramNode2, int paramInt)
  {
    if ((paramString == null) && (paramInt == 0))
    {
      if (paramNode1 == null) {
        throw Kit.codeBug();
      }
      return new Node(36, paramNode1, paramNode2);
    }
    return createMemberRefGet(paramNode1, paramString, paramNode2, paramInt);
  }
  
  private Node createExprStatementNoReturn(Node paramNode, int paramInt)
  {
    return new Node(134, paramNode, paramInt);
  }
  
  private Node createFor(Scope paramScope, Node paramNode1, Node paramNode2, Node paramNode3, Node paramNode4)
  {
    if (paramNode1.getType() == 154)
    {
      Scope localScope = Scope.splitScope(paramScope);
      localScope.setType(154);
      localScope.addChildrenToBack(paramNode1);
      localScope.addChildToBack(createLoop(paramScope, 2, paramNode4, paramNode2, new Node(129), paramNode3));
      return localScope;
    }
    return createLoop(paramScope, 2, paramNode4, paramNode2, paramNode1, paramNode3);
  }
  
  private Node createForIn(int paramInt, Node paramNode1, Node paramNode2, Node paramNode3, Node paramNode4, boolean paramBoolean1, boolean paramBoolean2)
  {
    int j = 0;
    int i = paramNode2.getType();
    Node localNode1;
    int k;
    if ((i == 123) || (i == 154))
    {
      localNode1 = paramNode2.getLastChild();
      k = localNode1.getType();
      if ((k == 66) || (k == 67))
      {
        if (!(localNode1 instanceof ArrayLiteral)) {
          break label472;
        }
        j = ((ArrayLiteral)localNode1).getDestructuringLength();
        i = k;
      }
    }
    for (;;)
    {
      int m = k;
      for (;;)
      {
        label77:
        Node localNode2 = new Node(142);
        label98:
        Node localNode3;
        Node localNode4;
        Node localNode5;
        if (paramBoolean1)
        {
          k = 59;
          localNode3 = new Node(k, paramNode3);
          localNode3.putProp(3, localNode2);
          localNode4 = new Node(62);
          localNode4.putProp(3, localNode2);
          paramNode3 = new Node(63);
          paramNode3.putProp(3, localNode2);
          localNode5 = new Node(130);
          if (m == -1) {
            break label432;
          }
          paramNode3 = createDestructuringAssignment(paramInt, localNode1, paramNode3);
          if ((paramBoolean1) || (paramBoolean2) || ((m != 67) && (j == 2))) {
            break label445;
          }
          reportError("msg.bad.for.in.destruct");
        }
        label432:
        label445:
        for (;;)
        {
          localNode5.addChildToBack(new Node(134, paramNode3));
          localNode5.addChildToBack(paramNode4);
          paramNode1 = createLoop((Jump)paramNode1, 1, localNode5, localNode4, null, null);
          paramNode1.addChildToFront(localNode3);
          if ((i == 123) || (i == 154)) {
            paramNode1.addChildToFront(paramNode2);
          }
          localNode2.addChildToBack(paramNode1);
          return localNode2;
          if (k == 39)
          {
            localNode1 = Node.newString(39, localNode1.getString());
            k = -1;
            j = 0;
            break;
          }
          reportError("msg.bad.for.in.lhs");
          return null;
          if ((i == 66) || (i == 67))
          {
            j = 0;
            if (!(paramNode2 instanceof ArrayLiteral)) {
              break label454;
            }
            j = ((ArrayLiteral)paramNode2).getDestructuringLength();
            k = i;
            localNode1 = paramNode2;
            m = i;
            i = k;
            break label77;
          }
          localNode1 = makeReference(paramNode2);
          if (localNode1 != null) {
            break label448;
          }
          reportError("msg.bad.for.in.lhs");
          return null;
          if (paramBoolean2)
          {
            k = 61;
            break label98;
          }
          if (m != -1)
          {
            k = 60;
            break label98;
          }
          k = 58;
          break label98;
          paramNode3 = simpleAssignment(localNode1, paramNode3);
        }
        label448:
        m = -1;
        continue;
        label454:
        k = i;
        localNode1 = paramNode2;
        m = i;
        i = k;
      }
      label472:
      j = 0;
      i = k;
    }
  }
  
  private Node createIf(Node paramNode1, Node paramNode2, Node paramNode3, int paramInt)
  {
    int i = isAlwaysDefinedBoolean(paramNode1);
    if (i == 1) {
      return paramNode2;
    }
    if (i == -1)
    {
      if (paramNode3 != null) {
        return paramNode3;
      }
      return new Node(130, paramInt);
    }
    Node localNode1 = new Node(130, paramInt);
    Node localNode2 = Node.newTarget();
    paramNode1 = new Jump(7, paramNode1);
    paramNode1.target = localNode2;
    localNode1.addChildToBack(paramNode1);
    localNode1.addChildrenToBack(paramNode2);
    if (paramNode3 != null)
    {
      paramNode1 = Node.newTarget();
      localNode1.addChildToBack(makeJump(5, paramNode1));
      localNode1.addChildToBack(localNode2);
      localNode1.addChildrenToBack(paramNode3);
      localNode1.addChildToBack(paramNode1);
    }
    for (;;)
    {
      return localNode1;
      localNode1.addChildToBack(localNode2);
    }
  }
  
  private Node createIncDec(int paramInt, boolean paramBoolean, Node paramNode)
  {
    paramNode = makeReference(paramNode);
    switch (paramNode.getType())
    {
    default: 
      throw Kit.codeBug();
    }
    paramNode = new Node(paramInt, paramNode);
    int i = 0;
    if (paramInt == 108) {
      i = 1;
    }
    paramInt = i;
    if (paramBoolean) {
      paramInt = i | 0x2;
    }
    paramNode.putIntProp(13, paramInt);
    return paramNode;
  }
  
  private Node createLoop(Jump paramJump, int paramInt, Node paramNode1, Node paramNode2, Node paramNode3, Node paramNode4)
  {
    Node localNode3 = Node.newTarget();
    Node localNode2 = Node.newTarget();
    Node localNode1 = paramNode2;
    if (paramInt == 2)
    {
      localNode1 = paramNode2;
      if (paramNode2.getType() == 129) {
        localNode1 = new Node(45);
      }
    }
    paramNode2 = new Jump(6, localNode1);
    paramNode2.target = localNode3;
    localNode1 = Node.newTarget();
    paramJump.addChildToBack(localNode3);
    paramJump.addChildrenToBack(paramNode1);
    if ((paramInt == 1) || (paramInt == 2)) {
      paramJump.addChildrenToBack(new Node(129, paramJump.getLineno()));
    }
    paramJump.addChildToBack(localNode2);
    paramJump.addChildToBack(paramNode2);
    paramJump.addChildToBack(localNode1);
    paramJump.target = localNode1;
    if (paramInt != 1)
    {
      paramNode2 = localNode2;
      if (paramInt != 2) {}
    }
    else
    {
      paramJump.addChildToFront(makeJump(5, localNode2));
      paramNode2 = localNode2;
      if (paramInt == 2)
      {
        paramInt = paramNode3.getType();
        if (paramInt != 129)
        {
          paramNode2 = paramNode3;
          if (paramInt != 123)
          {
            paramNode2 = paramNode3;
            if (paramInt != 154) {
              paramNode2 = new Node(134, paramNode3);
            }
          }
          paramJump.addChildToFront(paramNode2);
        }
        paramNode3 = Node.newTarget();
        paramJump.addChildAfter(paramNode3, paramNode1);
        paramNode2 = paramNode3;
        if (paramNode4.getType() != 129)
        {
          paramJump.addChildAfter(new Node(134, paramNode4), paramNode3);
          paramNode2 = paramNode3;
        }
      }
    }
    paramJump.setContinue(paramNode2);
    return paramJump;
  }
  
  private Scope createLoopNode(Node paramNode, int paramInt)
  {
    Scope localScope = createScopeNode(133, paramInt);
    if (paramNode != null) {
      ((Jump)paramNode).setLoop(localScope);
    }
    return localScope;
  }
  
  private Node createMemberRefGet(Node paramNode1, String paramString, Node paramNode2, int paramInt)
  {
    Node localNode;
    if (paramString != null) {
      if (paramString.equals("*")) {
        localNode = new Node(42);
      }
    }
    for (;;)
    {
      if (paramNode1 == null) {
        if (paramString == null) {
          paramNode1 = new Node(80, paramNode2);
        }
      }
      for (;;)
      {
        if (paramInt != 0) {
          paramNode1.putIntProp(16, paramInt);
        }
        return new Node(68, paramNode1);
        localNode = createName(paramString);
        break;
        paramNode1 = new Node(81, localNode, paramNode2);
        continue;
        if (paramString == null) {
          paramNode1 = new Node(78, paramNode1, paramNode2);
        } else {
          paramNode1 = new Node(79, paramNode1, localNode, paramNode2);
        }
      }
      localNode = null;
    }
  }
  
  private Node createPropertyGet(Node paramNode, String paramString1, String paramString2, int paramInt)
  {
    if ((paramString1 == null) && (paramInt == 0))
    {
      if (paramNode == null) {
        return createName(paramString2);
      }
      checkActivationName(paramString2, 33);
      if (ScriptRuntime.isSpecialProperty(paramString2))
      {
        paramNode = new Node(72, paramNode);
        paramNode.putProp(17, paramString2);
        return new Node(68, paramNode);
      }
      return new Node(33, paramNode, Node.newString(paramString2));
    }
    return createMemberRefGet(paramNode, paramString1, Node.newString(paramString2), paramInt | 0x1);
  }
  
  private Node createString(String paramString)
  {
    return Node.newString(paramString);
  }
  
  private Node createTryCatchFinally(Node paramNode1, Node paramNode2, Node paramNode3, int paramInt)
  {
    int i;
    if ((paramNode3 != null) && ((paramNode3.getType() != 130) || (paramNode3.hasChildren())))
    {
      i = 1;
      if ((paramNode1.getType() != 130) || (paramNode1.hasChildren()) || (i != 0)) {
        break label54;
      }
    }
    label54:
    boolean bool;
    do
    {
      return paramNode1;
      i = 0;
      break;
      bool = paramNode2.hasChildren();
    } while ((i == 0) && (!bool));
    Node localNode1 = new Node(142);
    Jump localJump = new Jump(82, paramNode1, paramInt);
    localJump.putProp(3, localNode1);
    if (bool)
    {
      Node localNode2 = Node.newTarget();
      localJump.addChildToBack(makeJump(5, localNode2));
      paramNode1 = Node.newTarget();
      localJump.target = paramNode1;
      localJump.addChildToBack(paramNode1);
      Node localNode3 = new Node(142);
      paramNode1 = paramNode2.getFirstChild();
      int j = 0;
      paramInt = 0;
      if (paramNode1 != null)
      {
        int k = paramNode1.getLineno();
        Node localNode4 = paramNode1.getFirstChild();
        Node localNode5 = localNode4.getNext();
        paramNode2 = localNode5.getNext();
        paramNode1.removeChild(localNode4);
        paramNode1.removeChild(localNode5);
        paramNode1.removeChild(paramNode2);
        paramNode2.addChildToBack(new Node(3));
        paramNode2.addChildToBack(makeJump(5, localNode2));
        if (localNode5.getType() == 129) {
          j = 1;
        }
        for (;;)
        {
          localNode4 = new Node(57, localNode4, createUseLocal(localNode1));
          localNode4.putProp(3, localNode3);
          localNode4.putIntProp(14, paramInt);
          localNode3.addChildToBack(localNode4);
          localNode3.addChildToBack(createWith(createUseLocal(localNode3), paramNode2, k));
          paramNode1 = paramNode1.getNext();
          paramInt += 1;
          break;
          paramNode2 = createIf(localNode5, paramNode2, null, k);
        }
      }
      localJump.addChildToBack(localNode3);
      if (j == 0)
      {
        paramNode1 = new Node(51);
        paramNode1.putProp(3, localNode1);
        localJump.addChildToBack(paramNode1);
      }
      localJump.addChildToBack(localNode2);
    }
    if (i != 0)
    {
      paramNode2 = Node.newTarget();
      localJump.setFinally(paramNode2);
      localJump.addChildToBack(makeJump(136, paramNode2));
      paramNode1 = Node.newTarget();
      localJump.addChildToBack(makeJump(5, paramNode1));
      localJump.addChildToBack(paramNode2);
      paramNode2 = new Node(126, paramNode3);
      paramNode2.putProp(3, localNode1);
      localJump.addChildToBack(paramNode2);
      localJump.addChildToBack(paramNode1);
    }
    localNode1.addChildToBack(localJump);
    return localNode1;
  }
  
  private Node createUnary(int paramInt, Node paramNode)
  {
    int i = paramNode.getType();
    switch (paramInt)
    {
    }
    int j;
    do
    {
      do
      {
        do
        {
          do
          {
            return new Node(paramInt, paramNode);
            if (i == 39)
            {
              paramNode.setType(49);
              paramNode = new Node(paramInt, paramNode, Node.newString(paramNode.getString()));
            }
            for (;;)
            {
              return paramNode;
              Node localNode1;
              if ((i == 33) || (i == 36))
              {
                localNode1 = paramNode.getFirstChild();
                Node localNode2 = paramNode.getLastChild();
                paramNode.removeChild(localNode1);
                paramNode.removeChild(localNode2);
                paramNode = new Node(paramInt, localNode1, localNode2);
              }
              else if (i == 68)
              {
                localNode1 = paramNode.getFirstChild();
                paramNode.removeChild(localNode1);
                paramNode = new Node(70, localNode1);
              }
              else
              {
                paramNode = new Node(paramInt, new Node(45), paramNode);
              }
            }
          } while (i != 39);
          paramNode.setType(138);
          return paramNode;
        } while (i != 40);
        paramNode.setDouble(ScriptRuntime.toInt32(paramNode.getDouble()) ^ 0xFFFFFFFF);
        return paramNode;
      } while (i != 40);
      paramNode.setDouble(-paramNode.getDouble());
      return paramNode;
      j = isAlwaysDefinedBoolean(paramNode);
    } while (j == 0);
    if (j == 1) {}
    for (paramInt = 44; (i == 45) || (i == 44); paramInt = 45)
    {
      paramNode.setType(paramInt);
      return paramNode;
    }
    return new Node(paramInt);
  }
  
  private Node createUseLocal(Node paramNode)
  {
    if (142 != paramNode.getType()) {
      throw Kit.codeBug();
    }
    Node localNode = new Node(54);
    localNode.putProp(3, paramNode);
    return localNode;
  }
  
  private Node createWith(Node paramNode1, Node paramNode2, int paramInt)
  {
    setRequiresActivation();
    Node localNode = new Node(130, paramInt);
    localNode.addChildToBack(new Node(2, paramNode1));
    localNode.addChildrenToBack(new Node(124, paramNode2, paramInt));
    localNode.addChildToBack(new Node(3));
    return localNode;
  }
  
  private Node genExprTransformHelper(GeneratorExpression paramGeneratorExpression)
  {
    this.decompiler.addToken(88);
    j = paramGeneratorExpression.getLineno();
    Object localObject1 = transform(paramGeneratorExpression.getResult());
    List localList = paramGeneratorExpression.getLoops();
    int k = localList.size();
    Node[] arrayOfNode1 = new Node[k];
    Node[] arrayOfNode2 = new Node[k];
    i = 0;
    if (i < k)
    {
      GeneratorExpressionLoop localGeneratorExpressionLoop = (GeneratorExpressionLoop)localList.get(i);
      this.decompiler.addName(" ");
      this.decompiler.addToken(120);
      this.decompiler.addToken(88);
      Object localObject3 = localGeneratorExpressionLoop.getIterator();
      if (((AstNode)localObject3).getType() == 39)
      {
        localObject3 = ((AstNode)localObject3).getString();
        this.decompiler.addName((String)localObject3);
        localObject2 = localObject1;
        localObject1 = localObject3;
        label142:
        localObject3 = createName((String)localObject1);
        defineSymbol(154, (String)localObject1, false);
        arrayOfNode1[i] = localObject3;
        if (!localGeneratorExpressionLoop.isForOf()) {
          break label275;
        }
        this.decompiler.addName("of ");
      }
      for (;;)
      {
        arrayOfNode2[i] = transform(localGeneratorExpressionLoop.getIteratedObject());
        this.decompiler.addToken(89);
        i += 1;
        localObject1 = localObject2;
        break;
        decompile((AstNode)localObject3);
        localObject2 = this.currentScriptOrFn.getNextTempName();
        defineSymbol(88, (String)localObject2, false);
        localObject3 = createBinary(90, createAssignment(91, (Node)localObject3, createName((String)localObject2)), (Node)localObject1);
        localObject1 = localObject2;
        localObject2 = localObject3;
        break label142;
        label275:
        this.decompiler.addToken(52);
      }
    }
    Object localObject2 = new Node(134, new Node(73, (Node)localObject1, paramGeneratorExpression.getLineno()), j);
    localObject1 = localObject2;
    if (paramGeneratorExpression.getFilter() != null)
    {
      this.decompiler.addName(" ");
      this.decompiler.addToken(113);
      this.decompiler.addToken(88);
      localObject1 = createIf(transform(paramGeneratorExpression.getFilter()), (Node)localObject2, null, j);
      this.decompiler.addToken(89);
    }
    j = k - 1;
    paramGeneratorExpression = (GeneratorExpression)localObject1;
    i = 0;
    for (;;)
    {
      if (j >= 0) {}
      try
      {
        localObject1 = (GeneratorExpressionLoop)localList.get(j);
        localObject2 = createLoopNode(null, ((GeneratorExpressionLoop)localObject1).getLineno());
        pushScope((Scope)localObject2);
        i += 1;
      }
      finally
      {
        j = 0;
        while (j < i)
        {
          popScope();
          j += 1;
        }
      }
      try
      {
        paramGeneratorExpression = createForIn(154, (Node)localObject2, arrayOfNode1[j], arrayOfNode2[j], paramGeneratorExpression, ((GeneratorExpressionLoop)localObject1).isForEach(), ((GeneratorExpressionLoop)localObject1).isForOf());
        j -= 1;
      }
      finally
      {
        break label484;
      }
    }
    j = 0;
    while (j < i)
    {
      popScope();
      j += 1;
    }
  }
  
  private Object getPropKey(Node paramNode)
  {
    if ((paramNode instanceof Name))
    {
      paramNode = ((Name)paramNode).getIdentifier();
      this.decompiler.addName(paramNode);
      return ScriptRuntime.getIndexObject(paramNode);
    }
    if ((paramNode instanceof StringLiteral))
    {
      paramNode = ((StringLiteral)paramNode).getValue();
      this.decompiler.addString(paramNode);
      return ScriptRuntime.getIndexObject(paramNode);
    }
    if ((paramNode instanceof NumberLiteral))
    {
      double d = ((NumberLiteral)paramNode).getNumber();
      this.decompiler.addNumber(d);
      return ScriptRuntime.getIndexObject(d);
    }
    throw Kit.codeBug();
  }
  
  private Node initFunction(FunctionNode paramFunctionNode, int paramInt1, Node paramNode, int paramInt2)
  {
    paramFunctionNode.setFunctionType(paramInt2);
    paramFunctionNode.addChildToBack(paramNode);
    if (paramFunctionNode.getFunctionCount() != 0) {
      paramFunctionNode.setRequiresActivation();
    }
    if (paramInt2 == 2)
    {
      localObject = paramFunctionNode.getFunctionName();
      if ((localObject != null) && (((Name)localObject).length() != 0) && (paramFunctionNode.getSymbol(((Name)localObject).getIdentifier()) == null))
      {
        paramFunctionNode.putSymbol(new Symbol(110, ((Name)localObject).getIdentifier()));
        paramNode.addChildrenToFront(new Node(134, new Node(8, Node.newString(49, ((Name)localObject).getIdentifier()), new Node(64))));
      }
    }
    Object localObject = paramNode.getLastChild();
    if ((localObject == null) || (((Node)localObject).getType() != 4)) {
      paramNode.addChildToBack(new Node(4));
    }
    paramFunctionNode = Node.newString(110, paramFunctionNode.getName());
    paramFunctionNode.putIntProp(1, paramInt1);
    return paramFunctionNode;
  }
  
  private static int isAlwaysDefinedBoolean(Node paramNode)
  {
    int j = -1;
    int i = j;
    switch (paramNode.getType())
    {
    case 41: 
    case 43: 
    default: 
      i = 0;
    }
    double d;
    do
    {
      do
      {
        return i;
        return 1;
        d = paramNode.getDouble();
        i = j;
      } while (d != d);
      i = j;
    } while (d == 0.0D);
    return 1;
  }
  
  private Jump makeJump(int paramInt, Node paramNode)
  {
    Jump localJump = new Jump(paramInt);
    localJump.target = paramNode;
    return localJump;
  }
  
  private Node makeReference(Node paramNode)
  {
    Node localNode = paramNode;
    switch (paramNode.getType())
    {
    default: 
      localNode = null;
    case 33: 
    case 36: 
    case 39: 
    case 68: 
      return localNode;
    }
    paramNode.setType(71);
    return new Node(68, paramNode);
  }
  
  private Node transformArrayComp(ArrayComprehension paramArrayComprehension)
  {
    int i = paramArrayComprehension.getLineno();
    Scope localScope = createScopeNode(158, i);
    String str = this.currentScriptOrFn.getNextTempName();
    pushScope(localScope);
    try
    {
      defineSymbol(154, str, false);
      Node localNode1 = new Node(130, i);
      Node localNode2 = createCallOrNew(30, createName("Array"));
      localNode1.addChildToBack(new Node(134, createAssignment(91, createName(str), localNode2), i));
      localNode1.addChildToBack(arrayCompTransformHelper(paramArrayComprehension, str));
      localScope.addChildToBack(localNode1);
      localScope.addChildToBack(createName(str));
      return localScope;
    }
    finally
    {
      popScope();
    }
  }
  
  private Node transformArrayLiteral(ArrayLiteral paramArrayLiteral)
  {
    int j = 0;
    if (paramArrayLiteral.isDestructuring()) {
      return paramArrayLiteral;
    }
    this.decompiler.addToken(84);
    List localList = paramArrayLiteral.getElements();
    Node localNode = new Node(66);
    ArrayList localArrayList = null;
    int i = 0;
    while (i < localList.size())
    {
      AstNode localAstNode = (AstNode)localList.get(i);
      if (localAstNode.getType() != 129)
      {
        localNode.addChildToBack(transform(localAstNode));
        if (i < localList.size() - 1) {
          this.decompiler.addToken(90);
        }
        i += 1;
      }
      else
      {
        if (localArrayList != null) {
          break label229;
        }
        localArrayList = new ArrayList();
      }
    }
    label229:
    for (;;)
    {
      localArrayList.add(Integer.valueOf(i));
      break;
      this.decompiler.addToken(85);
      localNode.putIntProp(21, paramArrayLiteral.getDestructuringLength());
      if (localArrayList != null)
      {
        paramArrayLiteral = new int[localArrayList.size()];
        i = j;
        while (i < localArrayList.size())
        {
          paramArrayLiteral[i] = ((Integer)localArrayList.get(i)).intValue();
          i += 1;
        }
        localNode.putProp(11, paramArrayLiteral);
      }
      return localNode;
    }
  }
  
  private Node transformAssignment(Assignment paramAssignment)
  {
    Object localObject = removeParens(paramAssignment.getLeft());
    if (isDestructuring((Node)localObject)) {
      decompile((AstNode)localObject);
    }
    for (;;)
    {
      this.decompiler.addToken(paramAssignment.getType());
      return createAssignment(paramAssignment.getType(), (Node)localObject, transform(paramAssignment.getRight()));
      localObject = transform((AstNode)localObject);
    }
  }
  
  private Node transformBlock(AstNode paramAstNode)
  {
    if ((paramAstNode instanceof Scope)) {
      pushScope((Scope)paramAstNode);
    }
    try
    {
      ArrayList localArrayList = new ArrayList();
      Iterator localIterator2 = paramAstNode.iterator();
      while (localIterator2.hasNext()) {
        localArrayList.add(transform((AstNode)localIterator2.next()));
      }
      paramAstNode.removeChildren();
    }
    finally
    {
      if ((paramAstNode instanceof Scope)) {
        popScope();
      }
    }
    Iterator localIterator1 = ((List)localObject).iterator();
    while (localIterator1.hasNext()) {
      paramAstNode.addChildToBack((Node)localIterator1.next());
    }
    if ((paramAstNode instanceof Scope)) {
      popScope();
    }
    return paramAstNode;
  }
  
  private Node transformBreak(BreakStatement paramBreakStatement)
  {
    this.decompiler.addToken(121);
    if (paramBreakStatement.getBreakLabel() != null) {
      this.decompiler.addName(paramBreakStatement.getBreakLabel().getIdentifier());
    }
    this.decompiler.addEOL(83);
    return paramBreakStatement;
  }
  
  private Node transformCondExpr(ConditionalExpression paramConditionalExpression)
  {
    Node localNode1 = transform(paramConditionalExpression.getTestExpression());
    this.decompiler.addToken(103);
    Node localNode2 = transform(paramConditionalExpression.getTrueExpression());
    this.decompiler.addToken(104);
    return createCondExpr(localNode1, localNode2, transform(paramConditionalExpression.getFalseExpression()));
  }
  
  private Node transformContinue(ContinueStatement paramContinueStatement)
  {
    this.decompiler.addToken(122);
    if (paramContinueStatement.getLabel() != null) {
      this.decompiler.addName(paramContinueStatement.getLabel().getIdentifier());
    }
    this.decompiler.addEOL(83);
    return paramContinueStatement;
  }
  
  private Node transformDefaultXmlNamepace(UnaryExpression paramUnaryExpression)
  {
    this.decompiler.addToken(117);
    this.decompiler.addName(" xml");
    this.decompiler.addName(" namespace");
    this.decompiler.addToken(91);
    return createUnary(75, transform(paramUnaryExpression.getOperand()));
  }
  
  private Node transformDoLoop(DoLoop paramDoLoop)
  {
    paramDoLoop.setType(133);
    pushScope(paramDoLoop);
    try
    {
      this.decompiler.addToken(119);
      this.decompiler.addEOL(86);
      Node localNode1 = transform(paramDoLoop.getBody());
      this.decompiler.addToken(87);
      this.decompiler.addToken(118);
      this.decompiler.addToken(88);
      Node localNode2 = transform(paramDoLoop.getCondition());
      this.decompiler.addToken(89);
      this.decompiler.addEOL(83);
      paramDoLoop = createLoop(paramDoLoop, 0, localNode1, localNode2, null, null);
      return paramDoLoop;
    }
    finally
    {
      popScope();
    }
  }
  
  private Node transformElementGet(ElementGet paramElementGet)
  {
    Node localNode = transform(paramElementGet.getTarget());
    this.decompiler.addToken(84);
    paramElementGet = transform(paramElementGet.getElement());
    this.decompiler.addToken(85);
    return new Node(36, localNode, paramElementGet);
  }
  
  private Node transformExprStmt(ExpressionStatement paramExpressionStatement)
  {
    Node localNode = transform(paramExpressionStatement.getExpression());
    this.decompiler.addEOL(83);
    return new Node(paramExpressionStatement.getType(), localNode, paramExpressionStatement.getLineno());
  }
  
  /* Error */
  private Node transformForInLoop(ForInLoop paramForInLoop)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 25	org/mozilla/javascript/IRFactory:decompiler	Lorg/mozilla/javascript/Decompiler;
    //   4: bipush 120
    //   6: invokevirtual 82	org/mozilla/javascript/Decompiler:addToken	(I)V
    //   9: aload_1
    //   10: invokevirtual 641	org/mozilla/javascript/ast/ForInLoop:isForEach	()Z
    //   13: ifeq +12 -> 25
    //   16: aload_0
    //   17: getfield 25	org/mozilla/javascript/IRFactory:decompiler	Lorg/mozilla/javascript/Decompiler;
    //   20: ldc 122
    //   22: invokevirtual 116	org/mozilla/javascript/Decompiler:addName	(Ljava/lang/String;)V
    //   25: aload_0
    //   26: getfield 25	org/mozilla/javascript/IRFactory:decompiler	Lorg/mozilla/javascript/Decompiler;
    //   29: bipush 88
    //   31: invokevirtual 82	org/mozilla/javascript/Decompiler:addToken	(I)V
    //   34: aload_1
    //   35: sipush 133
    //   38: invokevirtual 642	org/mozilla/javascript/ast/ForInLoop:setType	(I)Lorg/mozilla/javascript/Node;
    //   41: pop
    //   42: aload_0
    //   43: aload_1
    //   44: invokevirtual 197	org/mozilla/javascript/IRFactory:pushScope	(Lorg/mozilla/javascript/ast/Scope;)V
    //   47: iconst_m1
    //   48: istore_2
    //   49: aload_1
    //   50: invokevirtual 643	org/mozilla/javascript/ast/ForInLoop:getIterator	()Lorg/mozilla/javascript/ast/AstNode;
    //   53: astore_3
    //   54: aload_3
    //   55: instanceof 645
    //   58: ifeq +11 -> 69
    //   61: aload_3
    //   62: checkcast 645	org/mozilla/javascript/ast/VariableDeclaration
    //   65: invokevirtual 646	org/mozilla/javascript/ast/VariableDeclaration:getType	()I
    //   68: istore_2
    //   69: aload_0
    //   70: aload_3
    //   71: invokevirtual 95	org/mozilla/javascript/IRFactory:transform	(Lorg/mozilla/javascript/ast/AstNode;)Lorg/mozilla/javascript/Node;
    //   74: astore_3
    //   75: aload_1
    //   76: invokevirtual 647	org/mozilla/javascript/ast/ForInLoop:isForOf	()Z
    //   79: ifeq +85 -> 164
    //   82: aload_0
    //   83: getfield 25	org/mozilla/javascript/IRFactory:decompiler	Lorg/mozilla/javascript/Decompiler;
    //   86: ldc -111
    //   88: invokevirtual 116	org/mozilla/javascript/Decompiler:addName	(Ljava/lang/String;)V
    //   91: aload_0
    //   92: aload_1
    //   93: invokevirtual 648	org/mozilla/javascript/ast/ForInLoop:getIteratedObject	()Lorg/mozilla/javascript/ast/AstNode;
    //   96: invokevirtual 95	org/mozilla/javascript/IRFactory:transform	(Lorg/mozilla/javascript/ast/AstNode;)Lorg/mozilla/javascript/Node;
    //   99: astore 4
    //   101: aload_0
    //   102: getfield 25	org/mozilla/javascript/IRFactory:decompiler	Lorg/mozilla/javascript/Decompiler;
    //   105: bipush 89
    //   107: invokevirtual 82	org/mozilla/javascript/Decompiler:addToken	(I)V
    //   110: aload_0
    //   111: getfield 25	org/mozilla/javascript/IRFactory:decompiler	Lorg/mozilla/javascript/Decompiler;
    //   114: bipush 86
    //   116: invokevirtual 571	org/mozilla/javascript/Decompiler:addEOL	(I)V
    //   119: aload_0
    //   120: aload_1
    //   121: invokevirtual 649	org/mozilla/javascript/ast/ForInLoop:getBody	()Lorg/mozilla/javascript/ast/AstNode;
    //   124: invokevirtual 95	org/mozilla/javascript/IRFactory:transform	(Lorg/mozilla/javascript/ast/AstNode;)Lorg/mozilla/javascript/Node;
    //   127: astore 5
    //   129: aload_0
    //   130: getfield 25	org/mozilla/javascript/IRFactory:decompiler	Lorg/mozilla/javascript/Decompiler;
    //   133: bipush 87
    //   135: invokevirtual 571	org/mozilla/javascript/Decompiler:addEOL	(I)V
    //   138: aload_0
    //   139: iload_2
    //   140: aload_1
    //   141: aload_3
    //   142: aload 4
    //   144: aload 5
    //   146: aload_1
    //   147: invokevirtual 641	org/mozilla/javascript/ast/ForInLoop:isForEach	()Z
    //   150: aload_1
    //   151: invokevirtual 647	org/mozilla/javascript/ast/ForInLoop:isForOf	()Z
    //   154: invokespecial 201	org/mozilla/javascript/IRFactory:createForIn	(ILorg/mozilla/javascript/Node;Lorg/mozilla/javascript/Node;Lorg/mozilla/javascript/Node;Lorg/mozilla/javascript/Node;ZZ)Lorg/mozilla/javascript/Node;
    //   157: astore_1
    //   158: aload_0
    //   159: invokevirtual 204	org/mozilla/javascript/IRFactory:popScope	()V
    //   162: aload_1
    //   163: areturn
    //   164: aload_0
    //   165: getfield 25	org/mozilla/javascript/IRFactory:decompiler	Lorg/mozilla/javascript/Decompiler;
    //   168: bipush 52
    //   170: invokevirtual 82	org/mozilla/javascript/Decompiler:addToken	(I)V
    //   173: goto -82 -> 91
    //   176: astore_1
    //   177: aload_0
    //   178: invokevirtual 204	org/mozilla/javascript/IRFactory:popScope	()V
    //   181: aload_1
    //   182: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	183	0	this	IRFactory
    //   0	183	1	paramForInLoop	ForInLoop
    //   48	92	2	i	int
    //   53	89	3	localObject	Object
    //   99	44	4	localNode1	Node
    //   127	18	5	localNode2	Node
    // Exception table:
    //   from	to	target	type
    //   49	54	176	finally
    //   54	69	176	finally
    //   69	91	176	finally
    //   91	158	176	finally
    //   164	173	176	finally
  }
  
  private Node transformForLoop(ForLoop paramForLoop)
  {
    this.decompiler.addToken(120);
    this.decompiler.addToken(88);
    paramForLoop.setType(133);
    Scope localScope = this.currentScope;
    this.currentScope = paramForLoop;
    try
    {
      Node localNode1 = transform(paramForLoop.getInitializer());
      this.decompiler.addToken(83);
      Node localNode2 = transform(paramForLoop.getCondition());
      this.decompiler.addToken(83);
      Node localNode3 = transform(paramForLoop.getIncrement());
      this.decompiler.addToken(89);
      this.decompiler.addEOL(86);
      Node localNode4 = transform(paramForLoop.getBody());
      this.decompiler.addEOL(87);
      paramForLoop = createFor(paramForLoop, localNode1, localNode2, localNode3, localNode4);
      return paramForLoop;
    }
    finally
    {
      this.currentScope = localScope;
    }
  }
  
  private Node transformFunction(FunctionNode paramFunctionNode)
  {
    int j = paramFunctionNode.getFunctionType();
    int k = this.decompiler.markFunctionStart(j);
    Node localNode2 = decompileFunctionHeader(paramFunctionNode);
    int i = this.currentScriptOrFn.addFunction(paramFunctionNode);
    Parser.PerFunctionVariables localPerFunctionVariables = new Parser.PerFunctionVariables(this, paramFunctionNode);
    try
    {
      Object localObject = (Node)paramFunctionNode.getProp(23);
      paramFunctionNode.removeProp(23);
      int m = paramFunctionNode.getBody().getLineno();
      this.nestingOfFunction += 1;
      Node localNode1 = transform(paramFunctionNode.getBody());
      if (!paramFunctionNode.isExpressionClosure()) {
        this.decompiler.addToken(87);
      }
      paramFunctionNode.setEncodedSourceBounds(k, this.decompiler.markFunctionEnd(k));
      if ((j != 2) && (!paramFunctionNode.isExpressionClosure())) {
        this.decompiler.addToken(1);
      }
      if (localObject != null) {
        localNode1.addChildToFront(new Node(134, (Node)localObject, m));
      }
      j = paramFunctionNode.getFunctionType();
      localNode1 = initFunction(paramFunctionNode, i, localNode1, j);
      localObject = localNode1;
      if (localNode2 != null)
      {
        localNode1 = createAssignment(91, localNode2, localNode1);
        localObject = localNode1;
        if (j != 2) {
          localObject = createExprStatementNoReturn(localNode1, paramFunctionNode.getLineno());
        }
      }
      return (Node)localObject;
    }
    finally
    {
      this.nestingOfFunction -= 1;
      localPerFunctionVariables.restore();
    }
  }
  
  private Node transformFunctionCall(FunctionCall paramFunctionCall)
  {
    Node localNode = createCallOrNew(38, transform(paramFunctionCall.getTarget()));
    localNode.setLineno(paramFunctionCall.getLineno());
    this.decompiler.addToken(88);
    paramFunctionCall = paramFunctionCall.getArguments();
    int i = 0;
    while (i < paramFunctionCall.size())
    {
      localNode.addChildToBack(transform((AstNode)paramFunctionCall.get(i)));
      if (i < paramFunctionCall.size() - 1) {
        this.decompiler.addToken(90);
      }
      i += 1;
    }
    this.decompiler.addToken(89);
    return localNode;
  }
  
  private Node transformGenExpr(GeneratorExpression paramGeneratorExpression)
  {
    FunctionNode localFunctionNode = new FunctionNode();
    localFunctionNode.setSourceName(this.currentScriptOrFn.getNextTempName());
    localFunctionNode.setIsGenerator();
    localFunctionNode.setFunctionType(2);
    localFunctionNode.setRequiresActivation();
    int j = localFunctionNode.getFunctionType();
    int k = this.decompiler.markFunctionStart(j);
    Node localNode2 = decompileFunctionHeader(localFunctionNode);
    int i = this.currentScriptOrFn.addFunction(localFunctionNode);
    Parser.PerFunctionVariables localPerFunctionVariables = new Parser.PerFunctionVariables(this, localFunctionNode);
    try
    {
      Object localObject = (Node)localFunctionNode.getProp(23);
      localFunctionNode.removeProp(23);
      int m = paramGeneratorExpression.lineno;
      this.nestingOfFunction += 1;
      Node localNode1 = genExprTransformHelper(paramGeneratorExpression);
      if (!localFunctionNode.isExpressionClosure()) {
        this.decompiler.addToken(87);
      }
      localFunctionNode.setEncodedSourceBounds(k, this.decompiler.markFunctionEnd(k));
      if ((j != 2) && (!localFunctionNode.isExpressionClosure())) {
        this.decompiler.addToken(1);
      }
      if (localObject != null) {
        localNode1.addChildToFront(new Node(134, (Node)localObject, m));
      }
      j = localFunctionNode.getFunctionType();
      localNode1 = initFunction(localFunctionNode, i, localNode1, j);
      localObject = localNode1;
      if (localNode2 != null)
      {
        localNode1 = createAssignment(91, localNode2, localNode1);
        localObject = localNode1;
        if (j != 2) {
          localObject = createExprStatementNoReturn(localNode1, localFunctionNode.getLineno());
        }
      }
      this.nestingOfFunction -= 1;
      localPerFunctionVariables.restore();
      localObject = createCallOrNew(38, (Node)localObject);
      ((Node)localObject).setLineno(paramGeneratorExpression.getLineno());
      this.decompiler.addToken(88);
      this.decompiler.addToken(89);
      return (Node)localObject;
    }
    finally
    {
      this.nestingOfFunction -= 1;
      localPerFunctionVariables.restore();
    }
  }
  
  private Node transformIf(IfStatement paramIfStatement)
  {
    this.decompiler.addToken(113);
    this.decompiler.addToken(88);
    Node localNode2 = transform(paramIfStatement.getCondition());
    this.decompiler.addToken(89);
    this.decompiler.addEOL(86);
    Node localNode3 = transform(paramIfStatement.getThenPart());
    Node localNode1 = null;
    if (paramIfStatement.getElsePart() != null)
    {
      this.decompiler.addToken(87);
      this.decompiler.addToken(114);
      this.decompiler.addEOL(86);
      localNode1 = transform(paramIfStatement.getElsePart());
    }
    this.decompiler.addEOL(87);
    return createIf(localNode2, localNode3, localNode1, paramIfStatement.getLineno());
  }
  
  private Node transformInfix(InfixExpression paramInfixExpression)
  {
    Node localNode1 = transform(paramInfixExpression.getLeft());
    this.decompiler.addToken(paramInfixExpression.getType());
    Node localNode2 = transform(paramInfixExpression.getRight());
    if ((paramInfixExpression instanceof XmlDotQuery)) {
      this.decompiler.addToken(89);
    }
    return createBinary(paramInfixExpression.getType(), localNode1, localNode2);
  }
  
  private Node transformLabeledStatement(LabeledStatement paramLabeledStatement)
  {
    Label localLabel1 = paramLabeledStatement.getFirstLabel();
    Object localObject = paramLabeledStatement.getLabels();
    this.decompiler.addName(localLabel1.getName());
    if (((List)localObject).size() > 1)
    {
      localObject = ((List)localObject).subList(1, ((List)localObject).size()).iterator();
      while (((Iterator)localObject).hasNext())
      {
        Label localLabel2 = (Label)((Iterator)localObject).next();
        this.decompiler.addEOL(104);
        this.decompiler.addName(localLabel2.getName());
      }
    }
    if (paramLabeledStatement.getStatement().getType() == 130)
    {
      this.decompiler.addToken(67);
      this.decompiler.addEOL(86);
    }
    for (;;)
    {
      localObject = transform(paramLabeledStatement.getStatement());
      if (paramLabeledStatement.getStatement().getType() == 130) {
        this.decompiler.addEOL(87);
      }
      paramLabeledStatement = Node.newTarget();
      localObject = new Node(130, localLabel1, (Node)localObject, paramLabeledStatement);
      localLabel1.target = paramLabeledStatement;
      return (Node)localObject;
      this.decompiler.addEOL(104);
    }
  }
  
  /* Error */
  private Node transformLetNode(LetNode paramLetNode)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: invokevirtual 197	org/mozilla/javascript/IRFactory:pushScope	(Lorg/mozilla/javascript/ast/Scope;)V
    //   5: aload_0
    //   6: getfield 25	org/mozilla/javascript/IRFactory:decompiler	Lorg/mozilla/javascript/Decompiler;
    //   9: sipush 154
    //   12: invokevirtual 82	org/mozilla/javascript/Decompiler:addToken	(I)V
    //   15: aload_0
    //   16: getfield 25	org/mozilla/javascript/IRFactory:decompiler	Lorg/mozilla/javascript/Decompiler;
    //   19: bipush 88
    //   21: invokevirtual 82	org/mozilla/javascript/Decompiler:addToken	(I)V
    //   24: aload_0
    //   25: aload_1
    //   26: invokevirtual 793	org/mozilla/javascript/ast/LetNode:getVariables	()Lorg/mozilla/javascript/ast/VariableDeclaration;
    //   29: invokespecial 797	org/mozilla/javascript/IRFactory:transformVariableInitializers	(Lorg/mozilla/javascript/ast/VariableDeclaration;)Lorg/mozilla/javascript/Node;
    //   32: astore_3
    //   33: aload_0
    //   34: getfield 25	org/mozilla/javascript/IRFactory:decompiler	Lorg/mozilla/javascript/Decompiler;
    //   37: bipush 89
    //   39: invokevirtual 82	org/mozilla/javascript/Decompiler:addToken	(I)V
    //   42: aload_1
    //   43: aload_3
    //   44: invokevirtual 798	org/mozilla/javascript/ast/LetNode:addChildToBack	(Lorg/mozilla/javascript/Node;)V
    //   47: aload_1
    //   48: invokevirtual 799	org/mozilla/javascript/ast/LetNode:getType	()I
    //   51: sipush 159
    //   54: if_icmpne +56 -> 110
    //   57: iconst_1
    //   58: istore_2
    //   59: aload_1
    //   60: invokevirtual 800	org/mozilla/javascript/ast/LetNode:getBody	()Lorg/mozilla/javascript/ast/AstNode;
    //   63: ifnull +41 -> 104
    //   66: iload_2
    //   67: ifeq +48 -> 115
    //   70: aload_0
    //   71: getfield 25	org/mozilla/javascript/IRFactory:decompiler	Lorg/mozilla/javascript/Decompiler;
    //   74: ldc 112
    //   76: invokevirtual 116	org/mozilla/javascript/Decompiler:addName	(Ljava/lang/String;)V
    //   79: aload_1
    //   80: aload_0
    //   81: aload_1
    //   82: invokevirtual 800	org/mozilla/javascript/ast/LetNode:getBody	()Lorg/mozilla/javascript/ast/AstNode;
    //   85: invokevirtual 95	org/mozilla/javascript/IRFactory:transform	(Lorg/mozilla/javascript/ast/AstNode;)Lorg/mozilla/javascript/Node;
    //   88: invokevirtual 798	org/mozilla/javascript/ast/LetNode:addChildToBack	(Lorg/mozilla/javascript/Node;)V
    //   91: iload_2
    //   92: ifne +12 -> 104
    //   95: aload_0
    //   96: getfield 25	org/mozilla/javascript/IRFactory:decompiler	Lorg/mozilla/javascript/Decompiler;
    //   99: bipush 87
    //   101: invokevirtual 571	org/mozilla/javascript/Decompiler:addEOL	(I)V
    //   104: aload_0
    //   105: invokevirtual 204	org/mozilla/javascript/IRFactory:popScope	()V
    //   108: aload_1
    //   109: areturn
    //   110: iconst_0
    //   111: istore_2
    //   112: goto -53 -> 59
    //   115: aload_0
    //   116: getfield 25	org/mozilla/javascript/IRFactory:decompiler	Lorg/mozilla/javascript/Decompiler;
    //   119: bipush 86
    //   121: invokevirtual 571	org/mozilla/javascript/Decompiler:addEOL	(I)V
    //   124: goto -45 -> 79
    //   127: astore_1
    //   128: aload_0
    //   129: invokevirtual 204	org/mozilla/javascript/IRFactory:popScope	()V
    //   132: aload_1
    //   133: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	134	0	this	IRFactory
    //   0	134	1	paramLetNode	LetNode
    //   58	54	2	i	int
    //   32	12	3	localNode	Node
    // Exception table:
    //   from	to	target	type
    //   5	57	127	finally
    //   59	66	127	finally
    //   70	79	127	finally
    //   79	91	127	finally
    //   95	104	127	finally
    //   115	124	127	finally
  }
  
  private Node transformLiteral(AstNode paramAstNode)
  {
    this.decompiler.addToken(paramAstNode.getType());
    return paramAstNode;
  }
  
  private Node transformName(Name paramName)
  {
    this.decompiler.addName(paramName.getIdentifier());
    return paramName;
  }
  
  private Node transformNewExpr(NewExpression paramNewExpression)
  {
    this.decompiler.addToken(30);
    Node localNode = createCallOrNew(30, transform(paramNewExpression.getTarget()));
    localNode.setLineno(paramNewExpression.getLineno());
    List localList = paramNewExpression.getArguments();
    this.decompiler.addToken(88);
    int i = 0;
    while (i < localList.size())
    {
      localNode.addChildToBack(transform((AstNode)localList.get(i)));
      if (i < localList.size() - 1) {
        this.decompiler.addToken(90);
      }
      i += 1;
    }
    this.decompiler.addToken(89);
    if (paramNewExpression.getInitializer() != null) {
      localNode.addChildToBack(transformObjectLiteral(paramNewExpression.getInitializer()));
    }
    return localNode;
  }
  
  private Node transformNumber(NumberLiteral paramNumberLiteral)
  {
    this.decompiler.addNumber(paramNumberLiteral.getNumber());
    return paramNumberLiteral;
  }
  
  private Node transformObjectLiteral(ObjectLiteral paramObjectLiteral)
  {
    if (paramObjectLiteral.isDestructuring()) {
      return paramObjectLiteral;
    }
    this.decompiler.addToken(86);
    paramObjectLiteral = paramObjectLiteral.getElements();
    Node localNode = new Node(67);
    if (paramObjectLiteral.isEmpty()) {}
    Object[] arrayOfObject;
    for (paramObjectLiteral = ScriptRuntime.emptyArgs;; paramObjectLiteral = arrayOfObject)
    {
      this.decompiler.addToken(87);
      localNode.putProp(12, paramObjectLiteral);
      return localNode;
      int k = paramObjectLiteral.size();
      arrayOfObject = new Object[k];
      Iterator localIterator = paramObjectLiteral.iterator();
      int i = 0;
      if (localIterator.hasNext())
      {
        ObjectProperty localObjectProperty = (ObjectProperty)localIterator.next();
        label132:
        int j;
        if (localObjectProperty.isGetterMethod())
        {
          this.decompiler.addToken(152);
          j = i + 1;
          arrayOfObject[i] = getPropKey(localObjectProperty.getLeft());
          if (!localObjectProperty.isMethod()) {
            this.decompiler.addToken(67);
          }
          paramObjectLiteral = transform(localObjectProperty.getRight());
          if (!localObjectProperty.isGetterMethod()) {
            break label261;
          }
          paramObjectLiteral = createUnary(152, paramObjectLiteral);
        }
        for (;;)
        {
          localNode.addChildToBack(paramObjectLiteral);
          if (j < k) {
            this.decompiler.addToken(90);
          }
          i = j;
          break;
          if (localObjectProperty.isSetterMethod())
          {
            this.decompiler.addToken(153);
            break label132;
          }
          if (!localObjectProperty.isNormalMethod()) {
            break label132;
          }
          this.decompiler.addToken(164);
          break label132;
          label261:
          if (localObjectProperty.isSetterMethod()) {
            paramObjectLiteral = createUnary(153, paramObjectLiteral);
          } else if (localObjectProperty.isNormalMethod()) {
            paramObjectLiteral = createUnary(164, paramObjectLiteral);
          }
        }
      }
    }
  }
  
  private Node transformParenExpr(ParenthesizedExpression paramParenthesizedExpression)
  {
    paramParenthesizedExpression = paramParenthesizedExpression.getExpression();
    this.decompiler.addToken(88);
    int i = 1;
    while ((paramParenthesizedExpression instanceof ParenthesizedExpression))
    {
      this.decompiler.addToken(88);
      i += 1;
      paramParenthesizedExpression = ((ParenthesizedExpression)paramParenthesizedExpression).getExpression();
    }
    paramParenthesizedExpression = transform(paramParenthesizedExpression);
    int j = 0;
    while (j < i)
    {
      this.decompiler.addToken(89);
      j += 1;
    }
    paramParenthesizedExpression.putProp(19, Boolean.TRUE);
    return paramParenthesizedExpression;
  }
  
  private Node transformPropertyGet(PropertyGet paramPropertyGet)
  {
    Node localNode = transform(paramPropertyGet.getTarget());
    paramPropertyGet = paramPropertyGet.getProperty().getIdentifier();
    this.decompiler.addToken(109);
    this.decompiler.addName(paramPropertyGet);
    return createPropertyGet(localNode, null, paramPropertyGet, 0);
  }
  
  private Node transformRegExp(RegExpLiteral paramRegExpLiteral)
  {
    this.decompiler.addRegexp(paramRegExpLiteral.getValue(), paramRegExpLiteral.getFlags());
    this.currentScriptOrFn.addRegExp(paramRegExpLiteral);
    return paramRegExpLiteral;
  }
  
  private Node transformReturn(ReturnStatement paramReturnStatement)
  {
    boolean bool1 = Boolean.TRUE.equals(paramReturnStatement.getProp(25));
    boolean bool2 = Boolean.TRUE.equals(paramReturnStatement.getProp(27));
    AstNode localAstNode;
    if (bool1)
    {
      if (!bool2) {
        this.decompiler.addName(" ");
      }
      localAstNode = paramReturnStatement.getReturnValue();
      if (localAstNode != null) {
        break label99;
      }
    }
    label99:
    for (Node localNode = null;; localNode = transform(localAstNode))
    {
      if (!bool1) {
        this.decompiler.addEOL(83);
      }
      if (localAstNode != null) {
        break label110;
      }
      return new Node(4, paramReturnStatement.getLineno());
      this.decompiler.addToken(4);
      break;
    }
    label110:
    return new Node(4, localNode, paramReturnStatement.getLineno());
  }
  
  private Node transformScript(ScriptNode paramScriptNode)
  {
    this.decompiler.addToken(137);
    if (this.currentScope != null) {
      Kit.codeBug();
    }
    this.currentScope = paramScriptNode;
    Node localNode = new Node(130);
    Iterator localIterator = paramScriptNode.iterator();
    while (localIterator.hasNext()) {
      localNode.addChildToBack(transform((AstNode)localIterator.next()));
    }
    paramScriptNode.removeChildren();
    localNode = localNode.getFirstChild();
    if (localNode != null) {
      paramScriptNode.addChildrenToBack(localNode);
    }
    return paramScriptNode;
  }
  
  private Node transformString(StringLiteral paramStringLiteral)
  {
    this.decompiler.addString(paramStringLiteral.getValue());
    return Node.newString(paramStringLiteral.getValue());
  }
  
  private Node transformSwitch(SwitchStatement paramSwitchStatement)
  {
    this.decompiler.addToken(115);
    this.decompiler.addToken(88);
    Node localNode = transform(paramSwitchStatement.getExpression());
    this.decompiler.addToken(89);
    paramSwitchStatement.addChildToBack(localNode);
    localNode = new Node(130, paramSwitchStatement, paramSwitchStatement.getLineno());
    this.decompiler.addEOL(86);
    Iterator localIterator = paramSwitchStatement.getCases().iterator();
    while (localIterator.hasNext())
    {
      Object localObject1 = (SwitchCase)localIterator.next();
      Object localObject2 = ((SwitchCase)localObject1).getExpression();
      paramSwitchStatement = null;
      if (localObject2 != null)
      {
        this.decompiler.addToken(116);
        paramSwitchStatement = transform((AstNode)localObject2);
      }
      for (;;)
      {
        this.decompiler.addEOL(104);
        localObject2 = ((SwitchCase)localObject1).getStatements();
        localObject1 = new Block();
        if (localObject2 == null) {
          break;
        }
        localObject2 = ((List)localObject2).iterator();
        while (((Iterator)localObject2).hasNext()) {
          ((Node)localObject1).addChildToBack(transform((AstNode)((Iterator)localObject2).next()));
        }
        this.decompiler.addToken(117);
      }
      addSwitchCase(localNode, paramSwitchStatement, (Node)localObject1);
    }
    this.decompiler.addEOL(87);
    closeSwitch(localNode);
    return localNode;
  }
  
  private Node transformThrow(ThrowStatement paramThrowStatement)
  {
    this.decompiler.addToken(50);
    Node localNode = transform(paramThrowStatement.getExpression());
    this.decompiler.addEOL(83);
    return new Node(50, localNode, paramThrowStatement.getLineno());
  }
  
  private Node transformTry(TryStatement paramTryStatement)
  {
    this.decompiler.addToken(82);
    this.decompiler.addEOL(86);
    Node localNode1 = transform(paramTryStatement.getTryBlock());
    this.decompiler.addEOL(87);
    Block localBlock = new Block();
    Iterator localIterator = paramTryStatement.getCatchClauses().iterator();
    if (localIterator.hasNext())
    {
      CatchClause localCatchClause = (CatchClause)localIterator.next();
      this.decompiler.addToken(125);
      this.decompiler.addToken(88);
      String str = localCatchClause.getVarName().getIdentifier();
      this.decompiler.addName(str);
      localObject = localCatchClause.getCatchCondition();
      if (localObject != null)
      {
        this.decompiler.addName(" ");
        this.decompiler.addToken(113);
      }
      for (localObject = transform((AstNode)localObject);; localObject = new EmptyExpression())
      {
        this.decompiler.addToken(89);
        this.decompiler.addEOL(86);
        Node localNode2 = transform(localCatchClause.getBody());
        this.decompiler.addEOL(87);
        localBlock.addChildToBack(createCatch(str, (Node)localObject, localNode2, localCatchClause.getLineno()));
        break;
      }
    }
    Object localObject = null;
    if (paramTryStatement.getFinallyBlock() != null)
    {
      this.decompiler.addToken(126);
      this.decompiler.addEOL(86);
      localObject = transform(paramTryStatement.getFinallyBlock());
      this.decompiler.addEOL(87);
    }
    return createTryCatchFinally(localNode1, localBlock, (Node)localObject, paramTryStatement.getLineno());
  }
  
  private Node transformUnary(UnaryExpression paramUnaryExpression)
  {
    int i = paramUnaryExpression.getType();
    if (i == 75) {
      return transformDefaultXmlNamepace(paramUnaryExpression);
    }
    if (paramUnaryExpression.isPrefix()) {
      this.decompiler.addToken(i);
    }
    Node localNode = transform(paramUnaryExpression.getOperand());
    if (paramUnaryExpression.isPostfix()) {
      this.decompiler.addToken(i);
    }
    if ((i == 107) || (i == 108)) {
      return createIncDec(i, paramUnaryExpression.isPostfix(), localNode);
    }
    return createUnary(i, localNode);
  }
  
  private Node transformVariableInitializers(VariableDeclaration paramVariableDeclaration)
  {
    Object localObject = paramVariableDeclaration.getVariables();
    int j = ((List)localObject).size();
    Iterator localIterator = ((List)localObject).iterator();
    int i = 0;
    if (localIterator.hasNext())
    {
      VariableInitializer localVariableInitializer = (VariableInitializer)localIterator.next();
      localObject = localVariableInitializer.getTarget();
      AstNode localAstNode = localVariableInitializer.getInitializer();
      label75:
      Node localNode;
      if (localVariableInitializer.isDestructuring())
      {
        decompile((AstNode)localObject);
        localNode = null;
        if (localAstNode != null)
        {
          this.decompiler.addToken(91);
          localNode = transform(localAstNode);
        }
        if (!localVariableInitializer.isDestructuring()) {
          break label172;
        }
        if (localNode != null) {
          break label153;
        }
        paramVariableDeclaration.addChildToBack((Node)localObject);
      }
      for (;;)
      {
        if (i < j - 1) {
          this.decompiler.addToken(90);
        }
        i += 1;
        break;
        localObject = transform((AstNode)localObject);
        break label75;
        label153:
        paramVariableDeclaration.addChildToBack(createDestructuringAssignment(paramVariableDeclaration.getType(), (Node)localObject, localNode));
        continue;
        label172:
        if (localNode != null) {
          ((Node)localObject).addChildToBack(localNode);
        }
        paramVariableDeclaration.addChildToBack((Node)localObject);
      }
    }
    return paramVariableDeclaration;
  }
  
  private Node transformVariables(VariableDeclaration paramVariableDeclaration)
  {
    this.decompiler.addToken(paramVariableDeclaration.getType());
    transformVariableInitializers(paramVariableDeclaration);
    AstNode localAstNode = paramVariableDeclaration.getParent();
    if ((!(localAstNode instanceof Loop)) && (!(localAstNode instanceof LetNode))) {
      this.decompiler.addEOL(83);
    }
    return paramVariableDeclaration;
  }
  
  private Node transformWhileLoop(WhileLoop paramWhileLoop)
  {
    this.decompiler.addToken(118);
    paramWhileLoop.setType(133);
    pushScope(paramWhileLoop);
    try
    {
      this.decompiler.addToken(88);
      Node localNode1 = transform(paramWhileLoop.getCondition());
      this.decompiler.addToken(89);
      this.decompiler.addEOL(86);
      Node localNode2 = transform(paramWhileLoop.getBody());
      this.decompiler.addEOL(87);
      paramWhileLoop = createLoop(paramWhileLoop, 1, localNode2, localNode1, null, null);
      return paramWhileLoop;
    }
    finally
    {
      popScope();
    }
  }
  
  private Node transformWith(WithStatement paramWithStatement)
  {
    this.decompiler.addToken(124);
    this.decompiler.addToken(88);
    Node localNode1 = transform(paramWithStatement.getExpression());
    this.decompiler.addToken(89);
    this.decompiler.addEOL(86);
    Node localNode2 = transform(paramWithStatement.getStatement());
    this.decompiler.addEOL(87);
    return createWith(localNode1, localNode2, paramWithStatement.getLineno());
  }
  
  private Node transformXmlLiteral(XmlLiteral paramXmlLiteral)
  {
    Node localNode = new Node(30, paramXmlLiteral.getLineno());
    Object localObject = paramXmlLiteral.getFragments();
    if (((XmlString)((List)localObject).get(0)).getXml().trim().startsWith("<>"))
    {
      paramXmlLiteral = "XMLList";
      localNode.addChildToBack(createName(paramXmlLiteral));
      Iterator localIterator = ((List)localObject).iterator();
      paramXmlLiteral = null;
      label69:
      if (!localIterator.hasNext()) {
        break label270;
      }
      localObject = (XmlFragment)localIterator.next();
      if (!(localObject instanceof XmlString)) {
        break label149;
      }
      localObject = ((XmlString)localObject).getXml();
      this.decompiler.addName((String)localObject);
      if (paramXmlLiteral != null) {
        break label133;
      }
    }
    label133:
    for (paramXmlLiteral = createString((String)localObject);; paramXmlLiteral = createBinary(21, paramXmlLiteral, createString((String)localObject)))
    {
      break label69;
      paramXmlLiteral = "XML";
      break;
    }
    label149:
    localObject = (XmlExpression)localObject;
    boolean bool = ((XmlExpression)localObject).isXmlAttribute();
    this.decompiler.addToken(86);
    if ((((XmlExpression)localObject).getExpression() instanceof EmptyExpression))
    {
      localObject = createString("");
      label186:
      this.decompiler.addToken(87);
      if (!bool) {
        break label259;
      }
      localObject = createUnary(76, (Node)localObject);
    }
    label259:
    for (localObject = createBinary(21, createBinary(21, createString("\""), (Node)localObject), createString("\""));; localObject = createUnary(77, (Node)localObject))
    {
      paramXmlLiteral = createBinary(21, paramXmlLiteral, (Node)localObject);
      break;
      localObject = transform(((XmlExpression)localObject).getExpression());
      break label186;
    }
    label270:
    localNode.addChildToBack(paramXmlLiteral);
    return localNode;
  }
  
  private Node transformXmlMemberGet(XmlMemberGet paramXmlMemberGet)
  {
    XmlRef localXmlRef = paramXmlMemberGet.getMemberRef();
    Node localNode = transform(paramXmlMemberGet.getLeft());
    int i;
    if (localXmlRef.isAttributeAccess())
    {
      i = 2;
      if (paramXmlMemberGet.getType() != 144) {
        break label62;
      }
      i |= 0x4;
      this.decompiler.addToken(144);
    }
    for (;;)
    {
      return transformXmlRef(localNode, localXmlRef, i);
      i = 0;
      break;
      label62:
      this.decompiler.addToken(109);
    }
  }
  
  private Node transformXmlRef(Node paramNode, XmlRef paramXmlRef, int paramInt)
  {
    if ((paramInt & 0x2) != 0) {
      this.decompiler.addToken(148);
    }
    Object localObject = paramXmlRef.getNamespace();
    if (localObject != null) {}
    for (localObject = ((Name)localObject).getIdentifier();; localObject = null)
    {
      if (localObject != null)
      {
        this.decompiler.addName((String)localObject);
        this.decompiler.addToken(145);
      }
      if (!(paramXmlRef instanceof XmlPropRef)) {
        break;
      }
      paramXmlRef = ((XmlPropRef)paramXmlRef).getPropName().getIdentifier();
      this.decompiler.addName(paramXmlRef);
      return createPropertyGet(paramNode, (String)localObject, paramXmlRef, paramInt);
    }
    this.decompiler.addToken(84);
    paramXmlRef = transform(((XmlElemRef)paramXmlRef).getExpression());
    this.decompiler.addToken(85);
    return createElementGet(paramNode, (String)localObject, paramXmlRef, paramInt);
  }
  
  private Node transformXmlRef(XmlRef paramXmlRef)
  {
    if (paramXmlRef.isAttributeAccess()) {}
    for (int i = 2;; i = 0) {
      return transformXmlRef(null, paramXmlRef, i);
    }
  }
  
  private Node transformYield(Yield paramYield)
  {
    this.decompiler.addToken(73);
    if (paramYield.getValue() == null) {}
    for (Node localNode = null; localNode != null; localNode = transform(paramYield.getValue())) {
      return new Node(73, localNode, paramYield.getLineno());
    }
    return new Node(73, paramYield.getLineno());
  }
  
  void decompile(AstNode paramAstNode)
  {
    switch (paramAstNode.getType())
    {
    default: 
      Kit.codeBug("unexpected token: " + Token.typeToName(paramAstNode.getType()));
    case 129: 
      return;
    case 66: 
      decompileArrayLiteral((ArrayLiteral)paramAstNode);
      return;
    case 67: 
      decompileObjectLiteral((ObjectLiteral)paramAstNode);
      return;
    case 41: 
      this.decompiler.addString(((StringLiteral)paramAstNode).getValue());
      return;
    case 39: 
      this.decompiler.addName(((Name)paramAstNode).getIdentifier());
      return;
    case 40: 
      this.decompiler.addNumber(((NumberLiteral)paramAstNode).getNumber());
      return;
    case 33: 
      decompilePropertyGet((PropertyGet)paramAstNode);
      return;
    case 36: 
      decompileElementGet((ElementGet)paramAstNode);
      return;
    }
    this.decompiler.addToken(paramAstNode.getType());
  }
  
  void decompileArrayLiteral(ArrayLiteral paramArrayLiteral)
  {
    this.decompiler.addToken(84);
    paramArrayLiteral = paramArrayLiteral.getElements();
    int j = paramArrayLiteral.size();
    int i = 0;
    while (i < j)
    {
      decompile((AstNode)paramArrayLiteral.get(i));
      if (i < j - 1) {
        this.decompiler.addToken(90);
      }
      i += 1;
    }
    this.decompiler.addToken(85);
  }
  
  void decompileElementGet(ElementGet paramElementGet)
  {
    decompile(paramElementGet.getTarget());
    this.decompiler.addToken(84);
    decompile(paramElementGet.getElement());
    this.decompiler.addToken(85);
  }
  
  Node decompileFunctionHeader(FunctionNode paramFunctionNode)
  {
    int j = 1;
    int k = 0;
    Node localNode;
    if (paramFunctionNode.getFunctionName() != null)
    {
      this.decompiler.addName(paramFunctionNode.getName());
      localNode = null;
    }
    for (;;)
    {
      int i;
      if (paramFunctionNode.getFunctionType() == 4)
      {
        i = 1;
        label36:
        if ((i == 0) || (paramFunctionNode.getLp() != -1)) {
          break label152;
        }
      }
      for (;;)
      {
        if (j == 0) {
          this.decompiler.addToken(88);
        }
        List localList = paramFunctionNode.getParams();
        while (k < localList.size())
        {
          decompile((AstNode)localList.get(k));
          if (k < localList.size() - 1) {
            this.decompiler.addToken(90);
          }
          k += 1;
        }
        if (paramFunctionNode.getMemberExprNode() == null) {
          break label203;
        }
        localNode = transform(paramFunctionNode.getMemberExprNode());
        break;
        i = 0;
        break label36;
        label152:
        j = 0;
      }
      if (j == 0) {
        this.decompiler.addToken(89);
      }
      if (i != 0) {
        this.decompiler.addToken(165);
      }
      if (!paramFunctionNode.isExpressionClosure()) {
        this.decompiler.addEOL(86);
      }
      return localNode;
      label203:
      localNode = null;
    }
  }
  
  void decompileObjectLiteral(ObjectLiteral paramObjectLiteral)
  {
    this.decompiler.addToken(86);
    paramObjectLiteral = paramObjectLiteral.getElements();
    int j = paramObjectLiteral.size();
    int i = 0;
    while (i < j)
    {
      ObjectProperty localObjectProperty = (ObjectProperty)paramObjectLiteral.get(i);
      boolean bool = Boolean.TRUE.equals(localObjectProperty.getProp(26));
      decompile(localObjectProperty.getLeft());
      if (!bool)
      {
        this.decompiler.addToken(104);
        decompile(localObjectProperty.getRight());
      }
      if (i < j - 1) {
        this.decompiler.addToken(90);
      }
      i += 1;
    }
    this.decompiler.addToken(87);
  }
  
  void decompilePropertyGet(PropertyGet paramPropertyGet)
  {
    decompile(paramPropertyGet.getTarget());
    this.decompiler.addToken(109);
    decompile(paramPropertyGet.getProperty());
  }
  
  boolean isDestructuring(Node paramNode)
  {
    return ((paramNode instanceof DestructuringForm)) && (((DestructuringForm)paramNode).isDestructuring());
  }
  
  public Node transform(AstNode paramAstNode)
  {
    Object localObject = paramAstNode;
    switch (paramAstNode.getType())
    {
    default: 
      if ((paramAstNode instanceof ExpressionStatement)) {
        localObject = transformExprStmt((ExpressionStatement)paramAstNode);
      }
      break;
    case 129: 
      return (Node)localObject;
    case 158: 
      return transformArrayComp((ArrayComprehension)paramAstNode);
    case 66: 
      return transformArrayLiteral((ArrayLiteral)paramAstNode);
    case 130: 
      return transformBlock(paramAstNode);
    case 121: 
      return transformBreak((BreakStatement)paramAstNode);
    case 38: 
      return transformFunctionCall((FunctionCall)paramAstNode);
    case 122: 
      return transformContinue((ContinueStatement)paramAstNode);
    case 119: 
      return transformDoLoop((DoLoop)paramAstNode);
    case 120: 
      if ((paramAstNode instanceof ForInLoop)) {
        return transformForInLoop((ForInLoop)paramAstNode);
      }
      return transformForLoop((ForLoop)paramAstNode);
    case 110: 
      return transformFunction((FunctionNode)paramAstNode);
    case 163: 
      return transformGenExpr((GeneratorExpression)paramAstNode);
    case 36: 
      return transformElementGet((ElementGet)paramAstNode);
    case 33: 
      return transformPropertyGet((PropertyGet)paramAstNode);
    case 103: 
      return transformCondExpr((ConditionalExpression)paramAstNode);
    case 113: 
      return transformIf((IfStatement)paramAstNode);
    case 42: 
    case 43: 
    case 44: 
    case 45: 
    case 161: 
      return transformLiteral(paramAstNode);
    case 39: 
      return transformName((Name)paramAstNode);
    case 40: 
      return transformNumber((NumberLiteral)paramAstNode);
    case 30: 
      return transformNewExpr((NewExpression)paramAstNode);
    case 67: 
      return transformObjectLiteral((ObjectLiteral)paramAstNode);
    case 48: 
      return transformRegExp((RegExpLiteral)paramAstNode);
    case 4: 
      return transformReturn((ReturnStatement)paramAstNode);
    case 137: 
      return transformScript((ScriptNode)paramAstNode);
    case 41: 
      return transformString((StringLiteral)paramAstNode);
    case 115: 
      return transformSwitch((SwitchStatement)paramAstNode);
    case 50: 
      return transformThrow((ThrowStatement)paramAstNode);
    case 82: 
      return transformTry((TryStatement)paramAstNode);
    case 118: 
      return transformWhileLoop((WhileLoop)paramAstNode);
    case 124: 
      return transformWith((WithStatement)paramAstNode);
    case 73: 
      return transformYield((Yield)paramAstNode);
    }
    if ((paramAstNode instanceof Assignment)) {
      return transformAssignment((Assignment)paramAstNode);
    }
    if ((paramAstNode instanceof UnaryExpression)) {
      return transformUnary((UnaryExpression)paramAstNode);
    }
    if ((paramAstNode instanceof XmlMemberGet)) {
      return transformXmlMemberGet((XmlMemberGet)paramAstNode);
    }
    if ((paramAstNode instanceof InfixExpression)) {
      return transformInfix((InfixExpression)paramAstNode);
    }
    if ((paramAstNode instanceof VariableDeclaration)) {
      return transformVariables((VariableDeclaration)paramAstNode);
    }
    if ((paramAstNode instanceof ParenthesizedExpression)) {
      return transformParenExpr((ParenthesizedExpression)paramAstNode);
    }
    if ((paramAstNode instanceof LabeledStatement)) {
      return transformLabeledStatement((LabeledStatement)paramAstNode);
    }
    if ((paramAstNode instanceof LetNode)) {
      return transformLetNode((LetNode)paramAstNode);
    }
    if ((paramAstNode instanceof XmlRef)) {
      return transformXmlRef((XmlRef)paramAstNode);
    }
    if ((paramAstNode instanceof XmlLiteral)) {
      return transformXmlLiteral((XmlLiteral)paramAstNode);
    }
    throw new IllegalArgumentException("Can't transform: " + paramAstNode);
  }
  
  public ScriptNode transformTree(AstRoot paramAstRoot)
  {
    this.currentScriptOrFn = paramAstRoot;
    this.inUseStrictDirective = paramAstRoot.isInStrictMode();
    int i = this.decompiler.getCurrentOffset();
    paramAstRoot = (ScriptNode)transform(paramAstRoot);
    paramAstRoot.setEncodedSourceBounds(i, this.decompiler.getCurrentOffset());
    if (this.compilerEnv.isGeneratingSource()) {
      paramAstRoot.setEncodedSource(this.decompiler.getEncodedSource());
    }
    this.decompiler = null;
    return paramAstRoot;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\IRFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */