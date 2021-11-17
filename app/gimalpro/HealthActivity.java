package org.app.gimalpro;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HealthActivity extends AppCompatActivity {

    private RecyclerView rv_todo;
    private FloatingActionButton bt_write;
    private ArrayList<Todoitem> todoitems;
    private DBHelp_health_list dbHelpHealthlist;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);

        setInit();
    }

    private void setInit() {
        dbHelpHealthlist = new DBHelp_health_list(this);
        rv_todo = findViewById(R.id.rv_todo);
        bt_write = findViewById(R.id.bt_write);
        todoitems = new ArrayList<>();

        //아래함수
        loadRecentdb();

        bt_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(HealthActivity.this, android.R.style.Theme_Material_Light_Dialog);
                dialog.setContentView(R.layout.dialog);
                EditText et_title = dialog.findViewById(R.id.et_title);
                EditText et_content = dialog.findViewById(R.id.et_content);
                Button btn_ok = dialog.findViewById(R.id.bt_ok);
                btn_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //db insert
                        String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());//현재시간 받아오기
                        dbHelpHealthlist.insertTodo(LoginActivity.UserID,et_title.getText().toString(),et_content.getText().toString(),currentTime);

                        //ui insert
                        Todoitem item = new Todoitem();
                        item.setTitle(et_title.getText().toString());
                        item.setContent(et_content.getText().toString());
                        item.setWritedate(currentTime);

                        adapter.additem(item);
                        rv_todo.smoothScrollToPosition(0);
                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(), "할 일 목록이 추가되었습니다.", Toast.LENGTH_SHORT).show();

                    }
                });
                dialog.show();

            }
        });

    }

    private void loadRecentdb() {
        //저장되어있던 db가져오는 함수
        todoitems= dbHelpHealthlist.getTodolist();
        if (adapter==null){
            adapter = new Adapter(todoitems,this);
            rv_todo.setHasFixedSize(true);
            rv_todo.setAdapter(adapter);


        }
    }
}