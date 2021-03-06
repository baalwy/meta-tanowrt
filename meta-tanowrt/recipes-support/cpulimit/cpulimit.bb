#
SUMMARY = "Cpulimit"
DESCRIPTION = "Cpulimit is a tool which limits the CPU usage \
of a process (expressed in percentage, not in CPU time)."
HOMEPAGE = "https://github.com/opsengine/cpulimit"
SECTION = "console/utils"

PR = "tano0"
PV = "0.2+git${SRCPV}"
S = "${WORKDIR}/git"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86c1c0d961a437e529db93aa3bb32dc4"

SRC_URI = "git://github.com/opsengine/cpulimit.git;protocol=https"
SRCREV = "f4d2682804931e7aea02a869137344bb5452a3cd"

INSANE_SKIP_${PN} = "ldflags"

do_compile() {
	oe_runmake
}

do_install() {
	install -dm 0755 ${D}${bindir}
	install -m 0755 ${S}/src/cpulimit ${D}${bindir}/cpulimit
}
