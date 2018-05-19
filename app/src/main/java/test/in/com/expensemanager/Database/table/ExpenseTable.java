package test.in.com.expensemanager.Database.table;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import test.in.com.expensemanager.Database.DbScheme;
import test.in.com.expensemanager.Database.Utils;
import test.in.com.expensemanager.Database.model.ExpenseModel;

public class ExpenseTable {

    private DbScheme mDbScheme;

    public ExpenseTable(Context context) {
        mDbScheme = new DbScheme(context);
    }

    public long insertData(ExpenseModel model) {
        SQLiteDatabase database = mDbScheme.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DbScheme.EXPENSE_GROUP_ID, model.getGroupId());
        values.put(DbScheme.EXPENSE_GROUP_NAME, model.getGroupName());
        values.put(DbScheme.EXPENSE_PAID_BY_USER_ID, model.getPaidUserId());
        values.put(DbScheme.EXPENSE_PAID_BY_USER_NAME, model.getPaidUserName());
        values.put(DbScheme.EXPENSE_PAID_TIME, Utils.getCurrentTime());
        values.put(DbScheme.EXPENSE_AMOUNT, model.getAmount());
        values.put(DbScheme.EXPENSE_AMOUNT_TITLE, model.getAmountTitle());

        Log.d("EXPENSE_TAG", "insertData: "+model.toString());

        return database.insert(DbScheme.TABLE_EXPENSE, null, values);
    }

    public List<ExpenseModel> getAllExpense() {
        List<ExpenseModel> models = new ArrayList<>();

        SQLiteDatabase database = mDbScheme.getReadableDatabase();

        String QUERY = "SELECT * FROM " + DbScheme.TABLE_EXPENSE;

        Cursor cursor = database.rawQuery(QUERY, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                ExpenseModel model = new ExpenseModel();
                model.setId(cursor.getInt(cursor.getColumnIndex(DbScheme.EXPENSE_ID)));
                model.setGroupId(cursor.getInt(cursor.getColumnIndex(DbScheme.EXPENSE_GROUP_ID)));
                model.setGroupName(cursor.getString(cursor.getColumnIndex(DbScheme.EXPENSE_GROUP_NAME)));
                model.setPaidUserId(cursor.getInt(cursor.getColumnIndex(DbScheme.EXPENSE_PAID_BY_USER_ID)));
                model.setPaidUserName(cursor.getString(cursor.getColumnIndex(DbScheme.EXPENSE_PAID_BY_USER_NAME)));
                model.setPaidTime(cursor.getString(cursor.getColumnIndex(DbScheme.EXPENSE_PAID_TIME)));
                model.setAmount(cursor.getString(cursor.getColumnIndex(DbScheme.EXPENSE_AMOUNT)));
                model.setAmountTitle(cursor.getString(cursor.getColumnIndex(DbScheme.EXPENSE_AMOUNT_TITLE)));

                models.add(model);

            } while (cursor.moveToNext());
            cursor.close();
        }
        database.close();
        return models;
    }

    public List<ExpenseModel> getAllExpenseByGroupId(int groupId) {
        List<ExpenseModel> models = new ArrayList<>();

        SQLiteDatabase database = mDbScheme.getReadableDatabase();

        String QUERY = "SELECT * FROM " + DbScheme.TABLE_EXPENSE+" WHERE "+DbScheme.EXPENSE_GROUP_ID +" = "+groupId;

        Cursor cursor = database.rawQuery(QUERY, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                ExpenseModel model = new ExpenseModel();
                model.setId(cursor.getInt(cursor.getColumnIndex(DbScheme.EXPENSE_ID)));
                model.setGroupId(cursor.getInt(cursor.getColumnIndex(DbScheme.EXPENSE_GROUP_ID)));
                model.setGroupName(cursor.getString(cursor.getColumnIndex(DbScheme.EXPENSE_GROUP_NAME)));
                model.setPaidUserId(cursor.getInt(cursor.getColumnIndex(DbScheme.EXPENSE_PAID_BY_USER_ID)));
                model.setPaidUserName(cursor.getString(cursor.getColumnIndex(DbScheme.EXPENSE_PAID_BY_USER_NAME)));
                model.setPaidTime(cursor.getString(cursor.getColumnIndex(DbScheme.EXPENSE_PAID_TIME)));
                model.setAmount(cursor.getString(cursor.getColumnIndex(DbScheme.EXPENSE_AMOUNT)));
                model.setAmountTitle(cursor.getString(cursor.getColumnIndex(DbScheme.EXPENSE_AMOUNT_TITLE)));

                models.add(model);

            } while (cursor.moveToNext());
            cursor.close();
        }
        database.close();
        return models;
    }
    public float getAllExpenseAmountByGroupId(int groupId) {


        SQLiteDatabase database = mDbScheme.getReadableDatabase();

        String QUERY = "SELECT * FROM " + DbScheme.TABLE_EXPENSE+" WHERE "+DbScheme.EXPENSE_GROUP_ID +" = "+groupId;

        Cursor cursor = database.rawQuery(QUERY, null);

        float totalAmount =0f;
        if (cursor != null && cursor.moveToFirst()) {
            do {
                float amount = Float.valueOf(cursor.getString(cursor.getColumnIndex(DbScheme.EXPENSE_AMOUNT)));
                totalAmount = totalAmount + amount;
            } while (cursor.moveToNext());
            cursor.close();
        }
        database.close();
        return totalAmount;
    }


}