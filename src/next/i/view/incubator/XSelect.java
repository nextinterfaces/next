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
package next.i.view.incubator;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;

/**
 * Simplified XPicker version
 */
public class XSelect extends Composite {

	private final ListBox listBox;
	private final HTML overlay;
	private final FlowPanel flowPanel;

	public XSelect() {
		flowPanel = new FlowPanel();

		initWidget(flowPanel);
		setStyleName("xselect");

		overlay = new HTML();
		overlay.setStyleName("overlay");
		listBox = new ListBox();
		flowPanel.add(overlay);
		flowPanel.add(listBox);

		listBox.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				repaint();
			}
		});

		listBox.setStyleName("list");
		// repaint();
	}

	public void repaint() {
		if (listBox.getSelectedIndex() > 0) {
			String item = listBox.getValue(listBox.getSelectedIndex());
			overlay.setText("+" + item);
		}
	}

	public ListBox getListBox() {
		return listBox;
	}

}