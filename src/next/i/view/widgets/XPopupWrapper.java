/*
 * Copyright 2011 Vancouver Ywebb Consulting Ltd
 * 
 * You may not use this file except in compliance with the License. You 
 * may obtain a copy of the License Agreement at
 * 
 * http://www.gwttouch.com/LICENSE
 */
package next.i.view.widgets;

import next.i.XStyle;
import next.i.util.FxUtil;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.PopupPanel;

public class XPopupWrapper {

	private class OverlayPanel extends PopupPanel implements HasClickHandlers {
		OverlayPanel() {
			super(false, true);
			addStyleName(XStyle.xpopup.name() + "-bg");
			sinkEvents(Event.ONCLICK);
		}

		@Override
		public HandlerRegistration addClickHandler(ClickHandler handler) {
			return addHandler(handler, ClickEvent.getType());
		}

		@Override
		public void hide(final boolean autoClosed) {
			FxUtil.fadeOut(this, new Command() {
				@Override
				public void execute() {
					OverlayPanel.super.hide(autoClosed);
					OverlayPanel.super.setVisible(false);
				}
			});
		}

		@Override
		public void show() {
			setVisible(false);
			super.show();
			FxUtil.fadeIn(this);
		}

	}

	private OverlayPanel overlay;

	private PopupPanel popup;

	public XPopupWrapper(PopupPanel popupPanel) {

		this.popup = popupPanel;
		popup.addStyleName(XStyle.xpopup.name());

		popupPanel.addCloseHandler(new CloseHandler<PopupPanel>() {
			@Override
			public void onClose(CloseEvent<PopupPanel> event) {
				overlay.hide();
			}
		});

		overlay = new OverlayPanel();
		overlay.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				overlay.hide();
				popup.hide();
			}
		});

		overlay.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				overlay.hide();
				popup.hide();
			}
		});

		// Window.addResizeHandler(new ResizeHandler() {
		// @Override
		// public void onResize(ResizeEvent event) {
		// DOM.setStyleAttribute(overlay.getElement(), "height", getHeight() +
		// "px");
		// DOM.setStyleAttribute(overlay.getElement(), "width",
		// Window.getClientWidth() + "px");
		// }
		// });
	}

	public void showOverlay() {
		overlay.show();
	}

	// private int getHeight() {
	// return Window.getClientWidth();
	// // if (MiscUtils.isIE()) {
	// // return Window.getClientWidth(); // IE workarounds..
	// // } else {
	// // return MiscUtils.getViewportHeight();
	// // }
	// }

}