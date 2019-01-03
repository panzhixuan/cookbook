package com.example.administrator.cookbook.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.cookbook.R;
import com.example.administrator.cookbook.controller.RegisterpageController;
import com.example.administrator.cookbook.model.Cuisine;
import com.example.administrator.cookbook.model.Occasion;
import com.example.administrator.cookbook.model.Taste;
import com.example.administrator.cookbook.model.User;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;

public class RegisterpageActivity extends AppCompatActivity implements RegisterpageControllerListener{
    private final int CODE_FOR_STORAGE = 1;

    private ImageView userHeader;

    private File file=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registerpage);
        userHeader=(ImageView) findViewById(R.id.userheader);
        RegisterpageController Controller = new RegisterpageController(this.findViewById(R.id.registerpage), this);
        setListeners(Controller);
        EditText password=(EditText)findViewById(R.id.password);
        EditText confirmpassword=(EditText)findViewById(R.id.confirmpassword);
        password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        confirmpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }
    public void setListeners(View.OnClickListener onClickListener){
        findViewById(R.id.handin).setOnClickListener(onClickListener);
        findViewById(R.id.userheader).setOnClickListener(onClickListener);
    }
    @Override
    public void submit() {
        file = new File(getFilesDir(), "register_user_header.jpg");
        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Bitmap bmp= BitmapFactory.decodeResource(getResources(),R.drawable.default_user_header);
        BufferedOutputStream bos = null;
        try {
            bos = new BufferedOutputStream(new FileOutputStream(file));
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        final BmobFile bmobFile=new BmobFile(file);
        final User user = new User();
        final EditText username=(EditText)findViewById(R.id.username);
        final EditText password=(EditText)findViewById(R.id.password);
        final EditText confirmpassword=(EditText)findViewById(R.id.confirmpassword);
        if(!(password.getText().toString().equals(confirmpassword.getText().toString()))){
            Toast.makeText(RegisterpageActivity.this, "密码不一致", Toast.LENGTH_SHORT).show();
        }
        else {
            bmobFile.uploadblock(new UploadFileListener() {
                @Override
                public void done(BmobException e) {
                    Cuisine cuisine=new Cuisine();
                    cuisine.setObjectId("Hisg5559");
                    Occasion occasion=new Occasion();
                    occasion.setObjectId("UGvR111D");
                    Taste taste=new Taste();
                    taste.setObjectId("Mh9VLLLJ");
                    user.setUsername(username.getText().toString());
                    user.setPassword(password.getText().toString());
                    user.setEmail(username.getText().toString());
                    user.setUser_image(bmobFile);
                    user.setUser_cuision(cuisine);
                    user.setUser_occasion(occasion);
                    user.setUser_taste(taste);
                    user.setUser_place("未设置的地区");
                    user.setUser_nickname("未设置用户名");
                    user.signUp(new SaveListener<User>() {
                        @Override
                        public void done(User user, BmobException e) {
                            if (e == null) {
                                Toast.makeText(RegisterpageActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                RegisterpageActivity.this.finish();
                            } else {
                                Toast.makeText(RegisterpageActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            });
        }
    }
    @Override
    public void selectheader() {
        initPermission();
    }

    private void initPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        CODE_FOR_STORAGE);
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        CODE_FOR_STORAGE);
                Toast.makeText(RegisterpageActivity.this, "请设置相应权限", Toast.LENGTH_SHORT).show();
            }
        } else {
            selectImage();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == CODE_FOR_STORAGE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            selectImage();
        }
    }

    private void selectImage() {
        Intent intent = new Intent("android.intent.action.PICK");
        intent.setType("image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            Bitmap bitmap = null;
            if (data.getExtras() != null) {
                bitmap = data.getExtras().getParcelable("data");
                userHeader.setImageBitmap(bitmap);
                file = new File(getFilesDir(), "register_user_header.jpg");
                if (file.exists()) {
                    file.delete();
                }
                try {
                    file.createNewFile();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                try {
                    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                    bos.flush();
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
