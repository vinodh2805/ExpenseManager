package test.in.com.expensemanager.Database.table;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import test.in.com.expensemanager.Database.DbScheme;
import test.in.com.expensemanager.Database.Utils;
import test.in.com.expensemanager.Database.model.UserModel;

public class UserTable {

    private DbScheme mDb;

    public UserTable(Context context) {
        mDb = new DbScheme(context);
    }

    public void insertMember(String name, int groupId) {

        SQLiteDatabase database = mDb.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DbScheme.USER_NAME, name);
        values.put(DbScheme.USER_GROUP_ID, groupId);
        values.put(DbScheme.USER_CREATE_TIME, Utils.getCurrentTime());

        database.insert(DbScheme.TABLE_MEMBER, null, values);
        database.close();
    }

    public void updateGroupId(int userId, int groupId) {
        SQLiteDatabase database = mDb.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DbScheme.USER_GROUP_ID, groupId);

        database.update(DbScheme.TABLE_MEMBER, values, DbScheme.USER_ID + " = " + userId, null);
        database.close();
    }

    public List<UserModel> getAllMembers() {

        List<UserModel> memberModels = new ArrayList<>();

        SQLiteDatabase database = mDb.getReadableDatabase();

        Cursor cursor = database.query(DbScheme.TABLE_MEMBER, null,
                null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                UserModel model = new UserModel();
                model.setId(cursor.getInt(cursor.getColumnIndex(DbScheme.USER_ID)));
                model.setName(cursor.getString(cursor.getColumnIndex(DbScheme.USER_NAME)));
                model.setGroupId(cursor.getInt(cursor.getColumnIndex(DbScheme.USER_GROUP_ID)));
                model.setCreateTime(cursor.getString(cursor.getColumnIndex(DbScheme.USER_CREATE_TIME)));

                memberModels.add(model);
            } while (cursor.moveToNext());

            cursor.close();
            return memberModels;
        } else {
            return memberModels;
        }
    }

    public List<UserModel> getUsersByGroup(int groupId) {
        List<UserModel> memberModels = new ArrayList<>();

        SQLiteDatabase database = mDb.getReadableDatabase();

        String QUERY = "SELECT * from " + DbScheme.TABLE_MEMBER + " WHERE "
                + DbScheme.USER_GROUP_ID + " = " + groupId;

        Cursor cursor = database.rawQuery(QUERY, null);

        if (cursor != null && cursor.moveToFirst()) {

            do {
                UserModel model = new UserModel();
                model.setId(cursor.getInt(cursor.getColumnIndex(DbScheme.USER_ID)));
                model.setName(cursor.getString(cursor.getColumnIndex(DbScheme.USER_NAME)));
                model.setGroupId(cursor.getInt(cursor.getColumnIndex(DbScheme.USER_GROUP_ID)));
                model.setCreateTime(cursor.getString(cursor.getColumnIndex(DbScheme.USER_CREATE_TIME)));

                memberModels.add(model);
            } while (cursor.moveToNext());

            cursor.close();
            return memberModels;

        } else {
            return memberModels;
        }
    }
}