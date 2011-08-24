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
package next.i.controller;

import next.i.view.IView;
import next.i.view.XHorizontalScrollView;

import com.google.gwt.user.client.ui.IsWidget;

/**
 * Manages a TableView, automatically creating an instance with the correct
 * dimensions and resizing mask, and acting as the table view's delegate and
 * data source.
 * 
 * </pre>
 */
public class XHorizontalScrollController extends XController {

	private XHorizontalScrollView _view;

	public IView getView() {
		if (_view == null) {
			_view = new XHorizontalScrollView();
		}
		return _view;
	}

	@Override
	public IsWidget getViewContent() {
		return null;
	}

}
