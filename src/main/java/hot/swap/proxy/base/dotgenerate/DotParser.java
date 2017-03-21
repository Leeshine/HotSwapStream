// $ANTLR 2.7.2: "DotParser.g" -> "DotParser.java"$

  package hot.swap.proxy.base.dotgenerate;

import antlr.TokenBuffer;
import antlr.TokenStreamException;
import antlr.TokenStreamIOException;
import antlr.ANTLRException;
import antlr.LLkParser;
import antlr.Token;
import antlr.TokenStream;
import antlr.RecognitionException;
import antlr.NoViableAltException;
import antlr.MismatchedTokenException;
import antlr.SemanticException;
import antlr.ParserSharedInputState;
import antlr.collections.impl.BitSet;
import antlr.collections.AST;
import java.util.Hashtable;
import antlr.ASTFactory;
import antlr.ASTPair;
import antlr.collections.impl.ASTArray;

public class DotParser extends antlr.LLkParser       implements DotParserTokenTypes
 {


protected DotParser(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

public DotParser(TokenBuffer tokenBuf) {
  this(tokenBuf,1);
}

protected DotParser(TokenStream lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

public DotParser(TokenStream lexer) {
  this(lexer,1);
}

public DotParser(ParserSharedInputState state) {
  super(state,1);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

	public final void graph() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST graph_AST = null;
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case STRICT_LITERAL:
			{
				AST tmp1_AST = null;
				tmp1_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp1_AST);
				match(STRICT_LITERAL);
				break;
			}
			case GRAPH_LITERAL:
			case DIGRAPH_LITERAL:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			switch ( LA(1)) {
			case GRAPH_LITERAL:
			{
				AST tmp2_AST = null;
				tmp2_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp2_AST);
				match(GRAPH_LITERAL);
				break;
			}
			case DIGRAPH_LITERAL:
			{
				AST tmp3_AST = null;
				tmp3_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp3_AST);
				match(DIGRAPH_LITERAL);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			switch ( LA(1)) {
			case ID:
			{
				AST tmp4_AST = null;
				tmp4_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp4_AST);
				match(ID);
				break;
			}
			case O_BRACKET:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(O_BRACKET);
			stmt_list();
			astFactory.addASTChild(currentAST, returnAST);
			match(C_BRACKET);
			graph_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				consume();
				consumeUntil(_tokenSet_0);
			} else {
			  throw ex;
			}
		}
		returnAST = graph_AST;
	}
	
	protected final void stmt_list() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST stmt_list_AST = null;
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case GRAPH_LITERAL:
			case NODE_LITERAL:
			case EDGE_LITERAL:
			case SUBGRAPH_LITERAL:
			case ID:
			{
				stmt();
				astFactory.addASTChild(currentAST, returnAST);
				stmt_list();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case C_BRACKET:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			stmt_list_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				consume();
				consumeUntil(_tokenSet_1);
			} else {
			  throw ex;
			}
		}
		returnAST = stmt_list_AST;
	}
	
	protected final void stmt() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST stmt_AST = null;
		Token  i = null;
		AST i_AST = null;
		String currentId = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case SUBGRAPH_LITERAL:
			{
				subgraph();
				astFactory.addASTChild(currentAST, returnAST);
				stmt_AST = (AST)currentAST.root;
				break;
			}
			case ID:
			{
				i = LT(1);
				i_AST = astFactory.create(i);
				astFactory.makeASTRoot(currentAST, i_AST);
				match(ID);
				if ( inputState.guessing==0 ) {
					currentId = i.getText();
				}
				{
				switch ( LA(1)) {
				case EQUAL:
				{
					match(EQUAL);
					AST tmp8_AST = null;
					tmp8_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp8_AST);
					match(ID);
					break;
				}
				case O_SQR_BRACKET:
				case SEMI_COLON:
				case COLON:
				case EDGEOP_LITERAL:
				{
					node_or_edge_stmt(currentId);
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				match(SEMI_COLON);
				stmt_AST = (AST)currentAST.root;
				break;
			}
			case GRAPH_LITERAL:
			case NODE_LITERAL:
			case EDGE_LITERAL:
			{
				attr_stmt();
				astFactory.addASTChild(currentAST, returnAST);
				stmt_AST = (AST)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				consume();
				consumeUntil(_tokenSet_2);
			} else {
			  throw ex;
			}
		}
		returnAST = stmt_AST;
	}
	
	protected final void subgraph() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST subgraph_AST = null;
		
		try {      // for error handling
			AST tmp10_AST = null;
			tmp10_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp10_AST);
			match(SUBGRAPH_LITERAL);
			AST tmp11_AST = null;
			tmp11_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp11_AST);
			match(ID);
			{
			switch ( LA(1)) {
			case O_BRACKET:
			{
				match(O_BRACKET);
				stmt_list();
				astFactory.addASTChild(currentAST, returnAST);
				match(C_BRACKET);
				break;
			}
			case GRAPH_LITERAL:
			case NODE_LITERAL:
			case EDGE_LITERAL:
			case SUBGRAPH_LITERAL:
			case C_BRACKET:
			case O_SQR_BRACKET:
			case SEMI_COLON:
			case EDGEOP_LITERAL:
			case ID:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			subgraph_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				consume();
				consumeUntil(_tokenSet_3);
			} else {
			  throw ex;
			}
		}
		returnAST = subgraph_AST;
	}
	
	protected final void node_or_edge_stmt(
		String id
	) throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST node_or_edge_stmt_AST = null;
		String p = null;
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case COLON:
			{
				p=port();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case O_SQR_BRACKET:
			case SEMI_COLON:
			case EDGEOP_LITERAL:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			switch ( LA(1)) {
			case EDGEOP_LITERAL:
			{
				edge_stmt(id, p);
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case O_SQR_BRACKET:
			case SEMI_COLON:
			{
				node_stmt(id, p);
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			node_or_edge_stmt_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				consume();
				consumeUntil(_tokenSet_4);
			} else {
			  throw ex;
			}
		}
		returnAST = node_or_edge_stmt_AST;
	}
	
	protected final void attr_stmt() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST attr_stmt_AST = null;
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case GRAPH_LITERAL:
			{
				AST tmp14_AST = null;
				tmp14_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp14_AST);
				match(GRAPH_LITERAL);
				break;
			}
			case NODE_LITERAL:
			{
				AST tmp15_AST = null;
				tmp15_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp15_AST);
				match(NODE_LITERAL);
				break;
			}
			case EDGE_LITERAL:
			{
				AST tmp16_AST = null;
				tmp16_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp16_AST);
				match(EDGE_LITERAL);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			attr_list();
			astFactory.addASTChild(currentAST, returnAST);
			}
			match(SEMI_COLON);
			attr_stmt_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				consume();
				consumeUntil(_tokenSet_2);
			} else {
			  throw ex;
			}
		}
		returnAST = attr_stmt_AST;
	}
	
	protected final String  port() throws RecognitionException, TokenStreamException {
		String result = null;
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST port_AST = null;
		Token  i = null;
		AST i_AST = null;
		Token  c1 = null;
		AST c1_AST = null;
		Token  c2 = null;
		AST c2_AST = null;
		String compass = null;
		
		try {      // for error handling
			match(COLON);
			{
			switch ( LA(1)) {
			case ID:
			{
				i = LT(1);
				i_AST = astFactory.create(i);
				astFactory.makeASTRoot(currentAST, i_AST);
				match(ID);
				{
				switch ( LA(1)) {
				case COLON:
				{
					match(COLON);
					c1 = LT(1);
					c1_AST = astFactory.create(c1);
					astFactory.addASTChild(currentAST, c1_AST);
					match(COMPASS_PT);
					if ( inputState.guessing==0 ) {
						compass = c1.getText();
					}
					break;
				}
				case O_SQR_BRACKET:
				case SEMI_COLON:
				case EDGEOP_LITERAL:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				if ( inputState.guessing==0 ) {
					
					result = i.getText();
					if  (compass != null)
					{
					result += ":" + compass;
					}
					
				}
				break;
			}
			case COMPASS_PT:
			{
				c2 = LT(1);
				c2_AST = astFactory.create(c2);
				astFactory.makeASTRoot(currentAST, c2_AST);
				match(COMPASS_PT);
				if ( inputState.guessing==0 ) {
					result = c2.getText();
				}
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			port_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				consume();
				consumeUntil(_tokenSet_5);
			} else {
			  throw ex;
			}
		}
		returnAST = port_AST;
		return result;
	}
	
	protected final void edgeRHS() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST edgeRHS_AST = null;
		
		try {      // for error handling
			AST tmp20_AST = null;
			tmp20_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp20_AST);
			match(EDGEOP_LITERAL);
			{
			switch ( LA(1)) {
			case ID:
			{
				node_id();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case SUBGRAPH_LITERAL:
			{
				subgraph();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case O_SQR_BRACKET:
			case SEMI_COLON:
			case EDGEOP_LITERAL:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			switch ( LA(1)) {
			case EDGEOP_LITERAL:
			{
				edgeRHS();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case O_SQR_BRACKET:
			case SEMI_COLON:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			edgeRHS_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				consume();
				consumeUntil(_tokenSet_6);
			} else {
			  throw ex;
			}
		}
		returnAST = edgeRHS_AST;
	}
	
	protected final void edge_stmt(
		String id, String port
	) throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST edge_stmt_AST = null;
		
		try {      // for error handling
			edgeRHS();
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case O_SQR_BRACKET:
			{
				attr_list();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case SEMI_COLON:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			edge_stmt_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				consume();
				consumeUntil(_tokenSet_4);
			} else {
			  throw ex;
			}
		}
		returnAST = edge_stmt_AST;
	}
	
	protected final void node_stmt(
		String id, String port
	) throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST node_stmt_AST = null;
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case O_SQR_BRACKET:
			{
				attr_list();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case SEMI_COLON:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			node_stmt_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				consume();
				consumeUntil(_tokenSet_4);
			} else {
			  throw ex;
			}
		}
		returnAST = node_stmt_AST;
	}
	
	protected final void attr_list() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST attr_list_AST = null;
		
		try {      // for error handling
			match(O_SQR_BRACKET);
			{
			switch ( LA(1)) {
			case ID:
			{
				a_list();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case C_SQR_BRACKET:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(C_SQR_BRACKET);
			{
			switch ( LA(1)) {
			case O_SQR_BRACKET:
			{
				attr_list();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case SEMI_COLON:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			attr_list_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				consume();
				consumeUntil(_tokenSet_4);
			} else {
			  throw ex;
			}
		}
		returnAST = attr_list_AST;
	}
	
	protected final void a_list() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST a_list_AST = null;
		
		try {      // for error handling
			{
			arg();
			astFactory.addASTChild(currentAST, returnAST);
			}
			{
			switch ( LA(1)) {
			case COMMA:
			{
				match(COMMA);
				break;
			}
			case C_SQR_BRACKET:
			case ID:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			switch ( LA(1)) {
			case ID:
			{
				a_list();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case C_SQR_BRACKET:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			a_list_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				consume();
				consumeUntil(_tokenSet_7);
			} else {
			  throw ex;
			}
		}
		returnAST = a_list_AST;
	}
	
	protected final void arg() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST arg_AST = null;
		
		try {      // for error handling
			AST tmp24_AST = null;
			tmp24_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp24_AST);
			match(ID);
			{
			switch ( LA(1)) {
			case EQUAL:
			{
				match(EQUAL);
				AST tmp26_AST = null;
				tmp26_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp26_AST);
				match(ID);
				break;
			}
			case C_SQR_BRACKET:
			case COMMA:
			case ID:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			arg_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				consume();
				consumeUntil(_tokenSet_8);
			} else {
			  throw ex;
			}
		}
		returnAST = arg_AST;
	}
	
	protected final void node_id() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST node_id_AST = null;
		String p = null;
		
		try {      // for error handling
			AST tmp27_AST = null;
			tmp27_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp27_AST);
			match(ID);
			{
			switch ( LA(1)) {
			case COLON:
			{
				p=port();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case O_SQR_BRACKET:
			case SEMI_COLON:
			case EDGEOP_LITERAL:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			node_id_AST = (AST)currentAST.root;
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				consume();
				consumeUntil(_tokenSet_5);
			} else {
			  throw ex;
			}
		}
		returnAST = node_id_AST;
	}
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"LITERALS",
		"GRAPH_LITERAL",
		"DIGRAPH_LITERAL",
		"STRICT_LITERAL",
		"NODE_LITERAL",
		"EDGE_LITERAL",
		"SUBGRAPH_LITERAL",
		"O_BRACKET",
		"C_BRACKET",
		"O_SQR_BRACKET",
		"C_SQR_BRACKET",
		"SEMI_COLON",
		"EQUAL",
		"COMMA",
		"COLON",
		"EDGEOP_LITERAL",
		"ID",
		"COMPASS_PT",
		"ALPHACHAR",
		"VALIDSTR",
		"NUMBER",
		"QUOTEDSTR",
		"HTMLSTR",
		"WS",
		"COMMENT",
		"SL_COMMENT",
		"ML_COMMENT"
	};
	
	protected void buildTokenTypeASTClassMap() {
		tokenTypeToASTClassMap=null;
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { 2L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { 4096L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { 1054496L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { 1619744L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = { 32768L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	private static final long[] mk_tokenSet_5() {
		long[] data = { 565248L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());
	private static final long[] mk_tokenSet_6() {
		long[] data = { 40960L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_6 = new BitSet(mk_tokenSet_6());
	private static final long[] mk_tokenSet_7() {
		long[] data = { 16384L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_7 = new BitSet(mk_tokenSet_7());
	private static final long[] mk_tokenSet_8() {
		long[] data = { 1196032L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_8 = new BitSet(mk_tokenSet_8());
	
	}
