package com.kodetr.resepmakanan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class AddMakanan extends AppCompatActivity {

    private EditText et_nama, et_gambar, et_harga, et_diskon;
    private Button btn_simpan;

    private MakananInterface mi;
    private boolean TAG = true; // create

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_makanan);

        init();

        Intent in = getIntent();
        if (in.getStringExtra("nama") != null) {
            // update
            et_nama.setText(in.getStringExtra("nama"));
            et_gambar.setText(in.getStringExtra("gambar"));
            TAG = true; // TODO : Rekues error balik true dan false
        } else {
            // create
            TAG = false;
        }
    }

    private void init() {
        et_nama = findViewById(R.id.et_nama);
        et_gambar = findViewById(R.id.et_gambar);
        et_harga = findViewById(R.id.et_harga);
        et_diskon = findViewById(R.id.et_diskon);
        btn_simpan = findViewById(R.id.btn_simpan);
        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                create();
            }
        });
    }

    private void create() {

        if (!TextUtils.isEmpty(et_nama.getText()) && !TextUtils.isEmpty(et_gambar.getText())) {

            mi = new MakananImp(this);

            if(TAG) {

                Random rand = new Random();
                int id = rand.nextInt(1000);

                ResepMakanan makanan = new ResepMakanan();
                makanan.setId(id);
                makanan.setNama_makanan(et_nama.getText().toString());
                makanan.setGambar(et_gambar.getText().toString());


                mi.create(makanan);
                Toast.makeText(this, "Berhasil disimpan", Toast.LENGTH_SHORT).show();
            }else{


                Toast.makeText(this, "Berhasil diubah", Toast.LENGTH_SHORT).show();
            }

            finish();
        } else {
            Toast.makeText(this, "Inputan masih kosong!", Toast.LENGTH_SHORT).show();
        }

    }
}