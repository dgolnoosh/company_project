import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws Exception{
//        Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse("2021-07-25");
        Date date1 = new Date();
        LocalDate localDate = LocalDate.parse("2021-07-25", DateTimeFormatter.ISO_LOCAL_DATE);
        java.sql.Date date = java.sql.Date.valueOf(localDate);
    }
}
