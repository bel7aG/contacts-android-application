package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView contactList;

    Button addButton;

    DbContact dbContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ListView
        setContentView(R.layout.activity_main);
        contactList = findViewById(R.id.contactList);

        dbContact = new DbContact(this);

        ArrayList<Contact> contacts = dbContact.getAllContacts();


        ContactAdapter contactAdapter = new ContactAdapter(this, R.layout.item_contact, contacts);

        contactList.setAdapter(contactAdapter);


        addButton = (Button) findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddContact.class);

                startActivity(intent);
            }
        });


        contactList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, UpdateContact.class);
                startActivity(intent);
            }
        });



    }
}
