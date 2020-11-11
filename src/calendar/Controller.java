package calendar;

/**
 * 通过"假期"、"调休"、"周末"三个维度来判断当天是否需要闹铃；True表示闹铃；False表示无需闹铃
 *
 * @author 张益斌
 */

public class Controller {

    private String calendar = "";

    private void setCalendar(String calendar) {
        this.calendar = calendar;

    }

    private String getCalendar() {
        return this.calendar;

    }

    public Controller(String calendar) {
        this.setCalendar(calendar);

    }

    public boolean getBoolean() throws Exception {
        Boolean token = true;
//        System.out.println(this.getCalendar());
        JuheCalendar juheCalendar = new JuheCalendar();
        String weekend = juheCalendar.getCalendar(this.getCalendar());
//        System.out.println(weekend);

        if (token == true) {
            for (String holiday : HolidayInterface.HOLIDAY) {
                if (this.getCalendar().equals(holiday)) {
                    token = false;
                    break;

                } else {
                    for (String rest : HolidayInterface.REST) {
                        if (this.getCalendar().equals(rest)) {
                            token = true;
                            break;

                        } else {
                            token = false;
                            if (token == false) {
                                if (weekend.equals("星期六") == false && weekend.equals("星期日") == false) {
                                    token = true;
                                    break;

                                } else {
                                    token = false;

                                }
                            }
                        }
                    }
                }
            }
        }
        return token;

    }


//    public static void main(String[] args) throws Exception {
//        for (String a : HolidayInterface.REST) {
//            System.out.println(
//                    new Controller(a).getBoolean()
//
//            );
//        }

//        System.out.println(
//                new Controller("2020-5-6").getBoolean()
//
//        );

//    }
}
