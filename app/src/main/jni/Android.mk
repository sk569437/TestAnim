LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE := libJNITest
LOCAL_SRC_FILES := JNITest.cpp
LOCAL_C_INCLUDES := $(LOCAL_PATH)
LOCAL_LDLIBS := -llog

#PRODUCT_COPY_FILES += $(LOCAL_PATH)/../jniLibs/armeabi

$(shell cp -af $(LOCAL_PATH)/../libs/armeabi/libJNITest.so $(LOCAL_PATH)/../jniLibs/armeabi)

include $(BUILD_SHARED_LIBRARY)