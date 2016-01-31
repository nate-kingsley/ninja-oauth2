/**
 * 
 */
package com.natekingsley.ninja.models;

import java.io.Serializable;
import java.util.Date;

/**
 * @author nate-kingsley
 * Superclass for oauth models
 */
/**
 * @author natekingsley
 *
 */
/**
 * @author natekingsley
 *
 */
public class NinjaOAuthModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 131006490131714526L;

	
	/**
	 * The client id
	 */
	private String clientId;
	
	/**
	 * The client secret
	 */
	private String clientSecret;
	
	/**
	 * The authorization code
	 */
	private String authorizationCode;
	
	/**
	 * The access token
	 */
	private String accessToken;
	
	/**
	 * The refresh token
	 */
	private String refreshToken;
	
	/**
	 * The address of the requester
	 */
	private String remoteIp;
	
	
	/**
	 * The date/time the tokens expire
	 */
	private Date exipreTime;
	
	/**
	 * 
	 */
	public NinjaOAuthModel() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param clientId The client id
	 * @param clientSecret The client secret
	 * @param authorizationCode The authorization code
	 * @param accessToken The access token
	 * @param refreshToken The refresh token
	 * @param remoteIp The address of the requester
	 * @param exipreTime The date/time the tokens expire
	 */
	public NinjaOAuthModel(String clientId, String clientSecret,
			String authorizationCode, String accessToken, String refreshToken,
			String remoteIp, Date exipreTime) {
		super();
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.authorizationCode = authorizationCode;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.remoteIp = remoteIp;
		this.exipreTime = exipreTime;
	}

	/**
	 * @return the clientId
	 */
	public String getClientId() {
		return clientId;
	}

	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	/**
	 * @return the clientSecret
	 */
	public String getClientSecret() {
		return clientSecret;
	}

	/**
	 * @param clientSecret the clientSecret to set
	 */
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	/**
	 * @return the authorizationCode
	 */
	public String getAuthorizationCode() {
		return authorizationCode;
	}

	/**
	 * @param authorizationCode the authorizationCode to set
	 */
	public void setAuthorizationCode(String authorizationCode) {
		this.authorizationCode = authorizationCode;
	}

	/**
	 * @return the accessToken
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * @param accessToken the accessToken to set
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	/**
	 * @return the refreshToken
	 */
	public String getRefreshToken() {
		return refreshToken;
	}

	/**
	 * @param refreshToken the refreshToken to set
	 */
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	/**
	 * @return the remoteIp
	 */
	public String getRemoteIp() {
		return remoteIp;
	}

	/**
	 * @param remoteIp the remoteIp to set
	 */
	public void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
	}

	/**
	 * @return the exipreTime
	 */
	public Date getExipreTime() {
		return exipreTime;
	}

	/**
	 * @param exipreTime the exipreTime to set
	 */
	public void setExipreTime(Date exipreTime) {
		this.exipreTime = exipreTime;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OAuthApplication ["
				+ (clientId != null ? "clientId=" + clientId + ", " : "")
				+ (clientSecret != null ? "clientSecret=" + clientSecret + ", "
						: "")
				+ (authorizationCode != null ? "authorizationCode="
						+ authorizationCode + ", " : "")
				+ (accessToken != null ? "accessToken=" + accessToken + ", "
						: "")
				+ (refreshToken != null ? "refreshToken=" + refreshToken + ", "
						: "")
				+ (remoteIp != null ? "remoteIp=" + remoteIp + ", " : "")
				+ (exipreTime != null ? "exipreTime=" + exipreTime : "") + "]";
	}
	
	

}
