package com.example.contacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ContactAdapter extends ArrayAdapter<Contact> {


    Context context;
    int resource;

    public ContactAdapter(@NonNull Context context, int resource, @NonNull List<Contact> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, parent, false);

        TextView nameTv = (TextView) convertView.findViewById(R.id.nameTV);
        TextView phoneTV = (TextView) convertView.findViewById(R.id.phoneTV);

        Contact currentContact = getItem(position);

        nameTv.setText(currentContact.getName());
        phoneTV.setText(String.valueOf(currentContact.getPhone()));

        return convertView;
    }
}
