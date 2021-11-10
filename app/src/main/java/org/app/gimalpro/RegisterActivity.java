package org.app.gimalpro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    EditText et_id,et_pass,et_name,et_age;
    Button bt_register1;
    RadioGroup rg_gender;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        et_id=findViewById(R.id.et_id);
        et_pass=findViewById(R.id.et_pass);
        et_name=findViewById(R.id.et_name);
        et_age=findViewById(R.id.et_age);
        bt_register1=findViewById(R.id.bt_register1);
        rg_gender=findViewById(R.id.rg_gender);

        //회원가입 버튼 클릭시 실행
        bt_register1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String userID=et_id.getText().toString();
            String userPass=et_pass.getText().toString();
            String userName=et_name.getText().toString();
            int userAge=Integer.parseInt(et_age.getText().toString());
            int gender_id=rg_gender.getCheckedRadioButtonId();
                RadioButton rb = findViewById(gender_id);
                String gender=rb.getText().toString();



                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if(success){//회원등록에 성공한 경우
                                Toast.makeText(getApplicationContext(), "회원등록에 성공하셨습니다.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);

                            }
                            else{//회원등록에 실패한 경우
                                Toast.makeText(getApplicationContext(), "회원등록에 실패하셨습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "에러발생", Toast.LENGTH_SHORT).show();
                        }

                    }
                };
                //서버로 volley를 이용해 요청
                RegisterRequest registerRequest = new RegisterRequest (userID,userPass,userName,userAge,gender,responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }
        });


    }
}