package cn.bjtu.edu.sso.entity;

import cn.bjtu.edu.common.dao.common.Fixed;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * <p>
 * 用户表 cit_training_008_data
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Document(collection = "user")
@ApiModel(value="User对象", description="用户表")
public class User implements Serializable {

private static final long serialVersionUID=1L;
    public static final String _ID = "id";

    @Fixed
    @ApiModelProperty(value = "主键ID")
    @Id
    private String _id;

    @ApiModelProperty(value = "人员ID")
    @Field("user_id")
    private int user_id;

    @ApiModelProperty(value = "性别")
    @Field("sex")
    private int sex;

    @ApiModelProperty(value = "出生年月")
    @Field("birthday")
    private int birthday;

    @ApiModelProperty(value = "总旅行里程")
    @Field("t_distance")
    private int distance;

    @ApiModelProperty(value = "总旅行时间")
    @Field("t_time")
    private int time;
}
