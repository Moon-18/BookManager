package com.gmy.book.entity;

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
 *
 * </p>
 *
 * @author gmy
 * @since 2022-06-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "Book对象", description = "")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "ID", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "名称")
    @TableField("NAME")
    private String name;

    @ApiModelProperty(value = "作者")
    @TableField("WRITER")
    private String writer;

    @ApiModelProperty(value = "图书种类，科教文卫体与其他")
    @TableField("KIND")
    private String kind;

    @ApiModelProperty(value = "图书描述")
    @TableField("COMMENT")
    private String comment;

    @ApiModelProperty(value = "关键词")
    @TableField("WORD")
    private String word;

    @ApiModelProperty(value = "当前在馆数量")
    @TableField("CUR_NUM")
    private Integer curNum;

    @ApiModelProperty(value = "图书全部数量")
    @TableField("SUM_NUM")
    private Integer sumNum;

    @ApiModelProperty(value = "入馆日期")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
