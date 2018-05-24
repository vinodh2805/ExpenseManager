package test.in.com.expensemanager.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import test.in.com.expensemanager.Database.model.UserModel;
import test.in.com.expensemanager.Model.GroupModel;
import test.in.com.expensemanager.Model.MembersModel;
import test.in.com.expensemanager.R;

public class MembersAdapter extends
        RecyclerView.Adapter<MembersAdapter.ViewHolder> {

    private List<UserModel> grpList;

    public MembersAdapter(List<UserModel> group_list) {
        this.grpList = group_list;

    }

    // Create new views
    @Override
    public MembersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.memberscardview_row, null);

        // create ViewHolder

        MembersAdapter.ViewHolder viewHolder = new MembersAdapter.ViewHolder(itemLayoutView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MembersAdapter.ViewHolder viewHolder, int position) {

        final int pos = position;

        viewHolder.groudname.setText(grpList.get(position).getName());



    }

    // Return the size arraylist
    @Override
    public int getItemCount() {
        return grpList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView groudname,amount_spent;

        public GroupModel singlestudent;

        public ViewHolder(View itemLayoutView)
        {
            super(itemLayoutView);

            groudname = (TextView) itemLayoutView.findViewById(R.id.groudname);





        }

    }

    // method to access in activity after updating selection
    public List<UserModel> getStudentist() {
        return grpList;
    }

}