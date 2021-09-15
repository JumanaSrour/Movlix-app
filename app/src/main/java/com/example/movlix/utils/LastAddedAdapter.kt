//package com.sundus.abjw.moge.feature.lastAdd.adapter
//
//import android.app.Activity
//
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageButton
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.example.movlix.R
//
//import java.util.*
//
//
//class LastAddedAdapter(private val mActivity: Activity) :
//    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//    private val list: ArrayList<PostsItem>?
//    private var listener: OnItemClickListener? = null
//    private var isLoadingAdded = false
//    private var index = 0
//
//    init {
//        list = ArrayList()
//    }
//
//    override fun getItemViewType(position: Int): Int {
//        return if (list!![position].type == TYPE_LAST_ITEM) TYPE_LAST_ITEM else TYPE_ITEM
//    }
//
//    fun addLoadingFooter() {
//        if (!isLoadingAdded) {
//            isLoadingAdded = true
//            add()
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        val viewHolder: RecyclerView.ViewHolder
//        val view: View
//        when (viewType) {
//            TYPE_ITEM -> {
//                view = LayoutInflater.from(parent.context)
//                    .inflate(R.layout.adapter_last_add, parent, false)
//                viewHolder = ProductViewHolder(view)
//            }
//            TYPE_LAST_ITEM -> {
//                view =
//                    LayoutInflater.from(parent.context).inflate(R.layout.load_more, parent, false)
//                viewHolder = LoadMoreViewHolder(view)
//            }
//            else -> {
//                view = LayoutInflater.from(parent.context)
//                    .inflate(R.layout.adapter_last_add, parent, false)
//                viewHolder = ProductViewHolder(view)
//            }
//        }
//        return viewHolder
//    }
//
//    override fun onBindViewHolder(
//        viewHolder: RecyclerView.ViewHolder,
//        position: Int
//    ) {
//        when (getItemViewType(position)) {
//            TYPE_ITEM -> {
//                val productViewHolder = viewHolder as ProductViewHolder
//                setData(productViewHolder, getItem(position), position)
//            }
//        }
//    }
//
//    private fun setData(holder: ProductViewHolder, data: PostsItem?, position: Int) {
//        if (data != null) {
//            if (data.mediaType != null) {
//                when (data.mediaType) {
//                    "video" -> {
//                        holder.videoView!!.visibility = View.VISIBLE
//                        holder.playBack!!.visibility = View.VISIBLE
//                        holder.image!!.visibility = View.GONE
//                        holder.iv_music!!.visibility = View.GONE
//                        holder.tvDescription!!.visibility = View.VISIBLE
//                        holder.cardViewContainer!!.visibility = View.VISIBLE
//                    }
//                    "voice" -> {
//                        holder.videoView!!.visibility = View.VISIBLE
//                        holder.playBack!!.visibility = View.GONE
//                        holder.image!!.visibility = View.GONE
//                        holder.iv_music!!.visibility = View.VISIBLE
//                        holder.tvDescription!!.visibility = View.VISIBLE
//                        holder.cardViewContainer!!.visibility = View.VISIBLE
//                    }
//                    "photo" -> {
//                        holder.videoView!!.visibility = View.GONE
//                        holder.playBack!!.visibility = View.GONE
//                        holder.iv_music!!.visibility = View.GONE
//                        holder.image!!.visibility = View.VISIBLE
//                        holder.tvDescription!!.visibility = View.VISIBLE
//                        holder.cardViewContainer!!.visibility = View.VISIBLE
//
//                        Glide.with(mActivity)
//                            .load(data.mediaLink)
//                            .into(holder.image!!)
//                    }
//                    else -> {
//                        holder.videoView!!.visibility = View.GONE
//                        holder.playBack!!.visibility = View.GONE
//                        holder.image!!.visibility = View.GONE
//                        holder.iv_music!!.visibility = View.GONE
//                        holder.tvDescription!!.visibility = View.VISIBLE
//                        holder.cardViewContainer!!.visibility = View.GONE
//                    }
//                }
//            } else {
//                holder.videoView!!.visibility = View.GONE
//                holder.playBack!!.visibility = View.GONE
//                holder.image!!.visibility = View.GONE
//                holder.tvDescription!!.visibility = View.VISIBLE
//                holder.cardViewContainer!!.visibility = View.GONE
//
//            }
//            holder.tvDescription?.let { it.text = data?.text }
//            holder.tvNumLikes?.let { it.text = data?.likesTotal.toString() }
//            holder.tvComments?.let { it.text = data?.commentsCount.toString() }
//            holder.tvNear?.let { it.text = checkDistance(data?.lat, data?.lng) }
//
////            when (data.liked) {
////                1 -> {
////                    holder.ivLike!!.isChecked = true
////                }
////                else -> {
////                    holder.ivLike!!.isChecked = false
////                }
////            }
////
//            holder.ivLike?.let {
//                it.setOnClickListener {
//                    if (listener != null) listener?.setItemFavListener(data!!, position, 1)
//                }
//            }
//            holder.ivDisLike?.let {
//                it.setOnClickListener {
//                    if (listener != null) listener?.setItemFavListener(data!!, position, 0)
//                }
//            }
//
//            holder.itemView.let {
//                it.setOnClickListener {
//                    if (listener != null) listener?.setItemClickListener(data!!, position)
//                }
//            }
//
//            try {
//                val milliseconds = ToolUtils().getLocalDateTimeLong(data?.createdAt!!)
//                holder.tvDate?.let { it.text = ToolUtils().convertTimeStamp(milliseconds) }
//            } catch (e: Exception) {
//                holder.tvDate?.let { it.text = data?.createdAt!! }
//            }
//            if (position == 0) {
//                holder.cardView!!.setCardBackgroundColor(mActivity.resources.getColor(R.color.dark_blue))
//            } else {
//                when (index) {
//                    0 -> holder.cardView!!.setCardBackgroundColor(mActivity.resources.getColor(R.color.light_blue))
//                    1 -> holder.cardView!!.setCardBackgroundColor(mActivity.resources.getColor(R.color.orange_color))
//                    2 -> holder.cardView!!.setCardBackgroundColor(mActivity.resources.getColor(R.color.red))
//                }
//                index++
//                if (index >= 3) {
//                    index = 0
//                }
//            }
//            holder.playBack!!.setOnClickListener {
//                Log.e("**mediaType", data.mediaType)
//                if (data.mediaType!! == "video") {
//                    Log.e("**inside if", data.mediaType)
//                    mActivity.startActivity(
//                        VideoActivity.newInstance(
//                            mActivity,
//                            data.mediaLink!!,
//                            data.mediaType!!
//                        )
//                    )
//                } else {
//                    Log.e("**inside else", data.mediaType)
//                    mActivity.startActivity(
//                        ZoomInActivity.newInstance(
//                            mActivity,
//                            data.mediaLink!!,
//                            data.mediaType!!
//                        )
//                    )
//                }
//            }
//            holder.image!!.setOnClickListener {
//                mActivity.startActivity(
//                    ZoomInActivity.newInstance(
//                        mActivity,
//                        data.mediaLink!!,
//                        data.mediaType!!
//                    )
//                )
//            }
//            holder.iv_music!!.setOnClickListener {
//                mActivity.startActivity(
//                    ZoomInActivity.newInstance(
//                        mActivity,
//                        data.mediaLink!!,
//                        data.mediaType!!
//                    )
//                )
//            }
//            holder.tvInfo!!.setOnClickListener {
//                if (listener != null) {
//                    listener?.onDialogGenderChoice(data, position)
//                }
//            }
//        }
//    }
//
//
//    override fun getItemCount(): Int {
//        return list?.size ?: 0
//    }
//
//    fun getItem(pos: Int): PostsItem? {
//        return list!![pos]
//    }
//
//    fun setItem(pos: Int, item: PostsItem) {
//        list!![pos] = item
//        notifyDataSetChanged()
//    }
//
//    fun add(location: Int, item: PostsItem) {
//        list!!.add(location, item)
//        notifyItemInserted(location)
//    }
//
//    fun add() {
//        val postsItem = PostsItem()
//        postsItem.type = 2
//        list!!.add(postsItem)
//        notifyDataSetChanged()
//    }
//
//    fun add(mc: PostsItem) {
//        list!!.add(mc)
//        notifyItemInserted(list.size - 1)
//    }
//
//    fun removeLoadingFooter() {
//        isLoadingAdded = false
//
//        val position = list!!.size - 1
//        val item = getItem(position)
//
//        if (item != null) {
//            list.removeAt(position)
//            notifyItemRemoved(position)
//        }
//    }
//
//    fun remove(location: Int) {
//        if (location >= list!!.size)
//            return
//
//        if (getItem(location)?.type == TYPE_LAST_ITEM) {
//            list.removeAt(location)
//            notifyItemRemoved(location)
//        }
//    }
//
//    fun remove() {
//        for (i in list!!.indices) {
//            if (list[i].type == TYPE_LAST_ITEM)
//                list.remove(list[i])
//            notifyDataSetChanged()
//        }
//    }
//
//    fun removeAll() {
//        this.list!!.clear()
//        notifyDataSetChanged()
//
//    }
//
//    fun setData(products: ArrayList<PostsItem>) {
//        this.list!!.clear()
//        this.list.addAll(products)
//        notifyDataSetChanged()
//    }
//
//    fun addItem(productList: ArrayList<PostsItem>) {
//        for (mc in productList) {
//            if (!ToolUtils().containsLastAddId(list!!, mc.id!!))
//                add(mc)
//            Log.e("mc-->", mc.toString())
//        }
//        notifyDataSetChanged()
//    }
//
//    fun setOnItemClickListener(listener: OnItemClickListener) {
//        this.listener = listener
//    }
//
//
//    inner class ProductViewHolder internal constructor(itemView: View) :
//        RecyclerView.ViewHolder(itemView) {
//        var tvNumLikes: TextView? = null
//        var tvComments: TextView? = null
//        var cardView: CardView? = null
//        var tvDate: TextView? = null
//        var tvDescription: TextView? = null
//        var tvNear: TextView? = null
//        var tvInfo: TextView? = null
//        var ivLike: ImageButton? = null
//        var ivDisLike: ImageButton? = null
//        var playBack: ImageView? = null
//        var image: ImageView? = null
//        var iv_music: ImageView? = null
//        var videoView: View? = null
//        //        var videoView: VideoView? = null
//        var cardViewContainer: CardView? = null
//
//        init {
//            this.cardViewContainer = itemView.findViewById(R.id.cardViewContainer)
//            this.iv_music = itemView.findViewById(R.id.iv_music)
//            this.image = itemView.findViewById(R.id.image)
//            this.playBack = itemView.findViewById(R.id.playBack)
//            this.videoView = itemView.findViewById(R.id.videoView)
//            this.tvComments = itemView.findViewById(R.id.tvComments)
//            this.cardView = itemView.findViewById(R.id.cardView)
//            this.tvNumLikes = itemView.findViewById(R.id.tvNumLikes)
//            this.tvInfo = itemView.findViewById(R.id.tv_info)
//            this.tvDate = itemView.findViewById(R.id.tvDate)
//            this.tvDescription = itemView.findViewById(R.id.tvDescription)
//            this.tvNear = itemView.findViewById(R.id.tvNear)
//            this.ivLike = itemView.findViewById(R.id.ivLike)
//            this.ivDisLike = itemView.findViewById(R.id.ivDisLike)
//        }
//    }
//
//
//    inner class LoadMoreViewHolder internal constructor(itemView: View) :
//        RecyclerView.ViewHolder(itemView) {
//        var loadMore: AVLoadingIndicatorView? = null
//
//        init {
//            this.loadMore = itemView.findViewById(R.id.loadMore)
//
//        }
//    }
//
//    interface OnItemClickListener {
//        fun setItemClickListener(item: PostsItem, position: Int)
//        fun setItemFavListener(
//            item: PostsItem,
//            position: Int,
//            checked: Int
//        )
//
//        fun onDialogGenderChoice(
//            item: PostsItem,
//            position: Int
//        )
//    }
//
//    companion object {
//        val TYPE_ITEM = 0
//        val TYPE_LAST_ITEM = 1
//    }
//}
//
//
