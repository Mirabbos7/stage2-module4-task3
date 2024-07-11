package com.mjc.stage2.parser;


import com.mjc.stage2.entity.AbstractTextComponent;
import com.mjc.stage2.entity.SymbolLeaf;
import com.mjc.stage2.entity.TextComponent;
import com.mjc.stage2.entity.TextComponentType;

public class WordParser extends AbstractTextParser {
    @Override
    public void parse(AbstractTextComponent abstractTextComponent, String string) {
        for (char c : string.toCharArray()) {
            abstractTextComponent.add(new SymbolLeaf(c));
        }
    }
    public static void main(String[] args) {
        ChainParserBuilder parserBuilder = new ChainParserBuilder();
        AbstractTextParser parser = parserBuilder
                .setParser(new LexemeParser())
                .setParser(new WordParser())
                .build();

        TextComponent sentence = new TextComponent(TextComponentType.SENTENCE);
        String sentenceString = "Hello world!";
        parser.parse(sentence, sentenceString);

        System.out.println(sentence.operation());
    }
}
