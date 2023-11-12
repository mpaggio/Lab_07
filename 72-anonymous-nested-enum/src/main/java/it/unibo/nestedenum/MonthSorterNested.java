package it.unibo.nestedenum;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.lang.String;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {
    
    @Override
    public Comparator<String> sortByDays() {
        return new SortByDate();
    }

    @Override
    public Comparator<String> sortByOrder() {
        return new SortByMonthOrder();
    }

    private enum Month{
        JANUARY("JANUARY", 31),
        FEBRUARY("FEBRUARY", 28),
        MARCH("MARCH", 31),
        APRIL("APRIL", 30),
        MAY("MAY", 31),
        JUNE("JUNE", 30),
        JULY("JULY", 31),
        AUGUST("AUGUST", 31),
        SEPTEMBER("SEPTEMBER", 30),
        OCTOBER("OCTOBER", 31),
        NOVEMBER("NOVEMBER", 30),
        DICEMBER("DICEMBER", 31);

        private final int daysOfTheMonth;
        private final String nameOfTheMonth;

        private Month(final String name, final int days){
            this.daysOfTheMonth = days;
            this.nameOfTheMonth = name;
        }

        public static Month fromString(final String stringedMonth) {
            Month found = null;
            List<Month> occurencyList = new ArrayList<>();
            for(Month month : values()){
                if(month.nameOfTheMonth.startsWith(stringedMonth.toUpperCase())){
                    occurencyList.add(month);
                }
            }
            if(occurencyList.size() > 1){
                throw new IllegalArgumentException("Ambiguous string");
            }
            else if(occurencyList.size() == 1){
                found = occurencyList.get(0);
            }
            else if(occurencyList.isEmpty()){
                throw new IllegalArgumentException("The given string is not a month");
            }
            return found;
        }
    }
    
    private static class SortByDate implements Comparator<String>{

        @Override
        public int compare(final String o1, final String o2) {
            final Month firstMonth = Month.fromString(o1);
            final Month secondMonth = Month.fromString(o2);
            return Integer.compare(secondMonth.daysOfTheMonth, firstMonth.daysOfTheMonth);
        }

    }

    private static class SortByMonthOrder implements Comparator<String>{

        @Override
        public int compare(final String o1, final String o2) {
            return Month.fromString(o1).compareTo(Month.fromString(o2));
        }

    }
    
}
