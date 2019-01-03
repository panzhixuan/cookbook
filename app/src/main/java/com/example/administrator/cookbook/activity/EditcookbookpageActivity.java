package com.example.administrator.cookbook.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.cookbook.R;
import com.example.administrator.cookbook.controller.CreatecookbookpageController;
import com.example.administrator.cookbook.controller.EditcookbookpageController;
import com.example.administrator.cookbook.model.Cookbook;
import com.example.administrator.cookbook.model.Cuisine;
import com.example.administrator.cookbook.model.Occasion;
import com.example.administrator.cookbook.model.Taste;
import com.example.administrator.cookbook.model.User;
import com.example.administrator.cookbook.view.GetImageByUrl;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;

public class EditcookbookpageActivity extends AppCompatActivity implements EditcookbookpageControllerListener{
    private LinearLayout materialList;
    private LinearLayout stepList;
    private final int CODE_FOR_STORAGE = 1;
    private File file=null;
    private String cookbook_id;
    private EditText cookbookname;
    private Spinner caishi;
    private Spinner caixi;
    private Spinner changhe;
    private ImageView cover;
    private EditText nutrition;
    private EditText tips;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editcookbookpage);
        EditcookbookpageController Controller = new EditcookbookpageController(this.findViewById(R.id.editcookbookpage), this);
        setListeners(Controller);
        Spinner caishispinner = (Spinner) findViewById(R.id.caishi);
        String[] caishitype = {"清淡", "辣","酸"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, caishitype);
        caishispinner.setAdapter(adapter);
        Spinner caixispinner = (Spinner) findViewById(R.id.caixi);
        String[] caixitype = {"川菜", "淮扬菜","徽菜","鲁菜","闽菜","粤菜","湘菜","浙菜"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, caixitype);
        caixispinner.setAdapter(adapter1);
        Spinner changhespinner = (Spinner) findViewById(R.id.changhe);
        String[] changhetype = {"家", "饭店","快餐店"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, changhetype);
        changhespinner.setAdapter(adapter2);
        materialList = (LinearLayout) findViewById(R.id.material_list);
        stepList = (LinearLayout) findViewById(R.id.step_list);
        Intent intent = getIntent();
        cookbook_id = intent.getStringExtra("cookbook_id");
        init();
        initData();
    }
    public void setListeners(View.OnClickListener onClickListener){
        findViewById(R.id.material_add).setOnClickListener(onClickListener);
        findViewById(R.id.add_step).setOnClickListener(onClickListener);
        findViewById(R.id.delete_step).setOnClickListener(onClickListener);
        findViewById(R.id.cover).setOnClickListener(onClickListener);
        findViewById(R.id.create_cookbook).setOnClickListener(onClickListener);
    }
    @Override
    public void editcover(){
        initPermission();
    }

    @Override
    public void create(){
        uploadCookbook();
    }

    private void init(){
        cookbookname=findViewById(R.id.cookbookname);
        caishi=findViewById(R.id.caishi);
        caixi=findViewById(R.id.caixi);
        changhe=findViewById(R.id.changhe);
        cover=findViewById(R.id.cover);
        nutrition=findViewById(R.id.nutrition);
        tips=findViewById(R.id.tips);
        materialList=findViewById(R.id.material_list);
        stepList=findViewById(R.id.step_list);
    }
    private void initData(){
        BmobQuery<Cookbook> bmobQuery = new BmobQuery<Cookbook>();
        bmobQuery.include("user,cuisine,taste,occasion");
//        bmobQuery.include("cuisine");
//        bmobQuery.include("taste");
//        bmobQuery.include("occasion");
        bmobQuery.getObject(cookbook_id, new QueryListener<Cookbook>() {
            @Override
            public void done(Cookbook cookbook,BmobException e) {
                if(e==null){
                    cookbookname.setText(cookbook.getCb_name());
                    int pos1=0;
                    String[] caishitype = {"清淡", "辣","酸"};
                    for(int i=0;i<caishitype.length;i++){
                        if(caishitype[i].equals(cookbook.getTaste().getName())){
                            pos1=i;
                            Log.i("login3",cookbook.getTaste().getName());
                            break;
                        }
                    }
                    caishi.setSelection(pos1,true);
                    int pos2=0;
                    String[] caixitype = {"川菜", "淮扬菜","徽菜","鲁菜","闽菜","粤菜","湘菜","浙菜"};
                    for(int i=0;i<caixitype.length;i++){
                        if(caixitype[i].equals(cookbook.getCuisine().getName())){
                            pos2=i;
                            break;
                        }
                    }
                    caixi.setSelection(pos2,true);
                    int pos=0;
                    String[] changhetype = {"家", "饭店","快餐店"};
                    for(int i=0;i<changhetype.length;i++){
                        if(changhetype[i].equals(cookbook.getOccasion().getName())){
                            pos=i;
                            Log.i("login3",cookbook.getOccasion().getName());
                            break;
                        }
                    }
                    changhe.setSelection(pos,true);
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
                        final View temp = View.inflate(EditcookbookpageActivity.this, R.layout.material_item, null);
                        EditText materialName=(EditText)temp.findViewById(R.id.material_name);
                        materialName.setText(name);
                        EditText materialNumber=(EditText)temp.findViewById(R.id.material_quantity);
                        materialNumber.setText(num);
                        Button delete = (Button) temp.findViewById(R.id.material_delete);
                        delete.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (materialList.getChildCount() > 1) {
                                    materialList.removeView(temp);
                                }
                            }
                        });
                        materialList.addView(temp, -1);
                    }
                    for (int i = 0; i <steps.size(); i++) {
                        JsonObject jsonObject=steps.get(i).getAsJsonObject();
                        String step=jsonObject.get(""+i).getAsString();
                        final View temp = View.inflate(EditcookbookpageActivity.this, R.layout.add_cookbook_step, null);
                        TextView stepDesc = (TextView) temp.findViewById(R.id.cookbook_step_desc);
                        int j=i+1;
                        stepDesc.setText(step);
                        stepList.addView(temp, -1);
                    }
                }else{
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void addMItem() {
        final View temp = View.inflate(this, R.layout.material_item, null);
        materialList.addView(temp, -1);
        Button delete = (Button) temp.findViewById(R.id.material_delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (materialList.getChildCount() > 1) {
                    materialList.removeView(temp);
                }
            }
        });
    }
    @Override
    public void addSItem() {
        final View temp = View.inflate(this, R.layout.add_cookbook_step, null);
        stepList.addView(temp, -1);
        TextView stepNum = (TextView) temp.findViewById(R.id.cookbook_step_number);
        stepNum.setText("" + stepList.getChildCount() + ":");
    }
    @Override
    public void delSItem() {
        final View temp = stepList.getChildAt(stepList.getChildCount() - 1);
        if (stepList.getChildCount() > 1) {
            stepList.removeView(temp);
        }
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
                Toast.makeText(EditcookbookpageActivity.this, "请设置相应权限", Toast.LENGTH_SHORT).show();
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
                cover.setImageBitmap(bitmap);
                file = new File(getFilesDir(), "cookbookImage.jpg");
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
    private void uploadCookbook() {

        final Cookbook cookbook = new Cookbook();

        final BmobQuery<User> query = new BmobQuery<User>();
        query.addWhereEqualTo("objectId", BmobUser.getCurrentUser().getObjectId());
        query.findObjects(new FindListener<User>() {
            @Override
            public void done(List<User> object, BmobException e) {
                if (e == null) {
                    cookbook.setUser(object.get(0));
                    EditText cookbook_name = (EditText) findViewById(R.id.cookbookname);
                    if (cookbook_name.getText().toString().equals("")) {
                        final AlertDialog.Builder normalDialog =
                                new AlertDialog.Builder(EditcookbookpageActivity.this);
                        normalDialog.setTitle("存在未填项");
                        normalDialog.setMessage("您未填写菜品名称");
                        normalDialog.setPositiveButton("确定",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                        normalDialog.show();
                        return;
                    }
                    cookbook.setCb_name(cookbook_name.getText().toString());

                    Spinner caishi = (Spinner)findViewById(R.id.caishi);
                    BmobQuery<Taste> TasteQuery = new BmobQuery<>();
                    TasteQuery.addWhereEqualTo("name", caishi.getSelectedItem().toString());
                    TasteQuery.findObjects(new FindListener<Taste>() {
                        @Override
                        public void done(List<Taste> object, BmobException e) {
                            if (e == null) {
                                cookbook.setTaste(object.get(0));
                                cookbook.update(cookbook_id,new UpdateListener() {
                                    @Override
                                    public void done(BmobException e) {
                                        if (e == null) {
                                        } else {
                                        }
                                    }
                                });
                                Log.i("login3",object.get(0).getName());
                            } else {
                            }
                        }
                    });
                    Spinner caixi = (Spinner)findViewById(R.id.caixi);
                    BmobQuery<Cuisine> CuisineQuery = new BmobQuery<>();
                    CuisineQuery.addWhereEqualTo("name", caixi.getSelectedItem().toString());
                    CuisineQuery.findObjects(new FindListener<Cuisine>() {
                        @Override
                        public void done(List<Cuisine> object, BmobException e) {
                            if (e == null) {
                                cookbook.setCuisine(object.get(0));
                                cookbook.update(cookbook_id,new UpdateListener() {
                                    @Override
                                    public void done(BmobException e) {
                                        if (e == null) {
                                        } else {
                                        }
                                    }
                                });
                                Log.i("login3",object.get(0).getName());
                            } else {
                            }
                        }
                    });
                    Spinner changhe = (Spinner)findViewById(R.id.changhe);
                    BmobQuery<Occasion> OccasionQuery = new BmobQuery<>();
                    OccasionQuery.addWhereEqualTo("name", changhe.getSelectedItem().toString());
                    OccasionQuery.findObjects(new FindListener<Occasion>() {
                        @Override
                        public void done(List<Occasion> object, BmobException e) {
                            if (e == null) {
                                cookbook.setOccasion(object.get(0));
                                cookbook.update(cookbook_id,new UpdateListener() {
                                    @Override
                                    public void done(BmobException e) {
                                        if (e == null) {
                                        } else {
                                        }
                                    }
                                });
                                Log.i("login3",object.get(0).getName());
                            } else {
                            }
                        }
                    });


                    EditText nutrition = (EditText) findViewById(R.id.nutrition);
                    if (nutrition.getText().toString().equals("")) {
                        final AlertDialog.Builder normalDialog =
                                new AlertDialog.Builder(EditcookbookpageActivity.this);
                        normalDialog.setTitle("存在未填项");
                        normalDialog.setMessage("您未填写营养必读");
                        normalDialog.setPositiveButton("确定",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                        normalDialog.show();
                        return;
                    }
                    cookbook.setCb_nutrition(nutrition.getText().toString());

                    EditText tips= (EditText) findViewById(R.id.tips);
                    if (tips.getText().toString().equals("")) {
                        final AlertDialog.Builder normalDialog =
                                new AlertDialog.Builder(EditcookbookpageActivity.this);
                        normalDialog.setTitle("存在未填项");
                        normalDialog.setMessage("您未填写小贴士");
                        normalDialog.setPositiveButton("确定",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                        normalDialog.show();
                        return;
                    }
                    cookbook.setCb_tips(tips.getText().toString());


                    final JsonArray materials = new JsonArray();
                    final JsonArray steps = new JsonArray();

                    for (int i = 0; i < materialList.getChildCount(); i++) {
                        View temp = materialList.getChildAt(i);

                        EditText mName = (EditText) temp.findViewById(R.id.material_name);
                        if (mName.getText().toString().equals("")) {
                            final AlertDialog.Builder normalDialog =
                                    new AlertDialog.Builder(EditcookbookpageActivity.this);
                            normalDialog.setTitle("存在未填项");
                            normalDialog.setMessage("您未填写第" + (i + 1) + "项材料名称");
                            normalDialog.setPositiveButton("确定",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                        }
                                    });
                            normalDialog.show();
                            return;
                        }

                        EditText mNum = (EditText) temp.findViewById(R.id.material_quantity);
                        if (mNum.getText().toString().equals("")) {
                            final AlertDialog.Builder normalDialog =
                                    new AlertDialog.Builder(EditcookbookpageActivity.this);
                            normalDialog.setTitle("存在未填项");
                            normalDialog.setMessage("您未填写第" + (i + 1) + "项材料用量");
                            normalDialog.setPositiveButton("确定",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                        }
                                    });
                            normalDialog.show();
                            return;
                        }

                        JsonObject jsonObject = new JsonObject();
                        //jsonObject.addProperty(mName.getText().toString(), mNum.getText().toString());
                        jsonObject.addProperty("name",mName.getText().toString().trim());
                        jsonObject.addProperty("num",mNum.getText().toString().trim());
                        materials.add(jsonObject);
                    }
                    for (int i = 0; i < stepList.getChildCount(); i++) {
                        View temp = stepList.getChildAt(i);
                        EditText stepDesc = (EditText) temp.findViewById(R.id.cookbook_step_desc);
                        if (stepDesc.getText().toString().equals("")) {
                            final AlertDialog.Builder normalDialog =
                                    new AlertDialog.Builder(EditcookbookpageActivity.this);
                            normalDialog.setTitle("存在未填项");
                            normalDialog.setMessage("您未填写第" + (i + 1) + "步的具体内容");
                            normalDialog.setPositiveButton("确定",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                        }
                                    });
                            normalDialog.show();
                            return;
                        }
                        JsonObject jsonObject = new JsonObject();
                        jsonObject.addProperty("" + i, stepDesc.getText().toString());
                        steps.add(jsonObject);
                    }

                    cookbook.setCb_ingredint(materials.toString());

                    cookbook.setCb_content(steps.toString());


                    final BmobFile bmobFile = new BmobFile(file);
                    bmobFile.uploadblock(new UploadFileListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e == null) {
                                cookbook.setCb_image(bmobFile);
                                cookbook.update(cookbook_id,new UpdateListener() {
                                    @Override
                                    public void done(BmobException e) {
                                        if (e == null) {
                                            Toast.makeText(EditcookbookpageActivity.this, "Success", Toast.LENGTH_SHORT).show();
                                            finish();
                                        } else {
                                            Toast.makeText(EditcookbookpageActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            } else {
                                cookbook.update(cookbook_id,new UpdateListener() {
                                    @Override
                                    public void done(BmobException e) {
                                        if (e == null) {
                                            Toast.makeText(EditcookbookpageActivity.this, "Success", Toast.LENGTH_SHORT).show();
                                            finish();
                                        } else {
                                            Toast.makeText(EditcookbookpageActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        }
                    });
                } else {
                    Toast.makeText(EditcookbookpageActivity.this, "Failed to get User", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
