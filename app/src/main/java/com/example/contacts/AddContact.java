package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddContact extends AppCompatActivity {
    EditText editName, editPhone;
    Button addButton;

    DbContact dbContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        dbContact = new DbContact(this);

        addButton = (Button) findViewById(R.id.addContact);
        editName = (EditText) findViewById(R.id.addName);
        editPhone = (EditText) findViewById(R.id.addPhone);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String name = editName.getText().toString();
                int phone = Integer.parseInt(editPhone.getText().toString());

                Contact contact = new Contact(name, phone);

                dbContact.addContact(contact);
            }
        });

    }
}
