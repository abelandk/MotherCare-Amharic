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


public class WhatToDo extends ListActivity {

	SharedPreferences shared;
	String langauge;
	private SOH dbhelper;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shared=getSharedPreferences("LANGUAGE", MODE_PRIVATE);
        langauge=shared.getString("LANGUAGE_VALUE", "NO_LANGUAGE");
		if(langauge.equals("Amharic"))
		{
			setContentView(R.layout.activity_main_a);
		}
		else if(langauge.equals("English"))
		{
			setContentView(R.layout.activity_main);
		}
        dbhelper=new SOH(this);
        dbhelper.open();
        //dbhelper.createNote();
        add_note();
        registerForContextMenu(getListView());
        
    }
   @SuppressWarnings("deprecation")
private void add_note() {
	        
	        if(langauge.equals("Amharic"))
			{
	        	Cursor notesCursor_a = dbhelper.fetchAllNotes_a();
		        startManagingCursor(notesCursor_a);
	        	 // Create an array to specify the fields we want to display in the list (only TITLE)
		         
		        String[] from = new String[]{SOH.TODO_ID,SOH.TODO_DO_A};

		        // and an array of the fields we want to bind those fields to (in this case just text1)
		        int[] to = new int[]{R.id.contraction_start_static_text_a,R.id.contraction_stop_static_text_a};

		        // Now create a simple cursor adapter and set it to display
		        SimpleCursorAdapter notes = 
		            new SimpleCursorAdapter(this, R.layout.what_to_do_a, notesCursor_a, from, to);
		        setListAdapter(notes);
			}
	        else if(langauge.equals("English"))
			{
	        	Cursor notesCursor = dbhelper.fetchAllNotes();
		        startManagingCursor(notesCursor);
	        	// Create an array to specify the fields we want to display in the list (only TITLE)
		         
		        String[] from = new String[]{SOH.TODO_ID,SOH.TODO_DO};

		        // and an array of the fields we want to bind those fields to (in this case just text1)
		        int[] to = new int[]{R.id.contraction_start_static_text,R.id.contraction_stop_static_text};

		        // Now create a simple cursor adapter and set it to display
		        SimpleCursorAdapter notes = 
		            new SimpleCursorAdapter(this, R.layout.what_to_do, notesCursor, from, to);
		        setListAdapter(notes);
			}

	       
	    }
    
 

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
    	MenuInflater inflator=getMenuInflater();
        inflator.inflate(R.menu.motherinfo_backtohome, menu);
        //R.menu.main is the resource id for an xml layout resource to load
        super.onCreateOptionsMenu(menu);
        return true;
    }
    
    
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
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
