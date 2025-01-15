package com.pb.starter.component.constant;


import lombok.Getter;

@Getter
public enum Menu {
    HOME("Home", "/","mdi mdi-home",null),
    SUBJECT("SUBJECT", "/subject/main","mdi mdi-arrange-bring-forward",new SubJectSubMenu[]{
            SubJectSubMenu.SUBJECT_MAIN
    });

    private final String name;
    private final String location;
    private final String iconPath;
    private final SubJectSubMenu[] subMenus;

    Menu(String name, String location, String iconPath, SubJectSubMenu[] subMenus) {
        this.name = name;
        this.location = location;
        this.iconPath = iconPath;
        this.subMenus = subMenus;
    }

    @Getter
    public enum SubJectSubMenu{
        SUBJECT_MAIN("MAIN", "/subject/main");

        private final String name;
        private final String location;

        SubJectSubMenu(String name, String location) {
            this.name = name;
            this.location = location;
        }
    }
}
