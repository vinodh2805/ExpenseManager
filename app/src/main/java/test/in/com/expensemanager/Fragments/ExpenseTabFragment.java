package test.in.com.expensemanager.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import test.in.com.expensemanager.R;

public class ExpenseTabFragment extends Fragment {

    private int position;
    private TextView content;
    private ImageView image;


    public static Fragment getInstance(int position) {
        if(position==0) {
            MembersFragment f = new MembersFragment();
            Bundle args = new Bundle();
            args.putInt("position", position);
            f.setArguments(args);
            return f;
        }
        else
        {
            ExpenseCountFragment f = new ExpenseCountFragment();
            Bundle args = new Bundle();
            args.putInt("position", position);
            f.setArguments(args);
            return f;
        }


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get data from Argument
        position = getArguments().getInt("position");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        image = (ImageView) view.findViewById(R.id.image);
        content = (TextView) view.findViewById(R.id.textView);
        setContentView();
    }

    private void setContentView() {
        if (position == 0) {
            //food fragment
            // image.setImageResource(R.mipmap.food);
            content.setText("This is Food Fragment");
        }  else  {
            //shopping fragment
            // image.setImageResource(R.mipmap.shopping);
            content.setText("This is Travel Fragment");
        }
    }
}