# Web Service Annotations

The annotation @WebService tells Fabric server runtime environment to expose all public methods on that bean as a web service. 
Other annotations on individual methods or parameters can be added to increase granularity. 
Using annotations makes it much easier to expose Java artifacts as web services, therefore Fabric developers can tag any java function developed within a project and have Fabric expose all its public methods as a web service.

Conversely, a Java class that implements a Web service must specify the @WebService annotation.


### Web services methods within the Logic Java file

When creating a new webservice java file, it is added as a method within the public class Logic in the java logic file k2_ws.java 
In the example below we have added 4 methods testWSannotations1(), testWSannotations2(),testWSannotations3(),testWSannotations4(), each one with a different set of attributes:
  
```public class Logic extends WebServiceUserCode {

	@desc("Show example of annotations 1")
	@webService(path = "", verb = {MethodType.GET, MethodType.POST}, version = "1", isRaw = true, isCustomPayload = false, produce = {Produce.XML, Produce.JSON, Produce.CSV})
	public static Object testWSannotations1() throws Exception {		
	}

	@desc("Show example of annotations 2")
	@serializeNull(false)	
	@webService(path = "", verb = {MethodType.GET, MethodType.DELETE}, version = "2", isRaw = false, isCustomPayload = true, produce = {Produce.XML, Produce.JSON})
	public static ResultSetWrapper testWSannotations2() throws Exception {
	}

	@desc("Show example of annotations 3")
	@serializeNull(false)	
	@webService(path = "", verb = {MethodType.GET, MethodType.POST, MethodType.PUT, MethodType.DELETE}, version = "1", isRaw = false, isCustomPayload = true, produce = {Produce.XML})
	public static Object testWSannotations3() throws Exception {
	}

	@desc("Show example of annotations 4")
	@webService(path = "", verb = {MethodType.GET}, version = "1", isRaw = true, isCustomPayload = true, produce = {Produce.XML, Produce.JSON})
	public static Object testWSannotations4() throws Exception {			
	}
}

```

### Annotations
Looking at the 4 objects defined above, we can observe that the following annotations are used:

#### @webService
This annotation has been added before each one of the web-services defined in the project. 

#### @desc
This tag has been added before the public function - e.g.
```("Show example of annotations 3")``` 

#### @serializeNull
When serialization is deactivated for the webservice (in the property panel of the web service java file) a @serializeNull(false) tag is added before the declaration of the method itself.

#### verbs 
The following [properties](/articles/15_web_services_and_graphit/02_web_services_properties.md#verb) are added as follows:

- [method](/articles/15_web_services_and_graphit/02_web_services_properties.md#verb)```MethodType.GET, MethodType.POST, MethodType.PUT, MethodType.DELETE``` depending on what was selected in the properties panel of the java web service window in Fabric Studio
- [payload](/articles/15_web_services_and_graphit/02_web_services_properties.md#custom-payload) in this case set to true: ``` isCustomPayload = true``` 
- [output format](/articles/15_web_services_and_graphit/02_web_services_properties.md#produce) can be XML, JSON or CSV: e.g. ```produce = {Produce.XML, Produce.JSON})```
- [isRaw](/articles/15_web_services_and_graphit/02_web_services_properties.md#is-raw) to brings the data response as is or not: e.g. ```isRaw = true```

#### Legacy Annotations

Full alignment with RESTful Web Services functionality was introduced in Fabric 5.5.  To enable backwards compatibility while preserving the existing Web Services response structure, add the @legacy annotation on a category level. 
Use either Notepad or IntelliJ to edit the annotation. 
 * Web Services under a category with an @legacy annotation respond in the older structure. 
 * New Web Services under the same category align the Web Service response structure accordingly.



[![Previous](/articles/images/Previous.png)](/articles/15_web_services_and_graphit/09_swagger.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/15_web_services_and_graphit/11_response_codes.md)


