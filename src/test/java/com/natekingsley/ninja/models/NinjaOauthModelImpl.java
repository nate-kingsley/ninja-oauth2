/**
 * 
 */
package com.natekingsley.ninja.models;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;

import com.natekingsley.ninja.models.NinjaOAuthModel;

/**
 * @author nate-kingsley
 *
 */
public class NinjaOauthModelImpl extends NinjaOAuthModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 329034871665008510L;
	
	
	@Id
    protected ObjectId objectId;

    public ObjectId getId() {
        return this.objectId;
    }

}
