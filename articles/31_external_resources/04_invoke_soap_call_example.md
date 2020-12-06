# Invoking a SOAP Call

### Introduction

SOAP is an XML-based messaging protocol for web services. While many services now work with REST with JSON, SOAP is still used in the Billing and CRM arena and by some common services which use SAML.

SOAP starts by analyzing WSDL (Web Services Description Language), an XML-based document that describes the web service and specifies its location, methods, data types, expected parameters and returned data structures. 

In this article we explain the recommended SOAP implementation and best practices and provide an example that demonstrates the steps for working with SOAP.

### Preparations

Due to the complexity of WSDL, it is recommended to use ready-made Java code or libraries. Note that this supportive code is dedicated per web service and is not  generic.

There are two options for integration with a SOAP service provider:

* The service provider supplies the JAR holding the relevant code. This usually contains the service's endpoint and authorization keys. The JAR is saved and used as instructed by the external JAR. For more information, click [here](/articles/31_external_resources/01_external_jars.md). 

* The service provider publishes the service's WSDL, either as a URL or a file, which the client learns. This scenario uses a **wsimport** JDK command line tool which discovers the WSDL and translates it into Java code form - the SOAP client code.

The following describes how a service is published using WSDL and discusses the preparation steps that enable implementing the code in Fabric functions. Note that these steps are not required when using a JAR.

#### 1. WSDL Discovery: Generating SOAP Client Code

The SOAP client code is generated via the JDK `wsimport` command which expects this syntax: `wsimport [options] <wsdl>`.  

The WSDL path's parameter can be a URL or a file saved in the local file system, according to the method used by the service provider to expose it. 

It is recommended to use the following command options:

* `-Xnocompile`, do not compile the generated Java files. This process is redundant since the build process is implemented by Fabric.
* `-keep`, keep the generated Java file.
* `-p`, package name. 

For more options about using this command, click [here](https://docs.oracle.com/javase/7/docs/technotes/tools/share/wsimport.html).

When running this command a new package containing the Interface and Service Java files is generated holding the Java methods that can be used in Fabric code, without the protocol or SOAP XML format. The file names are derived from the names in WSDL.

Note that the generated code uses the JAX-WS built-in JAVA library whereby an external JAR is not required.

#### 2. Copying the Files Generated in Step 1 into the Fabric Folder

1. Go to the Fabric Studio tree and create a dedicated **Category** folder in the Java section.
2. Right click and select **Open Folder**:

![image](images/open_java_folder.png)

3. Copy the files to the Windows Explorer. Do not copy the directory's hierarchy. The files are displayed in the Fabric Studio Tree under Java section but not under the **Category**.

#### 3. Update and Verify the Service URL

The WSDL contains the service address which is automatically populated in the generated Java files.

If the supplied WSDL does not contain the actual service address, open the service file, search for the URL value and then either verify or update it.

### Example

In our example, the **Calculator** Web Service provides basic add, subtract, multiply and divide mathematical operations:

The demo service server-side can be found [here](/articles/31_external_resources/SOAP_Server_Example.zip). It contains three Java files:
-  **CalculatorI**, an interface which contains the declarations according to the WSDL published.
-  **CalculatorImpl**, which implements the calculation.
-  **CalcPublisher**, the executable class that publishes the Web Service and exposes it in the localhost in port 10000.


1. **Generate the SOAP client Java Files**

   In this example the WSDL path is supplied as a URL endpoint (refers to a localhost service):

   `wsimport -Xnocompile -keep -p com.k2view.cdbms.usercode.lu.Customer.Utilities http://127.0.0.1:10000/calcServer?wsdl`

   To access the WSDL in this example, click [here](/articles/31_external_resources/calculator.wsdl).

   As can be shown in the `-p` package option in the command, this service is used in a specific LU. Although the command can located and used elsewhere, including in the project's shared area, the web service can be LU oriented and therefore it is recommended to use this option. In this example it is saved in the  **Utilities** functions category/directory. 

2. **Copy the Generated Files into the Fabric Folder**

   The Fabric Studio tree is illustrated below with yellow highlights.

   ![image](images/soap_java_generated_files_at_studio_tree.png)



3. **Update and Verify Service the URL**

   The generated code in the example refers to the localhost, which also runs the demo Web Service, and does not need to be updated.

4. **Use the Generated Code**

   The generated code can now be used to call the SOAP service. The available methods can be found in the generated interface file.

   In our example, the generated code contains the add, divide, subtract and multiply methods.

   The following code is an example of a utility function which gets the operation and 2 operand numbers as input parameters. Its output is the calculation result:

   ```java
   CalculatorImplService service = new CalculatorImplService();
   CalculatorI calc = service.getCalculatorImplPort();
   
   int result = 0;
   if (operation.equals("add")) {
   	result = calc.add(operand1, operand2);
   } else if (operation.equals("subtract")) {
   	result = calc.subtract(operand1, operand2);
   } else if (operation.equals("multiply")) {
   	result = calc.multiply(operand1, operand2);
   } else if (operation.equals("divide")) {
   	result = calc.divide(operand1, operand2);
   }
   log.info("result: " + result);
   return result;
   
   ```

   The first two lines initiate the generated code.

   
   
  The following is an example of how a LuFucntion Actor in Broadway uses this function:

<img src="images/LuFuncActor_soap.png" alt="LuFucntion Actor using soap function" />



[![Previous](/articles/images/Previous.png)](/articles/31_external_resources/03_invoke_http_rest_call_example.md)
