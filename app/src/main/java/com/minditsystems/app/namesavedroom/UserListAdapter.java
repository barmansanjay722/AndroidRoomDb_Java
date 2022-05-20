package com.minditsystems.app.namesavedroom;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.minditsystems.app.namesavedroom.db.AppDatabase;
import com.minditsystems.app.namesavedroom.db.User;

import java.io.Serializable;
import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.MyViewHolder> {

    private Context context;
    private List<User> userList;
    public UserListAdapter(Context context){
        this.context = context;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public UserListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserListAdapter.MyViewHolder holder, int position) {
        holder.tvProductName.setText(this.userList.get(position).productName);
        holder.tvBrandName.setText(this.userList.get(position).productBrand);
//        holder.tvdescription.setText(this.userList.get(position).description);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String descriptionstr = userList.get(holder.getAdapterPosition()).description;
                int idstr = userList.get(holder.getAdapterPosition()).uid;
                Intent i = new Intent(context.getApplicationContext(), DescriptionPage.class);
                i.putExtra("description",descriptionstr);
                i.putExtra("uid",idstr);
                context.startActivity(i);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                onDelete(userList.get(holder.getAdapterPosition()));
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.userList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvProductName;
        TextView tvBrandName;
//        TextView tvdescription;

        public MyViewHolder(View view){
            super(view);
            tvProductName = view.findViewById(R.id.tvProductName);
            tvBrandName = view.findViewById(R.id.tvBrandName);
//            tvdescription = view.findViewById(R.id.tvdescription);
        }
    }


    public void onDelete(User user)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Do you want to Delete?");
        builder.setTitle("Delete !");
        builder.setCancelable(false);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                AppDatabase db = AppDatabase.getDbInstance(context.getApplicationContext());
                db.userDao().delete(user);
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
