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

import com.example.nh12_pro1121_md18310.Dao.KhachHangDao;
import com.example.nh12_pro1121_md18310.Model.KhachHang;
import com.example.nh12_pro1121_md18310.R;

import java.util.ArrayList;

public class KhachHangAdapter extends RecyclerView.Adapter<KhachHangAdapter.ViewHorder> {
    private Context context;
    private ArrayList<KhachHang> listKh;
    KhachHangDao khachHangDao;

    public KhachHangAdapter(Context context, ArrayList<KhachHang> list) {
        this.context = context;
        this.listKh = list;
        khachHangDao = new KhachHangDao(context);
    }

    @NonNull
    @Override
    public KhachHangAdapter.ViewHorder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_khachhang, parent, false);
        return new KhachHangAdapter.ViewHorder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KhachHangAdapter.ViewHorder holder, int position) {
        holder.txt_tenKh.setText("Họ và tên: "+listKh.get(position).getHoTenKh());
        holder.txt_namSinhKh.setText("Năm sinh: "+String.valueOf(listKh.get(position).getNamSinhKh()));
        holder.txt_sdtKh.setText(" Số điện thoại: "+String.valueOf(listKh.get(position).getSdtKhachHang()));
        KhachHang khachHang = listKh.get(position);
        holder.btn_deleteKh.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Cảnh báo");
                builder.setMessage("Bạn có chắc chắn muốn xóa không");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(khachHangDao.delete(khachHang)){
                            listKh.clear();
                            listKh.addAll(khachHangDao.getDs());
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
        holder.btn_updateKh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogUpdate(khachHang);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listKh.size();
    }

    public class ViewHorder extends RecyclerView.ViewHolder {

        TextView txt_tenKh, txt_namSinhKh, txt_sdtKh;
        ImageButton btn_updateKh, btn_deleteKh;
        public ViewHorder(@NonNull View itemView) {
            super(itemView);
            txt_tenKh = itemView.findViewById(R.id.txt_tenKh);
            txt_namSinhKh = itemView.findViewById(R.id.txt_namSinhKh);
            txt_sdtKh = itemView.findViewById(R.id.txt_sdtKh);
            btn_updateKh = itemView.findViewById(R.id.btn_updateKh);
            btn_deleteKh = itemView.findViewById(R.id.btn_deleteKh);

        }

    }
    public void openDialogUpdate(KhachHang kh) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.update_khachhang, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        EditText edtHtNKh = view.findViewById(R.id.edt_suahtKh);
        EditText edtNsKh = view.findViewById(R.id.edt_suansKh);
        EditText edtSdtKh = view.findViewById(R.id.edt_suasdtKh);
        Button btneditForm = view.findViewById(R.id.btn_suasaveKh);

        edtHtNKh.setText(kh.getHoTenKh());
        edtNsKh.setText(kh.getNamSinhKh()+"");
        edtSdtKh.setText(kh.getSdtKhachHang());
        btneditForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kh.setHoTenKh(edtHtNKh.getText().toString());
                kh.setNamSinhKh(Integer.parseInt(edtNsKh.getText().toString()));
                kh.setSdtKhachHang(edtSdtKh.getText().toString());
                if (khachHangDao.update(kh)) {
                    listKh.clear();
                    listKh.addAll(khachHangDao.getDs());
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
