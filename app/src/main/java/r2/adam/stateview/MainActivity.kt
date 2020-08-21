package r2.adam.stateview

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import r2.adam.sv.State


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        stateView.setState(State.CONTENT, ::setContentDefaultListeners)
        contentBtn.setOnClickListener {
            stateView.setState(State.CONTENT, ::setContentDefaultListeners)
        }

        loadingBtn.setOnClickListener {
            stateView.setState(State.LOADING) {}
        }

        errorBtn.setOnClickListener {
            stateView.setState(State.ERROR) {}
        }

        emptyBtn.setOnClickListener {
            stateView.setState(State.EMPTY) {}
        }
    }

    private fun setContentDefaultListeners(content: View) {
        content.findViewById<View>(R.id.okBtn).setOnClickListener {
            Toast.makeText(it.context, "Click", Toast.LENGTH_SHORT).show()
        }
    }
}