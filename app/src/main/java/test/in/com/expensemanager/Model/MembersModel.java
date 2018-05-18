package test.in.com.expensemanager.Model;

import java.io.Serializable;

public class MembersModel implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String membersname;
    private int memberid;





    public MembersModel() {

    }

    public MembersModel(String member_name) {

        this.membersname = member_name;


    }



    public String getMemberName() {
        return membersname;
    }

    public void setMemberName(String member_name) {
        this.membersname = member_name;
    }

    public int getMemberId() {
        return memberid;
    }

    public void setMemberId(int member_id) {
        this.memberid = member_id;
    }


}