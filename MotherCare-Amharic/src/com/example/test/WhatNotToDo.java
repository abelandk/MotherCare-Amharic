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

public class WhatNotToDo extends ListActivity{

	private SOH soh;
	SharedPreferences shared;
	String language;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		shared=getSharedPreferences("LANGUAGE",MODE_PRIVATE);
		language=shared.getString("LANGUAGE_VALUE", "NO_LANGUAGE");
		if(language.equals("Amharic"))
		{
			setContentView(R.layout.activity_main_a);

		}
		else if(language.equals("English"))
		{
			setContentView(R.layout.activity_main);

		}
        soh=new SOH(this);
        soh.open();
        WhatNotToDo_Info();
	}

	@SuppressWarnings("deprecation")
	public void WhatNotToDo_Info()
	{
		if(language.equals("Amharic"))
		{
		Cursor cursor=soh.fetchWhatNotToDo_A();
		startManagingCursor(cursor);
		String [] from=new String []{SOH.TABLE_DATE_ID,SOH.TODO_NOTDO_A};
		int [] to=new int[]{R.id.what_not_to_do_id_a,R.id.what_not_to_do_text_a};
		
		SimpleCursorAdapter adapter=new SimpleCursorAdapter(this, R.layout.what_not_to_do_a, cursor, from, to);
		setListAdapter(adapter);
		}
		else if(language.equals("English"))
		{
			Cursor cursor=soh.fetchWhatNotToDo();
			startManagingCursor(cursor);
			String [] from=new String []{SOH.TABLE_DATE_ID,SOH.TODO_NOTDO};
			int [] to=new int[]{R.id.what_not_to_do_id,R.id.what_not_to_do_text};
			
			SimpleCursorAdapter adapter=new SimpleCursorAdapter(this, R.layout.what_not_to_do, cursor, from, to);
			setListAdapter(adapter);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater=getMenuInflater();
		inflater.inflate(R.menu.motherinfo_backtohome, menu);
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
