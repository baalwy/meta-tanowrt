# Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>
# Copyright (C) 2018-2020 Anton Kikin <a.kikin@tano-systems.com>
# Released under the MIT license (see COPYING.MIT for the terms)

PR = "tano18"

SUMMARY = "OpenWrt DHCP/DHCPv6(-PD)/RA Server & Relay"
HOMEPAGE = "http://git.openwrt.org/?p=project/odhcpd.git;a=summary"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://src/odhcpd.c;beginline=1;endline=13;md5=b5b1da01ca7e1cdd0308c552dc0a1384"
SECTION = "base"
DEPENDS = "libubox ubus libnl uci"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "\
    git://${GIT_OPENWRT_ORG}/project/odhcpd.git;name=odhcpd \
    file://0100-OE-build-fails-due-to-libnl-tiny-dependency-in-CMakeLists.patch \
    file://odhcpd.defaults \
    file://odhcpd.init \
    file://odhcpd-update \
"

# 14.01.2020
# dhcpv6-ia: use dhcp leasetime to set preferred/valid statefull lifetimes
SRCREV = "6db312a698e920ff61505ef1f42469880829774d"
S = "${WORKDIR}/git"

inherit cmake pkgconfig openwrt openwrt-services

OPENWRT_SERVICE_PACKAGES = "odhcpd"
OPENWRT_SERVICE_SCRIPTS_odhcpd += "odhcpd"
OPENWRT_SERVICE_STATE_odhcpd-odhcpd ?= "disabled"

SRCREV_openwrt = "${OPENWRT_SRCREV}"

EXTRA_OECMAKE_append = " -DUBUS=1"

do_install_append() {
    install -Dm 0644 ${WORKDIR}/odhcpd.defaults ${D}${sysconfdir}/uci-defaults/odhcpd
    install -Dm 0755 ${WORKDIR}/odhcpd.init ${D}${sysconfdir}/init.d/odhcpd
    install -Dm 0755 ${WORKDIR}/odhcpd-update ${D}${sbindir}/odhcpd-update
}

FILES_${PN} += "\
               ${sysconfdir}/uci-defaults/odhcpd.defaults \
               ${sysconfdir}/init.d/odhcpd \
               ${sbindir}/odhcpd-update \
               "

RDEPENDS_${PN} += "base-files-scripts-openwrt"
