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

import com.google.gwt.user.client.Window;

public class XLog {

	private static String host = Window.Location.getHost();

	// private static boolean isLocalhost = (host.contains("127") ||
	// host.contains("192.168") || host.contains("localhost")) ? true
	// : false;

	public static void info(String text) {
		// if (isLocalhost) {
		console("INFO: " + text);
		// }
	}

	public static void warn(String text) {
		// if (isLocalhost) {
		console("WARN: " + text);
		// }
	}

	public static void err(String text) {
		// if (isLocalhost) {
		console("ERROR: " + text);
		// }
	}

	/**
	 * Utility method throwing an error allowing to position stak execution of a
	 * method
	 */
	public static void throwStackTrace() {
		try {
			throw new RuntimeException("throwStackTrace");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static native void console(String msg) /*-{
		var logElem = $doc.getElementById('xlog');
		if (logElem) {
			logElem.innerHTML = msg + '<br/>' + logElem.innerHTML;
		} else {
			if ($wnd.console) {
				$wnd.console.log(msg);
			}
		}
	}-*/;
}
