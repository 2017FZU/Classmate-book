package com.example.classmate;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.classmate.item.Person;
import com.example.classmate.view.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_MODIFY = 1111;
    RecyclerView recyclerView;
    List<Person> persons = new ArrayList<Person>();
    List<Person> selectedList = new ArrayList<Person>();
    MyAdapter myAdapter;
    MyAdapter selectedAdapter;
    MyAdapter.OnItemClickListener onItemClickListener = new MyAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            Person person = persons.get(position);
            gotoModify(person);
        }

        @Override
        public void onItemLongClick(View view, int position) {
            delete(position);
        }
    };

    TextView addButton;
    TextView selectButton;
    TextView clearButton;
    TextView emailButton;
    TextView excelButton;

    FrameLayout dialogAddLayout;
    TextView confirmButton;
    TextView cancelButton;
    LinearLayout dialogAdd;

    TextView dialogTextName, dialogTextAddress,dialogTextPhone,dialogTextWechat,dialogTextEmail,dialogTextQQ,dialogTextMessage;

    public final static String SER_KEY = "com.tutor.objecttran.ser";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDatabase();
         initData();
        initView();
        setDialogAdd();
    }

    public void initDatabase() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode) {
            case REQUEST_MODIFY:
                if (resultCode == RESULT_OK) {
                    Person person = (Person) data.getSerializableExtra(MainActivity.SER_KEY);
                }
                break;
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        loadData();
    }

    public void setMyAdapter(){
        myAdapter = new MyAdapter(persons);
        myAdapter.setOnItemClickListener(onItemClickListener);
        recyclerView.setAdapter(myAdapter);
    }

    public void loadData(){
        //persons.clear();


        setMyAdapter();

        //myAdapter.notifyDataSetChanged();
//        if (persons.size() != 0) {
//            for (int i = 0; i < persons.size(); i++) {
//                System.out.println("==========" + persons.get(i).id);
//            }
//        }

    }

    public void gotoModify(Person person){

    }

    public void delete(final int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder
                .setMessage("确定删除？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        persons.remove(position);
                        myAdapter.notifyItemRemoved(position);
                        myAdapter.notifyItemRangeRemoved(position, myAdapter.getItemCount() - 1);

                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }

    public void add(){
        //startActivity(new Intent(MainActivity.this, SelectActivity.class));
        dialogAddLayout.setVisibility(View.VISIBLE);
        dialogTextName.setText(R.string.name);
        dialogTextAddress.setText(R.string.address);
        dialogTextPhone.setText(R.string.phone);
        dialogTextWechat.setText(R.string.wechat);
        dialogTextEmail.setText(R.string.email);
        dialogTextQQ.setText(R.string.qq);
        dialogTextMessage.setText(R.string.message);

    }

    public void selectData(String name){
        selectedList.clear();
        for (int i = 0; i < persons.size(); i++) {
            if (persons.get(i).name.contains(name)) {
                selectedList.add(persons.get(i));
            }
        }
        if (selectedList.size() == 0) {
            Toast.makeText(MainActivity.this,"您查找的同学不存在！",Toast.LENGTH_SHORT).show();
        }
        selectedAdapter = new MyAdapter(selectedList);
        selectedAdapter.setOnItemClickListener(onItemClickListener);
        recyclerView.setAdapter(selectedAdapter);

    }

    public void select(){
        final EditText editText = new EditText(MainActivity.this);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder
                .setTitle("请输入需要查询的名字")
                .setView(editText)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Intent intent = new Intent(MainActivity.this, SelectActivity.class);
//                        intent.putExtra("name", editText.getText().toString());
//                        startActivity(intent);
                        selectData(editText.getText().toString());
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

    public void editName(){
        LinearLayout view = (LinearLayout) findViewById(R.id.edit_name);
        final TextView textView = (TextView) findViewById(R.id.text_name);
        final EditText editText = new EditText(MainActivity.this);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder
                        .setTitle("修改")
                        .setView(editText)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                textView.setText(editText.getText());
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();
            }
        });
    }

    public void setDialogAdd(){
        setEdit(R.id.edit_name, R.id.text_name);
        setEdit(R.id.edit_address, R.id.text_address);
        setEdit(R.id.edit_phone, R.id.text_phone);
        setEdit(R.id.edit_wechat, R.id.text_wechat);
        setEdit(R.id.edit_email, R.id.text_email);
        setEdit(R.id.edit_qq, R.id.text_qq);
        setEdit(R.id.edit_message, R.id.text_message);
    }

    public void setEdit(int id_clickItem, int id_textView) {
        final LinearLayout view = (LinearLayout) findViewById(id_clickItem);
        final TextView textView = (TextView) findViewById(id_textView);
        final EditText editText = new EditText(MainActivity.this);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder
                        .setTitle("修改" + textView.getText().toString())
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
        });
    }



    public void addItem(){
        Integer id = 0;
        if (persons.size() != 0) {
            id = persons.get(persons.size() - 1).id + 1;
        }
        Person person = new Person(
                id,
                dialogTextName.getText().toString(),
                dialogTextAddress.getText().toString(),
                dialogTextPhone.getText().toString(),
                dialogTextWechat.getText().toString(),
                dialogTextEmail.getText().toString(),
                dialogTextQQ.getText().toString(),
                dialogTextMessage.getText().toString()
        );

        persons.add(person);
        myAdapter.notifyItemInserted(persons.size());
        myAdapter.notifyDataSetChanged();

    }

    public Boolean enableToAddItem(){

        if (dialogTextName.getText().toString().equals(getString(R.string.name)) ||
                dialogTextName.getText() =="") return false;
        if (dialogTextAddress.getText().toString().equals(getString(R.string.address)) ||
                dialogTextAddress.getText() =="") return false;
        if (dialogTextPhone.getText().toString().equals(getString(R.string.phone)) ||
                dialogTextPhone.getText() =="") return false;
        if (dialogTextWechat.getText().toString().equals(getString(R.string.wechat)) ||
                dialogTextWechat.getText() =="") return false;
        if (dialogTextEmail.getText().toString().equals(getString(R.string.email)) ||
                dialogTextEmail.getText() =="") return false;
        if (dialogTextQQ.getText().toString().equals(getString(R.string.qq)) ||
                dialogTextQQ.getText() =="") return false;
        if (dialogTextMessage.getText().toString().equals(getString(R.string.message)) ||
                dialogTextMessage.getText() =="") return false;

        return true;
    }

    public void sendEmail(){
//        Toast.makeText(this, "sendEmail", Toast.LENGTH_SHORT).show();
        /*Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:497977128@qq.com"));
        intent.setData(Uri.parse("mailto:1156456212@qq.com"));
//        intent.putExtra(Intent.EXTRA_SUBJECT, "这是标题");
//        intent.putExtra(Intent.EXTRA_TEXT, "这是内容");
        //startActivity(data);
        startActivity(Intent.createChooser(intent, "请选择邮件类应用"));*/

//        String[] to = {"1156456212@qq.com", "497977128@qq.com"};

        ArrayList<String> receiver = new ArrayList<>();

        for (int i = 0; i < persons.size(); i++) {
            receiver.add(persons.get(i).email);
        }

//        String tto[] = receiver.toArray(new String[receiver.size()]);

        //Toast.makeText(this, receiver.size(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        //设置文本格式
        intent.setType("text/plain");
        //设置对方邮件地址
        intent.putExtra(android.content.Intent.EXTRA_EMAIL, receiver.toArray(new String[receiver.size()]));
        startActivity(Intent.createChooser(intent,"请选择邮件类应用"));
    }

    public void saveToExcel(){
        Toast.makeText(this, "saveToExcel", Toast.LENGTH_SHORT).show();

    }

    public void setListener() {

        excelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToExcel();
            }
        });

        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectData("");
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });

        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select();
            }
        });

        dialogAddLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAddLayout.setVisibility(View.GONE);
            }
        });

        dialogAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (enableToAddItem()) {
                    addItem();
                    dialogAddLayout.setVisibility(View.GONE);
                } else {
                    Toast.makeText(MainActivity.this, "请输入完整数据", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAddLayout.setVisibility(View.GONE);
            }
        });

        myAdapter.setOnItemClickListener(onItemClickListener);
    }

    public void initView(){

        myAdapter = new MyAdapter(persons);

        recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);

        emailButton = (TextView) findViewById(R.id.button_send_email);
        addButton = (TextView) findViewById(R.id.button_add);
        selectButton = (TextView) findViewById(R.id.button_select);
        confirmButton = (TextView) findViewById(R.id.btn_add_confirm);
        cancelButton = (TextView) findViewById(R.id.btn_add_cancel);
        clearButton  = (TextView) findViewById(R.id.button_clear);
        excelButton = (TextView) findViewById(R.id.button_excel);

        dialogAddLayout = (FrameLayout) findViewById(R.id.dialog_add_layout);
        dialogAdd = (LinearLayout) findViewById(R.id.dialog_add);

        dialogTextName = (TextView) findViewById(R.id.text_name);
        dialogTextAddress = (TextView) findViewById(R.id.text_address);
        dialogTextPhone = (TextView) findViewById(R.id.text_phone);
        dialogTextWechat = (TextView) findViewById(R.id.text_wechat);
        dialogTextEmail = (TextView) findViewById(R.id.text_email);
        dialogTextQQ = (TextView) findViewById(R.id.text_qq);
        dialogTextMessage = (TextView) findViewById(R.id.text_message);

        setListener();
    }

    public void initData(){
        for (int i = 0; i < 10; i++) {
            String text = ""+ i;
//            Person person = new Person(i, text, text,text,text,text,text,text);
            persons.add(new Person(i, text, text,text,text,text,text,text));
        }
    }
}
