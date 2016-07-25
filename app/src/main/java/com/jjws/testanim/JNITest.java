package com.jjws.testanim;

//import com.jjws.model.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sk on 16-7-25.
 */
public class JNITest {

    static {
        System.loadLibrary("JNITest");
    }


    public native String getNativeString(String src);
    //public native Person[] getPersonObjArray(ArrayList<Person> list);
}
