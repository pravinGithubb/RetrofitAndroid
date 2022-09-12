package com.app.retrofitandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.retrofitandroid.R;
import com.app.retrofitandroid.databinding.CustomListItemBinding;
import com.app.retrofitandroid.model.User;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomRecyclerAdapter extends RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<User> itemList;
    private CustomListItemBinding binding;


   public CustomRecyclerAdapter(Context context, ArrayList<User> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //  View view = LayoutInflater.from(context).inflate(R.layout.custom_list_item_horizontal, parent, false);
        binding = CustomListItemBinding.inflate(LayoutInflater.from(context), parent, false);
        //  MyViewHolder holder = new MyViewHolder(view);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        User item = itemList.get(position);
        holder.binding.tvName.setText(item.getfName() + " " + item.getlName());
        holder.binding.tvEmail.setText(item.getEmail());

        Glide
                .with(context)
                .load(item.getImageUrl())
                .centerCrop()
                .placeholder(R.color.purple_200)
                .into(holder.binding.ivLogo);

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        CustomListItemBinding binding;

        public MyViewHolder(@NonNull CustomListItemBinding binding) {
            super(binding.getRoot())
            ;
            this.binding = binding;

        }
    }


    public void setItems(ArrayList<User> userList){
        itemList = userList;
        notifyDataSetChanged();
    }

}
