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
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;

/**
 * XProgress depicts the progress of a task over time. The current progress is
 * represented by a floating-point value between 0.0 and 1.0, inclusive, where
 * 1.0 indicates the completion of the task. Values less than 0.0 and greater
 * than 1.0 are pinned to those limits.
 * 
 * <p>
 * <img class='ai' src='../../../../resources/XProgress.png' />
 * </p>
 */
public class XProgress extends Composite {

	private double _value = 0;
	private FlowPanel _panel = new FlowPanel();
	private HTML _label = new HTML("0%");
	private HTML _progress = new HTML();
	private Integer transistionTiming = null;
	private State state = State.NOT_RUNNING;
	private Style progressStyle = null;

	private enum State {
		NOT_RUNNING, RUNNING, COMPLETED
	}

	public XProgress() {
		initWidget(_panel);
		setStyleName(XStyle.xprogress.name());

		_panel.add(_label);
		_panel.add(_progress);

		_label.setStyleName("label");
		_progress.setStyleName("bar");

		_progress.setHTML("<div class=right></div><div class=left></div>");
		reset();
	}

	public XProgress(int initialValue) {
		this();
		setValue(initialValue);
	}

	public void reset() {
		// FIXME doesn't remove -webkit-transition-property: width;
		// causing progress to animate backwards on restart
		css_().setProperty("display", "none");
		css_().clearProperty("webkitTransitionProperty");
		css_().setProperty("webkitTransitionProperty", "height");
		setValue(0);
		css_().setProperty("webkitTransitionProperty", "width");
		state = State.NOT_RUNNING;
	}

	public void setValue(double v) {
		if (_value != v) {

			if (state != State.RUNNING) {
				css_().setProperty("display", "inline");
				state = State.RUNNING;
			}

			if (v < 0.0) {
				_value = 0;

			} else if (v >= 1.0) {
				_value = 1;

			} else {
				_value = v;
			}
			updatePosition_();
		}
	}

	public void setTransistionTiming(Integer transistionTiming) {
		this.transistionTiming = transistionTiming;
	}

	public double getValue() {
		return _value;
	}

	public int getPercentValue() {
		return (int) Math.round(_value * 100.0);
	}

	public void setLabelVisible(boolean display) {
		if (display) {
			_label.setVisible(true);
		} else {
			_label.setVisible(false);
		}
	}

	public boolean isRunning() {
		return state == State.RUNNING;
	}

	private void updatePosition_() {
		css_().setWidth((_value * 100), Unit.PCT);
		final int pctVal = getPercentValue();

		if (pctVal >= 100) {
			state = State.COMPLETED;
//			XLog.warn("value=" + pctVal + " COMPLETED");
		}

		if (transistionTiming != null) {
			css_().setProperty("webkitTransitionDuration", transistionTiming + "ms");

			new Timer() {
				public void run() {
					_label.setHTML(pctVal + "%");
				}
			}.schedule(transistionTiming);
		} else {

			new Timer() {
				public void run() {
					_label.setHTML(pctVal + "%");
				}
			}.schedule(500/* defined at css */);
		}

	}

	private Style css_() {
		if (progressStyle != null) {
			return progressStyle;
		}
		progressStyle = ((Element) _progress.getElement().getChild(1)).getStyle();
		return progressStyle;
	}

}
