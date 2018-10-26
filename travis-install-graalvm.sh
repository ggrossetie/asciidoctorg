#!/usr/bin/env bash

GRAAL_VM_VERSION="1.0.0-rc7"
GRAAL_VM_ARCHIVE="https://github.com/oracle/graal/releases/download/vm-${GRAAL_VM_VERSION}/graalvm-ce-${GRAAL_VM_VERSION}-linux-amd64.tar.gz"

echo "Downloading ${GRAAL_VM_ARCHIVE}..."
wget -q "${GRAAL_VM_ARCHIVE}" -O graalvm.tar.gz

echo "Extract graalvm.tar.gz to ${TRAVIS_BUILD_DIR}/graalvm directory..."
mkdir "${TRAVIS_BUILD_DIR}/graalvm"
tar -xzf graalvm.tar.gz -C "${TRAVIS_BUILD_DIR}/graalvm" --strip-components 1
