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

import next.i.controller.IController;
import next.i.controller.XNavigationController;
import next.i.view.IView;
import next.i.view.XNavigationBar;
import next.i.view.XNavigationView;
import next.i.view.XScrollView;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.PopupPanel;

public class SlideController implements IController {

	enum BLAH {
		slide, slideup, start, SC
	}

	private XNavigationBar _navigationBar;
	private XScrollView _view;
	private String _title;
	private int _id;
	private PopupPanel _popup;

	private XNavigationController navigationController;

	public SlideController() {
		_view = new XScrollView();
		_navigationBar = new XNavigationBar();

//		this._id = ControllerID.get();
	}

	public SlideController(IsWidget isWidget) {
		this();
//		_view.setContent(isWidget);
	}

	void register(XNavigationController navigationController) {
		this.navigationController = navigationController;
	}

	public IView getView() {
		return _view;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		this._title = title;
		_navigationBar.setTitle(title);
	}

	public XNavigationBar getNavigationBar() {
		return _navigationBar;
	}

	/**
	 * This method is invoked when on hide completion.
	 */
	public void onHideComplete() {
		// will be implemented by children
	}

	public void show() {
		final XNavigationView navigation = new XNavigationView();
//		navigation.getNavigationContent().addWidget(getView());
//		navigation.getNavigationUIBar().addWidget(getNavigationBar());
		navigation.setTitle(getTitle());

		this._popup = new PopupPanel();
		_popup.setWidget(navigation);
		_popup.getElement().getStyle().setWidth(100, Unit.PCT);
		_popup.getElement().getStyle().setHeight(100, Unit.PCT);
//		Utils.setStyle(_popup, BLAH.slideup, BLAH.SC, BLAH.start);
		_popup.show();

		new Timer() {
			public void run() {
//				Utils.removeStyle(_popup, BLAH.start);
			}
		}.schedule(20);
	}

	public void hide() {
		if (_popup == null) {
			assert false : "invoking hide() before show()";
		}
		_popup.addStyleName(BLAH.start.name());
	}

	public XNavigationController getNavigationController() {
		return navigationController;
	}

	@Override
	public String toString() {
		return _title + "-" + hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (obj instanceof SlideController) {
			SlideController objSC = (SlideController) obj;
			return objSC.hashCode() == this.hashCode();

		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return _id;
	}

	void _onHideComplete() {
		onHideComplete();
	}

	public IView getNavigationView() {
		// TODO Auto-generated method stub
		return null;
	}

}
