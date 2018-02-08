#!/usr/bin/env bash

set -e -x

git clone resource-seroter-repo resource-app

cd resource-app

mvn clean install