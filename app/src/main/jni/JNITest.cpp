//
// Created by sk on 16-7-25.
//
#include <jni.h>
#include <android/log.h>
#include <stdio.h>
#include <stdlib.h>

#ifndef __JNI_TEST_H__
#define __JNI_TEST_H__

#define LOG_TAG ("Tag")
#define LOGD(...)   __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)
#define LOGI(...)   __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)





#ifdef __cplusplus
extern "C"
{
#endif

JNIEXPORT jstring JNICALL Java_com_jjws_testanim_JNITest_getNativeString(JNIEnv *env, jobject obj, jstring src) {


    const char *src1 = (const char*)env->GetStringUTFChars(src, NULL);
    char *des = NULL;

    int len = 512;
    int tmplen = 0;

    if(src1 == NULL)
    {
        LOGD("getNativeString src1 is NULL");
         return NULL;
    }

    des = (char*)malloc(len + 1);
    if(des == NULL) {
        LOGD("getNativeString des is NULL");
        return NULL;
    }

    memset(des, 0x00, len+1);

    tmplen = strlen(src1);
    strncpy(des, src1, tmplen);

    strcat(des, "+sk test string");

    return env->NewStringUTF(des);

}








#ifdef __cplusplus
}
#endif



















#endif  //#ifndef __JNI_TEST_H__
