# Designing the Purge Process

The first step when designing the purge process, is the definition of the purge Logical Unit (LU). 

The logical unit is the entity that will form the basis for the purging process. When the process is executed, it will go over the database, retrieving the data of one of the instances of this logical unit at a time.