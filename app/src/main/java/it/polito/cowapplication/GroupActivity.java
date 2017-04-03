package it.polito.cowapplication;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.*;


public class GroupActivity extends AppCompatActivity {

    private ListView list;
    private List<ExpenseItem> expenses;
    private List<DebitsItem> debits;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    list = (ListView) findViewById(R.id.lv_expenses);
                    BaseAdapter bae = getAdapterExpenses();
                    //bae.registerDataSetObserver(getObserverExpenses());
                    list.setAdapter(bae);
                    break;

                case R.id.navigation_dashboard:
                    list = (ListView) findViewById(R.id.lv_expenses);
                    BaseAdapter bad = getAdapterDebit();
                    //DataSetObserver observer1 = new DataSetObserver() {};
                    //bad.registerDataSetObserver(observer1);
                    list.setAdapter(bad);
                    break;
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        expenses = new ArrayList<>();
        debits = new ArrayList<>();

        for (int i=0;i<5;i++)
            debits.add(new DebitsItem("Person " + i, String.valueOf(2.1*i)));

        list = (ListView) findViewById(R.id.lv_expenses);
        BaseAdapter bae = getAdapterExpenses();
        //bae.registerDataSetObserver(getObserverExpenses());
        list.setAdapter(bae);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExpenseItem exp = new ExpenseItem("Expense "+expenses.size(),String.valueOf(2.3*expenses.size()));
                expenses.add(exp);
                ((BaseAdapter) list.getAdapter()).notifyDataSetChanged();
            }
        });
        fab.show();
    }

    protected BaseAdapter getAdapterExpenses() {
        BaseAdapter bae = new BaseAdapter() {
            @Override
            public int getCount() {
                return expenses.size();
            }

            @Override
            public Object getItem(int position) {
                return expenses.get(position);
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null)
                    convertView = getLayoutInflater().inflate(R.layout.expense_item, parent, false);
                TextView tv = (TextView) convertView.findViewById(R.id.tv_expense);
                tv.setText(expenses.get(position).getName());
                tv = (TextView) convertView.findViewById(R.id.personal_column2);
                tv.setText(expenses.get(position).getValue());
                FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
                fab.show();
                return convertView;
            }
        };
        return bae;
    }
        //list.setAdapter(new ArrayAdapter<String>(this, R.layout.expense_item,expensesList));
         /*ListAdapter lae = new ListAdapter() {
            @Override
            public boolean areAllItemsEnabled() {return true;}
            @Override
            public boolean isEnabled(int position) {return true;}
            @Override
            public void registerDataSetObserver(DataSetObserver observer) {  }
            @Override
            public void unregisterDataSetObserver(DataSetObserver observer) { }
            @Override
            public int getCount() {return expenses.size(); }
            @Override
            public Object getItem(int position) {return expenses.get(position); }
            @Override
            public long getItemId(int position) {return 0;}
            @Override
            public boolean hasStableIds() {return false;}
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null)
                    convertView = getLayoutInflater().inflate(R.layout.expense_item, parent, false);
                TextView tv = (TextView) convertView.findViewById(R.id.tv_expense);
                tv.setText(expenses.get(position).getName());
                tv = (TextView) convertView.findViewById(R.id.personal_column2);
                tv.setText(expenses.get(position).getValue());
                FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
                fab.show();
                return convertView;
            }
            @Override
            public int getItemViewType(int position) {return 0;}
            @Override
            public int getViewTypeCount() {return 0;}
            @Override
            public boolean isEmpty() {
                if (expenses.size()==0){
                    return true;} else{
                    return false;}
            }
        };*/

protected BaseAdapter getAdapterDebit() {
    BaseAdapter bad = new BaseAdapter() {
        @Override
        public int getCount() {
            return debits.size();
        }

        @Override
        public Object getItem(int position) {
            return debits.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null)
                convertView = getLayoutInflater().inflate(R.layout.personal_summary_item, parent, false);
            TextView tv = (TextView) convertView.findViewById(R.id.personal_column1);
            tv.setText(debits.get(position).getName());
            tv = (TextView) convertView.findViewById(R.id.personal_column2);
            tv.setText(debits.get(position).getValue());
            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.hide();
            return convertView;
        }
    };
    return bad;
}
    /*ListAdapter lad = new ListAdapter() {
        @Override
        public boolean areAllItemsEnabled() {return true; }
        @Override
        public boolean isEnabled(int position) {return true;}
        @Override
        public void registerDataSetObserver(DataSetObserver observer) { }
        @Override
        public void unregisterDataSetObserver(DataSetObserver observer) {  }
        @Override
        public int getCount() {return debits.size();}
        @Override
        public DebitsItem getItem(int position) { return debits.get(position);}
        @Override
        public long getItemId(int position) {return 0;}
        @Override
        public boolean hasStableIds() {return false;}
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null)
                convertView = getLayoutInflater().inflate(R.layout.personal_summary_item, parent, false);
            TextView tv = (TextView) convertView.findViewById(R.id.personal_column1);
            tv.setText(debits.get(position).getName());
            tv = (TextView) convertView.findViewById(R.id.personal_column2);
            tv.setText(debits.get(position).getValue());
            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.hide();
            return convertView;
        }
        @Override
        public int getItemViewType(int position) {return 0;}
        @Override
        public int getViewTypeCount() {return 0;}
        @Override
        public boolean isEmpty() {
            if (debits.size()==0)
                return true;
            return false;
        }
    };*/

}
