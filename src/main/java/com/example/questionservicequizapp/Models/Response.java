package com.example.questionservicequizapp.Models;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@RequiredArgsConstructor
public class Response {
    private int id;
    private String response;
}
