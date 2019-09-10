package com.example.apt_annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;

@Retention(RetentionPolicy.CLASS)
@Target(METHOD)
public @interface OnClick {
    int[] value();
}
