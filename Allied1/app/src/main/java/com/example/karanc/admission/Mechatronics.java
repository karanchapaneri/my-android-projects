package com.example.karanc.admission;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.karanc.Branch_Classes.Coll_with_Marine;
import com.example.karanc.Branch_Classes.Coll_with_Mecha;
import com.example.karanc.allied1.R;

public class Mechatronics extends AppCompatActivity {
    Button collbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechatronics);

        collbtn= (Button) findViewById(R.id.Coll_mecha);
        collbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), Coll_with_Mecha.class);
                startActivity(intent);
            }
        });

    }
}
