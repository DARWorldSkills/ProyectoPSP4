package com.aprendiz.ragp.proyectopsp4.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.PropertyResourceBundle;

public class ManagerDB {
    SQLiteDatabase db;

    Context context;

    public ManagerDB(Context context) {
        this.context = context;
    }

    public void openWriteDB(){
        GestorDB gestorDB = new GestorDB(context);
        db = gestorDB.getWritableDatabase();
    }

    public void openReadDB(){
        GestorDB gestorDB = new GestorDB(context);
        db = gestorDB.getWritableDatabase();
    }

    public List<Proyecto> proyectoList(){
        openReadDB();
        List<Proyecto> results= new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM PROYECTO",null);
        if (cursor.moveToFirst()){
            do {
                Proyecto proyecto = new Proyecto();
                proyecto.setId(cursor.getInt(0));
                proyecto.setNombre(cursor.getString(1));
                results.add(proyecto);


            }while (cursor.moveToNext());
        }

        cursor.close();
        closeDB();
        return results;

    }
    public void inputProyecto(Proyecto proyecto){
        openWriteDB();
        ContentValues values = new ContentValues();
        values.put("NOMBRE",proyecto.getNombre());
        db.insert("PROYECTO",null,values);
        closeDB();
    }


    public void inputTimeLog(CTimeLog cTimeLog,View view){
        openWriteDB();
        ContentValues values = new ContentValues();
        values.put("PHASE",cTimeLog.getPhase());
        values.put("START",cTimeLog.getStart());
        values.put("INTERRUPCION",cTimeLog.getInterrupcions());
        values.put("STOP",cTimeLog.getStop());
        values.put("DELTA",cTimeLog.getDelta());
        values.put("COMMENTS",cTimeLog.getComments());
        values.put("PROYECTO",cTimeLog.getProyecto());

        try {
            db.insert("TIMELOG", null,values);
            Snackbar.make(view,"Se ha ingresado correctamente el TimeLog",Snackbar.LENGTH_SHORT).show();
        }catch (Exception e){
            Log.e("Error Actualizar: ",e.getMessage());
        }

        closeDB();

    }

    public void closeDB(){
        db.close();
    }


}
