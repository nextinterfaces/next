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

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.TextBox;

/**
 * InputRangeSlider displays a horizontal bar, called a track, that represents a range
 * of values. The current value is shown by the position of an indicator, or
 * thumb. A user selects a value by sliding the thumb along the track. You can
 * customize the appearance of both the track and the thumb.
 */
public class InputRangeSlider extends TextBox {

	public InputRangeSlider() {
		this(false);
	}

	public InputRangeSlider(boolean isVertical) {
		Element el = getElement();
		el.setAttribute("type", "range");
		if (isVertical) {
			el.getStyle().setProperty("webkitAppearance", "slider-vertical");
		}
	}

}
