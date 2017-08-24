package idv.evan.tools.cn;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class ChineseHelper {

	public enum ChineseType {
		T(0), S(1);
		private final int value;

		private ChineseType(int value) {
			this.value = value;
		}
	}

	private Properties charMap = new Properties();
	private Set<String> conflictingSets = new HashSet<String>();

	public static final int TRADITIONAL = 0;
	public static final int SIMPLIFIED = 1;
	private static final int NUM_OF_CONVERTERS = 2;
	private static final ChineseHelper[] converters = new ChineseHelper[NUM_OF_CONVERTERS];
	private static final String[] propertyFiles = new String[2];

	static {
		propertyFiles[TRADITIONAL] = "zh2Hant.properties";
		propertyFiles[SIMPLIFIED] = "zh2Hans.properties";
	}

	public static ChineseHelper getInstance(ChineseType type) {
		if (converters[type.value] == null) {
			synchronized (ChineseHelper.class) {
				if (converters[type.value] == null) {
					converters[type.value] = new ChineseHelper(propertyFiles[type.value]);
				}
			}
		}
		return converters[type.value];
	}

	public static String convert(String text, ChineseType type) {
		ChineseHelper instance = getInstance(type);
		return instance.convert(text);
	}

	private ChineseHelper(String propertyFile) {
		InputStream is = null;
		is = getClass().getResourceAsStream(propertyFile);
		// File propertyFile = new File("C:/Temp/testMDB/TestTranslator/abc.txt");
		if (is != null) {
			BufferedReader bfrd = null;
			try {
				bfrd = new BufferedReader(new InputStreamReader(is));
				charMap.load(bfrd);
			} catch (IOException e) {
				System.err.printf("ERROR: %s", e.getMessage());
			} finally {
				Closeable[] cs = new Closeable[] { bfrd, is };
				try {
					for (Closeable c : cs) {
						if (c != null) {
							c.close();
						}
					}
				} catch (IOException e) {
				}
			}
		}
		initializeHelper();
	}

	private void initializeHelper() {
		Map<String, Integer> stringPossibilities = new HashMap<String, Integer>();
		Iterator<?> iter = charMap.keySet().iterator();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			if (key.length() >= 1) {
				for (int i = 0; i < (key.length()); i++) {
					String keySubstring = key.substring(0, i + 1);
					if (stringPossibilities.containsKey(keySubstring)) {
						Integer integer = (Integer) (stringPossibilities.get(keySubstring));
						stringPossibilities.put(keySubstring, new Integer(integer.intValue() + 1));
					} else {
						stringPossibilities.put(keySubstring, new Integer(1));
					}
				}
			}
		}

		Iterator<String> iterPoss = stringPossibilities.keySet().iterator();
		while (iterPoss.hasNext()) {
			String key = (String) iterPoss.next();
			if (((Integer) (stringPossibilities.get(key))).intValue() > 1) {
				conflictingSets.add(key);
			}
		}
	}

	public String convert(String in) {
		StringBuilder outString = new StringBuilder();
		StringBuilder stackString = new StringBuilder();

		for (int i = 0; i < in.length(); i++) {
			char c = in.charAt(i);
			String key = "" + c;
			stackString.append(key);

			if (conflictingSets.contains(stackString.toString())) {
			} else if (charMap.containsKey(stackString.toString())) {
				outString.append(charMap.get(stackString.toString()));
				stackString.setLength(0);
			} else {
				CharSequence sequence = stackString.subSequence(0, stackString.length() - 1);
				stackString.delete(0, stackString.length() - 1);
				flushStack(outString, new StringBuilder(sequence));
			}
		}

		flushStack(outString, stackString);

		return outString.toString();
	}

	private void flushStack(StringBuilder outString, StringBuilder stackString) {
		while (stackString.length() > 0) {
			if (charMap.containsKey(stackString.toString())) {
				outString.append(charMap.get(stackString.toString()));
				stackString.setLength(0);
			} else {
				outString.append("" + stackString.charAt(0));
				stackString.delete(0, 1);
			}
		}
	}

	String parseOneChar(String c) {
		if (charMap.containsKey(c)) {
			return (String) charMap.get(c);
		}
		return c;
	}
}
