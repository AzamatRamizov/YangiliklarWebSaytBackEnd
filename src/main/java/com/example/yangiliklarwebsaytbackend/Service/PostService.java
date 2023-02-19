package com.example.yangiliklarwebsaytbackend.Service;

import com.example.yangiliklarwebsaytbackend.Dto.APIResponse;
import com.example.yangiliklarwebsaytbackend.Entity.Post;
import com.example.yangiliklarwebsaytbackend.Repository.PostRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    PostRepos postRepos;

    public APIResponse addpost(Post post) {
        Post post1=new Post();
        post1.setPostSarlavhasi(post.getPostSarlavhasi());
        post1.setPostMatni(post.getPostMatni());
        postRepos.save(post1);
        return new APIResponse("Post joylandi",true);
    }

    public APIResponse editpost(Post post, Integer id) {
        Optional<Post> byId = postRepos.findById(id);
        if(byId.isPresent()){
            Post post1 = byId.get();
            if(post.getPostSarlavhasi()!=null){
                post1.setPostSarlavhasi(post.getPostSarlavhasi());
            }
            if(post.getPostMatni()!=null){
                post1.setPostMatni(post.getPostMatni());
            }
            postRepos.save(post1);
            return new APIResponse("Post was edited succesfully!",true);
        }
        return new APIResponse("Post not found!",false);
    }

    public APIResponse delpost(Integer id) {
        Optional<Post> byId = postRepos.findById(id);
        if(byId.isPresent()){
            postRepos.deleteById(id);
            return new APIResponse("Post deleted",true);
        }
        return new APIResponse("Post not found",false);
    }

    public APIResponse getpost(Integer id) {
        Optional<Post> byId = postRepos.findById(id);
        if(byId.isPresent()){
            Post post = byId.get();
            return new APIResponse(post.toString(),true);
        }
        return new APIResponse("Post not found",false);
    }

    public APIResponse getallposts() {
        List<Post> all = postRepos.findAll();
        if(all.isEmpty()){
            return new APIResponse("Hali post yo'q",false);
        }
        return new APIResponse(all.toString(),true);
    }
}
