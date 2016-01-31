/**
 * 
 */
package com.natekingsley.ninja.etc;

import org.mongodb.morphia.mapping.Mapper;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import net.binggl.ninja.mongodb.MongoDB;

import com.google.inject.Inject;
import com.natekingsley.ninja.models.NinjaOAuthModel;


/**
 * @author natekingsley
 *
 */
public class NinjaOAuthDatastoreImpl implements NinjaOAuthDatastore {
	
	
	MongoDB mongodb;

	/**
	 * 
	 */
	@Inject
	public NinjaOAuthDatastoreImpl(MongoDB mongodb) {
		this.mongodb = mongodb;
	}

	/* (non-Javadoc)
	 * @see com.natekingsley.ninja.etc.NinjaOAuthDatastore#findByClientId(java.lang.String)
	 */
	@Override
	public NinjaOAuthModel findByClientId(String clientId) {
		return this.mongodb.getDatastore().find(NinjaOAuthModel.class, "clientId", clientId).get();
	}

	/* (non-Javadoc)
	 * @see com.natekingsley.ninja.etc.NinjaOAuthDatastore#findByAuthorizationCode(java.lang.String)
	 */
	@Override
	public NinjaOAuthModel findByAuthorizationCode(String authorizationCode) {
		return this.mongodb.getDatastore().find(NinjaOAuthModel.class, "authorizationCode", authorizationCode).get();
	}

	/* (non-Javadoc)
	 * @see com.natekingsley.ninja.etc.NinjaOAuthDatastore#findByAccessToken(java.lang.String)
	 */
	@Override
	public NinjaOAuthModel findByAccessToken(String accessToken) {
		return this.mongodb.getDatastore().find(NinjaOAuthModel.class, "accessToken", accessToken).get();
	}

	/* (non-Javadoc)
	 * @see com.natekingsley.ninja.etc.NinjaOAuthDatastore#findByRefreshToken(java.lang.String)
	 */
	@Override
	public NinjaOAuthModel findByRefreshToken(String refreshToken) {
		return this.mongodb.getDatastore().find(NinjaOAuthModel.class, "refreshToken", refreshToken).get();
	}

	/* (non-Javadoc)
	 * @see com.natekingsley.ninja.etc.NinjaOAuthDatastore#addOAuthItem(com.natekingsley.ninja.models.NinjaOAuthModel)
	 */
	@Override
	public boolean addOAuthItem(NinjaOAuthModel item) {
		return this.mongodb.getDatastore().save(item) != null ? true : false;
	}

	/* (non-Javadoc)
	 * @see com.natekingsley.ninja.etc.NinjaOAuthDatastore#itemExists(com.natekingsley.ninja.models.NinjaOAuthModel)
	 */
	@Override
	public boolean itemExists(NinjaOAuthModel item) {
		return this.mongodb.getDatastore().exists(item) != null ? true : false;
	}

	@Override
	public boolean removeOAuthItem(NinjaOAuthModel item) {
		return this.mongodb.getDatastore().delete(NinjaOAuthModel.class, item).wasAcknowledged();
	}

	@Override
	public boolean updateOAuthItem(NinjaOAuthModel item) {
		Query<NinjaOAuthModel> q = this.mongodb.getDatastore().createQuery(NinjaOAuthModel.class)
			.field("clientId").equal(item.getClientId());
		this.mongodb.getDatastore().delete(q);
		return this.mongodb.getDatastore().save(item) != null ? true : false;
	}

}
