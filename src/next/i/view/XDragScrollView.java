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
 * This view class accepts finger drag actions and can be scrolled horizontally
 * and vertically.
 */
public class XDragScrollView extends MPanelBase implements HasWidgets, DragEventsHandler, SwipeEventsHandler, IView {

	private boolean _hasTextBox = false;
	private Element widgetElement;
	private int panelWidth = -1;
	private int widgetWidth = -1;
	private int panelHeight = -1;
	private int widgetHeight = -1;
	
	public XDragScrollView() {
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
		FxUtil.setTranslateY(el(), 0);
	}

	public void setPostionToTop() {
		FxUtil.setTransitionDuration(el(), 0);
		FxUtil.setTranslateX(el(), 0);
		FxUtil.setTranslateY(el(), 0);
	}

	public void setPositionToBottom() {
		FxUtil.setTransitionDuration(el(), 0);
		//FxUtil.setTranslateX(el(), this.getElement().getClientWidth() - this.getElement().getScrollWidth());
		FxUtil.setTranslateXY(el(), 
				(this.getElement().getClientWidth() - this.getElement().getScrollWidth()),
				(this.getElement().getClientHeight() - this.getElement().getScrollHeight()));
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

	public void setScrollPositionXY(double posX, double posY) {
		if (_hasTextBox) {
			FxUtil.setStyleLeft(this, posX);
			FxUtil.setStyleTop(this, posY);
		} else {
			FxUtil.setTranslateXY(el(), posX, posY);
		}
	}

	@Override
	public void onDragStart(DragEvent e) {

		_lazyInit();

		double matrixX = getScrollToPositionX();
		double currX = getScrollPositionX();
		double matrixY = getScrollToPositionY();
		double currY = getScrollPositionY();
		FxUtil.setTransitionDuration(el(), 0);
		if (currX != matrixX || currY != matrixY) {
			// scroll on going
			double diffX = currX - matrixX;
			double offsetX = diffX > 2 ? 2 : diffX > -2 ? diffX : -2;
//			setScrollPositionX(matrixX + offsetX);
			double diffY = currY - matrixY;
			double offsetY = diffY > 2 ? 2 : diffY > -2 ? diffY : -2;
//			setScrollPositionY(matrixY + offsetY);
			setScrollPositionXY(matrixX + offsetX, matrixY + offsetY);
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
//		setScrollPositionX(currX);
		
		
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
		
		setScrollPositionXY(currX, currY);
	}

	@Override
	public void onDragEnd(DragEvent e) {

		double currX = getScrollPositionX();
		double currY = getScrollPositionY();
		if (currX == 0 || currY == 0) {
			return;
		}
		
		// exceed left boundary
		if (currX > 0 || panelWidth > widgetWidth) {
			FxUtil.setTransitionDuration(el(), 500);
			setScrollPositionXY(0, getScrollPositionY());

		} else if (-currX + panelWidth > widgetWidth) {
			// exceed right boundary
			FxUtil.setTransitionDuration(el(), 500);
			setScrollPositionXY(panelWidth - widgetWidth, getScrollPositionY());
		}
		
		// exceed top boundary
		if (currY > 0 || panelHeight > widgetHeight) {
			FxUtil.setTransitionDuration(el(), 500);
			setScrollPositionXY(getScrollPositionX(), 0);
		} else if (-currY + panelHeight > widgetHeight) {
			// exceed bottom boundary
			FxUtil.setTransitionDuration(el(), 500);
			setScrollPositionXY(getScrollPositionX(), panelHeight - widgetHeight);
		}
	}

	@Override
	public void onSwipeVertical(SwipeEvent e) {
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
		setScrollPositionXY(getScrollPositionX(), (int) currY);
	}

	@Override
	public void onSwipeHorizontal(SwipeEvent e) {
		double currX = getScrollPositionX();

		// exceed left boundary
		if ((currX >= 0) || (-currX + panelWidth >= widgetWidth)) {
			// exceed right boundary
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
			// exceed left boundary
			double timeAdj = 1 - (double) currX / distance;
			time = (long) (time * timeAdj);
			currX = 0;
		} else if (-currX + panelWidth > widgetWidth) {
			// exceed right boundary
			long bottom = panelWidth - widgetWidth;
			double timeAdj = 1 - (double) (currX - bottom) / distance;
			time = (long) (time * timeAdj);
			currX = bottom;
		}
		FxUtil.setTransitionDuration(el(), time);
		setScrollPositionXY((int) currX, getScrollPositionY());
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
			panelWidth = FxUtil.getWidth(this.getElement());
		}
		if (widgetWidth < 1) {
			widgetWidth = el().getOffsetWidth();
		}

		panelHeight = FxUtil.getHeight(this.getElement());
		widgetHeight = el().getOffsetHeight();
	}

	@Override
	public void onDragMoveHorizontal(DragEvent e) {
		// Not implemented
	}

	@Override
	public void onDragMoveVertical(DragEvent e) {
		// Not implemented
	}

}
