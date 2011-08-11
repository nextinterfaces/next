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


import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * Utility class attaching controller view into the global RootLayoutPanel, thus
 * view stretches vertically and horizontally occupying whole screen;
 */
class RootAttacher {

	static XTabBarController _tabBarController;

	static void attach(XController controller) {

		if (RootAttacher._tabBarController == null) {
			RootLayoutPanel.get().add(controller.getNavigationView());

		} else {
			// Utils.fillParent(controller.getNavigation().getElement());
			_tabBarController.getTabBar().add(controller.getNavigationView());
			Utils.fillParent(controller.getNavigationView().getElement());
		}
	}

	static void register(XTabBarController tabBarController) {
		RootAttacher._tabBarController = tabBarController;
	}

}
