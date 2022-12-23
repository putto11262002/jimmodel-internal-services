package com.jimmodel.services.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MonthTest {

    @Test
    void compareTo() {
        Month monthBeforeSameYear = new Month(2022, 2);
        Month monthAfterSameYear = new Month(2022, 4);
        assertThat(monthBeforeSameYear.compareTo(monthAfterSameYear)).isLessThan(0);
        assertThat(monthAfterSameYear.compareTo(monthBeforeSameYear)).isGreaterThan(0);

        Month sameMonthYearBefore = new Month(2020, 11);
        Month sameMonthYearAfter = new Month(2021, 11);
        assertThat(sameMonthYearBefore.compareTo(sameMonthYearAfter)).isLessThan(0);
        assertThat(sameMonthYearAfter.compareTo(sameMonthYearBefore)).isGreaterThan(0);

        Month sameMonthSameYear = new Month(2022, 3);
        assertThat(sameMonthSameYear.compareTo(sameMonthSameYear)).isEqualTo(0);
    }

    @Test
    void testToString() {
        Month month1 = new Month(2022, 3);
        assertThat(month1.toString()).isEqualTo(2022 + "-" + 3);
    }
}