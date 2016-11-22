package com.example.keen.netsecnews.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.keen.netsecnews.AboutUsActivity;
import com.example.keen.netsecnews.AdvisionActivity;
import com.example.keen.netsecnews.LoginActivity;
import com.example.keen.netsecnews.MainActivity;
import com.example.keen.netsecnews.NewsContentActivity;
import com.example.keen.netsecnews.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by keen on 2016/6/21.
 */
public class UserFragment extends Fragment implements View.OnClickListener{

    private TextView textView;
    private ImageView imageView;
    private View view;

    private TextView textViewAdvision;
    private TextView textViewAboutUs;

    private TextView textViewLogout;

    private SharedPreferences pref;
    private SharedPreferences.Editor prefEditor;
    private boolean bLogin = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        Log.d("indicator", "UserFragment onCreateView...");

        if(view == null) {
            view = inflater.inflate(R.layout.fragment_user, container, false);
            textView = (TextView)view.findViewById(R.id.textView);
            imageView = (CircleImageView)view.findViewById(R.id.profile_image);
            textView.setOnClickListener(this);
            imageView.setOnClickListener(this);

            textViewAdvision = (TextView)view.findViewById(R.id.id_textview_advision);
            textViewAboutUs = (TextView)view.findViewById(R.id.id_textview_about_us);

            textViewLogout = (TextView)view.findViewById(R.id.id_textview_logout);


            textViewAboutUs.setOnClickListener(this);
            textViewAdvision.setOnClickListener(this);
            textViewLogout.setOnClickListener(this);
        }
        ViewGroup parent = (ViewGroup)view.getParent();
        if(parent != null){
            parent.removeView(view);
        }
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("indicator", "UserFragment onActivityCreated...");
        pref = getActivity().getSharedPreferences("userdata", getContext().MODE_PRIVATE);
        prefEditor = pref.edit();
        bLogin = pref.getBoolean("isLogin", false);
        if(bLogin){
            String username = pref.getString("username", "");
            textView.setText(username);
        }else{
            textView.setText("登录/注册");
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("indicator", "UserFragment onActivityResult...");
        bLogin = pref.getBoolean("isLogin", false);
        if(bLogin){
            String username = pref.getString("username", "");
            textView.setText(username);
        }else{
            textView.setText("登录/注册");
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.profile_image:
            case R.id.textView:
                //每次点击登录按钮，先判断是否登录，默认未登录
                bLogin = pref.getBoolean("isLogin", false);
                if(!bLogin){
                    //Toast.makeText(getActivity(),"Register", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity().getApplicationContext(), LoginActivity.class);
                    //startActivity(intent);
                    startActivityForResult(intent, 1);
                    //bIsLogin = true;
                }else {
                    Toast.makeText(getActivity(),"Ok", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.id_textview_advision:
                //Toast.makeText(getActivity(),"advision", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity().getApplicationContext(), AdvisionActivity.class);
                startActivity(intent);
                break;
            case R.id.id_textview_about_us:
                //Toast.makeText(getActivity(),"about us", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(getActivity().getApplicationContext(), AboutUsActivity.class);
                startActivity(intent2);
                break;
            case R.id.id_textview_logout:
                Toast.makeText(getActivity(),"logout", Toast.LENGTH_SHORT).show();
                prefEditor.putBoolean("isLogin", false);
                prefEditor.commit();
                textView.setText("登录/注册");

                break;
            default:
                break;
        }
    }
}
