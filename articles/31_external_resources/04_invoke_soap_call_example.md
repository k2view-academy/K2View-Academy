# Example of Invoking SOAP Call

### Introduction

SOAP is XML based messaging protocol, used for web services. While many services these days moved to work with REST with JSON, some enterprise systems, especially at the Billing and CRM arena, are still using it . In addition, few common services are still working with SOAP such as SAML.

SOAP handling starts with analyzing the WSDL, stands for Web Services Description Language. It is an XML based document that describes the web service, specifying the location of the service, the methods, data types, what parameters it expects, and what data structures it returns. 

Because this is  a complex XML it is recommended to use ready made wrapping code libraries. More over, in order to reduce hassles, JDK provides a command line tool called `wsimport` for this discovery step, "translating" this descriptive XML to Java code.

In this article we will go thru an example demonstrating end-to-end SOAP usage, along all steps.

### End To End Example

In our example, we are going to use a Calculator service which provide base math operations - add, subtract, multiple and divide.

Note: In this example we will use the JAX-WS built in JAVA library. There is no to use an external JAR.

1. **Generate the SOAP client Java files**

   Use the `wsimport` command which gets as input the WSDL file location. Open a command line (CMD) and run the command using its expected syntax: `wsimport [options] <wsdl>` . The WSDL path can be a URL or a file at your computer file system, depended on how the service provider exposed it to you. In the below example, the WSDL path is supplied as a URL endpoint (refers to a localhost service):

   `wsimport -Xnocompile -keep -p com.k2view.cdbms.usercode.lu.Customer.Utilities http://127.0.0.1:10000/calcServer?wsdl`

   The command options which are used here and recommended:

   * `-Xnocompile`: indicate to not compile generated Java files (the build is done at Fabric deployment process).
   * `-keep`: keep the generated Java file
   * `-p`: package name. In our example we are going to use and place it at specific LU (*Customer*) at *Utilities* functions category/directory. For your purpose choose the right location. Although our example here is generic, it is assumed that in many cases the web service is LU oriented.

   For more options, available for this command, please see [here](https://docs.oracle.com/javase/7/docs/technotes/tools/share/wsimport.html).

   The WSDL which is used at this example can be also found [here](/articles/31_external_resources/calculator.wsdl).

   

   As result of running this command a new package will be created, containing 2 files, one of them is an interface. These files contain java methods that can be used smoothly, in our code, without being aware of the protocol or format, SOAP XML in this case. The file names are derived from the names in WSDL.

   

   **Note**: in some cases, the SOAP service provider supplies the JAR that contains similar files. in such case, this step is redundant.

   

2. **Copy the Generated File**

   in this step we copy the generated files, from step 1, to the required location at Fabric.  

   * Find the right Java area at Fabric Studio tree.  You can create a dedicated *category* for this purpose.
   * Right click on it will open a context menu with the "Open Folder" option, similar to the following:

![image](images/open_java_folder.png)

* 

  * Copy the generated files from step 1 to the opened Windows Explorer. Copy only the files **without** all generated directories hierarchy.

  * After copying the files they will be shown at the Fabric Studio Tree at the relevant Java area (yet, not under the *category*), as illustrated below (yellow highlighted)

    ![image](images/soap_java_generated_files_at_studio_tree.png)

  

  **Note**: in case the SOAP service provider supplies a JAR for this integration, locate it as external jar. Follow the instruction [here](/articles/31_external_resources/01_external_jars.md).

  

3. **Update & Verify Service address URL**

   The WSDL contains the service address. This is also auto populated at the generated Java files.

   Sometimes the supplied WSDL does not contain the actual service address, and it shall be updated.

   Open the file - in our example - the "CalculatorImplService.java" and look for the URL value. Verify or update the value.

   

4. **Use the Generated Code**

   Now you can use the generated code for calling the SOAP service. the available methods can be found at the generated interface file.

   In our example, the generated code contains 4 methods - "add", "divide", "subtract", "multiply".

   Following is a code example of a utility function which use it. It get 3 input parameters - the operation and the 2 operand numbers. its output is the calculation result:

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

   The first 2 lines initiate the generated code.

   (To see and feel it in action, a LuFucntion Actor can be created at Broadway, which uses this function.)

