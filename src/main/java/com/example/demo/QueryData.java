package com.example.demo;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author cxl
 * @date： 2020-11-19 16:54
 */
@Data
public class QueryData {
    private List<List<Map<String,String>>> queryData;
}
