package com.ericsson.oss.services.api;

import java.io.Serializable;

/**
 * Define targets that involved in relationship as PoIds with source and destination.
 */
public class AssociatedTarget implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long sourcePoId;
    private Long destinationPoId;

    public AssociatedTarget(final Long sourcePoId, final Long destinationPoId) {
        this.sourcePoId = sourcePoId;
        this.destinationPoId = destinationPoId;
    }

    public Long getSourcePoId() {
        return sourcePoId;
    }

    public void setSourcePoId(final Long sourcePoId) {
        this.sourcePoId = sourcePoId;
    }

    public Long getDestinationPoId() {
        return destinationPoId;
    }

    public void setDestinationPoId(final Long destinationPoId) {
        this.destinationPoId = destinationPoId;
    }
}
