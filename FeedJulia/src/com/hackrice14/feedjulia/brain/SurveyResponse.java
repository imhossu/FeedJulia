package com.hackrice14.feedjulia.brain;

import java.util.HashMap;

public class SurveyResponse {
	private HashMap<String, Double> answers;
	
	public SurveyResponse() {
		answers = new HashMap<String, Double>();
	}
	
	public void setAnswer(String question, double answer) {
		answers.put(question, answer);
	}
	
	public double getAnswer(String question) {
		return answers.get(question);
	}
}
