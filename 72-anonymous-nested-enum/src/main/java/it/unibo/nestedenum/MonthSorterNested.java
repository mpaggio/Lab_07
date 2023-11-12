package it.unibo.nestedenum;

import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;

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
        JANUARY(31),
        FEBRUARY(28),
        MARCH(31),
        APRIL(30),
        MAY(31),
        JUNE(30),
        JULY(31),
        AUGUST(31),
        SEPTEMBER(30),
        OCTOBER(31),
        NOVEMBER(30),
        DECEMBER(31);

        private final int daysOfTheMonth;

        Month(final int days){
            this.daysOfTheMonth = days;
        }

        public static Month fromString(final String stringedMonth) {
            Objects.requireNonNull(stringedMonth);
            Month found = null;
            int occurrency = 0;
            for(final Month month : Month.values()){
                if(month.toString().toLowerCase(Locale.ROOT).startsWith(stringedMonth.toLowerCase(Locale.ROOT))){
                    occurrency++;
                    found = month;
                }
            }
            if(occurrency > 1){
                throw new IllegalArgumentException("Ambiguous string");
            }
            if(found == null || occurrency == 0){
                throw new IllegalArgumentException("The given string is not a month");
            }
            return found;
        }
    }
    
    private class SortByDate implements Comparator<String>{

        @Override
        public int compare(final String o1, final String o2) {
            return Month.fromString(o1).daysOfTheMonth - Month.fromString(o2).daysOfTheMonth;
        }

    }

    private class SortByMonthOrder implements Comparator<String>{

        @Override
        public int compare(final String o1, final String o2) {
            return Month.fromString(o1).compareTo(Month.fromString(o2));
        }

    }
    
}
