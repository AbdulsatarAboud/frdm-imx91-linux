cmake_minimum_required(VERSION 3.16)
project(heartbeatd LANGUAGES C)

include(GNUInstallDirs)

add_executable(heartbeatd heartbeatd.c)
target_compile_options(heartbeatd PRIVATE -Wall -Wextra -Werror)

install(TARGETS heartbeatd RUNTIME DESTINATION ${CMAKE_INSTALL_SBINDIR})
