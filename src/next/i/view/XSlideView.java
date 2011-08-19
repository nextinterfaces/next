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
package next.i.view;

import java.util.ArrayList;
import java.util.Iterator;

import next.i.XLog;
import next.i.mobile.DragController;
import next.i.mobile.SwipeEvent;
import next.i.mobile.SwipeEventsHandler;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * This view class accepts finger drag actions and can be scrolled vertically.
 * 
 * 
 * <p>
 * <img class='ai' src='../../../resources/XSlideView.png' />
 * </p>
 */
public class XSlideView extends MWidgetBase implements HasWidgets, SwipeEventsHandler {

	protected FlowPanel _panel = new FlowPanel();
	protected int _count = 0;
	protected int _current = 0;
	// protected SlideProvider _slideProvider = null;
	protected ArrayList<Widget> _slides = new ArrayList<Widget>();

	public XSlideView() {
		initWidget(_panel);
		setStyleName("SlidePanel");
	}

	public void setSlideCount(int count) {
		_count = count;
	}

	public int getSlideCount() {
		return _count > 0 ? _count : _slides.size();
	}

	// public void setSlideProvider(SlideProvider provider) {
	// _slideProvider = provider;
	// }
	//
	// public SlideProvider getSlideProvider() {
	// return _slideProvider;
	// }

	@Override
	protected void onInitialLoad() {
		super.onInitialLoad();
		_current = 0;
		Slide slide = getSlide(_current);
		if (slide != null) {
			_panel.add(slide);
		}
	}

	public Slide getSlide(int index) {
		Slide slide = null;
		// if (_slideProvider != null) {
		// slide = _slideProvider.loadSlide(index);
		// }
		if (slide == null && index < _slides.size()) {
			slide = (Slide) _slides.get(index);
		}
		return slide;
	}

	@Override
	public void onLoad() {
		super.onLoad();
		DragController.get().addSwipeEventsHandler(this);
	}

	@Override
	protected void onUnload() {
		DragController.get().removeSwipeEventHandler(this);
		super.onUnload();
	}

	@Override
	public void onSwipeHorizontal(SwipeEvent e) {
		XLog.info("onSwipeHorizontal");
		if (e.getSpeed() < 0) { // swipe to next
			next();
		} else { // swipe to previous
			previous();
		}
	}

	@Override
	public void onSwipeVertical(SwipeEvent e) {
		XLog.info("onSwipeVertical");
	}

	public void next() {
		XLog.info("next");
		if (_current >= getSlideCount() - 1) {
			return;
		}
		_current++;
		Slide to = getSlide(_current);
		Slide from = (Slide) _panel.getWidget(0);

		// ////////
		// TODO play transition animations
		// Element elFrom = from.getElement();
		// Element elTo = from.getElement();
		_panel.remove(from);
		_panel.add(to);
		to.onTransitionEnd();
		// Transition transition = Transition.SLIDE;
		// transition.start(from, to, _panel, false);
	}

	public void previous() {
		XLog.info("previous");
		if (_current <= 0) {
			return;
		}
		_current--;
		Slide to = getSlide(_current);
		Slide from = (Slide) _panel.getWidget(0);

		// ////////
		// TODO play transition animations
		_panel.remove(from);
		_panel.add(to);
		to.onTransitionEnd();
		// Transition transition = Transition.SLIDE;
		// transition.start(from, to, _panel, true);
	}

	// ///////////////
	// // animations here
	// private void prepare() {
	// _from.addStyleName(_transitionStyleName + " " + CSS.T.out());
	// _to.addStyleName(_transitionStyleName + " " + CSS.T.in());
	// if (_reverse) {
	// _from.addStyleName(CSS.T.reverse());
	// _to.addStyleName(CSS.T.reverse());
	// }
	// _parent.add(_to);
	// }
	//
	// private void start() {
	// registerTransitionEndEvent();
	// new Timer() {
	// @Override
	// public void run() {
	// _from.addStyleName(CSS.T.start());
	// _to.addStyleName(CSS.T.start());
	// }
	// }.schedule(20); //xxms instead of 1ms, to give iOS/Android enough time to
	// set the starting state.
	// }
	// protected void registerTransitionEndEvent(boolean isReverse) {
	// if (!isReverse) {
	// Utils.addEventListenerOnce(_to.getElement(), "webkitTransitionEnd", false,
	// this);
	// }
	// else {
	// Utils.addEventListenerOnce(_from.getElement(), "webkitTransitionEnd",
	// false, this);
	// }
	// }
	// ///////////////

	@Override
	public void onTransitionEnd() {
		super.onTransitionEnd();
		_panel.remove(0);
	}

	public int getCurrentSlideIndex() {
		return _current;
	}

	@Override
	public void add(Widget w) {
		assert (w instanceof Slide) : "Can only add Slide widgets to SlidePanel.";
		_slides.add(w);
	}

	@Override
	public void clear() {
		_slides.clear();

	}

	@Override
	public Iterator<Widget> iterator() {
		return _slides.iterator();
	}

	@Override
	public boolean remove(Widget w) {
		return _slides.remove(w);
	}

	/********************* interface SlideProvider *******************/

	// public interface SlideProvider {
	// Slide loadSlide(int index);
	// }
}
