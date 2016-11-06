package cn.edu.gdmec.a07150817.mycontacts3;

/**
 * Created by ys on 2016/10/23.
 */
public class User {
    //字段名常量
    //这要来干啥
    public final  static String NAME = "name";
    public final  static String MOBILE = "mobile";
    public final  static String DANWEI = "danwei";
    public final  static String QQ = "qq";
    public final  static String ADDRESS = "address";
    //类属性
    private String name;
    private String mobile;
    private String danwei;
    private String qq;
    private String address;
    private int id_DB= -1;
    //getter setter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDanwei() {
        return danwei;
    }

    public void setDanwei(String danwei) {
        this.danwei = danwei;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public int getId_DB() {
        return id_DB;
    }

    public void setId_DB(int id_DB) {
        this.id_DB = id_DB;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
