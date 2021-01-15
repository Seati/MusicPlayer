package com.example.mytt.utils;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.example.mytt.SharedPreferences.SPAccountInfo;
import com.example.mytt.bean.LocationInfoBean;

public class MyLocationListener extends BDAbstractLocationListener {
    public static  String city;
    LocationInfoBean locationInfoBean=new LocationInfoBean();

    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
        //以下只列举部分获取地址相关的结果信息
        //更多结果信息获取说明，请参照类参考中BDLocation类中的说明
        try {
           /*String addr = bdLocation.getAddrStr();        //获取详细地址信息
             String country = bdLocation.getCountry();       //获取国家
            String province = bdLocation.getProvince();    //获取省份
            String city = bdLocation.getCity();            //获取城市
            String district = bdLocation.getDistrict();    //获取区县
            String street = bdLocation.getStreet();        //获取街道信息
            String adcode = bdLocation.getAdCode();        //获取adcode
            String town = bdLocation.getTown();            //获取乡镇信息*/
            locationInfoBean.setAddr(bdLocation.getAddrStr());
            locationInfoBean.setCountry(bdLocation.getCountry());
            locationInfoBean.setProvince(bdLocation.getProvince());
            locationInfoBean.setCity(bdLocation.getCity());
            locationInfoBean.setDistrict(bdLocation.getDistrict());
            locationInfoBean.setStreet(bdLocation.getStreet());
            locationInfoBean.setAdcode(bdLocation.getAdCode());
            locationInfoBean.setTown(bdLocation.getTown());
            SPAccountInfo.put(SPAccountInfo.KEY_LOCAL_CITY,bdLocation.getCity().toString());
            SPAccountInfo.putLocationInfo(locationInfoBean);

        }catch (Exception e){
        }
    }
}
