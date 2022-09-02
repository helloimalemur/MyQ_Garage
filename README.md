# MyQ_Garage
Monitors for presence of specified IP address, such as a cell phone, and opens MyQ Garage upon device connection.
Packaged for install on Arch using 'makepkg' on PKGBUILD.


INSTALL;

1. Run; ```bash <(curl -s https://raw.githubusercontent.com/helloimalemur/MyQ_Garage/master/src/main/java/myq-archinstall.sh)```
2. Add MyQ credentials; ```/usr/share/myq/myq.yml```
2. Update config; ```/usr/share/myq/myq.config```
2. Restart Service; ```sudo systemctl restart myq```

UNINSTALL;

```pacman -R jkoonts-myq```
