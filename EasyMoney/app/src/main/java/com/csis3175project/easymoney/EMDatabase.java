package com.csis3175project.easymoney;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.preference.PreferenceManager;

import static android.content.Context.MODE_PRIVATE;

public class EMDatabase {
    myDbHelper myhelper;
    public EMDatabase(Context context)
    {
        myhelper = new myDbHelper(context);

    }

    public String username(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String givenUsername = preferences.getString("key1","");
        return givenUsername;
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

        return id;
    }

    //INSERT INTO INCOME
    public long insertINCOMEData(String username,String IName,double amount, String cat, String date, int recurring)
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.USERNAME, username);
        contentValues.put(myDbHelper.INAME, IName);
        contentValues.put(myDbHelper.AMOUNT, amount);
        contentValues.put(myDbHelper.CATEGORY, cat);
        contentValues.put(myDbHelper.RECURRING, recurring);

        long id = dbb.insert(myDbHelper.TABLE_INCOME, null , contentValues);
        return id;
    }

    //INSERT INTO MISC
    public long insertMISCData(String username ,double dAllowance, double savings)
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
    public long insertEXPENSEData(String username, String eName, double amount, String date, String cat, int recurring, int repeat)
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.USERNAME, username);
        contentValues.put(myDbHelper.ENAME, eName);
        contentValues.put(myDbHelper.AMOUNT, amount);
        contentValues.put(myDbHelper.DATE, date);
        contentValues.put(myDbHelper.CATEGORY, cat);
        contentValues.put(myDbHelper.RECURRING, recurring);
        contentValues.put(myDbHelper.REPEAT, repeat);
        long id = dbb.insert(myDbHelper.TABLE_EXPENSE, null , contentValues);
        return id;
    }


    //GET DATA FROM TABLES

    //GET USER DATA
    public Cursor getUSERData()
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String query = "SELECT * FROM "+myhelper.TABLE_USER;
        Cursor USER = db.rawQuery(query, null);
        /*
        Cursor cursor =db.query(myDbHelper.TABLE_USER,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            String username =cursor.getString(cursor.getColumnIndex(myDbHelper.USERNAME));
            if(givenUsername.equals(username)){
                int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
                String  email =cursor.getString(cursor.getColumnIndex(myDbHelper.EMAIL));
                String  password =cursor.getString(cursor.getColumnIndex(myDbHelper.MyPASSWORD));
                int  adminAccess =cursor.getInt(cursor.getColumnIndex(myDbHelper.ADMINACCESS));
                buffer.append(cid+ "   " + username + "   " + email +"   " + password +"   " + adminAccess +" \n");
                if(choice == 1){
                    return email;
                }
                else if(choice == 2){
                    return password;
                }
                else if(choice == 3){
                    return String.valueOf(adminAccess);
                }
            }

        }*/
        return USER;
    }

    //get Email -- needs username
    public String getEmail(String username){
        String EMAIL = "email not found";
        Cursor c = getUSERData();
        if(c.getCount()>0){
            while(c.moveToNext()){
                if(username.equals(c.getString(1)))
                    EMAIL = c.getString(2);
            }
        }
        c.close();
        return EMAIL;
    }

    //get password -- used for login -- needs username
    public String getPassword(String username){
        String PASSWORD = "password not found";
        Cursor c = getUSERData();
        if(c.getCount()>0){
            while(c.moveToNext()){
                if(username.equals(c.getString(1)))
                    PASSWORD = c.getString(3);
            }
        }
        c.close();
        return PASSWORD;
    }

    //get password is correct -- used for login -- needs username and password input
    public boolean checkLogin(String username, String password){
        boolean login = false;
        String PASSWORD = "NA";
        Cursor c = getUSERData();
        if(c.getCount()>0){
            while(c.moveToNext()){
                if(username.equals(c.getString(1)))
                    PASSWORD = c.getString(3);
                if(password.equals(PASSWORD))
                    login = true;
            }
        }
        c.close();
        return login;
    }

    //get Admin -- Admin == 1 - admin created account / Admin == 0 - user generated/admin has no access -- needs username
    public int getAdmin(String username){
        int ADMIN = 0;
        Cursor c = getUSERData();
        if(c.getCount()>0){
            while(c.moveToNext()){
                if(username.equals(c.getString(1)))
                    ADMIN = c.getInt(4);
            }
        }
        c.close();
        return ADMIN;
    }


    //GET INCOME DATA
    public Cursor getINCOMEData()
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String query = "SELECT * FROM "+myhelper.TABLE_INCOME;
        Cursor INCOME = db.rawQuery(query, null);
        /*String[] columns = {myDbHelper.UID,myDbHelper.USERNAME,myDbHelper.AMOUNT,myDbHelper.CATEGORY};
        Cursor cursor =db.query(myDbHelper.TABLE_INCOME,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            String username =cursor.getString(cursor.getColumnIndex(myDbHelper.USERNAME));
            if(givenUsername.equals(username)){
                int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
                String IName=cursor.getString(cursor.getColumnIndex(myDbHelper.INAME));
                double  amount =cursor.getDouble(cursor.getColumnIndex(myDbHelper.AMOUNT));
                String  category =cursor.getString(cursor.getColumnIndex(myDbHelper.CATEGORY));
                buffer.append(cid+ "   " + username + "   " + IName +"   " + amount +"   " + category +" \n");
                if(choice == 1){
                    return IName;
                }
                else if(choice == 2){
                    return String.valueOf(amount);
                }
                else if(choice == 3){
                    return category;
                }
            }*/

        return INCOME;
    }

    // Get Income Name -- gets all income names in StringBuilder // seperated by "," -- needs username
    public StringBuilder getIName(String username){
        StringBuilder INAME = new StringBuilder();
        Cursor c = getINCOMEData();
        if(c.getCount()>0){
            while(c.moveToNext()){
                if(username.equals(c.getString(1))){
                    INAME.append(c.getString(2));
                    INAME.append(",");
                }
            }
        }
        c.close();
        return INAME;
    }

    //get income ammount (needs the name of specific income and username)
    public double getIAmmount(String name, String username){
        double AMMOUNT = 0;
        Cursor c = getINCOMEData();
        if(c.getCount()>0){
            while(c.moveToNext()){
                if(username.equals(c.getString(1)))
                    if(name.equals(c.getString(2))){
                        AMMOUNT = c.getDouble(3);
                    }
            }
        }
        c.close();
        return AMMOUNT;
    }

    //get income Category (needs the name of specific income and username)
    public String getICategory(String name, String username){
        String CATEGORY = "no Category selected";
        Cursor c = getINCOMEData();
        if(c.getCount()>0){
            while(c.moveToNext()){
                if(username.equals(c.getString(1)))
                    if(name.equals(c.getString(2))){
                        CATEGORY = c.getString(4);
                    }
            }
        }
        c.close();
        return CATEGORY;
    }


    //GET MISC DATA
    public Cursor getMISCData()
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String query = "SELECT * FROM "+myhelper.TABLE_MISC;
        Cursor MISC = db.rawQuery(query, null);
        /*String[] columns = {myDbHelper.UID,myDbHelper.USERNAME,myDbHelper.DAILYA,myDbHelper.SAVINGS};
        Cursor cursor =db.query(myDbHelper.TABLE_MISC,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            String username =cursor.getString(cursor.getColumnIndex(myDbHelper.USERNAME));
            if(givenUsername.equals(username)){
                int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
                double  dAllowance =cursor.getDouble(cursor.getColumnIndex(myDbHelper.DAILYA));
                double  savings =cursor.getDouble(cursor.getColumnIndex(myDbHelper.SAVINGS));
                buffer.append(cid+ "   " + username + "   " + dAllowance +"   " + savings +" \n");
                if(choice == 1){
                    return String.valueOf(dAllowance);
                }
                else if(choice == 2){
                    return String.valueOf(savings);
                }
            }

        }*/
        return MISC;
    }

    //Get Daily Allowance -- needs username
    public double getDAllowance(String username){
        double DALLOWANCE = 0;
        Cursor c = getMISCData();
        if(c.getCount()>0){
            while(c.moveToNext()){
                if(username.equals(c.getString(1)))
                    DALLOWANCE = c.getDouble(2);
            }
        }
        c.close();
        return DALLOWANCE;
    }

    //Get Savings -- needs username
    public double getSavings(String username){
        double DALLOWANCE = 0;
        Cursor c = getMISCData();
        if(c.getCount()>0){
            while(c.moveToNext()){
                if(username.equals(c.getString(1)))
                    DALLOWANCE = c.getDouble(3);
            }
        }
        c.close();
        return DALLOWANCE;
    }

    //GET EXPENSE DATA
    public Cursor getEXPENSEData()
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String query = "SELECT * FROM "+myhelper.TABLE_EXPENSE;
        Cursor EXPENSE = db.rawQuery(query, null);
        /*String[] columns = {myDbHelper.UID,myDbHelper.USERNAME,myDbHelper.ENAME,myDbHelper.AMOUNT,myDbHelper.DATE,myDbHelper.CATEGORY,myDbHelper.RECURRING};
        Cursor cursor =db.query(myDbHelper.TABLE_EXPENSE,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            String username =cursor.getString(cursor.getColumnIndex(myDbHelper.USERNAME));
            if(givenUsername.equals(username)){
                int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
                String ename =cursor.getString(cursor.getColumnIndex(myDbHelper.ENAME));
                double  amount =cursor.getDouble(cursor.getColumnIndex(myDbHelper.AMOUNT));
                String  date =cursor.getString(cursor.getColumnIndex(myDbHelper.DATE));
                String category =cursor.getString(cursor.getColumnIndex(myDbHelper.CATEGORY));
                int  recurring =cursor.getInt(cursor.getColumnIndex(myDbHelper.RECURRING));
                buffer.append(cid+ "   " + username + "   " + ename +"   " + amount +"   " + date +"   " + category +"   " + recurring +" \n");
                if(choice == 1){
                    return ename;
                }
                else if(choice == 2){
                    return String.valueOf(amount);
                }
                else if(choice == 3){
                    return date;
                }
                else if(choice == 4){
                    return category;
                }
                else if(choice == 5){
                    return String.valueOf(recurring);
                }
            }

        }*/
        return EXPENSE;
    }

    // Get Expense Name -- gets all Expense names in StringBuilder // seperated by "," -- needs username
    public StringBuilder getEName(String username){
        StringBuilder ENAME = new StringBuilder();
        Cursor c = getEXPENSEData();
        if(c.getCount()>0){
            while(c.moveToNext()){
                if(username.equals(c.getString(1))){
                    ENAME.append(c.getString(2));
                     ENAME.append(",");
                }
            }
        }
        c.close();
        return ENAME;
    }

    //get Expense ammount (needs the name of specific expense and username)
    public double getEAmmount(String name, String username){
        double AMMOUNT = 0;
        Cursor c = getEXPENSEData();
        if(c.getCount()>0){
            while(c.moveToNext()){
                if(username.equals(c.getString(1)))
                    if(name.equals(c.getString(2))){
                        AMMOUNT = c.getDouble(3);
                    }
            }
        }
        c.close();
        return AMMOUNT;
    }

    //get Expense Monthly Date (needs the name of specific expense and username)
    public String getEDate(String name,String username){
        String DATE = "no DATE selected";
        Cursor c = getEXPENSEData();
        if(c.getCount()>0){
            while(c.moveToNext()){
                if(username.equals(c.getString(1)))
                    if(name.equals(c.getString(2))){
                        DATE = c.getString(4);
                    }
            }
        }
        c.close();
        return DATE;
    }

    //get Expense Category (needs the name of specific expense and username)
    public String getECategory(String name, String username){
        String CATEGORY = "no Category selected";
        Cursor c = getEXPENSEData();
        if(c.getCount()>0){
            while(c.moveToNext()){
                if(username.equals(c.getString(1)))
                    if(name.equals(c.getString(2))){
                        CATEGORY = c.getString(5);
                    }
            }
        }
        c.close();
        return CATEGORY;
    }

    //get Expense if recurring (needs the name of specific expense and username) -- recurring == 1 - repeats / recurring == 0 - does not repeat
    public int getERecurring(String name, String username){
        int RECURRING = 0;
        Cursor c = getEXPENSEData();
        if(c.getCount()>0){
            while(c.moveToNext()){
                if(username.equals(c.getString(1)))
                    if(name.equals(c.getString(2))){
                        RECURRING = c.getInt(6);
                    }
            }
        }
        c.close();
        return RECURRING;
    }

    //get Expense repetition (needs the name of specific expense and username) -- recurring == 0 - does not repeat / else, 1 = 1 month
    public int getERepeat(String name, String username){
        int REPEAT = 0;
        Cursor c = getEXPENSEData();
        if(c.getCount()>0){
            while(c.moveToNext()){
                if(username.equals(c.getString(1)))
                    if(name.equals(c.getString(2))){
                        REPEAT = c.getInt(6);
                    }
            }
        }
        c.close();
        return REPEAT;
    }



    //DELETE USER FROM DATABASE
    public  boolean deleteUSER(String username)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] whereArgs ={username};

        int count = db.delete(myDbHelper.TABLE_USER ,myDbHelper.USERNAME+" = ?",whereArgs);
        db.delete(myDbHelper.TABLE_INCOME ,myDbHelper.USERNAME+" = ?",whereArgs);
        db.delete(myDbHelper.TABLE_MISC ,myDbHelper.USERNAME+" = ?",whereArgs);
        db.delete(myDbHelper.TABLE_EXPENSE ,myDbHelper.USERNAME+" = ?",whereArgs);
        if(count>0)
            return true;
        else return false;
    }

    // CHANGE USERNAME
    public boolean updateUsername(String oldName , String newName)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.USERNAME,newName);
        String[] whereArgs= {oldName};
        int count =db.update(myDbHelper.TABLE_USER,contentValues, myDbHelper.USERNAME+" = ?",whereArgs );
        if(count>0)
            return true;
        else return false;
    }

    // CHANGE PASSWORD
    public boolean updatePassword(String oldPassword , String newPassword, String username)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.MyPASSWORD,newPassword);
        String[] whereArgs= {username};
        int count =db.update(myDbHelper.TABLE_USER,contentValues, myDbHelper.USERNAME+" = ?",whereArgs );
        if(count>0)
            return true;
        else return false;
    }

    //CHANGE ADMIN ACCESS
    public Boolean updateAACCESS(int access, String username)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.ADMINACCESS,access);
        String[] whereArgs = {username};
        int count =db.update(myDbHelper.TABLE_USER,contentValues, myDbHelper.USERNAME+" = ?",whereArgs );
        if(count>0)
            return true;
        else return false;
    }

    //CHANGE DAILY ALLOWANCE
    public boolean updateDAllowance(double dAllowance, String username)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.DAILYA,dAllowance);
        String[] whereArgs= {username};
        int count =db.update(myDbHelper.TABLE_MISC,contentValues, myDbHelper.USERNAME+" = ?",whereArgs );
        if(count>0)
            return true;
        else return false;
    }

    //CHANGE SAVINGS
    public boolean updateSavings(double savings, String username)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.SAVINGS,savings);
        String[] whereArgs= {username};
        int count =db.update(myDbHelper.TABLE_MISC,contentValues, myDbHelper.USERNAME+" = ?",whereArgs );
        if(count>0)
            return true;
        else return false;
    }

    //CHANGE AMOUNT
    public boolean updateEDate(String ename, double amount, String username)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.AMOUNT,amount);
        try {
            db.execSQL("UPDATE "+myDbHelper.TABLE_EXPENSE+" SET "+myDbHelper.AMOUNT+"='"+amount+"' WHERE "+myDbHelper.USERNAME+"='"+username+"' AND "+myDbHelper.ENAME+"='"+ename+"'");
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    //CHANGE DATE
    public boolean updateEDate(String ename, String date, String username)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.DATE,date);
        try {
            db.execSQL("UPDATE "+myDbHelper.TABLE_EXPENSE+" SET "+myDbHelper.DATE+"='"+date+"' WHERE "+myDbHelper.USERNAME+"='"+username+"' AND "+myDbHelper.ENAME+"='"+ename+"'");
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    //CHANGE RECURRING
    public boolean updateERecurring(String ename, int recurring, String username)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.RECURRING,recurring);
        try {
            db.execSQL("UPDATE "+myDbHelper.TABLE_EXPENSE+" SET "+myDbHelper.RECURRING+"='"+recurring+"' WHERE "+myDbHelper.USERNAME+"='"+username+"' AND "+myDbHelper.ENAME+"='"+ename+"'");
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    //CHANGE RECURRING
    public boolean updateERepeat(String ename, int repeat, String username)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.RECURRING,repeat);
        try {
            db.execSQL("UPDATE "+myDbHelper.TABLE_EXPENSE+" SET "+myDbHelper.REPEAT+"='"+repeat+"' WHERE "+myDbHelper.USERNAME+"='"+username+"' AND "+myDbHelper.ENAME+"='"+ename+"'");
            return true;
        } catch (SQLException e) {
            return false;
        }
    }


    static class myDbHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "EMDatabase";    // Database Name
        private static final int DATABASE_Version = 2;    // Database Version

        //USER TABLE
        private static final String TABLE_USER = "USER";   // USER table Name
        private static final String UID="Uid";     // Column I (Primary Key)
        private static final String USERNAME = "UserName";    //Column II
        private static final String EMAIL= "Email";    // Column III
        private static final String MyPASSWORD= "Password";    // Column IV
        private static final String ADMINACCESS= "AdminAccess";    // Column V
        private static final String CREATE_USER_TABLE = "CREATE TABLE "+TABLE_USER+
                " ("+
                UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                USERNAME+" VARCHAR(50) ,"+
                EMAIL+" VARCHAR(70) ,"+
                MyPASSWORD+" VARCHAR(50) ,"+
                ADMINACCESS+" int);";
        private static final String DROP_USER_TABLE ="DROP TABLE IF EXISTS "+TABLE_USER;

        //INCOME TABLE
        private static final String ID = "Id";   // MISC table Name
        private static final String TABLE_INCOME = "INCOME";   // MISC table Name
        private static final String INAME= "IName";    // Column III
        private static final String AMOUNT= "Amount";    // Column IV
        private static final String CATEGORY= "Category";    // Column V

        //MISC TABLE
        private static final String TABLE_MISC = "MISC";   // MISC table Name
        private static final String DAILYA= "DailyAllowance";    // Column III
        private static final String SAVINGS= "Savings";    // Column IV
        private static final String CREATE_MISC_TABLE = "" +
                "CREATE TABLE "+TABLE_MISC+
                " ("+
                ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                USERNAME+" VARCHAR(50) ,"+
                DAILYA+" REAL ,"+
                SAVINGS+" REAL);";
        private static final String DROP_MISC_TABLE ="DROP TABLE IF EXISTS "+TABLE_MISC;

        //EXPENSES TABLE
        private static final String TABLE_EXPENSE = "EXPENSE";   // EXPENSE table Name
        private static final String ENAME= "ExpenseName";    // Column III
        private static final String DATE= "Date";    // Column V
        private static final String RECURRING= "Recurring";    // Column VII
        private static final String REPEAT= "Repeat";    // Column VIII
        private static final String CREATE_EXPENSE_TABLE = "CREATE TABLE "+TABLE_EXPENSE+
                " ("+
                ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                USERNAME+" VARCHAR(50) ,"+
                ENAME +" VARCHAR(100) ,"+
                AMOUNT +" REAL ,"+
                DATE + " TEXT ,"+
                CATEGORY + " VARCHAR(14) ,"+
                RECURRING +" INTEGER ,"+
                REPEAT +" INTEGER);";
        private static final String DROP_EXPENSE_TABLE ="DROP TABLE IF EXISTS "+TABLE_MISC;

        //INCOME TABLE CREATE/DROP
        private static final String CREATE_INCOME_TABLE = "CREATE TABLE "+TABLE_INCOME+
                " ("+
                ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                USERNAME+" VARCHAR(50) ,"+
                INAME+" VARCHAR(100) ,"+
                AMOUNT+" REAL ,"+
                DATE + " TEXT ,"+
                CATEGORY+" VARCHAR(14) ,"+
                RECURRING+" INTEGER);";
        private static final String DROP_INCOME_TABLE ="DROP TABLE IF EXISTS "+TABLE_INCOME;


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