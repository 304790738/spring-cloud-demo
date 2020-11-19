package cn.bjtu.edu.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;

@Data
@ApiModel(value = "返回数据封装类")
public class CommonResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    /** 成功标志 */
    public static final long SUCCESS= 0;
    public static final long FAIL= -1;
    public static final long SYSFAIL= -500;
    /** 响应提示语 */
    public static final String SUCCESS_TEXT = "Success";
    public static final String FAIL_TEXT = "Fail";
    public static final String SYSFAIL_TEXT = "Error";

    @ApiModelProperty(value = "成功标志：0成功，-1失败，-500 系统错误")
    private long status;
    @ApiModelProperty(value = "响应提示语")
    private String statusText;
    @ApiModelProperty(value = "响应数据")
    private T data;

    /**
     * 返回成功状态（默认成功信息：操作成功）
     */
    public static CommonResult success() {
        CommonResult commonResult = new CommonResult();
        commonResult.setStatus(CommonResult.SUCCESS);
        commonResult.setStatusText(CommonResult.SUCCESS_TEXT);
        return commonResult;
    }

    /**
     * 返回成功数据（默认成功信息：操作成功）
     */
    public static CommonResult success(Object data) {
        CommonResult commonResult = new CommonResult();
        commonResult.setStatus(CommonResult.SUCCESS);
        commonResult.setStatusText(CommonResult.SUCCESS_TEXT);
        commonResult.setData(data);
        return commonResult;
    }

    /**
     * 返回成功信息和数据
     *
     * @param msg 响应提示语
     * @param data 响应数据
     */
    public static CommonResult success(String msg, Object data) {
        CommonResult commonResult = new CommonResult();
        commonResult.setStatus(CommonResult.SUCCESS);
        commonResult.setStatusText(msg);
        commonResult.setData(data);
        return commonResult;
    }

    /**
     * 返回失败状态（默认错误信息：操作失败）
     */
    public static CommonResult fail() {
        CommonResult commonResult = new CommonResult();
        commonResult.setStatus(CommonResult.FAIL);
        commonResult.setStatusText(CommonResult.FAIL_TEXT);
        return commonResult;
    }

    /**
     * 返回失败信息
     *
     * @param msg
     */
    public static CommonResult fail(String msg) {
        CommonResult commonResult = new CommonResult();
        commonResult.setStatus(CommonResult.FAIL);
        commonResult.setStatusText(msg);
        return commonResult;
    }

    /**
     * 返回失败信息和数据
     *
     * @param msg
     * @param data
     */
    public static CommonResult fail(String msg, Object data) {
        CommonResult commonResult = new CommonResult();
        commonResult.setStatus(CommonResult.FAIL);
        commonResult.setStatusText(msg);
        commonResult.setData(data);
        return commonResult;
    }

    /**
     * 用户自定义返回代码、信息
     *
     * @param code
     * @param msg
     */
    public static CommonResult udd(long code, String msg) {
        CommonResult commonResult = new CommonResult();
        commonResult.setStatus(code);
        commonResult.setStatusText(msg);
        return commonResult;
    }

    /**
     * 用户自定义返回代码、信息、数据
     *
     * @param code
     * @param msg
     * @param data
     */
    public static CommonResult udd(long code, String msg, Object data) {
        CommonResult commonResult = new CommonResult();
        commonResult.setStatus(code);
        commonResult.setStatusText(msg);
        commonResult.setData(data);
        return commonResult;
    }

    /**
     * 用户返回系统级异常代码、信息（只打印前3行错误信息）
     *
     * @param e
     */
    public static CommonResult sysFail(Exception e) {
        return CommonResult.udd(CommonResult.SYSFAIL, printStackTraceToString(e));
    }

    /**
     * 打印完整异常信息
     *
     * @param e
     * @return
     */
    public static String printStackTraceToString(Throwable e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw, true));
        String exStr = sw.getBuffer().toString();
        int index = appearIndex(exStr, "\n", 3);
        if (index > 0) {
            exStr = exStr.substring(0, index - "\n".length());
        }
        return exStr;
    }

    /**
     * 返回在此字符串中第3次出现指定字符处的索引
     *
     * @param srcText
     * @param findText
     * @return
     */
    public static int appearIndex(String srcText, String findText, int limitCount) {
        int count = 0;
        int index = 0;
        while ((index = srcText.indexOf(findText, index)) != -1) {
            index = index + findText.length();
            count++;
            if (count >= limitCount) {
                break;
            }
        }
        return index;
    }

    /**
     * 无参构造
     */
    public CommonResult() {
        this.status = CommonResult.SUCCESS;
        this.statusText = CommonResult.SUCCESS_TEXT;
    }

    /**
     * 有参构造
     *
     * @param status 成功标志
     * @param statusText 响应提示语
     * @param data 响应数据
     */
    public CommonResult(Integer status, String statusText, T data) {
        this.status = status;
        this.statusText = statusText;
        this.data = data;
    }

    /**
     * 有参构造 自动判断data类型是否有效
     *      有效：返回成功数据
     *      无效：返回异常信息
     *
     * @param data 响应数据
     */
    public CommonResult(T data) {
        if (data instanceof Exception) {
            this.status = FAIL;
            this.statusText = ((Exception) data).getLocalizedMessage();
        } else {
            this.status = SUCCESS;
            this.statusText = SUCCESS_TEXT;
            this.data = data;
        }
    }
}
