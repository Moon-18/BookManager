package com.gmy.borrow.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 借阅信息管理
 * </p>
 *
 * @author gmy
 * @since 2022-06-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "Borrow对象", description = "借阅信息管理")
public class Borrow implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "对应图书id")
    @TableField("BOOK_NAME")
    private String bookName;

    @ApiModelProperty(value = "对应读者id")
    @TableField("READER_ACCOUNT")
    private String readerAccount;

    @ApiModelProperty(value = "0这条记录未结束,1已结束")
    @TableField("STATE")
    private boolean state;

    @ApiModelProperty(value = "备注,标明具体情况")
    @TableField("MESSAGE")
    private String message;

    @ApiModelProperty(value = "借出时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "实际归还时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    @ApiModelProperty(value = "应该归还时间")
    private Date deadline;

}
