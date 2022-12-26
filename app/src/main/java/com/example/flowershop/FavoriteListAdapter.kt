import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flowershop.Product
import com.example.flowershop.R
import com.bumptech.glide.request.RequestOptions
import com.example.flowershop.FirestoreAdapter
import com.example.flowershop.ui.dashboard.DashboardFragment
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query

//class FavoriteListAdapter(query: Query) : FirestoreAdapter<FavoriteListAdapter.FavoriteViewHolder>(query) {
//
//    class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//
//        private val productImage = itemView.findViewById<ImageView>(R.id.imageFavoriteText)
//        private val productName = itemView.findViewById<TextView>(R.id.nameFavoriteText)
//        private val productPrice = itemView.findViewById<TextView>(R.id.priceFavoriteText)
//        private val favoriteButton = itemView.findViewById<Button>(R.id.favoriteButtonFavoriteAdd)
//        private val addButton = itemView.findViewById<Button>(R.id.favoriteButtonFavoriteAdd)
//
//        fun bind(snapshot: DocumentSnapshot) {
//            val product: Product? = snapshot.toObject(Product::class.java)
//            productName.setText(product?.name)
//            productPrice.setText(product?.price.toString())
//
//            val requestOptions = RequestOptions()
//                .placeholder(R.drawable.ic_launcher_background)
//                .error(R.drawable.ic_launcher_background)
//            Glide.with(itemView.context)
//                .applyDefaultRequestOptions(requestOptions)
//                .load(product?.image).into(productImage)
//
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
//        return FavoriteViewHolder(LayoutInflater.from(parent.context).inflate(
//            R.layout.favorite_row, parent, false)
//        )
//    }
//
//    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
//        getSnapshot(position)?.let { snapshot -> holder.bind(snapshot) }
//    }
//}


class FavoriteListAdapter(val listener: DashboardFragment) : RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
    var items: List<Product> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ShopListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.favorite_row, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ShopListViewHolder ->{
                holder.bind(items.get(position), listener)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(favoriteList: List<Product>){
        items = favoriteList
    }

    interface Listener{
        fun onClick(product: Product)
    }

    class ShopListViewHolder constructor(
        itemView: View
    ): RecyclerView.ViewHolder(itemView){
        private val productImage = itemView.findViewById<ImageView>(R.id.imageFavoriteText)
        private val productName = itemView.findViewById<TextView>(R.id.nameFavoriteText)
        private val productPrice = itemView.findViewById<TextView>(R.id.priceFavoriteText)
        private val favoriteButton = itemView.findViewById<Button>(R.id.favoriteButtonFavoriteAdd)
        private val addButton = itemView.findViewById<Button>(R.id.favoriteButtonFavoriteAdd)


        fun bind(product: Product, listener: DashboardFragment){
            productName.setText(product.name)
            productPrice.setText(product.price.toString())

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(product.image).into(productImage)

//            favoriteButton.setOnClickListener{
//                listener.deleteFromFavorite(product)
//            }
//
//            addButton.setOnClickListener{
//                listener.addToBusket(product)
//            }

        }
    }
}