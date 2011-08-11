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
import next.i.util.Utils;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.RadioButton;

/**
 * 
 * 
 * <p>
 * <img class='ai' src='../../../../resources/XRadioButton.png' />
 * </p>
 */
public class XRadioButton extends RadioButton {

	public XRadioButton() {
		this(null, null);
	}

	public XRadioButton(String name, String text) {
		super(null);
		if (Utils.isAndroid() && Utils.isWVGA()) {
			setStyleName(XStyle.xradioButton.name());
			DivElement div = Document.get().createDivElement();
			this.getElement().insertFirst(div);
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
		setValue(value, true);
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

}
