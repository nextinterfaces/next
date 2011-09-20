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

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HasVerticalAlignment;

/**
 * This class encapsulates information about a navigation item on the
 * NavigationBar object's stack, including what is displayed on the navigation
 * bar when the item is at the top of the stack and how it is represented when
 * it is the back item. To be represented on the navigation bar, a navigation
 * item must have a title.
 * 
 * <p>
 * It also represents an item in a Toolbar or NavigationItem. Each bar item
 * behaves similarly to a button, and has a title, image, action, and target.
 * The BarItem class provides methods you can use to specify bar button items
 * with system-provided images, such as the plus image.
 * </p>
 * 
 * <p>
 * <img class='ai' src='../../../resources/XBarItem.png' />
 * </p>
 */
public class XBarItem extends Composite implements HasClickHandlers, DragEventsHandler {

	private FlexTable _item;
	private boolean _hasClickHandler = false;
	private boolean enabled = true;

	// private boolean isBackButton;

	public static enum Type {
		BUTTON, BACK_BUTTON, TOGGLE_BUTTON, LABEL
	}

	XBarItem(Type type) {
		BarItem_(type);
	}

	public XBarItem(Type type, String title) {
		this(type);
		setTitle(title);
	}

	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		_hasClickHandler = true;
		return addHandler(handler, ClickEvent.getType());
	}

	@Override
	public void onLoad() {
		super.onLoad();
		DragController.get().addDragEventsHandler(this);
	}

	@Override
	public void onUnload() {
		DragController.get().removeDragEventsHandler(this);
	}

	void setSelected(boolean isActive) {
		setSelected_(isActive);
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		if (enabled) {
			XBarItem.this.removeStyleName(XStyle.disabled.name());
		} else {
			XBarItem.this.addStyleName(XStyle.disabled.name());
		}
	}

	boolean hasClickHandler() {
		return _hasClickHandler;
	}

	public void setTitle(String title) {
		_item.setText(0, 0, title);
	}

	public void setHighlighted(boolean isHightLighted) {
		addStyleName(XStyle.highlighted.name());
	}

	public void onDragEnd(DragEvent e) {
		setSelected_(false);
	}

	public void onDragMove(DragEvent e) {
		setSelected_(true);
	}

	public void onDragStart(DragEvent e) {
		setSelected_(true);
	}

	/**
	 * private
	 */

	private void setSelected_(boolean isActive) {
		if (isActive) {
			XBarItem.this.addStyleName(XStyle.selected.name());
		} else {
			XBarItem.this.removeStyleName(XStyle.selected.name());
		}
	}

	private void BarItem_(Type type) {
		_item = new FlexTable();
		_item.setCellPadding(0);
		_item.setCellSpacing(0);
		initWidget(_item);
		sinkEvents(Event.ONCLICK | Event.ONTOUCHCANCEL | Event.ONTOUCHEND | Event.ONTOUCHMOVE | Event.ONTOUCHSTART);

		setStyleName(XStyle.navBarItem.name());

		_item.setHTML(0, 0, "&nbsp;");
		if (type != Type.LABEL) {
			_item.setHTML(0, 1, "<div>&nbsp;</div>");
		}

		FlexCellFormatter cf = _item.getFlexCellFormatter();
		cf.setStyleName(0, 0, "body");
		if (type != Type.LABEL) {
			cf.setStyleName(0, 1, "right");
		}

		cf.setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_MIDDLE);

		if (type == Type.BACK_BUTTON) {
			addStyleName(XStyle.navBarItemBack.name());
		}
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
