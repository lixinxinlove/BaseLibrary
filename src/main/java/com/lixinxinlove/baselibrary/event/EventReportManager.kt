package com.lixinxinlove.baselibrary.event

import android.util.Log
import com.lixinxinlove.baselibrary.model.EventRequest
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.ReentrantLock

object EventReportManager {

    private const val TAG = "EventReportManager"

    private val executorService = Executors.newScheduledThreadPool(1)
    private val lock = ReentrantLock()
    private val eventList = mutableListOf<EventRequest>()


    private fun pushEventRequest(event: EventRequest) {
        try {
            lock.lock()
            eventList.add(event)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            lock.unlock()
        }
    }


    init {
        executorService.scheduleAtFixedRate({
            try {
                lock.lock()
                Log.e(TAG,"网络上传")
                if (eventList.size == 0) {
                    return@scheduleAtFixedRate
                }
                val list = mutableListOf<EventRequest>()
                while (eventList.size > 0) {
                    list.add(eventList.removeFirst())
                    if (list.size > 20) {
                        break
                    }
                }

                //TODO 网络上传


            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                lock.unlock()
            }

        }, 10, 10, TimeUnit.SECONDS)
    }


    fun reportEvent(event: String) {
        val mEventRequest = EventRequest(event)
        pushEventRequest(mEventRequest)
    }

}