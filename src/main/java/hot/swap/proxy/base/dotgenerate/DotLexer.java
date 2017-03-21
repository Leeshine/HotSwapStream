// $ANTLR 2.7.2: "DotLexer.g" -> "DotLexer.java"$

package hot.swap.proxy.base.dotgenerate;

import java.io.InputStream;

import antlr.TokenStreamException;
import antlr.TokenStreamIOException;
import antlr.TokenStreamRecognitionException;
import antlr.CharStreamException;
import antlr.CharStreamIOException;
import antlr.ANTLRException;
import java.io.Reader;
import java.util.Hashtable;
import antlr.CharScanner;
import antlr.InputBuffer;
import antlr.ByteBuffer;
import antlr.CharBuffer;
import antlr.Token;
import antlr.CommonToken;
import antlr.RecognitionException;
import antlr.NoViableAltForCharException;
import antlr.MismatchedCharException;
import antlr.TokenStream;
import antlr.ANTLRHashString;
import antlr.LexerSharedInputState;
import antlr.collections.impl.BitSet;
import antlr.SemanticException;



public class DotLexer extends antlr.CharScanner implements DotTokenTypes, TokenStream
{

    public DotLexer(InputStream in) {
        this(new ByteBuffer(in));
    }
    public DotLexer(Reader in) {
        this(new CharBuffer(in));
    }
    public DotLexer(InputBuffer ib) {
        this(new LexerSharedInputState(ib));
    }
    public DotLexer(LexerSharedInputState state) {
        super(state);
        caseSensitiveLiterals = true;
        setCaseSensitive(true);
        literals = new Hashtable();
    }

    public Token nextToken() throws TokenStreamException {
        Token theRetToken=null;
tryAgain:
        for (;;) {
            Token _token = null;
            int _ttype = Token.INVALID_TYPE;
            resetText();
            try {   // for char stream error handling
                try {   // for lexical error handling
                    switch ( LA(1)) {
                        case '"':  case ',':  case '-':  case '0':
                        case '1':  case '2':  case '3':  case '4':
                        case '5':  case '6':  case '7':  case '8':
                        case '9':  case ':':  case ';':  case '<':
                        case '=':  case 'A':  case 'B':  case 'C':
                        case 'D':  case 'E':  case 'F':  case 'G':
                        case 'H':  case 'I':  case 'J':  case 'K':
                        case 'L':  case 'M':  case 'N':  case 'O':
                        case 'P':  case 'Q':  case 'R':  case 'S':
                        case 'T':  case 'U':  case 'V':  case 'W':
                        case 'X':  case 'Y':  case 'Z':  case '[':
                        case ']':  case '_':  case 'a':  case 'b':
                        case 'c':  case 'd':  case 'e':  case 'f':
                        case 'g':  case 'h':  case 'i':  case 'j':
                        case 'k':  case 'l':  case 'm':  case 'n':
                        case 'o':  case 'p':  case 'q':  case 'r':
                        case 's':  case 't':  case 'u':  case 'v':
                        case 'w':  case 'x':  case 'y':  case 'z':
                        case '{':  case '}':
                            {
                                mLITERALS(true);
                                theRetToken=_returnToken;
                                break;
                            }
                        case '\t':  case '\n':  case '\r':  case ' ':
                            {
                                mWS(true);
                                theRetToken=_returnToken;
                                break;
                            }
                        case '/':
                            {
                                mCOMMENT(true);
                                theRetToken=_returnToken;
                                break;
                            }
                        default:
                            {
                                if (LA(1)==EOF_CHAR) {uponEOF(); _returnToken = makeToken(Token.EOF_TYPE);}
                                else {throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());}
                            }
                    }
                    if ( _returnToken==null ) continue tryAgain; // found SKIP token
                    _ttype = _returnToken.getType();
				_ttype = testLiteralsTable(_ttype);
				_returnToken.setType(_ttype);
				return _returnToken;
			}
			catch (RecognitionException e) {
				throw new TokenStreamRecognitionException(e);
			}
		}
		catch (CharStreamException cse) {
			if ( cse instanceof CharStreamIOException ) {
				throw new TokenStreamIOException(((CharStreamIOException)cse).io);
			}
			else {
				throw new TokenStreamException(cse.getMessage());
			}
		}
	}
}

	public final void mLITERALS(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = LITERALS;
		int _saveIndex;
		
		{
		switch ( LA(1)) {
		case '{':
		{
			mO_BRACKET(false);
			if ( inputState.guessing==0 ) {
				_ttype = O_BRACKET;
			}
			break;
		}
		case '}':
		{
			mC_BRACKET(false);
			if ( inputState.guessing==0 ) {
				_ttype = C_BRACKET;
			}
			break;
		}
		case '[':
		{
			mO_SQR_BRACKET(false);
			if ( inputState.guessing==0 ) {
				_ttype = O_SQR_BRACKET;
			}
			break;
		}
		case ']':
		{
			mC_SQR_BRACKET(false);
			if ( inputState.guessing==0 ) {
				_ttype = C_SQR_BRACKET;
			}
			break;
		}
		case ';':
		{
			mSEMI_COLON(false);
			if ( inputState.guessing==0 ) {
				_ttype = SEMI_COLON;
			}
			break;
		}
		case '=':
		{
			mEQUAL(false);
			if ( inputState.guessing==0 ) {
				_ttype = EQUAL;
			}
			break;
		}
		case ',':
		{
			mCOMMA(false);
			if ( inputState.guessing==0 ) {
				_ttype = COMMA;
			}
			break;
		}
		case ':':
		{
			mCOLON(false);
			if ( inputState.guessing==0 ) {
				_ttype = COLON;
			}
			break;
		}
		default:
			boolean synPredMatched4 = false;
			if (((LA(1)=='g'))) {
				int _m4 = mark();
				synPredMatched4 = true;
				inputState.guessing++;
				try {
					{
					match("graph");
					}
				}
				catch (RecognitionException pe) {
					synPredMatched4 = false;
				}
				rewind(_m4);
				inputState.guessing--;
			}
			if ( synPredMatched4 ) {
				mGRAPH_LITERAL(false);
				if ( inputState.guessing==0 ) {
					_ttype = GRAPH_LITERAL;
				}
			}
			else {
				boolean synPredMatched6 = false;
				if (((LA(1)=='d'))) {
					int _m6 = mark();
					synPredMatched6 = true;
					inputState.guessing++;
					try {
						{
						match("digraph");
						}
					}
					catch (RecognitionException pe) {
						synPredMatched6 = false;
					}
					rewind(_m6);
					inputState.guessing--;
				}
				if ( synPredMatched6 ) {
					mDIGRAPH_LITERAL(false);
					if ( inputState.guessing==0 ) {
						_ttype = DIGRAPH_LITERAL;
					}
				}
				else {
					boolean synPredMatched8 = false;
					if (((LA(1)=='s'))) {
						int _m8 = mark();
						synPredMatched8 = true;
						inputState.guessing++;
						try {
							{
							match("strict");
							}
						}
						catch (RecognitionException pe) {
							synPredMatched8 = false;
						}
						rewind(_m8);
						inputState.guessing--;
					}
					if ( synPredMatched8 ) {
						mSTRICT_LITERAL(false);
						if ( inputState.guessing==0 ) {
							_ttype = STRICT_LITERAL;
						}
					}
					else {
						boolean synPredMatched10 = false;
						if (((LA(1)=='n'))) {
							int _m10 = mark();
							synPredMatched10 = true;
							inputState.guessing++;
							try {
								{
								match("node");
								}
							}
							catch (RecognitionException pe) {
								synPredMatched10 = false;
							}
							rewind(_m10);
							inputState.guessing--;
						}
						if ( synPredMatched10 ) {
							mNODE_LITERAL(false);
							if ( inputState.guessing==0 ) {
								_ttype = NODE_LITERAL;
							}
						}
						else {
							boolean synPredMatched12 = false;
							if (((LA(1)=='e'))) {
								int _m12 = mark();
								synPredMatched12 = true;
								inputState.guessing++;
								try {
									{
									match("edge");
									}
								}
								catch (RecognitionException pe) {
									synPredMatched12 = false;
								}
								rewind(_m12);
								inputState.guessing--;
							}
							if ( synPredMatched12 ) {
								mEDGE_LITERAL(false);
								if ( inputState.guessing==0 ) {
									_ttype = EDGE_LITERAL;
								}
							}
							else {
								boolean synPredMatched14 = false;
								if (((LA(1)=='s'))) {
									int _m14 = mark();
									synPredMatched14 = true;
									inputState.guessing++;
									try {
										{
										match("subgraph");
										}
									}
									catch (RecognitionException pe) {
										synPredMatched14 = false;
									}
									rewind(_m14);
									inputState.guessing--;
								}
								if ( synPredMatched14 ) {
									mSUBGRAPH_LITERAL(false);
									if ( inputState.guessing==0 ) {
										_ttype = SUBGRAPH_LITERAL;
									}
								}
								else {
									boolean synPredMatched16 = false;
									if (((LA(1)=='-'))) {
										int _m16 = mark();
										synPredMatched16 = true;
										inputState.guessing++;
										try {
											{
											match("--");
											}
										}
										catch (RecognitionException pe) {
											synPredMatched16 = false;
										}
										rewind(_m16);
										inputState.guessing--;
									}
									if ( synPredMatched16 ) {
										mEDGEOP_LITERAL(false);
										if ( inputState.guessing==0 ) {
											_ttype = EDGEOP_LITERAL;
										}
									}
									else {
										boolean synPredMatched18 = false;
										if (((LA(1)=='-'))) {
											int _m18 = mark();
											synPredMatched18 = true;
											inputState.guessing++;
											try {
												{
												match("->");
												}
											}
											catch (RecognitionException pe) {
												synPredMatched18 = false;
											}
											rewind(_m18);
											inputState.guessing--;
										}
										if ( synPredMatched18 ) {
											mEDGEOP_LITERAL(false);
											if ( inputState.guessing==0 ) {
												_ttype = EDGEOP_LITERAL;
											}
										}
										else if ((_tokenSet_0.member(LA(1)))) {
											mID(false);
											if ( inputState.guessing==0 ) {
												_ttype = ID;
											}
										}
									else {
										throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
									}
									}}}}}}}}
									}
									if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
										_token = makeToken(_ttype);
										_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
									}
									_returnToken = _token;
								}
								
	protected final void mGRAPH_LITERAL(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = GRAPH_LITERAL;
		int _saveIndex;
		
		match("graph");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	protected final void mDIGRAPH_LITERAL(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = DIGRAPH_LITERAL;
		int _saveIndex;
		
		match("digraph");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	protected final void mSTRICT_LITERAL(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = STRICT_LITERAL;
		int _saveIndex;
		
		match("strict");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	protected final void mNODE_LITERAL(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = NODE_LITERAL;
		int _saveIndex;
		
		match("node");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	protected final void mEDGE_LITERAL(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = EDGE_LITERAL;
		int _saveIndex;
		
		match("edge");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	protected final void mSUBGRAPH_LITERAL(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = SUBGRAPH_LITERAL;
		int _saveIndex;
		
		match("subgraph");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	protected final void mEDGEOP_LITERAL(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = EDGEOP_LITERAL;
		int _saveIndex;
		
		{
		boolean synPredMatched52 = false;
		if (((LA(1)=='-'))) {
			int _m52 = mark();
			synPredMatched52 = true;
			inputState.guessing++;
			try {
				{
				match("->");
				}
			}
			catch (RecognitionException pe) {
				synPredMatched52 = false;
			}
			rewind(_m52);
			inputState.guessing--;
		}
		if ( synPredMatched52 ) {
			match("->");
		}
		else {
			boolean synPredMatched54 = false;
			if (((LA(1)=='-'))) {
				int _m54 = mark();
				synPredMatched54 = true;
				inputState.guessing++;
				try {
					{
					match("--");
					}
				}
				catch (RecognitionException pe) {
					synPredMatched54 = false;
				}
				rewind(_m54);
				inputState.guessing--;
			}
			if ( synPredMatched54 ) {
				match("--");
			}
			else {
				throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
			}
			}
			}
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		}
		
	protected final void mO_BRACKET(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = O_BRACKET;
		int _saveIndex;
		
		match('{');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	protected final void mC_BRACKET(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = C_BRACKET;
		int _saveIndex;
		
		match('}');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	protected final void mO_SQR_BRACKET(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = O_SQR_BRACKET;
		int _saveIndex;
		
		match('[');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	protected final void mC_SQR_BRACKET(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = C_SQR_BRACKET;
		int _saveIndex;
		
		match(']');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	protected final void mSEMI_COLON(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = SEMI_COLON;
		int _saveIndex;
		
		match(';');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	protected final void mEQUAL(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = EQUAL;
		int _saveIndex;
		
		match('=');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	protected final void mCOMMA(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = COMMA;
		int _saveIndex;
		
		match(',');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	protected final void mCOLON(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = COLON;
		int _saveIndex;
		
		match(':');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	protected final void mID(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = ID;
		int _saveIndex;
		
		{
		switch ( LA(1)) {
		case 'A':  case 'B':  case 'C':  case 'D':
		case 'E':  case 'F':  case 'G':  case 'H':
		case 'I':  case 'J':  case 'K':  case 'L':
		case 'M':  case 'N':  case 'O':  case 'P':
		case 'Q':  case 'R':  case 'S':  case 'T':
		case 'U':  case 'V':  case 'W':  case 'X':
		case 'Y':  case 'Z':  case '_':  case 'a':
		case 'b':  case 'c':  case 'd':  case 'e':
		case 'f':  case 'g':  case 'h':  case 'i':
		case 'j':  case 'k':  case 'l':  case 'm':
		case 'n':  case 'o':  case 'p':  case 'q':
		case 'r':  case 's':  case 't':  case 'u':
		case 'v':  case 'w':  case 'x':  case 'y':
		case 'z':
		{
			mVALIDSTR(false);
			break;
		}
		case '-':  case '0':  case '1':  case '2':
		case '3':  case '4':  case '5':  case '6':
		case '7':  case '8':  case '9':
		{
			mNUMBER(false);
			break;
		}
		case '"':
		{
			mQUOTEDSTR(false);
			break;
		}
		case '<':
		{
			mHTMLSTR(false);
			break;
		}
		default:
		{
			throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
		}
		}
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	protected final void mVALIDSTR(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = VALIDSTR;
		int _saveIndex;
		
		mALPHACHAR(false);
		{
		_loop73:
		do {
			switch ( LA(1)) {
			case 'A':  case 'B':  case 'C':  case 'D':
			case 'E':  case 'F':  case 'G':  case 'H':
			case 'I':  case 'J':  case 'K':  case 'L':
			case 'M':  case 'N':  case 'O':  case 'P':
			case 'Q':  case 'R':  case 'S':  case 'T':
			case 'U':  case 'V':  case 'W':  case 'X':
			case 'Y':  case 'Z':  case '_':  case 'a':
			case 'b':  case 'c':  case 'd':  case 'e':
			case 'f':  case 'g':  case 'h':  case 'i':
			case 'j':  case 'k':  case 'l':  case 'm':
			case 'n':  case 'o':  case 'p':  case 'q':
			case 'r':  case 's':  case 't':  case 'u':
			case 'v':  case 'w':  case 'x':  case 'y':
			case 'z':
			{
				mALPHACHAR(false);
				break;
			}
			case '0':  case '1':  case '2':  case '3':
			case '4':  case '5':  case '6':  case '7':
			case '8':  case '9':
			{
				matchRange('0','9');
				break;
			}
			default:
			{
				break _loop73;
			}
			}
		} while (true);
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	protected final void mNUMBER(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = NUMBER;
		int _saveIndex;
		
		{
		switch ( LA(1)) {
		case '-':
		{
			match('-');
			break;
		}
		case '0':  case '1':  case '2':  case '3':
		case '4':  case '5':  case '6':  case '7':
		case '8':  case '9':
		{
			break;
		}
		default:
		{
			throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
		}
		}
		}
		{
		int _cnt77=0;
		_loop77:
		do {
			if (((LA(1) >= '0' && LA(1) <= '9'))) {
				matchRange('0','9');
			}
			else {
				if ( _cnt77>=1 ) { break _loop77; } else {throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());}
			}
			
			_cnt77++;
		} while (true);
		}
		{
		if ((LA(1)=='.')) {
			match('.');
			{
			int _cnt80=0;
			_loop80:
			do {
				if (((LA(1) >= '0' && LA(1) <= '9'))) {
					matchRange('0','9');
				}
				else {
					if ( _cnt80>=1 ) { break _loop80; } else {throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());}
				}
				
				_cnt80++;
			} while (true);
			}
		}
		else {
		}
		
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	protected final void mQUOTEDSTR(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = QUOTEDSTR;
		int _saveIndex;
		
		_saveIndex=text.length();
		match('"');
		text.setLength(_saveIndex);
		{
		_loop86:
		do {
			boolean synPredMatched84 = false;
			if (((LA(1)=='\\'))) {
				int _m84 = mark();
				synPredMatched84 = true;
				inputState.guessing++;
				try {
					{
					match("\\\"");
					}
				}
				catch (RecognitionException pe) {
					synPredMatched84 = false;
				}
				rewind(_m84);
				inputState.guessing--;
			}
			if ( synPredMatched84 ) {
				match("\\\"");
			}
			else if ((_tokenSet_1.member(LA(1)))) {
				{
				match(_tokenSet_1);
				}
			}
			else {
				break _loop86;
			}
			
		} while (true);
		}
		_saveIndex=text.length();
		match('"');
		text.setLength(_saveIndex);
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	protected final void mHTMLSTR(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = HTMLSTR;
		int _saveIndex;
		
		match('<');
		{
		_loop89:
		do {
			if ((_tokenSet_2.member(LA(1)))) {
				matchNot('>');
			}
			else {
				break _loop89;
			}
			
		} while (true);
		}
		match('>');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	protected final void mCOMPASS_PT(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = COMPASS_PT;
		int _saveIndex;
		
		{
		switch ( LA(1)) {
		case 'e':
		{
			match("e");
			break;
		}
		case 'w':
		{
			match("w");
			break;
		}
		default:
			boolean synPredMatched60 = false;
			if (((LA(1)=='n'))) {
				int _m60 = mark();
				synPredMatched60 = true;
				inputState.guessing++;
				try {
					{
					match("ne");
					}
				}
				catch (RecognitionException pe) {
					synPredMatched60 = false;
				}
				rewind(_m60);
				inputState.guessing--;
			}
			if ( synPredMatched60 ) {
				match("ne");
			}
			else {
				boolean synPredMatched62 = false;
				if (((LA(1)=='n'))) {
					int _m62 = mark();
					synPredMatched62 = true;
					inputState.guessing++;
					try {
						{
						match("nw");
						}
					}
					catch (RecognitionException pe) {
						synPredMatched62 = false;
					}
					rewind(_m62);
					inputState.guessing--;
				}
				if ( synPredMatched62 ) {
					match("nw");
				}
				else {
					boolean synPredMatched64 = false;
					if (((LA(1)=='n'))) {
						int _m64 = mark();
						synPredMatched64 = true;
						inputState.guessing++;
						try {
							{
							match("node");
							}
						}
						catch (RecognitionException pe) {
							synPredMatched64 = false;
						}
						rewind(_m64);
						inputState.guessing--;
					}
					if ( synPredMatched64 ) {
						mNODE_LITERAL(false);
					}
					else if ((LA(1)=='n')) {
						match("n");
					}
					else {
						boolean synPredMatched66 = false;
						if (((LA(1)=='s'))) {
							int _m66 = mark();
							synPredMatched66 = true;
							inputState.guessing++;
							try {
								{
								match("se");
								}
							}
							catch (RecognitionException pe) {
								synPredMatched66 = false;
							}
							rewind(_m66);
							inputState.guessing--;
						}
						if ( synPredMatched66 ) {
							match("se");
						}
						else {
							boolean synPredMatched68 = false;
							if (((LA(1)=='s'))) {
								int _m68 = mark();
								synPredMatched68 = true;
								inputState.guessing++;
								try {
									{
									match("sw");
									}
								}
								catch (RecognitionException pe) {
									synPredMatched68 = false;
								}
								rewind(_m68);
								inputState.guessing--;
							}
							if ( synPredMatched68 ) {
								match("sw");
							}
							else if ((LA(1)=='s')) {
								match("s");
							}
						else {
							throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
						}
						}}}}}
						}
						if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
							_token = makeToken(_ttype);
							_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
						}
						_returnToken = _token;
					}
					
	protected final void mALPHACHAR(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = ALPHACHAR;
		int _saveIndex;
		
		{
		switch ( LA(1)) {
		case 'a':  case 'b':  case 'c':  case 'd':
		case 'e':  case 'f':  case 'g':  case 'h':
		case 'i':  case 'j':  case 'k':  case 'l':
		case 'm':  case 'n':  case 'o':  case 'p':
		case 'q':  case 'r':  case 's':  case 't':
		case 'u':  case 'v':  case 'w':  case 'x':
		case 'y':  case 'z':
		{
			matchRange('a','z');
			break;
		}
		case 'A':  case 'B':  case 'C':  case 'D':
		case 'E':  case 'F':  case 'G':  case 'H':
		case 'I':  case 'J':  case 'K':  case 'L':
		case 'M':  case 'N':  case 'O':  case 'P':
		case 'Q':  case 'R':  case 'S':  case 'T':
		case 'U':  case 'V':  case 'W':  case 'X':
		case 'Y':  case 'Z':
		{
			matchRange('A','Z');
			break;
		}
		case '_':
		{
			match('_');
			break;
		}
		default:
		{
			throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
		}
		}
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mWS(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = WS;
		int _saveIndex;
		
		{
		switch ( LA(1)) {
		case ' ':
		{
			match(' ');
			break;
		}
		case '\t':
		{
			match('\t');
			break;
		}
		case '\r':
		{
			match('\r');
			match('\n');
			if ( inputState.guessing==0 ) {
				newline();
			}
			break;
		}
		case '\n':
		{
			match('\n');
			if ( inputState.guessing==0 ) {
				newline();
			}
			break;
		}
		default:
		{
			throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
		}
		}
		}
		if ( inputState.guessing==0 ) {
			_ttype = Token.SKIP;
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mCOMMENT(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = COMMENT;
		int _saveIndex;
		
		{
		boolean synPredMatched95 = false;
		if (((LA(1)=='/'))) {
			int _m95 = mark();
			synPredMatched95 = true;
			inputState.guessing++;
			try {
				{
				match("/*");
				}
			}
			catch (RecognitionException pe) {
				synPredMatched95 = false;
			}
			rewind(_m95);
			inputState.guessing--;
		}
		if ( synPredMatched95 ) {
			mML_COMMENT(false);
		}
		else {
			boolean synPredMatched97 = false;
			if (((LA(1)=='/'))) {
				int _m97 = mark();
				synPredMatched97 = true;
				inputState.guessing++;
				try {
					{
					match("//");
					}
				}
				catch (RecognitionException pe) {
					synPredMatched97 = false;
				}
				rewind(_m97);
				inputState.guessing--;
			}
			if ( synPredMatched97 ) {
				mSL_COMMENT(false);
			}
			else {
				throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
			}
			}
			}
			if ( inputState.guessing==0 ) {
				_ttype = Token.SKIP; newline();
			}
			if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
				_token = makeToken(_ttype);
				_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
			}
			_returnToken = _token;
		}
		
	protected final void mML_COMMENT(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = ML_COMMENT;
		int _saveIndex;
		
		match("/*");
		{
		_loop107:
		do {
			if (((LA(1)=='*'))&&(LA(2)!='/')) {
				match('*');
			}
			else if ((LA(1)=='\r')) {
				match('\r');
				match('\n');
				if ( inputState.guessing==0 ) {
					newline();
				}
			}
			else if ((LA(1)=='\r')) {
				match('\r');
				if ( inputState.guessing==0 ) {
					newline();
				}
			}
			else if ((LA(1)=='\n')) {
				match('\n');
				if ( inputState.guessing==0 ) {
					newline();
				}
			}
			else if ((_tokenSet_3.member(LA(1)))) {
				{
				match(_tokenSet_3);
				}
			}
			else {
				break _loop107;
			}
			
		} while (true);
		}
		match("*/");
		if ( inputState.guessing==0 ) {
			_ttype = Token.SKIP;
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	protected final void mSL_COMMENT(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = SL_COMMENT;
		int _saveIndex;
		
		match("//");
		{
		_loop101:
		do {
			if ((_tokenSet_4.member(LA(1)))) {
				{
				match(_tokenSet_4);
				}
			}
			else {
				break _loop101;
			}
			
		} while (true);
		}
		{
		switch ( LA(1)) {
		case '\n':
		{
			match('\n');
			break;
		}
		case '\r':
		{
			match('\r');
			{
			if ((LA(1)=='\n')) {
				match('\n');
			}
			else {
			}
			
			}
			break;
		}
		default:
		{
			throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
		}
		}
		}
		if ( inputState.guessing==0 ) {
			_ttype = Token.SKIP; newline();
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { 1440905607333806080L, 576460745995190270L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = new long[8];
		data[0]=-17179869192L;
		for (int i = 1; i<=3; i++) { data[i]=-1L; }
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = new long[8];
		data[0]=-4611686018427387912L;
		for (int i = 1; i<=3; i++) { data[i]=-1L; }
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = new long[8];
		data[0]=-4398046520328L;
		for (int i = 1; i<=3; i++) { data[i]=-1L; }
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = new long[8];
		data[0]=-9224L;
		for (int i = 1; i<=3; i++) { data[i]=-1L; }
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	
	}
