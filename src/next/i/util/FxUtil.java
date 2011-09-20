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
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;

/**
 * Animation utils.
 */
public class FxUtil {

	public static native void reload() /*-{
		$wnd.location.reload();
	}-*/;

	public static native boolean supportOrientation() /*-{
		var b = ("onorientationchange" in window);
		return (b != null && b == true);
	}-*/;

	public static native void addOrientationListener() /*-{
		$wnd.addEventListener("orientationchange", function() {
			//			alert('window.orientation:' + $wnd.orientation);
			setTimeout(function() {
				$wnd.__doEvent('1', 'ORIENTATION_CHANGE');
			}, 100);
		}, false);
	}-*/;

	public static double getStyleLeft(Widget w) {
		Style style = w.getElement().getStyle();
		String left = style.getLeft();
		if (left.isEmpty()) {
			return 0;
		} else {
			return Double.parseDouble(left.replace("px", ""));
		}
	}

	public static void setStyleLeft(Widget w, double letf) {
		Style style = w.getElement().getStyle();
		style.setLeft(letf, Unit.PX);
	}

	public static double getStyleTop(Widget w) {
		Style style = w.getElement().getStyle();
		String top = style.getTop();
		if (top.isEmpty()) {
			return 0;
		} else {
			return Double.parseDouble(top.replace("px", ""));
		}
	}

	public static void setStyleTop(Widget w, double top) {
		Style style = w.getElement().getStyle();
		style.setTop(top, Unit.PX);
	}

	public static void fadeIn(final UIObject obj) {

		final Element ele = obj.getElement();

		// obj.setVisible(false);
		ele.getStyle().setOpacity(0);
		setTransitionProperty(ele, "opacity");
		setTransitionDuration(ele, 250);

		new Timer() {
			public void run() {
				obj.setVisible(true);
				ele.getStyle().setOpacity(1);
			}
		}.schedule(10);
	}

	public static void fadeOut(final UIObject obj, final Command onClose) {

		final Element ele = obj.getElement();

		obj.setVisible(true);
		ele.getStyle().setOpacity(1);
		setTransitionProperty(ele, "opacity");
		setTransitionDuration(ele, 250);

		new Timer() {
			public void run() {

				ele.getStyle().setOpacity(0);

				if (onClose != null) {
					new Timer() {
						public void run() {
							// obj.setVisible(false);
							onClose.execute();
						}
					}.schedule(250);
				}

			}
		}.schedule(10);
	}

	public static native void setTranslateY(Element ele, double y) /*-{
		ele.style.webkitTransform = "translate3d(0px, " + y + "px ,0px)";
	}-*/;

	public static native void setTranslateX(Element ele, double x) /*-{
		ele.style.webkitTransform = "translate3d(" + x + "px ,0px,0px)";
	}-*/;

	public static native void setTranslateXY(Element ele, double x, double y) /*-{
		ele.style.webkitTransform = "translate3d(" + x + "px ," + y + "px,0px)";
	}-*/;

	public static native int getTranslateX(Element ele) /*-{
		var transform = ele.style.webkitTransform;
		var translateX = 0;
		if (transform && transform !== "") {
			// this fails with IndexOutOfBounds error
			//			translateX = parseInt((/translate3d\((\-?.*)px, 0px, 0px\)/).exec(transform)[1]);
			var s = transform.replace("translate3d(", "").replace(")", "");
			var arr = s.split("px,");
			//console.log( "transform=" + transform + " [0]=" + arr[0] + "");
			translateX = parseInt(arr[0]);
		}
		return translateX;
	}-*/;

	public static native int getTranslateY(Element ele) /*-{
		var transform = ele.style.webkitTransform;
		var translateY = 0;
		if (transform && transform !== "") {
			// this fails with IndexOutOfBounds error
			// var v = (/translate3d\(0px, (\-?.*)px, 0px\)/).exec(transform);
			// translateY = parseInt(v[1]);
			var s = transform.replace("translate3d(", "").replace(")", "");
			var arr = s.split("px,");
			//console.log( "transform=" + transform + " [1]=" + arr[1] + "");
			translateY = parseInt(arr[1]);
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
		//console.log( "XX: a:" + matrix.a + " b:" + matrix.b + " c:" + matrix.c + " d:" + matrix.d + " e:" + matrix.e + " f:" + matrix.f + " ");
		return matrix.e;
	}-*/;

	public static native double getMatrixY(Element ele) /*-{
		var matrix = new WebKitCSSMatrix(
				window.getComputedStyle(ele).webkitTransform);
		//console.log( "YY: a:" + matrix.a + " b:" + matrix.b + " c:" + matrix.c + " d:" + matrix.d + " e:" + matrix.e + " f:" + matrix.f + " ");
		return matrix.f;
	}-*/;

	public static native int getHeight(Element ele) /*-{
		return parseInt($doc.defaultView.getComputedStyle(ele, "")
				.getPropertyValue("height"));
	}-*/;

	public static native int getWidth(Element ele) /*-{
		return parseInt($doc.defaultView.getComputedStyle(ele, "")
				.getPropertyValue("width"));
	}-*/;

}
