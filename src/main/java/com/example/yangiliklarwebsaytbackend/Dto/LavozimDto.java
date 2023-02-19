package com.example.yangiliklarwebsaytbackend.Dto;

import com.example.yangiliklarwebsaytbackend.Entity.Enums.Huquqlar;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class LavozimDto {

    @NotBlank
    private String lavozimNomi;

    @NotEmpty
    private List<Huquqlar> huquqlars;

    @NotNull(message = "Izoh qoldiring!")
    private String izoh;

}
