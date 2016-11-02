package com.example.test;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Home extends Activity implements OnClickListener{

    /*
     * Define the shared preferences here
     * In this Activity the shared preferences is defined
     * to retrieve the saved values(Here to get which language
     * was selected in the Language Activity).
     * The selected language is used to save the retried 
     * Shared Preference value.
     */
	SharedPreferences langauge;
	String selected_langauge;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		langauge=getSharedPreferences("LANGUAGE", MODE_PRIVATE);
		selected_langauge = langauge.getString("LANGUAGE_VALUE", "NO_LANGUAGE");
		if(selected_langauge.equals("Amharic"))
			  
	     {
				setContentView(R.layout.home_page_a);
				final Button mother_info=(Button)findViewById(R.id.mother_info_a);
				final Button what_to_do=(Button)findViewById(R.id.what_to_do_a);
				final Button what_not_to_do=(Button)findViewById(R.id.what_not_to_do_a);
				final Button alarm=(Button)findViewById(R.id.alarm_a);
				final Button what_to_expect=(Button)findViewById(R.id.what_to_expect_a);
				final Button measure_contraction=(Button)findViewById(R.id.measure_contraction_a);
		
				mother_info.setOnClickListener(this);
				what_to_do.setOnClickListener(this);
				what_not_to_do.setOnClickListener(this);
				alarm.setOnClickListener(this);
				what_to_expect.setOnClickListener(this);
				measure_contraction.setOnClickListener(this);
	      }
	else if (selected_langauge.equals("English"))
		{
				setContentView(R.layout.home_page);
				final Button mother_info=(Button)findViewById(R.id.mother_info);
				final Button what_to_do=(Button)findViewById(R.id.what_to_do);
				final Button what_not_to_do=(Button)findViewById(R.id.what_not_to_do);
				final Button alarm=(Button)findViewById(R.id.alarm);
				final Button what_to_expect=(Button)findViewById(R.id.what_to_expect);
				final Button measure_contraction=(Button)findViewById(R.id.measure_contraction);
	
				mother_info.setOnClickListener(this);
				what_to_do.setOnClickListener(this);
				what_not_to_do.setOnClickListener(this);
				alarm.setOnClickListener(this);
				what_to_expect.setOnClickListener(this);
				measure_contraction.setOnClickListener(this);
		}


	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(selected_langauge.equals("English")){
		switch(v.getId())
		{
		
		  case R.id.mother_info: 
			Intent intent = new Intent(this, MotherInfo.class);
			 startActivity(intent);
			  break;
		  case R.id.what_to_do:
			  Intent intent_do=new Intent(this, WhatToDo.class);
			  startActivity(intent_do);
			  break;
		  case R.id.what_not_to_do:
			  Intent intent_notdo=new Intent(this, WhatNotToDo.class);
			  startActivity(intent_notdo);
			  break;
		  case R.id.what_to_expect:
			  Intent intent_expect=new Intent(this, WhatToExpect.class);
			  startActivity(intent_expect);
			  break;
		  case R.id.alarm:
			  Intent intent_alarm=new Intent(this, WebAPI.class);
			  startActivity(intent_alarm);
			  break;
		  case R.id.measure_contraction:
			  Intent intent_contraction=new Intent(this, MeasureContraction.class);
			  startActivity(intent_contraction);
			  
		}
	}
		else if(selected_langauge.equals("Amharic"))
		{
			switch(v.getId())
			{
			
			  case R.id.mother_info_a: 
				Intent intent = new Intent(this, MotherInfo.class);
				 startActivity(intent);
				  break;
			  case R.id.what_to_do_a:
				  Intent intent_do=new Intent(this, WhatToDo.class);
				  startActivity(intent_do);
				  break;
			  case R.id.what_not_to_do_a:
				  Intent intent_notdo=new Intent(this, WhatNotToDo.class);
				  startActivity(intent_notdo);
				  break;
			  case R.id.what_to_expect_a:
				  Intent intent_expect=new Intent(this, WhatToExpect.class);
				  startActivity(intent_expect);
				  break;
			  case R.id.alarm_a:
				  Intent intent_alarm=new Intent(this, WebAPI.class);
				  startActivity(intent_alarm);
				  break;
			  case R.id.measure_contraction_a:
				  Intent intent_contraction=new Intent(this, MeasureContraction.class);
				  startActivity(intent_contraction);
				  
			}
		}	
	}
	

}
