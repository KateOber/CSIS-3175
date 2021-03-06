package com.example.week9project_003;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.content.Context.MODE_PRIVATE;

public class EMDatabase {
    myDbHelper myhelper;
    public EMDatabase(Context context)
    {
        myhelper = new myDbHelper(context);
    }


    //INSERTS INTO TABLES

    //INSERT INTO USER
    public long insertUSERData(String username,String email, String pass, int adminAccess)
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.USERNAME, username);
        contentValues.put(myDbHelper.EMAIL, email);
        contentValues.put(myDbHelper.MyPASSWORD, pass);
        contentValues.put(myDbHelper.ADMINACCESS, adminAccess);
        long id = dbb.insert(myDbHelper.TABLE_USER, null , contentValues);
//        SharedPreferences userInfo = getSharedPreferences("loggedUser", MODE_PRIVATE);
//        SharedPreferences.Editor editor = userInfo.edit(); //edit the preference
//        editor.putString("key1", username); // put values, keys have to be unique
//        editor.commit();
        return id;
    }

    //INSERT INTO INCOME
    public long insertINCOMEData(String username,double amount, String cat)
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.USERNAME, username);
        contentValues.put(myDbHelper.AMOUNT, amount);
        contentValues.put(myDbHelper.CATEGORY, cat);
        long id = dbb.insert(myDbHelper.TABLE_INCOME, null , contentValues);
        return id;
    }

    //INSERT INTO MISC
    public long insertMISCData(String username,double dAllowance, double savings)
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.USERNAME, username);
        contentValues.put(myDbHelper.DAILYA, dAllowance);
        contentValues.put(myDbHelper.SAVINGS, savings);
        long id = dbb.insert(myDbHelper.TABLE_MISC, null , contentValues);
        return id;
    }

    //INSERT INTO EXPENSE
    public long insertEXPENSEData(String username,String eName, double amount, int date, String cat, int recurring)
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.USERNAME, username);
        contentValues.put(myDbHelper.ENAME, eName);
        contentValues.put(myDbHelper.AMOUNT, amount);
        contentValues.put(myDbHelper.DATE, date);
        contentValues.put(myDbHelper.CATEGORY, cat);
        contentValues.put(myDbHelper.RECURRING, recurring);
        long id = dbb.insert(myDbHelper.TABLE_EXPENSE, null , contentValues);
        return id;
    }


    //GET DATA FROM TABLES

    //GET USER DATA
    public String getUSERData()
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID,myDbHelper.USERNAME,myDbHelper.EMAIL,myDbHelper.MyPASSWORD,myDbHelper.ADMINACCESS};
        Cursor cursor =db.query(myDbHelper.TABLE_USER,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            String username =cursor.getString(cursor.getColumnIndex(myDbHelper.USERNAME));
            String  email =cursor.getString(cursor.getColumnIndex(myDbHelper.EMAIL));
            String  password =cursor.getString(cursor.getColumnIndex(myDbHelper.MyPASSWORD));
            int  adminAccess =cursor.getInt(cursor.getColumnIndex(myDbHelper.ADMINACCESS));
            buffer.append(cid+ "   " + username + "   " + email +"   " + password +"   " + adminAccess +" \n");
        }
        return buffer.toString();
    }

    //GET INCOME DATA
    public String getINCOMEData()
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID,myDbHelper.USERNAME,myDbHelper.AMOUNT,myDbHelper.CATEGORY};
        Cursor cursor =db.query(myDbHelper.TABLE_INCOME,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            String username =cursor.getString(cursor.getColumnIndex(myDbHelper.USERNAME));
            double  amount =cursor.getDouble(cursor.getColumnIndex(myDbHelper.AMOUNT));
            String  category =cursor.getString(cursor.getColumnIndex(myDbHelper.CATEGORY));
            buffer.append(cid+ "   " + username + "   " + amount +"   " + category +" \n");
        }
        return buffer.toString();
    }

    //GET MISC DATA
    public String getMISCData()
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID,myDbHelper.USERNAME,myDbHelper.DAILYA,myDbHelper.SAVINGS};
        Cursor cursor =db.query(myDbHelper.TABLE_MISC,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            String username =cursor.getString(cursor.getColumnIndex(myDbHelper.USERNAME));
            double  dAllowance =cursor.getDouble(cursor.getColumnIndex(myDbHelper.DAILYA));
            double  savings =cursor.getDouble(cursor.getColumnIndex(myDbHelper.SAVINGS));
            buffer.append(cid+ "   " + username + "   " + dAllowance +"   " + savings +" \n");
        }
        return buffer.toString();
    }

    //GET MISC DATA
    public String getEXPENSEData()
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID,myDbHelper.USERNAME,myDbHelper.ENAME,myDbHelper.AMOUNT,myDbHelper.DATE,myDbHelper.CATEGORY,myDbHelper.RECURRING};
        Cursor cursor =db.query(myDbHelper.TABLE_EXPENSE,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            String username =cursor.getString(cursor.getColumnIndex(myDbHelper.USERNAME));
            String ename =cursor.getString(cursor.getColumnIndex(myDbHelper.ENAME));
            double  amount =cursor.getDouble(cursor.getColumnIndex(myDbHelper.AMOUNT));
            int  date =cursor.getInt(cursor.getColumnIndex(myDbHelper.DATE));
            String category =cursor.getString(cursor.getColumnIndex(myDbHelper.CATEGORY));
            int  recurring =cursor.getInt(cursor.getColumnIndex(myDbHelper.RECURRING));
            buffer.append(cid+ "   " + username + "   " + ename +"   " + amount +"   " + date +"   " + category +"   " + recurring +" \n");
        }
        return buffer.toString();
    }


    //DELETE USER FROM DATABASE
    public  int deleteUSER(String username)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] whereArgs ={username};

        int count =db.delete(myDbHelper.TABLE_USER ,myDbHelper.USERNAME+" = ?",whereArgs);
        db.delete(myDbHelper.TABLE_INCOME ,myDbHelper.USERNAME+" = ?",whereArgs);
        return  count;
    }

    // CHANGE USERNAME
    public int updateUsername(String oldName , String newName)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.USERNAME,newName);
        String[] whereArgs= {oldName};
        int count =db.update(myDbHelper.TABLE_USER,contentValues, myDbHelper.USERNAME+" = ?",whereArgs );
        return count;
    }

    //CHANGE DAILY ALLOWANCE
    public int updateDAllowance(String username, double dAllowance)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.DAILYA,dAllowance);
        String[] whereArgs= {username};
        int count =db.update(myDbHelper.TABLE_MISC,contentValues, myDbHelper.USERNAME+" = ?",whereArgs );
        return count;
    }

    //CHANGE SAVINGS
    public int updateSavings(String username, double savings)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.DAILYA,savings);
        String[] whereArgs= {username};
        int count =db.update(myDbHelper.TABLE_MISC,contentValues, myDbHelper.USERNAME+" = ?",whereArgs );
        return count;
    }

    //CHANGE AMOUNT
    public int updateEDate(String username, String ename, double amount)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.AMOUNT,amount);
        String[] whereArgs= {username};
        int count =db.update(myDbHelper.TABLE_EXPENSE,contentValues, myDbHelper.USERNAME+" = ?",whereArgs );
        return count;
    }

    //CHANGE DATE
    public int updateEDate(String username, String ename, int date)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.DATE,date);
        String[] whereArgs= {username};
        int count =db.update(myDbHelper.TABLE_EXPENSE,contentValues, myDbHelper.USERNAME+" = ?",whereArgs );
        return count;
    }

    //CHANGE RECURRING
    public int updateERecurring(String username, String ename, int recurring)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.RECURRING,recurring);
        String[] whereArgs= {username};
        int count =db.update(myDbHelper.TABLE_EXPENSE,contentValues, myDbHelper.USERNAME+" = ?",whereArgs );
        return count;
    }


    static class myDbHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "EMDatabase";    // Database Name
        private static final int DATABASE_Version = 1;    // Database Version

        //USER TABLE
        private static final String TABLE_USER = "USER";   // USER table Name
        private static final String UID="_id";     // Column I (Primary Key)
        private static final String USERNAME = "UserName";    //Column II
        private static final String EMAIL= "Email";    // Column III
        private static final String MyPASSWORD= "Password";    // Column IV
        private static final String ADMINACCESS= "AdminAccess";    // Column V
        private static final String CREATE_USER_TABLE = "CREATE TABLE "+TABLE_USER+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+USERNAME+" VARCHAR(255) ,"+EMAIL+" VARCHAR(255) ,"+MyPASSWORD+" VARCHAR(255) ,"+ ADMINACCESS+" int);";
        private static final String DROP_USER_TABLE ="DROP TABLE IF EXISTS "+TABLE_USER;

        //INCOME TABLE
        private static final String TABLE_INCOME = "INCOME";   // MISC table Name
        private static final String AMOUNT= "Amount";    // Column III
        private static final String CATEGORY= "Category";    // Column IV
        private static final String CREATE_INCOME_TABLE = "CREATE TABLE "+TABLE_INCOME+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+USERNAME+" VARCHAR(255) ,"+AMOUNT+" DOUBLE ,"+ CATEGORY+" VARCHAR(225));";
        private static final String DROP_INCOME_TABLE ="DROP TABLE IF EXISTS "+TABLE_INCOME;

        //MISC TABLE
        private static final String TABLE_MISC = "MISC";   // MISC table Name
        private static final String DAILYA= "DailyAllowance";    // Column III
        private static final String SAVINGS= "Savings";    // Column IV
        private static final String CREATE_MISC_TABLE = "CREATE TABLE "+TABLE_MISC+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+USERNAME+" VARCHAR(255) ,"+DAILYA+" DOUBLE ,"+ SAVINGS+" DOUBLE);";
        private static final String DROP_MISC_TABLE ="DROP TABLE IF EXISTS "+TABLE_MISC;

        //EXPENSES TABLE
        private static final String TABLE_EXPENSE = "EXPENSE";   // EXPENSE table Name
        private static final String ENAME= "ExpenseName";    // Column III
        private static final String DATE= "ExpenseName";    // Column V
        private static final String RECURRING= "Recurring";    // Column VII
        private static final String CREATE_EXPENSE_TABLE = "CREATE TABLE "+TABLE_EXPENSE+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+USERNAME+" VARCHAR(255) ,"+ ENAME +" VARCHAR(255) ,"+ AMOUNT +" DOUBLE ,"+ DATE +" TEXT ,"+
                CATEGORY +" VARCHAR(255) ,"+ RECURRING +" INTEGER);";
        private static final String DROP_EXPENSE_TABLE ="DROP TABLE IF EXISTS "+TABLE_MISC;

        private Context context;
        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context=context;
        }

        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_USER_TABLE);
                db.execSQL(CREATE_INCOME_TABLE);
                db.execSQL(CREATE_MISC_TABLE);
                db.execSQL(CREATE_EXPENSE_TABLE);
            } catch (Exception e) {
                Message.message(context,""+e);
            }
        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Message.message(context,"OnUpgrade");
                db.execSQL(DROP_USER_TABLE);
                db.execSQL(DROP_INCOME_TABLE);
                db.execSQL(DROP_MISC_TABLE);
                db.execSQL(DROP_EXPENSE_TABLE);
                onCreate(db);
            }catch (Exception e) {
                Message.message(context,""+e);
            }
        }
    }
}