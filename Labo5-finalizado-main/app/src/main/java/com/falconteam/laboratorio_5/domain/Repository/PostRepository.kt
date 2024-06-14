package com.falconteam.laboratorio_5.domain.Repository

import com.falconteam.laboratorio_5.data.resource.Resource
import com.falconteam.laboratorio_5.data.service.AddCommentResponse
import com.falconteam.laboratorio_5.data.service.AddPostResponse
import com.falconteam.laboratorio_5.data.service.DeletePostResponse
import com.falconteam.laboratorio_5.data.service.GetAllPostWithCommentsResponse

interface PostRepository {
    suspend fun getAllPostsWithComments(token: String): Resource<List<GetAllPostWithCommentsResponse>>
    suspend fun addPost(token: String, title: String, description: String): Resource<AddPostResponse>
    suspend fun addComment(token: String, postId: String, comment: String): Resource<AddCommentResponse>
    suspend fun deletePost(token: String, postId: String): Resource<DeletePostResponse>
}