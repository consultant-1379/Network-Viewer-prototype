#! /bin/bash

# This script is executed before JBoss, so it can be used to configure the environment.
#--------------------------------------------------------------------------------------

# Load the common functions
source docker-env-functions.sh

# Install the RPMs from rpms folder, and the txt files in iso and nexus folders
install_rpms_from_nexus
install_rpms_from_iso

cp /opt/ericsson/com.ericsson.oss.services.cm/*.ear $JBOSS_HOME/standalone/deployments
cp /opt/ericsson/ERICps_CXP9030203/*.ear $JBOSS_HOME/standalone/deployments
cp /opt/ericsson/com.ericsson.oss.services.network-explorer-import/*.ear $JBOSS_HOME/standalone/deployments
cp /opt/ericsson/ERICmodelinformationservice_CXP9031071/*.ear $JBOSS_HOME/standalone/deployments
cp /opt/ericsson/com.ericsson.oss.itpf.common.PlatformIntegrationBridge/*.ear $JBOSS_HOME/standalone/deployments

# Makes JBoss wait for DPS Integration image to be ready
wait_dps_integration

# Creates a user on JBoss to access PIB
$JBOSS_HOME/bin/add-user.sh --user pibUser --password pib12345$ -a -g PibAdmin