package com.example.yangiliklarwebsaytbackend.Service;

import com.example.yangiliklarwebsaytbackend.Dto.APIResponse;
import com.example.yangiliklarwebsaytbackend.Dto.CommentDto;
import com.example.yangiliklarwebsaytbackend.Entity.Comment;
import com.example.yangiliklarwebsaytbackend.Entity.Post;
import com.example.yangiliklarwebsaytbackend.Repository.ComentRepos;
import com.example.yangiliklarwebsaytbackend.Repository.PostRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    ComentRepos comentRepos;
    @Autowired
    PostRepos postRepos;
    public APIResponse addcomment(CommentDto commentDto) {
        Optional<Post> byId = postRepos.findById(commentDto.getPostId());
        if(byId.isPresent()){
            Comment comment=new Comment();
            Post post = byId.get();
            comment.setCommentMatni(commentDto.getCommentMatni());
            comment.setPost(post);
            comentRepos.save(comment);
            return new APIResponse("Comment uploaded!",true);
        }
        return new APIResponse("Post unavailable!",false);
    }

    public APIResponse editComment(Integer id, CommentDto commentDto) {
        Optional<Comment> byId = comentRepos.findById(id);
        if(byId.isPresent()){
            Comment comment = byId.get();
            if(commentDto.getCommentMatni()!=null){
                comment.setCommentMatni(commentDto.getCommentMatni());
            }
            comentRepos.save(comment);
            return new APIResponse("Comment edited!",true);
        }
        return new APIResponse("Comment not found",false);
    }

    public APIResponse delComment(Integer id) {
        Optional<Comment> byId = comentRepos.findById(id);
        if(byId.isPresent()){
            comentRepos.deleteById(id);
            return new APIResponse("Comment deleted",true);
        }
        return new APIResponse("Comment not found",false);
    }

    public APIResponse readByIdComment(Integer id) {
        Optional<Comment> byId = comentRepos.findById(id);
        if(byId.isPresent()){
            Comment comment = byId.get();
            return new APIResponse(comment.toString(),true);
        }
        return new APIResponse("Comment not found",false);
    }
}
