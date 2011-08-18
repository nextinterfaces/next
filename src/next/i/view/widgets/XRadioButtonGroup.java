/** This is a modified version of gwtmobile-ui class.
 * 
 * License bellow:
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

package next.i.view.widgets;

import next.i.XStyle;
import next.i.mobile.DragController;
import next.i.mobile.DragEvent;
import next.i.mobile.DragEventsHandler;
import next.i.mobile.SelectionChangedEvent;
import next.i.mobile.SelectionChangedHandler;
import next.i.view.MPanelBase;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * 
 * 
 * <p>
 * <img class='ai' src='../../../../resources/XRadioButtonGroup.png' />
 * </p>
 */
public class XRadioButtonGroup extends MPanelBase implements HasWidgets, ClickHandler, DragEventsHandler,
		ValueChangeHandler<Boolean> {

	// private int _pressed = -1;
	private String _name = null;

	public XRadioButtonGroup() {
		this(false);
	}

	public XRadioButtonGroup(boolean isVertical) {
		super();
		sinkEvents(Event.ONCLICK | Event.ONTOUCHCANCEL | Event.ONTOUCHEND | Event.ONTOUCHMOVE | Event.ONTOUCHSTART);

		addDomHandler(this, ClickEvent.getType());
		setStyleName(XStyle.xradioButtonGroup.name());
		setVertical(isVertical);
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
	protected void onUnload() {
		super.onUnload();
		DragController.get().removeDragEventsHandler(this);
	}

	public HandlerRegistration addSelectionChangedHandler(SelectionChangedHandler handler) {
		return this.addHandler(handler, SelectionChangedEvent.TYPE);
	}

	public void setVertical(boolean vertical) {
		if (vertical) {
			addStyleName(XStyle.vertical.name());
			removeStyleName(XStyle.horizontal.name());
		} else {
			addStyleName(XStyle.horizontal.name());
			removeStyleName(XStyle.vertical.name());
		}
	}

	@Override
	public void onValueChange(ValueChangeEvent<Boolean> event) {
		// FIXME ???
		// XLog.info("onValueChange " + event.getValue() + " " +
		// event.getSource().getClass());
	}

	@Override
	public void onDragStart(DragEvent e) {
		// _pressed = Utils.getTargetItemIndex(getElement(),
		// e.getNativeEvent().getEventTarget());
		// if (_pressed >= 0) {
		// Widget item = getWidget(_pressed);
		// item.addStyleName("Pressed");
		// }
	}

	@Override
	public void onDragMove(DragEvent e) {
		// if (_pressed >= 0) {
		// Widget item = getWidget(_pressed);
		// item.removeStyleName("Pressed");
		// _pressed = -1;
		// }
	}

	@Override
	public void onDragEnd(DragEvent e) {
		// onDragMove(e);
	}

	@Override
	public void onClick(ClickEvent e) {
		final EventTarget target = e.getNativeEvent().getEventTarget();
		String targetTagName = ((Element) target.cast()).getTagName().toUpperCase();

		if (targetTagName.equals("LABEL")) {
			return; // if check box label is click, another (simulated) click event
							// with
			// check box INPUT as target will fire after this one. So this click event
			// can be safely ignored.
		}

		// XLog.warn("2 target=" + target + " targetTagName=" + targetTagName);

		Element div = Element.as(target);
		while (!div.getNodeName().toUpperCase().equals("SPAN") || div.getParentElement() != this.getElement()) {
			div = div.getParentElement();
			if (div == null) {
				// XLog.info("XCheckboxGroup onClick: span not found");
				return;
			}
		}

		for (int i = 0; i < _panel.getWidgetCount(); i++) {
			XRadioButton radio = (XRadioButton) getWidget(i);
			radio.setValue(false, false);
		}

		final int index = DOM.getChildIndex(this.getElement(), (com.google.gwt.user.client.Element) div);
		// XLog.warn("target=" + target + " div=" + div + " inx=" + index);

		XRadioButton checkbox = (XRadioButton) _panel.getWidget(index);
		checkbox.setValue(true);

		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				SelectionChangedEvent selectionChangedEvent = new SelectionChangedEvent(index, target);
				fireEvent(selectionChangedEvent);
			}
		});
	}

	public int getCheckedIndex() {
		for (int i = 0; i < _panel.getWidgetCount(); i++) {
			XRadioButton radio = (XRadioButton) _panel.getWidget(i);
			if (radio.getValue()) {
				return i;
			}
		}
		return -1;
	}

	public XRadioButton getCheckedWidget() {
		int i = getCheckedIndex();
		if (i > -1) {
			return (XRadioButton) getWidget(i);
		}
		return null;
	}

	public void add(XRadioButton... radioButtons) {
		if (radioButtons != null) {
			for (XRadioButton radio : radioButtons) {
				_panel.add(radio);
				radio.addValueChangeHandler(this);
			}
		}
	}

	public void setName(String name) {
		_name = name;
		for (int i = 0; i < _panel.getWidgetCount(); i++) {
			XRadioButton radio = (XRadioButton) _panel.getWidget(i);
			radio.setName(_name);
		}
	}

	public String getName() {
		return _name;
	}

}
