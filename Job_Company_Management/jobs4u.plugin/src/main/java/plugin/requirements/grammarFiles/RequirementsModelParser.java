// Generated from /Users/cristinasemikina/Desktop/isep/sem4pi-23-24-2dc4/jobs4u.plugin/src/main/java/plugin/requirements/grammarFiles/RequirementsModel.g4 by ANTLR 4.13.1
package plugin.requirements.grammarFiles;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class RequirementsModelParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, OPT_LETTERS=14, REQ=15, EDUCATION_LEVEL=16, 
		LANGUAGES=17, PROGRAMMING_LANGUAGES=18, BOOLEAN=19, DIGIT=20, COLLON=21, 
		DECIMAL=22, SHORT_TEXT=23, LONG_TEXT=24, DATE=25, RANGE=26, NEWLINE=27, 
		WS=28;
	public static final int
		RULE_start = 0, RULE_requirement_model = 1, RULE_specification = 2, RULE_boolean_spec = 3, 
		RULE_short_text_spec = 4, RULE_long_text_spec = 5, RULE_education_level_spec = 6, 
		RULE_languages_spec = 7, RULE_programming_languages_spec = 8, RULE_digit_spec = 9, 
		RULE_decimal_spec = 10, RULE_date_spec = 11, RULE_range_spec = 12, RULE_multi_planguages = 13, 
		RULE_multi_languages = 14;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "requirement_model", "specification", "boolean_spec", "short_text_spec", 
			"long_text_spec", "education_level_spec", "languages_spec", "programming_languages_spec", 
			"digit_spec", "decimal_spec", "date_spec", "range_spec", "multi_planguages", 
			"multi_languages"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'/'", "'REQUIREMENT ID'", "'BOOLEAN'", "'SHORT_TEXT'", "'LONG_TEXT'", 
			"'EDUCATION_LEVEL'", "'LANGUAGES'", "'PROGRAMMING_LANGUAGES'", "'DIGIT'", 
			"'DECIMAL'", "'DATE'", "'RANGE'", "','", null, null, null, null, null, 
			null, null, "':'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, "OPT_LETTERS", "REQ", "EDUCATION_LEVEL", "LANGUAGES", "PROGRAMMING_LANGUAGES", 
			"BOOLEAN", "DIGIT", "COLLON", "DECIMAL", "SHORT_TEXT", "LONG_TEXT", "DATE", 
			"RANGE", "NEWLINE", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "RequirementsModel.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public RequirementsModelParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StartContext extends ParserRuleContext {
		public Requirement_modelContext requirement_model() {
			return getRuleContext(Requirement_modelContext.class,0);
		}
		public TerminalNode EOF() { return getToken(RequirementsModelParser.EOF, 0); }
		public List<TerminalNode> REQ() { return getTokens(RequirementsModelParser.REQ); }
		public TerminalNode REQ(int i) {
			return getToken(RequirementsModelParser.REQ, i);
		}
		public List<SpecificationContext> specification() {
			return getRuleContexts(SpecificationContext.class);
		}
		public SpecificationContext specification(int i) {
			return getRuleContext(SpecificationContext.class,i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(RequirementsModelParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(RequirementsModelParser.NEWLINE, i);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsModelListener ) ((RequirementsModelListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsModelListener ) ((RequirementsModelListener)listener).exitStart(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RequirementsModelVisitor ) return ((RequirementsModelVisitor<? extends T>)visitor).visitStart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(30);
			requirement_model();
			setState(37); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(31);
				match(REQ);
				setState(32);
				match(T__0);
				setState(33);
				specification();
				setState(35);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NEWLINE) {
					{
					setState(34);
					match(NEWLINE);
					}
				}

				}
				}
				setState(39); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==REQ );
			setState(41);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Requirement_modelContext extends ParserRuleContext {
		public TerminalNode COLLON() { return getToken(RequirementsModelParser.COLLON, 0); }
		public TerminalNode DIGIT() { return getToken(RequirementsModelParser.DIGIT, 0); }
		public TerminalNode NEWLINE() { return getToken(RequirementsModelParser.NEWLINE, 0); }
		public Requirement_modelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_requirement_model; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsModelListener ) ((RequirementsModelListener)listener).enterRequirement_model(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsModelListener ) ((RequirementsModelListener)listener).exitRequirement_model(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RequirementsModelVisitor ) return ((RequirementsModelVisitor<? extends T>)visitor).visitRequirement_model(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Requirement_modelContext requirement_model() throws RecognitionException {
		Requirement_modelContext _localctx = new Requirement_modelContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_requirement_model);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43);
			match(T__1);
			setState(44);
			match(COLLON);
			setState(45);
			match(DIGIT);
			setState(46);
			match(NEWLINE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SpecificationContext extends ParserRuleContext {
		public Boolean_specContext boolean_spec() {
			return getRuleContext(Boolean_specContext.class,0);
		}
		public Short_text_specContext short_text_spec() {
			return getRuleContext(Short_text_specContext.class,0);
		}
		public Long_text_specContext long_text_spec() {
			return getRuleContext(Long_text_specContext.class,0);
		}
		public Education_level_specContext education_level_spec() {
			return getRuleContext(Education_level_specContext.class,0);
		}
		public Languages_specContext languages_spec() {
			return getRuleContext(Languages_specContext.class,0);
		}
		public Digit_specContext digit_spec() {
			return getRuleContext(Digit_specContext.class,0);
		}
		public Decimal_specContext decimal_spec() {
			return getRuleContext(Decimal_specContext.class,0);
		}
		public Programming_languages_specContext programming_languages_spec() {
			return getRuleContext(Programming_languages_specContext.class,0);
		}
		public Date_specContext date_spec() {
			return getRuleContext(Date_specContext.class,0);
		}
		public Range_specContext range_spec() {
			return getRuleContext(Range_specContext.class,0);
		}
		public SpecificationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_specification; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsModelListener ) ((RequirementsModelListener)listener).enterSpecification(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsModelListener ) ((RequirementsModelListener)listener).exitSpecification(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RequirementsModelVisitor ) return ((RequirementsModelVisitor<? extends T>)visitor).visitSpecification(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SpecificationContext specification() throws RecognitionException {
		SpecificationContext _localctx = new SpecificationContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_specification);
		try {
			setState(58);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				enterOuterAlt(_localctx, 1);
				{
				setState(48);
				boolean_spec();
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(49);
				short_text_spec();
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 3);
				{
				setState(50);
				long_text_spec();
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 4);
				{
				setState(51);
				education_level_spec();
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 5);
				{
				setState(52);
				languages_spec();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 6);
				{
				setState(53);
				digit_spec();
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 7);
				{
				setState(54);
				decimal_spec();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 8);
				{
				setState(55);
				programming_languages_spec();
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 9);
				{
				setState(56);
				date_spec();
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 10);
				{
				setState(57);
				range_spec();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Boolean_specContext extends ParserRuleContext {
		public TerminalNode COLLON() { return getToken(RequirementsModelParser.COLLON, 0); }
		public TerminalNode BOOLEAN() { return getToken(RequirementsModelParser.BOOLEAN, 0); }
		public Boolean_specContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolean_spec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsModelListener ) ((RequirementsModelListener)listener).enterBoolean_spec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsModelListener ) ((RequirementsModelListener)listener).exitBoolean_spec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RequirementsModelVisitor ) return ((RequirementsModelVisitor<? extends T>)visitor).visitBoolean_spec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Boolean_specContext boolean_spec() throws RecognitionException {
		Boolean_specContext _localctx = new Boolean_specContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_boolean_spec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(60);
			match(T__2);
			setState(61);
			match(COLLON);
			setState(62);
			match(BOOLEAN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Short_text_specContext extends ParserRuleContext {
		public TerminalNode COLLON() { return getToken(RequirementsModelParser.COLLON, 0); }
		public TerminalNode SHORT_TEXT() { return getToken(RequirementsModelParser.SHORT_TEXT, 0); }
		public Short_text_specContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_short_text_spec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsModelListener ) ((RequirementsModelListener)listener).enterShort_text_spec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsModelListener ) ((RequirementsModelListener)listener).exitShort_text_spec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RequirementsModelVisitor ) return ((RequirementsModelVisitor<? extends T>)visitor).visitShort_text_spec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Short_text_specContext short_text_spec() throws RecognitionException {
		Short_text_specContext _localctx = new Short_text_specContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_short_text_spec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			match(T__3);
			setState(65);
			match(COLLON);
			setState(66);
			match(SHORT_TEXT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Long_text_specContext extends ParserRuleContext {
		public TerminalNode COLLON() { return getToken(RequirementsModelParser.COLLON, 0); }
		public TerminalNode LONG_TEXT() { return getToken(RequirementsModelParser.LONG_TEXT, 0); }
		public Long_text_specContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_long_text_spec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsModelListener ) ((RequirementsModelListener)listener).enterLong_text_spec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsModelListener ) ((RequirementsModelListener)listener).exitLong_text_spec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RequirementsModelVisitor ) return ((RequirementsModelVisitor<? extends T>)visitor).visitLong_text_spec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Long_text_specContext long_text_spec() throws RecognitionException {
		Long_text_specContext _localctx = new Long_text_specContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_long_text_spec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			match(T__4);
			setState(69);
			match(COLLON);
			setState(70);
			match(LONG_TEXT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Education_level_specContext extends ParserRuleContext {
		public TerminalNode COLLON() { return getToken(RequirementsModelParser.COLLON, 0); }
		public TerminalNode EDUCATION_LEVEL() { return getToken(RequirementsModelParser.EDUCATION_LEVEL, 0); }
		public Education_level_specContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_education_level_spec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsModelListener ) ((RequirementsModelListener)listener).enterEducation_level_spec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsModelListener ) ((RequirementsModelListener)listener).exitEducation_level_spec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RequirementsModelVisitor ) return ((RequirementsModelVisitor<? extends T>)visitor).visitEducation_level_spec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Education_level_specContext education_level_spec() throws RecognitionException {
		Education_level_specContext _localctx = new Education_level_specContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_education_level_spec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			match(T__5);
			setState(73);
			match(COLLON);
			setState(74);
			match(EDUCATION_LEVEL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Languages_specContext extends ParserRuleContext {
		public TerminalNode COLLON() { return getToken(RequirementsModelParser.COLLON, 0); }
		public Multi_languagesContext multi_languages() {
			return getRuleContext(Multi_languagesContext.class,0);
		}
		public Languages_specContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_languages_spec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsModelListener ) ((RequirementsModelListener)listener).enterLanguages_spec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsModelListener ) ((RequirementsModelListener)listener).exitLanguages_spec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RequirementsModelVisitor ) return ((RequirementsModelVisitor<? extends T>)visitor).visitLanguages_spec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Languages_specContext languages_spec() throws RecognitionException {
		Languages_specContext _localctx = new Languages_specContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_languages_spec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
			match(T__6);
			setState(77);
			match(COLLON);
			setState(78);
			multi_languages();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Programming_languages_specContext extends ParserRuleContext {
		public TerminalNode COLLON() { return getToken(RequirementsModelParser.COLLON, 0); }
		public Multi_planguagesContext multi_planguages() {
			return getRuleContext(Multi_planguagesContext.class,0);
		}
		public Programming_languages_specContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_programming_languages_spec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsModelListener ) ((RequirementsModelListener)listener).enterProgramming_languages_spec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsModelListener ) ((RequirementsModelListener)listener).exitProgramming_languages_spec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RequirementsModelVisitor ) return ((RequirementsModelVisitor<? extends T>)visitor).visitProgramming_languages_spec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Programming_languages_specContext programming_languages_spec() throws RecognitionException {
		Programming_languages_specContext _localctx = new Programming_languages_specContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_programming_languages_spec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			match(T__7);
			setState(81);
			match(COLLON);
			setState(82);
			multi_planguages();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Digit_specContext extends ParserRuleContext {
		public TerminalNode COLLON() { return getToken(RequirementsModelParser.COLLON, 0); }
		public TerminalNode DIGIT() { return getToken(RequirementsModelParser.DIGIT, 0); }
		public Digit_specContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_digit_spec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsModelListener ) ((RequirementsModelListener)listener).enterDigit_spec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsModelListener ) ((RequirementsModelListener)listener).exitDigit_spec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RequirementsModelVisitor ) return ((RequirementsModelVisitor<? extends T>)visitor).visitDigit_spec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Digit_specContext digit_spec() throws RecognitionException {
		Digit_specContext _localctx = new Digit_specContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_digit_spec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			match(T__8);
			setState(85);
			match(COLLON);
			setState(86);
			match(DIGIT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Decimal_specContext extends ParserRuleContext {
		public TerminalNode COLLON() { return getToken(RequirementsModelParser.COLLON, 0); }
		public TerminalNode DECIMAL() { return getToken(RequirementsModelParser.DECIMAL, 0); }
		public Decimal_specContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decimal_spec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsModelListener ) ((RequirementsModelListener)listener).enterDecimal_spec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsModelListener ) ((RequirementsModelListener)listener).exitDecimal_spec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RequirementsModelVisitor ) return ((RequirementsModelVisitor<? extends T>)visitor).visitDecimal_spec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Decimal_specContext decimal_spec() throws RecognitionException {
		Decimal_specContext _localctx = new Decimal_specContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_decimal_spec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			match(T__9);
			setState(89);
			match(COLLON);
			setState(90);
			match(DECIMAL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Date_specContext extends ParserRuleContext {
		public TerminalNode COLLON() { return getToken(RequirementsModelParser.COLLON, 0); }
		public TerminalNode DATE() { return getToken(RequirementsModelParser.DATE, 0); }
		public Date_specContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_date_spec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsModelListener ) ((RequirementsModelListener)listener).enterDate_spec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsModelListener ) ((RequirementsModelListener)listener).exitDate_spec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RequirementsModelVisitor ) return ((RequirementsModelVisitor<? extends T>)visitor).visitDate_spec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Date_specContext date_spec() throws RecognitionException {
		Date_specContext _localctx = new Date_specContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_date_spec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			match(T__10);
			setState(93);
			match(COLLON);
			setState(94);
			match(DATE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Range_specContext extends ParserRuleContext {
		public TerminalNode COLLON() { return getToken(RequirementsModelParser.COLLON, 0); }
		public TerminalNode RANGE() { return getToken(RequirementsModelParser.RANGE, 0); }
		public Range_specContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_range_spec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsModelListener ) ((RequirementsModelListener)listener).enterRange_spec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsModelListener ) ((RequirementsModelListener)listener).exitRange_spec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RequirementsModelVisitor ) return ((RequirementsModelVisitor<? extends T>)visitor).visitRange_spec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Range_specContext range_spec() throws RecognitionException {
		Range_specContext _localctx = new Range_specContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_range_spec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			match(T__11);
			setState(97);
			match(COLLON);
			setState(98);
			match(RANGE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Multi_planguagesContext extends ParserRuleContext {
		public List<TerminalNode> PROGRAMMING_LANGUAGES() { return getTokens(RequirementsModelParser.PROGRAMMING_LANGUAGES); }
		public TerminalNode PROGRAMMING_LANGUAGES(int i) {
			return getToken(RequirementsModelParser.PROGRAMMING_LANGUAGES, i);
		}
		public Multi_planguagesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multi_planguages; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsModelListener ) ((RequirementsModelListener)listener).enterMulti_planguages(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsModelListener ) ((RequirementsModelListener)listener).exitMulti_planguages(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RequirementsModelVisitor ) return ((RequirementsModelVisitor<? extends T>)visitor).visitMulti_planguages(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Multi_planguagesContext multi_planguages() throws RecognitionException {
		Multi_planguagesContext _localctx = new Multi_planguagesContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_multi_planguages);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
			match(PROGRAMMING_LANGUAGES);
			setState(105);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__12) {
				{
				{
				setState(101);
				match(T__12);
				setState(102);
				match(PROGRAMMING_LANGUAGES);
				}
				}
				setState(107);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Multi_languagesContext extends ParserRuleContext {
		public List<TerminalNode> LANGUAGES() { return getTokens(RequirementsModelParser.LANGUAGES); }
		public TerminalNode LANGUAGES(int i) {
			return getToken(RequirementsModelParser.LANGUAGES, i);
		}
		public Multi_languagesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multi_languages; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsModelListener ) ((RequirementsModelListener)listener).enterMulti_languages(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsModelListener ) ((RequirementsModelListener)listener).exitMulti_languages(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RequirementsModelVisitor ) return ((RequirementsModelVisitor<? extends T>)visitor).visitMulti_languages(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Multi_languagesContext multi_languages() throws RecognitionException {
		Multi_languagesContext _localctx = new Multi_languagesContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_multi_languages);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			match(LANGUAGES);
			setState(113);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__12) {
				{
				{
				setState(109);
				match(T__12);
				setState(110);
				match(LANGUAGES);
				}
				}
				setState(115);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u001cu\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0003\u0000$\b\u0000\u0004\u0000"+
		"&\b\u0000\u000b\u0000\f\u0000\'\u0001\u0000\u0001\u0000\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0003\u0002;\b\u0002\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0005\rh\b\r\n\r\f"+
		"\rk\t\r\u0001\u000e\u0001\u000e\u0001\u000e\u0005\u000ep\b\u000e\n\u000e"+
		"\f\u000es\t\u000e\u0001\u000e\u0000\u0000\u000f\u0000\u0002\u0004\u0006"+
		"\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u0000\u0000r\u0000"+
		"\u001e\u0001\u0000\u0000\u0000\u0002+\u0001\u0000\u0000\u0000\u0004:\u0001"+
		"\u0000\u0000\u0000\u0006<\u0001\u0000\u0000\u0000\b@\u0001\u0000\u0000"+
		"\u0000\nD\u0001\u0000\u0000\u0000\fH\u0001\u0000\u0000\u0000\u000eL\u0001"+
		"\u0000\u0000\u0000\u0010P\u0001\u0000\u0000\u0000\u0012T\u0001\u0000\u0000"+
		"\u0000\u0014X\u0001\u0000\u0000\u0000\u0016\\\u0001\u0000\u0000\u0000"+
		"\u0018`\u0001\u0000\u0000\u0000\u001ad\u0001\u0000\u0000\u0000\u001cl"+
		"\u0001\u0000\u0000\u0000\u001e%\u0003\u0002\u0001\u0000\u001f \u0005\u000f"+
		"\u0000\u0000 !\u0005\u0001\u0000\u0000!#\u0003\u0004\u0002\u0000\"$\u0005"+
		"\u001b\u0000\u0000#\"\u0001\u0000\u0000\u0000#$\u0001\u0000\u0000\u0000"+
		"$&\u0001\u0000\u0000\u0000%\u001f\u0001\u0000\u0000\u0000&\'\u0001\u0000"+
		"\u0000\u0000\'%\u0001\u0000\u0000\u0000\'(\u0001\u0000\u0000\u0000()\u0001"+
		"\u0000\u0000\u0000)*\u0005\u0000\u0000\u0001*\u0001\u0001\u0000\u0000"+
		"\u0000+,\u0005\u0002\u0000\u0000,-\u0005\u0015\u0000\u0000-.\u0005\u0014"+
		"\u0000\u0000./\u0005\u001b\u0000\u0000/\u0003\u0001\u0000\u0000\u0000"+
		"0;\u0003\u0006\u0003\u00001;\u0003\b\u0004\u00002;\u0003\n\u0005\u0000"+
		"3;\u0003\f\u0006\u00004;\u0003\u000e\u0007\u00005;\u0003\u0012\t\u0000"+
		"6;\u0003\u0014\n\u00007;\u0003\u0010\b\u00008;\u0003\u0016\u000b\u0000"+
		"9;\u0003\u0018\f\u0000:0\u0001\u0000\u0000\u0000:1\u0001\u0000\u0000\u0000"+
		":2\u0001\u0000\u0000\u0000:3\u0001\u0000\u0000\u0000:4\u0001\u0000\u0000"+
		"\u0000:5\u0001\u0000\u0000\u0000:6\u0001\u0000\u0000\u0000:7\u0001\u0000"+
		"\u0000\u0000:8\u0001\u0000\u0000\u0000:9\u0001\u0000\u0000\u0000;\u0005"+
		"\u0001\u0000\u0000\u0000<=\u0005\u0003\u0000\u0000=>\u0005\u0015\u0000"+
		"\u0000>?\u0005\u0013\u0000\u0000?\u0007\u0001\u0000\u0000\u0000@A\u0005"+
		"\u0004\u0000\u0000AB\u0005\u0015\u0000\u0000BC\u0005\u0017\u0000\u0000"+
		"C\t\u0001\u0000\u0000\u0000DE\u0005\u0005\u0000\u0000EF\u0005\u0015\u0000"+
		"\u0000FG\u0005\u0018\u0000\u0000G\u000b\u0001\u0000\u0000\u0000HI\u0005"+
		"\u0006\u0000\u0000IJ\u0005\u0015\u0000\u0000JK\u0005\u0010\u0000\u0000"+
		"K\r\u0001\u0000\u0000\u0000LM\u0005\u0007\u0000\u0000MN\u0005\u0015\u0000"+
		"\u0000NO\u0003\u001c\u000e\u0000O\u000f\u0001\u0000\u0000\u0000PQ\u0005"+
		"\b\u0000\u0000QR\u0005\u0015\u0000\u0000RS\u0003\u001a\r\u0000S\u0011"+
		"\u0001\u0000\u0000\u0000TU\u0005\t\u0000\u0000UV\u0005\u0015\u0000\u0000"+
		"VW\u0005\u0014\u0000\u0000W\u0013\u0001\u0000\u0000\u0000XY\u0005\n\u0000"+
		"\u0000YZ\u0005\u0015\u0000\u0000Z[\u0005\u0016\u0000\u0000[\u0015\u0001"+
		"\u0000\u0000\u0000\\]\u0005\u000b\u0000\u0000]^\u0005\u0015\u0000\u0000"+
		"^_\u0005\u0019\u0000\u0000_\u0017\u0001\u0000\u0000\u0000`a\u0005\f\u0000"+
		"\u0000ab\u0005\u0015\u0000\u0000bc\u0005\u001a\u0000\u0000c\u0019\u0001"+
		"\u0000\u0000\u0000di\u0005\u0012\u0000\u0000ef\u0005\r\u0000\u0000fh\u0005"+
		"\u0012\u0000\u0000ge\u0001\u0000\u0000\u0000hk\u0001\u0000\u0000\u0000"+
		"ig\u0001\u0000\u0000\u0000ij\u0001\u0000\u0000\u0000j\u001b\u0001\u0000"+
		"\u0000\u0000ki\u0001\u0000\u0000\u0000lq\u0005\u0011\u0000\u0000mn\u0005"+
		"\r\u0000\u0000np\u0005\u0011\u0000\u0000om\u0001\u0000\u0000\u0000ps\u0001"+
		"\u0000\u0000\u0000qo\u0001\u0000\u0000\u0000qr\u0001\u0000\u0000\u0000"+
		"r\u001d\u0001\u0000\u0000\u0000sq\u0001\u0000\u0000\u0000\u0005#\':iq";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}