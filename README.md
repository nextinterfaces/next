NEXT
====

NEXT is a Java GWT UI framework for developing Native Looking HTML5 Mobile apps for SmartPhones and Tablets. 

MVC based. Amazing looking UI catalog. Cross-Platform.

### More information:
http://nextinterfaces.com/next
 
### Demo (WebKit only):
http://nextinterfaces.com/DEMO

### Apps (WebKit only):
http://nextinterfaces.com/showcases


Supported Phones & Tablets:
---------------------------

Device agnostic, it runs on 6 devices: iOS, Android, BlackBerry 10

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
* [Download](http://nextinterfaces.com/download) `next-xx.zip` file 
* Add the attached `next.jar` (`/hello-next/war/WEB-INF/next.jar`) file to your GWT project & classpath
* Add `next.css` and `next/images` to your project root. Use `index.html` for reference.
* Edit your project .gwt.xml and add

				<?xml version="1.0" encoding="UTF-8"?>
				<module rename-to='your-mobule-name'>
								...
								<inherits name='next.interfaces' />
								<entry-point class='com.domain.YourEntryPoint' />
								...
				</module>


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

      
* Eclipse `/Run /Run as Web Application` resulting in [this screenshot](http://nextinterfaces.com/images/misc/hello-world-gwt-mobile-demo-phone-touch.png)

See the zipped `/hello-next` project or [next-demo](https://github.com/nextinterfaces/next-demo) for more information.


Documentation:
--------------

http://nextinterfaces.com/next

Copyright (c) 2017	NEXT interfaces, released under Apache License v.2.0
 
