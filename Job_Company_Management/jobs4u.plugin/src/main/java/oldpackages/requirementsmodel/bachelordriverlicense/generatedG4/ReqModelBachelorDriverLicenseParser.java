// Generated from /Users/cristinasemikina/Desktop/isep/sem4pi-23-24-2dc4/jobs4.plugin/src/main/java/requirementsmodel/bachelordriverlicense/ReqModelBachelorDriverLicense.g4 by ANTLR 4.13.1
package oldpackages.requirementsmodel.bachelordriverlicense.generatedG4;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class ReqModelBachelorDriverLicenseParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		EDUCATION_LEVEL=1, DRIVER_LICENSE=2, BACHELOR=3, LICENSE=4, COLLON=5, 
		WS=6;
	public static final int
		RULE_start = 0, RULE_specification = 1, RULE_educationRequirement = 2, 
		RULE_driverLicenseRequirement = 3;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "specification", "educationRequirement", "driverLicenseRequirement"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'education-level'", "'driver-license'", null, null, "':'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "EDUCATION_LEVEL", "DRIVER_LICENSE", "BACHELOR", "LICENSE", "COLLON", 
			"WS"
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
	public String getGrammarFileName() { return "ReqModelBachelorDriverLicense.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ReqModelBachelorDriverLicenseParser(TokenStream input) {
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
			if ( listener instanceof ReqModelBachelorDriverLicenseListener ) ((ReqModelBachelorDriverLicenseListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReqModelBachelorDriverLicenseListener ) ((ReqModelBachelorDriverLicenseListener)listener).exitStart(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReqModelBachelorDriverLicenseVisitor) return ((ReqModelBachelorDriverLicenseVisitor<? extends T>)visitor).visitStart(this);
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
		public EducationRequirementContext educationRequirement() {
			return getRuleContext(EducationRequirementContext.class,0);
		}
		public DriverLicenseRequirementContext driverLicenseRequirement() {
			return getRuleContext(DriverLicenseRequirementContext.class,0);
		}
		public SpecificationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_specification; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReqModelBachelorDriverLicenseListener ) ((ReqModelBachelorDriverLicenseListener)listener).enterSpecification(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReqModelBachelorDriverLicenseListener ) ((ReqModelBachelorDriverLicenseListener)listener).exitSpecification(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReqModelBachelorDriverLicenseVisitor ) return ((ReqModelBachelorDriverLicenseVisitor<? extends T>)visitor).visitSpecification(this);
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
			educationRequirement();
			setState(11);
			driverLicenseRequirement();
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
	public static class EducationRequirementContext extends ParserRuleContext {
		public TerminalNode EDUCATION_LEVEL() { return getToken(ReqModelBachelorDriverLicenseParser.EDUCATION_LEVEL, 0); }
		public TerminalNode COLLON() { return getToken(ReqModelBachelorDriverLicenseParser.COLLON, 0); }
		public TerminalNode BACHELOR() { return getToken(ReqModelBachelorDriverLicenseParser.BACHELOR, 0); }
		public EducationRequirementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_educationRequirement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReqModelBachelorDriverLicenseListener ) ((ReqModelBachelorDriverLicenseListener)listener).enterEducationRequirement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReqModelBachelorDriverLicenseListener ) ((ReqModelBachelorDriverLicenseListener)listener).exitEducationRequirement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReqModelBachelorDriverLicenseVisitor ) return ((ReqModelBachelorDriverLicenseVisitor<? extends T>)visitor).visitEducationRequirement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EducationRequirementContext educationRequirement() throws RecognitionException {
		EducationRequirementContext _localctx = new EducationRequirementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_educationRequirement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(13);
			match(EDUCATION_LEVEL);
			setState(14);
			match(COLLON);
			setState(15);
			match(BACHELOR);
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
	public static class DriverLicenseRequirementContext extends ParserRuleContext {
		public TerminalNode DRIVER_LICENSE() { return getToken(ReqModelBachelorDriverLicenseParser.DRIVER_LICENSE, 0); }
		public TerminalNode COLLON() { return getToken(ReqModelBachelorDriverLicenseParser.COLLON, 0); }
		public TerminalNode LICENSE() { return getToken(ReqModelBachelorDriverLicenseParser.LICENSE, 0); }
		public DriverLicenseRequirementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_driverLicenseRequirement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ReqModelBachelorDriverLicenseListener ) ((ReqModelBachelorDriverLicenseListener)listener).enterDriverLicenseRequirement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ReqModelBachelorDriverLicenseListener ) ((ReqModelBachelorDriverLicenseListener)listener).exitDriverLicenseRequirement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ReqModelBachelorDriverLicenseVisitor ) return ((ReqModelBachelorDriverLicenseVisitor<? extends T>)visitor).visitDriverLicenseRequirement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DriverLicenseRequirementContext driverLicenseRequirement() throws RecognitionException {
		DriverLicenseRequirementContext _localctx = new DriverLicenseRequirementContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_driverLicenseRequirement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(17);
			match(DRIVER_LICENSE);
			setState(18);
			match(COLLON);
			setState(19);
			match(LICENSE);
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
		"\u0005\u0003\u0000\u0000\u0010\u0005\u0001\u0000\u0000\u0000\u0011\u0012"+
		"\u0005\u0002\u0000\u0000\u0012\u0013\u0005\u0005\u0000\u0000\u0013\u0014"+
		"\u0005\u0004\u0000\u0000\u0014\u0007\u0001\u0000\u0000\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}