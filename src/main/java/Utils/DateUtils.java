package Utils;

import service.PriceService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtils {
    public static boolean isNoPriceForToday(PriceService priceService) {
        //is today
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        if(priceService.findLatestPricedDate(priceService.findPrices()) == null) {
            return true;
        }
        System.out.println(sdf.format(priceService.findLatestPricedDate(priceService.findPrices())));
        sdf.format(new Date());
        return !sdf.format(priceService.findLatestPricedDate(priceService.findPrices())).equals(sdf.format(new Date()));
    }

    public static boolean isSameDay(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        return cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR) && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
    }

    public static boolean isNotValidDate(String inputDate) {
        String regex = "^(3[01]|[12][0-9]|0?[1-9])/(1[0-2]|0?[1-9])/(?:[0-9]{2})?[0-9]{2}$";
        //Creating a pattern object
        Pattern pattern = Pattern.compile(regex);
        //Matching the compiled pattern in the String
        Matcher matcher = pattern.matcher(inputDate);
        return !matcher.matches();
    }
    }
