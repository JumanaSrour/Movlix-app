package com.sundus.abjw.moge.utils


import android.widget.AbsListView.OnScrollListener.SCROLL_STATE_IDLE
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class PaginationLinearScrollListener : RecyclerView.OnScrollListener {
    lateinit var layoutManager: LinearLayoutManager
    private var userScrolled = false

    constructor(layoutManager: LinearLayoutManager) {
        this.layoutManager = layoutManager
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        userScrolled = newState != SCROLL_STATE_IDLE
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView!!, dx, dy)
        onScrolledList(dx, dy)
        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

        if (!_isLoading()/* && userScrolled */ && !_isLastPage()) {
            if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                loadMoreItems()
            }
        }
        /*int topRowVerticalPosition = (recyclerView == null || recyclerView.getChildCount() == 0) ?
               0 : recyclerView.getChildAt(0).getTop();
       LinearLayoutManager linearLayoutManager1 = (LinearLayoutManager) recyclerView.getLayoutManager();
       int firstVisibleItem = linearLayoutManager1.findFirstVisibleItemPosition();
       setEnabled(firstVisibleItem == 0 && topRowVerticalPosition >= 0);*/

        var topRowVerticalPosition = recyclerView!!.getChildAt(0).top
        if (recyclerView == null || recyclerView.childCount === 0) {
            topRowVerticalPosition = 0
        }
        val linearLayoutManager1 = recyclerView!!.layoutManager as LinearLayoutManager
        val firstVisibleItem = linearLayoutManager1.findFirstVisibleItemPosition()
        setEnabled(firstVisibleItem == 0 && topRowVerticalPosition >= 0)
    }

    abstract fun onScrolledList(dx: Int, dy: Int)
    protected abstract fun loadMoreItems()
    protected abstract fun setEnabled(refresh: Boolean)
    abstract fun _isLastPage(): Boolean
    abstract fun _isLoading(): Boolean
}

//data class Two<A, B>(val a: A, val b: B)
