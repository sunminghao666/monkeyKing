/********************************************************
 * <p>Description: 优悦口腔_文章模块Controller</p>
 * <p>Project: ArticleController.java </p>
 * <p>Date: 2019-04-07 10:00:00 </p>
 * <p>Author: SunMinghao </p>
 * <p>Comment : </p>
 *
 *********************************************************/
package com.monkey.bussiness.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@Api(value="/article", tags="文章模块")
@RestController
@RequestMapping("/article")
public class ArticleController {

}
