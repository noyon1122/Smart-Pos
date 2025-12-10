package com.noyon.entity.acl;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;

public class RequestMapListener {

    @PostPersist
    @PostUpdate
    @PostRemove
    public void onChange(Object entity) {
        System.out.println("🔄 Auto-reloaded RequestMap security rules!");
    }
}
