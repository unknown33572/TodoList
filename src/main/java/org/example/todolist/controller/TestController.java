package org.example.todolist.controller;

import org.example.todolist.dto.ResponseDTO;
import org.example.todolist.dto.TestRequestBodyDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {
//  @GetMapping("{id}")
//  public String testControllerWithPathVariables(@PathVariable(required = false) int id) {
//    return "Hello World!" + id;
//  }

//  @GetMapping("testRequestParam")
//  public String testControllerWithRequestParams(@RequestParam(required = false) int id) {
//    return "Hello World!" + id;
//  }

  @GetMapping("/testRequestBody")
  public String testControllerRequestBody(@RequestBody TestRequestBodyDTO testRequestBodyDTO) {
    return "Hello World! ID" + testRequestBodyDTO.getId() + " Message" + testRequestBodyDTO.getMessage();
  }

  @GetMapping("/testResponseBody")
  public ResponseDTO<String> testControllerResponseBody() {
    List<String> list = new ArrayList<>();
    list.add("Hello World! I'm S");
    ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
    return response;
  }

  @GetMapping("/testResponseEntity")
  public ResponseEntity<?> testControllerResponseEntity() {
    List<String> list = new ArrayList<>();
    list.add("Hello World! I'm S");
    ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
//    return ResponseEntity.badRequest().body(response);
    return ResponseEntity.ok(response);
  }
}
