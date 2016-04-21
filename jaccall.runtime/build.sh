#!/usr/bin/env bash
set -e;

BUILD_ROOT="src/main/c/jaccall/build";
DOCKER_IMAGE="zubnix/jni-cross-compilers";
#ARCHS match docker tags of "zubnix/jni-cross-compilers" images
#TODO add android support
ARCHS=("linux-aarch64" "linux-armv7hf" "linux-armv7sf" "linux-armv6hf" "linux-x86_64" "linux-i686");


NORMAL=$(tput sgr0);
GREEN=$(tput setaf 2);
MAGENTA=$(tput setaf 5);
BOLD=$(tput bold);

prep_build_for_arch() {
    ARCH=$1;
    BUILD_DIR="${BUILD_ROOT}/${ARCH}";

    rm -rf ${BUILD_DIR};
    mkdir -p ${BUILD_DIR};
}

build_for_arch() {
    ARCH=$1;
    BUILD_DIR="${BUILD_ROOT}/${ARCH}";

    USER_IDS="-e BUILDER_UID=$( id -u ) -e BUILDER_GID=$( id -g )";

    printf "${GREEN} Cross compiling for ARCH=%s in BUILD_DIR=%s\n${NORMAL}" ${ARCH} ${BUILD_DIR}
    docker run --rm -v $PWD:/build ${USER_IDS} ${DOCKER_IMAGE}:${ARCH} "cmake" "-DCMAKE_TOOLCHAIN_FILE=\$CMAKE_TOOLCHAIN_FILE" "-H${BUILD_ROOT}/.." "-B${BUILD_DIR}";
    docker run --rm -v $PWD:/build ${USER_IDS} ${DOCKER_IMAGE}:${ARCH} "make" "-C" "${BUILD_DIR}";
}

cross_compile_all() {
    for ARCH in "${ARCHS[@]}"
    do
        prep_build_for_arch "${ARCH}";
        build_for_arch "${ARCH}";
    done
}

cross_compile() {
    command -v docker >/dev/null 2>&1 || { echo "Need docker for cross compilation. exiting." ; exit 1; }

    ARCH=$1;

    if [[ "$ARCH" == "all" ]]; then
        cross_compile_all;
    else
        prep_build_for_arch "${ARCH}";
        build_for_arch "${ARCH}";
    fi;
}

native_compile() {
    #if cmake is not installed, bail out.
    command -v cmake >/dev/null 2>&1 || { echo >&2 "cmake is required but it's not installed.  Aborting."; exit 1; }

    ARCH="native";
    prep_build_for_arch "${ARCH}";

    BUILD_DIR="${BUILD_ROOT}/${ARCH}";
    printf "${GREEN} Native compilation in BUILD_DIR=%s\n${NORMAL}" ${BUILD_DIR}
    pushd ${BUILD_DIR};
        cmake ../..;
        make;
    popd;
}

main() {
    if [ -z "$1" ]; then
        printf "${BOLD}${MAGENTA} Native compilation enabled\n${NORMAL}"
        native_compile;
    else
        printf "${BOLD}${MAGENTA} Cross compilation enabled\n${NORMAL}"
        cross_compile "$1";
    fi;

    exit 0;
}

main "$@"