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
package next.i.mobile;

import com.google.gwt.user.client.Event;

public class SwipeEvent {

	public enum Type {
		VerticalTopBottom, 
		VerticalBottomTop, 
		HorizontalLeftRight, 
		HorizontalRightLeft
	};

	private double _speed;
	private Event _nativeEvent;
	private boolean _stopPropagation = false;
	private Type _type;

	public SwipeEvent(Event nativeEvent, Type type, double speed) {
		_nativeEvent = nativeEvent;
		_type = type;
		_speed = speed;
	}

	public Type getType() {
		return _type;
	}

	public void stopPropagation() {
		_nativeEvent.stopPropagation();
		_stopPropagation = true;
	}

	public boolean getStopPropagation() {
		return _stopPropagation;
	}

	public double getSpeed() {
		return _speed;
	}

	public Event getNativeEvent() {
		return _nativeEvent;
	}

	public void dispatch(SwipeEventsHandler handler) {
		if (_type == Type.VerticalTopBottom || _type == Type.VerticalBottomTop) {
			handler.onSwipeVertical(this);
		} else if (_type == Type.HorizontalLeftRight || _type == Type.HorizontalRightLeft) {
			handler.onSwipeHorizontal(this);
		}
	}
}
