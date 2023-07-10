package com.example.ujianandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText edNamaDepan = (EditText) findViewById(R.id.edNamaDepan);
        EditText edNamaBelakang = (EditText) findViewById(R.id.edNamaBelakang);
        EditText edUmur = (EditText) findViewById(R.id.edUmur);
        Button btnSimpan = (Button) findViewById(R.id.btnSimpan);

        ArrayList<String> daftar_nama = new ArrayList<>();

        Intent intent_list = new Intent(MainActivity.this, ListActivity.class);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String isian_nama_depan = edNamaDepan.getText().toString();
                String isian_nama_belakang = edNamaBelakang.getText().toString();
                String isian_umur = edUmur.getText().toString();
                Integer doubleisian_nama_umur = Integer.valueOf(isian_umur);

                if(isian_nama_depan.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Isian masih kosong", Toast.LENGTH_SHORT).show();
                }else{
                    String nama_lengkap = isian_nama_depan.concat(" ").concat(isian_nama_belakang);
                    daftar_nama.clear();

                    for (int i = 0; i < doubleisian_nama_umur; i++) {
                        String status;
//                        pengkondisian umur by Valien
                        if (doubleisian_nama_umur >= 0 && doubleisian_nama_umur <= 10) {
                            status = "Anak";
                        } else if (doubleisian_nama_umur >= 11 && doubleisian_nama_umur <= 20) {
                            status = "Remaja";
                        } else if (doubleisian_nama_umur >= 21 && doubleisian_nama_umur <= 30) {
                            status = "Dewasa";
                        } else {
                            status = "Tua";
                        }

                        if (i % 2 == 0) {
                            // Menambahkan angka incrementing dan status di belakang nama hanya untuk nomor ganjil by Valien
                            String nama_dengan_status = i + " " + nama_lengkap + ". Status : " + status;
                            daftar_nama.add(nama_dengan_status);
                        }
                    }

                    edNamaDepan.setText("");
                    edNamaBelakang.setText("");
                    edUmur.setText("");

                    intent_list.putStringArrayListExtra("daftar_nama", daftar_nama);
                    startActivity(intent_list);
                }
            }
        });
    }
}