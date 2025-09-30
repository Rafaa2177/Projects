// Generated from /Users/cristinasemikina/Desktop/isep/sem4pi-23-24-2dc4/jobs4u.plugin/src/main/java/plugin/interviews/grammarFiles/InterviewModel.g4 by ANTLR 4.13.1
package plugin.interviews.grammarFiles;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class InterviewModelParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, OPT_LETTERS=14, ANSWER=15, BOOLEAN=16, 
		DIGIT=17, DATE=18, COLLON=19, DECIMAL=20, SHORT_TEXT=21, TIME=22, NEWLINE=23, 
		WS=24;
	public static final int
		RULE_start = 0, RULE_interview_model = 1, RULE_specification = 2, RULE_boolean_spec = 3, 
		RULE_short_text_spec = 4, RULE_option_spec = 5, RULE_multi_spec = 6, RULE_digit_spec = 7, 
		RULE_decimal_spec = 8, RULE_date_spec = 9, RULE_time_spec = 10, RULE_range_spec = 11, 
		RULE_multi = 12, RULE_option = 13;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "interview_model", "specification", "boolean_spec", "short_text_spec", 
			"option_spec", "multi_spec", "digit_spec", "decimal_spec", "date_spec", 
			"time_spec", "range_spec", "multi", "option"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'/'", "'INTERVIEW ID'", "'BOOLEAN'", "'SHORT_TEXT'", "'OPTION'", 
			"'MULTIPLE_CHOICE'", "'DIGIT'", "'DECIMAL'", "'DATE'", "'TIME'", "'RANGE'", 
			"','", "')'", null, null, null, null, null, "':'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, "OPT_LETTERS", "ANSWER", "BOOLEAN", "DIGIT", "DATE", "COLLON", 
			"DECIMAL", "SHORT_TEXT", "TIME", "NEWLINE", "WS"
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
	public String getGrammarFileName() { return "InterviewModel.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public InterviewModelParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StartContext extends ParserRuleContext {
		public Interview_modelContext interview_model() {
			return getRuleContext(Interview_modelContext.class,0);
		}
		public TerminalNode EOF() { return getToken(InterviewModelParser.EOF, 0); }
		public List<TerminalNode> ANSWER() { return getTokens(InterviewModelParser.ANSWER); }
		public TerminalNode ANSWER(int i) {
			return getToken(InterviewModelParser.ANSWER, i);
		}
		public List<SpecificationContext> specification() {
			return getRuleContexts(SpecificationContext.class);
		}
		public SpecificationContext specification(int i) {
			return getRuleContext(SpecificationContext.class,i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(InterviewModelParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(InterviewModelParser.NEWLINE, i);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelListener ) ((InterviewModelListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelListener ) ((InterviewModelListener)listener).exitStart(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelVisitor ) return ((InterviewModelVisitor<? extends T>)visitor).visitStart(this);
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
			setState(28);
			interview_model();
			setState(35); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(29);
				match(ANSWER);
				setState(30);
				match(T__0);
				setState(31);
				specification();
				setState(33);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NEWLINE) {
					{
					setState(32);
					match(NEWLINE);
					}
				}

				}
				}
				setState(37); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ANSWER );
			setState(39);
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
	public static class Interview_modelContext extends ParserRuleContext {
		public TerminalNode COLLON() { return getToken(InterviewModelParser.COLLON, 0); }
		public TerminalNode DIGIT() { return getToken(InterviewModelParser.DIGIT, 0); }
		public TerminalNode NEWLINE() { return getToken(InterviewModelParser.NEWLINE, 0); }
		public Interview_modelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interview_model; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelListener ) ((InterviewModelListener)listener).enterInterview_model(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelListener ) ((InterviewModelListener)listener).exitInterview_model(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelVisitor ) return ((InterviewModelVisitor<? extends T>)visitor).visitInterview_model(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Interview_modelContext interview_model() throws RecognitionException {
		Interview_modelContext _localctx = new Interview_modelContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_interview_model);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(41);
			match(T__1);
			setState(42);
			match(COLLON);
			setState(43);
			match(DIGIT);
			setState(44);
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
		public Option_specContext option_spec() {
			return getRuleContext(Option_specContext.class,0);
		}
		public Multi_specContext multi_spec() {
			return getRuleContext(Multi_specContext.class,0);
		}
		public Digit_specContext digit_spec() {
			return getRuleContext(Digit_specContext.class,0);
		}
		public Decimal_specContext decimal_spec() {
			return getRuleContext(Decimal_specContext.class,0);
		}
		public Date_specContext date_spec() {
			return getRuleContext(Date_specContext.class,0);
		}
		public Time_specContext time_spec() {
			return getRuleContext(Time_specContext.class,0);
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
			if ( listener instanceof InterviewModelListener ) ((InterviewModelListener)listener).enterSpecification(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelListener ) ((InterviewModelListener)listener).exitSpecification(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelVisitor ) return ((InterviewModelVisitor<? extends T>)visitor).visitSpecification(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SpecificationContext specification() throws RecognitionException {
		SpecificationContext _localctx = new SpecificationContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_specification);
		try {
			setState(55);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				enterOuterAlt(_localctx, 1);
				{
				setState(46);
				boolean_spec();
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(47);
				short_text_spec();
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 3);
				{
				setState(48);
				option_spec();
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 4);
				{
				setState(49);
				multi_spec();
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 5);
				{
				setState(50);
				digit_spec();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 6);
				{
				setState(51);
				decimal_spec();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 7);
				{
				setState(52);
				date_spec();
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 8);
				{
				setState(53);
				time_spec();
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 9);
				{
				setState(54);
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
		public TerminalNode COLLON() { return getToken(InterviewModelParser.COLLON, 0); }
		public TerminalNode BOOLEAN() { return getToken(InterviewModelParser.BOOLEAN, 0); }
		public Boolean_specContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolean_spec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelListener ) ((InterviewModelListener)listener).enterBoolean_spec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelListener ) ((InterviewModelListener)listener).exitBoolean_spec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelVisitor ) return ((InterviewModelVisitor<? extends T>)visitor).visitBoolean_spec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Boolean_specContext boolean_spec() throws RecognitionException {
		Boolean_specContext _localctx = new Boolean_specContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_boolean_spec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(57);
			match(T__2);
			setState(58);
			match(COLLON);
			setState(59);
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
		public TerminalNode COLLON() { return getToken(InterviewModelParser.COLLON, 0); }
		public TerminalNode SHORT_TEXT() { return getToken(InterviewModelParser.SHORT_TEXT, 0); }
		public Short_text_specContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_short_text_spec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelListener ) ((InterviewModelListener)listener).enterShort_text_spec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelListener ) ((InterviewModelListener)listener).exitShort_text_spec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelVisitor ) return ((InterviewModelVisitor<? extends T>)visitor).visitShort_text_spec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Short_text_specContext short_text_spec() throws RecognitionException {
		Short_text_specContext _localctx = new Short_text_specContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_short_text_spec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61);
			match(T__3);
			setState(62);
			match(COLLON);
			setState(63);
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
	public static class Option_specContext extends ParserRuleContext {
		public TerminalNode COLLON() { return getToken(InterviewModelParser.COLLON, 0); }
		public OptionContext option() {
			return getRuleContext(OptionContext.class,0);
		}
		public Option_specContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_option_spec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelListener ) ((InterviewModelListener)listener).enterOption_spec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelListener ) ((InterviewModelListener)listener).exitOption_spec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelVisitor ) return ((InterviewModelVisitor<? extends T>)visitor).visitOption_spec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Option_specContext option_spec() throws RecognitionException {
		Option_specContext _localctx = new Option_specContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_option_spec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65);
			match(T__4);
			setState(66);
			match(COLLON);
			setState(67);
			option();
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
	public static class Multi_specContext extends ParserRuleContext {
		public TerminalNode COLLON() { return getToken(InterviewModelParser.COLLON, 0); }
		public MultiContext multi() {
			return getRuleContext(MultiContext.class,0);
		}
		public Multi_specContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multi_spec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelListener ) ((InterviewModelListener)listener).enterMulti_spec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelListener ) ((InterviewModelListener)listener).exitMulti_spec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelVisitor ) return ((InterviewModelVisitor<? extends T>)visitor).visitMulti_spec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Multi_specContext multi_spec() throws RecognitionException {
		Multi_specContext _localctx = new Multi_specContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_multi_spec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			match(T__5);
			setState(70);
			match(COLLON);
			setState(71);
			multi();
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
		public TerminalNode COLLON() { return getToken(InterviewModelParser.COLLON, 0); }
		public TerminalNode DIGIT() { return getToken(InterviewModelParser.DIGIT, 0); }
		public Digit_specContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_digit_spec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelListener ) ((InterviewModelListener)listener).enterDigit_spec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelListener ) ((InterviewModelListener)listener).exitDigit_spec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelVisitor ) return ((InterviewModelVisitor<? extends T>)visitor).visitDigit_spec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Digit_specContext digit_spec() throws RecognitionException {
		Digit_specContext _localctx = new Digit_specContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_digit_spec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(73);
			match(T__6);
			setState(74);
			match(COLLON);
			setState(75);
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
		public TerminalNode COLLON() { return getToken(InterviewModelParser.COLLON, 0); }
		public TerminalNode DECIMAL() { return getToken(InterviewModelParser.DECIMAL, 0); }
		public Decimal_specContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decimal_spec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelListener ) ((InterviewModelListener)listener).enterDecimal_spec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelListener ) ((InterviewModelListener)listener).exitDecimal_spec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelVisitor ) return ((InterviewModelVisitor<? extends T>)visitor).visitDecimal_spec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Decimal_specContext decimal_spec() throws RecognitionException {
		Decimal_specContext _localctx = new Decimal_specContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_decimal_spec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			match(T__7);
			setState(78);
			match(COLLON);
			setState(79);
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
		public TerminalNode COLLON() { return getToken(InterviewModelParser.COLLON, 0); }
		public TerminalNode DATE() { return getToken(InterviewModelParser.DATE, 0); }
		public Date_specContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_date_spec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelListener ) ((InterviewModelListener)listener).enterDate_spec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelListener ) ((InterviewModelListener)listener).exitDate_spec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelVisitor ) return ((InterviewModelVisitor<? extends T>)visitor).visitDate_spec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Date_specContext date_spec() throws RecognitionException {
		Date_specContext _localctx = new Date_specContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_date_spec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(81);
			match(T__8);
			setState(82);
			match(COLLON);
			setState(83);
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
	public static class Time_specContext extends ParserRuleContext {
		public TerminalNode COLLON() { return getToken(InterviewModelParser.COLLON, 0); }
		public TerminalNode TIME() { return getToken(InterviewModelParser.TIME, 0); }
		public Time_specContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_time_spec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelListener ) ((InterviewModelListener)listener).enterTime_spec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelListener ) ((InterviewModelListener)listener).exitTime_spec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelVisitor ) return ((InterviewModelVisitor<? extends T>)visitor).visitTime_spec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Time_specContext time_spec() throws RecognitionException {
		Time_specContext _localctx = new Time_specContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_time_spec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			match(T__9);
			setState(86);
			match(COLLON);
			setState(87);
			match(TIME);
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
		public TerminalNode COLLON() { return getToken(InterviewModelParser.COLLON, 0); }
		public TerminalNode DIGIT() { return getToken(InterviewModelParser.DIGIT, 0); }
		public Range_specContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_range_spec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelListener ) ((InterviewModelListener)listener).enterRange_spec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelListener ) ((InterviewModelListener)listener).exitRange_spec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelVisitor ) return ((InterviewModelVisitor<? extends T>)visitor).visitRange_spec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Range_specContext range_spec() throws RecognitionException {
		Range_specContext _localctx = new Range_specContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_range_spec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			match(T__10);
			setState(90);
			match(COLLON);
			setState(91);
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
	public static class MultiContext extends ParserRuleContext {
		public List<OptionContext> option() {
			return getRuleContexts(OptionContext.class);
		}
		public OptionContext option(int i) {
			return getRuleContext(OptionContext.class,i);
		}
		public MultiContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multi; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelListener ) ((InterviewModelListener)listener).enterMulti(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelListener ) ((InterviewModelListener)listener).exitMulti(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelVisitor ) return ((InterviewModelVisitor<? extends T>)visitor).visitMulti(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultiContext multi() throws RecognitionException {
		MultiContext _localctx = new MultiContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_multi);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
			option();
			setState(98);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(94);
				match(T__11);
				setState(95);
				option();
				}
				}
				setState(100);
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
	public static class OptionContext extends ParserRuleContext {
		public TerminalNode OPT_LETTERS() { return getToken(InterviewModelParser.OPT_LETTERS, 0); }
		public OptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_option; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelListener ) ((InterviewModelListener)listener).enterOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelListener ) ((InterviewModelListener)listener).exitOption(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelVisitor ) return ((InterviewModelVisitor<? extends T>)visitor).visitOption(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OptionContext option() throws RecognitionException {
		OptionContext _localctx = new OptionContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_option);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(101);
			match(OPT_LETTERS);
			setState(102);
			match(T__12);
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
		"\u0004\u0001\u0018i\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0003\u0000\"\b\u0000\u0004\u0000$\b\u0000\u000b\u0000\f"+
		"\u0000%\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u00028\b"+
		"\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t"+
		"\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0005\fa\b\f\n"+
		"\f\f\fd\t\f\u0001\r\u0001\r\u0001\r\u0001\r\u0000\u0000\u000e\u0000\u0002"+
		"\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u0000\u0000"+
		"e\u0000\u001c\u0001\u0000\u0000\u0000\u0002)\u0001\u0000\u0000\u0000\u0004"+
		"7\u0001\u0000\u0000\u0000\u00069\u0001\u0000\u0000\u0000\b=\u0001\u0000"+
		"\u0000\u0000\nA\u0001\u0000\u0000\u0000\fE\u0001\u0000\u0000\u0000\u000e"+
		"I\u0001\u0000\u0000\u0000\u0010M\u0001\u0000\u0000\u0000\u0012Q\u0001"+
		"\u0000\u0000\u0000\u0014U\u0001\u0000\u0000\u0000\u0016Y\u0001\u0000\u0000"+
		"\u0000\u0018]\u0001\u0000\u0000\u0000\u001ae\u0001\u0000\u0000\u0000\u001c"+
		"#\u0003\u0002\u0001\u0000\u001d\u001e\u0005\u000f\u0000\u0000\u001e\u001f"+
		"\u0005\u0001\u0000\u0000\u001f!\u0003\u0004\u0002\u0000 \"\u0005\u0017"+
		"\u0000\u0000! \u0001\u0000\u0000\u0000!\"\u0001\u0000\u0000\u0000\"$\u0001"+
		"\u0000\u0000\u0000#\u001d\u0001\u0000\u0000\u0000$%\u0001\u0000\u0000"+
		"\u0000%#\u0001\u0000\u0000\u0000%&\u0001\u0000\u0000\u0000&\'\u0001\u0000"+
		"\u0000\u0000\'(\u0005\u0000\u0000\u0001(\u0001\u0001\u0000\u0000\u0000"+
		")*\u0005\u0002\u0000\u0000*+\u0005\u0013\u0000\u0000+,\u0005\u0011\u0000"+
		"\u0000,-\u0005\u0017\u0000\u0000-\u0003\u0001\u0000\u0000\u0000.8\u0003"+
		"\u0006\u0003\u0000/8\u0003\b\u0004\u000008\u0003\n\u0005\u000018\u0003"+
		"\f\u0006\u000028\u0003\u000e\u0007\u000038\u0003\u0010\b\u000048\u0003"+
		"\u0012\t\u000058\u0003\u0014\n\u000068\u0003\u0016\u000b\u00007.\u0001"+
		"\u0000\u0000\u00007/\u0001\u0000\u0000\u000070\u0001\u0000\u0000\u0000"+
		"71\u0001\u0000\u0000\u000072\u0001\u0000\u0000\u000073\u0001\u0000\u0000"+
		"\u000074\u0001\u0000\u0000\u000075\u0001\u0000\u0000\u000076\u0001\u0000"+
		"\u0000\u00008\u0005\u0001\u0000\u0000\u00009:\u0005\u0003\u0000\u0000"+
		":;\u0005\u0013\u0000\u0000;<\u0005\u0010\u0000\u0000<\u0007\u0001\u0000"+
		"\u0000\u0000=>\u0005\u0004\u0000\u0000>?\u0005\u0013\u0000\u0000?@\u0005"+
		"\u0015\u0000\u0000@\t\u0001\u0000\u0000\u0000AB\u0005\u0005\u0000\u0000"+
		"BC\u0005\u0013\u0000\u0000CD\u0003\u001a\r\u0000D\u000b\u0001\u0000\u0000"+
		"\u0000EF\u0005\u0006\u0000\u0000FG\u0005\u0013\u0000\u0000GH\u0003\u0018"+
		"\f\u0000H\r\u0001\u0000\u0000\u0000IJ\u0005\u0007\u0000\u0000JK\u0005"+
		"\u0013\u0000\u0000KL\u0005\u0011\u0000\u0000L\u000f\u0001\u0000\u0000"+
		"\u0000MN\u0005\b\u0000\u0000NO\u0005\u0013\u0000\u0000OP\u0005\u0014\u0000"+
		"\u0000P\u0011\u0001\u0000\u0000\u0000QR\u0005\t\u0000\u0000RS\u0005\u0013"+
		"\u0000\u0000ST\u0005\u0012\u0000\u0000T\u0013\u0001\u0000\u0000\u0000"+
		"UV\u0005\n\u0000\u0000VW\u0005\u0013\u0000\u0000WX\u0005\u0016\u0000\u0000"+
		"X\u0015\u0001\u0000\u0000\u0000YZ\u0005\u000b\u0000\u0000Z[\u0005\u0013"+
		"\u0000\u0000[\\\u0005\u0011\u0000\u0000\\\u0017\u0001\u0000\u0000\u0000"+
		"]b\u0003\u001a\r\u0000^_\u0005\f\u0000\u0000_a\u0003\u001a\r\u0000`^\u0001"+
		"\u0000\u0000\u0000ad\u0001\u0000\u0000\u0000b`\u0001\u0000\u0000\u0000"+
		"bc\u0001\u0000\u0000\u0000c\u0019\u0001\u0000\u0000\u0000db\u0001\u0000"+
		"\u0000\u0000ef\u0005\u000e\u0000\u0000fg\u0005\r\u0000\u0000g\u001b\u0001"+
		"\u0000\u0000\u0000\u0004!%7b";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}