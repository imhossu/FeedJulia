package com.hackrice14.feedjulia;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class StartScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
        
        ImageView pass = (ImageView)findViewById(R.id.passThePhone);
        pass.setOnClickListener(new OnClickListener() 
        {
            @Override
            public void onClick(View v) 
            {
            	Intent group = new Intent(StartScreen.this, GroupSettings.class);
            	group.putExtra("TYPE", "passPhone");
            	startActivity(group);
            	
            }
        });
        
        ImageView createGroup = (ImageView)findViewById(R.id.createGroup);
        createGroup.setOnClickListener(new OnClickListener() 
        {
            @Override
            public void onClick(View v) 
            {
            	Intent group = new Intent(StartScreen.this, GroupSettings.class);
            	group.putExtra("TYPE", "createGroup");
            	startActivity(group);
            	
            }
        });
        
        ImageView joinGroup = (ImageView)findViewById(R.id.joinGroup);
        joinGroup.setOnClickListener(new OnClickListener() 
        {
            @Override
            public void onClick(View v) 
            {
            	Intent group = new Intent(StartScreen.this, GroupSettings.class);
            	group.putExtra("TYPE", "joinGroup");
            	startActivity(group);
            	
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.start_screen, menu);
        return true;
    }
    
}
