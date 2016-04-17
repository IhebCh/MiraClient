package com.itech.miraclient;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;

import java.util.Date;

public class AddNew extends AppCompatActivity {
    Firebase refPills,help;
    EditText numberOfTimes ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);
        final EditText name = (EditText) findViewById(R.id.addNew_medicine_name);
         numberOfTimes = (EditText) findViewById(R.id.addNew_times);
        EditText first = (EditText) findViewById(R.id.addNew_first);
        EditText second = (EditText) findViewById(R.id.addNew_second);
        final EditText third = (EditText) findViewById(R.id.addNew_third);
        Button image = (Button) findViewById(R.id.addNew_photo);




        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 10);
            }
        });

        Button add = (Button) findViewById(R.id.addNew_add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Medecine medecine = new Medecine();
                medecine.setTokken(false);
                try {

                    medecine.setDose(Integer.parseInt(numberOfTimes.getText().toString()));
                    medecine.setName(name.getText().toString());
                }

                catch (Exception e ){

                    medecine.setDose(5);
                    medecine.setName("Penicillin");

                }

                medecine.setPillTime(new Date());

                pushData(medecine);

                startActivity(new Intent(AddNew.this, MainActivity.class));
            }
        });


        Firebase.setAndroidContext(this);

        refPills = new Firebase("https://miraapp.firebaseio.com/android/saving-data/miraPills");

    }

    public void pushData(Medecine pillSchedule) {

        refPills.push().setValue(pillSchedule);

    }


}
