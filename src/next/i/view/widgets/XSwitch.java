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

import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;

/**
 * Displays an element that shows the user the boolean state of a given value.
 * By tapping the control, the state can be toggled.
 * 
 * <p>
 * <img class='ai' src='../../../../resources/XSwitch.png' />
 * </p>
 */
public class XSwitch extends Composite implements DragEventsHandler, HasValueChangeHandlers<Boolean> {

	private HTML _html = new HTML();
	private boolean _switched;

	public XSwitch(boolean isOn) {

		initWidget(_html);
		_html.setStyleName(XStyle.xswitch.name());

		sinkEvents(Event.ONCLICK | Event.ONTOUCHCANCEL | Event.ONTOUCHEND | Event.ONTOUCHMOVE | Event.ONTOUCHSTART);

		_switched = isOn;
		if (isOn) {
			_html.setStyleName(XStyle.xswitch.name());
		} else {
			_html.setStyleName(XStyle.xswitch.name() + " " + "off");
		}
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
	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<Boolean> handler) {
		return this.addHandler(handler, ValueChangeEvent.getType());
	}

	@Override
	public void onDragStart(DragEvent e) {
		switch_();
		DragController.get().captureDragEvents(this);
	}

	@Override
	public void onDragMove(DragEvent e) {
		e.stopPropagation();
	}

	@Override
	public void onDragEnd(DragEvent e) {
		DragController.get().releaseCapture(this);
	}

	private void switch_() {
		if (_switched) {
			_html.addStyleName("off");
		} else {
			_html.removeStyleName("off");
		}
		_switched = !_switched;
		ValueChangeEvent.fire(XSwitch.this, _switched);
	}
}
