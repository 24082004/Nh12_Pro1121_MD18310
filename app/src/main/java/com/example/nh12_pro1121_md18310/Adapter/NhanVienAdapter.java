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

import com.example.nh12_pro1121_md18310.Dao.NhanVienDao;
import com.example.nh12_pro1121_md18310.Model.NhanVien;
import com.example.nh12_pro1121_md18310.R;

import java.util.ArrayList;

public class NhanVienAdapter extends RecyclerView.Adapter<NhanVienAdapter.ViewHorder> {
    private Context context;
    private ArrayList<NhanVien> listNv;
    NhanVienDao spdao;

    public NhanVienAdapter(Context context, ArrayList<NhanVien> list) {
        this.context = context;
        this.listNv = list;
        spdao = new NhanVienDao(context);
    }

    @NonNull
    @Override
    public ViewHorder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_nhanvien, parent, false);
        return new ViewHorder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHorder holder, int position) {
        holder.txt_tenNv.setText(listNv.get(position).getHoTenNv());
        holder.txt_namSinhNv.setText(String.valueOf(listNv.get(position).getNamSinhNv()));
        holder.txt_sdtNv.setText(" Số điện thoại: "+String.valueOf(listNv.get(position).getSdtNv()));
        holder.txt_emailNv.setText(" Email: "+String.valueOf(listNv.get(position).getEmailNv()));
        NhanVien nhanVien = listNv.get(position);
        holder.btn_deleteNv.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Cảnh báo");
                builder.setMessage("Bạn có chắc chắn muốn xóa không");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(spdao.delete(nhanVien)){
                            listNv.clear();
                            listNv.addAll(spdao.getDs());
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
        holder.btn_updateNv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogUpdate(nhanVien);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listNv.size();
    }

    public class ViewHorder extends RecyclerView.ViewHolder {

        TextView txt_tenNv, txt_namSinhNv, txt_sdtNv, txt_emailNv;
        ImageButton btn_updateNv, btn_deleteNv;
        public ViewHorder(@NonNull View itemView) {
            super(itemView);
            txt_tenNv = itemView.findViewById(R.id.txt_tenNv);
            txt_namSinhNv = itemView.findViewById(R.id.txt_namSinhNv);
            txt_sdtNv = itemView.findViewById(R.id.txt_sdtNv);
            txt_emailNv = itemView.findViewById(R.id.txt_emailNv);
            btn_updateNv = itemView.findViewById(R.id.btn_updateNv);
            btn_deleteNv = itemView.findViewById(R.id.btn_deleteNv);

        }

    }
    public void openDialogUpdate(NhanVien nv) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.update_nhanvien, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        EditText edtHtNv = view.findViewById(R.id.edt_suahtNv);
        EditText edtNsNv = view.findViewById(R.id.edt_suansNv);
        EditText edtSdt = view.findViewById(R.id.edt_suasdtNv);
        EditText edtEmailNv = view.findViewById(R.id.edt_suaemailNv);
        Button btneditForm = view.findViewById(R.id.btn_suasaveNv);

        edtHtNv.setText(nv.getHoTenNv());
        edtNsNv.setText(nv.getNamSinhNv()+"");
        edtSdt.setText(nv.getSdtNv());
        edtSdt.setText(nv.getEmailNv());
        btneditForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nv.setHoTenNv(edtHtNv.getText().toString());
                nv.setNamSinhNv(Integer.parseInt(edtNsNv.getText().toString()));
                nv.setSdtNv(edtSdt.getText().toString());
                nv.setEmailNv(edtEmailNv.getText().toString());
                if (spdao.update(nv)) {
                    listNv.clear();
                    listNv.addAll(spdao.getDs());
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
