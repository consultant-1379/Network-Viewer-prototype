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

package com.ericsson.oss.services.api;

import javax.ejb.Local;

/**
 * Provides features for Relationship service.
 */
@Local
public interface RelationshipService {

    /**
     * To get relation for the given relationTypes and targets.
     *
     * @param relationRequest
     *            {@link RelationRequest}
     * @return {@link RelationResponse}All relations existing for the given targets.
     */
    RelationResponse getRelationInfoForTargets(final RelationRequest relationRequest);

}