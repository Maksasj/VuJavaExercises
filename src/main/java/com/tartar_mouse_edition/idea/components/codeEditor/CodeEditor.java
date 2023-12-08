package com.tartar_mouse_edition.idea.components.codeEditor;

import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;

import java.util.Collection;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeEditor extends CodeArea {
    public StyleSpans<Collection<String>> computeHighlighting(String text) {
        Matcher matcher = EditorCodePattens.PATTERN.matcher(text);
        int lastKwEnd = 0;
        StyleSpansBuilder<Collection<String>> spansBuilder
                = new StyleSpansBuilder<>();
        while(matcher.find()) {
            String styleClass =
                matcher.group("KEYWORD") != null ? "keyword" :
                matcher.group("PAREN") != null ? "paren" :
                matcher.group("BRACE") != null ? "brace" :
                matcher.group("BRACKET") != null ? "bracket" :
                matcher.group("SEMICOLON") != null ? "semicolon" :
                matcher.group("STRING") != null ? "string" :
                matcher.group("COMMENT") != null ? "comment" : "clear_text";

            spansBuilder.add(Collections.emptyList(), matcher.start() - lastKwEnd);
            spansBuilder.add(Collections.singleton(styleClass), matcher.end() - matcher.start());
            lastKwEnd = matcher.end();
        }
        spansBuilder.add(Collections.emptyList(), text.length() - lastKwEnd);
        return spansBuilder.create();
    }

    public CodeEditor() {
        super("-- write your code there\n" +
            "\n" +
            "function path()\n" +
            "\n" +
            "end\n");

        setParagraphGraphicFactory(LineNumberFactory.get(this));
        getVisibleParagraphs().addModificationObserver(new VisibleParagraphStyler<>(this, this::computeHighlighting));

        final Pattern whiteSpace = Pattern.compile( "^\\s+" );
        addEventHandler( KeyEvent.KEY_PRESSED, KE -> {
            if ( KE.getCode() == KeyCode.ENTER ) {
                int caretPosition = getCaretPosition();
                int currentParagraph = getCurrentParagraph();
                Matcher m0 = whiteSpace.matcher(getParagraph( currentParagraph-1 ).getSegments().get( 0 ));
                if ( m0.find() ) Platform.runLater( () -> insertText(caretPosition, m0.group()));
            }
        });
    }
}
