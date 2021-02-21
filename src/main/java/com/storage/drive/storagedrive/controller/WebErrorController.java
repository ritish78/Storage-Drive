package com.storage.drive.storagedrive.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;

public class WebErrorController implements ErrorController {

    @RequestMapping("/error")
    public String showErrorPage() {
        return "error";
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
