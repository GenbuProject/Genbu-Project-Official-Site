package org.mozilla.javascript;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.mozilla.javascript.ast.ArrayComprehension;
import org.mozilla.javascript.ast.ArrayComprehensionLoop;
import org.mozilla.javascript.ast.ArrayLiteral;
import org.mozilla.javascript.ast.Assignment;
import org.mozilla.javascript.ast.AstNode;
import org.mozilla.javascript.ast.AstRoot;
import org.mozilla.javascript.ast.Block;
import org.mozilla.javascript.ast.BreakStatement;
import org.mozilla.javascript.ast.CatchClause;
import org.mozilla.javascript.ast.Comment;
import org.mozilla.javascript.ast.ConditionalExpression;
import org.mozilla.javascript.ast.ContinueStatement;
import org.mozilla.javascript.ast.DestructuringForm;
import org.mozilla.javascript.ast.DoLoop;
import org.mozilla.javascript.ast.ElementGet;
import org.mozilla.javascript.ast.EmptyExpression;
import org.mozilla.javascript.ast.EmptyStatement;
import org.mozilla.javascript.ast.ErrorNode;
import org.mozilla.javascript.ast.ExpressionStatement;
import org.mozilla.javascript.ast.ForInLoop;
import org.mozilla.javascript.ast.ForLoop;
import org.mozilla.javascript.ast.FunctionCall;
import org.mozilla.javascript.ast.FunctionNode;
import org.mozilla.javascript.ast.GeneratorExpression;
import org.mozilla.javascript.ast.GeneratorExpressionLoop;
import org.mozilla.javascript.ast.IdeErrorReporter;
import org.mozilla.javascript.ast.IfStatement;
import org.mozilla.javascript.ast.InfixExpression;
import org.mozilla.javascript.ast.Jump;
import org.mozilla.javascript.ast.KeywordLiteral;
import org.mozilla.javascript.ast.Label;
import org.mozilla.javascript.ast.LabeledStatement;
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

public class Parser
{
  public static final int ARGC_LIMIT = 65536;
  static final int CLEAR_TI_MASK = 65535;
  private static final int GET_ENTRY = 2;
  private static final int METHOD_ENTRY = 8;
  private static final int PROP_ENTRY = 1;
  private static final int SET_ENTRY = 4;
  static final int TI_AFTER_EOL = 65536;
  static final int TI_CHECK_LABEL = 131072;
  boolean calledByCompileFunction;
  CompilerEnvirons compilerEnv;
  private int currentFlaggedToken = 0;
  private Comment currentJsDocComment;
  private LabeledStatement currentLabel;
  Scope currentScope;
  ScriptNode currentScriptOrFn;
  private int currentToken;
  private boolean defaultUseStrictDirective;
  private int endFlags;
  private IdeErrorReporter errorCollector;
  private ErrorReporter errorReporter;
  private boolean inDestructuringAssignment;
  private boolean inForInit;
  protected boolean inUseStrictDirective;
  private Map<String, LabeledStatement> labelSet;
  private List<Jump> loopAndSwitchSet;
  private List<Loop> loopSet;
  protected int nestingOfFunction;
  private boolean parseFinished;
  private int prevNameTokenLineno;
  private int prevNameTokenStart;
  private String prevNameTokenString = "";
  private List<Comment> scannedComments;
  private char[] sourceChars;
  private String sourceURI;
  private int syntaxErrorCount;
  private TokenStream ts;
  
  public Parser()
  {
    this(new CompilerEnvirons());
  }
  
  public Parser(CompilerEnvirons paramCompilerEnvirons)
  {
    this(paramCompilerEnvirons, paramCompilerEnvirons.getErrorReporter());
  }
  
  public Parser(CompilerEnvirons paramCompilerEnvirons, ErrorReporter paramErrorReporter)
  {
    this.compilerEnv = paramCompilerEnvirons;
    this.errorReporter = paramErrorReporter;
    if ((paramErrorReporter instanceof IdeErrorReporter)) {
      this.errorCollector = ((IdeErrorReporter)paramErrorReporter);
    }
  }
  
  private void addError(String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3, String paramString3, int paramInt4)
  {
    this.syntaxErrorCount += 1;
    paramString1 = lookupMessage(paramString1, paramString2);
    if (this.errorCollector != null)
    {
      this.errorCollector.error(paramString1, this.sourceURI, paramInt1, paramInt2);
      return;
    }
    this.errorReporter.error(paramString1, this.sourceURI, paramInt3, paramString3, paramInt4);
  }
  
  private AstNode addExpr()
    throws IOException
  {
    int i;
    int j;
    for (Object localObject = mulExpr();; localObject = new InfixExpression(i, (AstNode)localObject, mulExpr(), j))
    {
      i = peekToken();
      j = this.ts.tokenBeg;
      if ((i != 21) && (i != 22)) {
        break;
      }
      consumeToken();
    }
    return (AstNode)localObject;
  }
  
  private void addStrictWarning(String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3, String paramString3, int paramInt4)
  {
    if (this.compilerEnv.isStrictMode()) {
      addWarning(paramString1, paramString2, paramInt1, paramInt2, paramInt3, paramString3, paramInt4);
    }
  }
  
  private void addWarning(String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3, String paramString3, int paramInt4)
  {
    String str = lookupMessage(paramString1, paramString2);
    if (this.compilerEnv.reportWarningAsError())
    {
      addError(paramString1, paramString2, paramInt1, paramInt2, paramInt3, paramString3, paramInt4);
      return;
    }
    if (this.errorCollector != null)
    {
      this.errorCollector.warning(str, this.sourceURI, paramInt1, paramInt2);
      return;
    }
    this.errorReporter.warning(str, this.sourceURI, paramInt3, paramString3, paramInt4);
  }
  
  private AstNode andExpr()
    throws IOException
  {
    AstNode localAstNode = bitOrExpr();
    if (matchToken(106))
    {
      int i = this.ts.tokenBeg;
      return new InfixExpression(106, localAstNode, andExpr(), i);
    }
    return localAstNode;
  }
  
  /* Error */
  private List<AstNode> argumentList()
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: bipush 89
    //   3: invokespecial 208	org/mozilla/javascript/Parser:matchToken	(I)Z
    //   6: ifeq +5 -> 11
    //   9: aconst_null
    //   10: areturn
    //   11: new 214	java/util/ArrayList
    //   14: dup
    //   15: invokespecial 215	java/util/ArrayList:<init>	()V
    //   18: astore 4
    //   20: aload_0
    //   21: getfield 134	org/mozilla/javascript/Parser:inForInit	Z
    //   24: istore_2
    //   25: aload_0
    //   26: iconst_0
    //   27: putfield 134	org/mozilla/javascript/Parser:inForInit	Z
    //   30: aload_0
    //   31: invokespecial 166	org/mozilla/javascript/Parser:peekToken	()I
    //   34: bipush 73
    //   36: if_icmpne +9 -> 45
    //   39: aload_0
    //   40: ldc -39
    //   42: invokevirtual 221	org/mozilla/javascript/Parser:reportError	(Ljava/lang/String;)V
    //   45: aload_0
    //   46: invokespecial 224	org/mozilla/javascript/Parser:assignExpr	()Lorg/mozilla/javascript/ast/AstNode;
    //   49: astore 5
    //   51: aload_0
    //   52: invokespecial 166	org/mozilla/javascript/Parser:peekToken	()I
    //   55: istore_1
    //   56: iload_1
    //   57: bipush 120
    //   59: if_icmpne +47 -> 106
    //   62: aload 4
    //   64: aload_0
    //   65: aload 5
    //   67: iconst_0
    //   68: iconst_1
    //   69: invokespecial 228	org/mozilla/javascript/Parser:generatorExpression	(Lorg/mozilla/javascript/ast/AstNode;IZ)Lorg/mozilla/javascript/ast/AstNode;
    //   72: invokeinterface 234 2 0
    //   77: pop
    //   78: aload_0
    //   79: bipush 90
    //   81: invokespecial 208	org/mozilla/javascript/Parser:matchToken	(I)Z
    //   84: istore_3
    //   85: iload_3
    //   86: ifne -56 -> 30
    //   89: aload_0
    //   90: iload_2
    //   91: putfield 134	org/mozilla/javascript/Parser:inForInit	Z
    //   94: aload_0
    //   95: bipush 89
    //   97: ldc -20
    //   99: invokespecial 240	org/mozilla/javascript/Parser:mustMatchToken	(ILjava/lang/String;)Z
    //   102: pop
    //   103: aload 4
    //   105: areturn
    //   106: aload 4
    //   108: aload 5
    //   110: invokeinterface 234 2 0
    //   115: pop
    //   116: goto -38 -> 78
    //   119: astore 4
    //   121: aload_0
    //   122: iload_2
    //   123: putfield 134	org/mozilla/javascript/Parser:inForInit	Z
    //   126: aload 4
    //   128: athrow
    //   129: astore 5
    //   131: goto -53 -> 78
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	134	0	this	Parser
    //   55	5	1	i	int
    //   24	99	2	bool1	boolean
    //   84	2	3	bool2	boolean
    //   18	89	4	localArrayList	ArrayList
    //   119	8	4	localObject	Object
    //   49	60	5	localAstNode	AstNode
    //   129	1	5	localIOException	IOException
    // Exception table:
    //   from	to	target	type
    //   30	45	119	finally
    //   45	56	119	finally
    //   62	78	119	finally
    //   78	85	119	finally
    //   106	116	119	finally
    //   62	78	129	java/io/IOException
  }
  
  private AstNode arrayComprehension(AstNode paramAstNode, int paramInt)
    throws IOException
  {
    ArrayList localArrayList = new ArrayList();
    while (peekToken() == 120) {
      localArrayList.add(arrayComprehensionLoop());
    }
    int i = -1;
    ConditionData localConditionData = null;
    if (peekToken() == 113)
    {
      consumeToken();
      i = this.ts.tokenBeg - paramInt;
      localConditionData = condition();
    }
    mustMatchToken(85, "msg.no.bracket.arg");
    ArrayComprehension localArrayComprehension = new ArrayComprehension(paramInt, this.ts.tokenEnd - paramInt);
    localArrayComprehension.setResult(paramAstNode);
    localArrayComprehension.setLoops(localArrayList);
    if (localConditionData != null)
    {
      localArrayComprehension.setIfPosition(i);
      localArrayComprehension.setFilter(localConditionData.condition);
      localArrayComprehension.setFilterLp(localConditionData.lp - paramInt);
      localArrayComprehension.setFilterRp(localConditionData.rp - paramInt);
    }
    return localArrayComprehension;
  }
  
  private ArrayComprehensionLoop arrayComprehensionLoop()
    throws IOException
  {
    boolean bool2 = true;
    if (nextToken() != 120) {
      codeBug();
    }
    int n = this.ts.tokenBeg;
    ArrayComprehensionLoop localArrayComprehensionLoop = new ArrayComprehensionLoop(n);
    pushScope(localArrayComprehensionLoop);
    for (;;)
    {
      try
      {
        if (!matchToken(39)) {
          break label457;
        }
        if (this.ts.getString().equals("each"))
        {
          j = this.ts.tokenBeg - n;
          if (!mustMatchToken(88, "msg.no.paren.for")) {
            break label446;
          }
          k = this.ts.tokenBeg - n;
        }
        Object localObject1;
        boolean bool1;
        int i;
        int m;
        switch (peekToken())
        {
        case 84: 
          reportError("msg.bad.var");
          localObject1 = null;
          if (((AstNode)localObject1).getType() == 39) {
            defineSymbol(154, this.ts.getString(), true);
          }
          switch (nextToken())
          {
          case 52: 
            reportError("msg.in.after.for.name");
            bool1 = false;
            i = -1;
            AstNode localAstNode = expr();
            if (mustMatchToken(89, "msg.no.paren.for.ctrl"))
            {
              m = this.ts.tokenBeg - n;
              localArrayComprehensionLoop.setLength(this.ts.tokenEnd - n);
              localArrayComprehensionLoop.setIterator((AstNode)localObject1);
              localArrayComprehensionLoop.setIteratedObject(localAstNode);
              localArrayComprehensionLoop.setInPosition(i);
              localArrayComprehensionLoop.setEachPosition(j);
              if (j != -1)
              {
                localArrayComprehensionLoop.setIsForEach(bool2);
                localArrayComprehensionLoop.setParens(k, m);
                localArrayComprehensionLoop.setIsForOf(bool1);
                return localArrayComprehensionLoop;
                reportError("msg.no.paren.for");
              }
            }
            break;
          }
          break;
        case 86: 
          localObject1 = destructuringPrimaryExpr();
          markDestructuring((AstNode)localObject1);
          break;
        case 39: 
          consumeToken();
          localObject1 = createNameNode();
          continue;
          i = this.ts.tokenBeg;
          bool1 = false;
          i -= n;
          continue;
          if (!"of".equals(this.ts.getString())) {
            continue;
          }
          if (j != -1) {
            reportError("msg.invalid.for.each");
          }
          i = this.ts.tokenBeg;
          bool1 = true;
          i -= n;
          continue;
          bool2 = false;
          continue;
          m = -1;
        }
      }
      finally
      {
        popScope();
      }
      continue;
      label446:
      int k = -1;
      continue;
      continue;
      continue;
      label457:
      int j = -1;
    }
  }
  
  private AstNode arrayLiteral()
    throws IOException
  {
    int n = 1;
    if (this.currentToken != 84) {
      codeBug();
    }
    int i1 = this.ts.tokenBeg;
    int m = this.ts.tokenEnd;
    Object localObject = new ArrayList();
    ArrayLiteral localArrayLiteral = new ArrayLiteral(i1);
    int k = 0;
    int i = -1;
    int j = 1;
    int i2;
    for (;;)
    {
      i2 = peekToken();
      if (i2 != 90) {
        break;
      }
      consumeToken();
      i = this.ts.tokenEnd;
      if (j == 0)
      {
        j = 1;
      }
      else
      {
        ((List)localObject).add(new EmptyExpression(this.ts.tokenBeg, 1));
        k += 1;
      }
    }
    if (i2 == 85)
    {
      consumeToken();
      m = this.ts.tokenEnd;
      i2 = ((List)localObject).size();
      if (j != 0)
      {
        j = n;
        label161:
        localArrayLiteral.setDestructuringLength(i2 + j);
        localArrayLiteral.setSkipCount(k);
        if (i == -1) {
          break label333;
        }
        warnTrailingComma(i1, (List)localObject, i);
        i = m;
      }
    }
    for (;;)
    {
      label193:
      localObject = ((List)localObject).iterator();
      for (;;)
      {
        if (((Iterator)localObject).hasNext())
        {
          localArrayLiteral.addElement((AstNode)((Iterator)localObject).next());
          continue;
          j = 0;
          break label161;
          if ((i2 == 120) && (j == 0) && (((List)localObject).size() == 1)) {
            return arrayComprehension((AstNode)((List)localObject).get(0), i1);
          }
          if (i2 == 0)
          {
            reportError("msg.no.bracket.arg");
            i = m;
            break label193;
          }
          if (j == 0) {
            reportError("msg.no.bracket.arg");
          }
          ((List)localObject).add(assignExpr());
          i = -1;
          j = 0;
          break;
        }
      }
      localArrayLiteral.setLength(i - i1);
      return localArrayLiteral;
      label333:
      i = m;
    }
  }
  
  private AstNode arrowFunction(AstNode paramAstNode)
    throws IOException
  {
    int j = this.ts.lineno;
    int i;
    FunctionNode localFunctionNode;
    Object localObject1;
    Object localObject2;
    PerFunctionVariables localPerFunctionVariables;
    if (paramAstNode != null)
    {
      i = paramAstNode.getPosition();
      localFunctionNode = new FunctionNode(i);
      localFunctionNode.setFunctionType(4);
      localFunctionNode.setJsDocNode(getAndResetJsDoc());
      localObject1 = new HashMap();
      localObject2 = new HashSet();
      localPerFunctionVariables = new PerFunctionVariables(localFunctionNode);
    }
    for (;;)
    {
      try
      {
        if (!(paramAstNode instanceof ParenthesizedExpression)) {
          break label221;
        }
        localFunctionNode.setParens(0, paramAstNode.getLength());
        paramAstNode = ((ParenthesizedExpression)paramAstNode).getExpression();
        if (!(paramAstNode instanceof EmptyExpression)) {
          arrowFunctionParams(localFunctionNode, paramAstNode, (Map)localObject1, (Set)localObject2);
        }
        if (((Map)localObject1).isEmpty()) {
          break label243;
        }
        paramAstNode = new Node(90);
        localObject1 = ((Map)localObject1).entrySet().iterator();
        if (!((Iterator)localObject1).hasNext()) {
          break label235;
        }
        localObject2 = (Map.Entry)((Iterator)localObject1).next();
        paramAstNode.addChildToBack(createDestructuringAssignment(123, (Node)((Map.Entry)localObject2).getValue(), createName((String)((Map.Entry)localObject2).getKey())));
        continue;
        i = -1;
      }
      finally
      {
        localPerFunctionVariables.restore();
      }
      break;
      label221:
      arrowFunctionParams(localFunctionNode, paramAstNode, (Map)localObject1, (Set)localObject2);
    }
    label235:
    localFunctionNode.putProp(23, paramAstNode);
    label243:
    localFunctionNode.setBody(parseFunctionBody(4, localFunctionNode));
    localFunctionNode.setEncodedSourceBounds(i, this.ts.tokenEnd);
    localFunctionNode.setLength(this.ts.tokenEnd - i);
    localPerFunctionVariables.restore();
    if (localFunctionNode.isGenerator())
    {
      reportError("msg.arrowfunction.generator");
      return makeErrorNode();
    }
    localFunctionNode.setSourceName(this.sourceURI);
    localFunctionNode.setBaseLineno(j);
    localFunctionNode.setEndLineno(this.ts.lineno);
    return localFunctionNode;
  }
  
  private void arrowFunctionParams(FunctionNode paramFunctionNode, AstNode paramAstNode, Map<String, Node> paramMap, Set<String> paramSet)
  {
    if (((paramAstNode instanceof ArrayLiteral)) || ((paramAstNode instanceof ObjectLiteral)))
    {
      markDestructuring(paramAstNode);
      paramFunctionNode.addParam(paramAstNode);
      paramFunctionNode = this.currentScriptOrFn.getNextTempName();
      defineSymbol(88, paramFunctionNode, false);
      paramMap.put(paramFunctionNode, paramAstNode);
    }
    do
    {
      return;
      if (((paramAstNode instanceof InfixExpression)) && (paramAstNode.getType() == 90))
      {
        arrowFunctionParams(paramFunctionNode, ((InfixExpression)paramAstNode).getLeft(), paramMap, paramSet);
        arrowFunctionParams(paramFunctionNode, ((InfixExpression)paramAstNode).getRight(), paramMap, paramSet);
        return;
      }
      if (!(paramAstNode instanceof Name)) {
        break;
      }
      paramFunctionNode.addParam(paramAstNode);
      paramFunctionNode = ((Name)paramAstNode).getIdentifier();
      defineSymbol(88, paramFunctionNode);
    } while (!this.inUseStrictDirective);
    if (("eval".equals(paramFunctionNode)) || ("arguments".equals(paramFunctionNode))) {
      reportError("msg.bad.id.strict", paramFunctionNode);
    }
    if (paramSet.contains(paramFunctionNode)) {
      addError("msg.dup.param.strict", paramFunctionNode);
    }
    paramSet.add(paramFunctionNode);
    return;
    reportError("msg.no.parm", paramAstNode.getPosition(), paramAstNode.getLength());
    paramFunctionNode.addParam(makeErrorNode());
  }
  
  private AstNode assignExpr()
    throws IOException
  {
    int j = 1;
    int i = peekToken();
    if (i == 73)
    {
      localObject = returnOrYield(i, true);
      return (AstNode)localObject;
    }
    Object localObject = condExpr();
    i = peekTokenOrEOL();
    if (i == 1) {
      i = peekToken();
    }
    for (;;)
    {
      if ((91 <= i) && (i <= 102))
      {
        consumeToken();
        Comment localComment = getAndResetJsDoc();
        markDestructuring((AstNode)localObject);
        j = this.ts.tokenBeg;
        Assignment localAssignment = new Assignment(i, (AstNode)localObject, assignExpr(), j);
        localObject = localAssignment;
        if (localComment == null) {
          break;
        }
        localAssignment.setJsDocNode(localComment);
        return localAssignment;
      }
      if (i == 83)
      {
        if (this.currentJsDocComment != null)
        {
          ((AstNode)localObject).setJsDocNode(getAndResetJsDoc());
          return (AstNode)localObject;
        }
      }
      else if ((j == 0) && (i == 165))
      {
        consumeToken();
        return arrowFunction((AstNode)localObject);
      }
      return (AstNode)localObject;
      j = 0;
    }
  }
  
  private AstNode attributeAccess()
    throws IOException
  {
    int i = nextToken();
    int j = this.ts.tokenBeg;
    switch (i)
    {
    default: 
      reportError("msg.no.name.after.xmlAttr");
      return makeErrorNode();
    case 39: 
      return propertyName(j, this.ts.getString(), 0);
    case 23: 
      saveNameTokenData(this.ts.tokenBeg, "*", this.ts.lineno);
      return propertyName(j, "*", 0);
    }
    return xmlElemRef(j, null, -1);
  }
  
  private void autoInsertSemicolon(AstNode paramAstNode)
    throws IOException
  {
    int i = peekFlaggedToken();
    int j = paramAstNode.getPosition();
    switch (0xFFFF & i)
    {
    default: 
      if ((i & 0x10000) == 0)
      {
        reportError("msg.no.semi.stmt");
        return;
      }
      break;
    case 83: 
      consumeToken();
      paramAstNode.setLength(this.ts.tokenEnd - j);
      return;
    case -1: 
    case 0: 
    case 87: 
      warnMissingSemi(j, nodeEnd(paramAstNode));
      return;
    }
    warnMissingSemi(j, nodeEnd(paramAstNode));
  }
  
  private AstNode bitAndExpr()
    throws IOException
  {
    int i;
    for (Object localObject = eqExpr(); matchToken(11); localObject = new InfixExpression(11, (AstNode)localObject, eqExpr(), i)) {
      i = this.ts.tokenBeg;
    }
    return (AstNode)localObject;
  }
  
  private AstNode bitOrExpr()
    throws IOException
  {
    int i;
    for (Object localObject = bitXorExpr(); matchToken(9); localObject = new InfixExpression(9, (AstNode)localObject, bitXorExpr(), i)) {
      i = this.ts.tokenBeg;
    }
    return (AstNode)localObject;
  }
  
  private AstNode bitXorExpr()
    throws IOException
  {
    int i;
    for (Object localObject = bitAndExpr(); matchToken(10); localObject = new InfixExpression(10, (AstNode)localObject, bitAndExpr(), i)) {
      i = this.ts.tokenBeg;
    }
    return (AstNode)localObject;
  }
  
  private AstNode block()
    throws IOException
  {
    if (this.currentToken != 86) {
      codeBug();
    }
    consumeToken();
    int i = this.ts.tokenBeg;
    Scope localScope = new Scope(i);
    localScope.setLineno(this.ts.lineno);
    pushScope(localScope);
    try
    {
      statements(localScope);
      mustMatchToken(87, "msg.no.brace.block");
      localScope.setLength(this.ts.tokenEnd - i);
      return localScope;
    }
    finally
    {
      popScope();
    }
  }
  
  private BreakStatement breakStatement()
    throws IOException
  {
    if (this.currentToken != 121) {
      codeBug();
    }
    consumeToken();
    int j = this.ts.lineno;
    int k = this.ts.tokenBeg;
    int i = this.ts.tokenEnd;
    Name localName;
    if (peekTokenOrEOL() == 39)
    {
      localName = createNameNode();
      i = getNodeEnd(localName);
    }
    for (;;)
    {
      Object localObject1 = matchJumpLabelName();
      if (localObject1 == null)
      {
        localObject1 = null;
        localObject2 = localObject1;
        if (localObject1 == null)
        {
          localObject2 = localObject1;
          if (localName == null)
          {
            if ((this.loopAndSwitchSet != null) && (this.loopAndSwitchSet.size() != 0)) {
              break label190;
            }
            localObject2 = localObject1;
            if (localName == null) {
              reportError("msg.bad.break", k, i - k);
            }
          }
        }
      }
      label190:
      for (Object localObject2 = localObject1;; localObject2 = (Jump)this.loopAndSwitchSet.get(this.loopAndSwitchSet.size() - 1))
      {
        localObject1 = new BreakStatement(k, i - k);
        ((BreakStatement)localObject1).setBreakLabel(localName);
        if (localObject2 != null) {
          ((BreakStatement)localObject1).setBreakTarget((Jump)localObject2);
        }
        ((BreakStatement)localObject1).setLineno(j);
        return (BreakStatement)localObject1;
        localObject1 = ((LabeledStatement)localObject1).getFirstLabel();
        break;
      }
      localName = null;
    }
  }
  
  private void checkBadIncDec(UnaryExpression paramUnaryExpression)
  {
    int i = removeParens(paramUnaryExpression.getOperand()).getType();
    if ((i != 39) && (i != 33) && (i != 36) && (i != 68) && (i != 38)) {
      if (paramUnaryExpression.getType() != 107) {
        break label61;
      }
    }
    label61:
    for (paramUnaryExpression = "msg.bad.incr";; paramUnaryExpression = "msg.bad.decr")
    {
      reportError(paramUnaryExpression);
      return;
    }
  }
  
  private void checkCallRequiresActivation(AstNode paramAstNode)
  {
    if (((paramAstNode.getType() == 39) && ("eval".equals(((Name)paramAstNode).getIdentifier()))) || ((paramAstNode.getType() == 33) && ("eval".equals(((PropertyGet)paramAstNode).getProperty().getIdentifier())))) {
      setRequiresActivation();
    }
  }
  
  private RuntimeException codeBug()
    throws RuntimeException
  {
    throw Kit.codeBug("ts.cursor=" + this.ts.cursor + ", ts.tokenBeg=" + this.ts.tokenBeg + ", currentToken=" + this.currentToken);
  }
  
  private AstNode condExpr()
    throws IOException
  {
    AstNode localAstNode1 = orExpr();
    if (matchToken(103))
    {
      int j = this.ts.lineno;
      int k = this.ts.tokenBeg;
      int i = -1;
      boolean bool = this.inForInit;
      this.inForInit = false;
      try
      {
        AstNode localAstNode3 = assignExpr();
        this.inForInit = bool;
        if (mustMatchToken(104, "msg.no.colon.cond")) {
          i = this.ts.tokenBeg;
        }
        AstNode localAstNode4 = assignExpr();
        int m = localAstNode1.getPosition();
        ConditionalExpression localConditionalExpression = new ConditionalExpression(m, getNodeEnd(localAstNode4) - m);
        localConditionalExpression.setLineno(j);
        localConditionalExpression.setTestExpression(localAstNode1);
        localConditionalExpression.setTrueExpression(localAstNode3);
        localConditionalExpression.setFalseExpression(localAstNode4);
        localConditionalExpression.setQuestionMarkPosition(k - m);
        localConditionalExpression.setColonPosition(i - m);
        return localConditionalExpression;
      }
      finally
      {
        this.inForInit = bool;
      }
    }
    return localAstNode2;
  }
  
  private ConditionData condition()
    throws IOException
  {
    ConditionData localConditionData = new ConditionData(null);
    if (mustMatchToken(88, "msg.no.paren.cond")) {
      localConditionData.lp = this.ts.tokenBeg;
    }
    localConditionData.condition = expr();
    if (mustMatchToken(89, "msg.no.paren.after.cond")) {
      localConditionData.rp = this.ts.tokenBeg;
    }
    if ((localConditionData.condition instanceof Assignment)) {
      addStrictWarning("msg.equal.as.assign", "", localConditionData.condition.getPosition(), localConditionData.condition.getLength());
    }
    return localConditionData;
  }
  
  private void consumeToken()
  {
    this.currentFlaggedToken = 0;
  }
  
  private ContinueStatement continueStatement()
    throws IOException
  {
    Loop localLoop = null;
    if (this.currentToken != 122) {
      codeBug();
    }
    consumeToken();
    int j = this.ts.lineno;
    int k = this.ts.tokenBeg;
    int i = this.ts.tokenEnd;
    Name localName;
    if (peekTokenOrEOL() == 39)
    {
      localName = createNameNode();
      i = getNodeEnd(localName);
    }
    for (;;)
    {
      Object localObject = matchJumpLabelName();
      if ((localObject == null) && (localName == null)) {
        if ((this.loopSet == null) || (this.loopSet.size() == 0)) {
          reportError("msg.continue.outside");
        }
      }
      for (;;)
      {
        localObject = new ContinueStatement(k, i - k);
        if (localLoop != null) {
          ((ContinueStatement)localObject).setTarget(localLoop);
        }
        ((ContinueStatement)localObject).setLabel(localName);
        ((ContinueStatement)localObject).setLineno(j);
        return (ContinueStatement)localObject;
        localLoop = (Loop)this.loopSet.get(this.loopSet.size() - 1);
        continue;
        if ((localObject == null) || (!(((LabeledStatement)localObject).getStatement() instanceof Loop))) {
          reportError("msg.continue.nonloop", k, i - k);
        }
        if (localObject != null) {
          localLoop = (Loop)((LabeledStatement)localObject).getStatement();
        }
      }
      localName = null;
    }
  }
  
  private Name createNameNode()
  {
    return createNameNode(false, 39);
  }
  
  private Name createNameNode(boolean paramBoolean, int paramInt)
  {
    int j = this.ts.tokenBeg;
    Object localObject1 = this.ts.getString();
    int i = this.ts.lineno;
    if (!"".equals(this.prevNameTokenString))
    {
      j = this.prevNameTokenStart;
      localObject1 = this.prevNameTokenString;
      i = this.prevNameTokenLineno;
      this.prevNameTokenStart = 0;
      this.prevNameTokenString = "";
      this.prevNameTokenLineno = 0;
    }
    Object localObject2 = localObject1;
    localObject1 = localObject2;
    if (localObject2 == null) {
      if (!this.compilerEnv.isIdeMode()) {
        break label131;
      }
    }
    for (localObject1 = "";; localObject1 = localObject2)
    {
      localObject2 = new Name(j, (String)localObject1);
      ((Name)localObject2).setLineno(i);
      if (paramBoolean) {
        checkActivationName((String)localObject1, paramInt);
      }
      return (Name)localObject2;
      label131:
      codeBug();
    }
  }
  
  private StringLiteral createStringLiteral()
  {
    int i = this.ts.tokenBeg;
    StringLiteral localStringLiteral = new StringLiteral(i, this.ts.tokenEnd - i);
    localStringLiteral.setLineno(this.ts.lineno);
    localStringLiteral.setValue(this.ts.getString());
    localStringLiteral.setQuoteCharacter(this.ts.getQuoteChar());
    return localStringLiteral;
  }
  
  private AstNode defaultXmlNamespace()
    throws IOException
  {
    if (this.currentToken != 117) {
      codeBug();
    }
    consumeToken();
    mustHaveXML();
    setRequiresActivation();
    int i = this.ts.lineno;
    int j = this.ts.tokenBeg;
    if ((!matchToken(39)) || (!"xml".equals(this.ts.getString()))) {
      reportError("msg.bad.namespace");
    }
    if ((!matchToken(39)) || (!"namespace".equals(this.ts.getString()))) {
      reportError("msg.bad.namespace");
    }
    if (!matchToken(91)) {
      reportError("msg.bad.namespace");
    }
    AstNode localAstNode = expr();
    UnaryExpression localUnaryExpression = new UnaryExpression(j, getNodeEnd(localAstNode) - j);
    localUnaryExpression.setOperator(75);
    localUnaryExpression.setOperand(localAstNode);
    localUnaryExpression.setLineno(i);
    return new ExpressionStatement(localUnaryExpression, true);
  }
  
  private AstNode destructuringPrimaryExpr()
    throws IOException, Parser.ParserException
  {
    try
    {
      this.inDestructuringAssignment = true;
      AstNode localAstNode = primaryExpr();
      return localAstNode;
    }
    finally
    {
      this.inDestructuringAssignment = false;
    }
  }
  
  private DoLoop doLoop()
    throws IOException
  {
    if (this.currentToken != 119) {
      codeBug();
    }
    consumeToken();
    int j = this.ts.tokenBeg;
    DoLoop localDoLoop = new DoLoop(j);
    localDoLoop.setLineno(this.ts.lineno);
    enterLoop(localDoLoop);
    try
    {
      AstNode localAstNode = statement();
      mustMatchToken(118, "msg.no.while.do");
      localDoLoop.setWhilePosition(this.ts.tokenBeg - j);
      ConditionData localConditionData = condition();
      localDoLoop.setCondition(localConditionData.condition);
      localDoLoop.setParens(localConditionData.lp - j, localConditionData.rp - j);
      int i = getNodeEnd(localAstNode);
      localDoLoop.setBody(localAstNode);
      exitLoop();
      if (matchToken(83)) {
        i = this.ts.tokenEnd;
      }
      localDoLoop.setLength(i - j);
      return localDoLoop;
    }
    finally
    {
      exitLoop();
    }
  }
  
  private void enterLoop(Loop paramLoop)
  {
    if (this.loopSet == null) {
      this.loopSet = new ArrayList();
    }
    this.loopSet.add(paramLoop);
    if (this.loopAndSwitchSet == null) {
      this.loopAndSwitchSet = new ArrayList();
    }
    this.loopAndSwitchSet.add(paramLoop);
    pushScope(paramLoop);
    if (this.currentLabel != null)
    {
      this.currentLabel.setStatement(paramLoop);
      this.currentLabel.getFirstLabel().setLoop(paramLoop);
      paramLoop.setRelative(-this.currentLabel.getPosition());
    }
  }
  
  private void enterSwitch(SwitchStatement paramSwitchStatement)
  {
    if (this.loopAndSwitchSet == null) {
      this.loopAndSwitchSet = new ArrayList();
    }
    this.loopAndSwitchSet.add(paramSwitchStatement);
  }
  
  private AstNode eqExpr()
    throws IOException
  {
    Object localObject = relExpr();
    int i = peekToken();
    int j = this.ts.tokenBeg;
    switch (i)
    {
    default: 
      return (AstNode)localObject;
    }
    consumeToken();
    if (this.compilerEnv.getLanguageVersion() == 120) {
      if (i == 12) {
        i = 46;
      }
    }
    for (;;)
    {
      localObject = new InfixExpression(i, (AstNode)localObject, relExpr(), j);
      break;
      if (i == 13) {
        i = 47;
      }
    }
  }
  
  private void exitLoop()
  {
    Loop localLoop = (Loop)this.loopSet.remove(this.loopSet.size() - 1);
    this.loopAndSwitchSet.remove(this.loopAndSwitchSet.size() - 1);
    if (localLoop.getParent() != null) {
      localLoop.setRelative(localLoop.getParent().getPosition());
    }
    popScope();
  }
  
  private void exitSwitch()
  {
    this.loopAndSwitchSet.remove(this.loopAndSwitchSet.size() - 1);
  }
  
  private AstNode expr()
    throws IOException
  {
    Object localObject = assignExpr();
    int i = ((AstNode)localObject).getPosition();
    while (matchToken(90))
    {
      int j = this.ts.tokenBeg;
      if ((this.compilerEnv.isStrictMode()) && (!((AstNode)localObject).hasSideEffects())) {
        addStrictWarning("msg.no.side.effects", "", i, nodeEnd((AstNode)localObject) - i);
      }
      if (peekToken() == 73) {
        reportError("msg.yield.parenthesized");
      }
      localObject = new InfixExpression(90, (AstNode)localObject, assignExpr(), j);
    }
    return (AstNode)localObject;
  }
  
  private Loop forLoop()
    throws IOException
  {
    if (this.currentToken != 120) {
      codeBug();
    }
    consumeToken();
    int i1 = this.ts.tokenBeg;
    int i2 = this.ts.lineno;
    Object localObject4 = null;
    Scope localScope = new Scope();
    pushScope(localScope);
    for (;;)
    {
      int k;
      boolean bool2;
      int m;
      int j;
      boolean bool1;
      int n;
      try
      {
        if (!matchToken(39)) {
          break label671;
        }
        if ("each".equals(this.ts.getString()))
        {
          k = this.ts.tokenBeg - i1;
          bool2 = true;
          if (!mustMatchToken(88, "msg.no.paren.for")) {
            break label653;
          }
          m = this.ts.tokenBeg - i1;
          localAstNode = forLoopInit(peekToken());
          if (!matchToken(52)) {
            continue;
          }
          i = this.ts.tokenBeg;
          localObject1 = expr();
          j = 1;
          bool1 = false;
          i -= i1;
          if (!mustMatchToken(89, "msg.no.paren.for.ctrl")) {
            break label647;
          }
          n = this.ts.tokenBeg - i1;
          break label659;
          localObject4 = new ForInLoop(i1);
          if (((localAstNode instanceof VariableDeclaration)) && (((VariableDeclaration)localAstNode).getVariables().size() > 1)) {
            reportError("msg.mult.index");
          }
          if ((bool1) && (bool2)) {
            reportError("msg.invalid.for.each");
          }
          ((ForInLoop)localObject4).setIterator(localAstNode);
          ((ForInLoop)localObject4).setIteratedObject((AstNode)localObject1);
          ((ForInLoop)localObject4).setInPosition(i);
          ((ForInLoop)localObject4).setIsForEach(bool2);
          ((ForInLoop)localObject4).setEachPosition(k);
          ((ForInLoop)localObject4).setIsForOf(bool1);
          localObject1 = localObject4;
          this.currentScope.replaceWith((Scope)localObject1);
          popScope();
          enterLoop((Loop)localObject1);
        }
      }
      finally
      {
        AstNode localAstNode;
        int i;
        Object localObject1;
        ForLoop localForLoop;
        if (this.currentScope == localScope) {
          popScope();
        }
      }
      try
      {
        localObject4 = statement();
        ((Loop)localObject1).setLength(getNodeEnd((AstNode)localObject4) - i1);
        ((Loop)localObject1).setBody((AstNode)localObject4);
        exitLoop();
        if (this.currentScope == localScope) {
          popScope();
        }
        ((Loop)localObject1).setParens(m, n);
        ((Loop)localObject1).setLineno(i2);
        return (Loop)localObject1;
      }
      finally
      {
        exitLoop();
      }
      reportError("msg.no.paren.for");
      if ((this.compilerEnv.getLanguageVersion() >= 200) && (matchToken(39)) && ("of".equals(this.ts.getString())))
      {
        i = this.ts.tokenBeg;
        localObject1 = expr();
        j = 0;
        bool1 = true;
        i -= i1;
      }
      else
      {
        mustMatchToken(83, "msg.no.semi.for");
        if (peekToken() == 83)
        {
          localObject1 = new EmptyExpression(this.ts.tokenBeg, 1);
          ((AstNode)localObject1).setLineno(this.ts.lineno);
          mustMatchToken(83, "msg.no.semi.for.cond");
          i = this.ts.tokenEnd;
          if (peekToken() == 89)
          {
            localObject4 = new EmptyExpression(i, 1);
            ((AstNode)localObject4).setLineno(this.ts.lineno);
            j = 0;
            bool1 = false;
            i = -1;
          }
        }
        else
        {
          localObject1 = expr();
          continue;
        }
        localObject4 = expr();
        j = 0;
        bool1 = false;
        i = -1;
        continue;
        localForLoop = new ForLoop(i1);
        localForLoop.setInitializer(localAstNode);
        localForLoop.setCondition((AstNode)localObject1);
        localForLoop.setIncrement((AstNode)localObject4);
        localObject1 = localForLoop;
        continue;
        label647:
        n = -1;
        break label659;
        label653:
        m = -1;
        continue;
        label659:
        if (j == 0) {
          if (bool1)
          {
            continue;
            label671:
            k = -1;
            bool2 = false;
          }
        }
      }
    }
  }
  
  /* Error */
  private AstNode forLoopInit(int paramInt)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: iconst_1
    //   2: putfield 134	org/mozilla/javascript/Parser:inForInit	Z
    //   5: iload_1
    //   6: bipush 83
    //   8: if_icmpne +37 -> 45
    //   11: new 388	org/mozilla/javascript/ast/EmptyExpression
    //   14: dup
    //   15: aload_0
    //   16: getfield 168	org/mozilla/javascript/Parser:ts	Lorg/mozilla/javascript/TokenStream;
    //   19: getfield 173	org/mozilla/javascript/TokenStream:tokenBeg	I
    //   22: iconst_1
    //   23: invokespecial 389	org/mozilla/javascript/ast/EmptyExpression:<init>	(II)V
    //   26: astore_2
    //   27: aload_2
    //   28: aload_0
    //   29: getfield 168	org/mozilla/javascript/Parser:ts	Lorg/mozilla/javascript/TokenStream;
    //   32: getfield 430	org/mozilla/javascript/TokenStream:lineno	I
    //   35: invokevirtual 964	org/mozilla/javascript/ast/AstNode:setLineno	(I)V
    //   38: aload_0
    //   39: iconst_0
    //   40: putfield 134	org/mozilla/javascript/Parser:inForInit	Z
    //   43: aload_2
    //   44: areturn
    //   45: iload_1
    //   46: bipush 123
    //   48: if_icmpeq +10 -> 58
    //   51: iload_1
    //   52: sipush 154
    //   55: if_icmpne +24 -> 79
    //   58: aload_0
    //   59: invokespecial 176	org/mozilla/javascript/Parser:consumeToken	()V
    //   62: aload_0
    //   63: iload_1
    //   64: aload_0
    //   65: getfield 168	org/mozilla/javascript/Parser:ts	Lorg/mozilla/javascript/TokenStream;
    //   68: getfield 173	org/mozilla/javascript/TokenStream:tokenBeg	I
    //   71: iconst_0
    //   72: invokespecial 980	org/mozilla/javascript/Parser:variables	(IIZ)Lorg/mozilla/javascript/ast/VariableDeclaration;
    //   75: astore_2
    //   76: goto -38 -> 38
    //   79: aload_0
    //   80: invokespecial 336	org/mozilla/javascript/Parser:expr	()Lorg/mozilla/javascript/ast/AstNode;
    //   83: astore_2
    //   84: aload_0
    //   85: aload_2
    //   86: invokevirtual 372	org/mozilla/javascript/Parser:markDestructuring	(Lorg/mozilla/javascript/ast/AstNode;)V
    //   89: goto -51 -> 38
    //   92: astore_2
    //   93: aload_0
    //   94: iconst_0
    //   95: putfield 134	org/mozilla/javascript/Parser:inForInit	Z
    //   98: aload_2
    //   99: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	100	0	this	Parser
    //   0	100	1	paramInt	int
    //   26	60	2	localObject1	Object
    //   92	7	2	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   0	5	92	finally
    //   11	38	92	finally
    //   58	76	92	finally
    //   79	89	92	finally
  }
  
  private FunctionNode function(int paramInt)
    throws IOException
  {
    Object localObject4 = null;
    String str = null;
    int k = this.ts.lineno;
    int m = this.ts.tokenBeg;
    Object localObject1;
    label129:
    int i;
    label146:
    int j;
    label153:
    FunctionNode localFunctionNode;
    PerFunctionVariables localPerFunctionVariables;
    if (matchToken(39))
    {
      localObject1 = createNameNode(true, 39);
      if (this.inUseStrictDirective)
      {
        localObject4 = ((Name)localObject1).getIdentifier();
        if (("eval".equals(localObject4)) || ("arguments".equals(localObject4))) {
          reportError("msg.bad.id.strict", (String)localObject4);
        }
      }
      if (matchToken(88)) {
        break label503;
      }
      if (!this.compilerEnv.isAllowMemberExprAsFunctionName()) {
        break label493;
      }
      localObject1 = memberExprTail(false, (AstNode)localObject1);
      localObject4 = str;
      mustMatchToken(88, "msg.no.paren.parms");
      if (this.currentToken != 88) {
        break label449;
      }
      i = this.ts.tokenBeg;
      if (localObject1 == null) {
        break label482;
      }
      j = 2;
      if ((j != 2) && (localObject4 != null) && (((Name)localObject4).length() > 0)) {
        defineSymbol(110, ((Name)localObject4).getIdentifier());
      }
      localFunctionNode = new FunctionNode(m, (Name)localObject4);
      localFunctionNode.setFunctionType(paramInt);
      if (i != -1) {
        localFunctionNode.setLp(i - m);
      }
      localFunctionNode.setJsDocNode(getAndResetJsDoc());
      localPerFunctionVariables = new PerFunctionVariables(localFunctionNode);
    }
    for (;;)
    {
      try
      {
        parseFunctionParams(localFunctionNode);
        localFunctionNode.setBody(parseFunctionBody(paramInt, localFunctionNode));
        localFunctionNode.setEncodedSourceBounds(m, this.ts.tokenEnd);
        localFunctionNode.setLength(this.ts.tokenEnd - m);
        if ((this.compilerEnv.isStrictMode()) && (!localFunctionNode.getBody().hasConsistentReturnUsage()))
        {
          if ((localObject4 != null) && (((Name)localObject4).length() > 0))
          {
            str = "msg.no.return.value";
            break label513;
            addStrictWarning(str, (String)localObject4);
          }
        }
        else
        {
          localPerFunctionVariables.restore();
          if (localObject1 != null)
          {
            Kit.codeBug();
            localFunctionNode.setMemberExprNode((AstNode)localObject1);
          }
          localFunctionNode.setSourceName(this.sourceURI);
          localFunctionNode.setBaseLineno(k);
          localFunctionNode.setEndLineno(this.ts.lineno);
          if (this.compilerEnv.isIdeMode()) {
            localFunctionNode.setParentScope(this.currentScope);
          }
          return localFunctionNode;
          if (matchToken(88))
          {
            localObject1 = null;
            break label129;
          }
          if (!this.compilerEnv.isAllowMemberExprAsFunctionName()) {
            break label487;
          }
          localObject1 = memberExpr(false);
          mustMatchToken(88, "msg.no.paren.parms");
          break label129;
          label449:
          i = -1;
          break label146;
        }
        str = "msg.anon.no.return.value";
      }
      finally
      {
        localPerFunctionVariables.restore();
      }
      localObject4 = ((Name)localObject4).getIdentifier();
      continue;
      label482:
      j = paramInt;
      break label153;
      label487:
      Object localObject3 = null;
      continue;
      label493:
      localObject4 = localObject3;
      localObject3 = null;
      break;
      label503:
      localObject4 = localObject3;
      localObject3 = null;
      break label129;
      label513:
      if (localObject4 == null) {
        localObject4 = "";
      }
    }
  }
  
  private AstNode generatorExpression(AstNode paramAstNode, int paramInt)
    throws IOException
  {
    return generatorExpression(paramAstNode, paramInt, false);
  }
  
  private AstNode generatorExpression(AstNode paramAstNode, int paramInt, boolean paramBoolean)
    throws IOException
  {
    ArrayList localArrayList = new ArrayList();
    while (peekToken() == 120) {
      localArrayList.add(generatorExpressionLoop());
    }
    int i = -1;
    ConditionData localConditionData = null;
    if (peekToken() == 113)
    {
      consumeToken();
      i = this.ts.tokenBeg - paramInt;
      localConditionData = condition();
    }
    if (!paramBoolean) {
      mustMatchToken(89, "msg.no.paren.let");
    }
    GeneratorExpression localGeneratorExpression = new GeneratorExpression(paramInt, this.ts.tokenEnd - paramInt);
    localGeneratorExpression.setResult(paramAstNode);
    localGeneratorExpression.setLoops(localArrayList);
    if (localConditionData != null)
    {
      localGeneratorExpression.setIfPosition(i);
      localGeneratorExpression.setFilter(localConditionData.condition);
      localGeneratorExpression.setFilterLp(localConditionData.lp - paramInt);
      localGeneratorExpression.setFilterRp(localConditionData.rp - paramInt);
    }
    return localGeneratorExpression;
  }
  
  private GeneratorExpressionLoop generatorExpressionLoop()
    throws IOException
  {
    int k = -1;
    if (nextToken() != 120) {
      codeBug();
    }
    int m = this.ts.tokenBeg;
    GeneratorExpressionLoop localGeneratorExpressionLoop = new GeneratorExpressionLoop(m);
    pushScope(localGeneratorExpressionLoop);
    for (;;)
    {
      try
      {
        if (!mustMatchToken(88, "msg.no.paren.for")) {
          break label282;
        }
        i = this.ts.tokenBeg - m;
        switch (peekToken())
        {
        case 84: 
          reportError("msg.bad.var");
          localObject1 = null;
          if (((AstNode)localObject1).getType() == 39) {
            defineSymbol(154, this.ts.getString(), true);
          }
          if (!mustMatchToken(52, "msg.in.after.for.name")) {
            break label277;
          }
          j = this.ts.tokenBeg - m;
          AstNode localAstNode = expr();
          if (mustMatchToken(89, "msg.no.paren.for.ctrl")) {
            k = this.ts.tokenBeg - m;
          }
          localGeneratorExpressionLoop.setLength(this.ts.tokenEnd - m);
          localGeneratorExpressionLoop.setIterator((AstNode)localObject1);
          localGeneratorExpressionLoop.setIteratedObject(localAstNode);
          localGeneratorExpressionLoop.setInPosition(j);
          localGeneratorExpressionLoop.setParens(i, k);
          return localGeneratorExpressionLoop;
        }
      }
      finally
      {
        Object localObject1;
        popScope();
      }
      localObject1 = destructuringPrimaryExpr();
      markDestructuring((AstNode)localObject1);
      continue;
      consumeToken();
      localObject1 = createNameNode();
      continue;
      label277:
      int j = -1;
      continue;
      label282:
      int i = -1;
    }
  }
  
  private Comment getAndResetJsDoc()
  {
    Comment localComment = this.currentJsDocComment;
    this.currentJsDocComment = null;
    return localComment;
  }
  
  private String getDirective(AstNode paramAstNode)
  {
    if ((paramAstNode instanceof ExpressionStatement))
    {
      paramAstNode = ((ExpressionStatement)paramAstNode).getExpression();
      if ((paramAstNode instanceof StringLiteral)) {
        return ((StringLiteral)paramAstNode).getValue();
      }
    }
    return null;
  }
  
  private int getNodeEnd(AstNode paramAstNode)
  {
    return paramAstNode.getPosition() + paramAstNode.getLength();
  }
  
  private int getNumberOfEols(String paramString)
  {
    int i = paramString.length();
    int j = 0;
    i -= 1;
    while (i >= 0)
    {
      int k = j;
      if (paramString.charAt(i) == '\n') {
        k = j + 1;
      }
      i -= 1;
      j = k;
    }
    return j;
  }
  
  private IfStatement ifStatement()
    throws IOException
  {
    if (this.currentToken != 113) {
      codeBug();
    }
    consumeToken();
    int j = this.ts.tokenBeg;
    int k = this.ts.lineno;
    ConditionData localConditionData = condition();
    AstNode localAstNode2 = statement();
    AstNode localAstNode1 = null;
    int i;
    if (matchToken(114))
    {
      i = this.ts.tokenBeg;
      localAstNode1 = statement();
      i -= j;
    }
    for (;;)
    {
      if (localAstNode1 != null) {}
      for (Object localObject = localAstNode1;; localObject = localAstNode2)
      {
        localObject = new IfStatement(j, getNodeEnd((AstNode)localObject) - j);
        ((IfStatement)localObject).setCondition(localConditionData.condition);
        ((IfStatement)localObject).setParens(localConditionData.lp - j, localConditionData.rp - j);
        ((IfStatement)localObject).setThenPart(localAstNode2);
        ((IfStatement)localObject).setElsePart(localAstNode1);
        ((IfStatement)localObject).setElsePosition(i);
        ((IfStatement)localObject).setLineno(k);
        return (IfStatement)localObject;
      }
      i = -1;
    }
  }
  
  /* Error */
  private AstNode let(boolean paramBoolean, int paramInt)
    throws IOException
  {
    // Byte code:
    //   0: new 1083	org/mozilla/javascript/ast/LetNode
    //   3: dup
    //   4: iload_2
    //   5: invokespecial 1084	org/mozilla/javascript/ast/LetNode:<init>	(I)V
    //   8: astore 4
    //   10: aload 4
    //   12: aload_0
    //   13: getfield 168	org/mozilla/javascript/Parser:ts	Lorg/mozilla/javascript/TokenStream;
    //   16: getfield 430	org/mozilla/javascript/TokenStream:lineno	I
    //   19: invokevirtual 1085	org/mozilla/javascript/ast/LetNode:setLineno	(I)V
    //   22: aload_0
    //   23: bipush 88
    //   25: ldc_w 1087
    //   28: invokespecial 240	org/mozilla/javascript/Parser:mustMatchToken	(ILjava/lang/String;)Z
    //   31: ifeq +17 -> 48
    //   34: aload 4
    //   36: aload_0
    //   37: getfield 168	org/mozilla/javascript/Parser:ts	Lorg/mozilla/javascript/TokenStream;
    //   40: getfield 173	org/mozilla/javascript/TokenStream:tokenBeg	I
    //   43: iload_2
    //   44: isub
    //   45: invokevirtual 1088	org/mozilla/javascript/ast/LetNode:setLp	(I)V
    //   48: aload_0
    //   49: aload 4
    //   51: invokevirtual 307	org/mozilla/javascript/Parser:pushScope	(Lorg/mozilla/javascript/ast/Scope;)V
    //   54: aload 4
    //   56: aload_0
    //   57: sipush 154
    //   60: aload_0
    //   61: getfield 168	org/mozilla/javascript/Parser:ts	Lorg/mozilla/javascript/TokenStream;
    //   64: getfield 173	org/mozilla/javascript/TokenStream:tokenBeg	I
    //   67: iload_1
    //   68: invokespecial 980	org/mozilla/javascript/Parser:variables	(IIZ)Lorg/mozilla/javascript/ast/VariableDeclaration;
    //   71: invokevirtual 1092	org/mozilla/javascript/ast/LetNode:setVariables	(Lorg/mozilla/javascript/ast/VariableDeclaration;)V
    //   74: aload_0
    //   75: bipush 89
    //   77: ldc_w 1033
    //   80: invokespecial 240	org/mozilla/javascript/Parser:mustMatchToken	(ILjava/lang/String;)Z
    //   83: ifeq +17 -> 100
    //   86: aload 4
    //   88: aload_0
    //   89: getfield 168	org/mozilla/javascript/Parser:ts	Lorg/mozilla/javascript/TokenStream;
    //   92: getfield 173	org/mozilla/javascript/TokenStream:tokenBeg	I
    //   95: iload_2
    //   96: isub
    //   97: invokevirtual 1095	org/mozilla/javascript/ast/LetNode:setRp	(I)V
    //   100: iload_1
    //   101: ifeq +91 -> 192
    //   104: aload_0
    //   105: invokespecial 166	org/mozilla/javascript/Parser:peekToken	()I
    //   108: bipush 86
    //   110: if_icmpne +82 -> 192
    //   113: aload_0
    //   114: invokespecial 176	org/mozilla/javascript/Parser:consumeToken	()V
    //   117: aload_0
    //   118: getfield 168	org/mozilla/javascript/Parser:ts	Lorg/mozilla/javascript/TokenStream;
    //   121: getfield 173	org/mozilla/javascript/TokenStream:tokenBeg	I
    //   124: istore_3
    //   125: aload_0
    //   126: invokespecial 1097	org/mozilla/javascript/Parser:statements	()Lorg/mozilla/javascript/ast/AstNode;
    //   129: astore 5
    //   131: aload_0
    //   132: bipush 87
    //   134: ldc_w 1099
    //   137: invokespecial 240	org/mozilla/javascript/Parser:mustMatchToken	(ILjava/lang/String;)Z
    //   140: pop
    //   141: aload 5
    //   143: aload_0
    //   144: getfield 168	org/mozilla/javascript/Parser:ts	Lorg/mozilla/javascript/TokenStream;
    //   147: getfield 259	org/mozilla/javascript/TokenStream:tokenEnd	I
    //   150: iload_3
    //   151: isub
    //   152: invokevirtual 637	org/mozilla/javascript/ast/AstNode:setLength	(I)V
    //   155: aload 4
    //   157: aload_0
    //   158: getfield 168	org/mozilla/javascript/Parser:ts	Lorg/mozilla/javascript/TokenStream;
    //   161: getfield 259	org/mozilla/javascript/TokenStream:tokenEnd	I
    //   164: iload_2
    //   165: isub
    //   166: invokevirtual 1100	org/mozilla/javascript/ast/LetNode:setLength	(I)V
    //   169: aload 4
    //   171: aload 5
    //   173: invokevirtual 1101	org/mozilla/javascript/ast/LetNode:setBody	(Lorg/mozilla/javascript/ast/AstNode;)V
    //   176: aload 4
    //   178: sipush 154
    //   181: invokevirtual 1105	org/mozilla/javascript/ast/LetNode:setType	(I)Lorg/mozilla/javascript/Node;
    //   184: pop
    //   185: aload_0
    //   186: invokevirtual 366	org/mozilla/javascript/Parser:popScope	()V
    //   189: aload 4
    //   191: areturn
    //   192: aload_0
    //   193: invokespecial 336	org/mozilla/javascript/Parser:expr	()Lorg/mozilla/javascript/ast/AstNode;
    //   196: astore 5
    //   198: aload 4
    //   200: aload_0
    //   201: aload 5
    //   203: invokespecial 671	org/mozilla/javascript/Parser:getNodeEnd	(Lorg/mozilla/javascript/ast/AstNode;)I
    //   206: iload_2
    //   207: isub
    //   208: invokevirtual 1100	org/mozilla/javascript/ast/LetNode:setLength	(I)V
    //   211: aload 4
    //   213: aload 5
    //   215: invokevirtual 1101	org/mozilla/javascript/ast/LetNode:setBody	(Lorg/mozilla/javascript/ast/AstNode;)V
    //   218: iload_1
    //   219: ifeq -34 -> 185
    //   222: aload_0
    //   223: invokevirtual 1108	org/mozilla/javascript/Parser:insideFunction	()Z
    //   226: ifne +34 -> 260
    //   229: iconst_1
    //   230: istore_1
    //   231: new 861	org/mozilla/javascript/ast/ExpressionStatement
    //   234: dup
    //   235: aload 4
    //   237: iload_1
    //   238: invokespecial 864	org/mozilla/javascript/ast/ExpressionStatement:<init>	(Lorg/mozilla/javascript/ast/AstNode;Z)V
    //   241: astore 5
    //   243: aload 5
    //   245: aload 4
    //   247: invokevirtual 1111	org/mozilla/javascript/ast/LetNode:getLineno	()I
    //   250: invokevirtual 1112	org/mozilla/javascript/ast/ExpressionStatement:setLineno	(I)V
    //   253: aload_0
    //   254: invokevirtual 366	org/mozilla/javascript/Parser:popScope	()V
    //   257: aload 5
    //   259: areturn
    //   260: iconst_0
    //   261: istore_1
    //   262: goto -31 -> 231
    //   265: astore 4
    //   267: aload_0
    //   268: invokevirtual 366	org/mozilla/javascript/Parser:popScope	()V
    //   271: aload 4
    //   273: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	274	0	this	Parser
    //   0	274	1	paramBoolean	boolean
    //   0	274	2	paramInt	int
    //   124	28	3	i	int
    //   8	238	4	localLetNode	org.mozilla.javascript.ast.LetNode
    //   265	7	4	localObject1	Object
    //   129	129	5	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   54	100	265	finally
    //   104	185	265	finally
    //   192	218	265	finally
    //   222	229	265	finally
    //   231	253	265	finally
  }
  
  private AstNode letStatement()
    throws IOException
  {
    if (this.currentToken != 154) {
      codeBug();
    }
    consumeToken();
    int i = this.ts.lineno;
    int j = this.ts.tokenBeg;
    if (peekToken() == 88) {}
    for (Object localObject = let(true, j);; localObject = variables(154, j, true))
    {
      ((AstNode)localObject).setLineno(i);
      return (AstNode)localObject;
    }
  }
  
  private int lineBeginningFor(int paramInt)
  {
    int j = 0;
    if (this.sourceChars == null) {
      i = -1;
    }
    do
    {
      return i;
      i = j;
    } while (paramInt <= 0);
    char[] arrayOfChar = this.sourceChars;
    int i = paramInt;
    if (paramInt >= arrayOfChar.length) {
      i = arrayOfChar.length - 1;
    }
    do
    {
      paramInt = i - 1;
      i = j;
      if (paramInt < 0) {
        break;
      }
      i = paramInt;
    } while (!ScriptRuntime.isJSLineTerminator(arrayOfChar[paramInt]));
    return paramInt + 1;
  }
  
  private ErrorNode makeErrorNode()
  {
    ErrorNode localErrorNode = new ErrorNode(this.ts.tokenBeg, this.ts.tokenEnd - this.ts.tokenBeg);
    localErrorNode.setLineno(this.ts.lineno);
    return localErrorNode;
  }
  
  private LabeledStatement matchJumpLabelName()
    throws IOException
  {
    Object localObject = null;
    LabeledStatement localLabeledStatement = null;
    if (peekTokenOrEOL() == 39)
    {
      consumeToken();
      if (this.labelSet != null) {
        localLabeledStatement = (LabeledStatement)this.labelSet.get(this.ts.getString());
      }
      localObject = localLabeledStatement;
      if (localLabeledStatement == null)
      {
        reportError("msg.undef.label");
        localObject = localLabeledStatement;
      }
    }
    return (LabeledStatement)localObject;
  }
  
  private boolean matchToken(int paramInt)
    throws IOException
  {
    if (peekToken() != paramInt) {
      return false;
    }
    consumeToken();
    return true;
  }
  
  private AstNode memberExpr(boolean paramBoolean)
    throws IOException
  {
    int i = peekToken();
    int j = this.ts.lineno;
    Object localObject1;
    if (i != 30) {
      localObject1 = primaryExpr();
    }
    for (;;)
    {
      ((AstNode)localObject1).setLineno(j);
      return memberExprTail(paramBoolean, (AstNode)localObject1);
      consumeToken();
      int k = this.ts.tokenBeg;
      localObject1 = new NewExpression(k);
      Object localObject2 = memberExpr(false);
      i = getNodeEnd((AstNode)localObject2);
      ((NewExpression)localObject1).setTarget((AstNode)localObject2);
      if (matchToken(88))
      {
        int m = this.ts.tokenBeg;
        localObject2 = argumentList();
        if ((localObject2 != null) && (((List)localObject2).size() > 65536)) {
          reportError("msg.too.many.constructor.args");
        }
        int n = this.ts.tokenBeg;
        i = this.ts.tokenEnd;
        if (localObject2 != null) {
          ((NewExpression)localObject1).setArguments((List)localObject2);
        }
        ((NewExpression)localObject1).setParens(m - k, n - k);
      }
      if (matchToken(86))
      {
        localObject2 = objectLiteral();
        i = getNodeEnd((AstNode)localObject2);
        ((NewExpression)localObject1).setInitializer((ObjectLiteral)localObject2);
      }
      ((NewExpression)localObject1).setLength(i - k);
    }
  }
  
  private AstNode memberExprTail(boolean paramBoolean, AstNode paramAstNode)
    throws IOException
  {
    if (paramAstNode == null) {
      codeBug();
    }
    int k = paramAstNode.getPosition();
    int i;
    label96:
    for (;;)
    {
      i = peekToken();
      switch (i)
      {
      default: 
        return paramAstNode;
      case 109: 
      case 144: 
        j = this.ts.lineno;
        paramAstNode = propertyAccess(i, paramAstNode);
        paramAstNode.setLineno(j);
      }
    }
    consumeToken();
    int m = this.ts.tokenBeg;
    int n = this.ts.lineno;
    mustHaveXML();
    setRequiresActivation();
    AstNode localAstNode = expr();
    int j = getNodeEnd(localAstNode);
    if (mustMatchToken(89, "msg.no.paren"))
    {
      i = this.ts.tokenBeg;
      j = this.ts.tokenEnd;
    }
    for (;;)
    {
      Object localObject = new XmlDotQuery(k, j - k);
      ((XmlDotQuery)localObject).setLeft(paramAstNode);
      ((XmlDotQuery)localObject).setRight(localAstNode);
      ((XmlDotQuery)localObject).setOperatorPosition(m);
      ((XmlDotQuery)localObject).setRp(i - k);
      ((XmlDotQuery)localObject).setLineno(n);
      paramAstNode = (AstNode)localObject;
      break label96;
      consumeToken();
      m = this.ts.tokenBeg;
      n = this.ts.lineno;
      localAstNode = expr();
      j = getNodeEnd(localAstNode);
      if (mustMatchToken(85, "msg.no.bracket.index"))
      {
        i = this.ts.tokenBeg;
        j = this.ts.tokenEnd;
      }
      for (;;)
      {
        localObject = new ElementGet(k, j - k);
        ((ElementGet)localObject).setTarget(paramAstNode);
        ((ElementGet)localObject).setElement(localAstNode);
        ((ElementGet)localObject).setParens(m, i);
        ((ElementGet)localObject).setLineno(n);
        paramAstNode = (AstNode)localObject;
        break label96;
        if (!paramBoolean) {
          break;
        }
        i = this.ts.lineno;
        consumeToken();
        checkCallRequiresActivation(paramAstNode);
        localObject = new FunctionCall(k);
        ((FunctionCall)localObject).setTarget(paramAstNode);
        ((FunctionCall)localObject).setLineno(i);
        ((FunctionCall)localObject).setLp(this.ts.tokenBeg - k);
        paramAstNode = argumentList();
        if ((paramAstNode != null) && (paramAstNode.size() > 65536)) {
          reportError("msg.too.many.function.args");
        }
        ((FunctionCall)localObject).setArguments(paramAstNode);
        ((FunctionCall)localObject).setRp(this.ts.tokenBeg - k);
        ((FunctionCall)localObject).setLength(this.ts.tokenEnd - k);
        paramAstNode = (AstNode)localObject;
        break label96;
        i = -1;
      }
      i = -1;
    }
  }
  
  private ObjectProperty methodDefinition(int paramInt1, AstNode paramAstNode, int paramInt2)
    throws IOException
  {
    FunctionNode localFunctionNode = function(2);
    Object localObject = localFunctionNode.getFunctionName();
    if ((localObject != null) && (((Name)localObject).length() != 0)) {
      reportError("msg.bad.prop");
    }
    localObject = new ObjectProperty(paramInt1);
    switch (paramInt2)
    {
    }
    for (;;)
    {
      paramInt2 = getNodeEnd(localFunctionNode);
      ((ObjectProperty)localObject).setLeft(paramAstNode);
      ((ObjectProperty)localObject).setRight(localFunctionNode);
      ((ObjectProperty)localObject).setLength(paramInt2 - paramInt1);
      return (ObjectProperty)localObject;
      ((ObjectProperty)localObject).setIsGetterMethod();
      localFunctionNode.setFunctionIsGetterMethod();
      continue;
      ((ObjectProperty)localObject).setIsSetterMethod();
      localFunctionNode.setFunctionIsSetterMethod();
      continue;
      ((ObjectProperty)localObject).setIsNormalMethod();
      localFunctionNode.setFunctionIsNormalMethod();
    }
  }
  
  private AstNode mulExpr()
    throws IOException
  {
    int i;
    int j;
    for (Object localObject = unaryExpr();; localObject = new InfixExpression(i, (AstNode)localObject, unaryExpr(), j))
    {
      i = peekToken();
      j = this.ts.tokenBeg;
      switch (i)
      {
      default: 
        return (AstNode)localObject;
      }
      consumeToken();
    }
  }
  
  private void mustHaveXML()
  {
    if (!this.compilerEnv.isXmlAvailable()) {
      reportError("msg.XML.not.available");
    }
  }
  
  private boolean mustMatchToken(int paramInt, String paramString)
    throws IOException
  {
    return mustMatchToken(paramInt, paramString, this.ts.tokenBeg, this.ts.tokenEnd - this.ts.tokenBeg);
  }
  
  private boolean mustMatchToken(int paramInt1, String paramString, int paramInt2, int paramInt3)
    throws IOException
  {
    if (matchToken(paramInt1)) {
      return true;
    }
    reportError(paramString, paramInt2, paramInt3);
    return false;
  }
  
  private AstNode name(int paramInt1, int paramInt2)
    throws IOException
  {
    String str = this.ts.getString();
    paramInt2 = this.ts.tokenBeg;
    int i = this.ts.lineno;
    if (((0x20000 & paramInt1) != 0) && (peekToken() == 104))
    {
      Label localLabel = new Label(paramInt2, this.ts.tokenEnd - paramInt2);
      localLabel.setName(str);
      localLabel.setLineno(this.ts.lineno);
      return localLabel;
    }
    saveNameTokenData(paramInt2, str, i);
    if (this.compilerEnv.isXmlAvailable()) {
      return propertyName(-1, str, 0);
    }
    return createNameNode(true, 39);
  }
  
  private AstNode nameOrLabel()
    throws IOException
  {
    boolean bool2 = true;
    boolean bool1 = true;
    if (this.currentToken != 39) {
      throw codeBug();
    }
    int i = this.ts.tokenBeg;
    this.currentFlaggedToken |= 0x20000;
    Object localObject1 = expr();
    if (((AstNode)localObject1).getType() != 131)
    {
      if (!insideFunction()) {}
      for (;;)
      {
        localObject3 = new ExpressionStatement((AstNode)localObject1, bool1);
        ((AstNode)localObject3).lineno = ((AstNode)localObject1).lineno;
        return (AstNode)localObject3;
        bool1 = false;
      }
    }
    Object localObject3 = new LabeledStatement(i);
    recordLabel((Label)localObject1, (LabeledStatement)localObject3);
    ((LabeledStatement)localObject3).setLineno(this.ts.lineno);
    if (peekToken() == 39)
    {
      this.currentFlaggedToken |= 0x20000;
      localObject1 = expr();
      if (((AstNode)localObject1).getType() != 131) {
        if (!insideFunction())
        {
          bool1 = bool2;
          label170:
          localObject1 = new ExpressionStatement((AstNode)localObject1, bool1);
          autoInsertSemicolon((AstNode)localObject1);
        }
      }
    }
    for (;;)
    {
      try
      {
        this.currentLabel = ((LabeledStatement)localObject3);
        if (localObject1 != null) {
          break label386;
        }
        localObject1 = statementHelper();
        this.currentLabel = null;
        localObject4 = ((LabeledStatement)localObject3).getLabels().iterator();
        if (!((Iterator)localObject4).hasNext()) {
          break label343;
        }
        Label localLabel = (Label)((Iterator)localObject4).next();
        this.labelSet.remove(localLabel.getName());
        continue;
        bool1 = false;
        break label170;
      }
      finally
      {
        this.currentLabel = null;
        localObject3 = ((LabeledStatement)localObject3).getLabels().iterator();
        if (!((Iterator)localObject3).hasNext()) {
          continue;
        }
        Object localObject4 = (Label)((Iterator)localObject3).next();
        this.labelSet.remove(((Label)localObject4).getName());
        continue;
      }
      recordLabel((Label)localObject1, (LabeledStatement)localObject3);
      break;
      label343:
      if (localAstNode.getParent() == null) {}
      for (i = getNodeEnd(localAstNode) - i;; i = getNodeEnd(localAstNode))
      {
        ((LabeledStatement)localObject3).setLength(i);
        ((LabeledStatement)localObject3).setStatement(localAstNode);
        return (AstNode)localObject3;
      }
      label386:
      continue;
      Object localObject2 = null;
    }
  }
  
  private int nextFlaggedToken()
    throws IOException
  {
    peekToken();
    int i = this.currentFlaggedToken;
    consumeToken();
    return i;
  }
  
  private int nextToken()
    throws IOException
  {
    int i = peekToken();
    consumeToken();
    return i;
  }
  
  private int nodeEnd(AstNode paramAstNode)
  {
    return paramAstNode.getPosition() + paramAstNode.getLength();
  }
  
  private static final boolean nowAllSet(int paramInt1, int paramInt2, int paramInt3)
  {
    return ((paramInt1 & paramInt3) != paramInt3) && ((paramInt2 & paramInt3) == paramInt3);
  }
  
  private ObjectLiteral objectLiteral()
    throws IOException
  {
    int j = this.ts.tokenBeg;
    int k = this.ts.lineno;
    int i = -1;
    ArrayList localArrayList = new ArrayList();
    HashSet localHashSet2 = null;
    HashSet localHashSet1 = null;
    if (this.inUseStrictDirective)
    {
      localHashSet2 = new HashSet();
      localHashSet1 = new HashSet();
    }
    Comment localComment1 = getAndResetJsDoc();
    int n;
    Comment localComment2;
    Object localObject;
    for (;;)
    {
      n = peekToken();
      localComment2 = getAndResetJsDoc();
      if (n == 87) {
        if (i != -1) {
          warnTrailingComma(j, localArrayList, i);
        }
      }
      label236:
      do
      {
        mustMatchToken(87, "msg.no.brace.prop");
        localObject = new ObjectLiteral(j, this.ts.tokenEnd - j);
        if (localComment1 != null) {
          ((ObjectLiteral)localObject).setJsDocNode(localComment1);
        }
        ((ObjectLiteral)localObject).setElements(localArrayList);
        ((ObjectLiteral)localObject).setLineno(k);
        return (ObjectLiteral)localObject;
        localObject = objliteralProperty();
        if (localObject != null) {
          break;
        }
        reportError("msg.bad.prop");
        localObject = null;
        i = 1;
        if ((this.inUseStrictDirective) && (localObject != null)) {}
        switch (i)
        {
        case 3: 
        case 5: 
        case 6: 
        case 7: 
        default: 
          getAndResetJsDoc();
        }
      } while (!matchToken(90));
      i = this.ts.tokenEnd;
    }
    String str = this.ts.getString();
    int m = this.ts.tokenBeg;
    consumeToken();
    i = peekToken();
    if ((i != 90) && (i != 104) && (i != 87)) {
      if (i == 88) {
        i = 8;
      }
    }
    for (;;)
    {
      if ((i == 2) || (i == 4))
      {
        localObject = objliteralProperty();
        if (localObject == null) {
          reportError("msg.bad.prop");
        }
        consumeToken();
      }
      if (localObject == null)
      {
        localObject = null;
        break;
        if (((AstNode)localObject).getType() != 39) {
          break label600;
        }
        if ("get".equals(str))
        {
          i = 2;
          continue;
        }
        if (!"set".equals(str)) {
          break label600;
        }
        i = 4;
        continue;
      }
      str = this.ts.getString();
      ObjectProperty localObjectProperty = methodDefinition(m, (AstNode)localObject, i);
      ((AstNode)localObject).setJsDocNode(localComment2);
      localArrayList.add(localObjectProperty);
      localObject = str;
      break;
      ((AstNode)localObject).setJsDocNode(localComment2);
      localArrayList.add(plainProperty((AstNode)localObject, n));
      localObject = str;
      i = 1;
      break;
      if ((localHashSet2.contains(localObject)) || (localHashSet1.contains(localObject))) {
        addError("msg.dup.obj.lit.prop.strict", (String)localObject);
      }
      localHashSet2.add(localObject);
      localHashSet1.add(localObject);
      break label236;
      if (localHashSet2.contains(localObject)) {
        addError("msg.dup.obj.lit.prop.strict", (String)localObject);
      }
      localHashSet2.add(localObject);
      break label236;
      if (localHashSet1.contains(localObject)) {
        addError("msg.dup.obj.lit.prop.strict", (String)localObject);
      }
      localHashSet1.add(localObject);
      break label236;
      label600:
      i = 1;
    }
  }
  
  private AstNode objliteralProperty()
    throws IOException
  {
    switch (peekToken())
    {
    default: 
      if ((this.compilerEnv.isReservedKeywordAsIdentifier()) && (TokenStream.isKeyword(this.ts.getString(), this.compilerEnv.getLanguageVersion(), this.inUseStrictDirective))) {
        return createNameNode();
      }
      break;
    case 39: 
      return createNameNode();
    case 41: 
      return createStringLiteral();
    case 40: 
      return new NumberLiteral(this.ts.tokenBeg, this.ts.getString(), this.ts.getNumber());
    }
    return null;
  }
  
  private AstNode orExpr()
    throws IOException
  {
    AstNode localAstNode = andExpr();
    if (matchToken(105))
    {
      int i = this.ts.tokenBeg;
      return new InfixExpression(105, localAstNode, orExpr(), i);
    }
    return localAstNode;
  }
  
  private AstNode parenExpr()
    throws IOException
  {
    boolean bool = this.inForInit;
    this.inForInit = false;
    try
    {
      Comment localComment = getAndResetJsDoc();
      int i = this.ts.lineno;
      int j = this.ts.tokenBeg;
      AstNode localAstNode = expr();
      if (peekToken() == 120)
      {
        localObject1 = generatorExpression(localAstNode, j);
        return (AstNode)localObject1;
      }
      ParenthesizedExpression localParenthesizedExpression = new ParenthesizedExpression(localAstNode);
      Object localObject1 = localComment;
      if (localComment == null) {
        localObject1 = getAndResetJsDoc();
      }
      if (localObject1 != null) {
        localParenthesizedExpression.setJsDocNode((Comment)localObject1);
      }
      mustMatchToken(89, "msg.no.paren");
      if ((localAstNode.getType() == 129) && (peekToken() != 165))
      {
        reportError("msg.syntax");
        localObject1 = makeErrorNode();
        return (AstNode)localObject1;
      }
      localParenthesizedExpression.setLength(this.ts.tokenEnd - localParenthesizedExpression.getPosition());
      localParenthesizedExpression.setLineno(i);
      return localParenthesizedExpression;
    }
    finally
    {
      this.inForInit = bool;
    }
  }
  
  /* Error */
  private AstRoot parse()
    throws IOException
  {
    // Byte code:
    //   0: new 1332	org/mozilla/javascript/ast/AstRoot
    //   3: dup
    //   4: iconst_0
    //   5: invokespecial 1333	org/mozilla/javascript/ast/AstRoot:<init>	(I)V
    //   8: astore 9
    //   10: aload_0
    //   11: aload 9
    //   13: putfield 547	org/mozilla/javascript/Parser:currentScriptOrFn	Lorg/mozilla/javascript/ast/ScriptNode;
    //   16: aload_0
    //   17: aload 9
    //   19: putfield 954	org/mozilla/javascript/Parser:currentScope	Lorg/mozilla/javascript/ast/Scope;
    //   22: aload_0
    //   23: getfield 168	org/mozilla/javascript/Parser:ts	Lorg/mozilla/javascript/TokenStream;
    //   26: getfield 430	org/mozilla/javascript/TokenStream:lineno	I
    //   29: istore 5
    //   31: aload_0
    //   32: getfield 572	org/mozilla/javascript/Parser:inUseStrictDirective	Z
    //   35: istore 6
    //   37: aload_0
    //   38: aload_0
    //   39: getfield 1335	org/mozilla/javascript/Parser:defaultUseStrictDirective	Z
    //   42: putfield 572	org/mozilla/javascript/Parser:inUseStrictDirective	Z
    //   45: aload_0
    //   46: getfield 572	org/mozilla/javascript/Parser:inUseStrictDirective	Z
    //   49: ifeq +9 -> 58
    //   52: aload 9
    //   54: iconst_1
    //   55: invokevirtual 1338	org/mozilla/javascript/ast/AstRoot:setInStrictMode	(Z)V
    //   58: iconst_1
    //   59: istore_3
    //   60: iconst_0
    //   61: istore_1
    //   62: iload_1
    //   63: istore_2
    //   64: aload_0
    //   65: invokespecial 166	org/mozilla/javascript/Parser:peekToken	()I
    //   68: istore 4
    //   70: iload 4
    //   72: ifgt +62 -> 134
    //   75: aload_0
    //   76: iload 6
    //   78: putfield 572	org/mozilla/javascript/Parser:inUseStrictDirective	Z
    //   81: aload_0
    //   82: getfield 140	org/mozilla/javascript/Parser:syntaxErrorCount	I
    //   85: ifeq +277 -> 362
    //   88: aload_0
    //   89: ldc_w 1340
    //   92: aload_0
    //   93: getfield 140	org/mozilla/javascript/Parser:syntaxErrorCount	I
    //   96: invokestatic 1344	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   99: invokevirtual 144	org/mozilla/javascript/Parser:lookupMessage	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   102: astore 7
    //   104: aload_0
    //   105: getfield 102	org/mozilla/javascript/Parser:compilerEnv	Lorg/mozilla/javascript/CompilerEnvirons;
    //   108: invokevirtual 817	org/mozilla/javascript/CompilerEnvirons:isIdeMode	()Z
    //   111: ifne +251 -> 362
    //   114: aload_0
    //   115: getfield 104	org/mozilla/javascript/Parser:errorReporter	Lorg/mozilla/javascript/ErrorReporter;
    //   118: aload 7
    //   120: aload_0
    //   121: getfield 146	org/mozilla/javascript/Parser:sourceURI	Ljava/lang/String;
    //   124: iload 5
    //   126: aconst_null
    //   127: iconst_0
    //   128: invokeinterface 1348 6 0
    //   133: athrow
    //   134: iload 4
    //   136: bipush 110
    //   138: if_icmpne +124 -> 262
    //   141: iload_1
    //   142: istore_2
    //   143: aload_0
    //   144: invokespecial 176	org/mozilla/javascript/Parser:consumeToken	()V
    //   147: iload_1
    //   148: istore_2
    //   149: aload_0
    //   150: getfield 1350	org/mozilla/javascript/Parser:calledByCompileFunction	Z
    //   153: ifeq +103 -> 256
    //   156: iconst_2
    //   157: istore 4
    //   159: iload_1
    //   160: istore_2
    //   161: aload_0
    //   162: iload 4
    //   164: invokespecial 1202	org/mozilla/javascript/Parser:function	(I)Lorg/mozilla/javascript/ast/FunctionNode;
    //   167: astore 7
    //   169: iload_3
    //   170: istore 4
    //   172: iload_1
    //   173: istore_2
    //   174: aload_0
    //   175: aload 7
    //   177: invokespecial 671	org/mozilla/javascript/Parser:getNodeEnd	(Lorg/mozilla/javascript/ast/AstNode;)I
    //   180: istore_1
    //   181: iload_1
    //   182: istore_2
    //   183: aload 9
    //   185: aload 7
    //   187: invokevirtual 1351	org/mozilla/javascript/ast/AstRoot:addChildToBack	(Lorg/mozilla/javascript/Node;)V
    //   190: iload_1
    //   191: istore_2
    //   192: aload 7
    //   194: aload 9
    //   196: invokevirtual 1354	org/mozilla/javascript/ast/AstNode:setParent	(Lorg/mozilla/javascript/ast/AstNode;)V
    //   199: iload 4
    //   201: istore_3
    //   202: goto -140 -> 62
    //   205: astore 7
    //   207: aload_0
    //   208: ldc_w 1356
    //   211: invokevirtual 1359	org/mozilla/javascript/Parser:lookupMessage	(Ljava/lang/String;)Ljava/lang/String;
    //   214: astore 7
    //   216: aload_0
    //   217: getfield 102	org/mozilla/javascript/Parser:compilerEnv	Lorg/mozilla/javascript/CompilerEnvirons;
    //   220: invokevirtual 817	org/mozilla/javascript/CompilerEnvirons:isIdeMode	()Z
    //   223: ifne +128 -> 351
    //   226: aload 7
    //   228: aload_0
    //   229: getfield 146	org/mozilla/javascript/Parser:sourceURI	Ljava/lang/String;
    //   232: aload_0
    //   233: getfield 168	org/mozilla/javascript/Parser:ts	Lorg/mozilla/javascript/TokenStream;
    //   236: getfield 430	org/mozilla/javascript/TokenStream:lineno	I
    //   239: aconst_null
    //   240: iconst_0
    //   241: invokestatic 1364	org/mozilla/javascript/Context:reportRuntimeError	(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;I)Lorg/mozilla/javascript/EvaluatorException;
    //   244: athrow
    //   245: astore 7
    //   247: aload_0
    //   248: iload 6
    //   250: putfield 572	org/mozilla/javascript/Parser:inUseStrictDirective	Z
    //   253: aload 7
    //   255: athrow
    //   256: iconst_1
    //   257: istore 4
    //   259: goto -100 -> 159
    //   262: iload_1
    //   263: istore_2
    //   264: aload_0
    //   265: invokespecial 881	org/mozilla/javascript/Parser:statement	()Lorg/mozilla/javascript/ast/AstNode;
    //   268: astore 8
    //   270: aload 8
    //   272: astore 7
    //   274: iload_3
    //   275: istore 4
    //   277: iload_3
    //   278: ifeq -106 -> 172
    //   281: iload_1
    //   282: istore_2
    //   283: aload_0
    //   284: aload 8
    //   286: invokespecial 1366	org/mozilla/javascript/Parser:getDirective	(Lorg/mozilla/javascript/ast/AstNode;)Ljava/lang/String;
    //   289: astore 10
    //   291: aload 10
    //   293: ifnonnull +13 -> 306
    //   296: iconst_0
    //   297: istore 4
    //   299: aload 8
    //   301: astore 7
    //   303: goto -131 -> 172
    //   306: aload 8
    //   308: astore 7
    //   310: iload_3
    //   311: istore 4
    //   313: iload_1
    //   314: istore_2
    //   315: aload 10
    //   317: ldc_w 1368
    //   320: invokevirtual 318	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   323: ifeq -151 -> 172
    //   326: iload_1
    //   327: istore_2
    //   328: aload_0
    //   329: iconst_1
    //   330: putfield 572	org/mozilla/javascript/Parser:inUseStrictDirective	Z
    //   333: iload_1
    //   334: istore_2
    //   335: aload 9
    //   337: iconst_1
    //   338: invokevirtual 1338	org/mozilla/javascript/ast/AstRoot:setInStrictMode	(Z)V
    //   341: aload 8
    //   343: astore 7
    //   345: iload_3
    //   346: istore 4
    //   348: goto -176 -> 172
    //   351: aload_0
    //   352: iload 6
    //   354: putfield 572	org/mozilla/javascript/Parser:inUseStrictDirective	Z
    //   357: iload_2
    //   358: istore_1
    //   359: goto -278 -> 81
    //   362: iload_1
    //   363: istore_2
    //   364: aload_0
    //   365: getfield 1370	org/mozilla/javascript/Parser:scannedComments	Ljava/util/List;
    //   368: ifnull +78 -> 446
    //   371: aload_0
    //   372: getfield 1370	org/mozilla/javascript/Parser:scannedComments	Ljava/util/List;
    //   375: invokeinterface 392 1 0
    //   380: istore_2
    //   381: iload_1
    //   382: aload_0
    //   383: aload_0
    //   384: getfield 1370	org/mozilla/javascript/Parser:scannedComments	Ljava/util/List;
    //   387: iload_2
    //   388: iconst_1
    //   389: isub
    //   390: invokeinterface 422 2 0
    //   395: checkcast 324	org/mozilla/javascript/ast/AstNode
    //   398: invokespecial 671	org/mozilla/javascript/Parser:getNodeEnd	(Lorg/mozilla/javascript/ast/AstNode;)I
    //   401: invokestatic 1376	java/lang/Math:max	(II)I
    //   404: istore_1
    //   405: aload_0
    //   406: getfield 1370	org/mozilla/javascript/Parser:scannedComments	Ljava/util/List;
    //   409: invokeinterface 406 1 0
    //   414: astore 7
    //   416: iload_1
    //   417: istore_2
    //   418: aload 7
    //   420: invokeinterface 411 1 0
    //   425: ifeq +21 -> 446
    //   428: aload 9
    //   430: aload 7
    //   432: invokeinterface 415 1 0
    //   437: checkcast 1378	org/mozilla/javascript/ast/Comment
    //   440: invokevirtual 1381	org/mozilla/javascript/ast/AstRoot:addComment	(Lorg/mozilla/javascript/ast/Comment;)V
    //   443: goto -27 -> 416
    //   446: aload 9
    //   448: iload_2
    //   449: iconst_0
    //   450: isub
    //   451: invokevirtual 1382	org/mozilla/javascript/ast/AstRoot:setLength	(I)V
    //   454: aload 9
    //   456: aload_0
    //   457: getfield 146	org/mozilla/javascript/Parser:sourceURI	Ljava/lang/String;
    //   460: invokevirtual 1383	org/mozilla/javascript/ast/AstRoot:setSourceName	(Ljava/lang/String;)V
    //   463: aload 9
    //   465: iload 5
    //   467: invokevirtual 1384	org/mozilla/javascript/ast/AstRoot:setBaseLineno	(I)V
    //   470: aload 9
    //   472: aload_0
    //   473: getfield 168	org/mozilla/javascript/Parser:ts	Lorg/mozilla/javascript/TokenStream;
    //   476: getfield 430	org/mozilla/javascript/TokenStream:lineno	I
    //   479: invokevirtual 1385	org/mozilla/javascript/ast/AstRoot:setEndLineno	(I)V
    //   482: aload 9
    //   484: areturn
    //   485: astore 7
    //   487: goto -412 -> 75
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	490	0	this	Parser
    //   61	356	1	i	int
    //   63	388	2	j	int
    //   59	287	3	k	int
    //   68	279	4	m	int
    //   29	437	5	n	int
    //   35	318	6	bool	boolean
    //   102	91	7	localObject1	Object
    //   205	1	7	localStackOverflowError	StackOverflowError
    //   214	13	7	str1	String
    //   245	9	7	localObject2	Object
    //   272	159	7	localObject3	Object
    //   485	1	7	localParserException	ParserException
    //   268	74	8	localAstNode	AstNode
    //   8	475	9	localAstRoot	AstRoot
    //   289	27	10	str2	String
    // Exception table:
    //   from	to	target	type
    //   64	70	205	java/lang/StackOverflowError
    //   143	147	205	java/lang/StackOverflowError
    //   149	156	205	java/lang/StackOverflowError
    //   161	169	205	java/lang/StackOverflowError
    //   174	181	205	java/lang/StackOverflowError
    //   183	190	205	java/lang/StackOverflowError
    //   192	199	205	java/lang/StackOverflowError
    //   264	270	205	java/lang/StackOverflowError
    //   283	291	205	java/lang/StackOverflowError
    //   315	326	205	java/lang/StackOverflowError
    //   328	333	205	java/lang/StackOverflowError
    //   335	341	205	java/lang/StackOverflowError
    //   64	70	245	finally
    //   143	147	245	finally
    //   149	156	245	finally
    //   161	169	245	finally
    //   174	181	245	finally
    //   183	190	245	finally
    //   192	199	245	finally
    //   207	245	245	finally
    //   264	270	245	finally
    //   283	291	245	finally
    //   315	326	245	finally
    //   328	333	245	finally
    //   335	341	245	finally
    //   149	156	485	org/mozilla/javascript/Parser$ParserException
    //   161	169	485	org/mozilla/javascript/Parser$ParserException
  }
  
  private AstNode parseFunctionBody(int paramInt, FunctionNode paramFunctionNode)
    throws IOException
  {
    int j = 1;
    int i;
    Block localBlock;
    boolean bool;
    if (!matchToken(86))
    {
      if ((this.compilerEnv.getLanguageVersion() < 180) && (paramInt != 4))
      {
        reportError("msg.no.brace.body");
        i = 0;
        if (paramInt != 4) {
          break label236;
        }
      }
      label236:
      for (paramInt = 1;; paramInt = 0)
      {
        this.nestingOfFunction += 1;
        int k = this.ts.tokenBeg;
        localBlock = new Block(k);
        bool = this.inUseStrictDirective;
        localBlock.setLineno(this.ts.lineno);
        if (i == 0) {
          break label241;
        }
        try
        {
          paramFunctionNode = new ReturnStatement(this.ts.lineno);
          paramFunctionNode.setReturnValue(assignExpr());
          paramFunctionNode.putProp(25, Boolean.TRUE);
          localBlock.putProp(25, Boolean.TRUE);
          if (paramInt != 0) {
            paramFunctionNode.putProp(27, Boolean.TRUE);
          }
          localBlock.addStatement(paramFunctionNode);
        }
        catch (ParserException paramFunctionNode)
        {
          for (;;)
          {
            this.nestingOfFunction -= 1;
            this.inUseStrictDirective = bool;
            continue;
            consumeToken();
            localObject = function(1);
            paramInt = j;
            continue;
            localObject = localAstNode;
            paramInt = j;
            if (str.equals("use strict"))
            {
              this.inUseStrictDirective = true;
              paramFunctionNode.setInStrictMode(true);
              localObject = localAstNode;
              paramInt = j;
              if (!bool)
              {
                setRequiresActivation();
                localObject = localAstNode;
                paramInt = j;
              }
            }
          }
        }
        finally
        {
          this.nestingOfFunction -= 1;
          this.inUseStrictDirective = bool;
        }
        j = this.ts.tokenEnd;
        getAndResetJsDoc();
        paramInt = j;
        if (i == 0)
        {
          paramInt = j;
          if (mustMatchToken(87, "msg.no.brace.after.body")) {
            paramInt = this.ts.tokenEnd;
          }
        }
        localBlock.setLength(paramInt - k);
        return localBlock;
        i = 1;
        break;
      }
      label241:
      switch (peekToken())
      {
      }
    }
    for (;;)
    {
      AstNode localAstNode = statement();
      Object localObject = localAstNode;
      paramInt = j;
      String str;
      if (j != 0)
      {
        str = getDirective(localAstNode);
        if (str == null)
        {
          paramInt = 0;
          localObject = localAstNode;
        }
      }
      else
      {
        localBlock.addStatement((AstNode)localObject);
        j = paramInt;
        break label241;
      }
      i = 0;
      break;
    }
  }
  
  private void parseFunctionParams(FunctionNode paramFunctionNode)
    throws IOException
  {
    if (matchToken(89)) {
      paramFunctionNode.setRp(this.ts.tokenBeg - paramFunctionNode.getPosition());
    }
    label351:
    do
    {
      return;
      Object localObject1 = null;
      Object localObject3 = new HashSet();
      int i = peekToken();
      Object localObject4;
      Object localObject2;
      if ((i == 84) || (i == 86))
      {
        localObject4 = destructuringPrimaryExpr();
        markDestructuring((AstNode)localObject4);
        paramFunctionNode.addParam((AstNode)localObject4);
        localObject2 = localObject1;
        if (localObject1 == null) {
          localObject2 = new HashMap();
        }
        localObject1 = this.currentScriptOrFn.getNextTempName();
        defineSymbol(88, (String)localObject1, false);
        ((Map)localObject2).put(localObject1, localObject4);
      }
      for (;;)
      {
        localObject1 = localObject2;
        if (matchToken(90)) {
          break;
        }
        if (localObject2 == null) {
          break label351;
        }
        localObject1 = new Node(90);
        localObject2 = ((Map)localObject2).entrySet().iterator();
        while (((Iterator)localObject2).hasNext())
        {
          localObject3 = (Map.Entry)((Iterator)localObject2).next();
          ((Node)localObject1).addChildToBack(createDestructuringAssignment(123, (Node)((Map.Entry)localObject3).getValue(), createName((String)((Map.Entry)localObject3).getKey())));
        }
        if (mustMatchToken(39, "msg.no.parm"))
        {
          paramFunctionNode.addParam(createNameNode());
          localObject4 = this.ts.getString();
          defineSymbol(88, (String)localObject4);
          localObject2 = localObject1;
          if (this.inUseStrictDirective)
          {
            if (("eval".equals(localObject4)) || ("arguments".equals(localObject4))) {
              reportError("msg.bad.id.strict", (String)localObject4);
            }
            if (((Set)localObject3).contains(localObject4)) {
              addError("msg.dup.param.strict", (String)localObject4);
            }
            ((Set)localObject3).add(localObject4);
            localObject2 = localObject1;
          }
        }
        else
        {
          paramFunctionNode.addParam(makeErrorNode());
          localObject2 = localObject1;
        }
      }
      paramFunctionNode.putProp(23, localObject1);
    } while (!mustMatchToken(89, "msg.no.paren.after.parms"));
    paramFunctionNode.setRp(this.ts.tokenBeg - paramFunctionNode.getPosition());
  }
  
  private int peekFlaggedToken()
    throws IOException
  {
    peekToken();
    return this.currentFlaggedToken;
  }
  
  private int peekToken()
    throws IOException
  {
    if (this.currentFlaggedToken != 0) {
      return this.currentToken;
    }
    int k = this.ts.getLineno();
    int i = this.ts.getToken();
    int j = 0;
    if ((i == 1) || (i == 162))
    {
      int m;
      if (i == 1)
      {
        i = k + 1;
        m = 1;
      }
      for (;;)
      {
        int n = this.ts.getToken();
        j = m;
        k = i;
        i = n;
        break;
        m = j;
        i = k;
        if (this.compilerEnv.isRecordingComments())
        {
          String str = this.ts.getAndResetCurrentComment();
          recordComment(k, str);
          i = k + getNumberOfEols(str);
          m = j;
        }
      }
    }
    this.currentToken = i;
    if (j != 0) {}
    for (j = 65536;; j = 0)
    {
      this.currentFlaggedToken = (j | i);
      return this.currentToken;
    }
  }
  
  private int peekTokenOrEOL()
    throws IOException
  {
    int i = peekToken();
    if ((this.currentFlaggedToken & 0x10000) != 0) {
      i = 1;
    }
    return i;
  }
  
  private ObjectProperty plainProperty(AstNode paramAstNode, int paramInt)
    throws IOException
  {
    int i = peekToken();
    if (((i == 90) || (i == 87)) && (paramInt == 39) && (this.compilerEnv.getLanguageVersion() >= 180))
    {
      if (!this.inDestructuringAssignment) {
        reportError("msg.bad.object.init");
      }
      localObject = new Name(paramAstNode.getPosition(), paramAstNode.getString());
      ObjectProperty localObjectProperty = new ObjectProperty();
      localObjectProperty.putProp(26, Boolean.TRUE);
      localObjectProperty.setLeftAndRight(paramAstNode, (AstNode)localObject);
      return localObjectProperty;
    }
    mustMatchToken(104, "msg.no.colon.prop");
    Object localObject = new ObjectProperty();
    ((ObjectProperty)localObject).setOperatorPosition(this.ts.tokenBeg);
    ((ObjectProperty)localObject).setLeftAndRight(paramAstNode, assignExpr());
    return (ObjectProperty)localObject;
  }
  
  private AstNode primaryExpr()
    throws IOException
  {
    int j = peekFlaggedToken();
    int i = 0xFFFF & j;
    switch (i)
    {
    default: 
      consumeToken();
      reportError("msg.syntax");
    }
    for (;;)
    {
      consumeToken();
      return makeErrorNode();
      consumeToken();
      return function(2);
      consumeToken();
      return arrayLiteral();
      consumeToken();
      return objectLiteral();
      consumeToken();
      return let(false, this.ts.tokenBeg);
      consumeToken();
      return parenExpr();
      consumeToken();
      mustHaveXML();
      return attributeAccess();
      consumeToken();
      return name(j, i);
      consumeToken();
      Object localObject2 = this.ts.getString();
      if ((this.inUseStrictDirective) && (this.ts.isNumberOldOctal())) {
        reportError("msg.no.old.octal.strict");
      }
      Object localObject1 = localObject2;
      if (this.ts.isNumberBinary()) {
        localObject1 = "0b" + (String)localObject2;
      }
      localObject2 = localObject1;
      if (this.ts.isNumberOldOctal()) {
        localObject2 = "0" + (String)localObject1;
      }
      localObject1 = localObject2;
      if (this.ts.isNumberOctal()) {
        localObject1 = "0o" + (String)localObject2;
      }
      localObject2 = localObject1;
      if (this.ts.isNumberHex()) {
        localObject2 = "0x" + (String)localObject1;
      }
      return new NumberLiteral(this.ts.tokenBeg, (String)localObject2, this.ts.getNumber());
      consumeToken();
      return createStringLiteral();
      consumeToken();
      this.ts.readRegExp(i);
      i = this.ts.tokenBeg;
      localObject1 = new RegExpLiteral(i, this.ts.tokenEnd - i);
      ((RegExpLiteral)localObject1).setValue(this.ts.getString());
      ((RegExpLiteral)localObject1).setFlags(this.ts.readAndClearRegExpFlags());
      return (AstNode)localObject1;
      consumeToken();
      j = this.ts.tokenBeg;
      return new KeywordLiteral(j, this.ts.tokenEnd - j, i);
      return new EmptyExpression();
      consumeToken();
      reportError("msg.reserved.id");
      continue;
      consumeToken();
      continue;
      consumeToken();
      reportError("msg.unexpected.eof");
    }
  }
  
  private AstNode propertyAccess(int paramInt, AstNode paramAstNode)
    throws IOException
  {
    if (paramAstNode == null) {
      codeBug();
    }
    int i = 0;
    int k = this.ts.lineno;
    int j = this.ts.tokenBeg;
    consumeToken();
    if (paramInt == 144)
    {
      mustHaveXML();
      i = 4;
    }
    if (!this.compilerEnv.isXmlAvailable())
    {
      if ((nextToken() != 39) && ((!this.compilerEnv.isReservedKeywordAsIdentifier()) || (!TokenStream.isKeyword(this.ts.getString(), this.compilerEnv.getLanguageVersion(), this.inUseStrictDirective)))) {
        reportError("msg.no.name.after.dot");
      }
      paramAstNode = new PropertyGet(paramAstNode, createNameNode(true, 33), j);
      paramAstNode.setLineno(k);
      return paramAstNode;
    }
    k = nextToken();
    Object localObject1;
    boolean bool;
    switch (k)
    {
    default: 
      if (this.compilerEnv.isReservedKeywordAsIdentifier())
      {
        localObject1 = Token.keywordToName(k);
        if (localObject1 != null)
        {
          saveNameTokenData(this.ts.tokenBeg, (String)localObject1, this.ts.lineno);
          localObject1 = propertyName(-1, (String)localObject1, i);
          bool = localObject1 instanceof XmlRef;
          if (!bool) {
            break label493;
          }
        }
      }
      break;
    }
    label493:
    for (Object localObject2 = new XmlMemberGet();; localObject2 = new PropertyGet())
    {
      if ((bool) && (paramInt == 109)) {
        ((InfixExpression)localObject2).setType(109);
      }
      paramInt = paramAstNode.getPosition();
      ((InfixExpression)localObject2).setPosition(paramInt);
      ((InfixExpression)localObject2).setLength(getNodeEnd((AstNode)localObject1) - paramInt);
      ((InfixExpression)localObject2).setOperatorPosition(j - paramInt);
      ((InfixExpression)localObject2).setLineno(paramAstNode.getLineno());
      ((InfixExpression)localObject2).setLeft(paramAstNode);
      ((InfixExpression)localObject2).setRight((AstNode)localObject1);
      return (AstNode)localObject2;
      saveNameTokenData(this.ts.tokenBeg, "throw", this.ts.lineno);
      localObject1 = propertyName(-1, "throw", i);
      break;
      localObject1 = propertyName(-1, this.ts.getString(), i);
      break;
      saveNameTokenData(this.ts.tokenBeg, "*", this.ts.lineno);
      localObject1 = propertyName(-1, "*", i);
      break;
      localObject1 = attributeAccess();
      break;
      localObject1 = this.ts.getString();
      saveNameTokenData(this.ts.tokenBeg, (String)localObject1, this.ts.lineno);
      localObject1 = propertyName(-1, (String)localObject1, i);
      break;
      reportError("msg.no.name.after.dot");
      return makeErrorNode();
    }
  }
  
  private AstNode propertyName(int paramInt1, String paramString, int paramInt2)
    throws IOException
  {
    if (paramInt1 != -1) {}
    int k;
    int i;
    for (int j = paramInt1;; j = this.ts.tokenBeg)
    {
      k = this.ts.lineno;
      paramString = createNameNode(true, this.currentToken);
      if (!matchToken(145)) {
        break;
      }
      i = this.ts.tokenBeg;
      switch (nextToken())
      {
      default: 
        reportError("msg.no.name.after.coloncolon");
        return makeErrorNode();
      }
    }
    Object localObject = createNameNode();
    String str = paramString;
    paramString = (String)localObject;
    for (;;)
    {
      if ((str == null) && (paramInt2 == 0) && (paramInt1 == -1))
      {
        return paramString;
        saveNameTokenData(this.ts.tokenBeg, "*", this.ts.lineno);
        localObject = createNameNode(false, -1);
        str = paramString;
        paramString = (String)localObject;
        continue;
        return xmlElemRef(paramInt1, paramString, i);
      }
      else
      {
        localObject = new XmlPropRef(j, getNodeEnd(paramString) - j);
        ((XmlPropRef)localObject).setAtPos(paramInt1);
        ((XmlPropRef)localObject).setNamespace(str);
        ((XmlPropRef)localObject).setColonPos(i);
        ((XmlPropRef)localObject).setPropName(paramString);
        ((XmlPropRef)localObject).setLineno(k);
        return (AstNode)localObject;
        str = null;
        i = -1;
      }
    }
  }
  
  private String readFully(Reader paramReader)
    throws IOException
  {
    paramReader = new BufferedReader(paramReader);
    try
    {
      char[] arrayOfChar = new char['?'];
      StringBuilder localStringBuilder = new StringBuilder(1024);
      for (;;)
      {
        int i = paramReader.read(arrayOfChar, 0, 1024);
        if (i == -1) {
          break;
        }
        localStringBuilder.append(arrayOfChar, 0, i);
      }
      str = localStringBuilder.toString();
    }
    finally
    {
      paramReader.close();
    }
    String str;
    paramReader.close();
    return str;
  }
  
  private void recordComment(int paramInt, String paramString)
  {
    if (this.scannedComments == null) {
      this.scannedComments = new ArrayList();
    }
    paramString = new Comment(this.ts.tokenBeg, this.ts.getTokenLength(), this.ts.commentType, paramString);
    if ((this.ts.commentType == Token.CommentType.JSDOC) && (this.compilerEnv.isRecordingLocalJsDocComments())) {
      this.currentJsDocComment = paramString;
    }
    paramString.setLineno(paramInt);
    this.scannedComments.add(paramString);
  }
  
  private void recordLabel(Label paramLabel, LabeledStatement paramLabeledStatement)
    throws IOException
  {
    if (peekToken() != 104) {
      codeBug();
    }
    consumeToken();
    String str = paramLabel.getName();
    if (this.labelSet == null) {
      this.labelSet = new HashMap();
    }
    for (;;)
    {
      paramLabeledStatement.addLabel(paramLabel);
      this.labelSet.put(str, paramLabeledStatement);
      return;
      Object localObject = (LabeledStatement)this.labelSet.get(str);
      if (localObject != null)
      {
        if (this.compilerEnv.isIdeMode())
        {
          localObject = ((LabeledStatement)localObject).getLabelByName(str);
          reportError("msg.dup.label", ((Label)localObject).getAbsolutePosition(), ((Label)localObject).getLength());
        }
        reportError("msg.dup.label", paramLabel.getPosition(), paramLabel.getLength());
      }
    }
  }
  
  private AstNode relExpr()
    throws IOException
  {
    int i;
    int j;
    for (Object localObject = shiftExpr();; localObject = new InfixExpression(i, (AstNode)localObject, shiftExpr(), j))
    {
      i = peekToken();
      j = this.ts.tokenBeg;
      switch (i)
      {
      default: 
      case 52: 
        do
        {
          return (AstNode)localObject;
        } while (this.inForInit);
      }
      consumeToken();
    }
  }
  
  private AstNode returnOrYield(int paramInt, boolean paramBoolean)
    throws IOException
  {
    int j = 4;
    Object localObject;
    int k;
    int m;
    int i;
    int n;
    if (!insideFunction())
    {
      if (paramInt == 4)
      {
        localObject = "msg.bad.return";
        reportError((String)localObject);
      }
    }
    else
    {
      consumeToken();
      k = this.ts.lineno;
      m = this.ts.tokenBeg;
      i = this.ts.tokenEnd;
      localObject = null;
      switch (peekTokenOrEOL())
      {
      default: 
        localObject = expr();
        i = getNodeEnd((AstNode)localObject);
      }
      n = this.endFlags;
      if (paramInt != 4) {
        break label303;
      }
      int i1 = this.endFlags;
      paramInt = j;
      if (localObject == null) {
        paramInt = 2;
      }
      this.endFlags = (paramInt | i1);
      localObject = new ReturnStatement(m, i - m, (AstNode)localObject);
      if (!nowAllSet(n, this.endFlags, 6)) {
        break label389;
      }
      addStrictWarning("msg.return.inconsistent", "", m, i - m);
    }
    label230:
    label303:
    label371:
    label386:
    label389:
    for (;;)
    {
      Name localName;
      if ((insideFunction()) && (nowAllSet(n, this.endFlags, 12)))
      {
        localName = ((FunctionNode)this.currentScriptOrFn).getFunctionName();
        if ((localName != null) && (localName.length() != 0)) {
          break label371;
        }
        addError("msg.anon.generator.returns", "");
      }
      for (;;)
      {
        ((AstNode)localObject).setLineno(k);
        return (AstNode)localObject;
        localObject = "msg.bad.yield";
        break;
        if (!insideFunction()) {
          reportError("msg.bad.yield");
        }
        this.endFlags |= 0x8;
        localObject = new Yield(m, i - m, (AstNode)localObject);
        setRequiresActivation();
        setIsGenerator();
        if (paramBoolean) {
          break label386;
        }
        localObject = new ExpressionStatement((AstNode)localObject);
        break label230;
        addError("msg.generator.returns", localName.getIdentifier());
      }
    }
  }
  
  private void saveNameTokenData(int paramInt1, String paramString, int paramInt2)
  {
    this.prevNameTokenStart = paramInt1;
    this.prevNameTokenString = paramString;
    this.prevNameTokenLineno = paramInt2;
  }
  
  private AstNode shiftExpr()
    throws IOException
  {
    int i;
    int j;
    for (Object localObject = addExpr();; localObject = new InfixExpression(i, (AstNode)localObject, addExpr(), j))
    {
      i = peekToken();
      j = this.ts.tokenBeg;
      switch (i)
      {
      default: 
        return (AstNode)localObject;
      }
      consumeToken();
    }
  }
  
  private AstNode statement()
    throws IOException
  {
    int i = this.ts.tokenBeg;
    AstNode localAstNode;
    for (;;)
    {
      try
      {
        localAstNode = statementHelper();
        if (localAstNode != null)
        {
          if ((!this.compilerEnv.isStrictMode()) || (localAstNode.hasSideEffects())) {
            break;
          }
          j = localAstNode.getPosition();
          j = Math.max(j, lineBeginningFor(j));
          if ((localAstNode instanceof EmptyStatement))
          {
            str = "msg.extra.trailing.semi";
            addStrictWarning(str, "", j, nodeEnd(localAstNode) - j);
            return localAstNode;
          }
          String str = "msg.no.side.effects";
          continue;
        }
      }
      catch (ParserException localParserException)
      {
        int j;
        continue;
      }
      j = peekTokenOrEOL();
      consumeToken();
      switch (j)
      {
      }
    }
    return new EmptyStatement(i, this.ts.tokenBeg - i);
    return localAstNode;
  }
  
  private AstNode statementHelper()
    throws IOException
  {
    boolean bool = true;
    if ((this.currentLabel != null) && (this.currentLabel.getStatement() != null)) {
      this.currentLabel = null;
    }
    int i = peekToken();
    int j = this.ts.tokenBeg;
    Object localObject;
    switch (i)
    {
    default: 
      i = this.ts.lineno;
      localObject = expr();
      if (insideFunction()) {
        break;
      }
    }
    for (;;)
    {
      localObject = new ExpressionStatement((AstNode)localObject, bool);
      ((AstNode)localObject).setLineno(i);
      for (;;)
      {
        autoInsertSemicolon((AstNode)localObject);
        AstNode localAstNode;
        do
        {
          do
          {
            do
            {
              return (AstNode)localObject;
              return ifStatement();
              return switchStatement();
              return whileLoop();
              return doLoop();
              return forLoop();
              return tryStatement();
              localObject = throwStatement();
              break;
              localObject = breakStatement();
              break;
              localObject = continueStatement();
              break;
              if (this.inUseStrictDirective) {
                reportError("msg.no.with.strict");
              }
              return withStatement();
              consumeToken();
              i = this.ts.lineno;
              localObject = variables(this.currentToken, this.ts.tokenBeg, true);
              ((AstNode)localObject).setLineno(i);
              break;
              localAstNode = letStatement();
              localObject = localAstNode;
            } while (!(localAstNode instanceof VariableDeclaration));
            localObject = localAstNode;
          } while (peekToken() != 83);
          localObject = localAstNode;
          break;
          localObject = returnOrYield(i, false);
          break;
          consumeToken();
          localObject = new KeywordLiteral(this.ts.tokenBeg, this.ts.tokenEnd - this.ts.tokenBeg, i);
          ((AstNode)localObject).setLineno(this.ts.lineno);
          break;
          return block();
          consumeToken();
          return makeErrorNode();
          consumeToken();
          i = this.ts.tokenBeg;
          localObject = new EmptyStatement(i, this.ts.tokenEnd - i);
          ((AstNode)localObject).setLineno(this.ts.lineno);
          return (AstNode)localObject;
          consumeToken();
          return function(3);
          localObject = defaultXmlNamespace();
          break;
          localAstNode = nameOrLabel();
          localObject = localAstNode;
        } while (!(localAstNode instanceof ExpressionStatement));
        localObject = localAstNode;
      }
      bool = false;
    }
  }
  
  private AstNode statements()
    throws IOException
  {
    return statements(null);
  }
  
  private AstNode statements(AstNode paramAstNode)
    throws IOException
  {
    if ((this.currentToken != 86) && (!this.compilerEnv.isIdeMode())) {
      codeBug();
    }
    int i = this.ts.tokenBeg;
    if (paramAstNode != null) {}
    for (;;)
    {
      paramAstNode.setLineno(this.ts.lineno);
      for (;;)
      {
        int j = peekToken();
        if ((j <= 0) || (j == 87)) {
          break;
        }
        paramAstNode.addChild(statement());
      }
      paramAstNode = new Block(i);
    }
    paramAstNode.setLength(this.ts.tokenBeg - i);
    return paramAstNode;
  }
  
  private SwitchStatement switchStatement()
    throws IOException
  {
    if (this.currentToken != 115) {
      codeBug();
    }
    consumeToken();
    int j = this.ts.tokenBeg;
    SwitchStatement localSwitchStatement = new SwitchStatement(j);
    if (mustMatchToken(88, "msg.no.paren.switch")) {
      localSwitchStatement.setLp(this.ts.tokenBeg - j);
    }
    localSwitchStatement.setLineno(this.ts.lineno);
    localSwitchStatement.setExpression(expr());
    enterSwitch(localSwitchStatement);
    for (;;)
    {
      int i;
      int k;
      int m;
      try
      {
        if (mustMatchToken(89, "msg.no.paren.after.switch")) {
          localSwitchStatement.setRp(this.ts.tokenBeg - j);
        }
        mustMatchToken(86, "msg.no.brace.switch");
        i = 0;
        int n = nextToken();
        k = this.ts.tokenBeg;
        m = this.ts.lineno;
        switch (n)
        {
        case 87: 
          reportError("msg.bad.switch");
          return localSwitchStatement;
        }
      }
      finally
      {
        exitSwitch();
      }
      localSwitchStatement.setLength(this.ts.tokenEnd - j);
      continue;
      AstNode localAstNode = expr();
      mustMatchToken(104, "msg.no.colon.case");
      SwitchCase localSwitchCase;
      for (;;)
      {
        localSwitchCase = new SwitchCase(k);
        localSwitchCase.setExpression(localAstNode);
        localSwitchCase.setLength(this.ts.tokenEnd - j);
        localSwitchCase.setLineno(m);
        for (;;)
        {
          k = peekToken();
          if ((k == 87) || (k == 116) || (k == 117) || (k == 0)) {
            break;
          }
          localSwitchCase.addStatement(statement());
        }
        if (i != 0) {
          reportError("msg.double.switch.default");
        }
        i = 1;
        localAstNode = null;
        mustMatchToken(104, "msg.no.colon.case");
      }
      localSwitchStatement.addCase(localSwitchCase);
    }
  }
  
  private ThrowStatement throwStatement()
    throws IOException
  {
    if (this.currentToken != 50) {
      codeBug();
    }
    consumeToken();
    int i = this.ts.tokenBeg;
    int j = this.ts.lineno;
    if (peekTokenOrEOL() == 1) {
      reportError("msg.bad.throw.eol");
    }
    Object localObject = expr();
    localObject = new ThrowStatement(i, getNodeEnd((AstNode)localObject), (AstNode)localObject);
    ((ThrowStatement)localObject).setLineno(j);
    return (ThrowStatement)localObject;
  }
  
  private TryStatement tryStatement()
    throws IOException
  {
    if (this.currentToken != 82) {
      codeBug();
    }
    consumeToken();
    Comment localComment = getAndResetJsDoc();
    int i1 = this.ts.tokenBeg;
    int i2 = this.ts.lineno;
    if (peekToken() != 86) {
      reportError("msg.no.brace.try");
    }
    AstNode localAstNode = statement();
    int i = getNodeEnd(localAstNode);
    Object localObject1 = null;
    Object localObject2 = null;
    int k = 0;
    int m = peekToken();
    int i4;
    int i3;
    if (m == 125)
    {
      j = i;
      localObject1 = localObject2;
      i = k;
      localObject2 = localObject1;
      if (!matchToken(125)) {
        break label453;
      }
      i4 = this.ts.lineno;
      if (i != 0) {
        reportError("msg.catch.unreachable");
      }
      i3 = this.ts.tokenBeg;
      m = -1;
      if (!mustMatchToken(88, "msg.no.paren.catch")) {
        break label572;
      }
    }
    label249:
    label453:
    label566:
    label572:
    for (int j = this.ts.tokenBeg;; j = -1)
    {
      mustMatchToken(39, "msg.bad.catchcond");
      Object localObject3 = createNameNode();
      localObject2 = ((Name)localObject3).getIdentifier();
      if ((this.inUseStrictDirective) && (("eval".equals(localObject2)) || ("arguments".equals(localObject2)))) {
        reportError("msg.bad.id.strict", (String)localObject2);
      }
      int n;
      CatchClause localCatchClause;
      if (matchToken(113))
      {
        k = this.ts.tokenBeg;
        localObject2 = expr();
        if (mustMatchToken(89, "msg.bad.catchcond")) {
          m = this.ts.tokenBeg;
        }
        mustMatchToken(86, "msg.no.brace.catchblock");
        Block localBlock = (Block)statements();
        n = getNodeEnd(localBlock);
        localCatchClause = new CatchClause(i3);
        localCatchClause.setVarName((Name)localObject3);
        localCatchClause.setCatchCondition((AstNode)localObject2);
        localCatchClause.setBody(localBlock);
        if (k != -1) {
          localCatchClause.setIfPosition(k - i3);
        }
        localCatchClause.setParens(j, m);
        localCatchClause.setLineno(i4);
        if (!mustMatchToken(87, "msg.no.brace.after.body")) {
          break label566;
        }
      }
      for (j = this.ts.tokenEnd;; j = n)
      {
        localCatchClause.setLength(j - i3);
        if (localObject1 == null) {
          localObject1 = new ArrayList();
        }
        for (;;)
        {
          ((List)localObject1).add(localCatchClause);
          break;
          k = -1;
          localObject2 = null;
          i = 1;
          break label249;
          localObject2 = localObject1;
          j = i;
          if (m != 126)
          {
            mustMatchToken(126, "msg.try.no.catchfinally");
            j = i;
            localObject2 = localObject1;
          }
          localObject1 = null;
          if (matchToken(126))
          {
            i = this.ts.tokenBeg;
            localObject1 = statement();
            j = getNodeEnd((AstNode)localObject1);
          }
          for (;;)
          {
            localObject3 = new TryStatement(i1, j - i1);
            ((TryStatement)localObject3).setTryBlock(localAstNode);
            ((TryStatement)localObject3).setCatchClauses((List)localObject2);
            ((TryStatement)localObject3).setFinallyBlock((AstNode)localObject1);
            if (i != -1) {
              ((TryStatement)localObject3).setFinallyPosition(i - i1);
            }
            ((TryStatement)localObject3).setLineno(i2);
            if (localComment != null) {
              ((TryStatement)localObject3).setJsDocNode(localComment);
            }
            return (TryStatement)localObject3;
            i = -1;
          }
        }
      }
    }
  }
  
  private AstNode unaryExpr()
    throws IOException
  {
    int j = peekToken();
    int i = this.ts.lineno;
    switch (j)
    {
    }
    do
    {
      localObject = memberExpr(true);
      j = peekTokenOrEOL();
      if ((j == 107) || (j == 108)) {
        break;
      }
      return (AstNode)localObject;
      consumeToken();
      localObject = new UnaryExpression(j, this.ts.tokenBeg, unaryExpr());
      ((AstNode)localObject).setLineno(i);
      return (AstNode)localObject;
      consumeToken();
      localObject = new UnaryExpression(28, this.ts.tokenBeg, unaryExpr());
      ((AstNode)localObject).setLineno(i);
      return (AstNode)localObject;
      consumeToken();
      localObject = new UnaryExpression(29, this.ts.tokenBeg, unaryExpr());
      ((AstNode)localObject).setLineno(i);
      return (AstNode)localObject;
      consumeToken();
      localObject = new UnaryExpression(j, this.ts.tokenBeg, memberExpr(true));
      ((UnaryExpression)localObject).setLineno(i);
      checkBadIncDec((UnaryExpression)localObject);
      return (AstNode)localObject;
      consumeToken();
      localObject = new UnaryExpression(j, this.ts.tokenBeg, unaryExpr());
      ((AstNode)localObject).setLineno(i);
      return (AstNode)localObject;
      consumeToken();
      return makeErrorNode();
    } while (!this.compilerEnv.isXmlAvailable());
    consumeToken();
    return memberExprTail(true, xmlInitializer());
    consumeToken();
    Object localObject = new UnaryExpression(j, this.ts.tokenBeg, (AstNode)localObject, true);
    ((UnaryExpression)localObject).setLineno(i);
    checkBadIncDec((UnaryExpression)localObject);
    return (AstNode)localObject;
  }
  
  private VariableDeclaration variables(int paramInt1, int paramInt2, boolean paramBoolean)
    throws IOException
  {
    VariableDeclaration localVariableDeclaration = new VariableDeclaration(paramInt2);
    localVariableDeclaration.setType(paramInt1);
    localVariableDeclaration.setLineno(this.ts.lineno);
    Object localObject1 = getAndResetJsDoc();
    if (localObject1 != null) {
      localVariableDeclaration.setJsDocNode((Comment)localObject1);
    }
    int k = peekToken();
    int j = this.ts.tokenBeg;
    int i = this.ts.tokenEnd;
    Object localObject2;
    label130:
    Comment localComment;
    AstNode localAstNode;
    if ((k == 84) || (k == 86))
    {
      localObject2 = destructuringPrimaryExpr();
      i = getNodeEnd((AstNode)localObject2);
      if (!(localObject2 instanceof DestructuringForm)) {
        reportError("msg.bad.assign.left", j, i - j);
      }
      markDestructuring((AstNode)localObject2);
      localObject1 = null;
      k = this.ts.lineno;
      localComment = getAndResetJsDoc();
      if (!matchToken(91)) {
        break label389;
      }
      localAstNode = assignExpr();
      i = getNodeEnd(localAstNode);
    }
    for (;;)
    {
      VariableInitializer localVariableInitializer = new VariableInitializer(j, i - j);
      if (localObject2 != null)
      {
        if ((localAstNode == null) && (!this.inForInit)) {
          reportError("msg.destruct.assign.no.init");
        }
        localVariableInitializer.setTarget((AstNode)localObject2);
      }
      for (;;)
      {
        localVariableInitializer.setInitializer(localAstNode);
        localVariableInitializer.setType(paramInt1);
        localVariableInitializer.setJsDocNode(localComment);
        localVariableInitializer.setLineno(k);
        localVariableDeclaration.addVariable(localVariableInitializer);
        if (matchToken(90)) {
          break;
        }
        localVariableDeclaration.setLength(i - paramInt2);
        localVariableDeclaration.setIsStatement(paramBoolean);
        return localVariableDeclaration;
        mustMatchToken(39, "msg.bad.var");
        localObject1 = createNameNode();
        ((Name)localObject1).setLineno(this.ts.getLineno());
        if (this.inUseStrictDirective)
        {
          localObject2 = this.ts.getString();
          if (("eval".equals(localObject2)) || ("arguments".equals(this.ts.getString()))) {
            reportError("msg.bad.id.strict", (String)localObject2);
          }
        }
        defineSymbol(paramInt1, this.ts.getString(), this.inForInit);
        localObject2 = null;
        break label130;
        localVariableInitializer.setTarget((AstNode)localObject1);
      }
      label389:
      localAstNode = null;
    }
  }
  
  private void warnMissingSemi(int paramInt1, int paramInt2)
  {
    int[] arrayOfInt;
    String str;
    if (this.compilerEnv.isStrictMode())
    {
      arrayOfInt = new int[2];
      str = this.ts.getLine(paramInt2, arrayOfInt);
      if (!this.compilerEnv.isIdeMode()) {
        break label72;
      }
      paramInt1 = Math.max(paramInt1, paramInt2 - arrayOfInt[1]);
    }
    label72:
    while (str != null)
    {
      addStrictWarning("msg.missing.semi", "", paramInt1, paramInt2 - paramInt1, arrayOfInt[0], str, arrayOfInt[1]);
      return;
    }
    addStrictWarning("msg.missing.semi", "", paramInt1, paramInt2 - paramInt1);
  }
  
  private void warnTrailingComma(int paramInt1, List<?> paramList, int paramInt2)
  {
    if (this.compilerEnv.getWarnTrailingComma())
    {
      if (!paramList.isEmpty()) {
        paramInt1 = ((AstNode)paramList.get(0)).getPosition();
      }
      paramInt1 = Math.max(paramInt1, lineBeginningFor(paramInt2));
      addWarning("msg.extra.trailing.comma", paramInt1, paramInt2 - paramInt1);
    }
  }
  
  private WhileLoop whileLoop()
    throws IOException
  {
    if (this.currentToken != 118) {
      codeBug();
    }
    consumeToken();
    int i = this.ts.tokenBeg;
    WhileLoop localWhileLoop = new WhileLoop(i);
    localWhileLoop.setLineno(this.ts.lineno);
    enterLoop(localWhileLoop);
    try
    {
      Object localObject2 = condition();
      localWhileLoop.setCondition(((ConditionData)localObject2).condition);
      localWhileLoop.setParens(((ConditionData)localObject2).lp - i, ((ConditionData)localObject2).rp - i);
      localObject2 = statement();
      localWhileLoop.setLength(getNodeEnd((AstNode)localObject2) - i);
      localWhileLoop.setBody((AstNode)localObject2);
      return localWhileLoop;
    }
    finally
    {
      exitLoop();
    }
  }
  
  private WithStatement withStatement()
    throws IOException
  {
    int j = -1;
    if (this.currentToken != 124) {
      codeBug();
    }
    consumeToken();
    Comment localComment = getAndResetJsDoc();
    int k = this.ts.lineno;
    int m = this.ts.tokenBeg;
    if (mustMatchToken(88, "msg.no.paren.with")) {}
    for (int i = this.ts.tokenBeg;; i = -1)
    {
      AstNode localAstNode1 = expr();
      if (mustMatchToken(89, "msg.no.paren.after.with")) {
        j = this.ts.tokenBeg;
      }
      AstNode localAstNode2 = statement();
      WithStatement localWithStatement = new WithStatement(m, getNodeEnd(localAstNode2) - m);
      localWithStatement.setJsDocNode(localComment);
      localWithStatement.setExpression(localAstNode1);
      localWithStatement.setStatement(localAstNode2);
      localWithStatement.setParens(i, j);
      localWithStatement.setLineno(k);
      return localWithStatement;
    }
  }
  
  private XmlElemRef xmlElemRef(int paramInt1, Name paramName, int paramInt2)
    throws IOException
  {
    int k = -1;
    int m = this.ts.tokenBeg;
    if (paramInt1 != -1) {}
    for (int i = paramInt1;; i = m)
    {
      AstNode localAstNode = expr();
      int j = getNodeEnd(localAstNode);
      if (mustMatchToken(85, "msg.no.bracket.index"))
      {
        k = this.ts.tokenBeg;
        j = this.ts.tokenEnd;
      }
      XmlElemRef localXmlElemRef = new XmlElemRef(i, j - i);
      localXmlElemRef.setNamespace(paramName);
      localXmlElemRef.setColonPos(paramInt2);
      localXmlElemRef.setAtPos(paramInt1);
      localXmlElemRef.setExpression(localAstNode);
      localXmlElemRef.setBrackets(m, k);
      return localXmlElemRef;
    }
  }
  
  private AstNode xmlInitializer()
    throws IOException
  {
    if (this.currentToken != 14) {
      codeBug();
    }
    int j = this.ts.tokenBeg;
    int i = this.ts.getFirstXMLToken();
    if ((i != 146) && (i != 149))
    {
      reportError("msg.syntax");
      return makeErrorNode();
    }
    XmlLiteral localXmlLiteral = new XmlLiteral(j);
    localXmlLiteral.setLineno(this.ts.lineno);
    switch (i)
    {
    case 147: 
    case 148: 
    default: 
      reportError("msg.syntax");
      return makeErrorNode();
    case 146: 
      localXmlLiteral.addFragment(new XmlString(this.ts.tokenBeg, this.ts.getString()));
      mustMatchToken(86, "msg.syntax");
      i = this.ts.tokenBeg;
      if (peekToken() == 87) {}
      for (Object localObject = new EmptyExpression(i, this.ts.tokenEnd - i);; localObject = expr())
      {
        mustMatchToken(87, "msg.syntax");
        localObject = new XmlExpression(i, (AstNode)localObject);
        ((XmlExpression)localObject).setIsXmlAttribute(this.ts.isXMLAttribute());
        ((XmlExpression)localObject).setLength(this.ts.tokenEnd - i);
        localXmlLiteral.addFragment((XmlFragment)localObject);
        i = this.ts.getNextXMLToken();
        break;
      }
    }
    localXmlLiteral.addFragment(new XmlString(this.ts.tokenBeg, this.ts.getString()));
    return localXmlLiteral;
  }
  
  void addError(String paramString)
  {
    addError(paramString, this.ts.tokenBeg, this.ts.tokenEnd - this.ts.tokenBeg);
  }
  
  void addError(String paramString, int paramInt1, int paramInt2)
  {
    addError(paramString, null, paramInt1, paramInt2);
  }
  
  void addError(String paramString1, String paramString2)
  {
    addError(paramString1, paramString2, this.ts.tokenBeg, this.ts.tokenEnd - this.ts.tokenBeg);
  }
  
  void addError(String paramString1, String paramString2, int paramInt1, int paramInt2)
  {
    int i = 1;
    this.syntaxErrorCount += 1;
    paramString2 = lookupMessage(paramString1, paramString2);
    if (this.errorCollector != null)
    {
      this.errorCollector.error(paramString2, this.sourceURI, paramInt1, paramInt2);
      return;
    }
    paramString1 = "";
    if (this.ts != null)
    {
      paramInt1 = this.ts.getLineno();
      paramString1 = this.ts.getLine();
    }
    for (paramInt2 = this.ts.getOffset();; paramInt2 = i)
    {
      this.errorReporter.error(paramString2, this.sourceURI, paramInt1, paramString1, paramInt2);
      return;
      paramInt1 = 1;
    }
  }
  
  void addStrictWarning(String paramString1, String paramString2)
  {
    int i = -1;
    int j;
    if (this.ts != null)
    {
      j = this.ts.tokenBeg;
      i = this.ts.tokenEnd - this.ts.tokenBeg;
    }
    for (;;)
    {
      addStrictWarning(paramString1, paramString2, j, i);
      return;
      j = -1;
    }
  }
  
  void addStrictWarning(String paramString1, String paramString2, int paramInt1, int paramInt2)
  {
    if (this.compilerEnv.isStrictMode()) {
      addWarning(paramString1, paramString2, paramInt1, paramInt2);
    }
  }
  
  void addWarning(String paramString, int paramInt1, int paramInt2)
  {
    addWarning(paramString, null, paramInt1, paramInt2);
  }
  
  void addWarning(String paramString1, String paramString2)
  {
    int i = -1;
    int j;
    if (this.ts != null)
    {
      j = this.ts.tokenBeg;
      i = this.ts.tokenEnd - this.ts.tokenBeg;
    }
    for (;;)
    {
      addWarning(paramString1, paramString2, j, i);
      return;
      j = -1;
    }
  }
  
  void addWarning(String paramString1, String paramString2, int paramInt1, int paramInt2)
  {
    String str = lookupMessage(paramString1, paramString2);
    if (this.compilerEnv.reportWarningAsError())
    {
      addError(paramString1, paramString2, paramInt1, paramInt2);
      return;
    }
    if (this.errorCollector != null)
    {
      this.errorCollector.warning(str, this.sourceURI, paramInt1, paramInt2);
      return;
    }
    this.errorReporter.warning(str, this.sourceURI, this.ts.getLineno(), this.ts.getLine(), this.ts.getOffset());
  }
  
  protected void checkActivationName(String paramString, int paramInt)
  {
    if (!insideFunction()) {}
    for (;;)
    {
      return;
      if (("arguments".equals(paramString)) && (((FunctionNode)this.currentScriptOrFn).getFunctionType() != 4)) {
        paramInt = 1;
      }
      while (paramInt != 0)
      {
        setRequiresActivation();
        return;
        if ((this.compilerEnv.getActivationNames() != null) && (this.compilerEnv.getActivationNames().contains(paramString))) {
          paramInt = 1;
        } else if (("length".equals(paramString)) && (paramInt == 33) && (this.compilerEnv.getLanguageVersion() == 120)) {
          paramInt = 1;
        } else {
          paramInt = 0;
        }
      }
    }
  }
  
  protected void checkMutableReference(Node paramNode)
  {
    if ((paramNode.getIntProp(16, 0) & 0x4) != 0) {
      reportError("msg.bad.assign.left");
    }
  }
  
  Node createDestructuringAssignment(int paramInt, Node paramNode1, Node paramNode2)
  {
    String str = this.currentScriptOrFn.getNextTempName();
    paramNode1 = destructuringAssignmentHelper(paramInt, paramNode1, paramNode2, str);
    paramNode1.getLastChild().addChildToBack(createName(str));
    return paramNode1;
  }
  
  protected Node createName(int paramInt, String paramString, Node paramNode)
  {
    paramString = createName(paramString);
    paramString.setType(paramInt);
    if (paramNode != null) {
      paramString.addChildToBack(paramNode);
    }
    return paramString;
  }
  
  protected Node createName(String paramString)
  {
    checkActivationName(paramString, 39);
    return Node.newString(39, paramString);
  }
  
  protected Node createNumber(double paramDouble)
  {
    return Node.newNumber(paramDouble);
  }
  
  protected Scope createScopeNode(int paramInt1, int paramInt2)
  {
    Scope localScope = new Scope();
    localScope.setType(paramInt1);
    localScope.setLineno(paramInt2);
    return localScope;
  }
  
  void defineSymbol(int paramInt, String paramString)
  {
    defineSymbol(paramInt, paramString, false);
  }
  
  void defineSymbol(int paramInt, String paramString, boolean paramBoolean)
  {
    if (paramString == null) {
      if (!this.compilerEnv.isIdeMode()) {}
    }
    Object localObject;
    int i;
    label55:
    label119:
    label125:
    label179:
    do
    {
      return;
      codeBug();
      Scope localScope = this.currentScope.getDefiningScope(paramString);
      if (localScope != null)
      {
        localObject = localScope.getSymbol(paramString);
        if (localObject == null) {
          break label119;
        }
        i = ((Symbol)localObject).getDeclType();
        if ((localObject == null) || ((i != 155) && (paramInt != 155) && ((localScope != this.currentScope) || (i != 154)))) {
          break label179;
        }
        if (i != 155) {
          break label125;
        }
        localObject = "msg.const.redecl";
      }
      for (;;)
      {
        addError((String)localObject, paramString);
        return;
        localObject = null;
        break;
        i = -1;
        break label55;
        if (i == 154) {
          localObject = "msg.let.redecl";
        } else if (i == 123) {
          localObject = "msg.var.redecl";
        } else if (i == 110) {
          localObject = "msg.fn.redecl";
        } else {
          localObject = "msg.parm.redecl";
        }
      }
      switch (paramInt)
      {
      default: 
        throw codeBug();
      case 154: 
        if ((!paramBoolean) && ((this.currentScope.getType() == 113) || ((this.currentScope instanceof Loop))))
        {
          addError("msg.let.decl.not.in.block");
          return;
        }
        this.currentScope.putSymbol(new Symbol(paramInt, paramString));
        return;
      case 110: 
      case 123: 
      case 155: 
        if (localObject == null) {
          break label325;
        }
        if (i == 123)
        {
          addStrictWarning("msg.var.redecl", paramString);
          return;
        }
        break;
      }
    } while (i != 88);
    addStrictWarning("msg.var.hides.arg", paramString);
    return;
    label325:
    this.currentScriptOrFn.putSymbol(new Symbol(paramInt, paramString));
    return;
    if (localObject != null) {
      addWarning("msg.dup.parms", paramString);
    }
    this.currentScriptOrFn.putSymbol(new Symbol(paramInt, paramString));
  }
  
  boolean destructuringArray(ArrayLiteral paramArrayLiteral, int paramInt, String paramString, Node paramNode, List<String> paramList)
  {
    if (paramInt == 155) {}
    boolean bool;
    int i;
    Object localObject;
    for (int j = 156;; j = 8)
    {
      paramArrayLiteral = paramArrayLiteral.getElements().iterator();
      bool = true;
      i = 0;
      for (;;)
      {
        if (!paramArrayLiteral.hasNext()) {
          break label199;
        }
        localObject = (AstNode)paramArrayLiteral.next();
        if (((AstNode)localObject).getType() != 129) {
          break;
        }
        i += 1;
      }
    }
    Node localNode = new Node(36, createName(paramString), createNumber(i));
    if (((AstNode)localObject).getType() == 39)
    {
      localObject = ((AstNode)localObject).getString();
      paramNode.addChildToBack(new Node(j, createName(49, (String)localObject, null), localNode));
      if (paramInt != -1)
      {
        defineSymbol(paramInt, (String)localObject, true);
        paramList.add(localObject);
      }
    }
    for (;;)
    {
      bool = false;
      i += 1;
      break;
      paramNode.addChildToBack(destructuringAssignmentHelper(paramInt, (Node)localObject, localNode, this.currentScriptOrFn.getNextTempName()));
    }
    label199:
    return bool;
  }
  
  Node destructuringAssignmentHelper(int paramInt, Node paramNode1, Node paramNode2, String paramString)
  {
    boolean bool = true;
    Scope localScope = createScopeNode(159, paramNode1.getLineno());
    localScope.addChildToFront(new Node(154, createName(39, paramString, paramNode2)));
    for (;;)
    {
      ArrayList localArrayList;
      try
      {
        pushScope(localScope);
        defineSymbol(154, paramString, true);
        popScope();
        paramNode2 = new Node(90);
        localScope.addChildToBack(paramNode2);
        localArrayList = new ArrayList();
        switch (paramNode1.getType())
        {
        default: 
          reportError("msg.bad.assign.left");
          if (bool) {
            paramNode2.addChildToBack(createNumber(0.0D));
          }
          localScope.putProp(22, localArrayList);
          return localScope;
        }
      }
      finally
      {
        popScope();
      }
      bool = destructuringArray((ArrayLiteral)paramNode1, paramInt, paramString, paramNode2, localArrayList);
      continue;
      bool = destructuringObject((ObjectLiteral)paramNode1, paramInt, paramString, paramNode2, localArrayList);
    }
    switch (paramInt)
    {
    }
    for (;;)
    {
      paramNode2.addChildToBack(simpleAssignment(paramNode1, createName(paramString)));
      break;
      reportError("msg.bad.assign.left");
    }
  }
  
  boolean destructuringObject(ObjectLiteral paramObjectLiteral, int paramInt, String paramString, Node paramNode, List<String> paramList)
  {
    int i;
    boolean bool;
    label26:
    Object localObject;
    if (paramInt == 155)
    {
      i = 156;
      Iterator localIterator = paramObjectLiteral.getElements().iterator();
      bool = true;
      if (!localIterator.hasNext()) {
        break label302;
      }
      localObject = (ObjectProperty)localIterator.next();
      if (this.ts == null) {
        break label305;
      }
    }
    label104:
    label279:
    label302:
    label305:
    for (int j = this.ts.lineno;; j = 0)
    {
      paramObjectLiteral = ((ObjectProperty)localObject).getLeft();
      if ((paramObjectLiteral instanceof Name))
      {
        paramObjectLiteral = Node.newString(((Name)paramObjectLiteral).getIdentifier());
        paramObjectLiteral = new Node(33, createName(paramString), paramObjectLiteral);
        paramObjectLiteral.setLineno(j);
        localObject = ((ObjectProperty)localObject).getRight();
        if (((AstNode)localObject).getType() != 39) {
          break label279;
        }
        localObject = ((Name)localObject).getIdentifier();
        paramNode.addChildToBack(new Node(i, createName(49, (String)localObject, null), paramObjectLiteral));
        if (paramInt != -1)
        {
          defineSymbol(paramInt, (String)localObject, true);
          paramList.add(localObject);
        }
      }
      for (;;)
      {
        bool = false;
        break label26;
        i = 8;
        break;
        if ((paramObjectLiteral instanceof StringLiteral))
        {
          paramObjectLiteral = Node.newString(((StringLiteral)paramObjectLiteral).getValue());
          paramObjectLiteral = new Node(33, createName(paramString), paramObjectLiteral);
          break label104;
        }
        if ((paramObjectLiteral instanceof NumberLiteral))
        {
          paramObjectLiteral = createNumber((int)((NumberLiteral)paramObjectLiteral).getNumber());
          paramObjectLiteral = new Node(36, createName(paramString), paramObjectLiteral);
          break label104;
        }
        throw codeBug();
        paramNode.addChildToBack(destructuringAssignmentHelper(paramInt, (Node)localObject, paramObjectLiteral, this.currentScriptOrFn.getNextTempName()));
      }
      return bool;
    }
  }
  
  public boolean eof()
  {
    return this.ts.eof();
  }
  
  public boolean inUseStrictDirective()
  {
    return this.inUseStrictDirective;
  }
  
  boolean insideFunction()
  {
    return this.nestingOfFunction != 0;
  }
  
  String lookupMessage(String paramString)
  {
    return lookupMessage(paramString, null);
  }
  
  String lookupMessage(String paramString1, String paramString2)
  {
    if (paramString2 == null) {
      return ScriptRuntime.getMessage0(paramString1);
    }
    return ScriptRuntime.getMessage1(paramString1, paramString2);
  }
  
  void markDestructuring(AstNode paramAstNode)
  {
    if ((paramAstNode instanceof DestructuringForm)) {
      ((DestructuringForm)paramAstNode).setIsDestructuring(true);
    }
    while (!(paramAstNode instanceof ParenthesizedExpression)) {
      return;
    }
    markDestructuring(((ParenthesizedExpression)paramAstNode).getExpression());
  }
  
  public AstRoot parse(Reader paramReader, String paramString, int paramInt)
    throws IOException
  {
    if (this.parseFinished) {
      throw new IllegalStateException("parser reused");
    }
    if (this.compilerEnv.isIdeMode()) {
      return parse(readFully(paramReader), paramString, paramInt);
    }
    try
    {
      this.sourceURI = paramString;
      this.ts = new TokenStream(this, paramReader, null, paramInt);
      paramReader = parse();
      return paramReader;
    }
    finally
    {
      this.parseFinished = true;
    }
  }
  
  public AstRoot parse(String paramString1, String paramString2, int paramInt)
  {
    if (this.parseFinished) {
      throw new IllegalStateException("parser reused");
    }
    this.sourceURI = paramString2;
    if (this.compilerEnv.isIdeMode()) {
      this.sourceChars = paramString1.toCharArray();
    }
    this.ts = new TokenStream(this, null, paramString1, paramInt);
    try
    {
      paramString1 = parse();
      return paramString1;
    }
    catch (IOException paramString1)
    {
      throw new IllegalStateException();
    }
    finally
    {
      this.parseFinished = true;
    }
  }
  
  void popScope()
  {
    this.currentScope = this.currentScope.getParentScope();
  }
  
  void pushScope(Scope paramScope)
  {
    Scope localScope = paramScope.getParentScope();
    if (localScope != null) {
      if (localScope != this.currentScope) {
        codeBug();
      }
    }
    for (;;)
    {
      this.currentScope = paramScope;
      return;
      this.currentScope.addChildScope(paramScope);
    }
  }
  
  protected AstNode removeParens(AstNode paramAstNode)
  {
    while ((paramAstNode instanceof ParenthesizedExpression)) {
      paramAstNode = ((ParenthesizedExpression)paramAstNode).getExpression();
    }
    return paramAstNode;
  }
  
  void reportError(String paramString)
  {
    reportError(paramString, null);
  }
  
  void reportError(String paramString, int paramInt1, int paramInt2)
  {
    reportError(paramString, null, paramInt1, paramInt2);
  }
  
  void reportError(String paramString1, String paramString2)
  {
    if (this.ts == null)
    {
      reportError(paramString1, paramString2, 1, 1);
      return;
    }
    reportError(paramString1, paramString2, this.ts.tokenBeg, this.ts.tokenEnd - this.ts.tokenBeg);
  }
  
  void reportError(String paramString1, String paramString2, int paramInt1, int paramInt2)
  {
    addError(paramString1, paramString2, paramInt1, paramInt2);
    if (!this.compilerEnv.recoverFromErrors()) {
      throw new ParserException(null);
    }
  }
  
  public void setDefaultUseStrictDirective(boolean paramBoolean)
  {
    this.defaultUseStrictDirective = paramBoolean;
  }
  
  protected void setIsGenerator()
  {
    if (insideFunction()) {
      ((FunctionNode)this.currentScriptOrFn).setIsGenerator();
    }
  }
  
  protected void setRequiresActivation()
  {
    if (insideFunction()) {
      ((FunctionNode)this.currentScriptOrFn).setRequiresActivation();
    }
  }
  
  protected Node simpleAssignment(Node paramNode1, Node paramNode2)
  {
    int i = paramNode1.getType();
    Object localObject;
    switch (i)
    {
    default: 
      throw codeBug();
    case 39: 
      localObject = ((Name)paramNode1).getIdentifier();
      if ((this.inUseStrictDirective) && (("eval".equals(localObject)) || ("arguments".equals(localObject)))) {
        reportError("msg.bad.id.strict", (String)localObject);
      }
      paramNode1.setType(49);
      return new Node(8, paramNode1, paramNode2);
    case 33: 
    case 36: 
      if ((paramNode1 instanceof PropertyGet))
      {
        localObject = ((PropertyGet)paramNode1).getTarget();
        paramNode1 = ((PropertyGet)paramNode1).getProperty();
        if (i != 33) {
          break label213;
        }
        i = 35;
        paramNode1.setType(41);
      }
      for (;;)
      {
        return new Node(i, (Node)localObject, paramNode1, paramNode2);
        if ((paramNode1 instanceof ElementGet))
        {
          localObject = ((ElementGet)paramNode1).getTarget();
          paramNode1 = ((ElementGet)paramNode1).getElement();
          break;
        }
        localObject = paramNode1.getFirstChild();
        paramNode1 = paramNode1.getLastChild();
        break;
        label213:
        i = 37;
      }
    }
    paramNode1 = paramNode1.getFirstChild();
    checkMutableReference(paramNode1);
    return new Node(69, paramNode1, paramNode2);
  }
  
  private static class ConditionData
  {
    AstNode condition;
    int lp = -1;
    int rp = -1;
  }
  
  private static class ParserException
    extends RuntimeException
  {
    static final long serialVersionUID = 5882582646773765630L;
  }
  
  protected class PerFunctionVariables
  {
    private Scope savedCurrentScope;
    private ScriptNode savedCurrentScriptOrFn = Parser.this.currentScriptOrFn;
    private int savedEndFlags;
    private boolean savedInForInit;
    private Map<String, LabeledStatement> savedLabelSet;
    private List<Jump> savedLoopAndSwitchSet;
    private List<Loop> savedLoopSet;
    
    PerFunctionVariables(FunctionNode paramFunctionNode)
    {
      Parser.this.currentScriptOrFn = paramFunctionNode;
      this.savedCurrentScope = Parser.this.currentScope;
      Parser.this.currentScope = paramFunctionNode;
      this.savedLabelSet = Parser.this.labelSet;
      Parser.access$202(Parser.this, null);
      this.savedLoopSet = Parser.this.loopSet;
      Parser.access$302(Parser.this, null);
      this.savedLoopAndSwitchSet = Parser.this.loopAndSwitchSet;
      Parser.access$402(Parser.this, null);
      this.savedEndFlags = Parser.this.endFlags;
      Parser.access$502(Parser.this, 0);
      this.savedInForInit = Parser.this.inForInit;
      Parser.access$602(Parser.this, false);
    }
    
    void restore()
    {
      Parser.this.currentScriptOrFn = this.savedCurrentScriptOrFn;
      Parser.this.currentScope = this.savedCurrentScope;
      Parser.access$202(Parser.this, this.savedLabelSet);
      Parser.access$302(Parser.this, this.savedLoopSet);
      Parser.access$402(Parser.this, this.savedLoopAndSwitchSet);
      Parser.access$502(Parser.this, this.savedEndFlags);
      Parser.access$602(Parser.this, this.savedInForInit);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\Parser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */