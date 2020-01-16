package com.enquery.customeAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface abc {
    AuditDestination value();
    public enum AuditDestination{
        DATABASE,FILE_SYSTEM
    };
}