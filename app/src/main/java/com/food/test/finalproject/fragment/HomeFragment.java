package com.food.test.finalproject.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.food.test.finalproject.R;
import com.food.test.finalproject.adapter.QuickAdapter;
import com.food.test.finalproject.model.Comment;
import com.food.test.finalproject.model.PostModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {

    @BindView(R.id.rvPosts)
    RecyclerView rvPosts;
    @BindView(R.id.swipeContainer)
    SwipeRefreshLayout swipeContainer;
    static List<PostModel> posts;
    static List<PostModel> newPosts;

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static QuickAdapter<PostModel> quickAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup parent, Bundle savedInsanceState){
        // define the xml file for the fragment
        initData();
        return inflater.inflate(R.layout.fragment_home, parent, false);
    }

    public void initData(){
        posts = new ArrayList<>();
        newPosts = new ArrayList<>();

        for(int i=0;i<5;i++){
            PostModel post=new PostModel();
            post.setHandle("haha");
            post.setCreatedAt(df.format(new Date()));
            post.setDescription("哈哈哈，傻了吧");
            post.setLike(0);
            posts.add(post);
        }
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        // bind views using butterknife
        ButterKnife.bind(this, view);

        quickAdapter=new QuickAdapter<PostModel>(posts) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.item_post;
            }

            @Override
            public void convert(final VH holder, final PostModel data, int position) {
                holder.setText(R.id.tvTimeStamp, data.getCreatedAt());
                holder.setText(R.id.tvHandle, data.getHandle());
                holder.setText(R.id.tvCaption,data.getDescription() );
                if(data.getImage()==null)
                    holder.setImage(R.id.ivImage, R.drawable.food);
                else
//                    holder.setImage(R.id.ivImage, data.getImage());
                    holder.setImage(R.id.ivProfileImage, R.drawable.head);

                final RecyclerView commentlist=holder.itemView.findViewById(R.id.commentList);

                final List<Comment> comments=new ArrayList<>();
                for(int i=0;i<3;i++)
                    comments.add(new Comment("haha:","我来说句话"));

                final QuickAdapter<Comment> commentAdapter;
                commentAdapter=new QuickAdapter<Comment>(comments) {
                    @Override
                    public int getLayoutId(int viewType) {
                        return R.layout.item_comment_main;
                    }
                    @Override
                    public void convert(VH holder, Comment data, int position) {
                        holder.setText(R.id.username, data.getUsername());
                        holder.setText(R.id.comment_content, data.getCommentcontent());
                    }

                };
                commentlist.setAdapter(commentAdapter);
                commentlist.setLayoutManager(new LinearLayoutManager(getContext()));

                holder.itemView.findViewById(R.id.comment).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "你点了评论",Toast.LENGTH_LONG ).show();
                    }
                });

                holder.itemView.findViewById(R.id.heart).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        v=(ImageView)v;
                        if(data.getLike()==0){
                            ((ImageView) v).setImageDrawable(getResources().getDrawable(R.drawable.ufi_heart_active));
                            data.setLike(1);
                        }
                        else{
                            ((ImageView) v).setImageDrawable(getResources().getDrawable(R.drawable.ufi_heart));
                            data.setLike(0);
                        }
                    }
                });


            }
        };

        // recyclerview setup
        rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));
        // set the adapter
        rvPosts.setAdapter(quickAdapter);

//        getPosts();

        // swipe refresh
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPosts();
            }
        });

        // configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

    }

    // method that uses query to populate the timeline with posts
    public void getPosts(){
        newPosts.clear();

        PostModel post=new PostModel();
        post.setHandle("add");
        post.setCreatedAt(df.format(new Date()));
        post.setDescription("哈哈哈，傻了吧");
        posts.add(post);
        quickAdapter.updateData(posts);
        quickAdapter.notifyDataSetChanged();
        swipeContainer.setRefreshing(false);
    }

    public void addPost(PostModel postModel){
        posts.add(postModel);
        quickAdapter.updateData(posts);
        quickAdapter.notifyDataSetChanged();
        swipeContainer.setRefreshing(false);
    }

}
