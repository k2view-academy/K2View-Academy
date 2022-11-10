# Web Service Annotations

The *@WebService* annotation instructs the Fabric server runtime environment to expose all public methods on that object as a web service. 
Other annotations on individual methods or parameters can be added to increase granularity.

Using annotations makes it much easier to expose Java artifacts as web services. This enables Fabric developers to tag any Java function developed within a project and have Fabric expose all its public methods as a web service.

Note that a Java class that implements a web service must specify the *@WebService* annotation.


### Web services methods within the Logic Java file

When creating a new web service Java file, the web service is added as a method within the public class Logic in the Java logic file k2_ws.java . 
In the example below we have added 6 methods, each of which is configured with a different set of annotations and properties: 
- testWSannotations1(), 
- testWSannotations2(),
- testWSannotations3(),
- testWSannotations4(),
- testWSannotations5(),
- testWSannotations6()

```
public class Logic extends WebServiceUserCode {

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

@desc("Show example of annotations 5")
@webService(path = "a/b/c", verb = MethodType.GET, version = "1", isRaw = true, isCustomPayload = true, produce = {Produce.XML, Produce.JSON})
public static Object testWSannotations5() throws Exception {			
}

@desc("Show example of annotations 6")
@webService(path = "{name}/polak", verb = MethodType.GET, version = "1", isRaw = true, isCustomPayload = true, produce = {Produce.XML, Produce.JSON})
public static Object testWSannotations6(String name) throws Exception {			
}
}

```

### Annotations
Referring to the 6 web services defined in the above code snippet, we can observe that the following annotations are used:

#### @webService
This annotation is automatically added before each of the web services defined in the project. 

#### @desc
The [description tag](/articles/15_web_services_and_graphit/02_web_services_properties.md#description) is added before the public function declaration - e.g.
```("Show example of annotations 3")``` 

#### @serializeNull
When [serialization](/articles/15_web_services_and_graphit/02_web_services_properties.md#serialize-null) is de-activated for a given web service (this is done from the property panel of the web service Java file) a @serializeNull(false) tag is added before the declaration of the method itself.

#### @param
This annotation allows users to parse a specific function or variable as a parameter to the web service when it is called. This way the web service can benefit from dynamic input and output capabilities.

```
class inputPersonalData {
		String name;
		int age;
	}

@webService(path = "", verb = MethodType.POST, version = "1", isRaw = false, isCustomPayload = false, produce = {Produce.XML, Produce.JSON})
	public static Object testWSannotations7(@param(required=true) inputPersonalData persData) throws Exception {
	return persData.name + persData.age;
	} 
```

Due to Java limitations, not allowing to parse complex variables as input parameters, the @param annotation can also be attached to an input variable, and therefore it overrides the value of the input variable with the specified new value - e.g:

```
	@webService(path = "", verb = {MethodType.GET, MethodType.POST, MethodType.PUT, MethodType.DELETE}, version = "1", isRaw = false, isCustomPayload = false, produce = {Produce.XML, Produce.JSON})
	public static Object testWSannotations8(@param(required=true, name="name.private") String testStr1, @param(required=true, name="ido_polak") String testStr2 ) throws Exception {}
```

```public static Object testWSannotations8(@param(required=true, name="name.private") String testStr1```
- ```required=true``` flag must be set to true. If this is set to false, the new name will be disregarded.
- ```name="name.private"``` the new value of the variable *name.private* is taken as an input instead of the value held in ```String testStr1``` 



#### Path
If left empty, the value of the path will be the method name itself, i.e., using the first example of annotations in the code snippet above:
```api/v1/testWSannotations1```

Or, the path can be indicated explicitly, as shown in testWSannotations5:

```@webService(path = "a/b/c", verb = MethodType.GET, version = "1", isRaw = true, isCustomPayload = true, produce = {Produce.XML, Produce.JSON})```

It is also possible to set the path to the following directory: ```api/v1/a/b/c```


Additionally, variables can also be parsed into the path, as shown in the ```testWSannotations6``` example:

- {name}/polak will set the path to: ```api/v1/ido/polak``` provided that {name} was previously assigned to the value "ido"
- nameit/{name1}/{name2}/ will set the path to: ```api/v1/nameit/ido/bob``` provided that {name1} and {name2} were respectively assigned to the values "ido" and "bob"

***Note that if the path contains duplications, the web service deployment will fail.***



#### Verbs 
The following [properties](/articles/15_web_services_and_graphit/02_web_services_properties.md#web-service-properties) are added:

- [method](/articles/15_web_services_and_graphit/02_web_services_properties.md#verb) - ```MethodType.GET, MethodType.POST, MethodType.PUT, MethodType.DELETE``` depending on what was selected in the properties panel of the Java web service window in Fabric Studio.

#### Properties
The following flags are used to set the web services formats:
- [custom payload](/articles/15_web_services_and_graphit/02_web_services_properties.md#custom-payload) - in this case is set to true: ``` isCustomPayload = true``` 
- [produce](/articles/15_web_services_and_graphit/02_web_services_properties.md#produce) - can be either XML, JSON or CSV: e.g. ```produce = {Produce.XML, Produce.JSON})```
- [isRaw](/articles/15_web_services_and_graphit/02_web_services_properties.md#is-raw) - brings the data response 'as is' or not: e.g. ```isRaw = true```
- [elevated permission](/articles/15_web_services_and_graphit/02_web_services_properties.md#elevated_permission) - indicates whether users' permissions should be elevated to the web service or not.

#### @legacy
Full alignment with RESTful Web Services functionality was introduced in Fabric 5.5.
To enable a backward compatibility while preserving the existing Web Services response structure, the ```@legacy``` annotation must be added at the category level. 
Use either Notepad or IntelliJ to edit the annotation. 
 * Web Services under a category with an @legacy annotation respond according to the legacy (older) structure. 
 * New Web Services under the same category use the appropriate (more recent) Web Service response structure.



[![Previous](/articles/images/Previous.png)](/articles/15_web_services_and_graphit/12_custom_ws_java_examples.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/15_web_services_and_graphit/14_rest_api_additions.md)

