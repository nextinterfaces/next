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

import com.google.gwt.user.client.ui.HTML;

/**
 * Implements a read-only text view. A label can contain an arbitrary amount of
 * text, but XLabel may shrink, wrap, or truncate the text, depending on the
 * size of the bounding rectangle and properties you set. You can control the
 * font, text color, alignment, highlighting, and shadowing of the text in the
 * label.
 * 
 * <p>
 * <img class='ai' src='../../../../resources/XLabel.png' />
 * </p>
 */
public class XLabel extends HTML {
	
	public static enum XLabelType {
		Header,
		Text,
	}
	

  /**
   * Creates an empty XLabel.
   *
   * @param html the new widget's HTML contents
   */
  public XLabel() {
    setStyleName(XStyle.xlabel.name() + " " + XLabelType.Text.name());
  }

  /**
   * Creates an XLabel widget with the specified HTML contents.
   *
   * @param html the new widget's HTML contents
   */
  public XLabel(String html) {
    this();
    setHTML(html);
  }

  /**
   * Creates an XLabel widget with the specified HTML contents.
   *
   * @param html the new widget's HTML contents
   */
  public XLabel(String html, XLabelType type) {
    super();
    setHTML(html);
    setStyleName(XStyle.xlabel.name() + " " + type.name());
  }
  
}
