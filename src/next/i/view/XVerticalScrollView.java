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
 * This view class accepts finger drag actions and can be scrolled vertically.
 * 
 * 
 * <p>
 * <img class='ai' src='../../../resources/XScrollView.png' />
 * </p>
 */
public class XVerticalScrollView extends MPanelBase implements HasWidgets, DragEventsHandler, SwipeEventsHandler, IView {

	private boolean _hasTextBox = false;
	private Element widgetElement;

	public XVerticalScrollView() {
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
		FxUtil.setTranslateY(el(), 0);
	}

	public void setPostionToTop() {
		FxUtil.setTransitionDuration(el(), 0);
		FxUtil.setTranslateY(el(), 0);
	}

	public void setPositionToBottom() {
		FxUtil.setTransitionDuration(el(), 0);
		FxUtil.setTranslateY(el(), this.getElement().getClientHeight()
				- this.getElement().getScrollHeight());
	}

	public void setScrollPositionY(double pos) {
		if (_hasTextBox) {
			FxUtil.setStyleTop(this, pos);
		} else {
			FxUtil.setTranslateY(el(), pos);
		}
	}

	public double getScrollPositionY() {
		if (_hasTextBox) {
			return FxUtil.getStyleTop(this);
		} else {
			return FxUtil.getTranslateY(el());
		}
	}

	public double getScrollToPositionY() {
		if (_hasTextBox) {
			return FxUtil.getStyleTop(this);
		} else {
			return FxUtil.getMatrixY(el());
		}
	}

	@Override
	public void onDragStart(DragEvent e) {
		double matrixY = getScrollToPositionY();
		double currY = getScrollPositionY();
		FxUtil.setTransitionDuration(el(), 0);
		if (currY != matrixY) {
			// scroll on going
			double diff = currY - matrixY;
			double offset = diff > 2 ? 2 : diff > -2 ? diff : -2;
			setScrollPositionY(matrixY + offset);
			DragController.get().suppressNextClick();
		}
	}

	@Override
	public void onDragMove(DragEvent e) {
		int panelHeight = Utils.getHeight(this.getElement());
		int widgetHeight = el().getOffsetHeight();
		double currY = getScrollPositionY();
		if (currY > 0) {
			// exceed top boundary
			if (e.OffsetY > 0) {
				// resist scroll down.
				currY += (int) (e.OffsetY / 2);
				// need the cast for production mode.
			} else {
				currY += e.OffsetY * 2;
			}
		} else if (-currY + panelHeight > widgetHeight) {
			// exceed bottom boundary
			if (e.OffsetY < 0) {
				// resist scroll up.
				currY += (int) (e.OffsetY / 2);
			} else {
				currY += e.OffsetY * 2;
			}
		} else {
			currY += e.OffsetY;
		}
		setScrollPositionY(currY);
	}

	@Override
	public void onDragEnd(DragEvent e) {
		double currY = getScrollPositionY();
		if (currY == 0) {
			return;
		}
		int panelHeight = Utils.getHeight(this.getElement());
		int widgetHeight = el().getOffsetHeight();
		// exceed top boundary
		if (currY > 0 || panelHeight > widgetHeight) {
			FxUtil.setTransitionDuration(el(), 500);
			setScrollPositionY(0);
		} else if (-currY + panelHeight > widgetHeight) {
			// exceed bottom boundary
			FxUtil.setTransitionDuration(el(), 500);
			setScrollPositionY(panelHeight - widgetHeight);
		}
	}

	@Override
	public void onSwipeVertical(SwipeEvent e) {
		int panelHeight = Utils.getHeight(this.getElement());
		int widgetHeight = el().getOffsetHeight();
		double currY = getScrollPositionY();
		// exceed top boundary
		if ((currY >= 0) || (-currY + panelHeight >= widgetHeight)) {
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
		currY += distance;
		if (currY > 0) {
			// exceed top boundary
			double timeAdj = 1 - (double) currY / distance;
			time = (long) (time * timeAdj);
			currY = 0;
		} else if (-currY + panelHeight > widgetHeight) {
			// exceed bottom boundary
			long bottom = panelHeight - widgetHeight;
			double timeAdj = 1 - (double) (currY - bottom) / distance;
			time = (long) (time * timeAdj);
			currY = bottom;
		}
		FxUtil.setTransitionDuration(el(), time);
		setScrollPositionY((int) currY);
	}

	@Override
	public void onSwipeHorizontal(SwipeEvent e) {
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

//	private void _lazyInit() {
//		// lazy init. no reason to calculate each time in onDragMove
//		if (panelWidth < 1) {
//			panelWidth = Utils.getWidth(this.getElement());
//		}
//		if (widgetWidth < 1) {
//			widgetWidth = el().getOffsetWidth();
//		}
//	}

}
