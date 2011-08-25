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

import next.i.XStyle;
import next.i.mobile.DragController;
import next.i.mobile.DragEvent;
import next.i.mobile.DragEventsHandler;
import next.i.mobile.SwipeEvent;
import next.i.mobile.SwipeEventsHandler;
import next.i.util.FxUtil;
import next.i.util.Utils;

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * This view class accepts finger drag actions and can be scrolled horizontally.
 * 
 * 
 */
public class XHorizontalScrollView extends MPanelBase implements HasWidgets, DragEventsHandler, SwipeEventsHandler,
		IView {

	private boolean _hasTextBox = false;

	public XHorizontalScrollView() {
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
	}

	@Override
	public void onUnload() {
		DragController.get().removeDragEventsHandler(this);
		DragController.get().removeSwipeEventHandler(this);
	}

	@Override
	public Widget getWidget() {
		return _panel.getWidget(0);
	}

	public void reset() {
		FxUtil.setTransitionDuration(getWidget().getElement(), 0);
		FxUtil.setTranslateX(getWidget().getElement(), 0);
	}

	public void setPostionToTop() {
		FxUtil.setTransitionDuration(getWidget().getElement(), 0);
		FxUtil.setTranslateX(getWidget().getElement(), 0);
	}

	public void setPositionToBottom() {
		FxUtil.setTransitionDuration(getWidget().getElement(), 0);
		FxUtil.setTranslateX(getWidget().getElement(), this.getElement().getClientWidth()
				- this.getElement().getScrollWidth());
	}

	public void setScrollPosition(double pos) {
		if (_hasTextBox) {
			FxUtil.setStyleLeft(this, pos);
		} else {
			Element element = getWidget().getElement();
			FxUtil.setTranslateX(element, pos);
		}
	}

	public double getScrollPosition() {
		if (_hasTextBox) {
			return FxUtil.getStyleLeft(this);
		} else {
			Element element = getWidget().getElement();
			return FxUtil.getTranslateX(element);
		}
	}

	public double getScrollToPosition() {
		if (_hasTextBox) {
			return FxUtil.getStyleLeft(this);
		} else {
			Element element = getWidget().getElement();
			return FxUtil.getMatrixX(element);
		}
	}

	@Override
	public void onDragStart(DragEvent e) {
		// XLog.info("{} onDragStart...");
		// FIXME matrix is wrong
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
		int panelWidth = Utils.getWidth(this.getElement());
		int widgetWidth = widgetEle.getOffsetWidth();
		double current = getScrollPosition();
		if (current > 0) {
			// exceed top boundary
			if (e.OffsetX > 0) {
				// resist scroll down.
				current += (int) (e.OffsetX / 2);
				// need the cast for production mode.
			} else {
				current += e.OffsetX * 2;
			}
		} else if (-current + panelWidth > widgetWidth) {
			// exceed bottom boundary
			if (e.OffsetX < 0) {
				// resist scroll up.
				current += (int) (e.OffsetX / 2);
			} else {
				current += e.OffsetX * 2;
			}
		} else {
			current += e.OffsetX;
		}
		// XLog.info("current: " + current + ", e.OffsetX: " + e.OffsetX +
		// ", e.OffsetY: " + e.OffsetY + ", e.X: " + e.X
		// + ", e.Y: " + e.Y);
		setScrollPosition(current);
	}

	@Override
	public void onDragEnd(DragEvent e) {
		// XLog.info("{} onDragEnd...");
		Element widgetEle = getWidget().getElement();
		double current = getScrollPosition();
		if (current == 0) {
			return;
		}
		int panelWidth = Utils.getWidth(this.getElement());
		int widgetWidth = widgetEle.getOffsetWidth();
		// exceed top boundary
		if (current > 0 || panelWidth > widgetWidth) {
			FxUtil.setTransitionDuration(widgetEle, 500);
			setScrollPosition(0);
		} else if (-current + panelWidth > widgetWidth) {
			// exceed bottom boundary
			FxUtil.setTransitionDuration(widgetEle, 500);
			setScrollPosition(panelWidth - widgetWidth);
		}
	}

	@Override
	public void onSwipeVertical(SwipeEvent e) {
	}

	@Override
	public void onSwipeHorizontal(SwipeEvent e) {
		Element widgetEle = getWidget().getElement();
		int panelWidth = Utils.getWidth(this.getElement());
		int widgetWidth = widgetEle.getOffsetWidth();
		double current = getScrollPosition();
		// exceed top boundary
		if ((current >= 0) || (-current + panelWidth >= widgetWidth)) {
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
		} else if (-current + panelWidth > widgetWidth) {
			// exceed bottom boundary
			long bottom = panelWidth - widgetWidth;
			double timeAdj = 1 - (double) (current - bottom) / distance;
			time = (long) (time * timeAdj);
			current = bottom;
		}
		FxUtil.setTransitionDuration(widgetEle, time);
		setScrollPosition((int) current);
	}

	@Override
	public void add(Widget w) {
		assert _panel.getWidgetCount() == 0 : "Can only add one widget to MScrollPanel.";
		super.add(w);
	}

}
