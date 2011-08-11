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
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * The class manages TabBar view behavior.
 * <p>
 * Manages a set of view controllers, each of which represents a tab bar item.
 * Each view controller provides information about its tab bar item and supplies
 * the view to be displayed when the item is selected.
 * 
 * <p>
 * <img class='ai' src='
 * 'http://next.com/wp-content/uploads/2011/05/tabs-gwt-touch-253x300.png'
 * />
 * </p>
 * 
 * <h3>Example:</h3>
 * 
 * <pre>
 * TabController tabOne = new TabController(new ControllerOne());
 * tabOne.set(&quot;Hello&quot;, new Image(&quot;tabIcon.png&quot;), new Image(&quot;tabSelected.png&quot;));
 * 
 * TabController tabTwo = new TabController(new TableController() {
 * 	{
 * 		setTitle(&quot;GoodBye&quot;);
 * 		TableDataSource tableDS = new TableDataSource();
 * 		tableDS.add(&quot;Good&quot;, &quot;Bye&quot;);
 * 		initDataSource(tableDS);
 * 	}
 * });
 * tabTwo.set(&quot;GoodBye&quot;, new Image(&quot;tabIcon.png&quot;), new Image(&quot;tabSelected.png&quot;));
 * 
 * TabBarController tabBar = new TabBarController();
 * tabBar.addControllers(tabOne, tabTwo);
 * </pre>
 */
public class XTabBarController {

	private XTabBar _view;
	private ArrayList<XTabController> _list;
	private XTabController _visibleTabController;

	public XTabBarController() {
		TabBarController_();
	}

	public void addControllers(XTabController... controllers) {
		addControllers_(controllers);
	}

	public void onTabChange(XTabController selectedTab) {
		onTabChange_(selectedTab);
	}

	public XTabController getVisibleTabController() {
		return _visibleTabController;
	}

	public XTabBar getTabBar() {
		return _view;
	}

	public void attach() {
		attach_();
	}

	/**
	 * private
	 */

	private void attach_() {
		if (!_view.isAttached()) {
			// getNavigation().setSize("100%", "100%");
			RootLayoutPanel.get().add(this._view);
		}
	}

	private void onTabChange_(XTabController selectedTab) {
		for (XTabController tc : _list) {
			tc.setSelected(false);
		}
		selectedTab.setSelected(true);
		_visibleTabController = selectedTab;
		addTabContent_(selectedTab);
	}

	private void addControllers_(final XTabController... controllers) {
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

	private void TabBarController_() {
		_view = new XTabBar();
		_list = new ArrayList<XTabController>();
		this.attach();
		RootAttacher.register(this);
	}

	private void addTabContent_(XTabController tab) {
		// Window.alert("added tab " + tab.hashCode());
		getTabBar().getContent().clear();
		// tab.getIController().getNavigation().asWidget().getElement().setId("xxxxxxxx");
		// Utils.fillParent(tab.getIController().getNavigation().asWidget().getElement());
		getTabBar().add(tab.getIController().getNavigationView());
		// getTabBar().get
		Utils.fillParent(tab.getIController().getNavigationView().asWidget().getElement());
		// tab.getUIController().getUiView().setHeight(Globals.getTabContentHeight()
		// + "px");
		// Window.alert("getOffsetHeight: " + Globals.getTabContentHeight());
	}

}
