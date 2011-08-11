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
package next.i.view;

import static com.google.gwt.dom.client.Style.Unit.PX;

import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;

/**
 * Utility class
 */
class Utils {

	// refer to *.gwt.xml file for all mobile UserAgents
	native boolean isMobile() /*-{
		var ua = $wnd.navigator.userAgent.toLowerCase();
		if (ua.indexOf("webkit") != -1 && ua.indexOf("mobile") != -1) {
			return "mobilesafari";
		}
		return "none";
	}-*/;

	static void fillParent(Element elem) {
		Style style = elem.getStyle();
		style.setPosition(Position.ABSOLUTE);
		style.setLeft(0, PX);
		style.setTop(0, PX);
		style.setRight(0, PX);
		style.setBottom(0, PX);
		style.setWidth(100, Unit.PCT);
		style.setHeight(100, Unit.PCT);
	}

	static boolean isVisible(Element element) {

		int left = element.getAbsoluteLeft();
		int top = element.getAbsoluteTop();
		int winH = Window.getClientHeight();
		int winW = Window.getClientWidth();
		boolean isVisible = (left >= 0 && left < winW) && (top >= 0 && top < winH);
		// Log.info("top=" + top + ", left=" + left);

		return isVisible;
	}

	native static void disableTextSelect(Element e, boolean disable)/*-{
		if (disable) {
			e.ondrag = function() {
				return false;
			};
			e.onselectstart = function() {
				return false;
			};
			e.style.MozUserSelect = "none"
		} else {
			e.ondrag = null;
			e.onselectstart = null;
			e.style.MozUserSelect = "text"
		}
	}-*/;

	static String noNull(String str) {
		return noNull(str, "");
	}

	static String noNull(String str, String default_) {
		if (null == str) {
			return default_;
		}
		return str;
	}

	static boolean isEmpty(String str) {
		if (null == str || str.trim().length() < 1) {
			return true;
		}
		return false;
	}

}
