/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2018
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/

package com.ericsson.oss.services.relation.rest.resources;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;

import com.ericsson.oss.services.api.RelationRequest;
import com.ericsson.oss.services.api.RelationResponse;
import com.ericsson.oss.services.api.RelationshipService;

public class RelationRestResourceBean implements RelationRestResource {

    @Inject
    private Logger logger;

    @Inject
    private RelationshipService relationshipService;

    @Override
    public Response getRelationInfoForTargets(final RelationRequest relationRequest) {
        logger.info("Resource is invoked with relations : {}", relationRequest.getRelationTypes());
        final RelationResponse relationResponse = relationshipService.getRelationInfoForTargets(relationRequest);
        return Response.status(201).entity(relationResponse).build();
    }
}