package com.vpp.psa.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vpp.psa.model.ErrorObject;
import com.vpp.psa.model.ReturnObject;
import lombok.extern.slf4j.Slf4j;
import org.jooq.exception.NoDataFoundException;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
@Slf4j
public class BatteryResponseBodyWrapAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return (AnnotationUtils.findAnnotation(returnType.getContainingClass(), ResponseBody.class) != null ||
                returnType.getMethodAnnotation(ResponseBody.class) != null);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        ReturnObject<Object> resp = new ReturnObject<>();

        ObjectMapper mapper  = new ObjectMapper();
        String bodyStr = null;
        JsonNode jsonBody = null;
        try {
            bodyStr = mapper.writeValueAsString(body);
            jsonBody = mapper.readTree(bodyStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        log.info("{}", jsonBody);

        resp.setSuccess(!jsonBody.has("type"));
        resp.setData(body);
        return resp;
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<ErrorObject> handleNoDataException(final NoDataFoundException ex){
        return new ResponseEntity<>(ErrorObject.builder()
                .withCode(000)
                .withMessage(ex.getLocalizedMessage())
                .withType(ex.getClass().getSimpleName()).build(),
                HttpStatus.NOT_FOUND);
    }
}
