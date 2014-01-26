package com.hackrice14.feedjulia.brain;

import java.util.ArrayList;
import java.util.HashMap;

import com.hackrice14.feedjulia.places.PlacesAPI;
import com.hackrice14.feedjulia.places.RestaurantInfo;

public class DecisionMaker {
	public ArrayList<RestaurantInfo> makeDecision(SurveyResponse[] responses, double lat, double lon) {
		QuestionWeight[] questions = QuestionWeight.questions;
		
		HashMap<String, Double> record = new HashMap<String, Double>();
		for(SurveyResponse person : responses) {
			for(QuestionWeight question : questions) {
				question.getWeightsFromResponse(person, record);
			}
		}
		
		double top = -1000;
		double next = -1000;
		double third = -1000;
		
		String best = "";
		String nextBest = "";
		String thirdBest = "";
		for(String keyword : record.keySet()) {
			double weight = record.get(keyword);
			if(weight > top) {
				thirdBest = nextBest;
				nextBest = best;
				best = keyword;
				
				third = next;
				next = top;
				top = weight;
			} else if(weight > next) {
				thirdBest = nextBest;
				nextBest = keyword;
				
				third = next;
				next = weight;
			} else if(weight > third) {
				thirdBest = keyword;
				third = weight;
			}
		}
		
		PlacesAPI lookup = new PlacesAPI();
		ArrayList<RestaurantInfo> topMatches = lookup.getPlaces(lat, lon, new String[]{best});
		ArrayList<RestaurantInfo> nextMatches = lookup.getPlaces(lat, lon, new String[]{nextBest});
		ArrayList<RestaurantInfo> thirdMatches = lookup.getPlaces(lat, lon, new String[]{thirdBest});
		
		ArrayList<RestaurantInfo> result = new ArrayList<RestaurantInfo>();
		for(int i = 0; i < topMatches.size() || i < nextMatches.size() || i < thirdMatches.size(); i++) {
			if(i < topMatches.size())
				result.add(topMatches.get(i));
			if(i < nextMatches.size())
				result.add(nextMatches.get(i));
			if(i < thirdMatches.size())
				result.add(thirdMatches.get(i));
		}
		return result;
	}
}
