package com.example.praktos
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.praktos.databinding.PostcardBinding
typealias OnRemoveListener = (post:Post) -> Unit
class PostAdapter(private val listener: Listener):ListAdapter<Post, PostAdapter.PostViewHolder>(PostDiffCallback()) {
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
                textViewAmountLike.text = convertToString(post.amountLike)
                textView6.text = convertToString(post.amountShare)
                textView7.text = convertToString(post.viewAmount)
                textViewLink.text = post.link
                imageButton2.setBackgroundResource(if (post.likedByMe) R.drawable.like_press else R.drawable.like_unpress)
                imageButton2.setOnClickListener {
                    listener.onClickLike(post)
                }
                imageButton3.setOnClickListener {
                    listener.onClickShare(post)
                }
                morebtn.setOnClickListener {
                    PopupMenu(it.context, it).apply {
                        inflate(R.menu.popup_menu_more)
                        setOnMenuItemClickListener {item ->
                            when (item.itemId){
                                R.id.popup_delete -> {
                                    Toast.makeText(binding.root.context, "Пост удалён", Toast.LENGTH_SHORT).show()
                                    listener.onRemove(post)
                                    true
                                }
                                R.id.popup_edit -> {
                                    Toast.makeText(binding.root.context, "Редактикрование текста", Toast.LENGTH_SHORT).show()
                                    listener.onEdit(post)
                                    true
                                }
                                R.id.popup_add -> {
                                    Toast.makeText(binding.root.context, "Добавление текста", Toast.LENGTH_SHORT).show()
                                    listener.add()
                                    true
                                }
                                else -> false
                            }
                        }
                    }.show()
                }
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
    interface Listener{
        fun onClickLike(post: Post)
        fun onClickShare(post: Post)
        fun onClickMore(post: Post,view: View)
        fun onEdit(post: Post)
        fun onLike(post: Post)
        fun onRemove(post: Post)
        fun add()
    }

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
}
class PostDiffCallback: DiffUtil.ItemCallback<Post>(){
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }
    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }
}
