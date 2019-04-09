/********************************************************
 * <p>Description: 优悦口腔_用户模块Controller</p>
 * <p>Project: UserController.java </p>
 * <p>Date: 2019-04-07 10:00:00 </p>
 * <p>Author: SunMinghao </p>
 * <p>Comment : </p>
 *
 *********************************************************/
package com.monkey.bussiness.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@Api(value="/user", tags="用户模块")
@RestController
@RequestMapping("/user")
public class UserController {

}
