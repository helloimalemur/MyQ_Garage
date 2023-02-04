# MyQ_Garage
Monitors for presence of specified IP address, such as a cell phone, and opens MyQ Garage upon device connection.

Personally used to automatically open garage when arriving home on motorcycle.

Packaged for install on Arch using 'makepkg' on PKGBUILD.


Arch Linux Install (if not Arch, clone to /usr/share/myq);

1. Run; ```bash <(curl -s https://raw.githubusercontent.com/helloimalemur/MyQ_Garage/master/src/main/java/myq-archinstall.sh)```
2. Add MyQ credentials; ```/usr/share/myq/myq.yml```
2. Update config; ```/usr/share/myq/myq.config```
2. Restart Service; ```sudo systemctl restart myq```

UNINSTALL;

```pacman -R jkoonts-myq```
