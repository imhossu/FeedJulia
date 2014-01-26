package com.hackrice14.feedjulia;

import java.util.ArrayList;

import com.hackrice14.feedjulia.brain.DecisionMaker;
import com.hackrice14.feedjulia.brain.SurveyResponse;
import com.hackrice14.feedjulia.places.RestaurantInfo;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class Survey extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_survey);
		
		final Intent intent = getIntent();
		final ArrayList<SurveyResponse> responses = new ArrayList<SurveyResponse>();
    	final int index[] = new int[1];
    	index[0] = 0;

		final Button another = (Button) findViewById(R.id.moreSurvey);
		another.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	responses.add(getResponse());
            	resetSpinners();
            }
        });
		
		final Button decision = (Button) findViewById(R.id.makeDecision);
		decision.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
				StrictMode.setThreadPolicy(policy);
            	DecisionMaker decision = new DecisionMaker();
            	//TODO: don't hardcode the lat and long of Rice
            	responses.add(getResponse());
            	SurveyResponse[] r = new SurveyResponse[responses.size()];
            	ArrayList<RestaurantInfo> restaurants = decision.makeDecision(responses.toArray(r), 29.7169, -95.4028); 
            	Intent place = new Intent(Survey.this, Place.class);
            	place.putExtra("restaurants", restaurants);
            	startActivity(place);
            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.survey, menu);
		return true;
	}
	
	private void resetSpinners() {
		
	}
	
	private SurveyResponse getResponse() {
		SurveyResponse current = new SurveyResponse();
		
		Spinner veg = (Spinner) findViewById(R.id.vegChoices);
		current.setAnswer("vegetarian", convertString(veg.getSelectedItem().toString()));
		veg.setSelection(1);
		
		Spinner spicy = (Spinner) findViewById(R.id.SpicyChoices);
		current.setAnswer("spicy", convertString(spicy.getSelectedItem().toString()));
		spicy.setSelection(1);
		
		Spinner light = (Spinner) findViewById(R.id.LightChoices);
		current.setAnswer("light", convertString(light.getSelectedItem().toString()));
		light.setSelection(1);
		
		Spinner fish = (Spinner) findViewById(R.id.FishChoices);
		current.setAnswer("fish", convertString(fish.getSelectedItem().toString()));
		fish.setSelection(1);
		
		Spinner meat = (Spinner) findViewById(R.id.MeatChoices);
		current.setAnswer("carnivorous", convertString(meat.getSelectedItem().toString()));
		meat.setSelection(1);
		
		Spinner soup = (Spinner) findViewById(R.id.SoupChoices);
		current.setAnswer("soup", convertString(soup.getSelectedItem().toString()));
		soup.setSelection(1);
		
		Spinner asian = (Spinner) findViewById(R.id.AsianChoices);
		current.setAnswer("asian", convertString(asian.getSelectedItem().toString()));
		asian.setSelection(1);
		
		Spinner warm = (Spinner) findViewById(R.id.WarmChoices);
		double warmVal = convertString(warm.getSelectedItem().toString());
		current.setAnswer("warm", warmVal);
		warm.setSelection(1);
		current.setAnswer("cold", Math.abs(warmVal - 1.00));
		
		Spinner price = (Spinner) findViewById(R.id.PriceChoices);
		current.setAnswer("price", price.getSelectedItem().toString().length());
		price.setSelection(0);
		
		return current;
	}

	private double convertString(String choice) {
		if (choice.equals("No")) {
			return -1;
		} else if (choice.equals("Neutral")) {
			return 0;
		} else {
			return 1;
		}
	}

}
