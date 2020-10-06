![](/academy/Training_Level_1/03_fabric_basic_LU/images/Solution.png) 

### Reference Tables - Exercise 1

Step 1:

![](/academy/Training_Level_1/08_reference(commonDB)_tables/images/01_commonDB_refExercise.png) 



Step 2:

fabric>select ref_USA_CoV19_cases_Oct2020.STATE, CASES_LAST_7, CASES_PER_100K from ref_USA_CoV19_cases_Oct2020 INNER JOIN ADDRESS ON ref_USA_CoV19_cases_Oct2020.STATE=ADDRESS.STATE;
|STATE|CASES_LAST_7|CASES_PER_100K|
+-----+------------+--------------+
|NV   |3431        |2679          |

(1 rows)