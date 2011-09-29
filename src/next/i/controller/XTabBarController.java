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
package next.i.controller;

import java.util.ArrayList;

import next.i.view.XTabBar;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * The class manages TabBar view behavior.
 * <p>
 * Manages a set of view controllers, each of which represents a tab bar item.
 * Each view controller provides information about its tab bar item and supplies
 * the view to be displayed when the item is selected.
 * 
 * <p>
 * <img src='../../../resources/XTabBar.png'/>
 * </p>
 * 
 * <h3>Example:</h3>
 * 
 * <pre>
 * XTabController tabOne = new XTabController(new ControllerOne());
 * tabOne.set(&quot;Tab1&quot;, new Image(&quot;tabIcon.png&quot;), new Image(&quot;tabSelected.png&quot;));
 * 
 * XTabController tabTwo = new XTabController(new TableController() {
 * 	{
 * 		setTitle(&quot;Tab2&quot;);
 * 		TableData tableDS = new TableData();
 * 		tableDS.add(&quot;A row&quot;, &quot;Another row&quot;);
 * 		initDataSource(tableDS);
 * 	}
 * });
 * tabTwo.set(&quot;Tab2&quot;, new Image(&quot;tabIcon.png&quot;), new Image(&quot;tabSelected.png&quot;));
 * 
 * XTabBarController tabBar = new XTabBarController();
 * tabBar.addControllers(tabOne, tabTwo);
 * tabBar.attach(RootLayoutPanel.get());
 * </pre>
 */
public class XTabBarController {

	private XTabBar _view;
	private ArrayList<XTabController> _list;
	private XTabController _visibleTabController;

	public XTabBarController() {
		_view = new XTabBar();
		_list = new ArrayList<XTabController>();
	}

	public void addControllers(XTabController... controllers) {
		if (controllers != null && controllers.length > 0) {

			double count = controllers.length;
			double width = 100.0 / count;
			for (XTabController c : controllers) {
				c.register(this);
				_list.add(c);
				_view.add(c.getTab());
				c.getTab().getElement().getStyle().setWidth(width, Unit.PCT);
				getTabBar().add(c.getIController().getNavigationView());
			}

			controllers[0].setSelected(true);
			_visibleTabController = controllers[0];
			addTabContent_(controllers[0]);
		}
	}

	public void onTabChange(XTabController selectedTab) {
		for (XTabController tc : _list) {
			tc.setSelected(false);
		}
		selectedTab.setSelected(true);
		_visibleTabController = selectedTab;
		addTabContent_(selectedTab);
	}

	public XTabController getVisibleTabController() {
		return _visibleTabController;
	}

	public XTabBar getTabBar() {
		return _view;
	}

	public void attach(HasWidgets container) {
		if (!_view.isAttached()) {
			container.add(this._view);
		}
		RootAttacher.register(this);
	}

	private void addTabContent_(XTabController tab) {
		getTabBar().getContent().clear();
		getTabBar().add(tab.getIController().getNavigationView());
		Utils.fillParent(tab.getIController().getNavigationView().asWidget().getElement());
	}

}
