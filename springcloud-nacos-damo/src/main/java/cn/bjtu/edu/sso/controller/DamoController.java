package cn.bjtu.edu.sso.controller;

import cn.bjtu.edu.common.vo.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 单点登录API
 *
 * @Author Ryan
 * @Date 2020/5/20 14:32
 */
@RestController
@RequestMapping("/sso")
@Api(description = "单点登录API")
public class DamoController {

    @ApiOperation("用户登录")
    @GetMapping("/login")
    public CommonResult login(
            @RequestParam @ApiParam(value = "账户", required = true) String account,
            @RequestParam @ApiParam(value = "密码", required = true) String password) throws Exception {

        Map<String, String> map = new HashMap<>(1);
        map.put("account", account);
        map.put("password", password);
        return CommonResult.success("",map);
    }
}
