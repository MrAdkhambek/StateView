package r2.adam.sv

import android.view.View

interface ViewFactory {
    fun makeView(state: State): View
}