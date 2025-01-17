package com.hartwig.testsupport;

import java.util.ArrayList;
import java.util.List;

import com.hartwig.io.OutputType;
import com.hartwig.patient.Sample;

public class AssertionChain {
    private final Sample flowCell;
    private final List<BAMFileAssertion> assertions = new ArrayList<>();

    AssertionChain(final Sample flowCell) {
        this.flowCell = flowCell;
    }

    public AssertionChain aligned() {
        assertions.add(new AlignmentFileAssertion(flowCell, OutputType.DUPLICATE_MARKED));
        return this;
    }

    public AssertionChain duplicatesMarked() {
        assertions.add(new DuplicateMarkedFileAssertion(flowCell));
        return this;
    }

    public void isEqualToExpected() {
        assertions.forEach(BAMFileAssertion::isEqualToExpected);
    }
}
