# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018-2019 Anton Kikin <a.kikin@tano-systems.com>
# Released under the MIT license (see COPYING.MIT for the terms)

PR = "tano4"
SUMMARY = "Small stream SSL library"
HOMEPAGE = "http://git.openwrt.org/?p=project/ustream-ssl.git;a=summary"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://ustream-ssl.h;beginline=1;endline=17;md5=f633104677420342f142ab4835e04031"
SECTION = "base"
DEPENDS = "libubox openssl"

SRC_URI = "git://${GIT_OPENWRT_ORG}/project/ustream-ssl.git \
          "

# 08.12.2019
# ustream-ssl: mbedtls: fix ssl client verification
SRCREV = "30cebb4fc78e49e0432a404f7c9dd8c9a93b3cc3"

S = "${WORKDIR}/git"

inherit cmake pkgconfig openwrt

do_install_append() {
	install -d ${D}${includedir}/libubox
	install -m 0644 ${S}/*.h ${D}${includedir}/libubox

	install -dm 0755 ${D}${base_libdir}
	mv ${D}${libdir}/libustream-ssl.so ${D}${base_libdir}/libustream-ssl.so
	rmdir --ignore-fail-on-non-empty ${D}${libdir}
}

FILES_${PN}  += "${base_libdir}/*"
FILES_${PN}-dbg  += "${libdir}/lua/5.*/.debug"

RDEPENDS_${PN} += "libcrypto libssl"
