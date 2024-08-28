package com.ericsson.oss.services.trs.cucumber.rest

abstract class AbstractRestAdapter {

    protected final String BASE_URL = "http://localhost:8080"
    protected final String IPLANET_DIRECTORY_PRO_COOKIE = 'iPlanetDirectoryPro=AQIC5wM2LY4SfcwNPrcXlzFTsoG5t9sdf_DBM-t2ubdkyZc.AAJTSQACMDE'
    protected String userId
    private final String ADMINISTRATOR_USER = 'Network_Explorer_Administrator_User'

    void setUserId(final String userId) {
        this.userId = userId
    }

    void setUserToAdministrator() {
        this.userId = ADMINISTRATOR_USER
    }
}
