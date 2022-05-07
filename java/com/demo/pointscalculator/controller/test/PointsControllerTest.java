package com.demo.pointscalculator.controller.test;
import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
 
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class PointsControllerTest 
{   
    @LocalServerPort
    int randomServerPort;
     
    @Test
    public void testPointsControllerSuccess() throws URISyntaxException 
    {
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:"+randomServerPort+"/api/customer/1";
        URI uri = new URI(baseUrl);
         
        ResponseEntity<String> result = restTemplate.getForEntity(uri, null);
         
        //Verify request succeed
        Assertions.assertEquals(200, result.getStatusCodeValue());
    }
   
}
