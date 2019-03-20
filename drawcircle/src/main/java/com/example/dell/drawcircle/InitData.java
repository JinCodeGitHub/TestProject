package com.example.dell.drawcircle;

import android.content.Context;

/**
 * <pre>
 *     author : dell
 *     time   : 2019/01/21
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class InitData {

   private   SetData msetdata;
   private   Context mContext;

  InitData(Context context ,SetData setData){

       this.mContext  = context;
       this.msetdata  = setData;

   }



    public   void  getsData(int a ,int b){
        msetdata.getData(a,b);
    }
}
