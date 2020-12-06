package cn.bjtu.edu.common.vo;

public enum UserQueryField {
    birthday("birthday"), distance("t_distance"), time("t_time");

    private String field;

    UserQueryField(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
