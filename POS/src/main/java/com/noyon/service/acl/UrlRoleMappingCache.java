package com.noyon.service.acl;

import org.springframework.beans.factory.annotation.Autowired;


import java.util.HashMap;
import java.util.Map;

public class UrlRoleMappingCache {

    private Map<String, String[]> cache = new HashMap<>();

    @Autowired
    private UrlRoleMappingService service;

    /**
     * Returns cached URL-role map.
     * If empty, loads from DB (lazy loading).
     */
    public synchronized Map<String, String[]> get() {
        if (cache.isEmpty()) {
            cache = service.getUrlRoleMap();
        }
        return cache;
    }

    /**
     * Force reload from DB.
     * Called automatically by RequestMapListener or Scheduler.
     */
    public synchronized void refresh() {
        cache = service.getUrlRoleMap();
        System.out.println("🔄 UrlRoleMappingCache refreshed!");
    }
}
