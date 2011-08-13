NEXT
====

NEXT is a Java GWT UI framework for developing Native Looking HTML5 Mobile apps for SmartPhones and Tablets. 

MVC based. Amazing looking UI catalog. Cross-Platform.

### More information:
http://nextinterfaces.com/

### Demo (WebKit only):
http://nextinterfaces.com/DEMO


Supported Phones & Tablets:
---------------------------

Device agnostic, it runs on 6 devices: iOS, Android, BlackBerry OS6+, webOS, Samsung Bada, PlayBook


### Dependencies:
* Java5+
* GWT (Google Web Kit) 2.2+
* Eclipse & ANT (optional)
	
Installation:
-------------

### Running the Demo:
* [Download](http://nextinterfaces.com/download) `next-xx.zip` file 
* Add the attached `hello-next` project to Eclipse. Eclipse should automatically discover it as a GWT project.
* From Eclipse `/Run /Run As Web Application`
* You should see a demo similar to [next-demo](http://nextinterfaces.com/demo)

### Start a new project:
* Alternatively, you can copy `hello-next/war/WEB-INF/next.jar` file to your GWT project
* Add it to your `classpath`
* Add `next.css` and `next/images` to your project root.
* And those are all the required libraries to get you started


### Hello World in 30 seconds:

* Create a new GWT project as explained above
* Create class `HelloWorldController`

				class HelloWorldController extends XTableController {
						public HelloWorldController() {
								setTitle("Hello World");
								TableData tableDS = new TableData();
								tableDS.add("Hello", "World");
								initDataSource(tableDS);
						}
				}
      
* In your `EntryPoint` class paste

				public void onModuleLoad() {
								XTabBarController tabBarController = new XTabBarController();
								tabBarController.addControllers(new XTabController(new HelloWorldController()));
				}

      
* Eclipse `/Run /Run as Web Application` resulting in [this screenshot](http://goo.gl/fFQXY)

See the zipped `/hello-next` project or [next-demo](https://github.com/nextinterfaces/next-demo) for more information.


Documentation:
--------------

http://nextinterfaces.com/START

### Google Group:
http://groups.google.com/group/nextinterfaces

### Forum:
http://nextinterfaces.com/forum

Copyright (c) 2011 Vancouver Ywebb Consulting Ltd, released under Apache License v.2.0
 