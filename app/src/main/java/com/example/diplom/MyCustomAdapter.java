package com.example.diplom;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.Date;
import java.util.List;


public class MyCustomAdapter extends BaseAdapter implements ListAdapter {


    private List<Note> mValues;
    private Context mContext;


    public MyCustomAdapter(List<Note> values, Context applicationContext) {
        mValues = values;
        mContext = applicationContext;
    }

    @Override
    public int getCount() {
        return mValues.size();
    }

    @Override
    public Object getItem(int i) {
        return mValues.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_layout, null);
        }

        Note note = mValues.get(i);
        TextView title = view.findViewById(R.id.TextOne);
        TextView subtitle = view.findViewById(R.id.TextTwo);
        TextView date = view.findViewById(R.id.TextThree);
        title.setText(note.getHeading());
        subtitle.setText(note.getBody());
        date.setText(DateUtil.DateToString(note.getDate()));
        Date temp = note.getDate();
        Date now = DateUtil.clockReset(new Date());


        if (temp != null) {
            if (DateUtil.clockReset(temp).toString().equals(now.toString())) {
                view.setBackgroundColor(Color.YELLOW);
            } else if (temp.before(now)) {
                view.setBackgroundColor(Color.RED);
            } else {
                view.setBackground(null);
            }

        }


        return view;
    }

}
