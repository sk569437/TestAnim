LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)


LOCAL_FFMPEG_PATH := $(LOCAL_PATH)/ffmpeg
LOCAL_MODULE := libffmpeg
LOCAL_SRC_FILES := $(LOCAL_PATH)/ffmpeg/libffmpeg.so
LOCAL_C_INCLUDES += $(LOCAL_PATH)/ffmpeg/libavcodec $(LOCAL_PATH)/ffmpeg/libavutil $(LOCAL_PATH)/ffmpeg/libavformat \
                        $(LOCAL_PATH)/ffmpeg/libswscale $(LOCAL_PATH)/ffmpeg/libavdevice $(LOCAL_PATH)/ffmpeg/libavfilter \
                        $(LOCAL_PATH)/ffmpeg/libswresample
include $(PREBUILT_SHARED_LIBRARY)


include $(CLEAR_VARS)
LOCAL_MODULE := libJNITest
LOCAL_SRC_FILES := JNITest.c
LOCAL_C_INCLUDES := $(LOCAL_PATH) $(LOCAL_PATH)/ffmpeg

LOCAL_LDLIBS := -llog
LOCAL_SHARED_LIBRARIES := libffmpeg




include $(BUILD_SHARED_LIBRARY)

#cp lib to jniLibs dir
$(shell test -f [文件] && echo yes)
HAVE_JNI_LIB_FILE := $(shell test -f $(LOCAL_PATH)/../libs/armeabi/libJNITest.so && echo yes)

ifeq ($(HAVE_JNI_LIB_FILE), yes)
$(shell cp -af $(LOCAL_PATH)/../libs/armeabi/libJNITest.so $(LOCAL_PATH)/../jniLibs/armeabi)
endif