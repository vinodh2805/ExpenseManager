package test.in.com.expensemanager.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import test.in.com.expensemanager.Fragments.ExpenseTabFragment;

public class ExpenseAdapter extends FragmentStatePagerAdapter {
    int numOfTabs;

    public ExpenseAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;

    }

    @Override
    public Fragment getItem(int position) {

        return ExpenseTabFragment.getInstance(position);
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
