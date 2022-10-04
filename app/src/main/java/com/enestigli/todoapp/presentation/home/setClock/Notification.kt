package com.enestigli.todoapp.presentation.home.setClock

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.enestigli.todoapp.R
import com.enestigli.todoapp.util.Constants

class Notification :BroadcastReceiver(){

    override fun onReceive(context: Context, intent: Intent) {

        val notification = NotificationCompat.Builder(context, Constants.CHANNEL_ID)
            .setContentTitle("")
            .setContentText("This is the content text")
            .setSmallIcon(R.drawable.ic_remove_btn2)
            .setPriority(NotificationCompat.PRIORITY_HIGH) //aşağıdaki priority ile alakası yok , aiağıdaki daha çok bildirim geldiğinde ses gelsinmi falan gibi bu daha çok bildirimlerin ne kadar önemli olduğu ile alakalı
            .build()

        //val notificationManager = NotificationManagerCompat.from(context)
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(Constants.NOTIFICATION_ID,notification)




    }
}