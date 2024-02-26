package com.example.praktos
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.praktos.databinding.PostcardBinding

class PostAdapter(private val listener: Listener):RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
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

                imageButton2.setBackgroundResource(if (post.likedByMe) R.drawable.like_press else R.drawable.like_unpress)
                imageButton2.setOnClickListener {
                    listener.onClickLike(post)
                }
                imageButton3.setOnClickListener {
                    listener.onClickShare(post)
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
    override fun getItemCount() :Int = list. size

    interface Listener{
        fun onClickLike(post: Post)
        fun onClickShare(post: Post)
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