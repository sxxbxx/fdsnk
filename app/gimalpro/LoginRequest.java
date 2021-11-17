package org.app.gimalpro;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {

    final static private String URL = "http://yuijin.ivyro.net/Login.php";
    private Map<String,String> map;

    public LoginRequest(String userID, String userPassword, Response.Listener<String> listener){
    super(Method.POST, URL, listener,null);

    map= new HashMap<>();
    map.put("userID",userID);
    map.put("userPassword",userPassword);

    }

    @Nullable
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
