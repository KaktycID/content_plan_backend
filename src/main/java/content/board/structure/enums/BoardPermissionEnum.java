package content.board.structure.enums;

public enum BoardPermissionEnum {

    AUTHOR("Автор"),
    EDITOR("Редактор"),
    READER("Читатель");

    private final String title;

    BoardPermissionEnum(String title) {
        this.title = title;
    }

}
