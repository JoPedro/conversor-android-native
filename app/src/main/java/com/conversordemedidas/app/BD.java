package com.conversordemedidas.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class BD {
    private SQLiteDatabase bd;

    public BD(Context contexto) {
        AbrirBD abd = new AbrirBD(contexto);
        bd = abd.getWritableDatabase();
    }

    public void inserir(TaxaParaBD forex) {
        ContentValues cv = new ContentValues();
        cv.put("base", forex.getBase());
        cv.put("convert_to", forex.getConvert_to());
        cv.put("resultado", forex.getResultado());
        bd.insert("forex", null, cv);
    }

    public List<TaxaParaBD> buscar() {
        List<TaxaParaBD> lista = new ArrayList<TaxaParaBD>();
        String[] colunas = new String[]{"_id", "base", "convert_to", "resultado"};
        Cursor c = bd.query("forex", colunas, null, null, null, null, "base ASC");
        if (c.getCount() > 0) {
            c.moveToFirst();
            do{
                TaxaParaBD forex = new TaxaParaBD();
                forex.setId(c.getInt(0));
                forex.setBase(c.getString(1));
                forex.setConvert_to(c.getString(2));
                forex.setResultado(c.getDouble(3));
                lista.add(forex);
            }while(c.moveToNext());
        }
        else {
            System.out.println("SEM DADOS");
        }

        return lista;
    }

    public boolean buscar(String leftUnit, String rightUnit) {
        String[] colunas = new String[]{"_id", "base", "convert_to", "resultado"};
        Cursor c = bd.query("forex", colunas, "base = \'"+leftUnit+"\' and convert_to = \'"+rightUnit+"\'", null, null, null, "base ASC");
        if (c.getCount() > 0) {
            System.out.println("Já existe");
            return true;
        }
        else {
            System.out.println("Não existe");
            return false;
        }
    }
}
