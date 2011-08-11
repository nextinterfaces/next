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
package next.i.view.incubator;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Sample TouchScrollPanel
 * 
 * @author peter.franza
 */
public class TouchScrollPanel extends ScrollPanel {

	private int _initialTouchX = -1;
	private int _initialTouchY = -1;
	private int _initialHorizontalOffset;
	private int _initialVerticalOffset;
	private boolean _moved = false;

	{
		attachTouch(getElement());
	}

	public TouchScrollPanel(VerticalPanel body) {
		super(body);
	}

	private native void attachTouch(JavaScriptObject ele) /*-{
		var ref = this;
		ele.ontouchstart = function(evt) {
			evt.preventDefault();
			ref.@next.i.view.incubator.TouchScrollPanel::setInitialTouch(II)(evt.touches[0].screenX, evt.touches[0].screenY);
		}
		ele.ontouchmove = function(evt) {
			evt.preventDefault();
			ref.@next.i.view.incubator.TouchScrollPanel::doScroll(II)(evt.touches[0].screenX, evt.touches[0].screenY);
		}
		ele.ontouchend = function(evt) {
			evt.preventDefault();
			ref.@next.i.view.incubator.TouchScrollPanel::setEndTouch(II)(evt.pageX, evt.pageY);
		}
	}-*/;

	private native void fireClick(int x, int y) /*-{
		var theTarget = $doc.elementFromPoint(x, y);
		if (theTarget.nodeType == 3)
			theTarget = theTarget.parentNode;

		var theEvent = $doc.createEvent('MouseEvents');
		theEvent.initEvent('click', true, true);
		theTarget.dispatchEvent(theEvent);
	}-*/;
	
	
	@SuppressWarnings("deprecation")
	private void setInitialTouch(int x, int y) {
		_initialVerticalOffset = getScrollPosition();
		_initialHorizontalOffset = getHorizontalScrollPosition();

		_initialTouchX = x;
		_initialTouchY = y;
		_moved = false;

	}

	@SuppressWarnings("deprecation")
	private void doScroll(int x, int y) {
		if (_initialTouchY != -1) {
			_moved = true;
			int vDelta = _initialTouchY - y;
			int hDelta = _initialTouchX - x;
			setScrollPosition(vDelta + _initialVerticalOffset);
			setHorizontalScrollPosition(hDelta + _initialHorizontalOffset);
		}
	}

	private void setEndTouch(int x, int y) {
		if (!_moved) {
			fireClick(x, y);
		}
		_initialTouchX = -1;
		_initialTouchY = -1;
	}
}