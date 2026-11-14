SUMMARY = "Heartbeat daemon used for training"
DESCRIPTION = "Logs a heartbeat line to stdout (journald) at a fixed interval."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = " \
    file://heartbeatd.c \
    file://CMakeLists.txt \
    file://heartbeatd.service \
"

# Local files unpack into UNPACKDIR (Styhead and later). S = "${WORKDIR}" is an error now.
S = "${UNPACKDIR}"

inherit cmake systemd

SYSTEMD_SERVICE:${PN} = "heartbeatd.service"

do_install:append() {
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${UNPACKDIR}/heartbeatd.service ${D}${systemd_system_unitdir}/
}
