package cn.edu.sdwu.android02.classroom.sn170507180205;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;


public class MediaService extends Service {
        private MediaPlayer mediaPlayer;
        private MyBinder myBinder;
        public MediaService() {
        }

        @Override
        public void onCreate() {
            super.onCreate();
            mediaPlayer=MediaPlayer.create(this,R.raw.wav);
            mediaPlayer.setLooping(false);
            myBinder=new MyBinder();
            Log.i(MediaService.class.toString(),"onCreate");
        }

        @Override
        public int onStartCommand(Intent intent,int flags, int startId) {
            Log.i(MediaService.class.toString(),"onStartCommand");
            //从intent中获取用户新要求的操作，然后进一步处理
            String state=intent.getStringExtra("PlayerState");
            if (state!=null){
                if (state.equals("SRART")){
                    //播放
                    start();
                }
                if (state.equals("PAUSE")){
                    //暂停
                    pause();
                }
                if (state.equals("STOP")){
                    //停止播放
                    stop();
                }
                if (state.equals("STOPSERVICE")){
                    //停止服务
                    stopSelf();
                }
            }
            return super.onStartCommand(intent, flags, startId);
        }
        public void start(){
            mediaPlayer.start();
        }
        public void pause(){
            if (mediaPlayer.isPlaying()){
                mediaPlayer.pause();
            }
        }
        public void stop(){
            mediaPlayer.stop();
            //为了下一次的播放，需要调用prepare方法
            try{
                mediaPlayer.prepare();
            }catch(Exception e){
                Log.e(MediaService.class.toString(),e.toString());
            }

        }

        @Override
        public void onDestroy() {
            Log.i(MediaService.class.toString(),"onDestory");
            mediaPlayer.stop();
            mediaPlayer.release();
            super.onDestroy();
        }
    public class MyBinler extends Binder {
public MediaService getMediaService (){
    return MediaService.this;
}
}

    @Override
        public IBinder onBind(Intent intent) {
          return myBinder;
        }

    public class MyBinder extends Binder{
    public MediaService getMediaService() {
        return MediaService.this;
    }
    }
    }