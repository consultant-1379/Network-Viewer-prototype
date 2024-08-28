package com.ericsson.oss.services.api;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The data class to define request for relationship service.
 */
@XmlRootElement
public class RelationRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @XmlElement
    private List<String> relationTypes;

    @XmlElement
    private List<Long> poIds;

    public List<String> getRelationTypes() {
        return relationTypes;
    }

    public void setRelationTypes(final List<String> relationTypes) {
        this.relationTypes = relationTypes;
    }

    public List<Long> getPoIds() {
        return poIds;
    }

    public void setPoIds(final List<Long> poIds) {
        this.poIds = poIds;
    }
}
