package test.in.com.expensemanager.Database.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ExpenseModel implements Parcelable{
    private int id;
    private int groupId;
    private String groupName;
    private String amount;
    private String amountTitle;
    private int paidUserId;
    private String paidUserName;
    private String paidTime;

    public ExpenseModel() {
    }

    protected ExpenseModel(Parcel in) {
        id = in.readInt();
        groupId = in.readInt();
        groupName = in.readString();
        amount = in.readString();
        amountTitle = in.readString();
        paidUserId = in.readInt();
        paidUserName = in.readString();
        paidTime = in.readString();
    }

    public static final Creator<ExpenseModel> CREATOR = new Creator<ExpenseModel>() {
        @Override
        public ExpenseModel createFromParcel(Parcel in) {
            return new ExpenseModel(in);
        }

        @Override
        public ExpenseModel[] newArray(int size) {
            return new ExpenseModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAmountTitle() {
        return amountTitle;
    }

    public void setAmountTitle(String amountTitle) {
        this.amountTitle = amountTitle;
    }

    public int getPaidUserId() {
        return paidUserId;
    }

    public void setPaidUserId(int paidUserId) {
        this.paidUserId = paidUserId;
    }

    public String getPaidUserName() {
        return paidUserName;
    }

    public void setPaidUserName(String paidUserName) {
        this.paidUserName = paidUserName;
    }

    public String getPaidTime() {
        return paidTime;
    }

    public void setPaidTime(String paidTime) {
        this.paidTime = paidTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(groupId);
        parcel.writeString(groupName);
        parcel.writeString(amount);
        parcel.writeString(amountTitle);
        parcel.writeInt(paidUserId);
        parcel.writeString(paidUserName);
        parcel.writeString(paidTime);
    }
}
