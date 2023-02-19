package com.example.yangiliklarwebsaytbackend.Service;

import com.example.yangiliklarwebsaytbackend.Dto.APIResponse;
import com.example.yangiliklarwebsaytbackend.Dto.LavozimDto;
import com.example.yangiliklarwebsaytbackend.Entity.Lavozim;
import com.example.yangiliklarwebsaytbackend.Repository.LavozimRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LavozimService {

    @Autowired
    LavozimRepos lavozimRepos;


    public APIResponse addLavozim(LavozimDto lavozimDto) {
        Optional<Lavozim> byLavozimNomi = lavozimRepos.findByLavozimNomi(lavozimDto.getLavozimNomi());
        if(!byLavozimNomi.isPresent()){
            Lavozim lavozim=new Lavozim();
            lavozim.setLavozimNomi(lavozimDto.getLavozimNomi());
            lavozim.setIzoh(lavozimDto.getIzoh());
            lavozim.setHuquqlar(lavozimDto.getHuquqlars());
            lavozimRepos.save(lavozim);
            return new APIResponse("Lavozim saqlandi",true);
        }
        return new APIResponse("Lavozim qo'shilmadi",false);
    }

    public APIResponse editlavozim(Integer id, LavozimDto lavozimDto) {
        Optional<Lavozim> byId = lavozimRepos.findById(id);
        if(byId.isPresent()){
            Optional<Lavozim> byLavozimNomi = lavozimRepos.findByLavozimNomi(lavozimDto.getLavozimNomi());
            if(!byLavozimNomi.isPresent()){
                Lavozim lavozim = byId.get();
                lavozim.setLavozimNomi(lavozimDto.getLavozimNomi());
                lavozim.setHuquqlar(lavozimDto.getHuquqlars());
                lavozim.setIzoh(lavozimDto.getIzoh());
                lavozimRepos.save(lavozim);
                return new APIResponse("Lavozim edited",true);
            }
            return new APIResponse("This lavozim is already exist",false);
        }
        return new APIResponse("lavozim not found",false);
    }

    public APIResponse delLavozim(Integer id) {
        Optional<Lavozim> byId = lavozimRepos.findById(id);
        if(byId.isPresent()){
            lavozimRepos.deleteById(id);
            return new APIResponse("Lavozim deleted",true);
        }
        return new APIResponse("Lavozim not found",false);
    }

    public APIResponse readById(Integer id) {
        Optional<Lavozim> byId = lavozimRepos.findById(id);
        if(byId.isPresent()){
            Lavozim lavozim = byId.get();
            return new APIResponse(lavozim.toString(),true);
        }
        return new APIResponse("Lavozim not found",false);
    }
}
