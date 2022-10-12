package com.enestigli.todoapp.presentation.home.setClock

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.enestigli.todoapp.R
import com.enestigli.todoapp.base.BaseBindingFragment
import com.enestigli.todoapp.databinding.FragmentClockBinding
import com.enestigli.todoapp.util.Constants
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class ClockFragment : BaseBindingFragment<FragmentClockBinding,ClockViewModel>() {


    override val viewModel:ClockViewModel by viewModels()
    override val layoutId: Int = R.layout.fragment_clock

    //private lateinit var fragmentClockBinding :FragmentClockBinding

    override fun onReady(savedInstanceState: Bundle?) {
        super.onReady(savedInstanceState)

        createNotificationChannel()


        binding.setAlarmBtn.setOnClickListener{
            setClockAlarm()
        }
    }


    private fun getTime(): Long
    {
        val minute =    binding.timePicker.minute
        val day    =    binding.datePicker.dayOfMonth
        val month  =    binding.datePicker.month
        val hour   =    binding.timePicker.hour
        val year   =    binding.datePicker.year


        val calendar = Calendar.getInstance()
        calendar.set(year, month, day, hour, minute)
        return calendar.timeInMillis
    }

    private fun setClockAlarm() {


        val intent = Intent(requireContext().applicationContext, Notification::class.java)
        val title = binding.notificationTitle.text.toString()
        val message = binding.notificationNote.text.toString()
        intent.putExtra("titleExtra",title)
        intent.putExtra("messageExtra",message)


        if(title.isEmpty() || message.isEmpty()){
            Toast.makeText(requireActivity(),"Enter title , message please.",Toast.LENGTH_LONG).show()
            return
        }

        val pendingIntent = PendingIntent.getBroadcast(
            requireContext().applicationContext,
            Constants.NOTIFICATION_ID,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmManager = requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val time = getTime()
        alarmManager.setExact(AlarmManager.RTC_WAKEUP,time,pendingIntent)

        showAlert(time,title,message)

    }

    private fun showAlert(time: Long, title: String, message: String)
    {
        val date = Date(time)
        val dateFormat = android.text.format.DateFormat.getLongDateFormat(requireContext().applicationContext)
        val timeFormat = android.text.format.DateFormat.getTimeFormat(requireContext().applicationContext)

        AlertDialog.Builder(requireContext())
            .setTitle("Notification Scheduled")
            .setMessage(
                "Title: " + title +
                        "\nMessage: " + message +
                        "\nAt: " + dateFormat.format(date) + " " + timeFormat.format(date))
            .setPositiveButton("Okay"){_,_ ->}
            .show()
    }


    private fun createNotificationChannel() {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            //android oreo'dan buyuk mu
            val channel = NotificationChannel(Constants.CHANNEL_ID,
                Constants.CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH).apply {
                    lightColor = Color.GREEN
                    enableLights(true)
            }
            val manager = requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }

    }


}