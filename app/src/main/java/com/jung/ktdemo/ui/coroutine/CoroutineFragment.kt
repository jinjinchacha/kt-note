package com.jung.ktdemo.ui.coroutine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.jung.ktdemo.databinding.CoroutineFragmentBinding
import com.jung.ktdemo.ui.base.BaseFragment
import kotlinx.coroutines.*
import java.lang.Exception

class CoroutineFragment : BaseFragment<CoroutineFragmentBinding>() {

    private lateinit var timerJob: Job
    private var time = 10

    override fun bindView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): CoroutineFragmentBinding = CoroutineFragmentBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         *  suspend function 會等待上一個 function 執行完畢後，再往下執行
         *  逐行執行
         **/
        lifecycleScope.launch {
            f1()
            f2()
            f3()
        }

        /**
         *  用 async 異步方式執行，r4 與 r5 同時併發取值，回傳一個 Deferred<T>
         *  用 await 收割 r4 與 r5 的值
         *  async 預設使用 CoroutineStart.DEFAULT，所以會在程式執行到這行的時候就開始請求取值，
         *  如果想要等到使用 await 才開始取值的話可以使用 CoroutineStart.LAZY
         **/
        lifecycleScope.launch {
            val r4 = async { f4() }
//            val r4 = async(start = CoroutineStart.LAZY) { f4() }
            val r5 = async { f5() }
            println("r4 + r5 = ${r4.await() + r5.await()}")
        }

        /**
         *  launch 回傳一個 Job
         *  用 job.isActive 判斷該 job 是否還在執行
         *  用 job.join 等待該 job 執行完畢後再繼續執行後面的程式
         *
         *  以下範例：
         *  1.因 countDownJob.join() 所以 println("timer done") 會在 countDownJob 執行完才打印
         *  2.每次點擊時都會用 timerJob.isActive 確認 timerJob 是否還在執行
         **/
        binding.click.setOnClickListener {
            if (::timerJob.isInitialized.not()) {
                timerJob = lifecycleScope.launch {
                    println("start timer")
                    val countDownJob = launch {
                        repeat(time) {
                            binding.num.text = time.toString()
                            time -= 1
                            delay(1000)
                        }
                    }
                    countDownJob.join()
                    println("timer done")
                }
            }
            println("is timerJob active = ${timerJob.isActive}")
        }

        /**
         *  用 CoroutineExceptionHandler 捕捉異常
         *  以及 coroutineScope 和 supervisorScope 的作用域效果
         *
         *  宣告一個 CoroutineExceptionHandler 放進 launch 或 async 內來捕捉異常
         *  可知 CoroutineExceptionHandler 也是一種 CoroutineContext
         *
         *  coroutineScope : 子協程只要有一個異常，則整個作用域將停止執行
         *  supervisorScope : 子協程異常，不影響其他子協程運作
         *
         *  分析
         *  case1:
         *  coroutineScope 內之 fException() 因回傳異常，導致 f4() 無法繼續執行
         *  連同外面的 f1() 也被迫中斷
         *
         *  case2:
         *  因使用 supervisorScope 即使 fException() 回傳異常， f5() 仍然繼續執行
         *
         *  case3:
         *  supervisorScope 內有兩個子協程
         *  因為使用 supervisorScope 即便 fException() 回傳異常，第二個子協程(launch)仍可繼續執行
         *  值得注意的是 第二個子協程(launch) 並未指定是哪一種 scope ，故啟動時為預設 coroutineScope
         *  所以在第二個子協程(launch)內的 innerException 回傳異常後，該協程便中斷，無法繼續執行 f4()
         *
         **/
        val exceptionHandler = CoroutineExceptionHandler { _, e ->
            println("Coroutine Exception Handler: ${e.message}")
        }
        // case1
        lifecycleScope.launch(exceptionHandler) {
            launch { f1() }
            coroutineScope {
                val rException = async(exceptionHandler) { fException() }
                val r4 = async { f4() }
            }
        }
        // case2
        lifecycleScope.launch(exceptionHandler) {
            supervisorScope {
                val rException = async(exceptionHandler) { fException() }
                val r5 = async { f5() }
            }
        }
        // case3
        lifecycleScope.launch(exceptionHandler) {
            supervisorScope {
                val rException = async(exceptionHandler) { fException() }
                launch {
                    val innerException = async(exceptionHandler) { fException() }
                    val r4 = async { f4() }
                }
            }
        }

        /** yield 待續... **/
    }

    private suspend fun f1() {
        delay(5000)
        println("f1: ${Thread.currentThread()}")
    }

    private suspend fun f2() {
        delay(3000)
        println("f2: ${Thread.currentThread()}")
    }

    private fun f3() {
        println("f3: ${Thread.currentThread()}")
    }

    private suspend fun f4(): Int {
        delay(3000)
        println("f4: ${Thread.currentThread()}")
        return 4
    }

    private suspend fun f5(): Int {
        delay(3000)
        println("f5: ${Thread.currentThread()}")
        return 5
    }

    private suspend fun fException(): Int {
        delay(3000)
        println("fException: ${Thread.currentThread()}")
        throw Exception("fException exception")
    }
}