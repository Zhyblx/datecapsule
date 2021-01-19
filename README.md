#### 日期胶囊 ####

----
##### 1.接口说明 #####


包：calendar(日历)

|类|方法|类型|参数|说明|
|---|---|---|---|---|
|Controller|getBoolean()|Boolean|构造:calendar;格式：2020-5-6|已封装的功能类；判断当天是否需要闹铃|
|JuheCalendar|getCalendar()|String|参数:calendar;格式：2020-5-6|返回信息星期一～星期日|



包：weather(天气)

|类|方法|类型|参数|说明|
|---|---|---|---|---|
|JuheWeather|getCityWeather()|String|参数:cityId;格式：1512|已封装的功能类；查询城市天气情况|
|JuheWeather|getCityWeatherData|String[]|参数:cityId;格式：1512|查询城市天气ID、当前温度和当天温度区间|

|WeatherMap|getInfo() |String|参数:key;格式：31|查询天气种类|


##### 2.数据库文件 #####
数据库：city.db <br>
作用：存储城市了城市ID信息 <br>
数据字典：

 |字段|含义|
 |---|---|
 |id|城市ID|
 |province|省份|
 |city|城市|
 |district|区县|
 

######* 接口(HolidayInterface)中的"假期"和"调休"日期每年都需要根据国务院安排进行更新；


