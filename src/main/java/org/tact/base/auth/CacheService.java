package org.tact.base.auth;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

@Service
public class CacheService {
	
	private static Logger _log = LoggerFactory.getLogger(CacheService.class);
    
    private final int REFERSH_TIME_MINUTES = 2;
    
    private LoadingCache<String, List<User>> baseCache;

    // keep the cache for one day
    public CacheService() {
        super();
        baseCache = CacheBuilder.newBuilder().expireAfterWrite(REFERSH_TIME_MINUTES, TimeUnit.MINUTES).build(new CacheLoader<String, List<User>>() {        	
        	@Override
            public List<User> load(final String key) {
        		
        		List<User> list = new LinkedList<User>();
        		User user = new User("John", "Doe", "Toronto");
        		list.add(user);
                return list;       		        		
            }
        });
    }
    
    public void removeCache(final String key) {
        baseCache.invalidate(key);
    }
    
    public List<User> getCache(final String key) {
        try {
			return baseCache.get(key);
		} catch (ExecutionException e) {			
			e.printStackTrace();
			return null;
		}
    }

    public void addCache(final String key, User user) {
    	
    	_log.info(" key ["+key+"] added ");
    	
        List<User> list = null; 
        try {
        	list = (List<User>) baseCache.get(key);
        } catch (final ExecutionException e) {
        	_log.error("error on getting cache");
        }        
        
        list.add(user);
        
        baseCache.put(key, list);
    }    
}
