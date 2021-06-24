# Task Execution Monitoring API

### API URL

wsTaskMonitor/{taskId}

### HTTP Method

GET

### API Category

TDM_Tasks

### API Description

Returns the details of the current or last execution of the given task_id. If the task is pending, it will return only its status, else it will return the statistics of the entities handled by the task.

### API Input

- taskId

### API Input Example

```
http://localhost:3213/api/wsTaskMonitor/300
```

### API Output Examples

#### Running Task

```json
{
  "result": {
    "Task ID": "300",
    "Task Details": [
      {
        "Task Status": "Pending",
        "LU Name": "Billing"
      },
      {
        "Task Status": "Pending",
        "LU Name": "Collection"
      },
      {
        "Task Status": "Pending",
        "LU Name": "Orders"
      },
      {
        "Fabric Batch ID": "c03d06e2-3766-428c-8465-79e6487b2887",
        "Task Statistics": [
          {
            "Status": "",
            "Ent./sec (avg.)": "242.01",
            "Added": 0,
            "Ent./sec (pace)": "242.01",
            "Updated": 1000,
            "Failed": "0",
            "Duration": "00:00:04",
            "End time": "2021-06-20 09:08:19.487",
            "Name": "2373be01-f751-47ee-926b-c6e9312aab6e",
            "Succeeded": "1000",
            "Total": "--",
            "Level": "Node",
            "Remaining dur.": "00:00:00",
            "Remaining": "0",
            "Start time": "2021-06-20 09:08:15.355",
            "Unchanged": 0,
            "% Completed": "100"
          },
          {
            "Status": "",
            "Ent./sec (avg.)": "242.01",
            "Added": 0,
            "Ent./sec (pace)": "242.01",
            "Updated": 1000,
            "Failed": "0",
            "Duration": "00:00:04",
            "End time": "2021-06-20 09:08:19.487",
            "Name": "DC1",
            "Succeeded": "1000",
            "Total": "--",
            "Level": "DC",
            "Remaining dur.": "00:00:00",
            "Remaining": "0",
            "Start time": "2021-06-20 09:08:15.355",
            "Unchanged": 0,
            "% Completed": "100"
          },
          {
            "Status": "DONE",
            "Ent./sec (avg.)": "242.01",
            "Added": 0,
            "Ent./sec (pace)": "242.01",
            "Updated": 1000,
            "Failed": "0",
            "Duration": "00:00:04",
            "End time": "2021-06-20 09:08:19.487",
            "Name": "--",
            "Succeeded": "1000",
            "Total": "1000",
            "Level": "Cluster",
            "Remaining dur.": "00:00:00",
            "Remaining": "0",
            "Start time": "2021-06-20 09:08:15.355",
            "Unchanged": 0,
            "% Completed": "100"
          }
        ],
        "Task Status": "running",
        "LU Name": "Customer"
      }
    ],
    "Task Name": "testRefAndEntities",
    "Task Execution ID": 499,
    "Task Reference Statistics": {
      "Customer": {
        "minStartExecutionDate": "Sun Jun 20 09:08:15 UTC 2021",
        "maxEndExecutionDate": "Sun Jun 20 09:08:20 UTC 2021",
        "totNumOfTablesToProcess": 1,
        "numOfProcessedRefTables": 1,
        "numOfCopiedRefTables": 1,
        "numOfFailedRefTables": 0,
        "numOfProcessingRefTables": 0,
        "numberOfNotStartedRefTables": 0
      }
    }
  },
  "errorCode": "SUCCESS",
  "message": null
}
```



#### Completed Task Execution

```json
{
  "result": {
    "Task ID": "300",
    "Task Details": [
      {
        "Fabric Batch ID": "03a1e3da-8492-4106-9671-deaf75792e72",
        "Task Statistics": [
          {
            "Status": "",
            "Ent./sec (avg.)": "196.66",
            "Added": 0,
            "Ent./sec (pace)": "196.66",
            "Updated": 1000,
            "Failed": "0",
            "Duration": "00:00:05",
            "End time": "2021-06-20 09:10:10.799",
            "Name": "2373be01-f751-47ee-926b-c6e9312aab6e",
            "Succeeded": "1000",
            "Total": "--",
            "Level": "Node",
            "Remaining dur.": "00:00:00",
            "Remaining": "0",
            "Start time": "2021-06-20 09:10:05.714",
            "Unchanged": 0,
            "% Completed": "100"
          },
          {
            "Status": "",
            "Ent./sec (avg.)": "196.66",
            "Added": 0,
            "Ent./sec (pace)": "196.66",
            "Updated": 1000,
            "Failed": "0",
            "Duration": "00:00:05",
            "End time": "2021-06-20 09:10:10.799",
            "Name": "DC1",
            "Succeeded": "1000",
            "Total": "--",
            "Level": "DC",
            "Remaining dur.": "00:00:00",
            "Remaining": "0",
            "Start time": "2021-06-20 09:10:05.714",
            "Unchanged": 0,
            "% Completed": "100"
          },
          {
            "Status": "DONE",
            "Ent./sec (avg.)": "196.66",
            "Added": 0,
            "Ent./sec (pace)": "196.66",
            "Updated": 1000,
            "Failed": "0",
            "Duration": "00:00:05",
            "End time": "2021-06-20 09:10:10.799",
            "Name": "--",
            "Succeeded": "1000",
            "Total": "1000",
            "Level": "Cluster",
            "Remaining dur.": "00:00:00",
            "Remaining": "0",
            "Start time": "2021-06-20 09:10:05.714",
            "Unchanged": 0,
            "% Completed": "100"
          }
        ],
        "Task Status": "completed",
        "LU Name": "Customer"
      },
      {
        "Fabric Batch ID": "8224ab0a-b0ed-4f0e-9ef7-bcf5855d021e",
        "Task Statistics": [
          {
            "Status": "",
            "Ent./sec (avg.)": "265.5",
            "Added": 0,
            "Ent./sec (pace)": "265.5",
            "Updated": 2450,
            "Failed": "0",
            "Duration": "00:00:09",
            "End time": "2021-06-20 09:10:55.136",
            "Name": "2373be01-f751-47ee-926b-c6e9312aab6e",
            "Succeeded": "2450",
            "Total": "--",
            "Level": "Node",
            "Remaining dur.": "00:00:00",
            "Remaining": "0",
            "Start time": "2021-06-20 09:10:45.908",
            "Unchanged": 0,
            "% Completed": "100"
          },
          {
            "Status": "",
            "Ent./sec (avg.)": "265.5",
            "Added": 0,
            "Ent./sec (pace)": "265.5",
            "Updated": 2450,
            "Failed": "0",
            "Duration": "00:00:09",
            "End time": "2021-06-20 09:10:55.136",
            "Name": "DC1",
            "Succeeded": "2450",
            "Total": "--",
            "Level": "DC",
            "Remaining dur.": "00:00:00",
            "Remaining": "0",
            "Start time": "2021-06-20 09:10:45.908",
            "Unchanged": 0,
            "% Completed": "100"
          },
          {
            "Status": "DONE",
            "Ent./sec (avg.)": "265.5",
            "Added": 0,
            "Ent./sec (pace)": "265.5",
            "Updated": 2450,
            "Failed": "0",
            "Duration": "00:00:09",
            "End time": "2021-06-20 09:10:55.136",
            "Name": "--",
            "Succeeded": "2450",
            "Total": "2450",
            "Level": "Cluster",
            "Remaining dur.": "00:00:00",
            "Remaining": "0",
            "Start time": "2021-06-20 09:10:45.908",
            "Unchanged": 0,
            "% Completed": "100"
          }
        ],
        "Task Status": "completed",
        "LU Name": "Billing"
      },
      {
        "Fabric Batch ID": "aa292ea2-8b44-41ab-86a1-5e69dd90eca0",
        "Task Statistics": [
          {
            "Status": "",
            "Ent./sec (avg.)": "318.82",
            "Added": 0,
            "Ent./sec (pace)": "318.82",
            "Updated": 3705,
            "Failed": "0",
            "Duration": "00:00:12",
            "End time": "2021-06-20 09:10:57.492",
            "Name": "2373be01-f751-47ee-926b-c6e9312aab6e",
            "Succeeded": "3705",
            "Total": "--",
            "Level": "Node",
            "Remaining dur.": "00:00:00",
            "Remaining": "0",
            "Start time": "2021-06-20 09:10:45.871",
            "Unchanged": 0,
            "% Completed": "100"
          },
          {
            "Status": "",
            "Ent./sec (avg.)": "318.82",
            "Added": 0,
            "Ent./sec (pace)": "318.82",
            "Updated": 3705,
            "Failed": "0",
            "Duration": "00:00:12",
            "End time": "2021-06-20 09:10:57.492",
            "Name": "DC1",
            "Succeeded": "3705",
            "Total": "--",
            "Level": "DC",
            "Remaining dur.": "00:00:00",
            "Remaining": "0",
            "Start time": "2021-06-20 09:10:45.871",
            "Unchanged": 0,
            "% Completed": "100"
          },
          {
            "Status": "DONE",
            "Ent./sec (avg.)": "318.82",
            "Added": 0,
            "Ent./sec (pace)": "318.82",
            "Updated": 3705,
            "Failed": "0",
            "Duration": "00:00:12",
            "End time": "2021-06-20 09:10:57.492",
            "Name": "--",
            "Succeeded": "3705",
            "Total": "3705",
            "Level": "Cluster",
            "Remaining dur.": "00:00:00",
            "Remaining": "0",
            "Start time": "2021-06-20 09:10:45.871",
            "Unchanged": 0,
            "% Completed": "100"
          }
        ],
        "Task Status": "completed",
        "LU Name": "Orders"
      },
      {
        "Fabric Batch ID": "bfc283f2-ee9f-4ca1-bceb-774fc5671b37",
        "Task Statistics": [
          {
            "Status": "",
            "Ent./sec (avg.)": "199.09",
            "Added": 0,
            "Ent./sec (pace)": "199.09",
            "Updated": 480,
            "Failed": "0",
            "Duration": "00:00:02",
            "End time": "2021-06-20 09:10:48.284",
            "Name": "2373be01-f751-47ee-926b-c6e9312aab6e",
            "Succeeded": "480",
            "Total": "--",
            "Level": "Node",
            "Remaining dur.": "00:00:00",
            "Remaining": "0",
            "Start time": "2021-06-20 09:10:45.873",
            "Unchanged": 0,
            "% Completed": "100"
          },
          {
            "Status": "",
            "Ent./sec (avg.)": "199.09",
            "Added": 0,
            "Ent./sec (pace)": "199.09",
            "Updated": 480,
            "Failed": "0",
            "Duration": "00:00:02",
            "End time": "2021-06-20 09:10:48.284",
            "Name": "DC1",
            "Succeeded": "480",
            "Total": "--",
            "Level": "DC",
            "Remaining dur.": "00:00:00",
            "Remaining": "0",
            "Start time": "2021-06-20 09:10:45.873",
            "Unchanged": 0,
            "% Completed": "100"
          },
          {
            "Status": "DONE",
            "Ent./sec (avg.)": "199.09",
            "Added": 0,
            "Ent./sec (pace)": "199.09",
            "Updated": 480,
            "Failed": "0",
            "Duration": "00:00:02",
            "End time": "2021-06-20 09:10:48.284",
            "Name": "--",
            "Succeeded": "480",
            "Total": "480",
            "Level": "Cluster",
            "Remaining dur.": "00:00:00",
            "Remaining": "0",
            "Start time": "2021-06-20 09:10:45.873",
            "Unchanged": 0,
            "% Completed": "100"
          }
        ],
        "Task Status": "completed",
        "LU Name": "Collection"
      }
    ],
    "Task Name": "testRefAndEntities",
    "Task Execution ID": 500,
    "Task Reference Statistics": {
      "Customer": {
        "minStartExecutionDate": "Sun Jun 20 09:10:05 UTC 2021",
        "maxEndExecutionDate": "Sun Jun 20 09:10:12 UTC 2021",
        "totNumOfTablesToProcess": 1,
        "numOfProcessedRefTables": 1,
        "numOfCopiedRefTables": 1,
        "numOfFailedRefTables": 0,
        "numOfProcessingRefTables": 0,
        "numberOfNotStartedRefTables": 0
      }
    }
  },
  "errorCode": "SUCCESS",
  "message": null
}
```

  [![Previous](/articles/images/Previous.png)](01_tdm_basic_task_execution_flow.md)

