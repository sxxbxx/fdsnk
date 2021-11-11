package org.app.gimalpro;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper_body extends SQLiteOpenHelper {
    private static final int DB_version =1;
    private static final String DB_name ="User_body.db";


    public DBHelper_body(@Nullable Context context) {
        super(context, DB_name, null, DB_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS Body (NUMBER INTEGER PRIMARY KEY AUTOINCREMENT, ID TEXT NOT NULL, Height REAL NOT NULL, Weight REAL NOT NULL, Muscle REAL NOT NULL, Fat REAL NOT NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public ArrayList<Bodyitem> selectBody(){
        ArrayList<Bodyitem> bodyitems = new ArrayList<>();
        SQLiteDatabase db=getReadableDatabase();
        String _ID = LoginActivity.UserID;
        Cursor cursor = db.rawQuery("SELECT * FROM Body WHERE ID='"+_ID+"'",null);
        if (cursor.getCount() !=0){    //if문의 getCount가 0이 아니라는 의미는 db에 정보가 있다는 뜻
            while(cursor.moveToNext()){
                int NUMBER = cursor.getInt(cursor.getColumnIndexOrThrow("NUMBER"));
                String ID = cursor.getString(cursor.getColumnIndexOrThrow("ID"));
                Double Height = cursor.getDouble(cursor.getColumnIndexOrThrow("Height"));
                Double Weight = cursor.getDouble(cursor.getColumnIndexOrThrow("Weight"));
                Double Muscle = cursor.getDouble(cursor.getColumnIndexOrThrow("Muscle"));
                Double Fat = cursor.getDouble(cursor.getColumnIndexOrThrow("Fat"));

                Bodyitem bodyitem = new Bodyitem();
                bodyitem.setNUMBER(NUMBER);
                bodyitem.setID(ID);
                bodyitem.setHeight(Height);
                bodyitem.setWeight(Weight);
                bodyitem.setMuscle(Muscle);
                bodyitem.setFat(Fat);
                bodyitems.add(bodyitem);
            }
    }
        cursor.close();
        return bodyitems;
    }



    public void insertBody(String _ID,double _Height,double _Weight, double _Muscle, double _Fat){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO Body(ID ,Height, Weight, Muscle,Fat) VALUES ('"+_ID+"','"+_Height+"', '"+ _Weight+"','"+_Muscle+"','"+_Fat+"');");
    }
    public void updateBody(String _ID,double _Height, double _Weight ,double _Muscle,  double _Fat,double _NUMBER){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE Body SET Height ='"+_Height+"',Weight='"+_Weight+"',Muscle ='"+_Muscle+"',Fat='"+_Fat+"' WHERE ID='"+_ID+"' AND NUMBER ='"+_NUMBER+"'");
    }
    public void deleteBody(String _ID,int _NUMBER){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM Body WHERE ID = '"+_ID+"' AND NUMBER='"+_NUMBER+"'");
    }
}
