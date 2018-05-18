package test.in.com.expensemanager.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbScheme extends SQLiteOpenHelper {


    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "db_manager";

    //table name
    public static String TABLE_GROUP = "manager_group";
    public static String TABLE_MEMBER = "manager_member";
    public static String TABLE_EXPENSE = "manager_expense";

    //column names
    //member
    public static String USER_ID = "id";
    public static String USER_NAME = "name";
    public static String USER_CREATE_TIME = "crated_time";
    public static String USER_GROUP_ID = "group_id";

    //group
    public static String GROUP_ID = "id";
    public static String GROUP_NAME = "name";
    public static String GROUP_CREATE_TIME = "create_time";
    public static String GROUP_USER_ID = "user_id";
    public static String GROUP_USER_NAME = "user_name";

    //expense
    public static String EXPENSE_ID = "id";
    public static String EXPENSE_GROUP_ID = "group_id";
    public static String EXPENSE_GROUP_NAME = "group_name";
    public static String EXPENSE_AMOUNT = "amount";
    public static String EXPENSE_AMOUNT_TITLE = "amount_title";
    public static String EXPENSE_PAID_BY_USER_ID = "paid_by_member_id";
    public static String EXPENSE_PAID_BY_USER_NAME = "paid_by_member_name";
    public static String EXPENSE_PAID_TIME = "paid_time";

    //query for creating table
    private static String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_MEMBER + "(" + USER_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + USER_NAME + " TEXT," + USER_CREATE_TIME + " TEXT,"
            + USER_GROUP_ID + " INTEGER)";
    private static String CREATE_GROUP_TABLE = "CREATE TABLE " + TABLE_GROUP + "(" + GROUP_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + GROUP_NAME + " TEXT," + GROUP_CREATE_TIME + " TEXT,"
            + GROUP_USER_ID + " INTEGER, " + GROUP_USER_NAME + " TEXT)";
    private static String CREATE_EXPENSE_TABLE = "CREATE TABLE " + TABLE_EXPENSE + "(" + EXPENSE_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + EXPENSE_GROUP_ID + " INTEGER," + EXPENSE_GROUP_NAME + " TEXT,"
            + EXPENSE_AMOUNT + " TEXT, " + EXPENSE_AMOUNT_TITLE + " TEXT," + EXPENSE_PAID_BY_USER_ID + " TEXT,"
            + EXPENSE_PAID_BY_USER_NAME + " TEXT," + EXPENSE_PAID_TIME + " TEXT)";


    public DbScheme(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_GROUP_TABLE);
        db.execSQL(CREATE_EXPENSE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEMBER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GROUP);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXPENSE);

        // create new tables
        onCreate(db);
    }
}
