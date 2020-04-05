package com.example.quanlybanhang.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.quanlybanhang.R;
import com.example.quanlybanhang.dao.SanPhamDAO;
import com.example.quanlybanhang.model.SanPham;

import java.util.ArrayList;
import java.util.List;

public class SanPhamAdapter extends BaseAdapter implements Filterable {
    List<SanPham> arrSanPham;
    List<SanPham> arrSortSanPham;
    private Filter CaFilter;
    public Activity context;
    public LayoutInflater inflater;
    SanPhamDAO sanPhamDAO;
    public SanPhamAdapter(Activity context, List<SanPham> arraySanPham) {
        super();
        this.context = context;
        this.arrSanPham = arraySanPham;
        this.arrSortSanPham = arraySanPham;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        sanPhamDAO = new SanPhamDAO(context);
    }
    @Override
    public int getCount() {
        return arrSanPham.size();
    }
    @Override
    public Object getItem(int position) {
        return arrSanPham.get(position);
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    public static class ViewHolder {
        ImageView img;
        TextView txtSanPhamName;
        TextView txtSanPhamPrice;
        TextView txtSoLuong;
        ImageView imgDelete;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null)
        {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_san_pham, null);
            holder.img = (ImageView) convertView.findViewById(R.id.ivIcon);
            holder.txtSanPhamName = (TextView) convertView.findViewById(R.id.tvSanPhamName);
            holder.txtSanPhamPrice = (TextView) convertView.findViewById(R.id.tvSanPhamPrice);
            holder.txtSoLuong= (TextView) convertView.findViewById(R.id.tvSoLuong);
            holder.imgDelete = (ImageView)convertView.findViewById(R.id.ivDelete);
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sanPhamDAO.deleteSanPhamByID(arrSanPham.get(position).getMaSanPham());
                    arrSanPham.remove(position);
                    notifyDataSetChanged();
                }
            });
            convertView.setTag(holder);
        }
        else
            holder=(ViewHolder)convertView.getTag();
        SanPham _entry = (SanPham) arrSanPham.get(position);
        holder.img.setImageResource(R.drawable.icon_sp);
        holder.txtSanPhamName.setText("Mã Sản Phẩm: "+_entry.getMaSanPham());
        holder.txtSoLuong.setText("Số lượng: "+_entry.getSoLuong());
        holder.txtSanPhamPrice.setText("Giá: "+ _entry.getGia()+"");
        return convertView;
    }
    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
    public void changeDataset(List<SanPham> items){
        this.arrSanPham = items;
        notifyDataSetChanged();
    }
    public void resetData() {
        arrSanPham = arrSortSanPham;
    }


    public Filter getFilter() {
        if (CaFilter == null)
            CaFilter = new CustomFilter();
        return CaFilter;
    }
    private class CustomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            // We implement here the filter logic
            if (constraint == null || constraint.length() == 0) {
                results.values = arrSortSanPham;
                results.count = arrSortSanPham.size();
            }
            else {
                List<SanPham> lsSanPham = new ArrayList<SanPham>();
                for (SanPham p : arrSanPham) {
                    if (p.getMaSanPham().toUpperCase().startsWith(constraint.toString().toUpperCase()))
                        lsSanPham.add(p);                 }
                results.values = lsSanPham;
                results.count = lsSanPham.size();
            }
            return results;         }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.count == 0)
                notifyDataSetInvalidated();
            else {
                arrSanPham = (List<SanPham>) results.values;
                notifyDataSetChanged();
            }
        }
    }
}
