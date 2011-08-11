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

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;

public class Main implements EntryPoint {

	// TransitionPage mainPage = new TransitionPage();
	// TransitionDemoPage mainPage = new TransitionDemoPage();
	// MyPage mainPage = new MyPage();

	public void onModuleLoad() {

		// final UIGinjector di = UIGinjector.INSTANCE;
		//		
		// Dispatcher.listen(di.getContext());

		// RootLayoutPanel.get().add(mainPage);

//		RootLayoutPanel.get().getElement().setId("rootLayoutPanel");
//
//		initHistory();
//
//		DemoTransitionController ctrl0 = new DemoTransitionController();
//		// ctrl0.getNavigation().getElement().setId("navigationPanel");
//		// ctrl0.attach();
//
//		NavigationController navCtrl = new NavigationController(ctrl0);
//
//		TabBarController tabBarController = new TabBarController();
//		tabBarController.getTabBar().getElement().setId("tabBarPanel");
//
//		TabController tabCtrl0 = new TabController(navCtrl);
//		tabCtrl0.set("Animations", "images/icons/tabIcon.png", "images/icons/tabIconSelected.png");
//
//		TabController tabCtrl1 = new TabController(new DemoWidgetsController("Widgets", "images/icons/bgSample0.png"));
//		tabCtrl1.set("Widgets", "images/icons/tabIcon2.png", "images/icons/tabIconSelected2.png");
//
//		TabController tabCtrl2 = new TabController(new DemoWidgetsController("Themes", "images/icons/bgSample1.png"));
//		tabCtrl2.set("Themes", "images/icons/tabIcon7.png", "images/icons/tabIconSelected7.png");
//
//		TabController tabCtrl3 = new TabController(new DemoWidgetsController("Phone Gap & HTML5",
//				"images/icons/bgSample2.png"));
//		tabCtrl3.set("More", "images/icons/tabIcon4.png", "images/icons/tabIconSelected4.png");
//
//		tabBarController.addControllers(tabCtrl0, tabCtrl1, tabCtrl2, tabCtrl3);

		// tabBarController.getTabBar().setView(ctrl0.getNavigation());

		// DemoFlipController ctrl1 = new DemoFlipController(null);
		// DemoFlipController ctrl2 = new DemoFlipController(null);
		// TabController tabCtrl0 = new TabController(navCtrl);
		// tabCtrl0.setTitle("tab0");
		// TabController tabCtrl1 = new TabController(ctrl1);
		// tabCtrl1.setTitle("tab1");
		// TabController tabCtrl2 = new TabController(ctrl2);
		// tabCtrl2.setTitle("tab2");
		// tabBarController.addControllers(tabCtrl0, tabCtrl1, tabCtrl2);

		// showDebugPanel(navCtrl, ctrl0, ctrl1, ctrl2);

		// DemoSwapController swapCtrl = new DemoSwapController(null);
		// swapCtrl.attach();
		// TestTableCtrl swapCtrl2 = new TestTableCtrl();
		// swapCtrl2.attach();
		// showDebugPanel(null, null, null, null, swapCtrl, swapCtrl2);

		// -------------

		// //------------- Working...
		// final DemoTransitionController page0 = new DemoTransitionController();
		// RootLayoutPanel.get().add(page0.getView());
		//
		// final SlideController page1 = new SlideController(new HTML("page 1..."));
		// page1.setTitle("page1");
		// page1.getNavigationBar().setRightTitle("Settings");
		//
		// final SlideController page2 = new SlideController(new HTML("page 2..."));
		// page2.setTitle("page2");
		//
		// final NavigationController navCtrl = new NavigationController(page0);
		// RootLayoutPanel.get().add(navCtrl.getView());

		// ---------- OLD

		// Button b0 = new Button("Push 0");
		// b0.addClickHandler(new ClickHandler() {
		// public void onClick(ClickEvent event) {
		// navCtrl.pushController(page0, true);
		// }
		// });
		// Button b1 = new Button("Push 1");
		// b1.addClickHandler(new ClickHandler() {
		// public void onClick(ClickEvent event) {
		// navCtrl.pushController(page1, true);
		// }
		// });
		// Button b2 = new Button("Push 2");
		// b2.addClickHandler(new ClickHandler() {
		// public void onClick(ClickEvent event) {
		// navCtrl.pushController(page2, true);
		// }
		// });
		// Button b4 = new Button("[Pop]");
		// b4.addClickHandler(new ClickHandler() {
		// public void onClick(ClickEvent event) {
		// navCtrl.popController(true);
		// }
		// });
		// Button b5 = new Button("[PopTo page1]");
		// b5.addClickHandler(new ClickHandler() {
		// public void onClick(ClickEvent event) {
		// navCtrl.popToController(page1, true);
		// }
		// });
		// Button b6 = new Button("[PopTo Root]");
		// b6.addClickHandler(new ClickHandler() {
		// public void onClick(ClickEvent event) {
		// navCtrl.popToRootController(true);
		// }
		// });
		// RootPanel.get(Id.NORTH.name()).add(b0);
		// RootPanel.get(Id.NORTH.name()).add(b1);
		// RootPanel.get(Id.NORTH.name()).add(b2);
		// RootPanel.get(Id.NORTH.name()).add(b4);
		// RootPanel.get(Id.NORTH.name()).add(b5);
		// RootPanel.get(Id.NORTH.name()).add(b6);
	}

	private void initHistory_() {
		History.newItem("1");
		History.newItem("2");
		History.newItem("3");
		History.newItem("4");
		History.newItem("5");

		History.addValueChangeHandler(new ValueChangeHandler<String>() {
			public void onValueChange(ValueChangeEvent<String> e) {
				History.newItem("7");
			}
		});
	}

//	private void showDebugPanel_(final XNavigationController navtroller, final XController... ctrls) {
//
//		PopupPanel debugPopup = new PopupPanel();
//		debugPopup.getElement().setId("dbg");
//		FlowPanel fp = new FlowPanel();
//
//		Button b0 = new Button("pop 0");
//		b0.addClickHandler(new ClickHandler() {
//			public void onClick(ClickEvent event) {
//				navtroller.popToController(ctrls[0], true);
//			}
//		});
//		fp.add(b0);
//
//		Button b1 = new Button("pop 1");
//		b1.addClickHandler(new ClickHandler() {
//			public void onClick(ClickEvent event) {
//				navtroller.popToController(ctrls[1], true);
//			}
//		});
//		fp.add(b1);
//
//		Button b2 = new Button("pop Root");
//		b2.addClickHandler(new ClickHandler() {
//			public void onClick(ClickEvent event) {
//				navtroller.popToRootController(true);
//			}
//		});
//		fp.add(b2);
//
//		Button b3 = new Button("push 0");
//		b3.addClickHandler(new ClickHandler() {
//			public void onClick(ClickEvent event) {
//				navtroller.pushController(ctrls[0], true);
//			}
//		});
//		fp.add(b3);
//
//		Button b4 = new Button("push 1");
//		b4.addClickHandler(new ClickHandler() {
//			public void onClick(ClickEvent event) {
//				navtroller.pushController(ctrls[1], true);
//			}
//		});
//		fp.add(b4);
//
//		Button b5 = new Button("push 2");
//		b5.addClickHandler(new ClickHandler() {
//			public void onClick(ClickEvent event) {
//				navtroller.pushController(ctrls[2], true);
//			}
//		});
//		fp.add(b5);
//
//		Button b6 = new Button("Fade IN");
//		b6.addClickHandler(new ClickHandler() {
//			public void onClick(ClickEvent event) {
////				Animator a0 = new Animator(Transform.FADE_IN, ctrls[3]);
////				XController.animate(null, 300, 1000, a0);
//			}
//		});
//		fp.add(b6);
//
//		Button b7 = new Button("Fade OUT");
//		b7.addClickHandler(new ClickHandler() {
//			public void onClick(ClickEvent event) {
////				Animator a0 = new Animator(Transform.FADE_OUT, ctrls[3]);
////				XController.animate(null, 300, 1000, a0);
//			}
//		});
//		fp.add(b7);
//
//		Button b8 = new Button("Swap");
//		b8.addClickHandler(new ClickHandler() {
//			public void onClick(ClickEvent event) {
//
//				ctrls[4].swapTo(ctrls[3], null);
//				// Command cmdTwo = new Command() {
//				// public void execute() {
//				// Animator a0 = new Animator(Transform.SWAP_OUT_TWO, ctrls[3]);
//				// Animator a1 = new Animator(Transform.SWAP_IN_TWO, ctrls[4]);
//				// XController.animate(new Command() {
//				// public void execute() {
//				// ctrls[3].dettach();
//				// }
//				// }, 10, Interval.DURATION_SWAP, a0, a1);
//				// }
//				// };
//				// Animator a0 = new Animator(Transform.SWAP_OUT_ONE, ctrls[3]);
//				// Animator a1 = new Animator(Transform.SWAP_IN_ONE, ctrls[4]);
//				// XController.animate(cmdTwo, 10, Interval.DURATION_SWAP, a0, a1);
//			}
//		});
//		fp.add(b8);
//
//		Button b9 = new Button("Swap Reverse");
//		b9.addClickHandler(new ClickHandler() {
//			public void onClick(ClickEvent event) {
//
//				ctrls[3].swapToReverse(ctrls[4], null);
//				// Command cmdTwo = new Command() {
//				// public void execute() {
//				// Animator a0 = new Animator(Transform.SWAP_OUT_TWO_REVERSE, ctrls[3]);
//				// Animator a1 = new Animator(Transform.SWAP_IN_TWO_REVERSE, ctrls[4]);
//				// XController.animate(new Command() {
//				// public void execute() {
//				// ctrls[4].dettach();
//				// }
//				// }, 10, Interval.DURATION_SWAP, a0, a1);
//				// }
//				// };
//				// Animator a0 = new Animator(Transform.SWAP_OUT_ONE_REVERSE, ctrls[3]);
//				// Animator a1 = new Animator(Transform.SWAP_IN_ONE_REVERSE, ctrls[4]);
//				// XController.animate(cmdTwo, 10, Interval.DURATION_SWAP, a0, a1);
//			}
//		});
//		fp.add(b9);
//
//		for (final Transform t : Transform.values()) {
//			Button b = new Button(t.title() + "." + t.direction());
//			b.addClickHandler(new ClickHandler() {
//				public void onClick(ClickEvent event) {
//					// ctrl0.transform(t);
//					// ctx.getEventBus().fireEvent(new NavigationEvent(0));
//				}
//			});
//			// fp.add(b);
//		}
//
//		debugPopup.add(fp);
//		debugPopup.center();
//	}

}
