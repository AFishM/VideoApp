package com.xu.videoapp;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView=findViewById(R.id.video_list_view);
        recyclerView.setLayoutManager(new ViewPagerLayoutManager(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(new MyAdapter());
    }
    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
        private int[] imgs = {R.mipmap.img_video_1,R.mipmap.img_video_2};
        private int[] videos = {R.raw.video_1,R.raw.video_2};
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.play_item,parent,false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.thumbView.setImageResource(imgs[position%2]);
            holder.videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+videos[position%2]));
        }

        @Override
        public void onViewAttachedToWindow(MyViewHolder holder) {
            super.onViewAttachedToWindow(holder);
            Log.d("xzx","onViewAttachedToWindow");
            holder.videoView.start();
            holder.thumbView.animate().alpha(0.f).start();
            Log.d("xzx","videoView.start");
        }

        @Override
        public void onViewDetachedFromWindow(MyViewHolder holder) {
            super.onViewDetachedFromWindow(holder);
            Log.d("xzx","onViewDetachedFromWindow");
            holder.videoView.stopPlayback();
            holder.thumbView.animate().alpha(1.f).start();
            Log.d("xzx","videoView.stopPlayback");
        }

        @Override
        public int getItemCount() {
            return 20;
        }

        class MyViewHolder extends RecyclerView.ViewHolder{
            ImageView thumbView;
            VideoView videoView;
            MyViewHolder(View itemView) {
                super(itemView);
                thumbView=itemView.findViewById(R.id.thumb_view);
                videoView=itemView.findViewById(R.id.video_view);
            }
        }
    }
}
