package com.kodetr.resepmakanan;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MakananAdapter.OnClikMakananInterface {

    private RecyclerView rvMakanan;
    private FloatingActionButton fab;

    private MakananInterface mi;
    private List<ResepMakanan> makananList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        read();
    }

    private void init() {
        rvMakanan = findViewById(R.id.rvMakanan);
        rvMakanan.setLayoutManager(new LinearLayoutManager(this));

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddMakanan.class));
            }
        });
    }

    private void read() {
        mi = new MakananImp(this);
        makananList = new ArrayList<>();

        Cursor c = mi.read();
        if (c.moveToNext()) {
            do {
                ResepMakanan makanan = new ResepMakanan();
                makanan.setId(c.getInt(0));
                makanan.setNama_makanan(c.getString(1));
                makanan.setGambar(c.getString(2));
                makananList.add(makanan);
            } while (c.moveToFirst()); // TODO: error to first harus masukan MoveToNext
        }

        MakananAdapter adapter = new MakananAdapter(this, makananList);
        rvMakanan.setAdapter(adapter);

    }

    @Override
    public void onClikMakanan(int position) {
        final ResepMakanan makanan = makananList.get(position);

        String[] pilihan = {"Ubah","Hapus"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pilihan");
        builder.setItems(pilihan, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i){
                    case 0: // Ubah
                       Intent in = new Intent(MainActivity.this, AddMakanan.class);
                       in.putExtra("nama", makanan.getNama_makanan());
                       in.putExtra("gambar", makanan.getGambar());
                       startActivity(in);
                        break;
                    case 1: // hapus

                    AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                    builder1.setMessage("Yakin ingin Hapus!");
                    builder1.setPositiveButton("YA", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            read();
                        }
                    });

                    builder1.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    builder1.show();
                        break;
                }
            }
        });

        builder.show();


    }
}
