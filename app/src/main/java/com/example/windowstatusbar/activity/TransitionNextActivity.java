package com.example.windowstatusbar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;

import com.example.windowstatusbar.R;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.umeng.socialize.utils.ContextUtil.getContext;

/**
 * recycleview item 进场动画
 */
public class TransitionNextActivity extends AppCompatActivity {

    private int mPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_next);

        //setupWindowAnimations();

        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener((v) -> finish());


        Intent intent = getIntent();
        if (intent != null) {
            mPos = intent.getIntExtra("position", 0);
        }
        CircleImageView circleImageView = findViewById(R.id.cirImageView);
        Integer[] resources = new Integer[]{
                R.color.color_blue, R.color.color_green,
                R.color.color_red, R.color.color_green,
                R.color.color_gray_light, R.color.color_red};

        circleImageView.setImageResource(resources[mPos]);
        circleImageView.setOnClickListener((v) -> onBackPressed());


        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        //请将LinearLayoutManager第三个参数改为true并且setStackFromEnd也为true
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setStackFromEnd(false);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new TransitionNextActivity.MyRecyViewAdapter());

        //通过加载XML动画设置文件来创建一个Animation对象；
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.left);
        //得到一个LayoutAnimationController对象；
        LayoutAnimationController lac = new LayoutAnimationController(animation);
        //设置控件显示的顺序；
        lac.setOrder(LayoutAnimationController.ORDER_NORMAL);
        //设置控件显示间隔时间；
        lac.setDelay(0.2f);
        //为ListView设置LayoutAnimationController属性；
        recyclerView.setLayoutAnimation(lac);

    }

    class MyRecyViewAdapter extends RecyclerView.Adapter<MyRecyViewAdapter.ViewHolder> {

        @NonNull
        @Override
        public MyRecyViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MyRecyViewAdapter.ViewHolder(LayoutInflater.from(TransitionNextActivity.this).inflate(R.layout.transition_next_rv_item, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull MyRecyViewAdapter.ViewHolder holder, int position) {

            Integer[] resources = new Integer[]{
                    R.color.color_blue, R.color.color_green,
                    R.color.color_red, R.color.color_green,
                    R.color.color_gray_light, R.color.color_red};

            holder.iv.setImageResource(resources[position]);
            holder.iv.setOnClickListener(v -> onBackPressed());
        }


        @Override
        public int getItemCount() {
            return 6;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            CircleImageView iv;

            ViewHolder(View itemView) {
                super(itemView);
                iv = itemView.findViewById(R.id.img);
            }
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        TransitionNextActivity.this.finish();
    }

    private void setupWindowAnimations() {
//        Fade fade = (Fade) TransitionInflater.from(this).inflateTransition(R.transition.activity_fade);
//        getWindow().setEnterTransition(fade);

        Fade fade = new Fade();
        fade.setDuration(1000);
        getWindow().setEnterTransition(fade);
    }
}
