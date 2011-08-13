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


import java.util.List;

import next.i.XStyle;
import next.i.view.CellData;
import next.i.view.IView;
import next.i.view.TableData;
import next.i.view.XTableCell;
import next.i.view.XTableStyle;
import next.i.view.XTableView;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.IsWidget;

/**
 * Manages a TableView, automatically creating an instance with the correct
 * dimensions and resizing mask, and acting as the table view's delegate and
 * data source.
 * 
 * <p>
 * <img src='../../../resources/XTableView.png'/>
 * </p>
 * 
 * <h3>Example:</h3>
 * 
 * <pre>
 * public class MyTableController extends XTableController {
 * 
 * 	public MyTableController() {
 * 
 * 		setTitle(&quot;Hello World&quot;);
 * 
 * 		TableData tableDS = new TableData();
 * 		tableDS.add(&quot;Hello&quot;, &quot;World&quot;);
 * 
 * 		initDataSource(tableDS);
 * 	}
 * }
 * </pre>
 */
public class XTableController extends XController {

	private XTableView _view;
	private XTableStyle _style;
	private TableData _dataSource;
	private XTableCell _selectedCell;

	public XTableController() {
		TableController_();
	}

	public XTableController(XTableStyle style, TableData dataSource) {
		TableController_(style, dataSource);
	}

	public void initDataSource(TableData dataSource) {
		initDataSource_(dataSource);
	}

	public IView getView() {
		if (_view == null) {
			_view = new XTableView();
		}
		return _view;
	}

	/**
	 * Callback factory method invoked on row selection. Implement this method to
	 * hook
	 */
	public void onRowSelected(int indexSelected, CellData cellDataSelected, XTableCell tableCell) {
		// will be implemented by children
	}

	/**
	 * Callback factory method invoked on row selection. Implement this method to
	 * hook
	 */
	public void doOnRowSelected(int indexSelected, CellData cellDataSelected, XTableCell tableCell) {
		doOnRowSelected_(indexSelected, cellDataSelected, tableCell);
	}

	@Override
	void doOnHideComplete() {
		doOnHideComplete_();
	}

	public void onAfterLoad() {
		// Utils.setStyle(this, Transition.BEFORE, Transition.start);
	}

	/**
	 * private
	 */

	private void initDataSource_(TableData dataSource) {
		this._dataSource = dataSource;

		if (dataSource != null) {
			List<CellData> list = dataSource.getList();
			int len = list.size();
			for (int i = 0; i < len; i++) {
				CellData data = list.get(i);
				assert data.getTextWidgets().length > 0 : "CellData must contain at least one textWidget or title";
//				Widget titleWidget = data.getTextWidgets()[0];
				XTableCell cell = new XTableCell(i, this, data);
				_view.addItem(cell);
			}
		}
	}

	private void TableController_() {
		this._style = XTableStyle.Plain;
		// this.view = new TableView();

		// Utils.setStyle(this, Transition.BEFORE, Transition.start);
	}

	private void TableController_(XTableStyle style, TableData dataSource) {
		TableController_();
		this._style = style;
		initDataSource_(dataSource);
	}

	private void doOnRowSelected_(int indexSelected, CellData dataSelected, XTableCell tableCell) {
		this._selectedCell = tableCell;
		onRowSelected(indexSelected, dataSelected, tableCell);

		new Timer() {
			public void run() {
				_selectedCell.removeStyleName(XStyle.selected.name());
			}
		}.schedule(300);
	}

	private void doOnHideComplete_() {
		super.doOnHideComplete();
		if (_selectedCell != null) {
			_selectedCell.removeStyleName(XStyle.selected.name());
		}
	}

	@Override
	public IsWidget getViewContent() {
//		Window.alert("TableController_getViewContent");
		return null;
	}

}
