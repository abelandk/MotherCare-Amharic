package com.example.test;


import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MeasureContractionDetail extends ListActivity {

	SOH soh;
	Cursor cursor;
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		soh=new SOH(this);
		soh.open();
		cursor=soh.stop_watch_list();
		startManagingCursor(cursor);
		String [] from=new String []{SOH.TABLE_CONTARCTION_DURATION,SOH.TABLE_CONTARCTION_TIME_BETWEEN_CONTRACTION};
		int [] to=new int[]{R.id.contraction_deatil_duration_list_text,R.id.contraction_deatil_between_list_text};
		
		SimpleCursorAdapter adapter=new SimpleCursorAdapter(this, R.layout.measure_contraction_detail, cursor, from, to);
		setListAdapter(adapter);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflator=getMenuInflater();
		inflator.inflate(R.menu.share, menu);
		 super.onCreateOptionsMenu(menu);
		 return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId())
		{
		  case R.id.back_detail:
			   Intent intent=new Intent(this,MeasureContraction.class);
			   startActivity(intent);
			   return true;
		   case R.id.share:
			   send_message();
			   return true;
		   case R.id.email:
			   send_email();
			   return true;
		   
		        
		}
		return super.onOptionsItemSelected(item);
	}

    public void send_message()
    {
    	String message=message_to_send();
    	try{
    	Intent intent_message=new Intent(android.content.Intent.ACTION_VIEW);
    	intent_message.setType("vnd.android-dir/mms-sms");
    	intent_message.putExtra("sms_body", message);
    	startActivity(intent_message);}
    	catch (Exception e) {
			Toast.makeText(getApplicationContext(),
					"SMS faild, please try EMAIL or Download SMS APPLICATIONS!",
					Toast.LENGTH_LONG).show();
    		
    	}
    }
    
    public void send_email()
    {
    	String email=message_to_send();
    	try{
    	String subject="My Contraction Today";
    	Intent intent_email=new Intent(android.content.Intent.ACTION_SEND);
    	intent_email.setType("plain/text");
    	intent_email.putExtra(android.content.Intent.EXTRA_TEXT,email);
    	intent_email.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
    	startActivity(intent_email);}
    	catch (Exception e) {
			Toast.makeText(getApplicationContext(),
					"EMAIL faild, please try SMS or Download MAIL APPLICATIONS",
					Toast.LENGTH_LONG).show();
    		
    	}
    }
    
    public String message_to_send()
    {
    	int duration_key=cursor.getColumnIndex(SOH.TABLE_CONTARCTION_DURATION);
    	int length_between=cursor.getColumnIndex(SOH.TABLE_CONTARCTION_TIME_BETWEEN_CONTRACTION);
    	String message="";
    	String duration="Duration of a single contraction ";
    	String second=" seconds ";
    	String length="AND The Following contraction started in ";
    	
    	for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext())
    	{
    		message=message +duration + cursor.getString(duration_key) + second + length + cursor.getString(length_between) + second + '\n';
    	}
		return message;
	
    
    }
	
}
