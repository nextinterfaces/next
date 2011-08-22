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
package next.i.view.widgets;

import java.util.ArrayList;

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
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * <p>
 * <img class='ai' src='../../../../resources/XCheckboxGroup.png' />
 * </p>
 */
public class XCheckboxGroup extends MPanelBase implements HasWidgets, ClickHandler, DragEventsHandler,
		ValueChangeHandler<Boolean> {

	// private int _pressed = -1;

	public XCheckboxGroup() {
		this(false);
	}

	public XCheckboxGroup(boolean isVertical) {
		super();
		sinkEvents(Event.ONCLICK | Event.ONTOUCHCANCEL | Event.ONTOUCHEND | Event.ONTOUCHMOVE | Event.ONTOUCHSTART);

		addDomHandler(this, ClickEvent.getType());
		setStyleName(XStyle.xcheckboxGroup.name());
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

	@Override
	public void onClick(ClickEvent e) {
		final EventTarget target = e.getNativeEvent().getEventTarget();
		String targetTagName = ((Element) target.cast()).getTagName().toUpperCase();
		//XLog.warn("onClick target=" + target + " targetTagName=" + targetTagName);
		if (targetTagName.equals("LABEL")) {
			return; // if check box label is click, another (simulated) click event
			// with
			// check box INPUT as target will fire after this one. So this click event
			// can be safely ignored.
		}
		Element div = Element.as(target);
		while (!div.getNodeName().toUpperCase().equals("SPAN") || div.getParentElement() != this.getElement()) {
			div = div.getParentElement();
			if (div == null) {
				// XLog.info("XCheckboxGroup onClick: span not found");
				return;
			}
		}
		final int index = DOM.getChildIndex(this.getElement(), (com.google.gwt.user.client.Element) div);
		CheckBox checkbox = (CheckBox) _panel.getWidget(index);
		// XLog.info("onClick " + checkbox.getValue());
		if (targetTagName.equals("INPUT")) {
			// XLog.info("onClick value changed");
			checkbox.setValue(checkbox.getValue()); // if target is check box INPUT,
			// check box value is
			// already changed when click event is fired.
			// just need to set its current value back to the check box
			// to update style.
		} else {
			checkbox.setValue(!checkbox.getValue());
		}

		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				SelectionChangedEvent selectionChangedEvent = new SelectionChangedEvent(index, target);
				fireEvent(selectionChangedEvent);
			}
		});
	}

	public ArrayList<Integer> getCheckedIndices() {
		int count = _panel.getWidgetCount();
		ArrayList<Integer> checkedList = new ArrayList<Integer>(count);
		for (int i = 0; i < count; i++) {
			XCheckbox c = (XCheckbox) _panel.getWidget(i);
			if (c.getValue()) {
				checkedList.add(i);
			}
		}
		return checkedList;
	}

	public ArrayList<XCheckbox> getCheckedWidgets() {
		int count = _panel.getWidgetCount();
		ArrayList<XCheckbox> checkedList = new ArrayList<XCheckbox>(count);
		for (int i = 0; i < count; i++) {
			XCheckbox c = (XCheckbox) _panel.getWidget(i);
			if (c.getValue()) {
				checkedList.add(c);
			}
		}
		return checkedList;
	}

	public void add(XCheckbox... checkBoxs) {
		if (checkBoxs != null) {
			for (XCheckbox c : checkBoxs) {
				// add(c);
				_panel.add(c);
				c.addValueChangeHandler(this);
			}
		}
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

}
