package org.app.gimalpro;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class BodyActivity extends AppCompatActivity {
    private Bodyitem bodyitem;
    private DBHelper_body dbHelper_body;
    private ArrayList<Bodyitem> bodyitems;
    TextView tv_height;
    TextView tv_weight;
    TextView tv_muscle;
    TextView tv_fat;
    Button bt_input;
    Button bt_update;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body);
        tv_height=findViewById(R.id.tv_height);
        tv_weight=findViewById(R.id.tv_weight);
        tv_muscle=findViewById(R.id.tv_muscle);
        tv_fat=findViewById(R.id.tv_fat);

        bodyitem = new Bodyitem();
        dbHelper_body=new DBHelper_body(this);

        loadRecentdb();


        bt_input=findViewById(R.id.bt_input);
        bt_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(BodyActivity.this, android.R.style.Theme_Material_Light_Dialog);
                dialog.setContentView(R.layout.dialog_body_insert);
                EditText et_height = dialog.findViewById(R.id.et_height);
                EditText et_weight = dialog.findViewById(R.id.et_weight);
                EditText et_muscle = dialog.findViewById(R.id.et_muscle);
                EditText et_fat = dialog.findViewById(R.id.et_fat);



                Button btn_okbody = dialog.findViewById(R.id.bt_okbody);
                btn_okbody.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //오류나면 여기다 0000으로 insert문 만들기

                        if(et_height.length()==0||et_weight.length()==0||et_muscle.length()==0||et_fat.length()==0){
                            tv_height.setText("CM");
                            tv_weight.setText("KG");
                            tv_muscle.setText("KG");
                            tv_fat.setText("KG");

                        }

                        else{
                        //db insert
                        dbHelper_body.insertBody(LoginActivity.UserID,Integer.parseInt(et_height.getText().toString()),Integer.parseInt(et_weight.getText().toString()),Integer.parseInt(et_muscle.getText().toString()),Integer.parseInt(et_fat.getText().toString()));

                        //ui insert
                        Bodyitem item = new Bodyitem();
                        item.setHeight(Integer.parseInt(et_height.getText().toString()));
                        item.setWeight(Integer.parseInt(et_weight.getText().toString()));
                        item.setMuscle(Integer.parseInt(et_muscle.getText().toString()));
                        item.setFat(Integer.parseInt(et_fat.getText().toString()));
                        tv_height.setText(String.valueOf(item.getHeight()));
                        tv_weight.setText(String.valueOf(item.getWeight()));
                        tv_muscle.setText(String.valueOf(item.getMuscle()));
                        tv_fat.setText(String.valueOf(item.getFat()));
                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(), "새로운 신체정보가 추가되었습니다.", Toast.LENGTH_SHORT).show();}

                    }
                });
                dialog.show();
            }
        });
        bt_update=findViewById(R.id.bt_update);
        tv_height.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //db delete
                dbHelper_body.deleteBody(LoginActivity.UserID);
                tv_height.setText("CM");
                tv_weight.setText("KG");
                tv_muscle.setText("KG");
                tv_fat.setText("KG");

                return true;
            }
        });
        bt_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bodyitems = dbHelper_body.selectBody();
                Dialog dialog = new Dialog(BodyActivity.this, android.R.style.Theme_Material_Light_Dialog);
                dialog.setContentView(R.layout.dialog_body_update);
                Button button =dialog.findViewById(R.id.bt_okbodyup);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        EditText et_height = dialog.findViewById(R.id.et_height);
                        EditText et_weight = dialog.findViewById(R.id.et_weight);
                        EditText et_muscle = dialog.findViewById(R.id.et_muscle);
                        EditText et_fat = dialog.findViewById(R.id.et_fat);
                        int height=Integer.parseInt(et_height.getText().toString());
                        int weight=Integer.parseInt(et_weight.getText().toString());
                        int muscle=Integer.parseInt(et_muscle.getText().toString());
                        int fat=Integer.parseInt(et_fat.getText().toString());

                        //update
                        if(!bodyitems.isEmpty()){
                        dbHelper_body.updateBody(LoginActivity.UserID,height,weight,muscle,fat, bodyitems.get(0).getNUMBER());
                        Bodyitem item = new Bodyitem();
                        item.setHeight(Integer.parseInt(et_height.getText().toString()));
                        item.setWeight(Integer.parseInt(et_weight.getText().toString()));
                        item.setMuscle(Integer.parseInt(et_muscle.getText().toString()));
                        item.setFat(Integer.parseInt(et_fat.getText().toString()));
                        tv_height.setText(String.valueOf(item.getHeight()));
                        tv_weight.setText(String.valueOf(item.getWeight()));
                        tv_muscle.setText(String.valueOf(item.getMuscle()));
                        tv_fat.setText(String.valueOf(item.getFat()));
                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(), "신체정보가 업데이트 되었습니다.", Toast.LENGTH_SHORT).show();}

                        else {
                            dialog.dismiss();
                            Toast.makeText(getApplicationContext(), "신체정보를 먼저 추가하세요!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.show();



            }

        });




    }

    private void loadRecentdb() {
        bodyitems = dbHelper_body.selectBody();

        if(!bodyitems.isEmpty()) {
            if ((tv_height.getText()).equals("CM")) {
                tv_height.setText(String.valueOf(bodyitems.get(0).getHeight()));
            }
            if ((tv_weight.getText()).equals("KG")) {
                tv_weight.setText(String.valueOf(bodyitems.get(0).getWeight()));
            }
            if ((tv_muscle.getText()).equals("KG")) {
                tv_muscle.setText(String.valueOf(bodyitems.get(0).getMuscle()));
            }
            if ((tv_fat.getText()).equals("KG")) {
                tv_fat.setText(String.valueOf(bodyitems.get(0).getFat()));
            }
        }
        else{
            tv_height.setText("CM");
            tv_weight.setText("KG");
            tv_muscle.setText("KG");
            tv_fat.setText("KG");
        }
    }


}