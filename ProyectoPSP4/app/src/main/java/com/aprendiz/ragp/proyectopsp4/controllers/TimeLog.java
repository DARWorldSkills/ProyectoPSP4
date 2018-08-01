package com.aprendiz.ragp.proyectopsp4.controllers;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.aprendiz.ragp.proyectopsp4.R;
import com.aprendiz.ragp.proyectopsp4.models.CTimeLog;
import com.aprendiz.ragp.proyectopsp4.models.ManagerDB;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TimeLog extends AppCompatActivity implements View.OnClickListener{

    private TextView mTextMessage;
    Spinner spPhase;
    EditText txtStart, txtInterrupcion, txtStop,txtDelta, txtComments;
    Button btnStart, btnStop;
    Date dateStart = new Date();
    Date dateStop = new Date();
    ConstraintLayout constraintLayout;
    int interrupcion=0;
    boolean bandera= false;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    cleanV();
                    return true;
                case R.id.navigation_dashboard:
                    inputData();
                    return true;
                case R.id.navigation_notifications:
                    listData();
                    return true;
            }
            return false;
        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_log);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        inizialite();
        listPhase();

    }

    private void listPhase() {
        List<String> phaseList= new ArrayList<>();
        phaseList.add("Planning");
        phaseList.add("Design");
        phaseList.add("Code");
        phaseList.add("Compile");
        phaseList.add("UT");
        phaseList.add("PM");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,phaseList);
        spPhase.setAdapter(adapter);


    }

    private void inizialite() {
        txtStart = findViewById(R.id.txtStart);
        txtInterrupcion = findViewById(R.id.txtInterrupcion);
        txtStop = findViewById(R.id.txtStop);
        txtDelta = findViewById(R.id.txtDelta);
        txtComments = findViewById(R.id.txtComments);

        spPhase = findViewById(R.id.spPhase);

        btnStart= findViewById(R.id.btnStart);
        btnStop= findViewById(R.id.btnStop);

        constraintLayout = findViewById(R.id.container);

        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);

        btnStop.setEnabled(false);
    }

    public void cleanV(){
        txtStart.setText("");
        txtInterrupcion.setText("");
        txtStop.setText("");
        txtDelta.setText("");
        txtComments.setText("");
        btnStop.setEnabled(false);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnStart:
                inputDataStart();
                btnStop.setEnabled(true);
                break;

            case R.id.btnStop:
                calculateDelta();
                inputDateStop();
                break;
        }
    }




    private void inputDataStart() {
        dateStart = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String dateA = format.format(dateStart);
        txtStart.setText(dateA);
    }

    private void inputDateStop() {
        dateStop = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String dateA = format.format(dateStop);
        txtStop.setText(dateA);
    }


    private void inputData() {
        validarCampos();
        if (bandera==true) {
            CTimeLog cTimeLog = new CTimeLog();
            cTimeLog.setPhase(spPhase.getSelectedItem().toString());
            cTimeLog.setStart(txtStart.getText().toString());
            try {
                interrupcion = Integer.parseInt(txtInterrupcion.getText().toString());
                int delta = Integer.parseInt(txtDelta.getText().toString());
                cTimeLog.setInterrupcions(interrupcion);
                cTimeLog.setDelta(delta);
            } catch (Exception e) {

            }
            cTimeLog.setStop(txtStop.getText().toString());
            cTimeLog.setComments(txtComments.getText().toString());
            cTimeLog.setComments(txtComments.getText().toString());
            cTimeLog.setProyecto(MenuPrincipal.proyecto.getId());
            ManagerDB managerDB = new ManagerDB(this);

            managerDB.inputTimeLog(cTimeLog, constraintLayout);
            cleanV();
        }else {
            Snackbar.make(constraintLayout,"Faltan campos por ingresar",Snackbar.LENGTH_SHORT).show();
        }


    }

    private void calculateDelta() {
        long diferencia = dateStop.getTime() - dateStart.getTime();
        int tmp = (int) (((diferencia / 60) / 60) - interrupcion);
        txtDelta.setText(Integer.toString(tmp));

    }

    private void validarCampos() {
        int valor = 0;
        if (txtStart.getText().toString().length()>0){
            valor++;
        }else {
            txtStart.setError("Por favor cálcule el tiempo de inicio");
        }

        if (txtStop.getText().toString().length()>0){
            valor++;
        }else {
            txtStop.setError("Por favor cálcule el tiempo de fin");
        }

        if (txtDelta.getText().toString().length()>0){
            valor++;
        }


        if (valor<3){
            bandera=false;
            valor=0;
        }else {
            bandera=true;
            valor=0;
        }

    }


    private void listData() {
    }

}
