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

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasHorizontalAlignment.HorizontalAlignmentConstant;
import com.google.gwt.user.client.ui.Widget;

public class XFlexTable extends FlexTable {

	public XFlexTable() {
		this("100%", "#fff");
	}

	public XFlexTable(String height, String background) {
		setWidth("100%");
		setCellPadding(0);
		setCellSpacing(0);
		if (height != null) {
			getElement().getStyle().setProperty("height", height);
		}
		if (background != null) {
			getElement().getStyle().setProperty("background", background);
		}
	}
  public void add(Widget child) {
		addWidgets(HasHorizontalAlignment.ALIGN_CENTER, child);
  }

	public void addWidgets(Widget... widgets) {
		addWidgets(HasHorizontalAlignment.ALIGN_CENTER, widgets);
	}

	public void addWidgets(HorizontalAlignmentConstant align, Widget... widgets) {
		for (Widget w : widgets) {
			int nextRow = getRowCount();
			setWidget(nextRow, 0, w);
			getFlexCellFormatter().setHorizontalAlignment(nextRow, 0, align);
		}
	}

}
