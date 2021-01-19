package weather;

import org.jsoup.Jsoup;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.json.JSONObject;
import org.json.JSONArray;

import weather.db.InsertDatate;

public class JuheWeather {
    private String key = "9c111c32673d7c5aff5a5bb9af0a0559";

    /**
     * 储存省份、城市信息
     *
     * @return cityList
     * @throws Exception
     */
    private String getCityList() throws Exception {

        Connection connection = Jsoup
                .connect("http://apis.juhe.cn/simpleWeather/cityList?key=" + key);
        connection.ignoreContentType(true);
        connection.timeout(5000);
        connection.userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.121 Safari/537.36");
        Document document = connection.get();

        JSONObject jsonObject = new JSONObject(document.text());
        JSONArray jsonArray = jsonObject.getJSONArray("result");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
            System.out.println(jsonObject1.get("id"));
            System.out.println(jsonObject1.get("province"));
            System.out.println(jsonObject1.get("city"));
            System.out.println(jsonObject1.get("district"));
            InsertDatate.setInsertCity
                    (
                            String.valueOf(jsonObject1.get("id")),
                            String.valueOf(jsonObject1.get("province")),
                            String.valueOf(jsonObject1.get("city")),
                            String.valueOf(jsonObject1.get("district"))
                    );

        }
        return null;

    }

    /**
     * 查询城市天气情况
     *
     * @param cityId
     * @return
     * @throws Exception
     */

    public String getCityWeather(String cityId) throws Exception {
        String weather = "";
        Connection connection = Jsoup.connect(
                "http://apis.juhe.cn/simpleWeather/query?city=" + cityId + "&key=" + key
        );
        connection.ignoreContentType(true);
        connection.timeout(5000);
        connection.userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.121 Safari/537.36");
        Document document = connection.get();
        JSONObject jsonObject = new JSONObject(document.text());
        JSONObject result = jsonObject.getJSONObject("result");
        JSONObject realtime = result.getJSONObject("realtime");
        weather = new WeatherMap().getInfo(String.valueOf(realtime.get("wid")));
        return weather;

    }

    /**
     *
     * 查询城市天气ID、当前温度 和 当天温度区间
     *
     * @param cityId
     * @throws Exception
     */
    public String[] getCityWeatherData(String cityId) throws Exception {
        String[] weatherArray=new String[3];
        Connection connection = Jsoup.connect(
                "http://apis.juhe.cn/simpleWeather/query?city=" + cityId + "&key=" + key
        );
        connection.ignoreContentType(true);
        connection.timeout(5000);
        connection.userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.121 Safari/537.36");
        Document document = connection.get();
        JSONObject jsonObject = new JSONObject(document.text());
        JSONObject result = jsonObject.getJSONObject("result");
        JSONObject realtime = result.getJSONObject("realtime");
        weatherArray[0] = String.valueOf(realtime.get("wid")); //天气ID
        weatherArray[1]=String.valueOf(realtime.get("temperature")); //当前温度

        JSONArray jsonArray=result.getJSONArray("future");
        for(int i=0;i<jsonArray.length();i++){
            JSONObject jsonObject1=jsonArray.getJSONObject(i);
            //System.out.println(jsonObject1);
            if(i==0){
                String temperature=jsonObject1.get("temperature").toString();
                //System.out.println(temperature);
                weatherArray[2]=temperature;//当天温度区间

            }
        }
        return weatherArray;

    }

    public static void main(String[] args) throws Exception {
//        JuheWeather juheWeather = new JuheWeather();
//        String[] array=juheWeather.getCityWeatherData("1512");
//        System.out.println(array[0]+";"+array[1]+";"+array[2]);

    }
}
