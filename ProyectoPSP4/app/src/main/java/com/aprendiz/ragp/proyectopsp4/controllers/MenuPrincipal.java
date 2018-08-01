package com.aprendiz.ragp.proyectopsp4.controllers;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.EditText;

import com.aprendiz.ragp.proyectopsp4.R;
import com.aprendiz.ragp.proyectopsp4.models.AdapterP;
import com.aprendiz.ragp.proyectopsp4.models.ManagerDB;
import com.aprendiz.ragp.proyectopsp4.models.Proyecto;

import java.util.List;

public class MenuPrincipal extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton btnAgregar;
    public static Proyecto proyecto= new Proyecto();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        inizialite();
        inputAdapter();
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MenuPrincipal.this);
                builder.setTitle("Agregar Proyecto");
                LayoutInflater inflater = getLayoutInflater();
                final View view = inflater.inflate(R.layout.item_ingresarp,null);
                final EditText txtProyecto = view.findViewById(R.id.txtNombreP2);
                builder.setView(view);
                builder.setPositiveButton("Agreagar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            ManagerDB managerDB = new ManagerDB(MenuPrincipal.this);
                            Proyecto proyecto = new Proyecto();
                            proyecto.setNombre(txtProyecto.getText().toString());
                            if (proyecto.getNombre().length()>1) {
                                managerDB.inputProyecto(proyecto);
                                dialog.cancel();
                                inputAdapter();
                                Snackbar.make(v,"Se ha agregado el proyecto "+proyecto.getNombre(),Snackbar.LENGTH_SHORT).show();
                            }else {
                                Snackbar.make(v,"Para poder agreagar un proyecto por favor no deje el campo vacio",Snackbar.LENGTH_SHORT).show();
                            }
                        }catch (Exception e){
                            Snackbar.make(v,"Por favor ingrese un nombre",Snackbar.LENGTH_SHORT).show();
                        }

                    }
                });

                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();

            }
        });
    }



    private void inizialite() {
        recyclerView = findViewById(R.id.recyclerView);
        btnAgregar = findViewById(R.id.btnAgregar);
    }

    private void inputAdapter() {
        ManagerDB managerDB = new ManagerDB(this);
        final List<Proyecto> proyectoList = managerDB.proyectoList();
        AdapterP adapterP = new AdapterP(proyectoList);
        recyclerView.setAdapter(adapterP);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setHasFixedSize(true);
        adapterP.setMlistener(new AdapterP.OnItemClickListener() {
            @Override
            public void itemClick(int position) {
                proyecto= proyectoList.get(position);
                Intent intent = new Intent(MenuPrincipal.this,MenuProyecto.class);
                startActivity(intent);
            }
        });
    }
}
