package com.sid03kr.projcet.controller;

import com.sid03kr.projcet.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class PostController {

    // HTML <FORM>
    // ajax 검색
    // http post body -> data
    // json , xml, multipart-form

    @PostMapping("/postMethod")
    public SearchParam postMethod(@RequestBody SearchParam searchParam) {

        return searchParam;
    }
}
