package cn.edu.gdmec.a07150817.mycontacts3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText et1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1 = (EditText) findViewById(R.id.editText);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,1,0,"新镇");
        menu.add(0,2,0,"编辑");
        menu.add(0,3,0,"查看信息");
        menu.add(0,4,0,"删除");
        menu.add(0,5,0,"查询");
        menu.add(0,6,0,"导入到手机通讯录");
        menu.add(0,7,0,"退出");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent();
        switch (item.getItemId()){
            case 1://新增
                //看截图
                intent.setClass(this,AddContactsActivity.class);
                startActivity(intent);
                break;
            case 2://编辑
                intent.setClass(this,UpdateContactsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("user_ID",Integer.parseInt(et1.getText().toString()));
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case 3://查看信息
                intent.setClass(this,ContactsMessageActivity.class);
                intent.putExtra("user_ID",Integer.parseInt(et1.getText().toString()));
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
