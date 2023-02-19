package com.example.yangiliklarwebsaytbackend.Controller;

import com.example.yangiliklarwebsaytbackend.Dto.APIResponse;
import com.example.yangiliklarwebsaytbackend.Dto.LavozimDto;
import com.example.yangiliklarwebsaytbackend.Service.LavozimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/lavozim")
public class LavozimContr {

    @Autowired
    LavozimService lavozimService;

    @PreAuthorize(value = "hasAuthority('ADDLAVOZIM')")
    @PostMapping("/add")
    public HttpEntity<?> addLavozim(@Valid @RequestBody LavozimDto lavozimDto){
        APIResponse apiResponse=lavozimService.addLavozim(lavozimDto);
        return ResponseEntity.status(apiResponse.isHolat()?200:208).body(apiResponse.getHabar());
    }
    @PreAuthorize(value = "hasAuthority('EDITLAVOZIM')")
    @PutMapping("/edit/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id,@RequestBody LavozimDto lavozimDto){
        APIResponse apiResponse=lavozimService.editlavozim(id,lavozimDto);
        return ResponseEntity.status(apiResponse.isHolat()?200:208).body(apiResponse.getHabar());
    }
    @PreAuthorize(value = "hasAuthority('DELETELAVOZIM')")
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> del(@PathVariable Integer id){
        APIResponse apiResponse=lavozimService.delLavozim(id);
        return ResponseEntity.status(apiResponse.isHolat()?208:200).body(apiResponse.getHabar());
    }
    @PreAuthorize(value = "hasAuthority('READLAVOZIM')")
    @GetMapping("/readlavozim/{id}")
    public HttpEntity<?> readById(@PathVariable Integer id){
        APIResponse apiResponse=lavozimService.readById(id);
        return ResponseEntity.status(apiResponse.isHolat()?200:208).body(apiResponse.getHabar());
    }
}
