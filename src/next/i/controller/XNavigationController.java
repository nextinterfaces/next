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


import java.util.List;
import java.util.Stack;

import next.i.view.IView;
import next.i.view.XNavigationBar;
import next.i.view.XNavigationView;


import com.google.gwt.user.client.Command;

/**
 * This class manages a stack of XControllers, each of which holds
 * information about a view, such as its title and the navigation item
 * associated with the view. When view controllers are pushed onto and popped
 * off the stack, the navigation controller updates the navigation bar and view
 * appropriately.
 * 
 * <p>
 * <img class='ai' src='
 * 'http://next.com/wp-content/uploads/2011/05/navigationController.png' />
 * </p>
 * 
 * <h3>Example:</h3>
 * 
 * <pre>
 *  class MyController extends XTableController {
 *  	public MyController() {
 *  
 *  		setTitle("Slide Example");
 *  
 *  		getNavigationBar().setRightTitle("Source");
 *  		getNavigationBar().setLeftTitle("Back", Type.BACK_BUTTON);
 *  
 *  		TableDataSource tableDS = new TableDataSource();
 *   		tableDS.add(&quot;0 list item&quot;, &quot;1 list item&quot;);
 *  
 *  		initDataSource(tableDS);
 *  	}
 *  }
 *  ..
 * new NavigationController(new MyController());
 * </pre>
 */
public class XNavigationController implements IController {

	private XController _rootController;
	private XController _currentController;
	private Stack<XController> _stack;

	// private TabBarController tabBarController;

	public XNavigationController(XController rootController) {
		NavigationController_(rootController);
	}

	public XController getTopController() {
		return _stack.peek();
	}

	public XController getVisibleController() {
		return _currentController;
	}

	public List<XController> getControllers() {
		return _stack;
	}

	public void pushController(final XController newController, boolean animated) {
		pushController_(newController, animated);
	}

	public XController popController(boolean animated) {
		return popController_(animated);
	}

	public XController popToRootController(boolean animated) {
		return popToRootController_(animated);
	}

	public XController popToController(XController controller, boolean animated) {
		return popToController_(controller, animated);
	}

	public XNavigationBar getNavigationBar() {
		return _currentController.getNavigationBar();
	}

	public XNavigationView getNavigationView() {
		return _currentController.getNavigationView();
	}

	public IView getView() {
		return _currentController.getNavigationView();
	}

	/**
	 * private
	 */

	private XController popToRootController_(boolean animated) {
		if (_stack.empty()) {
			return _rootController;

		} else {
			_stack.clear();
			return popController_(_currentController, _rootController, animated);
		}
	}

	private XController popToController_(XController controller, boolean animated) {
		while (!_stack.empty()) {
			final XController sc = _stack.pop();
			if (controller.equals(sc)) {
				return popController_(_currentController, sc, animated);
			}
		}
		return null;
	}

	private XController popController_(boolean animated) {
		if (_stack.empty()) {
			return null;

		} else {
			XController newController = _stack.pop();
			return popController_(_currentController, newController, animated);
		}
	}

	private void pushController_(final XController newController, boolean animated) {

		newController.register(this);

		boolean contains = _stack.contains(newController) || _currentController.equals(newController);
		if (!contains) {
			newController.attach();

			Animator a0 = new Animator(Transform.SLIDE_CW, _currentController);
			Animator a1 = new Animator(Transform.SLIDE_EC, newController);
			XController.animate(new Command() {
				public void execute() {
					_stack.push(_currentController);
					_currentController.doOnHideComplete();
					newController.doOnHideComplete();

					_currentController.getNavigationView().getElement().getStyle().setZIndex(-1);
					newController.getNavigationView().getElement().getStyle().setZIndex(1);

					_currentController = newController;
				}
			}, 300, Interval.DURATION_SLIDE, a0, a1);
		}
	}

	private void NavigationController_(XController rootController) {
		this._rootController = rootController;
		this._currentController = rootController;
		_stack = new Stack<XController>();

		this._rootController.register(this);
		this._rootController.attach();
	}

	private XController popController_(final XController oldController,
			final XController newController, boolean animated) {

		Animator a0 = new Animator(Transform.SLIDE_CE, oldController);
		Animator a1 = new Animator(Transform.SLIDE_WC, newController);

		XController.animate(new Command() {
			public void execute() {
				oldController.getNavigationView().getElement().getStyle().setZIndex(-1);
				newController.getNavigationView().getElement().getStyle().setZIndex(1);

				oldController.doOnHideComplete();
				newController.doOnHideComplete();
				_currentController = newController;
				oldController.dettach();
			}
		}, 300, Interval.DURATION_SLIDE, a0, a1);

		return newController;
	}

	// public void setNavigationBarVisible(boolean visible, boolean animated) {
	// // TODO animated action
	// // navigation.getNavigationUIBar().setVisible(visible);
	// }
	//
	// public Toolbar getToolbar() {
	// return navigation.getToolbar();
	// }
	//
	// public void setToolbarVisible(boolean visible, boolean animated) {
	// // TODO animated action
	// navigation.getToolbar().setVisible(visible);
	// }
	// /**
	// * -----------------------<br>
	// * Private Methods <br>
	// * -----------------------<br>
	// */
	//
	// private void _pushNavBar(final SlideController newCtrl, final boolean
	// animated) {
	//
	// final NavigationBar newBar = newCtrl.getNavigationBar();
	// final NavigationBar currBar = currentController.getNavigationBar();
	//
	// newBar.setLeftTitle(currentController.getTitle(), true);
	//
	// BarItem leftBtn = newBar.getButtonLeft();
	// if (!leftBtn.hasClickHandler()) {
	// leftBtn.addClickHandler(new ClickHandler() {
	// public void onClick(ClickEvent event) {
	// _popController(animated, 0);
	// }
	// });
	// }
	// // navigation.getNavigationUIBar().addWidget(newBar);
	//
	// _animateBar(currBar, newBar, Direction.EAST_WEST, null);
	// }
	//
	// private void _popNavBar(SlideController newCtrl, final boolean animated) {
	//
	// final NavigationBar newBar = newCtrl.getNavigationBar();
	// final NavigationBar currBar = currentController.getNavigationBar();
	//
	// if (stack.size() > 0) {
	// newBar.setLeftTitle(stack.peek().getTitle(), true);
	//
	// BarItem leftBtn = newBar.getButtonLeft();
	// if (!leftBtn.hasClickHandler()) {
	// leftBtn.addClickHandler(new ClickHandler() {
	// public void onClick(ClickEvent event) {
	// _popController(animated, 0);
	// }
	// });
	// }
	// }
	// // navigation.getNavigationUIBar().addWidget(newBar);
	//
	// _animateBar(currBar, newBar, Direction.WEST_EAST, null);
	// }
	//
	// private void _animateBar(final NavigationBar currBar, final NavigationBar
	// newBar, Direction dir,
	// final Command afterCmd) {
	//
	// if (dir == Direction.WEST_EAST) {
	// Utils.setStyle(newBar, Transition.barSlide, dir.leftController(),
	// Transition.start);
	// Utils.setStyle(currBar, Transition.barSlide, dir.rightController(),
	// Transition.start);
	//
	// } else {
	// Utils.setStyle(currBar, Transition.barSlide, dir.leftController(),
	// Transition.start);
	// Utils.setStyle(newBar, Transition.barSlide, dir.rightController(),
	// Transition.start);
	// }
	//
	// new Timer() {
	// @Override
	// public void run() {
	// Utils.removeStyle(currBar, Transition.start);
	// Utils.removeStyle(newBar, Transition.start);
	//
	// new Timer() {
	// public void run() {
	// currBar.clearActive();
	//
	// currBar.removeFromParent();
	// Utils.setStyle(newBar, Transition.barSlide);
	// if (afterCmd != null) {
	// afterCmd.execute();
	// }
	// // Anime.release();
	// }
	// }.schedule(DURATION_BAR_ANIME);
	//
	// }
	// }.schedule(WAIT_BEFORE_START_ANIME);
	// }
	//
	// private void _animate(final SlideController currCtrl, final SlideController
	// newCtrl, Direction dir,
	// int startSchedule, final Command afterCmd) {
	//
	// Anime.lock();
	//
	// if (dir == Direction.WEST_EAST) {
	// Utils.setStyle(newCtrl, Transition.slide, dir.leftController(),
	// Transition.start);
	// Utils.setStyle(currCtrl, Transition.slide, dir.rightController(),
	// Transition.start);
	//
	// } else {
	// Utils.setStyle(currCtrl, Transition.slide, dir.leftController(),
	// Transition.start);
	// Utils.setStyle(newCtrl, Transition.slide, dir.rightController(),
	// Transition.start);
	// }
	//
	// //
	// navigation.getNavigationContent().addWidget(newCtrl.getView().asWidget());
	//
	// new Timer() {
	// @Override
	// public void run() {
	// Utils.removeStyle(currCtrl, Transition.start);
	// Utils.removeStyle(newCtrl, Transition.start);
	//
	// new Timer() {
	// public void run() {
	// // currCtrl.getView().asWidget().removeFromParent();
	// Utils.setStyle(newCtrl, Transition.slide);
	// if (afterCmd != null) {
	// afterCmd.execute();
	// }
	// Anime.release();
	// }
	// }.schedule(DURATION_SLIDER_ANIME);
	//
	// }
	// }.schedule(startSchedule);
	// }

	private String dbg_() {
		String d = ", size: " + _stack.size() + ", currentCtrl=" + _currentController;
		d += ", [";
		for (XController c : _stack) {
			d += c + ", ";
		}
		d += "]";
		return d;
	}

}
