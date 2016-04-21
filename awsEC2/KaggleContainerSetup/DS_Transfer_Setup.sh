#!/bin/bash
sudo apt-get update
sudo apt-get --assume-yes install apt-transport-https ca-certificates
sudo apt-key adv --keyserver hkp://p80.pool.sks-keyservers.net:80 --recv-keys 58118E89F3A912897C070ADBF76221572C52609D
sudo su -c 'echo "deb https://apt.dockerproject.org/repo ubuntu-trusty main" >> /etc/apt/sources.list.d/docker.list'
sudo apt-get update
sudo apt-get purge lxc-docker
apt-cache policy docker-engine
sudo apt-get --assume-yes install linux-image-extra-$(uname -r)
sudo apt-get --assume-yes install apparmor
sudo apt-get --assume-yes install docker-engine
sudo service docker start
sudo docker run hello-world
sudo groupadd docker
sudo usermod -aG docker ubuntu
