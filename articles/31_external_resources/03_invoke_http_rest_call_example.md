# Example of Invoking HTTP REST Call

### Objective and Introduction

In this example we demonstrates how a Telco mobile carrier can use customer's IP domain (assumed as rederived during sync) to reveal his current location (such as country, city, long&lat coordinates) . 

This can be useful for cases when customer calls to complain about bad service, where carrier knows that there are problems in some areas. Another example that it can be used is when customer is being abroad and carrier can suggest him best roaming packages.

For this purpose we will use REST call to one of the existing IP Geolocation services - https://ip-api.com/. This service provides several response format options and we will use the JSON response format, using a JSON parser provided in an external JAR. See here to learn about [External Jars](/articles/31_external_resources/01_external_jars.md) usage. 



### Example Implementation

* place "org.json" JAR at the fabric JARs directory (can be downloaded [here](https://mvnrepository.com/artifact/org.json/json). for more information about this library see [here](https://github.com/stleary/JSON-java))

* We will use this capability as enrichment function. Add to the Enrichment Logic file these import statements:

  ~~~java
  import org.json.JSONObject;  
  import org.json.JSONArray;
  ~~~

  ~~~java
  import java.net.HttpURLConnection;
  import java.net.URL;
  ~~~

  The first 2 lines aimed for JSON parsing while the later are for performing the HTTP call (Java internal support, thus no need an external JAR).

* Code example (**still without LU integration...**)

  ~~~java
  log.info("HTTP REST CALLS fucntion");
  URL url = new URL("http://ip-api.com/json/24.48.0.1");
  HttpURLConnection con = (HttpURLConnection) url.openConnection();
  con.setRequestMethod("GET");
  
  int responseCode = con.getResponseCode();
  log.info("Response code: " + responseCode);
  
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
  	log.info("countryCode: " + jsonResponse.getString("countryCode"));	
  	log.info("city: " + jsonResponse.getString("city"));
  	log.info("lon: " + jsonResponse.getNumber("lon"));	
  	log.info("lat: " + jsonResponse.getNumber("lat"));
  	
  	
  	
  } else {
  	log.info("The request call was failed");
  }
  	
  
  con.disconnect();
  ~~~

  \* Note: for simplicity we skipped null value validations and catching exceptions.







