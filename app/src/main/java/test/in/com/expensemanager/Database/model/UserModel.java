package test.in.com.expensemanager.Database.model;

import android.os.Parcel;
import android.os.Parcelable;

public class UserModel implements Parcelable {
    private int id;
    private String name;
    private String createTime;
    private int groupId;

    public UserModel() {
    }

    protected UserModel(Parcel in) {
        id = in.readInt();
        name = in.readString();
        createTime = in.readString();
        groupId = in.readInt();
    }

    public static final Creator<UserModel> CREATOR = new Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(Parcel in) {
            return new UserModel(in);
        }

        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(createTime);
        parcel.writeInt(groupId);
    }
}
