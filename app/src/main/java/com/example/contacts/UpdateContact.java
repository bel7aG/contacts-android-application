package com.example.contacts;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateContact extends AppCompatActivity {
    Button updateButton, deleteButton;
    EditText updateName, updatePhone;

    DbContact dbContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact);

        int id = getIntent().getIntExtra("id", 0);

        dbContact = new DbContact(this);

        Contact contact = dbContact.getContactById(id);

        updateName = (EditText) findViewById(R.id.updateName);
        updatePhone = (EditText) findViewById(R.id.updatePhone);

        updateName.setText(contact.getName());
        updatePhone.setText(contact.getPhone() + "");

        updateButton = (Button) findViewById(R.id.updateButton);
        deleteButton = (Button) findViewById(R.id.deleteButton);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlert();
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = getIntent().getIntExtra("id", 0);
                try {
                    int phone = Integer.parseInt(updatePhone.getText().toString());
                    String name = updateName.getText().toString();
                    Contact contact = new Contact(id, name, phone);
                    dbContact.updateContact(contact);
                    Toast.makeText(UpdateContact.this, "contact succesfully updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UpdateContact.this, MainActivity.class);
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(UpdateContact.this, "phone must be a number", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void showAlert() {
        AlertDialog.Builder myAlert = new AlertDialog.Builder(this);
        myAlert.setTitle("Confirmation")
                .setMessage("Are you sure you want to delete this contact?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int id = getIntent().getIntExtra("id", 0);

                        dbContact.deleteContact(id);

                        Intent intent = new Intent(UpdateContact.this, MainActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

        AlertDialog dialog = myAlert.create();
        dialog.show();
    }




}
