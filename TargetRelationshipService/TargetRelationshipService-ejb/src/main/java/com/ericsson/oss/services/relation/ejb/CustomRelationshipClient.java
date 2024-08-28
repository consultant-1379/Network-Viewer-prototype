package com.ericsson.oss.services.relation.ejb;

import com.ericsson.oss.itpf.sdk.core.classic.ServiceFinderBean;
import com.ericsson.oss.services.relation.spi.CustomRelationshipProvider;

/**
 * This is a wrapper for Service framework Service Finder. It locates all the available services for the qualified relation type.
 */
public class CustomRelationshipClient {
    private final ServiceFinderBean serviceFinder = new ServiceFinderBean();

    public CustomRelationshipProvider getCustomRelationshipBean(final String relationType) {
        final CustomRelationshipProvider customRelationshipProvider = serviceFinder.find(CustomRelationshipProvider.class, relationType);
        return customRelationshipProvider;
    }
}
