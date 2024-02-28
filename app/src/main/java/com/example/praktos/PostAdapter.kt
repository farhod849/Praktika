package com.example.praktos
import android.view.LayoutInflater
<<<<<<< Updated upstream
<<<<<<< Updated upstream
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.praktos.databinding.PostcardBinding

class PostAdapter(private val listener: Listener):RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
=======
=======
>>>>>>> Stashed changes
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.praktos.databinding.PostcardBinding
typealias OnRemoveListener = (post:Post) -> Unit
class PostAdapter(private val listener: Listener):ListAdapter<Post, PostAdapter.PostViewHolder>(PostDiffCallback()) {
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
    var list = emptyList<Post>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    class PostViewHolder(
        private val binding: PostcardBinding
    ):RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post,listener: Listener) {
            binding.apply {
                textView3.text = post.author
                textViewmain.text = post.content
<<<<<<< Updated upstream
<<<<<<< Updated upstream

                textViewAmountLike.text = convertToString(post.amountLike)
                textView6.text = convertToString(post.amountShare)

=======
                textViewAmountLike.text = convertToString(post.amountLike)
                textView6.text = convertToString(post.amountShare)
                textView7.text = convertToString(post.viewAmount)
>>>>>>> Stashed changes
=======
                textViewAmountLike.text = convertToString(post.amountLike)
                textView6.text = convertToString(post.amountShare)
                textView7.text = convertToString(post.viewAmount)
>>>>>>> Stashed changes
                imageButton2.setBackgroundResource(if (post.likedByMe) R.drawable.like_press else R.drawable.like_unpress)
                imageButton2.setOnClickListener {
                    listener.onClickLike(post)
                }
                imageButton3.setOnClickListener {
                    listener.onClickShare(post)
                }
<<<<<<< Updated upstream
<<<<<<< Updated upstream
=======
=======
>>>>>>> Stashed changes
                morebtn.setOnClickListener {
                    PopupMenu(it.context, it).apply {
                        inflate(R.menu.popup_menu_more)
                        setOnMenuItemClickListener {item ->
                            when (item.itemId){
                                R.id.popup_delete -> {
                                    listener.onRemove(post)
                                    true
                                }
                                R.id.popup_edit -> {
                                    listener.onEdit(post)
                                    true
                                }
                                else -> false
                            }
                        }

                    }.show()
                }
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = PostcardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }
    override fun onBindViewHolder(holder: PostViewHolder, position:Int){
        val post = list[position]
        holder.bind(post, listener)
    }
<<<<<<< Updated upstream
<<<<<<< Updated upstream
    override fun getItemCount() :Int = list. size

    interface Listener{
        fun onClickLike(post: Post)
        fun onClickShare(post: Post)
    }
=======
=======
>>>>>>> Stashed changes
    interface Listener{
        fun onClickLike(post: Post)
        fun onClickShare(post: Post)
        fun onClickMore(post: Post,view: View)
        fun onEdit(post: Post)
        fun onLike(post: Post)
        fun onRemove(post: Post)
    }

<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
}
private fun convertToString(count:Int):String{
    return when(count){
        in 0..<1_000 -> count.toString()
        in 1000..<1_100-> "1K"
        in 1_100..<10_000 -> ((count/100).toFloat()/10).toString() + "K"
        in 10_000..<1_000_000 -> (count/1_000).toString() + "K"
        in 1_000_000..<1_100_000 -> "1M"
        in 1_100_000..<10_000_000 -> ((count/100_000).toFloat()/10).toString() + "M"
        in 10_000_000..<1_000_000_000 -> (count/1_000_000).toString() + "M"
        else -> "ꚙ"
    }
<<<<<<< Updated upstream
<<<<<<< Updated upstream
=======
=======
>>>>>>> Stashed changes
}
class PostDiffCallback: DiffUtil.ItemCallback<Post>(){
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }
    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
}