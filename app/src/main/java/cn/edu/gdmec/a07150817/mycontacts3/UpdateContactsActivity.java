package cn.edu.gdmec.a07150817.mycontacts3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateContactsActivity extends AppCompatActivity {
    private EditText nameEditText;
    private EditText mobileEditText;
    private EditText qqEditText;
    private EditText addressEditText;
    private EditText danweiEditText;
    private  User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        //创建ui实例
        nameEditText = (EditText) findViewById(R.id.name);
        mobileEditText = (EditText) findViewById(R.id.mobile);
        qqEditText = (EditText) findViewById(R.id.qq);
        addressEditText = (EditText) findViewById(R.id.address);
        danweiEditText = (EditText) findViewById(R.id.danwei);
        //获取intent也即mainactivity传过来的数据
        Bundle bundle = new Bundle();
        //id表示用户需要修改的值的记录的主键
        int id = bundle.getInt("user_ID");
        ContactsTable ct = new ContactsTable(this);
        user = ct.getUserById(id);
        //把接受到的值放到用户界面显示
        nameEditText.setText(user.getName());
        mobileEditText.setText(user.getMobile());
        qqEditText.setText(user.getQq());
        danweiEditText.setText(user.getDanwei());
        qqEditText.setText(user.getQq());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,1,0,"保存");
        menu.add(0,2,0,"返回");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:
                if (!nameEditText.getText().toString().equals("")){
                    user.setName(nameEditText.getText().toString());
                    user.setMobile(mobileEditText.getText().toString());
                    user.setQq(qqEditText.getText().toString());
                    user.setAddress(addressEditText.getText().toString());
                    user.setDanwei(danweiEditText.getText().toString());
                    ContactsTable ct = new ContactsTable(this);
                    if (ct.updateUser(user)){
                        Toast.makeText(this,"修改成功",Toast.LENGTH_LONG).show();
                        finish();
                    }else{
                        Toast.makeText(this,"修改失败",Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(this,"请先输入数据",Toast.LENGTH_LONG).show();
                }
                break;
            case 2:
                finish();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
