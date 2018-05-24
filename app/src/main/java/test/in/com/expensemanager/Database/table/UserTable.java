package test.in.com.expensemanager.Database.table;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import test.in.com.expensemanager.Constants.Constants;
import test.in.com.expensemanager.Database.DbHelper;
import test.in.com.expensemanager.Database.Utils;
import test.in.com.expensemanager.Database.model.UserModel;

public class UserTable {

    private DbHelper mDb;

    public UserTable(Context context) {
        mDb = new DbHelper(context);
    }

    public boolean insertMember(String name, int groupId) {

        SQLiteDatabase database = mDb.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DbHelper.USER_NAME, name);
        values.put(DbHelper.USER_GROUP_ID, groupId);
        values.put(DbHelper.USER_CREATE_TIME, Utils.getCurrentTime());

        long result = database.insert(DbHelper.TABLE_MEMBER, null, values);
        database.close();
        return result > 0;
    }

    public boolean isUserNameAlreadyExists(String userName, int groupId) {
        SQLiteDatabase database = mDb.getReadableDatabase();

        String QUERY = "SELECT * FROM " + DbHelper.TABLE_MEMBER + " WHERE "
                + DbHelper.USER_NAME + " = '" + userName + "' AND "
                + DbHelper.USER_GROUP_ID + " = '" + groupId+"'";

        Cursor cursor = database.rawQuery(QUERY, null);

        if (cursor != null && cursor.getCount() > 0) {
            //already exists
            cursor.close();
            return true;
        } else {
            //new user
            assert cursor != null;
            cursor.close();
            return false;
        }


    }

    public void updateGroupId(int userId, int groupId) {
        SQLiteDatabase database = mDb.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DbHelper.USER_GROUP_ID, groupId);

        database.update(DbHelper.TABLE_MEMBER, values, DbHelper.USER_ID + " = " + userId, null);
        database.close();
    }

    public List<UserModel> getAllMembers() {

        List<UserModel> memberModels = new ArrayList<>();

        SQLiteDatabase database = mDb.getReadableDatabase();

        Cursor cursor = database.query(DbHelper.TABLE_MEMBER, null,
                null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                UserModel model = new UserModel();
                model.setId(cursor.getInt(cursor.getColumnIndex(DbHelper.USER_ID)));
                model.setName(cursor.getString(cursor.getColumnIndex(DbHelper.USER_NAME)));
                model.setGroupId(cursor.getInt(cursor.getColumnIndex(DbHelper.USER_GROUP_ID)));
                model.setCreateTime(cursor.getString(cursor.getColumnIndex(DbHelper.USER_CREATE_TIME)));

                memberModels.add(model);
            } while (cursor.moveToNext());

            cursor.close();
            return memberModels;
        } else {
            return memberModels;
        }
    }
    public int getAllMembersCount() {


        SQLiteDatabase database = mDb.getReadableDatabase();

        String QUERY = "SELECT count(*) FROM " + DbHelper.TABLE_MEMBER ;

        Cursor cursor = database.rawQuery(QUERY, null);
        cursor.moveToFirst();
        int count= cursor.getInt(0);
        cursor.close();
        return count;
    }

    public List<UserModel> getUsersByGroup(int groupId) {
        List<UserModel> memberModels = new ArrayList<>();

        SQLiteDatabase database = mDb.getReadableDatabase();

        String QUERY = "SELECT * from " + DbHelper.TABLE_MEMBER + " WHERE "
                + DbHelper.USER_GROUP_ID + " = " + groupId;

        Cursor cursor = database.rawQuery(QUERY, null);

        if (cursor != null && cursor.moveToFirst()) {

            do {
                UserModel model = new UserModel();
                model.setId(cursor.getInt(cursor.getColumnIndex(DbHelper.USER_ID)));
                model.setName(cursor.getString(cursor.getColumnIndex(DbHelper.USER_NAME)));
                model.setGroupId(cursor.getInt(cursor.getColumnIndex(DbHelper.USER_GROUP_ID)));
                model.setCreateTime(cursor.getString(cursor.getColumnIndex(DbHelper.USER_CREATE_TIME)));
                Constants.userscount = Constants.userscount + 1;
                memberModels.add(model);
            } while (cursor.moveToNext());

            cursor.close();
            return memberModels;

        } else {
            return memberModels;
        }
    }
}