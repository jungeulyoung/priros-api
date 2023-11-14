package com.lnt.priros.controller;


import com.lnt.priros.model.dto.Task;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/task")
public class TaskController {


    @GetMapping("regist-info")
    public Task registInfo(Task task) {
        return task;
    }

    @GetMapping("bond-discount-rate")
    public Task bondDiscountRate(Task task) {
        return task;
    }

}
