package com.example.patientvisit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;

public class visitDate extends AppCompatActivity {
    EditText date,amount;
    Button add;
    DatePickerDialog datePickerDialog;
    String date2,date1,userId;
    product p;
    FirebaseAuth auth;
    FirebaseFirestore db;
    int total,cost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_date);
        date=findViewById(R.id.date);
        add=findViewById(R.id.add_date);
        amount=findViewById(R.id.amount);
        p = (product) getIntent().getSerializableExtra("product");
        auth = FirebaseAuth.getInstance();
        userId = auth.getCurrentUser().getUid();
        db = FirebaseFirestore.getInstance();
        date1 = p.getDate();
        final Boolean[] isDateChanged = {false};
        total = Integer.parseInt(p.getTotal_amount());
        cost = Integer.parseInt(p.getCost());
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Date picked");
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                isDateChanged[0] =true;

                datePickerDialog = new DatePickerDialog(visitDate.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                date.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);


                            }
                        }, mYear, mMonth, mDay);

                datePickerDialog.show();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                date2 = date.getText().toString();


                if(date2.equals(date1)) {
                    Toast.makeText(visitDate.this,"date1:"+p.getStarting_date()+" date2:"+date2,Toast.LENGTH_SHORT).show();
                    amount.setText(p.getTotal_amount());
                } else {

                    total += cost;
                    String t = ""+total;
                    amount.setText(t);
                    db.collection(userId).document(p.getId()).update("total_amount",t,
                            "visit_date",date2
                            );

                }
            }
        });
    }
}
