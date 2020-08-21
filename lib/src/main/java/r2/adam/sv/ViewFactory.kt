package r2.adam.sv

import android.view.View

internal interface ViewFactory {
    fun makeView(state: State): View
}