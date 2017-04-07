package it.polito.cowapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.*;

public class GroupActivity extends AppCompatActivity {

    private ListView list;
    private List<ExpenseItem> expenses;
    private List<DebitsItem> debits;


    // these arrays of strings are used to populate the List "expenses" and "debits" that are then shown in the app
    // they will be then substituted by the data coming from the database
    private String[] expenses_base = {"Expense1", "Expense2", "Expense3", "Expense4", "Expense5"};
    private String[] value_expenses_base = {"13.50€", "1.50€", "21.00€", "8.30€", "15.00€"};
    private String[] names_base = {"Michela", "Matteo", "Simona", "Greta", "Agnese","Stefania","Elena","Martina","Laura","Monica","Letizia"};
    private String description = "Lorem ipsum dolor sit amet, ...";
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    list = (ListView) findViewById(R.id.lv_expenses);
                    BaseAdapter bae = getAdapterExpenses();
                    list.setAdapter(bae);
                    break;

                case R.id.navigation_dashboard:
                    list = (ListView) findViewById(R.id.lv_expenses);
                    BaseAdapter bad = getAdapterDebit();
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

        for (int i=0;i<3;i++){
            ExpenseItem expense_tmp = new ExpenseItem(expenses_base[i],value_expenses_base[i],description);
            expense_tmp.setAuthor(names_base[i]);
            expenses.add(expense_tmp);
            DebitsItem debit_tmp = new DebitsItem(names_base[i],value_expenses_base[i]);
            debits.add(debit_tmp);
        }

        list = (ListView) findViewById(R.id.lv_expenses);
        BaseAdapter bae = getAdapterExpenses();
        list.setAdapter(bae);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),ExpenseCreation.class);
                startActivityForResult(intent,1);
            }
        });
        fab.show();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                String str_name = data.getStringExtra("name");
                String str_import = data.getStringExtra("import")+"€";
                String str_description = data.getStringExtra("description");
                String str_author = data.getStringExtra("author");
                ExpenseItem expense_tmp = new ExpenseItem(str_name,str_import,str_description);
                expense_tmp.setAuthor(str_author);
                expenses.add(expense_tmp);

            }
        }
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
                TextView tv = (TextView) convertView.findViewById(R.id.expense_name);
                tv.setText(expenses.get(position).getName());
                tv = (TextView) convertView.findViewById(R.id.expense_import);
                tv.setText(expenses.get(position).getValue());
                tv = (TextView) convertView.findViewById(R.id.expense_description);
                tv.setText(expenses.get(position).getDescription());

                FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
                fab.show();
                return convertView;
            }
        };
        return bae;
    }

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

                TextView tv = (TextView) convertView.findViewById(R.id.summary_name);
                tv.setText(expenses.get(position).getAuthor());
                tv = (TextView) convertView.findViewById(R.id.summary_import);
                tv.setText("-"+expenses.get(position).getValue());
                FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
                fab.hide();
                return convertView;
            }
        };
        return bad;
    }



}