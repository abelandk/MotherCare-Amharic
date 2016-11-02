package com.example.test;
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends Activity implements OnClickListener {

	String pregnancy_date;
	String Name;
	String Age;
	String Phone;
	String Weight;
	EditText edittext;
	private SOH soh;
	String key="registered";
   
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        
        SharedPreferences pref=getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        Boolean check=pref.getBoolean(key, false);
        if(!check)
        {
            Editor edit=pref.edit();
            edit.putBoolean(key,false);
            edit.commit();
        }
        else
        {
        	Intent intent_home=new Intent(this, Home.class);
        	startActivity(intent_home);
        }

        final Button picker=(Button)findViewById(R.id.register_button_pick_date);


        picker.setOnClickListener(this);

	}


          @Override
         public void onClick(View v) {
	        // TODO Auto-generated method stub
	       switch(v.getId())
         	{
				case R.id.register_button_pick_date:
					
					   DialogFragment newFragment = new DatePicker();
				    // newFragment.show(getSupportFragment(), "datePicker");
				    newFragment.show(getFragmentManager(), "datepicker");
				    break;
				    
					   
         	}
	       
          }
	
@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// TODO Auto-generated method stub
	        
	       MenuInflater inflator=getMenuInflater();
	       inflator.inflate(R.menu.main, menu);
	       super.onCreateOptionsMenu(menu);
			
			return true;
			
		}


@Override
public boolean onOptionsItemSelected(MenuItem item) {
	// TODO Auto-generated method stub
	switch(item.getItemId())
	{
	   case R.id.register_save:
		 EditText NAME=(EditText)findViewById(R.id.register_name);
		 EditText AGE=(EditText)findViewById(R.id.register_age);
		 EditText PHONE=(EditText)findViewById(R.id.register_phone);
		 EditText WEIGHT=(EditText)findViewById(R.id.register_weight);
		 Name=NAME.getText().toString();
		 Age=AGE.getText().toString();
		 Phone=PHONE.getText().toString();
		 Weight=WEIGHT.getText().toString();
		 //date_of_pregnancy=edittext.getText().toString();
		 soh=new SOH(this);
		 soh.open();
		 soh.setMotherInfo(Name, Age, Phone, Weight, pregnancy_date);
		 Intent intent = new Intent(this, Home.class);
		 startActivity(intent);

		 int duration = Toast.LENGTH_SHORT;
		  Toast toast=Toast.makeText(getApplicationContext(), "Mother Information saved:" + Name + " " + 
		                Age + " " + Phone + " " + Weight + " " + pregnancy_date + " ",duration);
		   toast.show();

		 break;
	}
	return super.onOptionsItemSelected(item);
}


public class DatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener{
	static final int dialog_id=1;
	int year,month,date;
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Calendar today=Calendar.getInstance();
		year=today.get(Calendar.YEAR);
		month=today.get(Calendar.MONTH);
		date=today.get(Calendar.DAY_OF_MONTH);

		return new DatePickerDialog(getActivity(), this, year, month, date);
	
	}

	@Override
	public void onDateSet(android.widget.DatePicker view, int year,
			int monthOfYear, int dayOfMonth) {
		// TODO Auto-generated method stub
		int Month=monthOfYear + 1;
		pregnancy_date=dayOfMonth + "/" + Month + "/" +year;
		edittext=(EditText)findViewById(R.id.register_pregnancydate);
		edittext.setText(pregnancy_date);
		//int duration=Toast.LENGTH_SHORT;
		//Toast toast=Toast.makeText(getApplicationContext(), "Pregnancy Date: " + pregnancy_date,duration);
		//toast.show();
		
	}
	public void showDatePickerDialog(View v) {
	    DialogFragment newFragment = new DatePicker();
	   // newFragment.show(getSupportFragment(), "datePicker");
	    newFragment.show(getFragmentManager(), "datepicker");
	}
   
	

}
}


	
