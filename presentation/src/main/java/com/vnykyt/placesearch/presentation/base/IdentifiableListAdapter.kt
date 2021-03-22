package com.vnykyt.placesearch.presentation.base

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxrelay3.PublishRelay
import com.jakewharton.rxrelay3.Relay
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.Consumer

abstract class IdentifiableListAdapter<I : Identifiable>(
    @LayoutRes val layoutRes: Int,
    diffCallback: DiffUtil.ItemCallback<I> = IdentifiableDiffCallback(),
    val createViewHolder: (view: View) -> BindableViewHolder<I>
) : ListAdapter<I, BindableViewHolder<I>>(diffCallback) {

    private var itemClickConsumer: Relay<I>? = null

    val itemClickObservable: Observable<I> get() = requireNotNull(itemClickConsumer).filter { isClickable }

    var isClickable: Boolean = true

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindableViewHolder<I> {
        val view = LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)
        return createViewHolder(view)
    }

    override fun onBindViewHolder(holder: BindableViewHolder<I>, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onBindViewHolder(holder: BindableViewHolder<I>, position: Int, payloads: MutableList<Any>) {
        holder.bindClick(itemClickConsumer, getItem(position))
        onBindViewHolder(holder, position)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        itemClickConsumer = PublishRelay.create()
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        itemClickConsumer = null
    }
}

interface Identifiable {
    val id: String
}

open class IdentifiableDiffCallback<I : Identifiable> : DiffUtil.ItemCallback<I>() {

    override fun areItemsTheSame(oldItem: I, newItem: I): Boolean =
        oldItem.javaClass == newItem.javaClass && oldItem.id == newItem.id

    override fun getChangePayload(oldItem: I, newItem: I): Any? = Any()

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: I, newItem: I): Boolean = oldItem == newItem
}

interface Bindable<I> {

    fun bind(item: I)

    fun bindClick(consumer: Consumer<I>?, item: I)
}

abstract class BindableViewHolder<I : Any>(itemView: View) : RecyclerView.ViewHolder(itemView), Bindable<I> {

    override fun bindClick(consumer: Consumer<I>?, item: I) {
        itemView.setOnClickListener { consumer?.accept(item) }
    }
}