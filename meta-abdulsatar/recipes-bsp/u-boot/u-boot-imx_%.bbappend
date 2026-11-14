# Workaround: meta-freescale doesn't provide efitools-native (cert-to-efi-sig-list),
# needed when CONFIG_EFI_CAPSULE_AUTHENTICATE=y. See training/upstream-bugs.md #3.
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI += "file://disable-capsule-auth.cfg"
