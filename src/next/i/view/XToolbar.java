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

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.IsWidget;

/**
 * Provides a mechanism for displaying a toolbar at the bottom of the screen and
 * supports the display and selection of toolbar items (instances of BarItem).
 * 
 * TODO to be implemented
 */
@Deprecated
class XToolbar extends Composite {

	// barStyle property
	// tintColor property
	// translucent property

	private FlowPanel wrapper;

	XToolbar() {
		Toolbar_();
	}

	public void addWidget(IsWidget isWidget) {
		wrapper.add(isWidget);
	}

	/**
	 * private
	 */

	private void Toolbar_() {
		wrapper = new FlowPanel();
		initWidget(wrapper);
//		wrapper.setStyleName(Style.navigationToolbar.name());
	}

}
