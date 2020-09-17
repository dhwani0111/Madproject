package com.example.patientvisit;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;


public class addVisit extends AppCompatActivity {
    public static final Pattern PHONE=
            Pattern.compile(
                    "(\\+[0-9]+[\\- \\.]*)?"
                            + "(\\([0-9]+\\)+[\\- \\.]*)?"
                            + "([0-9][0-9\\- \\.]+[0-9])");

    private EditText name;
    private EditText medical;
    private EditText add;
    private EditText phone;
    private EditText age;
    private EditText cost;
    Button save;
    RadioGroup gender;
    EditText date;
    DatePickerDialog datePickerDialog;

    String userID;
    FirebaseAuth auth;
    private FirebaseFirestore db;
    RadioButton maleRadioButton, femaleRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addvisit);

        db=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
        name=findViewById(R.id.p_name);
        add=findViewById(R.id.p_add);
        age=findViewById(R.id.p_age);
        medical=findViewById(R.id.p_medical);
        cost=findViewById(R.id.cst);
        gender=findViewById(R.id.radioGroup);
        phone=findViewById(R.id.p_phn);

        save=findViewById(R.id.p_save);
        final Boolean[] isDateChanged = {false};

        maleRadioButton=(RadioButton)findViewById(R.id.radioButton3);
        femaleRadioButton=(RadioButton)findViewById(R.id.radioButton4);

        date = (EditText) findViewById(R.id.date);
        // perform click event on edit text
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

                datePickerDialog = new DatePickerDialog(addVisit.this,
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
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Pressed line");
                String dt = date.getText().toString();
                String nm = name.getText().toString();
                String ad = add.getText().toString();
                String ag = age.getText().toString();
                String cst = cost.getText().toString();
                String med = medical.getText().toString();
                String phn = phone.getText().toString();
                String g="";
                if (maleRadioButton.isChecked()){
                    g="Male";
                }else if(femaleRadioButton.isChecked()){
                    g="Female";
                }
                //String gen=gender.;
                if (nm.isEmpty()) {
                    name.requestFocus();
                    name.setError("Field cannot be Empty");
                } else if (med.isEmpty()) {
                    medical.requestFocus();
                    medical.setError("Field cannot be Empty");
                } else if (ad.isEmpty()) {
                    add.requestFocus();
                    add.setError("Field cannot be Empty");
                } else if (phn.isEmpty()) {
                    phone.requestFocus();
                    phone.setError("Field cannot be Empty");
                } else if (phone.getText().length() != 10) {
                    phone.requestFocus();
                    phone.setError("Please enter valid phone number");

                } else if (ag.isEmpty()) {
                    age.requestFocus();
                    age.setError("Field cannot be Empty");
                } else if (cst.isEmpty()) {
                    cost.requestFocus();
                    cost.setError("Field cannot be Empty");
                } else if (dt.isEmpty()) {
                    date.requestFocus();
                    date.setError("Field cannot be Empty");

                }
                else
                {
                    userID=auth.getCurrentUser().getUid();
                    DocumentReference dbProducts=db.collection(userID).document();
                    Map<String,Object> user=new HashMap<>();
                    user.put("name",nm);
                    user.put("medical_description",med);
                    user.put("address",ad);
                    user.put("phone_no",phn);
                    user.put("cost",cst);
                    user.put("age",ag);
                    user.put("starting_date",dt);
                    user.put("gender", g );

                    dbProducts.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d("","Patient details has been added!!"+userID);
                            Intent intent=new Intent(addVisit.this,HomeActivity.class);

                           finish();
                        }
                    });
//                    patient patient=new patient(
//                            nm,
//                            med,
//                            ad,
//                            ag,
//                            phn,
//                            cst,
//                            dt
//
//                    );
//                    dbProducts.add(patient).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                        @Override
//                        public void onSuccess(DocumentReference documentReference) {
//                            Toast.makeText(addVisit.this,"Patient deatils added",Toast.LENGTH_LONG).show();
//                            Intent intent=new Intent(addVisit.this,HomeActivity.class);
//                            startActivity(intent);
//                        }
//                    }).addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Toast.makeText(addVisit.this,e.getMessage(),Toast.LENGTH_LONG).show();
//                        }
//                    });

                }
            }
        });
    }
}