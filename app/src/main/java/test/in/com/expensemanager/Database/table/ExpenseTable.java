package test.in.com.expensemanager.Database.table;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import test.in.com.expensemanager.Database.DbHelper;
import test.in.com.expensemanager.Database.Utils;
import test.in.com.expensemanager.Database.model.ExpenseModel;

public class ExpenseTable {

    private DbHelper mDbHelper;

    public ExpenseTable(Context context) {
        mDbHelper = new DbHelper(context);
    }

    public long insertData(ExpenseModel model) {
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DbHelper.EXPENSE_GROUP_ID, model.getGroupId());
        values.put(DbHelper.EXPENSE_GROUP_NAME, model.getGroupName());
        values.put(DbHelper.EXPENSE_PAID_BY_USER_ID, model.getPaidUserId());
        values.put(DbHelper.EXPENSE_PAID_BY_USER_NAME, model.getPaidUserName());
        values.put(DbHelper.EXPENSE_PAID_TIME, Utils.getCurrentTime());
        values.put(DbHelper.EXPENSE_AMOUNT, model.getAmount());
        values.put(DbHelper.EXPENSE_AMOUNT_TITLE, model.getAmountTitle());

        Log.d("EXPENSE_TAG", "insertData: "+model.toString());

        return database.insert(DbHelper.TABLE_EXPENSE, null, values);
    }

    public List<ExpenseModel> getAllExpense() {
        List<ExpenseModel> models = new ArrayList<>();

        SQLiteDatabase database = mDbHelper.getReadableDatabase();

        String QUERY = "SELECT * FROM " + DbHelper.TABLE_EXPENSE;

        Cursor cursor = database.rawQuery(QUERY, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                ExpenseModel model = new ExpenseModel();
                model.setId(cursor.getInt(cursor.getColumnIndex(DbHelper.EXPENSE_ID)));
                model.setGroupId(cursor.getInt(cursor.getColumnIndex(DbHelper.EXPENSE_GROUP_ID)));
                model.setGroupName(cursor.getString(cursor.getColumnIndex(DbHelper.EXPENSE_GROUP_NAME)));
                model.setPaidUserId(cursor.getInt(cursor.getColumnIndex(DbHelper.EXPENSE_PAID_BY_USER_ID)));
                model.setPaidUserName(cursor.getString(cursor.getColumnIndex(DbHelper.EXPENSE_PAID_BY_USER_NAME)));
                model.setPaidTime(cursor.getString(cursor.getColumnIndex(DbHelper.EXPENSE_PAID_TIME)));
                model.setAmount(cursor.getString(cursor.getColumnIndex(DbHelper.EXPENSE_AMOUNT)));
                model.setAmountTitle(cursor.getString(cursor.getColumnIndex(DbHelper.EXPENSE_AMOUNT_TITLE)));

                models.add(model);

            } while (cursor.moveToNext());
            cursor.close();
        }
        database.close();
        return models;
    }

    public List<ExpenseModel> getAllExpenseByGroupId(int groupId) {
        List<ExpenseModel> models = new ArrayList<>();

        SQLiteDatabase database = mDbHelper.getReadableDatabase();

        String QUERY = "SELECT * FROM " + DbHelper.TABLE_EXPENSE+" WHERE "+ DbHelper.EXPENSE_GROUP_ID +" = "+groupId;

        Cursor cursor = database.rawQuery(QUERY, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                ExpenseModel model = new ExpenseModel();
                model.setId(cursor.getInt(cursor.getColumnIndex(DbHelper.EXPENSE_ID)));
                model.setGroupId(cursor.getInt(cursor.getColumnIndex(DbHelper.EXPENSE_GROUP_ID)));
                model.setGroupName(cursor.getString(cursor.getColumnIndex(DbHelper.EXPENSE_GROUP_NAME)));
                model.setPaidUserId(cursor.getInt(cursor.getColumnIndex(DbHelper.EXPENSE_PAID_BY_USER_ID)));
                model.setPaidUserName(cursor.getString(cursor.getColumnIndex(DbHelper.EXPENSE_PAID_BY_USER_NAME)));
                model.setPaidTime(cursor.getString(cursor.getColumnIndex(DbHelper.EXPENSE_PAID_TIME)));
                model.setAmount(cursor.getString(cursor.getColumnIndex(DbHelper.EXPENSE_AMOUNT)));
                model.setAmountTitle(cursor.getString(cursor.getColumnIndex(DbHelper.EXPENSE_AMOUNT_TITLE)));

                models.add(model);

            } while (cursor.moveToNext());
            cursor.close();
        }
        database.close();
        return models;
    }
    public float getAllExpenseAmountByGroupId(int groupId) {


        SQLiteDatabase database = mDbHelper.getReadableDatabase();

        String QUERY = "SELECT * FROM " + DbHelper.TABLE_EXPENSE+" WHERE "+ DbHelper.EXPENSE_GROUP_ID +" = "+groupId;

        Cursor cursor = database.rawQuery(QUERY, null);

        float totalAmount =0f;
        if (cursor != null && cursor.moveToFirst()) {
            do {
                float amount = Float.valueOf(cursor.getString(cursor.getColumnIndex(DbHelper.EXPENSE_AMOUNT)));
                totalAmount = totalAmount + amount;
            } while (cursor.moveToNext());
            cursor.close();
        }
        database.close();
        return totalAmount;
    }

    public float getUserTotalExpense(String userName,int groupId){
        SQLiteDatabase database = mDbHelper.getReadableDatabase();

        String QUERY = "SELECT * FROM " + DbHelper.TABLE_EXPENSE + " WHERE "
                + DbHelper.EXPENSE_PAID_BY_USER_NAME + " = '" + userName + "' AND "
                + DbHelper.EXPENSE_GROUP_ID + " = '" + groupId+"'";

        Cursor cursor = database.rawQuery(QUERY, null);
        float totalAmount = 0f;
        if (cursor!=null && cursor.moveToFirst()){
            do {

                float amount = Float.valueOf(cursor.getString(cursor.getColumnIndex(DbHelper.EXPENSE_AMOUNT)));
                totalAmount = totalAmount + amount;

            }while (cursor.moveToNext());
            cursor.close();
        }

        database.close();
        return totalAmount;
    }


}