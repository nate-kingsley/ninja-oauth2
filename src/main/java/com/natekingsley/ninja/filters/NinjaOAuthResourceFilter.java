/**
 * 
 */
package com.natekingsley.ninja.filters;

import java.util.Date;
import java.util.Map;

import org.apache.oltu.oauth2.common.exception.OAuthProblemException;

import com.google.common.collect.Maps;
import com.google.inject.Inject;
import com.natekingsley.ninja.etc.NinjaOAuthDatastore;
import com.natekingsley.ninja.models.NinjaOAuthModel;

import ninja.Context;
import ninja.Filter;
import ninja.FilterChain;
import ninja.Result;
import ninja.Results;

/**
 * @author nate-kingsley
 *
 */
public class NinjaOAuthResourceFilter implements Filter {

	NinjaOAuthDatastore datastore;
	
	/**
	 * 
	 */
	@Inject
	public NinjaOAuthResourceFilter(NinjaOAuthDatastore datastore) {
		this.datastore = datastore;
	}

	/* (non-Javadoc)
	 * @see ninja.Filter#filter(ninja.FilterChain, ninja.Context)
	 */
	@Override
	public Result filter(FilterChain chain, Context context) {

		try {
			if(context.getHeader("Authorization") != null){
				
				String token = context.getHeader("Authorization").replaceAll("Bearer ", "");
				
				NinjaOAuthModel noam = datastore.findByAccessToken(token);
				
				if(noam != null && noam.getExpireTime().after(new Date()) && context.getRemoteAddr().equals(noam.getRemoteIp())){
					return chain.next(context);
				} else {
					throw OAuthProblemException.error("invalid_request", "Invalid token or request from bad ip");
				}

				
			} else {
				throw OAuthProblemException.error("invalid_request", "Missing header token value");
			}
		} catch (OAuthProblemException e) {
        	Map<String, Object> map = Maps.newHashMap();
        	map.put("error", e.getError());
        	map.put("error_description", e.getDescription());
        	map.put("error_uri", e.getUri());
        	if(e.getState() != null)
        		map.put("state", e.getState());
        	
        	return Results.status(Result.SC_403_FORBIDDEN).json().render(map);
        	
        } catch (Exception e){
        	e.printStackTrace();
        	return null;
        }
	}

}
