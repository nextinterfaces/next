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

import next.i.XStyle;
import next.i.util.Utils;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HTML;

/**
 * <p>
 * <img class='ai' src='../../../../resources/XCheckbox.png' />
 * </p>
 */
public class XCheckbox extends CheckBox {

	public XCheckbox() {
		this(null, null);
	}

	public XCheckbox(String name, String text) {
		super();
		if (Utils.isAndroid() && Utils.isWVGA()) {
			CheckBoxIndicator indicator = new CheckBoxIndicator();
			this.getElement().insertFirst(indicator.getElement());
		}
		if (name != null) {
			setName(name);
		}
		if (text != null) {
			setText(text);
		}
	}

	@Override
	public void setValue(Boolean value) {
		setValue(value, false);
	}

	@Override
	public void setValue(Boolean checked, boolean fireEvents) {
		super.setValue(checked, fireEvents);
		if (checked) {
			addStyleName(XStyle.selected.name());
		} else {
			removeStyleName(XStyle.selected.name());
		}
	}

	static class CheckBoxIndicator extends HTML {

		public CheckBoxIndicator() {
			super("<div><div></div></div><div></div><div></div><div></div>");
			setStyleName("CheckBoxIndicator");
		}
	}

}
