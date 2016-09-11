package org.mozilla.javascript;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.mozilla.javascript.ast.FunctionNode;
import org.mozilla.javascript.ast.Jump;
import org.mozilla.javascript.ast.Scope;
import org.mozilla.javascript.ast.ScriptNode;

public class NodeTransformer
{
  private boolean hasFinally;
  private ObjArray loopEnds;
  private ObjArray loops;
  
  private static Node addBeforeCurrent(Node paramNode1, Node paramNode2, Node paramNode3, Node paramNode4)
  {
    if (paramNode2 == null)
    {
      if (paramNode3 != paramNode1.getFirstChild()) {
        Kit.codeBug();
      }
      paramNode1.addChildToFront(paramNode4);
      return paramNode4;
    }
    if (paramNode3 != paramNode2.getNext()) {
      Kit.codeBug();
    }
    paramNode1.addChildAfter(paramNode4, paramNode2);
    return paramNode4;
  }
  
  private static Node replaceCurrent(Node paramNode1, Node paramNode2, Node paramNode3, Node paramNode4)
  {
    if (paramNode2 == null)
    {
      if (paramNode3 != paramNode1.getFirstChild()) {
        Kit.codeBug();
      }
      paramNode1.replaceChild(paramNode3, paramNode4);
      return paramNode4;
    }
    if (paramNode2.next == paramNode3)
    {
      paramNode1.replaceChildAfter(paramNode2, paramNode4);
      return paramNode4;
    }
    paramNode1.replaceChild(paramNode3, paramNode4);
    return paramNode4;
  }
  
  private void transformCompilationUnit(ScriptNode paramScriptNode, boolean paramBoolean)
  {
    this.loops = new ObjArray();
    this.loopEnds = new ObjArray();
    this.hasFinally = false;
    boolean bool1;
    if ((paramScriptNode.getType() != 110) || (((FunctionNode)paramScriptNode).requiresActivation()))
    {
      bool1 = true;
      if (bool1) {
        break label76;
      }
    }
    label76:
    for (boolean bool2 = true;; bool2 = false)
    {
      paramScriptNode.flattenSymbolTable(bool2);
      transformCompilationUnit_r(paramScriptNode, paramScriptNode, paramScriptNode, bool1, paramBoolean);
      return;
      bool1 = false;
      break;
    }
  }
  
  private void transformCompilationUnit_r(ScriptNode paramScriptNode, Node paramNode, Scope paramScope, boolean paramBoolean1, boolean paramBoolean2)
  {
    Object localObject1 = null;
    Object localObject3 = null;
    if (localObject1 == null) {}
    Object localObject2;
    for (localObject1 = paramNode.getFirstChild(); localObject1 == null; localObject1 = localObject2)
    {
      return;
      localObject2 = ((Node)localObject1).getNext();
      localObject3 = localObject1;
    }
    int i = ((Node)localObject1).getType();
    Object localObject4;
    Object localObject5;
    Object localObject6;
    if ((paramBoolean1) && ((i == 130) || (i == 133) || (i == 158)) && ((localObject1 instanceof Scope)))
    {
      localObject2 = (Scope)localObject1;
      if (((Scope)localObject2).getSymbolTable() != null)
      {
        if (i == 158) {}
        for (i = 159;; i = 154)
        {
          localObject4 = new Node(i);
          localObject5 = new Node(154);
          ((Node)localObject4).addChildToBack((Node)localObject5);
          localObject6 = ((Scope)localObject2).getSymbolTable().keySet().iterator();
          while (((Iterator)localObject6).hasNext()) {
            ((Node)localObject5).addChildToBack(Node.newString(39, (String)((Iterator)localObject6).next()));
          }
        }
        ((Scope)localObject2).setSymbolTable(null);
        localObject2 = replaceCurrent(paramNode, (Node)localObject3, (Node)localObject1, (Node)localObject4);
        i = ((Node)localObject2).getType();
        ((Node)localObject4).addChildToBack((Node)localObject1);
        localObject1 = localObject2;
      }
    }
    for (;;)
    {
      label440:
      label455:
      label684:
      int j;
      label698:
      int k;
      switch (i)
      {
      default: 
      case 115: 
      case 131: 
      case 133: 
      case 124: 
      case 82: 
      case 3: 
      case 132: 
      case 73: 
      case 4: 
        for (;;)
        {
          if ((localObject1 instanceof Scope))
          {
            localObject2 = (Scope)localObject1;
            transformCompilationUnit_r(paramScriptNode, (Node)localObject1, (Scope)localObject2, paramBoolean1, paramBoolean2);
            break;
            this.loops.push(localObject1);
            this.loopEnds.push(((Jump)localObject1).target);
            continue;
            this.loops.push(localObject1);
            localObject2 = ((Node)localObject1).getNext();
            if (((Node)localObject2).getType() != 3) {
              Kit.codeBug();
            }
            this.loopEnds.push(localObject2);
            continue;
            localObject2 = ((Jump)localObject1).getFinally();
            if (localObject2 != null)
            {
              this.hasFinally = true;
              this.loops.push(localObject1);
              this.loopEnds.push(localObject2);
              continue;
              if ((!this.loopEnds.isEmpty()) && (this.loopEnds.peek() == localObject1))
              {
                this.loopEnds.pop();
                this.loops.pop();
                continue;
                ((FunctionNode)paramScriptNode).addResumptionPoint((Node)localObject1);
                continue;
                if ((paramScriptNode.getType() == 110) && (((FunctionNode)paramScriptNode).isGenerator())) {}
                for (i = 1;; i = 0)
                {
                  if (i != 0) {
                    ((Node)localObject1).putIntProp(20, 1);
                  }
                  if (this.hasFinally) {
                    break label684;
                  }
                  break;
                }
                localObject2 = null;
                j = this.loops.size() - 1;
                if (j >= 0)
                {
                  localObject5 = (Node)this.loops.get(j);
                  k = ((Node)localObject5).getType();
                  if (k != 82)
                  {
                    localObject4 = localObject2;
                    if (k != 124) {}
                  }
                  else
                  {
                    if (k != 82) {
                      break label820;
                    }
                    localObject4 = new Jump(136);
                    ((Jump)localObject4).target = ((Jump)localObject5).getFinally();
                    label774:
                    if (localObject2 != null) {
                      break label1796;
                    }
                    localObject2 = new Node(130, ((Node)localObject1).getLineno());
                  }
                }
              }
            }
          }
        }
      }
      label820:
      label971:
      label1024:
      label1120:
      label1230:
      label1302:
      label1533:
      label1796:
      for (;;)
      {
        ((Node)localObject2).addChildToBack((Node)localObject4);
        localObject4 = localObject2;
        j -= 1;
        localObject2 = localObject4;
        break label698;
        localObject4 = new Node(3);
        break label774;
        if (localObject2 == null) {
          break label440;
        }
        localObject4 = ((Node)localObject1).getFirstChild();
        localObject3 = replaceCurrent(paramNode, (Node)localObject3, (Node)localObject1, (Node)localObject2);
        if ((localObject4 == null) || (i != 0))
        {
          ((Node)localObject2).addChildToBack((Node)localObject1);
          localObject1 = localObject3;
          break;
        }
        localObject1 = new Node(135, (Node)localObject4);
        ((Node)localObject2).addChildToFront((Node)localObject1);
        ((Node)localObject2).addChildToBack(new Node(65));
        transformCompilationUnit_r(paramScriptNode, (Node)localObject1, paramScope, paramBoolean1, paramBoolean2);
        localObject1 = localObject3;
        break;
        localObject4 = (Jump)localObject1;
        localObject5 = ((Jump)localObject4).getJumpStatement();
        if (localObject5 == null) {
          Kit.codeBug();
        }
        j = this.loops.size();
        localObject2 = localObject3;
        if (j == 0) {
          throw Kit.codeBug();
        }
        j -= 1;
        localObject3 = (Node)this.loops.get(j);
        if (localObject3 == localObject5)
        {
          if (i != 121) {
            break label1120;
          }
          ((Jump)localObject4).target = ((Jump)localObject5).target;
          ((Jump)localObject4).setType(5);
          break label440;
        }
        k = ((Node)localObject3).getType();
        if (k == 124) {
          localObject2 = addBeforeCurrent(paramNode, (Node)localObject2, (Node)localObject1, new Node(3));
        }
        for (;;)
        {
          break label971;
          if (k == 82)
          {
            localObject3 = (Jump)localObject3;
            localObject6 = new Jump(136);
            ((Jump)localObject6).target = ((Jump)localObject3).getFinally();
            localObject2 = addBeforeCurrent(paramNode, (Node)localObject2, (Node)localObject1, (Node)localObject6);
            continue;
            ((Jump)localObject4).target = ((Jump)localObject5).getContinue();
            break label1024;
            visitCall((Node)localObject1, paramScriptNode);
            break;
            visitNew((Node)localObject1, paramScriptNode);
            break;
            if (((Node)localObject1).getFirstChild().getType() == 154)
            {
              if ((paramScriptNode.getType() != 110) || (((FunctionNode)paramScriptNode).requiresActivation())) {}
              for (boolean bool = true;; bool = false)
              {
                localObject1 = visitLet(bool, paramNode, (Node)localObject3, (Node)localObject1);
                break;
              }
            }
            localObject5 = new Node(130);
            localObject2 = ((Node)localObject1).getFirstChild();
            for (;;)
            {
              if (localObject2 != null)
              {
                localObject4 = ((Node)localObject2).getNext();
                if (((Node)localObject2).getType() == 39)
                {
                  if (!((Node)localObject2).hasChildren())
                  {
                    localObject2 = localObject4;
                    continue;
                  }
                  localObject6 = ((Node)localObject2).getFirstChild();
                  ((Node)localObject2).removeChild((Node)localObject6);
                  ((Node)localObject2).setType(49);
                  if (i == 155)
                  {
                    j = 156;
                    localObject2 = new Node(j, (Node)localObject2, (Node)localObject6);
                  }
                }
              }
            }
            for (;;)
            {
              ((Node)localObject5).addChildToBack(new Node(134, (Node)localObject2, ((Node)localObject1).getLineno()));
              localObject2 = localObject4;
              break label1230;
              j = 8;
              break label1302;
              if (((Node)localObject2).getType() != 159)
              {
                throw Kit.codeBug();
                localObject1 = replaceCurrent(paramNode, (Node)localObject3, (Node)localObject1, (Node)localObject5);
                break;
                localObject2 = paramScope.getDefiningScope(((Node)localObject1).getString());
                if (localObject2 != null) {
                  ((Node)localObject1).setScope((Scope)localObject2);
                }
                break;
                localObject3 = ((Node)localObject1).getFirstChild();
                localObject2 = localObject3;
                if (i == 7)
                {
                  while (((Node)localObject3).getType() == 26) {
                    localObject3 = ((Node)localObject3).getFirstChild();
                  }
                  if (((Node)localObject3).getType() != 12)
                  {
                    localObject2 = localObject3;
                    if (((Node)localObject3).getType() != 13) {}
                  }
                  else
                  {
                    localObject4 = ((Node)localObject3).getFirstChild();
                    localObject5 = ((Node)localObject3).getLastChild();
                    if ((((Node)localObject4).getType() != 39) || (!((Node)localObject4).getString().equals("undefined"))) {
                      break label1533;
                    }
                    localObject2 = localObject5;
                  }
                }
                while (((Node)localObject2).getType() == 33)
                {
                  ((Node)localObject2).setType(34);
                  break;
                  localObject2 = localObject3;
                  if (((Node)localObject5).getType() == 39)
                  {
                    localObject2 = localObject3;
                    if (((Node)localObject5).getString().equals("undefined")) {
                      localObject2 = localObject4;
                    }
                  }
                }
                if (paramBoolean2) {
                  ((Node)localObject1).setType(74);
                }
                if (paramBoolean1) {
                  break;
                }
                if (i == 39) {
                  localObject2 = localObject1;
                }
                for (;;)
                {
                  if (((Node)localObject2).getScope() != null)
                  {
                    break;
                    localObject4 = ((Node)localObject1).getFirstChild();
                    localObject2 = localObject4;
                    if (((Node)localObject4).getType() != 49)
                    {
                      if (i == 31) {
                        break;
                      }
                      throw Kit.codeBug();
                    }
                  }
                }
                localObject4 = paramScope.getDefiningScope(((Node)localObject2).getString());
                if (localObject4 == null) {
                  break;
                }
                ((Node)localObject2).setScope((Scope)localObject4);
                if (i == 39)
                {
                  ((Node)localObject1).setType(55);
                  break;
                }
                if ((i == 8) || (i == 74))
                {
                  ((Node)localObject1).setType(56);
                  ((Node)localObject2).setType(41);
                  break;
                }
                if (i == 156)
                {
                  ((Node)localObject1).setType(157);
                  ((Node)localObject2).setType(41);
                  break;
                }
                if (i == 31)
                {
                  localObject1 = replaceCurrent(paramNode, (Node)localObject3, (Node)localObject1, new Node(44));
                  break;
                }
                throw Kit.codeBug();
                localObject2 = paramScope;
                break label455;
              }
            }
          }
        }
      }
    }
  }
  
  public final void transform(ScriptNode paramScriptNode)
  {
    transform(paramScriptNode, false);
  }
  
  public final void transform(ScriptNode paramScriptNode, boolean paramBoolean)
  {
    int i = 0;
    if ((paramBoolean) || (paramScriptNode.isInStrictMode())) {}
    for (paramBoolean = true;; paramBoolean = false)
    {
      transformCompilationUnit(paramScriptNode, paramBoolean);
      while (i != paramScriptNode.getFunctionCount())
      {
        transform(paramScriptNode.getFunctionNode(i), paramBoolean);
        i += 1;
      }
    }
  }
  
  protected void visitCall(Node paramNode, ScriptNode paramScriptNode) {}
  
  protected Node visitLet(boolean paramBoolean, Node paramNode1, Node paramNode2, Node paramNode3)
  {
    Node localNode3 = paramNode3.getFirstChild();
    Object localObject1 = localNode3.getNext();
    paramNode3.removeChild(localNode3);
    paramNode3.removeChild((Node)localObject1);
    int i;
    if (paramNode3.getType() == 159)
    {
      i = 1;
      if (!paramBoolean) {
        break label408;
      }
      if (i == 0) {
        break label155;
      }
    }
    Node localNode1;
    Object localObject2;
    Node localNode2;
    label155:
    for (int j = 160;; j = 130)
    {
      localNode1 = replaceCurrent(paramNode1, paramNode2, paramNode3, new Node(j));
      localObject2 = new ArrayList();
      localNode2 = new Node(67);
      paramNode1 = localNode3.getFirstChild();
      paramNode2 = (Node)localObject1;
      if (paramNode1 == null) {
        break label350;
      }
      if (paramNode1.getType() != 159) {
        break label815;
      }
      paramNode3 = (List)paramNode1.getProp(22);
      localObject1 = paramNode1.getFirstChild();
      if (((Node)localObject1).getType() == 154) {
        break label163;
      }
      throw Kit.codeBug();
      i = 0;
      break;
    }
    label163:
    if (i != 0) {}
    for (paramNode2 = new Node(90, ((Node)localObject1).getNext(), paramNode2); paramNode3 != null; paramNode2 = new Node(130, new Node(134, ((Node)localObject1).getNext()), paramNode2))
    {
      ((ArrayList)localObject2).addAll(paramNode3);
      j = 0;
      while (j < paramNode3.size())
      {
        localNode2.addChildToBack(new Node(127, Node.newNumber(0.0D)));
        j += 1;
      }
    }
    label350:
    label408:
    label519:
    label659:
    label809:
    label815:
    for (paramNode3 = ((Node)localObject1).getFirstChild();; paramNode3 = paramNode1)
    {
      if (paramNode3.getType() != 39) {
        throw Kit.codeBug();
      }
      ((ArrayList)localObject2).add(ScriptRuntime.getIndexObject(paramNode3.getString()));
      localObject1 = paramNode3.getFirstChild();
      paramNode3 = (Node)localObject1;
      if (localObject1 == null) {
        paramNode3 = new Node(127, Node.newNumber(0.0D));
      }
      localNode2.addChildToBack(paramNode3);
      paramNode1 = paramNode1.getNext();
      break;
      localNode2.putProp(12, ((ArrayList)localObject2).toArray());
      localNode1.addChildToBack(new Node(2, localNode2));
      localNode1.addChildToBack(new Node(124, paramNode2));
      localNode1.addChildToBack(new Node(3));
      return localNode1;
      if (i != 0) {}
      for (j = 90;; j = 130)
      {
        localObject2 = replaceCurrent(paramNode1, paramNode2, paramNode3, new Node(j));
        localNode2 = new Node(90);
        paramNode1 = localNode3.getFirstChild();
        paramNode2 = (Node)localObject1;
        if (paramNode1 == null) {
          break label659;
        }
        if (paramNode1.getType() != 159) {
          break label809;
        }
        localObject1 = paramNode1.getFirstChild();
        if (((Node)localObject1).getType() == 154) {
          break;
        }
        throw Kit.codeBug();
      }
      if (i != 0)
      {
        paramNode2 = new Node(90, ((Node)localObject1).getNext(), paramNode2);
        Scope.joinScopes((Scope)paramNode1, (Scope)paramNode3);
      }
      for (localObject1 = ((Node)localObject1).getFirstChild();; localObject1 = paramNode1)
      {
        if (((Node)localObject1).getType() != 39)
        {
          throw Kit.codeBug();
          paramNode2 = new Node(130, new Node(134, ((Node)localObject1).getNext()), paramNode2);
          break label519;
        }
        localNode3 = Node.newString(((Node)localObject1).getString());
        localNode3.setScope((Scope)paramNode3);
        localNode1 = ((Node)localObject1).getFirstChild();
        localObject1 = localNode1;
        if (localNode1 == null) {
          localObject1 = new Node(127, Node.newNumber(0.0D));
        }
        localNode2.addChildToBack(new Node(56, localNode3, (Node)localObject1));
        paramNode1 = paramNode1.getNext();
        break;
        if (i != 0)
        {
          ((Node)localObject2).addChildToBack(localNode2);
          paramNode3.setType(90);
          ((Node)localObject2).addChildToBack(paramNode3);
          paramNode3.addChildToBack(paramNode2);
          if ((paramNode2 instanceof Scope))
          {
            paramNode1 = ((Scope)paramNode2).getParentScope();
            ((Scope)paramNode2).setParentScope((Scope)paramNode3);
            ((Scope)paramNode3).setParentScope(paramNode1);
            return (Node)localObject2;
          }
        }
        else
        {
          ((Node)localObject2).addChildToBack(new Node(134, localNode2));
          paramNode3.setType(130);
          ((Node)localObject2).addChildToBack(paramNode3);
          paramNode3.addChildrenToBack(paramNode2);
          if ((paramNode2 instanceof Scope))
          {
            paramNode1 = ((Scope)paramNode2).getParentScope();
            ((Scope)paramNode2).setParentScope((Scope)paramNode3);
            ((Scope)paramNode3).setParentScope(paramNode1);
          }
        }
        return (Node)localObject2;
      }
    }
  }
  
  protected void visitNew(Node paramNode, ScriptNode paramScriptNode) {}
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\NodeTransformer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */