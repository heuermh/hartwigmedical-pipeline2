package com.hartwig.testsupport;

import com.hartwig.patient.ImmutableLane;
import com.hartwig.patient.Lane;

public class Lanes {

    public static ImmutableLane.Builder emptyBuilder() {
        return Lane.builder().directory("").name("").readsPath("").matesPath("").flowCellId("").index("").suffix("");
    }
}