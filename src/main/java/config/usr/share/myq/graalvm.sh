#!/bin/bash

#https://github.com/graalvm/graalvm-ce-builds/releases/download/vm-22.3.0/graalvm-ce-java17-linux-amd64-22.3.0.tar.gz
#https://github.com/graalvm/graalvm-ce-builds/releases/download/vm-22.3.0/graalvm-ce-java17-linux-aarch64-22.3.0.tar.gz


cd ~
#wget 'https://github.com/graalvm/graalvm-ce-builds/releases/download/vm-22.3.0/graalvm-ce-java17-linux-aarch64-22.3.0.tar.gz' --no-check-certificate
wget 'https://github.com/graalvm/graalvm-ce-builds/releases/download/vm-21.3.3.1/graalvm-ce-java17-linux-aarch64-21.3.3.1.tar.gz' --no-check-certificate
#tar xvf graalvm-ce-java17-linux-aarch64-22.3.0.tar.gz
tar xvf graalvm-ce-java17-linux-aarch64-21.3.3.1.tar.gz
rm -rf ~/.jdks/
mkdir ~/.jdks/

mv graalvm* ~/.jdks

sudo rm /usr/lib/jvm/default
sudo rm /usr/lib/jvm/default-runtime

sudo ln -s /home/$(whoami)/.jdks/graalvm-ce-java17-22.3.0/ /usr/lib/jvm/default-runtime
sudo ln -s /home/$(whoami)/.jdks/graalvm-ce-java17-22.3.0/ /usr/lib/jvm/default
export JAVA_HOME=/home/$(whoami)/.jdks/graalvm-ce-java17-22.3.0/
export GRAALVM_HOME=/home/$(whoami)/.jdks/graalvm-ce-java17-22.3.0/





