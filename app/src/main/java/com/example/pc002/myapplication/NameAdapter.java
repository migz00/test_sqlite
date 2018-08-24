package com.example.pc002.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class NameAdapter extends ArrayAdapter<ArrayList> {
    private ArrayList<String> names = null;
    private Context context = null;
    private int LayoutRes = -1;

    public NameAdapter(@NonNull Context context, int resource, @NonNull ArrayList names) {
        super(context, resource, names);
        this.names = names;
        this.context = context;
        LayoutRes = resource;
    }

    public String getIt(int position) {
        return names.get(position);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String name = getIt(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View namelist = inflater.inflate(LayoutRes, parent, false);

        TextView tv = namelist.findViewById(R.id.tv_names);
        tv.setText(name);
        return namelist;
    }

}
