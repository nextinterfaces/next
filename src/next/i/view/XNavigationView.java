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
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;

/**
 * NavigationView defines a window object, which provides an area for displaying
 * the NavigationBar(top) and the content IView. The latter are being animated
 * during XController transitions.
 * 
 * <p>
 * <img class='ai' src='../../../resources/XNavigationView.png' />
 * </p>
 */
public class XNavigationView extends Composite implements IView {

	private SimplePanel _navigationBar;
	private SimplePanel _navContent;

	// private Toolbar _toolbar;

	public XNavigationView() {
		NavigationView_();
	}

	// public Toolbar getToolbar() {
	// return _toolbar;
	// }

	public void setNavigationBar(XNavigationBar navigationBar) {
		this._navigationBar.add(navigationBar);
	}

//	@Override
	public void setNavigationContent(IsWidget w) {
		this._navContent.add(w);
	}

	/**
	 * private
	 */

	private void NavigationView_() {
		XMobileFlexTable panel = new XMobileFlexTable();
		initWidget(panel);

		_navigationBar = new SimplePanel();
		_navigationBar.getElement().getStyle().setProperty("height", "100%");
//		_navigationBar.setStyleName(XStyle.navigationBar.name());

		_navContent = new SimplePanel();
		_navContent.setStyleName(XStyle.navigationContent.name());

		// _toolbar = new Toolbar();

		panel.setWidget(0, 0, _navigationBar);
		panel.setWidget(1, 0, _navContent);
		// _wrapper.setWidget(2, 0, _toolbar);
		// toolbar.addWidget(new HTML("bottom toolbar"));

		FlexCellFormatter fcf = panel.getFlexCellFormatter();
		fcf.setHeight(0, 0, XGlobals.NAVIGATION_BAR_HEIGHT + "px");
		fcf.setHeight(1, 0, "99%");
		// fcf.setHeight(2, 0, Globals.NAVIGATION_TOOL_BAR_HEIGHT + "px");

		// Utils.disableTextSelect(getElement(), true);
	}
}
