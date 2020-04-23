package cn.edu.sdwu.android02.classroom.sn170507180205;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MyService extends Service {
    private MediaPlayer mediaPlayer;

    public MyService() {
    }

    @Override

    public void onCreate() {
        super.onCreate();

        mediaPlayer = MediaPlayer.create(this.R.raw.wav);
        mediaPlayer.setLooping(true);
    }

    @Override

    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);

    }
        @Override
        public IBinder onBind (Intent intent){
            // TODO: Return the communication channel to the service.
            throw new UnsupportedOperationException("Not yet implemented");
        }
    }
