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
        db.execSQL("CREATE TABLE IF NOT EXISTS Body (NUMBER INTEGER PRIMARY KEY AUTOINCREMENT, ID TEXT NOT NULL, Height INTEGER NOT NULL, Weight INTEGER NOT NULL, Muscle INTEGER NOT NULL, Fat INTEGER NOT NULL)");

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
                int Height = cursor.getInt(cursor.getColumnIndexOrThrow("Height"));
                int Weight = cursor.getInt(cursor.getColumnIndexOrThrow("Weight"));
                int Muscle = cursor.getInt(cursor.getColumnIndexOrThrow("Muscle"));
                int Fat = cursor.getInt(cursor.getColumnIndexOrThrow("Fat"));

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
        db.close();
        return bodyitems;
    }



    public void insertBody(String _ID,int _Height,int _Weight, int _Muscle, int _Fat){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO Body(ID ,Height, Weight, Muscle,Fat) VALUES ('"+_ID+"','"+_Height+"', '"+ _Weight+"','"+_Muscle+"','"+_Fat+"');");
    }
    public void updateBody(String _ID,int _Height, int _Weight ,int _Muscle,  int _Fat,int _NUMBER){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE Body SET Height ='"+_Height+"',Weight='"+_Weight+"',Muscle ='"+_Muscle+"',Fat='"+_Fat+"' WHERE ID='"+_ID+"' AND NUMBER ='"+_NUMBER+"'");
    }
    public void deleteBody(String _ID){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM Body WHERE ID = '"+_ID+"'");
    }
}
