package test.in.com.expensemanager.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import test.in.com.expensemanager.Database.model.ExpenseModel;
import test.in.com.expensemanager.Database.model.UserModel;
import test.in.com.expensemanager.Model.GroupModel;
import test.in.com.expensemanager.R;

public class ExpenseListAdapter extends
        RecyclerView.Adapter<ExpenseListAdapter.ViewHolder> {

    private List<ExpenseModel> grpList;

    public ExpenseListAdapter(List<ExpenseModel> group_list) {
        this.grpList = group_list;

    }

    // Create new views
    @Override
    public ExpenseListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.expensecardview_row, null);

        // create ViewHolder
        ExpenseListAdapter.ViewHolder viewHolder = new ExpenseListAdapter.ViewHolder(itemLayoutView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ExpenseListAdapter.ViewHolder viewHolder, int position) {

        final int pos = position;

        viewHolder.groupname.setText(grpList.get(position).getAmountTitle());
        viewHolder.amount.setText("Rs."+grpList.get(position).getAmount());
        viewHolder.givenby.setText("Paid by "+grpList.get(position).getPaidUserName());



    }

    // Return the size arraylist
    @Override
    public int getItemCount() {
        return grpList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView groupname,amount,givenby;

        public GroupModel singlestudent;

        public ViewHolder(View itemLayoutView)
        {
            super(itemLayoutView);

            groupname = (TextView) itemLayoutView.findViewById(R.id.groupname);
            amount = (TextView) itemLayoutView.findViewById(R.id.amount);
            givenby = (TextView) itemLayoutView.findViewById(R.id.givenby);




        }

    }

    // method to access in activity after updating selection
    public List<ExpenseModel> getStudentist() {
        return grpList;
    }

}