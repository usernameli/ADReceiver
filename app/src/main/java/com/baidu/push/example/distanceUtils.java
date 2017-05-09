package com.baidu.push.example;

/**
 * Created by brill on 2017/5/10.
 */
public class distanceUtils {
    private static double PI = Math.PI; //3.14159265;  //π
    private static double EARTH_RADIUS = 6378137;    //地球半径
    private static double RAD = Math.PI / 180.0;   //   π/180


    /// <summary>
    /// 根据坐标，计算指定范围内的最大最小经纬度
    /// </summary>
    /// <param name="lng">经度</param>
    /// <param name="lat">纬度</param>
    /// <param name="raidus">范围（米）</param>
    /// <returns>返回最大、最小经纬度minLng, minLat, maxLng, maxLat</returns>
    public double[] getAround(double lng, double lat, int raidus) {
        //The circumference of the earth is 24,901 miles.
        //24,901/360 = 69.17 miles / degree

        Double latitude = lat;
        Double longitude = lng;

        Double degree = (24901 * 1609) / 360.0;   //地球的周长是24901英里
        double raidusMile = raidus;

        //先计算纬度
        Double dpmLat = 1 / degree;
        Double radiusLat = dpmLat * raidusMile;
        Double minLat = latitude - radiusLat;
        Double maxLat = latitude + radiusLat;

        //计算经度
        Double mpdLng = degree * Math.cos(latitude * (PI / 180));  //纬度的余弦
        Double dpmLng = 1 / mpdLng;
        Double radiusLng = dpmLng * raidusMile;
        Double minLng = longitude - radiusLng;
        Double maxLng = longitude + radiusLng;
        //System.out.println("["+minLat+","+minLng+","+maxLat+","+maxLng+"]");
        //最小经度，最小纬度，最大经度，最大纬度
        return new double[]{minLng, minLat, maxLng, maxLat};
    }

    /// <summary>
    /// 根据两点间经纬度坐标（double值），计算两点间距离，单位为米
    /// </summary>
    /// <param name="lng1">经度1</param>
    /// <param name="lat1">纬度1</param>
    /// <param name="lng2">经度2</param>
    /// <param name="lat2">纬度2</param>
    /// <returns>返回距离（米）</returns>
    public static double getDistance(double lng1, double lat1, double lng2, double lat2) {
        double radLat1 = lat1 * RAD;  // // RAD=π/180
        double radLat2 = lat2 * RAD;
        double a = radLat1 - radLat2;
        double b = (lng1 - lng2) * RAD;
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }

}
