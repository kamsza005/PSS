package com.example.pss.gui;

import com.example.pss.service.DelegationService;
import com.example.pss.service.UserService;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.pss.model.User;


@Title("Delegation")
@SpringUI(path = "/logged")
public class Panel extends UI {

    @Autowired
    private UserService userService;

    @Autowired
    private DelegationService delegationService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private User loggedUser;

    private VerticalLayout layout;
    private HorizontalLayout uzytkownik;
    private HorizontalLayout layout2;
    private VerticalLayout przyciski;
    private VerticalLayout informacje;
    private Label pracownik;
    private Label info;

    @Override
    protected void init(VaadinRequest request) {
        loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        layout = new VerticalLayout();
        pracownik=new Label(loggedUser.getName() + " " + loggedUser.getLastName());

        uzytkownik = new HorizontalLayout();
        uzytkownik.addComponent(pracownik);

        layout2 = new HorizontalLayout();
        przyciski = new VerticalLayout();
        info= new Label();


        Button delegacje = new Button("Delegacje");
        delegacje.setWidth("140");
        delegacje.addClickListener(clickEvent -> {
                    informacje.removeAllComponents();
                    info=new Label("Delegacje");
                    informacje.addComponents(info,new Delegacje(userService, delegationService, loggedUser.getId()));
                }
        );

        Button edycja = new Button("Edycja");
        edycja.setWidth("140");
        edycja.addClickListener(clickEvent -> {
                    informacje.removeAllComponents();
                    info=new Label("Edycja");
                    informacje.addComponents(info,new Edycja(userService, loggedUser.getId()));
                }
        );


        Button zmianaHasla = new Button("Zmiana hasla");
        zmianaHasla.setWidth("140");
        zmianaHasla.addClickListener(clickEvent -> {
                    informacje.removeAllComponents();
                    info=new Label("Zmiana hasla");
                    informacje.addComponents(info,new ZmianaHasla(userService, passwordEncoder, loggedUser.getId()));
                }
        );

        Button wylogowanie = new Button("Wyloguj sie");
        wylogowanie.setWidth("140");
        wylogowanie.addClickListener(clickEvent -> {
                    getUI().getPage().setLocation("/logout");
                }
        );

        przyciski.addComponents(delegacje, edycja, zmianaHasla, wylogowanie);

        informacje = new VerticalLayout();
        layout2.addComponents(przyciski, informacje);

        layout.addComponents(uzytkownik, layout2);
        layout.setSizeUndefined();
        setContent(layout);

    }
}
