package com.yuvasai.baseproject.utils

import androidx.databinding.Observable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.igweze.ebi.simplecalladapter.Subscription
import java.util.ArrayList

fun Subscription.addTo(disposable: ArrayList<Subscription>) {
    disposable.add(this)

}

fun <Request> ObservableField<Request>.observe(execute: (Request?) -> Unit) {
    val observable = this
    observable.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
            execute(observable.get())
        }
    })
}

fun ObservableBoolean.observe(execute: (Boolean?) -> Unit) {
    val observable = this
    observable.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
            execute(observable.get())
        }
    })
}