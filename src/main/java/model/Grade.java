package model;

public enum Grade {
    A("Grade A"), B("Grade B"), C("Grade C"), REJECTED("Rejected");

    String title;

    Grade(String title) {
        this.title = title;
    }
}
