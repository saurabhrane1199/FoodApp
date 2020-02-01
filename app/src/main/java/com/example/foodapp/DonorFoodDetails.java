package com.example.foodapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodapp.Models.donations;
import com.example.foodapp.helpingClasses.statics;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Date;

public class DonorFoodDetails extends AppCompatActivity {

    EditText userName;
    EditText userContact;
    EditText foodDesc;
    EditText userNote;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_food_details);


    }

    public void writeDonation(String name,String contact,String desc,String note,Integer novol){
        DatabaseReference donation = FirebaseDatabase.getInstance().getReference().child("donations");
        String key = donation.push().getKey();
        String dateTime = DateFormat.getDateTimeInstance().format(new Date());
        donation.push().setValue(new donations(contact,desc, statics.currLat,statics.currLong,name,novol,dateTime));
    }

    public void onPostBtnClicked(View view){

        String url = "https://foodapp-fa08e.firebaseio.com/donations.json";

        //Name
        userName = findViewById(R.id.userName);
        final String userNameIn = userName.getText().toString();

        //Contact
        userContact = findViewById(R.id.userContact);
        final String userContactIn = userContact.getText().toString();

        //Description
        foodDesc = findViewById(R.id.foodDesc);
        final String foodDescIn = foodDesc.getText().toString();

        //Note
        userNote = findViewById(R.id.userNote);
        final String userNotIn = userNote.getText().toString();

        String tet = userContactIn+userNameIn+userNotIn;
        Toast.makeText(getApplicationContext(),tet,Toast.LENGTH_SHORT).show();

        writeDonation(userNameIn,userContactIn,foodDescIn,userNotIn,4);
    }

}
