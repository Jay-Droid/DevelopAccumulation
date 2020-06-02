package com.jay.develop.java.mult_thread

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.jay.develop.R

class ThreadActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_thread)
        //获取虚拟机中运行所有线程
        getAllThread()
        //获取设备的核心数量
        getAvailableProcessors()

    }

    /**
     *返回虚拟机可用的最大处理器数
     */
    private fun getAvailableProcessors() {
        val processors = Runtime.getRuntime().availableProcessors()

        Log.d("ThreadActivity", "processors=$processors") //8

    }

    /**
     * 获取虚拟机中运行所有线程
     */
    private fun getAllThread() {
        //开启一个线程
        Thread(Runnable { Log.d("ThreadActivity", "jay_thread running") }, "jay_thread").start()

        /**
         * getAllStackTraces
         * 返回虚拟机中所有活的线程的堆栈跟踪信息的Map集合。键是线程，值是一个StackTraceElement数组表示堆栈转储所对应的Thread。
         * 调用此方法时，线程可能正在执行。每个线程的堆栈跟踪仅代表一个快照，每个堆栈跟踪可能在不同的时间获得。
         */
        val threadMap = Thread.getAllStackTraces()
        for (entry in threadMap) {
            val threadInfo = entry.key
            Log.d("ThreadActivity", "[" + threadInfo.id + "]" + " " + threadInfo.name)
        }

        /**
         * D: jay_thread running
        D: [1335] Thread-2
        D: [1354] OkHttp ConnectionPool
        D: [1389] ACCS0
        D: [1373] pool-1-thread-1
        D: [1394] Thread-30
        D: [1413] ACCS-SEND1
        D: [1418] AsyncTask #1
        D: [1370] NetWorkSender
        D: [1330] HeapTaskDaemon
        D: [1391] Binder:interceptor
        D: [1396] Thread-31
        D: [1327] ReferenceQueueDaemon
        D: [1377] queued-work-looper
        D: [1333] Binder:8785_3
        D: [1341] Thread-8
        D: [1427] jay_thread
        D: [1382] LMREQUEST
        D: [1337] Thread-4
        D: [1417] Binder:8785_5
        D: [1395] AWCN Scheduler1
        D: [1329] FinalizerWatchdogDaemon
        D: [1423] UMThreadPoolExecutor6
        D: [1416] SL-NetWorkSender
        D: [1336] T-log
        D: [1324] Jit thread pool worker thread 0
        D: [1332] Binder:8785_2
        D: [1381] Okio Watchdog
        D: [2] T-connStch
        D: [1331] Binder:8785_1
        D: [1411] pool-4-thread-1
        D: [1419] UMThreadPoolExecutor3
        D: [1359] Thread-23
        D: [1378] UMThreadPoolExecutor2
        D: [1355] Thread-19
        D: [1367] SpStatus daemon
        D: [1349] Thread-15
        D: [1420] UMThreadPoolExecutor4
        D: [1409] spdy-0
        D: [1421] Thread-41
        D: [1414] Binder:8785_4
        D: [1356] UMThreadPoolExecutor1
        D: [1403] RenderThread
        D: [1325] Signal Catcher
        D: [1366] Thread-26
        D: [1371] FileObserver
        D: [1334] Profile Saver
        D: [1398] AMDC1
        D: [1384] sensor_thread
        D: [1422] UMThreadPoolExecutor5
        D: [1407] process reaper
        D: [1369] ConnectivityThread
        D: [1328] FinalizerDaemon
        D: [1358] Thread-22
        D: [1397] Thread-32
        D: [1402] AMDC2
        D: [1364] DBUpdater
        D: [1343] Thread-10
        D: [1346] Thread-12
        D: [1365] work_thread
        D: processors=8
        D: jay_thread running
        D: [1335] Thread-2
        D: [1354] OkHttp ConnectionPool
        D: [1389] ACCS0
        D: [1373] pool-1-thread-1
        D: [1394] Thread-30
        D: [1413] ACCS-SEND1
        D: [1418] AsyncTask #1
        D: [1370] NetWorkSender
        D: [1330] HeapTaskDaemon
        D: [1391] Binder:interceptor
        D: [1396] Thread-31
        D: [1327] ReferenceQueueDaemon
        D: [1377] queued-work-looper
        D: [1333] Binder:8785_3
        D: [1341] Thread-8
        D: [1382] LMREQUEST
        D: [1337] Thread-4
        D: [1417] Binder:8785_5
        D: [1395] AWCN Scheduler1
        D: [1329] FinalizerWatchdogDaemon
        D: [1423] UMThreadPoolExecutor6
        D: [1416] SL-NetWorkSender
        D: [1336] T-log
        D: [1324] Jit thread pool worker thread 0
        D: [1332] Binder:8785_2
        D: [1381] Okio Watchdog
        D: [2] T-connStch
        D: [1331] Binder:8785_1
        D: [1411] pool-4-thread-1
        D: [1419] UMThreadPoolExecutor3
        D: [1359] Thread-23
        D: [1378] UMThreadPoolExecutor2
        D: [1355] Thread-19
        D: [1367] SpStatus daemon
        D: [1349] Thread-15
        D: [1420] UMThreadPoolExecutor4
        D: [1409] spdy-0
        D: [1421] Thread-41
        D: [1414] Binder:8785_4
        D: [1356] UMThreadPoolExecutor1
        D: [1403] RenderThread
        D: [1325] Signal Catcher
        D: [1366] Thread-26
        D: [1371] FileObserver
        D: [1334] Profile Saver
        D: [1429] jay_thread
        D: [1398] AMDC1
        D: [1384] sensor_thread
        D: [1422] UMThreadPoolExecutor5
        D: [1407] process reaper
        D: [1369] ConnectivityThread
        D: [1328] FinalizerDaemon
        D: [1358] Thread-22
        D: [1397] Thread-32
        D: [1402] AMDC2
        D: [1364] DBUpdater
        D: [1343] Thread-10
        D: [1346] Thread-12
        D: [1365] work_thread
        D: processors=8
         */
    }


}
