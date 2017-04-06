package it.polito.cowapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ExpenseCreation extends AppCompatActivity implements View.OnClickListener{

    private Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_creation);

        /*EditText et_name = (EditText)findViewById(R.id.expense_name);
        EditText et_description = (EditText)findViewById(R.id.expense_description);
        EditText et_import = (EditText)findViewById(R.id.expense_import);*/



        bt = (Button) findViewById(R.id.expense_button);
        bt.setOnClickListener(this);

    }

    public void onClick(View v){
        EditText et_name = (EditText)findViewById(R.id.expense_name);
        EditText et_description = (EditText)findViewById(R.id.expense_description);
        EditText et_import = (EditText)findViewById(R.id.expense_import);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //DatabaseReference myRef = database.getReference("Chiara");
        //myRef.push();
        ListView list = (ListView) findViewById(R.id.lv_expenses);
        ((BaseAdapter) list.getAdapter()).notifyDataSetChanged();

    }
}
