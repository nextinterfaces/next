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
import next.i.util.Utils;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.dom.client.Node;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * 
 * <p>
 * <img class='ai' src='../../../../resources/XPopup.png' />
 * </p>
 */
public class XPopup extends SimplePanel {

	private boolean isCentered = false;

	private String top;
	private String right;
	private String bottom;
	private String left;

	public XPopup() {
		setStyleName("xpopupOverlay");

		addDomHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				hide();
			}
		}, ClickEvent.getType());
	}

	@Override
	public void onBrowserEvent(Event e) {
		EventTarget target = e.getEventTarget();
		Node node = Node.as(target);

		if (Element.is(node)) {
			Element ele = Element.as(target);
			// maek sure click is invoked from overlay not inner widget
			if (ele.getClassName().contains("xpopupOverlay")) {
				super.onBrowserEvent(e);
				return;
			}
		}
	}

	public void setTop(String value) {
		top = value;
	}

	public void setRight(String value) {
		right = value;
	}

	public void setBottom(String value) {
		bottom = value;
	}

	public void setLeft(String value) {
		left = value;
	}

	public void center() {
		isCentered = true;
	}

	@Override
	protected void onLoad() {
		super.onLoad();
		if (getWidget() != null) {
//			getWidget().addStyleName("xpopup");
//			Style s = getWidget().getElement().getStyle();
			doPosition();
		}
	}

	@Override
	public void setWidget(Widget w) {
		super.setWidget(w);
		w.addStyleName("xpopup");
	}

	public void doPosition() {
		
		Style s = getWidget().getElement().getStyle();
		
		if (isCentered) {

			setBottom(null);
			setRight(null);
			setTop("50%");
			setLeft("50%");
			int height = getWidget().getOffsetHeight();
			int width = getWidget().getOffsetWidth();

			// XLog.info("height: " + height + ", getOffsetHeight(): " +
			// getOffsetHeight());

			int top_ = height >= getOffsetHeight() ? 0 : (height / 2);
			int left_ = width >= getOffsetWidth() ? 0 : (width / 2);

			s.setProperty("marginTop", "-" + top_ + "px");
			s.setProperty("marginLeft", "-" + left_ + "px");

		} else {

			if (top == null) {
				s.clearProperty("top");
			} else {
				s.setProperty("top", top);
			}

			if (right == null) {
				s.clearProperty("right");
			} else {
				s.setProperty("right", right);
			}

			if (bottom == null) {
				s.clearProperty("bottom");
			} else {
				s.setProperty("bottom", bottom);
			}

			if (left == null) {
				s.clearProperty("left");
			} else {
				s.setProperty("left", left);
			}

		}

	}

	public void show() {
		RootLayoutPanel.get().add(this);
		if (Utils.isAndroid()) {
			this.setVisible(true);
			this.getElement().getStyle().setOpacity(1);
		} else {
			FxUtil.fadeIn(this.getElement());
		}
	}

	public void hide() {
		if (Utils.isAndroid()) {
			removeFromParent();
		} else {
			FxUtil.fadeOut(this.getElement(), 250, new Command() {
				public void execute() {
					// XPopup.super.hide(autoClosed);
					// setVisible(false);
					removeFromParent();
				}
			});
		}
	}

}
