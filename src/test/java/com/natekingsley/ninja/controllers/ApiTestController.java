/**
 * 
 */
package com.natekingsley.ninja.controllers;

import java.util.Map;

import ninja.Context;
import ninja.FilterWith;
import ninja.Result;
import ninja.Results;

import com.google.common.collect.Maps;
import com.natekingsley.ninja.filters.NinjaOAuthResourceFilter;;

/**
 * @author natekingsley
 *
 */
public class ApiTestController {

	/**
	 * 
	 */
	public ApiTestController() {
		// TODO Auto-generated constructor stub
	}
	
	@FilterWith(NinjaOAuthResourceFilter.class)
	public Result test(Context context){
		
		Map<String, Object> map = Maps.newHashMap();
		
		map.put("result", "ok");
		
		return Results.ok().json().render(map);
	}

}
