# Create Document DB
Note ensure that you have registered your account to utilize CosmosDB
```
az provider register --namespace Microsoft.DocumentDB
```
Here is your command to create the service
```
cf create-service azure-cosmosdb standard bootdocdb -c ./azure-config/broker-documentdb-config.json
```

# Create Storage Service DB
```
cf create-service azure-storage standard bootstorage -c ./azure-config/broker-storage-config.json
```

# Test the app
curl --data "cartId=101" springboot-azure-recommendations.apps.azure.winterfell.live/recommendations

# See the logs
- Azure portal
- Click Storage Accounts
- Click pcfazureservicebrokerapplogs
- Click Blob Service -> Containers
- Click logs  

# See the database entry
- Azure portal
- Click Azure Cosmos DB
- Click random-name
- Click Collections -> Document Explorer
- Click Open Data Explorer
- Expand Database -> Items -> Documents
- Click on Id  

# Clean up