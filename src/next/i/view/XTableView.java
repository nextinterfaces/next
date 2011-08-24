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


import java.util.ArrayList;
import java.util.List;

import next.i.XStyle;


import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Displays a single-column list of multiple rows through which users can
 * scroll. Each row in a table view is a TableCell object. The plain TableStyle
 * displays rows that occupy the full width of the view and can display optional
 * headers and footers for arbitrary sections of rows and for the table as a
 * whole.
 * 
 * <p>
 * <img class='ai' src='../../../resources/XTableView.png' />
 * </p>
 */
public class XTableView extends XVerticalScrollView {

	private FlowPanel _panel;
	private ArrayList<XTableCell> _items;
	private boolean _showChevron = false;

	public XTableView() {
		TableView_();
	}

	public void addItem(XTableCell... cell) {
		if(cell != null){
			for (XTableCell c : cell) {
				addItem_(c);
			}
		}
	}

	public List<XTableCell> getItems() {
		return _items;
	}

	public void showChevron(boolean showChevron) {
		showChevron_(showChevron);
	}

	/**
	 * private
	 */

	private void TableView_() {
		_panel = new FlowPanel();
		super.add(_panel);
		_panel.setStyleName(XStyle.tableView.name());
		_items = new ArrayList<XTableCell>();
	}

	private void addItem_(XTableCell cell) {
		if (_showChevron) {
			cell.showChevron(_showChevron);
		}
		_panel.add(cell);
		_items.add(cell);
	}

	@SuppressWarnings("unused")
	@Override
	public void add(Widget w) {
		assert true : "Method not implemented. Use addItem instead";
	}

	private void showChevron_(boolean showChevron) {
		this._showChevron = showChevron;
		for (XTableCell cell : _items) {
			cell.showChevron(showChevron);
		}
	}

}
