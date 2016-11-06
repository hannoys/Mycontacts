package cn.edu.gdmec.a07150817.mycontacts3;
//数据库类

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDB extends SQLiteOpenHelper{
//属性
    //数据库名？
    private static  String DB_NAME = "My_DB.db";
    //数据库版本
    private static int DB_VERSION = 2;
    //数据库对象？
    private SQLiteDatabase db;
    public MyDB(Context context) {
        super(context,DB_NAME,null,DB_VERSION);
        db=getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public SQLiteDatabase openConnection(){
        if (!db.isOpen()){
            db=getWritableDatabase();
        }
        return db;
    }
    //为什么这里又用的是void
    public void closeConnection (){
        try {
        if(db!=null &&db.isOpen()){
            db.close();
        }}catch (Exception e){
            e.printStackTrace();
        }
    }
    public boolean createTable (String createTableSql){
       try {
           openConnection();
           db.execSQL(createTableSql);
       }catch (Exception e){
           e.printStackTrace();
           return false;
       }finally {
           closeConnection();
       }
       return true;
    }
    //保存数据的方法
    public boolean save(String tableName, ContentValues values){
        //每次都要调用一下这个打开数据库连接的方法
        //没有更好的方法吗？
        //现在来说貌似是没有的，或许以后可以用模版解决？
        try {
            openConnection();
            db.insert(tableName,null,values);
        }catch (Exception e){
            e.printStackTrace();
            return  false;
        }finally {
            closeConnection();
        }
        return true;
    }
    //更新数据的方法
    //都是一个尿性啊
    //whereClause这啥
    //whereClause貌似是Sql语句里的where
    public boolean update(String table,ContentValues values,String whereClause,String[] whereArgs){
        try {
            openConnection();
            //自己调用自己？
            db.update(table,values,whereClause,whereArgs);
        }catch (Exception e){
            e.printStackTrace();
            return  false;
        }finally {
            closeConnection();
        }
        return true;
    }
    //删除数据的方法
    //只有一句代码不一样吗
    public boolean delete (String table ,String deleteSql,String obj[]){
        try {
            openConnection();
            //自己调用自己？
            db.delete(table,deleteSql,obj);
        }catch (Exception e){
            e.printStackTrace();
            return  false;
        }finally {
            closeConnection();
        }
        return true;
    }
    //查找数据
    //终于有个不一样的了，但是，我却看不懂
    public Cursor find (String findSql,String obj[]){
        //游标？
        try {
            openConnection();
            Cursor cursor = db.rawQuery(findSql,obj);
            return cursor;
            //果真是游标啊
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }
    //数据表是否存在
    //为什么这个方法是最后一个啊？
    //还有 是exists不是exit
    public boolean isTableExists(String tablename){
        try {
            openConnection();
             String str = "select count(*)xcount from "+tablename;
            //这个用法不是很懂，查询后关闭的意思，还是关闭查询的意思
            db.rawQuery(str,null).close();
        }catch (Exception e){
            e.printStackTrace();
            return  false;
        }finally {
            closeConnection();
        }
        return true;
    }

}
