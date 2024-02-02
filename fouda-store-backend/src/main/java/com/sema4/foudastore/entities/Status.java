package com.sema4.foudastore.entities;

import java.util.Collections;
import java.util.List;

public enum Status {
    NEW {
        @Override
        public List<Status> possibleStatus() {
            return List.of(FORWARDED,CANCELED);
        }
    },
    FORWARDED{
        @Override
        public List<Status> possibleStatus() {
            return List.of(CANCELED,DELIVERED);
        }
    },
    CANCELED{
        @Override
        public List<Status> possibleStatus() {
            return Collections.emptyList();
        }
    },
    DELIVERED{
        @Override
        public List<Status> possibleStatus() {
            return Collections.emptyList();
        }
    };

    public abstract List<Status> possibleStatus();
}
