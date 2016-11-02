package com.example.test;



import java.sql.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class SOH  {

	/*
	 * This class extends the Sqliteopenhelper to use the sqlite database
	 * Define the database parameters here
	 */
	String previouse_stop ;
	
	public final static String DB_NAME="mypregnancy.db";
	public final static int DB_VERSION=1;
	
	
	public final static String TABLE_TODO_NAME="name";
	public final static String TODO_ID="_id";
	public final static String TODO_DO="todo";
	public final static String TODO_NOTDO="notdo";
	public final static String TODO_EXPECT="expect";
	public final static String TODO_DO_A="todo_a";
	public final static String TODO_NOTDO_A="notdo_a";
	public final static String TODO_EXPECT_A="expect_a";
	public static String TODO_WEEK="week";
	
	
	public static String TABLE_DATE_NAME="pregnancy_date";
	public static String TABLE_DATE_ID="_id";
	public static String TABLE_DATE_NAME_COLUMN="name";
	public static String TABLE_DATE_AGE="age";
	public static String TABLE_DATE_PHONE="phone";
	public static String TABLE_DATE_WEIGHT="weight";
	public static String TABLE_DATE_DATE="date";
	
	public static String TABLE_CONTRACTION_NAME="contraction";
	public static String TABLE_CONTARCTION_ID="_id";
	public static String TABLE_CONTARCTION_START="start_time";
	public static String TABLE_CONTARCTION_STOP="stop_time";
	public static String TABLE_CONTARCTION_DURATION="duration";
	public static String TABLE_CONTARCTION_TIME_BETWEEN_CONTRACTION="time_between_contraction";
	
	
	
	private OpenHelper dbhelper;
	private SQLiteDatabase Db;
	

	/*
	 * This Strings are used to create the tables
	 * MOTHER INFORMATION,
	 * TODO
	 * AND CONTRACTION respectively
	 */
	public static final String CREATE_TABLE_MOTHER_INFO="create table pregnancy_date (_id integer primary key autoincrement,"+
	"name text NOT NULL,age integer NOT NULL,phone integer NOT NULL,weight integer,date text);";
	public static final  String CREATE_TABLE_TODO="create table name (_id integer primary key autoincrement," +  
		 "week integer NOT NULL,todo text,notdo text,expect text,todo_a text,notdo_a text,expect_a text);";
	
	public static final String CREATE_TABLE_CONTARCTION="create table contraction (_id integer primary key autoincrement,"+
	"start_time long,stop_time long,duration long,time_between_contraction long);";

	/*
	 * Below are the columns names where the fetch Methods use
	 * this are:
	 * WHAT TO DO COLUMNS,
	 * WHAT NOT TO DO COLUMNS,
	 * MOTHER INFORMATION COLUMNS, and
	 * WHAT TO EXPECT COLUMNS respectively
	 */
	
	private String[] COLUMN_TODO=
	    {
				SOH.TODO_ID,SOH.TODO_WEEK,SOH.TODO_DO
		};
	private String[] COLUMN_NOT_TODO=
	    {
				SOH.TODO_ID,SOH.TODO_WEEK,SOH.TODO_NOTDO
		};
	private String[] COLUMN_Mother_Info=
	    {
			SOH.TABLE_DATE_ID,SOH.TABLE_DATE_NAME_COLUMN,SOH.TABLE_DATE_AGE,SOH.TABLE_DATE_PHONE,SOH.TABLE_DATE_WEIGHT,SOH.TABLE_DATE_DATE
		};
	private static final String[] COLUMN_TO_EXPECT = 
		{
		SOH.TODO_ID,SOH.TODO_WEEK,SOH.TODO_EXPECT
       };
	private String[] COLUMN_TODO_A=
	    {
				SOH.TODO_ID,SOH.TODO_WEEK,SOH.TODO_DO_A
		};
	private String[] COLUMN_NOT_TODO_A=
	    {
				SOH.TODO_ID,SOH.TODO_WEEK,SOH.TODO_NOTDO_A
		};
	private static final String[] COLUMN_TO_EXPECT_A = 
		{
		SOH.TODO_ID,SOH.TODO_WEEK,SOH.TODO_EXPECT_A
       };
	private static final String[] COLUMN_CONTARCTION_STOP = 
		{
		SOH.TABLE_CONTARCTION_ID,SOH.TABLE_CONTARCTION_START,SOH.TABLE_CONTARCTION_STOP,SOH.TABLE_CONTARCTION_DURATION,SOH.TABLE_CONTARCTION_TIME_BETWEEN_CONTRACTION
       };
	private static final String[] COLUMN_CONTARCTION_ID = 
		{
		SOH.TABLE_CONTARCTION_ID
       };
	
	private Context mCtx;
	private static class OpenHelper extends SQLiteOpenHelper{
		
		private Data data=new Data();
		
		public OpenHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		// TODO Auto-generated constructor stub
	}

		/*
		 * (non-Javadoc)
		 * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
		 * 
		 * Here the create table queries will be executed when the database is first created
		 * that means when the Activity is called
		 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

		db.execSQL(CREATE_TABLE_TODO);
		db.execSQL(CREATE_TABLE_MOTHER_INFO);
		db.execSQL(CREATE_TABLE_CONTARCTION);
		createNote(db);
		
		
    }
	
	/*
	 * Here the create Note method will be used to insert data in to the TABLE_DO
	 * database.This datas are unique over the application and the datas are changed in the
	 * Data Activity
	 */
	   public long createNote(SQLiteDatabase db) {
	       ContentValues initialValues = new ContentValues();
	       initialValues.put(TODO_WEEK, 1);
	       initialValues.put(TODO_DO,data.week_one_do());
	       initialValues.put(TODO_NOTDO, data.week_one_not());
	       initialValues.put(TODO_EXPECT, data.week_one_expect());
	      initialValues.put(TODO_DO_A,data.week_one_do_a());
	       initialValues.put(TODO_NOTDO_A, data.week_one_not_a());
	       initialValues.put(TODO_EXPECT_A, data.week_one_expect_a());

	       return db.insert(TABLE_TODO_NAME, null, initialValues);
	   }
	   @Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("drop table if exists" + TABLE_TODO_NAME);
		db.execSQL("drop table if exists" + TABLE_DATE_NAME);
		db.execSQL("drop table if exists" + TABLE_CONTRACTION_NAME);
		onCreate(db);
	}
	}
	
	public SOH(Context ctx) {
        this.mCtx = ctx;
    }
	
	
	/*
	 * This Method is used to insert values to the DATE table
	 * Information about the mother is inserted in this method
	 */
	public void setMotherInfo(String Name,String Age,String Phone,String Weight,String Date)
	{
        
		ContentValues values=new ContentValues();
		values.put(TABLE_DATE_NAME_COLUMN, Name);
		values.put(TABLE_DATE_AGE, Age);
		values.put(TABLE_DATE_PHONE, Phone);
		values.put(TABLE_DATE_WEIGHT, Weight);
		values.put(TABLE_DATE_DATE, Date);
		
		Db.insert(TABLE_DATE_NAME, null, values);
		
		
	}
	
	public void setContractionTime(String formattedDate,String formattedDate_stop, long time_between_contraction, long duration2)

	{
		//,Date next_start)--------use the start of now as the next_start of the previous
		ContentValues values=new ContentValues();
		
		values.put(TABLE_CONTARCTION_START, formattedDate);
		values.put(TABLE_CONTARCTION_STOP, formattedDate_stop);
		values.put(TABLE_CONTARCTION_DURATION,duration2);
		values.put(TABLE_CONTARCTION_TIME_BETWEEN_CONTRACTION, time_between_contraction);

		Db.insert(TABLE_CONTRACTION_NAME, null, values);
		
	}
	public void setContractionTime_first(String formattedDate,String formattedDate_stop, long duration) {
		ContentValues values=new ContentValues();

		values.put(TABLE_CONTARCTION_START, formattedDate);
		values.put(TABLE_CONTARCTION_STOP, formattedDate_stop);
		values.put(TABLE_CONTARCTION_DURATION,duration);
		values.put(TABLE_CONTARCTION_TIME_BETWEEN_CONTRACTION,-1);

		Db.insert(TABLE_CONTRACTION_NAME, null, values);
		
	}
	public Cursor stop_watch_list()
	{
		String WhereClaue=TABLE_CONTARCTION_ID +"="+(last_id()-1);
		Cursor stop_previouse=Db.query(TABLE_CONTRACTION_NAME, COLUMN_CONTARCTION_STOP, null, null, null, null, null);
		return stop_previouse;
	}
	
	
	
  public int last_id()
  {
	  Cursor last_id=Db.query(TABLE_CONTRACTION_NAME, COLUMN_CONTARCTION_ID, null, null, null, null, null);
	 int column_index =last_id.getCount();//Returns the number of rows in the cursor
	  return  column_index;
	  
  }
    /**@return 
	 * This Method is used to open the database
	 * to read from it or insert into the database
	 */

   public SOH open() throws SQLException {
	        dbhelper = new OpenHelper(mCtx);
	        Db = dbhelper.getWritableDatabase();
	        return this;
	    }
   
   /*
    * This Method is used to fetch only the what to do values from the TODO TABLE
    * where the column names to fetch are declared as string values
    * of COLUMN_TODO
    */
  
   public Cursor fetchAllNotes() {

       return Db.query(TABLE_TODO_NAME,COLUMN_TODO, null, null, null, null, null);
   }
   public Cursor fetchAllNotes_a() {

       return Db.query(TABLE_TODO_NAME,COLUMN_TODO_A, null, null, null, null, null);
   }
   
   /*
    * This Method is used to fetch Information about the Mother
    * The columns to be fetched are specified in COLUMN_Mother_Info
    */

   public Cursor fetchMotherInfo() {

       return Db.query(TABLE_DATE_NAME,COLUMN_Mother_Info, null, null, null, null, null);
   }
   
   /**@return
    * This Method is used only to fetch the what not to do part from the 
    * TODO TABLE
    */
   
public Cursor fetchWhatNotToDo() {
	// TODO Auto-generated method stub
	
	 return Db.query(TABLE_TODO_NAME,COLUMN_NOT_TODO, null, null, null, null, null);
}
public Cursor fetchWhatNotToDo_A() {
	// TODO Auto-generated method stub
	
	 return Db.query(TABLE_TODO_NAME,COLUMN_NOT_TODO_A, null, null, null, null, null);
}
/**@return
 * This Method is used to fetch only what to expect columns from
 * the TODO table
 */

public Cursor fetchWhatToExpect() {
	// TODO Auto-generated method stub
	return Db.query(TABLE_TODO_NAME, COLUMN_TO_EXPECT, null, null, null, null, null);
}
public Cursor fetchWhatToExpect_A() {
	// TODO Auto-generated method stub
	return Db.query(TABLE_TODO_NAME, COLUMN_TO_EXPECT_A, null, null, null, null, null);
}



	
	
	
}
