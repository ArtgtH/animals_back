//package com.animals_back.advices;
//
//import org.apache.catalina.connector.Response;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//@ControllerAdvice
//public class DefaultAdvice {
//
//    @ExceptionHandler(NoSuchRequestHandlingMethodException.class)
//    public ResponseEntity<Response> handleException(BusinessException e) {
//        Response response = new Response(e.getMessage());
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//
//}