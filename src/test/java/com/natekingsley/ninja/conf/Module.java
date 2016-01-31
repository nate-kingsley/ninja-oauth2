/**
 * 
 */
package com.natekingsley.ninja.conf;

import ninja.Context;
import ninja.utils.FakeContext;

import com.google.inject.AbstractModule;
import com.natekingsley.ninja.etc.NinjaOAuthDatastore;
import com.natekingsley.ninja.etc.NinjaOAuthDatastoreImpl;
import com.natekingsley.ninja.models.NinjaOAuthModel;
import com.natekingsley.ninja.models.NinjaOauthModelImpl;

/**
 * @author natekingsley
 *
 */
public class Module extends AbstractModule{

	/**
	 * 
	 */
	public Module() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void configure() {
		bind(NinjaOAuthDatastore.class).to(NinjaOAuthDatastoreImpl.class);
		bind(NinjaOAuthModel.class).to(NinjaOauthModelImpl.class);
		//bind(Context.class).to(FakeContext.class);
	}

}
