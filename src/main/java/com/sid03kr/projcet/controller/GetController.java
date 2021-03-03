package com.sid03kr.projcet.controller;

import com.sid03kr.projcet.model.SearchParam;
import com.sid03kr.projcet.model.network.Header;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")  // localhost:8080/api
@CrossOrigin(origins = "http://localhost:3000")
public class GetController {

    @RequestMapping(method = RequestMethod.GET, path = "/getMethod")  //localhost:8080/api/getMethod
    public String getRequest() {

        return "hi getMethod";
    }

    @GetMapping("/getParameter")   // localhost:8080/api/getParameter?id=1234&password=abcd
    public String getParameter(@RequestParam String id, @RequestParam(name = "password") String pdw) {

        System.out.println("id" + id);
        System.out.println("pdw" + pdw);

        return id+pdw;
    }

    // localhost:8080/api/multiParameter?account=abcd&email=study@naver.com&page=10
    @GetMapping("/multiParameter")
    public SearchParam getMultiParameter(SearchParam searchParam){
        System.out.println(searchParam.getAccount());
        System.out.println(searchParam.getEmail());
        System.out.println(searchParam.getPage());

        // { "account" : "", "email" :"", "page": 0 }
        return searchParam;
    }

    @GetMapping("/header")
    public Header getHeader() {

        // {"resultCode": "OK", "description" : "OK"}
        return Header.builder().resultCode("OK").description("OK").build();
    }
}
