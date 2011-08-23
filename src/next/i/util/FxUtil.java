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

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;

/**
 * Animation utils.
 */
public class FxUtil {

	public static double getStyleTop(Widget w) {
		com.google.gwt.dom.client.Style style = w.getElement().getStyle();
		String top = style.getTop();
		if (top.isEmpty()) {
			return 0;
		} else {
			return Double.parseDouble(top.replace("px", ""));
		}
	}

	public static void setStyleTop(Widget w, double top) {
		com.google.gwt.dom.client.Style style = w.getElement().getStyle();
		style.setTop(top, Unit.PX);
	}

	public static void fadeIn(UIObject obj) {

		final Element ele = obj.getElement();

		obj.setVisible(false);
		ele.getStyle().setOpacity(0);
		setTransitionProperty(ele, "opacity");
		setTransitionDuration(ele, 300);

		obj.setVisible(true);

		new Timer() {
			public void run() {
				ele.getStyle().setOpacity(1);
			}
		}.schedule(10);
	}

	public static void fadeOut(final UIObject obj, final Command onClose) {

		final Element ele = obj.getElement();

		obj.setVisible(true);
		ele.getStyle().setOpacity(1);
		setTransitionProperty(ele, "opacity");
		setTransitionDuration(ele, 300);

		new Timer() {
			public void run() {
				ele.getStyle().setOpacity(0);

				if (onClose != null) {
					new Timer() {
						public void run() {
							// obj.setVisible(false);
							onClose.execute();
						}
					}.schedule(290);
				}

			}
		}.schedule(10);
	}

	public static native void setTranslateY(Element ele, double value) /*-{
		ele.style.webkitTransform = "translate3d(0px, " + value + "px ,0px)";
	}-*/;

	public static native int getTranslateX(Element ele) /*-{
		var transform = ele.style.webkitTransform;
		var translateX = 0;
		if (transform && transform !== "") {
			translateX = parseInt((/translate3d\((\-?.*)px, 0px, 0px\)/)
					.exec(transform)[1]);
		}
		return translateX;
	}-*/;

	public static native int getTranslateY(Element ele) /*-{
		var transform = ele.style.webkitTransform;
		var translateY = 0;
		if (transform && transform !== "") {
			translateY = parseInt((/translate3d\(0px, (\-?.*)px, 0px\)/)
					.exec(transform)[1]);
		}
		return translateY;
	}-*/;

	public static native void setTransitionDuration(Element ele, double value) /*-{
		ele.style.webkitTransitionDuration = "" + value + "ms";
	}-*/;

	public static native void setTransitionProperty(Element ele, String property) /*-{
		ele.style.webkitTransitionProperty = property;
	}-*/;

	public static native double getMatrixX(Element ele) /*-{
		var matrix = new WebKitCSSMatrix(
				window.getComputedStyle(ele).webkitTransform);
		return matrix.a;
	}-*/;

}
