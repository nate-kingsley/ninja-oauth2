/**
 * 
 */
package com.natekingsley.ninja.etc;

import com.natekingsley.ninja.models.NinjaOAuthModel;

/**
 * @author nate-kingsley
 *
 */
public interface NinjaOAuthDatastore {

	NinjaOAuthModel findByClientId(String clientId);
	
	NinjaOAuthModel findByAuthorizationCode(String authorizationCode);
	
	NinjaOAuthModel findByAccessToken(String accessToken);
	
	NinjaOAuthModel findByRefreshToken(String refreshToken);
	
	boolean addOAuthItem(NinjaOAuthModel item);
	
	boolean updateOAuthItem(NinjaOAuthModel item);
	
	boolean itemExists(NinjaOAuthModel item);
	
	boolean removeOAuthItem(NinjaOAuthModel item);
	
}
