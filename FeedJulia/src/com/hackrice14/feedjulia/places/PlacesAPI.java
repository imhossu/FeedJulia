package com.hackrice14.feedjulia.places;

import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class PlacesAPI {
	private String key = "AIzaSyD53LDwRYVU9YVnNp4wdM24uV26Inn_e94";
	
	public ArrayList<RestaurantInfo> getPlaces(double lat, double lon, String[] keywords) {
		String url = buildURL(lat, lon, keywords);
		String response = getResponse(url);

		ArrayList<RestaurantInfo> restResults = new ArrayList<RestaurantInfo>();
		
		try {
			
			JSONObject json = new JSONObject(response);
			JSONArray results = json.getJSONArray("results");
			for(int i = 0; i < results.length(); i++) {
				RestaurantInfo info = new RestaurantInfo(results.getJSONObject(i), key);
				restResults.add(info);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return restResults;
	}

	private String getResponse(String url) {
		try {
			System.out.println(url);
			HttpClient client = new DefaultHttpClient();
			HttpGet get = new HttpGet(url);

			HttpResponse response = client.execute(get);
			HttpEntity entity = response.getEntity();
			return EntityUtils.toString(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

	private String buildURL(double lat, double lon, String[] keywords) {
		// https://maps.googleapis.com/maps/api/place/nearbysearch/xml?key=ABCDE&location=29.76429,-95.38370&radius=10000&sensor=true&types=restaurant&keyword=vegetarian,burger

		//https://maps.googleapis.com/maps/api/place/nearbysearch/json?key=AIzaSyCo-i6xMhpk_c4MTPBQwohSxUugKZosl_A&location=29.76429,-95.38370&radius=10000&sensor=true&types=restaurant&keyword=vegetarian
		
		//if(true)
		//	return "https://maps.googleapis.com/maps/api/place/nearbysearch/json?key=AIzaSyD53LDwRYVU9YVnNp4wdM24uV26Inn_e94&location=29.76429,-95.38370&radius=10000&sensor=true&types=restaurant&keyword=vegetarian";
		String baseUrl = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?";

		String result = baseUrl;

		result += "key=" + key;
		result += "&location=" + lat + "," + lon;
		result += "&radius=10000"; // distance (in meters) to search
		result += "&sensor=true";
		result += "&types=restaurant";

		if (keywords.length > 0) {
			result += "&keyword=" + keywords[0];
			for (int i = 1; i < keywords.length; i++) {
				result += "," + keywords[i];
			}
		}

		return result;
	}
}
