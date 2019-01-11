package rece;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * <pre>
 *     author : dell
 *     time   : 2018/05/23
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class BrRe extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context,"接收到的消息是："+intent.getStringExtra("data"),Toast.LENGTH_SHORT).show();
    }
}
