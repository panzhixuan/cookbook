package com.example.administrator.cookbook.activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.cookbook.R;
import com.example.administrator.cookbook.controller.UserPageController;
import com.example.administrator.cookbook.model.Cuisine;
import com.example.administrator.cookbook.model.Occasion;
import com.example.administrator.cookbook.model.Taste;
import com.example.administrator.cookbook.model.User;
import com.example.administrator.cookbook.view.GetImageByUrl;

import org.w3c.dom.Text;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;

import static cn.bmob.v3.Bmob.getFilesDir;

public class UserFragment extends Fragment implements UserpageControllerListener{
    private MainpageActivity mainpageActivity;
    private final int CODE_FOR_STORAGE = 1;


    private File file=null;

    private ImageView headshot;
    private EditText editTextUserName;
    private Button buttonCaishi;
    private Button buttonCaixi;
    private Button buttonChanghe;
    private EditText editTextRegion;

    private TextView textViewMyCollection;
    private TextView textViewMyView;
    private TextView textViewMyCaipu;
    private UserPageController controller;
    private View view;
    private Button edit;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainpageActivity = (MainpageActivity) context;
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            String name = bundle.get("name").toString();
            Log.d("MyFragment", name);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.userpage, null);
        headshot=view.findViewById(R.id.headshot);
        editTextUserName = view.findViewById(R.id.username);
        buttonCaishi = view.findViewById(R.id.caishi);
        buttonCaixi = view.findViewById(R.id.caixi);
        buttonChanghe = view.findViewById(R.id.changhe);
        editTextRegion = view.findViewById(R.id.region);
        textViewMyCollection = view.findViewById(R.id.my_collection);
        textViewMyView = view.findViewById(R.id.my_view);
        textViewMyCaipu = view.findViewById(R.id.my_caipu);
        edit=view.findViewById(R.id.edit);

        controller = new UserPageController(view.findViewById(R.id.userpage), this);
        setListeners(controller);
        init();
        return view;
    }

    public static UserFragment newInstance(String name) {
        Bundle args = new Bundle();
        args.putString("name", name);
        UserFragment fragment = new UserFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void init() {
        User user= BmobUser.getCurrentUser(User.class);
        GetImageByUrl getImageByUrl = new GetImageByUrl();
        getImageByUrl.setImage(headshot,user.getUser_image().getFileUrl());
        editTextUserName.setText(user.getUser_nickname());
        editTextRegion.setText(user.getUser_place());
        getcuisine(user.getUser_cuision().getObjectId());
        gettaste(user.getUser_taste().getObjectId());
        getoccasion(user.getUser_occasion().getObjectId());
    }

    private void getcuisine(String id) {
        BmobQuery<Cuisine> Query = new BmobQuery<>();
        Query.addWhereEqualTo("objectId", id);
        Query.findObjects(new FindListener<Cuisine>() {
            @Override
            public void done(List<Cuisine> object, BmobException e) {
                if (e == null) {
                    Log.i("BMOB", object.get(0).getName());
                    buttonCaixi.setText(object.get(0).getName());
                } else {
                    Log.i("BMOB", e.toString());
                }
            }
        });
    }

    private void gettaste(String id) {
        BmobQuery<Taste> Query = new BmobQuery<>();
        Query.addWhereEqualTo("objectId", id);
        Query.findObjects(new FindListener<Taste>() {
            @Override
            public void done(List<Taste> object, BmobException e) {
                if (e == null) {
                    Log.i("BMOB", object.get(0).getName());
                    buttonCaishi.setText(object.get(0).getName());
                } else {
                    Log.i("BMOB", e.toString());
                }
            }
        });
    }

    private void getoccasion(String id) {
        BmobQuery<Occasion> Query = new BmobQuery<>();
        Query.addWhereEqualTo("objectId", id);
        Query.findObjects(new FindListener<Occasion>() {
            @Override
            public void done(List<Occasion> object, BmobException e) {
                if (e == null) {
                    Log.i("BMOB", object.get(0).getName());
                    buttonChanghe.setText(object.get(0).getName());
                } else {
                    Log.i("BMOB", e.toString());
                }
            }
        });
    }

    public void setListeners(View.OnClickListener onClickListener){
        view.findViewById(R.id.username).setOnClickListener(onClickListener);
        view.findViewById(R.id.caishi).setOnClickListener(onClickListener);
        view.findViewById(R.id.caixi).setOnClickListener(onClickListener);
        view.findViewById(R.id.changhe).setOnClickListener(onClickListener);
        view.findViewById(R.id.region).setOnClickListener(onClickListener);
        view.findViewById(R.id.my_collection).setOnClickListener(onClickListener);
        view.findViewById(R.id.my_view).setOnClickListener(onClickListener);
        view.findViewById(R.id.my_caipu).setOnClickListener(onClickListener);
        view.findViewById(R.id.logout).setOnClickListener(onClickListener);
        view.findViewById(R.id.headshot).setOnClickListener(onClickListener);
        view.findViewById(R.id.edit).setOnClickListener(onClickListener);
    }
    @Override
    public void toCollectpage(){
        Intent intent=null;
        intent=new Intent(mainpageActivity,CollectpageActivity.class);
        startActivity(intent);
    }
    @Override
    public void toBrowserpage(){
        Intent intent=null;
        intent=new Intent(mainpageActivity,BrowserpageActivity.class);
        startActivity(intent);
    }
    @Override
    public void toMycookbook(){
        Intent intent=null;
        intent=new Intent(mainpageActivity,MycookbookActivity.class);
        startActivity(intent);
    }
    @Override
    public void logout() {
        final AlertDialog.Builder confirmDialog = new AlertDialog.Builder(mainpageActivity);
        confirmDialog.setTitle("注销");
        confirmDialog.setMessage("确定要注销吗？");
        confirmDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                BmobUser.logOut();
                Intent intent=null;
                intent=new Intent(mainpageActivity,LogginpageActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        confirmDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        confirmDialog.show();
    }
    @Override
    public void changheadslot(){
        initPermission();
    }
    @Override
    public void edit(){

        BmobQuery<User> query = new BmobQuery<User>();
        query.addWhereEqualTo("objectId", BmobUser.getCurrentUser(User.class).getObjectId());
        query.findObjects(new FindListener<User>() {

            @Override
            public void done(List<User> object, BmobException e) {
                if(e==null){
                    User currentuser=object.get(0);
                    updateusername(currentuser,editTextUserName.getText().toString());
                    updateuserplace(currentuser,editTextRegion.getText().toString());
                    updateusercuisine(currentuser,buttonCaixi.getText().toString());
                    updateusertaste(currentuser,buttonCaishi.getText().toString());
                    updateuseroccasion(currentuser,buttonChanghe.getText().toString());
                    updateuser_image();
                    Toast.makeText(mainpageActivity, "修改成功", Toast.LENGTH_SHORT).show();
                }else{
                    Log.i("bmob","失败："+e.getMessage());
                    Toast.makeText(mainpageActivity, "修改失败", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
    @Override
    public void caishi(){
        final String[] caishitype = {"清淡", "辣","酸"};
        new AlertDialog.Builder(mainpageActivity).setTitle("选择口味").setSingleChoiceItems(caishitype, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                buttonCaishi.setText(caishitype[i]);
            }
        }).show();
    }
    @Override
    public void caixi(){
        final String[] caixitype = {"川菜", "淮扬菜","徽菜","鲁菜","闽菜","粤菜","湘菜","浙菜"};
        new AlertDialog.Builder(mainpageActivity).setTitle("选择口味").setSingleChoiceItems(caixitype, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                buttonCaixi.setText(caixitype[i]);
            }
        }).show();
    }
    @Override
    public void changhe(){
        final String[] changhetype = {"家", "饭店","快餐店"};
        new AlertDialog.Builder(mainpageActivity).setTitle("选择口味").setSingleChoiceItems(changhetype, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                buttonChanghe.setText(changhetype[i]);
            }
        }).show();
    }

    private void updateuser_image(){

        final User user=BmobUser.getCurrentUser(User.class);
        final BmobFile icon=new BmobFile(new File(getFilesDir(), "header_image.jpg"));

        icon.uploadblock(new UploadFileListener() {
                             @Override
                             public void done(BmobException e) {
                                 User temp=new User();
                                 temp.setUser_image(icon);

                                 Log.d("test",temp.getUser_image().getLocalFile().getName());

                                 temp.update(user.getObjectId(), new UpdateListener() {

                                     @Override
                                     public void done(BmobException e) {
                                         Log.i("bmob", "done: kkkk");
                                         if(e==null){
                                             Log.i("bmob","edituserimage");
                                         }else{
                                             Log.i("bmob","noedituserimage");
                                             Log.i("bmob",e.getMessage());
                                         }
                                     }

                                 });
                             }
                         }
        );

    }
    private void updateusername(User user,String username){
        user.setUser_nickname(username);
        user.update(user.getObjectId(), new UpdateListener() {

            @Override
            public void done(BmobException e) {
                Log.i("bmob", "done: kkkk");
                if(e==null){
                    Log.i("bmob","editpersoninf");
                }else{
                    Log.i("bmob","noeditpersoninf");
                }
            }

        });
    }
    private void updateusercuisine(final User user,String cuisine){
        BmobQuery<Cuisine> Query = new BmobQuery<>();
        Query.addWhereEqualTo("name",cuisine);
        Query.findObjects(new FindListener<Cuisine>() {
            @Override
            public void done(List<Cuisine> object, BmobException e) {
                if (e == null) {
                    User usertemp = new User();
                    usertemp.setObjectId(user.getObjectId());
                    //修改一对一关联，修改帖子和用户的关系
                    usertemp.setUser_cuision(object.get(0));
                    usertemp.update(new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e == null) {
                            } else {
                            }
                        }
                    });
                } else {
                    Log.i("BMOB", e.toString());
                }
            }
        });
    }
    private void updateusertaste(final User user,String taste){
        BmobQuery<Taste> Query = new BmobQuery<>();
        Query.addWhereEqualTo("name",taste);
        Query.findObjects(new FindListener<Taste>() {
            @Override
            public void done(List<Taste> object, BmobException e) {
                if (e == null) {
                    User usertemp = new User();
                    usertemp.setObjectId(user.getObjectId());
                    //修改一对一关联，修改帖子和用户的关系
                    usertemp.setUser_taste(object.get(0));
                    usertemp.update(new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e == null) {
                            } else {
                            }
                        }
                    });
                } else {
                    Log.i("BMOB", e.toString());
                }
            }
        });
    }
    private void updateuseroccasion(final User user,String occasion){
        BmobQuery<Occasion> Query = new BmobQuery<>();
        Query.addWhereEqualTo("name",occasion);
        Query.findObjects(new FindListener<Occasion>() {
            @Override
            public void done(List<Occasion> object, BmobException e) {
                if (e == null) {
                    User usertemp = new User();
                    usertemp.setObjectId(user.getObjectId());
                    //修改一对一关联，修改帖子和用户的关系
                    usertemp.setUser_occasion(object.get(0));
                    usertemp.update(new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e == null) {
                            } else {
                            }
                        }
                    });
                } else {
                    Log.i("BMOB", e.toString());
                }
            }
        });
    }
    private void updateuserplace(User user,String userplace){
        user.setUser_place(userplace);
        user.update(user.getObjectId(), new UpdateListener() {

            @Override
            public void done(BmobException e) {
                Log.i("bmob", "done: kkkk");
                if(e==null){
                    Log.i("bmob","editpersoninf");
                }else{
                    Log.i("bmob","noeditpersoninf");
                }
            }

        });
    }
    private void initPermission() {
        if (ContextCompat.checkSelfPermission(mainpageActivity,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(mainpageActivity,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                ActivityCompat.requestPermissions(mainpageActivity,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        CODE_FOR_STORAGE);
            } else {
                ActivityCompat.requestPermissions(mainpageActivity,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        CODE_FOR_STORAGE);
                Toast.makeText(mainpageActivity, "请设置相应权限", Toast.LENGTH_SHORT).show();
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            Bitmap bitmap = null;
            if (data.getExtras() != null) {
                bitmap = data.getExtras().getParcelable("data");
                headshot.setImageBitmap(bitmap);
                file = new File(getFilesDir(), "header_image.jpg");
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

