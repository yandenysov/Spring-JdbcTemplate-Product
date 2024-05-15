package org.example.app.controller;

import org.example.app.entity.Product;
import org.example.app.service.ProductService;
import org.example.app.utils.Constants;
import org.example.app.utils.AppStarter;
import org.example.app.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ProductController {

    @Autowired
    AppView menuView;
    @Autowired
    ProductCreateView createView;
    @Autowired
    ProductReadView readView;
    @Autowired
    ProductReadByIdView readByIdView;
    @Autowired
    ProductUpdateView updateView;
    @Autowired
    ProductDeleteView deleteView;
    @Autowired
    ProductService service;


    public void getOption() {
        int option = Integer.parseInt(menuView.getOption());
        switch (option) {
            case 1 -> create();
            case 2 -> getAll();
            case 3 -> getById();
            case 4 -> update();
            case 5 -> delete();
            case 0 -> menuView.getOutput(Constants.APP_CLOSE_MSG);
        }
    }

    public void create() {
        try {
            String[] data = createView.getData();
            Product product = new Product(data[0], Integer.parseInt(data[1]), Double.parseDouble(data[2]));
            createView.getOutput(service.create(product));
            AppStarter.startApp();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public void getAll() {
        readView.getOutput(service.getAll());
        AppStarter.startApp();
    }

    public void getById() {
        try {
            readByIdView.getOutput(service
                    .getById(readByIdView.getData()));
            AppStarter.startApp();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public void update() {
        try {
            Map<String, String> data = updateView.getData();
            Product product = new Product(Long.parseLong(data.get("id")),
                    data.get("name"), Integer.parseInt(data.get("quota")),
                    Double.parseDouble(data.get("price")));
            updateView.getOutput(service.update(product));
            AppStarter.startApp();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public void delete() {
        try {
            deleteView.getOutput(service
                    .delete(deleteView.getData()));
            AppStarter.startApp();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
