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
import next.i.view.XBarItem.Type;

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;

/**
 * Provides a mechanism for displaying a navigation bar just below the status
 * bar. To support navigation of hierarchical content, NavigationBar uses a
 * stack to manage instances of BarItem, each of which represents a state of the
 * navigation bar. By default, NavigationBar displays a back button on the left
 * and a title in the center, but you can specify custom views for these, in
 * addition to providing an optional button on the right of the navigation bar.
 * Note that if you use a NavigationController object to manage hierarchical
 * navigation, you should not directly access the navigation bar object.
 * 
 * <p>
 * <img class='ai' src='../../../resources/XNavigationBar.png' />
 * </p>
 */
public class XNavigationBar extends Composite {

	private FlexTable _bar;
	private XBarItem _buttonLeft;
	private XBarItem _buttonRight;
	private XBarItem _barTitle;

	public XNavigationBar() {
		NavigationBar_();
		setStyleName(XStyle.navigationBar.name());
	}

	public void setTitle(String title) {
		this._barTitle.setTitle(title);
	}

	public XBarItem getLeftButton() {
		return _buttonLeft;
	}

	public XBarItem getRightButton() {
		return _buttonRight;
	}

	public XBarItem getBarTitle() {
		return _barTitle;
	}

	public void repaint() {
		repaint_();
	}

	public void setLeftTitle(String title, Type type) {
		setLeftTitle_(title, type);
	}

	public void setRightTitle(String title) {
		setRightTitle_(title);
	}

	/**
	 * Private
	 */

	private void NavigationBar_() {
		_bar = new FlexTable();
		initWidget(_bar);
		// _bar.setStyleName(XStyle.navigationBar.name());

		_barTitle = new XBarItem(Type.LABEL);

		_barTitle.setStyleName(XStyle.navBarTitle.name());

		_bar.setWidget(0, 1, _barTitle);

		_bar.setWidth("100%");

		FlexCellFormatter fcf = _bar.getFlexCellFormatter();
		fcf.setWidth(0, 0, "15%");
		fcf.setWidth(0, 1, "70%");
		fcf.setWidth(0, 2, "15%");
		fcf.setHeight(0, 0, "100%");
		fcf.setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);
		fcf.setHorizontalAlignment(0, 2, HasHorizontalAlignment.ALIGN_RIGHT);
		fcf.setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_MIDDLE);
		fcf.setVerticalAlignment(0, 1, HasVerticalAlignment.ALIGN_MIDDLE);
		fcf.setVerticalAlignment(0, 2, HasVerticalAlignment.ALIGN_MIDDLE);
		fcf.setWordWrap(0, 1, false);
	}

	private void setLeftTitle_(String title, Type type) {
		if (title != null) {
			initLeftItem_(type);
			this._buttonLeft.setTitle(title);
		}
	}

	private void setRightTitle_(String title) {
		if (title != null) {
			initRightItem_(Type.BUTTON);
			this._buttonRight.setTitle(title);
		}
	}

	private void initLeftItem_(Type type) {
		if (_buttonLeft == null) {
			_buttonLeft = new XBarItem(type);
			_bar.setWidget(0, 0, _buttonLeft);
		}
	}

	private void initRightItem_(Type type) {
		if (_buttonRight == null) {
			_buttonRight = new XBarItem(type);
			_bar.setWidget(0, 2, _buttonRight);
		}
	}

	private void clearSelection_() {
		if (_buttonLeft != null) {
			_buttonLeft.setSelected(false);
		}
		if (_buttonRight != null) {
			_buttonRight.setSelected(false);
		}
	}

	private void repaint_() {
		FlexCellFormatter fcf = _bar.getFlexCellFormatter();
		Element elLeft = fcf.getElement(0, 0);
		// Element elCenter = fcf.getElement(0, 1);
		Element elRight = fcf.getElement(0, 2);
		int barWidth = _bar.getOffsetWidth();

		// Window.alert("left=" + elLeft.getOffsetWidth() + " center=" +
		// elCenter.getOffsetWidth() + " right=" + elRight.getOffsetWidth()
		// + " barWidth=" + barWidth);

		int widthLeft = elLeft.getOffsetWidth();
		int widthRight = elRight.getOffsetWidth();

		if (widthLeft > widthRight) {
			widthRight = widthLeft;
			fcf.setWidth(0, 0, widthLeft + "px");
			fcf.setWidth(0, 2, widthLeft + "px");
			fcf.setWidth(0, 1, (barWidth - 2 * widthLeft) + "px");

		} else if (widthLeft < widthRight) {
			widthLeft = widthRight;
			fcf.setWidth(0, 0, widthRight + "px");
			fcf.setWidth(0, 2, widthRight + "px");
			fcf.setWidth(0, 1, (barWidth - 2 * widthLeft) + "px");
		}

		// Window.alert(" after >>> left=" + elLeft.getOffsetWidth() + " center=" +
		// elCenter.getOffsetWidth() + " right="
		// + elRight.getOffsetWidth() + " barWidth=" + barWidth);
	}

}
