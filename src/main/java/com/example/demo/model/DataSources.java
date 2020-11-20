package com.example.demo.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author cxl
 * @date： 2020-11-19 15:34
 */
@Data
public class DataSources {
    @JSONField(name = "ConnectionProperties")
    private ConnectionProperties connectionProperties;

    @Data
    class ConnectionProperties {
        @JSONField(name = "ConnectString")
        private String connectString;
    }
}
