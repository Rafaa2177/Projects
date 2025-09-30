// Generated from /Users/cristinasemikina/Desktop/isep/sem4pi-23-24-2dc4/jobs4.plugin/src/main/java/requirementsmodel/fiveyearsjava/ReqModels5YearsJava.g4 by ANTLR 4.13.1
package oldpackages.requirementsmodel.fiveyearsjava.generatedG4;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class ReqModels5YearsJavaParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		YEARS_EXPERIENCE=1, PROGRAMMING_LANGUAGES=2, JAVA=3, AT_LEAST_5_YEARS=4, 
		COLLON=5, WS=6;
	public static final int
		RULE_start = 0, RULE_specification = 1, RULE_experienceRequirement = 2, 
		RULE_languageRequirement = 3;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "specification", "experienceRequirement", "languageRequirement"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'years-experience'", "'programming-languages'", null, null, "':'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "YEARS_EXPERIENCE", "PROGRAMMING_LANGUAGES", "JAVA", "AT_LEAST_5_YEARS", 
			"COLLON", "WS"
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
	public String getGrammarFileName() { return "ReqModels5YearsJava.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ReqModels5YearsJavaParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StartContext extends ParserRuleContext {
		public SpecificationContext specification() {
			return getRuleContext(SpecificationContext.class,0);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReqModels5YearsJavaListener ) ((ReqModels5YearsJavaListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReqModels5YearsJavaListener ) ((ReqModels5YearsJavaListener)listener).exitStart(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReqModels5YearsJavaVisitor) return ((ReqModels5YearsJavaVisitor<? extends T>)visitor).visitStart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(8);
			specification();
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
		public ExperienceRequirementContext experienceRequirement() {
			return getRuleContext(ExperienceRequirementContext.class,0);
		}
		public LanguageRequirementContext languageRequirement() {
			return getRuleContext(LanguageRequirementContext.class,0);
		}
		public SpecificationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_specification; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReqModels5YearsJavaListener ) ((ReqModels5YearsJavaListener)listener).enterSpecification(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReqModels5YearsJavaListener ) ((ReqModels5YearsJavaListener)listener).exitSpecification(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReqModels5YearsJavaVisitor ) return ((ReqModels5YearsJavaVisitor<? extends T>)visitor).visitSpecification(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SpecificationContext specification() throws RecognitionException {
		SpecificationContext _localctx = new SpecificationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_specification);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(10);
			experienceRequirement();
			setState(11);
			languageRequirement();
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
	public static class ExperienceRequirementContext extends ParserRuleContext {
		public TerminalNode YEARS_EXPERIENCE() { return getToken(ReqModels5YearsJavaParser.YEARS_EXPERIENCE, 0); }
		public TerminalNode COLLON() { return getToken(ReqModels5YearsJavaParser.COLLON, 0); }
		public TerminalNode AT_LEAST_5_YEARS() { return getToken(ReqModels5YearsJavaParser.AT_LEAST_5_YEARS, 0); }
		public ExperienceRequirementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_experienceRequirement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReqModels5YearsJavaListener ) ((ReqModels5YearsJavaListener)listener).enterExperienceRequirement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReqModels5YearsJavaListener ) ((ReqModels5YearsJavaListener)listener).exitExperienceRequirement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReqModels5YearsJavaVisitor ) return ((ReqModels5YearsJavaVisitor<? extends T>)visitor).visitExperienceRequirement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExperienceRequirementContext experienceRequirement() throws RecognitionException {
		ExperienceRequirementContext _localctx = new ExperienceRequirementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_experienceRequirement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(13);
			match(YEARS_EXPERIENCE);
			setState(14);
			match(COLLON);
			setState(15);
			match(AT_LEAST_5_YEARS);
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
	public static class LanguageRequirementContext extends ParserRuleContext {
		public TerminalNode PROGRAMMING_LANGUAGES() { return getToken(ReqModels5YearsJavaParser.PROGRAMMING_LANGUAGES, 0); }
		public TerminalNode COLLON() { return getToken(ReqModels5YearsJavaParser.COLLON, 0); }
		public TerminalNode JAVA() { return getToken(ReqModels5YearsJavaParser.JAVA, 0); }
		public LanguageRequirementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_languageRequirement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReqModels5YearsJavaListener ) ((ReqModels5YearsJavaListener)listener).enterLanguageRequirement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReqModels5YearsJavaListener ) ((ReqModels5YearsJavaListener)listener).exitLanguageRequirement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReqModels5YearsJavaVisitor ) return ((ReqModels5YearsJavaVisitor<? extends T>)visitor).visitLanguageRequirement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LanguageRequirementContext languageRequirement() throws RecognitionException {
		LanguageRequirementContext _localctx = new LanguageRequirementContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_languageRequirement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(17);
			match(PROGRAMMING_LANGUAGES);
			setState(18);
			match(COLLON);
			setState(19);
			match(JAVA);
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
		"\u0004\u0001\u0006\u0016\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0001\u0000\u0001\u0000"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0000\u0000\u0004\u0000\u0002\u0004\u0006\u0000\u0000\u0011\u0000\b\u0001"+
		"\u0000\u0000\u0000\u0002\n\u0001\u0000\u0000\u0000\u0004\r\u0001\u0000"+
		"\u0000\u0000\u0006\u0011\u0001\u0000\u0000\u0000\b\t\u0003\u0002\u0001"+
		"\u0000\t\u0001\u0001\u0000\u0000\u0000\n\u000b\u0003\u0004\u0002\u0000"+
		"\u000b\f\u0003\u0006\u0003\u0000\f\u0003\u0001\u0000\u0000\u0000\r\u000e"+
		"\u0005\u0001\u0000\u0000\u000e\u000f\u0005\u0005\u0000\u0000\u000f\u0010"+
		"\u0005\u0004\u0000\u0000\u0010\u0005\u0001\u0000\u0000\u0000\u0011\u0012"+
		"\u0005\u0002\u0000\u0000\u0012\u0013\u0005\u0005\u0000\u0000\u0013\u0014"+
		"\u0005\u0003\u0000\u0000\u0014\u0007\u0001\u0000\u0000\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}