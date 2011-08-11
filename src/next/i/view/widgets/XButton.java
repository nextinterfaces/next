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
 * Implements a button that intercepts touch events and sends an action message
 * to a target object when it's tapped. You can set the title, image, and other
 * appearance properties of a button. In addition, you can specify a different
 * appearance for each button state.
 * 
 * <p>
 * <img class='ai' src='../../../../resources/XButton.png' />
 * </p>
 */
public class XButton extends Composite implements HasClickHandlers, DragEventsHandler {

	/**
	 * Specifies the style of a button.
	 */
	public static enum XButtonType {
		Rounded, 
		Shadow,
		Custom, 
		Disclosure, 
		InfoLight, 
		InfoDark, 
		Add,
		Navigation
	}

	private XButtonType buttonType;

	// Configuring Button Title
	private String titleLabel;
	private boolean reversesTitleShadowWhenHighlighted;
	// � setTitle:forState:
	// � setTitleColor:forState:
	// � setTitleShadowColor:forState:
	// � titleColorForState:
	// � titleForState:
	// � titleShadowColorForState:

	// Configuring Button Images
	private boolean adjustsImageWhenHighlighted;
	private boolean adjustsImageWhenDisabled;
	private boolean showsTouchWhenHighlighted;

	// � backgroundImageForState:
	// � imageForState:
	// � setBackgroundImage:forState:
	// � setImage:forState:
	// //Configuring Edge Insets
	// contentEdgeInsets property
	// titleEdgeInsets property
	// imageEdgeInsets property
	// //Getting the Current State
	// currentTitle property
	// currentTitleColor property
	// currentTitleShadowColor property
	// currentImage property
	// currentBackgroundImage property
	// imageView property
	// //Getting Dimensions
	// � backgroundRectForBounds:
	// � contentRectForBounds:
	// � titleRectForContentRect:
	// � imageRectForContentRect:

	private FlexTable _item;
	private boolean _hasClickHandler = false;
	private boolean enabled = true;

	public XButton(String title) {
		this(title, XButtonType.Rounded, true);
	}

	public XButton(String title, XButtonType buttonType) {
		this(title, buttonType, true);
	}

	public XButton(String title, XButtonType buttonType, boolean iconOrientationLeft) {
		XButton_(buttonType, iconOrientationLeft);
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
			removeStyleName(XStyle.disabled.name());
		} else {
			addStyleName(XStyle.disabled.name());
		}
	}

	boolean hasClickHandler() {
		return _hasClickHandler;
	}

	public void setTitle(String title) {
		_item.setText(0, 0, title);
	}

//	public void setHighlighted(boolean isHightLighted) {
//		addStyleName(XStyle.highlighted.name());
//	}

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
			addStyleName(XStyle.selected.name());
		} else {
			removeStyleName(XStyle.selected.name());
		}
	}

	private void XButton_(XButtonType buttonType, boolean iconOrientationLeft) {
		_item = new FlexTable();
		_item.setCellPadding(0);
		_item.setCellSpacing(0);
		initWidget(_item);
		sinkEvents(Event.ONCLICK | Event.ONTOUCHCANCEL | Event.ONTOUCHEND | Event.ONTOUCHMOVE | Event.ONTOUCHSTART);

		setStyleName(XStyle.xbutton.name());

		_item.setHTML(0, 0, "&nbsp;");
		_item.setHTML(0, 1, "<div>&nbsp;</div>");

		FlexCellFormatter cf = _item.getFlexCellFormatter();
		cf.setStyleName(0, 0, "body");
		cf.setStyleName(0, 1, "right");

		cf.setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_MIDDLE);
		addStyleName(buttonType.name());

		if (buttonType == XButtonType.Rounded) {

		} else if (buttonType == XButtonType.Custom) {

		} else if (buttonType == XButtonType.Disclosure || buttonType == XButtonType.InfoDark
				|| buttonType == XButtonType.InfoLight || buttonType == XButtonType.Add) {
			if (iconOrientationLeft) {
				_item.getElement().getStyle().setProperty("paddingLeft", "30px");
				_item.getElement().getStyle().setProperty("backgroundPosition", "left center");

			} else {
				_item.getElement().getStyle().setProperty("paddingRight", "30px");
				_item.getElement().getStyle().setProperty("backgroundPosition", "right center");
			}

		}
	}

}