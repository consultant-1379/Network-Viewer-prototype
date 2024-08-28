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

package com.ericsson.oss.services.relation.ejb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.ericsson.oss.services.api.AssociatedTarget;
import com.ericsson.oss.services.api.NoImplementationFoundException;
import com.ericsson.oss.services.api.RelationRequest;
import com.ericsson.oss.services.api.RelationResponse;
import com.ericsson.oss.services.api.RelationshipService;
import com.ericsson.oss.services.relation.dao.DpsOperations;
import com.ericsson.oss.services.relation.spi.AssociatedTargetType;
import com.ericsson.oss.services.relation.spi.RelationInfo;

/**
 * This would get {@link RelationInfo} via Model/Custom implementation and link all targets involved in relationship.
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class RelationshipServiceBean implements RelationshipService {

    @Inject
    private Logger logger;

    @Inject
    private RelationInfoRetriever relationInfoRetriever;

    @Inject
    private DpsOperations dpsOperations;

    /**
     * Retrieve link for all targets that are involved in relationship.
     *
     * @param relationRequest
     *            {@link RelationRequest}
     * @return - relation response {@link RelationResponse}
     */
    @Override
    public RelationResponse getRelationInfoForTargets(final RelationRequest relationRequest) {
        final RelationResponse response = new RelationResponse();
        final Map<String, List<AssociatedTarget>> relationTypeToAllRelatedTargets = new HashMap<>();
        final Map<String, List<Long>> targetTypesToPoIds = dpsOperations.getTargetTypes(relationRequest.getPoIds());

        for (final String relationType : relationRequest.getRelationTypes()) {
            try {
                final RelationInfo relationInfo = relationInfoRetriever.getRelationInfo(relationType);
                final List<AssociatedTargetType> associationForRequestedTargetTypes = getAssociationForRequestedTargetTypes(
                           targetTypesToPoIds.keySet(),
                           relationInfo.getAssociatedTargetTypes());
                relationTypeToAllRelatedTargets.put(relationType, getAllRelations(targetTypesToPoIds, associationForRequestedTargetTypes));
            } catch (final NoImplementationFoundException e) {
                logger.error("No Model/Custom implementation found for relation : {}", relationType);
            } catch (final Exception e) {
                logger.error("Error during processing get relation for target for relation : {}", relationType);
            }
        }

        response.setRelationTypeToTargets(relationTypeToAllRelatedTargets);
        return response;
    }

    private List<AssociatedTargetType> getAssociationForRequestedTargetTypes(final Set<String> uniqueTargets,
                                                                             final List<AssociatedTargetType> associatedTargetTypes) {

        final List<AssociatedTargetType> associationForRequestedTargetTypes = new ArrayList<>();
        for (final AssociatedTargetType associatedTargetType : associatedTargetTypes) {
            final String source = associatedTargetType.getSource();
            final String destination = associatedTargetType.getDestination();
            if (uniqueTargets.contains(source)
                       && uniqueTargets.contains(destination)) {
                associationForRequestedTargetTypes.add(new AssociatedTargetType(source, destination));
            }
        }
        return associationForRequestedTargetTypes;
    }

    private List<AssociatedTarget> getAllRelations(final Map<String, List<Long>> targetTypesToPoIds,
                                                   final List<AssociatedTargetType> associationForRequestedTargetTypes) {
        final List<AssociatedTarget> allRelations = new ArrayList<>();
        for (final AssociatedTargetType targetType : associationForRequestedTargetTypes) {
            allRelations.addAll(getTargetsInRelation(targetTypesToPoIds.get(targetType.getSource()),
                       targetTypesToPoIds.get(targetType.getDestination())));
        }
        return allRelations;
    }

    private List<AssociatedTarget> getTargetsInRelation(final List<Long> sourceList, final List<Long> destinationList) {
        final List<AssociatedTarget> targetsInRelation = new ArrayList<>();
        for (final Long source : sourceList) {
            for (final Long destination : destinationList) {
                targetsInRelation.add(new AssociatedTarget(source, destination));
            }
        }
        return targetsInRelation;
    }
}
