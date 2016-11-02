package com.example.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class MeasureContraction extends Activity implements OnClickListener{
	
	private SOH soh;
	public long start=0L,timeInMilli=0L,duration=0L,time_now=0L,timeMilliSec=0L;
	Button start_button,stop_button;
	
	TextView start_text,stop_text,duration_text,stop_watch,start_static_text,stop_static_text,duration_static_text,
	start_list_text,stop_list_text,duration_list_text;
	public int time_milli,time_sec,time_min;
	
	Date time_start,time_stop,time_previouse_stop;
	long time_between_contraction;
	String formattedDate,formattedDate_stop;
	
	Handler myHandler=new Handler();
	
	


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.measure_contraction);
		start_button=(Button)findViewById(R.id.contraction_start_button);
		stop_button=(Button)findViewById(R.id.contraction_stop_button);
		
		start_text=(TextView)findViewById(R.id.contraction_start_text);
		stop_text=(TextView)findViewById(R.id.contraction_stop_text);
		duration_text=(TextView)findViewById(R.id.contraction_duration_text);
		stop_watch=(TextView)findViewById(R.id.contraction_stopwatch_text);
		start_static_text=(TextView)findViewById(R.id.contraction_start_static_text);
		stop_static_text=(TextView)findViewById(R.id.contraction_stop_static_text);
		duration_static_text=(TextView)findViewById(R.id.contraction_duration_static_text);


		
		
		start_button.setVisibility(View.VISIBLE);
		stop_button.setVisibility(View.GONE);
		start_text.setVisibility(View.GONE);
		stop_text.setVisibility(View.GONE);
		duration_text.setVisibility(View.GONE);
		start_static_text.setVisibility(View.GONE);
		stop_static_text.setVisibility(View.GONE);
		duration_static_text.setVisibility(View.GONE);
		
		start_button.setOnClickListener(this);
		stop_button.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.contraction_start_button:
			/*
			 * Hide the start button and show the others.
			 * Get the instance of the calendar class to get the current date and time as a DATE object
			 * and format that with a specific format which is HH:MM:SS
			 * Record the uptime of the computer to record the start time and each time recored the uptime 
			 * to get how long the stopwatch was running
			 */
			startButton(v);
			Calendar c=Calendar.getInstance();
			time_start = c.getTime();
			SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
			formattedDate = df.format(time_start);
			start=SystemClock.uptimeMillis();
            myHandler.postDelayed(myRunnable, 0);//causes the runnable('myrunnable') to srart at a specfic time t(t=0,in this case)
            timeMilliSec=SystemClock.uptimeMillis()-start;
			int timeSec=(int)(timeMilliSec/1000);
			timeSec=(timeSec%60);
			start_text.setText(formattedDate);
			break;
		case R.id.contraction_stop_button:
			/*
			 * Hide the stop button and show the others
			 */
			stopButton(v);
			Calendar cal=Calendar.getInstance();
			time_stop = cal.getTime();
		
			SimpleDateFormat df_stop = new SimpleDateFormat("HH:mm:ss");
			formattedDate_stop = df_stop.format(time_stop);
			duration=SystemClock.uptimeMillis()-start;
			int duration_second=(int)(duration/1000);
			int duration_minute=duration_second/60;
			duration_second=(int)(duration_second % 60);
			int duration_millisec=(int)(duration%1000);
			time_now=SystemClock.uptimeMillis()-start;
			int time_second=(int)(time_now/1000);
			time_second=(int)(time_second % 60);
			stop_text.setText(formattedDate_stop);
			duration_text.setText("" + String.format("%02d", duration_minute)  + ":" + String.format("%02d",duration_second) + ":"
					+ String.format("%03d", duration_millisec));
			if(time_previouse_stop!=null)
			{
				time_between_contraction=time_start.getTime()-time_previouse_stop.getTime();
				long Duration_msec=time_stop.getTime() - time_start.getTime();
				long Duration=Duration_msec/1000;
				int Duration_int=(int)Duration_msec%1000;
				if(Duration_int>=500)
				{
					Duration=Duration +1;
				}
				long time_between=time_between_contraction/1000;
				int time_between_int=(int)time_between_contraction%1000;
				if(time_between_int>=500)
				{
					time_between=time_between +1;
				}
				soh=new SOH(this);
				soh.open();
				soh.setContractionTime(formattedDate,formattedDate_stop,time_between,Duration);
			}
			else
			{
				long Duration_msec=time_stop.getTime() - time_start.getTime();
				long Duration=Duration_msec/1000;
				int Duration_int=(int)Duration_msec%1000;
				if(Duration_int>=500)
				{
					Duration=Duration +1;
				}
				soh=new SOH(this);
				soh.open();
				soh.setContractionTime_first(formattedDate,formattedDate_stop,Duration);
			}
			time_previouse_stop=time_stop;
		
			
			//Toast.makeText(this, "Selected: " + soh.previouse_stop, Toast.LENGTH_LONG).show();					
			myHandler.removeCallbacks(myRunnable);
			break;
			
		}
	}
	
	/**
	 * @return This will set the Visibility of the parameters
	 * per the specification of the Start Button
	 * Since the Time is Already Running the start button is not visible.
	 */
	public void startButton(View view)
	{
		start_button.setVisibility(View.GONE);
		stop_button.setVisibility(View.VISIBLE);
		start_text.setVisibility(View.VISIBLE);
		stop_text.setVisibility(View.VISIBLE);
		duration_text.setVisibility(View.VISIBLE);
		start_static_text.setVisibility(View.VISIBLE);
		stop_static_text.setVisibility(View.VISIBLE);
		duration_static_text.setVisibility(View.VISIBLE);

		
	}

	/**
	 * @return This will set the Visibility of the parameters
	 * per the specification of the Start Button
	 * Since the Time is Already Stopped the stop button is not visible.
	 */
	public void stopButton(View view)
	{
		stop_button.setVisibility(View.GONE);
		start_button.setVisibility(View.VISIBLE);
		start_button.setVisibility(View.VISIBLE);
		start_text.setVisibility(View.VISIBLE);
		stop_text.setVisibility(View.VISIBLE);
		duration_text.setVisibility(View.VISIBLE);
		start_static_text.setVisibility(View.VISIBLE);
		stop_static_text.setVisibility(View.VISIBLE);
		duration_static_text.setVisibility(View.VISIBLE);
		
	}
	
		public Runnable myRunnable=new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				timeInMilli=SystemClock.uptimeMillis()-start;
				time_sec=(int)(timeInMilli/1000);
				time_min=(time_sec/60);
				time_sec=(time_sec%60);
				int milliseconds=(int)(timeInMilli%1000);
				stop_watch.setText("" + time_min + ":" +String.format("%02d",time_sec) + ":"
						+String.format("%03d", milliseconds));
				myHandler.postDelayed(this, 0);
				
			}
		};

		
		
		

		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// TODO Auto-generated method stub
			MenuInflater inflator=getMenuInflater();
			inflator.inflate(R.menu.gotodetail, menu);
			super.onCreateOptionsMenu(menu);
			return true;
		}
		

		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
			// TODO Auto-generated method stub
			
			switch(item.getItemId())
			{
			   case R.id.go_to_detail:
				   Intent intent=new Intent(this,MeasureContractionDetail.class);
				   startActivity(intent);
				   return true;
			   case R.id.back_stopwatch:
				   Intent intent2=new Intent(this,Home.class);
				   startActivity(intent2);
				   return true;
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
