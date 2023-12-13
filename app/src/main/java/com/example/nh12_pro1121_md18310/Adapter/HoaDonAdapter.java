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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nh12_pro1121_md18310.Dao.HoaDonDao;
import com.example.nh12_pro1121_md18310.Dao.KhachHangDao;
import com.example.nh12_pro1121_md18310.Dao.SanPhamDao;
import com.example.nh12_pro1121_md18310.IClickItemRCV;
import com.example.nh12_pro1121_md18310.Model.HoaDon;
import com.example.nh12_pro1121_md18310.Model.KhachHang;
import com.example.nh12_pro1121_md18310.Model.SanPham;
import com.example.nh12_pro1121_md18310.R;

import java.util.ArrayList;

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.ViewHorder>{
    private Context context;
    private ArrayList<HoaDon> listHd;
    HoaDonDao hoaDonDao;
    SanPhamDao sanPhamDao;
    SanPhamSpinnerAdapter sanPhamSpinnerAdapter;
    ArrayList<SanPham> lst;
    int maSp = 0, dongia;
    public HoaDonAdapter(Context context, ArrayList<HoaDon> list) {
        this.context = context;
        this.listHd = list;
        hoaDonDao = new HoaDonDao(context);
        sanPhamDao = new SanPhamDao(context);
        lst = sanPhamDao.getDs();
    }

    @NonNull
    @Override
    public ViewHorder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_hoadon, parent, false);
        return new ViewHorder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHorder holder, int position) {
        HoaDon hoaDon = listHd.get(position);
        sanPhamDao = new SanPhamDao(context);
        SanPham sp = sanPhamDao.getDs().get(Integer.parseInt(String.valueOf(hoaDon.getMaSp())));

        holder.txttenspHd.setText("Tên san phẩm: " + sp.getTenSanPham());
        holder.txtsoLhuongHd.setText("Số lượng: "+String.valueOf(listHd.get(position).getSoLuong()));
        holder.txttongtienHd.setText("Tổng tiền: "+String.valueOf(listHd.get(position).getTongTien()));
        holder.txttrangthaiHd.setText("Trạng thái thanh toán: "+String.valueOf(listHd.get(position).getTrangThaiTT()));
        HoaDon hd = listHd.get(position);
        holder.btndeleteHd.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Cảnh báo");
                builder.setMessage("Bạn có chắc chắn muốn xóa không");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(hoaDonDao.delete(hd)){
                            listHd.clear();
                            listHd.addAll(hoaDonDao.getDshd());
                            notifyDataSetChanged();
                            Toast.makeText(context,"Xoá thành công",Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(context,"Xóa thất bại",Toast.LENGTH_LONG).show();
                        }
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context,"Không xóa",Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        holder.btnupdateHd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogUpdate(hd);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listHd.size();
    }

    public class ViewHorder extends RecyclerView.ViewHolder {

        TextView txttenspHd, txtsoLhuongHd, txttongtienHd, txttrangthaiHd;
        ImageButton btnupdateHd, btndeleteHd;
        public ViewHorder(@NonNull View itemView) {
            super(itemView);
            txttenspHd = itemView.findViewById(R.id.txt_tenspHd);
            txtsoLhuongHd = itemView.findViewById(R.id.txt_soLhuongHd);
            txttongtienHd = itemView.findViewById(R.id.txt_tongtienHd);
            txttrangthaiHd = itemView.findViewById(R.id.txt_trangthaiHd);
            btnupdateHd = itemView.findViewById(R.id.btn_updateHd);
            btndeleteHd = itemView.findViewById(R.id.btn_deleteHd);

        }

    }
    public void openDialogUpdate(HoaDon hd) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.update_hoadon, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        Spinner edtthemtenspHd = view.findViewById(R.id.edt_suatenspHd);
        EditText edtupslhd = view.findViewById(R.id.edt_suasoluongHd);
        EditText edtuptothd = view.findViewById(R.id.edt_suatongtienHd);
        EditText edtuptthd = view.findViewById(R.id.edt_suatrangthaiHd);
        Button btneditForm = view.findViewById(R.id.btn_suasaveHd);
        edtupslhd.setText(hd.getSoLuong()+"");
        edtuptothd.setText(hd.getTongTien()+"");
        edtuptthd.setText(hd.getTrangThaiTT());
        btneditForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hd.setSoLuong(Integer.parseInt(edtupslhd.getText().toString()));
                hd.setTongTien(Integer.parseInt(edtuptothd.getText().toString()));
                hd.setTrangThaiTT(edtuptthd.getText().toString());
                if (hoaDonDao.update(hd)) {
                    listHd.clear();
                    listHd.addAll(hoaDonDao.getDshd());
                    notifyDataSetChanged();
                    dialog.dismiss();
                    Toast.makeText(context, "Sửa thành công", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_LONG).show();
                }
            }
        });
        sanPhamSpinnerAdapter = new SanPhamSpinnerAdapter(context,R.layout.item_view_spinner,lst);
        edtthemtenspHd.setAdapter(sanPhamSpinnerAdapter);
        edtthemtenspHd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maSp = lst.get(position).getMaSanPham();
                dongia = lst.get(position).getDonGia();
                hd.setMaSp(maSp);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }
}
