package com.company.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseWrapper {

    private Boolean success;
    private String message;
    private Integer code;
    private Object data;// can be any dto object

    public ResponseWrapper(String message, int code ,Object data){
        this.success=true;
        this.message = message;
        this.code= code;
        this.data=data;

    }

    public ResponseWrapper(String message){
        this.message=message;
        this.success=true;
    }

    public ResponseWrapper(Object data){
        this.data=data;
    }
}
