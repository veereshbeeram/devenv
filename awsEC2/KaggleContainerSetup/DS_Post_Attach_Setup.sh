#!/bin/bash
sudo mkdir /var/lib/docker
mkdir /home/ubuntu/kaggle
sudo mount /dev/xvdf /var/lib/docker
sudo mount /dev/xvdg /home/ubuntu/kaggle
sudo chown ubuntu:ubuntu /home/ubuntu/kaggle
cd
cat kaggle/setup_helpers/bashrc_add >> ~/.bashrc
source .bashrc
cp kaggle/setup_helpers/screenrc ~/.screenrc
