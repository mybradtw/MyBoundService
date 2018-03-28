package tw.brad.myboundservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private MyServiceConnection connection = new MyServiceConnection();
    private MyService myService;
    private UIHandler handler;
    private TextView mesg;

    private class MyServiceConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.v("brad", "onServiceConnected");
            MyService.LocalBinder binder = (MyService.LocalBinder)iBinder;
            myService = binder.getMyService();
            myService.setHandler(handler);
        }
        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.v("brad", "onServiceDisconnected");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new UIHandler();
        mesg = findViewById(R.id.mesg);

        Log.v("brad", "MainActivity:onCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v("brad", "MainActivity:onStart()");

        Intent intent = new Intent(this, MyService.class);
        bindService(intent, connection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v("brad", "MainActivity:onPause()");
        unbindService(connection);
    }

    public void test1(View view) {
        myService.sayHello("Brad");
    }
    public void test2(View view) {
        myService.doCallback();
    }

    class UIHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.v("brad", "MainActivity:UIHandler:handlerMessage");
            mesg.setText("rand = " + msg.arg1);
        }
    }

}
