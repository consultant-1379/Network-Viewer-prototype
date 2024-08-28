package com.ericsson.oss.services.relation.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.ericsson.oss.itpf.datalayer.dps.DataBucket;
import com.ericsson.oss.itpf.datalayer.dps.DataPersistenceService;
import com.ericsson.oss.itpf.datalayer.dps.exception.general.ObjectNotFoundException;
import com.ericsson.oss.itpf.datalayer.dps.persistence.PersistenceObject;
import com.ericsson.oss.itpf.sdk.core.annotation.EServiceRef;

/**
 * This bean would act as a delegate to Data persistence service.
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class DpsOperations {

    @EServiceRef
    private DataPersistenceService dataPersistenceService;

    @Inject
    private Logger logger;

    private static final String DATA_BUCKET_CONFIGURATION = "LIVE";

    private static final String TARGET_TYPE_ATTRIBUTE = "type";

    /**
     * Retrieves targetTypes for the given identities (poIds).
     *
     * @param persistenceObjectIds
     * @return Key - targetTypes, Value - poIds
     */
    public Map<String, List<Long>> getTargetTypes(final List<Long> persistenceObjectIds) {
        final List<PersistenceObject> persistenceObjects = getDataBucket().findPosByIds(persistenceObjectIds);
        final Map<String, List<Long>> targetTypesToPoIds = new HashMap<>();
        for (final PersistenceObject po : persistenceObjects) {
            final String targetType = po.getTarget().getAttribute(TARGET_TYPE_ATTRIBUTE);
            logger.info("Target type is : {} ", targetType);
            final List<Long> availablePoIds = getOrAddAvailablePoIds(targetType, targetTypesToPoIds);
            availablePoIds.add(po.getPoId());
        }
        return targetTypesToPoIds;
    }

    private List<Long> getOrAddAvailablePoIds(final String targetType, final Map<String, List<Long>> targetTypesToPoIds) {
        if (!targetTypesToPoIds.containsKey(targetType)) {
            final List<Long> poIds = new ArrayList<>();
            targetTypesToPoIds.put(targetType, poIds);
        }
        return targetTypesToPoIds.get(targetType);

    }

    private DataBucket getDataBucket() {
        setReadOnlyTransaction();
        try {
            return dataPersistenceService.getDataBucket(DATA_BUCKET_CONFIGURATION);
        } catch (final ObjectNotFoundException onfe) {
            throw onfe;
        }
    }

    private void setReadOnlyTransaction() {
        dataPersistenceService.setWriteAccess(false);
    }
}
