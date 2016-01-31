/**
 * 
 */
package com.natekingsley.ninja.filters;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.binggl.ninja.mongodb.MongoDB;
import ninja.Context;
import ninja.NinjaTest;
import ninja.Result;
import ninja.Results;
import ninja.utils.FakeContext;

import org.apache.commons.codec.binary.Base64;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import com.google.common.collect.Maps;
import com.google.inject.matcher.Matchers;
import com.natekingsley.ninja.etc.NinjaOAuthDatastore;
import com.natekingsley.ninja.models.NinjaOAuthModel;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;




/**
 * @author natekingsley
 *
 */
public class NinjaOAuthResourceFilterTest extends NinjaTest {
	
	private static final MongodStarter starter = MongodStarter.getDefaultInstance();
	private static MongodExecutable mongodExe;
	private static MongodProcess mongod;
	
	private NinjaOAuthDatastore datastore;
	
	private static final String correctAccessToken = Base64.encodeBase64String("correctToken".getBytes());
	private static final String incorrectAccessToken = Base64.encodeBase64String("incorrectToken".getBytes());
	

    @Before
    public void setup() throws Exception{
    	
    	this.datastore = getInjector().getInstance(NinjaOAuthDatastore.class);
    	
    	MongoDB mongodb = getInjector().getInstance(MongoDB.class);

    	mongodb.deleteAll(NinjaOAuthModel.class);
    	
    	this.datastore.addOAuthItem(new NinjaOAuthModel(
    			"myclient", 
    			"mysecret", 
    			null, 
    			correctAccessToken, 
    			null, 
    			"127.0.0.1", 
    			new Date(new Date().getTime() + (3600 * 1000))));
    	
    }
    
    @BeforeClass
    public static void init() throws UnknownHostException, IOException{

    	IMongodConfig mongodConfig = new MongodConfigBuilder()
        .version(Version.Main.PRODUCTION)
        .net(new Net(29019, Network.localhostIsIPv6()))
        .build();

		mongodExe = starter.prepare(mongodConfig);
		mongod = mongodExe.start();
    }
    
    @AfterClass
    public static void destroy() {
    	mongod.stop();
    	mongodExe.stop();
    }
    
    /**
	 * Test method for {@link com.natekingsley.ninja.filters.NinjaOAuthResourceFilter#filter(ninja.FilterChain, ninja.Context)}.
	 */
    @Test
    public void testFilter() throws Exception {
    	Context context = getInjector().getInstance(FakeContext.class);
		assertNotNull(context);
				
		((FakeContext) context).addHeader("Authorization","Bearer " + NinjaOAuthResourceFilterTest.correctAccessToken);
		assertNotNull("Test for Authorization header", context.getHeader("Authorization"));
		
		String token = context.getHeader("Authorization").replaceAll("Bearer ", "");
		assertTrue("Test for nonempty token", token.length() > 0);
		
		NinjaOAuthModel noam = this.datastore.findByAccessToken(token);
		
		//testing for valid oauth model
		assertNotNull("Test for valid oauth model", noam);
		
		//testing for exiration time
		assertTrue("Testing for valid expiration date", noam.getExpireTime().getTime() > new Date().getTime());
		
		//testing for remote ip
		assertEquals("Testing for correct ip", "127.0.0.1", noam.getRemoteIp());
		
		
    }
	

	
	@Test
	public void testFilterFromEndpoint() throws Exception {

		Map<String, String> headers = Maps.newHashMap();
		
		//setting correct token
		headers.put("Authorization", "Bearer " + NinjaOAuthResourceFilterTest.correctAccessToken);

		String result = ninjaTestBrowser.makeRequest(getServerAddress() +  "/api/test", headers);		
		assertEquals("Calling filtered api endpoint with correct token", "{\"result\":\"ok\"}", result);
	
		//checking to make sure wrong token fails the filter
		headers.clear();
		headers.put("Authorization", "Bearer " + NinjaOAuthResourceFilterTest.incorrectAccessToken);
		
		result = ninjaTestBrowser.makeRequest(getServerAddress() +  "/api/test", headers);
		assertEquals("Calling filtered api endpoint with incorrect token", 
				"{\"error_description\":\"Invalid token or request from bad ip\"" + 
						",\"error\":\"invalid_request\",\"error_uri\":null}", 
				result);
		
		//checking to make sure token from wrong ip fails the filter
		headers.clear();
		headers.put("Authorization", "Bearer " + NinjaOAuthResourceFilterTest.correctAccessToken);
		NinjaOAuthModel noam = this.datastore.findByAccessToken(NinjaOAuthResourceFilterTest.correctAccessToken);
		noam.setRemoteIp("192.168.123.123");
		this.datastore.updateOAuthItem(noam);
		
		result = ninjaTestBrowser.makeRequest(getServerAddress() +  "/api/test", headers);
		assertEquals("Calling filtered api endpoint with incorrect address", 
				"{\"error_description\":\"Invalid token or request from bad ip\"" + 
						",\"error\":\"invalid_request\",\"error_uri\":null}", 
				result);
		
	}

}
