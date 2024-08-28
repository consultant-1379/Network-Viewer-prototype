package com.ericsson.oss.services.relation.spi;

import java.io.Serializable;

/**
 * The data class to define links to targets involved in relationship.
 */
public class AssociatedTargetType implements Serializable {

    private static final long serialVersionUID = 1L;
    private String source;
    private String destination;

    public AssociatedTargetType(final String source, final String destination) {
        this.source = source;
        this.destination = destination;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }
}
