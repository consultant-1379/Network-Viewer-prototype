package com.ericsson.oss.services.relation.ejb;

import javax.inject.Inject;

import org.slf4j.Logger;

import com.ericsson.oss.itpf.sdk.core.EServiceNotFoundException;
import com.ericsson.oss.services.api.NoImplementationFoundException;
import com.ericsson.oss.services.relation.spi.RelationInfo;

/**
 * This would locate any model available for the relation type and target Type and get {@link RelationInfo}. If no models are available for the target
 * type and its relation type, then use the custom implementation for the relationType.
 */
public class RelationInfoRetriever {

    @Inject
    private Logger logger;

    @Inject
    private CustomRelationshipClient customRelationshipClient;

    /**
     * Retrieve relation information via Model, if no models available then use custom implementation.
     *
     * @param relationType
     *            - relation type
     * @return relationship details {@link RelationInfo}
     */
    RelationInfo getRelationInfo(final String relationType) {
        logger.info("No model implementation found for relation : {}, will be using custom relation", relationType);
        try {
            return customRelationshipClient.getCustomRelationshipBean(relationType).getRelationInfo(relationType);
        } catch (final EServiceNotFoundException serviceNotFoundException) {
            throw new NoImplementationFoundException("No custom implementation found for relation : " + relationType,
                       serviceNotFoundException);
        }
    }
}
