package com.tartar_mouse_edition.idea.components.codeEditor;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import org.fxmisc.richtext.CodeArea;

public class DefaultContextMenu extends ContextMenu  {
    private MenuItem fold, unfold, print;

    public DefaultContextMenu()  {
        fold = new MenuItem( "Fold selected text" );
        fold.setOnAction( AE -> { hide(); fold(); } );

        unfold = new MenuItem( "Unfold from cursor" );
        unfold.setOnAction( AE -> { hide(); unfold(); } );

        print = new MenuItem( "Print" );
        print.setOnAction( AE -> { hide(); print(); } );

        getItems().addAll( fold, unfold, print );
    }

    private void fold() {
        ((CodeArea) getOwnerNode()).foldSelectedParagraphs();
    }

    private void unfold() {
        CodeArea area = (CodeArea) getOwnerNode();
        area.unfoldParagraphs( area.getCurrentParagraph() );
    }

    private void print() {
        System.out.println( ((CodeArea) getOwnerNode()).getText() );
    }
}