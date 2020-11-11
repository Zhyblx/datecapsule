package weather;

import java.util.Map;
import java.util.HashMap;

public class WeatherMap {

    public String getInfo(String key) {
        String info = "";
        Map<String, String> map = new HashMap<String, String>();
        map.put("00", "晴");
        map.put("01", "多云");
        map.put("02", "阴");
        map.put("03", "阵雨");
        map.put("04", "雷阵雨");
        map.put("05", "雷阵雨伴有冰雹");
        map.put("06", "雨夹雪");
        map.put("07", "小雨");
        map.put("08", "中雨");
        map.put("09", "大雨");
        map.put("10", "暴雨");
        map.put("11", "大暴雨");
        map.put("12", "特大暴雨");
        map.put("13", "阵雪");
        map.put("14", "小雪");
        map.put("15", "中雪");
        map.put("16", "大雪");
        map.put("17", "暴雪");
        map.put("18", "雾");
        map.put("19", "冻雨");
        map.put("20", "沙尘暴");
        map.put("21", "小到中雨");
        map.put("22", "中到大雨");
        map.put("23", "大到暴雨");
        map.put("24", "暴雨到大暴雨");
        map.put("25", "大暴雨到特大暴雨");
        map.put("26", "小到中雪");
        map.put("27", "中到大雪");
        map.put("28", "大到暴雪");
        map.put("29", "浮尘");
        map.put("30", "扬沙");
        map.put("31", "强沙尘暴");
        map.put("53", "霾");
        info = map.get(key);
        return info;

    }

//    public static void main(String[] args) {
//        System.out.println(
//                new WeatherMap().getInfo("31"));
//
//    }
}
