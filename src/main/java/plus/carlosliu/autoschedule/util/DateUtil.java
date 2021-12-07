package plus.carlosliu.autoschedule.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DateUtil {
    public static List<String> getDateList(String startDate, int size) {
        List<String> dateList = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = simpleDateFormat.parse(startDate);
            for (int count = 0; count < size; count++){
                date = new Date(date.getTime()+86400000);
                String dateString = simpleDateFormat.format(date);
                dateList.add(dateString);
            }
            return dateList;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> getDateList(String startDate, String endDate) {
        List<String> dateList = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = simpleDateFormat.parse(startDate);
            String dateString = startDate;
            dateList.add(dateString);
            for (int count = 0; !endDate.equals(dateString); count++){
                date = new Date(date.getTime()+86400000);
                dateString = simpleDateFormat.format(date);
                dateList.add(dateString);
            }
            return dateList;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
