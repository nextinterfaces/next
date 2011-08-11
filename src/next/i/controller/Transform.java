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

enum Transform {	

	/**
	 * Obfuscated mode
	 */
	START(C.s.s().start(), null, -1),
	
	SLIDE_NC(C.s.s().slide(), C.s.s().NC(), 300),
	SLIDE_CN(C.s.s().slide(), C.s.s().CN(), 300),
	
	SLIDE_CE(C.s.s().slide(), C.s.s().CE(), 300),
	SLIDE_EC(C.s.s().slide(), C.s.s().EC(), 300),
	
	SLIDE_CS(C.s.s().slide(), C.s.s().CS(), 300),
	SLIDE_SC(C.s.s().slide(), C.s.s().SC(), 300),
	
	SLIDE_CW(C.s.s().slide(), C.s.s().CW(), 300),
	SLIDE_WC(C.s.s().slide(), C.s.s().WC(), 300),
	
	FADE_OUT(C.s.s().fade(), C.s.s().out(), 1000),
	FADE_IN(C.s.s().fade(), C.s.s().in(), 1000),
	
	POP_IN(C.s.s().pop(), C.s.s().in(), 300),
	POP_OUT(C.s.s().pop(), C.s.s().out(), 300),
	
	FLIP_IN(C.s.s().flip(), C.s.s().in(), 300),
	FLIP_OUT(C.s.s().flip(), C.s.s().out(), 300),
	
	SWAP_OUT_ONE(C.s.s().swapOne(), C.s.s().out(), 400),
	SWAP_OUT_TWO(C.s.s().swapTwo(), C.s.s().out(), 400),
	SWAP_IN_ONE(C.s.s().swapOne(), C.s.s().in(), 400),
	SWAP_IN_TWO(C.s.s().swapTwo(), C.s.s().in(), 400),
	
	SWAP_OUT_ONE_REVERSE(C.s.s().swapOneReverse(), C.s.s().out(), 400),
	SWAP_OUT_TWO_REVERSE(C.s.s().swapTwoReverse(), C.s.s().out(), 400),
	SWAP_IN_ONE_REVERSE(C.s.s().swapOneReverse(), C.s.s().in(), 400),
	SWAP_IN_TWO_REVERSE(C.s.s().swapTwoReverse(), C.s.s().in(), 400),
	;
	
//	/**
//	 * NON obfuscated CSS mode
//	 */
//	START("start", null, -1),
//	
//	SLIDE_NC("slide", "NC", 300),
//	SLIDE_CN("slide", "CN", 300),
//	
//	SLIDE_CE("slide", "CE", 300),
//	SLIDE_EC("slide", "EC", 300),
//	
//	SLIDE_CS("slide", "CS", 300),
//	SLIDE_SC("slide", "SC", 300),
//	
//	SLIDE_CW("slide", "CW", 300),
//	SLIDE_WC("slide", "WC", 300),
//	
//	FADE_OUT("fade", "out", 1000),
//	FADE_IN("fade", "in", 1000),
//	
//	POP_IN("pop", "in", 300),
//	POP_OUT("pop", "out", 300),
//	
//	FLIP_IN("flip", "in", 300),
//	FLIP_OUT("flip", "out", 300),
//	
//	SWAP_OUT_ONE("swapOne", "out", 400),
//	SWAP_OUT_TWO("swapTwo", "out", 400),
//	SWAP_IN_ONE("swapOne", "in", 400),
//	SWAP_IN_TWO("swapTwo", "in", 400),
//	
//	SWAP_OUT_ONE_REVERSE("swapOneReverse", "out", 400),
//	SWAP_OUT_TWO_REVERSE("swapTwoReverse", "out", 400),
//	SWAP_IN_ONE_REVERSE("swapOneReverse", "in", 400),
//	SWAP_IN_TWO_REVERSE("swapTwoReverse", "in", 400),
//	;
	
	private String title;
	private String direction;
	private int duration;

	Transform(String title, String direction, int duration) {
		this.title = title;
		this.direction = direction;
		this.duration = duration;
	}

	public String title() {
		return title;
	}

	public String direction() {
		return direction;
	}

	public int duration() {
		return duration;
	}

}
