package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author cxl
 * @date： 2020-11-19 15:34
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultData {
    private String date;
    private String pieceCount;
    private String address;
    private String carCount;
    private String name;
    private String class_num;
    private String reg_code;
}
