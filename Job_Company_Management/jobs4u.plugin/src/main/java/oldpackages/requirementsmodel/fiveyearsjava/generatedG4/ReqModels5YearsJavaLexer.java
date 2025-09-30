// Generated from /Users/cristinasemikina/Desktop/isep/sem4pi-23-24-2dc4/jobs4.plugin/src/main/java/requirementsmodel/fiveyearsjava/ReqModels5YearsJava.g4 by ANTLR 4.13.1
package oldpackages.requirementsmodel.fiveyearsjava.generatedG4;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class ReqModels5YearsJavaLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		YEARS_EXPERIENCE=1, PROGRAMMING_LANGUAGES=2, JAVA=3, AT_LEAST_5_YEARS=4, 
		COLLON=5, WS=6;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"YEARS_EXPERIENCE", "PROGRAMMING_LANGUAGES", "JAVA", "AT_LEAST_5_YEARS", 
			"COLLON", "WS"
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


	public ReqModels5YearsJavaLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "ReqModels5YearsJava.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u0006T\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0003\u0002A\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0004\u0003"+
		"F\b\u0003\u000b\u0003\f\u0003G\u0003\u0003J\b\u0003\u0001\u0004\u0001"+
		"\u0004\u0001\u0005\u0004\u0005O\b\u0005\u000b\u0005\f\u0005P\u0001\u0005"+
		"\u0001\u0005\u0000\u0000\u0006\u0001\u0001\u0003\u0002\u0005\u0003\u0007"+
		"\u0004\t\u0005\u000b\u0006\u0001\u0000\u0004\u0001\u000059\u0001\u0000"+
		"19\u0001\u000009\u0003\u0000\t\n\r\r  X\u0000\u0001\u0001\u0000\u0000"+
		"\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000"+
		"\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000"+
		"\u0000\u000b\u0001\u0000\u0000\u0000\u0001\r\u0001\u0000\u0000\u0000\u0003"+
		"\u001e\u0001\u0000\u0000\u0000\u0005@\u0001\u0000\u0000\u0000\u0007I\u0001"+
		"\u0000\u0000\u0000\tK\u0001\u0000\u0000\u0000\u000bN\u0001\u0000\u0000"+
		"\u0000\r\u000e\u0005y\u0000\u0000\u000e\u000f\u0005e\u0000\u0000\u000f"+
		"\u0010\u0005a\u0000\u0000\u0010\u0011\u0005r\u0000\u0000\u0011\u0012\u0005"+
		"s\u0000\u0000\u0012\u0013\u0005-\u0000\u0000\u0013\u0014\u0005e\u0000"+
		"\u0000\u0014\u0015\u0005x\u0000\u0000\u0015\u0016\u0005p\u0000\u0000\u0016"+
		"\u0017\u0005e\u0000\u0000\u0017\u0018\u0005r\u0000\u0000\u0018\u0019\u0005"+
		"i\u0000\u0000\u0019\u001a\u0005e\u0000\u0000\u001a\u001b\u0005n\u0000"+
		"\u0000\u001b\u001c\u0005c\u0000\u0000\u001c\u001d\u0005e\u0000\u0000\u001d"+
		"\u0002\u0001\u0000\u0000\u0000\u001e\u001f\u0005p\u0000\u0000\u001f \u0005"+
		"r\u0000\u0000 !\u0005o\u0000\u0000!\"\u0005g\u0000\u0000\"#\u0005r\u0000"+
		"\u0000#$\u0005a\u0000\u0000$%\u0005m\u0000\u0000%&\u0005m\u0000\u0000"+
		"&\'\u0005i\u0000\u0000\'(\u0005n\u0000\u0000()\u0005g\u0000\u0000)*\u0005"+
		"-\u0000\u0000*+\u0005l\u0000\u0000+,\u0005a\u0000\u0000,-\u0005n\u0000"+
		"\u0000-.\u0005g\u0000\u0000./\u0005u\u0000\u0000/0\u0005a\u0000\u0000"+
		"01\u0005g\u0000\u000012\u0005e\u0000\u000023\u0005s\u0000\u00003\u0004"+
		"\u0001\u0000\u0000\u000045\u0005J\u0000\u000056\u0005a\u0000\u000067\u0005"+
		"v\u0000\u00007A\u0005a\u0000\u000089\u0005j\u0000\u00009:\u0005a\u0000"+
		"\u0000:;\u0005v\u0000\u0000;A\u0005a\u0000\u0000<=\u0005J\u0000\u0000"+
		"=>\u0005A\u0000\u0000>?\u0005V\u0000\u0000?A\u0005A\u0000\u0000@4\u0001"+
		"\u0000\u0000\u0000@8\u0001\u0000\u0000\u0000@<\u0001\u0000\u0000\u0000"+
		"A\u0006\u0001\u0000\u0000\u0000BJ\u0007\u0000\u0000\u0000CE\u0007\u0001"+
		"\u0000\u0000DF\u0007\u0002\u0000\u0000ED\u0001\u0000\u0000\u0000FG\u0001"+
		"\u0000\u0000\u0000GE\u0001\u0000\u0000\u0000GH\u0001\u0000\u0000\u0000"+
		"HJ\u0001\u0000\u0000\u0000IB\u0001\u0000\u0000\u0000IC\u0001\u0000\u0000"+
		"\u0000J\b\u0001\u0000\u0000\u0000KL\u0005:\u0000\u0000L\n\u0001\u0000"+
		"\u0000\u0000MO\u0007\u0003\u0000\u0000NM\u0001\u0000\u0000\u0000OP\u0001"+
		"\u0000\u0000\u0000PN\u0001\u0000\u0000\u0000PQ\u0001\u0000\u0000\u0000"+
		"QR\u0001\u0000\u0000\u0000RS\u0006\u0005\u0000\u0000S\f\u0001\u0000\u0000"+
		"\u0000\u0005\u0000@GIP\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}