package cn.bjtu.edu.common.vo;

/**
 * Created by anson on 9/6/16.
 */
public enum UserColumn {

    ID("_id"),
    USERID("user_id"),
    SEX("sex"),
    BIRTHDAY("birthday"),
    DISTANCE("t_distance"),
    TIME("t_time");

    private String columnName;

    private UserColumn(String columnName){
        this.columnName = columnName;
    }

    public String getColumnName(){
        return this.columnName;
    }
}
