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

## Create Storage Service DB
```
cf create-service azure-storage general-purpose-storage-account bootstorage -c '{"resourceGroup":"recommendations-demo", "location":"eastus"}'
```

## Test the app
```
curl --data "cartId=101" springboot-azure-recommendations.<APPS_DOMAIN>/recommendations
```

### See the logs
- Azure portal
- Click Storage Accounts
- Click on random name
- Click Blob Service -> blobs
- Click logs  

### See the database entry
- Azure portal
- Click Azure Cosmos DB
- Click random-name
- Click Collections -> Document Explorer
- Click Open Data Explorer
- Expand Database -> Items -> Documents
- Click on Id  

# Clean up
```
cf delete springboot-azure-recommendations
cf delete-service bootstorage
cf delete-service bootdocdb
```
