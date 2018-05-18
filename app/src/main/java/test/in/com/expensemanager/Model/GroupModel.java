package test.in.com.expensemanager.Model;

import java.io.Serializable;

public class GroupModel implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String groupname;
    private int groupid;





    public GroupModel() {

    }

    public GroupModel(String group_name) {

        this.groupname = group_name;


    }



    public String getGroupName() {
        return groupname;
    }

    public void setGroupName(String group_name) {
        this.groupname = group_name;
    }

    public int getGroupId() {
        return groupid;
    }

    public void setGroupId(int group_id) {
        this.groupid = group_id;
    }


}
