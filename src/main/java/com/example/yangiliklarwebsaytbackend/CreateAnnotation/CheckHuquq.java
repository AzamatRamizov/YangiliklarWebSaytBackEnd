package com.example.yangiliklarwebsaytbackend.CreateAnnotation;

import java.lang.annotation.*;

@Documented
@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface CheckHuquq {
    String huquq();
}
