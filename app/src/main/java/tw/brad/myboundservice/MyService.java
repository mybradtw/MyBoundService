package tw.brad.myboundservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

public class MyService extends Service {
    private final  LocalBinder binder = new LocalBinder();
    private MainActivity.UIHandler handler;

    public class LocalBinder extends Binder {
        MyService getMyService(){
            Log.v("brad", "getMyService");
            return MyService.this;
        }
    }

    public MyService() {
        Log.v("brad", "MyService");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.v("brad", "onBind");

        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v("brad", "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v("brad", "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v("brad", "onDestroy");
    }

    void sayHello(String name){
        Log.v("brad", "MyService:sayHello:" + name);
    }

    void doCallback(){
        int rand = (int)(Math.random()*49+1);
        Message message = new Message();
        message.arg1 = rand;
        handler.sendMessage(message);
    }

    void setHandler(MainActivity.UIHandler handler){
        this.handler = handler;
    }

}
