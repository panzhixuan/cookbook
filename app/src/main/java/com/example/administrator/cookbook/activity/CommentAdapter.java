package com.example.administrator.cookbook.activity;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.cookbook.R;
import com.example.administrator.cookbook.model.Comment;
import com.example.administrator.cookbook.model.Cookbook;
import com.example.administrator.cookbook.model.Cuisine;
import com.example.administrator.cookbook.model.User;
import com.example.administrator.cookbook.view.GetImageByUrl;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class CommentAdapter extends ArrayAdapter implements TypelistControllerListener{
    private final int resourceId;
    private Context context;
    private View view;

    public CommentAdapter(Context context, int textViewResourceId, List<Comment> objects) {
        super(context, textViewResourceId,objects);
        this.context=context;
        resourceId = textViewResourceId;
    }
    @Override
    public View getView(int position, final View convertViw, final ViewGroup parent) {
        final Comment comment = (Comment) getItem(position);
        view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        ImageView header=(ImageView)view.findViewById(R.id.cookbook_head_image);
        TextView username=(TextView)view.findViewById(R.id.cookbook_username);
        TextView commentcontent=(TextView)view.findViewById(R.id.cookbook_comment);
        loaduser(comment.getAuthor().getObjectId());
//        GetImageByUrl getImageByUrl = new GetImageByUrl();
//        getImageByUrl.setImage(header,comment.getAuthor().getUser_image().getFileUrl());
//        username.setText(comment.getAuthor().getUser_nickname());
        commentcontent.setText(comment.getCm_content());
        return view;
    }
    private void loaduser(String id) {
        BmobQuery<User> Query = new BmobQuery<>();
        Query.addWhereEqualTo("objectId", id);
        Query.findObjects(new FindListener<User>() {
            @Override
            public void done(List<User> object, BmobException e) {
                if (e == null) {
                    ImageView header=(ImageView)view.findViewById(R.id.cookbook_head_image);
                    TextView username=(TextView)view.findViewById(R.id.cookbook_username);
                    GetImageByUrl getImageByUrl = new GetImageByUrl();
                    getImageByUrl.setImage(header,object.get(0).getUser_image().getFileUrl());
                    username.setText(object.get(0).getUser_nickname());
                } else {
                    Log.i("BMOB", e.toString());
                }
            }
        });
    }
}
