package com.hartwig.patient;

import org.immutables.value.Value;

@Value.Immutable
public interface Lane extends FileSystemEntity, Named {

    @Override
    String directory();

    @Override
    String name();

    String readsPath();

    String matesPath();

    String flowCellId();

    String index();

    String suffix();

    default String recordGroupId() {
        String[] split = name().split("_");
        return String.format("%s_%s_%s_%s_%s", split[0], flowCellId(), index(), split[1], suffix());
    }

    @Override
    default void accept(FileSystemVisitor visitor) {
        visitor.visit(this);
    }

    static ImmutableLane.Builder builder() {
        return ImmutableLane.builder();
    }
}
