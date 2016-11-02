package com.example.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

public class WebAPI extends Activity{

	String url="http://mothercare.comeze.com/android.php";
	String TAG_SUCESS="SUCESS";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_page);
		
		Web run=new Web();
		run.execute();
	}


	
	
	public class Web extends AsyncTask<Void, Void, Void>
	{
		String resultServer;

		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			int duration=Toast.LENGTH_SHORT;
			Toast toast=Toast.makeText(getApplicationContext(),"Your Call Is Being Passed To The Hospitals.....",duration);
			toast.show();
		
		}


		@Override
		protected Void doInBackground(Void... params) {
			List<NameValuePair> parm=new ArrayList<NameValuePair>();
			parm.add(new BasicNameValuePair("Name","a"));
			parm.add(new BasicNameValuePair("Telephone","phone"));
			parm.add(new BasicNameValuePair("Date","a"));
			parm.add(new BasicNameValuePair("Gps","a"));
			  resultServer  =makeHttpPost(url,"POST",parm);
			 try {
				JSONObject jobj=new JSONObject(resultServer);
				int sucess=jobj.getInt(TAG_SUCESS);
				if(sucess==1)
				{
					int duration=Toast.LENGTH_SHORT;
					Toast toast=Toast.makeText(getApplicationContext(),"Your Call In Passed To The Hospitals",duration);
					toast.show();
				}
				else
				{}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
		}
		

		private String makeHttpPost(String url, String string,
				List<NameValuePair> parm) {
			// TODO Auto-generated method stub
			StringBuilder sb=new StringBuilder();
			try {
				DefaultHttpClient client=new DefaultHttpClient();
				HttpPost httppost=new HttpPost(url);
				httppost.setEntity(new UrlEncodedFormEntity(parm));
				HttpResponse httpresponse=client.execute(httppost);
				HttpEntity entity=httpresponse.getEntity();
				InputStream input_stream = entity.getContent();
				BufferedReader reader=new BufferedReader(new InputStreamReader(input_stream));
				
				String line;
				while((line = reader.readLine()) !=null){
					sb.append(line);
			}
				input_stream.close();
				
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    catch (ClientProtocolException e) {
			e.printStackTrace();} 
			catch (IOException e) {
			e.printStackTrace();}
			
			return sb.toString();
		}




		
	}

	
}
