package com.example.demo.controller;

import com.example.demo.redis.IRedisService;
import com.example.demo.util.RestResult;
import com.example.demo.util.RestResultType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/redis")
public class RedisController{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IRedisService redisService ;

	/**
	 * 存入redis
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveredisString")
	public ResponseEntity<RestResult> saveredisString(HttpServletRequest request , @Valid String key, @Valid String value) {
		logger.info("saveredisString方法 key:"+key+"value:"+value);
		try {
			redisService.setValue(key,value);
			logger.info("saveredisString方法 key:"+key+"value:"+value);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("saveredisString方法异常 key:"+key+"value:"+value);
		} finally {
			logger.info("saveredisString方法finally key:"+key+"value:"+value);
		}
		return new ResponseEntity<>(new RestResult(RestResultType.OK), HttpStatus.OK);
	}

	/**
	 * 存入redis
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getredis")
	public ResponseEntity getredis(HttpServletRequest request , @Valid String key) {
		logger.info("getredis key:"+key);
		try {
			redisService.getValue(key);
			logger.info("getredis key:"+key);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("getredis********** key:"+key);
		} finally {
			logger.info("getredis方法finally key:"+key);
		}
		return new ResponseEntity<>(redisService.getValue(key), redisService.getValue(key)!=null ?  HttpStatus.OK : HttpStatus.UNAUTHORIZED);
	}

}