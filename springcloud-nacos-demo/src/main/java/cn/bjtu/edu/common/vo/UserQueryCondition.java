package cn.bjtu.edu.common.vo;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@ApiOperation("查询实体")
public class UserQueryCondition {
    @ApiModelProperty(value = "查询字段必须是以下字段之一：birthday, distance, time")
    public String field;
    @ApiModelProperty(value = "查询字段对应的区间最小值")
    public int min;
    @ApiModelProperty(value = "查询字段对应的区间最大值")
    public int max;
}
