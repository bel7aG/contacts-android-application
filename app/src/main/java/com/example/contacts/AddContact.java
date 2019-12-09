package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                try {
                    int phone = Integer.parseInt(editPhone.getText().toString());
                    Contact contact = new Contact(name, phone);
                    dbContact.addContact(contact);
                    Toast.makeText(AddContact.this, "contact added", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddContact.this, MainActivity.class);
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(AddContact.this, "phone must be a number", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
