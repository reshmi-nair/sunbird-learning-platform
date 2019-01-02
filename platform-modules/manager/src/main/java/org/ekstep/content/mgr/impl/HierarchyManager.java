package org.ekstep.content.mgr.impl;

import org.ekstep.common.dto.Response;
import org.ekstep.content.mgr.impl.operation.hierarchy.GetHierarchyOperation;
import org.ekstep.content.mgr.impl.operation.hierarchy.SyncHierarchyOperation;
import org.ekstep.content.mgr.impl.operation.hierarchy.UpdateHierarchyOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class HierarchyManager {

    @Autowired private GetHierarchyOperation getHierarchyOperation;
    @Autowired private UpdateHierarchyOperation updateHierarchyOperation;
    @Autowired private SyncHierarchyOperation syncHierarchyOperation;

    public Response get(String contentId, String mode) {
        return this.getHierarchyOperation.getHierarchy(contentId, mode);
    }

    public Response update(Map<String, Object> data) {
        return this.updateHierarchyOperation.updateHierarchy(data);
    }

    public Response sync(String identifier) { return this.syncHierarchyOperation.syncHierarchy(identifier); }
}
