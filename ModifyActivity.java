package com.example.classmate;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.classmate.item.Person;

/**
 * Created by 59800 on 2017/11/4.
 */

public class ModifyActivity extends AppCompatActivity implements View.OnClickListener {

    //public static final int REQUEST_MODIFY = 1111;

    TextView dialogTextName, dialogTextAddress, dialogTextPhone, dialogTextWechat, dialogTextEmail, dialogTextQQ, dialogTextMessage;
    LinearLayout modifyName, modifyAddress, modifyPhone, modifyWechat, modifyEmail, modifyQQ, modifyMessage;
    Button buttonConfirm, buttonCancel;
    Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
        person = (Person) getIntent().getSerializableExtra(MainActivity.SER_KEY);
        initView();
    }

    public void createDialog(final TextView textView, String title) {
        final EditText editText = new EditText(ModifyActivity.this);
        AlertDialog.Builder builder = new AlertDialog.Builder(ModifyActivity.this);
        builder
                .setTitle("修改" + title)
                .setView(editText)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        textView.setText(editText.getText());
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    public void initView() {
        dialogTextName = (TextView) findViewById(R.id.text_name);
        dialogTextAddress = (TextView) findViewById(R.id.text_address);
        dialogTextPhone = (TextView) findViewById(R.id.text_phone);
        dialogTextWechat = (TextView) findViewById(R.id.text_wechat);
        dialogTextEmail = (TextView) findViewById(R.id.text_email);
        dialogTextQQ = (TextView) findViewById(R.id.text_qq);
        dialogTextMessage = (TextView) findViewById(R.id.text_message);


        modifyName = (LinearLayout) findViewById(R.id.modify_name);
        modifyAddress = (LinearLayout) findViewById(R.id.modify_address);
        modifyPhone = (LinearLayout) findViewById(R.id.modify_phone);
        modifyWechat = (LinearLayout) findViewById(R.id.modify_wechat);
        modifyEmail = (LinearLayout) findViewById(R.id.modify_email);
        modifyQQ = (LinearLayout) findViewById(R.id.modify_qq);
        modifyMessage = (LinearLayout) findViewById(R.id.modify_message);

        buttonConfirm = (Button) findViewById(R.id.modify_confirm);
        buttonCancel = (Button) findViewById(R.id.modify_cancel);

        modifyName.setOnClickListener(this);
        modifyAddress.setOnClickListener(this);
        modifyPhone.setOnClickListener(this);
        modifyWechat.setOnClickListener(this);
        modifyEmail.setOnClickListener(this);
        modifyQQ.setOnClickListener(this);
        modifyMessage.setOnClickListener(this);

        buttonConfirm.setOnClickListener(this);
        buttonCancel.setOnClickListener(this);

        dialogTextName.setText(person.name);
        dialogTextAddress.setText(person.address);
        dialogTextPhone.setText(person.phone);
        dialogTextWechat.setText(person.wechat);
        dialogTextEmail.setText(person.email);
        dialogTextQQ.setText(person.qq);
        dialogTextMessage.setText(person.message);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.modify_name:
                createDialog(dialogTextName, getString(R.string.name));
                break;
            case R.id.modify_address:
                createDialog(dialogTextAddress, getString(R.string.address));
                break;
            case R.id.modify_phone:
                createDialog(dialogTextPhone, getString(R.string.phone));
                break;
            case R.id.modify_wechat:
                createDialog(dialogTextWechat, getString(R.string.wechat));
                break;
            case R.id.modify_email:
                createDialog(dialogTextEmail, getString(R.string.email));
                break;
            case R.id.modify_qq:
                createDialog(dialogTextQQ, getString(R.string.qq));
                break;
            case R.id.modify_message:
                createDialog(dialogTextMessage, getString(R.string.message));
                break;
            case R.id.modify_confirm:
                writeDateToDatabase();
                //gotoMain();
                startActivity(new Intent(ModifyActivity.this, MainActivity.class));
                break;
            case R.id.modify_cancel:
                startActivity(new Intent(ModifyActivity.this, MainActivity.class));
                break;
        }
    }

    public void gotoMain() {
        Person person = new Person(
                this.person.id,
                dialogTextName.getText().toString(),
                dialogTextAddress.getText().toString(),
                dialogTextPhone.getText().toString(),
                dialogTextWechat.getText().toString(),
                dialogTextEmail.getText().toString(),
                dialogTextQQ.getText().toString(),
                dialogTextMessage.getText().toString()
        );
        Intent intent = new Intent(ModifyActivity.this, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(MainActivity.SER_KEY, person);
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        //startActivityForResult(intent);
        ModifyActivity.this.finish();
    }

    public void writeDateToDatabase() {

    }
}
