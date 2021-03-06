# This file Copyright (C) 2015 Khem Raj <raj.khem@gmail.com> and
#           Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>
#           Copyright (C) 2019-2020 Anton Kikin <a.kikin@tano-systems.com>

# 03.03.2020
# base-files: Add /etc/shinit for non-login shell init
OPENWRT_SRCREV = "12020f8a95d21547bd7d7fd1fef9cdecf2892803"
OPENWRT_BRANCH = "master"

INC_PR = "14"

LICENSE = "GPL-2.0+"
LIC_FILES_CHKSUM = " file://${WORKDIR}/openwrt/LICENSE;md5=94d55d512a9ba36caa9b7df079bae19f "

SRC_URI += "\
	git://${GIT_OPENWRT_ORG}/openwrt/openwrt.git;name=openwrt;destsuffix=openwrt;branch=${OPENWRT_BRANCH} \
"

SRCREV_openwrt = "${OPENWRT_SRCREV}"
