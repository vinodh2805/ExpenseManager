package test.in.com.expensemanager.Activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.transition.Fade;
import android.support.transition.TransitionManager;
import android.support.transition.TransitionSet;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

import test.in.com.expensemanager.Adapter.CardViewDataAdapter;
import test.in.com.expensemanager.Components.RecyclerTouchListener;
import test.in.com.expensemanager.Constants.Constants;
import test.in.com.expensemanager.Database.model.GroupModel;
import test.in.com.expensemanager.Database.table.GroupTable;

import test.in.com.expensemanager.R;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<test.in.com.expensemanager.Database.model.GroupModel> group_List;
    GroupTable group_table_obj;

    Cursor resultSet;
    FloatingActionButton group_create_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        group_create_button = (FloatingActionButton) findViewById(R.id.group_create_button);
        group_List = new ArrayList<GroupModel>();
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        group_table_obj = new GroupTable(MainActivity.this);
        // use a linear layout manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // create an Object for Adapter

        mAdapter = new CardViewDataAdapter(group_List);

        // set the adapter object to the Recyclerview
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, mRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                GroupModel group = group_List.get(position);
                //Toast.makeText(getApplicationContext(),""+group.getId(),Toast.LENGTH_SHORT).show();

                Intent expense = new Intent(MainActivity.this,ExpenseActivity.class);
                Constants.groupid=group.getId();
                Constants.Groupname=group.getGroupName();
                startActivity(expense);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        getGrouplist();

        group_create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.group_create_form, (ViewGroup) findViewById(R.id.layout_root));
//layout_root should be the name of the "top-level" layout node in the dialog_layout.xml file.
                final EditText nameBox = (EditText) layout.findViewById(R.id.name);


                //Building dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setView(layout);
                builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        nameBox.getText();
                        group_table_obj.insertGroup(nameBox.getText().toString());
                        //mydatabase.execSQL("INSERT INTO members VALUES('"+nameBox.getText()+"');");
                        getGrouplist();
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
    }

    private void getGrouplist() {
        group_List.clear();
        group_List.addAll(group_table_obj.getAllGroups());



        mAdapter.notifyDataSetChanged();
        Log.d("SIZE","SIZE"+group_List.size());

    }

}
