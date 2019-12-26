package mufilbito.com.videoplayer.kotlinClasses.retrohit

import java.io.Serializable

object Model {

    data class Comments (val postId: String, val id: String, val name: String, val email: String, val body: String)

    data class Posts (val userId: String, val id: String, val title: String, val body: String)

}

//data class Comments (val postId: String, val id: String, val name: String, val email: String, val body: String) : Serializable