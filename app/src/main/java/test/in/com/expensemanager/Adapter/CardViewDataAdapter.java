package test.in.com.expensemanager.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;


import test.in.com.expensemanager.Database.model.GroupModel;
import test.in.com.expensemanager.R;


/**
 * Created by android-vinoth on 17/10/16.
 */

public class CardViewDataAdapter extends
        RecyclerView.Adapter<CardViewDataAdapter.ViewHolder> {

        private List<GroupModel> grpList;

    public CardViewDataAdapter(List<GroupModel> group_list) {
        this.grpList = group_list;

    }

    // Create new views
    @Override
    public CardViewDataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.cardview_row, null);

        // create ViewHolder

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        final int pos = position;

        viewHolder.groudname.setText(grpList.get(position).getGroupName());


    }

    // Return the size arraylist
    @Override
    public int getItemCount() {
        return grpList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView groudname;

        public GroupModel singlestudent;

        public ViewHolder(View itemLayoutView)
        {
            super(itemLayoutView);

            groudname = (TextView) itemLayoutView.findViewById(R.id.groudname);




        }

    }

    // method to access in activity after updating selection
    public List<GroupModel> getStudentist() {
        return grpList;
    }

}
