package com.hackrice14.feedjulia.brain;

import java.util.Map;

public class QuestionWeight {
	private String question;
	private double yesWeight;
	private double noWeight;
	private String[] yesKeywords;

	private QuestionWeight(String question, double yesWeight, double noWeight,
			String... yesKeywords) {
		this.question = question;
		this.yesWeight = yesWeight;
		this.noWeight = noWeight;
		this.yesKeywords = yesKeywords;
	}

	public void getWeightsFromResponse(SurveyResponse response,
			Map<String, Double> record) {
		double answer = response.getAnswer(question);

		double weight;
		if (answer > 0) {
			weight = yesWeight * answer;
		} else {
			weight = noWeight * answer;
		}
		
		for (String keyword : yesKeywords) {
			if (!record.containsKey(keyword))
				record.put(keyword, 0d);
			record.put(keyword, record.get(keyword) + weight);
		}
	}

	public static QuestionWeight[] questions = {
			new QuestionWeight("vegetarian", 3, 1, "vegetarian", "indian",
					"italian"),
			new QuestionWeight("spicy", 1, 1, "mexican", "thai", "indian",
					"korean"),
			new QuestionWeight("light", 1, 1, "sushi", "deli", "cafe",
					"italian"),
			new QuestionWeight("fish", 1, 3, "seafood", "sushi"),
			new QuestionWeight("carnivorous", 1, 1, "german", "grill", "steak"),
			new QuestionWeight("soup", 1, 1, "italian", "pub", "deli", "pho"),
			new QuestionWeight("asian", 1, 1, "chinese", "japanese", "korean",
					"pho", "thai", "sushi"),
			new QuestionWeight("warm", 0.1, 0.5, "italian", "grill", "pizza",
					"mexican"),
			new QuestionWeight("cold", 0.5, 0.1, "sushi", "smoothie", "froyo") };
}
