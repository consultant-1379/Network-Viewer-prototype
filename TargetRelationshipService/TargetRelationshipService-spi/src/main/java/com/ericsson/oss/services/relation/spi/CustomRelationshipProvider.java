package com.ericsson.oss.services.relation.spi;

import javax.ejb.Remote;

import com.ericsson.oss.itpf.sdk.core.annotation.EService;

/**
 * This is to define custom relations. All relations that needs domain-specific code should implement this interface.
 */
@Remote
@EService
public interface CustomRelationshipProvider {

    /**
     * To get relation details for the given relationTypes via domain-specific custom implementation.
     *
     * @param relationType
     *            - relation type
     * @return - relation details {@link RelationInfo}.
     */
    RelationInfo getRelationInfo(final String relationType);
}
