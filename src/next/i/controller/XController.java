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
import next.i.view.MPanelBase;
import next.i.view.XDragScrollView;
import next.i.view.XHorizontalScrollView;
import next.i.view.XNavigationBar;
import next.i.view.XNavigationView;
import next.i.view.XVerticalScrollView;
import next.i.view.XView;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.IsWidget;

/**
 * This class allows view-management functionality for toolbars, navigation
 * bars, and application views. The XController class also animates on view
 * transitions.
 *
 * 
 * <h3>Example:</h3>
 * 
 * <pre>
 * class MyController extends XController {
 * 
 * 	private Command action;
 * 
 * 	public MyController() {
 * 
 * 		setTitle(&quot;Transform&quot;);
 * 
 * 		getNavigationBar().setLeftTitle(&quot;Back&quot;, Type.BACK_BUTTON);
 * 		getNavigationBar().getButtonLeft().addClickHandler(new ClickHandler() {
 * 			public void onClick(ClickEvent event) {
 * 				getNavigationController().popController(true);
 * 			}
 * 		});
 * 
 * 		getNavigationBar().setRightTitle(&quot;Okay&quot;);
 * 		getNavigationBar().getButtonRight().addClickHandler(new ClickHandler() {
 * 			public void onClick(ClickEvent event) {
 * 				Window.alert(&quot;You clicked Okay.&quot;);
 * 			}
 * 		});
 * 	}
 * }
 * </pre>
 */
public abstract class XController implements IController {

	private XNavigationBar _navigationBar;
	private IView _view;
	private String _title;
	private int _id;
	private XNavigationView _navigation;
	private XNavigationController _navigationController;
	private IsWidget viewContent;

	public static enum Scroll {
		VERTICAL, // default
		HORIZONTAL, DRAGGABLE, NO_SCROLL
	}

	public XController() {
		XController_();
	}

	abstract public Scroll getScrollOrientation();

	void register(XNavigationController navigationController) {
		// if (view != null) {
		// view.removeFromParent();
		// }
		// if (navigationBar != null) {
		// navigationBar.removeFromParent();
		// }
		this._navigationController = navigationController;
		// this.navigationBar = navigationController.getNavigationBar();
		// this.navigation = navigationController.getNavigation();
	}

	public IView getView() {
		if (_view == null) {
			if (getScrollOrientation() == Scroll.VERTICAL) {
				_view = new XVerticalScrollView();

			} else if (getScrollOrientation() == Scroll.HORIZONTAL) {
				_view = new XHorizontalScrollView();

			} else if (getScrollOrientation() == Scroll.DRAGGABLE) {
				_view = new XDragScrollView();

			} else if (getScrollOrientation() == Scroll.NO_SCROLL) {
				_view = new XView();

			} else {
				_view = new XVerticalScrollView();

			}
		}
		return _view;
	}

	public abstract IsWidget getViewContent();

	public XNavigationView getNavigationView() {
		return _navigation;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		this._title = title;
		_navigationBar.setTitle(this._title);
	}

	public XNavigationBar getNavigationBar() {
		return _navigationBar;
	}

	void doOnHideComplete() {
		onHideComplete();
	}

	@Override
	public String toString() {
		return _title + "-" + hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return equals_(obj);
	}

	@Override
	public int hashCode() {
		return _id;
	}

	public XNavigationController getNavigationController() {
		return _navigationController;
	}

	public void attach() {
		attach_();
	}

	public void dettach() {
		getNavigationView().removeFromParent();
	}

	/**
	 * This method is invoked when on hide completion.
	 */
	public void onHideComplete() {
		// will be implemented by children
	}

	public void onAfterLoad() {
		// Utils.setStyle(this, Transition.BEFORE, Transition.start);
	}

	public static void animate(final Command afterCmd, final int delayBeforeStart, final int animeDuration,
			final Animator... animators) {
		animate_(afterCmd, delayBeforeStart, animeDuration, animators);
	}

	public void slideUpIn() {
		slideUpIn_();
	}

	public void slideUpReverse() {
		slideUpReverse_();
	}

	public void slideDownIn() {
		slideDownIn_();
	}

	public void slideDownReverse() {
		slideDownReverse_();
	}

	public void flipTo(final XController controller, final Command afterCommand) {
		flipTo_(controller, afterCommand);
	}

	public void swapTo(final XController controller, final Command afterCommand) {
		swapTo_(controller, afterCommand);
	}

	public void swapToReverse(final XController controller, final Command afterCommand) {
		swapToReverse_(controller, afterCommand);
	}

	public void popIn() {
		popIn_();
	}

	public void popOut() {
		popOut_();
	}

	public void fadeIn() {
		fadeIn_();
	}

	public void fadeOut() {
		fadeOut_();
	}

	/**
	 * private
	 */
	private void XController_() {

		this._id = ControllerID.get();

		_navigationBar = new XNavigationBar();
		_navigation = new XNavigationView();
		_navigation.setNavigationContent(getView());
		_navigation.setNavigationBar(_navigationBar);

		// defer invocation after sub-class constructors
		Timer t = new Timer() {
			@Override
			public void run() {
				onAfterLoad();

				if (XController.this.viewContent == null) {
					IsWidget w = getViewContent();
					if (w != null) {
						XController.this.viewContent = w;
						// TODO casting doesn't seem right
						((MPanelBase) getView()).add(w.asWidget());
					}
				}
				getNavigationBar().repaint();
			}
		};
		t.schedule(1);
	}

	// private void XController_(/*IsWidget isWidget*/) {
	// XController_();
	// // _view.setContent(isWidget);
	// _view.setContent(getViewContent());
	// }

	private boolean equals_(Object obj) {
		if (obj == null) {
			return false;
		}

		if (obj instanceof XController) {
			XController objSC = (XController) obj;
			return objSC.hashCode() == this.hashCode();

		} else {
			return false;
		}
	}

	private void attach_() {
		if (!getNavigationView().isAttached()) {
			getNavigationView().setSize("100%", "100%");
			// RootLayoutPanel.get().add(this.getNavigation());
			RootAttacher.attach(this);
		}
	}

	private static void animate_(final Command afterCmd, final int delayBeforeStart, final int animeDuration,
			final Animator... animators) {

		Anime.lock();

		for (Animator a : animators) {
			a.getController().attach();
			Element el = a.getController().getNavigationView().asWidget().getElement();
			el.getStyle().setProperty("WebkitTransitionDuration", "0ms");
			Utils
					.setStyle(a.getController(), a.getTransform().title(), a.getTransform().direction(), Transform.START.title());
		}

		new Timer() {
			@Override
			public void run() {
				for (Animator a : animators) {
					Element el = a.getController().getNavigationView().asWidget().getElement();
					el.getStyle().setProperty("WebkitTransitionDuration", a.getTransform().duration() + "ms");
				}

				new Timer() {
					public void run() {
						for (Animator b : animators) {
							Utils.removeStyle(b.getController(), Transform.START.title());
						}

						new Timer() {
							public void run() {
								// currCtrl.getView().asWidget().removeFromParent();
								// Utils.setStyle(XController.this, Transition.slide);
								// Utils.setStyle(XController.this, Transition.BEFORE,
								// Transition.start);
								if (afterCmd != null) {
									afterCmd.execute();
								}
								Anime.release();
							}
						}.schedule(animeDuration);

					}
				}.schedule(delayBeforeStart);

			}
		}.schedule(1);

	}

	private void slideUpIn_() {
		Animator a0 = new Animator(Transform.SLIDE_SC, this);
		XController.animate(null, 300, Interval.DURATION_SLIDE, a0);
	}

	private void slideUpReverse_() {
		Animator a0 = new Animator(Transform.SLIDE_CS, this);
		XController.animate(new Command() {
			public void execute() {
				XController.this.dettach();
			}
		}, 300, Interval.DURATION_SLIDE, a0);
	}

	private void slideDownIn_() {
		Animator a0 = new Animator(Transform.SLIDE_NC, this);
		XController.animate(null, 300, Interval.DURATION_SLIDE, a0);
	}

	private void slideDownReverse_() {
		Animator a0 = new Animator(Transform.SLIDE_CN, this);
		XController.animate(new Command() {
			public void execute() {
				XController.this.dettach();
			}
		}, 300, Interval.DURATION_SLIDE, a0);
	}

	private void flipTo_(final XController controller, final Command afterCommand) {
		Animator a1 = new Animator(Transform.FLIP_OUT, this);
		XController.animate(null, 300, Interval.DURATION_FLIP, a1);

		new Timer() {
			public void run() {
				if (afterCommand != null) {
					afterCommand.execute();
				}
				XController.this.dettach();
				Animator a0 = new Animator(Transform.FLIP_IN, controller);
				XController.animate(null, 200, Interval.DURATION_FLIP, a0);
			}
		}.schedule(500);
	}

	private void swapTo_(final XController controller, final Command afterCommand) {
		Command cmdTwo = new Command() {
			public void execute() {
				Animator a0 = new Animator(Transform.SWAP_OUT_TWO, XController.this);
				Animator a1 = new Animator(Transform.SWAP_IN_TWO, controller);
				XController.animate(new Command() {
					public void execute() {
						XController.this.dettach();
					}
				}, 10, Interval.DURATION_SWAP, a0, a1);
			}
		};
		Animator a0 = new Animator(Transform.SWAP_OUT_ONE, this);
		Animator a1 = new Animator(Transform.SWAP_IN_ONE, controller);
		XController.animate(cmdTwo, 10, Interval.DURATION_SWAP, a0, a1);
	}

	private void swapToReverse_(final XController controller, final Command afterCommand) {
		Command cmdTwo = new Command() {
			public void execute() {
				Animator a0 = new Animator(Transform.SWAP_OUT_TWO_REVERSE, controller);
				Animator a1 = new Animator(Transform.SWAP_IN_TWO_REVERSE, XController.this);
				XController.animate(new Command() {
					public void execute() {
						XController.this.dettach();
					}
				}, 10, Interval.DURATION_SWAP, a0, a1);
			}
		};
		Animator a0 = new Animator(Transform.SWAP_OUT_ONE_REVERSE, controller);
		Animator a1 = new Animator(Transform.SWAP_IN_ONE_REVERSE, this);
		XController.animate(cmdTwo, 10, Interval.DURATION_SWAP, a0, a1);
	}

	private void popIn_() {
		Animator a0 = new Animator(Transform.POP_IN, this);
		XController.animate(null, 1, Interval.DURATION_POP, a0);
	}

	private void popOut_() {
		Animator a0 = new Animator(Transform.POP_OUT, this);
		XController.animate(new Command() {
			public void execute() {
				XController.this.dettach();
			}
		}, 1, Interval.DURATION_POP, a0);
	}

	private void fadeIn_() {
		Animator a0 = new Animator(Transform.FADE_IN, this);
		XController.animate(null, 1, Interval.DURATION_FADE, a0);
	}

	private void fadeOut_() {
		Animator a0 = new Animator(Transform.FADE_OUT, this);
		XController.animate(new Command() {
			public void execute() {
				XController.this.dettach();
			}
		}, 1, Interval.DURATION_FADE, a0);
	}
}
