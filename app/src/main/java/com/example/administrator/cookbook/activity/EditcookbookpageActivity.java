package com.example.administrator.cookbook.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.administrator.cookbook.R;
import com.example.administrator.cookbook.controller.CreatecookbookpageController;
import com.example.administrator.cookbook.controller.EditcookbookpageController;

public class EditcookbookpageActivity extends AppCompatActivity implements EditcookbookpageControllerListener{
    private LinearLayout materialList;
    private LinearLayout stepList;
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
        String[] changhetype = {"家", "饭店","快餐厅"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, changhetype);
        changhespinner.setAdapter(adapter2);
        materialList = (LinearLayout) findViewById(R.id.material_list);
        addMItem();
        stepList = (LinearLayout) findViewById(R.id.step_list);
        addSItem();
    }
    public void setListeners(View.OnClickListener onClickListener){
        findViewById(R.id.material_add).setOnClickListener(onClickListener);
        findViewById(R.id.add_step).setOnClickListener(onClickListener);
        findViewById(R.id.delete_step).setOnClickListener(onClickListener);
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
}
