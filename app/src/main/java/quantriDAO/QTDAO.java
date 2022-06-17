package quantriDAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import MODEL.Quantri;
import MODEL.User;
import MyDataBase.Database;

public class QTDAO
{
    Database mydata;
// ham khoi tao
    public QTDAO(Context context) {
        mydata = new Database(context);
    }

    //ham kiem tra user
    public boolean CheckUser(User user){
        SQLiteDatabase db = mydata.getReadableDatabase();
        Cursor cs = db.rawQuery("select * from quantri where username =? and password=?",new String[]{
                user.getUsername(),
                user.getPass()
        });
        if(cs.getCount() <=0){
            return false;
        }
        else {
            return true;
        }
    }

    public boolean Update(User user){
        SQLiteDatabase db = mydata.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("password",user.getPass());
        int row = db.update("QUANTRI",values,"username",new String[]{user.getUsername()});
        return row>0;
    }

    public ArrayList<Quantri> readAll(){
        ArrayList<Quantri> data = new ArrayList<>();

        SQLiteDatabase db = mydata.getReadableDatabase();

        // tao con tro de lay du lieu
        Cursor cs = db.rawQuery("select * from QUANTRI", null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            String username = cs.getString(0);
            String password = cs.getString(1);
            data.add(new Quantri(username,password));
            cs.moveToNext();
        }
        cs.close();
        return data;
    }

    //add
    public boolean add(Quantri qt){
        SQLiteDatabase db = mydata.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("username",qt.getUsername());
        values.put("password",qt.getPass());
        long row = db.insert("QUANTRI",null, values);
        return row>0;
    }

    //delete
    public boolean delete(String  user){
        SQLiteDatabase db = mydata.getReadableDatabase();
        ContentValues values = new ContentValues();

        long row = db.delete("QUANTRI","username = ?",
                new String[]{user});
        return row>0;
    }

}
