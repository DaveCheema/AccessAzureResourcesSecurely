package com.example.springboot;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.azure.storage.blob.*;
import com.azure.identity.DefaultAzureCredentialBuilder;
import java.util.Locale;
import java.io.File;
import java.io.FileInputStream;


@RestController
public class HelloController {

	@RequestMapping("/")
	public String index() {
           //String endpoint = String.format(Locale.ROOT, "https://%s.blob.core.windows.net", "myteststorage1");
           String endpoint = String.format(Locale.ROOT, "https://%s.blob.core.windows.net", "dc1storageacct1");
           BlobServiceClient storageClient = new BlobServiceClientBuilder()
              .endpoint(endpoint)
              .credential(new DefaultAzureCredentialBuilder().build())
              .buildClient();           
           //BlobClient blobClient =  storageClient.getBlobContainerClient("quickstartblobs1")
           BlobClient blobClient =  storageClient.getBlobContainerClient("input")
                //.getBlobClient("curl-7.61.1-r3.apk");
                .getBlobClient("movies.csv");
           String localPath = "/home/site/wwwroot/data/";
           String downloadFileName = localPath+java.util.UUID.randomUUID()+"-downloaded.csv";
           File downloadedFile = new File(localPath + downloadFileName);
           blobClient.downloadToFile(downloadFileName);

           return "File "+downloadFileName+" downloaded successfully";
        }
}
//
// To clean, compile, turn off test project and create the .jar file, 
// run the following command: mvn clean package -DskipTests
//
