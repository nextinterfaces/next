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

import next.i.XLog;
import next.i.XStyle;
import next.i.controller.XTableController;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * This class defines the attributes and behavior of cells in a table view.
 * 
 * <p>
 * <img class='ai' src='../../../resources/XTableCell.png' />
 * </p>
 */
public class XTableCell extends Composite implements IsWidget, HasClickHandlers {

	private Widget _panel;
	private boolean _isFromController = false;
	private CellData data;

	/**
	 * Use this constructor to manually create a XTableCell using CellData.
	 */
	public XTableCell(CellData cellData) {
		XTableCell_(cellData);
	}

	public XTableCell(Image westIcon, Label titleLabel, Image eastIcon) {
		XTableCell_(westIcon, titleLabel, eastIcon);
	}

	/**
	 * This constructor is used by XTableController to bind the Cell and the View
	 * together.
	 */
	public XTableCell(final int index, final XTableController tableController, final CellData data) {
		_isFromController = true;

		XTableCell_(data);

		addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				_panel.addStyleName(XStyle.selected.name());
				// Window.alert("selecetd...");
				new Timer() {
					public void run() {
						if (tableController != null) {
							tableController.doOnRowSelected(index, data, XTableCell.this);
						}
					}
				}.schedule(10);
			}
		});
	}

	public HandlerRegistration addClickHandler(ClickHandler handler) {
		return addClickHandler_(handler);
	}

	public void showChevron(boolean showChevron) {
		showChevron_(showChevron);
	}

	/**
	 * private
	 */

	private HandlerRegistration addClickHandler_(ClickHandler handler) {
		return addDomHandler(handler, ClickEvent.getType());
	}

	private void showChevron_(boolean showChevron) {
		if (showChevron) {
			addStyleName(XStyle.chevron.name());

		} else {
			removeStyleName(XStyle.chevron.name());
		}
	}

	private void XTableCell_(CellData cellData) {

		FlexTable xt = new FlexTable();
		_panel = xt;
		initWidget(_panel);
		_panel.setStyleName(XStyle.tableCell.name());
		FlexCellFormatter fcf = xt.getFlexCellFormatter();

		Widget[] westW = cellData.getWestWidgets();
		Widget[] eastW = cellData.getEastWidgets();
		Widget[] textW = cellData.getTextWidgets();

		int colInx = 0;
		int westLen = 0;

		if (westW != null) {
			westLen = westW.length;

			for (Widget w : westW) {
				xt.setWidget(0, colInx, w);
				if (textW != null) {
					fcf.setRowSpan(0, colInx, textW.length);
					fcf.setHorizontalAlignment(0, colInx, HasHorizontalAlignment.ALIGN_LEFT);
					fcf.setVerticalAlignment(0, colInx, HasVerticalAlignment.ALIGN_MIDDLE);
				}
				colInx++;
			}
		}
		if (textW != null) {
			for (int i = 0; i < textW.length; i++) {
				if (i == 0) {
					xt.setWidget(i, colInx, textW[i]);
					fcf.setWidth(i, colInx, "99%");
				} else {
					xt.setWidget(i, colInx - westLen, textW[i]);
				}
			}
			colInx++;
		}
		if (eastW != null) {
			for (Widget w : eastW) {
				xt.setWidget(0, colInx, w);
				if (textW != null) {
					fcf.setRowSpan(0, colInx, textW.length);
					fcf.setHorizontalAlignment(0, colInx, HasHorizontalAlignment.ALIGN_RIGHT);
				}
				colInx++;
			}
		}

		// TODO toggle state
		if (!_isFromController) {
			addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					_panel.addStyleName(XStyle.selected.name());
					new Timer() {
						public void run() {
							removeStyleName(XStyle.selected.name());
						}
					}.schedule(300);
				}
			});
		}
	}

	private void XTableCell_(Image westIcon, Label titleLabel, Image eastIcon) {
		_panel = new FlowPanel();
		initWidget(_panel);
		_panel.setStyleName(XStyle.tableCell.name());

		// this.title = titleText;

		// if(titleText == null){
		// throw new RuntimeException("titleText == null");
		// }
		// assert (null == titleText) : "titleText can not be null.";

		// Content
		// Label _contentView = new Label(titleText);
		titleLabel.setStyleName(XStyle.tableCellTitle.name());
		((FlowPanel) _panel).add(titleLabel);

		if (westIcon != null) {
			westIcon.addStyleName(XStyle.tableCellWestImage.name());
			int height = westIcon.getHeight();
			int width = westIcon.getWidth();
			westIcon.getElement().getStyle().setProperty("marginTop", "-" + (height / 2) + "px");
			titleLabel.getElement().getStyle().setProperty("marginLeft", "" + (width + 20) + "px");
			((FlowPanel) _panel).add(westIcon);
		}

		if (eastIcon != null) {
			((FlowPanel) _panel).add(eastIcon);
			eastIcon.addStyleName(XStyle.tableCellEastImage.name());
			int height = eastIcon.getHeight();
			eastIcon.getElement().getStyle().setProperty("marginTop", "-" + (height / 2) + "px");
		}

		// TODO toggle state
		if (!_isFromController) {
			addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					_panel.addStyleName(XStyle.selected.name());
					new Timer() {
						public void run() {
							removeStyleName(XStyle.selected.name());
						}
					}.schedule(300);
				}
			});
		}
	}

	// @Override
	public CellData getData() {
		return data;
	}

}
