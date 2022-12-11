package com.gmy.service.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GmyException extends RuntimeException {
    private Integer code;//状态码
    private String msg;//状态信息
}
