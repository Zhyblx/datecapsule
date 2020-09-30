package calendar;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.json.JSONObject;

/**
 * 获取聚合数据万年历接口的日期信息
 *
 * @author 张益斌
 */

public class JuheCalendar {

    public String getCalendar(String calendar) throws Exception {
        String key = "c11f13ca8364071b921bcb0ff03aee51";
        Connection connection = Jsoup.connect(
                "http://v.juhe.cn/calendar/day?date=" +
                        calendar + "&key=" + key
        );
        connection.ignoreContentType(true);
        connection.timeout(5000);
        connection.userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.121 Safari/537.36");

        Document document = connection.get();
        String doc = document.text();

        JSONObject jsonObject = new JSONObject(doc);
        JSONObject result = jsonObject.getJSONObject("result");
        JSONObject data = result.getJSONObject("data");
        return data.get("weekday").toString(); // 返回：星期一～星期天

    }

//    public static void main(String[] args) throws Exception {
//        JuheCalendar juheCalendar=new JuheCalendar();
//        System.out.println(juheCalendar.getCalendar("2020-10-18"));
//
//    }

}
