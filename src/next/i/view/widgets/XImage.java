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
package next.i.view.widgets;

import next.i.XStyle;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Image;

/**
 * 
 * <p>
 * <img class='ai' src='../../../../resources/XImage.png' />
 * </p>
 * 
 * @deprecated Using Image has locking effect on DOM, thus Image support will be
 *             dropped. Consider using alternatives such as XImageDiv using
 *             &lt;div/&gt; with css background-image
 */
@Deprecated
public class XImage extends Image {

	/**
	 * Creates an empty image.
	 */
	public XImage() {
		setStyleName(XStyle.ximage.name());
	}

	/**
	 * Creates an image whose size and content are defined by an ImageResource.
	 * 
	 * @param resource
	 *          the ImageResource to be displayed
	 */
	public XImage(ImageResource resource) {
		super(resource);
	}

	/**
	 * Creates an image with a specified URL. The load event will be fired once
	 * the image at the given URL has been retrieved by the browser.
	 * 
	 * @param url
	 *          the URL of the image to be displayed
	 */
	public XImage(String url) {
		super(url);

		addLoadHandler(new LoadHandler() {
			@Override
			public void onLoad(LoadEvent event) {
				Element element = event.getRelativeElement();
				if (element == XImage.this.getElement()) {
					int originalHeight = XImage.this.getOffsetHeight();
					int originalWidth = XImage.this.getOffsetWidth();
					int clientW = Window.getClientWidth();
					int clientH = Window.getClientHeight();
					// Window.alert("originalHeight=" + originalHeight +
					// ", originalWidth=" + originalWidth);

					int size = 0;
					if (clientH > clientW) {
						size = clientW;
					} else {
						size = clientH;
					}

					size = size - 20/* padding */;

					// FIXME add orientation listener
					if (originalHeight > originalWidth) {
						XImage.this.setHeight(size + "px");
					} else {
						XImage.this.setWidth(size + "px");
					}
				}
			}
		});

	}

	private native int orienttionInx()/*-{
		if ($wnd.orientation) {
			if ($wnd.orientation == 0) {
				return 1;
			} else {
				return 1.6;
			}
		} else {
			return 1;
		}
	}-*/;

	/**
	 * Creates a clipped image with a specified URL and visibility rectangle. The
	 * visibility rectangle is declared relative to the the rectangle which
	 * encompasses the entire image, which has an upper-left vertex of (0,0). The
	 * load event will be fired immediately after the object has been constructed
	 * (i.e. potentially before the image has been loaded in the browser). Since
	 * the width and height are specified explicitly by the user, this behavior
	 * will not cause problems with retrieving the width and height of a clipped
	 * image in a load event handler.
	 * 
	 * @param url
	 *          the URL of the image to be displayed
	 * @param left
	 *          the horizontal co-ordinate of the upper-left vertex of the
	 *          visibility rectangle
	 * @param top
	 *          the vertical co-ordinate of the upper-left vertex of the
	 *          visibility rectangle
	 * @param width
	 *          the width of the visibility rectangle
	 * @param height
	 *          the height of the visibility rectangle
	 */
	public XImage(String url, int left, int top, int width, int height) {
		super(url, left, top, width, height);
	}
}
