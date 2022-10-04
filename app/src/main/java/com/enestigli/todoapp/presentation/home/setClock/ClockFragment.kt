package com.enestigli.todoapp.presentation.home.setClock

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.viewModels
import com.enestigli.todoapp.R
import com.enestigli.todoapp.databinding.FragmentClockBinding
import com.enestigli.todoapp.util.Constants
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class ClockFragment : Fragment(R.layout.fragment_clock) {


    private val viewModel:ClockViewModel by viewModels()
    private lateinit var fragmentClockBinding :FragmentClockBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentClockBinding.bind(view)
        fragmentClockBinding = binding


        createNotificationChannel()


        binding.setAlarmBtn.setOnClickListener{
            setClockAlarm()
        }

    }


    private fun getTime(): Long
    {
        val minute = fragmentClockBinding.timePicker.minute
        val day = fragmentClockBinding.datePicker.dayOfMonth
        val month = fragmentClockBinding.datePicker.month
        val hour = fragmentClockBinding.timePicker.hour
        val year = fragmentClockBinding.datePicker.year


        val calendar = Calendar.getInstance()
        calendar.set(year, month, day, hour, minute)
        return calendar.timeInMillis
    }

    private fun setClockAlarm() {


     val intent = Intent(requireContext().applicationContext, Notification::class.java)

        val pendingIntent = PendingIntent.getBroadcast(
            requireContext().applicationContext,
            Constants.NOTIFICATION_ID,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmManager = requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val time = getTime()
        alarmManager.setExact(AlarmManager.RTC_WAKEUP,time,pendingIntent)

        //showAlert(time,"Title","Desc")
        //showAlert(time, "title!!!'212", "message!'^1231")
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