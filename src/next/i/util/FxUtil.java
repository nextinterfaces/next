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
package next.i.util;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.UIObject;

/**
 * Animation utils.
 */
public class FxUtil {

	public static void fadeIn(UIObject obj) {

		final Style s = obj.getElement().getStyle();

		obj.setVisible(false);
		s.setOpacity(0);
		s.setProperty("webkitTransitionProperty", "opacity");
		s.setProperty("webkitTransitionDuration", "300ms");
		obj.setVisible(true);

		new Timer() {
			public void run() {
				s.setOpacity(1);
			}
		}.schedule(10);
	}

	public static void fadeOut(final UIObject obj, final Command onClose) {

		final Style s = obj.getElement().getStyle();

		obj.setVisible(true);
		s.setOpacity(1);
		s.setProperty("webkitTransitionProperty", "opacity");
		s.setProperty("webkitTransitionDuration", "300ms");

		new Timer() {
			public void run() {
				s.setOpacity(0);

				new Timer() {
					public void run() {
						obj.setVisible(false);
						onClose.execute();
					}
				}.schedule(290);

			}
		}.schedule(10);
	}

}
