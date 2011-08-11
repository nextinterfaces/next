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

import java.util.ArrayList;

import com.google.gwt.user.client.ui.PopupPanel;

/**
 * Used by XController to lock/release screen animations.
 */
class Anime {

	private static boolean _isActive = false;
	private static Overlay _popup;
	private static ArrayList<Overlay> _list = new ArrayList<Overlay>();

	static boolean isActive() {
		return _isActive;
	}

	static void lock() {
		Anime._isActive = true;
		_popup = new Overlay();
		_popup.show();
		_list.add(_popup);
	}

	static void release() {
		Anime._isActive = false;
		for (Overlay popup : _list) {
			popup.hide();
		}
		_popup.clear();
		// popup.hide();
		// Window.alert("Animation released");
	}

	static class Overlay extends PopupPanel {
		Overlay() {
			super(false, true);
			addStyleName("animeLock");
		}
	}

}
