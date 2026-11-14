SUMMARY = "Ungwana image for FRDM i.MX 91"
LICENSE = "MIT"

inherit core-image

IMAGE_FEATURES += "ssh-server-dropbear"
IMAGE_INSTALL:append = " heartbeatd"

# Allow root login with empty password
IMAGE_FEATURES += "allow-empty-password allow-root-login"
