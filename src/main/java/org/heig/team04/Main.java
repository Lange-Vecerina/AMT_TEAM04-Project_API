package org.heig.team04;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.*;
import org.apache.http.HttpHeaders;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.*;
import java.net.URISyntaxException;
import java.util.Arrays;


public class Main {


    public static void main(String[] args) throws IOException, URISyntaxException {
        runBucketAndImageExists();

    }

    static void runBucketAndImageExists() throws IOException {

        System.out.println("=== creating image ===");
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost reqCreateFromUrl = new HttpPost("http://localhost:8080/data-object?uri=amt.team04.diduno.education/tmp/testing.jpg");
        reqCreateFromUrl.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        String createBody = "{ \"url\": \"https://carwow-uk-wp-3.imgix.net/18015-MC20BluInfinito-scaled-e1666008987698.jpg\" }";
        StringEntity strEntity = new StringEntity(createBody);
        reqCreateFromUrl.setEntity(strEntity);

        CloseableHttpResponse response = httpClient.execute(reqCreateFromUrl);
        try {

            // Get HttpResponse Status
            //System.out.println(response.getProtocolVersion());              // HTTP/1.1
            //System.out.println(response.getStatusLine().getStatusCode());   // 200
            //System.out.println(response.getStatusLine().getReasonPhrase()); // OK
            System.out.println(response.getStatusLine().toString());        // HTTP/1.1 200 OK

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                // return it as a String
                String result = EntityUtils.toString(entity);
                System.out.println(result);
            }
        } finally {
            response.close();
        }


        System.out.println("=== getting link ===");
        HttpGet reqPublishBucketLink = new HttpGet("http://localhost:8080/data-object/link?uri=amt.team04.diduno.education/tmp/testing.jpg");
        reqPublishBucketLink.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");

        CloseableHttpResponse linkResponse = httpClient.execute(reqPublishBucketLink);

        try {

            // Get HttpResponse Status
            //System.out.println(linkResponse.getProtocolVersion());              // HTTP/1.1
            //System.out.println(linkResponse.getStatusLine().getStatusCode());   // 200
            //System.out.println(linkResponse.getStatusLine().getReasonPhrase()); // OK
            System.out.println(linkResponse.getStatusLine().toString());        // HTTP/1.1 200 OK

            HttpEntity entity = linkResponse.getEntity();
            if (entity != null) {
                // return it as a String
                String result = EntityUtils.toString(entity);
                System.out.println(result);
            }
        } finally {
            response.close();
        }


        System.out.println("=== Analyze ===");

        byte [] responseArray = new byte[0];
        HttpPost reqAnalyzeLink = new HttpPost("http://localhost:8081/label-detector/analyze");
        reqAnalyzeLink.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        String analyzeBody = "{ \"uri\": \"https://carwow-uk-wp-3.imgix.net/18015-MC20BluInfinito-scaled-e1666008987698.jpg\" }";
        StringEntity analyzeEntity = new StringEntity(analyzeBody);
        reqAnalyzeLink.setEntity(analyzeEntity);

        CloseableHttpResponse analyzeResponse = httpClient.execute(reqAnalyzeLink);
        try {

            // Get HttpResponse Status
            //System.out.println(analyzeResponse.getProtocolVersion());              // HTTP/1.1
            //System.out.println(analyzeResponse.getStatusLine().getStatusCode());   // 200
            //System.out.println(analyzeResponse.getStatusLine().getReasonPhrase()); // OK
            System.out.println(analyzeResponse.getStatusLine().toString());        // HTTP/1.1 200 OK

            HttpEntity entity = analyzeResponse.getEntity();
            if (entity != null) {
                // return it as a String
                String result = EntityUtils.toString(entity);
                System.out.println(result);
                responseArray = result.getBytes();
            }
        } finally {
            response.close();
        }

        System.out.println("=== publish response ===");

        HttpPost reqPostResult = new HttpPost("http://localhost:8080/data-object?uri=amt.team04.diduno.education/tmp/testingmain.json");
        reqPostResult.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        String reqPostBody = "{ \"content\": "+Arrays.toString(responseArray)+" }";
        StringEntity strPostResultEntity = new StringEntity(reqPostBody);
        reqPostResult.setEntity(strPostResultEntity);

        CloseableHttpResponse PostResultResponse = httpClient.execute(reqPostResult);
        try {

            // Get HttpResponse Status
            //System.out.println(PostResultResponse.getProtocolVersion());              // HTTP/1.1
            //System.out.println(PostResultResponse.getStatusLine().getStatusCode());   // 200
            //System.out.println(PostResultResponse.getStatusLine().getReasonPhrase()); // OK
            System.out.println(PostResultResponse.getStatusLine().toString());        // HTTP/1.1 200 OK

            HttpEntity entity = PostResultResponse.getEntity();
            if (entity != null) {
                // return it as a String
                String result = EntityUtils.toString(entity);
                System.out.println(result);
            }
        } finally {
            response.close();
        }

    }

    void runEverythingNewScenario(){

    }

    void runBucketExistScenario() {

    }


}



