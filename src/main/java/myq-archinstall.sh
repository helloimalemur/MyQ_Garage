#!/bin/bash
##removes if already installed
sudo systemctl reload-daemon
sudo systemctl stop myq
##if configs already exist back them up to ~/.myq
mkdir ~/.myq
cp /usr/share/myq/myq.* ~/.myq/
##removing
sudo pacman -R jkoonts-myq --noconfirm
##reinstalling
rm -rf ~/MyQ_Garage/
cd ~
git clone https://github.com/helloimalemur/MyQ_Garage.git
cd MyQ_Garage/src/main/java/
makepkg -si --noconfirm
sudo systemctl stop myq
##if configs had already exist they should be in home dir already
cd ~
sudo cp ~/.myq/* /usr/share/myq/
sudo chmod 755 /usr/share/myq/*
sudo systemctl reload-daemon
sudo systemctl start myq
sudo systemctl status myq

