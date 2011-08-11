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
package next.i.view;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * This is a model class holding XTableCell layout widgets.
 */
public class CellData {

	private Widget[] westWidgets;
	private Widget[] eastWidgets;
	private Widget[] textWidgets;

	public CellData() {
	}

	public CellData(String title) {
		this(null, title, null);
	}

	public CellData(Widget westImage, String title, Widget eastImage) {
		setWestWidgets(westImage);
		setTextWidgets(new Label(title));
		setEastWidgets(eastImage);
	}

	public CellData(Widget westWidget, Widget textWidget, Widget eastWidget) {
		setWestWidgets(westWidget);
		setTextWidgets(textWidget);
		setEastWidgets(eastWidget);
	}

	public CellData(Widget[] westWidgets, Widget[] textWidgets, Widget[] eastWidgets) {
		this.westWidgets = westWidgets;
		this.eastWidgets = eastWidgets;
		this.textWidgets = textWidgets;
	}

	public void setWestWidgets(Widget... widgets) {
		if (widgets != null) {
			westWidgets = widgets;
		}
	}

	public void setEastWidgets(Widget... widgets) {
		if (widgets != null) {
			eastWidgets = widgets;
		}
	}

	public void setTextWidgets(Widget... widgets) {
		if (widgets != null) {
			textWidgets = widgets;
		}
	}

	public Widget[] getWestWidgets() {
		return westWidgets;
	}

	public Widget[] getEastWidgets() {
		return eastWidgets;
	}

	public Widget[] getTextWidgets() {
		return textWidgets;
	}

}
