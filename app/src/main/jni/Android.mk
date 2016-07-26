LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE := libJNITest
LOCAL_SRC_FILES := JNITest.cpp
LOCAL_C_INCLUDES := $(LOCAL_PATH)
LOCAL_LDLIBS := -llog

#PRODUCT_COPY_FILES += $(LOCAL_PATH)/../jniLibs/armeabi


include $(BUILD_SHARED_LIBRARY)

#cp lib to jniLibs dir
$(shell test -f [文件] && echo yes)
HAVE_JNI_LIB_FILE := $(shell test -f $(LOCAL_PATH)/../libs/armeabi/libJNITest.so && echo yes)

ifeq ($(HAVE_JNI_LIB_FILE), yes)
$(shell cp -af $(LOCAL_PATH)/../libs/armeabi/libJNITest.so $(LOCAL_PATH)/../jniLibs/armeabi)
endif