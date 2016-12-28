package cn.edu.gdmec.a07150817.mycontacts3;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


/**
 * Created by ys on 2016/11/1.
 */


public class FindDialog extends Dialog {
    private Context l_context;
    public FindDialog(Context context) {
        super(context);
        l_context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find);
        setTitle("联系人查询");
        final Button find1 = (Button) findViewById(R.id.find1);
        Button cancel1 = (Button) findViewById(R.id.cancel1);
        find1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText value1 = (EditText) findViewById(R.id.value1);
                ContactsTable ct = new ContactsTable(l_context);
                //模糊查询联系人
                User[] users = ct.findUserByKey(value1.getText().toString());
                for(int i = 0;i<users.length;i++){
                    System.out.println("姓名:"+users[i].getName()+"电话:"+users[i].getMobile());
                }
                //主界面的数据更新？
                ((MainActivity)l_context).setUsers(users);
                //刷新联系人列表显示
                ((MainActivity)l_context).getListViewAdapter().notifyDataSetChanged();
                //设置被选中项目
                ((MainActivity)l_context).setSelectItem(0);
                dismiss();
            }
        });
        cancel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
