package com.example.patientvisit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class options extends AppCompatActivity {

    Button update,delete,visit;
    product p;
    FirebaseAuth auth;
    String userId;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        update=findViewById(R.id.p_update);
        delete=findViewById(R.id.delete);
        visit=findViewById(R.id.p_visit);
        auth = FirebaseAuth.getInstance();
        userId = auth.getCurrentUser().getUid();
        db = FirebaseFirestore.getInstance();
        p = (product) getIntent().getSerializableExtra("product");
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(options.this, Updatevisit.class);
                intent.putExtra("product",p);
                startActivity(intent);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection(userId).document(p.getId()).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(options.this,"Visit Deleted",Toast.LENGTH_LONG).show();
                            finish();
                            startActivity(new Intent(options.this,HomeActivity.class));
                        }
                    }
                });
            }
        });
        visit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(options.this, visitDate.class);
                intent.putExtra("product",p);
                startActivity(intent);
            }
        });

    }
}
