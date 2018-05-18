package test.in.com.expensemanager.Database.table;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import test.in.com.expensemanager.Database.DbScheme;
import test.in.com.expensemanager.Database.Utils;
import test.in.com.expensemanager.Database.model.GroupModel;
import test.in.com.expensemanager.Database.model.UserModel;

public class GroupTable {

    private DbScheme mDb;
    private UserTable memberTable;

    public GroupTable(Context context) {
        mDb = new DbScheme(context);
        memberTable = new UserTable(context);
    }

    public long insertGroup(String groupName) {
        SQLiteDatabase database = mDb.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DbScheme.GROUP_NAME, groupName);
        values.put(DbScheme.GROUP_CREATE_TIME, Utils.getCurrentTime());

        return database.insert(DbScheme.TABLE_GROUP, null, values);
    }

    public long addMembers(List<UserModel> models, int groupId) {
        SQLiteDatabase database = mDb.getWritableDatabase();

        String memberIds = "";
        String memberName = "";

        for (UserModel model : models) {
            memberTable.updateGroupId(model.getId(), groupId);
            memberIds = memberIds.concat(",").concat(String.valueOf(model.getId()));
            memberName = memberName.concat(",").concat(String.valueOf(model.getName()));
        }

        if (!memberIds.trim().isEmpty()) {
            ContentValues values = new ContentValues();
            values.put(DbScheme.GROUP_USER_ID, memberIds);
            values.put(DbScheme.GROUP_USER_NAME, memberName);

            return database.update(DbScheme.TABLE_GROUP, values,
                    DbScheme.GROUP_ID + " = " + groupId, null);
        } else {
            return 0;
        }
    }

    public List<GroupModel> getAllGroups() {
        List<GroupModel> groupModels = new ArrayList<>();

        SQLiteDatabase database = mDb.getReadableDatabase();

        String QUERY = "SELECT * FROM " + DbScheme.TABLE_GROUP;

        Cursor cursor = database.rawQuery(QUERY, null);


        if (cursor != null && cursor.moveToFirst()) {

            do {

                GroupModel model = new GroupModel();

                model.setId(cursor.getInt(cursor.getColumnIndex(DbScheme.GROUP_ID)));
                model.setGroupName(cursor.getString(cursor.getColumnIndex(DbScheme.GROUP_NAME)));
                model.setUsersId(cursor.getString(cursor.getColumnIndex(DbScheme.GROUP_USER_ID)));
                model.setUsersName(cursor.getString(cursor.getColumnIndex(DbScheme.GROUP_USER_NAME)));
                model.setCreatedTime(cursor.getString(cursor.getColumnIndex(DbScheme.GROUP_CREATE_TIME)));

                groupModels.add(model);

            } while (cursor.moveToNext());

            cursor.close();
//            database.close();
        }
        return groupModels;
    }
}