package jp.co.rngd.ss.todo.controllers.model_settings;

import org.springframework.ui.Model;

public class AppModelSetting {
    public static Model applyMenuModel(Model model) {
        model.addAttribute("left_menu", "common/left_menu::left_menu");
        return model;
    }
}
