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
import com.example.quanlybanhang.dao.NguoiDungDAO;
import com.example.quanlybanhang.model.NguoiDung;

import java.util.List;

public class NguoiDungAdapter extends BaseAdapter {
    List<NguoiDung> arrNguoiDung;
    public Activity context;
    public LayoutInflater inflater;
    NguoiDungDAO nguoiDungDAO;
    public NguoiDungAdapter(Activity context, List<NguoiDung> arrayNguoiDung) {
        super();
        this.context = context;
        this.arrNguoiDung = arrayNguoiDung;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        nguoiDungDAO = new NguoiDungDAO(context);     }
        @Override
        public int getCount() {
        return arrNguoiDung.size();     }
       @Override
    public Object getItem(int position) {
        return arrNguoiDung.get(position);
    }
       @Override
    public long getItemId(int position) {
        return 0;
    }
    public static class ViewHolder {
        ImageView img;
        TextView txtName;
        TextView txtChucVu;
        ImageView imgDelete;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null)
        {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_nguoi_dung, null);
            holder.img = (ImageView) convertView.findViewById(R.id.ivIcon);
            holder.txtName = (TextView) convertView.findViewById(R.id.tvName);
            holder.txtChucVu = (TextView) convertView.findViewById(R.id.tvChucVu);
            holder.imgDelete = (ImageView)convertView.findViewById(R.id.ivDelete);
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nguoiDungDAO.deleteNguoiDungByID(arrNguoiDung.get(position).getUserName());
                    arrNguoiDung.remove(position);
                    notifyDataSetChanged();
                }
            });
            convertView.setTag(holder);
        }
        else
            holder=(ViewHolder)convertView.getTag();
        NguoiDung _entry = (NguoiDung) arrNguoiDung.get(position);
        if (position % 3 ==0) {
            holder.img.setImageResource(R.drawable.emone);
        }else if (position % 3 == 1){
            holder.img.setImageResource(R.drawable.emtwo);
        }else {
            holder.img.setImageResource(R.drawable.emthree);
        }
        holder.txtName.setText(_entry.getHoTen());
        holder.txtChucVu.setText(_entry.getChucVu());
        return convertView;
    }
    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
    public void changeDataset(List<NguoiDung> items){
        this.arrNguoiDung = items;
        notifyDataSetChanged();
    }


}