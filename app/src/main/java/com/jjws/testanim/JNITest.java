package com.jjws.testanim;


import com.jjws.model.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sk on 16-7-25.
 */
public class JNITest {

    private static JNITest instance = null;

    static {
        System.loadLibrary("JNITest");
    }


    public native String getNativeString(String src);
    public native Person[] getPersonObjArray(ArrayList<Person> list);

    public native ArrayList<Person> getPersonListFromNative(int len);




























    public JNITest() {

    }

    public static JNITest getInstance() {
        if(instance == null) {
            instance = new JNITest();
        }

        return instance;
    }
}
