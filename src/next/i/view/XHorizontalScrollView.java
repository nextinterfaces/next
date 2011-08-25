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
	private Element widgetElement;
	private int panelWidth = -1;
	private int widgetWidth = -1;

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
		FxUtil.setTransitionDuration(el(), 0);
		FxUtil.setTranslateX(el(), 0);
	}

	public void setPostionToTop() {
		FxUtil.setTransitionDuration(el(), 0);
		FxUtil.setTranslateX(el(), 0);
	}

	public void setPositionToBottom() {
		FxUtil.setTransitionDuration(el(), 0);
		FxUtil.setTranslateX(el(), this.getElement().getClientWidth() - this.getElement().getScrollWidth());
	}

	public void setScrollPositionX(double pos) {
		if (_hasTextBox) {
			FxUtil.setStyleLeft(this, pos);
		} else {
			FxUtil.setTranslateX(el(), pos);
		}
	}

	public double getScrollPositionX() {
		if (_hasTextBox) {
			return FxUtil.getStyleLeft(this);
		} else {
			return FxUtil.getTranslateX(el());
		}
	}

	public double getScrollToPositionX() {
		if (_hasTextBox) {
			return FxUtil.getStyleLeft(this);
		} else {
			return FxUtil.getMatrixX(el());
		}
	}

	@Override
	public void onDragStart(DragEvent e) {

		_lazyInit();

		double matrixX = getScrollToPositionX();
		double currX = getScrollPositionX();
		FxUtil.setTransitionDuration(el(), 0);
		if (currX != matrixX) {
			// scroll on going
			double diffX = currX - matrixX;
			double offsetX = diffX > 2 ? 2 : diffX > -2 ? diffX : -2;
			setScrollPositionX(matrixX + offsetX);
			DragController.get().suppressNextClick();
		}
	}

	@Override
	public void onDragMove(DragEvent e) {
		double currX = getScrollPositionX();
		// exceed top boundary
		if (currX > 0) {
			// resist scroll down.
			if (e.OffsetX > 0) {
				currX += (int) (e.OffsetX / 2);
			} else {
				currX += e.OffsetX * 2;
			}
			// exceed bottom boundary
		} else if (-currX + panelWidth > widgetWidth) {
			// resist scroll up.
			if (e.OffsetX < 0) {
				currX += (int) (e.OffsetX / 2);
			} else {
				currX += e.OffsetX * 2;
			}
		} else {
			currX += e.OffsetX;
		}
		// XLog.info("current: " + current + ", e.OffsetX: " + e.OffsetX +
		// ", e.OffsetY: " + e.OffsetY + ", e.X: " + e.X
		// + ", e.Y: " + e.Y);
		setScrollPositionX(currX);
	}

	@Override
	public void onDragEnd(DragEvent e) {

		double currX = getScrollPositionX();
		if (currX == 0) {
			return;
		}
		// exceed top boundary
		if (currX > 0 || panelWidth > widgetWidth) {
			FxUtil.setTransitionDuration(el(), 500);
			setScrollPositionX(0);

		} else if (-currX + panelWidth > widgetWidth) {
			// exceed bottom boundary
			FxUtil.setTransitionDuration(el(), 500);
			setScrollPositionX(panelWidth - widgetWidth);
		}
	}

	@Override
	public void onSwipeVertical(SwipeEvent e) {
	}

	@Override
	public void onSwipeHorizontal(SwipeEvent e) {
		double currX = getScrollPositionX();

		// exceed top boundary
		if ((currX >= 0) || (-currX + panelWidth >= widgetWidth)) {
			// exceed bottom boundary
			return;
		}

		double speed = e.getSpeed();
		double timeFactor = 2800;
		long time = (long) Math.abs(speed * timeFactor);
		double dicstanceFactor = 0.24;
		long distance = (long) (speed * time * dicstanceFactor);
		// Utils.Console("speed " + speed + " time " + time + " distance " +
		// distance + " currX " + currX);
		currX += distance;
		if (currX > 0) {
			// exceed top boundary
			double timeAdj = 1 - (double) currX / distance;
			time = (long) (time * timeAdj);
			currX = 0;
		} else if (-currX + panelWidth > widgetWidth) {
			// exceed bottom boundary
			long bottom = panelWidth - widgetWidth;
			double timeAdj = 1 - (double) (currX - bottom) / distance;
			time = (long) (time * timeAdj);
			currX = bottom;
		}
		FxUtil.setTransitionDuration(el(), time);
		setScrollPositionX((int) currX);
	}

	@Override
	public void add(Widget w) {
		assert _panel.getWidgetCount() == 0 : "Can only add one widget to MScrollPanel.";
		super.add(w);
	}

	private Element el() {
		if (widgetElement == null) {
			widgetElement = getWidget().getElement();
		}
		return widgetElement;
	}

	private void _lazyInit() {
		// lazy init. no reason to calculate each time in onDragMove
		if (panelWidth < 1) {
			panelWidth = Utils.getWidth(this.getElement());
		}
		if (widgetWidth < 1) {
			widgetWidth = el().getOffsetWidth();
		}
	}

}
