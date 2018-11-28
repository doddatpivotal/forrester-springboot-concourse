# PCF and Azure Services Demo
The following demo leverages the [Azure Open Service Broker for PCF](https://docs.pivotal.io/partners/azure-open-service-broker-pcf/index.html).
 
## Create Document DB
Note ensure that you have registered your account to utilize CosmosDB
```
az provider register --namespace Microsoft.DocumentDB
```
Here is your command to create the service
```
cf create-service azure-cosmosdb-sql sql-api bootdocdb -c '{"resourceGroup":"recommendations-demo", "location":"eastus"}'
```
>This takes a few minutes.

## Create Storage Service DB
```
cf create-service azure-storage general-purpose-storage-account bootstorage -c '{"resourceGroup":"recommendations-demo", "location":"eastus"}'
```
>This is pretty quick.

## Deploy
```
cf push
```
Retrieve the domain name for use with testing

## Test the app
```
curl --data "cartId=101" springboot-azure-recommendations.<APPS_DOMAIN>/recommendations
```
You should receive the following in response: ```Forrester TEI study (Y777-TF2001)```

### See the logs
- Azure portal
- Click Storage Accounts
- Click on random name
- Click Blob Service -> Blobs
- Click on logs within the list
- Then choose the view/edit the first entry.  You should see *101* in the contents.   

### See the database entry
- Azure portal
- Click Azure Cosmos DB
- Click random-name
- Click Collections -> Document Explorer
- Click Open Data Explorer
- Expand Database -> Items -> Documents
- Click on Id
- You should see something similar to...
```
{
    "recommendedProduct": "Y777-TF2001",
    "cartId": "101",
    "id": "ff625522-2021-43c9-a17a-22395dc8dac6",
    "recId": "d93e72e8-513e-45fa-8307-34bda9e2b741",
    "recommendationDate": "Wed Nov 28 15:37:10 UTC 2018",
    "_rid": "N0YRAM7m7WIBAAAAAAAAAA==",
    "_self": "dbs/N0YRAA==/colls/N0YRAM7m7WI=/docs/N0YRAM7m7WIBAAAAAAAAAA==/",
    "_etag": "\"d0009a5d-0000-0000-0000-5bfeb6260000\"",
    "_attachments": "attachments/",
    "_ts": 1543419430
}
```  

# Clean up
```
cf delete springboot-azure-recommendations
cf delete-service bootstorage
cf delete-service bootdocdb
```
