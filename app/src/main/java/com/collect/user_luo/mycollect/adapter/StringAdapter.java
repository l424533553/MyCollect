package com.collect.user_luo.mycollect.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.collect.user_luo.mycollect.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StringAdapter extends BaseAdapter {
    private Context context;
    private List<String> data;

    public StringAdapter(Context context, List<String> data) {
        this.context = context;
        this.data = data;
    }

    public StringAdapter(Context context, Map<String, Class> map) {
        this.context = context;
        if (map != null) {
            data = new ArrayList<>();
            data.addAll(map.keySet());
        }
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_item_string, null);
            viewHolder.tvIndex = convertView.findViewById(R.id.tvIndex);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvIndex.setText(data.get(position));

        return convertView;
    }

    class ViewHolder {
        private TextView tvSamplenum;
        private TextView tvIndex;
    }
}
