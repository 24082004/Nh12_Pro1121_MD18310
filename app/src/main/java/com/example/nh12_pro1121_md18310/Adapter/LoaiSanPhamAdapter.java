package com.example.nh12_pro1121_md18310.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nh12_pro1121_md18310.Dao.LoaiSanPhamDao;
import com.example.nh12_pro1121_md18310.Dao.SanPhamDao;
import com.example.nh12_pro1121_md18310.IClickItemRCV;
import com.example.nh12_pro1121_md18310.Model.LoaiSanPham;
import com.example.nh12_pro1121_md18310.Model.SanPham;
import com.example.nh12_pro1121_md18310.R;

import java.util.ArrayList;

public class LoaiSanPhamAdapter extends RecyclerView.Adapter<LoaiSanPhamAdapter.ViewHorder>{
    private Context context;
    private ArrayList<LoaiSanPham> listlSp;
    LoaiSanPhamDao loaiSanPhamDao;

    public LoaiSanPhamAdapter(Context context, ArrayList<LoaiSanPham> list) {
        this.context = context;
        this.listlSp = list;
        loaiSanPhamDao = new LoaiSanPhamDao(context);
    }

    @NonNull
    @Override
    public LoaiSanPhamAdapter.ViewHorder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_loaisanpham, parent, false);
        return new LoaiSanPhamAdapter.ViewHorder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoaiSanPhamAdapter.ViewHorder holder, int position) {
        holder.txt_tenLoai.setText(listlSp.get(position).getTenLoai());
        LoaiSanPham loaiSanPham = listlSp.get(position);
        holder.btn_deleteLsp.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Cảnh báo");
                builder.setMessage("Bạn có chắc chắn muốn xóa không");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(loaiSanPhamDao.delete(loaiSanPham)){
                            listlSp.clear();
                            listlSp.addAll(loaiSanPhamDao.getDs());
                            notifyDataSetChanged();
                            Toast.makeText(context,"Xóa thành công",Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(context,"Xóa thất bại",Toast.LENGTH_LONG).show();
                        }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context,"Không xóa",Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        holder.btn_updateLsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogUpdate(loaiSanPham);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listlSp.size();
    }

    public class ViewHorder extends RecyclerView.ViewHolder {

        TextView txt_tenLoai;
        ImageButton btn_updateLsp, btn_deleteLsp;
        public ViewHorder(@NonNull View itemView) {
            super(itemView);
            txt_tenLoai = itemView.findViewById(R.id.txt_tenLsp);
            btn_updateLsp = itemView.findViewById(R.id.btn_updateLsp);
            btn_deleteLsp = itemView.findViewById(R.id.btn_deleteLsp);

        }

    }
    public void openDialogUpdate(LoaiSanPham lsp) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.update_loaisanpham, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        EditText edtsuatenLsp = view.findViewById(R.id.edt_suatenLsp);
        Button btnsuasaveLsp = view.findViewById(R.id.btn_suasaveLsp);

        edtsuatenLsp.setText(lsp.getTenLoai());
        btnsuasaveLsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lsp.setTenLoai(edtsuatenLsp.getText().toString());
                if (loaiSanPhamDao.update(lsp)) {
                    listlSp.clear();
                    listlSp.addAll(loaiSanPhamDao.getDs());
                    notifyDataSetChanged();
                    dialog.dismiss();
                    Toast.makeText(context, "Sửa thành công", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
