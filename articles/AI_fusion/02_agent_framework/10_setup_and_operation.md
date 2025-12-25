# Setup and Operation

In addition to implementation setup activities like extensions installs, there are setup activities that shall be done and verified on any deployment - Dev, QA and Production.

* Language models readiness and network accessibility is enbled

* Pipeline DB

* Assurance DB

* LUI snaps Storage

* When using a non managed or service vector DB, the below shall be verified too

  * Embedding model readiness and network accessibility is enbled

  * markitdown python lib is installed

  * DB schema is set for the vector DB

    If Fabric is used for this purpose, then config.ini shall be updated accordingly
