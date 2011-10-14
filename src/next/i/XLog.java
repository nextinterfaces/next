/*
 * Copyright 2011 Vancouver Ywebb Consulting Ltd
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package next.i;


public class XLog {

	private static boolean isDiv = isDiv();

	private static String level = getLevel();

	public static void setLevel(String level) {
		XLog.level = level;
	}

	public static void info(String text) {
		if (!isDiv) {
			if ("INFO".equals(level)) {
				console("INFO: " + text);
			}
		} else {
			if ("INFO".equals(level)) {
				consoleDiv("INFO: " + text);
			}
		}
	}

	public static void info(String text, Object... args) {
		if (args != null) {
			for (int i = 0; i < args.length; i++) {
				text = text.replace("$" + (i + 1), "" + args[i]);
			}
		}
		info(text);
	}

	public static void warn(String text) {
		if (!isDiv) {
			if ("INFO".equals(level) || "WARN".equals(level)) {
				console("WARN: " + text);
			}
		} else {
			if ("INFO".equals(level) || "WARN".equals(level)) {
				consoleDiv("WARN: " + text);
			}
		}
	}

	public static void warn(String text, Object... args) {
		if (args != null) {
			for (int i = 0; i < args.length; i++) {
				text = text.replace("$" + (i + 1), "" + args[i]);
			}
		}
		warn(text);
	}

	public static void err(String text) {
		if (!isDiv) {
			console("ERROR: " + text);
		} else {
			consoleDiv("ERROR: " + text);
		}
	}

	public static void err(String text, Object... args) {
		if (args != null) {
			for (int i = 0; i < args.length; i++) {
				text = text.replace("$" + (i + 1), "" + args[i]);
			}
		}
		err(text);
	}

	/**
	 * Utility method throwing an error allowing to track code execution in stack
	 */
	public static void throwStackTrace() {
		try {
			throw new RuntimeException("throwStackTrace");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static native boolean isDiv() /*-{
		var logElem = $doc.getElementById('xlog');
		if (logElem) {
			return true;
		}
		return false;
	}-*/;

	private static native void consoleDiv(String msg) /*-{
		//var logElem = $doc.getElementById('xlog');
		//if (logElem) {
		logElem.innerHTML = msg + '<br/>' + logElem.innerHTML;
		//}
	}-*/;

	private static native void console(String msg) /*-{
		if ($wnd.console) {
			$wnd.console.log(msg);
		}
	}-*/;

	private static native String getLevel() /*-{
		if ($wnd.NEXT && $wnd.NEXT.XLog && $wnd.NEXT.XLog.level) {
			return $wnd.NEXT.XLog.level;
		} else {
			$wnd.NEXT = $wnd.NEXT ? $wnd.NEXT : {};
			$wnd.NEXT.XLog = {};
			$wnd.NEXT.XLog.level = "WARN";
			return "WARN";
		}
	}-*/;

}
