![](/academy/Training_Level_1/03_fabric_basic_LU/images/Solution.png) 


### Solution - Additional Web Service

1.

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
					ludb("Customer", i_id).execute(sql,activity_id,case_id,case_date,case_type,status);
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

  

2. 
`Answer : POST`

3. 
```json
    Answer: i_id : String ; i_info: List<Map<String,String>>
   
   Example request body :{
     "i_id": "1",
     "i_info": [
       {
         "activity_id":"437", 
         "case_id":"225",
         "case_date":"2015-08-02 08:33:06",
         "case_type":"Network Issue",
         "status":"Open"
       
       }]}  
```


[![Previous](/articles/images/Previous.png)](/academy/Training_Level_1/06_web_services/07_additional_web_services_exercises.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/academy/Training_Level_1/06_web_services/09_web_services_quiz.md)

------
