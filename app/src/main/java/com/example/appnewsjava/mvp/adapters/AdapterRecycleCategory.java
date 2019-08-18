package com.example.appnewsjava.mvp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appnewsjava.R;
import com.example.appnewsjava.data.pojo.Category;
import com.example.appnewsjava.mvp.list_category.ItemClickCategoryListener;
import java.util.ArrayList;

public class AdapterRecycleCategory extends RecyclerView.Adapter<AdapterRecycleCategory.MyViewHolderCategory> {


    private Context context;
    private ArrayList<Category> listCategory;
    private ItemClickCategoryListener listener;



    public AdapterRecycleCategory(Context context, ArrayList<Category> listCategory, ItemClickCategoryListener listener) {
        this.context = context;
        this.listCategory = listCategory;
        this.listener = listener;
    }


    @NonNull
    @Override
    public AdapterRecycleCategory.MyViewHolderCategory onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_list_category, parent,false);
        return new MyViewHolderCategory(view);
    }


    @Override
    public void onBindViewHolder(@NonNull AdapterRecycleCategory.MyViewHolderCategory viewHolder, final int position) {
        final Category category = listCategory.get(position);
        viewHolder.txtTitle.setText(category.getName());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(position, category);
            }
        });
    }


    @Override
    public int getItemCount() {
        return listCategory.size();
    }


    class MyViewHolderCategory extends RecyclerView.ViewHolder {
        final TextView txtTitle;

        public MyViewHolderCategory(View view) {
            super(view);
            txtTitle = view.findViewById(R.id.txtTitle);
        }
    }
}