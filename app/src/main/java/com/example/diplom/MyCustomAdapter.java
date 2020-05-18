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
import java.util.Map;

import static com.example.diplom.ListActivity.KEY1;
import static com.example.diplom.ListActivity.KEY2;
import static com.example.diplom.ListActivity.KEY3;


public class MyCustomAdapter extends BaseAdapter implements ListAdapter {


    private List<Map<String, String>> mValues;
    private Context mContext;


    public MyCustomAdapter(List<Map<String, String>> values, Context applicationContext) {
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

        Map<String, String> map = mValues.get(i);
        TextView title = view.findViewById(R.id.TextOne);
        TextView subtitle = view.findViewById(R.id.TextTwo);
        TextView date = view.findViewById(R.id.TextThree);
        title.setText(map.get(KEY1));
        subtitle.setText(map.get(KEY2));
        date.setText(map.get(KEY3));
        Date temp = DateUtil.StringToDate(map.get(KEY3));
        Date now = DateUtil.clockReset(new Date());


        if (temp != null) {
            if (DateUtil.clockReset(temp).toString().equals(now.toString())) {
                title.setBackgroundColor(Color.YELLOW);
                subtitle.setBackgroundColor(Color.YELLOW);
                date.setBackgroundColor(Color.YELLOW);
            } else if (temp.before(now)) {
                title.setBackgroundColor(Color.RED);
                subtitle.setBackgroundColor(Color.RED);
                date.setBackgroundColor(Color.RED);
            }

        }


        return view;
    }

}
