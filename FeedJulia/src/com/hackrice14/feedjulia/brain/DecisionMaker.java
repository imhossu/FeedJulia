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
		String best = "";
		String nextBest = "";
		
		for(String keyword : record.keySet()) {
			double weight = record.get(keyword);
			if(weight > top) {
				nextBest = best;
				best = keyword;
				next = top;
				top = weight;
			} else if(weight > next) {
				next = weight;
				nextBest = keyword;
			}
		}
		
		PlacesAPI lookup = new PlacesAPI();
		return lookup.getPlaces(lat, lon, new String[]{best, nextBest});
	}
}
