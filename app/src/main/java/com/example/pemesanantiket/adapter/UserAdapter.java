package com.example.pemesanantiket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pemesanantiket.R;
import com.example.pemesanantiket.database.userEntity.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewAdapter> {
    private List<User> list;
    private Context context;
    private Dialog dialog;

    public interface Dialog {
        void onClick(int position);
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    public UserAdapter(Context context, List<User> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_user, parent, false);
        return new ViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAdapter holder, int position) {
        holder.nama.setText(list.get(position).nama);
        holder.nope.setText(list.get(position).nope);
        holder.keberangkatan.setText(list.get(position).keberangkatan);
        holder.tujuan.setText(list.get(position).tujuan);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewAdapter extends RecyclerView.ViewHolder{
        TextView nama, nope, keberangkatan, tujuan;

        public ViewAdapter(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.namaPenumpang);
            nope = itemView.findViewById(R.id.nomorHp);
            keberangkatan = itemView.findViewById(R.id.tvKeberangkatan);
            tujuan = itemView.findViewById(R.id.tvTujuan);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (dialog!=null){
                        dialog.onClick(getLayoutPosition());
                    }
                }
            });
        }
    }
}
