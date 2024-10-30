package id.ac.polbeng.susisalina.workmanagerexample
import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.delay

class LoopWorker(
    context: Context,
    workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters) {
    private val TAG: String = LoopWorker::class.java.name
    companion object {
        const val PROGRESS = "progress"
        const val COUNTER = "counter"
        const val MESSAGE = "message"
    }
    override suspend fun doWork(): Result {
        val counter = inputData.getInt(COUNTER, 0)
        Log.d(TAG, "Start counter...")
        val progressStep = 100f / counter
        for (i in 1..counter) {
            val progress = kotlin.math.ceil(i * progressStep).toInt()
            setProgress(workDataOf(PROGRESS to progress))
            delay(1000)
        }
        Log.d(TAG, "Finish counter!")
        val outputData = workDataOf(MESSAGE to "Finish counter!")
        return Result.success(outputData)
    }
}
