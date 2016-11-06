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
    public FindDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find);
        setTitle("联系人查询");
        Button find1 = (Button) findViewById(R.id.find1);
        Button cancel1 = (Button) findViewById(R.id.cancel1);
        find1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText value1 = (EditText) findViewById(R.id.value1);
               // ContactsTable ct = new ContactsTable(MainActivity.this);

            }
        });
    }
}
