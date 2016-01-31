/**
 * 
 */
package com.natekingsley.ninja.controllers;

import com.google.inject.Singleton;

import ninja.Context;
import ninja.Result;
import ninja.Results;

/**
 * @author nate-kingsley
 *
 */
@Singleton
public class NinjaOAuthAuthorizationController {

	/**
	 * 
	 */
	public NinjaOAuthAuthorizationController() {
		// TODO Auto-generated constructor stub
	}
	
	public Result authorize(Context context){
		
		
		return Results.ok().json();
	}
	
	public Result token(Context context){
		
		return Results.ok().json();
	}

}
