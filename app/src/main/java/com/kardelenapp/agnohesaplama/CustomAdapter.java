package com.kardelenapp.agnohesaplama;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class CustomAdapter extends ArrayAdapter<CustomListItem> {
    Context context;
    int layoutResourceId;
    ArrayList<CustomListItem> data = new ArrayList<CustomListItem>();
    int mode;


    public CustomAdapter(Context context, int layoutResourceId,
                             ArrayList<CustomListItem> data, int mode) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
        this.mode = mode;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        UserHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new UserHolder();
            holder.textName = (EditText) row.findViewById(R.id.editTextList1);
            holder.textPuan = (Spinner) row.findViewById(R.id.spinner3);
            holder.textKredi = (Spinner) row.findViewById(R.id.spinner2);

            holder.btnDelete = (Button) row.findViewById(R.id.button2);

            row.setTag(holder);
        } else {
            holder = (UserHolder) row.getTag();
        }
        final CustomListItem user = data.get(position);
        holder.textName.setText(user.getName());

        String[] harf = new String[]{};
        if (mode ==1)
        {
            harf = new String[]{"AA", "BA", "BB", "CB", "CC", "DC", "DD", "FD", "FF", "G"};
        }
        else if  (mode ==2)
        {
            harf = new String[]{"A1", "A2", "A3", "B1", "B2", "B3", "C1", "C2", "C3", "F3", "F2", "F1"};
        }
        else if  (mode ==3)
        {
            harf = new String[]{"A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "DF", "FX", "FZ"};
        }
        else
        {
            harf = new String[]{"AA", "BA", "BB", "CB", "CC", "DC", "DD", "FD", "FF", "G"};
        }




        String[] kredi = new String[]{"0.5", "1", "1.5", "2", "2.5", "3", "3.5", "4", "4.5", "5", "5.5", "6", "6.5", "7", "7.5", "8", "8.5", "9", "9.5", "10", "10.5", "11", "11.5", "12", "12.5", "13", "13.5", "14", "14.5", "15"};
        ArrayAdapter<String> harfadapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, harf);
        ArrayAdapter<String> krediapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, kredi);

        holder.textPuan.setAdapter(harfadapter);
        holder.textKredi.setAdapter(krediapter);

        //setText( Float.toString( user.getPuan()));
        //holder.textKredi.setAdapter(krediapter);
        holder.textPuan.setSelection(user.getPuan());
        holder.textKredi.setSelection(user.getKredi());

        holder.btnDelete.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //Log.i("Delete Button Clicked", "**********");
                //Toast.makeText(context, "Delete button Clicked", Toast.LENGTH_LONG).show();

                DersEkleme.removeListItem(user);

            }
        });


        final UserHolder finalHolder = holder;
        holder.textName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){

                }else {
                    user.setName(String.valueOf(finalHolder.textName.getText()));
                }
            }
        });


        holder.textPuan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                user.setPuan(finalHolder.textPuan.getSelectedItemPosition());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        holder.textKredi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                user.setKredi(finalHolder.textKredi.getSelectedItemPosition());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        return row;

    }

    static class UserHolder {
        EditText textName;
        Spinner textPuan;
        Spinner textKredi;
        int index;


        Button btnDelete;
    }
}