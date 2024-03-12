package org.example.todolist.controller;

import org.example.todolist.dto.TestRequestBodyDTO;
import org.springframework.web.bind.annotation.*;

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
}
