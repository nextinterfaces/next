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

import next.i.XStyle;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;

/**
 * Provides a picker user-interface (a picker wheel, drop down menu or a
 * selection list depending on the OS) which has a series of items (rows) at
 * indexed locations on the widget.
 * 
 * <p>
 * <img class='ai' src='../../../../resources/XPicker.png' />
 * </p>
 */
public class XPicker extends Composite implements FocusHandler, BlurHandler, ChangeHandler,
		HasValueChangeHandlers<String> {

	private ListBox _listBox = new ListBox();
	private FlowPanel _panel = new FlowPanel();
	private HTML jqPicker;

	public XPicker() {
		initWidget(_panel);

		jqPicker = new HTML("<div class='body'><div>&nbsp;</div><div class='icon'></div></div>");
		FlowPanel xbtn = new FlowPanel();
		xbtn.setStyleName("xbtn");
		_panel.add(xbtn);

		xbtn.add(jqPicker);
		xbtn.add(_listBox);

		sinkEvents(Event.ONCLICK | Event.ONTOUCHCANCEL | Event.ONTOUCHEND | Event.ONTOUCHMOVE | Event.ONTOUCHSTART);

		setStyleName(XStyle.xpicker.name());
		_listBox.addFocusHandler(this);
		_listBox.addBlurHandler(this);
		_listBox.addChangeHandler(this);
	}

	@Override
	public void onBrowserEvent(Event e) {
		e.stopPropagation();
		super.onBrowserEvent(e);
	}

	@Override
	protected void onUnload() {
		removeStyleName(XStyle.selected.name());
		super.onUnload();
	}

	@Override
	public void onFocus(FocusEvent event) {
		addStyleName(XStyle.selected.name());
	}

	@Override
	public void onBlur(BlurEvent event) {
		removeStyleName(XStyle.selected.name());
	}

	@Override
	public void onChange(ChangeEvent event) {
		int index = _listBox.getSelectedIndex();
		String value = _listBox.getValue(index);
		String item = _listBox.getItemText(index);
		setTextItem_(item);
		ValueChangeEvent.fire(this, value);
	}

	private void setTextItem_(String item) {
		Element el = (Element) jqPicker.getElement().getFirstChild().getFirstChild();
		el.setInnerHTML(item);
	}

	public void add(String item, boolean isSelected) {
		add(item, item, isSelected);
	}

	public void add(String item) {
		add(item, false);
	}

	public void add(String item, String value) {
		add(item, value, false);
	}

	public void add(String item, String value, boolean isSelected) {
		_listBox.addItem(item, value);
		if (isSelected) {
			setTextItem_(item);
			getListBox().setSelectedIndex(_listBox.getItemCount()-1);
		}
	}

	// @Override
	// public void add(Widget w) {
	// assert w.getClass() == DropDownItem.class :
	// "Can only contain DropDownItem in DropDownList.";
	// DropDownItem item = (DropDownItem) w;
	// _listBox.addItem(item.getText(), item.getValue());
	// }

	public String getSelectedText() {
		int index = _listBox.getSelectedIndex();
		if (index >= 0) {
			return _listBox.getItemText(index);
		} else {
			return null;
		}
	}

	public String getSelectedValue() {
		int index = _listBox.getSelectedIndex();
		if (index >= 0) {
			return _listBox.getValue(index);
		} else {
			return null;
		}
	}

	public ListBox getListBox() {
		return _listBox;
	}

	@Override
	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<String> handler) {
		return this.addHandler(handler, ValueChangeEvent.getType());
	}

}