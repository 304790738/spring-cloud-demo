package cn.bjtu.edu.sso.controller;

import cn.bjtu.edu.common.dao.common.PageList;
import cn.bjtu.edu.common.vo.CommonResult;
import cn.bjtu.edu.sso.entity.User;
import cn.bjtu.edu.sso.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
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
@Slf4j
public class DamoController {

    @Resource
    private UserService userService;

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


    /**
     * status
     *
     * @return
     */
    @ApiOperation("状态检查")
    @GetMapping("/")
    public CommonResult status() {
        try {
            return CommonResult.success("true");
        } catch (Exception e) {
            log.error(e.getMessage());
            return new CommonResult(e);
        }
    }

    /**
     * 查询user列表
     *
     * @param pageSize 每页记录数
     * @param currentPage 当前页码
     * @param field 查询字段
     * @param min 区间最小
     * @param max 区间最大
     * @return
     */
    @ApiOperation("查询列表")
    @GetMapping("/users")
    public CommonResult<PageList<User>> getUserPage(
            @RequestParam @ApiParam(value = "每页记录数", required = false, defaultValue = "10") Integer pageSize,
            @RequestParam @ApiParam(value = "当前页码", required = false, defaultValue = "1") Integer currentPage,
            @RequestParam @ApiParam(value = "查询字段：birthday, distance, time", required = false ,example = "birthday") String field,
            @RequestParam @ApiParam(value = "区间最小", required = false, defaultValue = "1" ,example = "1971") Integer min,
            @RequestParam @ApiParam(value = "区间最大", required = false, defaultValue = "1" ,example = "1980") Integer max) {
        try {

            // 查询数据库
            PageList<User> page = userService.getUserPage(pageSize, currentPage, field, min, max);

            // 组装数据
            return CommonResult.success(page);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new CommonResult(e);
        }
    }

    /**
     * 查询user
     *
     * @param id 唯一主键 _id
     * @return
     */
    @ApiOperation("根据_id查询单个用户")
    @GetMapping("/users/{_id}")
    public CommonResult<User> getUserById(@PathVariable("_id") String id) {
        try {
            // 查询数据库
            User user = userService.getOneById(id);

            // 组装数据
            return CommonResult.success(user);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new CommonResult(e);
        }
    }

    /**
     * 添加user
     *
     * @param user
     * @return
     */
    @ApiOperation("添加用户")
    @PostMapping("/users")
    public CommonResult<User> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
        try {
            // 查询数据库
            User reUser = userService.saveUser(user);

            // 组装数据
            //            HttpHeaders headers = new HttpHeaders();
            //            headers.setLocation(ucBuilder.path("/users/{_id}").buildAndExpand(reUser.get_id()).toUri());

            return CommonResult.success(reUser);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new CommonResult(e);
        }
    }

    /**
     * 删除user
     *
     * @param id
     * @return
     */
    @ApiOperation("删除用户")
    @DeleteMapping("/users/{_id}")
    public CommonResult<Long> deleteUser(@PathVariable("_id") String id) {
        try {
            // 查询数据库
            long count = userService.deleteUser(id);

            return CommonResult.success(count);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new CommonResult(e);
        }
    }

    /**
     * 更新user
     *
     * @param id
     * @param user
     * @return
     */
    @ApiOperation("更新用户")
    @PutMapping("/users/{_id}")
    public CommonResult<Long> updateUser(@PathVariable("_id") String id, @RequestBody User user) {
        try {
            user.set_id(id);
            // 查询数据库
            long count = userService.updateUser(user);
            return CommonResult.success(count);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new CommonResult(e);
        }
    }

}
