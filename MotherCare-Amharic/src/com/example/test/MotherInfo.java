package com.example.test;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MotherInfo extends ListActivity{

	/*
	 * Define the shared reference to retrieve the selected
	 * language,and store it in the local string file of language
	 */
	SharedPreferences shared;
	String langauge;
	SOH soh;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		shared=getSharedPreferences("LANGUAGE", 0);
		langauge=shared.getString("LANGUAGE_VALUE", "NO_LANGUAGE");
		if(langauge.equals("Amharic"))
		{
			setContentView(R.layout.activity_main_a);
		}
		else if(langauge.equals("English"))
		{
			setContentView(R.layout.activity_main);
		}
		soh=new SOH(this);
		soh.open();
		MotherInformation();
		//setContentView(R.layout.mother_info);
	}
	
	@SuppressWarnings("deprecation")
	private void MotherInformation()
	{
		Cursor cursor=soh.fetchMotherInfo();
		startManagingCursor(cursor);
		
		 // Create an array to specify the fields we want to display in the list (only TITLE)
        
        String[] from = new String[]{SOH.TABLE_DATE_NAME_COLUMN,SOH.TABLE_DATE_AGE,SOH.TABLE_DATE_PHONE,SOH.TABLE_DATE_WEIGHT,SOH.TABLE_DATE_DATE};
        if(langauge.equals("Amharic"))
		{
		
        // and an array of the fields we want to bind those fields to (in this case just text1)
        int[] to = new int[]{R.id.text_name_a,R.id.text_age_a,R.id.text_telephone_a,R.id.text_weight_a,R.id.text_pregnancy_date_a};
        
        // Now create a simple cursor adapter and set it to display
        SimpleCursorAdapter notes = 
	            new SimpleCursorAdapter(this, R.layout.mother_info_a, cursor, from, to);
	        setListAdapter(notes);
		}
        else if(langauge.equals("Amharic"))
		{
    		
            // and an array of the fields we want to bind those fields to (in this case just text1)
            int[] to = new int[]{R.id.text_name,R.id.text_age,R.id.text_telephone,R.id.text_weight,R.id.text_pregnancy_date};
            
            // Now create a simple cursor adapter and set it to display
            SimpleCursorAdapter notes = 
    	            new SimpleCursorAdapter(this, R.layout.mother_info, cursor, from, to);
    	        setListAdapter(notes);
    		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflator=getMenuInflater();
		inflator.inflate(R.menu.motherinfo_backtohome, menu);
	    
		super.onCreateOptionsMenu(menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		switch(item.getItemId())
		{
		case R.id.motherinfo_back:
			Intent intent=new Intent(this, Home.class);
			startActivity(intent);
			 break;
		}
		
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent intent=new Intent(this, Home.class);
		startActivity(intent);
	}
	
	

	
}
