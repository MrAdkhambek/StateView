package r2.adam.sv

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ViewAnimator
import android.widget.ViewSwitcher
import r2.adam.sv.State.*


class StateView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ViewAnimator(context, attrs), ViewFactory {

    private var layoutEmptyRes: Int = R.layout.layout_empty
    private var layoutErrorRes: Int = R.layout.layout_error
    private var layoutContent: Int = R.layout.layout_content
    private var layoutLoadingRes: Int = R.layout.layout_loading

    private var state: State = CONTENT
    fun setState(state: State, body: (View) -> Unit) {
        if (this.state == state) return
        body(obtainView(state))
        this.state = state
    }

    init {
        val attributes: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.StateView, 0, 0)

        try {
            layoutEmptyRes = attributes.getResourceId(R.styleable.StateView_emptyResource, R.layout.layout_empty)
            layoutErrorRes = attributes.getResourceId(R.styleable.StateView_errorResource, R.layout.layout_error)
            layoutContent = attributes.getResourceId(R.styleable.StateView_contentResource, R.layout.layout_content)
            layoutLoadingRes = attributes.getResourceId(R.styleable.StateView_loadingResource, R.layout.layout_loading)

        } catch (e: Exception) {
            Log.e("StateView", e.message ?: e.localizedMessage ?: "Error")
        } finally {
            attributes.recycle()
        }

        setState(LOADING) {}
        showNext()
        setInAnimation(context, android.R.anim.fade_in)
        setOutAnimation(context, android.R.anim.fade_out)
    }

    override fun addView(child: View, params: ViewGroup.LayoutParams) {
        super.addView(child, params)
        showNext()
        if (childCount > 1) removeViewAt(0)
    }

    override fun getAccessibilityClassName(): CharSequence? = ViewSwitcher::class.java.name

    private fun obtainView(state: State): View {
        val child = makeView(state)
        var lp = child.layoutParams as? LayoutParams?
        if (lp == null) {
            lp = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        }
        addView(child, lp)
        return child
    }

    override fun makeView(state: State): View {
        val inflater: LayoutInflater = LayoutInflater.from(this.context)
        return inflater.inflate(
            when (state) {
                LOADING -> layoutLoadingRes
                CONTENT -> layoutContent
                EMPTY -> layoutEmptyRes
                ERROR -> layoutErrorRes
            }, this, false
        )
    }
}

