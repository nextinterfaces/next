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
package next.i.view.widgets;

import next.i.util.FxUtil;

import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.PopupPanel;
/**
 * 
 * <p>
 * <img class='ai' src='../../../../resources/XPopup.png' />
 * </p>
 */
public class XPopup extends PopupPanel {

	private final XPopupWrapper overlayWrapper;

	private Style s = getElement().getStyle();

	private boolean positionChanged = false;

	private Double top;
	private Double right;
	private Double bottom;
	private Double left;
	private Unit topUnit;
	private Unit rightUnit;
	private Unit bottomUnit;
	private Unit leftUnit;

	public XPopup() {
		this(false, false);
	}

	private XPopup(boolean autoHide, boolean modal) {
		super(autoHide, modal);
		overlayWrapper = new XPopupWrapper(this);
	}

	public void setTop(Double value, Unit unit) {
		positionChanged = true;
		top = value;
		topUnit = unit;
	}

	public void setRight(Double value, Unit unit) {
		positionChanged = true;
		right = value;
		rightUnit = unit;
	}

	public void setBottom(Double value, Unit unit) {
		positionChanged = true;
		bottom = value;
		bottomUnit = unit;
	}

	public void setLeft(Double value, Unit unit) {
		positionChanged = true;
		left = value;
		leftUnit = unit;
	}

	@Override
	public void show() {
		overlayWrapper.showOverlay();
		if (positionChanged) {
			setVisible(false);
			super.show();
			doPosition();
			FxUtil.fadeIn(this);

		} else {
			super.show();
		}
	}

	@Override
	public void hide(final boolean autoClosed) {
		FxUtil.fadeOut(this, new Command() {
			@Override
			public void execute() {
				XPopup.super.hide(autoClosed);
				XPopup.super.setVisible(false);
			}
		});
	}

	private void doPosition() {
		if (top == null) {
			s.clearProperty("top");
		} else {
			s.setTop(top, topUnit);
		}

		if (right == null) {
			s.clearProperty("right");
		} else {
			s.setRight(right, rightUnit);
		}

		if (bottom == null) {
			s.clearProperty("bottom");
		} else {
			s.setBottom(bottom, bottomUnit);
		}

		if (left == null) {
			s.clearProperty("left");
		} else {
			s.setLeft(left, leftUnit);
		}
	}

	// TODO move to utilities
	private static native int getViewportHeight() /*-{
		var viewportHeight;
		var d = $doc;
		var w = $wnd;

		if (w.innerHeight && w.scrollMaxY) {
			viewportHeight = w.innerHeight + w.scrollMaxY;

		} else if (d.body.scrollHeight > d.body.offsetHeight) {
			// all but explorer mac
			viewportHeight = d.body.scrollHeight;

		} else {
			// explorer mac...would also work in explorer 6 strict, mozilla and safari
			viewportHeight = d.body.offsetHeight;
		}

		return viewportHeight;
	}-*/;

	// TODO move to utilities
	private static native int getVerticalOffset() /*-{
		var d = $doc;
		var w = $wnd;
		// viewport vertical scroll offset
		var verticalOffset;

		if (self.pageYOffset) {
			verticalOffset = self.pageYOffset;

		} else if (d.documentElement && d.documentElement.scrollTop) {
			// Explorer 6 Strict
			verticalOffset = d.documentElement.scrollTop;

		} else if (d.body) {
			// all other Explorers
			verticalOffset = d.body.scrollTop;
		}
		return verticalOffset;
	}-*/;

}