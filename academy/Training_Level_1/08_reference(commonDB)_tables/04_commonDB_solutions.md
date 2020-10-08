![](/academy/Training_Level_1/03_fabric_basic_LU/images/Solution.png) 

### Reference Tables - Exercise 1

Step 1:

![](/academy/Training_Level_1/08_reference(commonDB)_tables/images/01_commonDB_refExercise.PNG) 



Step 2:

Command:

```fabric>select ref_USA_CoV19_cases_Oct2020.STATE, CASES_LAST_7, CASES_PER_100K from ref_USA_CoV19_cases_Oct2020 INNER JOIN ADDRESS ON ref_USA_CoV19_cases_Oct2020.STATE=ADDRESS.STATE;```

Response:
```
|STATE|CASES_LAST_7|CASES_PER_100K|
+-----+------------+--------------+
|NV   |3431        |2679          |

(1 rows)
```

Step 3:

Command:

```
set COMMON_LOCAL_TRX=true;
(1 row affected)
begin;
fabric>insert into ref_USA_CoV19_cases_Oct2020(STATE,TOTALCASES,CASES_LAST_7,CASES_PER_100K,FATALITIES_LAST_7,FATALITIES_PER_100K) VALUES ('GUAM','400','14','800','1','100');
commit;
```
  
Response:

```  
|STATE|TOTALCASES|CASES_LAST_7|CASES_PER_100K|FATALITIES_LAST_7|FATALITIES_PER_100K|
+-----+----------+------------+--------------+-----------------+-------------------+
|AK   |8074      |820         |1095          |5                |7                  |
|AL   |156698    |6040        |3206          |59               |52                 |
|AR   |85779     |5833        |2846          |126              |46                 |
|AZ   |219763    |3396        |3064          |106              |79                 |
|CA   |817277    |23237       |2066          |588              |40                 |
....
....
....
|WV   |16307     |1354        |903           |25               |19                 |
|WY   |6214      |794         |1076          |3                |9                  |
|USA  |7310625   |301539      |2209          |4940             |63                 |
|GUAM |400       |14          |800           |1                |100                |

(53 rows)

```

Response:
HI - (Hawaii)



[![Previous](/articles/images/Previous.png)](/academy/Training_Level_1/08_reference(commonDB)_tables/03_commonDB_exercises.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/academy/Training_Level_1/08_reference(commonDB)_tables/05_commonDB_quiz.md)

------

