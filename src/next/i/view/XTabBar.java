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
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.IsWidget;

/**
 * This class displays a tab bar at the bottom of the screen and supports the
 * display and selection of tab bar items (instances of TabBarItem).
 * 
 * <p>
 * <img class='ai' src='../../../resources/XTabBar.png' />
 * </p>
 */
public class XTabBar extends Composite {

	private FlowPanel _bar;
	private FlowPanel _content;
	private FlowPanel _wrapper;

	public XTabBar() {
		TabBar_();
	}

	public void add(XTab tab) {
		_bar.add(tab);
	}

	public void add(IsWidget view) {
		_content.add(view.asWidget());
	}

	public FlowPanel getContent() {
		return _content;
	}

	/**
	 * private
	 */
	private void TabBar_() {
		_bar = new FlowPanel();
		// bar.getElement().setId(Style.tabBar.name());
		_bar.setStyleName(XStyle.tabBar.name());
		_bar.setHeight(XGlobals.TAB_BAR_HEIGHT + "px");
		_content = new FlowPanel();
		_content.getElement().getStyle().setProperty("bottom", XGlobals.TAB_BAR_HEIGHT + "px");
		_content.setStyleName("tabContent");
		_wrapper = new FlowPanel();

		_wrapper.add(_content);
		_wrapper.add(_bar);
		initWidget(_wrapper);
	}

}
