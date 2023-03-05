package net.deali.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import net.deali.coredomain.entity.BaseEntity
import net.deali.coredomain.entity.BottomErrorEntity
import net.deali.coredomain.entity.LoadingEntity

abstract class LazyColumnViewModel : BaseViewModel() {
    protected val _items = MutableLiveData<List<BaseEntity>>(listOf())
    val items: LiveData<List<BaseEntity>> = _items

    var pageCount: Int = 1
    var isAllLoaded: Boolean = false

    abstract fun onLoadMore()

    protected fun hasLoadingEntity() = _items.value?.any {
        it is LoadingEntity
    } ?: false

    protected fun hasBottomErrorEntity() = _items.value?.any {
        it is BottomErrorEntity
    } ?: false

    protected fun getPureItems() = _items.value?.filterNot {
        it is LoadingEntity || it is BottomErrorEntity
    } ?: listOf()

    open fun onRefresh() {
        if (hasLoadingEntity()) return
        _items.value = listOf()
        pageCount = 1
        isAllLoaded = false
        onLoadMore()
    }

    fun onBottomRefresh() {
        if (hasLoadingEntity()) return
        _items.value = getPureItems()
        onLoadMore()
    }

}