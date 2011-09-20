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
import next.i.mobile.DragController;
import next.i.mobile.DragEvent;
import next.i.mobile.DragEventsHandler;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;

/**
 * 
 * <p>
 * <img class='ai' src='../../../../resources/XSlider.png' />
 * </p>
 */
public class XSlider extends Composite implements DragEventsHandler, HasValueChangeHandlers<Integer> {

	private int _value = 0;
	private FlowPanel _panel = new FlowPanel();
	private HTML _label = new HTML("0%");
	private HTML _slider = new HTML();

	public XSlider() {
		initWidget(_panel);
		setStyleName(XStyle.xslider.name());

		sinkEvents(Event.ONCLICK | Event.ONTOUCHCANCEL | Event.ONTOUCHEND | Event.ONTOUCHMOVE | Event.ONTOUCHSTART);

		_panel.add(_label);
		_panel.add(_slider);

		_label.setStyleName("label");
		_slider.setStyleName("bar");
		_slider.setHTML("<div class=right></div><div class=left></div><div class=thumb></div>");
	}

	public XSlider(int initialValue) {
		this();
		setValue(initialValue);
	}

	@Override
	public void onBrowserEvent(Event e) {
		e.stopPropagation();
		super.onBrowserEvent(e);
	}

	@Override
	public void onLoad() {
		super.onLoad();
		DragController.get().addDragEventsHandler(this);
	}

	@Override
	public void onUnload() {
		DragController.get().removeDragEventsHandler(this);
	}

	@Override
	public void onDragStart(DragEvent e) {
		DragController.get().captureDragEvents(this);
		int value = computeNewValue_(e);
		setValue(value);
	}

	@Override
	public void onDragMove(DragEvent e) {
		e.stopPropagation();
		int value = computeNewValue_(e);
		setValue(value);
	}

	@Override
	public void onDragEnd(DragEvent e) {
		DragController.get().releaseDragCapture(this);
	}

	public void setValue(int value) {
		if (_value != value) {
			_value = value;
			updateSliderPosition_();
			ValueChangeEvent.fire(this, _value);
		}
	}

	public int getValue() {
		return _value;
	}

	@Override
	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<Integer> handler) {
		return this.addHandler(handler, ValueChangeEvent.getType());
	}

	public void setLabelVisible(boolean display) {
		if (display) {
			_label.setVisible(true);
		} else {
			_label.setVisible(false);
		}
	}

	private int computeNewValue_(DragEvent e) {
		Element ele = _slider.getElement();
		int offset = (int) e.X - ele.getAbsoluteLeft();
		int width = ele.getClientWidth();
		int value = offset * 100 / width;
		if (value > 100) {
			value = 100;
		} else if (value < 0) {
			value = 0;
		}
		return value;
	}

	private void updateSliderPosition_() {
		_label.setHTML(_value + "%");
		Element slider = getSliderElement_();
		slider.getStyle().setWidth(_value, Unit.PCT);
	}

	private Element getSliderElement_() {
		return (Element) _slider.getElement().getChild(1);
	}

	@Override
	public void onDragMoveHorizontal(DragEvent e) {
		// Not implemented
	}

	@Override
	public void onDragMoveVertical(DragEvent e) {
		// Not implemented
	}
}
