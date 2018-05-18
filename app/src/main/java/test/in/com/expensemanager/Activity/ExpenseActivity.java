package test.in.com.expensemanager.Activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import test.in.com.expensemanager.Adapter.ExpenseAdapter;
import test.in.com.expensemanager.Components.NonSwipeableViewPager;
import test.in.com.expensemanager.R;

public class ExpenseActivity extends AppCompatActivity {


    private final int numOfPages = 2; //viewpager has 4 pages
    private final String[] pageTitle = {"Members", "Expense"};
    TextView mBackIcon;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflate the layout for this fragment
        setContentView(R.layout.fragment_report);
        mBackIcon = (TextView) findViewById(R.id.back_icon);


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        for (int i = 0; i < numOfPages; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(pageTitle[i]));
        }

        //set gravity for tab bar
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final NonSwipeableViewPager viewPager = (NonSwipeableViewPager) findViewById(R.id.pager);
        final ExpenseAdapter adapter = new ExpenseAdapter
                (getSupportFragmentManager(), numOfPages);

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(onTabSelectedListener(viewPager));
        viewPager.setCurrentItem(0, false);
        mBackIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }



    public void onResume() {
        super.onResume();


    }

    private TabLayout.OnTabSelectedListener onTabSelectedListener(final ViewPager pager) {
        return new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        };


    }


}