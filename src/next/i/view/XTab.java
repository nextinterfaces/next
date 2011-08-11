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

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

/**
 * Represents a tab in a TabBar. I can contain text and/or an Image.
 * 
 * <p>
 * <img class='ai' src='../../../resources/XTab.png' />
 * </p>
 */
public class XTab extends Composite /* implements HasClickHandlers */{

	private FlowPanel _panel;
	private Image _iconSelected;
	private Image _icon;
	private XMobileFlexTable _content;

	public XTab() {
		Tab_();
	}

	public void setSelected(boolean selected) {
		setSelected_(selected);
	}

	public void setTitle(String title) {
		_panel.add(buildTab_(title));
	}

	// public void setIcon(String urlIcon, String urlSelected) {
	// icon = new Image(urlIcon);
	// iconSelected = new Image(urlSelected);
	// content.setWidget(0, 0, icon);
	// }

	public void setIcons(Image imageTab, Image imageTabSelected) {
		setIcons_(imageTab, imageTabSelected);
	}

	/**
	 * private
	 */
	private void Tab_() {
		_panel = new FlowPanel();
		_panel.setStyleName(XStyle.tab.name());
		initWidget(_panel);

		_icon = new Image();
		_iconSelected = new Image();
		_content = new XMobileFlexTable();
	}

	private void setSelected_(boolean selected) {
		if (selected) {
			addStyleName(XStyle.selected.name());
			_content.setWidget(0, 0, _iconSelected);

		} else {
			removeStyleName(XStyle.selected.name());
			_content.setWidget(0, 0, _icon);
		}
	}

	private void setIcons_(Image imageTab, Image imageTabSelected) {
		_icon = imageTab;
		_iconSelected = imageTabSelected;
		_content.setWidget(0, 0, _icon);
	}

	private Widget buildTab_(String txt) {
		_content.setWidget(0, 0, _icon);
		_content.setHTML(1, 0, txt);

		FlexCellFormatter fcf = _content.getFlexCellFormatter();
		fcf.setHeight(0, 0, "30px");
		fcf.setHeight(1, 0, "15px");
		fcf.setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_BOTTOM);
		fcf.setVerticalAlignment(1, 0, HasVerticalAlignment.ALIGN_TOP);
		fcf.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		fcf.setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);
		fcf.setStyleName(0, 0, "icon");
		fcf.setStyleName(1, 0, "title");

		return _content;
	}

}