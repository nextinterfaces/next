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
package next.i.controller;

import static com.google.gwt.dom.client.Style.Unit.PX;

import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

/**
 * Utility class
 */
@SuppressWarnings("rawtypes")
class Utils {

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

	static String getStyle(Enum... names) {
		String res = "";
		if (names != null) {
			for (Enum e : names) {
				res += " " + e.name();
			}
		}
		return res;
	}

	static void setStyle(Widget widget, Enum... styles) {
		String css = getStyle(styles);
		widget.setStyleName(css);
	}

	static void setStyle(XController ctrl, Enum... styles) {
		IsWidget view = ctrl.getNavigationView();
		if (view == null) {
			Window.alert("view is null");
		} else {
			// Window.alert("view is " + view);
		}
		Widget w = view.asWidget();
		setStyle(w, styles);
	}

	static void setStyle(XController ctrl, String... styles) {
		String style = "";
		for (String css : styles) {
			style += (css + " ");
		}
		ctrl.getNavigationView().asWidget().setStyleName(style.trim());
	}

	static void removeStyle(Widget widget, Enum... styles) {
		if (styles != null) {
			for (Enum e : styles) {
				widget.removeStyleName(e.name());
			}
		}
	}

	static void removeStyle(Widget widget, String... styles) {
		if (styles != null) {
			for (String c : styles) {
				widget.removeStyleName(c);
			}
		}
	}

	static void removeStyle(XController ctrl, Enum... styles) {
		removeStyle(ctrl.getNavigationView().asWidget(), styles);
	}

	static void removeStyle(XController ctrl, String... styles) {
		removeStyle(ctrl.getNavigationView().asWidget(), styles);
	}

}
