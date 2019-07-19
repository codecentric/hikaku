#!/bin/bash
install_jdk () {
    if $jabba use $JDK; then
        echo $JDK was available and Jabba is using it
    else
        echo installing $JDK
        $jabba install "$JDK" || exit $?
        echo setting $JDK as Jabba default
        $jabba use $JDK || exit $?
    fi
}

unix_pre () {
    curl -sL https://github.com/shyiko/jabba/raw/master/install.sh | bash && . ~/.jabba/jabba.sh
    unset _JAVA_OPTIONS
    export jabba=jabba
}

linux () {
    unix_pre
}

osx () {
    unix_pre
    export JAVA_HOME="$HOME/.jabba/jdk/$JDK/Contents/Home"
}

windows () {
    PowerShell -ExecutionPolicy Bypass -Command '[Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12; Invoke-Expression (Invoke-WebRequest https://github.com/shyiko/jabba/raw/master/install.ps1 -UseBasicParsing).Content'
    export jabba="$HOME/.jabba/bin/jabba.exe"
    # Windows is unable to clean child processes, so no Gradle daemon allowed
    export GRADLE_OPTS="-Dorg.gradle.daemon=false $GRADLE_OPTS"
    echo 'export GRADLE_OPTS="-Dorg.gradle.daemon=false $GRADLE_OPTS"' >> ~/.jdk_config
    # Apparently exported variables are ignored in subseguent phases on Windows. Write in config file
    echo "export JAVA_HOME=\"${JAVA_HOME}\"" >> ~/.jdk_config
    echo "export PATH=\"${PATH}\"" >> ~/.jdk_config
}

echo "running ${TRAVIS_OS_NAME}-specific configuration"
export JAVA_HOME="$HOME/.jabba/jdk/$JDK"
$TRAVIS_OS_NAME
export PATH="$JAVA_HOME/bin:$PATH"
install_jdk
which java
java -version