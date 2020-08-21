package r2.adam.stateview

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import r2.adam.sv.State

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        contentBtn.setOnClickListener {
            stateView.setState(State.CONTENT) { view ->
                view.findViewById<View>(R.id.okBtn).setOnClickListener {
                    Toast.makeText(it.context, "Click", Toast.LENGTH_SHORT).show()
                }
            }

            Log.d("TTT", stateView.childCount.toString())
        }

        loadingBtn.setOnClickListener {
            stateView.setState(State.LOADING) {

            }
            Log.d("TTT", stateView.childCount.toString())
        }

        errorBtn.setOnClickListener {
            stateView.setState(State.ERROR) {

            }
            Log.d("TTT", stateView.childCount.toString())
        }

        emptyBtn.setOnClickListener {
            stateView.setState(State.EMPTY) {

            }
            Log.d("TTT", stateView.childCount.toString())
        }

    }
}