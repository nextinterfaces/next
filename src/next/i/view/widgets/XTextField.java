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

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.layout.client.Layout.Alignment;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.TextBoxBase;

/**
 * Displays a rounded rectangle that can contain editable text. When a user taps
 * a text field, a keyboard appears; when a user taps Return in the keyboard,
 * the keyboard disappears and the text field can handle the input in an
 * application-specific way. XTextField supports overlay views to display
 * additional information, such as a bookmarks icon. XTextField also provides a
 * clear text control a user taps to erase the contents of the text field.
 *
 * 
 * <p>
 * <img class='ai' src='../../../../resources/XTextField.png' />
 * </p>
 */
public class XTextField extends Composite {

	public static enum XTextFieldType {
		TEXT, PASSWORD, NUMBER, EMAIL, TEL, URL, TEXTAREA
	}

	private String text;
	// placeholder property
	// font property
	// textColor property
	// textAlignment property
	// Sizing the Text Field’s Text
	private boolean adjustsFontSizeToFitWidth;
	private int minimumFontSize;
	// Managing the Editing Behavior
	// editing property
	// clearsOnBeginEditing property
	// Setting the View’s Background Appearance
	// borderStyle property
	// background property
	// disabledBackground property
	// Managing Overlay Views
	// clearButtonMode property
	// leftView property
	// leftViewMode property
	// rightView property
	// rightViewMode property

	private boolean isPassword;

	// – rightViewRectForBounds:

	private Label _label;
	private TextBoxBase _textBox;
	private LayoutPanel _panel;

	public XTextField(String label, XTextFieldType type) {
		_panel = new LayoutPanel();
		initWidget(_panel);

		setStyleName(XStyle.xtextfield.name());

		_label = new Label(label);
		if (type == XTextFieldType.TEXTAREA) {
			_textBox = new TextArea();

		} else {
			_textBox = new TextBox();
			if (type != null) {
				_textBox.getElement().setAttribute("type", type.name().toLowerCase());
			}
		}


		 _label.setStyleName("label");
		 _textBox.setStyleName("inputfield");

		_panel.add(_label);
		_panel.add(_textBox);

		_panel.setWidgetLeftWidth(_label, 0, Unit.PCT, 40, Unit.PCT);
		_panel.setWidgetRightWidth(_textBox, 0, Unit.PCT, 60, Unit.PCT);
		_panel.setWidgetVerticalPosition(_label, Alignment.STRETCH);
		
		_label.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				_textBox.setFocus(true);
			}
		});
	}

	public TextBoxBase getTextBox() {
		return _textBox;
	}

	public void setText(String text) {
		_textBox.setText(text);
	}

	// <div class="mw-row widget widget-input" id="widget1">
	// <_label contenteditable="true">Label:</_label>
	// <input type="text" class="mw-labeled" value="">
	// </div>

}
