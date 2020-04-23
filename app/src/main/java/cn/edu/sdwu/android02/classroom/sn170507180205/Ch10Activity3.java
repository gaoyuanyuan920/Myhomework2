package cn.edu.sdwu.android02.classroom.sn170507180205;

import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

//子Activity
public class Ch10Activity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_ch10_3);
    }

    public void ok(View view) {

        EditText editText = (EditText) findViewById(R.id.ch10_3_tv);
        String content = editText.getText().toString();
        //将数据放在Intent里面
        Intent intent = new Intent();
        intent.putExtra("name", content);
        setResult(RESULT_OK, intent);// iHEE
        finish();
    }
    public void cancel(View view) {
        setResult(RESULT_CANCELED);//取消
        finish();
    }

}
