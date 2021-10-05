# Moving From DEV to QA/PROD

It is recommended to have separate ExagoBI installations for different environment types (DEV/QA/PROD), so that you can develop and test the reports without impacting the Production.

Then, when reports are ready for production,  you can move either one or several reports at a time. 



~~~
> cd /opt/apps/exago/bin
> sudo mono ImportExportStorageMgmt.exe -f ./import/dev-prod.json
~~~



**dev-prod.json** - a configuration file that describes the import / export structure (if doesn't exist, the file is created).

