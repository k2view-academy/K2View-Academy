# Invoking an HTTP REST Call

### Objective and Introduction

This example demonstrates how a Telco mobile carrier responds to customers complaining about bad service using a REST API to reveal the location (country, city, long and lat coordinates) of a customer via their current IP domain. Note that this use case has already been implemented in a Fabric project, whereby proactive messages are sent to customers. 

Let's assume that a feed updates Fabric with data about the carrier's regional problems and that this information is stored in a Fabric reference table. When customers contact the Call Center to complain, a match is made between their current location and the reference table. To do so, the carrier's CRM calls the Fabric Web Service whose response  indicates whether the customer is located in a known bad service area.     

The following example features:
-  A Web Service. For more information about creating Web Services in Fabric, click [here](/articles/15_web_services_and_graphit/03_create_a_web_service.md).
-  A REST API that calls the IP Geolocation service - https://ip-api.com/ - which responds in JSON format via a JSON parser provided in an external JAR. For more information, click [External Jars](/articles/31_external_resources/01_external_jars.md) usage. In this scenario we have omitted external JAR handling guidelines.



### Example 

#### Preparations

In this example we created a new **SERVICE_ISSUES** reference table and populated it with 2 rows to simulate the feed process.



<img src="images/service_issues.png" alt="image" style="zoom:90%;" /><img src="images/service_issues_data.png" alt="image" style="zoom:80%;" />

#### Implementation

1.  Download the org.json JAR file from  [org.json](https://mvnrepository.com/artifact/org.json/json) and save it in the Fabric JARs directory. For more information about this library, click [here](https://github.com/stleary/JSON-java). 

2. Add the following **import** statements to the Logic file and save it in Web Service > Translation directory/category ("k2_ws\Translation"):

   ```java
   import org.json.JSONObject;
   import org.json.JSONArray;
   
   import java.net.HttpURLConnection;
   import java.net.URL;
   ```

   The first 2 lines parse the JSON. The other rows perform the HTTP call via Java internal support and therefore an external JAR is not required. 

 The following is example displays the Web Service code which gets the **userIP** input parameter and returns a string with the status and information. 

   ```java
   log.info("wsTrnIpToLocationREST");
   
   String issueStatusDetails = "No issues were found at customer's region";
   String SQLREF="SELECT ISSUE_DETAILS from SERVICE_ISSUES where COUNTRY_CODE = ? and REGION = ? and HAS_ISSUE = 1";
   
   URL url = new URL("http://ip-api.com/json/" + userIP);
   HttpURLConnection con = (HttpURLConnection) url.openConnection();
   con.setRequestMethod("GET");
   int responseCode = con.getResponseCode();
   log.info("wsTrnIpToLocationREST - Response code for user domain IP " + userIP + ": " + responseCode);
   
   if (responseCode == 200) { 
   
   	BufferedReader in = new BufferedReader(
     	new InputStreamReader(con.getInputStream()));
   	String inputLine;
   	StringBuffer content = new StringBuffer();
   	while ((inputLine = in.readLine()) != null) {
       	content.append(inputLine);
   	}
   	
   	in.close();
   	JSONObject jsonResponse = new JSONObject(content.toString());
   	String countryCode = jsonResponse.getString("countryCode"); 
   	log.info("countryCode: " + countryCode);	
   	String region = jsonResponse.getString("region");
   	log.info("region: " + region);
   	//log.info("city: " + jsonResponse.getString("city"));
   	//log.info("lon: " + jsonResponse.getNumber("lon"));	
   	//log.info("lat: " + jsonResponse.getNumber("lat"));
   	
   	String issueStatusDB = fabric().fetch(SQLREF,countryCode,region).firstValue().toString();
   	log.info("status: " + issueStatusDB);
   	if ((issueStatusDB != null) && (!issueStatusDB.isEmpty())) {
   		issueStatusDetails = issueStatusDB;
   	}
   	
   } else {
   	log.info("The request call was failed");
   }
   
   con.disconnect();
   
   return issueStatusDetails;
   
   ```
   
Web Service steps:
   
Search for the **userIP** input parameter and call the REST API service to get information about it.
   * If a good response is returned, parse it and take the countryCode and the region.
   * Check for an open entry in SERVICE_ISSUES (*has_issue = 1*) for this country-code and region.
   * If an open issue is found, set it as the Web Service result string. 
   

   
The following displays how the the Web Service gets input IP = "24.48.0.1":
   
<img src="images/REST_examle_results.png" alt="image"  />
   

   
   ### Authentication & Authorization
   
Sometimes the REST API requires that authentication and authorization is sent as headers. In the "java.net" library used in this example, this can be implemented using the **setRequestProperty** method. For example, if the API provider supplies a username and passwords and works with base authentication, the following can be used:
   
   ```java
   String encoded = Base64.encode(username+":"+password);
   cons.setRequestProperty("Authorization", "Basic "+encoded);
   ```

​		There are alternative HTTPP client JAR, such as apache, which already have specific "setHeader" method.



[![Previous](/articles/images/Previous.png)](/articles/31_external_resources/02_invoke_remote_server_calls.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/31_external_resources/04_invoke_soap_call_example.md)
