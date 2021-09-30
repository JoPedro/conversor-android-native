package com.conversordemedidas.app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AbrirBD extends SQLiteOpenHelper {
    private static final String NOME_BD = "forex";
    private static final int VERSAO_BD = 1;

    public AbrirBD(Context contexto) {
        super(contexto, NOME_BD, null, VERSAO_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table forex(" +
                "_id integer primary key autoincrement, " +
                "base text not null, " +
                "convert_to text not null, " +
                "resultado real not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table forex");
        onCreate(sqLiteDatabase);
    }
}
