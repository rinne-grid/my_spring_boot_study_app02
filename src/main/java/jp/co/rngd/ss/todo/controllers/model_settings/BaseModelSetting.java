package jp.co.rngd.ss.todo.controllers.model_settings;

import org.springframework.ui.Model;

public class BaseModelSetting {
	public static Model applyHeaderFooterModel(Model model) {
		model.addAttribute("header", "common/header::header");
		model.addAttribute("footer", "common/footer::footer");
		return model;
	}

}
