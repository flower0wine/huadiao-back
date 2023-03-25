import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Function
 * @Start_Time 2023 01 27 10:14
 */
public class Test {

    public static void main(String[] args) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH时mm分ss秒");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime localDateTime = now.plusDays(1);
        Date date = new Date(localDateTime.getYear() - 1900, localDateTime.getMonthValue(), localDateTime.getDayOfMonth(), 0, 0);
        System.out.println(localDateTime.getYear() + " " +  localDateTime.getMonthValue() + " " + localDateTime.getDayOfMonth() +
                " " + localDateTime.getHour() + " " + localDateTime.getMinute());
        System.out.println(simpleDateFormat.format(date));
    }
}
