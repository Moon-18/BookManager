package com.gmy.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

//用于echarts键值对
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NameValue implements Serializable {//实现序列化需要加上get set方法
    String name;
    Integer value;
}
