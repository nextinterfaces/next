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
 * --------
 * This class contains modified sources from gwtmobile-ui project. 
 * 
 * Copyright (c) 2010 Zhihua (Dennis) Jiang
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
package next.i.view.incubator;

import next.i.XLog;
import next.i.XStyle;
import next.i.mobile.DragController;
import next.i.mobile.DragEvent;
import next.i.mobile.DragEventsHandler;
import next.i.mobile.SwipeEvent;
import next.i.mobile.SwipeEventsHandler;
import next.i.util.FxUtil;
import next.i.util.Utils;
import next.i.view.MPanelBase;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class MScrollPanelScrollBar extends MPanelBase implements HasWidgets, DragEventsHandler, SwipeEventsHandler {

	private boolean _hasTextBox = false;

	private SimplePanel scrollbar;
	private Widget childWidget;
	private int scrollHeight = -1;
	private int panelHeight = -1;
	private int positionBottom = -1;
	private int positionTop = -1;

	public MScrollPanelScrollBar() {
		setStyleName(XStyle.scrollPanel.name());
	}

	public void setHasTextBox(boolean hasTextBox) {
		_hasTextBox = hasTextBox && Utils.isAndroid();
	}

	public boolean getHasTextBox() {
		return _hasTextBox;
	}

	@Override
	public void onLoad() {
		DragController.get().addDragEventsHandler(this);
		DragController.get().addSwipeEventsHandler(this);
		initScrollBar();
	}

	@Override
	public void onUnload() {
		DragController.get().removeDragEventsHandler(this);
		DragController.get().removeSwipeEventHandler(this);
	}

	private void initScrollBar() {
		scrollbar = new SimplePanel();
		// scrollbar.getElement().setId("xscrollBar");
		RootPanel.get().add(scrollbar);
		_initScrollBar(scrollbar.getElement());
	}

	private native void _initScrollBar(Element ele) /*-{
		// var scrollbar = document.getElementById('xscrollBar');
		//var scrollbar = document.createElement('div');
		//scrollbar.className = 'xscrollbar';
		//scrollbar.id = 'scrollbar';

		var scrollbar = ele;
		// We hardcode this CSS here to avoid having to provide a CSS file
		scrollbar.style.cssText = [
				'position: absolute',
				'top: 47px',
				'right: 1px',
				'width: 7px',
				'min-height: 7px',
				'opacity: 0',
				'-webkit-transform: translate3d(0,0,0)',
				'-webkit-box-sizing: border-box',
				'-webkit-border-image: url("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAA4AAAAOCAYAAAAfSC3RAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAUhJREFUeNp0Ur1OwzAQtt1CaZQQgUjDhuicrEwoqjJlzpBAXoIHywtkcwfECyQPwIgKQkoyFJWq5k6cJcsUS5/sO993/1wpxazjAU4BJyR/A3aA0TSaGu85kbSO0y0AM/pH8lYr8ZwBLpBUluVtGIaPjuM8IYIgeEAdObwkB4xTqgv8iOP4vuu6lZEFRkUDHkWRbNv2mVJ/x4g+1pPn+RJICRlzk4Q3/lVVdUP1nwtqgpJSYqQJGbMj96RpmhXJM01kwzBcWU2x36zv+wXppro5TAihvat/HCjxa6R0V7FY5rruhx3BTtfzvDeS95rI0zSVcB+MpijL0SHLsjW9d3ocIRZvjINbKSsYx5rGsQdsNHFOC8CKolhCh+/GcbxG2ff9TZIkL3Vdv5KjT8AXN3b12MqZi4yRBiTZu7olmEvOacH/LPmPAAMA2bZzzeYUC40AAAAASUVORK5CYII=") 6 2 6 2 / 3px 1px 3px 1px round round',
				'z-index: 2147483647', ].join(';');
		//document.body.appendChild(scrollbar);
	}-*/;

	@Override
	public Widget getWidget() {
		return _panel.getWidget(0);
	}

	public void reset() {
		XLog.info("reset ");
		FxUtil.setTransitionDuration(getWidget().getElement(), 0);
		FxUtil.setTranslateY(getWidget().getElement(), 0);
	}

	public void setPostionToTop() {
		XLog.info("TOP setPostionToTop: ");
		FxUtil.setTransitionDuration(getWidget().getElement(), 0);
		FxUtil.setTranslateY(getWidget().getElement(), 0);
	}

	public void setPositionToBottom() {
		XLog.info("BOTTOM setPositionToBottom: ");
		FxUtil.setTransitionDuration(getWidget().getElement(), 0);
		FxUtil.setTranslateY(getWidget().getElement(), this.getElement().getClientHeight()
				- this.getElement().getScrollHeight());
	}

	public void setScrollPosition(double pos) {
		if (panelHeight < 0) {
			scrollHeight = Utils.getHeight(this.getElement());
			if (childWidget != null) {
				panelHeight = Utils.getHeight(childWidget.getElement());
			}

			positionTop = this.getElement().getAbsoluteTop();
			positionBottom = this.getElement().getAbsoluteBottom();
		}
		XLog.info("setScrollPosition: " + pos + ", scrollHeight " + scrollHeight + ", panelHeight: " + panelHeight
				+ ", positionTop: " + positionTop + ", positionBottom: " + positionBottom);
		if (_hasTextBox) {
			setStyleTop(pos);
		} else {
			Element element = getWidget().getElement();
			FxUtil.setTranslateY(element, pos);
		}

		if (pos < 0.0001 && pos > -0.001) {
			scrollbar.getElement().getStyle().setOpacity(0);

		} else if (pos < -0.0001) {
			scrollbar.getElement().getStyle().setOpacity(1);
			scrollbar.getElement().getStyle().setProperty("height", Math.abs(pos) + "px");
			scrollbar.getElement().getStyle().setProperty("top", positionTop  + "px");
			scrollbar.getElement().getStyle().clearProperty("bottom");

		} else {
			scrollbar.getElement().getStyle().setOpacity(1);
			scrollbar.getElement().getStyle().setProperty("height", pos + "px");
			scrollbar.getElement().getStyle().setProperty("bottom", "47px");
			scrollbar.getElement().getStyle().clearProperty("top");
		}
	}

	public double getScrollPosition() {
		if (_hasTextBox) {
			return getStyleTop();
		} else {
			Element element = getWidget().getElement();
			return FxUtil.getTranslateY(element);
		}
	}

	public double getScrollToPosition() {
		if (_hasTextBox) {
			return getStyleTop();
		} else {
			Element element = getWidget().getElement();
			return Utils.getMatrixY(element);
		}
	}

	@Override
	public void onDragStart(DragEvent e) {
		double matrix = getScrollToPosition();
		double current = getScrollPosition();
		FxUtil.setTransitionDuration(getWidget().getElement(), 0);
		if (current != matrix) {
			// scroll on going
			double diff = current - matrix;
			double offset = diff > 2 ? 2 : diff > -2 ? diff : -2;
			setScrollPosition(matrix + offset);
			DragController.get().suppressNextClick();
		}
	}

	@Override
	public void onDragMove(DragEvent e) {
		Element widgetEle = getWidget().getElement();
		int panelHeight = Utils.getHeight(this.getElement());
		int widgetHeight = widgetEle.getOffsetHeight();
		double current = getScrollPosition();
		if (current > 0) {
			// exceed top boundary
			if (e.OffsetY > 0) {
				// resist scroll down.
				current += (int) (e.OffsetY / 2);
				// need the cast for production mode.
			} else {
				current += e.OffsetY * 2;
			}
		} else if (-current + panelHeight > widgetHeight) {
			// exceed bottom boundary
			if (e.OffsetY < 0) {
				// resist scroll up.
				current += (int) (e.OffsetY / 2);
			} else {
				current += e.OffsetY * 2;
			}
		} else {
			current += e.OffsetY;
		}
		setScrollPosition(current);
	}

	@Override
	public void onDragEnd(DragEvent e) {
		XLog.info("onDragEnd ");
		//FxUtil.fadeOut(scrollbar, null);
		Element widgetEle = getWidget().getElement();
		double current = getScrollPosition();
		if (current == 0) {
			return;
		}
		int panelHeight = Utils.getHeight(this.getElement());
		int widgetHeight = widgetEle.getOffsetHeight();
		

		FxUtil.setTransitionProperty(scrollbar.getElement(), "height");
		FxUtil.setTransitionDuration(scrollbar.getElement(), 550);
		new Timer() {
			public void run() {
				FxUtil.setTransitionProperty(scrollbar.getElement(), "");
			}
		}.schedule(550);
//		scrollbar.getElement().getStyle().setProperty("webkitTransitionProperty", "height");
//    -webkit-transition-property: -webkit-transform;
//    /* -webkit-transition-duration: 300ms; */
//    -webkit-transition-timing-function: ease-out;
//    position: absolute;
		
		// exceed top boundary
		if (current > 0 || panelHeight > widgetHeight) {
			FxUtil.setTransitionDuration(widgetEle, 550);
			setScrollPosition(0);
		} else if (-current + panelHeight > widgetHeight) {
			// exceed bottom boundary
			FxUtil.setTransitionDuration(widgetEle, 550);
			setScrollPosition(panelHeight - widgetHeight);
		}
	}

	@Override
	public void onSwipeVertical(SwipeEvent e) {
		Element widgetEle = getWidget().getElement();
		int panelHeight = Utils.getHeight(this.getElement());
		int widgetHeight = widgetEle.getOffsetHeight();
		double current = getScrollPosition();
		// exceed top boundary
		if ((current >= 0) || (-current + panelHeight >= widgetHeight)) {
			// exceed bottom boundary
			return;
		}

		double speed = e.getSpeed();
		double timeFactor = 2800;
		long time = (long) Math.abs(speed * timeFactor);
		double dicstanceFactor = 0.24;
		long distance = (long) (speed * time * dicstanceFactor);
		// Utils.Console("speed " + speed + " time " + time + " distance " +
		// distance + " current " + current);
		current += distance;
		if (current > 0) {
			// exceed top boundary
			double timeAdj = 1 - (double) current / distance;
			time = (long) (time * timeAdj);
			current = 0;
		} else if (-current + panelHeight > widgetHeight) {
			// exceed bottom boundary
			long bottom = panelHeight - widgetHeight;
			double timeAdj = 1 - (double) (current - bottom) / distance;
			time = (long) (time * timeAdj);
			current = bottom;
		}
		FxUtil.setTransitionDuration(widgetEle, time);
		setScrollPosition((int) current);
	}

	@Override
	public void onSwipeHorizontal(SwipeEvent e) {
	}

	@Override
	public void add(Widget w) {
		assert _panel.getWidgetCount() == 0 : "Can only add one widget to MScrollPanel.";
		super.add(w);
		this.childWidget = w;
	}

	private double getStyleTop() {
		com.google.gwt.dom.client.Style style = getWidget().getElement().getStyle();
		String top = style.getTop();
		if (top.isEmpty()) {
			return 0;
		} else {
			return Double.parseDouble(top.replace("px", ""));
		}
	}

	private void setStyleTop(double top) {
		com.google.gwt.dom.client.Style style = getWidget().getElement().getStyle();
		style.setTop(top, Unit.PX);
	}

}
