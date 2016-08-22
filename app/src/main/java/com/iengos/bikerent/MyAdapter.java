package com.iengos.bikerent;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Dave on 12/07/2016.
 */
public class MyAdapter extends BaseAdapter {

    Context context;
    ArrayList <InfoRow> data;
    private static LayoutInflater inflater = null;

    public MyAdapter(Context context, ArrayList <InfoRow> data) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.activity_listview, null);

        InfoRow CurrentRow = data.get(position);
        TextView Date = (TextView) vi.findViewById(R.id.Date);
        Date.setText(CurrentRow.Date);

        TextView Number = (TextView) vi.findViewById(R.id.Number);
        Number.setText(CurrentRow.Number);

        TextView Status = (TextView) vi.findViewById(R.id.Status);

        if(CurrentRow.Status.equals("Terminato"))
            Status.setTextColor(Color.parseColor("#9D1309"));

        Status.setText(CurrentRow.Status);
        return vi;
    }
}
