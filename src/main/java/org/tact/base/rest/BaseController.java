 package org.tact.base.rest;

import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tact.base.auth.CacheService;
import org.tact.base.auth.User;

@RestController
@RequestMapping(value = "/user")
public class BaseController {
	
	@Autowired
    private CacheService cacheService;
	
	private static Logger _log = LoggerFactory.getLogger(BaseController.class);
	
	/**
	 * 
	 * @return
	 * 
	 * possible urls:
	 * 		/user
	 * 		http://localhost:4545/user
	 */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public <T> T listUsers() {
        
    	Map<String, Object> map = new LinkedHashMap<String, Object>();        
        map.put("apiresult", 0);
        map.put("apimessage", "ok");
        map.put("apivalue", cacheService.getCache("users"));
        
        return (T) map;
    }
    
    /**
     * 
     * @param firstName
     * @param lastName
     * @param city
     * @return
     * 
     * possible urls:
	 * 		/user
	 * 		http://localhost:4545/user
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public <T> T addUser(
    	@RequestParam(value = "first_name") String firstName,
    	@RequestParam(value = "last_name") String lastName,
    	@RequestParam(value = "city") String city
    ) {
        
    	cacheService.addCache("users", new User(firstName, lastName, city));
    	
    	Map<String, Object> map = new LinkedHashMap<String, Object>();        
        map.put("apiresult", 0);
        map.put("apimessage", "ok");        
        
        return (T) map;
    }
}
