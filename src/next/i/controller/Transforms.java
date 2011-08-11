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

import com.google.gwt.resources.client.CssResource;

interface Transforms extends CssResource {

	String start();
	
	String slide();
	
	String SC();
	String CW();
	String EC();
	String CS();
	String CE();
	String WC();
	String NC();
	String CN();
	
	String fade();
	String swapTwoReverse();
	String pop();
	String flip();
	
	String swapOne();
	String swapOneReverse();
	String swap();
	String swapTwo();
	
	String in();
	String out();
	
}
