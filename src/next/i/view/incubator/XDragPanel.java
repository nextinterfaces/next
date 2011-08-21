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
package next.i.view.incubator;

import next.i.XLog;
import next.i.XStyle;
import next.i.mobile.DragController;
import next.i.mobile.DragEvent;
import next.i.mobile.DragEventsHandler;
import next.i.util.FxUtil;
import next.i.util.Utils;
import next.i.view.MPanelBase;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class XDragPanel extends MPanelBase implements HasWidgets, DragEventsHandler {
//	, SwipeEventsHandler {

	private boolean _hasTextBox = false;

	public XDragPanel() {
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
//		DragController.get().addSwipeEventsHandler(this);
	}

	@Override
	public void onUnload() {
		DragController.get().removeDragEventsHandler(this);
//		DragController.get().removeSwipeEventHandler(this);
	}

	@Override
	public Widget getWidget() {
		return _panel.getWidget(0);
	}

	public void reset() {
		FxUtil.setTransitionDuration(getWidget().getElement(), 0);
		FxUtil.setTranslateY(getWidget().getElement(), 0);
	}

	public void setPostionToTop() {
		FxUtil.setTransitionDuration(getWidget().getElement(), 0);
		FxUtil.setTranslateY(getWidget().getElement(), 0);
	}

	public void setPositionToBottom() {
		FxUtil.setTransitionDuration(getWidget().getElement(), 0);
		FxUtil.setTranslateY(getWidget().getElement(), this.getElement().getClientHeight()
				- this.getElement().getScrollHeight());
	}

	public void setScrollPosition(double pos) {
		if (_hasTextBox) {
			setStyleTop(pos);
		} else {
			Element element = getWidget().getElement();
			FxUtil.setTranslateY(element, pos);
		}
	}

	public double getScrollPosition() {
		if (_hasTextBox) {
			return getStyleTop();
		} else {
			Element element = getWidget().getElement();
			return FxUtil.getTranslateX(element);
		}
	}

	public double getScrollToPosition() {
		if (_hasTextBox) {
			return getStyleTop();
		} else {
			Element element = getWidget().getElement();
			return FxUtil.getMatrixX(element);
		}
	}

	@Override
	public void onDragStart(DragEvent e) {
		double matrix = getScrollToPosition();
		double current = getScrollPosition();
		
		XLog.info("onDragStart>> matrix: " + matrix + " current: " + current);
		FxUtil.setTransitionDuration(getWidget().getElement(), 0);
		if (current != matrix) {
			// scroll on going
			double diff = current - matrix;
			double offset = diff > 2 ? 2 : diff > -2 ? diff : -2;
			XLog.info("onDragStart>> setScrollPosition: " + (matrix + offset));
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
		XLog.info("onDragMove>> panelHeight: " + panelHeight + " widgetHeight: " + widgetHeight + " scrollPos: " + current);
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
		XLog.info("onDragMove>> setScrollPosition: " + current);
		setScrollPosition(current);
	}

	@Override
	public void onDragEnd(DragEvent e) {
		Element widgetEle = getWidget().getElement();
		double current = getScrollPosition();
		if (current == 0) {
			return;
		}
		int panelHeight = Utils.getHeight(this.getElement());
		int widgetHeight = widgetEle.getOffsetHeight();
		XLog.info("onDragEnd>> panelHeight: " + panelHeight + " widgetHeight: " + widgetHeight);
		// exceed top boundary
		if (current > 0 || panelHeight > widgetHeight) {
			FxUtil.setTransitionDuration(widgetEle, 500);
			XLog.info("onDragEnd>> 0 setScrollPosition: " + 0);
			setScrollPosition(0);
		} else if (-current + panelHeight > widgetHeight) {
			// exceed bottom boundary
			FxUtil.setTransitionDuration(widgetEle, 500);
			XLog.info("onDragEnd>> 1 setScrollPosition: " + (panelHeight - widgetHeight));
			setScrollPosition(panelHeight - widgetHeight);
		}
	}

//	@Override
//	public void onSwipeVertical(SwipeEvent e) {
//		Element widgetEle = getWidget().getElement();
//		int panelHeight = Utils.getHeight(this.getElement());
//		int widgetHeight = widgetEle.getOffsetHeight();
//		double current = getScrollPosition();
//		// exceed top boundary
//		if ((current >= 0) || (-current + panelHeight >= widgetHeight)) {
//			// exceed bottom boundary
//			return;
//		}
//
//		double speed = e.getSpeed();
//		double timeFactor = 2800;
//		long time = (long) Math.abs(speed * timeFactor);
//		double dicstanceFactor = 0.24;
//		long distance = (long) (speed * time * dicstanceFactor);
//		// Utils.Console("speed " + speed + " time " + time + " distance " +
//		// distance + " current " + current);
//		current += distance;
//		if (current > 0) {
//			// exceed top boundary
//			double timeAdj = 1 - (double) current / distance;
//			time = (long) (time * timeAdj);
//			current = 0;
//		} else if (-current + panelHeight > widgetHeight) {
//			// exceed bottom boundary
//			long bottom = panelHeight - widgetHeight;
//			double timeAdj = 1 - (double) (current - bottom) / distance;
//			time = (long) (time * timeAdj);
//			current = bottom;
//		}
//		FxUtil.setTransitionDuration(widgetEle, time);
//		setScrollPosition((int) current);
//	}
//
//	@Override
//	public void onSwipeHorizontal(SwipeEvent e) {
//	}

	@Override
	public void add(Widget w) {
		assert _panel.getWidgetCount() == 0 : "Can only add one widget to MScrollPanel.";
		super.add(w);
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