package com.example.quanlybanhang.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.quanlybanhang.R;
import com.example.quanlybanhang.dao.LoaiHangDAO;
import com.example.quanlybanhang.model.LoaiHang;

import java.util.List;

public class LoaiHangAdapter extends BaseAdapter {
    List<LoaiHang> arrLoaiHang;
    public Activity context;
    public LayoutInflater inflater;
    LoaiHangDAO loaiHangDAO;
    public LoaiHangAdapter(Activity context, List<LoaiHang> arrayLoaiHang) {
        super();
        this.context = context;
        this.arrLoaiHang = arrayLoaiHang;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        loaiHangDAO = new LoaiHangDAO(context);
    }
    @Override
    public int getCount() {
        return arrLoaiHang.size();
    }
    @Override
    public Object getItem(int position) {
        return arrLoaiHang.get(position);
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    public static class ViewHolder {
        ImageView img;
        TextView txtMaLoaiHang;
        TextView txtTenLoaiHang;
        ImageView imgDelete;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null)         {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_loai_hang, null);
            holder.img = (ImageView) convertView.findViewById(R.id.ivIcon);
            holder.txtMaLoaiHang = (TextView) convertView.findViewById(R.id.tvMaLoaiHang);
            holder.txtTenLoaiHang = (TextView) convertView.findViewById(R.id.tvTenLoaiHang);
            holder.imgDelete = (ImageView)convertView.findViewById(R.id.ivDelete);
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loaiHangDAO.deleteLoaiHangByID(arrLoaiHang.get(position).getMaLoaiHang());
                    arrLoaiHang.remove(position);
                    notifyDataSetChanged();
                }
            });
            convertView.setTag(holder);
        }
        else
            holder=(ViewHolder)convertView.getTag();
        LoaiHang _entry = (LoaiHang) arrLoaiHang.get(position);
        holder.img.setImageResource(R.drawable.icon_loaihang);
        holder.txtMaLoaiHang.setText(_entry.getMaLoaiHang());
        holder.txtTenLoaiHang.setText(_entry.getTenLoaiHang());
        return convertView;
    }
    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
    public void changeDataset(List<LoaiHang> items){
        this.arrLoaiHang = items;
        notifyDataSetChanged();
    }
}

