package com.haikalharin.damrinew.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.haikalharin.damrinew.Entity.DataItem;
import com.haikalharin.damrinew.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;

public class ListArrayAdapter extends ArrayAdapter<DataItem> {
    private ArrayList<DataItem> list;
    private LayoutInflater inflater;
    private int res;
    public ListArrayAdapter(@NonNull Context context, int resource, ArrayList<DataItem> list) {
        super(context, resource, list);
        this.list = list;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.res = resource;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        MyHolder holder = null;


        if (convertView == null) {

            convertView = inflater.inflate(res, parent, false);

            holder = new MyHolder();

            holder.asal = (TextView) convertView.findViewById(R.id.tv_asal);
            holder.nm_asal = (TextView) convertView.findViewById(R.id.tv_nm_asal);
            holder.nm_kota = (TextView) convertView.findViewById(R.id.tv_nm_kota);
            holder.latloc = (TextView) convertView.findViewById(R.id.tv_latloc);
            holder.longloc = (TextView) convertView.findViewById(R.id.tv_longloc);
            holder.alamat = (TextView) convertView.findViewById(R.id.tv_alamat);

            convertView.setTag(holder);

        } else {

            holder = (MyHolder) convertView.getTag();
        }

        holder.asal.setText(list.get(position).getAsal());
        holder.nm_asal.setText(list.get(position).getNmAsal());
        holder.nm_kota.setText(list.get(position).getNmKota());
        holder.latloc.setText(list.get(position).getLatloc());
        holder.longloc.setText(list.get(position).getLongloc());
        holder.alamat.setText(list.get(position).getAsal());

        return convertView;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public void remove(DataItem object) {
        super.remove(object);
    }

    @Override
    public void clear() {
        super.clear();
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    static class MyHolder {

        TextView asal;
        TextView nm_asal;
        TextView nm_kota;
        TextView latloc;
        TextView longloc;
        TextView alamat;


    }
}



