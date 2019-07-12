package com.example.windowstatusbar.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.windowstatusbar.R;

import java.util.ArrayList;
import java.util.List;

public class MyDialog extends Dialog {

    private boolean iscancelable = true;//控制点击dialog外部是否dismiss
    private boolean isBackCanCelable = true;//控制返回键是否dismiss
    private ArrayList<String> mList = new ArrayList<>();
    private Context mContext;
    private RecyclerView rvDialog;
    private TextView tvDialog;

    private OnSelectorListener cdListener;
    private DialogMemberAdapter adapter;


    public MyDialog(Context context, ArrayList<String> list, OnSelectorListener cdListener) {
        super(context,R.style.BottomComDialog);
        this.mContext = context;
        this.mList = list;
        this.cdListener = cdListener;
    }

    public MyDialog(Context context, int themeResId) {
        super(context, R.style.BottomComDialog);
    }

    protected MyDialog(Context context, boolean cancelable, DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.my_dialog);//这行一定要写在前面
        setCancelable(iscancelable);
        setCanceledOnTouchOutside(true);//点击外部可dismiss

        getWindow().setGravity(Gravity.BOTTOM);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        initView();

    }

    private void initView() {

        rvDialog = findViewById(R.id.rvDialog);
        tvDialog = findViewById(R.id.tvDialog);

        tvDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cdListener.cancel();
            }
        });

        rvDialog.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new DialogMemberAdapter();
        rvDialog.setAdapter(adapter);

    }


    private class DialogMemberAdapter extends RecyclerView.Adapter<DialogMemberAdapter.ViewHolder> {

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView name;

            public ViewHolder(View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.tv_name);
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_dialog_item, null);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            String lock = mList.get(position);
            holder.name.setText(lock);
            holder.name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cdListener.selectPosition(position);
                }
            });

        }

        @Override
        public int getItemCount() {
            return mList.size();
        }


    }

    /**
     * 确定 和 取消控件的回调接口
     */
    public interface OnSelectorListener {
        void selectPosition(int position);

        void cancel();

    }

}
