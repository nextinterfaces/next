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

import next.i.util.Utils;
import next.i.view.XTab;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.ui.Image;

/**
 * Manages a single tab view holding tab title, tab image and tab inner
 * controller.
 * 
 * It also acts as a mediator between master XTabBarController and inner view
 * IController.
 * 
 * <p>
 * <img src='../../../resources/XTab.png'/>
 * </p>
 */
public class XTabController implements EventListener {

	private XTabBarController _parent;
	private XTab _tab;
	private boolean _suppressNextClick = false;
	private JavaScriptObject _clickListener;
	private IController _uiController;

	public XTabController(IController uiController) {
		TabController_(uiController);
	}

	public void set(String title, Image imageTab, Image imageTabSelected) {
		set_(title, imageTab, imageTabSelected);
	}

	public void setSelected(boolean selected) {
		_tab.setSelected(selected);
	}

	public XTab getTab() {
		return _tab;
	}

	public IController getIController() {
		return _uiController;
	}

	public void setIController(IController uiController) {
		setIController_(uiController);
	}

	@Override
	public void onBrowserEvent(Event e) {
		onBrowserEvent_(e);
	}

	void register(XTabBarController parent) {
		this._parent = parent;
	}

	protected void registerEvents() {
		registerEvents_();
	}

	protected void unregisterEvents() {
		unregisterEvents_();
	}

	/**
	 * private
	 */

	private void TabController_(IController uiController) {
		_tab = new XTab();
		this._uiController = uiController;
		registerEvents();
	}

	private void set_(String title, Image imageTab, Image imageTabSelected) {
		_tab.setTitle(title);
		_tab.setIcons(imageTab, imageTabSelected);
	}

	private void setIController_(IController uiController) {
		this._uiController = uiController;
		// May be removeFromParent works better
		_parent.getTabBar().getContent().clear();
		_parent.getTabBar().add(uiController.getNavigationView());
	}

	private void onBrowserEvent_(Event e) {
		String type = e.getType();
		if (type.equals("click")) {
			onClick_(e);
		}
	}

	private void registerEvents_() {
		if (_clickListener == null) {
			_clickListener = Utils.addEventListener(_tab.getElement(), "click", true, this);
		}
	}

	private void unregisterEvents_() {
		if (_clickListener != null) {
			Utils.removeEventListener(_tab.getElement(), "click", true, _clickListener);
			_clickListener = null;
		}
	}

	private void onClick_(Event e) {
		if (_suppressNextClick) {
			e.stopPropagation();
			_suppressNextClick = false;
			// Utils.Console("click suppressed");
		} else {
			_parent.onTabChange(this);
		}
	}

}
