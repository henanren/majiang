#!/bin/sh


mvn install:install-file  \
    -Dfile=isnowfox-cms-2.0.0-SNAPSHOT.jar \
    -DgroupId=com.isnowfox.web \
    -DartifactId=isnowfox-cms \
    -Dversion=2.0.0-SNAPSHOT -Dpackaging=jar

mvn install:install-file  \
    -Dfile=isnowfox-core-2.0.0-SNAPSHOT.jar \
    -DgroupId=com.isnowfox.web \
    -DartifactId=isnowfox-core \
    -Dversion=2.0.0-SNAPSHOT -Dpackaging=jar

mvn install:install-file  \
    -Dfile=isnowfox-dbtool-2.0.0-SNAPSHOT.jar \
    -DgroupId=com.isnowfox.web \
    -DartifactId=isnowfox-dbtool \
    -Dversion=2.0.0-SNAPSHOT -Dpackaging=jar

mvn install:install-file  \
    -Dfile=isnowfox-game-2.0.0-SNAPSHOT.jar \
    -DgroupId=com.isnowfox.web \
    -DartifactId=isnowfox-game \
    -Dversion=2.0.0-SNAPSHOT -Dpackaging=jar

mvn install:install-file  \
    -Dfile=isnowfox-serialize-2.0.0-SNAPSHOT.jar \
    -DgroupId=com.isnowfox.web \
    -DartifactId=isnowfox-serialize \
    -Dversion=2.0.0-SNAPSHOT -Dpackaging=jar

mvn install:install-file  \
    -Dfile=isnowfox-web-2.0.0-SNAPSHOT.jar \
    -DgroupId=com.isnowfox.web \
    -DartifactId=isnowfox-web \
    -Dversion=2.0.0-SNAPSHOT -Dpackaging=jar








mvn install:install-file  \
    -Dfile=forkjoin-apikit-2.0.0-SNAPSHOT.jar \
    -DgroupId=org.forkjoin \
    -DartifactId=forkjoin-apikit \
    -Dversion=2.0.0-SNAPSHOT -Dpackaging=jar


mvn install:install-file  \
    -Dfile=forkjoin-apikit-core-2.0.0-SNAPSHOT.jar \
    -DgroupId=org.forkjoin \
    -DartifactId=forkjoin-apikit-core \
    -Dversion=2.0.0-SNAPSHOT -Dpackaging=jar


mvn install:install-file  \
    -Dfile=forkjoin-apikit-springmvc-2.0.0-SNAPSHOT.jar \
    -DgroupId=org.forkjoin \
    -DartifactId=forkjoin-apikit-springmvc \
    -Dversion=2.0.0-SNAPSHOT -Dpackaging=jar


mvn install:install-file  \
    -Dfile=forkjoin-core-2.0.0-SNAPSHOT.jar \
    -DgroupId=org.forkjoin \
    -DartifactId=forkjoin-core \
    -Dversion=2.0.0-SNAPSHOT -Dpackaging=jar


mvn install:install-file  \
    -Dfile=forkjoin-jdbckit-2.0.0-SNAPSHOT.jar \
    -DgroupId=org.forkjoin \
    -DartifactId=forkjoin-jdbckit \
    -Dversion=2.0.0-SNAPSHOT -Dpackaging=jar

