package com.provider.reactive.handler;

import org.springframework.beans.BeanUtils;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.*;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * 类似于Controller，处理用户请求的真实逻辑
 */
public class StudentHandler {


    public static Mono<ServerResponse> selectStudent(ServerRequest request) {
        Student studentBody = new Student();
        studentBody.setId(100);
        request.bodyToMono(Student.class).subscribe(student -> BeanUtils.copyProperties(student, studentBody));
        return ok().contentType(APPLICATION_JSON_UTF8).body(fromObject(studentBody));
    }

    public static Mono<ServerResponse> insertStudent(ServerRequest request){
        return ok().contentType(TEXT_PLAIN).body(fromObject("success"));

    }
    private static class Student {
        private Integer id;
        private String name;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}