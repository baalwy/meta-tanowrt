# Set preferred Qt5 versions. Different namespace from Qt4, so no conflict
include conf/distro/include/qt5-versions.inc

# Versions
PREFERRED_VERSION_iptables = "1.6.2"
PREFERRED_VERSION_usbutils = "007"
PREFERRED_VERSION_libwebsockets = "3.2%"
PREFERRED_VERSION_qrencode = "4.0.2"
PREFERRED_VERSION_rrdtool = "1.0.50"

# Providers
PREFERRED_PROVIDER_psplash = "psplash-tn"
PREFERRED_PROVIDER_os-release = "tano-version"
PREFERRED_PROVIDER_jpeg = "libjpeg-turbo"
