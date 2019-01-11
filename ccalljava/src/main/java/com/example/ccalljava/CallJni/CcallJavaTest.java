package com.example.ccalljava.CallJni;

/**
 * <pre>
 *     author : dell
 *     time   : 2018/06/25
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class CcallJavaTest {

    static {
        System.loadLibrary("");
    }

    public  native   void  JavaCallC ();



}
