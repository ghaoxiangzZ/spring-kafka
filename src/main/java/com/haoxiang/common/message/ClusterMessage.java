package com.haoxiang.common.message;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClusterMessage {
    private String port;
    private Long sendTime;
}
