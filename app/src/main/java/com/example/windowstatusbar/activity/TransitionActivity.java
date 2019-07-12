package com.example.windowstatusbar.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.transition.Slide;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.windowstatusbar.R;

public class TransitionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);
        //setupWindowAnimations();


        Button btn = findViewById(R.id.button2);
        btn.setOnClickListener(v -> startActivity(new Intent(TransitionActivity.this, TransitionNextActivity.class)));

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        //请将LinearLayoutManager第三个参数改为true并且setStackFromEnd也为true
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        linearLayoutManager.setStackFromEnd(false);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new MyRecyViewAdapter());

    }

    class MyRecyViewAdapter extends RecyclerView.Adapter<MyRecyViewAdapter.ViewHolder> {

        @NonNull
        @Override
        public MyRecyViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(TransitionActivity.this).inflate(R.layout.rv_item, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull MyRecyViewAdapter.ViewHolder holder, int position) {
            Drawable[] drawables = new Drawable[]{
                    getResources().getDrawable(R.color.color_blue), getResources().getDrawable(R.color.color_green),
                    getResources().getDrawable(R.color.color_red), getResources().getDrawable(R.color.color_green),
//                    getResources().getDrawable(R.color.color_red_light), getResources().getDrawable(R.color.color_red_light),
//                    getResources().getDrawable(R.color.color_gray), getResources().getDrawable(R.color.color_green),
                    getResources().getDrawable(R.color.color_gray_light), getResources().getDrawable(R.color.color_red)};

            Integer[] resources = new Integer[]{
                    R.color.color_blue, R.color.color_green,
                    R.color.color_red, R.color.color_green,
                    R.color.color_gray_light, R.color.color_red};

            holder.iv.setImageResource(resources[position]);
            if (position != 0) {
                setMargins(holder.iv, -10, 0, 0, 0);
            }

            holder.iv.setOnClickListener(v -> {
                Intent intent = new Intent(TransitionActivity.this, TransitionNextActivity.class);
                Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(TransitionActivity.this, holder.iv, "shareImg").toBundle();
                intent.putExtra("position", position);
                startActivity(intent, bundle);
            });
        }


        void setMargins(View v, int l, int t, int r, int b) {
            if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
                ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
                p.setMargins(l, t, r, b);
                v.requestLayout();
            }
        }

        @Override
        public int getItemCount() {
            return 6;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            ImageView iv;

            ViewHolder(View itemView) {
                super(itemView);
                iv = itemView.findViewById(R.id.image_view);
            }
        }
    }

    private void setupWindowAnimations() {
        Fade fade = new Fade();
        fade.setDuration(1000);
        getWindow().setEnterTransition(fade);

        Slide slide = new Slide();
        slide.setDuration(1000);
        getWindow().setReturnTransition(slide);
    }
}
