package cn.bjtu.edu.common.constants;


/**
 * 枚举常量类
 *
 * @Author
 * @Date 2020/4/13 15:14
 * @Description
 */
public class Constants {

    /**
     * 日志相关常量类
     *
     */
    public enum Log {

        LOG_ID("log-id", "日志id"),
        LOG_START_TIME("log-start-time", "日志开始时间");

        private String code;
        private String name;

        public String getCode() {
            return code;
        }
        public String getName() {
            return name;
        }

        Log(String code, String name) {
            this.code = code;
            this.name = name;
        }
    }

}
