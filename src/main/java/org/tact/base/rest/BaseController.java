 package org.tact.base.rest;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    @GetMapping(value = "")
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
    @PostMapping(value = "")
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
    
    /**
     * 
     * @param firstName
     * @return
     * 
     * possible urls:
	 * 		/user/john
	 * 		http://localhost:4545/user/john
     */
    @GetMapping(value = "/{first_name}")
    public <T> T getUser(    	
    	@PathVariable(value = "first_name") String firstName
    	) {
    	
    	List<User> list = cacheService.getCache("users");
    	
    	for (User user : list) {
			if(user.getFirstName().equalsIgnoreCase(firstName)){				
				
				Map<String, Object> map = new LinkedHashMap<String, Object>();        
		        map.put("apiresult", 0);
		        map.put("apimessage", "ok");
		        map.put("apivalue", user);
		        
		        return (T) map;
			}			
		}
        
    	Map<String, Object> map = new LinkedHashMap<String, Object>();        
        map.put("apiresult", 101);
        map.put("apimessage", "Item Not Found");        
        
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
	 * 		/user/john
	 * 		http://localhost:4545/user/john
     */    
    @PutMapping(value = "/{first_name}")
    public <T> T updateUser(
    	@PathVariable(value = "first_name") String firstName,
    	@RequestParam(value = "last_name") String lastName,
    	@RequestParam(value = "city") String city
    ) {        
    	
    	cacheService.updateCache("users", new User(firstName, lastName, city));
    	
    	Map<String, Object> map = new LinkedHashMap<String, Object>();        
        map.put("apiresult", 0);
        map.put("apimessage", "ok");        
        
        return (T) map;
    }
    
    /**
     * 
     * @param firstName
     * @return
     * 
     * possible urls:
	 * 		/user/john
	 * 		http://localhost:4545/user/john
     */
    @DeleteMapping(value = "/{first_name}")
    public <T> T deleteUser(
    		@PathVariable(value = "first_name") String firstName
    ) {        
    	
    	cacheService.deleteCache("users", new User(firstName));
    	
    	Map<String, Object> map = new LinkedHashMap<String, Object>();        
        map.put("apiresult", 0);
        map.put("apimessage", "ok");        
        
        return (T) map;
    }
}
