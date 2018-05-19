package test.in.com.expensemanager.Fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import test.in.com.expensemanager.Adapter.ExpenseListAdapter;
import test.in.com.expensemanager.Adapter.MembersAdapter;
import test.in.com.expensemanager.Components.RecyclerTouchListener;
import test.in.com.expensemanager.Constants.Constants;
import test.in.com.expensemanager.Database.model.ExpenseModel;
import test.in.com.expensemanager.Database.model.UserModel;
import test.in.com.expensemanager.Database.table.ExpenseTable;
import test.in.com.expensemanager.Database.table.UserTable;
import test.in.com.expensemanager.Model.MembersModel;
import test.in.com.expensemanager.R;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;


public class ExpenseCountFragment extends Fragment {
    View rootview;
    ExpenseListAdapter members_Adapter;
    RecyclerView my_recycler_view;
    TextView total_expense,share_expense;
    private List<ExpenseModel> member_list;
    MembersModel members_model_obj;
    FloatingActionButton member_add_button;
    MaterialBetterSpinner userspinner;
    ExpenseTable expenseTable;
    UserTable user_obj;
    ExpenseModel expenseModel;
    public ExpenseCountFragment() {
        // Required empty public constructor
    }


    int spinnerPosition =0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        rootview = inflater.inflate(R.layout.expense_main_layout, container, false);
        user_obj = new UserTable(getActivity());
        expenseModel = new ExpenseModel();
        member_list = new ArrayList<ExpenseModel>();
        expenseTable = new ExpenseTable(getActivity());
        my_recycler_view = (RecyclerView) rootview.findViewById(R.id.my_recycler_view);
        member_add_button = (FloatingActionButton) rootview.findViewById(R.id.member_add_button);
        total_expense = (TextView) rootview.findViewById(R.id.total_expense);
        share_expense = (TextView) rootview.findViewById(R.id.share_expense);

        if(Constants.userscount!=0) {
            total_expense.setText("Total Amount Spent: " + expenseTable.getAllExpenseAmountByGroupId(Constants.groupid));
            float share = 0f;
            share = expenseTable.getAllExpenseAmountByGroupId(Constants.groupid) / Constants.userscount;
            share_expense.setText("Share: " + share);
        }
        else
        {
            total_expense.setText("Total Amount Spent:Rs.0");
            share_expense.setText("Share: Rs.0" );
        }
        my_recycler_view.setHasFixedSize(true);

        // use a linear layout manager
        my_recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()));

        // create an Object for Adapter

        members_Adapter = new ExpenseListAdapter(member_list);

        // set the adapter object to the Recyclerview
        my_recycler_view.setAdapter(members_Adapter);
        my_recycler_view.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), my_recycler_view, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

            //    MembersModel group = member_list.get(position);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        member_add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Constants.userscount!=0) {
                    LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);
                    View layout = inflater.inflate(R.layout.expense_add_form, (ViewGroup) getActivity().findViewById(R.id.layout_root));
//layout_root should be the name of the "top-level" layout node in the dialog_layout.xml file.
                    final EditText nameBox = (EditText) layout.findViewById(R.id.name);
                    final EditText amount = (EditText) layout.findViewById(R.id.amount);

                    userspinner = (MaterialBetterSpinner) layout.findViewById(R.id.hours_data_spinner);
                    List<String> list = new ArrayList<>();
                    list = new ArrayList<String>();

                    final List<UserModel> users = user_obj.getUsersByGroup(Constants.groupid);

                    for (UserModel model : users) {
                        list.add(model.getName());
                    }

                    final String[] SPINNERLIST = list.toArray(new String[list.size()]);
                    //String[] SPINNERLIST =   {"Android Material Design", "Material Design Spinner", "Spinner Using Material Library", "Material Spinner Example"};

                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_dropdown_item_1line, SPINNERLIST);

                    userspinner.setAdapter(arrayAdapter);

                    userspinner.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            String user = s.toString();
                            for (int i = 0; i < SPINNERLIST.length; i++) {
                                if (user.equalsIgnoreCase(SPINNERLIST[i])) {
                                    spinnerPosition = i;
                                    break;
                                }
                            }
                        }
                    });
                    //Building dialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setView(layout);
                    builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            expenseModel.setAmount(amount.getText().toString());
                            expenseModel.setAmountTitle(nameBox.getText().toString());
                            expenseModel.setGroupId(Constants.groupid);
                            expenseModel.setGroupName(Constants.Groupname);
                            expenseModel.setPaidUserId(users.get(spinnerPosition).getId());
                            expenseModel.setPaidUserName(users.get(spinnerPosition).getName());


                            long result = expenseTable.insertData(expenseModel);
                            //boolean result =user_table_obj.insertMember(nameBox.getText().toString(), Constants.groupid);
                            Log.d("EXRESresult", "EXRESresult " + result);
                            //  getMemberlist();
                            getexpenselist();
                            dialog.dismiss();
                            // getdata();
                            //save info where you want it

                        }

                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else
                {
                    Toast.makeText(getActivity(),"Please Add users in Trip",Toast.LENGTH_SHORT).show();
                }
            }
        });
        getexpenselist();
        return rootview;
    }
    public void onResume(){
        super.onResume();

        // Set title bar
//        ((WorkaaActivity) getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00D1FD")));
//       ((WorkaaActivity)getActivity()).updateStatusBarColor("#00b3ff");


    }

    private void getexpenselist() {
        member_list.clear();
        member_list.addAll(expenseTable.getAllExpenseByGroupId(Constants.groupid));
        /*
        if(resultSet!=null && resultSet.getCount() > 0)
        {
            Log.d("DATABASE","DATABASE"+resultSet.getCount());
            // studentList.clear();
            group_List.clear();
            if (resultSet.moveToFirst())
            {
                do {
                    Log.d("DATABASE","DATABASE"+resultSet.getString(0)+resultSet.getColumnName(0));

                    grp_model_obj = new GroupModel(resultSet.getString(0));
                    group_List.add(grp_model_obj);


                } while (resultSet.moveToNext());
            }
        }*/

        members_Adapter.notifyDataSetChanged();
        Log.d("SIZE","SIZE"+member_list.size());

    }




}