#!/usr/bin/env bash

COLOR='\033[1;33m'
NOCOLOR='\033[0m'

echo
echo -e "${COLOR} -> Removing container instances ${NOCOLOR}"
# Removes stopped instances
docker-compose rm --stop -v --force

echo
echo -e "${COLOR} -> Pulling latest images changes ${NOCOLOR}"
# Pull the latest images
docker-compose pull

echo
echo -e "${COLOR} -> Rebuilding custom images ${NOCOLOR}"
# Rebuild your custom Dockerfiles
docker-compose build --pull

echo -e "${COLOR}"
echo -e "=================================================================================================="
echo -e " Containers updated and cleaned up. Run 'docker-compose up' to start your development environment "
echo -e "=================================================================================================="
echo -e "${NOCOLOR}"