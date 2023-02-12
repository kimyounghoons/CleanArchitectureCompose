package net.deali.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    protected val _event = MutableLiveData<Event>()
    val event: LiveData<Event> = _event

    open class Event

    protected fun sendEvent(event: Event) {
        _event.value = event
    }
}