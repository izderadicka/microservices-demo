#! /bin/bash
set -x

VERSION=0.1.0
mvn deploy:deploy-file -DgroupId=eu.zderadicka -DartifactId=spoiler -Dversion=$VERSION \
-Durl=file:../local-maven-repo/ -DrepositoryId=local-maven-repo -DupdateReleaseInfo=true \
-Dfile=./target/spoiler-$VERSION.jar