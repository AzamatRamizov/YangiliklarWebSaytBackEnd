package com.example.yangiliklarwebsaytbackend.Controller;

import com.example.yangiliklarwebsaytbackend.CreateAnnotation.CheckHuquq;
import com.example.yangiliklarwebsaytbackend.Dto.APIResponse;
import com.example.yangiliklarwebsaytbackend.Entity.Post;
import com.example.yangiliklarwebsaytbackend.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
public class PostContr {

    @Autowired
    PostService postService;
//    @PreAuthorize(value = "hasAuthority('ADDPOST')")
    @CheckHuquq(huquq = "ADDPOST")
    @PostMapping("/addpost")
    public HttpEntity<?> addpost(@RequestBody Post post){
        APIResponse apiResponse=postService.addpost(post);
        return ResponseEntity.status(apiResponse.isHolat()?200:208).body(apiResponse.getHabar());
    }
    @PreAuthorize(value = "hasAuthority('EDITPOST')")
    @PutMapping("/editpost/{id}")
    public HttpEntity<?> editpost(@RequestBody Post post,@PathVariable Integer id){
        APIResponse apiResponse=postService.editpost(post,id);
        return ResponseEntity.status(apiResponse.isHolat()?200:208).body(apiResponse.getHabar());
    }
    @PreAuthorize(value = "hasAuthority('DELETEPOST')")
    @DeleteMapping("/delpost/{id}")
    public HttpEntity<?> delpost(@PathVariable Integer id){
        APIResponse apiResponse=postService.delpost(id);
        return ResponseEntity.status(apiResponse.isHolat()?200:208).body(apiResponse.getHabar());
    }
    @PreAuthorize(value = "hasAuthority('READPOST')")
    @GetMapping("/getpost/{id}")
    public HttpEntity getpost(@PathVariable Integer id){
        APIResponse apiResponse=postService.getpost(id);
        return ResponseEntity.status(apiResponse.isHolat()?200:208).body(apiResponse.getHabar());
    }
    @GetMapping("/getallposts")
    public HttpEntity getallposts(){
        APIResponse apiResponse=postService.getallposts();
        return ResponseEntity.status(apiResponse.isHolat()?200:208).body(apiResponse.getHabar());
    }
}
