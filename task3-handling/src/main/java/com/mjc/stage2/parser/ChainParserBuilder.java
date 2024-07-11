package com.mjc.stage2.parser;

import java.util.ArrayList;
import java.util.List;

public class ChainParserBuilder {
    private AbstractTextParser firstParser;

    public ChainParserBuilder setParser(AbstractTextParser parser) {
        if (firstParser == null) {
            firstParser = parser;
        } else {
            AbstractTextParser temp = firstParser;
            while (temp.nextParser != null) {
                temp = temp.nextParser;
            }
            temp.setNextParser(parser);
        }
        return this;
    }

    public AbstractTextParser build() {
        return firstParser;
    }
}
