package self;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.RequestBuilder;
import org.asynchttpclient.Response;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import java.util.PriorityQueue;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.function.ToIntFunction;


public class JustLikeThat {
    public static void main(String[] args) throws IOException, InterruptedException {
        //getRackIdFromIMDS();
        //buildAWSRackAZMap();
        //readFileContents();
        String s = "abab";
        String t = s.substring(2, 4);
        System.out.println(t);

        int [] arr = {2, 4, 6, 7, 8, 10, 11};

        int target = 13;

        int index = testBinarySearch(arr, target);

        System.out.println("Index: " + index);

        Map<Integer, List<Integer>> map = new HashMap<>();
        var list = map.getOrDefault(1, new ArrayList<>());
        map.put(1, list);
        var reverseComparator = Comparator.comparingInt((Pair p) -> p.freq).reversed();
        PriorityQueue<Pair> queue = new PriorityQueue<>(reverseComparator);




    }

    private static int testBinarySearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length -1;

        while (low <= high) {
            int mid = low + (high - low) /2;
            int currVal = arr[mid];
            if (currVal == target) {
                System.out.println("Target found at index: " + mid);
                return mid;
            }

            if (currVal < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        System.out.println("Target not found");
        return -1;
    }

    static int getRackIdFromIMDS() {
        int rackId = -1;
        try (final AsyncHttpClient client = new DefaultAsyncHttpClient()) {
            RequestBuilder requestBuilder = new RequestBuilder();
            var request = requestBuilder.setUrl("http://www.google.com");
            Future<Response> responseFuture =  client.prepareRequest(request).execute();
            var response = responseFuture.get();
            System.out.println("Response from AWS IMDS : {}" +  response);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return rackId;
    }

    static Map<String, Integer> buildAWSRackAZMap() {
        Map routeAZMap = null;
        String rackAZMapping = "us-east-1a:1,us-east-1d:2,us-east-1f:3";
        if (routeAZMap == null && rackAZMapping != null) {
            routeAZMap = new HashMap<String,Integer>();
            String[] rackAZMappingData = rackAZMapping.split(",");
            for (String rackAZ : rackAZMappingData) {
                String[] parsedRackAZData = rackAZ.split(":");
                if (parsedRackAZData.length < 2) {
                    throw new RuntimeException("Bad Aerospike AWS AZ rackID configuration. Do not let server come up");
                }
                var azName = parsedRackAZData[0];
                var rackId = Integer.parseInt(parsedRackAZData[1]);
                routeAZMap.put(azName, rackId);
            }
        }
        return routeAZMap;
    }

    private static void readFileContents() throws IOException {
        var awsAzFilepath = Path.of("/Users/raviksha/Aerospike/ng-latest/sherpa-ng/qa/src/main/java/com/Test.java");
        var fileContent  = Files.readString(awsAzFilepath, StandardCharsets.UTF_8).trim();
        System.out.println(fileContent);
    }
}

class Pair {
    int freq;
    String name;
}
