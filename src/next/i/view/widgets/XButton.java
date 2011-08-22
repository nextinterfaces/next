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

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Timer;
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
		Rounded("Rounded"), 
		Shadow("Shadow"), 
		Navigation("Navigation"), 
		NavigationBlue("Blue"), 
		NavigationRed("Red"), 
		NavigationBlack("Black"), 
		Image("Image")
		;
		private String css;

		XButtonType(String css) {
			this.css = css;
		}

		public String css() {
			return css;
		}
	}

	private String title;

	private FlexTable _item;
	private boolean _hasClickHandler = false;
	private boolean enabled = true;

	public XButton(String title) {
		this(title, XButtonType.Rounded);
	}

	public XButton(String title, XButtonType buttonType) {
		this(title, buttonType, true);
	}

	public XButton(String title, XButtonType buttonType, String imageUrl, String imagePressedUrl, boolean orientation) {
		XButton_(buttonType, orientation, imageUrl, imagePressedUrl);
		setTitle(title);
	}

	public XButton(String title, XButtonType buttonType, boolean orientation) {
		XButton_(buttonType, orientation, null, null);
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

	@Override
	public void onBrowserEvent(Event e) {
		e.stopPropagation();
		super.onBrowserEvent(e);
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
		this.title = title;
		_item.setText(0, 0, title);
	}

	public String getTitle() {
		return title;
	}

	public void onDragEnd(DragEvent e) {
		new Timer() {
			public void run() {
				setSelected_(false);
			}
		}.schedule(200);
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

	private void XButton_(XButtonType buttonType, boolean iconOrientationLeft, String imageUrl, String imagePressedUrl) {
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

		if (buttonType == XButtonType.NavigationRed || buttonType == XButtonType.NavigationBlue
				|| buttonType == XButtonType.NavigationBlack) {
			addStyleName(XButtonType.Navigation.css() + " " + buttonType.css());

		} else {
			addStyleName(buttonType.css());
		}

		if (buttonType == XButtonType.Image) {
			Style s = _item.getElement().getStyle();
			// TODO fix pressed state
			s.setProperty("backgroundImage", "url(" + imageUrl + ")");

			if (iconOrientationLeft) {
				s.setProperty("paddingLeft", "30px");
				s.setProperty("backgroundPosition", "left center");

			} else {
				s.setProperty("paddingRight", "30px");
				s.setProperty("backgroundPosition", "right center");
			}
		}
	}

}