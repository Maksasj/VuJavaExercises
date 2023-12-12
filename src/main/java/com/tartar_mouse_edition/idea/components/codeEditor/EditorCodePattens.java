package com.tartar_mouse_edition.idea.components.codeEditor;

import java.util.regex.Pattern;

public class EditorCodePattens {
    public static final String[] KEYWORDS = new String[] {
            "and", "break", "do", "else", "elseif", "end",
            "for", "function", "if", "in", "local",
            "not", "or", "repeat", "return", "then",
            "until", "while",
    };

    public static final String[] RATKEYWORDS = new String[] {
            "rat", "walk", "rotate_left", "rotate_right",
            "look", "look_left", "look_right", "say",
            "look_left", "look_right", "cheese_distance",
            "get_x", "get_y"
    };

    public static final String[] VALUEKEYWORDS = new String[] {
            "true", "nil", "false"
    };

    public static final String KEYWORD_PATTERN = "\\b(" + String.join("|", KEYWORDS) + ")\\b";
    public static final String RATKEYWORDS_PATTERN = "\\b(" + String.join("|", RATKEYWORDS) + ")\\b";
    public static final String VALUEKEYWORDS_PATTERN = "\\b(" + String.join("|", VALUEKEYWORDS) + ")\\b";
    public static final String PAREN_PATTERN = "\\(|\\)";
    public static final String STRING_PATTERN = "\"([^\"\\\\]|\\\\.)*\"";
    public static final String COMMENT_PATTERN = "--[^\n]*";  // for visible paragraph processing (line by line)

    public static final Pattern PATTERN = Pattern.compile(
            "(?<KEYWORD>" + KEYWORD_PATTERN + ")"
            + "|(?<RATKEYWORDS>" + RATKEYWORDS_PATTERN + ")"
            + "|(?<VALUEKEYWORDS>" + VALUEKEYWORDS_PATTERN + ")"
            + "|(?<PAREN>" + PAREN_PATTERN + ")"
            + "|(?<STRING>" + STRING_PATTERN + ")"
            + "|(?<COMMENT>" + COMMENT_PATTERN + ")"
    );
}
