package MyDataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context) {
        super(context, "lab9", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create quantri table
        String sql = "CREATE TABLE QUANTRI(username Text primary  key not null, password Text)";
        db.execSQL(sql);
        db.execSQL("INSERT INTO QUANTRI values('admin@abc.com','12345')");
        db.execSQL("INSERT INTO QUANTRI values('admin123@abc.com','12345')");
        //create user table
        sql = "CREATE TABLE USER(UserName Text primary  key not null, Password Text)";
        db.execSQL(sql);
        db.execSQL("INSERT INTO QUANTRI values('abc@abc.com','12345')");
        db.execSQL("INSERT INTO QUANTRI values('cde@abc.com','12345')");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS QUANTRI");
        db.execSQL("DROP TABLE IF EXISTS USER");
        onCreate(db);

    }
}
