package com.example.yangiliklarwebsaytbackend.Controller;

import com.example.yangiliklarwebsaytbackend.Dto.APIResponse;
import com.example.yangiliklarwebsaytbackend.Dto.CommentDto;
import com.example.yangiliklarwebsaytbackend.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentContr {

    @Autowired
    CommentService commentService;
    @PreAuthorize(value = "hasAuthority('ADDCOMMENT')")
    @PostMapping("/addcomment")
    public HttpEntity<?> addcomment(@RequestBody CommentDto commentDto){
        APIResponse apiResponse=commentService.addcomment(commentDto);
        return ResponseEntity.status(apiResponse.isHolat()?200:208).body(apiResponse.getHabar());
    }
    @PreAuthorize(value = "hasAuthority('EDITCOMMENT')")
    @PutMapping("/editcomment/{id}")
    public HttpEntity<?> editComment(@PathVariable Integer id,@RequestBody CommentDto commentDto){
        APIResponse apiResponse=commentService.editComment(id,commentDto);
        return ResponseEntity.status(apiResponse.isHolat()?200:208).body(apiResponse.getHabar());
    }
    @PreAuthorize(value = "hasAuthority('DELETECOMMENT')")
    @DeleteMapping("/delcomment/{id}")
    public HttpEntity<?> delComment(@PathVariable Integer id){
        APIResponse apiResponse=commentService.delComment(id);
        return ResponseEntity.status(apiResponse.isHolat()?200:208).body(apiResponse.getHabar());
    }
    @PreAuthorize(value = "hasAuthority('READCOMMENT')")
    @GetMapping("/readcomment/{id}")
    public HttpEntity<?> readById(@PathVariable Integer id){
        APIResponse apiResponse=commentService.readByIdComment(id);
        return ResponseEntity.status(apiResponse.isHolat()?200:208).body(apiResponse.getHabar());
    }
}
