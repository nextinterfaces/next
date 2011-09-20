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

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;

/**
 * This is a light alternative of &lt;img /&gt; using &lt;div /&gt; with a css
 * background-image instead.
 * <p>
 * The reason of this class existence is that &lt;img /&gt; object locks the DOM
 * until fully loaded causing DOM unresponsiveness. Thumb of rule, you should
 * avoid using images and use div's with backgrounds instead.
 */
public class XImageDiv extends Composite implements HasClickHandlers {

	private SimplePanel panel;

	public XImageDiv(String imagePath, String width, String height, String backgroundPosition) {
		panel = new SimplePanel();
		initWidget(panel);
		setStyleName(XStyle.ximage.name());

		Style s = panel.getElement().getStyle();
		s.setProperty("backgroundImage", "url('" + imagePath + "')");
		s.setProperty("backgroundRepeat", "no-repeat");

		if (backgroundPosition != null) {
			s.setProperty("backgroundPosition", backgroundPosition);
		}
		if (width != null) {
			s.setProperty("width", width);
		}
		if (height != null) {
			s.setProperty("height", height);
		}
	}

	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		return addDomHandler(handler, ClickEvent.getType());
	}

}
