package com.example.test;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class Language extends Activity implements OnItemSelectedListener {

	/*
	 * Define the Languages to be selected here as a value 
	 * of Strings
	 */
	private static final String Amharic = "Amharic";
	private static final String English = "English";
	/*
	 * Define the shared preferences here
	 * and the String language will be used to store the 
	 * selected values from the spinner drop down menu
	 */
	String language;
	SharedPreferences data_language;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select_language);
		Spinner sp=(Spinner)findViewById(R.id.spinner_language);
		sp.setOnItemSelectedListener(this);
		ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,
				R.array.list_language, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp.setAdapter(adapter);	

	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		/*
		 * Retrieve the selected item using
		 * parent.getItemAtPosition(pos)
		 * On selecting a spinner item
		 */
     
		language = parent.getItemAtPosition(position).toString();
 
        //Showing selected spinner item
       Toast.makeText(parent.getContext(), "Selected: " + language, Toast.LENGTH_LONG).show();        
 
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		// Another interface callback
		Toast.makeText(parent.getContext(), 
		       "nothing",
		        Toast.LENGTH_SHORT).show();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflator=getMenuInflater();
		inflator.inflate(R.menu.language_next, menu);
		super.onCreateOptionsMenu(menu);
		return true;
		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId())
		{
		case R.id.language_next:
			/*
			 * Depending on the Language selected store
			 * the String values of the selected language
			 * In the shared preferences of LANGAUGE with key
			 * LANGUAGE_VALUE
			 */
			if(language.equals(English))
			{
				data_language=getSharedPreferences("LANGUAGE",0);
				SharedPreferences.Editor edit=data_language.edit();
				edit.putString("LANGUAGE_VALUE", language);	
				edit.commit();
			}
			else if(language.equals(Amharic))
			{	
			
				data_language=getSharedPreferences("LANGUAGE",0);
				SharedPreferences.Editor edit=data_language.edit();
				edit.putString("LANGUAGE_VALUE", language);	
				edit.commit();
			}
			    
			   
				Intent intent=new Intent(this,Home.class);
				startActivity(intent);
			
		}
		return super.onOptionsItemSelected(item);
	}
	
	


}
