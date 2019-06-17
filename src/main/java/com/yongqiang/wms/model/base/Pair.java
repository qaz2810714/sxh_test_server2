package com.yongqiang.wms.model.base;

import lombok.NonNull;

import java.beans.ConstructorProperties;

public final class Pair<S, T> {
    @NonNull
    private final S first;
    @NonNull
    private final T second;

    public static <S, T> Pair<S, T> of(S first, T second) {
        return new Pair(first, second);
    }

    public S getFirst() {
        return this.first;
    }

    public T getSecond() {
        return this.second;
    }

    public String toString() {
        return "Pair(first=" + this.getFirst() + ", second=" + this.getSecond() + ")";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Pair)) {
            return false;
        } else {
            Pair<?, ?> other = (Pair)o;
            Object this$first = this.getFirst();
            Object other$first = other.getFirst();
            if (this$first == null) {
                if (other$first != null) {
                    return false;
                }
            } else if (!this$first.equals(other$first)) {
                return false;
            }

            Object this$second = this.getSecond();
            Object other$second = other.getSecond();
            if (this$second == null) {
                if (other$second != null) {
                    return false;
                }
            } else if (!this$second.equals(other$second)) {
                return false;
            }

            return true;
        }
    }

    @ConstructorProperties({"first", "second"})
    private Pair(@NonNull S first, @NonNull T second) {
        if (first == null) {
            throw new IllegalArgumentException("first is null");
        } else if (second == null) {
            throw new IllegalArgumentException("second is null");
        } else {
            this.first = first;
            this.second = second;
        }
    }
}
