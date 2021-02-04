package ru.academy.mytesttask;

public class Item implements RowType{
    String text;


    public String getText() {
        return text;
    }

    public Item(String text) {
        this.text = text;
    }
}
