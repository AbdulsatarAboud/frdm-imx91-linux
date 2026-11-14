SUMMARY = "Ungwana image for FRDM i.MX 91"
LICENSE = "MIT"

inherit core-image

IMAGE_INSTALL = "packagegroup-core-boot ${CORE_IMAGE_EXTRA_INSTALL}"
IMAGE_INSTALL:append = " heartbeatd"

IMAGE_FEATURES += "ssh-server-dropbear"

# Development only: NEVER in a product image.
IMAGE_FEATURES += "empty-root-password allow-empty-password allow-root-login"
