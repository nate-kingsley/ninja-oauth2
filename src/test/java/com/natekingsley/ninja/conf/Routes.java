/**
 * 
 */
package com.natekingsley.ninja.conf;

import com.natekingsley.ninja.controllers.ApiTestController;
import com.natekingsley.ninja.controllers.NinjaOAuthAuthorizationController;

import ninja.Router;
import ninja.application.ApplicationRoutes;

/**
 * @author natekingsley
 *
 */
public class Routes implements ApplicationRoutes {


	/* (non-Javadoc)
	 * @see ninja.application.ApplicationRoutes#init(ninja.Router)
	 */
	@Override
	public void init(Router router) {

		router.GET().route("/oauth/authz").with(NinjaOAuthAuthorizationController.class, "authorize");
		router.POST().route("/oauth/token").with(NinjaOAuthAuthorizationController.class, "token");
		
		router.GET().route("/api/test").with(ApiTestController.class, "test");
	}

}
