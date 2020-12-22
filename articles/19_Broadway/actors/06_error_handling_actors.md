# Error Handling Actors

Broadway has a built-in error handling mechanism that handles exceptions in a flow using **Error Handlers**. An error handler can be assigned to each flow's [Stage](19_broadway_flow_stages.md)  to catch its exceptions and to decide whether to continue or to stop the flow. An error handler is defined using a Broadway [Actor](03_broadway_actor.md). 

Any Actor or an [Inner flow](22_broadway_flow_inner_flows.md) can act as an error handler. However the most advanced error handling features can be achieved using an  **ErrorHandler** and **ErrorFields** Actors.









[![Previous](/articles/images/Previous.png)](05_db_actors.md)