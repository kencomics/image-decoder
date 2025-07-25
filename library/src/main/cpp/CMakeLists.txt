cmake_minimum_required(VERSION 3.22..4.0)
project(imagedecoder C CXX ASM)
include(FetchContent)

set(CMAKE_BUILD_TYPE Release)

option(WITH_JPEG "Include JPEG decoder" ON)
option(WITH_PNG "Include PNG decoder" ON)
option(WITH_WEBP "Include WebP decoder" ON)

add_library(imagedecoder SHARED
  java_stream.cpp
  java_wrapper.cpp
  java_objects.cpp
  borders.cpp
  row_convert.cpp
)

# Change the default path of fetch content to avoid downloading the same dependencies per architecture
get_filename_component(deps "../_deps" REALPATH BASE_DIR "${CMAKE_BINARY_DIR}")
set(FETCHCONTENT_BASE_DIR ${deps})

add_subdirectory(lcms)

if(WITH_JPEG)
  add_subdirectory(libjpeg-turbo)
  add_definitions(-DHAVE_LIBJPEG)
  target_sources(imagedecoder PRIVATE decoder_jpeg.cpp)
endif()
if(WITH_PNG)
  add_subdirectory(libpng)
  add_definitions(-DHAVE_LIBPNG)
  target_sources(imagedecoder PRIVATE decoder_png.cpp)
endif()
if(WITH_WEBP)
  add_subdirectory(libwebp)
  add_definitions(-DHAVE_LIBWEBP)
  target_sources(imagedecoder PRIVATE decoder_webp.cpp)
endif()

target_link_libraries(imagedecoder android jnigraphics log)
