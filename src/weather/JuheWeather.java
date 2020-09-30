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

    public static void main(String[] args) throws Exception {
        JuheWeather juheWeather = new JuheWeather();
        System.out.println(juheWeather.getCityWeather("1512"));

    }

}
