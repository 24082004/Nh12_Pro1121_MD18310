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
import com.example.nh12_pro1121_md18310.Dao.NhanVienDao;
import com.example.nh12_pro1121_md18310.Dao.SanPhamDao;
import com.example.nh12_pro1121_md18310.IClickItemRCV;
import com.example.nh12_pro1121_md18310.Model.LoaiSanPham;
import com.example.nh12_pro1121_md18310.Model.NhanVien;
import com.example.nh12_pro1121_md18310.Model.SanPham;
import com.example.nh12_pro1121_md18310.R;

import java.util.ArrayList;
import java.util.List;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.ViewHorder>{
    private Context context;
    private ArrayList<SanPham> listSp;
    SanPhamDao sanPhamDao;

    public SanPhamAdapter(Context context, ArrayList<SanPham> list) {
        this.context = context;
        this.listSp = list;
        sanPhamDao = new SanPhamDao(context);
    }

    @NonNull
    @Override
    public SanPhamAdapter.ViewHorder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_sanpham, parent, false);
        return new SanPhamAdapter.ViewHorder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SanPhamAdapter.ViewHorder holder, int position) {
        holder.txt_loai.setText(listSp.get(position).getTenlLoaisp());
        holder.txt_tenSp.setText(String.valueOf(listSp.get(position).getTenSanPham()));
        holder.txt_donGiaSp.setText(String.valueOf(listSp.get(position).getDonGia())+" VNĐ ");
        SanPham sanPham = listSp.get(position);
        holder.btn_deleteSp.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Cảnh báo");
                builder.setMessage("Bạn có chắc chắn muốn xóa không");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(sanPhamDao.delete(sanPham)){
                            listSp.clear();
                            listSp.addAll(sanPhamDao.getDs());
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
        holder.btn_updateSp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogUpdate(sanPham);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listSp.size();
    }

    public class ViewHorder extends RecyclerView.ViewHolder {

        TextView txt_loai, txt_tenSp, txt_donGiaSp;
        ImageButton btn_updateSp, btn_deleteSp;
        public ViewHorder(@NonNull View itemView) {
            super(itemView);
            txt_loai = itemView.findViewById(R.id.txt_loai);
            txt_tenSp = itemView.findViewById(R.id.txt_tenSp);
            txt_donGiaSp = itemView.findViewById(R.id.txt_donGiaSp);
            btn_updateSp = itemView.findViewById(R.id.btn_updateSp);
            btn_deleteSp = itemView.findViewById(R.id.btn_deleteSp);

        }

    }
    public void openDialogUpdate(SanPham sp) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.update_sanpham, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        EditText edtsuatenllSp = view.findViewById(R.id.edt_suatenllSp);
        EditText edtsuatenSp = view.findViewById(R.id.edt_suatenSp);
        EditText edtsuadgSp = view.findViewById(R.id.edt_suadgSp);
        Button btneditForm = view.findViewById(R.id.btn_suasaveSp);

        edtsuatenllSp.setText(sp.getTenlLoaisp()+"");
        edtsuatenSp.setText(sp.getTenSanPham());
        edtsuadgSp.setText(sp.getDonGia()+"");
        btneditForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp.setTenlLoaisp(edtsuatenllSp.getText().toString());
                sp.setTenSanPham(edtsuatenSp.getText().toString());
                sp.setDonGia(Integer.parseInt(edtsuadgSp.getText().toString()));
                if (sanPhamDao.update(sp)) {
                    listSp.clear();
                    listSp.addAll(sanPhamDao.getDs());
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
