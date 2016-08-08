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


#define TEST_PERSON_NAME    "com/jjws/model/Person"
#define TEST_ARRAYlIST_NAME "java/util/ArrayList"


#ifdef __cplusplus
extern "C"
{
#endif
#if 0
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


JNIEXPORT jobjectArray JNICALL Java_com_jjws_testanim_JNITest_getPersonObjArray(JNIEnv *env, jobject thiz, jobject obj) {


    jclass cls = env->FindClass(TEST_PERSON_NAME);
    jclass listcls = env->FindClass(TEST_ARRAYlIST_NAME);

    LOGD("[getPersonObjArray]:get jclass suc");

    jmethodID id = env->GetMethodID(cls, "<init>", "()V");
    jmethodID listid = env->GetMethodID(listcls, "<init>", "()V");


    jmethodID listlenid =  (jmethodID)env->GetMethodID(listcls, "size", "()I");
    jmethodID listgetid =  (jmethodID)env->GetMethodID(listcls, "get", "(I)Ljava/lang/Object;");

    LOGD("[getPersonObjArray]:get method id suc");

    jint len = (jint)env->CallIntMethod(obj, listlenid);

    LOGD("[getPersonObjArray]:len=%d", len);
    jobjectArray list = (jobjectArray)env->NewObjectArray(len, cls, NULL);
    LOGD("[getPersonObjArray]:new object array");
#if 1
    int i = 0;
    for(i=0;i<len;i++) {
        jobject item = env->CallObjectMethod(obj, listgetid, i);
        if(item != NULL) {
            env->SetObjectArrayElement(list, i, item);
            LOGD("[getPersonObjArray]:get item %d", i);
        }
    }
#endif

    return list;

}

char *itoa(int i) {

    int tmpi = i;
    int src = i;
    int len = 1, offset = 0;
    char *des  = NULL;

    LOGD("[itoa]:function begin, i=%d", i);

    while((tmpi=tmpi/10) >= 10) {
        len ++;
    }

    LOGD("[itoa]:get i len, len=%d", len);

    des = (char*)malloc(len+1);
    if(des == NULL)
        return NULL;

    memset(des, 0x00, len + 1);

    offset = len-1;
    while((src/10) >= 10) {

        char ch = src%10 + 48;
        des[offset--] = ch;

        src=src/10;
    }

    LOGD("[itoa]:get remain src , src=%d", src);

    if(src>0) {
        char ch = src%10 + 48;
        des[offset] = ch;
    }

    LOGD("[itoa]:des=%s", des);

    return des;
}

JNIEXPORT jobject JNICALL Java_com_jjws_testanim_JNITest_getPersonListFromNative(JNIEnv *env, jobject thiz, jint jlen) {

    jclass cls = env->FindClass(TEST_PERSON_NAME);
    jclass listcls = env->FindClass(TEST_ARRAYlIST_NAME);
    int len = (int)jlen;

    jmethodID mid_size = env->GetMethodID(listcls, "size", "()I");
    jmethodID mid_get = env->GetMethodID(listcls, "get", "(I)Ljava/lang/Object;");
    jmethodID mid_add = env->GetMethodID(listcls, "add", "(Ljava/lang/Object;)Z");
    jmethodID mid_list = env->GetMethodID(listcls, "<init>", "()V");
    jmethodID mid_person = env->GetMethodID(cls, "<init>", "()V");


    jfieldID fid_id = env->GetFieldID(cls, "id", "Ljava/lang/String;");
    jfieldID fid_name = env->GetFieldID(cls, "name", "Ljava/lang/String;");
    jfieldID fid_sign = env->GetFieldID(cls, "sign", "Ljava/lang/String;");
    jfieldID fid_sex = env->GetFieldID(cls, "sex", "Ljava/lang/String;");
    jfieldID fid_age = env->GetFieldID(cls, "age", "I");

    jobject obj = env->NewObject(listcls, mid_list);
    if(obj == NULL) {
        LOGD("getPersonListFromNative->obj is null");
        return NULL;
    }

#if 1
    int i=0;
    for(i=0;i<len;i++) {
        jobject item = env->NewObject(cls, mid_person);
        int tmpid = 20160726 + i;
        char name[64]={0};
        char sign[64] = {0};
        char sex[8] = {0};
        char *p = NULL;
        p = itoa(i+1);
        strcpy(name, "item ");
        strcat(name, p);
        free(p);
        p = NULL;

        strcpy(sign, "good good study,day day up");
        strcpy(sex, (i%2==0)?("M"):("F"));
        p = itoa(tmpid);
        jstring jid = (jstring)env->NewStringUTF((const char*)p);
        jstring jname = (jstring)env->NewStringUTF((const char*)name);
        jstring jsign = (jstring)env->NewStringUTF((const char*)sign);
        jstring jsex = (jstring)env->NewStringUTF((const char*)sex);
        jint jage = 23 + i;
        free(p);
        p = NULL;
        env->SetObjectField(item, fid_id, jid);
        env->SetObjectField(item, fid_name, jname);
        env->SetObjectField(item, fid_sign, jsign);
        env->SetObjectField(item, fid_sex, jsex);
        env->SetIntField(item, fid_age, jage);

        env->CallBooleanMethod(obj, mid_add, item);
    }
#endif
    return obj;

}
#endif




#include <libavcodec/avcodec.h>
#include <libavutil/common.h>

JNIEXPORT jstring JNICALL Java_com_jjws_testanim_JNITest_testFFmpegLib(JNIEnv *env, jobject thiz) {

    unsigned int ver = avcodec_version();
    const char *config  = avcodec_configuration();
    const char *license = avcodec_license();
    char *des = NULL;
    const char *version_str = "AVCodec Version:";
    const char *config_str = "AVCodec Configuration:";
    const char *license_str = "AVCodec license:";

    jstring jstr = NULL;

    int ver_len = 0, config_len = 0, license_len = 0, len = 0;

    ver_len = sizeof(unsigned int);

    if(config != NULL) {
        config_len = strlen(config);
    }

    if(license != NULL) {
        license_len = strlen(license);
    }

    len = ver_len + config_len + license_len + strlen(version_str) + strlen(config_str) + strlen(license_str) + 4 + 1;

    des = (char*)malloc(len);
    if(des == NULL)
        return NULL;

    memset(des, 0x00, len);

    strcpy(des, version_str);
    sprintf(des+strlen(version_str), "%d", ver);
    strcat(des, "\n");
    strcat(des, "\n");

    strcat(des, license_str);
    strcat(des, license);
    strcat(des, "\n");
    strcat(des, "\n");

    strcat(des, config_str);
    strcat(des, config);



    jstr = (jstring)(*env)->NewStringUTF(env, des);

    (*env)->ReleaseStringUTFChars(env, jstr, (const char*)des);

    return jstr;

}


#ifdef __cplusplus
}
#endif



















#endif  //#ifndef __JNI_TEST_H__
