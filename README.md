# StateView
```xml
    <!--    your custom layout file @layout/layout_content_main -->
    <r2.adam.sv.StateView
            android:id="@+id/stateView"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            app:contentResource="@layout/layout_content_main"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintLeft_toLeftOf="parent"
            app:layout_constraintRight_toRightOf="parent"
            app:layout_constraintTop_toTopOf="parent" />
```

```kotlin
    
    stateView.setState(State.CONTENT, ::setContentDefaultListeners)


    private fun setContentDefaultListeners(content: View) {
        content.findViewById<View>(R.id.okBtn).setOnClickListener {
            Toast.makeText(it.context, "Click", Toast.LENGTH_SHORT).show()
        }
    }
```


![Image](media/img1.png)
![Image](media/img2.png)
