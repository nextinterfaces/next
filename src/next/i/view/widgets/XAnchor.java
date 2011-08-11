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

import com.google.gwt.user.client.ui.Anchor;

/**
 * <p>
 * <img class='ai' src='../../../../resources/XAnchor.png' />
 * </p>
 */
public class XAnchor extends Anchor {

	/**
	 * Creates an empty anchor.
	 */
	public XAnchor() {
		super();
		setStyleName(XStyle.xanchor.name());
	}

	/**
	 * Creates an anchor with its text and href (target URL) specified.
	 */
	public XAnchor(String html, String href) {
		super(html, href);
		setStyleName(XStyle.xanchor.name());
	}
	/**
	 * Creates an anchor with its text and href (target URL) specified.
	 */
	public XAnchor(String html, String href, String target) {
		super(html, href, target);
		setStyleName(XStyle.xanchor.name());
	}

}
