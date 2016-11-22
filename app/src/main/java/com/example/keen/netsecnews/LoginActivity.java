package com.example.keen.netsecnews;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.keen.netsecnews.news.News;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.ResponseStream;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import java.util.HashMap;
import java.util.List;

/**
 * Created by keen on 2016/7/20.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private String login_url = "http://103.228.130.66:8080/netec/servlet/UserLogin";
    Toolbar login_toolbar;
    private Button loginButton;
    private EditText userNameText;
    private EditText passwordText;

    public LoginInfo loginInfo;
    private Gson gson = new Gson();
    private HashMap<String, String> session =new HashMap<String, String>();

    private boolean bLogin = false;

    private SharedPreferences pref;
    private SharedPreferences.Editor prefEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);


        login_toolbar = (Toolbar)findViewById(R.id.login_toolbar);
        login_toolbar.setTitle("");
        setSupportActionBar(login_toolbar);
        login_toolbar.setNavigationIcon(R.drawable.img_menu);


        userNameText = (EditText)findViewById(R.id.userNameText);
        passwordText = (EditText)findViewById(R.id.passwdText);
        loginButton = (Button)findViewById(R.id.bnLogin);
        loginButton.setOnClickListener(this);
        prefEditor = getSharedPreferences("userdata", MODE_PRIVATE).edit();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bnLogin:
                //login
                CheckUser();
                break;
            default:
                break;
        }
    }



    private void CheckUser(){
        String username = userNameText.getText().toString();
        String password = passwordText.getText().toString();
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        params.addBodyParameter("username", username);
        params.addBodyParameter("password", password);

        httpUtils.send(HttpMethod.POST, login_url, params, new RequestCallBack<String>() {
            Intent intent = new Intent();
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                Log.e("indicator", "访问成功");
                System.out.print(responseInfo.result);
                loginInfo = gson.fromJson(responseInfo.result,LoginInfo.class);
                if(loginInfo.getFlag().equals("ok")){
//                    session.put("s_userid", loginInfo.getUserId());
//                    session.put("s_username", loginInfo.getUserName());
//                    session.put("s_sessionid", loginInfo.getSessionId());
                    prefEditor.putBoolean("isLogin", true);
                    //prefEditor.putString("userid", loginInfo.getUserId());
                    prefEditor.putString("username", loginInfo.getUserName());
                    prefEditor.putString("token", loginInfo.getToken());
                    prefEditor.commit();
                    //intent.putExtra("isLogin", true);
                    setResult(1, intent);
                    finish();

                    //跳转到登录后的界面
                    bLogin = true;
                }else{
                    prefEditor.putBoolean("isLogin", false);
                    prefEditor.commit();
                    bLogin = false;
                    Toast.makeText(LoginActivity.this,"Failed to login...", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(HttpException e, String s) {
                Log.e("indicator", "访问失败");
                bLogin = false;
                Toast.makeText(LoginActivity.this,"Failed to login...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
