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

/**
 * Holds next global constant values.
 */
class XGlobals {

	public static int TAB_BAR_HEIGHT = 47;
	public static int NAVIGATION_BAR_HEIGHT = 44;
	public static int NAVIGATION_TOOL_BAR_HEIGHT = 35;

	// public static int getTabContentHeight() {
	// String h = DOM.getStyleAttribute(DOM.getElementById(Style.tabBar.name()),
	// "height");
	// int h2 = (int)getDoubleAttr(h);
	// int delta = (Window.getClientHeight() - h2);
	// Utils.Console("tabBar.height: " + h + ", tabContent.height: " + delta +
	// ", win.height: " +
	// Window.getClientHeight());
	// return delta;
	// }

	static double toDouble(String pixels) {
		if (pixels != null) {
			pixels = pixels.replace("px", "");
			try {
				return Double.valueOf(pixels);

			} catch (NumberFormatException e) {
				e.printStackTrace();
				return -2;
			}
		}
		return -1;
	}

}
