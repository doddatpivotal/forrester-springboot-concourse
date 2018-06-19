package pivotal.demo;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;

@RestController
@SpringBootApplication
public class WebApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}
	
	//for blob storage
	@Autowired
	private CloudStorageAccount account;
	
	//for Cosmos DB
	@Autowired
	private RecommendationRepo repo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Bean
	public RestTemplate getRestTemplate() {
	    return new RestTemplate();
	}
	
	@RequestMapping(value="/recommendations", method=RequestMethod.POST)
	public String GetRecommendedProduct(@RequestParam("cartId") String cartId) throws URISyntaxException, StorageException, IOException {
		
		//create log file and upload to an Azure Storage Blob
		CloudBlobClient client = account.createCloudBlobClient();
		CloudBlobContainer container = client.getContainerReference("logs");
		container.createIfNotExists();
				
		String id = UUID.randomUUID().toString();
		String logId = String.format("log - %s.txt", id);
		CloudBlockBlob blob = container.getBlockBlobReference(logId);
		//create the log file and populate with cart id
		blob.uploadText(cartId);
		
		System.out.println("request log created");
		
		//add to DocumentDB collection (doesn't have to exist already)
		RecommendationItem r = new RecommendationItem();
		r.setRecId(id);
		r.setCartId(cartId);
		r.setRecommendedProduct("Y777-TF2001");
		r.setRecommendationDate(new Date().toString());
		repo.save(r);
		
		System.out.println("request db entry created");
		
		return "Forrester TEI study (Y777-TF2001)";	
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/chaining")
	public String MakeChainedCalls() {
		
        String s1 = restTemplate.getForObject("https://forrester-boot-downstream.cfapps.io/call1", String.class);
        String s2 = restTemplate.getForObject("https://forrester-boot-downstream.cfapps.io/call2", String.class);
		
		return "success";
	}
}
