# Response Codes and Supported Verbs

Now that you have created your first  Web Service, let's learn about the various reponse codes and their usage and the different Web Service commands, options and examples:

-  [Response Codes](/articles/15_web_services_and_graphit/11_response_codes.md)

-  [Supported Verbs GET](/articles/15_web_services_and_graphit/12_Supported_Verbs_Get.md)

-  [Supported Verbs POST](/articles/15_web_services_and_graphit/13_Supported_Verbs_Post.md)

-  [Supported Verbs PUT](/articles/15_web_services_and_graphit/14_Supported_Verbs_Put.md)

-  [Supported Verbs DELETE](/articles/15_web_services_and_graphit/15_Supported_Verbs_Delete.md)

In addition to the above supported verbs and response codes, the following information may provide more insight about the URL structure, authentication, encoding, etc.:

[REST API Additions](/articles/15_web_services_and_graphit/16_rest_api_additions.md)

![](/academy/Training_Level_1/03_fabric_basic_LU/images/information.png)Click the  [Web Services code examples](https://github.com/k2view-academy/K2View-Academy/blob/KB_DROP1_15_Web_Services_Merav/articles/15_web_services_and_graphit/06_web_services_code_examples.md) to find code examples worth reviewing. 

### ![](/academy/Training_Level_1/03_fabric_basic_LU/images/Exercise.png) Exercise  – Additional Web Service

Create a new Web Service that inserts entries into the CASES table with the following considerations: 
-    The Web Service can insert multiple subscribers for a given LUI. 
-    The Web Service response should include success or failure in the process.
-    The Web Service can perform basic input validations.

2.  Question: Which supported verbs will you use?

3.  Question: What are the input parameters and their data types?


### ![](/academy/Training_Level_1/03_fabric_basic_LU/images/Solution.png) Solution - Additional Web Service

```java
Map<String,String> result = new HashMap<>();
String name ="";
String message="";

// Parse the input map to extract the values for the delete condition 

//log.info(i_info.toString());
if ( i_info != null && i_info.size() >0 ){
	
	for (int i = 0; i < i_info.size(); i++) {
		
		//Map<String,String> m = i_info.get.(i);
		 if (i_info.get(i) != null){
			 
			String activity_id =i_info.get(i).get("activity_id");
			String case_id =i_info.get(i).get("case_id");
			String case_date =i_info.get(i).get("case_date");
			String case_type =i_info.get(i).get("case_type");
			String status =i_info.get(i).get("status");
			
		// Validate that input is not empty or wasn't exceed to number of object array
			
			    if (activity_id!=null && !activity_id.isEmpty()){
						
		// Insert into CASES table 	
					fabric().execute("Begin");
					String sql = "insert into cases (activity_id,case_id,case_date,case_type,status) values (?,?,?,?,?)";
					ludb("CustomerLU", i_id).execute(sql,activity_id,case_id,case_date,case_type,status);
					fabric().execute("Commit");
				}
			else{
//Missing values to process 
					i=i_info.size()+1;
				    name="Error";
				    message="MISSING VALUES"; 	
				    //result.put(name, message);
				   //return result;
				}
			
			}
	}
	name="Complete";
	message="INSERTED ALL";
} else {

// Input can not be processed, not data to parse
	
 name="Not Started";
 message="NO INPUT RECIEVED";
//message=Integer.toString(i_info.size());
}

   result.put(name, message);
   return result;
```

  

1. `Answer : POST`

2. ```json
    Answer: i_id : String ; i_info: List<Map<String,String>>
   
   Example request body :{
     "i_id": "1",
     "i_info": [
       {
         "activity_id":"437", 
         "case_id":"225",
         "case_date":"2015-08-02 08:33:06",
         case_type":"Network Issue",
         "status":"Open"
       
       }]}  
   ```

   


 [![Previous](/articles/images/Previous.png)](/academy/Training_Level_1/06_web_services/03_Invoking_a_web_service.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/academy/Training_Level_1/06_web_services/05_quiz.md)

