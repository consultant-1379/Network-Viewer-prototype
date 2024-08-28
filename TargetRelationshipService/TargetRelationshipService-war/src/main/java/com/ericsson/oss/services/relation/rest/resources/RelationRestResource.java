package com.ericsson.oss.services.relation.rest.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ericsson.oss.services.api.RelationRequest;

/*
 * Rest RelationshipService : To get relation for targets.
 *
 * Resources are served relative to the servlet path specified in the {@link ApplicationPath}
 * annotation.
 *
 */
@Path("/relation")
public interface RelationRestResource {

    /**
     * To get relation for the given relationType and targets.
     *
     * @param relationRequest
     *            {@link RelationRequest}
     * @return All relations existing for the given targets.
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Response getRelationInfoForTargets(final RelationRequest relationRequest);
}
