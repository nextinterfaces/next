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
 * --------
 * This class contains modified sources from gwtmobile-ui project. 
 * 
 * Copyright (c) 2010 Zhihua (Dennis) Jiang
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

import java.util.Iterator;

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class MPanelBase extends Composite implements HasWidgets {

	protected FlowPanel _panel = new FlowPanel();

	public MPanelBase() {
		initWidget(_panel);
	}

	@Override
	public void add(Widget w) {
		_panel.add(w);
	}

	@Override
	public void clear() {
		_panel.clear();
	}

	public Element getParentContainer() {
		return (Element)RootLayoutPanel.get().getWidgetContainerElement(this);
	}

	@Override
	public Iterator<Widget> iterator() {
		return _panel.iterator();
	}

	@Override
	public boolean remove(Widget w) {
		return _panel.remove(w);
	}

	public Widget getWidget(int index) {
		return _panel.getWidget(index);
	}

	public int getWidgetCount() {
		return _panel.getWidgetCount();
	}

	public void insert(Widget w, int beforeIndex) {
		_panel.insert(w, beforeIndex);
	}

	public void remove(int index) {
		_panel.remove(index);
	}

}
