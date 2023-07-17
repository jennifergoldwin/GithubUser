package com.example.githubuser.services.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.githubuser.DetailUserActivity;
import com.example.githubuser.R;
import com.example.githubuser.services.DbHandler;
import com.example.githubuser.utils.User;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListUserAdapter extends RecyclerView.Adapter<ListUserAdapter.ViewHolder> {
    Context context;
    List<User> userList;
    boolean isVisibleBtn;
    public ListUserAdapter(boolean isVisibleBtn, Context context, List<User> userList){
        this.isVisibleBtn = isVisibleBtn;
        this.context = context;
        this.userList = userList;
    }
    @NonNull
    @Override
    public ListUserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListUserAdapter.ViewHolder holder, int position) {
        Picasso.get().load(userList.get(position).getAvatar_url()).into(holder.imageView);
        holder.name.setText(userList.get(position).getLogin());
        if (isVisibleBtn){
            holder.btnDelete.setVisibility(View.VISIBLE);
            holder.btnDelete.setOnClickListener(view -> {
                DbHandler.getInstance(context).getUserDb().userDao().delete(userList.get(position));
                userList.remove(userList.get(position));
                notifyDataSetChanged();
            });

        }
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent( context, DetailUserActivity.class);
            intent.putExtra("username",userList.get(position).getLogin());
            intent.putExtra("id",userList.get(position).getAvatar_url());
            intent.putExtra("url",userList.get(position).getAvatar_url());
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView name;
        Button btnDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btnDelete = itemView.findViewById(R.id.btn_delete);
            imageView = itemView.findViewById(R.id.iv_item_user);
            name = itemView.findViewById(R.id.tv_item_user_name);
        }
    }
}
