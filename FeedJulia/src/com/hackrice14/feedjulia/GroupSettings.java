package com.hackrice14.feedjulia;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class GroupSettings extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_group_settings);
		final TextView people = (TextView) findViewById(R.id.numPeople);
		final Button button = (Button) findViewById(R.id.finish);
		System.out.println("got the num ppl and button id");
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	System.out.println("clicked");
            	Intent group = new Intent(GroupSettings.this, Survey.class);
            	group.putExtra("NumPeople", people.getText().toString());
            	group.putExtra("FirstSurvey", true);
            	startActivity(group);
            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.group_settings, menu);
		return true;
	}

}
