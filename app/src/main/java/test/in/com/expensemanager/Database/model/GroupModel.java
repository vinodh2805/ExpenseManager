package test.in.com.expensemanager.Database.model;

import android.os.Parcel;
import android.os.Parcelable;

public class GroupModel implements Parcelable {
    private int id;
    private String groupName;
    private String createdTime;
    private String usersId;
    private String usersName;

    public GroupModel() {
    }

    protected GroupModel(Parcel in) {
        id = in.readInt();
        groupName = in.readString();
        createdTime = in.readString();
        usersId = in.readString();
        usersName = in.readString();
    }

    public static final Creator<GroupModel> CREATOR = new Creator<GroupModel>() {
        @Override
        public GroupModel createFromParcel(Parcel in) {
            return new GroupModel(in);
        }

        @Override
        public GroupModel[] newArray(int size) {
            return new GroupModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getUsersId() {
        return usersId;
    }

    public void setUsersId(String usersId) {
        this.usersId = usersId;
    }

    public String getUsersName() {
        return usersName;
    }

    public void setUsersName(String usersName) {
        this.usersName = usersName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(groupName);
        parcel.writeString(createdTime);
        parcel.writeString(usersId);
        parcel.writeString(usersName);
    }

}
