package cn.edu.gdmec.a07150817.mycontacts3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.Vector;

/**
 * Created by ys on 2016/10/23.
 */
public class ContactsTable {
    //数据库表名字常量
    private final static String TABLENAME = "contactsTable";
    //声明数据库对象
    private MyDB db;
    //构造方法
    public ContactsTable(Context context){
        //创建MyDB对象 context很重要？context是什么？内容物？
        db = new MyDB(context);
        //如果数据表不存在则新疆数据表
        //是不是要调用一下MyDB的方法？

        if (!db.isTableExists(TABLENAME)){
            String createTableSql = "CREATE TABLE IF NOT EXISTS "+TABLENAME +"(id_DB integer "+
                    "primary key AUTOINCREMENT,"+
                    User.NAME+" VARCHAR,"+
                    User.MOBILE+" VARCHAR,"+
                    User.QQ+" VARCHAR,"+
                    User.DANWEI+" VARCHAR,"+
                    User.ADDRESS+" VARCHAR)";
            db.createTable(createTableSql);
        }
    }
//增加数据
    //为什么都是用boolean类型返回的
    public boolean addData(User user){
        //创建ContentVaules对象用于保存数据
        //那么说来Content确实类似于内容物的意思？
        ContentValues values = new ContentValues();
        //赋值
        values.put(User.NAME,user.getName());
        values.put(User.MOBILE,user.getMobile());
        values.put(User.DANWEI,user.getDanwei());
        values.put(User.QQ,user.getQq());
        values.put(User.ADDRESS,user.getAddress());
        //保存数据
        return db.save(TABLENAME,values);
    }
    public User[] getAllUser(){
        //向量？
        Vector<User> v = new Vector<User>();
        Cursor cursor =null;
        try{
            cursor = db.find("select * from "+TABLENAME,null);
            //遍历
            while (cursor.moveToNext()){
                //把游标返回的一行做成一个对象
                User temp = new User();
                temp.setName(cursor.getString(cursor.getColumnIndex(User.NAME)));
                temp.setId_DB(cursor.getInt(cursor.getColumnIndex("id_DB")));
                //mobile danwei qq phone 跟name同
                temp.setDanwei(cursor.getString(cursor.getColumnIndex(User.DANWEI)));
                temp.setMobile(cursor.getString(cursor.getColumnIndex(User.MOBILE)));
                temp.setAddress(cursor.getString(cursor.getColumnIndex(User.ADDRESS)));
                temp.setQq(cursor.getString(cursor.getColumnIndex(User.QQ)));
                //放进向量
                v.add(temp);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (cursor!=null){
                cursor.close();
            }
            //为什么不在MYdb里面closeConnection
            db.closeConnection();
        }
        if (v.size()>0){
           return v.toArray(new User[]{});
        }else {
            User[] users = new User[1];
            User user = new User();
            user.setName("没有内容");
            //什么意思
            users[0] = user;
            return users;
        }
    }
    public User getUserById(int id){
        Cursor cursor = null;
        User temp = new User();
        Vector<User> v = new Vector<User>();
        try{
                cursor = db.find(" select * from "+TABLENAME+" where "+" id_DB=?",new String[]{id+""});
                cursor.moveToNext();
                //把游标返回的一行做成一个对象
                temp.setId_DB(cursor.getInt(cursor.getColumnIndex("id_DB")));
                temp.setName(cursor.getString(cursor.getColumnIndex(User.NAME)));
                temp.setDanwei(cursor.getString(cursor.getColumnIndex(User.DANWEI)));
                temp.setMobile(cursor.getString(cursor.getColumnIndex(User.MOBILE)));
                temp.setAddress(cursor.getString(cursor.getColumnIndex(User.ADDRESS)));
                temp.setQq(cursor.getString(cursor.getColumnIndex(User.QQ)));
                return temp;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (cursor!=null){
                cursor.close();
            }
            db.closeConnection();
        }
        //像上面那个方法一样添加
        return  temp;
    }
    public boolean updateUser(User user){
        ContentValues values = new ContentValues();
        values.put("id_db",user.getId_DB());
        values.put(User.NAME,user.getName());
        values.put(User.MOBILE,user.getMobile());
        values.put(User.DANWEI,user.getDanwei());
        values.put(User.QQ,user.getQq());
        values.put(User.ADDRESS,user.getAddress());
        //其他同上
        //不懂
        //保存数据
        return  db.update(TABLENAME,values,"id_DB=?",new String[]{user.getId_DB()+""});
    }
    public User[] findUserByKey (String key){
        Vector<User> v = new Vector<User>();
        Cursor cursor = null;
        try {
           cursor = db.find("select * form +"+TABLENAME +" where "
                   +User.NAME+" like '%"+key+"%'"+
                   " or "+User.MOBILE+" like '%"+key+"%' "+
                    " or "+User.QQ+" like '%"+key+"%' ",null);
           while(cursor.moveToNext()){
               User temp = new User();
            temp.setName(cursor.getString(cursor.getColumnIndex(User.NAME)));
            temp.setId_DB(cursor.getInt(cursor.getColumnIndex("id_DB")));
            temp.setDanwei(cursor.getString(cursor.getColumnIndex(User.DANWEI)));
            temp.setMobile(cursor.getString(cursor.getColumnIndex(User.MOBILE)));
            temp.setAddress(cursor.getString(cursor.getColumnIndex(User.ADDRESS)));
            temp.setQq(cursor.getString(cursor.getColumnIndex(User.QQ)));
            v.add(temp);
           }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //为什么不在MYdb里面closeConnection
            if (cursor!= null){
            cursor.close();}
            db.closeConnection();
        }
        if (v.size()>0){
           return  v.toArray(new User[]{});
        }else {
            User[] users = new User[1];
            User user = new User();
            user.setName("没有内容");
            //什么意思
            users[0] = user;
            return users;
        }

        }
    public boolean deleteByUser (User user){
        db.delete(TABLENAME," id_DB=?",new String[]{user.getId_DB()+"" });
        return true;
    }
}
