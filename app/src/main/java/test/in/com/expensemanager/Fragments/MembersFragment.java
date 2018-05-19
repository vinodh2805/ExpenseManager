package test.in.com.expensemanager.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import test.in.com.expensemanager.Adapter.MembersAdapter;
import test.in.com.expensemanager.Components.RecyclerTouchListener;

import test.in.com.expensemanager.Constants.Constants;
import test.in.com.expensemanager.Database.model.UserModel;
import test.in.com.expensemanager.Database.table.UserTable;
import test.in.com.expensemanager.R;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class MembersFragment extends Fragment {
    View rootview;
    MembersAdapter members_Adapter;
    RecyclerView my_recycler_view;
    private List<UserModel> member_list;
    UserModel members_model_obj;
    UserTable user_table_obj;
    FloatingActionButton member_add_button;
    String groupid;
    public MembersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        rootview = inflater.inflate(R.layout.memberslist_layout, container, false);

        member_list = new ArrayList<UserModel>();
        user_table_obj = new UserTable(getActivity());
        my_recycler_view = (RecyclerView) rootview.findViewById(R.id.my_recycler_view);
        member_add_button = (FloatingActionButton) rootview.findViewById(R.id.member_add_button);
        my_recycler_view.setHasFixedSize(true);

        // use a linear layout manager
        my_recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()));

        // create an Object for Adapter

        members_Adapter = new MembersAdapter(member_list);

        // set the adapter object to the Recyclerview
        my_recycler_view.setAdapter(members_Adapter);
        my_recycler_view.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), my_recycler_view, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                UserModel group = member_list.get(position);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        member_add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.group_create_form, (ViewGroup) getActivity().findViewById(R.id.layout_root));
//layout_root should be the name of the "top-level" layout node in the dialog_layout.xml file.
                final EditText nameBox = (EditText) layout.findViewById(R.id.name);


                //Building dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setView(layout);
                builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        nameBox.getText();
                        if(!user_table_obj.isUserNameAlreadyExists(nameBox.getText().toString(),Constants.groupid)) {
                            boolean result = user_table_obj.insertMember(nameBox.getText().toString(), Constants.groupid);


                            getMemberlist();
                        }
                        else
                        {
                            Toast.makeText(getActivity(),"User already exists",Toast.LENGTH_SHORT).show();
                        }
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
        });
        getMemberlist();
        return rootview;
    }
    public void onResume(){
        super.onResume();

        // Set title bar
//        ((WorkaaActivity) getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00D1FD")));
//       ((WorkaaActivity)getActivity()).updateStatusBarColor("#00b3ff");


    }

    private void getMemberlist() {
        member_list.clear();
        member_list.addAll(user_table_obj.getUsersByGroup(Constants.groupid));
        Log.d("COUNT","COUNT"+member_list.size());
        Log.d("COUNT","COUNT"+Constants.groupid);
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