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


public class FxUtilOld {

//  public static NMorphStyle moveEffect(int fromX, int toX, Element element, final Command afterMoveCommand) {
////    int top = Globals.NAV_HIGHT; // getAbsoluteTop();
//    return moveHorizEffect(fromX, toX, element, afterMoveCommand, 100, 100);
//  }
//
//  public static NMorphStyle moveHorizEffect(int fromX, int toX, Element element, final Command afterMoveCommand,
//      int fromOpacity, int toOpacity) {
//    return moveHorizEffect(fromX, toX, element, afterMoveCommand, fromOpacity, toOpacity, 0.3);
//  }
//
//  public static NMorphStyle moveHorizEffect(int fromX, int toX, Element element, final Command afterMoveCommand,
//      int fromOpacity, int toOpacity, double duration) {
//    // position: absolute
//    Move eff = new Move(new Rule("start{position: relative; left:" + fromX + "px; opacity: " + fromOpacity + "%;}"),
//        new Rule("end{position: relative; left: " + toX + "px; opacity: " + toOpacity + "%;}"));
//    eff.setEffectElement(element);
//    if (afterMoveCommand != null) {
//      eff.addEffectCompletedHandler(new EffectCompletedHandler() {
//        @Override
//        public void onEffectCompleted(EffectCompletedEvent event) {
//          afterMoveCommand.execute();
//        }
//      });
//    }
//    // eff.setTransitionType(new LinearTransitionPhysics());
//    eff.setDuration(duration);
//    return eff;
//  }
//
//  public static NMorphStyle moveHorizEffectAbs(int fromX, int toX, Element element, final Command afterMoveCommand,
//      int fromOpacity, int toOpacity, double duration) {
//    // position: absolute
//    Move eff = new Move(new Rule("start{position: absolute; left:" + fromX + "px; top: 0px;opacity: " + fromOpacity
//        + "%;}"), new Rule("end{position: absolute; left: " + toX + "px; top: 0px; opacity: " + toOpacity + "%;}"));
//    eff.setEffectElement(element);
//    if (afterMoveCommand != null) {
//      eff.addEffectCompletedHandler(new EffectCompletedHandler() {
//        @Override
//        public void onEffectCompleted(EffectCompletedEvent event) {
//          afterMoveCommand.execute();
//        }
//      });
//    }
//    eff.setTransitionType(new LinearTransitionPhysics());
//    eff.setDuration(duration);
//    return eff;
//  }
//
//  public static NMorphStyle opacityEffect(Element element, final Command afterCmd, int fromOpacity, int toOpacity) {
//
//    Move eff = new Move(new Rule("start{opacity: " + fromOpacity + "%;}"),
//        new Rule("end{opacity: " + toOpacity + "%;}"));
//    eff.setEffectElement(element);
//
//    if (afterCmd != null) {
//      eff.addEffectCompletedHandler(new EffectCompletedHandler() {
//        @Override
//        public void onEffectCompleted(EffectCompletedEvent event) {
//          afterCmd.execute();
//        }
//      });
//    }
//
//    // eff.setTransitionType(new LinearTransitionPhysics());
//    eff.setDuration(0.3);
//    return eff;
//  }
//
//  public static NMorphStyle doShow(Widget widg, final Command afterCmd) {
//		
//  	//TODO
//  	Element el = widg.getElement();
//  	el.getStyle().setOpacity(0);
////  	widg.setVisible(true);
//  	int fromOpacity = 0;
//  	int toOpacity = 100;
//  	
//    Move eff = new Move(new Rule("start{opacity: " + fromOpacity + "%;}"),
//        new Rule("end{opacity: " + toOpacity + "%;}"));
//    eff.setEffectElement(el);
//
//    if (afterCmd != null) {
//      eff.addEffectCompletedHandler(new EffectCompletedHandler() {
//        @Override
//        public void onEffectCompleted(EffectCompletedEvent event) {
//          afterCmd.execute();
//        }
//      });
//    }
//
//    // eff.setTransitionType(new LinearTransitionPhysics());
//    eff.setDuration(0.3);
//    return eff;
//  }
//
//  public static NMorphStyle doHide(final Widget widg, final Command afterCmd) {
//  	int fromOpacity = 100;
//  	int toOpacity = 0;
//  	
//  	final Element el = widg.getElement();
//  	
//    Move eff = new Move(new Rule("start{opacity: " + fromOpacity + "%;}"),
//        new Rule("end{opacity: " + toOpacity + "%;}"));
//    eff.setEffectElement(el);
//    
//    eff.addEffectCompletedHandler(new EffectCompletedHandler() {
//			@Override
//			public void onEffectCompleted(EffectCompletedEvent event) {
//		    el.getStyle().setPropertyPx("left", -2000);
//		    el.getStyle().setPropertyPx("top", -2000);
//			}
//		});
//    
//    if (afterCmd != null) {
//      eff.addEffectCompletedHandler(new EffectCompletedHandler() {
//        @Override
//        public void onEffectCompleted(EffectCompletedEvent event) {
//          afterCmd.execute();
//        }
//      });
//    }
//
//    // eff.setTransitionType(new LinearTransitionPhysics());
//    eff.setDuration(0.3);
//    return eff;
//  }
//
//  public static NMorphStyle moveVerticalEffect(int fromY, int toY, Element element, final Command afterMoveCommand) {
//    Move eff = new Move(new Rule("start{position: absolute; left:" + 0 + "px; top: " + fromY + "px;}"), new Rule(
//        "end{position: absolute; left: " + 0 + "px; top: " + toY + "px;}"));
//    eff.setEffectElement(element);
//    if (afterMoveCommand != null) {
//      eff.addEffectCompletedHandler(new EffectCompletedHandler() {
//        @Override
//        public void onEffectCompleted(EffectCompletedEvent event) {
//          afterMoveCommand.execute();
//        }
//      });
//    }
//    // eff.setTransitionType(new LinearTransitionPhysics());
//    eff.setDuration(0.3);
//    return eff;
//  }
//
//  public static NMorphStyle moveEffect(int fromX, int toX, Element element) {
//    return moveEffect(fromX, toX, element, null);
//  }
//
//  public static void transitionWidgets(FlexTable flexTable, int row, int column, Widget newWidget) {
//
//    Widget prevWidg = null;
//
//    try {
//      prevWidg = flexTable.getWidget(row, column);
//    } catch (IndexOutOfBoundsException e) {
//      // normal behavior. FlexTabel with no rows and columns
//    }
//    if (prevWidg != null) {
//      Fade eff = new Fade(prevWidg.getElement());
//      eff.setDuration(0.5);
//      eff.play();
//    }
//
//    newWidget.getElement().getStyle().setOpacity(0);
//    flexTable.setWidget(row, column, newWidget);
//    NShow eff = new NShow(newWidget.getElement());
//    eff.setDuration(0.5);
//    eff.play();
//  }
//
//  public static void transitionWidgets(SimplePanel panel, Widget newWidget) {
//
//    Widget prevWidg = panel.getWidget();
//
//    if (prevWidg != null) {
//      Fade eff = new Fade(prevWidg.getElement());
//      eff.setDuration(0.5);
//      eff.play();
//    }
//
//    newWidget.getElement().getStyle().setOpacity(0);
//    panel.setWidget(newWidget);
//    NShow eff = new NShow(newWidget.getElement());
//    eff.setDuration(0.5);
//    eff.play();
//  }


}
