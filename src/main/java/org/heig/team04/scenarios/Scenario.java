package org.heig.team04.scenarios;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * This class is used to split the different scenarios.
 *
 * @author Ivan Vecerina, Yanik Lange
 * @version 1.0
 *
 */
public class Scenario {

    /**
     * Scenario of root object and image already existing
     *
     * @throws IOException If an error occurs
     */
    public static void runRootObjectAndImageExists() throws IOException {
        System.out.println("=== creating image ===");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost reqCreateFromUrl = creatingPostRequest("http://localhost:8080/data-object?uri=amt.team04.diduno.education/tmp/testing.jpg",
                "{ \"url\": \"https://carwow-uk-wp-3.imgix.net/18015-MC20BluInfinito-scaled-e1666008987698.jpg\" }");

        CloseableHttpResponse response = httpClient.execute(reqCreateFromUrl);
        try {
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
            System.out.println(linkResponse.getStatusLine().toString());

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

        HttpPost reqAnalyzeLink = creatingPostRequest("http://localhost:8081/label-detector/analyze",
                "{ \"uri\": \"https://carwow-uk-wp-3.imgix.net/18015-MC20BluInfinito-scaled-e1666008987698.jpg\" }");
        CloseableHttpResponse analyzeResponse = httpClient.execute(reqAnalyzeLink);
        try {
            System.out.println(analyzeResponse.getStatusLine().toString());

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

        HttpPost reqPostResult = creatingPostRequest("http://localhost:8080/data-object?uri=amt.team04.diduno.education/tmp/result.json",
                "{ \"content\": "+ Arrays.toString(responseArray)+" }");

        CloseableHttpResponse PostResultResponse = httpClient.execute(reqPostResult);
        try {
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

    /**
     * Scenario of root object existing bot not the image
     *
     * @throws IOException If an error occurs
     */
    public static void runRootObjectExistNoImageScenario() throws IOException {
        System.out.println("=== creating image ===");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost reqCreateFromUrl = creatingPostRequest("http://localhost:8080/data-object?uri=amt.team04.diduno.education/tmp/new.jpg",
                "{ \"url\": \"https://m.media-amazon.com/images/I/71LP+MOviUL._AC_UL320_.jpg\" }");
        CloseableHttpResponse response = httpClient.execute(reqCreateFromUrl);
        try {
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
        HttpGet reqPublishBucketLink = new HttpGet("http://localhost:8080/data-object/link?uri=amt.team04.diduno.education/tmp/new.jpg");
        reqPublishBucketLink.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");

        CloseableHttpResponse linkResponse = httpClient.execute(reqPublishBucketLink);

        try {
            System.out.println(linkResponse.getStatusLine().toString());

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

        HttpPost reqAnalyzeLink = creatingPostRequest("http://localhost:8081/label-detector/analyze",
                "{ \"uri\": \"https://m.media-amazon.com/images/I/71LP+MOviUL._AC_UL320_.jpg\" }");
        CloseableHttpResponse analyzeResponse = httpClient.execute(reqAnalyzeLink);
        try {
            System.out.println(analyzeResponse.getStatusLine().toString());

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

        HttpPost reqPostResult = creatingPostRequest("http://localhost:8080/data-object?uri=amt.team04.diduno.education/tmp/new.json",
                "{ \"content\": "+Arrays.toString(responseArray)+" }");

        CloseableHttpResponse PostResultResponse = httpClient.execute(reqPostResult);
        try {
            System.out.println(PostResultResponse.getStatusLine().toString());

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



    /**
     * Scenario of root object and image not existing.
     * Not used as it is not recommended to create/delete buckets.
     *
     * @throws IOException If an error occurs
     */
    void runEverythingNewScenario() throws IOException {
        System.out.println("=== creating image ===");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost reqCreateFromUrl = creatingPostRequest("http://localhost:8080/data-object?uri=amt.team04.diduno.education/tmp/new.jpg",
                "{ \"url\": \"https://m.media-amazon.com/images/I/71LP+MOviUL._AC_UL320_.jpg\" }");
        CloseableHttpResponse response = httpClient.execute(reqCreateFromUrl);
        try {
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
        HttpGet reqPublishBucketLink = new HttpGet("http://localhost:8080/data-object/link?uri=amt.team04.diduno.education/tmp/new.jpg");
        reqPublishBucketLink.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");

        CloseableHttpResponse linkResponse = httpClient.execute(reqPublishBucketLink);

        try {
            System.out.println(linkResponse.getStatusLine().toString());

            HttpEntity entity = linkResponse.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity);
                System.out.println(result);
            }
        } finally {
            response.close();
        }

        System.out.println("=== Analyze ===");
        byte [] responseArray = new byte[0];

        HttpPost reqAnalyzeLink = creatingPostRequest("http://localhost:8081/label-detector/analyze",
                "{ \"uri\": \"https://m.media-amazon.com/images/I/71LP+MOviUL._AC_UL320_.jpg\" }");
        CloseableHttpResponse analyzeResponse = httpClient.execute(reqAnalyzeLink);
        try {
            System.out.println(analyzeResponse.getStatusLine().toString());

            HttpEntity entity = analyzeResponse.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity);
                System.out.println(result);
                responseArray = result.getBytes();
            }
        } finally {
            response.close();
        }

        System.out.println("=== publish response ===");

        HttpPost reqPostResult = creatingPostRequest("http://localhost:8080/data-object?uri=amt.team04.diduno.education/tmp/new.json",
                "{ \"content\": "+Arrays.toString(responseArray)+" }");

        CloseableHttpResponse PostResultResponse = httpClient.execute(reqPostResult);
        try {
            System.out.println(PostResultResponse.getStatusLine().toString());

            HttpEntity entity = PostResultResponse.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity);
                System.out.println(result);
            }
        } finally {
            response.close();
        }
    }

    /**
     * Utils fonction to create post requests
     * @param endpoint the endpoint we want to reach
     * @param body json string with the body content
     * @return a HTTP post request that can be executed afterward
     *
     * @throws UnsupportedEncodingException if the encoding of the body is not supported.
     */
    static HttpPost creatingPostRequest(String endpoint, String body) throws UnsupportedEncodingException {
        HttpPost postRequest = new HttpPost(endpoint);
        postRequest.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        String createBody = body;
        StringEntity strEntity = new StringEntity(createBody);
        postRequest.setEntity(strEntity);
        return postRequest;
    }
}
