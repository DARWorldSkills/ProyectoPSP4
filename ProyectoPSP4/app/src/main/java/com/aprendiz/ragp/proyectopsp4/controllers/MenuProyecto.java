package com.aprendiz.ragp.proyectopsp4.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aprendiz.ragp.proyectopsp4.R;

public class MenuProyecto extends AppCompatActivity implements View.OnClickListener{
    Button btnTimeLog, btnDefectLog, btnPPS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_proyecto);
        inizialite();
    }

    private void inizialite() {
        btnTimeLog= findViewById(R.id.btnTimeLog);
        btnDefectLog= findViewById(R.id.btnDefectLog);
        btnPPS= findViewById(R.id.btnPPS);

        btnTimeLog.setOnClickListener(this);
        btnDefectLog.setOnClickListener(this);
        btnPPS.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.btnTimeLog:
                intent = new Intent(MenuProyecto.this,TimeLog.class);
                startActivity(intent);
                break;

            case R.id.btnDefectLog:
                intent = new Intent(MenuProyecto.this,DefectLog.class);
                startActivity(intent);
                break;

            case R.id.btnPPS:
                intent = new Intent(MenuProyecto.this,ProjectPlanSummary.class);
                startActivity(intent);
                break;
        }
    }
}
