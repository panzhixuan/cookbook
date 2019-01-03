package com.example.administrator.cookbook.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.cookbook.R;
import com.example.administrator.cookbook.controller.CookbookdetailpageController;
import com.example.administrator.cookbook.model.Comment;
import com.example.administrator.cookbook.model.Cookbook;
import com.example.administrator.cookbook.model.Cuisine;
import com.example.administrator.cookbook.model.Occasion;
import com.example.administrator.cookbook.model.Taste;
import com.example.administrator.cookbook.model.User;
import com.example.administrator.cookbook.view.GetImageByUrl;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.datatype.BmobRelation;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class CookbookdetailpageActivity extends AppCompatActivity implements CookbookdetailpageControllerListener{
    private String cookbook_id;
    private TextView cookbookname;
    private TextView username;
    private TextView caishi;
    private TextView caixi;
    private TextView changhe;
    private ImageView cover;
    private TextView nutrition;
    private TextView tips;
    private LinearLayout materialList;
    private LinearLayout stepList;
    private Button favor;
    private Button publish;
    private List<Comment> CommentList = new ArrayList<Comment>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cookbookdetailpage);
        Intent intent = getIntent();
        cookbook_id = intent.getStringExtra("cookbook_id");
        CookbookdetailpageController Controller = new CookbookdetailpageController(this.findViewById(R.id.cookbookdetailpage), this);
        setListeners(Controller);
    }

    @Override
    protected void onResume() {
        super.onResume();
        init();
        initData();
    }

    public void setListeners(View.OnClickListener onClickListener){
        findViewById(R.id.favor).setOnClickListener(onClickListener);
        findViewById(R.id.publish).setOnClickListener(onClickListener);
    }

    private void init(){
        cookbookname=findViewById(R.id.cookbookname);
        username=findViewById(R.id.username);
        caishi=findViewById(R.id.caishi);
        caixi=findViewById(R.id.caixi);
        changhe=findViewById(R.id.changhe);
        cover=findViewById(R.id.cover);
        nutrition=findViewById(R.id.nutrition);
        tips=findViewById(R.id.tips);
        materialList=findViewById(R.id.material_list);
        stepList=findViewById(R.id.step_list);
        favor=findViewById(R.id.favor);
        publish=findViewById(R.id.publish);
    }

    private void initData(){
        BmobQuery<Cookbook> bmobQuery = new BmobQuery<Cookbook>();
        bmobQuery.include("user");
        materialList.removeAllViews();
        stepList.removeAllViews();
        CommentList.clear();
        bmobQuery.getObject(cookbook_id, new QueryListener<Cookbook>() {
            @Override
            public void done(Cookbook cookbook,BmobException e) {
                if(e==null){
                    cookbookname.setText(cookbook.getCb_name());
                    addbrowse(cookbook_id,cookbook.getCb_browse()+1);
                    adduserbrowse(cookbook);
                    loadlikes(cookbook);
                    loadComment(cookbook);
                    username.setText(cookbook.getUser().getUser_nickname());
                    getcuisine(cookbook.getCuisine().getObjectId());
                    gettaste(cookbook.getTaste().getObjectId());
                    getoccasion(cookbook.getOccasion().getObjectId());
                    GetImageByUrl getImageByUrl = new GetImageByUrl();
                    getImageByUrl.setImage(cover, cookbook.getCb_image().getFileUrl());
                    nutrition.setText(cookbook.getCb_nutrition());
                    tips.setText(cookbook.getCb_tips());
                    final JsonArray materials = new JsonParser().parse(cookbook.getCb_ingredint()).getAsJsonArray();
                    final JsonArray steps = new JsonParser().parse(cookbook.getCb_content()).getAsJsonArray();

                    for (int i = 0; i < materials.size(); i++) {
                        JsonObject jsonObject=materials.get(i).getAsJsonObject();
                        String name=jsonObject.get("name").getAsString();
                        String num=jsonObject.get("num").getAsString();
                        final View temp = View.inflate(CookbookdetailpageActivity.this, R.layout.material_item1, null);
                        TextView materialName=(TextView)temp.findViewById(R.id.material_name);
                        materialName.setText(name);
                        TextView materialNumber=(TextView)temp.findViewById(R.id.material_quantity);
                        materialNumber.setText(num);
                        materialList.addView(temp, -1);
                    }
                    for (int i = 0; i <steps.size(); i++) {
                        JsonObject jsonObject=steps.get(i).getAsJsonObject();
                        String step=jsonObject.get(""+i).getAsString();
                        final View temp = View.inflate(CookbookdetailpageActivity.this, R.layout.add_cookbook_step1, null);
                        TextView stepDesc = (TextView) temp.findViewById(R.id.cookbook_step_desc);
                        int j=i+1;
                        stepDesc.setText(j+"."+step);
                        stepList.addView(temp, -1);
                    }
                }else{
                    e.printStackTrace();
                }
            }
        });
    }
    private void loadComment(Cookbook cookbook) {
        BmobQuery<Comment> bmobQuery = new BmobQuery<Comment>();
        // bmobQuery.addWhereContains("ckb_name",name);
        bmobQuery.addWhereEqualTo("cookbook",cookbook);
        bmobQuery.include("user");
        bmobQuery.findObjects(new FindListener<Comment>() {
            @Override
            public void done(List<Comment> list, BmobException e) {
                if (e == null) {
                    int n = list.size();
                    CommentList.clear();
                    System.out.println(n);
                    for (int i = 0; i < n; i++) {
                        Comment comment = list.get(i);
                        CommentList.add(comment);
                    }
                    CommentAdapter adapter = new CommentAdapter(CookbookdetailpageActivity.this, R.layout.commentlist, CommentList);
                    ListView listView = (ListView) findViewById(R.id.listview);
                    listView.setAdapter(adapter);
                }
                else {
                    System.out.println(e.getErrorCode());
                }
            }
        });
    }
    private void getcuisine(String id) {
        BmobQuery<Cuisine> Query = new BmobQuery<>();
        Query.addWhereEqualTo("objectId", id);
        Query.findObjects(new FindListener<Cuisine>() {
            @Override
            public void done(List<Cuisine> object, BmobException e) {
                if (e == null) {
                    Log.i("BMOB", object.get(0).getName());
                    caixi.setText(object.get(0).getName());
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
                    caishi.setText(object.get(0).getName());
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
                    changhe.setText(object.get(0).getName());
                } else {
                    Log.i("BMOB", e.toString());
                }
            }
        });
    }

    private void addbrowse(String id,Integer num){
        Cookbook p2 = new Cookbook();
        p2.setCb_browse(num);
        p2.update(id, new UpdateListener() {

            @Override
            public void done(BmobException e) {
                if(e==null){
                    Log.i("BMOB", "addbrowse");
                }else{
                    Log.i("BMOB", e.toString());
                }
            }

        });
    }

    @Override
    public void favorite() {
        BmobQuery<Cookbook> bmobQuery = new BmobQuery<Cookbook>();
        bmobQuery.getObject(cookbook_id, new QueryListener<Cookbook>() {
            @Override
            public void done(final Cookbook cookbook, BmobException e) {
                if (e == null) {
                    if(favor.getText().equals("取消收藏")) {
                        Integer a = cookbook.getCb_favorite();
                        a -= 1;
                        dellikes(cookbook);
                        dellikes1(cookbook);
                        updatefavarite(cookbook, a);
                        favor.setText("添加收藏");
                        Toast.makeText(CookbookdetailpageActivity.this, "取消收藏成功", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Integer a = cookbook.getCb_favorite();
                        a += 1;
                        addlikes(cookbook);
                        addlikes1(cookbook);
                        updatefavarite(cookbook, a);
                        favor.setText("取消收藏");
                        Toast.makeText(CookbookdetailpageActivity.this, "添加收藏成功", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.i("bmob", "失败：" + e.getMessage());
                }
            }
        });
    }
    @Override
    public void addcomment() {
        Intent intent=new Intent(CookbookdetailpageActivity.this,CommentpageActivity.class);
        intent.putExtra("cookbook_id",cookbook_id);
        startActivity(intent);
    }
    private void loadlikes(Cookbook cookbook) {
        final User user=BmobUser.getCurrentUser(User.class);
        BmobQuery<User> query = new BmobQuery<User>();
        Cookbook cookbook1 = new Cookbook();
        cookbook1.setObjectId(cookbook.getObjectId());
        query.addWhereRelatedTo("likes", new BmobPointer(cookbook1));
        query.findObjects(new FindListener<User>() {
            @Override
            public void done(List<User> object,BmobException e) {
                if(e==null){
                    int n = object.size();
                    String s=String.valueOf(n);
                    for (int i = 0; i < n; i++) {
                        User usertemp = object.get(i);
                        if(usertemp.getObjectId().equals(user.getObjectId()))
                            favor.setText("取消收藏");
                        else
                            favor.setText("添加收藏");
                    }
                    Log.i("bmob",s);
                }else{
                    Log.i("bmob","失败："+e.getMessage());
                }
            }

        });
    }
    private void addlikes(Cookbook cookbook) {
        User user=BmobUser.getCurrentUser(User.class);
        BmobRelation relation = new BmobRelation();
        relation.add(user);
        cookbook.setLikes(relation);
        cookbook.update(new UpdateListener() {

            @Override
            public void done(BmobException e) {
                if(e==null){
                    Log.i("bmob","用户B和该帖子关联成功");
                }else{
                    Log.i("bmob","失败："+e.getMessage());
                }
            }

        });
    }
    private void addlikes1(Cookbook cookbook) {
        User user=BmobUser.getCurrentUser(User.class);
        BmobRelation relation = new BmobRelation();
        relation.add(cookbook);
        user.setLikes(relation);
        user.update(new UpdateListener() {

            @Override
            public void done(BmobException e) {
                if(e==null){
                    Log.i("bmob","该帖子和用户关联成功");
                }else{
                    Log.i("bmob","失败："+e.getMessage());
                }
            }

        });
    }
    private void dellikes(Cookbook cookbook){
        User user=BmobUser.getCurrentUser(User.class);
        BmobRelation relation = new BmobRelation();
        relation.remove(user);
        cookbook.setLikes(relation);
        cookbook.update(new UpdateListener() {

            @Override
            public void done(BmobException e) {
                if(e==null){
                    Log.i("bmob","关联关系删除成功");
                }else{
                    Log.i("bmob","失败："+e.getMessage());
                }
            }

        });

    }
    private void dellikes1(Cookbook cookbook){
        User user=BmobUser.getCurrentUser(User.class);
        BmobRelation relation = new BmobRelation();
        relation.remove(cookbook);
        user.setLikes(relation);
        user.update(new UpdateListener() {

            @Override
            public void done(BmobException e) {
                if(e==null){
                    Log.i("bmob","关联关系111删除成功");
                }else{
                    Log.i("bmob","失败："+e.getMessage());
                }
            }

        });

    }
    private void updatefavarite(Cookbook cookbook,Integer favorite_num){
        cookbook.setCb_favorite(favorite_num);
        cookbook.update(cookbook.getObjectId(), new UpdateListener() {

            @Override
            public void done(BmobException e) {
                if(e==null){
                    System.out.println("done");
                }else{
                    System.out.println("false");
                }
            }

        });
    }
    private void adduserbrowse(Cookbook cookbook) {
        User user=BmobUser.getCurrentUser(User.class);
        BmobRelation relation = new BmobRelation();
        relation.add(cookbook);
        user.setBrowse(relation);
        user.update(new UpdateListener() {

            @Override
            public void done(BmobException e) {
                if(e==null){
                    Log.i("bmob","该帖子和用户关联成功");
                }else{
                    Log.i("bmob","失败："+e.getMessage());
                }
            }

        });
    }
}
