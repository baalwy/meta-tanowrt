# Copyright (C) 2015, MentorGraphics
# Copyright (C) 2015, Fabio Berton <fabio.berton@ossystems.com.br>
# Copyright (C) 2017, Theodore A. Roth <theodore_roth@trimble.com>
# Copyright (C) 2018-2019, Anton Kikin <a.kikin@tano-systems.com>

PR = "tano9"
DESCRIPTION = "OpenWrt system helper toolbox"
HOMEPAGE = "http://wiki.openwrt.org/doc/techref/ubox"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://kmodloader.c;beginline=1;endline=13;md5=61e3657604f131a859b57a40f27a9d8e"
SRCREV = "16f7e16181e2f3e9cf3e2ce56a7e291844900d09"
SECTION = "base"

DEPENDS = "ubus libubox uci"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "\
	git://${GIT_OPENWRT_ORG}/project/ubox.git \
	file://log.init \
"

# 31.12.2019
# kmodloader: added -a arg to modprobe
SRCREV = "0e34af143373126fc62b43612233a158694ec643"

S = "${WORKDIR}/git"

inherit cmake openwrt-services openwrt

OPENWRT_SERVICE_PACKAGES = "ubox"
OPENWRT_SERVICE_SCRIPTS_ubox += "log"
OPENWRT_SERVICE_STATE_ubox-log ?= "enabled"

do_install_append () {
	if [ "${@bb.utils.contains('VIRTUAL-RUNTIME_syslog', 'ubox', '1', '0', d)}" = "1" ]; then
		install -Dm 0755 ${WORKDIR}/log.init ${D}${sysconfdir}/init.d/log

		install -dm 0755 ${D}/sbin
		ln -s /usr/sbin/logd ${D}/sbin/logd
		ln -s /usr/sbin/logread ${D}/sbin/logread
		ln -s /usr/sbin/validate_data ${D}/sbin/validate_data
	fi

	if [ "${@bb.utils.contains('VIRTUAL-RUNTIME_kmod_manager', 'ubox', '1', '0', d)}" = "1" ]; then
		install -dm 0755 ${D}/usr/sbin
		ln -s /sbin/kmodloader ${D}/usr/sbin/rmmod
		ln -s /sbin/kmodloader ${D}/usr/sbin/insmod
		ln -s /sbin/kmodloader ${D}/usr/sbin/lsmod
		ln -s /sbin/kmodloader ${D}/usr/sbin/modinfo
		ln -s /sbin/kmodloader ${D}/usr/sbin/modprobe

		install -dm 0755 ${D}/sbin
		ln -s /usr/sbin/kmodloader ${D}/sbin/kmodloader
		ln -s /sbin/kmodloader ${D}/sbin/modprobe
	fi
}

RDEPENDS_${PN} += "\
	ubus \
	libubox \
"

FILES_${PN} = "\
	${libdir} \
	${sbindir} \
	${base_sbindir} \
	${sysconfdir} \
"

PACKAGES += "getrandom"
SUMMARY_getrandom = "OpenWrt getrandom system helper"
SECTION_getrandom = "base"
FILES_getrandom = "${bindir}/getrandom"
